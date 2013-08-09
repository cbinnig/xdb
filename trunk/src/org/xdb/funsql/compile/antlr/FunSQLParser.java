// $ANTLR 3.5 /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g 2013-07-31 15:26:19
 
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
		"KEYWORD_DATA", "KEYWORD_DISTINCT", "KEYWORD_DROP", "KEYWORD_END", "KEYWORD_FROM", 
		"KEYWORD_FUNCTION", "KEYWORD_GROUP", "KEYWORD_HAVING", "KEYWORD_IN", "KEYWORD_INFILE", 
		"KEYWORD_INTO", "KEYWORD_LIKE", "KEYWORD_LOAD", "KEYWORD_MAX", "KEYWORD_MIN", 
		"KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_OUT", "KEYWORD_PARTITION", "KEYWORD_PARTITIONED", 
		"KEYWORD_PASSWD", "KEYWORD_REPLICATED", "KEYWORD_SCHEMA", "KEYWORD_SELECT", 
		"KEYWORD_STORE", "KEYWORD_SUM", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", 
		"KEYWORD_VAR", "KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", 
		"LESS_THAN", "LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", 
		"M", "MINUS", "MOD", "MULT", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", 
		"PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", 
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
	public static final int KEYWORD_DATA=46;
	public static final int KEYWORD_DISTINCT=47;
	public static final int KEYWORD_DROP=48;
	public static final int KEYWORD_END=49;
	public static final int KEYWORD_FROM=50;
	public static final int KEYWORD_FUNCTION=51;
	public static final int KEYWORD_GROUP=52;
	public static final int KEYWORD_HAVING=53;
	public static final int KEYWORD_IN=54;
	public static final int KEYWORD_INFILE=55;
	public static final int KEYWORD_INTO=56;
	public static final int KEYWORD_LIKE=57;
	public static final int KEYWORD_LOAD=58;
	public static final int KEYWORD_MAX=59;
	public static final int KEYWORD_MIN=60;
	public static final int KEYWORD_NOT=61;
	public static final int KEYWORD_OR=62;
	public static final int KEYWORD_OUT=63;
	public static final int KEYWORD_PARTITION=64;
	public static final int KEYWORD_PARTITIONED=65;
	public static final int KEYWORD_PASSWD=66;
	public static final int KEYWORD_REPLICATED=67;
	public static final int KEYWORD_SCHEMA=68;
	public static final int KEYWORD_SELECT=69;
	public static final int KEYWORD_STORE=70;
	public static final int KEYWORD_SUM=71;
	public static final int KEYWORD_TABLE=72;
	public static final int KEYWORD_URL=73;
	public static final int KEYWORD_USER=74;
	public static final int KEYWORD_VAR=75;
	public static final int KEYWORD_WHERE=76;
	public static final int L=77;
	public static final int LBRACKET=78;
	public static final int LESS_EQUAL1=79;
	public static final int LESS_EQUAL2=80;
	public static final int LESS_THAN=81;
	public static final int LITERAL_DECIMAL=82;
	public static final int LITERAL_INTEGER=83;
	public static final int LITERAL_STRING=84;
	public static final int LPAREN=85;
	public static final int M=86;
	public static final int MINUS=87;
	public static final int MOD=88;
	public static final int MULT=89;
	public static final int N=90;
	public static final int NOT_EQUAL1=91;
	public static final int NOT_EQUAL2=92;
	public static final int O=93;
	public static final int P=94;
	public static final int PIPE=95;
	public static final int PLUS=96;
	public static final int Q=97;
	public static final int QUESTION=98;
	public static final int QUOTED_STRING=99;
	public static final int QUOTE_DOUBLE=100;
	public static final int QUOTE_SINGLE=101;
	public static final int QUOTE_TRIPLE=102;
	public static final int R=103;
	public static final int RBRACKET=104;
	public static final int RPAREN=105;
	public static final int S=106;
	public static final int SEMI=107;
	public static final int SHIFT_LEFT=108;
	public static final int SHIFT_RIGHT=109;
	public static final int T=110;
	public static final int TILDE=111;
	public static final int TYPE_DATE=112;
	public static final int TYPE_DECIMAL=113;
	public static final int TYPE_INTEGER=114;
	public static final int TYPE_VARCHAR=115;
	public static final int U=116;
	public static final int UNDERSCORE=117;
	public static final int V=118;
	public static final int W=119;
	public static final int WS=120;
	public static final int X=121;
	public static final int Y=122;
	public static final int Z=123;

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
		this.state.ruleMemo = new HashMap[124+1];


	}

	@Override public String[] getTokenNames() { return FunSQLParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g"; }


	  @Override
	  protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
	    throw new MismatchedTokenException(ttype, input);
	  }

	  @Override
	  public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
	    throw e;
	  }



	// $ANTLR start "statement"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:95:1: statement returns [AbstractServerStmt stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement ) ( SEMI )? ) ;
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
		ParserRuleReturnScope loadDataInfileStatement11 =null;


		        	stmt = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return stmt; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:99:9: ( ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement ) ( SEMI )? ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:100:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement ) ( SEMI )? )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:100:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement ) ( SEMI )? )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:101:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement ) ( SEMI )?
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:101:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement )
			int alt1=11;
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
			case KEYWORD_LOAD:
				{
				alt1=11;
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:102:17: createSchemaStatement
					{
					pushFollow(FOLLOW_createSchemaStatement_in_statement1106);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:108:17: dropSchemaStatement
					{
					pushFollow(FOLLOW_dropSchemaStatement_in_statement1161);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:114:17: createConnectionStatement
					{
					pushFollow(FOLLOW_createConnectionStatement_in_statement1216);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:120:17: dropConnectionStatement
					{
					pushFollow(FOLLOW_dropConnectionStatement_in_statement1271);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:126:17: createTableStatement
					{
					pushFollow(FOLLOW_createTableStatement_in_statement1326);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:132:17: dropTableStatement
					{
					pushFollow(FOLLOW_dropTableStatement_in_statement1381);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:138:17: createFunctionStatement
					{
					pushFollow(FOLLOW_createFunctionStatement_in_statement1436);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:144:17: dropFunctionStatement
					{
					pushFollow(FOLLOW_dropFunctionStatement_in_statement1491);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:150:17: callFunctionStatement
					{
					pushFollow(FOLLOW_callFunctionStatement_in_statement1546);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:156:17: selectStatement
					{
					pushFollow(FOLLOW_selectStatement_in_statement1601);
					selectStatement10=selectStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (selectStatement10!=null?((FunSQLParser.selectStatement_return)selectStatement10).stmt:null);
					                	stmt.setStmtString((selectStatement10!=null?input.toString(selectStatement10.start,selectStatement10.stop):null));
					                }
					}
					break;
				case 11 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:162:17: loadDataInfileStatement
					{
					pushFollow(FOLLOW_loadDataInfileStatement_in_statement1655);
					loadDataInfileStatement11=loadDataInfileStatement();
					state._fsp--;
					if (state.failed) return stmt;
					if ( state.backtracking==0 ) {
					                	stmt = (loadDataInfileStatement11!=null?((FunSQLParser.loadDataInfileStatement_return)loadDataInfileStatement11).stmt:null);
					                	stmt.setStmtString((loadDataInfileStatement11!=null?input.toString(loadDataInfileStatement11.start,loadDataInfileStatement11.stop):null));
					                }
					}
					break;

			}

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:168:17: ( SEMI )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==SEMI) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:168:17: SEMI
					{
					match(input,SEMI,FOLLOW_SEMI_in_statement1709); if (state.failed) return stmt;
					}
					break;

			}

			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
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
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:172:1: createSchemaStatement returns [CreateSchemaStmt stmt] : ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) ;
	public final FunSQLParser.createSchemaStatement_return createSchemaStatement() throws RecognitionException {
		FunSQLParser.createSchemaStatement_return retval = new FunSQLParser.createSchemaStatement_return();
		retval.start = input.LT(1);
		int createSchemaStatement_StartIndex = input.index();

		TokenSchema tokenSchema12 =null;


		        	retval.stmt = new CreateSchemaStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:176:9: ( ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:177:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:177:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:178:17: KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema
			{
			match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1787); if (state.failed) return retval;
			match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1805); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenSchema_in_createSchemaStatement1823);
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
		    	reportError(e);
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
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:186:1: dropSchemaStatement returns [DropSchemaStmt stmt] : ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) ;
	public final FunSQLParser.dropSchemaStatement_return dropSchemaStatement() throws RecognitionException {
		FunSQLParser.dropSchemaStatement_return retval = new FunSQLParser.dropSchemaStatement_return();
		retval.start = input.LT(1);
		int dropSchemaStatement_StartIndex = input.index();

		TokenSchema tokenSchema13 =null;


		        	retval.stmt = new DropSchemaStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:190:9: ( ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:191:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:191:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:192:17: KEYWORD_DROP KEYWORD_SCHEMA tokenSchema
			{
			match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1902); if (state.failed) return retval;
			match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1920); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenSchema_in_dropSchemaStatement1938);
			tokenSchema13=tokenSchema();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setSchema(tokenSchema13);
			                }
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		    	reportError(e);
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
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:200:1: createConnectionStatement returns [CreateConnectionStmt stmt] : ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) ;
	public final FunSQLParser.createConnectionStatement_return createConnectionStatement() throws RecognitionException {
		FunSQLParser.createConnectionStatement_return retval = new FunSQLParser.createConnectionStatement_return();
		retval.start = input.LT(1);
		int createConnectionStatement_StartIndex = input.index();

		TokenStringLiteral litURL =null;
		TokenStringLiteral litUser =null;
		TokenStringLiteral litPasswd =null;
		TokenStringLiteral litStore =null;
		TokenIdentifier tokenIdentifier14 =null;


		        	retval.stmt = new CreateConnectionStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:204:9: ( ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:205:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:205:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:206:17: KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral
			{
			match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createConnectionStatement2017); if (state.failed) return retval;
			match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement2035); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenIdentifier_in_createConnectionStatement2053);
			tokenIdentifier14=tokenIdentifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setConnection(tokenIdentifier14);
			                }
			match(input,KEYWORD_URL,FOLLOW_KEYWORD_URL_in_createConnectionStatement2073); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2093);
			litURL=tokenStringLiteral();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setURL(litURL);
			                }
			match(input,KEYWORD_USER,FOLLOW_KEYWORD_USER_in_createConnectionStatement2113); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2133);
			litUser=tokenStringLiteral();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setUser(litUser);
			                }
			match(input,KEYWORD_PASSWD,FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement2153); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2173);
			litPasswd=tokenStringLiteral();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setPasswd(litPasswd);
			                }
			match(input,KEYWORD_STORE,FOLLOW_KEYWORD_STORE_in_createConnectionStatement2193); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2213);
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
		    	reportError(e);
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
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:230:1: dropConnectionStatement returns [DropConnectionStmt stmt] : ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) ;
	public final FunSQLParser.dropConnectionStatement_return dropConnectionStatement() throws RecognitionException {
		FunSQLParser.dropConnectionStatement_return retval = new FunSQLParser.dropConnectionStatement_return();
		retval.start = input.LT(1);
		int dropConnectionStatement_StartIndex = input.index();

		TokenIdentifier tokenIdentifier15 =null;


		        	retval.stmt = new DropConnectionStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:234:9: ( ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:235:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:235:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:236:17: KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier
			{
			match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2300); if (state.failed) return retval;
			match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2318); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenIdentifier_in_dropConnectionStatement2336);
			tokenIdentifier15=tokenIdentifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setConnection(tokenIdentifier15);
			                }
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		    	reportError(e);
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
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:245:1: createTableStatement returns [CreateTableStmt stmt] : ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) ) ;
	public final FunSQLParser.createTableStatement_return createTableStatement() throws RecognitionException {
		FunSQLParser.createTableStatement_return retval = new FunSQLParser.createTableStatement_return();
		retval.start = input.LT(1);
		int createTableStatement_StartIndex = input.index();

		TokenTable table1 =null;
		String att1 =null;
		TokenDataType dataType1 =null;
		String att2 =null;
		TokenDataType dataType2 =null;
		TokenIdentifier connection1 =null;
		TokenIdentifier connectionR1 =null;
		TokenIdentifier connectionR2 =null;
		String method =null;
		String pcolumn1 =null;
		String pcolumn2 =null;
		String p1 =null;
		TokenIdentifier c1 =null;
		TokenIdentifier c2 =null;
		TokenIdentifier c3 =null;
		String p2 =null;


		        	retval.stmt = new CreateTableStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:249:9: ( ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:250:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:250:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:251:17: KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) )
			{
			match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2416); if (state.failed) return retval;
			match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2434); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenTable_in_createTableStatement2454);
			table1=tokenTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setTable(table1);
			                }
			match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2474); if (state.failed) return retval;
			pushFollow(FOLLOW_identifierText_in_createTableStatement2494);
			att1=identifierText();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.addAttribute(att1);
			                }
			pushFollow(FOLLOW_tokenDataType_in_createTableStatement2532);
			dataType1=tokenDataType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.addType(dataType1);
			                }
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:265:17: ( COMMA att2= identifierText dataType2= tokenDataType )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==COMMA) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:266:17: COMMA att2= identifierText dataType2= tokenDataType
					{
					match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2586); if (state.failed) return retval;
					pushFollow(FOLLOW_identifierText_in_createTableStatement2606);
					att2=identifierText();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addAttribute(att2);
					                }
					pushFollow(FOLLOW_tokenDataType_in_createTableStatement2644);
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

			match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2699); if (state.failed) return retval;
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:277:17: ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) )
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==KEYWORD_IN||LA13_0==KEYWORD_REPLICATED) ) {
				alt13=1;
			}
			else if ( (LA13_0==KEYWORD_PARTITIONED) ) {
				alt13=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:277:18: ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:277:18: ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) )
					int alt5=2;
					int LA5_0 = input.LA(1);
					if ( (LA5_0==KEYWORD_IN) ) {
						alt5=1;
					}
					else if ( (LA5_0==KEYWORD_REPLICATED) ) {
						alt5=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 5, 0, input);
						throw nvae;
					}

					switch (alt5) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:277:19: ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
							{
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:277:19: ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:278:17: KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier
							{
							match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2737); if (state.failed) return retval;
							match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2739); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2759);
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
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:282:19: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* )
							{
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:282:19: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* )
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:283:17: KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )*
							{
							match(input,KEYWORD_REPLICATED,FOLLOW_KEYWORD_REPLICATED_in_createTableStatement2800); if (state.failed) return retval;
							match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2802); if (state.failed) return retval;
							match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2804); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2825);
							connectionR1=tokenIdentifier();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addConnection(connectionR1);
							                }
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:287:17: ( COMMA connectionR2= tokenIdentifier )*
							loop4:
							while (true) {
								int alt4=2;
								int LA4_0 = input.LA(1);
								if ( (LA4_0==COMMA) ) {
									alt4=1;
								}

								switch (alt4) {
								case 1 :
									// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:287:19: COMMA connectionR2= tokenIdentifier
									{
									match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2847); if (state.failed) return retval;
									pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2869);
									connectionR2=tokenIdentifier();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									                	retval.stmt.addConnection(connectionR2);
									                }
									}
									break;

								default :
									break loop4;
								}
							}

							}

							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:294:17: ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:294:17: ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:295:17: KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN
					{
					match(input,KEYWORD_PARTITIONED,FOLLOW_KEYWORD_PARTITIONED_in_createTableStatement2963); if (state.failed) return retval;
					match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_createTableStatement2965); if (state.failed) return retval;
					pushFollow(FOLLOW_identifierText_in_createTableStatement2986);
					method=identifierText();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.setPartitionType(method);
					                }
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:300:17: ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )?
					int alt7=2;
					alt7 = dfa7.predict(input);
					switch (alt7) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:301:17: LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN
							{
							match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement3040); if (state.failed) return retval;
							pushFollow(FOLLOW_identifierText_in_createTableStatement3060);
							pcolumn1=identifierText();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addPartitionColumn(pcolumn1);
							                }
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:306:17: ( COMMA pcolumn2= identifierText )*
							loop6:
							while (true) {
								int alt6=2;
								int LA6_0 = input.LA(1);
								if ( (LA6_0==COMMA) ) {
									alt6=1;
								}

								switch (alt6) {
								case 1 :
									// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:307:17: COMMA pcolumn2= identifierText
									{
									match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3114); if (state.failed) return retval;
									pushFollow(FOLLOW_identifierText_in_createTableStatement3134);
									pcolumn2=identifierText();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									                	retval.stmt.addPartitionColumn(pcolumn2);
									                }
									}
									break;

								default :
									break loop6;
								}
							}

							match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement3189); if (state.failed) return retval;
							}
							break;

					}

					match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement3226); if (state.failed) return retval;
					pushFollow(FOLLOW_identifierText_in_createTableStatement3246);
					p1=identifierText();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addPartition(p1);
					                }
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:320:17: ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) )
					int alt9=2;
					int LA9_0 = input.LA(1);
					if ( (LA9_0==KEYWORD_IN) ) {
						alt9=1;
					}
					else if ( (LA9_0==KEYWORD_REPLICATED) ) {
						alt9=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 9, 0, input);
						throw nvae;
					}

					switch (alt9) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:320:19: ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier )
							{
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:320:19: ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier )
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:320:20: KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier
							{
							match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3285); if (state.failed) return retval;
							match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3287); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3307);
							c1=tokenIdentifier();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addPConnection(p1,c1);
							                }
							}

							}
							break;
						case 2 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:324:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
							{
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:324:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:324:18: KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )?
							{
							match(input,KEYWORD_REPLICATED,FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3330); if (state.failed) return retval;
							match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3332); if (state.failed) return retval;
							match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3334); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3355);
							c2=tokenIdentifier();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addPConnection(p1,c2);
							                }
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:328:17: ( COMMA c3= tokenIdentifier )?
							int alt8=2;
							alt8 = dfa8.predict(input);
							switch (alt8) {
								case 1 :
									// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:328:18: COMMA c3= tokenIdentifier
									{
									match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3376); if (state.failed) return retval;
									pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3381);
									c3=tokenIdentifier();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									                	retval.stmt.addPConnection(p1,c3);
									                }
									}
									break;

							}

							}

							}
							break;

					}

					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:331:17: ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )*
					loop12:
					while (true) {
						int alt12=2;
						int LA12_0 = input.LA(1);
						if ( (LA12_0==COMMA) ) {
							alt12=1;
						}

						switch (alt12) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:332:17: COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) )
							{
							match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3423); if (state.failed) return retval;
							pushFollow(FOLLOW_identifierText_in_createTableStatement3443);
							p2=identifierText();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							                	retval.stmt.addPartition(p2);
							                }
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:337:16: ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) )
							int alt11=2;
							int LA11_0 = input.LA(1);
							if ( (LA11_0==KEYWORD_IN) ) {
								alt11=1;
							}
							else if ( (LA11_0==KEYWORD_REPLICATED) ) {
								alt11=2;
							}

							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								NoViableAltException nvae =
									new NoViableAltException("", 11, 0, input);
								throw nvae;
							}

							switch (alt11) {
								case 1 :
									// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:337:18: ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )
									{
									// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:337:18: ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )
									// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:337:19: KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier
									{
									match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3481); if (state.failed) return retval;
									match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3483); if (state.failed) return retval;
									pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3503);
									c2=tokenIdentifier();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									                	retval.stmt.addPConnection(p2,c2);
									                }
									}

									}
									break;
								case 2 :
									// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:341:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
									{
									// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:341:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
									// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:341:18: KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )?
									{
									match(input,KEYWORD_REPLICATED,FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3526); if (state.failed) return retval;
									match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3528); if (state.failed) return retval;
									match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3530); if (state.failed) return retval;
									pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3551);
									c2=tokenIdentifier();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									                	retval.stmt.addPConnection(p2,c2);
									                }
									// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:345:17: ( COMMA c3= tokenIdentifier )?
									int alt10=2;
									alt10 = dfa10.predict(input);
									switch (alt10) {
										case 1 :
											// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:345:18: COMMA c3= tokenIdentifier
											{
											match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3572); if (state.failed) return retval;
											pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3577);
											c3=tokenIdentifier();
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) {
											                	retval.stmt.addPConnection(p2,c3);
											                }
											}
											break;

									}

									}

									}
									break;

							}

							}
							break;

						default :
							break loop12;
						}
					}

					match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement3620); if (state.failed) return retval;
					}

					}
					break;

			}

			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		    	reportError(e);
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
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:356:1: dropTableStatement returns [DropTableStmt stmt] : ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) ;
	public final FunSQLParser.dropTableStatement_return dropTableStatement() throws RecognitionException {
		FunSQLParser.dropTableStatement_return retval = new FunSQLParser.dropTableStatement_return();
		retval.start = input.LT(1);
		int dropTableStatement_StartIndex = input.index();

		TokenTable table1 =null;


		        	retval.stmt = new DropTableStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:360:9: ( ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:361:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:361:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:362:17: KEYWORD_DROP KEYWORD_TABLE table1= tokenTable
			{
			match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement3734); if (state.failed) return retval;
			match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement3752); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenTable_in_dropTableStatement3772);
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
		    	reportError(e);
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
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:370:1: createFunctionStatement returns [CreateFunctionStmt stmt] : ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI ) ;
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

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:374:9: ( ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:375:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:375:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:376:17: KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI
			{
			match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createFunctionStatement3838); if (state.failed) return retval;
			match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement3856); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenFunction_in_createFunctionStatement3876);
			function1=tokenFunction();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setFunction(function1);
			                }
			match(input,LPAREN,FOLLOW_LPAREN_in_createFunctionStatement3892); if (state.failed) return retval;
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:382:10: ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==KEYWORD_IN) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:383:17: KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA
					{
					match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createFunctionStatement3921); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement3941);
					var1=tokenVariable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					               		retval.stmt.addInParam(var1);
									}
					match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3948); if (state.failed) return retval;
					match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement3954); if (state.failed) return retval;
					}
					break;

				default :
					break loop14;
				}
			}

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:390:3: ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:391:17: KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
			{
			match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement3992); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement4012);
			var2=tokenVariable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			               		retval.stmt.addOutParam(var2);
							}
			match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4019); if (state.failed) return retval;
			}

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:397:3: ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==COMMA) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:398:4: COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
					{
					match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement4032); if (state.failed) return retval;
					match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement4037); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement4051);
					var2=tokenVariable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					           		retval.stmt.addOutParam(var2);
								}
					match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4057); if (state.failed) return retval;
					}
					break;

				default :
					break loop15;
				}
			}

			match(input,RPAREN,FOLLOW_RPAREN_in_createFunctionStatement4066); if (state.failed) return retval;
			match(input,KEYWORD_BEGIN,FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement4070); if (state.failed) return retval;
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:407:3: ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )*
			loop16:
			while (true) {
				int alt16=3;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==COLON||LA16_0==KEYWORD_VAR) ) {
					alt16=1;
				}
				else if ( (LA16_0==KEYWORD_CALL) ) {
					alt16=2;
				}

				switch (alt16) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:408:3: (ass1= tokenAssignment )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:408:3: (ass1= tokenAssignment )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:409:3: ass1= tokenAssignment
					{
					pushFollow(FOLLOW_tokenAssignment_in_createFunctionStatement4084);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:415:17: (call1= tokenFunctionCall )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:415:17: (call1= tokenFunctionCall )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:416:17: call1= tokenFunctionCall
					{
					pushFollow(FOLLOW_tokenFunctionCall_in_createFunctionStatement4163);
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
					break loop16;
				}
			}

			match(input,KEYWORD_END,FOLLOW_KEYWORD_END_in_createFunctionStatement4205); if (state.failed) return retval;
			match(input,SEMI,FOLLOW_SEMI_in_createFunctionStatement4212); if (state.failed) return retval;
			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		    	reportError(e);
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
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:426:1: dropFunctionStatement returns [DropFunctionStmt stmt] : ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) ;
	public final FunSQLParser.dropFunctionStatement_return dropFunctionStatement() throws RecognitionException {
		FunSQLParser.dropFunctionStatement_return retval = new FunSQLParser.dropFunctionStatement_return();
		retval.start = input.LT(1);
		int dropFunctionStatement_StartIndex = input.index();

		TokenFunction fun1 =null;


		        	retval.stmt = new DropFunctionStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:430:9: ( ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:431:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:431:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:432:17: KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction
			{
			match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropFunctionStatement4289); if (state.failed) return retval;
			match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement4307); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenFunction_in_dropFunctionStatement4327);
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
		    	reportError(e);
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
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:440:1: callFunctionStatement returns [CallFunctionStmt stmt] : ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) ;
	public final FunSQLParser.callFunctionStatement_return callFunctionStatement() throws RecognitionException {
		FunSQLParser.callFunctionStatement_return retval = new FunSQLParser.callFunctionStatement_return();
		retval.start = input.LT(1);
		int callFunctionStatement_StartIndex = input.index();

		TokenFunction fun1 =null;


		        	retval.stmt = new CallFunctionStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:444:9: ( ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:445:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:445:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:446:17: KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction
			{
			match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_callFunctionStatement4393); if (state.failed) return retval;
			match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement4411); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenFunction_in_callFunctionStatement4431);
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
		    	reportError(e);
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
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:454:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) ;
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

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:459:9: ( ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:460:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:460:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:462:17: KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )?
			{
			match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement4508); if (state.failed) return retval;
			pushFollow(FOLLOW_abstractExpression_in_selectStatement4528);
			selExpr1=abstractExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.addSelExpression(selExpr1);
			                	++i;
			                }
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:468:17: ( KEYWORD_AS selAlias1= tokenIdentifier )?
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==KEYWORD_AS) ) {
				alt17=1;
			}
			switch (alt17) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:469:18: KEYWORD_AS selAlias1= tokenIdentifier
					{
					match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4583); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4604);
					selAlias1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                		retval.stmt.setSelAlias(i-1, selAlias1);
					                	}
					}
					break;

			}

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:475:17: ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )*
			loop19:
			while (true) {
				int alt19=2;
				int LA19_0 = input.LA(1);
				if ( (LA19_0==COMMA) ) {
					alt19=1;
				}

				switch (alt19) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:476:17: COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )?
					{
					match(input,COMMA,FOLLOW_COMMA_in_selectStatement4678); if (state.failed) return retval;
					pushFollow(FOLLOW_abstractExpression_in_selectStatement4698);
					selExpr2=abstractExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addSelExpression(selExpr2);
					                	++i;
					                }
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:482:17: ( KEYWORD_AS selAlias2= tokenIdentifier )?
					int alt18=2;
					int LA18_0 = input.LA(1);
					if ( (LA18_0==KEYWORD_AS) ) {
						alt18=1;
					}
					switch (alt18) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:483:18: KEYWORD_AS selAlias2= tokenIdentifier
							{
							match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4753); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4774);
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
					break loop19;
				}
			}

			match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement4883); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenTable_in_selectStatement4903);
			table1=tokenTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.addTable(table1);
			                	i=1;
			                }
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:498:17: ( KEYWORD_AS tableAlias1= tokenIdentifier )?
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==KEYWORD_AS) ) {
				alt20=1;
			}
			switch (alt20) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:499:18: KEYWORD_AS tableAlias1= tokenIdentifier
					{
					match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4959); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4980);
					tableAlias1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                		retval.stmt.setTableAlias(i-1, tableAlias1);
					                	}
					}
					break;

			}

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:505:17: ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )*
			loop22:
			while (true) {
				int alt22=2;
				int LA22_0 = input.LA(1);
				if ( (LA22_0==COMMA) ) {
					alt22=1;
				}

				switch (alt22) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:506:17: COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )?
					{
					match(input,COMMA,FOLLOW_COMMA_in_selectStatement5054); if (state.failed) return retval;
					pushFollow(FOLLOW_tokenTable_in_selectStatement5074);
					table2=tokenTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addTable(table2);
					                	++i;
					                }
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:512:17: ( KEYWORD_AS tableAlias2= tokenIdentifier )?
					int alt21=2;
					int LA21_0 = input.LA(1);
					if ( (LA21_0==KEYWORD_AS) ) {
						alt21=1;
					}
					switch (alt21) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:513:18: KEYWORD_AS tableAlias2= tokenIdentifier
							{
							match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement5129); if (state.failed) return retval;
							pushFollow(FOLLOW_tokenIdentifier_in_selectStatement5150);
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
					break loop22;
				}
			}

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:521:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==KEYWORD_WHERE) ) {
				alt23=1;
			}
			switch (alt23) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:522:17: KEYWORD_WHERE predicate1= abstractPredicate
					{
					match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement5260); if (state.failed) return retval;
					pushFollow(FOLLOW_abstractPredicate_in_selectStatement5280);
					predicate1=abstractPredicate();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.setWherePredicate(predicate1);
					                }
					}
					break;

			}

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:530:17: ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )?
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==KEYWORD_GROUP) ) {
				alt25=1;
			}
			switch (alt25) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:531:17: KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )*
					{
					match(input,KEYWORD_GROUP,FOLLOW_KEYWORD_GROUP_in_selectStatement5387); if (state.failed) return retval;
					match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_selectStatement5389); if (state.failed) return retval;
					pushFollow(FOLLOW_abstractExpression_in_selectStatement5409);
					groupExpr1=abstractExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					                	retval.stmt.addGroupExpression(groupExpr1);
					                	++i;
					                }
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:537:17: ( COMMA groupExpr2= abstractExpression )*
					loop24:
					while (true) {
						int alt24=2;
						int LA24_0 = input.LA(1);
						if ( (LA24_0==COMMA) ) {
							alt24=1;
						}

						switch (alt24) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:538:17: COMMA groupExpr2= abstractExpression
							{
							match(input,COMMA,FOLLOW_COMMA_in_selectStatement5463); if (state.failed) return retval;
							pushFollow(FOLLOW_abstractExpression_in_selectStatement5483);
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
							break loop24;
						}
					}

					}
					break;

			}

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:548:17: ( KEYWORD_HAVING havingPred= abstractPredicate )?
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==KEYWORD_HAVING) ) {
				alt26=1;
			}
			switch (alt26) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:549:17: KEYWORD_HAVING havingPred= abstractPredicate
					{
					match(input,KEYWORD_HAVING,FOLLOW_KEYWORD_HAVING_in_selectStatement5609); if (state.failed) return retval;
					pushFollow(FOLLOW_abstractPredicate_in_selectStatement5629);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 11, selectStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "selectStatement"


	public static class loadDataInfileStatement_return extends ParserRuleReturnScope {
		public LoadDataInfileStmt stmt;
	};


	// $ANTLR start "loadDataInfileStatement"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:558:1: loadDataInfileStatement returns [LoadDataInfileStmt stmt] : ( KEYWORD_LOAD KEYWORD_DATA KEYWORD_INFILE filename1= identifierText KEYWORD_INTO KEYWORD_TABLE table1= tokenTable ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )? ) ;
	public final FunSQLParser.loadDataInfileStatement_return loadDataInfileStatement() throws RecognitionException {
		FunSQLParser.loadDataInfileStatement_return retval = new FunSQLParser.loadDataInfileStatement_return();
		retval.start = input.LT(1);
		int loadDataInfileStatement_StartIndex = input.index();

		String filename1 =null;
		TokenTable table1 =null;
		String partition1 =null;
		String partition2 =null;


		        	retval.stmt = new LoadDataInfileStmt();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:562:9: ( ( KEYWORD_LOAD KEYWORD_DATA KEYWORD_INFILE filename1= identifierText KEYWORD_INTO KEYWORD_TABLE table1= tokenTable ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )? ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:563:9: ( KEYWORD_LOAD KEYWORD_DATA KEYWORD_INFILE filename1= identifierText KEYWORD_INTO KEYWORD_TABLE table1= tokenTable ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )? )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:563:9: ( KEYWORD_LOAD KEYWORD_DATA KEYWORD_INFILE filename1= identifierText KEYWORD_INTO KEYWORD_TABLE table1= tokenTable ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )? )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:564:9: KEYWORD_LOAD KEYWORD_DATA KEYWORD_INFILE filename1= identifierText KEYWORD_INTO KEYWORD_TABLE table1= tokenTable ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )?
			{
			match(input,KEYWORD_LOAD,FOLLOW_KEYWORD_LOAD_in_loadDataInfileStatement5722); if (state.failed) return retval;
			match(input,KEYWORD_DATA,FOLLOW_KEYWORD_DATA_in_loadDataInfileStatement5732); if (state.failed) return retval;
			match(input,KEYWORD_INFILE,FOLLOW_KEYWORD_INFILE_in_loadDataInfileStatement5742); if (state.failed) return retval;
			pushFollow(FOLLOW_identifierText_in_loadDataInfileStatement5754);
			filename1=identifierText();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setTokenFilename(filename1);
			                }
			match(input,KEYWORD_INTO,FOLLOW_KEYWORD_INTO_in_loadDataInfileStatement5766); if (state.failed) return retval;
			match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_loadDataInfileStatement5776); if (state.failed) return retval;
			pushFollow(FOLLOW_tokenTable_in_loadDataInfileStatement5788);
			table1=tokenTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			                	retval.stmt.setTokenTable(table1);
			                }
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:575:9: ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )?
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==KEYWORD_PARTITION) ) {
				alt28=1;
			}
			switch (alt28) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:576:10: KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN
					{
					match(input,KEYWORD_PARTITION,FOLLOW_KEYWORD_PARTITION_in_loadDataInfileStatement5811); if (state.failed) return retval;
					match(input,LPAREN,FOLLOW_LPAREN_in_loadDataInfileStatement5822); if (state.failed) return retval;
					pushFollow(FOLLOW_identifierText_in_loadDataInfileStatement5835);
					partition1=identifierText();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					        			retval.stmt.setTokenPartition(partition1);
								}
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:581:10: ( COMMA partition2= identifierText )*
					loop27:
					while (true) {
						int alt27=2;
						int LA27_0 = input.LA(1);
						if ( (LA27_0==COMMA) ) {
							alt27=1;
						}

						switch (alt27) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:582:11: COMMA partition2= identifierText
							{
							match(input,COMMA,FOLLOW_COMMA_in_loadDataInfileStatement5860); if (state.failed) return retval;
							pushFollow(FOLLOW_identifierText_in_loadDataInfileStatement5874);
							partition2=identifierText();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) {
								        			retval.stmt.setTokenPartition(partition2);
											}
							}
							break;

						default :
							break loop27;
						}
					}

					match(input,RPAREN,FOLLOW_RPAREN_in_loadDataInfileStatement5899); if (state.failed) return retval;
					}
					break;

			}

			}

			}

			retval.stop = input.LT(-1);

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 12, loadDataInfileStatement_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "loadDataInfileStatement"



	// $ANTLR start "abstractPredicate"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:592:1: abstractPredicate returns [AbstractPredicate predicate] : predicate1= complexPredicateOr ;
	public final AbstractPredicate abstractPredicate() throws RecognitionException {
		AbstractPredicate predicate = null;

		int abstractPredicate_StartIndex = input.index();

		ComplexPredicate predicate1 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return predicate; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:593:2: (predicate1= complexPredicateOr )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:594:3: predicate1= complexPredicateOr
			{
			pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate5940);
			predicate1=complexPredicateOr();
			state._fsp--;
			if (state.failed) return predicate;
			if ( state.backtracking==0 ) {
						predicate = predicate1;
					}
			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 13, abstractPredicate_StartIndex); }

		}
		return predicate;
	}
	// $ANTLR end "abstractPredicate"



	// $ANTLR start "complexPredicateOr"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:599:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
	public final ComplexPredicate complexPredicateOr() throws RecognitionException {
		ComplexPredicate predicateOr = null;

		int complexPredicateOr_StartIndex = input.index();

		ComplexPredicate predicate1 =null;
		ComplexPredicate predicate2 =null;


		        	predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return predicateOr; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:603:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:604:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:604:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:605:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
			{
			pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr5978);
			predicate1=complexPredicateAnd();
			state._fsp--;
			if (state.failed) return predicateOr;
			if ( state.backtracking==0 ) {
						predicateOr.setPredicate1(predicate1);
					}
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:608:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
			loop29:
			while (true) {
				int alt29=2;
				int LA29_0 = input.LA(1);
				if ( (LA29_0==KEYWORD_OR) ) {
					alt29=1;
				}

				switch (alt29) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:609:4: KEYWORD_OR predicate2= complexPredicateAnd
					{
					match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr5989); if (state.failed) return predicateOr;
					if ( state.backtracking==0 ) {
									predicateOr.addOr();
								}
					pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr5997);
					predicate2=complexPredicateAnd();
					state._fsp--;
					if (state.failed) return predicateOr;
					if ( state.backtracking==0 ) {
									predicateOr.addPredicate2(predicate2);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 14, complexPredicateOr_StartIndex); }

		}
		return predicateOr;
	}
	// $ANTLR end "complexPredicateOr"



	// $ANTLR start "complexPredicateAnd"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:619:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
	public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
		ComplexPredicate predicateAnd = null;

		int complexPredicateAnd_StartIndex = input.index();

		ComplexPredicate predicate1 =null;
		ComplexPredicate predicate2 =null;


		        	predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return predicateAnd; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:623:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:624:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:624:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:625:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
			{
			pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd6036);
			predicate1=complexPredicateNot();
			state._fsp--;
			if (state.failed) return predicateAnd;
			if ( state.backtracking==0 ) {
						predicateAnd.setPredicate1(predicate1);
					}
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:628:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
			loop30:
			while (true) {
				int alt30=2;
				int LA30_0 = input.LA(1);
				if ( (LA30_0==KEYWORD_AND) ) {
					alt30=1;
				}

				switch (alt30) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:629:4: KEYWORD_AND predicate2= complexPredicateNot
					{
					match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd6047); if (state.failed) return predicateAnd;
					if ( state.backtracking==0 ) {
									predicateAnd.addAnd();
								}
					pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd6055);
					predicate2=complexPredicateNot();
					state._fsp--;
					if (state.failed) return predicateAnd;
					if ( state.backtracking==0 ) {
									predicateAnd.addPredicate2(predicate2);
								}
					}
					break;

				default :
					break loop30;
				}
			}

			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 15, complexPredicateAnd_StartIndex); }

		}
		return predicateAnd;
	}
	// $ANTLR end "complexPredicateAnd"



	// $ANTLR start "complexPredicateNot"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:639:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
	public final ComplexPredicate complexPredicateNot() throws RecognitionException {
		ComplexPredicate predicateNot = null;

		int complexPredicateNot_StartIndex = input.index();

		AbstractPredicate predicate1 =null;


		        	predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return predicateNot; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:643:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:644:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:644:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:645:3: ( KEYWORD_NOT )? predicate1= complexPredicate
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:645:3: ( KEYWORD_NOT )?
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==KEYWORD_NOT) ) {
				alt31=1;
			}
			switch (alt31) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:646:3: KEYWORD_NOT
					{
					match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot6096); if (state.failed) return predicateNot;
					if ( state.backtracking==0 ) {
								predicateNot.negate();
							}
					}
					break;

			}

			pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot6109);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 16, complexPredicateNot_StartIndex); }

		}
		return predicateNot;
	}
	// $ANTLR end "complexPredicateNot"



	// $ANTLR start "complexPredicate"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:656:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
	public final AbstractPredicate complexPredicate() throws RecognitionException {
		AbstractPredicate predicate = null;

		int complexPredicate_StartIndex = input.index();

		AbstractPredicate predicate1 =null;
		SimplePredicate predicate2 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return predicate; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:657:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:658:2: (predicate1= parenPredicate |predicate2= simplePredicate )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:658:2: (predicate1= parenPredicate |predicate2= simplePredicate )
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==LPAREN) ) {
				int LA32_1 = input.LA(2);
				if ( (synpred42_FunSQL()) ) {
					alt32=1;
				}
				else if ( (true) ) {
					alt32=2;
				}

			}
			else if ( (LA32_0==FUNCTION_AGGREGATION||LA32_0==IDENTIFIER||(LA32_0 >= LITERAL_DECIMAL && LA32_0 <= LITERAL_STRING)||LA32_0==MINUS||LA32_0==PLUS||LA32_0==QUOTE_DOUBLE||LA32_0==TYPE_DATE) ) {
				alt32=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return predicate;}
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}

			switch (alt32) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:659:3: predicate1= parenPredicate
					{
					pushFollow(FOLLOW_parenPredicate_in_complexPredicate6136);
					predicate1=parenPredicate();
					state._fsp--;
					if (state.failed) return predicate;
					if ( state.backtracking==0 ) {
								predicate = predicate1;
							}
					}
					break;
				case 2 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:664:3: predicate2= simplePredicate
					{
					pushFollow(FOLLOW_simplePredicate_in_complexPredicate6151);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 17, complexPredicate_StartIndex); }

		}
		return predicate;
	}
	// $ANTLR end "complexPredicate"



	// $ANTLR start "parenPredicate"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:671:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
	public final AbstractPredicate parenPredicate() throws RecognitionException {
		AbstractPredicate predicate = null;

		int parenPredicate_StartIndex = input.index();

		AbstractPredicate predicate1 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return predicate; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:672:2: ( LPAREN predicate1= abstractPredicate RPAREN )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:673:3: LPAREN predicate1= abstractPredicate RPAREN
			{
			match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate6176); if (state.failed) return predicate;
			pushFollow(FOLLOW_abstractPredicate_in_parenPredicate6183);
			predicate1=abstractPredicate();
			state._fsp--;
			if (state.failed) return predicate;
			if ( state.backtracking==0 ) {
						predicate = predicate1;
					}
			match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate6189); if (state.failed) return predicate;
			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 18, parenPredicate_StartIndex); }

		}
		return predicate;
	}
	// $ANTLR end "parenPredicate"



	// $ANTLR start "simplePredicate"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:680:1: simplePredicate returns [SimplePredicate predicate] : (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) ;
	public final SimplePredicate simplePredicate() throws RecognitionException {
		SimplePredicate predicate = null;

		int simplePredicate_StartIndex = input.index();

		AbstractExpression expr1 =null;
		ParserRuleReturnScope comp =null;
		AbstractExpression expr2 =null;


		        	predicate = new SimplePredicate();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return predicate; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:684:9: ( (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:685:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:685:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:686:3: expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression
			{
			pushFollow(FOLLOW_abstractExpression_in_simplePredicate6231);
			expr1=abstractExpression();
			state._fsp--;
			if (state.failed) return predicate;
			if ( state.backtracking==0 ) {
			                	predicate.setExpr1(expr1);
			                }
			pushFollow(FOLLOW_tokenCompOperator_in_simplePredicate6286);
			comp=tokenCompOperator();
			state._fsp--;
			if (state.failed) return predicate;
			if ( state.backtracking==0 ) {
			                	predicate.setComp(EnumCompOperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
			                }
			pushFollow(FOLLOW_abstractExpression_in_simplePredicate6324);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 19, simplePredicate_StartIndex); }

		}
		return predicate;
	}
	// $ANTLR end "simplePredicate"



	// $ANTLR start "abstractExpression"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:703:1: abstractExpression returns [AbstractExpression expression] : expression1= complexExpressionAdd ;
	public final AbstractExpression abstractExpression() throws RecognitionException {
		AbstractExpression expression = null;

		int abstractExpression_StartIndex = input.index();

		ComplexExpression expression1 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expression; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:704:2: (expression1= complexExpressionAdd )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:705:2: expression1= complexExpressionAdd
			{
			pushFollow(FOLLOW_complexExpressionAdd_in_abstractExpression6378);
			expression1=complexExpressionAdd();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
					expression = expression1;
				}
			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 20, abstractExpression_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "abstractExpression"



	// $ANTLR start "complexExpressionAdd"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:710:1: complexExpressionAdd returns [ComplexExpression expression] : (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) ;
	public final ComplexExpression complexExpressionAdd() throws RecognitionException {
		ComplexExpression expression = null;

		int complexExpressionAdd_StartIndex = input.index();

		ComplexExpression expression1 =null;
		ParserRuleReturnScope op1 =null;
		ComplexExpression expression2 =null;


		        	expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expression; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:714:2: ( (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:715:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:715:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:716:3: expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )*
			{
			pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd6408);
			expression1=complexExpressionMult();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression.setExpr1(expression1);
					}
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:719:3: (op1= tokenAddOperator expression2= complexExpressionMult )*
			loop33:
			while (true) {
				int alt33=2;
				int LA33_0 = input.LA(1);
				if ( (LA33_0==MINUS||LA33_0==PLUS) ) {
					alt33=1;
				}

				switch (alt33) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:720:4: op1= tokenAddOperator expression2= complexExpressionMult
					{
					pushFollow(FOLLOW_tokenAddOperator_in_complexExpressionAdd6421);
					op1=tokenAddOperator();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
					                		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
					                	}
					pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd6429);
					expression2=complexExpressionMult();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
									expression.addExpr2(expression2);
								}
					}
					break;

				default :
					break loop33;
				}
			}

			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 21, complexExpressionAdd_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "complexExpressionAdd"



	// $ANTLR start "complexExpressionMult"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:730:1: complexExpressionMult returns [ComplexExpression expression] : (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) ;
	public final ComplexExpression complexExpressionMult() throws RecognitionException {
		ComplexExpression expression = null;

		int complexExpressionMult_StartIndex = input.index();

		ComplexExpression expression1 =null;
		ParserRuleReturnScope op1 =null;
		ComplexExpression expression2 =null;


		        	expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expression; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:734:2: ( (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:735:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:735:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:736:3: expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )*
			{
			pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult6469);
			expression1=complexExpressionSigned();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression.setExpr1(expression1);
					}
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:739:3: (op1= tokenMultOperator expression2= complexExpressionSigned )*
			loop34:
			while (true) {
				int alt34=2;
				int LA34_0 = input.LA(1);
				if ( (LA34_0==DIV||LA34_0==MULT) ) {
					alt34=1;
				}

				switch (alt34) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:740:4: op1= tokenMultOperator expression2= complexExpressionSigned
					{
					pushFollow(FOLLOW_tokenMultOperator_in_complexExpressionMult6482);
					op1=tokenMultOperator();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
					                		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
					                	}
					pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult6490);
					expression2=complexExpressionSigned();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
									expression.addExpr2(expression2);
								}
					}
					break;

				default :
					break loop34;
				}
			}

			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 22, complexExpressionMult_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "complexExpressionMult"



	// $ANTLR start "complexExpressionSigned"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:751:1: complexExpressionSigned returns [ComplexExpression expression] : ( ( MINUS | PLUS )? expression1= complexExpression ) ;
	public final ComplexExpression complexExpressionSigned() throws RecognitionException {
		ComplexExpression expression = null;

		int complexExpressionSigned_StartIndex = input.index();

		AbstractExpression expression1 =null;


		        	expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expression; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:755:2: ( ( ( MINUS | PLUS )? expression1= complexExpression ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:756:2: ( ( MINUS | PLUS )? expression1= complexExpression )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:756:2: ( ( MINUS | PLUS )? expression1= complexExpression )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:757:3: ( MINUS | PLUS )? expression1= complexExpression
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:757:3: ( MINUS | PLUS )?
			int alt35=3;
			int LA35_0 = input.LA(1);
			if ( (LA35_0==MINUS) ) {
				alt35=1;
			}
			else if ( (LA35_0==PLUS) ) {
				alt35=2;
			}
			switch (alt35) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:758:3: MINUS
					{
					match(input,MINUS,FOLLOW_MINUS_in_complexExpressionSigned6532); if (state.failed) return expression;
					if ( state.backtracking==0 ) {
								expression.negate();
							}
					}
					break;
				case 2 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:762:3: PLUS
					{
					match(input,PLUS,FOLLOW_PLUS_in_complexExpressionSigned6541); if (state.failed) return expression;
					}
					break;

			}

			pushFollow(FOLLOW_complexExpression_in_complexExpressionSigned6553);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 23, complexExpressionSigned_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "complexExpressionSigned"



	// $ANTLR start "complexExpression"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:770:1: complexExpression returns [AbstractExpression expression] : (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) ;
	public final AbstractExpression complexExpression() throws RecognitionException {
		AbstractExpression expression = null;

		int complexExpression_StartIndex = input.index();

		AbstractExpression expression1 =null;
		AggregationExpression expression2 =null;
		SimpleExpression expression3 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return expression; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:771:2: ( (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:772:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:772:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
			int alt36=3;
			switch ( input.LA(1) ) {
			case LPAREN:
				{
				alt36=1;
				}
				break;
			case FUNCTION_AGGREGATION:
				{
				alt36=2;
				}
				break;
			case IDENTIFIER:
			case LITERAL_DECIMAL:
			case LITERAL_INTEGER:
			case LITERAL_STRING:
			case QUOTE_DOUBLE:
			case TYPE_DATE:
				{
				alt36=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return expression;}
				NoViableAltException nvae =
					new NoViableAltException("", 36, 0, input);
				throw nvae;
			}
			switch (alt36) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:773:3: expression1= parenExpression
					{
					pushFollow(FOLLOW_parenExpression_in_complexExpression6588);
					expression1=parenExpression();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
								expression = expression1;
							}
					}
					break;
				case 2 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:778:3: expression2= aggregationExpression
					{
					pushFollow(FOLLOW_aggregationExpression_in_complexExpression6603);
					expression2=aggregationExpression();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
								expression = expression2;
							}
					}
					break;
				case 3 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:783:3: expression3= simpleExpression
					{
					pushFollow(FOLLOW_simpleExpression_in_complexExpression6618);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 24, complexExpression_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "complexExpression"



	// $ANTLR start "parenExpression"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:790:1: parenExpression returns [AbstractExpression expression] : LPAREN expression1= abstractExpression RPAREN ;
	public final AbstractExpression parenExpression() throws RecognitionException {
		AbstractExpression expression = null;

		int parenExpression_StartIndex = input.index();

		AbstractExpression expression1 =null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return expression; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:791:2: ( LPAREN expression1= abstractExpression RPAREN )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:792:3: LPAREN expression1= abstractExpression RPAREN
			{
			match(input,LPAREN,FOLLOW_LPAREN_in_parenExpression6643); if (state.failed) return expression;
			pushFollow(FOLLOW_abstractExpression_in_parenExpression6650);
			expression1=abstractExpression();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression = expression1;
					}
			match(input,RPAREN,FOLLOW_RPAREN_in_parenExpression6656); if (state.failed) return expression;
			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 25, parenExpression_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "parenExpression"



	// $ANTLR start "aggregationExpression"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:800:1: aggregationExpression returns [AggregationExpression expression] : (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) ;
	public final AggregationExpression aggregationExpression() throws RecognitionException {
		AggregationExpression expression = null;

		int aggregationExpression_StartIndex = input.index();

		Token agg1=null;
		AbstractExpression expr1 =null;


		        	expression = new AggregationExpression();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return expression; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:804:9: ( (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:805:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:805:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:806:3: agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN
			{
			agg1=(Token)match(input,FUNCTION_AGGREGATION,FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression6708); if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression.setAggregation((agg1!=null?agg1.getText():null).toUpperCase());
					}
			match(input,LPAREN,FOLLOW_LPAREN_in_aggregationExpression6716); if (state.failed) return expression;
			pushFollow(FOLLOW_abstractExpression_in_aggregationExpression6723);
			expr1=abstractExpression();
			state._fsp--;
			if (state.failed) return expression;
			if ( state.backtracking==0 ) {
						expression.setExpression(expr1);
					}
			match(input,RPAREN,FOLLOW_RPAREN_in_aggregationExpression6729); if (state.failed) return expression;
			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 26, aggregationExpression_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "aggregationExpression"



	// $ANTLR start "simpleExpression"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:818:1: simpleExpression returns [SimpleExpression expression] : ( (att1= tokenAttribute |lit1= tokenLiteral ) ) ;
	public final SimpleExpression simpleExpression() throws RecognitionException {
		SimpleExpression expression = null;

		int simpleExpression_StartIndex = input.index();

		TokenAttribute att1 =null;
		TokenLiteral lit1 =null;


		        	expression = new SimpleExpression();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return expression; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:822:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:823:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:823:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:824:3: (att1= tokenAttribute |lit1= tokenLiteral )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:824:3: (att1= tokenAttribute |lit1= tokenLiteral )
			int alt37=2;
			int LA37_0 = input.LA(1);
			if ( (LA37_0==IDENTIFIER||LA37_0==QUOTE_DOUBLE) ) {
				alt37=1;
			}
			else if ( ((LA37_0 >= LITERAL_DECIMAL && LA37_0 <= LITERAL_STRING)||LA37_0==TYPE_DATE) ) {
				alt37=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return expression;}
				NoViableAltException nvae =
					new NoViableAltException("", 37, 0, input);
				throw nvae;
			}

			switch (alt37) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:825:3: att1= tokenAttribute
					{
					pushFollow(FOLLOW_tokenAttribute_in_simpleExpression6804);
					att1=tokenAttribute();
					state._fsp--;
					if (state.failed) return expression;
					if ( state.backtracking==0 ) {
					                	expression.setOper(att1);
					                }
					}
					break;
				case 2 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:830:17: lit1= tokenLiteral
					{
					pushFollow(FOLLOW_tokenLiteral_in_simpleExpression6860);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 27, simpleExpression_StartIndex); }

		}
		return expression;
	}
	// $ANTLR end "simpleExpression"



	// $ANTLR start "tokenAttribute"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:838:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
	public final TokenAttribute tokenAttribute() throws RecognitionException {
		TokenAttribute attribute = null;

		int tokenAttribute_StartIndex = input.index();

		TokenIdentifier table1 =null;
		TokenIdentifier id1 =null;


		        	attribute = new TokenAttribute();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return attribute; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:842:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:843:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:843:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:844:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:844:17: (table1= tokenIdentifier DOT )?
			int alt38=2;
			alt38 = dfa38.predict(input);
			switch (alt38) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:845:17: table1= tokenIdentifier DOT
					{
					pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute6988);
					table1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return attribute;
					if ( state.backtracking==0 ) {
					                	TokenTable table = new TokenTable();
					                	table.setName(table1);
					                	attribute.setTable(table);
					                }
					match(input,DOT,FOLLOW_DOT_in_tokenAttribute7008); if (state.failed) return attribute;
					}
					break;

			}

			pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute7047);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 28, tokenAttribute_StartIndex); }

		}
		return attribute;
	}
	// $ANTLR end "tokenAttribute"



	// $ANTLR start "tokenTable"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:858:1: tokenTable returns [TokenTable table] : ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) );
	public final TokenTable tokenTable() throws RecognitionException {
		TokenTable table = null;

		int tokenTable_StartIndex = input.index();

		TokenIdentifier schema1 =null;
		TokenIdentifier id1 =null;


		        	table = new TokenTable();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return table; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:862:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) )
			int alt41=2;
			alt41 = dfa41.predict(input);
			switch (alt41) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:863:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:863:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:864:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:864:17: (schema1= tokenIdentifier DOT )?
					int alt39=2;
					alt39 = dfa39.predict(input);
					switch (alt39) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:865:17: schema1= tokenIdentifier DOT
							{
							pushFollow(FOLLOW_tokenIdentifier_in_tokenTable7170);
							schema1=tokenIdentifier();
							state._fsp--;
							if (state.failed) return table;
							if ( state.backtracking==0 ) {
							                        TokenSchema schema = new TokenSchema();
							                	table.setSchema(schema);
							                	table.setVariable(false);
							                }
							match(input,DOT,FOLLOW_DOT_in_tokenTable7190); if (state.failed) return table;
							}
							break;

					}

					pushFollow(FOLLOW_tokenIdentifier_in_tokenTable7247);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:875:12: ( ( COLON )? id1= tokenIdentifier )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:875:12: ( ( COLON )? id1= tokenIdentifier )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:876:17: ( COLON )? id1= tokenIdentifier
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:876:17: ( COLON )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==COLON) ) {
						alt40=1;
					}
					switch (alt40) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:877:17: COLON
							{
							match(input,COLON,FOLLOW_COLON_in_tokenTable7314); if (state.failed) return table;
							}
							break;

					}

					pushFollow(FOLLOW_tokenIdentifier_in_tokenTable7356);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 29, tokenTable_StartIndex); }

		}
		return table;
	}
	// $ANTLR end "tokenTable"



	// $ANTLR start "tokenSchema"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:886:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
	public final TokenSchema tokenSchema() throws RecognitionException {
		TokenSchema schema = null;

		int tokenSchema_StartIndex = input.index();

		TokenIdentifier tokenIdentifier16 =null;


		        	schema = new TokenSchema();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return schema; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:890:9: ( ( tokenIdentifier ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:891:9: ( tokenIdentifier )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:891:9: ( tokenIdentifier )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:892:17: tokenIdentifier
			{
			pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema7437);
			tokenIdentifier16=tokenIdentifier();
			state._fsp--;
			if (state.failed) return schema;
			if ( state.backtracking==0 ) {
			                	schema.setName(tokenIdentifier16);
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 30, tokenSchema_StartIndex); }

		}
		return schema;
	}
	// $ANTLR end "tokenSchema"



	// $ANTLR start "tokenFunction"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:898:1: tokenFunction returns [TokenFunction function] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
	public final TokenFunction tokenFunction() throws RecognitionException {
		TokenFunction function = null;

		int tokenFunction_StartIndex = input.index();

		TokenIdentifier schema1 =null;
		TokenIdentifier id1 =null;


		        	function = new TokenFunction();
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return function; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:902:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:903:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:903:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:904:33: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:904:33: (schema1= tokenIdentifier DOT )?
			int alt42=2;
			alt42 = dfa42.predict(input);
			switch (alt42) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:905:17: schema1= tokenIdentifier DOT
					{
					pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction7553);
					schema1=tokenIdentifier();
					state._fsp--;
					if (state.failed) return function;
					if ( state.backtracking==0 ) {
					                        TokenSchema schema = new TokenSchema();
					                	function.setSchema(schema);
					                }
					match(input,DOT,FOLLOW_DOT_in_tokenFunction7573); if (state.failed) return function;
					}
					break;

			}

			pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction7612);
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 31, tokenFunction_StartIndex); }

		}
		return function;
	}
	// $ANTLR end "tokenFunction"



	// $ANTLR start "tokenVariable"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:917:1: tokenVariable returns [TokenVariable variable] : ( variableText ) ;
	public final TokenVariable tokenVariable() throws RecognitionException {
		TokenVariable variable = null;

		int tokenVariable_StartIndex = input.index();

		String variableText17 =null;


		        	variable = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return variable; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:921:9: ( ( variableText ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:922:9: ( variableText )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:922:9: ( variableText )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:923:17: variableText
			{
			pushFollow(FOLLOW_variableText_in_tokenVariable7692);
			variableText17=variableText();
			state._fsp--;
			if (state.failed) return variable;
			if ( state.backtracking==0 ) {
			                variable = new TokenVariable(variableText17);	
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 32, tokenVariable_StartIndex); }

		}
		return variable;
	}
	// $ANTLR end "tokenVariable"



	// $ANTLR start "tokenAssignment"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:929:1: tokenAssignment returns [TokenAssignment ass] : ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI ;
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
			if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return ass; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:933:3: ( ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:934:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:934:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable )
			int alt43=3;
			int LA43_0 = input.LA(1);
			if ( (LA43_0==COLON) ) {
				alt43=1;
			}
			else if ( (LA43_0==KEYWORD_VAR) ) {
				int LA43_2 = input.LA(2);
				if ( (LA43_2==IDENTIFIER) ) {
					int LA43_3 = input.LA(3);
					if ( (LA43_3==EQUAL1) ) {
						int LA43_4 = input.LA(4);
						if ( (LA43_4==COLON) ) {
							alt43=3;
						}
						else if ( (LA43_4==KEYWORD_SELECT) ) {
							alt43=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return ass;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 43, 4, input);
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
								new NoViableAltException("", 43, 3, input);
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
							new NoViableAltException("", 43, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return ass;}
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}

			switch (alt43) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:935:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:935:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:936:4: COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement
					{
					match(input,COLON,FOLLOW_COLON_in_tokenAssignment7759); if (state.failed) return ass;
					pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7771);
					var1=tokenVariable();
					state._fsp--;
					if (state.failed) return ass;
					if ( state.backtracking==0 ) {
							 ass.setReference(true);
							 ass.setVar(var1);
							 }
					match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7777); if (state.failed) return ass;
					pushFollow(FOLLOW_selectStatement_in_tokenAssignment7784);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:947:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:947:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:948:4: KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement
					{
					match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment7805); if (state.failed) return ass;
					pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7814);
					var2=tokenVariable();
					state._fsp--;
					if (state.failed) return ass;
					if ( state.backtracking==0 ) {		 
							 ass.setReference(false);
							 ass.setVar(var2);
							 }
					match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7820); if (state.failed) return ass;
					pushFollow(FOLLOW_selectStatement_in_tokenAssignment7827);
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
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:959:4: ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:959:4: ( KEYWORD_VAR var3= tokenVariable EQUAL1 )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:960:4: KEYWORD_VAR var3= tokenVariable EQUAL1
					{
					match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment7851); if (state.failed) return ass;
					pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7860);
					var3=tokenVariable();
					state._fsp--;
					if (state.failed) return ass;
					if ( state.backtracking==0 ) {		 
							 ass.setReference(false);
							 ass.setVar(var3);
							 }
					match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7866); if (state.failed) return ass;
					}

					match(input,COLON,FOLLOW_COLON_in_tokenAssignment7877); if (state.failed) return ass;
					pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7889);
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

			match(input,SEMI,FOLLOW_SEMI_in_tokenAssignment7900); if (state.failed) return ass;
			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 33, tokenAssignment_StartIndex); }

		}
		return ass;
	}
	// $ANTLR end "tokenAssignment"



	// $ANTLR start "tokenFunctionCall"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:976:1: tokenFunctionCall returns [TokenFunctionCall call] : ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI ) ;
	public final TokenFunctionCall tokenFunctionCall() throws RecognitionException {
		TokenFunctionCall call = null;

		int tokenFunctionCall_StartIndex = input.index();

		TokenFunction fun1 =null;
		TokenVariable var1 =null;
		TokenVariable var2 =null;
		TokenVariable var3 =null;


			 	call =new TokenFunctionCall();
			 
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return call; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:980:3: ( ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:981:3: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:981:3: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:982:4: KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI
			{
			match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_tokenFunctionCall7932); if (state.failed) return call;
			match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_tokenFunctionCall7937); if (state.failed) return call;
			pushFollow(FOLLOW_tokenFunction_in_tokenFunctionCall7944);
			fun1=tokenFunction();
			state._fsp--;
			if (state.failed) return call;
			if ( state.backtracking==0 ) {
					 call.setFun(fun1);
					 }
			match(input,LPAREN,FOLLOW_LPAREN_in_tokenFunctionCall7950); if (state.failed) return call;
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:988:4: ( COLON var1= tokenVariable )*
			loop44:
			while (true) {
				int alt44=2;
				int LA44_0 = input.LA(1);
				if ( (LA44_0==COLON) ) {
					alt44=1;
				}

				switch (alt44) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:989:5: COLON var1= tokenVariable
					{
					match(input,COLON,FOLLOW_COLON_in_tokenFunctionCall7961); if (state.failed) return call;
					pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall7974);
					var1=tokenVariable();
					state._fsp--;
					if (state.failed) return call;
					if ( state.backtracking==0 ) {
								 call.addInVar(var1);
								 }
					}
					break;

				default :
					break loop44;
				}
			}

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:994:4: ( KEYWORD_VAR var2= tokenVariable COMMA )*
			loop45:
			while (true) {
				int alt45=2;
				int LA45_0 = input.LA(1);
				if ( (LA45_0==KEYWORD_VAR) ) {
					int LA45_1 = input.LA(2);
					if ( (LA45_1==IDENTIFIER) ) {
						int LA45_2 = input.LA(3);
						if ( (LA45_2==COMMA) ) {
							alt45=1;
						}

					}

				}

				switch (alt45) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:995:5: KEYWORD_VAR var2= tokenVariable COMMA
					{
					match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7995); if (state.failed) return call;
					pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall8005);
					var2=tokenVariable();
					state._fsp--;
					if (state.failed) return call;
					if ( state.backtracking==0 ) {		
								 call.addOutVar(var2);
								 }
					match(input,COMMA,FOLLOW_COMMA_in_tokenFunctionCall8012); if (state.failed) return call;
					}
					break;

				default :
					break loop45;
				}
			}

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1001:4: ( KEYWORD_VAR var3= tokenVariable )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1002:5: KEYWORD_VAR var3= tokenVariable
			{
			match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenFunctionCall8034); if (state.failed) return call;
			pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall8044);
			var3=tokenVariable();
			state._fsp--;
			if (state.failed) return call;
			if ( state.backtracking==0 ) {		
						 call.addOutVar(var3);
						 }
			}

			match(input,RPAREN,FOLLOW_RPAREN_in_tokenFunctionCall8055); if (state.failed) return call;
			match(input,SEMI,FOLLOW_SEMI_in_tokenFunctionCall8060); if (state.failed) return call;
			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 34, tokenFunctionCall_StartIndex); }

		}
		return call;
	}
	// $ANTLR end "tokenFunctionCall"



	// $ANTLR start "tokenIdentifier"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1012:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
	public final TokenIdentifier tokenIdentifier() throws RecognitionException {
		TokenIdentifier identifier = null;

		int tokenIdentifier_StartIndex = input.index();

		String identifierText18 =null;


		        	identifier = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return identifier; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1016:9: ( ( identifierText ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1017:9: ( identifierText )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1017:9: ( identifierText )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1018:17: identifierText
			{
			pushFollow(FOLLOW_identifierText_in_tokenIdentifier8128);
			identifierText18=identifierText();
			state._fsp--;
			if (state.failed) return identifier;
			if ( state.backtracking==0 ) {
			                	identifier = new TokenIdentifier(identifierText18);
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 35, tokenIdentifier_StartIndex); }

		}
		return identifier;
	}
	// $ANTLR end "tokenIdentifier"



	// $ANTLR start "tokenDataType"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1025:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) ;
	public final TokenDataType tokenDataType() throws RecognitionException {
		TokenDataType dataType = null;

		int tokenDataType_StartIndex = input.index();

		Token TYPE_VARCHAR19=null;
		Token TYPE_INTEGER20=null;
		Token TYPE_DECIMAL21=null;
		Token TYPE_DATE22=null;


		        	dataType = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return dataType; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1029:9: ( ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1030:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1030:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
			int alt46=4;
			switch ( input.LA(1) ) {
			case TYPE_VARCHAR:
				{
				alt46=1;
				}
				break;
			case TYPE_INTEGER:
				{
				alt46=2;
				}
				break;
			case TYPE_DECIMAL:
				{
				alt46=3;
				}
				break;
			case TYPE_DATE:
				{
				alt46=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return dataType;}
				NoViableAltException nvae =
					new NoViableAltException("", 46, 0, input);
				throw nvae;
			}
			switch (alt46) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1031:17: TYPE_VARCHAR
					{
					TYPE_VARCHAR19=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType8209); if (state.failed) return dataType;
					if ( state.backtracking==0 ) {
					                	dataType = new TokenDataType((TYPE_VARCHAR19!=null?TYPE_VARCHAR19.getText():null));
					                }
					}
					break;
				case 2 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1035:17: TYPE_INTEGER
					{
					TYPE_INTEGER20=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType8247); if (state.failed) return dataType;
					if ( state.backtracking==0 ) {
					                	dataType = new TokenDataType((TYPE_INTEGER20!=null?TYPE_INTEGER20.getText():null));
					                }
					}
					break;
				case 3 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1039:17: TYPE_DECIMAL
					{
					TYPE_DECIMAL21=(Token)match(input,TYPE_DECIMAL,FOLLOW_TYPE_DECIMAL_in_tokenDataType8285); if (state.failed) return dataType;
					if ( state.backtracking==0 ) {
					                	dataType = new TokenDataType((TYPE_DECIMAL21!=null?TYPE_DECIMAL21.getText():null));
					                }
					}
					break;
				case 4 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1043:17: TYPE_DATE
					{
					TYPE_DATE22=(Token)match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDataType8323); if (state.failed) return dataType;
					if ( state.backtracking==0 ) {
					                	dataType = new TokenDataType((TYPE_DATE22!=null?TYPE_DATE22.getText():null));
					                }
					}
					break;

			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 36, tokenDataType_StartIndex); }

		}
		return dataType;
	}
	// $ANTLR end "tokenDataType"



	// $ANTLR start "tokenLiteral"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1049:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
	public final TokenLiteral tokenLiteral() throws RecognitionException {
		TokenLiteral literal = null;

		int tokenLiteral_StartIndex = input.index();

		TokenIntegerLiteral tokenIntegerLiteral23 =null;
		TokenStringLiteral tokenStringLiteral24 =null;
		TokenDecimalLiteral tokenDecimalLiteral25 =null;
		TokenDateLiteral tokenDateLiteral26 =null;


		        	literal = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return literal; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1053:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1054:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1054:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1055:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1055:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
			int alt47=4;
			switch ( input.LA(1) ) {
			case LITERAL_INTEGER:
				{
				alt47=1;
				}
				break;
			case LITERAL_STRING:
				{
				alt47=2;
				}
				break;
			case LITERAL_DECIMAL:
				{
				alt47=3;
				}
				break;
			case TYPE_DATE:
				{
				alt47=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return literal;}
				NoViableAltException nvae =
					new NoViableAltException("", 47, 0, input);
				throw nvae;
			}
			switch (alt47) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1056:17: tokenIntegerLiteral
					{
					pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral8414);
					tokenIntegerLiteral23=tokenIntegerLiteral();
					state._fsp--;
					if (state.failed) return literal;
					if ( state.backtracking==0 ) {
					                	literal = tokenIntegerLiteral23;
					                }
					}
					break;
				case 2 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1060:17: tokenStringLiteral
					{
					pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral8452);
					tokenStringLiteral24=tokenStringLiteral();
					state._fsp--;
					if (state.failed) return literal;
					if ( state.backtracking==0 ) {
					                	literal = tokenStringLiteral24;
					                }
					}
					break;
				case 3 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1064:17: tokenDecimalLiteral
					{
					pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral8490);
					tokenDecimalLiteral25=tokenDecimalLiteral();
					state._fsp--;
					if (state.failed) return literal;
					if ( state.backtracking==0 ) {
					                	literal = tokenDecimalLiteral25;
					                }
					}
					break;
				case 4 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1068:17: tokenDateLiteral
					{
					pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral8528);
					tokenDateLiteral26=tokenDateLiteral();
					state._fsp--;
					if (state.failed) return literal;
					if ( state.backtracking==0 ) {
					                	literal = tokenDateLiteral26;
					                }
					}
					break;

			}

			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 37, tokenLiteral_StartIndex); }

		}
		return literal;
	}
	// $ANTLR end "tokenLiteral"



	// $ANTLR start "tokenStringLiteral"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1075:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
	public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
		TokenStringLiteral literal = null;

		int tokenStringLiteral_StartIndex = input.index();

		Token lit1=null;


		        	literal = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return literal; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1079:9: ( (lit1= LITERAL_STRING ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1080:9: (lit1= LITERAL_STRING )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1080:9: (lit1= LITERAL_STRING )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1081:17: lit1= LITERAL_STRING
			{
			lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral8628); if (state.failed) return literal;
			if ( state.backtracking==0 ) {
			                	literal = new TokenStringLiteral((lit1!=null?lit1.getText():null).substring(1, (lit1!=null?lit1.getText():null).length()-1));
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 38, tokenStringLiteral_StartIndex); }

		}
		return literal;
	}
	// $ANTLR end "tokenStringLiteral"



	// $ANTLR start "tokenIntegerLiteral"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1087:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
	public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
		TokenIntegerLiteral literal = null;

		int tokenIntegerLiteral_StartIndex = input.index();

		Token LITERAL_INTEGER27=null;


		        	literal = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return literal; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1091:9: ( ( LITERAL_INTEGER ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1092:9: ( LITERAL_INTEGER )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1092:9: ( LITERAL_INTEGER )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1093:17: LITERAL_INTEGER
			{
			LITERAL_INTEGER27=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral8708); if (state.failed) return literal;
			if ( state.backtracking==0 ) {
			                	literal = new TokenIntegerLiteral((LITERAL_INTEGER27!=null?LITERAL_INTEGER27.getText():null));
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 39, tokenIntegerLiteral_StartIndex); }

		}
		return literal;
	}
	// $ANTLR end "tokenIntegerLiteral"



	// $ANTLR start "tokenDecimalLiteral"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1100:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
	public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
		TokenDecimalLiteral literal = null;

		int tokenDecimalLiteral_StartIndex = input.index();

		Token LITERAL_DECIMAL28=null;


		        	literal = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return literal; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1104:9: ( ( LITERAL_DECIMAL ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1105:9: ( LITERAL_DECIMAL )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1105:9: ( LITERAL_DECIMAL )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1106:17: LITERAL_DECIMAL
			{
			LITERAL_DECIMAL28=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral8798); if (state.failed) return literal;
			if ( state.backtracking==0 ) {
			                	literal = new TokenDecimalLiteral((LITERAL_DECIMAL28!=null?LITERAL_DECIMAL28.getText():null));
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 40, tokenDecimalLiteral_StartIndex); }

		}
		return literal;
	}
	// $ANTLR end "tokenDecimalLiteral"



	// $ANTLR start "tokenDateLiteral"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1112:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( TYPE_DATE LITERAL_STRING ) ;
	public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
		TokenDateLiteral literal = null;

		int tokenDateLiteral_StartIndex = input.index();

		Token LITERAL_STRING29=null;


		        	literal = null;
		        
		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return literal; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1116:9: ( ( TYPE_DATE LITERAL_STRING ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1117:9: ( TYPE_DATE LITERAL_STRING )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1117:9: ( TYPE_DATE LITERAL_STRING )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1119:17: TYPE_DATE LITERAL_STRING
			{
			match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDateLiteral8896); if (state.failed) return literal;
			LITERAL_STRING29=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenDateLiteral8914); if (state.failed) return literal;
			if ( state.backtracking==0 ) {
			                	literal = new TokenDateLiteral((LITERAL_STRING29!=null?LITERAL_STRING29.getText():null));
			                }
			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 41, tokenDateLiteral_StartIndex); }

		}
		return literal;
	}
	// $ANTLR end "tokenDateLiteral"



	// $ANTLR start "variableText"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1126:2: variableText returns [String text] : (var1= IDENTIFIER ) ;
	public final String variableText() throws RecognitionException {
		String text = null;

		int variableText_StartIndex = input.index();

		Token var1=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return text; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1127:3: ( (var1= IDENTIFIER ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1128:3: (var1= IDENTIFIER )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1128:3: (var1= IDENTIFIER )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1130:4: var1= IDENTIFIER
			{
			var1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variableText8973); if (state.failed) return text;
			if ( state.backtracking==0 ) {
			 		text = (var1!=null?var1.getText():null).toUpperCase();
			 		}
			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 42, variableText_StartIndex); }

		}
		return text;
	}
	// $ANTLR end "variableText"



	// $ANTLR start "identifierText"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1136:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) | ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE ) ) ;
	public final String identifierText() throws RecognitionException {
		String text = null;

		int identifierText_StartIndex = input.index();

		Token id1=null;
		Token id2=null;
		Token id3=null;
		Token id4=null;

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return text; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1137:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) | ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE ) ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1138:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) | ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE ) )
			{
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1138:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) | ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE ) )
			int alt49=3;
			int LA49_0 = input.LA(1);
			if ( (LA49_0==IDENTIFIER) ) {
				alt49=1;
			}
			else if ( (LA49_0==QUOTE_DOUBLE) ) {
				int LA49_2 = input.LA(2);
				if ( (LA49_2==IDENTIFIER) ) {
					int LA49_3 = input.LA(3);
					if ( (LA49_3==QUOTE_DOUBLE) ) {
						alt49=2;
					}
					else if ( (LA49_3==DIV||LA49_3==DOT) ) {
						alt49=3;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return text;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 49, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return text;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 49, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return text;}
				NoViableAltException nvae =
					new NoViableAltException("", 49, 0, input);
				throw nvae;
			}

			switch (alt49) {
				case 1 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1139:4: (id1= IDENTIFIER )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1139:4: (id1= IDENTIFIER )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1140:4: id1= IDENTIFIER
					{
					id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText9025); if (state.failed) return text;
					if ( state.backtracking==0 ) {
					                	text = (id1!=null?id1.getText():null).toUpperCase();
					                }
					}

					}
					break;
				case 2 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1145:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1145:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1146:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
					{
					match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText9081); if (state.failed) return text;
					id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText9097); if (state.failed) return text;
					if ( state.backtracking==0 ) {
					                	text = (id2!=null?id2.getText():null);
					                }
					match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText9117); if (state.failed) return text;
					}

					}
					break;
				case 3 :
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1153:11: ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE )
					{
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1153:11: ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1154:12: QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE
					{
					match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText9173); if (state.failed) return text;
					id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText9190); if (state.failed) return text;
					if ( state.backtracking==0 ) {
									        text = (id2!=null?id2.getText():null);
								}
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1158:11: ( DIV id3= IDENTIFIER )*
					loop48:
					while (true) {
						int alt48=2;
						int LA48_0 = input.LA(1);
						if ( (LA48_0==DIV) ) {
							alt48=1;
						}

						switch (alt48) {
						case 1 :
							// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1159:13: DIV id3= IDENTIFIER
							{
							match(input,DIV,FOLLOW_DIV_in_identifierText9220); if (state.failed) return text;
							id3=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText9238); if (state.failed) return text;
							if ( state.backtracking==0 ) {
												        text += "/";
												        text += (id3!=null?id3.getText():null);
											}
							}
							break;

						default :
							break loop48;
						}
					}

					match(input,DOT,FOLLOW_DOT_in_identifierText9251); if (state.failed) return text;
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1166:4: (id4= IDENTIFIER )
					// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1167:5: id4= IDENTIFIER
					{
					id4=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText9266); if (state.failed) return text;
					if ( state.backtracking==0 ) {
								        	text += ".";
								        	text += (id4!=null?id4.getText():null);
								        }
					}

					match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText9299); if (state.failed) return text;
					}

					}
					break;

			}

			}

		}

		    catch (RecognitionException e) {
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 43, identifierText_StartIndex); }

		}
		return text;
	}
	// $ANTLR end "identifierText"


	public static class tokenAddOperator_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tokenAddOperator"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1177:1: tokenAddOperator : ( PLUS | MINUS ) ;
	public final FunSQLParser.tokenAddOperator_return tokenAddOperator() throws RecognitionException {
		FunSQLParser.tokenAddOperator_return retval = new FunSQLParser.tokenAddOperator_return();
		retval.start = input.LT(1);
		int tokenAddOperator_StartIndex = input.index();

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1178:5: ( ( PLUS | MINUS ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 44, tokenAddOperator_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "tokenAddOperator"


	public static class tokenMultOperator_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tokenMultOperator"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1185:1: tokenMultOperator : ( MULT | DIV ) ;
	public final FunSQLParser.tokenMultOperator_return tokenMultOperator() throws RecognitionException {
		FunSQLParser.tokenMultOperator_return retval = new FunSQLParser.tokenMultOperator_return();
		retval.start = input.LT(1);
		int tokenMultOperator_StartIndex = input.index();

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1186:5: ( ( MULT | DIV ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 45, tokenMultOperator_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "tokenMultOperator"


	public static class tokenCompOperator_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tokenCompOperator"
	// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1193:1: tokenCompOperator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | KEYWORD_LIKE ) ;
	public final FunSQLParser.tokenCompOperator_return tokenCompOperator() throws RecognitionException {
		FunSQLParser.tokenCompOperator_return retval = new FunSQLParser.tokenCompOperator_return();
		retval.start = input.LT(1);
		int tokenCompOperator_StartIndex = input.index();

		try {
			if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }

			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1194:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | KEYWORD_LIKE ) )
			// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
			{
			if ( input.LA(1)==EQUAL1||(input.LA(1) >= GREATER_EQUAL1 && input.LA(1) <= GREATER_THAN)||input.LA(1)==KEYWORD_LIKE||(input.LA(1) >= LESS_EQUAL1 && input.LA(1) <= LESS_THAN)||(input.LA(1) >= NOT_EQUAL1 && input.LA(1) <= NOT_EQUAL2) ) {
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
		    	reportError(e);
		        throw e;
		    }

		finally {
			// do for sure before leaving
			if ( state.backtracking>0 ) { memoize(input, 46, tokenCompOperator_StartIndex); }

		}
		return retval;
	}
	// $ANTLR end "tokenCompOperator"

	// $ANTLR start synpred42_FunSQL
	public final void synpred42_FunSQL_fragment() throws RecognitionException {
		AbstractPredicate predicate1 =null;

		// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:659:3: (predicate1= parenPredicate )
		// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:659:3: predicate1= parenPredicate
		{
		pushFollow(FOLLOW_parenPredicate_in_synpred42_FunSQL6136);
		predicate1=parenPredicate();
		state._fsp--;
		if (state.failed) return;
		}

	}
	// $ANTLR end synpred42_FunSQL

	// $ANTLR start synpred52_FunSQL
	public final void synpred52_FunSQL_fragment() throws RecognitionException {
		TokenIdentifier schema1 =null;
		TokenIdentifier id1 =null;

		// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:863:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
		// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:863:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
		{
		// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:863:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
		// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:864:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
		{
		// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:864:17: (schema1= tokenIdentifier DOT )?
		int alt59=2;
		alt59 = dfa59.predict(input);
		switch (alt59) {
			case 1 :
				// /Users/erfanz/xdb/code/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:865:17: schema1= tokenIdentifier DOT
				{
				pushFollow(FOLLOW_tokenIdentifier_in_synpred52_FunSQL7170);
				schema1=tokenIdentifier();
				state._fsp--;
				if (state.failed) return;
				match(input,DOT,FOLLOW_DOT_in_synpred52_FunSQL7190); if (state.failed) return;
				}
				break;

		}

		pushFollow(FOLLOW_tokenIdentifier_in_synpred52_FunSQL7247);
		id1=tokenIdentifier();
		state._fsp--;
		if (state.failed) return;
		}

		}

	}
	// $ANTLR end synpred52_FunSQL

	// Delegated rules

	public final boolean synpred42_FunSQL() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred42_FunSQL_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred52_FunSQL() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred52_FunSQL_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}


	protected DFA7 dfa7 = new DFA7(this);
	protected DFA8 dfa8 = new DFA8(this);
	protected DFA10 dfa10 = new DFA10(this);
	protected DFA38 dfa38 = new DFA38(this);
	protected DFA41 dfa41 = new DFA41(this);
	protected DFA39 dfa39 = new DFA39(this);
	protected DFA42 dfa42 = new DFA42(this);
	protected DFA59 dfa59 = new DFA59(this);
	static final String DFA7_eotS =
		"\15\uffff";
	static final String DFA7_eofS =
		"\15\uffff";
	static final String DFA7_minS =
		"\1\125\1\41\1\15\1\41\2\uffff\1\21\1\15\2\41\1\21\1\144\1\15";
	static final String DFA7_maxS =
		"\1\125\1\144\1\151\1\41\2\uffff\1\144\1\151\2\41\1\23\1\144\1\151";
	static final String DFA7_acceptS =
		"\4\uffff\1\1\1\2\7\uffff";
	static final String DFA7_specialS =
		"\15\uffff}>";
	static final String[] DFA7_transitionS = {
			"\1\1",
			"\1\2\102\uffff\1\3",
			"\1\4\50\uffff\1\5\14\uffff\1\5\45\uffff\1\4",
			"\1\6",
			"",
			"",
			"\1\10\1\uffff\1\11\120\uffff\1\7",
			"\1\4\50\uffff\1\5\14\uffff\1\5\45\uffff\1\4",
			"\1\12",
			"\1\13",
			"\1\10\1\uffff\1\11",
			"\1\14",
			"\1\4\50\uffff\1\5\14\uffff\1\5\45\uffff\1\4"
	};

	static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
	static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
	static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
	static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
	static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
	static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
	static final short[][] DFA7_transition;

	static {
		int numStates = DFA7_transitionS.length;
		DFA7_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
		}
	}

	protected class DFA7 extends DFA {

		public DFA7(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 7;
			this.eot = DFA7_eot;
			this.eof = DFA7_eof;
			this.min = DFA7_min;
			this.max = DFA7_max;
			this.accept = DFA7_accept;
			this.special = DFA7_special;
			this.transition = DFA7_transition;
		}
		@Override
		public String getDescription() {
			return "300:17: ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )?";
		}
	}

	static final String DFA8_eotS =
		"\15\uffff";
	static final String DFA8_eofS =
		"\15\uffff";
	static final String DFA8_minS =
		"\1\15\1\41\1\uffff\1\15\1\41\1\uffff\1\21\1\15\2\41\1\21\1\144\1\15";
	static final String DFA8_maxS =
		"\1\151\1\144\1\uffff\1\151\1\41\1\uffff\1\144\1\151\2\41\1\23\1\144\1"+
		"\151";
	static final String DFA8_acceptS =
		"\2\uffff\1\2\2\uffff\1\1\7\uffff";
	static final String DFA8_specialS =
		"\15\uffff}>";
	static final String[] DFA8_transitionS = {
			"\1\1\133\uffff\1\2",
			"\1\3\102\uffff\1\4",
			"",
			"\1\5\50\uffff\1\2\14\uffff\1\2\45\uffff\1\5",
			"\1\6",
			"",
			"\1\10\1\uffff\1\11\120\uffff\1\7",
			"\1\5\50\uffff\1\2\14\uffff\1\2\45\uffff\1\5",
			"\1\12",
			"\1\13",
			"\1\10\1\uffff\1\11",
			"\1\14",
			"\1\5\50\uffff\1\2\14\uffff\1\2\45\uffff\1\5"
	};

	static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
	static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
	static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
	static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
	static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
	static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
	static final short[][] DFA8_transition;

	static {
		int numStates = DFA8_transitionS.length;
		DFA8_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
		}
	}

	protected class DFA8 extends DFA {

		public DFA8(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 8;
			this.eot = DFA8_eot;
			this.eof = DFA8_eof;
			this.min = DFA8_min;
			this.max = DFA8_max;
			this.accept = DFA8_accept;
			this.special = DFA8_special;
			this.transition = DFA8_transition;
		}
		@Override
		public String getDescription() {
			return "328:17: ( COMMA c3= tokenIdentifier )?";
		}
	}

	static final String DFA10_eotS =
		"\15\uffff";
	static final String DFA10_eofS =
		"\15\uffff";
	static final String DFA10_minS =
		"\1\15\1\41\1\uffff\1\15\1\41\1\uffff\1\21\1\15\2\41\1\21\1\144\1\15";
	static final String DFA10_maxS =
		"\1\151\1\144\1\uffff\1\151\1\41\1\uffff\1\144\1\151\2\41\1\23\1\144\1"+
		"\151";
	static final String DFA10_acceptS =
		"\2\uffff\1\2\2\uffff\1\1\7\uffff";
	static final String DFA10_specialS =
		"\15\uffff}>";
	static final String[] DFA10_transitionS = {
			"\1\1\133\uffff\1\2",
			"\1\3\102\uffff\1\4",
			"",
			"\1\5\50\uffff\1\2\14\uffff\1\2\45\uffff\1\5",
			"\1\6",
			"",
			"\1\10\1\uffff\1\11\120\uffff\1\7",
			"\1\5\50\uffff\1\2\14\uffff\1\2\45\uffff\1\5",
			"\1\12",
			"\1\13",
			"\1\10\1\uffff\1\11",
			"\1\14",
			"\1\5\50\uffff\1\2\14\uffff\1\2\45\uffff\1\5"
	};

	static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
	static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
	static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
	static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
	static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
	static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
	static final short[][] DFA10_transition;

	static {
		int numStates = DFA10_transitionS.length;
		DFA10_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
		}
	}

	protected class DFA10 extends DFA {

		public DFA10(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 10;
			this.eot = DFA10_eot;
			this.eof = DFA10_eof;
			this.min = DFA10_min;
			this.max = DFA10_max;
			this.accept = DFA10_accept;
			this.special = DFA10_special;
			this.transition = DFA10_transition;
		}
		@Override
		public String getDescription() {
			return "345:17: ( COMMA c3= tokenIdentifier )?";
		}
	}

	static final String DFA38_eotS =
		"\14\uffff";
	static final String DFA38_eofS =
		"\1\uffff\1\4\4\uffff\1\4\4\uffff\1\4";
	static final String DFA38_minS =
		"\1\41\1\15\1\41\2\uffff\1\21\1\15\2\41\1\21\1\144\1\15";
	static final String DFA38_maxS =
		"\1\144\1\153\1\41\2\uffff\1\144\1\153\2\41\1\23\1\144\1\153";
	static final String DFA38_acceptS =
		"\3\uffff\1\1\1\2\7\uffff";
	static final String DFA38_specialS =
		"\14\uffff}>";
	static final String[] DFA38_transitionS = {
			"\1\1\102\uffff\1\2",
			"\1\4\3\uffff\1\4\1\uffff\1\3\2\uffff\1\4\4\uffff\3\4\7\uffff\2\4\13"+
			"\uffff\1\4\1\uffff\2\4\3\uffff\1\4\4\uffff\1\4\20\uffff\3\4\5\uffff\1"+
			"\4\1\uffff\1\4\1\uffff\2\4\3\uffff\1\4\10\uffff\1\4\1\uffff\1\4",
			"\1\5",
			"",
			"",
			"\1\7\1\uffff\1\10\120\uffff\1\6",
			"\1\4\3\uffff\1\4\1\uffff\1\3\2\uffff\1\4\4\uffff\3\4\7\uffff\2\4\13"+
			"\uffff\1\4\1\uffff\2\4\3\uffff\1\4\4\uffff\1\4\20\uffff\3\4\5\uffff\1"+
			"\4\1\uffff\1\4\1\uffff\2\4\3\uffff\1\4\10\uffff\1\4\1\uffff\1\4",
			"\1\11",
			"\1\12",
			"\1\7\1\uffff\1\10",
			"\1\13",
			"\1\4\3\uffff\1\4\1\uffff\1\3\2\uffff\1\4\4\uffff\3\4\7\uffff\2\4\13"+
			"\uffff\1\4\1\uffff\2\4\3\uffff\1\4\4\uffff\1\4\20\uffff\3\4\5\uffff\1"+
			"\4\1\uffff\1\4\1\uffff\2\4\3\uffff\1\4\10\uffff\1\4\1\uffff\1\4"
	};

	static final short[] DFA38_eot = DFA.unpackEncodedString(DFA38_eotS);
	static final short[] DFA38_eof = DFA.unpackEncodedString(DFA38_eofS);
	static final char[] DFA38_min = DFA.unpackEncodedStringToUnsignedChars(DFA38_minS);
	static final char[] DFA38_max = DFA.unpackEncodedStringToUnsignedChars(DFA38_maxS);
	static final short[] DFA38_accept = DFA.unpackEncodedString(DFA38_acceptS);
	static final short[] DFA38_special = DFA.unpackEncodedString(DFA38_specialS);
	static final short[][] DFA38_transition;

	static {
		int numStates = DFA38_transitionS.length;
		DFA38_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA38_transition[i] = DFA.unpackEncodedString(DFA38_transitionS[i]);
		}
	}

	protected class DFA38 extends DFA {

		public DFA38(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 38;
			this.eot = DFA38_eot;
			this.eof = DFA38_eof;
			this.min = DFA38_min;
			this.max = DFA38_max;
			this.accept = DFA38_accept;
			this.special = DFA38_special;
			this.transition = DFA38_transition;
		}
		@Override
		public String getDescription() {
			return "844:17: (table1= tokenIdentifier DOT )?";
		}
	}

	static final String DFA41_eotS =
		"\14\uffff";
	static final String DFA41_eofS =
		"\14\uffff";
	static final String DFA41_minS =
		"\1\14\1\0\1\41\2\uffff\1\21\1\0\2\41\1\21\1\144\1\0";
	static final String DFA41_maxS =
		"\1\144\1\0\1\41\2\uffff\1\144\1\0\2\41\1\23\1\144\1\0";
	static final String DFA41_acceptS =
		"\3\uffff\1\2\1\1\7\uffff";
	static final String DFA41_specialS =
		"\1\uffff\1\1\4\uffff\1\2\4\uffff\1\0}>";
	static final String[] DFA41_transitionS = {
			"\1\3\24\uffff\1\1\102\uffff\1\2",
			"\1\uffff",
			"\1\5",
			"",
			"",
			"\1\7\1\uffff\1\10\120\uffff\1\6",
			"\1\uffff",
			"\1\11",
			"\1\12",
			"\1\7\1\uffff\1\10",
			"\1\13",
			"\1\uffff"
	};

	static final short[] DFA41_eot = DFA.unpackEncodedString(DFA41_eotS);
	static final short[] DFA41_eof = DFA.unpackEncodedString(DFA41_eofS);
	static final char[] DFA41_min = DFA.unpackEncodedStringToUnsignedChars(DFA41_minS);
	static final char[] DFA41_max = DFA.unpackEncodedStringToUnsignedChars(DFA41_maxS);
	static final short[] DFA41_accept = DFA.unpackEncodedString(DFA41_acceptS);
	static final short[] DFA41_special = DFA.unpackEncodedString(DFA41_specialS);
	static final short[][] DFA41_transition;

	static {
		int numStates = DFA41_transitionS.length;
		DFA41_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA41_transition[i] = DFA.unpackEncodedString(DFA41_transitionS[i]);
		}
	}

	protected class DFA41 extends DFA {

		public DFA41(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 41;
			this.eot = DFA41_eot;
			this.eof = DFA41_eof;
			this.min = DFA41_min;
			this.max = DFA41_max;
			this.accept = DFA41_accept;
			this.special = DFA41_special;
			this.transition = DFA41_transition;
		}
		@Override
		public String getDescription() {
			return "858:1: tokenTable returns [TokenTable table] : ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			TokenStream input = (TokenStream)_input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA41_11 = input.LA(1);
						 
						int index41_11 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred52_FunSQL()) ) {s = 4;}
						else if ( (true) ) {s = 3;}
						 
						input.seek(index41_11);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA41_1 = input.LA(1);
						 
						int index41_1 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred52_FunSQL()) ) {s = 4;}
						else if ( (true) ) {s = 3;}
						 
						input.seek(index41_1);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA41_6 = input.LA(1);
						 
						int index41_6 = input.index();
						input.rewind();
						s = -1;
						if ( (synpred52_FunSQL()) ) {s = 4;}
						else if ( (true) ) {s = 3;}
						 
						input.seek(index41_6);
						if ( s>=0 ) return s;
						break;
			}
			if (state.backtracking>0) {state.failed=true; return -1;}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 41, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA39_eotS =
		"\14\uffff";
	static final String DFA39_eofS =
		"\1\uffff\1\4\4\uffff\1\4\4\uffff\1\4";
	static final String DFA39_minS =
		"\1\41\1\15\1\41\2\uffff\1\21\1\15\2\41\1\21\1\144\1\15";
	static final String DFA39_maxS =
		"\1\144\1\153\1\41\2\uffff\1\144\1\153\2\41\1\23\1\144\1\153";
	static final String DFA39_acceptS =
		"\3\uffff\1\1\1\2\7\uffff";
	static final String DFA39_specialS =
		"\14\uffff}>";
	static final String[] DFA39_transitionS = {
			"\1\1\102\uffff\1\2",
			"\1\4\5\uffff\1\3\22\uffff\1\4\15\uffff\2\4\12\uffff\1\4\13\uffff\1\4"+
			"\10\uffff\1\4\25\uffff\1\4",
			"\1\5",
			"",
			"",
			"\1\7\1\uffff\1\10\120\uffff\1\6",
			"\1\4\5\uffff\1\3\22\uffff\1\4\15\uffff\2\4\12\uffff\1\4\13\uffff\1\4"+
			"\10\uffff\1\4\25\uffff\1\4",
			"\1\11",
			"\1\12",
			"\1\7\1\uffff\1\10",
			"\1\13",
			"\1\4\5\uffff\1\3\22\uffff\1\4\15\uffff\2\4\12\uffff\1\4\13\uffff\1\4"+
			"\10\uffff\1\4\25\uffff\1\4"
	};

	static final short[] DFA39_eot = DFA.unpackEncodedString(DFA39_eotS);
	static final short[] DFA39_eof = DFA.unpackEncodedString(DFA39_eofS);
	static final char[] DFA39_min = DFA.unpackEncodedStringToUnsignedChars(DFA39_minS);
	static final char[] DFA39_max = DFA.unpackEncodedStringToUnsignedChars(DFA39_maxS);
	static final short[] DFA39_accept = DFA.unpackEncodedString(DFA39_acceptS);
	static final short[] DFA39_special = DFA.unpackEncodedString(DFA39_specialS);
	static final short[][] DFA39_transition;

	static {
		int numStates = DFA39_transitionS.length;
		DFA39_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA39_transition[i] = DFA.unpackEncodedString(DFA39_transitionS[i]);
		}
	}

	protected class DFA39 extends DFA {

		public DFA39(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 39;
			this.eot = DFA39_eot;
			this.eof = DFA39_eof;
			this.min = DFA39_min;
			this.max = DFA39_max;
			this.accept = DFA39_accept;
			this.special = DFA39_special;
			this.transition = DFA39_transition;
		}
		@Override
		public String getDescription() {
			return "864:17: (schema1= tokenIdentifier DOT )?";
		}
	}

	static final String DFA42_eotS =
		"\14\uffff";
	static final String DFA42_eofS =
		"\1\uffff\1\4\4\uffff\1\4\4\uffff\1\4";
	static final String DFA42_minS =
		"\1\41\1\23\1\41\2\uffff\1\21\1\23\2\41\1\21\1\144\1\23";
	static final String DFA42_maxS =
		"\1\144\1\153\1\41\2\uffff\1\144\1\153\2\41\1\23\1\144\1\153";
	static final String DFA42_acceptS =
		"\3\uffff\1\1\1\2\7\uffff";
	static final String DFA42_specialS =
		"\14\uffff}>";
	static final String[] DFA42_transitionS = {
			"\1\1\102\uffff\1\2",
			"\1\3\101\uffff\1\4\25\uffff\1\4",
			"\1\5",
			"",
			"",
			"\1\7\1\uffff\1\10\120\uffff\1\6",
			"\1\3\101\uffff\1\4\25\uffff\1\4",
			"\1\11",
			"\1\12",
			"\1\7\1\uffff\1\10",
			"\1\13",
			"\1\3\101\uffff\1\4\25\uffff\1\4"
	};

	static final short[] DFA42_eot = DFA.unpackEncodedString(DFA42_eotS);
	static final short[] DFA42_eof = DFA.unpackEncodedString(DFA42_eofS);
	static final char[] DFA42_min = DFA.unpackEncodedStringToUnsignedChars(DFA42_minS);
	static final char[] DFA42_max = DFA.unpackEncodedStringToUnsignedChars(DFA42_maxS);
	static final short[] DFA42_accept = DFA.unpackEncodedString(DFA42_acceptS);
	static final short[] DFA42_special = DFA.unpackEncodedString(DFA42_specialS);
	static final short[][] DFA42_transition;

	static {
		int numStates = DFA42_transitionS.length;
		DFA42_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA42_transition[i] = DFA.unpackEncodedString(DFA42_transitionS[i]);
		}
	}

	protected class DFA42 extends DFA {

		public DFA42(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 42;
			this.eot = DFA42_eot;
			this.eof = DFA42_eof;
			this.min = DFA42_min;
			this.max = DFA42_max;
			this.accept = DFA42_accept;
			this.special = DFA42_special;
			this.transition = DFA42_transition;
		}
		@Override
		public String getDescription() {
			return "904:33: (schema1= tokenIdentifier DOT )?";
		}
	}

	static final String DFA59_eotS =
		"\14\uffff";
	static final String DFA59_eofS =
		"\1\uffff\1\4\4\uffff\1\4\4\uffff\1\4";
	static final String DFA59_minS =
		"\1\41\1\23\1\41\2\uffff\1\21\1\23\2\41\1\21\1\144\1\23";
	static final String DFA59_maxS =
		"\1\144\1\23\1\41\2\uffff\1\144\1\23\2\41\1\23\1\144\1\23";
	static final String DFA59_acceptS =
		"\3\uffff\1\1\1\2\7\uffff";
	static final String DFA59_specialS =
		"\14\uffff}>";
	static final String[] DFA59_transitionS = {
			"\1\1\102\uffff\1\2",
			"\1\3",
			"\1\5",
			"",
			"",
			"\1\7\1\uffff\1\10\120\uffff\1\6",
			"\1\3",
			"\1\11",
			"\1\12",
			"\1\7\1\uffff\1\10",
			"\1\13",
			"\1\3"
	};

	static final short[] DFA59_eot = DFA.unpackEncodedString(DFA59_eotS);
	static final short[] DFA59_eof = DFA.unpackEncodedString(DFA59_eofS);
	static final char[] DFA59_min = DFA.unpackEncodedStringToUnsignedChars(DFA59_minS);
	static final char[] DFA59_max = DFA.unpackEncodedStringToUnsignedChars(DFA59_maxS);
	static final short[] DFA59_accept = DFA.unpackEncodedString(DFA59_acceptS);
	static final short[] DFA59_special = DFA.unpackEncodedString(DFA59_specialS);
	static final short[][] DFA59_transition;

	static {
		int numStates = DFA59_transitionS.length;
		DFA59_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA59_transition[i] = DFA.unpackEncodedString(DFA59_transitionS[i]);
		}
	}

	protected class DFA59 extends DFA {

		public DFA59(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 59;
			this.eot = DFA59_eot;
			this.eof = DFA59_eof;
			this.min = DFA59_min;
			this.max = DFA59_max;
			this.accept = DFA59_accept;
			this.special = DFA59_special;
			this.transition = DFA59_transition;
		}
		@Override
		public String getDescription() {
			return "864:17: (schema1= tokenIdentifier DOT )?";
		}
	}

	public static final BitSet FOLLOW_createSchemaStatement_in_statement1106 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_dropSchemaStatement_in_statement1161 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_createConnectionStatement_in_statement1216 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_dropConnectionStatement_in_statement1271 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_createTableStatement_in_statement1326 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_dropTableStatement_in_statement1381 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_createFunctionStatement_in_statement1436 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_dropFunctionStatement_in_statement1491 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_callFunctionStatement_in_statement1546 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_selectStatement_in_statement1601 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_loadDataInfileStatement_in_statement1655 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
	public static final BitSet FOLLOW_SEMI_in_statement1709 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1787 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1805 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1823 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1902 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1920 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1938 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement2017 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement2035 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement2053 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
	public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement2073 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2093 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement2113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2133 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement2153 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2173 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement2193 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2213 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2300 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2318 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2336 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2416 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2434 = new BitSet(new long[]{0x0000000200001000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenTable_in_createTableStatement2454 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_LPAREN_in_createTableStatement2474 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement2494 = new BitSet(new long[]{0x0000000000000000L,0x000F000000000000L});
	public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2532 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_COMMA_in_createTableStatement2586 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement2606 = new BitSet(new long[]{0x0000000000000000L,0x000F000000000000L});
	public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2644 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_RPAREN_in_createTableStatement2699 = new BitSet(new long[]{0x0040000000000000L,0x000000000000000AL});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2737 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2739 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2759 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_REPLICATED_in_createTableStatement2800 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2802 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2804 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2825 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_COMMA_in_createTableStatement2847 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2869 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_KEYWORD_PARTITIONED_in_createTableStatement2963 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_KEYWORD_BY_in_createTableStatement2965 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement2986 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_LPAREN_in_createTableStatement3040 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement3060 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_COMMA_in_createTableStatement3114 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement3134 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_RPAREN_in_createTableStatement3189 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_LPAREN_in_createTableStatement3226 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement3246 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3285 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3287 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3307 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3330 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3332 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3334 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3355 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_COMMA_in_createTableStatement3376 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3381 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_COMMA_in_createTableStatement3423 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_identifierText_in_createTableStatement3443 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3481 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3483 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3503 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3526 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3528 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3530 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3551 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_COMMA_in_createTableStatement3572 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3577 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_RPAREN_in_createTableStatement3620 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement3734 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement3752 = new BitSet(new long[]{0x0000000200001000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenTable_in_dropTableStatement3772 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CREATE_in_createFunctionStatement3838 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement3856 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenFunction_in_createFunctionStatement3876 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_LPAREN_in_createFunctionStatement3892 = new BitSet(new long[]{0x8040000000000000L});
	public static final BitSet FOLLOW_KEYWORD_IN_in_createFunctionStatement3921 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement3941 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3948 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_createFunctionStatement3954 = new BitSet(new long[]{0x8040000000000000L});
	public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement3992 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement4012 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4019 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_COMMA_in_createFunctionStatement4032 = new BitSet(new long[]{0x8000000000000000L});
	public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement4037 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement4051 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4057 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_RPAREN_in_createFunctionStatement4066 = new BitSet(new long[]{0x0000010000000000L});
	public static final BitSet FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement4070 = new BitSet(new long[]{0x0002040000001000L,0x0000000000000800L});
	public static final BitSet FOLLOW_tokenAssignment_in_createFunctionStatement4084 = new BitSet(new long[]{0x0002040000001000L,0x0000000000000800L});
	public static final BitSet FOLLOW_tokenFunctionCall_in_createFunctionStatement4163 = new BitSet(new long[]{0x0002040000001000L,0x0000000000000800L});
	public static final BitSet FOLLOW_KEYWORD_END_in_createFunctionStatement4205 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_SEMI_in_createFunctionStatement4212 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_DROP_in_dropFunctionStatement4289 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement4307 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenFunction_in_dropFunctionStatement4327 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CALL_in_callFunctionStatement4393 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement4411 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenFunction_in_callFunctionStatement4431 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement4508 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_abstractExpression_in_selectStatement4528 = new BitSet(new long[]{0x0004004000002000L});
	public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4583 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4604 = new BitSet(new long[]{0x0004000000002000L});
	public static final BitSet FOLLOW_COMMA_in_selectStatement4678 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_abstractExpression_in_selectStatement4698 = new BitSet(new long[]{0x0004004000002000L});
	public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4753 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4774 = new BitSet(new long[]{0x0004000000002000L});
	public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement4883 = new BitSet(new long[]{0x0000000200001000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenTable_in_selectStatement4903 = new BitSet(new long[]{0x0030004000002002L,0x0000000000001000L});
	public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4959 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4980 = new BitSet(new long[]{0x0030000000002002L,0x0000000000001000L});
	public static final BitSet FOLLOW_COMMA_in_selectStatement5054 = new BitSet(new long[]{0x0000000200001000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenTable_in_selectStatement5074 = new BitSet(new long[]{0x0030004000002002L,0x0000000000001000L});
	public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement5129 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement5150 = new BitSet(new long[]{0x0030000000002002L,0x0000000000001000L});
	public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement5260 = new BitSet(new long[]{0x2000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_abstractPredicate_in_selectStatement5280 = new BitSet(new long[]{0x0030000000000002L});
	public static final BitSet FOLLOW_KEYWORD_GROUP_in_selectStatement5387 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_KEYWORD_BY_in_selectStatement5389 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_abstractExpression_in_selectStatement5409 = new BitSet(new long[]{0x0020000000002002L});
	public static final BitSet FOLLOW_COMMA_in_selectStatement5463 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_abstractExpression_in_selectStatement5483 = new BitSet(new long[]{0x0020000000002002L});
	public static final BitSet FOLLOW_KEYWORD_HAVING_in_selectStatement5609 = new BitSet(new long[]{0x2000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_abstractPredicate_in_selectStatement5629 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_LOAD_in_loadDataInfileStatement5722 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_KEYWORD_DATA_in_loadDataInfileStatement5732 = new BitSet(new long[]{0x0080000000000000L});
	public static final BitSet FOLLOW_KEYWORD_INFILE_in_loadDataInfileStatement5742 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_identifierText_in_loadDataInfileStatement5754 = new BitSet(new long[]{0x0100000000000000L});
	public static final BitSet FOLLOW_KEYWORD_INTO_in_loadDataInfileStatement5766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_KEYWORD_TABLE_in_loadDataInfileStatement5776 = new BitSet(new long[]{0x0000000200001000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenTable_in_loadDataInfileStatement5788 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
	public static final BitSet FOLLOW_KEYWORD_PARTITION_in_loadDataInfileStatement5811 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_LPAREN_in_loadDataInfileStatement5822 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_identifierText_in_loadDataInfileStatement5835 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_COMMA_in_loadDataInfileStatement5860 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_identifierText_in_loadDataInfileStatement5874 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
	public static final BitSet FOLLOW_RPAREN_in_loadDataInfileStatement5899 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate5940 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr5978 = new BitSet(new long[]{0x4000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr5989 = new BitSet(new long[]{0x2000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr5997 = new BitSet(new long[]{0x4000000000000002L});
	public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd6036 = new BitSet(new long[]{0x0000002000000002L});
	public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd6047 = new BitSet(new long[]{0x2000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd6055 = new BitSet(new long[]{0x0000002000000002L});
	public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot6096 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot6109 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parenPredicate_in_complexPredicate6136 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simplePredicate_in_complexPredicate6151 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_parenPredicate6176 = new BitSet(new long[]{0x2000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate6183 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_RPAREN_in_parenPredicate6189 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_abstractExpression_in_simplePredicate6231 = new BitSet(new long[]{0x0200000038400000L,0x0000000018038000L});
	public static final BitSet FOLLOW_tokenCompOperator_in_simplePredicate6286 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_abstractExpression_in_simplePredicate6324 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_complexExpressionAdd_in_abstractExpression6378 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd6408 = new BitSet(new long[]{0x0000000000000002L,0x0000000100800000L});
	public static final BitSet FOLLOW_tokenAddOperator_in_complexExpressionAdd6421 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd6429 = new BitSet(new long[]{0x0000000000000002L,0x0000000100800000L});
	public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult6469 = new BitSet(new long[]{0x0000000000020002L,0x0000000002000000L});
	public static final BitSet FOLLOW_tokenMultOperator_in_complexExpressionMult6482 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult6490 = new BitSet(new long[]{0x0000000000020002L,0x0000000002000000L});
	public static final BitSet FOLLOW_MINUS_in_complexExpressionSigned6532 = new BitSet(new long[]{0x0000000202000000L,0x00010010003C0000L});
	public static final BitSet FOLLOW_PLUS_in_complexExpressionSigned6541 = new BitSet(new long[]{0x0000000202000000L,0x00010010003C0000L});
	public static final BitSet FOLLOW_complexExpression_in_complexExpressionSigned6553 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parenExpression_in_complexExpression6588 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_aggregationExpression_in_complexExpression6603 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simpleExpression_in_complexExpression6618 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_parenExpression6643 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_abstractExpression_in_parenExpression6650 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_RPAREN_in_parenExpression6656 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression6708 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_LPAREN_in_aggregationExpression6716 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
	public static final BitSet FOLLOW_abstractExpression_in_aggregationExpression6723 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_RPAREN_in_aggregationExpression6729 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenAttribute_in_simpleExpression6804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenLiteral_in_simpleExpression6860 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute6988 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_DOT_in_tokenAttribute7008 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute7047 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable7170 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_DOT_in_tokenTable7190 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable7247 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_tokenTable7314 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable7356 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema7437 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction7553 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_DOT_in_tokenFunction7573 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction7612 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variableText_in_tokenVariable7692 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_tokenAssignment7759 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7771 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7777 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_selectStatement_in_tokenAssignment7784 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment7805 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7814 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7820 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_selectStatement_in_tokenAssignment7827 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment7851 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7860 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7866 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_COLON_in_tokenAssignment7877 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7889 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_SEMI_in_tokenAssignment7900 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_KEYWORD_CALL_in_tokenFunctionCall7932 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_tokenFunctionCall7937 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenFunction_in_tokenFunctionCall7944 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_LPAREN_in_tokenFunctionCall7950 = new BitSet(new long[]{0x0000000000001000L,0x0000000000000800L});
	public static final BitSet FOLLOW_COLON_in_tokenFunctionCall7961 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall7974 = new BitSet(new long[]{0x0000000000001000L,0x0000000000000800L});
	public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7995 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall8005 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_COMMA_in_tokenFunctionCall8012 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenFunctionCall8034 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall8044 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_RPAREN_in_tokenFunctionCall8055 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
	public static final BitSet FOLLOW_SEMI_in_tokenFunctionCall8060 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifierText_in_tokenIdentifier8128 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType8209 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType8247 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_DECIMAL_in_tokenDataType8285 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_DATE_in_tokenDataType8323 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral8414 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral8452 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral8490 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral8528 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral8628 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral8708 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral8798 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TYPE_DATE_in_tokenDateLiteral8896 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
	public static final BitSet FOLLOW_LITERAL_STRING_in_tokenDateLiteral8914 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_variableText8973 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENTIFIER_in_identifierText9025 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText9081 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_identifierText9097 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText9117 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText9173 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_identifierText9190 = new BitSet(new long[]{0x00000000000A0000L});
	public static final BitSet FOLLOW_DIV_in_identifierText9220 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_identifierText9238 = new BitSet(new long[]{0x00000000000A0000L});
	public static final BitSet FOLLOW_DOT_in_identifierText9251 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_IDENTIFIER_in_identifierText9266 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText9299 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parenPredicate_in_synpred42_FunSQL6136 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tokenIdentifier_in_synpred52_FunSQL7170 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_DOT_in_synpred52_FunSQL7190 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_tokenIdentifier_in_synpred52_FunSQL7247 = new BitSet(new long[]{0x0000000000000002L});
}
