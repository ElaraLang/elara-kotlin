package runtime.scope

import runtime.Value
import type.ElaraType
import type.TypeClass
import type.TypeClassInstance
import java.util.*

class ElaraContext : Scope
{
    private val scopes: Deque<Scope> = ArrayDeque(listOf(GlobalScope))

    override fun registerInstance(typeClass: TypeClass, instance: TypeClassInstance)
    {
        return scopes.peek().registerInstance(typeClass, instance)
    }

    override fun getInstance(typeClass: TypeClass, type: ElaraType): TypeClassInstance
    {
        return scopes.firstNotNullOfOrNull { it.getInstance(typeClass, type) } ?: throw IllegalStateException("No instance of $typeClass for $type found")
    }

    override fun registerType(name: String, type: ElaraType)
    {
        return scopes.peek().registerType(name, type)
    }

    override fun registerVariable(name: String, value: Value)
    {
        return scopes.peek().registerVariable(name, value)
    }

    override fun getType(name: String): ElaraType
    {
        return scopes.firstNotNullOfOrNull { it.getType(name) } ?: throw IllegalStateException("No type named $name found")
    }

    override fun getVariable(name: String): Value
    {
        return scopes.firstNotNullOfOrNull { it.getVariable(name) } ?: throw IllegalStateException("No variable named $name found")
    }

    override val variables: Map<String, Value>
        get() = scopes.peek().variables

    fun enterScope(name: String)
    {
        scopes.addFirst(ElaraScope(name))
    }

    fun exitScope(): Scope
    {
        return scopes.pop()
    }

}
