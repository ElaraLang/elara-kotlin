import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.atn.PredictionMode

fun main()
{
    val lexer = ElaraLexer(CharStreams.fromString("""
        def x : String
        let x = "Hello world!"
        
        def y : Int
        let y = 42
        """.trimIndent()))
    val parser = ElaraParser(CommonTokenStream(lexer))

    parser.interpreter.predictionMode = PredictionMode.LL_EXACT_AMBIG_DETECTION


    val context = interpret(parser.elaraFile())
    println(context.variables)
}
