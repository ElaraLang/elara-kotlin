import type.ElaraType
import type.IntType
import type.StringType

class ElaraContext
{
    val variables = mutableMapOf<String, Value<*>>()

    val types = mutableMapOf<String, ElaraType<*>>(
        "String" to StringType,
        "Int" to IntType
    )

}
