import type.ElaraType

data class Value(val type: ElaraType, val value: Any)
{
    override fun toString(): String
    {
        return "$value : $type"
    }
}
