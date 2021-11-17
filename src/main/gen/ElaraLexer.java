// Generated from /home/alex/workspace/elara-kotlin/src/main/antlr/ElaraLexer.g4 by ANTLR 4.9.2

  import com.yuvalshavit.antlr4.DenterHelper;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ElaraLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INDENT=1, DEDENT=2, NewLine=3, Semicolon=4, Whitespace=5, InlineComment=6, 
		MultiComment=7, IntegerLiteral=8, FloatLiteral=9, CharLiteral=10, StringLiteral=11, 
		Let=12, Def=13, Mut=14, Type=15, Class=16, Comma=17, LParen=18, RParen=19, 
		LSquareParen=20, RSquareParen=21, LBrace=22, RBrace=23, Colon=24, Dot=25, 
		PureArrow=26, ImpureArrow=27, Equals=28, Bar=29, TypeIdentifier=30, VarIdentifier=31, 
		OperatorIdentifier=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NewLine", "Semicolon", "Space", "Whitespace", "InlineComment", "MultiComment", 
			"NonZeroDigit", "Digit", "HexDigit", "ScientificNotation", "AbsoluteIntegerLiteral", 
			"IntegerLiteral", "FloatLiteral", "CharLiteral", "StringLiteral", "Let", 
			"Def", "Mut", "Type", "Class", "Comma", "LParen", "RParen", "LSquareParen", 
			"RSquareParen", "LBrace", "RBrace", "Colon", "Dot", "PureArrow", "ImpureArrow", 
			"Equals", "Bar", "TypeIdentifier", "VarIdentifier", "OperatorIdentifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "';'", null, null, null, null, null, null, null, 
			"'let'", "'def'", "'mut'", "'type'", "'class'", "','", "'('", "')'", 
			"'['", "']'", "'{'", "'}'", "':'", "'.'", "'->'", "'=>'", "'='", "'|'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INDENT", "DEDENT", "NewLine", "Semicolon", "Whitespace", "InlineComment", 
			"MultiComment", "IntegerLiteral", "FloatLiteral", "CharLiteral", "StringLiteral", 
			"Let", "Def", "Mut", "Type", "Class", "Comma", "LParen", "RParen", "LSquareParen", 
			"RSquareParen", "LBrace", "RBrace", "Colon", "Dot", "PureArrow", "ImpureArrow", 
			"Equals", "Bar", "TypeIdentifier", "VarIdentifier", "OperatorIdentifier"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	  private final DenterHelper denter = DenterHelper.builder()
	    .nl(NewLine)
	    .indent(ElaraParser.INDENT)
	    .dedent(ElaraParser.DEDENT)
	    .pullToken(ElaraLexer.super::nextToken);

	  @Override
	  public Token nextToken() {
	    return denter.nextToken();
	  }


	public ElaraLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ElaraLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u00eb\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\5\2M\n\2\3\2\3\2\7\2Q\n\2\f\2\16\2T"+
		"\13\2\3\3\3\3\3\4\3\4\3\5\6\5[\n\5\r\5\16\5\\\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\7\6e\n\6\f\6\16\6h\13\6\3\6\3\6\3\7\3\7\3\7\3\7\6\7p\n\7\r\7\16\7q\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\5\t}\n\t\3\n\3\n\3\13\3\13\3\13\3\f"+
		"\3\f\7\f\u0086\n\f\f\f\16\f\u0089\13\f\3\r\5\r\u008c\n\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u0098\n\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\6\20\u00a0\n\20\r\20\16\20\u00a1\3\20\3\20\3\21\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3"+
		"\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 "+
		"\3 \3!\3!\3\"\3\"\3#\3#\7#\u00db\n#\f#\16#\u00de\13#\3$\3$\7$\u00e2\n"+
		"$\f$\16$\u00e5\13$\3%\6%\u00e8\n%\r%\16%\u00e9\3q\2&\3\5\5\6\7\2\t\7\13"+
		"\b\r\t\17\2\21\2\23\2\25\2\27\2\31\n\33\13\35\f\37\r!\16#\17%\20\'\21"+
		")\22+\23-\24/\25\61\26\63\27\65\30\67\319\32;\33=\34?\35A\36C\37E G!I"+
		"\"\3\2\13\4\2\13\13\"\"\4\2\f\f\17\17\4\2\62;CH\4\2--//\3\2$$\3\2C\\\6"+
		"\2\62;C\\aac|\3\2c|\13\2##%\',-/\61>B^^``~~\u0080\u0080\2\u00f2\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3"+
		"L\3\2\2\2\5U\3\2\2\2\7W\3\2\2\2\tZ\3\2\2\2\13`\3\2\2\2\rk\3\2\2\2\17x"+
		"\3\2\2\2\21|\3\2\2\2\23~\3\2\2\2\25\u0080\3\2\2\2\27\u0083\3\2\2\2\31"+
		"\u008b\3\2\2\2\33\u008f\3\2\2\2\35\u0093\3\2\2\2\37\u009b\3\2\2\2!\u00a5"+
		"\3\2\2\2#\u00a9\3\2\2\2%\u00ad\3\2\2\2\'\u00b1\3\2\2\2)\u00b6\3\2\2\2"+
		"+\u00bc\3\2\2\2-\u00be\3\2\2\2/\u00c0\3\2\2\2\61\u00c2\3\2\2\2\63\u00c4"+
		"\3\2\2\2\65\u00c6\3\2\2\2\67\u00c8\3\2\2\29\u00ca\3\2\2\2;\u00cc\3\2\2"+
		"\2=\u00ce\3\2\2\2?\u00d1\3\2\2\2A\u00d4\3\2\2\2C\u00d6\3\2\2\2E\u00d8"+
		"\3\2\2\2G\u00df\3\2\2\2I\u00e7\3\2\2\2KM\7\17\2\2LK\3\2\2\2LM\3\2\2\2"+
		"MN\3\2\2\2NR\7\f\2\2OQ\7\"\2\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2"+
		"S\4\3\2\2\2TR\3\2\2\2UV\7=\2\2V\6\3\2\2\2WX\t\2\2\2X\b\3\2\2\2Y[\5\7\4"+
		"\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^_\b\5\2\2_\n\3"+
		"\2\2\2`a\7\61\2\2ab\7\61\2\2bf\3\2\2\2ce\n\3\2\2dc\3\2\2\2eh\3\2\2\2f"+
		"d\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\b\6\2\2j\f\3\2\2\2kl\7\61\2"+
		"\2lm\7,\2\2mo\3\2\2\2np\13\2\2\2on\3\2\2\2pq\3\2\2\2qr\3\2\2\2qo\3\2\2"+
		"\2rs\3\2\2\2st\7,\2\2tu\7\61\2\2uv\3\2\2\2vw\b\7\2\2w\16\3\2\2\2xy\4\63"+
		";\2y\20\3\2\2\2z}\7\62\2\2{}\5\17\b\2|z\3\2\2\2|{\3\2\2\2}\22\3\2\2\2"+
		"~\177\t\4\2\2\177\24\3\2\2\2\u0080\u0081\7G\2\2\u0081\u0082\t\5\2\2\u0082"+
		"\26\3\2\2\2\u0083\u0087\5\17\b\2\u0084\u0086\5\21\t\2\u0085\u0084\3\2"+
		"\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
		"\30\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u008c\7/\2\2\u008b\u008a\3\2\2\2"+
		"\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\5\27\f\2\u008e\32"+
		"\3\2\2\2\u008f\u0090\5\31\r\2\u0090\u0091\7\60\2\2\u0091\u0092\5\27\f"+
		"\2\u0092\34\3\2\2\2\u0093\u0097\7)\2\2\u0094\u0098\13\2\2\2\u0095\u0096"+
		"\7^\2\2\u0096\u0098\13\2\2\2\u0097\u0094\3\2\2\2\u0097\u0095\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u009a\7)\2\2\u009a\36\3\2\2\2\u009b\u009f\7$\2\2"+
		"\u009c\u00a0\n\6\2\2\u009d\u009e\7^\2\2\u009e\u00a0\7$\2\2\u009f\u009c"+
		"\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\7$\2\2\u00a4 \3\2\2\2\u00a5"+
		"\u00a6\7n\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7v\2\2\u00a8\"\3\2\2\2\u00a9"+
		"\u00aa\7f\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7h\2\2\u00ac$\3\2\2\2\u00ad"+
		"\u00ae\7o\2\2\u00ae\u00af\7w\2\2\u00af\u00b0\7v\2\2\u00b0&\3\2\2\2\u00b1"+
		"\u00b2\7v\2\2\u00b2\u00b3\7{\2\2\u00b3\u00b4\7r\2\2\u00b4\u00b5\7g\2\2"+
		"\u00b5(\3\2\2\2\u00b6\u00b7\7e\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7c\2"+
		"\2\u00b9\u00ba\7u\2\2\u00ba\u00bb\7u\2\2\u00bb*\3\2\2\2\u00bc\u00bd\7"+
		".\2\2\u00bd,\3\2\2\2\u00be\u00bf\7*\2\2\u00bf.\3\2\2\2\u00c0\u00c1\7+"+
		"\2\2\u00c1\60\3\2\2\2\u00c2\u00c3\7]\2\2\u00c3\62\3\2\2\2\u00c4\u00c5"+
		"\7_\2\2\u00c5\64\3\2\2\2\u00c6\u00c7\7}\2\2\u00c7\66\3\2\2\2\u00c8\u00c9"+
		"\7\177\2\2\u00c98\3\2\2\2\u00ca\u00cb\7<\2\2\u00cb:\3\2\2\2\u00cc\u00cd"+
		"\7\60\2\2\u00cd<\3\2\2\2\u00ce\u00cf\7/\2\2\u00cf\u00d0\7@\2\2\u00d0>"+
		"\3\2\2\2\u00d1\u00d2\7?\2\2\u00d2\u00d3\7@\2\2\u00d3@\3\2\2\2\u00d4\u00d5"+
		"\7?\2\2\u00d5B\3\2\2\2\u00d6\u00d7\7~\2\2\u00d7D\3\2\2\2\u00d8\u00dc\t"+
		"\7\2\2\u00d9\u00db\t\b\2\2\u00da\u00d9\3\2\2\2\u00db\u00de\3\2\2\2\u00dc"+
		"\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00ddF\3\2\2\2\u00de\u00dc\3\2\2\2"+
		"\u00df\u00e3\t\t\2\2\u00e0\u00e2\t\b\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e5"+
		"\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4H\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e6\u00e8\t\n\2\2\u00e7\u00e6\3\2\2\2\u00e8\u00e9\3\2"+
		"\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00eaJ\3\2\2\2\21\2LR\\f"+
		"q|\u0087\u008b\u0097\u009f\u00a1\u00dc\u00e3\u00e9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}