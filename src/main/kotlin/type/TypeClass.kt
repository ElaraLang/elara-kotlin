package type

import Value

data class TypeClass(val name: String, val members: List<TypeClassMember>) : ElaraType
{
    data class TypeClassMember(val name: String, val type: ElaraType)
}

data class TypeClassInstance(
    val clazz: TypeClass,
    val type: ElaraType,
    val members: Map<TypeClass.TypeClassMember, Value>,
)
