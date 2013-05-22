// $ANTLR 3.4 /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g 2013-05-22 10:01:11
 
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

@SuppressWarnings({"all", "warnings", "unchecked"})
public class FunSQLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", "AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", "D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", "F", "FUNCTION_AGGREGATION", "G", "GREATER_EQUAL1", "GREATER_EQUAL2", "GREATER_THAN", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", "KEYWORD_AND", "KEYWORD_AS", "KEYWORD_AVG", "KEYWORD_BEGIN", "KEYWORD_BY", "KEYWORD_CALL", "KEYWORD_CONNECTION", "KEYWORD_COUNT", "KEYWORD_CREATE", "KEYWORD_DISTINCT", "KEYWORD_DROP", "KEYWORD_END", "KEYWORD_FROM", "KEYWORD_FUNCTION", "KEYWORD_GROUP", "KEYWORD_HAVING", "KEYWORD_IN", "KEYWORD_LIKE", "KEYWORD_MAX", "KEYWORD_MIN", "KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_OUT", "KEYWORD_PARTITIONED", "KEYWORD_PASSWD", "KEYWORD_REPLICATED", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", "KEYWORD_SUM", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_VAR", "KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", "LESS_THAN", "LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", "MINUS", "MOD", "MULT", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", "QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", "SHIFT_RIGHT", "T", "TILDE", "TYPE_DATE", "TYPE_DECIMAL", "TYPE_INTEGER", "TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
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
    public static final int KEYWORD_LIKE=54;
    public static final int KEYWORD_MAX=55;
    public static final int KEYWORD_MIN=56;
    public static final int KEYWORD_NOT=57;
    public static final int KEYWORD_OR=58;
    public static final int KEYWORD_OUT=59;
    public static final int KEYWORD_PARTITIONED=60;
    public static final int KEYWORD_PASSWD=61;
    public static final int KEYWORD_REPLICATED=62;
    public static final int KEYWORD_SCHEMA=63;
    public static final int KEYWORD_SELECT=64;
    public static final int KEYWORD_STORE=65;
    public static final int KEYWORD_SUM=66;
    public static final int KEYWORD_TABLE=67;
    public static final int KEYWORD_URL=68;
    public static final int KEYWORD_USER=69;
    public static final int KEYWORD_VAR=70;
    public static final int KEYWORD_WHERE=71;
    public static final int L=72;
    public static final int LBRACKET=73;
    public static final int LESS_EQUAL1=74;
    public static final int LESS_EQUAL2=75;
    public static final int LESS_THAN=76;
    public static final int LITERAL_DECIMAL=77;
    public static final int LITERAL_INTEGER=78;
    public static final int LITERAL_STRING=79;
    public static final int LPAREN=80;
    public static final int M=81;
    public static final int MINUS=82;
    public static final int MOD=83;
    public static final int MULT=84;
    public static final int N=85;
    public static final int NOT_EQUAL1=86;
    public static final int NOT_EQUAL2=87;
    public static final int O=88;
    public static final int P=89;
    public static final int PIPE=90;
    public static final int PLUS=91;
    public static final int Q=92;
    public static final int QUESTION=93;
    public static final int QUOTED_STRING=94;
    public static final int QUOTE_DOUBLE=95;
    public static final int QUOTE_SINGLE=96;
    public static final int QUOTE_TRIPLE=97;
    public static final int R=98;
    public static final int RBRACKET=99;
    public static final int RPAREN=100;
    public static final int S=101;
    public static final int SEMI=102;
    public static final int SHIFT_LEFT=103;
    public static final int SHIFT_RIGHT=104;
    public static final int T=105;
    public static final int TILDE=106;
    public static final int TYPE_DATE=107;
    public static final int TYPE_DECIMAL=108;
    public static final int TYPE_INTEGER=109;
    public static final int TYPE_VARCHAR=110;
    public static final int U=111;
    public static final int UNDERSCORE=112;
    public static final int V=113;
    public static final int W=114;
    public static final int WS=115;
    public static final int X=116;
    public static final int Y=117;
    public static final int Z=118;

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
        this.state.ruleMemo = new HashMap[119+1];
         

    }

    public String[] getTokenNames() { return FunSQLParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g"; }


      @Override
      protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
        throw new MismatchedTokenException(ttype, input);
      }

      @Override
      public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
        throw e;
      }



    // $ANTLR start "statement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:95:1: statement returns [AbstractServerStmt stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? ) ;
    public final AbstractServerStmt statement() throws RecognitionException {
        AbstractServerStmt stmt = null;

        int statement_StartIndex = input.index();

        FunSQLParser.createSchemaStatement_return createSchemaStatement1 =null;

        FunSQLParser.dropSchemaStatement_return dropSchemaStatement2 =null;

        FunSQLParser.createConnectionStatement_return createConnectionStatement3 =null;

        FunSQLParser.dropConnectionStatement_return dropConnectionStatement4 =null;

        FunSQLParser.createTableStatement_return createTableStatement5 =null;

        FunSQLParser.dropTableStatement_return dropTableStatement6 =null;

        FunSQLParser.createFunctionStatement_return createFunctionStatement7 =null;

        FunSQLParser.dropFunctionStatement_return dropFunctionStatement8 =null;

        FunSQLParser.callFunctionStatement_return callFunctionStatement9 =null;

        FunSQLParser.selectStatement_return selectStatement10 =null;



                	stmt = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:99:9: ( ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:100:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:100:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:101:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )?
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:101:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement )
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
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;

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
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 2, input);

                    throw nvae;

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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:102:17: createSchemaStatement
                    {
                    pushFollow(FOLLOW_createSchemaStatement_in_statement1106);
                    createSchemaStatement1=createSchemaStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (createSchemaStatement1!=null?createSchemaStatement1.stmt:null);
                                    	stmt.setStmtString((createSchemaStatement1!=null?input.toString(createSchemaStatement1.start,createSchemaStatement1.stop):null));
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:108:17: dropSchemaStatement
                    {
                    pushFollow(FOLLOW_dropSchemaStatement_in_statement1161);
                    dropSchemaStatement2=dropSchemaStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (dropSchemaStatement2!=null?dropSchemaStatement2.stmt:null);
                                    	stmt.setStmtString((dropSchemaStatement2!=null?input.toString(dropSchemaStatement2.start,dropSchemaStatement2.stop):null));
                                    }

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:114:17: createConnectionStatement
                    {
                    pushFollow(FOLLOW_createConnectionStatement_in_statement1216);
                    createConnectionStatement3=createConnectionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (createConnectionStatement3!=null?createConnectionStatement3.stmt:null);
                                    	stmt.setStmtString((createConnectionStatement3!=null?input.toString(createConnectionStatement3.start,createConnectionStatement3.stop):null));
                                    }

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:120:17: dropConnectionStatement
                    {
                    pushFollow(FOLLOW_dropConnectionStatement_in_statement1271);
                    dropConnectionStatement4=dropConnectionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (dropConnectionStatement4!=null?dropConnectionStatement4.stmt:null);
                                    	stmt.setStmtString((dropConnectionStatement4!=null?input.toString(dropConnectionStatement4.start,dropConnectionStatement4.stop):null));
                                    }

                    }
                    break;
                case 5 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:126:17: createTableStatement
                    {
                    pushFollow(FOLLOW_createTableStatement_in_statement1326);
                    createTableStatement5=createTableStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (createTableStatement5!=null?createTableStatement5.stmt:null);
                                    	stmt.setStmtString((createTableStatement5!=null?input.toString(createTableStatement5.start,createTableStatement5.stop):null));
                                    }

                    }
                    break;
                case 6 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:132:17: dropTableStatement
                    {
                    pushFollow(FOLLOW_dropTableStatement_in_statement1381);
                    dropTableStatement6=dropTableStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (dropTableStatement6!=null?dropTableStatement6.stmt:null);
                                    	stmt.setStmtString((dropTableStatement6!=null?input.toString(dropTableStatement6.start,dropTableStatement6.stop):null));
                                    }

                    }
                    break;
                case 7 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:138:17: createFunctionStatement
                    {
                    pushFollow(FOLLOW_createFunctionStatement_in_statement1436);
                    createFunctionStatement7=createFunctionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (createFunctionStatement7!=null?createFunctionStatement7.stmt:null);
                                    	stmt.setStmtString((createFunctionStatement7!=null?input.toString(createFunctionStatement7.start,createFunctionStatement7.stop):null));
                                    }

                    }
                    break;
                case 8 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:144:17: dropFunctionStatement
                    {
                    pushFollow(FOLLOW_dropFunctionStatement_in_statement1491);
                    dropFunctionStatement8=dropFunctionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (dropFunctionStatement8!=null?dropFunctionStatement8.stmt:null);
                                    	stmt.setStmtString((dropFunctionStatement8!=null?input.toString(dropFunctionStatement8.start,dropFunctionStatement8.stop):null));
                                    }

                    }
                    break;
                case 9 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:150:17: callFunctionStatement
                    {
                    pushFollow(FOLLOW_callFunctionStatement_in_statement1546);
                    callFunctionStatement9=callFunctionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (callFunctionStatement9!=null?callFunctionStatement9.stmt:null);
                                    	stmt.setStmtString((callFunctionStatement9!=null?input.toString(callFunctionStatement9.start,callFunctionStatement9.stop):null));
                                    }

                    }
                    break;
                case 10 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:156:17: selectStatement
                    {
                    pushFollow(FOLLOW_selectStatement_in_statement1601);
                    selectStatement10=selectStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (selectStatement10!=null?selectStatement10.stmt:null);
                                    	stmt.setStmtString((selectStatement10!=null?input.toString(selectStatement10.start,selectStatement10.stop):null));
                                    }

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:162:17: ( SEMI )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==SEMI) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:162:17: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_statement1655); if (state.failed) return stmt;

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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:166:1: createSchemaStatement returns [CreateSchemaStmt stmt] : ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) ;
    public final FunSQLParser.createSchemaStatement_return createSchemaStatement() throws RecognitionException {
        FunSQLParser.createSchemaStatement_return retval = new FunSQLParser.createSchemaStatement_return();
        retval.start = input.LT(1);

        int createSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema11 =null;



                	retval.stmt = new CreateSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:170:9: ( ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:171:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:171:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:172:17: KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1733); if (state.failed) return retval;

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1751); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenSchema_in_createSchemaStatement1769);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:180:1: dropSchemaStatement returns [DropSchemaStmt stmt] : ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) ;
    public final FunSQLParser.dropSchemaStatement_return dropSchemaStatement() throws RecognitionException {
        FunSQLParser.dropSchemaStatement_return retval = new FunSQLParser.dropSchemaStatement_return();
        retval.start = input.LT(1);

        int dropSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema12 =null;



                	retval.stmt = new DropSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:184:9: ( ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:185:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:185:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:186:17: KEYWORD_DROP KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1848); if (state.failed) return retval;

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1866); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenSchema_in_dropSchemaStatement1884);
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
            if ( state.backtracking>0 ) { memoize(input, 3, dropSchemaStatement_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "dropSchemaStatement"


    public static class createConnectionStatement_return extends ParserRuleReturnScope {
        public CreateConnectionStmt stmt;
    };


    // $ANTLR start "createConnectionStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:194:1: createConnectionStatement returns [CreateConnectionStmt stmt] : ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:198:9: ( ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:199:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:199:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:200:17: KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1963); if (state.failed) return retval;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1981); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenIdentifier_in_createConnectionStatement1999);
            tokenIdentifier13=tokenIdentifier();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setConnection(tokenIdentifier13);
                            }

            match(input,KEYWORD_URL,FOLLOW_KEYWORD_URL_in_createConnectionStatement2019); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2039);
            litURL=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setURL(litURL);
                            }

            match(input,KEYWORD_USER,FOLLOW_KEYWORD_USER_in_createConnectionStatement2059); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2079);
            litUser=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setUser(litUser);
                            }

            match(input,KEYWORD_PASSWD,FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement2099); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2119);
            litPasswd=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setPasswd(litPasswd);
                            }

            match(input,KEYWORD_STORE,FOLLOW_KEYWORD_STORE_in_createConnectionStatement2139); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2159);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:224:1: dropConnectionStatement returns [DropConnectionStmt stmt] : ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) ;
    public final FunSQLParser.dropConnectionStatement_return dropConnectionStatement() throws RecognitionException {
        FunSQLParser.dropConnectionStatement_return retval = new FunSQLParser.dropConnectionStatement_return();
        retval.start = input.LT(1);

        int dropConnectionStatement_StartIndex = input.index();

        TokenIdentifier tokenIdentifier14 =null;



                	retval.stmt = new DropConnectionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:228:9: ( ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:229:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:229:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:230:17: KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2246); if (state.failed) return retval;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2264); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenIdentifier_in_dropConnectionStatement2282);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:239:1: createTableStatement returns [CreateTableStmt stmt] : ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) ) ;
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

        TokenIdentifier c2 =null;

        TokenIdentifier c3 =null;

        String p2 =null;



                	retval.stmt = new CreateTableStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:243:9: ( ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:244:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:244:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:245:17: KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) )
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2362); if (state.failed) return retval;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2380); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_createTableStatement2400);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setTable(table1);
                            	retval.stmt.setSourceTable(table1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2420); if (state.failed) return retval;

            pushFollow(FOLLOW_identifierText_in_createTableStatement2440);
            att1=identifierText();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addAttribute(att1);
                            }

            pushFollow(FOLLOW_tokenDataType_in_createTableStatement2478);
            dataType1=tokenDataType();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addType(dataType1);
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:260:17: ( COMMA att2= identifierText dataType2= tokenDataType )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:261:17: COMMA att2= identifierText dataType2= tokenDataType
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2532); if (state.failed) return retval;

            	    pushFollow(FOLLOW_identifierText_in_createTableStatement2552);
            	    att2=identifierText();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addAttribute(att2);
            	                    }

            	    pushFollow(FOLLOW_tokenDataType_in_createTableStatement2590);
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
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2645); if (state.failed) return retval;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:272:17: ( KEYWORD_FROM table2= tokenTable )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_FROM) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:273:17: KEYWORD_FROM table2= tokenTable
                    {
                    match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_createTableStatement2681); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenTable_in_createTableStatement2701);
                    table2=tokenTable();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setSourceTable(table2);
                                    }

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:278:17: ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==KEYWORD_IN||LA14_0==KEYWORD_REPLICATED) ) {
                alt14=1;
            }
            else if ( (LA14_0==KEYWORD_PARTITIONED) ) {
                alt14=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:278:18: ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:278:18: ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) )
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
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:278:19: ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
                            {
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:278:19: ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:279:17: KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier
                            {
                            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2760); if (state.failed) return retval;

                            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2762); if (state.failed) return retval;

                            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2782);
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
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:283:19: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* )
                            {
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:283:19: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* )
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:284:17: KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )*
                            {
                            match(input,KEYWORD_REPLICATED,FOLLOW_KEYWORD_REPLICATED_in_createTableStatement2823); if (state.failed) return retval;

                            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2825); if (state.failed) return retval;

                            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2827); if (state.failed) return retval;

                            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2848);
                            connectionR1=tokenIdentifier();

                            state._fsp--;
                            if (state.failed) return retval;

                            if ( state.backtracking==0 ) {
                                            	retval.stmt.addConnection(connectionR1);
                                            }

                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:288:17: ( COMMA connectionR2= tokenIdentifier )*
                            loop5:
                            do {
                                int alt5=2;
                                int LA5_0 = input.LA(1);

                                if ( (LA5_0==COMMA) ) {
                                    alt5=1;
                                }


                                switch (alt5) {
                            	case 1 :
                            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:288:19: COMMA connectionR2= tokenIdentifier
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2870); if (state.failed) return retval;

                            	    pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2892);
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
                            } while (true);


                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:295:17: ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:295:17: ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:296:17: KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN
                    {
                    match(input,KEYWORD_PARTITIONED,FOLLOW_KEYWORD_PARTITIONED_in_createTableStatement2986); if (state.failed) return retval;

                    match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_createTableStatement2988); if (state.failed) return retval;

                    pushFollow(FOLLOW_identifierText_in_createTableStatement3009);
                    method=identifierText();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setPartitionMethod(method);
                                    }

                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:301:17: ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )?
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
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:302:17: LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN
                            {
                            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement3063); if (state.failed) return retval;

                            pushFollow(FOLLOW_identifierText_in_createTableStatement3083);
                            pcolumn1=identifierText();

                            state._fsp--;
                            if (state.failed) return retval;

                            if ( state.backtracking==0 ) {
                                            	retval.stmt.addPartitionColumn(pcolumn1);
                                            }

                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:307:17: ( COMMA pcolumn2= identifierText )*
                            loop7:
                            do {
                                int alt7=2;
                                int LA7_0 = input.LA(1);

                                if ( (LA7_0==COMMA) ) {
                                    alt7=1;
                                }


                                switch (alt7) {
                            	case 1 :
                            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:308:17: COMMA pcolumn2= identifierText
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3137); if (state.failed) return retval;

                            	    pushFollow(FOLLOW_identifierText_in_createTableStatement3157);
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
                            } while (true);


                            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement3212); if (state.failed) return retval;

                            }
                            break;

                    }


                    match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement3249); if (state.failed) return retval;

                    pushFollow(FOLLOW_identifierText_in_createTableStatement3269);
                    p1=identifierText();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.addPartition(p1);
                                    }

                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:321:17: ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) )
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==KEYWORD_IN) ) {
                        alt10=1;
                    }
                    else if ( (LA10_0==KEYWORD_REPLICATED) ) {
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
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:321:19: ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier )
                            {
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:321:19: ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier )
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:321:20: KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier
                            {
                            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3308); if (state.failed) return retval;

                            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3310); if (state.failed) return retval;

                            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3330);
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
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:325:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
                            {
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:325:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:325:18: KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )?
                            {
                            match(input,KEYWORD_REPLICATED,FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3353); if (state.failed) return retval;

                            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3355); if (state.failed) return retval;

                            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3357); if (state.failed) return retval;

                            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3378);
                            c2=tokenIdentifier();

                            state._fsp--;
                            if (state.failed) return retval;

                            if ( state.backtracking==0 ) {
                                            	retval.stmt.addPConnection(p1,c2);
                                            }

                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:329:17: ( COMMA c3= tokenIdentifier )?
                            int alt9=2;
                            int LA9_0 = input.LA(1);

                            if ( (LA9_0==COMMA) ) {
                                int LA9_1 = input.LA(2);

                                if ( (LA9_1==IDENTIFIER) ) {
                                    int LA9_3 = input.LA(3);

                                    if ( (LA9_3==COMMA||LA9_3==RPAREN) ) {
                                        alt9=1;
                                    }
                                }
                                else if ( (LA9_1==QUOTE_DOUBLE) ) {
                                    int LA9_4 = input.LA(3);

                                    if ( (LA9_4==IDENTIFIER) ) {
                                        int LA9_6 = input.LA(4);

                                        if ( (LA9_6==QUOTE_DOUBLE) ) {
                                            int LA9_7 = input.LA(5);

                                            if ( (LA9_7==COMMA||LA9_7==RPAREN) ) {
                                                alt9=1;
                                            }
                                        }
                                    }
                                }
                            }
                            switch (alt9) {
                                case 1 :
                                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:329:18: COMMA c3= tokenIdentifier
                                    {
                                    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3399); if (state.failed) return retval;

                                    pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3404);
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


                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:332:17: ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==COMMA) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:333:17: COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) )
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3446); if (state.failed) return retval;

                    	    pushFollow(FOLLOW_identifierText_in_createTableStatement3466);
                    	    p2=identifierText();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    if ( state.backtracking==0 ) {
                    	                    	retval.stmt.addPartition(p2);
                    	                    }

                    	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:338:16: ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) )
                    	    int alt12=2;
                    	    int LA12_0 = input.LA(1);

                    	    if ( (LA12_0==KEYWORD_IN) ) {
                    	        alt12=1;
                    	    }
                    	    else if ( (LA12_0==KEYWORD_REPLICATED) ) {
                    	        alt12=2;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return retval;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 12, 0, input);

                    	        throw nvae;

                    	    }
                    	    switch (alt12) {
                    	        case 1 :
                    	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:338:18: ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )
                    	            {
                    	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:338:18: ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )
                    	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:338:19: KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier
                    	            {
                    	            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3504); if (state.failed) return retval;

                    	            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3506); if (state.failed) return retval;

                    	            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3526);
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
                    	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:342:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
                    	            {
                    	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:342:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
                    	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:342:18: KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )?
                    	            {
                    	            match(input,KEYWORD_REPLICATED,FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3549); if (state.failed) return retval;

                    	            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3551); if (state.failed) return retval;

                    	            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3553); if (state.failed) return retval;

                    	            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3574);
                    	            c2=tokenIdentifier();

                    	            state._fsp--;
                    	            if (state.failed) return retval;

                    	            if ( state.backtracking==0 ) {
                    	                            	retval.stmt.addPConnection(p2,c2);
                    	                            }

                    	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:346:17: ( COMMA c3= tokenIdentifier )?
                    	            int alt11=2;
                    	            int LA11_0 = input.LA(1);

                    	            if ( (LA11_0==COMMA) ) {
                    	                int LA11_1 = input.LA(2);

                    	                if ( (LA11_1==IDENTIFIER) ) {
                    	                    int LA11_3 = input.LA(3);

                    	                    if ( (LA11_3==COMMA||LA11_3==RPAREN) ) {
                    	                        alt11=1;
                    	                    }
                    	                }
                    	                else if ( (LA11_1==QUOTE_DOUBLE) ) {
                    	                    int LA11_4 = input.LA(3);

                    	                    if ( (LA11_4==IDENTIFIER) ) {
                    	                        int LA11_6 = input.LA(4);

                    	                        if ( (LA11_6==QUOTE_DOUBLE) ) {
                    	                            int LA11_7 = input.LA(5);

                    	                            if ( (LA11_7==COMMA||LA11_7==RPAREN) ) {
                    	                                alt11=1;
                    	                            }
                    	                        }
                    	                    }
                    	                }
                    	            }
                    	            switch (alt11) {
                    	                case 1 :
                    	                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:346:18: COMMA c3= tokenIdentifier
                    	                    {
                    	                    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3595); if (state.failed) return retval;

                    	                    pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3600);
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
                    	    break loop13;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement3643); if (state.failed) return retval;

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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:357:1: dropTableStatement returns [DropTableStmt stmt] : ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) ;
    public final FunSQLParser.dropTableStatement_return dropTableStatement() throws RecognitionException {
        FunSQLParser.dropTableStatement_return retval = new FunSQLParser.dropTableStatement_return();
        retval.start = input.LT(1);

        int dropTableStatement_StartIndex = input.index();

        TokenTable table1 =null;



                	retval.stmt = new DropTableStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:361:9: ( ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:362:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:362:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:363:17: KEYWORD_DROP KEYWORD_TABLE table1= tokenTable
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement3757); if (state.failed) return retval;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement3775); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_dropTableStatement3795);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:371:1: createFunctionStatement returns [CreateFunctionStmt stmt] : ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI ) ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:375:9: ( ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:376:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:376:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:377:17: KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createFunctionStatement3861); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement3879); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_createFunctionStatement3899);
            function1=tokenFunction();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setFunction(function1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createFunctionStatement3915); if (state.failed) return retval;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:383:10: ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==KEYWORD_IN) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:384:17: KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA
            	    {
            	    match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createFunctionStatement3944); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement3964);
            	    var1=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                   		retval.stmt.addInParam(var1);
            	    				}

            	    match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3971); if (state.failed) return retval;

            	    match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement3977); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:391:3: ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:392:17: KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
            {
            match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement4015); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement4035);
            var2=tokenVariable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                           		retval.stmt.addOutParam(var2);
            				}

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4042); if (state.failed) return retval;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:398:3: ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==COMMA) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:399:4: COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement4055); if (state.failed) return retval;

            	    match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement4060); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement4074);
            	    var2=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	               		retval.stmt.addOutParam(var2);
            	    			}

            	    match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4080); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createFunctionStatement4089); if (state.failed) return retval;

            match(input,KEYWORD_BEGIN,FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement4093); if (state.failed) return retval;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:408:3: ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )*
            loop17:
            do {
                int alt17=3;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==COLON||LA17_0==KEYWORD_VAR) ) {
                    alt17=1;
                }
                else if ( (LA17_0==KEYWORD_CALL) ) {
                    alt17=2;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:409:3: (ass1= tokenAssignment )
            	    {
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:409:3: (ass1= tokenAssignment )
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:410:3: ass1= tokenAssignment
            	    {
            	    pushFollow(FOLLOW_tokenAssignment_in_createFunctionStatement4107);
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
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:416:17: (call1= tokenFunctionCall )
            	    {
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:416:17: (call1= tokenFunctionCall )
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:417:17: call1= tokenFunctionCall
            	    {
            	    pushFollow(FOLLOW_tokenFunctionCall_in_createFunctionStatement4186);
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
            	    break loop17;
                }
            } while (true);


            match(input,KEYWORD_END,FOLLOW_KEYWORD_END_in_createFunctionStatement4228); if (state.failed) return retval;

            match(input,SEMI,FOLLOW_SEMI_in_createFunctionStatement4235); if (state.failed) return retval;

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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:427:1: dropFunctionStatement returns [DropFunctionStmt stmt] : ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) ;
    public final FunSQLParser.dropFunctionStatement_return dropFunctionStatement() throws RecognitionException {
        FunSQLParser.dropFunctionStatement_return retval = new FunSQLParser.dropFunctionStatement_return();
        retval.start = input.LT(1);

        int dropFunctionStatement_StartIndex = input.index();

        TokenFunction fun1 =null;



                	retval.stmt = new DropFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:431:9: ( ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:432:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:432:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:433:17: KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropFunctionStatement4312); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement4330); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_dropFunctionStatement4350);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:441:1: callFunctionStatement returns [CallFunctionStmt stmt] : ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) ;
    public final FunSQLParser.callFunctionStatement_return callFunctionStatement() throws RecognitionException {
        FunSQLParser.callFunctionStatement_return retval = new FunSQLParser.callFunctionStatement_return();
        retval.start = input.LT(1);

        int callFunctionStatement_StartIndex = input.index();

        TokenFunction fun1 =null;



                	retval.stmt = new CallFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:445:9: ( ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:446:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:446:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:447:17: KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction
            {
            match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_callFunctionStatement4416); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement4434); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_callFunctionStatement4454);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:455:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:460:9: ( ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:461:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:461:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:463:17: KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )?
            {
            match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement4531); if (state.failed) return retval;

            pushFollow(FOLLOW_abstractExpression_in_selectStatement4551);
            selExpr1=abstractExpression();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addSelExpression(selExpr1);
                            	++i;
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:469:17: ( KEYWORD_AS selAlias1= tokenIdentifier )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEYWORD_AS) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:470:18: KEYWORD_AS selAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4606); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4627);
                    selAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    		retval.stmt.setSelAlias(i-1, selAlias1);
                                    	}

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:476:17: ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==COMMA) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:477:17: COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement4701); if (state.failed) return retval;

            	    pushFollow(FOLLOW_abstractExpression_in_selectStatement4721);
            	    selExpr2=abstractExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addSelExpression(selExpr2);
            	                    	++i;
            	                    }

            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:483:17: ( KEYWORD_AS selAlias2= tokenIdentifier )?
            	    int alt19=2;
            	    int LA19_0 = input.LA(1);

            	    if ( (LA19_0==KEYWORD_AS) ) {
            	        alt19=1;
            	    }
            	    switch (alt19) {
            	        case 1 :
            	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:484:18: KEYWORD_AS selAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4776); if (state.failed) return retval;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4797);
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
            	    break loop20;
                }
            } while (true);


            match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement4906); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_selectStatement4926);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addTable(table1);
                            	i=1;
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:499:17: ( KEYWORD_AS tableAlias1= tokenIdentifier )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==KEYWORD_AS) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:500:18: KEYWORD_AS tableAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4982); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement5003);
                    tableAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    		retval.stmt.setTableAlias(i-1, tableAlias1);
                                    	}

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:506:17: ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==COMMA) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:507:17: COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement5077); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenTable_in_selectStatement5097);
            	    table2=tokenTable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addTable(table2);
            	                    	++i;
            	                    }

            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:513:17: ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    int alt22=2;
            	    int LA22_0 = input.LA(1);

            	    if ( (LA22_0==KEYWORD_AS) ) {
            	        alt22=1;
            	    }
            	    switch (alt22) {
            	        case 1 :
            	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:514:18: KEYWORD_AS tableAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement5152); if (state.failed) return retval;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement5173);
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
            	    break loop23;
                }
            } while (true);


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:522:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==KEYWORD_WHERE) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:523:17: KEYWORD_WHERE predicate1= abstractPredicate
                    {
                    match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement5283); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement5303);
                    predicate1=abstractPredicate();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setWherePredicate(predicate1);
                                    }

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:531:17: ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==KEYWORD_GROUP) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:532:17: KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )*
                    {
                    match(input,KEYWORD_GROUP,FOLLOW_KEYWORD_GROUP_in_selectStatement5410); if (state.failed) return retval;

                    match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_selectStatement5412); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractExpression_in_selectStatement5432);
                    groupExpr1=abstractExpression();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.addGroupExpression(groupExpr1);
                                    	++i;
                                    }

                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:538:17: ( COMMA groupExpr2= abstractExpression )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==COMMA) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:539:17: COMMA groupExpr2= abstractExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement5486); if (state.failed) return retval;

                    	    pushFollow(FOLLOW_abstractExpression_in_selectStatement5506);
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
                    	    break loop25;
                        }
                    } while (true);


                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:549:17: ( KEYWORD_HAVING havingPred= abstractPredicate )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==KEYWORD_HAVING) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:550:17: KEYWORD_HAVING havingPred= abstractPredicate
                    {
                    match(input,KEYWORD_HAVING,FOLLOW_KEYWORD_HAVING_in_selectStatement5632); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement5652);
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



    // $ANTLR start "abstractPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:560:1: abstractPredicate returns [AbstractPredicate predicate] : predicate1= complexPredicateOr ;
    public final AbstractPredicate abstractPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int abstractPredicate_StartIndex = input.index();

        ComplexPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:561:2: (predicate1= complexPredicateOr )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:562:3: predicate1= complexPredicateOr
            {
            pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate5714);
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
            if ( state.backtracking>0 ) { memoize(input, 12, abstractPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "abstractPredicate"



    // $ANTLR start "complexPredicateOr"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:567:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
    public final ComplexPredicate complexPredicateOr() throws RecognitionException {
        ComplexPredicate predicateOr = null;

        int complexPredicateOr_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return predicateOr; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:571:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:572:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:572:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:573:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
            {
            pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr5752);
            predicate1=complexPredicateAnd();

            state._fsp--;
            if (state.failed) return predicateOr;

            if ( state.backtracking==0 ) {
            			predicateOr.setPredicate1(predicate1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:576:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==KEYWORD_OR) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:577:4: KEYWORD_OR predicate2= complexPredicateAnd
            	    {
            	    match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr5763); if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addOr();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr5771);
            	    predicate2=complexPredicateAnd();

            	    state._fsp--;
            	    if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            }


            }

        }

            catch (RecognitionException e) {
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:587:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
    public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
        ComplexPredicate predicateAnd = null;

        int complexPredicateAnd_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return predicateAnd; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:591:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:592:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:592:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:593:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
            {
            pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd5810);
            predicate1=complexPredicateNot();

            state._fsp--;
            if (state.failed) return predicateAnd;

            if ( state.backtracking==0 ) {
            			predicateAnd.setPredicate1(predicate1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:596:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==KEYWORD_AND) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:597:4: KEYWORD_AND predicate2= complexPredicateNot
            	    {
            	    match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd5821); if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addAnd();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd5829);
            	    predicate2=complexPredicateNot();

            	    state._fsp--;
            	    if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);


            }


            }

        }

            catch (RecognitionException e) {
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:607:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
    public final ComplexPredicate complexPredicateNot() throws RecognitionException {
        ComplexPredicate predicateNot = null;

        int complexPredicateNot_StartIndex = input.index();

        AbstractPredicate predicate1 =null;



                	predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return predicateNot; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:611:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:612:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:612:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:613:3: ( KEYWORD_NOT )? predicate1= complexPredicate
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:613:3: ( KEYWORD_NOT )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==KEYWORD_NOT) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:614:3: KEYWORD_NOT
                    {
                    match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot5870); if (state.failed) return predicateNot;

                    if ( state.backtracking==0 ) {
                    			predicateNot.negate();
                    		}

                    }
                    break;

            }


            pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot5883);
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
            if ( state.backtracking>0 ) { memoize(input, 15, complexPredicateNot_StartIndex); }

        }
        return predicateNot;
    }
    // $ANTLR end "complexPredicateNot"



    // $ANTLR start "complexPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:624:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
    public final AbstractPredicate complexPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int complexPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;

        SimplePredicate predicate2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:625:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:626:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:626:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==LPAREN) ) {
                int LA31_1 = input.LA(2);

                if ( (synpred40_FunSQL()) ) {
                    alt31=1;
                }
                else if ( (true) ) {
                    alt31=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA31_0==FUNCTION_AGGREGATION||LA31_0==IDENTIFIER||(LA31_0 >= LITERAL_DECIMAL && LA31_0 <= LITERAL_STRING)||LA31_0==MINUS||LA31_0==PLUS||LA31_0==QUOTE_DOUBLE||LA31_0==TYPE_DATE) ) {
                alt31=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return predicate;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }
            switch (alt31) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:627:3: predicate1= parenPredicate
                    {
                    pushFollow(FOLLOW_parenPredicate_in_complexPredicate5910);
                    predicate1=parenPredicate();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate1;
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:632:3: predicate2= simplePredicate
                    {
                    pushFollow(FOLLOW_simplePredicate_in_complexPredicate5925);
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
            if ( state.backtracking>0 ) { memoize(input, 16, complexPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "complexPredicate"



    // $ANTLR start "parenPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:639:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
    public final AbstractPredicate parenPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int parenPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:640:2: ( LPAREN predicate1= abstractPredicate RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:641:3: LPAREN predicate1= abstractPredicate RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate5950); if (state.failed) return predicate;

            pushFollow(FOLLOW_abstractPredicate_in_parenPredicate5957);
            predicate1=abstractPredicate();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
            			predicate = predicate1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate5963); if (state.failed) return predicate;

            }

        }

            catch (RecognitionException e) {
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:648:1: simplePredicate returns [SimplePredicate predicate] : (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) ;
    public final SimplePredicate simplePredicate() throws RecognitionException {
        SimplePredicate predicate = null;

        int simplePredicate_StartIndex = input.index();

        AbstractExpression expr1 =null;

        FunSQLParser.tokenCompOperator_return comp =null;

        AbstractExpression expr2 =null;



                	predicate = new SimplePredicate();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:652:9: ( (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:653:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:653:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:654:3: expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression
            {
            pushFollow(FOLLOW_abstractExpression_in_simplePredicate6005);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setExpr1(expr1);
                            }

            pushFollow(FOLLOW_tokenCompOperator_in_simplePredicate6060);
            comp=tokenCompOperator();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setComp(EnumCompOperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
                            }

            pushFollow(FOLLOW_abstractExpression_in_simplePredicate6098);
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
            if ( state.backtracking>0 ) { memoize(input, 18, simplePredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "simplePredicate"



    // $ANTLR start "abstractExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:671:1: abstractExpression returns [AbstractExpression expression] : expression1= complexExpressionAdd ;
    public final AbstractExpression abstractExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int abstractExpression_StartIndex = input.index();

        ComplexExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:672:2: (expression1= complexExpressionAdd )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:673:2: expression1= complexExpressionAdd
            {
            pushFollow(FOLLOW_complexExpressionAdd_in_abstractExpression6152);
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
            if ( state.backtracking>0 ) { memoize(input, 19, abstractExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "abstractExpression"



    // $ANTLR start "complexExpressionAdd"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:678:1: complexExpressionAdd returns [ComplexExpression expression] : (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) ;
    public final ComplexExpression complexExpressionAdd() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionAdd_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenAddOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:682:2: ( (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:683:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:683:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:684:3: expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )*
            {
            pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd6182);
            expression1=complexExpressionMult();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:687:3: (op1= tokenAddOperator expression2= complexExpressionMult )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==MINUS||LA32_0==PLUS) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:688:4: op1= tokenAddOperator expression2= complexExpressionMult
            	    {
            	    pushFollow(FOLLOW_tokenAddOperator_in_complexExpressionAdd6195);
            	    op1=tokenAddOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd6203);
            	    expression2=complexExpressionMult();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            }


            }

        }

            catch (RecognitionException e) {
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:698:1: complexExpressionMult returns [ComplexExpression expression] : (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) ;
    public final ComplexExpression complexExpressionMult() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionMult_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenMultOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:702:2: ( (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:703:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:703:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:704:3: expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )*
            {
            pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult6243);
            expression1=complexExpressionSigned();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:707:3: (op1= tokenMultOperator expression2= complexExpressionSigned )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==DIV||LA33_0==MULT) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:708:4: op1= tokenMultOperator expression2= complexExpressionSigned
            	    {
            	    pushFollow(FOLLOW_tokenMultOperator_in_complexExpressionMult6256);
            	    op1=tokenMultOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult6264);
            	    expression2=complexExpressionSigned();

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
            } while (true);


            }


            }

        }

            catch (RecognitionException e) {
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:719:1: complexExpressionSigned returns [ComplexExpression expression] : ( ( MINUS | PLUS )? expression1= complexExpression ) ;
    public final ComplexExpression complexExpressionSigned() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionSigned_StartIndex = input.index();

        AbstractExpression expression1 =null;



                	expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:723:2: ( ( ( MINUS | PLUS )? expression1= complexExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:724:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:724:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:725:3: ( MINUS | PLUS )? expression1= complexExpression
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:725:3: ( MINUS | PLUS )?
            int alt34=3;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==MINUS) ) {
                alt34=1;
            }
            else if ( (LA34_0==PLUS) ) {
                alt34=2;
            }
            switch (alt34) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:726:3: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_complexExpressionSigned6306); if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression.negate();
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:730:3: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_complexExpressionSigned6315); if (state.failed) return expression;

                    }
                    break;

            }


            pushFollow(FOLLOW_complexExpression_in_complexExpressionSigned6327);
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
            if ( state.backtracking>0 ) { memoize(input, 22, complexExpressionSigned_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionSigned"



    // $ANTLR start "complexExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:738:1: complexExpression returns [AbstractExpression expression] : (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) ;
    public final AbstractExpression complexExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int complexExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;

        AggregationExpression expression2 =null;

        SimpleExpression expression3 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:739:2: ( (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:740:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:740:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
            int alt35=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt35=1;
                }
                break;
            case FUNCTION_AGGREGATION:
                {
                alt35=2;
                }
                break;
            case IDENTIFIER:
            case LITERAL_DECIMAL:
            case LITERAL_INTEGER:
            case LITERAL_STRING:
            case QUOTE_DOUBLE:
            case TYPE_DATE:
                {
                alt35=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }

            switch (alt35) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:741:3: expression1= parenExpression
                    {
                    pushFollow(FOLLOW_parenExpression_in_complexExpression6362);
                    expression1=parenExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression1;
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:746:3: expression2= aggregationExpression
                    {
                    pushFollow(FOLLOW_aggregationExpression_in_complexExpression6377);
                    expression2=aggregationExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression2;
                    		}

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:751:3: expression3= simpleExpression
                    {
                    pushFollow(FOLLOW_simpleExpression_in_complexExpression6392);
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
            if ( state.backtracking>0 ) { memoize(input, 23, complexExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpression"



    // $ANTLR start "parenExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:758:1: parenExpression returns [AbstractExpression expression] : LPAREN expression1= abstractExpression RPAREN ;
    public final AbstractExpression parenExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int parenExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:759:2: ( LPAREN expression1= abstractExpression RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:760:3: LPAREN expression1= abstractExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenExpression6417); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_parenExpression6424);
            expression1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression = expression1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenExpression6430); if (state.failed) return expression;

            }

        }

            catch (RecognitionException e) {
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:768:1: aggregationExpression returns [AggregationExpression expression] : (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) ;
    public final AggregationExpression aggregationExpression() throws RecognitionException {
        AggregationExpression expression = null;

        int aggregationExpression_StartIndex = input.index();

        Token agg1=null;
        AbstractExpression expr1 =null;



                	expression = new AggregationExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:772:9: ( (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:773:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:773:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:774:3: agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN
            {
            agg1=(Token)match(input,FUNCTION_AGGREGATION,FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression6482); if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setAggregation((agg1!=null?agg1.getText():null).toUpperCase());
            		}

            match(input,LPAREN,FOLLOW_LPAREN_in_aggregationExpression6490); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_aggregationExpression6497);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpression(expr1);
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_aggregationExpression6503); if (state.failed) return expression;

            }


            }

        }

            catch (RecognitionException e) {
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:786:1: simpleExpression returns [SimpleExpression expression] : ( (att1= tokenAttribute |lit1= tokenLiteral ) ) ;
    public final SimpleExpression simpleExpression() throws RecognitionException {
        SimpleExpression expression = null;

        int simpleExpression_StartIndex = input.index();

        TokenAttribute att1 =null;

        TokenLiteral lit1 =null;



                	expression = new SimpleExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:790:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:791:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:791:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:792:3: (att1= tokenAttribute |lit1= tokenLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:792:3: (att1= tokenAttribute |lit1= tokenLiteral )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==IDENTIFIER||LA36_0==QUOTE_DOUBLE) ) {
                alt36=1;
            }
            else if ( ((LA36_0 >= LITERAL_DECIMAL && LA36_0 <= LITERAL_STRING)||LA36_0==TYPE_DATE) ) {
                alt36=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;

            }
            switch (alt36) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:793:3: att1= tokenAttribute
                    {
                    pushFollow(FOLLOW_tokenAttribute_in_simpleExpression6578);
                    att1=tokenAttribute();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                                    	expression.setOper(att1);
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:798:17: lit1= tokenLiteral
                    {
                    pushFollow(FOLLOW_tokenLiteral_in_simpleExpression6634);
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
            if ( state.backtracking>0 ) { memoize(input, 26, simpleExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "simpleExpression"



    // $ANTLR start "tokenAttribute"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:806:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenAttribute tokenAttribute() throws RecognitionException {
        TokenAttribute attribute = null;

        int tokenAttribute_StartIndex = input.index();

        TokenIdentifier table1 =null;

        TokenIdentifier id1 =null;



                	attribute = new TokenAttribute();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return attribute; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:810:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:811:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:811:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:812:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:812:17: (table1= tokenIdentifier DOT )?
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:813:17: table1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute6762);
                    table1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return attribute;

                    if ( state.backtracking==0 ) {
                                    	TokenTable table = new TokenTable();
                                    	table.setName(table1);
                                    	attribute.setTable(table);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenAttribute6782); if (state.failed) return attribute;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute6821);
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
            if ( state.backtracking>0 ) { memoize(input, 27, tokenAttribute_StartIndex); }

        }
        return attribute;
    }
    // $ANTLR end "tokenAttribute"



    // $ANTLR start "tokenTable"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:826:1: tokenTable returns [TokenTable table] : ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) );
    public final TokenTable tokenTable() throws RecognitionException {
        TokenTable table = null;

        int tokenTable_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	table = new TokenTable();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return table; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:830:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) )
            int alt40=2;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                int LA40_1 = input.LA(2);

                if ( (synpred50_FunSQL()) ) {
                    alt40=1;
                }
                else if ( (true) ) {
                    alt40=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return table;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 1, input);

                    throw nvae;

                }
                }
                break;
            case QUOTE_DOUBLE:
                {
                int LA40_2 = input.LA(2);

                if ( (LA40_2==IDENTIFIER) ) {
                    int LA40_5 = input.LA(3);

                    if ( (LA40_5==QUOTE_DOUBLE) ) {
                        int LA40_6 = input.LA(4);

                        if ( (synpred50_FunSQL()) ) {
                            alt40=1;
                        }
                        else if ( (true) ) {
                            alt40=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return table;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 40, 6, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return table;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 40, 5, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return table;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 2, input);

                    throw nvae;

                }
                }
                break;
            case COLON:
                {
                alt40=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return table;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }

            switch (alt40) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:831:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:831:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:832:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:832:17: (schema1= tokenIdentifier DOT )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==IDENTIFIER) ) {
                        int LA38_1 = input.LA(2);

                        if ( (LA38_1==DOT) ) {
                            alt38=1;
                        }
                    }
                    else if ( (LA38_0==QUOTE_DOUBLE) ) {
                        int LA38_2 = input.LA(2);

                        if ( (LA38_2==IDENTIFIER) ) {
                            int LA38_5 = input.LA(3);

                            if ( (LA38_5==QUOTE_DOUBLE) ) {
                                int LA38_6 = input.LA(4);

                                if ( (LA38_6==DOT) ) {
                                    alt38=1;
                                }
                            }
                        }
                    }
                    switch (alt38) {
                        case 1 :
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:833:17: schema1= tokenIdentifier DOT
                            {
                            pushFollow(FOLLOW_tokenIdentifier_in_tokenTable6944);
                            schema1=tokenIdentifier();

                            state._fsp--;
                            if (state.failed) return table;

                            if ( state.backtracking==0 ) {
                                                    TokenSchema schema = new TokenSchema();
                                            	table.setSchema(schema);
                                            	table.setVariable(false);
                                            }

                            match(input,DOT,FOLLOW_DOT_in_tokenTable6964); if (state.failed) return table;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable7021);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:843:12: ( ( COLON )? id1= tokenIdentifier )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:843:12: ( ( COLON )? id1= tokenIdentifier )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:844:17: ( COLON )? id1= tokenIdentifier
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:844:17: ( COLON )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==COLON) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:845:17: COLON
                            {
                            match(input,COLON,FOLLOW_COLON_in_tokenTable7088); if (state.failed) return table;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable7130);
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
            if ( state.backtracking>0 ) { memoize(input, 28, tokenTable_StartIndex); }

        }
        return table;
    }
    // $ANTLR end "tokenTable"



    // $ANTLR start "tokenSchema"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:854:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
    public final TokenSchema tokenSchema() throws RecognitionException {
        TokenSchema schema = null;

        int tokenSchema_StartIndex = input.index();

        TokenIdentifier tokenIdentifier15 =null;



                	schema = new TokenSchema();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return schema; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:858:9: ( ( tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:859:9: ( tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:859:9: ( tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:860:17: tokenIdentifier
            {
            pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema7211);
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
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:866:1: tokenFunction returns [TokenFunction function] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenFunction tokenFunction() throws RecognitionException {
        TokenFunction function = null;

        int tokenFunction_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	function = new TokenFunction();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return function; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:870:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:871:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:871:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:872:33: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:872:33: (schema1= tokenIdentifier DOT )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==IDENTIFIER) ) {
                int LA41_1 = input.LA(2);

                if ( (LA41_1==DOT) ) {
                    alt41=1;
                }
            }
            else if ( (LA41_0==QUOTE_DOUBLE) ) {
                int LA41_2 = input.LA(2);

                if ( (LA41_2==IDENTIFIER) ) {
                    int LA41_5 = input.LA(3);

                    if ( (LA41_5==QUOTE_DOUBLE) ) {
                        int LA41_6 = input.LA(4);

                        if ( (LA41_6==DOT) ) {
                            alt41=1;
                        }
                    }
                }
            }
            switch (alt41) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:873:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction7327);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return function;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	function.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenFunction7347); if (state.failed) return function;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction7386);
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
            if ( state.backtracking>0 ) { memoize(input, 30, tokenFunction_StartIndex); }

        }
        return function;
    }
    // $ANTLR end "tokenFunction"



    // $ANTLR start "tokenVariable"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:885:1: tokenVariable returns [TokenVariable variable] : ( variableText ) ;
    public final TokenVariable tokenVariable() throws RecognitionException {
        TokenVariable variable = null;

        int tokenVariable_StartIndex = input.index();

        String variableText16 =null;



                	variable = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return variable; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:889:9: ( ( variableText ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:890:9: ( variableText )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:890:9: ( variableText )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:891:17: variableText
            {
            pushFollow(FOLLOW_variableText_in_tokenVariable7466);
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
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:897:1: tokenAssignment returns [TokenAssignment ass] : ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI ;
    public final TokenAssignment tokenAssignment() throws RecognitionException {
        TokenAssignment ass = null;

        int tokenAssignment_StartIndex = input.index();

        TokenVariable var1 =null;

        FunSQLParser.selectStatement_return selstmt1 =null;

        TokenVariable var2 =null;

        FunSQLParser.selectStatement_return selstmt2 =null;

        TokenVariable var3 =null;

        TokenVariable var4 =null;



        	 	ass =new TokenAssignment();
        	 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return ass; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:901:3: ( ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:902:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:902:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable )
            int alt42=3;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==COLON) ) {
                alt42=1;
            }
            else if ( (LA42_0==KEYWORD_VAR) ) {
                int LA42_2 = input.LA(2);

                if ( (LA42_2==IDENTIFIER) ) {
                    int LA42_3 = input.LA(3);

                    if ( (LA42_3==EQUAL1) ) {
                        int LA42_4 = input.LA(4);

                        if ( (LA42_4==COLON) ) {
                            alt42=3;
                        }
                        else if ( (LA42_4==KEYWORD_SELECT) ) {
                            alt42=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return ass;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 42, 4, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ass;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 42, 3, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ass;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ass;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }
            switch (alt42) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:903:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:903:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:904:4: COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement
                    {
                    match(input,COLON,FOLLOW_COLON_in_tokenAssignment7533); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7545);
                    var1=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setReference(true);
                    		 ass.setVar(var1);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7551); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment7558);
                    selstmt1=selectStatement();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setSelStmt((selstmt1!=null?selstmt1.stmt:null));
                    		 }

                    }


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:915:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:915:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:916:4: KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement
                    {
                    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment7579); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7588);
                    var2=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {		 
                    		 ass.setReference(false);
                    		 ass.setVar(var2);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7594); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment7601);
                    selstmt2=selectStatement();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setSelStmt((selstmt2!=null?selstmt2.stmt:null));
                    		 }

                    }


                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:927:4: ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:927:4: ( KEYWORD_VAR var3= tokenVariable EQUAL1 )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:928:4: KEYWORD_VAR var3= tokenVariable EQUAL1
                    {
                    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment7625); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7634);
                    var3=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {		 
                    		 ass.setReference(false);
                    		 ass.setVar(var3);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7640); if (state.failed) return ass;

                    }


                    match(input,COLON,FOLLOW_COLON_in_tokenAssignment7651); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7663);
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


            match(input,SEMI,FOLLOW_SEMI_in_tokenAssignment7674); if (state.failed) return ass;

            }

        }

            catch (RecognitionException e) {
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:944:1: tokenFunctionCall returns [TokenFunctionCall call] : ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI ) ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:948:3: ( ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:949:3: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:949:3: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:950:4: KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI
            {
            match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_tokenFunctionCall7706); if (state.failed) return call;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_tokenFunctionCall7711); if (state.failed) return call;

            pushFollow(FOLLOW_tokenFunction_in_tokenFunctionCall7718);
            fun1=tokenFunction();

            state._fsp--;
            if (state.failed) return call;

            if ( state.backtracking==0 ) {
            		 call.setFun(fun1);
            		 }

            match(input,LPAREN,FOLLOW_LPAREN_in_tokenFunctionCall7724); if (state.failed) return call;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:956:4: ( COLON var1= tokenVariable )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==COLON) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:957:5: COLON var1= tokenVariable
            	    {
            	    match(input,COLON,FOLLOW_COLON_in_tokenFunctionCall7735); if (state.failed) return call;

            	    pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall7748);
            	    var1=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return call;

            	    if ( state.backtracking==0 ) {
            	    			 call.addInVar(var1);
            	    			 }

            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:962:4: ( KEYWORD_VAR var2= tokenVariable COMMA )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==KEYWORD_VAR) ) {
                    int LA44_1 = input.LA(2);

                    if ( (LA44_1==IDENTIFIER) ) {
                        int LA44_2 = input.LA(3);

                        if ( (LA44_2==COMMA) ) {
                            alt44=1;
                        }


                    }


                }


                switch (alt44) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:963:5: KEYWORD_VAR var2= tokenVariable COMMA
            	    {
            	    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7769); if (state.failed) return call;

            	    pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall7779);
            	    var2=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return call;

            	    if ( state.backtracking==0 ) {		
            	    			 call.addOutVar(var2);
            	    			 }

            	    match(input,COMMA,FOLLOW_COMMA_in_tokenFunctionCall7786); if (state.failed) return call;

            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:969:4: ( KEYWORD_VAR var3= tokenVariable )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:970:5: KEYWORD_VAR var3= tokenVariable
            {
            match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7808); if (state.failed) return call;

            pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall7818);
            var3=tokenVariable();

            state._fsp--;
            if (state.failed) return call;

            if ( state.backtracking==0 ) {		
            			 call.addOutVar(var3);
            			 }

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_tokenFunctionCall7829); if (state.failed) return call;

            match(input,SEMI,FOLLOW_SEMI_in_tokenFunctionCall7834); if (state.failed) return call;

            }


            }

        }

            catch (RecognitionException e) {
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:980:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
    public final TokenIdentifier tokenIdentifier() throws RecognitionException {
        TokenIdentifier identifier = null;

        int tokenIdentifier_StartIndex = input.index();

        String identifierText17 =null;



                	identifier = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return identifier; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:984:9: ( ( identifierText ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:985:9: ( identifierText )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:985:9: ( identifierText )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:986:17: identifierText
            {
            pushFollow(FOLLOW_identifierText_in_tokenIdentifier7902);
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
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:993:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:997:9: ( ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:998:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:998:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
            int alt45=4;
            switch ( input.LA(1) ) {
            case TYPE_VARCHAR:
                {
                alt45=1;
                }
                break;
            case TYPE_INTEGER:
                {
                alt45=2;
                }
                break;
            case TYPE_DECIMAL:
                {
                alt45=3;
                }
                break;
            case TYPE_DATE:
                {
                alt45=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return dataType;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }

            switch (alt45) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:999:17: TYPE_VARCHAR
                    {
                    TYPE_VARCHAR18=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType7983); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_VARCHAR18!=null?TYPE_VARCHAR18.getText():null));
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1003:17: TYPE_INTEGER
                    {
                    TYPE_INTEGER19=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType8021); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_INTEGER19!=null?TYPE_INTEGER19.getText():null));
                                    }

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1007:17: TYPE_DECIMAL
                    {
                    TYPE_DECIMAL20=(Token)match(input,TYPE_DECIMAL,FOLLOW_TYPE_DECIMAL_in_tokenDataType8059); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_DECIMAL20!=null?TYPE_DECIMAL20.getText():null));
                                    }

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1011:17: TYPE_DATE
                    {
                    TYPE_DATE21=(Token)match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDataType8097); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_DATE21!=null?TYPE_DATE21.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 35, tokenDataType_StartIndex); }

        }
        return dataType;
    }
    // $ANTLR end "tokenDataType"



    // $ANTLR start "tokenLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1017:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1021:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1022:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1022:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1023:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1023:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            int alt46=4;
            switch ( input.LA(1) ) {
            case LITERAL_INTEGER:
                {
                alt46=1;
                }
                break;
            case LITERAL_STRING:
                {
                alt46=2;
                }
                break;
            case LITERAL_DECIMAL:
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
                if (state.backtracking>0) {state.failed=true; return literal;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }

            switch (alt46) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1024:17: tokenIntegerLiteral
                    {
                    pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral8188);
                    tokenIntegerLiteral22=tokenIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenIntegerLiteral22;
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1028:17: tokenStringLiteral
                    {
                    pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral8226);
                    tokenStringLiteral23=tokenStringLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenStringLiteral23;
                                    }

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1032:17: tokenDecimalLiteral
                    {
                    pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral8264);
                    tokenDecimalLiteral24=tokenDecimalLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenDecimalLiteral24;
                                    }

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1036:17: tokenDateLiteral
                    {
                    pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral8302);
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
            	reportError(e);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1043:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
    public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
        TokenStringLiteral literal = null;

        int tokenStringLiteral_StartIndex = input.index();

        Token lit1=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1047:9: ( (lit1= LITERAL_STRING ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1048:9: (lit1= LITERAL_STRING )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1048:9: (lit1= LITERAL_STRING )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1049:17: lit1= LITERAL_STRING
            {
            lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral8402); if (state.failed) return literal;

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
            if ( state.backtracking>0 ) { memoize(input, 37, tokenStringLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenStringLiteral"



    // $ANTLR start "tokenIntegerLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1055:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
    public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
        TokenIntegerLiteral literal = null;

        int tokenIntegerLiteral_StartIndex = input.index();

        Token LITERAL_INTEGER26=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1059:9: ( ( LITERAL_INTEGER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1060:9: ( LITERAL_INTEGER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1060:9: ( LITERAL_INTEGER )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1061:17: LITERAL_INTEGER
            {
            LITERAL_INTEGER26=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral8482); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenIntegerLiteral((LITERAL_INTEGER26!=null?LITERAL_INTEGER26.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 38, tokenIntegerLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenIntegerLiteral"



    // $ANTLR start "tokenDecimalLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1068:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
    public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
        TokenDecimalLiteral literal = null;

        int tokenDecimalLiteral_StartIndex = input.index();

        Token LITERAL_DECIMAL27=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1072:9: ( ( LITERAL_DECIMAL ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1073:9: ( LITERAL_DECIMAL )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1073:9: ( LITERAL_DECIMAL )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1074:17: LITERAL_DECIMAL
            {
            LITERAL_DECIMAL27=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral8572); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenDecimalLiteral((LITERAL_DECIMAL27!=null?LITERAL_DECIMAL27.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 39, tokenDecimalLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenDecimalLiteral"



    // $ANTLR start "tokenDateLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1080:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( TYPE_DATE LITERAL_STRING ) ;
    public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
        TokenDateLiteral literal = null;

        int tokenDateLiteral_StartIndex = input.index();

        Token LITERAL_STRING28=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1084:9: ( ( TYPE_DATE LITERAL_STRING ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1085:9: ( TYPE_DATE LITERAL_STRING )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1085:9: ( TYPE_DATE LITERAL_STRING )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1087:17: TYPE_DATE LITERAL_STRING
            {
            match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDateLiteral8670); if (state.failed) return literal;

            LITERAL_STRING28=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenDateLiteral8688); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenDateLiteral((LITERAL_STRING28!=null?LITERAL_STRING28.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 40, tokenDateLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenDateLiteral"



    // $ANTLR start "variableText"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1094:2: variableText returns [String text] : (var1= IDENTIFIER ) ;
    public final String variableText() throws RecognitionException {
        String text = null;

        int variableText_StartIndex = input.index();

        Token var1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return text; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1095:3: ( (var1= IDENTIFIER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1096:3: (var1= IDENTIFIER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1096:3: (var1= IDENTIFIER )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1098:4: var1= IDENTIFIER
            {
            var1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variableText8747); if (state.failed) return text;

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
            if ( state.backtracking>0 ) { memoize(input, 41, variableText_StartIndex); }

        }
        return text;
    }
    // $ANTLR end "variableText"



    // $ANTLR start "identifierText"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1104:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) ;
    public final String identifierText() throws RecognitionException {
        String text = null;

        int identifierText_StartIndex = input.index();

        Token id1=null;
        Token id2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return text; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1105:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1106:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1106:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==IDENTIFIER) ) {
                alt47=1;
            }
            else if ( (LA47_0==QUOTE_DOUBLE) ) {
                alt47=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return text;}
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;

            }
            switch (alt47) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1107:4: (id1= IDENTIFIER )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1107:4: (id1= IDENTIFIER )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1108:4: id1= IDENTIFIER
                    {
                    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText8799); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id1!=null?id1.getText():null).toUpperCase();
                                    }

                    }


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1113:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1113:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1114:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText8855); if (state.failed) return text;

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText8871); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id2!=null?id2.getText():null);
                                    }

                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText8891); if (state.failed) return text;

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
            if ( state.backtracking>0 ) { memoize(input, 42, identifierText_StartIndex); }

        }
        return text;
    }
    // $ANTLR end "identifierText"


    public static class tokenAddOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenAddOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1123:1: tokenAddOperator : ( PLUS | MINUS ) ;
    public final FunSQLParser.tokenAddOperator_return tokenAddOperator() throws RecognitionException {
        FunSQLParser.tokenAddOperator_return retval = new FunSQLParser.tokenAddOperator_return();
        retval.start = input.LT(1);

        int tokenAddOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1124:5: ( ( PLUS | MINUS ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            if ( state.backtracking>0 ) { memoize(input, 43, tokenAddOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenAddOperator"


    public static class tokenMultOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenMultOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1131:1: tokenMultOperator : ( MULT | DIV ) ;
    public final FunSQLParser.tokenMultOperator_return tokenMultOperator() throws RecognitionException {
        FunSQLParser.tokenMultOperator_return retval = new FunSQLParser.tokenMultOperator_return();
        retval.start = input.LT(1);

        int tokenMultOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1132:5: ( ( MULT | DIV ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            if ( state.backtracking>0 ) { memoize(input, 44, tokenMultOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenMultOperator"


    public static class tokenCompOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenCompOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1139:1: tokenCompOperator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | KEYWORD_LIKE ) ;
    public final FunSQLParser.tokenCompOperator_return tokenCompOperator() throws RecognitionException {
        FunSQLParser.tokenCompOperator_return retval = new FunSQLParser.tokenCompOperator_return();
        retval.start = input.LT(1);

        int tokenCompOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1140:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | KEYWORD_LIKE ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            if ( state.backtracking>0 ) { memoize(input, 45, tokenCompOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenCompOperator"

    // $ANTLR start synpred40_FunSQL
    public final void synpred40_FunSQL_fragment() throws RecognitionException {
        AbstractPredicate predicate1 =null;


        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:627:3: (predicate1= parenPredicate )
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:627:3: predicate1= parenPredicate
        {
        pushFollow(FOLLOW_parenPredicate_in_synpred40_FunSQL5910);
        predicate1=parenPredicate();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred40_FunSQL

    // $ANTLR start synpred50_FunSQL
    public final void synpred50_FunSQL_fragment() throws RecognitionException {
        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;


        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:831:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:831:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
        {
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:831:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:832:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
        {
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:832:17: (schema1= tokenIdentifier DOT )?
        int alt56=2;
        int LA56_0 = input.LA(1);

        if ( (LA56_0==IDENTIFIER) ) {
            int LA56_1 = input.LA(2);

            if ( (LA56_1==DOT) ) {
                alt56=1;
            }
        }
        else if ( (LA56_0==QUOTE_DOUBLE) ) {
            int LA56_2 = input.LA(2);

            if ( (LA56_2==IDENTIFIER) ) {
                int LA56_5 = input.LA(3);

                if ( (LA56_5==QUOTE_DOUBLE) ) {
                    int LA56_6 = input.LA(4);

                    if ( (LA56_6==DOT) ) {
                        alt56=1;
                    }
                }
            }
        }
        switch (alt56) {
            case 1 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:833:17: schema1= tokenIdentifier DOT
                {
                pushFollow(FOLLOW_tokenIdentifier_in_synpred50_FunSQL6944);
                schema1=tokenIdentifier();

                state._fsp--;
                if (state.failed) return ;

                match(input,DOT,FOLLOW_DOT_in_synpred50_FunSQL6964); if (state.failed) return ;

                }
                break;

        }


        pushFollow(FOLLOW_tokenIdentifier_in_synpred50_FunSQL7021);
        id1=tokenIdentifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }

    }
    // $ANTLR end synpred50_FunSQL

    // Delegated rules

    public final boolean synpred50_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred50_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred40_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred40_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_createSchemaStatement_in_statement1106 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_dropSchemaStatement_in_statement1161 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_createConnectionStatement_in_statement1216 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_dropConnectionStatement_in_statement1271 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_createTableStatement_in_statement1326 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_dropTableStatement_in_statement1381 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_createFunctionStatement_in_statement1436 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_dropFunctionStatement_in_statement1491 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_callFunctionStatement_in_statement1546 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_selectStatement_in_statement1601 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    public static final BitSet FOLLOW_SEMI_in_statement1655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1733 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1751 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1848 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1866 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1963 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1981 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement1999 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement2019 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2039 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement2059 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2079 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement2099 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2119 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement2139 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2246 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2264 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2362 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2380 = new BitSet(new long[]{0x0000000200001000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement2420 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2440 = new BitSet(new long[]{0x0000000000000000L,0x0000780000000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2478 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2532 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2552 = new BitSet(new long[]{0x0000000000000000L,0x0000780000000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2590 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement2645 = new BitSet(new long[]{0x5022000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2681 = new BitSet(new long[]{0x0000000200001000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2701 = new BitSet(new long[]{0x5020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2760 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2762 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_REPLICATED_in_createTableStatement2823 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2825 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2827 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2848 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2870 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2892 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_KEYWORD_PARTITIONED_in_createTableStatement2986 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_BY_in_createTableStatement2988 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3009 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement3063 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3083 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement3137 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3157 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement3212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement3249 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3269 = new BitSet(new long[]{0x4020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3308 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3310 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3330 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3353 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3355 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3357 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3378 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement3399 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3404 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement3446 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3466 = new BitSet(new long[]{0x4020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3504 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3506 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3526 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3549 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3551 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3553 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3574 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement3595 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3600 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement3643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement3757 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement3775 = new BitSet(new long[]{0x0000000200001000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenTable_in_dropTableStatement3795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createFunctionStatement3861 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement3879 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenFunction_in_createFunctionStatement3899 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_LPAREN_in_createFunctionStatement3915 = new BitSet(new long[]{0x0820000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createFunctionStatement3944 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement3964 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3971 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_createFunctionStatement3977 = new BitSet(new long[]{0x0820000000000000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement4015 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement4035 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4042 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_COMMA_in_createFunctionStatement4055 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement4060 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement4074 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4080 = new BitSet(new long[]{0x0000000000002000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_createFunctionStatement4089 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement4093 = new BitSet(new long[]{0x0001040000001000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenAssignment_in_createFunctionStatement4107 = new BitSet(new long[]{0x0001040000001000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenFunctionCall_in_createFunctionStatement4186 = new BitSet(new long[]{0x0001040000001000L,0x0000000000000040L});
    public static final BitSet FOLLOW_KEYWORD_END_in_createFunctionStatement4228 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_SEMI_in_createFunctionStatement4235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropFunctionStatement4312 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement4330 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenFunction_in_dropFunctionStatement4350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CALL_in_callFunctionStatement4416 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement4434 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenFunction_in_callFunctionStatement4454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement4531 = new BitSet(new long[]{0x0000000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement4551 = new BitSet(new long[]{0x0002004000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4606 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4627 = new BitSet(new long[]{0x0002000000002000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement4701 = new BitSet(new long[]{0x0000000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement4721 = new BitSet(new long[]{0x0002004000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4776 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4797 = new BitSet(new long[]{0x0002000000002000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement4906 = new BitSet(new long[]{0x0000000200001000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement4926 = new BitSet(new long[]{0x0018004000002002L,0x0000000000000080L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4982 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement5003 = new BitSet(new long[]{0x0018000000002002L,0x0000000000000080L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement5077 = new BitSet(new long[]{0x0000000200001000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement5097 = new BitSet(new long[]{0x0018004000002002L,0x0000000000000080L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement5152 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement5173 = new BitSet(new long[]{0x0018000000002002L,0x0000000000000080L});
    public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement5283 = new BitSet(new long[]{0x0200000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement5303 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_KEYWORD_GROUP_in_selectStatement5410 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_BY_in_selectStatement5412 = new BitSet(new long[]{0x0000000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement5432 = new BitSet(new long[]{0x0010000000002002L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement5486 = new BitSet(new long[]{0x0000000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement5506 = new BitSet(new long[]{0x0010000000002002L});
    public static final BitSet FOLLOW_KEYWORD_HAVING_in_selectStatement5632 = new BitSet(new long[]{0x0200000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement5652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate5714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr5752 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr5763 = new BitSet(new long[]{0x0200000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr5771 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd5810 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd5821 = new BitSet(new long[]{0x0200000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd5829 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot5870 = new BitSet(new long[]{0x0000000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot5883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_complexPredicate5910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicate_in_complexPredicate5925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenPredicate5950 = new BitSet(new long[]{0x0200000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate5957 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenPredicate5963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate6005 = new BitSet(new long[]{0x0040000038400000L,0x0000000000C01C00L});
    public static final BitSet FOLLOW_tokenCompOperator_in_simplePredicate6060 = new BitSet(new long[]{0x0000000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate6098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionAdd_in_abstractExpression6152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd6182 = new BitSet(new long[]{0x0000000000000002L,0x0000000008040000L});
    public static final BitSet FOLLOW_tokenAddOperator_in_complexExpressionAdd6195 = new BitSet(new long[]{0x0000000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd6203 = new BitSet(new long[]{0x0000000000000002L,0x0000000008040000L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult6243 = new BitSet(new long[]{0x0000000000020002L,0x0000000000100000L});
    public static final BitSet FOLLOW_tokenMultOperator_in_complexExpressionMult6256 = new BitSet(new long[]{0x0000000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult6264 = new BitSet(new long[]{0x0000000000020002L,0x0000000000100000L});
    public static final BitSet FOLLOW_MINUS_in_complexExpressionSigned6306 = new BitSet(new long[]{0x0000000202000000L,0x000008008001E000L});
    public static final BitSet FOLLOW_PLUS_in_complexExpressionSigned6315 = new BitSet(new long[]{0x0000000202000000L,0x000008008001E000L});
    public static final BitSet FOLLOW_complexExpression_in_complexExpressionSigned6327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenExpression_in_complexExpression6362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggregationExpression_in_complexExpression6377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleExpression_in_complexExpression6392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenExpression6417 = new BitSet(new long[]{0x0000000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_abstractExpression_in_parenExpression6424 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenExpression6430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression6482 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_LPAREN_in_aggregationExpression6490 = new BitSet(new long[]{0x0000000202000000L,0x000008008805E000L});
    public static final BitSet FOLLOW_abstractExpression_in_aggregationExpression6497 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_aggregationExpression6503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simpleExpression6578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenLiteral_in_simpleExpression6634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute6762 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenAttribute6782 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute6821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable6944 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenTable6964 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable7021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenTable7088 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable7130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema7211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction7327 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenFunction7347 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction7386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableText_in_tokenVariable7466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenAssignment7533 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7545 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7551 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment7558 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment7579 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7588 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment7601 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment7625 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7634 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7640 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_tokenAssignment7651 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7663 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_SEMI_in_tokenAssignment7674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CALL_in_tokenFunctionCall7706 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_tokenFunctionCall7711 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenFunction_in_tokenFunctionCall7718 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_LPAREN_in_tokenFunctionCall7724 = new BitSet(new long[]{0x0000000000001000L,0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_tokenFunctionCall7735 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall7748 = new BitSet(new long[]{0x0000000000001000L,0x0000000000000040L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7769 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall7779 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_tokenFunctionCall7786 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7808 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall7818 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_tokenFunctionCall7829 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_SEMI_in_tokenFunctionCall7834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierText_in_tokenIdentifier7902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType7983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType8021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DECIMAL_in_tokenDataType8059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DATE_in_tokenDataType8097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral8188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral8226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral8264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral8302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral8402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral8482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral8572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DATE_in_tokenDateLiteral8670 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenDateLiteral8688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variableText8747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText8799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText8855 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText8871 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText8891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_synpred40_FunSQL5910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_synpred50_FunSQL6944 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_synpred50_FunSQL6964 = new BitSet(new long[]{0x0000000200000000L,0x0000000080000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_synpred50_FunSQL7021 = new BitSet(new long[]{0x0000000000000002L});

}