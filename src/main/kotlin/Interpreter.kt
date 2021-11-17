import type.ElaraType
import type.IntType
import type.StringType
import type.UnitType

fun interpret(elaraFile: ElaraParser.ElaraFileContext, context: ElaraContext = ElaraContext()): ElaraContext
{
    elaraFile.line().forEach {
        interpret(it.elaraLine(), context)
    }
    return context
}

private fun interpret(line: ElaraParser.ElaraLineContext, context: ElaraContext) = when (line)
{
    is ElaraParser.VariableLineContext -> interpret(line.variable(), context)
    else -> throw UnsupportedOperationException(line.javaClass.name)
}

private fun interpret(variable: ElaraParser.VariableContext, context: ElaraContext)
{
    val def = variable.defClause()
    val let = variable.letClause()
    val varName = let.VarIdentifier().text
    if (def != null && varName != def.VarIdentifier().text)
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

private fun getType(type: ElaraParser.TypeContext, context: ElaraContext): ElaraType<*>
{
    return when (type)
    {
        is ElaraParser.SimpleTypeContext -> context.types[type.text]
            ?: throw IllegalArgumentException("Unknown type: ${type.text}")
        is ElaraParser.UnitTypeContext -> UnitType
        else -> throw UnsupportedOperationException(type.javaClass.name)
    }
}

private fun interpret(expression: ElaraParser.ExpressionContext, context: ElaraContext): Value<*>
{
    return when (expression)
    {
        is ElaraParser.UnitExpressionContext -> Value(UnitType, Unit)
        is ElaraParser.StringExpressionContext -> Value(StringType, expression.StringLiteral().text)
        is ElaraParser.IntExpressionContext -> Value(IntType, expression.IntegerLiteral().text.toInt())
        else -> throw UnsupportedOperationException(expression.javaClass.name)
    }
}
