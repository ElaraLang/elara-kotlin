import type.*

class ElaraContext
{
    val variables = mutableMapOf(
        "print" to ImpureFunctionType(StringType, UnitType).let { type ->
            Value(type,
                ElaraFunction(type) { _, args ->

                    println(args[0].value)
                    Value(UnitType, Unit)
                })
        },
        "getLine" to ImpureFunctionType(UnitType, StringType).let { type ->
            Value(type,
                ElaraFunction(type) { _, _ ->
                    val line = readLine()
                    if (line == null)
                        Value(StringType, "")
                    else
                        Value(StringType, line)
                })
        },
        "+" to PureFunctionType(IntType, PureFunctionType(IntType, IntType)).let { type ->
            Value(type,
                ElaraFunction(type) { _, args ->
                    val first = args[0].value
                    val second = args[1].value
                    if (first is String || second is String)
                    {
                        Value(StringType, first.toString() + second.toString())
                    } else if (first is Number && second is Number)
                    {
                        Value(if (first is Int) IntType else FloatType, first.toDouble() + second.toDouble())
                    } else
                    {
                        throw IllegalArgumentException("Cannot add ${first.javaClass.name} and ${second.javaClass.name}")
                    }
                })
        },
    )

    val instances = mutableMapOf<TypeClass, TypeClassInstance>()

    val types = mutableMapOf(
        "Int" to IntType,
        "Float" to FloatType,
        "Char" to CharType
    )

}
