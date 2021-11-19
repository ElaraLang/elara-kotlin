parser grammar ElaraParser;

options { tokenVocab=ElaraLexer; }

defClause : Def variableIdentifier Colon type separator;
letClause : Let variableIdentifier VarIdentifier* Equals letBody;
letBody : block | (expression separator);
variable : (defClause)? letClause;

operatorIdentifier : OperatorIdentifier+ ;
operatorVariable : '(' operatorIdentifier ')' ;
variableIdentifier : VarIdentifier | operatorVariable ;

typeIdentifier : VarIdentifier ;

unit : LParen RParen;

separator : (NewLine | Semicolon);

// Types
type : unit #UnitType
    | typeIdentifier #GenericType
    | typeName typeName+ #RealizedGenericType
    | type PureArrow type # PureFunctionType
    | type ImpureArrow type #ImpureFunctionType
    | LParen type RParen #ParenthesizedType
    | LParen (type (Comma type)+) RParen #TupleType
    | LSquareParen type RSquareParen # ListType
    | TypeIdentifier #SimpleType
    | Mut type #MutType;

typeName :
      TypeIdentifier #NormalTypeName
    | typeIdentifier #GenericTypeName;

typeAlias : type;
typeConstructor :
    TypeIdentifier type* #NormalTypeConstructor
    | TypeIdentifier recordType #RecordTypeConstructor;

sumType : typeConstructor (Bar typeConstructor)*;
recordTypeField : VarIdentifier Colon type;
recordType : LBrace recordTypeField (Comma recordTypeField)* RBrace;

typeDeclaration : Type TypeIdentifier typeIdentifier* Equals typeDeclarationValue NewLine;
typeDeclarationValue :
    typeAlias #TypeAliasValue
    | sumType #SumTypeValue
    | recordType # RecordTypeValue
    ;

typeClassDeclaration : Type Class TypeIdentifier typeIdentifier Where typeClassBody;
typeClassBody : blockOpen typeClassValue* blockClose;
typeClassValue : defClause | variable;

typeClassInstanceDeclaration : Instance TypeIdentifier type Where typeClassInstanceBody;
typeClassInstanceBody : blockOpen variable* blockClose;

blockOpen : INDENT ;
blockClose : DEDENT ;

// Expressions

block : blockOpen elaraLine+ blockClose;

expression :
      unit #UnitExpression
    | IntegerLiteral #IntExpression
    | FloatLiteral #FloatExpression
    | CharLiteral #CharExpression
    | StringLiteral #StringExpression
    | LSquareParen (expression (Comma expression)*)? RSquareParen #ListExpression
    | LParen expression RParen #ParenExpression
    | LParen (expression (Comma expression)+) RParen #TupleExpression
    | expression operatorIdentifier expression #OperatorApplicationExpression
    | expression expression+ #FunctionApplicationExpression
    | variableIdentifier # VariableExpression
;

statement :
    variable # VariableDeclarationStatement
    | typeDeclaration # TypeDeclarationStatement
    | typeClassDeclaration # TypeClassDeclarationStatement
    | typeClassInstanceDeclaration # TypeClassInstanceDeclarationStatement
    ;

// A complete compilation unit
elaraLine :
      expression separator # ExpressionLine
    | statement # StatementLine
    ;

elaraFile : elaraLine*;
