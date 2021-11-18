package runtime.scope

import runtime.Value
import type.ElaraType
import type.TypeClass
import type.TypeClassInstance

data class ElaraScope(val name: String) : Scope
{
    private val instances = mutableMapOf<TypeClass, MutableMap<ElaraType, TypeClassInstance>>()

    private val types = mutableMapOf<String, ElaraType>()
    private val _variables = mutableMapOf<String, Value>()

    override val variables: Map<String, Value>
        get() = _variables

    override fun registerInstance(typeClass: TypeClass, instance: TypeClassInstance)
    {
        instances.getOrPut(typeClass) { mutableMapOf() }[instance.type] = instance
    }

    override fun getInstance(typeClass: TypeClass, type: ElaraType): TypeClassInstance?
    {
        return instances[typeClass]?.get(type)
    }


    override fun registerType(name: String, type: ElaraType)
    {
        types[name] = type
    }

    override fun registerVariable(name: String, value: Value)
    {
        _variables[name] = value
    }

    override fun getVariable(name: String): Value?
    {
        return _variables[name]
    }

    override fun getType(name: String): ElaraType?
    {
        return types[name]
    }
}
