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
        "++" to GenericType("a").let { a ->
            PureFunctionType(ListType(a), PureFunctionType(a, a)).let { type ->
                Value(type, ElaraFunction(type) { _, args ->
                    val first = args[0].value as List<*>
                    val second = args[1].value as List<*>
                    val copy = first + second
                    Value(a, copy)
                })
            }
        }
    )


    val instances = mutableMapOf<TypeClass, Map<ElaraType, TypeClassInstance>>()

    val types = mutableMapOf(
        "Int" to IntType,
        "Float" to FloatType,
        "Char" to CharType
    )

}
