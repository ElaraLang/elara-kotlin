package type

data class GenericType(val name: String) : ElaraType
{
    override fun toString(): String
    {
        return name
    }
}
