import type.*

fun interpret(elaraFile: ElaraParser.ElaraFileContext, context: ElaraContext = ElaraContext()): ElaraContext
{
    elaraFile.elaraLine().forEach {
        interpret(it, context)
    }
    return context
}

private fun interpret(line: ElaraParser.ElaraLineContext, context: ElaraContext)
{
    when (line)
    {
        is ElaraParser.VariableLineContext -> interpret(line.variable(), context)
        is ElaraParser.TypeDeclarationLineContext -> interpret(line.typeDeclaration(), context)
        is ElaraParser.ExpressionLineContext -> interpret(line.expression(), context)
        is ElaraParser.TypeClassDeclarationLineContext -> interpret(line.typeClassDeclaration(), context)
        is ElaraParser.TypeClassInstanceDeclarationLineContext -> interpret(line.typeClassInstanceDeclaration(),
            context)
        else -> throw UnsupportedOperationException(line.javaClass.name)
    }

}

private fun interpret(
    typeClassInstanceDeclaration: ElaraParser.TypeClassInstanceDeclarationContext,
    context: ElaraContext,
)
{
    val clazz = context.types[typeClassInstanceDeclaration.TypeIdentifier().text] as? TypeClass
        ?: throw IllegalArgumentException("Not a type class")
    val type = getType(typeClassInstanceDeclaration.type(), context)
    val instances = (context.instances[clazz] ?: mapOf()).toMutableMap()
    val instance = TypeClassInstance(clazz, type,
        typeClassInstanceDeclaration.typeClassInstanceBody().variable()
            .associate {
                val name = getVariableIdentifier(it.letClause().variableIdentifier())
                clazz.members.find { it.name == name }!! to interpret(it.letClause().expression(), context)
            }
    )
    instances[type] = instance
    context.instances[clazz] = instances
}

private fun getVariableIdentifier(identifier: ElaraParser.VariableIdentifierContext): String
{
    return identifier.operatorVariable()?.operatorIdentifier()?.text ?: identifier.text
}

private fun interpret(typeClassDeclaration: ElaraParser.TypeClassDeclarationContext, context: ElaraContext)
{
    val typeClassName = typeClassDeclaration.TypeIdentifier().text
    val parameter = typeClassDeclaration.typeIdentifier().text

    val body = typeClassDeclaration.typeClassBody()
    val members = body.typeClassValue().map {
        val name =
            it.defClause().variableIdentifier().operatorVariable()?.operatorIdentifier()?.text ?: it.defClause().variableIdentifier().text
        val type = getType(it.defClause().type(), context)
        TypeClass.TypeClassMember(name, type)
    }
    val typeClass = TypeClass(typeClassName, members)
    members.forEach {
        val type = TypeClassConstraint(typeClass, parameter, it.type)
        context.variables[it.name] = Value(
            type,
            ElaraFunction(it.type as FunctionType, { context, args ->
                val argType = args[0].type
                val instance = context.instances[typeClass]?.get(argType)
                    ?: throw IllegalArgumentException("No instance of $typeClassName for $type")
                (instance.members[it]!!.value as ElaraFunction).call(context, args)
            })
        )
    }

    context.types[typeClassName] = typeClass
}

private fun interpret(typeDeclarationContext: ElaraParser.TypeDeclarationContext, context: ElaraContext)
{
    val name = typeDeclarationContext.TypeIdentifier().text
    val generics = typeDeclarationContext.typeIdentifier().map { it.text } // yeah idk what to do with these for now
    val type = when (val value = typeDeclarationContext.typeDeclarationValue())
    {
        is ElaraParser.TypeAliasValueContext ->
        {
            DelegateType(name, getType(value.typeAlias().type(), context))
        }
        else -> throw UnsupportedOperationException(value.javaClass.name)
    }
    context.types[name] = type
}

private fun interpret(variable: ElaraParser.VariableContext, context: ElaraContext)
{
    val def = variable.defClause()
    val let = variable.letClause()
    val varName =
        let.variableIdentifier().operatorVariable()?.operatorIdentifier()?.text ?: let.variableIdentifier().text
    if (def != null && varName != def.variableIdentifier().text)
    {
        throw IllegalArgumentException("Def name does not match variable name")
    }
    val value = interpret(let.expression(), context)
    if (def != null)
    {
        val type = getType(def.type(), context)
        if (!type.isAssignableTo(value.type))
        {
            throw IllegalArgumentException("Type mismatch, expected $type but got ${value.type}")
        }
    }
    context.variables[varName] = value
}

private fun getType(type: ElaraParser.TypeContext, context: ElaraContext): ElaraType
{
    return when (type)
    {
        is ElaraParser.SimpleTypeContext -> context.types[type.text]
            ?: throw IllegalArgumentException("Unknown type: ${type.text}")
        is ElaraParser.UnitTypeContext -> UnitType
        is ElaraParser.ListTypeContext -> ListType(getType(type.type(), context))
        is ElaraParser.PureFunctionTypeContext -> PureFunctionType(
            getType(type.type()[0], context),
            getType(type.type()[1], context))
        is ElaraParser.GenericTypeContext -> GenericType(type.typeIdentifier().text)
        else -> throw UnsupportedOperationException(type.javaClass.name)
    }
}

private fun interpret(expression: ElaraParser.ExpressionContext, context: ElaraContext): Value
{
    return when (expression)
    {
        is ElaraParser.ParenExpressionContext -> interpret(expression.expression(), context)
        is ElaraParser.UnitExpressionContext -> Value(UnitType, Unit)
        is ElaraParser.StringExpressionContext -> Value(ListType(CharType),
            expression.StringLiteral().text.removeSurrounding("\""))
        is ElaraParser.IntExpressionContext -> Value(IntType, expression.IntegerLiteral().text.toInt())
        is ElaraParser.CharExpressionContext -> Value(CharType, expression.CharLiteral().text[1])
        is ElaraParser.FloatExpressionContext -> Value(FloatType, expression.FloatLiteral().text.toFloat())
        is ElaraParser.ListExpressionContext ->
        {
            val values = expression.expression().map { interpret(it, context) }
            val types = values.map { it.type }
            // TODO proper type inference hindley-milner stuff
            if (types.any { types.any { other -> it.isAssignableTo(other).not() } })
            {
                throw IllegalArgumentException("List is not homogenous (${types.joinToString(", ")}")
            }
            val type = ListType(types.first())
            Value(type, values)
        }
        is ElaraParser.TupleExpressionContext ->
        {
            val length = expression.expression().size
            if (length <= 1)
            {
                throw IllegalArgumentException("Tuple too short")
            }
            val values = expression.expression().map { interpret(it, context) }
            val types = values.map { it.type }
            val type = when (length)
            {
                2 -> Tuple2Type(types[0], types[1])
                else -> throw UnsupportedOperationException("Tuple too long")
            }

            val value = when (length)
            {
                2 -> Tuple2Type.Tuple2(values[0], values[1])
                else -> throw UnsupportedOperationException("Tuple too long")
            }
            Value(type, value)
        }
        is ElaraParser.FunctionApplicationExpressionContext ->
        {
            val function = interpret(expression.expression().first(), context)
            if (function.type !is FunctionType)
            {
                throw IllegalArgumentException("Not a function")
            }
            val args = expression.expression().drop(1).map { interpret(it, context) }
            (function.value as ElaraFunction).call(context, args)
        }
        is ElaraParser.VariableExpressionContext ->
        {
            val variable = expression.variableIdentifier().operatorVariable()?.operatorIdentifier()?.text
                ?: expression.variableIdentifier().text
            context.variables[variable]
                ?: throw IllegalArgumentException("Unknown variable: $variable")
        }
        is ElaraParser.OperatorApplicationExpressionContext ->
        {
            val left = interpret(expression.expression().first(), context)
            val right = interpret(expression.expression().last(), context)
            val op = context.variables[expression.operatorIdentifier().text]
                ?: throw IllegalArgumentException("Unknown operator: ${expression.operatorIdentifier().text}")

            (op.value as ElaraFunction).call(context, listOf(left, right))
        }
        else -> throw UnsupportedOperationException(expression.javaClass.name)
    }
}
