package type

import runtime.Value

data class TypeClass(val name: String, val members: List<TypeClassMember>) : ElaraType
{
    override fun toString(): String
    {
        return """
            |type class $name where
            |   ${members.joinToString { "def ${it.name} : ${it.type}" }}
        """.trimMargin()

    }

    data class TypeClassMember(val name: String, val type: ElaraType)
}

data class TypeClassInstance(
    val clazz: TypeClass,
    val type: ElaraType,
    val members: Map<TypeClass.TypeClassMember, Value>,
)
