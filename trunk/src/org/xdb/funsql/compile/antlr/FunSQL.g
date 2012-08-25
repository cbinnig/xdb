grammar FunSQL;

tokens {
    EQUAL1              =   '=';
    EQUAL2              =   '==';
    NOT_EQUAL1          =   '!=';
    NOT_EQUAL2          =   '<>';
    LESS                =   '<';
    LESS_OR_EQ1         =   '<=';
    LESS_OR_EQ2         =   '!>';
    GREATER             =   '>';
    GREATER_OR_EQ1      =   '>=';
    GREATER_OR_EQ2      =   '!<';
    SHIFT_LEFT          =   '<<';
    SHIFT_RIGHT         =   '>>';
    AMPERSAND           =   '&';
    HAT                 =   '^';
    PIPE                =   '|';
    DOUBLE_PIPE         =   '||';
    DIV                 =   '/';
    STAR                =   '*';
    PLUS                =   '+';
    MINUS               =   '-';
    TILDE               =   '~';
    BACKSLASH           =   '\\';
    MOD                 =   '%';
    SEMI                =   ';';
    DOT                 =   '.';
    COMMA               =   ',';
    LPAREN              =   '(';
    RPAREN              =   ')';
    QUESTION            =   '?';
    AT                  =   '@';
    DOLLAR              =   '$';
    QUOTE_DOUBLE        =   '"';
    QUOTE_TRIPLE        =   '\'\'\'';
    QUOTE_SINGLE        =   '\'';    
    APOSTROPHE          =   '`';
    LBRACKET            =   '[';
    RBRACKET            =   ']';
    UNDERSCORE          =   '_';
    COLON               =   ':';
}

@parser::header { 
package org.xdb.funsql.compile.antlr; 

import org.xdb.funsql.compile.predicate.*;
import org.xdb.funsql.compile.tokens.*;
import org.xdb.funsql.statement.*;
}

@lexer::header { 
package org.xdb.funsql.compile.antlr;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

statement returns [AbstractStatement stmt]
        @init{
        	$stmt = null;
        }
        :       
        (
        	(
                createSchemaStatement 
                {
                	$stmt = $createSchemaStatement.stmt;
                }
                |
                dropSchemaStatement 
                {
                	$stmt = $dropSchemaStatement.stmt;
                }
                |
                createConnectionStatement 
                {
                	$stmt = $createConnectionStatement.stmt;
                }
                |
                dropConnectionStatement 
                {
                	$stmt = $dropConnectionStatement.stmt;
                }
                |
                createTableStatement 
                {
                	$stmt = $createTableStatement.stmt;
                }
                |
                dropTableStatement 
                {
                	$stmt = $dropTableStatement.stmt;
                }
                |
                selectStatement
                {
                	$stmt = $selectStatement.stmt;
                }
                )
                SEMI?
        );
        

createSchemaStatement returns [CreateSchemaStmt stmt]
        @init{
        	$stmt = new CreateSchemaStmt();
        }
        :
        (
                KEYWORD_CREATE
                KEYWORD_SCHEMA
                tokenSchema {
                	$stmt.setSchema($tokenSchema.schema);
                }
        )
        ;

dropSchemaStatement returns [DropSchemaStmt stmt]
        @init{
        	$stmt = new DropSchemaStmt();
        }
        :
        (
                KEYWORD_DROP
                KEYWORD_SCHEMA
                tokenSchema {
                	$stmt.setSchema($tokenSchema.schema);
                }
        )
        ;

createConnectionStatement returns [CreateConnectionStmt stmt]
        @init{
        	$stmt = new CreateConnectionStmt();
        }
        :
        (
                KEYWORD_CREATE
                KEYWORD_CONNECTION
                tokenIdentifier {
                	$stmt.setConnection($tokenIdentifier.identifier);
                }
                KEYWORD_URL
                litURL=tokenStringLiteral {
                	$stmt.setURL($litURL.literal);
                }
                KEYWORD_USER
                litUser=tokenStringLiteral {
                	$stmt.setUser($litUser.literal);
                }
                KEYWORD_PASSWD
                litPasswd=tokenStringLiteral {
                	$stmt.setPasswd($litPasswd.literal);
                }
                KEYWORD_STORE
                litStore=tokenStringLiteral {
                	$stmt.setStore($litStore.literal);
                }
        )
        ;
        
dropConnectionStatement returns [DropConnectionStmt stmt]
        @init{
        	$stmt = new DropConnectionStmt();
        }
        :
        (
                KEYWORD_DROP
                KEYWORD_CONNECTION
                tokenIdentifier {
                	$stmt.setConnection($tokenIdentifier.identifier);
                }
        )
        ;

createTableStatement returns [CreateTableStmt stmt]
        @init{
        	$stmt = new CreateTableStmt();
        }
        :
        (
                KEYWORD_CREATE
                KEYWORD_TABLE
                table1=tokenTable {
                	$stmt.setTable($table1.table);
                	$stmt.setSourceTable($table1.table);
                }
                LPAREN
                att1=identifierText
                {
                	$stmt.addAttribute($att1.text);
                }
                dataType1=tokenDataType
                {
                	$stmt.addType($dataType1.dataType);
                }
                (
                COMMA
                att2=identifierText
                {
                	$stmt.addAttribute($att2.text);
                }
                dataType2=tokenDataType
                {
                	$stmt.addType($dataType2.dataType);
                }
                )*
                RPAREN
                (
                KEYWORD_FROM
                table2=tokenTable {
                	$stmt.setSourceTable($table2.table);
                }
                )?
                KEYWORD_IN KEYWORD_CONNECTION
                connection1=tokenIdentifier {
                	$stmt.setConnection($connection1.identifier);
                }
        )
        ;

dropTableStatement returns [DropTableStmt stmt]
        @init{
        	$stmt = new DropTableStmt();
        }
        :
        (
                KEYWORD_DROP
                KEYWORD_TABLE
                table1=tokenTable {
                	$stmt.setTable($table1.table);
                }
	)
	;
	
selectStatement returns [SelectStmt stmt]
        @init{
        	$stmt = new SelectStmt();
        }
        :
        (
                KEYWORD_SELECT
                att1=tokenAttribute
                {
                	$stmt.addAttribute($att1.attribute);
                }
                (
                COMMA
                att2=tokenAttribute
                {
                	$stmt.addAttribute($att2.attribute);
                }
                )*
                KEYWORD_FROM
                table1=tokenTable 
                {
                	$stmt.addTable($table1.table);
                }
                (
                COMMA
                table2=tokenTable
                {
                	$stmt.addTable($table2.table);
                }
                )*
                (
                KEYWORD_WHERE
                predicate1=abstractPredicate
                {
                	$stmt.setPredicate($predicate1.predicate);
                }
                )?
                
	)
	;
	
abstractPredicate returns [AbstractPredicate predicate]
	@init{
        	$predicate = null;
        }
        :
        (
        	(
                predicate1=simplePredicateLiteral {
                	$predicate = $predicate1.predicate;
                }
                |
                predicate2=simplePredicateAttribute {
                	$predicate = $predicate2.predicate;
                }
                )
        )
        ;

simplePredicateLiteral returns [SimplePredicateLiteral predicate]
	@init{
        	$predicate = new SimplePredicateLiteral();
        }
        :
	(
		att1=tokenAttribute
                {
                	$predicate.setAttribute($att1.attribute);
                }
                EQUAL1
                lit1=tokenLiteral
                {
                	$predicate.setLiteral($lit1.literal);
                }
                
	)
	;
	
simplePredicateAttribute returns [SimplePredicateAttribute predicate]
	@init{
        	$predicate = new SimplePredicateAttribute();
        }
        :
	(
		att1=tokenAttribute
                {
                	$predicate.setLeftAtt($att1.attribute);
                }
                EQUAL1
                att2=tokenAttribute
                {
                	$predicate.setRightAtt($att2.attribute);
                }
	)
	;

        
tokenAttribute returns [TokenAttribute attribute]
	@init{
        	$attribute = new TokenAttribute();
        }
        :
        (
                (
                table1=tokenIdentifier {
                	TokenTable table = new TokenTable();
                	table.setName($table1.identifier);
                	$attribute.setTable(table);
                }
                DOT
                )?
                id1=tokenIdentifier {
                	$attribute.setName($id1.identifier);
                }
        )
        ;                

tokenTable returns [TokenTable table]
	@init{
        	$table = new TokenTable();
        }
        :
        (
                (
                schema1=tokenIdentifier {
                        TokenSchema schema = new TokenSchema();
                	$table.setSchema(schema);
                }
                DOT
                )?
                id1=tokenIdentifier {
                	$table.setName($id1.identifier);
                }
        )
        ;
        
tokenSchema returns [TokenSchema schema]
	@init{
        	$schema = new TokenSchema();
        }
        :
        (
                tokenIdentifier {
                	$schema.setName($tokenIdentifier.identifier);
                }
        )
        ;
        
tokenIdentifier returns [TokenIdentifier identifier]
	@init{
        	$identifier = null;
        }
        :
        (
                identifierText {
                	$identifier = new TokenIdentifier($identifierText.text);
                }
        )
        ;        


tokenDataType returns [TokenDataType dataType]
	@init{
        	$dataType = null;
        }
        :
        (
                TYPE_VARCHAR {
                	$dataType = new TokenDataType($TYPE_VARCHAR.text);
                }
                |
                TYPE_INTEGER {
                	$dataType = new TokenDataType($TYPE_INTEGER.text);
                }
        )
        ;
        
tokenLiteral returns [TokenLiteral literal]
	@init{
        	$literal = null;
        }
        :
        (
        	(
                tokenIntegerLiteral {
                	$literal = $tokenIntegerLiteral.literal;
                }
                |
                tokenStringLiteral {
                	$literal = $tokenStringLiteral.literal;
                }
                )
        )
        ;        

tokenStringLiteral returns [TokenStringLiteral literal]
	@init{
        	$literal = null;
        }
        :
        (
                lit1=LITERAL_STRING {
                	$literal = new TokenStringLiteral($lit1.text.substring(1, $lit1.text.length()-1));
                }
        )
        ;
        
tokenIntegerLiteral returns [TokenIntegerLiteral literal]
	@init{
        	$literal = null;
        }
        :
        (
                LITERAL_INTEGER {
                	$literal = new TokenIntegerLiteral($LITERAL_INTEGER.text);
                }
        )
        ;
        
 identifierText returns [String text]
 	:	
 	(
 		(
 		id1 = IDENTIFIER {
                	$text = $id1.text.toUpperCase();
                }
                )
         	|
         	(
         	QUOTE_DOUBLE
         	id2 = IDENTIFIER {
                	$text = $id2.text;
                }
                QUOTE_DOUBLE
         	)       
 	)
 	;
        
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

KEYWORD_CREATE: C R E A T E;
KEYWORD_DROP: D R O P;	 
KEYWORD_SELECT: S E L E C T;
KEYWORD_FROM: F R O M;
KEYWORD_WHERE: W H E R E;
KEYWORD_IN: I N;

KEYWORD_CONNECTION: C O N N E C T I O N;	 
KEYWORD_SCHEMA: S C H E M A;
KEYWORD_TABLE: T A B L E;

KEYWORD_URL: U R L;
KEYWORD_USER: U S E R;
KEYWORD_PASSWD: P A S S W O R D;
KEYWORD_STORE: S T O R E;

TYPE_VARCHAR: V A R C H A R;
TYPE_INTEGER: (I N T | I N T E G E R);

LITERAL_STRING
    :
    (QUOTED_STRING)
    ;

LITERAL_INTEGER 
    : 
    (DIGIT)+ 
    ;

IDENTIFIER
    :  
    ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* 
    )
    ;

IGNORE_CHAR: (WS|CONTROL_CHAR) {$channel=HIDDEN;};

     
fragment WS: (' ') ;
fragment CONTROL_CHAR: ('\r'|'\t'|'\u000B'|'\f'|'\n');
fragment QUOTED_STRING: QUOTE_SINGLE (~QUOTE_SINGLE)* QUOTE_SINGLE;
fragment DIGIT  : '0'..'9' ;
fragment CHAR : ('A' .. 'Z'|'a' .. 'z');

fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');