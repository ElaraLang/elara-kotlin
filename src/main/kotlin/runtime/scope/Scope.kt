package runtime.scope

import runtime.Value
import type.ElaraType
import type.TypeClass
import type.TypeClassInstance

interface Scope
{

    fun registerInstance(typeClass: TypeClass, instance: TypeClassInstance)

    fun getInstance(typeClass: TypeClass, type: ElaraType): TypeClassInstance?

    fun registerType(name: String, type: ElaraType)

    fun registerVariable(name: String, value: Value)

    fun getType(name: String): ElaraType?

    fun getVariable(name: String): Value?

    val variables : Map<String, Value>
}
