import type.ElaraType

data class Value<T>(val type: ElaraType<T>, val value: T)
