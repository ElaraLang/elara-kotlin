package type

import runtime.Value
import runtime.scope.ElaraContext

sealed interface ElaraType
{
    fun isAssignableTo(other: ElaraType): Boolean
    {
        return this == other
    }

    fun concreteType(): ElaraType
    {
        return this
    }
}

object UnitType : ElaraType
{
    override fun toString(): String
    {
        return "()"
    }
}

object IntType : ElaraType
{
    override fun toString(): String
    {
        return "Int"
    }
}

object FloatType : ElaraType
{
    override fun toString(): String
    {
        return "Float"
    }
}

data class ListType(val elemType: ElaraType) : ElaraType
{
    override fun toString(): String
    {
        return "[$elemType]"
    }
}

data class Tuple2Type(val aType: ElaraType, val bType: ElaraType) : ElaraType
{
    override fun toString(): String
    {
        return "($aType, $bType)"
    }

    data class Tuple2<A, B>(val a: A, val b: B)
    {
        override fun toString(): String
        {
            return "($a, $b)"
        }
    }
}

data class DelegateType(val name: String, val delegateType: ElaraType) : ElaraType by delegateType
{
    override fun toString(): String
    {
        return "name ($delegateType)"
    }
}


object CharType : ElaraType
{
    override fun toString(): String
    {
        return "Char"
    }
}


sealed class FunctionType(open val input: ElaraType, open val output: ElaraType) : ElaraType
data class PureFunctionType(override val input: ElaraType, override val output: ElaraType) : FunctionType(input, output)
{
    override fun toString(): String
    {
        return "$input -> $output"
    }
}

data class ImpureFunctionType(override val input: ElaraType, override val output: ElaraType) :
    FunctionType(input, output)
{
    override fun toString(): String
    {
        return "$input => $output"
    }
}

class ElaraFunction(val type: FunctionType, val parameterName: String, val body: (ElaraContext, Value) -> Value)
{
    override fun toString(): String
    {
        return "#Function"
    }

    fun call(context: ElaraContext, argument: Value): Value
    {
        val name = type.toString()
        val scope = context.enterScope(name)
        context.registerVariable(parameterName, argument)
        val res = body(context, argument)
        if (res.type is FunctionType)
        {
            // don't exit the scope
            return res
        }
        // keep popping until we exit this scope
        if (context.highestScope() === scope)
        {
            context.exitScope()
            return res
        }
        do {
            context.exitScope()
        }
        while (context.highestScope() !== scope)
        return res
    }
}


val StringType = ListType(CharType)
