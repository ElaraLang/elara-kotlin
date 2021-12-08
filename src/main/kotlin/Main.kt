import org.antlr.v4.gui.Trees
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.atn.PredictionMode

fun main()
{
    val code = """
            if true then 
                print 3
            else 
                print 4
            print 5
        """.trimIndent()


    val lexer = ElaraLexer(CharStreams.fromString(code))
    val parser = ElaraParser(CommonTokenStream(lexer))

//    Trees.inspect(parser.elaraFile(), parser)

    parser.interpreter.predictionMode = PredictionMode.LL_EXACT_AMBIG_DETECTION

    val context = interpret(parser.elaraFile())
    println("=== Variables ===")
    context.variables.entries.joinToString("\n") { it.key + " = " + it.value }.also(::println)
}
