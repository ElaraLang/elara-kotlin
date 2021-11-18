package type

class TypeClassConstraint(val clazz : TypeClass, val name: String, val type : ElaraType) : ElaraType
{
    override fun toString(): String
    {
        return "(${clazz.name} $name) :> $type"
    }
}
