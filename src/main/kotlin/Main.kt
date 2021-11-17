import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.atn.PredictionMode

fun main()
{
    val code = """
        type class Sum a where
           def (+) : a -> a -> a
        
        instance Sum String where
            let (+) = (++)
        """.trimIndent()

    println(code)
    val lexer = ElaraLexer(CharStreams.fromString(code))
    val parser = ElaraParser(CommonTokenStream(lexer))

    parser.interpreter.predictionMode = PredictionMode.LL_EXACT_AMBIG_DETECTION


    val context = interpret(parser.elaraFile())
    println("=== Variables ===")
    context.variables.entries.joinToString("\n") { it.key + " = " + it.value }.also(::println)
}
