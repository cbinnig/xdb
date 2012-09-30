grammar FunSQL;

options
{
    backtrack=true;
    memoize=true;
}

tokens {
    EQUAL1              =   '=';
    EQUAL2              =   '==';
    NOT_EQUAL1          =   '!=';
    NOT_EQUAL2          =   '<>';
    LESS_THAN           =   '<';
    LESS_EQUAL1         =   '<=';
    LESS_EQUAL2         =   '!>';
    GREATER_THAN        =   '>';
    GREATER_EQUAL1      =   '>=';
    GREATER_EQUAL2      =   '!<';
    SHIFT_LEFT          =   '<<';
    SHIFT_RIGHT         =   '>>';
    AMPERSAND           =   '&';
    HAT                 =   '^';
    PIPE                =   '|';
    DOUBLE_PIPE         =   '||';
    DIV                 =   '/';
    MULT                =   '*';
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

import org.xdb.funsql.compile.expression.*;
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

statement returns [AbstractServerStmt stmt]
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
        	int expressionNumber = 0;
        	int tableNumber = 0;
        }
        :
        (
                KEYWORD_SELECT
                expr1=abstractExpression
                {
                	$stmt.addExpression($expr1.expression);
                	expressionNumber++;
                }
                ( 
                KEYWORD_AS
		alias1=tokenIdentifier
		{
			$stmt.setExpressionAlias(expressionNumber-1, $alias1.identifier);
		}                 
                )?
                (
                COMMA
                expr2=abstractExpression
                {
                	$stmt.addExpression($expr2.expression);
                	expressionNumber++;
                }
                ( 
                KEYWORD_AS
		alias2=tokenIdentifier
		{
			$stmt.setExpressionAlias(expressionNumber-1, $alias2.identifier);
		}                 
                )?
                )*
                KEYWORD_FROM
                table1=tokenTable 
                {
                	$stmt.addTable($table1.table);
                	tableNumber++;
                }
                ( 
                KEYWORD_AS
		talias1=tokenIdentifier
		{
			$stmt.setTableAlias(tableNumber-1, $talias1.identifier);
		}                 
                )?
                (
                COMMA
                table2=tokenTable
                {
                	$stmt.addTable($table2.table);
                	tableNumber++;
                }
                (
                KEYWORD_AS
		talias2=tokenIdentifier
		{
			$stmt.setTableAlias(tableNumber-1, $talias2.identifier);
		}                 
                )?
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
	:	predicate1=complexPredicateOr{
			$predicate = $predicate1.predicateOr;
		}
	;
	        
complexPredicateOr returns [ComplexPredicate predicateOr]
	@init{
        	$predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
        }
	:	
	(
		predicate1=complexPredicateAnd{
			$predicateOr.setPredicate1($predicate1.predicateAnd);
		}
		( 
			KEYWORD_OR{
				$predicateOr.addOr();
			}
			predicate2=complexPredicateAnd{
				$predicateOr.addPredicate2($predicate2.predicateAnd);
			} 
		)* 
	)
	;

complexPredicateAnd returns [ComplexPredicate predicateAnd]
	@init{
        	$predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
        }
	:	
	( 
		predicate1=complexPredicateNot{
			$predicateAnd.setPredicate1($predicate1.predicateNot);
		}
		( 
			KEYWORD_AND{
				$predicateAnd.addAnd();
			}
			predicate2=complexPredicateNot {
				$predicateAnd.addPredicate2($predicate2.predicateNot);
			}
		)* 
	)
	;

complexPredicateNot returns [ComplexPredicate predicateNot]
	@init{
        	$predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
        }
	:
	(
		( 
		KEYWORD_NOT{
			$predicateNot.negate();
		}
		)? 
		predicate1=complexPredicate{
			$predicateNot.setPredicate1($predicate1.predicate);
		}
	) 
	;

complexPredicate returns [AbstractPredicate predicate]
	:
	(
		predicate1=parenPredicate
		{
			$predicate = $predicate1.predicate;
		}
		|	
		predicate2=simplePredicate
		{
			$predicate = $predicate2.predicate;
		}
	)
	;

parenPredicate returns [AbstractPredicate predicate]
	:	
		LPAREN 
		predicate1=abstractPredicate {
			$predicate = $predicate1.predicate;
		}
		RPAREN
	;
	
simplePredicate returns [SimplePredicate predicate]
	@init{
        	$predicate = new SimplePredicate();
        }
        :
        (
		expr1=abstractExpression
                {
                	$predicate.setExpr1($expr1.expression);
                }
                
                comp=tokenCompOperator{
                	$predicate.setComp(EnumCompOperator.get($comp.text));
                }
                
                expr2=abstractExpression
                {
                	$predicate.setExpr2($expr2.expression);
                }
        )
        ;


abstractExpression returns [AbstractExpression expression]
	:	expression1=complexExpressionAdd{
			$expression = $expression1.expression;
		}
	;

complexExpressionAdd returns [ComplexExpression expression]
	@init{
        	$expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
        }
	:	
	( 
		expression1=complexExpressionMult{
			$expression.setExpr1($expression1.expression);
		}
		( 
			op1=tokenAddOperator{
                		$expression.addOp(EnumExprOperator.get($op1.text));
                	}
			expression2=complexExpressionMult {
				$expression.addExpr2($expression2.expression);
			}
		)* 
	)
	;

complexExpressionMult returns [ComplexExpression expression]
	@init{
        	$expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
        }
	:	
	( 
		expression1=complexExpressionSigned{
			$expression.setExpr1($expression1.expression);
		}
		( 
			op1=tokenMultOperator{
                		$expression.addOp(EnumExprOperator.get($op1.text));
                	}
			expression2=complexExpressionSigned {
				$expression.addExpr2($expression2.expression);
			}
		)* 
	)
	;


complexExpressionSigned returns [ComplexExpression expression]
	@init{
        	$expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
        }
	:
	(
		( 
		MINUS{
			$expression.negate();
		}
		|
		PLUS
		)? 
		expression1=complexExpression{
			$expression.setExpr1($expression1.expression);
		}
	) 
	;
        
complexExpression returns [AbstractExpression expression]
	:
	(
		expression1=parenExpression
		{
			$expression = $expression1.expression;
		}
		|	
		expression2=simpleExpression
		{
			$expression = $expression2.expression;
		}
	)
	;

parenExpression returns [AbstractExpression expression]
	:	
		LPAREN 
		expression1=abstractExpression {
			$expression = $expression1.expression;
		}
		RPAREN
	;
	        
simpleExpression returns [SimpleExpression expression]
	@init{
        	$expression = new SimpleExpression();
        }
        :
        (
		(
		att1=tokenAttribute
                {
                	$expression.setOper($att1.attribute);
                }
                |
                lit1=tokenLiteral
                {
                	$expression.setOper($lit1.literal);
                }
                )
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
                |
                tokenDecimalLiteral {
                	$literal = $tokenDecimalLiteral.literal;
                }
                |
                tokenDateLiteral {
                	$literal = $tokenDateLiteral.literal;
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
        
        
 tokenDecimalLiteral returns [TokenDecimalLiteral literal]
	@init{
        	$literal = null;
        }
        :
        (
                LITERAL_DECIMAL {
                	$literal = new TokenDecimalLiteral($LITERAL_DECIMAL.text);
                }
        )
        ;
        
 tokenDateLiteral returns [TokenDateLiteral literal]
	@init{
        	$literal = null;
        }
        :
        (
                LITERAL_DATE {
                	$literal = new TokenDateLiteral($LITERAL_DATE.text);
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
    
tokenAddOperator
    :
    (
    PLUS |
    MINUS 
    )
    ;
    
tokenMultOperator
    :
    (
    MULT |
    DIV
    )
    ;

tokenCompOperator
    :
    (
    EQUAL1 |
    NOT_EQUAL1 |
    NOT_EQUAL2 |
    LESS_THAN |
    LESS_EQUAL1 |
    LESS_EQUAL2 |
    GREATER_EQUAL1 |
    GREATER_EQUAL2
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
KEYWORD_AND: A N D;
KEYWORD_OR: O R;
KEYWORD_NOT: N O T;
KEYWORD_AS: A S;

KEYWORD_CONNECTION: C O N N E C T I O N;	 
KEYWORD_SCHEMA: S C H E M A;
KEYWORD_TABLE: T A B L E;

KEYWORD_URL: U R L;
KEYWORD_USER: U S E R;
KEYWORD_PASSWD: P A S S W O R D;
KEYWORD_STORE: S T O R E;

TYPE_VARCHAR: V A R C H A R;
TYPE_INTEGER: (I N T | I N T E G E R);
    
LITERAL_DATE
    :
    (D A T E QUOTED_STRING)
    ;

LITERAL_STRING
    :
    (QUOTED_STRING)
    ;

LITERAL_DECIMAL 
    : 
    (DIGIT)+ DOT DIGIT*
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