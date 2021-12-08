package runtime.scope

import runtime.ConsList
import runtime.ConsList.Companion.toConsList
import runtime.Value
import type.*

val GlobalScope = ElaraScope("#Global")
    .apply {
        registerType("Int", IntType)
        registerType("Float", FloatType)
        registerType("Char", CharType)

        registerVariable("print", ImpureFunctionType(StringType, UnitType).let { type ->
            Value(type,
                ElaraFunction(type, "value") { _, value ->
                    val toPrint = if (value.type == StringType)
                    {
                        (value.value as ConsList<*>).joinToString("")
                    } else
                    {
                        value.value.toString()
                    }
                    println(toPrint)
                    Value(UnitType, Unit)
                })
        })

        registerVariable("getLine", ImpureFunctionType(UnitType, StringType).let { type ->
            Value(type,
                ElaraFunction(type, "unit") { _, _ ->
                    val line = readLine()
                    if (line == null)
                        Value(StringType, ConsList.empty<Char>())
                    else
                        Value(StringType, line.toConsList())
                })
        })

        registerVariable("++", GenericType("a").let { a ->
            PureFunctionType(ListType(a), PureFunctionType(a, a)).let { type ->
                Value(type, ElaraFunction(type, "left") { _, left ->
                    @Suppress("UNCHECKED_CAST") val first = left.value as ConsList<Any>
                    val functionType = PureFunctionType(a, a)
                    Value(PureFunctionType(a, a), ElaraFunction(functionType, "right") { _, right ->
                        @Suppress("UNCHECKED_CAST") val second = right.value as ConsList<Any>
                        val copy = first + second
                        Value(left.type, copy)
                    })
                })
            }
        })
    }

