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

@lexer::header { 
package org.xdb.funsql.compile.antlr;
}

@parser::header { 
package org.xdb.funsql.compile.antlr; 

import org.xdb.funsql.compile.expression.*;
import org.xdb.funsql.compile.predicate.*;
import org.xdb.funsql.compile.tokens.*;
import org.xdb.funsql.statement.*;
}


@lexer::members {
    @Override
    public void reportError(RecognitionException e) {
        throw new RuntimeException(e);
    }
} 

@parser::members {
  @Override
  protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
    throw new MismatchedTokenException(ttype, input);
  }

  @Override
  public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
    throw e;
  }
}
 
@rulecatch {
    catch (RecognitionException e) {
    	reportError(e);
        throw e;
    }
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
                	$stmt.setStmtString($createSchemaStatement.text);
                }
                |
                dropSchemaStatement 
                {
                	$stmt = $dropSchemaStatement.stmt;
                	$stmt.setStmtString($dropSchemaStatement.text);
                }
                |
                createConnectionStatement 
                {
                	$stmt = $createConnectionStatement.stmt;
                	$stmt.setStmtString($createConnectionStatement.text);
                }
                |
                dropConnectionStatement 
                {
                	$stmt = $dropConnectionStatement.stmt;
                	$stmt.setStmtString($dropConnectionStatement.text);
                }
                |
                createTableStatement 
                {
                	$stmt = $createTableStatement.stmt;
                	$stmt.setStmtString($createTableStatement.text);
                }
                |
                dropTableStatement 
                {
                	$stmt = $dropTableStatement.stmt;
                	$stmt.setStmtString($dropTableStatement.text);
                }
                |
                createFunctionStatement 
                {
                	$stmt = $createFunctionStatement.stmt;
                	$stmt.setStmtString($createFunctionStatement.text);
                }
                |
                dropFunctionStatement 
                {
                	$stmt = $dropFunctionStatement.stmt;
                	$stmt.setStmtString($dropFunctionStatement.text);
                }
                |
                callFunctionStatement 
                {
                	$stmt = $callFunctionStatement.stmt;
                	$stmt.setStmtString($callFunctionStatement.text);
                }
                |
                selectStatement
                {
                	$stmt = $selectStatement.stmt;
                	$stmt.setStmtString($selectStatement.text);
                }
                |
                loadDataInfileStatement
                {
                	$stmt = $loadDataInfileStatement.stmt;
                	$stmt.setStmtString($loadDataInfileStatement.text);
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
                //Connection info
                (
                  (
                    KEYWORD_IN KEYWORD_CONNECTION
                    connection1=tokenIdentifier {
                      $stmt.addConnection($connection1.identifier);
                    }
                  )
                  |
                  ( 
                    KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION 
                    connectionR1=tokenIdentifier {
                      $stmt.addConnection($connectionR1.identifier);
                    }
                    ( 
                      COMMA 
                      connectionR2=tokenIdentifier {
                	$stmt.addConnection($connectionR2.identifier);
                      }
                    )*
                  )
                )?
                //End Connection info
                
                //Partitioning info
                (
                KEYWORD_PARTITIONED KEYWORD_BY 
                method=identifierText
                {
                	$stmt.setPartitionType($method.text);
                }
                
                //Partition attributes 
                LPAREN
                patt1=tokenAttribute{
                  $stmt.addPartitionAttribute($patt1.attribute);
                }
                (
                  KEYWORD_REFERENCES ratt1=tokenAttribute{
                    $stmt.addRefPartitionAttribute($ratt1.attribute);
                  }
                )?
                (
                  COMMA
                  patt2=tokenAttribute{
                    $stmt.addPartitionAttribute($patt2.attribute);
                  }
                  (
                    KEYWORD_REFERENCES ratt2=tokenAttribute{
                      $stmt.addRefPartitionAttribute($ratt2.attribute);
                    }
                  )?
                )*
                RPAREN
                //End Partition attributes 
                   
                //Partition details
                (
                  LPAREN
                  p1=identifierText{
                    $stmt.addPartition($p1.text);
                  }
                  ( 
                    //Connection specification
                    (
                      KEYWORD_IN KEYWORD_CONNECTION
                      c1=tokenIdentifier {
                	$stmt.addPConnection($p1.text,$c1.identifier);
                      }
                    )
                    |
                    (
                      KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION
                      c2=tokenIdentifier {
                	$stmt.addPConnection($p1.text,$c2.identifier);
                      }
                      (
                        COMMA 
                        c3=tokenIdentifier {
                	  $stmt.addPConnection($p1.text,$c3.identifier);
                        }
                      )*
                    )
                    // End Connection specification
                  )
                  (
                    COMMA
                    p2=identifierText {
                	$stmt.addPartition($p2.text);
                    }
                    ( 
                      //Connection specification
                      (
                        KEYWORD_IN KEYWORD_CONNECTION
                        c2=tokenIdentifier {
                	  $stmt.addPConnection($p2.text,$c2.identifier);
                        }
                      )
                      |
                      (
                        KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION
                        c2=tokenIdentifier {
                	  $stmt.addPConnection($p2.text,$c2.identifier);
                        }
                        (
                          COMMA  
                          c3=tokenIdentifier {
                	    $stmt.addPConnection($p2.text,$c3.identifier);
                          }
                        )*
                      )
                      //End Connection specification
                    )
                  )*
                  RPAREN
                )?
                //End Partition details
              )?
              //End Partitioning info       
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
	
createFunctionStatement returns [CreateFunctionStmt stmt]
        @init{
        	$stmt = new CreateFunctionStmt();
        }
        :
        (
                KEYWORD_CREATE
                KEYWORD_FUNCTION
                function1=tokenFunction{
                	$stmt.setFunction($function1.function);
                }
            	LPAREN
	        (
                KEYWORD_IN
                var1=tokenVariable{
               		$stmt.addInParam(var1);
				}
				KEYWORD_TABLE
				COMMA
		)*
		(           
                KEYWORD_OUT
                var2=tokenVariable{
               		$stmt.addOutParam(var2);
				}
				KEYWORD_TABLE
		)
		(
			COMMA
			KEYWORD_OUT
         	var2=tokenVariable{
           		$stmt.addOutParam(var2);
			}
			KEYWORD_TABLE
		)*
		RPAREN
		KEYWORD_BEGIN
		(
		(
		ass1=tokenAssignment{
                	$stmt.addAssignment(ass1.getVar(), ass1.getSelStmt());
                	$stmt.addAssignment(ass1);
                }  
                )
                |
                (
                call1 = tokenFunctionCall{
                	$stmt.addFunctionCall(call1);                	
                }
                )              
		)*
		KEYWORD_END   
		SEMI             
	)
	;
	
dropFunctionStatement returns [DropFunctionStmt stmt]
        @init{
        	$stmt = new DropFunctionStmt();
        }
        :
        (
                KEYWORD_DROP
                KEYWORD_FUNCTION
                fun1=tokenFunction {
                	$stmt.setFunction($fun1.function);
                }
	)
	;
	
callFunctionStatement returns [CallFunctionStmt stmt]
        @init{
        	$stmt = new CallFunctionStmt();
        }
        :
        (
                KEYWORD_CALL
                KEYWORD_FUNCTION
                fun1=tokenFunction {
                	$stmt.setFunction($fun1.function);
                }
	)
	;
		
selectStatement returns [SelectStmt stmt]
        @init{
        	$stmt = new SelectStmt();
        	int i=0;
        }
        :
        (
        	//SELECT
                KEYWORD_SELECT
                selExpr1=abstractExpression
                {
                	$stmt.addSelExpression($selExpr1.expression);
                	++i;
                }
                (
                	KEYWORD_AS
                	selAlias1=tokenIdentifier
                	{
                		$stmt.setSelAlias(i-1, $selAlias1.identifier);
                	}
                )?
                (
                COMMA
                selExpr2=abstractExpression
                {
                	$stmt.addSelExpression($selExpr2.expression);
                	++i;
                }
                (
                	KEYWORD_AS
                	selAlias2=tokenIdentifier
                	{
                		$stmt.setSelAlias(i-1, $selAlias2.identifier);
                	}
                )?
                )*
                
                //FROM
                KEYWORD_FROM
                table1=tokenTable 
                {
                	$stmt.addTable($table1.table);
                	i=1;
                }
                (
                	KEYWORD_AS
                	tableAlias1=tokenIdentifier
                	{
                		$stmt.setTableAlias(i-1, $tableAlias1.identifier);
                	}
                )?
                (
                COMMA
                table2=tokenTable
                {
                	$stmt.addTable($table2.table);
                	++i;
                }
                (
                	KEYWORD_AS
                	tableAlias2=tokenIdentifier
                	{
                		$stmt.setTableAlias(i-1, $tableAlias2.identifier);
                	}
                )?
                )*
                
                (
                KEYWORD_WHERE
                predicate1=abstractPredicate
                {
                	$stmt.setWherePredicate($predicate1.predicate);
                }
                )?
                
                //GROUB BY
                (
                KEYWORD_GROUP KEYWORD_BY
                groupExpr1=abstractExpression
                {
                	$stmt.addGroupExpression($groupExpr1.expression);
                	++i;
                }
                (
                COMMA
                groupExpr2=abstractExpression
                {
                	$stmt.addGroupExpression($groupExpr2.expression);
                	++i;
                }
                )*
                )?
                
                //HAVING
                (
                KEYWORD_HAVING
                havingPred=abstractPredicate
                {
                	$stmt.setHavingPredicate($havingPred.predicate);
                }
                )?
	)
	;
	
loadDataInfileStatement returns [LoadDataInfileStmt stmt]
        @init{
        	$stmt = new LoadDataInfileStmt();
        }
        :
        (
        KEYWORD_LOAD
        KEYWORD_DATA
        KEYWORD_INFILE
        filename1=identifierText {
                	$stmt.setTokenFilename($filename1.text);
                }
        KEYWORD_INTO
        KEYWORD_TABLE
        table1=tokenTable {
                	$stmt.setTokenTable($table1.table);
                }
        (
        	KEYWORD_PARTITION
        	LPAREN
        	partition1=identifierText {
        			$stmt.setTokenPartition($partition1.text);
			}
        	(
	        	COMMA
	        	partition2=identifierText {
	        			$stmt.setTokenPartition($partition2.text);
				}
        	)*
        	RPAREN
        )?        
	);


abstractPredicate returns [AbstractPredicate predicate]
	:	
		predicate1=complexPredicateOr{
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
	:
	expression1=complexExpressionAdd{
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
		expression2=aggregationExpression
		{
			$expression = $expression2.expression;
		}
		|	
		expression3=simpleExpression
		{
			$expression = $expression3.expression;
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
	   
      
aggregationExpression returns [AggregationExpression expression]
	@init{
        	$expression = new AggregationExpression();
        }
        :
        (
		agg1=FUNCTION_AGGREGATION
		{
			$expression.setAggregation($agg1.text.toUpperCase());
		}
		LPAREN 
		(
		KEYWORD_DISTINCT{
			$expression.setDistinct();
		}
		)?
		expr1=abstractExpression {
			$expression.setExpression($expr1.expression);
		}
		RPAREN
        )
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
                	$table.setVariable(false);
                }
                DOT
                )?                  
                id1=tokenIdentifier {
                	$table.setName($id1.identifier);
                }
         )|(                
                (
                COLON
                )?  
                 id1=tokenIdentifier {
                	$table.setName($id1.identifier);                	
                	$table.setVariable(true);
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
        
tokenFunction returns [TokenFunction function]
	@init{
        	$function = new TokenFunction();
        }
        :
        (
                                (
                schema1=tokenIdentifier {
                        TokenSchema schema = new TokenSchema();
                	$function.setSchema(schema);
                }
                DOT
                )?
                id1=tokenIdentifier {
                	$function.setName($id1.identifier);
                }
        )
        ;
        
tokenVariable returns [TokenVariable variable]
	@init{
        	$variable = null;
        }
        :
        (
                variableText {
                $variable = new TokenVariable($variableText.text);	
                }
        )
        ;       
        
tokenAssignment returns [TokenAssignment ass]
	@init{
	 	$ass=new TokenAssignment();
	 }
	 :
	 (
		 (
		 COLON		 
		 var1 = tokenVariable{
		 $ass.setReference(true);
		 $ass.setVar($var1.variable);
		 }
		 EQUAL1
		 selstmt1=selectStatement{
		 $ass.setSelStmt($selstmt1.stmt);
		 }
		 )
		 |
		 (
		 KEYWORD_VAR
		 var2 = tokenVariable{		 
		 $ass.setReference(false);
		 $ass.setVar($var2.variable);
		 }
		 EQUAL1
		 selstmt2=selectStatement{
		 $ass.setSelStmt($selstmt2.stmt);
		 }		 
		 )
		 |
		 (
		 KEYWORD_VAR
		 var3 = tokenVariable{		 
		 $ass.setReference(false);
		 $ass.setVar($var3.variable);
		 }
		 EQUAL1
		 )	
		 COLON		 
		 var4 = tokenVariable{
		 $ass.setReference(true);
		 $ass.setVar($var4.variable);
		 }	
	 )
	  SEMI	 
	 ;

tokenFunctionCall returns [TokenFunctionCall call] 
	@init{
	 	$call=new TokenFunctionCall();
	 }
	 :
	 (
		 KEYWORD_CALL
		 KEYWORD_FUNCTION
		 fun1=tokenFunction{
		 $call.setFun(fun1);
		 }
		 LPAREN
		 (
			 COLON		 
			 var1 = tokenVariable{
			 $call.addInVar(var1);
			 }	
		 )*	 
		 (
			 KEYWORD_VAR
			 var2 = tokenVariable{		
			 $call.addOutVar(var2);
			 }
			 COMMA
		 )*
		 (		 	 
			 KEYWORD_VAR
			 var3 = tokenVariable{		
			 $call.addOutVar(var3);
			 }
		 )
		 RPAREN
		 SEMI	
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
                |
                TYPE_DECIMAL {
                	$dataType = new TokenDataType($TYPE_DECIMAL.text);
                }
                |
                TYPE_DATE {
                	$dataType = new TokenDataType($TYPE_DATE.text);
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
                
                TYPE_DATE
                LITERAL_STRING {
                	$literal = new TokenDateLiteral($LITERAL_STRING.text);
                }
        )
        ;
        
 variableText returns [String text]
 	:	
 	(
 	
 		var1 = IDENTIFIER {
 		$text = $var1.text.toUpperCase();
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
         	|
         	(
	         	QUOTE_DOUBLE
	         	id2 = IDENTIFIER {
				        $text = $id2.text;
			}
		        ( 	
		         	DIV
		         	id3 = IDENTIFIER {
					        $text += "/";
					        $text += $id3.text;
				}
			)*
			DOT
			(
				id4 = IDENTIFIER {
			        	$text += ".";
			        	$text += $id4.text;
			        }
		        )
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
    GREATER_EQUAL2 |
    GREATER_THAN |
    KEYWORD_LIKE
    )
    ;
    
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
FUNCTION_AGGREGATION
	:
	(KEYWORD_SUM|KEYWORD_MIN|KEYWORD_MAX|KEYWORD_AVG|KEYWORD_COUNT)
	;

KEYWORD_PARTITION:	P A R T I T I O N;
KEYWORD_REFERENCES:	R E F E R E N C E S;
KEYWORD_INTO:	I N T O;
KEYWORD_INFILE:	I N F I L E;
KEYWORD_DATA:	D A T A;
KEYWORD_LOAD:	L O A D;
KEYWORD_CALL: C A L L;
KEYWORD_CREATE: C R E A T E;
KEYWORD_DROP: D R O P;	 
KEYWORD_SELECT: S E L E C T;
KEYWORD_FROM: F R O M;
KEYWORD_WHERE: W H E R E;
KEYWORD_HAVING: H A V I N G;
KEYWORD_GROUP: G R O U P;
KEYWORD_BY: B Y;
KEYWORD_IN: I N;
KEYWORD_OUT: O U T;
KEYWORD_AND: A N D;
KEYWORD_OR: O R;
KEYWORD_NOT: N O T;
KEYWORD_AS: A S;
KEYWORD_LIKE: L I K E;	
fragment KEYWORD_SUM: S U M;
fragment KEYWORD_MIN: M I N;
fragment KEYWORD_MAX: M A X;
fragment KEYWORD_AVG: A V G;
fragment KEYWORD_COUNT: C O U N T;
KEYWORD_DISTINCT: D I S T I N C T;

KEYWORD_CONNECTION: C O N N E C T I O N;
KEYWORD_PARTITIONED: P A R T I O N E D;			 
KEYWORD_REPLICATED: R E P L I C A T E D;	
KEYWORD_SCHEMA: S C H E M A;
KEYWORD_TABLE: T A B L E;
KEYWORD_FUNCTION: F U N C T I O N;	
KEYWORD_BEGIN: B E G I N;
KEYWORD_END: E N D;
KEYWORD_VAR: V A R;

KEYWORD_URL: U R L;
KEYWORD_USER: U S E R;
KEYWORD_PASSWD: P A S S W O R D;
KEYWORD_STORE: S T O R E;
	
TYPE_VARCHAR: V A R C H A R;
TYPE_INTEGER: (I N T | I N T E G E R);
TYPE_DECIMAL: D E C I M A L;
TYPE_DATE: D A T E;

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