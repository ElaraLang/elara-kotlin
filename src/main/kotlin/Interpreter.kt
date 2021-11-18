import runtime.ConsList.Companion.toConsList
import runtime.Value
import runtime.scope.ElaraContext
import type.*

fun interpret(elaraFile: ElaraParser.ElaraFileContext, context: ElaraContext = ElaraContext()): ElaraContext
{
    elaraFile.elaraLine().forEach {
        interpret(it, context)
    }
    return context
}

private fun interpret(line: ElaraParser.ElaraLineContext, context: ElaraContext): Value?
{
    when (line)
    {
        is ElaraParser.StatementLineContext -> interpret(line.statement(), context)
        is ElaraParser.ExpressionLineContext -> return interpret(line.expression(), context)
        else -> throw UnsupportedOperationException(line.javaClass.name)
    }
    return null
}

fun interpret(statement: ElaraParser.StatementContext, context: ElaraContext)
{
    when (statement)
    {
        is ElaraParser.TypeDeclarationStatementContext -> interpret(statement.typeDeclaration(), context)
        is ElaraParser.TypeClassDeclarationStatementContext -> interpret(statement.typeClassDeclaration(), context)
        is ElaraParser.TypeClassInstanceDeclarationStatementContext -> interpret(statement.typeClassInstanceDeclaration(),
            context)
        is ElaraParser.VariableDeclarationStatementContext -> interpret(statement.variable(), context)
        else -> throw UnsupportedOperationException(statement.javaClass.name)
    }
}

private fun interpret(
    typeClassInstanceDeclaration: ElaraParser.TypeClassInstanceDeclarationContext,
    context: ElaraContext,
)
{
    val clazz = context.getType(typeClassInstanceDeclaration.TypeIdentifier().text) as? TypeClass
        ?: throw IllegalArgumentException("Not a type class")
    val type = getType(typeClassInstanceDeclaration.type(), context).concreteType()
    val instance = TypeClassInstance(clazz, type,
        typeClassInstanceDeclaration.typeClassInstanceBody().variable()
            .associate { variable ->
                val name = getVariableIdentifier(variable.letClause().variableIdentifier())
                clazz.members.find { it.name == name }!! to interpret(variable.letClause().letBody().expression(),
                    context)
            }
    )
    context.registerInstance(clazz, instance)
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
            it.defClause().variableIdentifier().operatorVariable()?.operatorIdentifier()?.text ?: it.defClause()
                .variableIdentifier().text
        val type = getType(it.defClause().type(), context)
        TypeClass.TypeClassMember(name, type)
    }
    val typeClass = TypeClass(typeClassName, members)
    members.forEach {
        val type = TypeClassConstraint(typeClass, parameter, it.type)
        context.registerVariable(it.name, Value(
            type,
            ElaraFunction(it.type as FunctionType, "a") { context, arg ->
                val argType = arg.type
                val instance = context.getInstance(typeClass, argType)
                (instance.members[it]!!.value as ElaraFunction).call(context, arg)
            }
        ))
    }

    context.registerType(typeClassName, typeClass)
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
    context.registerType(name, type)
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
    val parameters = let.VarIdentifier()
    val value = if (parameters.isNotEmpty())
    {
        var paramType: ElaraType
        paramType = GenericType(parameters.first().text)

        parameters.drop(1).forEach {
            paramType = PureFunctionType(paramType, GenericType(it.text))
        }
        val fType = PureFunctionType(paramType, GenericType("returns"))
        val function = ElaraFunction(fType, parameters[0].text) { context2, _ ->
            interpret(let.letBody(), context2)
        }
        Value(fType, function)
    } else
    {
        val value = interpret(let.letBody(), context)
        if (def != null)
        {
            val type = getType(def.type(), context)
            if (!type.isAssignableTo(value.type))
            {
                throw IllegalArgumentException("Type mismatch, expected $type but got ${value.type}")
            }
        }
        value
    }
    context.registerVariable(varName, value)
}

private fun interpret(letBody: ElaraParser.LetBodyContext, context: ElaraContext): Value
{
    return if (letBody.block() != null)
    {
        interpret(letBody.block(), context)
    } else
    {
        interpret(letBody.expression(), context)
    }
}

private fun interpret(block: ElaraParser.BlockContext, context: ElaraContext): Value
{
    return block.elaraLine().mapNotNull {
        interpret(it, context)
    }.first()
}

private fun getType(type: ElaraParser.TypeContext, context: ElaraContext): ElaraType
{
    return when (type)
    {
        is ElaraParser.SimpleTypeContext -> context.getType(type.text)
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
        is ElaraParser.StringExpressionContext -> Value(StringType,
            expression.StringLiteral().text.removeSurrounding("\"").toConsList())
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
            val function = interpret(expression.expression(0), context)
            if (function.type !is FunctionType)
            {
                throw IllegalArgumentException("Not a function")
            }
            val arg = interpret(expression.arg, context)
            val f = (function.value as ElaraFunction)
            f.call(context, arg)
        }
        is ElaraParser.VariableExpressionContext ->
        {
            val variable = expression.variableIdentifier().operatorVariable()?.operatorIdentifier()?.text
                ?: expression.variableIdentifier().text
            context.getVariable(variable)
        }
        is ElaraParser.OperatorApplicationExpressionContext ->
        {
            val left = interpret(expression.expression().first(), context)
            val right = interpret(expression.expression().last(), context)
            val op = runCatching { context.getVariable(expression.operatorIdentifier().text) }
                .getOrNull()
                ?: throw IllegalArgumentException("Unknown operator: ${expression.operatorIdentifier().text}")

            ((op.value as ElaraFunction).call(context, left).value as ElaraFunction).call(context, right)
        }
        else -> throw UnsupportedOperationException(expression.javaClass.name)
    }
}
