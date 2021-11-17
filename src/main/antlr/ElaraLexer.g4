lexer grammar ElaraLexer;

tokens { INDENT, DEDENT }
@lexer::header {
  import com.yuvalshavit.antlr4.DenterHelper;
}

@lexer::members {
  private final DenterHelper denter = DenterHelper.builder()
    .nl(NewLine)
    .indent(ElaraParser.INDENT)
    .dedent(ElaraParser.DEDENT)
    .pullToken(ElaraLexer.super::nextToken);

  @Override
  public Token nextToken() {
    return denter.nextToken();
  }
}

NewLine : ('\r'? '\n' '\t'* | '\n' ' '*);

Semicolon : ';';

Whitespace : [ \t] -> skip;

InlineComment : '//' ~[\r\n]* -> skip;
MultiComment : '/*' .+? '*/' -> skip;

fragment NonZeroDigit : '1'..'9';
fragment Digit: '0' | NonZeroDigit;
fragment HexDigit: [0-9A-F];
fragment ScientificNotation: 'E' [+-];

// Literals
fragment AbsoluteIntegerLiteral: NonZeroDigit Digit*;
IntegerLiteral : '-'? AbsoluteIntegerLiteral;
FloatLiteral: IntegerLiteral '.' AbsoluteIntegerLiteral ;

CharLiteral : '\'' (. | '\\' .) '\'';
StringLiteral : '"' (~'"' | '\\"')+ '"';

// Keywords
Let : 'let';
Def : 'def';
Mut: 'mut';
Type: 'type';
Class: 'class';
Where: 'where';
Instance: 'instance';

// Symbols
Comma: ',';
LParen: '(';
RParen: ')';
LSquareParen: '[';
RSquareParen: ']';
LBrace: '{';
RBrace: '}';
Colon: ':';
Dot: '.';
PureArrow: '->';
ImpureArrow: '=>';
Equals : '=';
Bar : '|';

// Identifiers
TypeIdentifier: [A-Z][a-zA-Z_0-9]*;
VarIdentifier: [a-z][a-zA-Z_0-9]*;
OperatorIdentifier: ('!' | '#' | '$' | '%' | '+' | '-' | '/' | '*' | '.' | '<' | '>' | '=' | '?' | '@' | '~' | '\\' | '^' | '|')+;

