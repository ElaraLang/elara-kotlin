package type

sealed class ElaraType<T>
{
    open fun isAssignableTo(other: ElaraType<*>): Boolean
    {
        return this == other
    }

    override fun toString(): String
    {
        return this.javaClass.simpleName
    }
}

object UnitType : ElaraType<Unit>()

object StringType : ElaraType<String>()

object IntType : ElaraType<Int>()
