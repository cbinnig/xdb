// $ANTLR 3.5 /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g 2013-03-08 10:36:41
 
package org.xdb.funsql.compile.antlr; 

import org.xdb.funsql.compile.expression.*;
import org.xdb.funsql.compile.predicate.*;
import org.xdb.funsql.compile.tokens.*;
import org.xdb.funsql.statement.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("all")
public class FunSQLParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", 
		"AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", 
		"D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", 
		"F", "FUNCTION_AGGREGATION", "G", "GREATER_EQUAL1", "GREATER_EQUAL2", 
		"GREATER_THAN", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", 
		"KEYWORD_AND", "KEYWORD_AS", "KEYWORD_AVG", "KEYWORD_BEGIN", "KEYWORD_BY", 
		"KEYWORD_CALL", "KEYWORD_CONNECTION", "KEYWORD_COUNT", "KEYWORD_CREATE", 
		"KEYWORD_DISTINCT", "KEYWORD_DROP", "KEYWORD_END", "KEYWORD_FROM", "KEYWORD_FUNCTION", 
		"KEYWORD_GROUP", "KEYWORD_HAVING", "KEYWORD_IN", "KEYWORD_MAX", "KEYWORD_MIN", 
		"KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_OUT", "KEYWORD_PARTITIONED", "KEYWORD_PASSWD", 
		"KEYWORD_REPLICATED", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", 
		"KEYWORD_SUM", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_VAR", 
		"KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", "LESS_THAN", 
		"LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", 
		"MINUS", "MOD", "MULT", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", 
		"PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", 
		"QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", 
		"SHIFT_RIGHT", "T", "TILDE", "TYPE_DATE", "TYPE_DECIMAL", "TYPE_INTEGER", 
		"TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
	};
	public static final int EOF=-1;
	public static final int A=4;
	public static final int AMPERSAND=5;
	public static final int APOSTROPHE=6;
	public static final int AT=7;
	public static final int B=8;
	public static final int BACKSLASH=9;
	public static final int C=10;
	public static final int CHAR=11;
	public static final int COLON=12;
	public static final int COMMA=13;
	public static final int CONTROL_CHAR=14;
	public static final int D=15;
	public static final int DIGIT=16;
	public static final int DIV=17;
	public static final int DOLLAR=18;
	public static final int DOT=19;
	public static final int DOUBLE_PIPE=20;
	public static final int E=21;
	public static final int EQUAL1=22;
	public static final int EQUAL2=23;
	public static final int F=24;
	public static final int FUNCTION_AGGREGATION=25;
	public static final int G=26;
	public static final int GREATER_EQUAL1=27;
	public static final int GREATER_EQUAL2=28;
	public static final int GREATER_THAN=29;
	public static final int H=30;
	public static final int HAT=31;
	public static final int I=32;
	public static final int IDENTIFIER=33;
	public static final int IGNORE_CHAR=34;
	public static final int J=35;
	public static final int K=36;
	public static final int KEYWORD_AND=37;
	public static final int KEYWORD_AS=38;
	public static final int KEYWORD_AVG=39;
	public static final int KEYWORD_BEGIN=40;
	public static final int KEYWORD_BY=41;
	public static final int KEYWORD_CALL=42;
	public static final int KEYWORD_CONNECTION=43;
	public static final int KEYWORD_COUNT=44;
	public static final int KEYWORD_CREATE=45;
	public static final int KEYWORD_DISTINCT=46;
	public static final int KEYWORD_DROP=47;
	public static final int KEYWORD_END=48;
	public static final int KEYWORD_FROM=49;
	public static final int KEYWORD_FUNCTION=50;
	public static final int KEYWORD_GROUP=51;
	public static final int KEYWORD_HAVING=52;
	public static final int KEYWORD_IN=53;
	public static final int KEYWORD_MAX=54;
	public static final int KEYWORD_MIN=55;
	public static final int KEYWORD_NOT=56;
	public static final int KEYWORD_OR=57;
	public static final int KEYWORD_OUT=58;
	public static final int KEYWORD_PARTITIONED=59;
	public static final int KEYWORD_PASSWD=60;
	public static final int KEYWORD_REPLICATED=61;
	public static final int KEYWORD_SCHEMA=62;
	public static final int KEYWORD_SELECT=63;
	public static final int KEYWORD_STORE=64;
	public static final int KEYWORD_SUM=65;
	public static final int KEYWORD_TABLE=66;
	public static final int KEYWORD_URL=67;
	public static final int KEYWORD_USER=68;
	public static final int KEYWORD_VAR=69;
	public static final int KEYWORD_WHERE=70;
	public static final int L=71;
	public static final int LBRACKET=72;
	public static final int LESS_EQUAL1=73;
	public static final int LESS_EQUAL2=74;
	public static final int LESS_THAN=75;
	public static final int LITERAL_DECIMAL=76;
	public static final int LITERAL_INTEGER=77;
	public static final int LITERAL_STRING=78;
	public static final int LPAREN=79;
	public static final int M=80;
	public static final int MINUS=81;
	public static final int MOD=82;
	public static final int MULT=83;
	public static final int N=84;
	public static final int NOT_EQUAL1=85;
	public static final int NOT_EQUAL2=86;
	public static final int O=87;
	public static final int P=88;
	public static final int PIPE=89;
	public static final int PLUS=90;
	public static final int Q=91;
	public static final int QUESTION=92;
	public static final int QUOTED_STRING=93;
	public static final int QUOTE_DOUBLE=94;
	public static final int QUOTE_SINGLE=95;
	public static final int QUOTE_TRIPLE=96;
	public static final int R=97;
	public static final int RBRACKET=98;
	public static final int RPAREN=99;
	public static final int S=100;
	public static final int SEMI=101;
	public static final int SHIFT_LEFT=102;
	public static final int SHIFT_RIGHT=103;
	public static final int T=104;
	public static final int TILDE=105;
	public static final int TYPE_DATE=106;
	public static final int TYPE_DECIMAL=107;
	public static final int TYPE_INTEGER=108;
	public static final int TYPE_VARCHAR=109;
	public static final int U=110;
	public static final int UNDERSCORE=111;
	public static final int V=112;
	public static final int W=113;
	public static final int WS=114;
	public static final int X=115;
	public static final int Y=116;
	public static final int Z=117;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public FunSQLParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public FunSQLParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
		this.state.ruleMemo = new HashMap[114+1];


	}

	@Override public String[] getTokenNames() { return FunSQLParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g"; }


	  @Override
	  protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
	    throw new MismatchedTokenException(ttype, input);
	  }

	  @Override
	  public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
	    throw e;
	  }



	// $ANTLR start "statement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:93:1: statement returns [AbstractServerStmt stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? ) ;
	public final AbstractServerStmt statement() throws RecognitionException {
		AbstractServerStmt stmt = null;

		int statement_StartIndex = input.index();

		ParserRuleReturnScope createSchemaStatement1 =null;
		ParserRuleReturnScope dropSchemaStatement2 =null;
		ParserRuleReturnScope createConnectionStatement3 =null;
		ParserRuleReturnScope dropConnectionStatement4 =null;
		ParserRuleReturnScope createTableStatement5 =null;
		ParserRuleReturnScope dropTableStatement6 =null;
		ParserRuleReturnScope createFunctionStatement7 =null;
		ParserRuleReturnScope dropFunctionStatement8 =null;
		ParserRuleReturnScope callFunctionStatement9 =null;
		ParserRuleReturnScope selectStatement10 =null;


		        	stmt = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return stmt; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:97:9: ( ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:98:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:98:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:99:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )?
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:99:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement )
			int alt1=10;
			switch ( input.LA(1) ) {
			case KEYWORD_CREATE:
				{
				switch ( input.LA(2) ) {
				case KEYWORD_SCHEMA:
					{
					alt1=1;
					}
					break;
				case KEYWORD_CONNECTION:
					{
					alt1=3;
					}
					break;
				case KEYWORD_TABLE:
					{
					alt1=5;
					}
					break;
				case KEYWORD_FUNCTION:
					{
					alt1=7;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return stmt;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case KEYWORD_DROP:
				{
				switch ( input.LA(2) ) {
				case KEYWORD_SCHEMA:
					{
					alt1=2;
					}
					break;
				case KEYWORD_CONNECTION:
					{
					alt1=4;
					}
					break;
				case KEYWORD_TABLE:
					{
					alt1=6;
					}
					break;
				case KEYWORD_FUNCTION:
					{
					alt1=8;
					}
					break;
				default:
					if (state.backtracking>0) {state.failed=true; return stmt;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case KEYWORD_CALL:
				{
				alt1=9;
				}
				break;
			case KEYWORD_SELECT:
				{
				alt1=10;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return stmt;}
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:100:17: createSchemaStatement
					{
					pushFollow(FOLLOW_createSchemaStatement_in_statement1105);
					createSchemaStatement1=createSchemaStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (createSchemaStatement1!=null?((FunSQLParser.createSchemaStatement_return)createSchemaStatement1).stmt:null);
					                	stmt.setStmtString((createSchemaStatement1!=null?input.toString(createSchemaStatement1.start,createSchemaStatement1.stop):null));
					                }
					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:106:17: dropSchemaStatement
					{
					pushFollow(FOLLOW_dropSchemaStatement_in_statement1160);
					dropSchemaStatement2=dropSchemaStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (dropSchemaStatement2!=null?((FunSQLParser.dropSchemaStatement_return)dropSchemaStatement2).stmt:null);
					                	stmt.setStmtString((dropSchemaStatement2!=null?input.toString(dropSchemaStatement2.start,dropSchemaStatement2.stop):null));
					                }
					}
					break;
				case 3 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:112:17: createConnectionStatement
					{
					pushFollow(FOLLOW_createConnectionStatement_in_statement1215);
					createConnectionStatement3=createConnectionStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (createConnectionStatement3!=null?((FunSQLParser.createConnectionStatement_return)createConnectionStatement3).stmt:null);
					                	stmt.setStmtString((createConnectionStatement3!=null?input.toString(createConnectionStatement3.start,createConnectionStatement3.stop):null));
					                }
					}
					break;
				case 4 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:118:17: dropConnectionStatement
					{
					pushFollow(FOLLOW_dropConnectionStatement_in_statement1270);
					dropConnectionStatement4=dropConnectionStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (dropConnectionStatement4!=null?((FunSQLParser.dropConnectionStatement_return)dropConnectionStatement4).stmt:null);
					                	stmt.setStmtString((dropConnectionStatement4!=null?input.toString(dropConnectionStatement4.start,dropConnectionStatement4.stop):null));
					                }
					}
					break;
				case 5 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:124:17: createTableStatement
					{
					pushFollow(FOLLOW_createTableStatement_in_statement1325);
					createTableStatement5=createTableStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (createTableStatement5!=null?((FunSQLParser.createTableStatement_return)createTableStatement5).stmt:null);
					                	stmt.setStmtString((createTableStatement5!=null?input.toString(createTableStatement5.start,createTableStatement5.stop):null));
					                }
					}
					break;
				case 6 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:130:17: dropTableStatement
					{
					pushFollow(FOLLOW_dropTableStatement_in_statement1380);
					dropTableStatement6=dropTableStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (dropTableStatement6!=null?((FunSQLParser.dropTableStatement_return)dropTableStatement6).stmt:null);
					                	stmt.setStmtString((dropTableStatement6!=null?input.toString(dropTableStatement6.start,dropTableStatement6.stop):null));
					                }
					}
					break;
				case 7 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:136:17: createFunctionStatement
					{
					pushFollow(FOLLOW_createFunctionStatement_in_statement1435);
					createFunctionStatement7=createFunctionStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (createFunctionStatement7!=null?((FunSQLParser.createFunctionStatement_return)createFunctionStatement7).stmt:null);
					                	stmt.setStmtString((createFunctionStatement7!=null?input.toString(createFunctionStatement7.start,createFunctionStatement7.stop):null));
					                }
					}
					break;
				case 8 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:142:17: dropFunctionStatement
					{
					pushFollow(FOLLOW_dropFunctionStatement_in_statement1490);
					dropFunctionStatement8=dropFunctionStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (dropFunctionStatement8!=null?((FunSQLParser.dropFunctionStatement_return)dropFunctionStatement8).stmt:null);
					                	stmt.setStmtString((dropFunctionStatement8!=null?input.toString(dropFunctionStatement8.start,dropFunctionStatement8.stop):null));
					                }
					}
					break;
				case 9 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:148:17: callFunctionStatement
					{
					pushFollow(FOLLOW_callFunctionStatement_in_statement1545);
					callFunctionStatement9=callFunctionStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (callFunctionStatement9!=null?((FunSQLParser.callFunctionStatement_return)callFunctionStatement9).stmt:null);
					                	stmt.setStmtString((callFunctionStatement9!=null?input.toString(callFunctionStatement9.start,callFunctionStatement9.stop):null));
					                }
					}
					break;
				case 10 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:154:17: selectStatement
					{
					pushFollow(FOLLOW_selectStatement_in_statement1600);
					selectStatement10=selectStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (selectStatement10!=null?((FunSQLParser.selectStatement_return)selectStatement10).stmt:null);
					                	stmt.setStmtString((selectStatement10!=null?input.toString(selectStatement10.start,selectStatement10.stop):null));
					                }
					}
					break;

			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:160:17: ( SEMI )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==SEMI) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:160:17: SEMI
					{
					match(input,SEMI,FOLLOW_SEMI_in_statement1654); if (state.failed) return stmt;
					}
					break;

			}

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 1, statement_StartIndex); }

		}
		return stmt;
	}
	// $ANTLR end "statement"


	public static class createSchemaStatement_return extends ParserRuleReturnScope {
		public CreateSchemaStmt stmt;
	};


	// $ANTLR start "createSchemaStatement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:164:1: createSchemaStatement returns [CreateSchemaStmt stmt] : ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) ;
	public final FunSQLParser.createSchemaStatement_return createSchemaStatement() throws RecognitionException {
		FunSQLParser.createSchemaStatement_return retval = new FunSQLParser.createSchemaStatement_return();
		retval.start = input.LT(1);
		int createSchemaStatement_StartIndex = input.index();

		TokenSchema tokenSchema11 =null;


		        	retval.stmt = new CreateSchemaStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:168:9: ( ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:169:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:169:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:170:17: KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema
			{
			match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1732); if (state.failed) return retval;
			match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1750); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenSchema_in_createSchemaStatement1768);
			tokenSchema11=tokenSchema();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setSchema(tokenSchema11);
			                }
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 2, createSchemaStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "createSchemaStatement"


	public static class dropSchemaStatement_return extends ParserRuleReturnScope {
		public DropSchemaStmt stmt;
	};


	// $ANTLR start "dropSchemaStatement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:178:1: dropSchemaStatement returns [DropSchemaStmt stmt] : ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) ;
	public final FunSQLParser.dropSchemaStatement_return dropSchemaStatement() throws RecognitionException {
		FunSQLParser.dropSchemaStatement_return retval = new FunSQLParser.dropSchemaStatement_return();
		retval.start = input.LT(1);
		int dropSchemaStatement_StartIndex = input.index();

		TokenSchema tokenSchema12 =null;


		        	retval.stmt = new DropSchemaStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:182:9: ( ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:183:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:183:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:184:17: KEYWORD_DROP KEYWORD_SCHEMA tokenSchema
			{
			match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1847); if (state.failed) return retval;
			match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1865); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenSchema_in_dropSchemaStatement1883);
			tokenSchema12=tokenSchema();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setSchema(tokenSchema12);
			                }
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 3, dropSchemaStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "dropSchemaStatement"


	public static class createConnectionStatement_return extends ParserRuleReturnScope {
		public CreateConnectionStmt stmt;
	};


	// $ANTLR start "createConnectionStatement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:192:1: createConnectionStatement returns [CreateConnectionStmt stmt] : ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) ;
	public final FunSQLParser.createConnectionStatement_return createConnectionStatement() throws RecognitionException {
		FunSQLParser.createConnectionStatement_return retval = new FunSQLParser.createConnectionStatement_return();
		retval.start = input.LT(1);
		int createConnectionStatement_StartIndex = input.index();

		TokenStringLiteral litURL =null;
		TokenStringLiteral litUser =null;
		TokenStringLiteral litPasswd =null;
		TokenStringLiteral litStore =null;
		TokenIdentifier tokenIdentifier13 =null;


		        	retval.stmt = new CreateConnectionStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:196:9: ( ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:197:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:197:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:198:17: KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral
			{
			match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1962); if (state.failed) return retval;
			match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1980); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenIdentifier_in_createConnectionStatement1998);
			tokenIdentifier13=tokenIdentifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setConnection(tokenIdentifier13);
			                }
			match(input,KEYWORD_URL,FOLLOW_KEYWORD_URL_in_createConnectionStatement2018); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2038);
			litURL=tokenStringLiteral();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setURL(litURL);
			                }
			match(input,KEYWORD_USER,FOLLOW_KEYWORD_USER_in_createConnectionStatement2058); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2078);
			litUser=tokenStringLiteral();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setUser(litUser);
			                }
			match(input,KEYWORD_PASSWD,FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement2098); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2118);
			litPasswd=tokenStringLiteral();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setPasswd(litPasswd);
			                }
			match(input,KEYWORD_STORE,FOLLOW_KEYWORD_STORE_in_createConnectionStatement2138); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2158);
			litStore=tokenStringLiteral();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setStore(litStore);
			                }
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 4, createConnectionStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "createConnectionStatement"


	public static class dropConnectionStatement_return extends ParserRuleReturnScope {
		public DropConnectionStmt stmt;
	};


	// $ANTLR start "dropConnectionStatement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:222:1: dropConnectionStatement returns [DropConnectionStmt stmt] : ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) ;
	public final FunSQLParser.dropConnectionStatement_return dropConnectionStatement() throws RecognitionException {
		FunSQLParser.dropConnectionStatement_return retval = new FunSQLParser.dropConnectionStatement_return();
		retval.start = input.LT(1);
		int dropConnectionStatement_StartIndex = input.index();

		TokenIdentifier tokenIdentifier14 =null;


		        	retval.stmt = new DropConnectionStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:226:9: ( ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:227:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:227:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:228:17: KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier
			{
			match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2245); if (state.failed) return retval;
			match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2263); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenIdentifier_in_dropConnectionStatement2281);
			tokenIdentifier14=tokenIdentifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setConnection(tokenIdentifier14);
			                }
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 5, dropConnectionStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "dropConnectionStatement"


	public static class createTableStatement_return extends ParserRuleReturnScope {
		public CreateTableStmt stmt;
	};


	// $ANTLR start "createTableStatement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:237:1: createTableStatement returns [CreateTableStmt stmt] : ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) ) ) ;
	public final FunSQLParser.createTableStatement_return createTableStatement() throws RecognitionException {
		FunSQLParser.createTableStatement_return retval = new FunSQLParser.createTableStatement_return();
		retval.start = input.LT(1);
		int createTableStatement_StartIndex = input.index();

		TokenTable table1 =null;
		String att1 =null;
		TokenDataType dataType1 =null;
		String att2 =null;
		TokenDataType dataType2 =null;
		TokenTable table2 =null;
		TokenIdentifier connection1 =null;
		TokenIdentifier connectionR1 =null;
		TokenIdentifier connectionR2 =null;
		String method =null;
		String pcolumn1 =null;
		String pcolumn2 =null;
		String p1 =null;
		TokenIdentifier c1 =null;
		String p2 =null;
		TokenIdentifier c2 =null;


		        	retval.stmt = new CreateTableStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:241:9: ( ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) ) ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:242:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) ) )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:242:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:243:17: KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) )
			{
			match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2361); if (state.failed) return retval;
			match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2379); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenTable_in_createTableStatement2399);
			table1=tokenTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setTable(table1);
			                	retval.stmt.setSourceTable(table1);
			                }
			match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2419); if (state.failed) return retval;
			pushFollow(FOLLOW_identifierText_in_createTableStatement2439);
			att1=identifierText();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.addAttribute(att1);
			                }
			pushFollow(FOLLOW_tokenDataType_in_createTableStatement2477);
			dataType1=tokenDataType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.addType(dataType1);
			                }
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:258:17: ( COMMA att2= identifierText dataType2= tokenDataType )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==COMMA) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:259:17: COMMA att2= identifierText dataType2= tokenDataType
					{
					match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2531); if (state.failed) return retval;
					pushFollow(FOLLOW_identifierText_in_createTableStatement2551);
					att2=identifierText();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addAttribute(att2);
					                }
					pushFollow(FOLLOW_tokenDataType_in_createTableStatement2589);
					dataType2=tokenDataType();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addType(dataType2);
					                }
					}
					break;

				default :
					break loop3;
				}
			}

			match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2644); if (state.failed) return retval;
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:270:17: ( KEYWORD_FROM table2= tokenTable )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==KEYWORD_FROM) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:271:17: KEYWORD_FROM table2= tokenTable
					{
					match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_createTableStatement2680); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenTable_in_createTableStatement2700);
					table2=tokenTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.setSourceTable(table2);
					                }
					}
					break;

			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:276:17: ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==KEYWORD_IN||LA10_0==KEYWORD_REPLICATED) ) {
				alt10=1;
			}
			else if ( (LA10_0==KEYWORD_PARTITIONED) ) {
				alt10=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:276:18: ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) )
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:276:18: ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) )
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( (LA6_0==KEYWORD_IN) ) {
						alt6=1;
					}
					else if ( (LA6_0==KEYWORD_REPLICATED) ) {
						alt6=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 6, 0, input);
						throw nvae;
					}

					switch (alt6) {
						case 1 :
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:276:19: ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
							{
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:276:19: ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:277:17: KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier
							{
							match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2759); if (state.failed) return retval;
							match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2761); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2781);
							connection1=tokenIdentifier();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addConnection(connection1);
							                }
							}

							}
							break;
						case 2 :
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:281:19: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* )
							{
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:281:19: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* )
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:282:17: KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )*
							{
							match(input,KEYWORD_REPLICATED,FOLLOW_KEYWORD_REPLICATED_in_createTableStatement2822); if (state.failed) return retval;
							match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2824); if (state.failed) return retval;
							match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2826); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2847);
							connectionR1=tokenIdentifier();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addConnection(connectionR1);
							                }
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:286:17: ( COMMA connectionR2= tokenIdentifier )*
							loop5:
							while (true) {
								int alt5=2;
								int LA5_0 = input.LA(1);
								if ( (LA5_0==COMMA) ) {
									alt5=1;
								}

								switch (alt5) {
								case 1 :
									// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:286:19: COMMA connectionR2= tokenIdentifier
									{
									match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2869); if (state.failed) return retval;
									pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2891);
									connectionR2=tokenIdentifier();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									                	retval.stmt.addConnection(connectionR2);
									                }
									}
									break;

								default :
									break loop5;
								}
							}

							}

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:293:17: ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN )
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:293:17: ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN )
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:294:17: KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN
					{
					match(input,KEYWORD_PARTITIONED,FOLLOW_KEYWORD_PARTITIONED_in_createTableStatement2985); if (state.failed) return retval;
					match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_createTableStatement2987); if (state.failed) return retval;
					pushFollow(FOLLOW_identifierText_in_createTableStatement3008);
					method=identifierText();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.setPartitionMethod(method);
					                }
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:299:17: ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )?
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0==LPAREN) ) {
						int LA8_1 = input.LA(2);
						if ( (LA8_1==IDENTIFIER) ) {
							int LA8_2 = input.LA(3);
							if ( (LA8_2==COMMA||LA8_2==RPAREN) ) {
								alt8=1;
							}
						}
						else if ( (LA8_1==QUOTE_DOUBLE) ) {
							int LA8_3 = input.LA(3);
							if ( (LA8_3==IDENTIFIER) ) {
								int LA8_6 = input.LA(4);
								if ( (LA8_6==QUOTE_DOUBLE) ) {
									int LA8_7 = input.LA(5);
									if ( (LA8_7==COMMA||LA8_7==RPAREN) ) {
										alt8=1;
									}
								}
							}
						}
					}
					switch (alt8) {
						case 1 :
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:300:17: LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN
							{
							match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement3062); if (state.failed) return retval;
							pushFollow(FOLLOW_identifierText_in_createTableStatement3082);
							pcolumn1=identifierText();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addPartitionColumn(pcolumn1);
							                }
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:305:17: ( COMMA pcolumn2= identifierText )*
							loop7:
							while (true) {
								int alt7=2;
								int LA7_0 = input.LA(1);
								if ( (LA7_0==COMMA) ) {
									alt7=1;
								}

								switch (alt7) {
								case 1 :
									// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:306:17: COMMA pcolumn2= identifierText
									{
									match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3136); if (state.failed) return retval;
									pushFollow(FOLLOW_identifierText_in_createTableStatement3156);
									pcolumn2=identifierText();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									                	retval.stmt.addPartitionColumn(pcolumn2);
									                }
									}
									break;

								default :
									break loop7;
								}
							}

							match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement3211); if (state.failed) return retval;
							}
							break;

					}

					match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement3248); if (state.failed) return retval;
					pushFollow(FOLLOW_identifierText_in_createTableStatement3268);
					p1=identifierText();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addPartition(p1);
					                }
					match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3304); if (state.failed) return retval;
					match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3306); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3326);
					c1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addPConnection(c1);
					                }
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:323:17: ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )*
					loop9:
					while (true) {
						int alt9=2;
						int LA9_0 = input.LA(1);
						if ( (LA9_0==COMMA) ) {
							alt9=1;
						}

						switch (alt9) {
						case 1 :
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:324:17: COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier
							{
							match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3364); if (state.failed) return retval;
							pushFollow(FOLLOW_identifierText_in_createTableStatement3384);
							p2=identifierText();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addPartition(p2);
							                }
							match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3420); if (state.failed) return retval;
							match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3422); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3442);
							c2=tokenIdentifier();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addPConnection(c2);
							                }
							}
							break;

						default :
							break loop9;
						}
					}

					match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement3481); if (state.failed) return retval;
					}

					}
					break;

			}

			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 6, createTableStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "createTableStatement"


	public static class dropTableStatement_return extends ParserRuleReturnScope {
		public DropTableStmt stmt;
	};


	// $ANTLR start "dropTableStatement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:341:1: dropTableStatement returns [DropTableStmt stmt] : ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) ;
	public final FunSQLParser.dropTableStatement_return dropTableStatement() throws RecognitionException {
		FunSQLParser.dropTableStatement_return retval = new FunSQLParser.dropTableStatement_return();
		retval.start = input.LT(1);
		int dropTableStatement_StartIndex = input.index();

		TokenTable table1 =null;


		        	retval.stmt = new DropTableStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:345:9: ( ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:346:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:346:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:347:17: KEYWORD_DROP KEYWORD_TABLE table1= tokenTable
			{
			match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement3595); if (state.failed) return retval;
			match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement3613); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenTable_in_dropTableStatement3633);
			table1=tokenTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setTable(table1);
			                }
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 7, dropTableStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "dropTableStatement"


	public static class createFunctionStatement_return extends ParserRuleReturnScope {
		public CreateFunctionStmt stmt;
	};


	// $ANTLR start "createFunctionStatement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:355:1: createFunctionStatement returns [CreateFunctionStmt stmt] : ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI ) ;
	public final FunSQLParser.createFunctionStatement_return createFunctionStatement() throws RecognitionException {
		FunSQLParser.createFunctionStatement_return retval = new FunSQLParser.createFunctionStatement_return();
		retval.start = input.LT(1);
		int createFunctionStatement_StartIndex = input.index();

		TokenFunction function1 =null;
		TokenVariable var1 =null;
		TokenVariable var2 =null;
		TokenAssignment ass1 =null;
		TokenFunctionCall call1 =null;


		        	retval.stmt = new CreateFunctionStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:359:9: ( ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:360:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:360:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:361:17: KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI
			{
			match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createFunctionStatement3699); if (state.failed) return retval;
			match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement3717); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenFunction_in_createFunctionStatement3737);
			function1=tokenFunction();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setFunction(function1);
			                }
			match(input,LPAREN,FOLLOW_LPAREN_in_createFunctionStatement3753); if (state.failed) return retval;
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:367:10: ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==KEYWORD_IN) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:368:17: KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA
					{
					match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createFunctionStatement3782); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement3802);
					var1=tokenVariable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					               		retval.stmt.addInParam(var1);
									}
					match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3809); if (state.failed) return retval;
					match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement3815); if (state.failed) return retval;
					}
					break;

				default :
					break loop11;
				}
			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:375:3: ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:376:17: KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
			{
			match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement3853); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement3873);
			var2=tokenVariable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			               		retval.stmt.addOutParam(var2);
							}
			match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3880); if (state.failed) return retval;
			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:382:3: ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==COMMA) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:383:4: COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
					{
					match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement3893); if (state.failed) return retval;
					match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement3898); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement3912);
					var2=tokenVariable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					           		retval.stmt.addOutParam(var2);
								}
					match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3918); if (state.failed) return retval;
					}
					break;

				default :
					break loop12;
				}
			}

			match(input,RPAREN,FOLLOW_RPAREN_in_createFunctionStatement3927); if (state.failed) return retval;
			match(input,KEYWORD_BEGIN,FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement3931); if (state.failed) return retval;
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:392:3: ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )*
			loop13:
			while (true) {
				int alt13=3;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==COLON||LA13_0==KEYWORD_VAR) ) {
					alt13=1;
				}
				else if ( (LA13_0==KEYWORD_CALL) ) {
					alt13=2;
				}

				switch (alt13) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:393:3: (ass1= tokenAssignment )
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:393:3: (ass1= tokenAssignment )
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:394:3: ass1= tokenAssignment
					{
					pushFollow(FOLLOW_tokenAssignment_in_createFunctionStatement3945);
					ass1=tokenAssignment();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addAssignment(ass1.getVar(), ass1.getSelStmt());
					                	retval.stmt.addAssignment(ass1);
					                }
					}

					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:400:17: (call1= tokenFunctionCall )
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:400:17: (call1= tokenFunctionCall )
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:401:17: call1= tokenFunctionCall
					{
					pushFollow(FOLLOW_tokenFunctionCall_in_createFunctionStatement4024);
					call1=tokenFunctionCall();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addFunctionCall(call1);                	
					                }
					}

					}
					break;

				default :
					break loop13;
				}
			}

			match(input,KEYWORD_END,FOLLOW_KEYWORD_END_in_createFunctionStatement4066); if (state.failed) return retval;
			match(input,SEMI,FOLLOW_SEMI_in_createFunctionStatement4073); if (state.failed) return retval;
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 8, createFunctionStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "createFunctionStatement"


	public static class dropFunctionStatement_return extends ParserRuleReturnScope {
		public DropFunctionStmt stmt;
	};


	// $ANTLR start "dropFunctionStatement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:411:1: dropFunctionStatement returns [DropFunctionStmt stmt] : ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) ;
	public final FunSQLParser.dropFunctionStatement_return dropFunctionStatement() throws RecognitionException {
		FunSQLParser.dropFunctionStatement_return retval = new FunSQLParser.dropFunctionStatement_return();
		retval.start = input.LT(1);
		int dropFunctionStatement_StartIndex = input.index();

		TokenFunction fun1 =null;


		        	retval.stmt = new DropFunctionStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:415:9: ( ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:416:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:416:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:417:17: KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction
			{
			match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropFunctionStatement4150); if (state.failed) return retval;
			match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement4168); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenFunction_in_dropFunctionStatement4188);
			fun1=tokenFunction();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setFunction(fun1);
			                }
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 9, dropFunctionStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "dropFunctionStatement"


	public static class callFunctionStatement_return extends ParserRuleReturnScope {
		public CallFunctionStmt stmt;
	};


	// $ANTLR start "callFunctionStatement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:425:1: callFunctionStatement returns [CallFunctionStmt stmt] : ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) ;
	public final FunSQLParser.callFunctionStatement_return callFunctionStatement() throws RecognitionException {
		FunSQLParser.callFunctionStatement_return retval = new FunSQLParser.callFunctionStatement_return();
		retval.start = input.LT(1);
		int callFunctionStatement_StartIndex = input.index();

		TokenFunction fun1 =null;


		        	retval.stmt = new CallFunctionStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:429:9: ( ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:430:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:430:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:431:17: KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction
			{
			match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_callFunctionStatement4254); if (state.failed) return retval;
			match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement4272); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenFunction_in_callFunctionStatement4292);
			fun1=tokenFunction();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setFunction(fun1);
			                }
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 10, callFunctionStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "callFunctionStatement"


	public static class selectStatement_return extends ParserRuleReturnScope {
		public SelectStmt stmt;
	};


	// $ANTLR start "selectStatement"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:439:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) ;
	public final FunSQLParser.selectStatement_return selectStatement() throws RecognitionException {
		FunSQLParser.selectStatement_return retval = new FunSQLParser.selectStatement_return();
		retval.start = input.LT(1);
		int selectStatement_StartIndex = input.index();

		AbstractExpression selExpr1 =null;
		TokenIdentifier selAlias1 =null;
		AbstractExpression selExpr2 =null;
		TokenIdentifier selAlias2 =null;
		TokenTable table1 =null;
		TokenIdentifier tableAlias1 =null;
		TokenTable table2 =null;
		TokenIdentifier tableAlias2 =null;
		AbstractPredicate predicate1 =null;
		AbstractExpression groupExpr1 =null;
		AbstractExpression groupExpr2 =null;
		AbstractPredicate havingPred =null;


		        	retval.stmt = new SelectStmt();
		        	int i=0;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:444:9: ( ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:445:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:445:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:447:17: KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )?
			{
			match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement4369); if (state.failed) return retval;
			pushFollow(FOLLOW_abstractExpression_in_selectStatement4389);
			selExpr1=abstractExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.addSelExpression(selExpr1);
			                	++i;
			                }
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:453:17: ( KEYWORD_AS selAlias1= tokenIdentifier )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==KEYWORD_AS) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:454:18: KEYWORD_AS selAlias1= tokenIdentifier
					{
					match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4444); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4465);
					selAlias1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                		retval.stmt.setSelAlias(i-1, selAlias1);
					                	}
					}
					break;

			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:460:17: ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )*
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==COMMA) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:461:17: COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )?
					{
					match(input,COMMA,FOLLOW_COMMA_in_selectStatement4539); if (state.failed) return retval;
					pushFollow(FOLLOW_abstractExpression_in_selectStatement4559);
					selExpr2=abstractExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addSelExpression(selExpr2);
					                	++i;
					                }
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:467:17: ( KEYWORD_AS selAlias2= tokenIdentifier )?
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( (LA15_0==KEYWORD_AS) ) {
						alt15=1;
					}
					switch (alt15) {
						case 1 :
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:468:18: KEYWORD_AS selAlias2= tokenIdentifier
							{
							match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4614); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4635);
							selAlias2=tokenIdentifier();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                		retval.stmt.setSelAlias(i-1, selAlias2);
							                	}
							}
							break;

					}

					}
					break;

				default :
					break loop16;
				}
			}

			match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement4744); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenTable_in_selectStatement4764);
			table1=tokenTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.addTable(table1);
			                	i=1;
			                }
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:483:17: ( KEYWORD_AS tableAlias1= tokenIdentifier )?
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==KEYWORD_AS) ) {
				alt17=1;
			}
			switch (alt17) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:484:18: KEYWORD_AS tableAlias1= tokenIdentifier
					{
					match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4820); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4841);
					tableAlias1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                		retval.stmt.setTableAlias(i-1, tableAlias1);
					                	}
					}
					break;

			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:490:17: ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )*
			loop19:
			while (true) {
				int alt19=2;
				int LA19_0 = input.LA(1);
				if ( (LA19_0==COMMA) ) {
					alt19=1;
				}

				switch (alt19) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:491:17: COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )?
					{
					match(input,COMMA,FOLLOW_COMMA_in_selectStatement4915); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenTable_in_selectStatement4935);
					table2=tokenTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addTable(table2);
					                	++i;
					                }
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:497:17: ( KEYWORD_AS tableAlias2= tokenIdentifier )?
					int alt18=2;
					int LA18_0 = input.LA(1);
					if ( (LA18_0==KEYWORD_AS) ) {
						alt18=1;
					}
					switch (alt18) {
						case 1 :
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:498:18: KEYWORD_AS tableAlias2= tokenIdentifier
							{
							match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4990); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_selectStatement5011);
							tableAlias2=tokenIdentifier();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                		retval.stmt.setTableAlias(i-1, tableAlias2);
							                	}
							}
							break;

					}

					}
					break;

				default :
					break loop19;
				}
			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:506:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==KEYWORD_WHERE) ) {
				alt20=1;
			}
			switch (alt20) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:507:17: KEYWORD_WHERE predicate1= abstractPredicate
					{
					match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement5121); if (state.failed) return retval;
					pushFollow(FOLLOW_abstractPredicate_in_selectStatement5141);
					predicate1=abstractPredicate();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.setWherePredicate(predicate1);
					                }
					}
					break;

			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:515:17: ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )?
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==KEYWORD_GROUP) ) {
				alt22=1;
			}
			switch (alt22) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:516:17: KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )*
					{
					match(input,KEYWORD_GROUP,FOLLOW_KEYWORD_GROUP_in_selectStatement5248); if (state.failed) return retval;
					match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_selectStatement5250); if (state.failed) return retval;
					pushFollow(FOLLOW_abstractExpression_in_selectStatement5270);
					groupExpr1=abstractExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addGroupExpression(groupExpr1);
					                	++i;
					                }
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:522:17: ( COMMA groupExpr2= abstractExpression )*
					loop21:
					while (true) {
						int alt21=2;
						int LA21_0 = input.LA(1);
						if ( (LA21_0==COMMA) ) {
							alt21=1;
						}

						switch (alt21) {
						case 1 :
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:523:17: COMMA groupExpr2= abstractExpression
							{
							match(input,COMMA,FOLLOW_COMMA_in_selectStatement5324); if (state.failed) return retval;
							pushFollow(FOLLOW_abstractExpression_in_selectStatement5344);
							groupExpr2=abstractExpression();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addGroupExpression(groupExpr2);
							                	++i;
							                }
							}
							break;

						default :
							break loop21;
						}
					}

					}
					break;

			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:533:17: ( KEYWORD_HAVING havingPred= abstractPredicate )?
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==KEYWORD_HAVING) ) {
				alt23=1;
			}
			switch (alt23) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:534:17: KEYWORD_HAVING havingPred= abstractPredicate
					{
					match(input,KEYWORD_HAVING,FOLLOW_KEYWORD_HAVING_in_selectStatement5470); if (state.failed) return retval;
					pushFollow(FOLLOW_abstractPredicate_in_selectStatement5490);
					havingPred=abstractPredicate();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.setHavingPredicate(havingPred);
					                }
					}
					break;

			}

			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 11, selectStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "selectStatement"



	// $ANTLR start "abstractPredicate"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:544:1: abstractPredicate returns [AbstractPredicate predicate] : predicate1= complexPredicateOr ;
	public final AbstractPredicate abstractPredicate() throws RecognitionException {
		AbstractPredicate predicate = null;

		int abstractPredicate_StartIndex = input.index();

		ComplexPredicate predicate1 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return predicate; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:545:2: (predicate1= complexPredicateOr )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:546:3: predicate1= complexPredicateOr
			{
			pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate5552);
			predicate1=complexPredicateOr();
			state._fsp--;
			if (state.failed) return predicate;
			if ( state.backtracking==0 ) {
						predicate = predicate1;
					}
			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 12, abstractPredicate_StartIndex); }

		}
		return predicate;
	}
	// $ANTLR end "abstractPredicate"



	// $ANTLR start "complexPredicateOr"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:551:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
	public final ComplexPredicate complexPredicateOr() throws RecognitionException {
		ComplexPredicate predicateOr = null;

		int complexPredicateOr_StartIndex = input.index();

		ComplexPredicate predicate1 =null;
		ComplexPredicate predicate2 =null;


		        	predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return predicateOr; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:555:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:556:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:556:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:557:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
			{
			pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr5590);
			predicate1=complexPredicateAnd();
			state._fsp--;
			if (state.failed) return predicateOr;
			if ( state.backtracking==0 ) {
						predicateOr.setPredicate1(predicate1);
					}
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:560:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
			loop24:
			while (true) {
				int alt24=2;
				int LA24_0 = input.LA(1);
				if ( (LA24_0==KEYWORD_OR) ) {
					alt24=1;
				}

				switch (alt24) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:561:4: KEYWORD_OR predicate2= complexPredicateAnd
					{
					match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr5601); if (state.failed) return predicateOr;
					if ( state.backtracking==0 ) {
									predicateOr.addOr();
								}
					pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr5609);
					predicate2=complexPredicateAnd();
					state._fsp--;
					if (state.failed) return predicateOr;
					if ( state.backtracking==0 ) {
									predicateOr.addPredicate2(predicate2);
								}
					}
					break;

				default :
					break loop24;
				}
			}

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 13, complexPredicateOr_StartIndex); }

		}
		return predicateOr;
	}
	// $ANTLR end "complexPredicateOr"



	// $ANTLR start "complexPredicateAnd"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:571:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
	public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
		ComplexPredicate predicateAnd = null;

		int complexPredicateAnd_StartIndex = input.index();

		ComplexPredicate predicate1 =null;
		ComplexPredicate predicate2 =null;


		        	predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return predicateAnd; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:575:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:576:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:576:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:577:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
			{
			pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd5648);
			predicate1=complexPredicateNot();
			state._fsp--;
			if (state.failed) return predicateAnd;
			if ( state.backtracking==0 ) {
						predicateAnd.setPredicate1(predicate1);
					}
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:580:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
			loop25:
			while (true) {
				int alt25=2;
				int LA25_0 = input.LA(1);
				if ( (LA25_0==KEYWORD_AND) ) {
					alt25=1;
				}

				switch (alt25) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:581:4: KEYWORD_AND predicate2= complexPredicateNot
					{
					match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd5659); if (state.failed) return predicateAnd;
					if ( state.backtracking==0 ) {
									predicateAnd.addAnd();
								}
					pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd5667);
					predicate2=complexPredicateNot();
					state._fsp--;
					if (state.failed) return predicateAnd;
					if ( state.backtracking==0 ) {
									predicateAnd.addPredicate2(predicate2);
								}
					}
					break;

				default :
					break loop25;
				}
			}

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 14, complexPredicateAnd_StartIndex); }

		}
		return predicateAnd;
	}
	// $ANTLR end "complexPredicateAnd"



	// $ANTLR start "complexPredicateNot"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:591:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
	public final ComplexPredicate complexPredicateNot() throws RecognitionException {
		ComplexPredicate predicateNot = null;

		int complexPredicateNot_StartIndex = input.index();

		AbstractPredicate predicate1 =null;


		        	predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return predicateNot; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:595:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:596:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:596:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:597:3: ( KEYWORD_NOT )? predicate1= complexPredicate
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:597:3: ( KEYWORD_NOT )?
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==KEYWORD_NOT) ) {
				alt26=1;
			}
			switch (alt26) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:598:3: KEYWORD_NOT
					{
					match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot5708); if (state.failed) return predicateNot;
					if ( state.backtracking==0 ) {
								predicateNot.negate();
							}
					}
					break;

			}

			pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot5721);
			predicate1=complexPredicate();
			state._fsp--;
			if (state.failed) return predicateNot;
			if ( state.backtracking==0 ) {
						predicateNot.setPredicate1(predicate1);
					}
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 15, complexPredicateNot_StartIndex); }

		}
		return predicateNot;
	}
	// $ANTLR end "complexPredicateNot"



	// $ANTLR start "complexPredicate"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:608:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
	public final AbstractPredicate complexPredicate() throws RecognitionException {
		AbstractPredicate predicate = null;

		int complexPredicate_StartIndex = input.index();

		AbstractPredicate predicate1 =null;
		SimplePredicate predicate2 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return predicate; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:609:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:610:2: (predicate1= parenPredicate |predicate2= simplePredicate )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:610:2: (predicate1= parenPredicate |predicate2= simplePredicate )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==LPAREN) ) {
				int LA27_1 = input.LA(2);
				if ( (synpred36_FunSQL()) ) {
					alt27=1;
				}
				else if ( (true) ) {
					alt27=2;
				}

			}
			else if ( (LA27_0==FUNCTION_AGGREGATION||LA27_0==IDENTIFIER||(LA27_0 >= LITERAL_DECIMAL && LA27_0 <= LITERAL_STRING)||LA27_0==MINUS||LA27_0==PLUS||LA27_0==QUOTE_DOUBLE||LA27_0==TYPE_DATE) ) {
				alt27=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return predicate;}
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:611:3: predicate1= parenPredicate
					{
					pushFollow(FOLLOW_parenPredicate_in_complexPredicate5748);
					predicate1=parenPredicate();
					state._fsp--;
					if (state.failed) return predicate;
					if ( state.backtracking==0 ) {
								predicate = predicate1;
							}
					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:616:3: predicate2= simplePredicate
					{
					pushFollow(FOLLOW_simplePredicate_in_complexPredicate5763);
					predicate2=simplePredicate();
					state._fsp--;
					if (state.failed) return predicate;
					if ( state.backtracking==0 ) {
								predicate = predicate2;
							}
					}
					break;

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 16, complexPredicate_StartIndex); }

		}
		return predicate;
	}
	// $ANTLR end "complexPredicate"



	// $ANTLR start "parenPredicate"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:623:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
	public final AbstractPredicate parenPredicate() throws RecognitionException {
		AbstractPredicate predicate = null;

		int parenPredicate_StartIndex = input.index();

		AbstractPredicate predicate1 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return predicate; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:624:2: ( LPAREN predicate1= abstractPredicate RPAREN )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:625:3: LPAREN predicate1= abstractPredicate RPAREN
			{
			match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate5788); if (state.failed) return predicate;
			pushFollow(FOLLOW_abstractPredicate_in_parenPredicate5795);
			predicate1=abstractPredicate();
			state._fsp--;
			if (state.failed) return predicate;
			if ( state.backtracking==0 ) {
						predicate = predicate1;
					}
			match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate5801); if (state.failed) return predicate;
			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 17, parenPredicate_StartIndex); }

		}
		return predicate;
	}
	// $ANTLR end "parenPredicate"



	// $ANTLR start "simplePredicate"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:632:1: simplePredicate returns [SimplePredicate predicate] : (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) ;
	public final SimplePredicate simplePredicate() throws RecognitionException {
		SimplePredicate predicate = null;

		int simplePredicate_StartIndex = input.index();

		AbstractExpression expr1 =null;
		ParserRuleReturnScope comp =null;
		AbstractExpression expr2 =null;


		        	predicate = new SimplePredicate();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return predicate; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:636:9: ( (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:637:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:637:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:638:3: expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression
			{
			pushFollow(FOLLOW_abstractExpression_in_simplePredicate5843);
			expr1=abstractExpression();
			state._fsp--;
			if (state.failed) return predicate;
			if ( state.backtracking==0 ) {
			                	predicate.setExpr1(expr1);
			                }
			pushFollow(FOLLOW_tokenCompOperator_in_simplePredicate5898);
			comp=tokenCompOperator();
			state._fsp--;
			if (state.failed) return predicate;
			if ( state.backtracking==0 ) {
			                	predicate.setComp(EnumCompOperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
			                }
			pushFollow(FOLLOW_abstractExpression_in_simplePredicate5936);
			expr2=abstractExpression();
			state._fsp--;
			if (state.failed) return predicate;
			if ( state.backtracking==0 ) {
			                	predicate.setExpr2(expr2);
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 18, simplePredicate_StartIndex); }

		}
		return predicate;
	}
	// $ANTLR end "simplePredicate"



	// $ANTLR start "abstractExpression"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:655:1: abstractExpression returns [AbstractExpression expression] : expression1= complexExpressionAdd ;
	public final AbstractExpression abstractExpression() throws RecognitionException {
		AbstractExpression expression = null;

		int abstractExpression_StartIndex = input.index();

		ComplexExpression expression1 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return expression; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:656:2: (expression1= complexExpressionAdd )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:657:2: expression1= complexExpressionAdd
			{
			pushFollow(FOLLOW_complexExpressionAdd_in_abstractExpression5990);
			expression1=complexExpressionAdd();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
					expression = expression1;
				}
			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 19, abstractExpression_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "abstractExpression"



	// $ANTLR start "complexExpressionAdd"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:662:1: complexExpressionAdd returns [ComplexExpression expression] : (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) ;
	public final ComplexExpression complexExpressionAdd() throws RecognitionException {
		ComplexExpression expression = null;

		int complexExpressionAdd_StartIndex = input.index();

		ComplexExpression expression1 =null;
		ParserRuleReturnScope op1 =null;
		ComplexExpression expression2 =null;


		        	expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expression; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:666:2: ( (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:667:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:667:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:668:3: expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )*
			{
			pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd6020);
			expression1=complexExpressionMult();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression.setExpr1(expression1);
					}
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:671:3: (op1= tokenAddOperator expression2= complexExpressionMult )*
			loop28:
			while (true) {
				int alt28=2;
				int LA28_0 = input.LA(1);
				if ( (LA28_0==MINUS||LA28_0==PLUS) ) {
					alt28=1;
				}

				switch (alt28) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:672:4: op1= tokenAddOperator expression2= complexExpressionMult
					{
					pushFollow(FOLLOW_tokenAddOperator_in_complexExpressionAdd6033);
					op1=tokenAddOperator();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
					                		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
					                	}
					pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd6041);
					expression2=complexExpressionMult();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
									expression.addExpr2(expression2);
								}
					}
					break;

				default :
					break loop28;
				}
			}

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 20, complexExpressionAdd_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "complexExpressionAdd"



	// $ANTLR start "complexExpressionMult"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:682:1: complexExpressionMult returns [ComplexExpression expression] : (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) ;
	public final ComplexExpression complexExpressionMult() throws RecognitionException {
		ComplexExpression expression = null;

		int complexExpressionMult_StartIndex = input.index();

		ComplexExpression expression1 =null;
		ParserRuleReturnScope op1 =null;
		ComplexExpression expression2 =null;


		        	expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expression; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:686:2: ( (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:687:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:687:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:688:3: expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )*
			{
			pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult6081);
			expression1=complexExpressionSigned();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression.setExpr1(expression1);
					}
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:691:3: (op1= tokenMultOperator expression2= complexExpressionSigned )*
			loop29:
			while (true) {
				int alt29=2;
				int LA29_0 = input.LA(1);
				if ( (LA29_0==DIV||LA29_0==MULT) ) {
					alt29=1;
				}

				switch (alt29) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:692:4: op1= tokenMultOperator expression2= complexExpressionSigned
					{
					pushFollow(FOLLOW_tokenMultOperator_in_complexExpressionMult6094);
					op1=tokenMultOperator();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
					                		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
					                	}
					pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult6102);
					expression2=complexExpressionSigned();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
									expression.addExpr2(expression2);
								}
					}
					break;

				default :
					break loop29;
				}
			}

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 21, complexExpressionMult_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "complexExpressionMult"



	// $ANTLR start "complexExpressionSigned"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:703:1: complexExpressionSigned returns [ComplexExpression expression] : ( ( MINUS | PLUS )? expression1= complexExpression ) ;
	public final ComplexExpression complexExpressionSigned() throws RecognitionException {
		ComplexExpression expression = null;

		int complexExpressionSigned_StartIndex = input.index();

		AbstractExpression expression1 =null;


		        	expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expression; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:707:2: ( ( ( MINUS | PLUS )? expression1= complexExpression ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:708:2: ( ( MINUS | PLUS )? expression1= complexExpression )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:708:2: ( ( MINUS | PLUS )? expression1= complexExpression )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:709:3: ( MINUS | PLUS )? expression1= complexExpression
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:709:3: ( MINUS | PLUS )?
			int alt30=3;
			int LA30_0 = input.LA(1);
			if ( (LA30_0==MINUS) ) {
				alt30=1;
			}
			else if ( (LA30_0==PLUS) ) {
				alt30=2;
			}
			switch (alt30) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:710:3: MINUS
					{
					match(input,MINUS,FOLLOW_MINUS_in_complexExpressionSigned6144); if (state.failed) return expression;
					if ( state.backtracking==0 ) {
								expression.negate();
							}
					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:714:3: PLUS
					{
					match(input,PLUS,FOLLOW_PLUS_in_complexExpressionSigned6153); if (state.failed) return expression;
					}
					break;

			}

			pushFollow(FOLLOW_complexExpression_in_complexExpressionSigned6165);
			expression1=complexExpression();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression.setExpr1(expression1);
					}
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 22, complexExpressionSigned_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "complexExpressionSigned"



	// $ANTLR start "complexExpression"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:722:1: complexExpression returns [AbstractExpression expression] : (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) ;
	public final AbstractExpression complexExpression() throws RecognitionException {
		AbstractExpression expression = null;

		int complexExpression_StartIndex = input.index();

		AbstractExpression expression1 =null;
		AggregationExpression expression2 =null;
		SimpleExpression expression3 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expression; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:723:2: ( (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:724:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:724:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
			int alt31=3;
			switch ( input.LA(1) ) {
			case LPAREN:
				{
				alt31=1;
				}
				break;
			case FUNCTION_AGGREGATION:
				{
				alt31=2;
				}
				break;
			case IDENTIFIER:
			case LITERAL_DECIMAL:
			case LITERAL_INTEGER:
			case LITERAL_STRING:
			case QUOTE_DOUBLE:
			case TYPE_DATE:
				{
				alt31=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return expression;}
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}
			switch (alt31) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:725:3: expression1= parenExpression
					{
					pushFollow(FOLLOW_parenExpression_in_complexExpression6200);
					expression1=parenExpression();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
								expression = expression1;
							}
					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:730:3: expression2= aggregationExpression
					{
					pushFollow(FOLLOW_aggregationExpression_in_complexExpression6215);
					expression2=aggregationExpression();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
								expression = expression2;
							}
					}
					break;
				case 3 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:735:3: expression3= simpleExpression
					{
					pushFollow(FOLLOW_simpleExpression_in_complexExpression6230);
					expression3=simpleExpression();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
								expression = expression3;
							}
					}
					break;

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 23, complexExpression_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "complexExpression"



	// $ANTLR start "parenExpression"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:742:1: parenExpression returns [AbstractExpression expression] : LPAREN expression1= abstractExpression RPAREN ;
	public final AbstractExpression parenExpression() throws RecognitionException {
		AbstractExpression expression = null;

		int parenExpression_StartIndex = input.index();

		AbstractExpression expression1 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return expression; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:743:2: ( LPAREN expression1= abstractExpression RPAREN )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:744:3: LPAREN expression1= abstractExpression RPAREN
			{
			match(input,LPAREN,FOLLOW_LPAREN_in_parenExpression6255); if (state.failed) return expression;
			pushFollow(FOLLOW_abstractExpression_in_parenExpression6262);
			expression1=abstractExpression();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression = expression1;
					}
			match(input,RPAREN,FOLLOW_RPAREN_in_parenExpression6268); if (state.failed) return expression;
			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 24, parenExpression_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "parenExpression"



	// $ANTLR start "aggregationExpression"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:752:1: aggregationExpression returns [AggregationExpression expression] : (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) ;
	public final AggregationExpression aggregationExpression() throws RecognitionException {
		AggregationExpression expression = null;

		int aggregationExpression_StartIndex = input.index();

		Token agg1=null;
		AbstractExpression expr1 =null;


		        	expression = new AggregationExpression();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return expression; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:756:9: ( (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:757:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:757:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:758:3: agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN
			{
			agg1=(Token)match(input,FUNCTION_AGGREGATION,FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression6320); if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression.setAggregation((agg1!=null?agg1.getText():null).toUpperCase());
					}
			match(input,LPAREN,FOLLOW_LPAREN_in_aggregationExpression6328); if (state.failed) return expression;
			pushFollow(FOLLOW_abstractExpression_in_aggregationExpression6335);
			expr1=abstractExpression();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression.setExpression(expr1);
					}
			match(input,RPAREN,FOLLOW_RPAREN_in_aggregationExpression6341); if (state.failed) return expression;
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 25, aggregationExpression_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "aggregationExpression"



	// $ANTLR start "simpleExpression"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:770:1: simpleExpression returns [SimpleExpression expression] : ( (att1= tokenAttribute |lit1= tokenLiteral ) ) ;
	public final SimpleExpression simpleExpression() throws RecognitionException {
		SimpleExpression expression = null;

		int simpleExpression_StartIndex = input.index();

		TokenAttribute att1 =null;
		TokenLiteral lit1 =null;


		        	expression = new SimpleExpression();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return expression; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:774:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:775:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:775:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:776:3: (att1= tokenAttribute |lit1= tokenLiteral )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:776:3: (att1= tokenAttribute |lit1= tokenLiteral )
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==IDENTIFIER||LA32_0==QUOTE_DOUBLE) ) {
				alt32=1;
			}
			else if ( ((LA32_0 >= LITERAL_DECIMAL && LA32_0 <= LITERAL_STRING)||LA32_0==TYPE_DATE) ) {
				alt32=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return expression;}
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}

			switch (alt32) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:777:3: att1= tokenAttribute
					{
					pushFollow(FOLLOW_tokenAttribute_in_simpleExpression6416);
					att1=tokenAttribute();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
					                	expression.setOper(att1);
					                }
					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:782:17: lit1= tokenLiteral
					{
					pushFollow(FOLLOW_tokenLiteral_in_simpleExpression6472);
					lit1=tokenLiteral();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
					                	expression.setOper(lit1);
					                }
					}
					break;

			}

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 26, simpleExpression_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "simpleExpression"



	// $ANTLR start "tokenAttribute"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:790:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
	public final TokenAttribute tokenAttribute() throws RecognitionException {
		TokenAttribute attribute = null;

		int tokenAttribute_StartIndex = input.index();

		TokenIdentifier table1 =null;
		TokenIdentifier id1 =null;


		        	attribute = new TokenAttribute();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return attribute; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:794:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:795:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:795:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:796:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:796:17: (table1= tokenIdentifier DOT )?
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==IDENTIFIER) ) {
				int LA33_1 = input.LA(2);
				if ( (LA33_1==DOT) ) {
					alt33=1;
				}
			}
			else if ( (LA33_0==QUOTE_DOUBLE) ) {
				int LA33_2 = input.LA(2);
				if ( (LA33_2==IDENTIFIER) ) {
					int LA33_5 = input.LA(3);
					if ( (LA33_5==QUOTE_DOUBLE) ) {
						int LA33_6 = input.LA(4);
						if ( (LA33_6==DOT) ) {
							alt33=1;
						}
					}
				}
			}
			switch (alt33) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:797:17: table1= tokenIdentifier DOT
					{
					pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute6600);
					table1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return attribute;
					if ( state.backtracking==0 ) {
					                	TokenTable table = new TokenTable();
					                	table.setName(table1);
					                	attribute.setTable(table);
					                }
					match(input,DOT,FOLLOW_DOT_in_tokenAttribute6620); if (state.failed) return attribute;
					}
					break;

			}

			pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute6659);
			id1=tokenIdentifier();
			state._fsp--;
			if (state.failed) return attribute;
			if ( state.backtracking==0 ) {
			                	attribute.setName(id1);
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 27, tokenAttribute_StartIndex); }

		}
		return attribute;
	}
	// $ANTLR end "tokenAttribute"



	// $ANTLR start "tokenTable"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:810:1: tokenTable returns [TokenTable table] : ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) );
	public final TokenTable tokenTable() throws RecognitionException {
		TokenTable table = null;

		int tokenTable_StartIndex = input.index();

		TokenIdentifier schema1 =null;
		TokenIdentifier id1 =null;


		        	table = new TokenTable();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return table; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:814:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) )
			int alt36=2;
			switch ( input.LA(1) ) {
			case IDENTIFIER:
				{
				int LA36_1 = input.LA(2);
				if ( (synpred46_FunSQL()) ) {
					alt36=1;
				}
				else if ( (true) ) {
					alt36=2;
				}

				}
				break;
			case QUOTE_DOUBLE:
				{
				int LA36_2 = input.LA(2);
				if ( (LA36_2==IDENTIFIER) ) {
					int LA36_5 = input.LA(3);
					if ( (LA36_5==QUOTE_DOUBLE) ) {
						int LA36_6 = input.LA(4);
						if ( (synpred46_FunSQL()) ) {
							alt36=1;
						}
						else if ( (true) ) {
							alt36=2;
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return table;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 36, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return table;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 36, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case COLON:
				{
				alt36=2;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return table;}
				NoViableAltException nvae =
					new NoViableAltException("", 36, 0, input);
				throw nvae;
			}
			switch (alt36) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:815:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:815:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:816:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:816:17: (schema1= tokenIdentifier DOT )?
					int alt34=2;
					int LA34_0 = input.LA(1);
					if ( (LA34_0==IDENTIFIER) ) {
						int LA34_1 = input.LA(2);
						if ( (LA34_1==DOT) ) {
							alt34=1;
						}
					}
					else if ( (LA34_0==QUOTE_DOUBLE) ) {
						int LA34_2 = input.LA(2);
						if ( (LA34_2==IDENTIFIER) ) {
							int LA34_5 = input.LA(3);
							if ( (LA34_5==QUOTE_DOUBLE) ) {
								int LA34_6 = input.LA(4);
								if ( (LA34_6==DOT) ) {
									alt34=1;
								}
							}
						}
					}
					switch (alt34) {
						case 1 :
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:817:17: schema1= tokenIdentifier DOT
							{
							pushFollow(FOLLOW_tokenIdentifier_in_tokenTable6782);
							schema1=tokenIdentifier();
							state._fsp--;
							if (state.failed) return table;
							if ( state.backtracking==0 ) {
							                        TokenSchema schema = new TokenSchema();
							                	table.setSchema(schema);
							                	table.setVariable(false);
							                }
							match(input,DOT,FOLLOW_DOT_in_tokenTable6802); if (state.failed) return table;
							}
							break;

					}

					pushFollow(FOLLOW_tokenIdentifier_in_tokenTable6859);
					id1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return table;
					if ( state.backtracking==0 ) {
					                	table.setName(id1);
					                }
					}

					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:827:12: ( ( COLON )? id1= tokenIdentifier )
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:827:12: ( ( COLON )? id1= tokenIdentifier )
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:828:17: ( COLON )? id1= tokenIdentifier
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:828:17: ( COLON )?
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==COLON) ) {
						alt35=1;
					}
					switch (alt35) {
						case 1 :
							// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:829:17: COLON
							{
							match(input,COLON,FOLLOW_COLON_in_tokenTable6926); if (state.failed) return table;
							}
							break;

					}

					pushFollow(FOLLOW_tokenIdentifier_in_tokenTable6968);
					id1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return table;
					if ( state.backtracking==0 ) {
					                	table.setName(id1);                	
					                	table.setVariable(true);
					                }
					}

					}
					break;

			}
		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 28, tokenTable_StartIndex); }

		}
		return table;
	}
	// $ANTLR end "tokenTable"



	// $ANTLR start "tokenSchema"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:838:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
	public final TokenSchema tokenSchema() throws RecognitionException {
		TokenSchema schema = null;

		int tokenSchema_StartIndex = input.index();

		TokenIdentifier tokenIdentifier15 =null;


		        	schema = new TokenSchema();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return schema; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:842:9: ( ( tokenIdentifier ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:843:9: ( tokenIdentifier )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:843:9: ( tokenIdentifier )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:844:17: tokenIdentifier
			{
			pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema7049);
			tokenIdentifier15=tokenIdentifier();
			state._fsp--;
			if (state.failed) return schema;
			if ( state.backtracking==0 ) {
			                	schema.setName(tokenIdentifier15);
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 29, tokenSchema_StartIndex); }

		}
		return schema;
	}
	// $ANTLR end "tokenSchema"



	// $ANTLR start "tokenFunction"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:850:1: tokenFunction returns [TokenFunction function] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
	public final TokenFunction tokenFunction() throws RecognitionException {
		TokenFunction function = null;

		int tokenFunction_StartIndex = input.index();

		TokenIdentifier schema1 =null;
		TokenIdentifier id1 =null;


		        	function = new TokenFunction();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return function; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:854:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:855:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:855:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:856:33: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:856:33: (schema1= tokenIdentifier DOT )?
			int alt37=2;
			int LA37_0 = input.LA(1);
			if ( (LA37_0==IDENTIFIER) ) {
				int LA37_1 = input.LA(2);
				if ( (LA37_1==DOT) ) {
					alt37=1;
				}
			}
			else if ( (LA37_0==QUOTE_DOUBLE) ) {
				int LA37_2 = input.LA(2);
				if ( (LA37_2==IDENTIFIER) ) {
					int LA37_5 = input.LA(3);
					if ( (LA37_5==QUOTE_DOUBLE) ) {
						int LA37_6 = input.LA(4);
						if ( (LA37_6==DOT) ) {
							alt37=1;
						}
					}
				}
			}
			switch (alt37) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:857:17: schema1= tokenIdentifier DOT
					{
					pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction7165);
					schema1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return function;
					if ( state.backtracking==0 ) {
					                        TokenSchema schema = new TokenSchema();
					                	function.setSchema(schema);
					                }
					match(input,DOT,FOLLOW_DOT_in_tokenFunction7185); if (state.failed) return function;
					}
					break;

			}

			pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction7224);
			id1=tokenIdentifier();
			state._fsp--;
			if (state.failed) return function;
			if ( state.backtracking==0 ) {
			                	function.setName(id1);
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 30, tokenFunction_StartIndex); }

		}
		return function;
	}
	// $ANTLR end "tokenFunction"



	// $ANTLR start "tokenVariable"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:869:1: tokenVariable returns [TokenVariable variable] : ( variableText ) ;
	public final TokenVariable tokenVariable() throws RecognitionException {
		TokenVariable variable = null;

		int tokenVariable_StartIndex = input.index();

		String variableText16 =null;


		        	variable = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return variable; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:873:9: ( ( variableText ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:874:9: ( variableText )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:874:9: ( variableText )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:875:17: variableText
			{
			pushFollow(FOLLOW_variableText_in_tokenVariable7304);
			variableText16=variableText();
			state._fsp--;
			if (state.failed) return variable;
			if ( state.backtracking==0 ) {
			                variable = new TokenVariable(variableText16);	
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 31, tokenVariable_StartIndex); }

		}
		return variable;
	}
	// $ANTLR end "tokenVariable"



	// $ANTLR start "tokenAssignment"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:881:1: tokenAssignment returns [TokenAssignment ass] : ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI ;
	public final TokenAssignment tokenAssignment() throws RecognitionException {
		TokenAssignment ass = null;

		int tokenAssignment_StartIndex = input.index();

		TokenVariable var1 =null;
		ParserRuleReturnScope selstmt1 =null;
		TokenVariable var2 =null;
		ParserRuleReturnScope selstmt2 =null;
		TokenVariable var3 =null;
		TokenVariable var4 =null;


			 	ass =new TokenAssignment();
			 
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return ass; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:885:3: ( ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:886:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:886:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable )
			int alt38=3;
			int LA38_0 = input.LA(1);
			if ( (LA38_0==COLON) ) {
				alt38=1;
			}
			else if ( (LA38_0==KEYWORD_VAR) ) {
				int LA38_2 = input.LA(2);
				if ( (LA38_2==IDENTIFIER) ) {
					int LA38_3 = input.LA(3);
					if ( (LA38_3==EQUAL1) ) {
						int LA38_4 = input.LA(4);
						if ( (LA38_4==COLON) ) {
							alt38=3;
						}
						else if ( (LA38_4==KEYWORD_SELECT) ) {
							alt38=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return ass;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 38, 4, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return ass;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 38, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return ass;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 38, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return ass;}
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}

			switch (alt38) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:887:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:887:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:888:4: COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement
					{
					match(input,COLON,FOLLOW_COLON_in_tokenAssignment7371); if (state.failed) return ass;
					pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7383);
					var1=tokenVariable();
					state._fsp--;
					if (state.failed) return ass;
					if ( state.backtracking==0 ) {
							 ass.setReference(true);
							 ass.setVar(var1);
							 }
					match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7389); if (state.failed) return ass;
					pushFollow(FOLLOW_selectStatement_in_tokenAssignment7396);
					selstmt1=selectStatement();
					state._fsp--;
					if (state.failed) return ass;
					if ( state.backtracking==0 ) {
							 ass.setSelStmt((selstmt1!=null?((FunSQLParser.selectStatement_return)selstmt1).stmt:null));
							 }
					}

					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:899:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:899:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:900:4: KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement
					{
					match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment7417); if (state.failed) return ass;
					pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7426);
					var2=tokenVariable();
					state._fsp--;
					if (state.failed) return ass;
					if ( state.backtracking==0 ) {		 
							 ass.setReference(false);
							 ass.setVar(var2);
							 }
					match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7432); if (state.failed) return ass;
					pushFollow(FOLLOW_selectStatement_in_tokenAssignment7439);
					selstmt2=selectStatement();
					state._fsp--;
					if (state.failed) return ass;
					if ( state.backtracking==0 ) {
							 ass.setSelStmt((selstmt2!=null?((FunSQLParser.selectStatement_return)selstmt2).stmt:null));
							 }
					}

					}
					break;
				case 3 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:911:4: ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:911:4: ( KEYWORD_VAR var3= tokenVariable EQUAL1 )
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:912:4: KEYWORD_VAR var3= tokenVariable EQUAL1
					{
					match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment7463); if (state.failed) return ass;
					pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7472);
					var3=tokenVariable();
					state._fsp--;
					if (state.failed) return ass;
					if ( state.backtracking==0 ) {		 
							 ass.setReference(false);
							 ass.setVar(var3);
							 }
					match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7478); if (state.failed) return ass;
					}

					match(input,COLON,FOLLOW_COLON_in_tokenAssignment7489); if (state.failed) return ass;
					pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7501);
					var4=tokenVariable();
					state._fsp--;
					if (state.failed) return ass;
					if ( state.backtracking==0 ) {
							 ass.setReference(true);
							 ass.setVar(var4);
							 }
					}
					break;

			}

			match(input,SEMI,FOLLOW_SEMI_in_tokenAssignment7512); if (state.failed) return ass;
			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 32, tokenAssignment_StartIndex); }

		}
		return ass;
	}
	// $ANTLR end "tokenAssignment"



	// $ANTLR start "tokenFunctionCall"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:928:1: tokenFunctionCall returns [TokenFunctionCall call] : ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI ) ;
	public final TokenFunctionCall tokenFunctionCall() throws RecognitionException {
		TokenFunctionCall call = null;

		int tokenFunctionCall_StartIndex = input.index();

		TokenFunction fun1 =null;
		TokenVariable var1 =null;
		TokenVariable var2 =null;
		TokenVariable var3 =null;


			 	call =new TokenFunctionCall();
			 
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return call; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:932:3: ( ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:933:3: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:933:3: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:934:4: KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI
			{
			match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_tokenFunctionCall7544); if (state.failed) return call;
			match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_tokenFunctionCall7549); if (state.failed) return call;
			pushFollow(FOLLOW_tokenFunction_in_tokenFunctionCall7556);
			fun1=tokenFunction();
			state._fsp--;
			if (state.failed) return call;
			if ( state.backtracking==0 ) {
					 call.setFun(fun1);
					 }
			match(input,LPAREN,FOLLOW_LPAREN_in_tokenFunctionCall7562); if (state.failed) return call;
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:940:4: ( COLON var1= tokenVariable )*
			loop39:
			while (true) {
				int alt39=2;
				int LA39_0 = input.LA(1);
				if ( (LA39_0==COLON) ) {
					alt39=1;
				}

				switch (alt39) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:941:5: COLON var1= tokenVariable
					{
					match(input,COLON,FOLLOW_COLON_in_tokenFunctionCall7573); if (state.failed) return call;
					pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall7586);
					var1=tokenVariable();
					state._fsp--;
					if (state.failed) return call;
					if ( state.backtracking==0 ) {
								 call.addInVar(var1);
								 }
					}
					break;

				default :
					break loop39;
				}
			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:946:4: ( KEYWORD_VAR var2= tokenVariable COMMA )*
			loop40:
			while (true) {
				int alt40=2;
				int LA40_0 = input.LA(1);
				if ( (LA40_0==KEYWORD_VAR) ) {
					int LA40_1 = input.LA(2);
					if ( (LA40_1==IDENTIFIER) ) {
						int LA40_2 = input.LA(3);
						if ( (LA40_2==COMMA) ) {
							alt40=1;
						}

					}

				}

				switch (alt40) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:947:5: KEYWORD_VAR var2= tokenVariable COMMA
					{
					match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7607); if (state.failed) return call;
					pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall7617);
					var2=tokenVariable();
					state._fsp--;
					if (state.failed) return call;
					if ( state.backtracking==0 ) {		
								 call.addOutVar(var2);
								 }
					match(input,COMMA,FOLLOW_COMMA_in_tokenFunctionCall7624); if (state.failed) return call;
					}
					break;

				default :
					break loop40;
				}
			}

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:953:4: ( KEYWORD_VAR var3= tokenVariable )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:954:5: KEYWORD_VAR var3= tokenVariable
			{
			match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7646); if (state.failed) return call;
			pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall7656);
			var3=tokenVariable();
			state._fsp--;
			if (state.failed) return call;
			if ( state.backtracking==0 ) {		
						 call.addOutVar(var3);
						 }
			}

			match(input,RPAREN,FOLLOW_RPAREN_in_tokenFunctionCall7667); if (state.failed) return call;
			match(input,SEMI,FOLLOW_SEMI_in_tokenFunctionCall7672); if (state.failed) return call;
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 33, tokenFunctionCall_StartIndex); }

		}
		return call;
	}
	// $ANTLR end "tokenFunctionCall"



	// $ANTLR start "tokenIdentifier"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:964:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
	public final TokenIdentifier tokenIdentifier() throws RecognitionException {
		TokenIdentifier identifier = null;

		int tokenIdentifier_StartIndex = input.index();

		String identifierText17 =null;


		        	identifier = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return identifier; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:968:9: ( ( identifierText ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:969:9: ( identifierText )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:969:9: ( identifierText )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:970:17: identifierText
			{
			pushFollow(FOLLOW_identifierText_in_tokenIdentifier7740);
			identifierText17=identifierText();
			state._fsp--;
			if (state.failed) return identifier;
			if ( state.backtracking==0 ) {
			                	identifier = new TokenIdentifier(identifierText17);
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 34, tokenIdentifier_StartIndex); }

		}
		return identifier;
	}
	// $ANTLR end "tokenIdentifier"



	// $ANTLR start "tokenDataType"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:977:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) ;
	public final TokenDataType tokenDataType() throws RecognitionException {
		TokenDataType dataType = null;

		int tokenDataType_StartIndex = input.index();

		Token TYPE_VARCHAR18=null;
		Token TYPE_INTEGER19=null;
		Token TYPE_DECIMAL20=null;
		Token TYPE_DATE21=null;


		        	dataType = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return dataType; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:981:9: ( ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:982:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:982:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
			int alt41=4;
			switch ( input.LA(1) ) {
			case TYPE_VARCHAR:
				{
				alt41=1;
				}
				break;
			case TYPE_INTEGER:
				{
				alt41=2;
				}
				break;
			case TYPE_DECIMAL:
				{
				alt41=3;
				}
				break;
			case TYPE_DATE:
				{
				alt41=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return dataType;}
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}
			switch (alt41) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:983:17: TYPE_VARCHAR
					{
					TYPE_VARCHAR18=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType7821); if (state.failed) return dataType;
					if ( state.backtracking==0 ) {
					                	dataType = new TokenDataType((TYPE_VARCHAR18!=null?TYPE_VARCHAR18.getText():null));
					                }
					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:987:17: TYPE_INTEGER
					{
					TYPE_INTEGER19=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType7859); if (state.failed) return dataType;
					if ( state.backtracking==0 ) {
					                	dataType = new TokenDataType((TYPE_INTEGER19!=null?TYPE_INTEGER19.getText():null));
					                }
					}
					break;
				case 3 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:991:17: TYPE_DECIMAL
					{
					TYPE_DECIMAL20=(Token)match(input,TYPE_DECIMAL,FOLLOW_TYPE_DECIMAL_in_tokenDataType7897); if (state.failed) return dataType;
					if ( state.backtracking==0 ) {
					                	dataType = new TokenDataType((TYPE_DECIMAL20!=null?TYPE_DECIMAL20.getText():null));
					                }
					}
					break;
				case 4 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:995:17: TYPE_DATE
					{
					TYPE_DATE21=(Token)match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDataType7935); if (state.failed) return dataType;
					if ( state.backtracking==0 ) {
					                	dataType = new TokenDataType((TYPE_DATE21!=null?TYPE_DATE21.getText():null));
					                }
					}
					break;

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 35, tokenDataType_StartIndex); }

		}
		return dataType;
	}
	// $ANTLR end "tokenDataType"



	// $ANTLR start "tokenLiteral"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1001:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
	public final TokenLiteral tokenLiteral() throws RecognitionException {
		TokenLiteral literal = null;

		int tokenLiteral_StartIndex = input.index();

		TokenIntegerLiteral tokenIntegerLiteral22 =null;
		TokenStringLiteral tokenStringLiteral23 =null;
		TokenDecimalLiteral tokenDecimalLiteral24 =null;
		TokenDateLiteral tokenDateLiteral25 =null;


		        	literal = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return literal; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1005:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1006:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1006:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1007:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1007:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
			int alt42=4;
			switch ( input.LA(1) ) {
			case LITERAL_INTEGER:
				{
				alt42=1;
				}
				break;
			case LITERAL_STRING:
				{
				alt42=2;
				}
				break;
			case LITERAL_DECIMAL:
				{
				alt42=3;
				}
				break;
			case TYPE_DATE:
				{
				alt42=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return literal;}
				NoViableAltException nvae =
					new NoViableAltException("", 42, 0, input);
				throw nvae;
			}
			switch (alt42) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1008:17: tokenIntegerLiteral
					{
					pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral8026);
					tokenIntegerLiteral22=tokenIntegerLiteral();
					state._fsp--;
					if (state.failed) return literal;
					if ( state.backtracking==0 ) {
					                	literal = tokenIntegerLiteral22;
					                }
					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1012:17: tokenStringLiteral
					{
					pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral8064);
					tokenStringLiteral23=tokenStringLiteral();
					state._fsp--;
					if (state.failed) return literal;
					if ( state.backtracking==0 ) {
					                	literal = tokenStringLiteral23;
					                }
					}
					break;
				case 3 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1016:17: tokenDecimalLiteral
					{
					pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral8102);
					tokenDecimalLiteral24=tokenDecimalLiteral();
					state._fsp--;
					if (state.failed) return literal;
					if ( state.backtracking==0 ) {
					                	literal = tokenDecimalLiteral24;
					                }
					}
					break;
				case 4 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1020:17: tokenDateLiteral
					{
					pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral8140);
					tokenDateLiteral25=tokenDateLiteral();
					state._fsp--;
					if (state.failed) return literal;
					if ( state.backtracking==0 ) {
					                	literal = tokenDateLiteral25;
					                }
					}
					break;

			}

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 36, tokenLiteral_StartIndex); }

		}
		return literal;
	}
	// $ANTLR end "tokenLiteral"



	// $ANTLR start "tokenStringLiteral"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1027:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
	public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
		TokenStringLiteral literal = null;

		int tokenStringLiteral_StartIndex = input.index();

		Token lit1=null;


		        	literal = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return literal; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1031:9: ( (lit1= LITERAL_STRING ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1032:9: (lit1= LITERAL_STRING )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1032:9: (lit1= LITERAL_STRING )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1033:17: lit1= LITERAL_STRING
			{
			lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral8240); if (state.failed) return literal;
			if ( state.backtracking==0 ) {
			                	literal = new TokenStringLiteral((lit1!=null?lit1.getText():null).substring(1, (lit1!=null?lit1.getText():null).length()-1));
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 37, tokenStringLiteral_StartIndex); }

		}
		return literal;
	}
	// $ANTLR end "tokenStringLiteral"



	// $ANTLR start "tokenIntegerLiteral"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1039:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
	public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
		TokenIntegerLiteral literal = null;

		int tokenIntegerLiteral_StartIndex = input.index();

		Token LITERAL_INTEGER26=null;


		        	literal = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return literal; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1043:9: ( ( LITERAL_INTEGER ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1044:9: ( LITERAL_INTEGER )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1044:9: ( LITERAL_INTEGER )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1045:17: LITERAL_INTEGER
			{
			LITERAL_INTEGER26=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral8320); if (state.failed) return literal;
			if ( state.backtracking==0 ) {
			                	literal = new TokenIntegerLiteral((LITERAL_INTEGER26!=null?LITERAL_INTEGER26.getText():null));
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 38, tokenIntegerLiteral_StartIndex); }

		}
		return literal;
	}
	// $ANTLR end "tokenIntegerLiteral"



	// $ANTLR start "tokenDecimalLiteral"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1052:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
	public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
		TokenDecimalLiteral literal = null;

		int tokenDecimalLiteral_StartIndex = input.index();

		Token LITERAL_DECIMAL27=null;


		        	literal = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return literal; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1056:9: ( ( LITERAL_DECIMAL ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1057:9: ( LITERAL_DECIMAL )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1057:9: ( LITERAL_DECIMAL )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1058:17: LITERAL_DECIMAL
			{
			LITERAL_DECIMAL27=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral8410); if (state.failed) return literal;
			if ( state.backtracking==0 ) {
			                	literal = new TokenDecimalLiteral((LITERAL_DECIMAL27!=null?LITERAL_DECIMAL27.getText():null));
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 39, tokenDecimalLiteral_StartIndex); }

		}
		return literal;
	}
	// $ANTLR end "tokenDecimalLiteral"



	// $ANTLR start "tokenDateLiteral"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1064:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( TYPE_DATE LITERAL_STRING ) ;
	public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
		TokenDateLiteral literal = null;

		int tokenDateLiteral_StartIndex = input.index();

		Token LITERAL_STRING28=null;


		        	literal = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return literal; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1068:9: ( ( TYPE_DATE LITERAL_STRING ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1069:9: ( TYPE_DATE LITERAL_STRING )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1069:9: ( TYPE_DATE LITERAL_STRING )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1071:17: TYPE_DATE LITERAL_STRING
			{
			match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDateLiteral8508); if (state.failed) return literal;
			LITERAL_STRING28=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenDateLiteral8526); if (state.failed) return literal;
			if ( state.backtracking==0 ) {
			                	literal = new TokenDateLiteral((LITERAL_STRING28!=null?LITERAL_STRING28.getText():null));
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 40, tokenDateLiteral_StartIndex); }

		}
		return literal;
	}
	// $ANTLR end "tokenDateLiteral"



	// $ANTLR start "variableText"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1078:2: variableText returns [String text] : (var1= IDENTIFIER ) ;
	public final String variableText() throws RecognitionException {
		String text = null;

		int variableText_StartIndex = input.index();

		Token var1=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return text; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1079:3: ( (var1= IDENTIFIER ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1080:3: (var1= IDENTIFIER )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1080:3: (var1= IDENTIFIER )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1082:4: var1= IDENTIFIER
			{
			var1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variableText8585); if (state.failed) return text;
			if ( state.backtracking==0 ) {
			 		text = (var1!=null?var1.getText():null).toUpperCase();
			 		}
			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 41, variableText_StartIndex); }

		}
		return text;
	}
	// $ANTLR end "variableText"



	// $ANTLR start "identifierText"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1088:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) ;
	public final String identifierText() throws RecognitionException {
		String text = null;

		int identifierText_StartIndex = input.index();

		Token id1=null;
		Token id2=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return text; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1089:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1090:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1090:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
			int alt43=2;
			int LA43_0 = input.LA(1);
			if ( (LA43_0==IDENTIFIER) ) {
				alt43=1;
			}
			else if ( (LA43_0==QUOTE_DOUBLE) ) {
				alt43=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return text;}
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}

			switch (alt43) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1091:4: (id1= IDENTIFIER )
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1091:4: (id1= IDENTIFIER )
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1092:4: id1= IDENTIFIER
					{
					id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText8637); if (state.failed) return text;
					if ( state.backtracking==0 ) {
					                	text = (id1!=null?id1.getText():null).toUpperCase();
					                }
					}

					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1097:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
					{
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1097:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1098:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
					{
					match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText8693); if (state.failed) return text;
					id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText8709); if (state.failed) return text;
					if ( state.backtracking==0 ) {
					                	text = (id2!=null?id2.getText():null);
					                }
					match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText8729); if (state.failed) return text;
					}

					}
					break;

			}

			}

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 42, identifierText_StartIndex); }

		}
		return text;
	}
	// $ANTLR end "identifierText"


	public static class tokenAddOperator_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tokenAddOperator"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1107:1: tokenAddOperator : ( PLUS | MINUS ) ;
	public final FunSQLParser.tokenAddOperator_return tokenAddOperator() throws RecognitionException {
		FunSQLParser.tokenAddOperator_return retval = new FunSQLParser.tokenAddOperator_return();
		retval.start = input.LT(1);
		int tokenAddOperator_StartIndex = input.index();

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1108:5: ( ( PLUS | MINUS ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
			{
			if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
				input.consume();
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 43, tokenAddOperator_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "tokenAddOperator"


	public static class tokenMultOperator_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tokenMultOperator"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1115:1: tokenMultOperator : ( MULT | DIV ) ;
	public final FunSQLParser.tokenMultOperator_return tokenMultOperator() throws RecognitionException {
		FunSQLParser.tokenMultOperator_return retval = new FunSQLParser.tokenMultOperator_return();
		retval.start = input.LT(1);
		int tokenMultOperator_StartIndex = input.index();

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1116:5: ( ( MULT | DIV ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
			{
			if ( input.LA(1)==DIV||input.LA(1)==MULT ) {
				input.consume();
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 44, tokenMultOperator_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "tokenMultOperator"


	public static class tokenCompOperator_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tokenCompOperator"
	// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1123:1: tokenCompOperator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN ) ;
	public final FunSQLParser.tokenCompOperator_return tokenCompOperator() throws RecognitionException {
		FunSQLParser.tokenCompOperator_return retval = new FunSQLParser.tokenCompOperator_return();
		retval.start = input.LT(1);
		int tokenCompOperator_StartIndex = input.index();

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1124:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
			{
			if ( input.LA(1)==EQUAL1||(input.LA(1) >= GREATER_EQUAL1 && input.LA(1) <= GREATER_THAN)||(input.LA(1) >= LESS_EQUAL1 && input.LA(1) <= LESS_THAN)||(input.LA(1) >= NOT_EQUAL1 && input.LA(1) <= NOT_EQUAL2) ) {
				input.consume();
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 45, tokenCompOperator_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "tokenCompOperator"

	// $ANTLR start synpred36_FunSQL
	public final void synpred36_FunSQL_fragment() throws RecognitionException {
		AbstractPredicate predicate1 =null;

		// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:611:3: (predicate1= parenPredicate )
		// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:611:3: predicate1= parenPredicate
		{
		pushFollow(FOLLOW_parenPredicate_in_synpred36_FunSQL5748);
		predicate1=parenPredicate();
		state._fsp--;
		if (state.failed) return;
		}

	}
	// $ANTLR end synpred36_FunSQL

	// $ANTLR start synpred46_FunSQL
	public final void synpred46_FunSQL_fragment() throws RecognitionException {
		TokenIdentifier schema1 =null;
		TokenIdentifier id1 =null;

		// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:815:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
		// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:815:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
		{
		// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:815:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
		// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:816:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
		{
		// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:816:17: (schema1= tokenIdentifier DOT )?
		int alt50=2;
		int LA50_0 = input.LA(1);
		if ( (LA50_0==IDENTIFIER) ) {
			int LA50_1 = input.LA(2);
			if ( (LA50_1==DOT) ) {
				alt50=1;
			}
		}
		else if ( (LA50_0==QUOTE_DOUBLE) ) {
			int LA50_2 = input.LA(2);
			if ( (LA50_2==IDENTIFIER) ) {
				int LA50_5 = input.LA(3);
				if ( (LA50_5==QUOTE_DOUBLE) ) {
					int LA50_6 = input.LA(4);
					if ( (LA50_6==DOT) ) {
						alt50=1;
					}
				}
			}
		}
		switch (alt50) {
			case 1 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:817:17: schema1= tokenIdentifier DOT
				{
				pushFollow(FOLLOW_tokenIdentifier_in_synpred46_FunSQL6782);
				schema1=tokenIdentifier();
				state._fsp--;
				if (state.failed) return;
				match(input,DOT,FOLLOW_DOT_in_synpred46_FunSQL6802); if (state.failed) return;
				}
				break;

		}

		pushFollow(FOLLOW_tokenIdentifier_in_synpred46_FunSQL6859);
		id1=tokenIdentifier();
		state._fsp--;
		if (state.failed) return;
		}

		}

	}
	// $ANTLR end synpred46_FunSQL

	// Delegated rules

	public final boolean synpred46_FunSQL() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred46_FunSQL_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred36_FunSQL() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred36_FunSQL_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}



	public static final BitSet FOLLOW_createSchemaStatement_in_statement1105 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_dropSchemaStatement_in_statement1160 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_createConnectionStatement_in_statement1215 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_dropConnectionStatement_in_statement1270 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_createTableStatement_in_statement1325 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_dropTableStatement_in_statement1380 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_createFunctionStatement_in_statement1435 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_dropFunctionStatement_in_statement1490 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_callFunctionStatement_in_statement1545 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_selectStatement_in_statement1600 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
	public static final BitSet FOLLOW_SEMI_in_statement1654 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1732 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1750 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1768 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1847 = new BitSet(new long[]{0x4000000000000000L});
	public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1865 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1883 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1962 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1980 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement1998 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement2018 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2038 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement2058 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2078 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement2098 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement2138 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2158 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2245 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2263 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2281 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2361 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2379 = new BitSet(new long[]{0x0000000200001000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenTable_in_createTableStatement2399 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_LPAREN_in_createTableStatement2419 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement2439 = new BitSet(new long[]{0x0000000000000000L,0x00003C0000000000L});
	public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2477 = new BitSet(new long[]{0x0000000000002000L,0x0000000800000000L});
	public static final BitSet FOLLOW_COMMA_in_createTableStatement2531 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement2551 = new BitSet(new long[]{0x0000000000000000L,0x00003C0000000000L});
	public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2589 = new BitSet(new long[]{0x0000000000002000L,0x0000000800000000L});
	public static final BitSet FOLLOW_RPAREN_in_createTableStatement2644 = new BitSet(new long[]{0x2822000000000000L});
	public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2680 = new BitSet(new long[]{0x0000000200001000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenTable_in_createTableStatement2700 = new BitSet(new long[]{0x2820000000000000L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2759 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2761 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2781 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_REPLICATED_in_createTableStatement2822 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2824 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2826 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2847 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_createTableStatement2869 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2891 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_KEYWORD_PARTITIONED_in_createTableStatement2985 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_KEYWORD_BY_in_createTableStatement2987 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement3008 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_LPAREN_in_createTableStatement3062 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement3082 = new BitSet(new long[]{0x0000000000002000L,0x0000000800000000L});
	public static final BitSet FOLLOW_COMMA_in_createTableStatement3136 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement3156 = new BitSet(new long[]{0x0000000000002000L,0x0000000800000000L});
	public static final BitSet FOLLOW_RPAREN_in_createTableStatement3211 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_LPAREN_in_createTableStatement3248 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement3268 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3304 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3306 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3326 = new BitSet(new long[]{0x0000000000002000L,0x0000000800000000L});
	public static final BitSet FOLLOW_COMMA_in_createTableStatement3364 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement3384 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3420 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3422 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3442 = new BitSet(new long[]{0x0000000000002000L,0x0000000800000000L});
	public static final BitSet FOLLOW_RPAREN_in_createTableStatement3481 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement3595 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement3613 = new BitSet(new long[]{0x0000000200001000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenTable_in_dropTableStatement3633 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CREATE_in_createFunctionStatement3699 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement3717 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenFunction_in_createFunctionStatement3737 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_LPAREN_in_createFunctionStatement3753 = new BitSet(new long[]{0x0420000000000000L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createFunctionStatement3782 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement3802 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3809 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_createFunctionStatement3815 = new BitSet(new long[]{0x0420000000000000L});
	public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement3853 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement3873 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3880 = new BitSet(new long[]{0x0000000000002000L,0x0000000800000000L});
	public static final BitSet FOLLOW_COMMA_in_createFunctionStatement3893 = new BitSet(new long[]{0x0400000000000000L});
	public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement3898 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement3912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3918 = new BitSet(new long[]{0x0000000000002000L,0x0000000800000000L});
	public static final BitSet FOLLOW_RPAREN_in_createFunctionStatement3927 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement3931 = new BitSet(new long[]{0x0001040000001000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tokenAssignment_in_createFunctionStatement3945 = new BitSet(new long[]{0x0001040000001000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tokenFunctionCall_in_createFunctionStatement4024 = new BitSet(new long[]{0x0001040000001000L,0x0000000000000020L});
	public static final BitSet FOLLOW_KEYWORD_END_in_createFunctionStatement4066 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_SEMI_in_createFunctionStatement4073 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_DROP_in_dropFunctionStatement4150 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement4168 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenFunction_in_dropFunctionStatement4188 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CALL_in_callFunctionStatement4254 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement4272 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenFunction_in_callFunctionStatement4292 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement4369 = new BitSet(new long[]{0x0000000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_abstractExpression_in_selectStatement4389 = new BitSet(new long[]{0x0002004000002000L});
	public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4444 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4465 = new BitSet(new long[]{0x0002000000002000L});
	public static final BitSet FOLLOW_COMMA_in_selectStatement4539 = new BitSet(new long[]{0x0000000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_abstractExpression_in_selectStatement4559 = new BitSet(new long[]{0x0002004000002000L});
	public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4614 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4635 = new BitSet(new long[]{0x0002000000002000L});
	public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement4744 = new BitSet(new long[]{0x0000000200001000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenTable_in_selectStatement4764 = new BitSet(new long[]{0x0018004000002002L,0x0000000000000040L});
	public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4820 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4841 = new BitSet(new long[]{0x0018000000002002L,0x0000000000000040L});
	public static final BitSet FOLLOW_COMMA_in_selectStatement4915 = new BitSet(new long[]{0x0000000200001000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenTable_in_selectStatement4935 = new BitSet(new long[]{0x0018004000002002L,0x0000000000000040L});
	public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4990 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement5011 = new BitSet(new long[]{0x0018000000002002L,0x0000000000000040L});
	public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement5121 = new BitSet(new long[]{0x0100000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_abstractPredicate_in_selectStatement5141 = new BitSet(new long[]{0x0018000000000002L});
	public static final BitSet FOLLOW_KEYWORD_GROUP_in_selectStatement5248 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_KEYWORD_BY_in_selectStatement5250 = new BitSet(new long[]{0x0000000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_abstractExpression_in_selectStatement5270 = new BitSet(new long[]{0x0010000000002002L});
	public static final BitSet FOLLOW_COMMA_in_selectStatement5324 = new BitSet(new long[]{0x0000000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_abstractExpression_in_selectStatement5344 = new BitSet(new long[]{0x0010000000002002L});
	public static final BitSet FOLLOW_KEYWORD_HAVING_in_selectStatement5470 = new BitSet(new long[]{0x0100000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_abstractPredicate_in_selectStatement5490 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate5552 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr5590 = new BitSet(new long[]{0x0200000000000002L});
	public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr5601 = new BitSet(new long[]{0x0100000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr5609 = new BitSet(new long[]{0x0200000000000002L});
	public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd5648 = new BitSet(new long[]{0x0000002000000002L});
	public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd5659 = new BitSet(new long[]{0x0100000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd5667 = new BitSet(new long[]{0x0000002000000002L});
	public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot5708 = new BitSet(new long[]{0x0000000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot5721 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parenPredicate_in_complexPredicate5748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simplePredicate_in_complexPredicate5763 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_parenPredicate5788 = new BitSet(new long[]{0x0100000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate5795 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_RPAREN_in_parenPredicate5801 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_abstractExpression_in_simplePredicate5843 = new BitSet(new long[]{0x0000000038400000L,0x0000000000600E00L});
	public static final BitSet FOLLOW_tokenCompOperator_in_simplePredicate5898 = new BitSet(new long[]{0x0000000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_abstractExpression_in_simplePredicate5936 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_complexExpressionAdd_in_abstractExpression5990 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd6020 = new BitSet(new long[]{0x0000000000000002L,0x0000000004020000L});
	public static final BitSet FOLLOW_tokenAddOperator_in_complexExpressionAdd6033 = new BitSet(new long[]{0x0000000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd6041 = new BitSet(new long[]{0x0000000000000002L,0x0000000004020000L});
	public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult6081 = new BitSet(new long[]{0x0000000000020002L,0x0000000000080000L});
	public static final BitSet FOLLOW_tokenMultOperator_in_complexExpressionMult6094 = new BitSet(new long[]{0x0000000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult6102 = new BitSet(new long[]{0x0000000000020002L,0x0000000000080000L});
	public static final BitSet FOLLOW_MINUS_in_complexExpressionSigned6144 = new BitSet(new long[]{0x0000000202000000L,0x000004004000F000L});
	public static final BitSet FOLLOW_PLUS_in_complexExpressionSigned6153 = new BitSet(new long[]{0x0000000202000000L,0x000004004000F000L});
	public static final BitSet FOLLOW_complexExpression_in_complexExpressionSigned6165 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parenExpression_in_complexExpression6200 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_aggregationExpression_in_complexExpression6215 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simpleExpression_in_complexExpression6230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_parenExpression6255 = new BitSet(new long[]{0x0000000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_abstractExpression_in_parenExpression6262 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_RPAREN_in_parenExpression6268 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression6320 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_LPAREN_in_aggregationExpression6328 = new BitSet(new long[]{0x0000000202000000L,0x000004004402F000L});
	public static final BitSet FOLLOW_abstractExpression_in_aggregationExpression6335 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_RPAREN_in_aggregationExpression6341 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenAttribute_in_simpleExpression6416 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenLiteral_in_simpleExpression6472 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute6600 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_DOT_in_tokenAttribute6620 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute6659 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable6782 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_DOT_in_tokenTable6802 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable6859 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_tokenTable6926 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable6968 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema7049 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction7165 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_DOT_in_tokenFunction7185 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction7224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variableText_in_tokenVariable7304 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_tokenAssignment7371 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7383 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7389 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_selectStatement_in_tokenAssignment7396 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment7417 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7426 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7432 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_selectStatement_in_tokenAssignment7439 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment7463 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7472 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7478 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_COLON_in_tokenAssignment7489 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7501 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_SEMI_in_tokenAssignment7512 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CALL_in_tokenFunctionCall7544 = new BitSet(new long[]{0x0004000000000000L});
	public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_tokenFunctionCall7549 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenFunction_in_tokenFunctionCall7556 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_LPAREN_in_tokenFunctionCall7562 = new BitSet(new long[]{0x0000000000001000L,0x0000000000000020L});
	public static final BitSet FOLLOW_COLON_in_tokenFunctionCall7573 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall7586 = new BitSet(new long[]{0x0000000000001000L,0x0000000000000020L});
	public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7607 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall7617 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_tokenFunctionCall7624 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7646 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall7656 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_RPAREN_in_tokenFunctionCall7667 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_SEMI_in_tokenFunctionCall7672 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifierText_in_tokenIdentifier7740 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType7821 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType7859 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_DECIMAL_in_tokenDataType7897 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_DATE_in_tokenDataType7935 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral8026 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral8064 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral8102 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral8140 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral8240 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral8320 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral8410 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_DATE_in_tokenDateLiteral8508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
	public static final BitSet FOLLOW_LITERAL_STRING_in_tokenDateLiteral8526 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_variableText8585 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_identifierText8637 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText8693 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_identifierText8709 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText8729 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parenPredicate_in_synpred36_FunSQL5748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIdentifier_in_synpred46_FunSQL6782 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_DOT_in_synpred46_FunSQL6802 = new BitSet(new long[]{0x0000000200000000L,0x0000000040000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_synpred46_FunSQL6859 = new BitSet(new long[]{0x0000000000000002L});
}
