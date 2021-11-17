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
		Let=12, Def=13, Mut=14, Type=15, Class=16, Where=17, Instance=18, Comma=19, 
		LParen=20, RParen=21, LSquareParen=22, RSquareParen=23, LBrace=24, RBrace=25, 
		Colon=26, Dot=27, PureArrow=28, ImpureArrow=29, Equals=30, Bar=31, TypeIdentifier=32, 
		VarIdentifier=33, OperatorIdentifier=34;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NewLine", "Semicolon", "Whitespace", "InlineComment", "MultiComment", 
			"NonZeroDigit", "Digit", "HexDigit", "ScientificNotation", "AbsoluteIntegerLiteral", 
			"IntegerLiteral", "FloatLiteral", "CharLiteral", "StringLiteral", "Let", 
			"Def", "Mut", "Type", "Class", "Where", "Instance", "Comma", "LParen", 
			"RParen", "LSquareParen", "RSquareParen", "LBrace", "RBrace", "Colon", 
			"Dot", "PureArrow", "ImpureArrow", "Equals", "Bar", "TypeIdentifier", 
			"VarIdentifier", "OperatorIdentifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "';'", null, null, null, null, null, null, null, 
			"'let'", "'def'", "'mut'", "'type'", "'class'", "'where'", "'instance'", 
			"','", "'('", "')'", "'['", "']'", "'{'", "'}'", "':'", "'.'", "'->'", 
			"'=>'", "'='", "'|'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INDENT", "DEDENT", "NewLine", "Semicolon", "Whitespace", "InlineComment", 
			"MultiComment", "IntegerLiteral", "FloatLiteral", "CharLiteral", "StringLiteral", 
			"Let", "Def", "Mut", "Type", "Class", "Where", "Instance", "Comma", "LParen", 
			"RParen", "LSquareParen", "RSquareParen", "LBrace", "RBrace", "Colon", 
			"Dot", "PureArrow", "ImpureArrow", "Equals", "Bar", "TypeIdentifier", 
			"VarIdentifier", "OperatorIdentifier"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u0100\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\5\2O\n\2\3\2\3\2\7\2S\n\2\f\2"+
		"\16\2V\13\2\3\2\3\2\7\2Z\n\2\f\2\16\2]\13\2\5\2_\n\2\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\7\5k\n\5\f\5\16\5n\13\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\6\6v\n\6\r\6\16\6w\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\5\b\u0083\n\b"+
		"\3\t\3\t\3\n\3\n\3\n\3\13\3\13\7\13\u008c\n\13\f\13\16\13\u008f\13\13"+
		"\3\f\5\f\u0092\n\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\5\16\u009e"+
		"\n\16\3\16\3\16\3\17\3\17\3\17\3\17\6\17\u00a6\n\17\r\17\16\17\u00a7\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3"+
		"\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3"+
		"\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3$\3$\7$\u00f0\n$\f$\16$\u00f3"+
		"\13$\3%\3%\7%\u00f7\n%\f%\16%\u00fa\13%\3&\6&\u00fd\n&\r&\16&\u00fe\3"+
		"w\2\'\3\5\5\6\7\7\t\b\13\t\r\2\17\2\21\2\23\2\25\2\27\n\31\13\33\f\35"+
		"\r\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27\63\30\65\31\67\329\33"+
		";\34=\35?\36A\37C E!G\"I#K$\3\2\13\4\2\13\13\"\"\4\2\f\f\17\17\4\2\62"+
		";CH\4\2--//\3\2$$\3\2C\\\6\2\62;C\\aac|\3\2c|\13\2##%\',-/\61>B^^``~~"+
		"\u0080\u0080\2\u0109\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2"+
		"\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\3^\3\2\2\2\5`\3\2\2\2"+
		"\7b\3\2\2\2\tf\3\2\2\2\13q\3\2\2\2\r~\3\2\2\2\17\u0082\3\2\2\2\21\u0084"+
		"\3\2\2\2\23\u0086\3\2\2\2\25\u0089\3\2\2\2\27\u0091\3\2\2\2\31\u0095\3"+
		"\2\2\2\33\u0099\3\2\2\2\35\u00a1\3\2\2\2\37\u00ab\3\2\2\2!\u00af\3\2\2"+
		"\2#\u00b3\3\2\2\2%\u00b7\3\2\2\2\'\u00bc\3\2\2\2)\u00c2\3\2\2\2+\u00c8"+
		"\3\2\2\2-\u00d1\3\2\2\2/\u00d3\3\2\2\2\61\u00d5\3\2\2\2\63\u00d7\3\2\2"+
		"\2\65\u00d9\3\2\2\2\67\u00db\3\2\2\29\u00dd\3\2\2\2;\u00df\3\2\2\2=\u00e1"+
		"\3\2\2\2?\u00e3\3\2\2\2A\u00e6\3\2\2\2C\u00e9\3\2\2\2E\u00eb\3\2\2\2G"+
		"\u00ed\3\2\2\2I\u00f4\3\2\2\2K\u00fc\3\2\2\2MO\7\17\2\2NM\3\2\2\2NO\3"+
		"\2\2\2OP\3\2\2\2PT\7\f\2\2QS\7\13\2\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU"+
		"\3\2\2\2U_\3\2\2\2VT\3\2\2\2W[\7\f\2\2XZ\7\"\2\2YX\3\2\2\2Z]\3\2\2\2["+
		"Y\3\2\2\2[\\\3\2\2\2\\_\3\2\2\2][\3\2\2\2^N\3\2\2\2^W\3\2\2\2_\4\3\2\2"+
		"\2`a\7=\2\2a\6\3\2\2\2bc\t\2\2\2cd\3\2\2\2de\b\4\2\2e\b\3\2\2\2fg\7\61"+
		"\2\2gh\7\61\2\2hl\3\2\2\2ik\n\3\2\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3"+
		"\2\2\2mo\3\2\2\2nl\3\2\2\2op\b\5\2\2p\n\3\2\2\2qr\7\61\2\2rs\7,\2\2su"+
		"\3\2\2\2tv\13\2\2\2ut\3\2\2\2vw\3\2\2\2wx\3\2\2\2wu\3\2\2\2xy\3\2\2\2"+
		"yz\7,\2\2z{\7\61\2\2{|\3\2\2\2|}\b\6\2\2}\f\3\2\2\2~\177\4\63;\2\177\16"+
		"\3\2\2\2\u0080\u0083\7\62\2\2\u0081\u0083\5\r\7\2\u0082\u0080\3\2\2\2"+
		"\u0082\u0081\3\2\2\2\u0083\20\3\2\2\2\u0084\u0085\t\4\2\2\u0085\22\3\2"+
		"\2\2\u0086\u0087\7G\2\2\u0087\u0088\t\5\2\2\u0088\24\3\2\2\2\u0089\u008d"+
		"\5\r\7\2\u008a\u008c\5\17\b\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2"+
		"\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\26\3\2\2\2\u008f\u008d"+
		"\3\2\2\2\u0090\u0092\7/\2\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0094\5\25\13\2\u0094\30\3\2\2\2\u0095\u0096\5\27"+
		"\f\2\u0096\u0097\7\60\2\2\u0097\u0098\5\25\13\2\u0098\32\3\2\2\2\u0099"+
		"\u009d\7)\2\2\u009a\u009e\13\2\2\2\u009b\u009c\7^\2\2\u009c\u009e\13\2"+
		"\2\2\u009d\u009a\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a0\7)\2\2\u00a0\34\3\2\2\2\u00a1\u00a5\7$\2\2\u00a2\u00a6\n\6\2\2"+
		"\u00a3\u00a4\7^\2\2\u00a4\u00a6\7$\2\2\u00a5\u00a2\3\2\2\2\u00a5\u00a3"+
		"\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\u00aa\7$\2\2\u00aa\36\3\2\2\2\u00ab\u00ac\7n\2\2"+
		"\u00ac\u00ad\7g\2\2\u00ad\u00ae\7v\2\2\u00ae \3\2\2\2\u00af\u00b0\7f\2"+
		"\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7h\2\2\u00b2\"\3\2\2\2\u00b3\u00b4\7"+
		"o\2\2\u00b4\u00b5\7w\2\2\u00b5\u00b6\7v\2\2\u00b6$\3\2\2\2\u00b7\u00b8"+
		"\7v\2\2\u00b8\u00b9\7{\2\2\u00b9\u00ba\7r\2\2\u00ba\u00bb\7g\2\2\u00bb"+
		"&\3\2\2\2\u00bc\u00bd\7e\2\2\u00bd\u00be\7n\2\2\u00be\u00bf\7c\2\2\u00bf"+
		"\u00c0\7u\2\2\u00c0\u00c1\7u\2\2\u00c1(\3\2\2\2\u00c2\u00c3\7y\2\2\u00c3"+
		"\u00c4\7j\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7t\2\2\u00c6\u00c7\7g\2\2"+
		"\u00c7*\3\2\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7p\2\2\u00ca\u00cb\7u\2"+
		"\2\u00cb\u00cc\7v\2\2\u00cc\u00cd\7c\2\2\u00cd\u00ce\7p\2\2\u00ce\u00cf"+
		"\7e\2\2\u00cf\u00d0\7g\2\2\u00d0,\3\2\2\2\u00d1\u00d2\7.\2\2\u00d2.\3"+
		"\2\2\2\u00d3\u00d4\7*\2\2\u00d4\60\3\2\2\2\u00d5\u00d6\7+\2\2\u00d6\62"+
		"\3\2\2\2\u00d7\u00d8\7]\2\2\u00d8\64\3\2\2\2\u00d9\u00da\7_\2\2\u00da"+
		"\66\3\2\2\2\u00db\u00dc\7}\2\2\u00dc8\3\2\2\2\u00dd\u00de\7\177\2\2\u00de"+
		":\3\2\2\2\u00df\u00e0\7<\2\2\u00e0<\3\2\2\2\u00e1\u00e2\7\60\2\2\u00e2"+
		">\3\2\2\2\u00e3\u00e4\7/\2\2\u00e4\u00e5\7@\2\2\u00e5@\3\2\2\2\u00e6\u00e7"+
		"\7?\2\2\u00e7\u00e8\7@\2\2\u00e8B\3\2\2\2\u00e9\u00ea\7?\2\2\u00eaD\3"+
		"\2\2\2\u00eb\u00ec\7~\2\2\u00ecF\3\2\2\2\u00ed\u00f1\t\7\2\2\u00ee\u00f0"+
		"\t\b\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2H\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f8\t\t\2\2"+
		"\u00f5\u00f7\t\b\2\2\u00f6\u00f5\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6"+
		"\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9J\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb"+
		"\u00fd\t\n\2\2\u00fc\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00fc\3\2"+
		"\2\2\u00fe\u00ff\3\2\2\2\u00ffL\3\2\2\2\22\2NT[^lw\u0082\u008d\u0091\u009d"+
		"\u00a5\u00a7\u00f1\u00f8\u00fe\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}