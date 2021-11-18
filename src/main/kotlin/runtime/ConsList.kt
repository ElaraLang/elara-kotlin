package runtime

@Suppress("UNCHECKED_CAST")
sealed interface ConsList<out T> : Iterable<T>
{
    fun isEmpty(): Boolean
    val head: T?
    val tail: ConsList<T>

    operator fun plus(other: ConsList<@UnsafeVariance T>): ConsList<T>


    companion object
    {
        fun <T> empty(): ConsList<T> = Nil

        fun <T> Collection<T>.toConsList(): ConsList<T>
        {
            var cons: ConsList<T> = Nil
            for (i in (0 until size).reversed())
            {
                cons = Cons(this.elementAt(i), cons)
            }
            return cons
        }

        fun String.toConsList(): ConsList<Char>
        {
            var cons: ConsList<Char> = Nil
            for (i in (0 until length).reversed())
            {
                cons = Cons(this.elementAt(i), cons)
            }
            return cons
        }
    }
}

object Nil : ConsList<Nothing>
{
    override fun isEmpty(): Boolean = true

    override val head = null
    override val tail = Nil

    override fun iterator() = iterator<Nothing> {}
    override fun plus(other: ConsList<Nothing>): ConsList<Nothing>
    {
        return other
    }

    override fun toString(): String
    {
        return "[]"
    }
}

class Cons<T>(override val head: T, override val tail: ConsList<T>) : ConsList<T>
{
    override fun isEmpty(): Boolean = false

    override fun toString(): String
    {
        return joinToString(", ", prefix = "[", postfix = "]")
    }

    override fun iterator(): Iterator<T> = iterator {
        yield(head)
        val next = tail.iterator()
        yieldAll(next.iterator())
    }

    override fun plus(other: ConsList<T>): ConsList<T>
    {
        return Cons(head, tail + other)
    }
}
