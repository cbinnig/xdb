// $ANTLR 3.4 C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g 2013-05-29 18:26:15
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", "AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", "D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", "F", "FUNCTION_AGGREGATION", "G", "GREATER_EQUAL1", "GREATER_EQUAL2", "GREATER_THAN", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", "KEYWORD_AND", "KEYWORD_AS", "KEYWORD_AVG", "KEYWORD_BEGIN", "KEYWORD_BY", "KEYWORD_CALL", "KEYWORD_CONNECTION", "KEYWORD_COUNT", "KEYWORD_CREATE", "KEYWORD_DATA", "KEYWORD_DISTINCT", "KEYWORD_DROP", "KEYWORD_END", "KEYWORD_FROM", "KEYWORD_FUNCTION", "KEYWORD_GROUP", "KEYWORD_HAVING", "KEYWORD_IN", "KEYWORD_INFILE", "KEYWORD_INTO", "KEYWORD_LIKE", "KEYWORD_LOAD", "KEYWORD_MAX", "KEYWORD_MIN", "KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_OUT", "KEYWORD_PARTITION", "KEYWORD_PARTITIONED", "KEYWORD_PASSWD", "KEYWORD_REPLICATED", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", "KEYWORD_SUM", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_VAR", "KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", "LESS_THAN", "LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", "MINUS", "MOD", "MULT", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", "QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", "SHIFT_RIGHT", "T", "TILDE", "TYPE_DATE", "TYPE_DECIMAL", "TYPE_INTEGER", "TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
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
        this.state.ruleMemo = new HashMap[125+1];
         

    }

    public String[] getTokenNames() { return FunSQLParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g"; }


      @Override
      protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
        throw new MismatchedTokenException(ttype, input);
      }

      @Override
      public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
        throw e;
      }



    // $ANTLR start "statement"
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:95:1: statement returns [AbstractServerStmt stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement ) ( SEMI )? ) ;
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

        FunSQLParser.loadDataInfileStatement_return loadDataInfileStatement11 =null;



                	stmt = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return stmt; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:99:9: ( ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement ) ( SEMI )? ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:100:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement ) ( SEMI )? )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:100:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement ) ( SEMI )? )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:101:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement ) ( SEMI )?
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:101:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement | loadDataInfileStatement )
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:102:17: createSchemaStatement
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:108:17: dropSchemaStatement
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:114:17: createConnectionStatement
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:120:17: dropConnectionStatement
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:126:17: createTableStatement
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:132:17: dropTableStatement
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:138:17: createFunctionStatement
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:144:17: dropFunctionStatement
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:150:17: callFunctionStatement
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:156:17: selectStatement
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
                case 11 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:162:17: loadDataInfileStatement
                    {
                    pushFollow(FOLLOW_loadDataInfileStatement_in_statement1655);
                    loadDataInfileStatement11=loadDataInfileStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = (loadDataInfileStatement11!=null?loadDataInfileStatement11.stmt:null);
                                    	stmt.setStmtString((loadDataInfileStatement11!=null?input.toString(loadDataInfileStatement11.start,loadDataInfileStatement11.stop):null));
                                    }

                    }
                    break;

            }


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:168:17: ( SEMI )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==SEMI) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:168:17: SEMI
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:172:1: createSchemaStatement returns [CreateSchemaStmt stmt] : ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) ;
    public final FunSQLParser.createSchemaStatement_return createSchemaStatement() throws RecognitionException {
        FunSQLParser.createSchemaStatement_return retval = new FunSQLParser.createSchemaStatement_return();
        retval.start = input.LT(1);

        int createSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema12 =null;



                	retval.stmt = new CreateSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:176:9: ( ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:177:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:177:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:178:17: KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:186:1: dropSchemaStatement returns [DropSchemaStmt stmt] : ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) ;
    public final FunSQLParser.dropSchemaStatement_return dropSchemaStatement() throws RecognitionException {
        FunSQLParser.dropSchemaStatement_return retval = new FunSQLParser.dropSchemaStatement_return();
        retval.start = input.LT(1);

        int dropSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema13 =null;



                	retval.stmt = new DropSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:190:9: ( ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:191:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:191:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:192:17: KEYWORD_DROP KEYWORD_SCHEMA tokenSchema
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:200:1: createConnectionStatement returns [CreateConnectionStmt stmt] : ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) ;
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

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:204:9: ( ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:205:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:205:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:206:17: KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:230:1: dropConnectionStatement returns [DropConnectionStmt stmt] : ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) ;
    public final FunSQLParser.dropConnectionStatement_return dropConnectionStatement() throws RecognitionException {
        FunSQLParser.dropConnectionStatement_return retval = new FunSQLParser.dropConnectionStatement_return();
        retval.start = input.LT(1);

        int dropConnectionStatement_StartIndex = input.index();

        TokenIdentifier tokenIdentifier15 =null;



                	retval.stmt = new DropConnectionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:234:9: ( ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:235:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:235:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:236:17: KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:245:1: createTableStatement returns [CreateTableStmt stmt] : ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) ) ;
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

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:249:9: ( ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:250:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:250:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:251:17: KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) )
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2416); if (state.failed) return retval;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2434); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_createTableStatement2454);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setTable(table1);
                            	retval.stmt.setSourceTable(table1);
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

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:266:17: ( COMMA att2= identifierText dataType2= tokenDataType )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:267:17: COMMA att2= identifierText dataType2= tokenDataType
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
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2699); if (state.failed) return retval;

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:278:17: ( KEYWORD_FROM table2= tokenTable )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_FROM) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:279:17: KEYWORD_FROM table2= tokenTable
                    {
                    match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_createTableStatement2735); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenTable_in_createTableStatement2755);
                    table2=tokenTable();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setSourceTable(table2);
                                    }

                    }
                    break;

            }


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:284:17: ( ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN ) )
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:284:18: ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) )
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:284:18: ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* ) )
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
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:284:19: ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
                            {
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:284:19: ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:285:17: KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier
                            {
                            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2814); if (state.failed) return retval;

                            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2816); if (state.failed) return retval;

                            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2836);
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
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:289:19: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* )
                            {
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:289:19: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )* )
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:290:17: KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION connectionR1= tokenIdentifier ( COMMA connectionR2= tokenIdentifier )*
                            {
                            match(input,KEYWORD_REPLICATED,FOLLOW_KEYWORD_REPLICATED_in_createTableStatement2877); if (state.failed) return retval;

                            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2879); if (state.failed) return retval;

                            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2881); if (state.failed) return retval;

                            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2902);
                            connectionR1=tokenIdentifier();

                            state._fsp--;
                            if (state.failed) return retval;

                            if ( state.backtracking==0 ) {
                                            	retval.stmt.addConnection(connectionR1);
                                            }

                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:294:17: ( COMMA connectionR2= tokenIdentifier )*
                            loop5:
                            do {
                                int alt5=2;
                                int LA5_0 = input.LA(1);

                                if ( (LA5_0==COMMA) ) {
                                    alt5=1;
                                }


                                switch (alt5) {
                            	case 1 :
                            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:294:19: COMMA connectionR2= tokenIdentifier
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2924); if (state.failed) return retval;

                            	    pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2946);
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:301:17: ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN )
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:301:17: ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN )
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:302:17: KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )* RPAREN
                    {
                    match(input,KEYWORD_PARTITIONED,FOLLOW_KEYWORD_PARTITIONED_in_createTableStatement3040); if (state.failed) return retval;

                    match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_createTableStatement3042); if (state.failed) return retval;

                    pushFollow(FOLLOW_identifierText_in_createTableStatement3063);
                    method=identifierText();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setPartitionMethod(method);
                                    }

                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:307:17: ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )?
                    int alt8=2;
                    alt8 = dfa8.predict(input);
                    switch (alt8) {
                        case 1 :
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:308:17: LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN
                            {
                            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement3117); if (state.failed) return retval;

                            pushFollow(FOLLOW_identifierText_in_createTableStatement3137);
                            pcolumn1=identifierText();

                            state._fsp--;
                            if (state.failed) return retval;

                            if ( state.backtracking==0 ) {
                                            	retval.stmt.addPartitionColumn(pcolumn1);
                                            }

                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:313:17: ( COMMA pcolumn2= identifierText )*
                            loop7:
                            do {
                                int alt7=2;
                                int LA7_0 = input.LA(1);

                                if ( (LA7_0==COMMA) ) {
                                    alt7=1;
                                }


                                switch (alt7) {
                            	case 1 :
                            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:314:17: COMMA pcolumn2= identifierText
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3191); if (state.failed) return retval;

                            	    pushFollow(FOLLOW_identifierText_in_createTableStatement3211);
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


                            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement3266); if (state.failed) return retval;

                            }
                            break;

                    }


                    match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement3303); if (state.failed) return retval;

                    pushFollow(FOLLOW_identifierText_in_createTableStatement3323);
                    p1=identifierText();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.addPartition(p1);
                                    }

                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:327:17: ( ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) )
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
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:327:19: ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier )
                            {
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:327:19: ( KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier )
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:327:20: KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier
                            {
                            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3362); if (state.failed) return retval;

                            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3364); if (state.failed) return retval;

                            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3384);
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
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:331:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
                            {
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:331:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:331:18: KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )?
                            {
                            match(input,KEYWORD_REPLICATED,FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3407); if (state.failed) return retval;

                            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3409); if (state.failed) return retval;

                            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3411); if (state.failed) return retval;

                            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3432);
                            c2=tokenIdentifier();

                            state._fsp--;
                            if (state.failed) return retval;

                            if ( state.backtracking==0 ) {
                                            	retval.stmt.addPConnection(p1,c2);
                                            }

                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:335:17: ( COMMA c3= tokenIdentifier )?
                            int alt9=2;
                            alt9 = dfa9.predict(input);
                            switch (alt9) {
                                case 1 :
                                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:335:18: COMMA c3= tokenIdentifier
                                    {
                                    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3453); if (state.failed) return retval;

                                    pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3458);
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


                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:338:17: ( COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==COMMA) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:339:17: COMMA p2= identifierText ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) )
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3500); if (state.failed) return retval;

                    	    pushFollow(FOLLOW_identifierText_in_createTableStatement3520);
                    	    p2=identifierText();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    if ( state.backtracking==0 ) {
                    	                    	retval.stmt.addPartition(p2);
                    	                    }

                    	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:344:16: ( ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ) | ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? ) )
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
                    	            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:344:18: ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )
                    	            {
                    	            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:344:18: ( KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )
                    	            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:344:19: KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier
                    	            {
                    	            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3558); if (state.failed) return retval;

                    	            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3560); if (state.failed) return retval;

                    	            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3580);
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
                    	            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:348:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
                    	            {
                    	            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:348:17: ( KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )? )
                    	            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:348:18: KEYWORD_REPLICATED KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier ( COMMA c3= tokenIdentifier )?
                    	            {
                    	            match(input,KEYWORD_REPLICATED,FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3603); if (state.failed) return retval;

                    	            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3605); if (state.failed) return retval;

                    	            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3607); if (state.failed) return retval;

                    	            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3628);
                    	            c2=tokenIdentifier();

                    	            state._fsp--;
                    	            if (state.failed) return retval;

                    	            if ( state.backtracking==0 ) {
                    	                            	retval.stmt.addPConnection(p2,c2);
                    	                            }

                    	            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:352:17: ( COMMA c3= tokenIdentifier )?
                    	            int alt11=2;
                    	            alt11 = dfa11.predict(input);
                    	            switch (alt11) {
                    	                case 1 :
                    	                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:352:18: COMMA c3= tokenIdentifier
                    	                    {
                    	                    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3649); if (state.failed) return retval;

                    	                    pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3654);
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


                    match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement3697); if (state.failed) return retval;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:363:1: dropTableStatement returns [DropTableStmt stmt] : ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) ;
    public final FunSQLParser.dropTableStatement_return dropTableStatement() throws RecognitionException {
        FunSQLParser.dropTableStatement_return retval = new FunSQLParser.dropTableStatement_return();
        retval.start = input.LT(1);

        int dropTableStatement_StartIndex = input.index();

        TokenTable table1 =null;



                	retval.stmt = new DropTableStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:367:9: ( ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:368:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:368:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:369:17: KEYWORD_DROP KEYWORD_TABLE table1= tokenTable
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement3811); if (state.failed) return retval;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement3829); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_dropTableStatement3849);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:377:1: createFunctionStatement returns [CreateFunctionStmt stmt] : ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI ) ;
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

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:381:9: ( ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:382:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:382:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:383:17: KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END SEMI
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createFunctionStatement3915); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement3933); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_createFunctionStatement3953);
            function1=tokenFunction();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setFunction(function1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createFunctionStatement3969); if (state.failed) return retval;

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:389:10: ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==KEYWORD_IN) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:390:17: KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA
            	    {
            	    match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createFunctionStatement3998); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement4018);
            	    var1=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                   		retval.stmt.addInParam(var1);
            	    				}

            	    match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4025); if (state.failed) return retval;

            	    match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement4031); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:397:3: ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:398:17: KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
            {
            match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement4069); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement4089);
            var2=tokenVariable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                           		retval.stmt.addOutParam(var2);
            				}

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4096); if (state.failed) return retval;

            }


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:404:3: ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==COMMA) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:405:4: COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement4109); if (state.failed) return retval;

            	    match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement4114); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement4128);
            	    var2=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	               		retval.stmt.addOutParam(var2);
            	    			}

            	    match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4134); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createFunctionStatement4143); if (state.failed) return retval;

            match(input,KEYWORD_BEGIN,FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement4147); if (state.failed) return retval;

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:414:3: ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )*
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
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:415:3: (ass1= tokenAssignment )
            	    {
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:415:3: (ass1= tokenAssignment )
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:416:3: ass1= tokenAssignment
            	    {
            	    pushFollow(FOLLOW_tokenAssignment_in_createFunctionStatement4161);
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
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:422:17: (call1= tokenFunctionCall )
            	    {
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:422:17: (call1= tokenFunctionCall )
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:423:17: call1= tokenFunctionCall
            	    {
            	    pushFollow(FOLLOW_tokenFunctionCall_in_createFunctionStatement4240);
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


            match(input,KEYWORD_END,FOLLOW_KEYWORD_END_in_createFunctionStatement4282); if (state.failed) return retval;

            match(input,SEMI,FOLLOW_SEMI_in_createFunctionStatement4289); if (state.failed) return retval;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:433:1: dropFunctionStatement returns [DropFunctionStmt stmt] : ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) ;
    public final FunSQLParser.dropFunctionStatement_return dropFunctionStatement() throws RecognitionException {
        FunSQLParser.dropFunctionStatement_return retval = new FunSQLParser.dropFunctionStatement_return();
        retval.start = input.LT(1);

        int dropFunctionStatement_StartIndex = input.index();

        TokenFunction fun1 =null;



                	retval.stmt = new DropFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:437:9: ( ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:438:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:438:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:439:17: KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropFunctionStatement4366); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement4384); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_dropFunctionStatement4404);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:447:1: callFunctionStatement returns [CallFunctionStmt stmt] : ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) ;
    public final FunSQLParser.callFunctionStatement_return callFunctionStatement() throws RecognitionException {
        FunSQLParser.callFunctionStatement_return retval = new FunSQLParser.callFunctionStatement_return();
        retval.start = input.LT(1);

        int callFunctionStatement_StartIndex = input.index();

        TokenFunction fun1 =null;



                	retval.stmt = new CallFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:451:9: ( ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:452:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:452:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:453:17: KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction
            {
            match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_callFunctionStatement4470); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement4488); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_callFunctionStatement4508);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:461:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) ;
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

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:466:9: ( ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:467:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:467:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:469:17: KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )?
            {
            match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement4585); if (state.failed) return retval;

            pushFollow(FOLLOW_abstractExpression_in_selectStatement4605);
            selExpr1=abstractExpression();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addSelExpression(selExpr1);
                            	++i;
                            }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:475:17: ( KEYWORD_AS selAlias1= tokenIdentifier )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEYWORD_AS) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:476:18: KEYWORD_AS selAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4660); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4681);
                    selAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    		retval.stmt.setSelAlias(i-1, selAlias1);
                                    	}

                    }
                    break;

            }


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:482:17: ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==COMMA) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:483:17: COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement4755); if (state.failed) return retval;

            	    pushFollow(FOLLOW_abstractExpression_in_selectStatement4775);
            	    selExpr2=abstractExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addSelExpression(selExpr2);
            	                    	++i;
            	                    }

            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:489:17: ( KEYWORD_AS selAlias2= tokenIdentifier )?
            	    int alt19=2;
            	    int LA19_0 = input.LA(1);

            	    if ( (LA19_0==KEYWORD_AS) ) {
            	        alt19=1;
            	    }
            	    switch (alt19) {
            	        case 1 :
            	            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:490:18: KEYWORD_AS selAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4830); if (state.failed) return retval;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4851);
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


            match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement4960); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_selectStatement4980);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addTable(table1);
                            	i=1;
                            }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:505:17: ( KEYWORD_AS tableAlias1= tokenIdentifier )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==KEYWORD_AS) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:506:18: KEYWORD_AS tableAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement5036); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement5057);
                    tableAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    		retval.stmt.setTableAlias(i-1, tableAlias1);
                                    	}

                    }
                    break;

            }


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:512:17: ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==COMMA) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:513:17: COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement5131); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenTable_in_selectStatement5151);
            	    table2=tokenTable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addTable(table2);
            	                    	++i;
            	                    }

            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:519:17: ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    int alt22=2;
            	    int LA22_0 = input.LA(1);

            	    if ( (LA22_0==KEYWORD_AS) ) {
            	        alt22=1;
            	    }
            	    switch (alt22) {
            	        case 1 :
            	            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:520:18: KEYWORD_AS tableAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement5206); if (state.failed) return retval;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement5227);
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


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:528:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==KEYWORD_WHERE) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:529:17: KEYWORD_WHERE predicate1= abstractPredicate
                    {
                    match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement5337); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement5357);
                    predicate1=abstractPredicate();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setWherePredicate(predicate1);
                                    }

                    }
                    break;

            }


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:537:17: ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==KEYWORD_GROUP) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:538:17: KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )*
                    {
                    match(input,KEYWORD_GROUP,FOLLOW_KEYWORD_GROUP_in_selectStatement5464); if (state.failed) return retval;

                    match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_selectStatement5466); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractExpression_in_selectStatement5486);
                    groupExpr1=abstractExpression();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.addGroupExpression(groupExpr1);
                                    	++i;
                                    }

                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:544:17: ( COMMA groupExpr2= abstractExpression )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==COMMA) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:545:17: COMMA groupExpr2= abstractExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement5540); if (state.failed) return retval;

                    	    pushFollow(FOLLOW_abstractExpression_in_selectStatement5560);
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


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:555:17: ( KEYWORD_HAVING havingPred= abstractPredicate )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==KEYWORD_HAVING) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:556:17: KEYWORD_HAVING havingPred= abstractPredicate
                    {
                    match(input,KEYWORD_HAVING,FOLLOW_KEYWORD_HAVING_in_selectStatement5686); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement5706);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:565:1: loadDataInfileStatement returns [LoadDataInfileStmt stmt] : ( KEYWORD_LOAD KEYWORD_DATA KEYWORD_INFILE filename1= identifierText KEYWORD_INTO KEYWORD_TABLE table1= tokenTable ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )? ) ;
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

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:569:9: ( ( KEYWORD_LOAD KEYWORD_DATA KEYWORD_INFILE filename1= identifierText KEYWORD_INTO KEYWORD_TABLE table1= tokenTable ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )? ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:570:9: ( KEYWORD_LOAD KEYWORD_DATA KEYWORD_INFILE filename1= identifierText KEYWORD_INTO KEYWORD_TABLE table1= tokenTable ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )? )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:570:9: ( KEYWORD_LOAD KEYWORD_DATA KEYWORD_INFILE filename1= identifierText KEYWORD_INTO KEYWORD_TABLE table1= tokenTable ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )? )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:571:9: KEYWORD_LOAD KEYWORD_DATA KEYWORD_INFILE filename1= identifierText KEYWORD_INTO KEYWORD_TABLE table1= tokenTable ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )?
            {
            match(input,KEYWORD_LOAD,FOLLOW_KEYWORD_LOAD_in_loadDataInfileStatement5799); if (state.failed) return retval;

            match(input,KEYWORD_DATA,FOLLOW_KEYWORD_DATA_in_loadDataInfileStatement5809); if (state.failed) return retval;

            match(input,KEYWORD_INFILE,FOLLOW_KEYWORD_INFILE_in_loadDataInfileStatement5819); if (state.failed) return retval;

            pushFollow(FOLLOW_identifierText_in_loadDataInfileStatement5831);
            filename1=identifierText();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setTokenFilename(filename1);
                            }

            match(input,KEYWORD_INTO,FOLLOW_KEYWORD_INTO_in_loadDataInfileStatement5843); if (state.failed) return retval;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_loadDataInfileStatement5853); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_loadDataInfileStatement5865);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setTokenTable(table1);
                            }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:582:9: ( KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==KEYWORD_PARTITION) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:583:10: KEYWORD_PARTITION LPAREN partition1= identifierText ( COMMA partition2= identifierText )* RPAREN
                    {
                    match(input,KEYWORD_PARTITION,FOLLOW_KEYWORD_PARTITION_in_loadDataInfileStatement5888); if (state.failed) return retval;

                    match(input,LPAREN,FOLLOW_LPAREN_in_loadDataInfileStatement5899); if (state.failed) return retval;

                    pushFollow(FOLLOW_identifierText_in_loadDataInfileStatement5912);
                    partition1=identifierText();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                            			retval.stmt.setTokenPartition(partition1);
                    			}

                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:588:10: ( COMMA partition2= identifierText )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==COMMA) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:589:11: COMMA partition2= identifierText
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_loadDataInfileStatement5937); if (state.failed) return retval;

                    	    pushFollow(FOLLOW_identifierText_in_loadDataInfileStatement5951);
                    	    partition2=identifierText();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    if ( state.backtracking==0 ) {
                    	    	        			retval.stmt.setTokenPartition(partition2);
                    	    				}

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_loadDataInfileStatement5976); if (state.failed) return retval;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:599:1: abstractPredicate returns [AbstractPredicate predicate] : predicate1= complexPredicateOr ;
    public final AbstractPredicate abstractPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int abstractPredicate_StartIndex = input.index();

        ComplexPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return predicate; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:600:2: (predicate1= complexPredicateOr )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:601:3: predicate1= complexPredicateOr
            {
            pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate6017);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:606:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
    public final ComplexPredicate complexPredicateOr() throws RecognitionException {
        ComplexPredicate predicateOr = null;

        int complexPredicateOr_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return predicateOr; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:610:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:611:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:611:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:612:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
            {
            pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr6055);
            predicate1=complexPredicateAnd();

            state._fsp--;
            if (state.failed) return predicateOr;

            if ( state.backtracking==0 ) {
            			predicateOr.setPredicate1(predicate1);
            		}

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:615:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==KEYWORD_OR) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:616:4: KEYWORD_OR predicate2= complexPredicateAnd
            	    {
            	    match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr6066); if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addOr();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr6074);
            	    predicate2=complexPredicateAnd();

            	    state._fsp--;
            	    if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop30;
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
            if ( state.backtracking>0 ) { memoize(input, 14, complexPredicateOr_StartIndex); }

        }
        return predicateOr;
    }
    // $ANTLR end "complexPredicateOr"



    // $ANTLR start "complexPredicateAnd"
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:626:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
    public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
        ComplexPredicate predicateAnd = null;

        int complexPredicateAnd_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return predicateAnd; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:630:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:631:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:631:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:632:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
            {
            pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd6113);
            predicate1=complexPredicateNot();

            state._fsp--;
            if (state.failed) return predicateAnd;

            if ( state.backtracking==0 ) {
            			predicateAnd.setPredicate1(predicate1);
            		}

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:635:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==KEYWORD_AND) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:636:4: KEYWORD_AND predicate2= complexPredicateNot
            	    {
            	    match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd6124); if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addAnd();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd6132);
            	    predicate2=complexPredicateNot();

            	    state._fsp--;
            	    if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop31;
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
            if ( state.backtracking>0 ) { memoize(input, 15, complexPredicateAnd_StartIndex); }

        }
        return predicateAnd;
    }
    // $ANTLR end "complexPredicateAnd"



    // $ANTLR start "complexPredicateNot"
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:646:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
    public final ComplexPredicate complexPredicateNot() throws RecognitionException {
        ComplexPredicate predicateNot = null;

        int complexPredicateNot_StartIndex = input.index();

        AbstractPredicate predicate1 =null;



                	predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return predicateNot; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:650:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:651:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:651:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:652:3: ( KEYWORD_NOT )? predicate1= complexPredicate
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:652:3: ( KEYWORD_NOT )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==KEYWORD_NOT) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:653:3: KEYWORD_NOT
                    {
                    match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot6173); if (state.failed) return predicateNot;

                    if ( state.backtracking==0 ) {
                    			predicateNot.negate();
                    		}

                    }
                    break;

            }


            pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot6186);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:663:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
    public final AbstractPredicate complexPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int complexPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;

        SimplePredicate predicate2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return predicate; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:664:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:665:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:665:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==LPAREN) ) {
                int LA33_1 = input.LA(2);

                if ( (synpred43_FunSQL()) ) {
                    alt33=1;
                }
                else if ( (true) ) {
                    alt33=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA33_0==FUNCTION_AGGREGATION||LA33_0==IDENTIFIER||(LA33_0 >= LITERAL_DECIMAL && LA33_0 <= LITERAL_STRING)||LA33_0==MINUS||LA33_0==PLUS||LA33_0==QUOTE_DOUBLE||LA33_0==TYPE_DATE) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return predicate;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }
            switch (alt33) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:666:3: predicate1= parenPredicate
                    {
                    pushFollow(FOLLOW_parenPredicate_in_complexPredicate6213);
                    predicate1=parenPredicate();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate1;
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:671:3: predicate2= simplePredicate
                    {
                    pushFollow(FOLLOW_simplePredicate_in_complexPredicate6228);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:678:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
    public final AbstractPredicate parenPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int parenPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return predicate; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:679:2: ( LPAREN predicate1= abstractPredicate RPAREN )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:680:3: LPAREN predicate1= abstractPredicate RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate6253); if (state.failed) return predicate;

            pushFollow(FOLLOW_abstractPredicate_in_parenPredicate6260);
            predicate1=abstractPredicate();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
            			predicate = predicate1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate6266); if (state.failed) return predicate;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:687:1: simplePredicate returns [SimplePredicate predicate] : (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) ;
    public final SimplePredicate simplePredicate() throws RecognitionException {
        SimplePredicate predicate = null;

        int simplePredicate_StartIndex = input.index();

        AbstractExpression expr1 =null;

        FunSQLParser.tokenCompOperator_return comp =null;

        AbstractExpression expr2 =null;



                	predicate = new SimplePredicate();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return predicate; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:691:9: ( (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:692:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:692:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:693:3: expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression
            {
            pushFollow(FOLLOW_abstractExpression_in_simplePredicate6308);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setExpr1(expr1);
                            }

            pushFollow(FOLLOW_tokenCompOperator_in_simplePredicate6363);
            comp=tokenCompOperator();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setComp(EnumCompOperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
                            }

            pushFollow(FOLLOW_abstractExpression_in_simplePredicate6401);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:710:1: abstractExpression returns [AbstractExpression expression] : expression1= complexExpressionAdd ;
    public final AbstractExpression abstractExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int abstractExpression_StartIndex = input.index();

        ComplexExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expression; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:711:2: (expression1= complexExpressionAdd )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:712:2: expression1= complexExpressionAdd
            {
            pushFollow(FOLLOW_complexExpressionAdd_in_abstractExpression6455);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:717:1: complexExpressionAdd returns [ComplexExpression expression] : (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) ;
    public final ComplexExpression complexExpressionAdd() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionAdd_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenAddOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expression; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:721:2: ( (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:722:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:722:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:723:3: expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )*
            {
            pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd6485);
            expression1=complexExpressionMult();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:726:3: (op1= tokenAddOperator expression2= complexExpressionMult )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==MINUS||LA34_0==PLUS) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:727:4: op1= tokenAddOperator expression2= complexExpressionMult
            	    {
            	    pushFollow(FOLLOW_tokenAddOperator_in_complexExpressionAdd6498);
            	    op1=tokenAddOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd6506);
            	    expression2=complexExpressionMult();

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
            if ( state.backtracking>0 ) { memoize(input, 21, complexExpressionAdd_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionAdd"



    // $ANTLR start "complexExpressionMult"
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:737:1: complexExpressionMult returns [ComplexExpression expression] : (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) ;
    public final ComplexExpression complexExpressionMult() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionMult_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenMultOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expression; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:741:2: ( (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:742:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:742:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:743:3: expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )*
            {
            pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult6546);
            expression1=complexExpressionSigned();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:746:3: (op1= tokenMultOperator expression2= complexExpressionSigned )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==DIV||LA35_0==MULT) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:747:4: op1= tokenMultOperator expression2= complexExpressionSigned
            	    {
            	    pushFollow(FOLLOW_tokenMultOperator_in_complexExpressionMult6559);
            	    op1=tokenMultOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult6567);
            	    expression2=complexExpressionSigned();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop35;
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
            if ( state.backtracking>0 ) { memoize(input, 22, complexExpressionMult_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionMult"



    // $ANTLR start "complexExpressionSigned"
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:758:1: complexExpressionSigned returns [ComplexExpression expression] : ( ( MINUS | PLUS )? expression1= complexExpression ) ;
    public final ComplexExpression complexExpressionSigned() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionSigned_StartIndex = input.index();

        AbstractExpression expression1 =null;



                	expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expression; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:762:2: ( ( ( MINUS | PLUS )? expression1= complexExpression ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:763:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:763:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:764:3: ( MINUS | PLUS )? expression1= complexExpression
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:764:3: ( MINUS | PLUS )?
            int alt36=3;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==MINUS) ) {
                alt36=1;
            }
            else if ( (LA36_0==PLUS) ) {
                alt36=2;
            }
            switch (alt36) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:765:3: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_complexExpressionSigned6609); if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression.negate();
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:769:3: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_complexExpressionSigned6618); if (state.failed) return expression;

                    }
                    break;

            }


            pushFollow(FOLLOW_complexExpression_in_complexExpressionSigned6630);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:777:1: complexExpression returns [AbstractExpression expression] : (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) ;
    public final AbstractExpression complexExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int complexExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;

        AggregationExpression expression2 =null;

        SimpleExpression expression3 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return expression; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:778:2: ( (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:779:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:779:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
            int alt37=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt37=1;
                }
                break;
            case FUNCTION_AGGREGATION:
                {
                alt37=2;
                }
                break;
            case IDENTIFIER:
            case LITERAL_DECIMAL:
            case LITERAL_INTEGER:
            case LITERAL_STRING:
            case QUOTE_DOUBLE:
            case TYPE_DATE:
                {
                alt37=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;

            }

            switch (alt37) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:780:3: expression1= parenExpression
                    {
                    pushFollow(FOLLOW_parenExpression_in_complexExpression6665);
                    expression1=parenExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression1;
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:785:3: expression2= aggregationExpression
                    {
                    pushFollow(FOLLOW_aggregationExpression_in_complexExpression6680);
                    expression2=aggregationExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression2;
                    		}

                    }
                    break;
                case 3 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:790:3: expression3= simpleExpression
                    {
                    pushFollow(FOLLOW_simpleExpression_in_complexExpression6695);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:797:1: parenExpression returns [AbstractExpression expression] : LPAREN expression1= abstractExpression RPAREN ;
    public final AbstractExpression parenExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int parenExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return expression; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:798:2: ( LPAREN expression1= abstractExpression RPAREN )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:799:3: LPAREN expression1= abstractExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenExpression6720); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_parenExpression6727);
            expression1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression = expression1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenExpression6733); if (state.failed) return expression;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:807:1: aggregationExpression returns [AggregationExpression expression] : (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) ;
    public final AggregationExpression aggregationExpression() throws RecognitionException {
        AggregationExpression expression = null;

        int aggregationExpression_StartIndex = input.index();

        Token agg1=null;
        AbstractExpression expr1 =null;



                	expression = new AggregationExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return expression; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:811:9: ( (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:812:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:812:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:813:3: agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN
            {
            agg1=(Token)match(input,FUNCTION_AGGREGATION,FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression6785); if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setAggregation((agg1!=null?agg1.getText():null).toUpperCase());
            		}

            match(input,LPAREN,FOLLOW_LPAREN_in_aggregationExpression6793); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_aggregationExpression6800);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpression(expr1);
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_aggregationExpression6806); if (state.failed) return expression;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:825:1: simpleExpression returns [SimpleExpression expression] : ( (att1= tokenAttribute |lit1= tokenLiteral ) ) ;
    public final SimpleExpression simpleExpression() throws RecognitionException {
        SimpleExpression expression = null;

        int simpleExpression_StartIndex = input.index();

        TokenAttribute att1 =null;

        TokenLiteral lit1 =null;



                	expression = new SimpleExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return expression; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:829:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:830:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:830:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:831:3: (att1= tokenAttribute |lit1= tokenLiteral )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:831:3: (att1= tokenAttribute |lit1= tokenLiteral )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==IDENTIFIER||LA38_0==QUOTE_DOUBLE) ) {
                alt38=1;
            }
            else if ( ((LA38_0 >= LITERAL_DECIMAL && LA38_0 <= LITERAL_STRING)||LA38_0==TYPE_DATE) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:832:3: att1= tokenAttribute
                    {
                    pushFollow(FOLLOW_tokenAttribute_in_simpleExpression6881);
                    att1=tokenAttribute();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                                    	expression.setOper(att1);
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:837:17: lit1= tokenLiteral
                    {
                    pushFollow(FOLLOW_tokenLiteral_in_simpleExpression6937);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:845:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenAttribute tokenAttribute() throws RecognitionException {
        TokenAttribute attribute = null;

        int tokenAttribute_StartIndex = input.index();

        TokenIdentifier table1 =null;

        TokenIdentifier id1 =null;



                	attribute = new TokenAttribute();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return attribute; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:849:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:850:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:850:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:851:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:851:17: (table1= tokenIdentifier DOT )?
            int alt39=2;
            alt39 = dfa39.predict(input);
            switch (alt39) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:852:17: table1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute7065);
                    table1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return attribute;

                    if ( state.backtracking==0 ) {
                                    	TokenTable table = new TokenTable();
                                    	table.setName(table1);
                                    	attribute.setTable(table);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenAttribute7085); if (state.failed) return attribute;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute7124);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:865:1: tokenTable returns [TokenTable table] : ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) );
    public final TokenTable tokenTable() throws RecognitionException {
        TokenTable table = null;

        int tokenTable_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	table = new TokenTable();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return table; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:869:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) )
            int alt42=2;
            alt42 = dfa42.predict(input);
            switch (alt42) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:870:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:870:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:871:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:871:17: (schema1= tokenIdentifier DOT )?
                    int alt40=2;
                    alt40 = dfa40.predict(input);
                    switch (alt40) {
                        case 1 :
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:872:17: schema1= tokenIdentifier DOT
                            {
                            pushFollow(FOLLOW_tokenIdentifier_in_tokenTable7247);
                            schema1=tokenIdentifier();

                            state._fsp--;
                            if (state.failed) return table;

                            if ( state.backtracking==0 ) {
                                                    TokenSchema schema = new TokenSchema();
                                            	table.setSchema(schema);
                                            	table.setVariable(false);
                                            }

                            match(input,DOT,FOLLOW_DOT_in_tokenTable7267); if (state.failed) return table;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable7324);
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:882:12: ( ( COLON )? id1= tokenIdentifier )
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:882:12: ( ( COLON )? id1= tokenIdentifier )
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:883:17: ( COLON )? id1= tokenIdentifier
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:883:17: ( COLON )?
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==COLON) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:884:17: COLON
                            {
                            match(input,COLON,FOLLOW_COLON_in_tokenTable7391); if (state.failed) return table;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable7433);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:893:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
    public final TokenSchema tokenSchema() throws RecognitionException {
        TokenSchema schema = null;

        int tokenSchema_StartIndex = input.index();

        TokenIdentifier tokenIdentifier16 =null;



                	schema = new TokenSchema();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return schema; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:897:9: ( ( tokenIdentifier ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:898:9: ( tokenIdentifier )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:898:9: ( tokenIdentifier )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:899:17: tokenIdentifier
            {
            pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema7514);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:905:1: tokenFunction returns [TokenFunction function] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenFunction tokenFunction() throws RecognitionException {
        TokenFunction function = null;

        int tokenFunction_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	function = new TokenFunction();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return function; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:909:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:910:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:910:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:911:33: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:911:33: (schema1= tokenIdentifier DOT )?
            int alt43=2;
            alt43 = dfa43.predict(input);
            switch (alt43) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:912:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction7630);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return function;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	function.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenFunction7650); if (state.failed) return function;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction7689);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:924:1: tokenVariable returns [TokenVariable variable] : ( variableText ) ;
    public final TokenVariable tokenVariable() throws RecognitionException {
        TokenVariable variable = null;

        int tokenVariable_StartIndex = input.index();

        String variableText17 =null;



                	variable = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return variable; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:928:9: ( ( variableText ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:929:9: ( variableText )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:929:9: ( variableText )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:930:17: variableText
            {
            pushFollow(FOLLOW_variableText_in_tokenVariable7769);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:936:1: tokenAssignment returns [TokenAssignment ass] : ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI ;
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
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return ass; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:940:3: ( ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:941:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable ) SEMI
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:941:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) | ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable )
            int alt44=3;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==COLON) ) {
                alt44=1;
            }
            else if ( (LA44_0==KEYWORD_VAR) ) {
                int LA44_2 = input.LA(2);

                if ( (LA44_2==IDENTIFIER) ) {
                    int LA44_3 = input.LA(3);

                    if ( (LA44_3==EQUAL1) ) {
                        int LA44_4 = input.LA(4);

                        if ( (LA44_4==COLON) ) {
                            alt44=3;
                        }
                        else if ( (LA44_4==KEYWORD_SELECT) ) {
                            alt44=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return ass;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 44, 4, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ass;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 44, 3, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ass;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 44, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ass;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;

            }
            switch (alt44) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:942:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:942:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:943:4: COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement
                    {
                    match(input,COLON,FOLLOW_COLON_in_tokenAssignment7836); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7848);
                    var1=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setReference(true);
                    		 ass.setVar(var1);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7854); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment7861);
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:954:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:954:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:955:4: KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement
                    {
                    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment7882); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7891);
                    var2=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {		 
                    		 ass.setReference(false);
                    		 ass.setVar(var2);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7897); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment7904);
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
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:966:4: ( KEYWORD_VAR var3= tokenVariable EQUAL1 ) COLON var4= tokenVariable
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:966:4: ( KEYWORD_VAR var3= tokenVariable EQUAL1 )
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:967:4: KEYWORD_VAR var3= tokenVariable EQUAL1
                    {
                    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment7928); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7937);
                    var3=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {		 
                    		 ass.setReference(false);
                    		 ass.setVar(var3);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7943); if (state.failed) return ass;

                    }


                    match(input,COLON,FOLLOW_COLON_in_tokenAssignment7954); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7966);
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


            match(input,SEMI,FOLLOW_SEMI_in_tokenAssignment7977); if (state.failed) return ass;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:983:1: tokenFunctionCall returns [TokenFunctionCall call] : ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI ) ;
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

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:987:3: ( ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:988:3: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:988:3: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:989:4: KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable COMMA )* ( KEYWORD_VAR var3= tokenVariable ) RPAREN SEMI
            {
            match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_tokenFunctionCall8009); if (state.failed) return call;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_tokenFunctionCall8014); if (state.failed) return call;

            pushFollow(FOLLOW_tokenFunction_in_tokenFunctionCall8021);
            fun1=tokenFunction();

            state._fsp--;
            if (state.failed) return call;

            if ( state.backtracking==0 ) {
            		 call.setFun(fun1);
            		 }

            match(input,LPAREN,FOLLOW_LPAREN_in_tokenFunctionCall8027); if (state.failed) return call;

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:995:4: ( COLON var1= tokenVariable )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==COLON) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:996:5: COLON var1= tokenVariable
            	    {
            	    match(input,COLON,FOLLOW_COLON_in_tokenFunctionCall8038); if (state.failed) return call;

            	    pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall8051);
            	    var1=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return call;

            	    if ( state.backtracking==0 ) {
            	    			 call.addInVar(var1);
            	    			 }

            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1001:4: ( KEYWORD_VAR var2= tokenVariable COMMA )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( (LA46_0==KEYWORD_VAR) ) {
                    int LA46_1 = input.LA(2);

                    if ( (LA46_1==IDENTIFIER) ) {
                        int LA46_2 = input.LA(3);

                        if ( (LA46_2==COMMA) ) {
                            alt46=1;
                        }


                    }


                }


                switch (alt46) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1002:5: KEYWORD_VAR var2= tokenVariable COMMA
            	    {
            	    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenFunctionCall8072); if (state.failed) return call;

            	    pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall8082);
            	    var2=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return call;

            	    if ( state.backtracking==0 ) {		
            	    			 call.addOutVar(var2);
            	    			 }

            	    match(input,COMMA,FOLLOW_COMMA_in_tokenFunctionCall8089); if (state.failed) return call;

            	    }
            	    break;

            	default :
            	    break loop46;
                }
            } while (true);


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1008:4: ( KEYWORD_VAR var3= tokenVariable )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1009:5: KEYWORD_VAR var3= tokenVariable
            {
            match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenFunctionCall8111); if (state.failed) return call;

            pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall8121);
            var3=tokenVariable();

            state._fsp--;
            if (state.failed) return call;

            if ( state.backtracking==0 ) {		
            			 call.addOutVar(var3);
            			 }

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_tokenFunctionCall8132); if (state.failed) return call;

            match(input,SEMI,FOLLOW_SEMI_in_tokenFunctionCall8137); if (state.failed) return call;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1019:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
    public final TokenIdentifier tokenIdentifier() throws RecognitionException {
        TokenIdentifier identifier = null;

        int tokenIdentifier_StartIndex = input.index();

        String identifierText18 =null;



                	identifier = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return identifier; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1023:9: ( ( identifierText ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1024:9: ( identifierText )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1024:9: ( identifierText )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1025:17: identifierText
            {
            pushFollow(FOLLOW_identifierText_in_tokenIdentifier8205);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1032:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) ;
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

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1036:9: ( ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1037:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1037:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
            int alt47=4;
            switch ( input.LA(1) ) {
            case TYPE_VARCHAR:
                {
                alt47=1;
                }
                break;
            case TYPE_INTEGER:
                {
                alt47=2;
                }
                break;
            case TYPE_DECIMAL:
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
                if (state.backtracking>0) {state.failed=true; return dataType;}
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;

            }

            switch (alt47) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1038:17: TYPE_VARCHAR
                    {
                    TYPE_VARCHAR19=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType8286); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_VARCHAR19!=null?TYPE_VARCHAR19.getText():null));
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1042:17: TYPE_INTEGER
                    {
                    TYPE_INTEGER20=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType8324); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_INTEGER20!=null?TYPE_INTEGER20.getText():null));
                                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1046:17: TYPE_DECIMAL
                    {
                    TYPE_DECIMAL21=(Token)match(input,TYPE_DECIMAL,FOLLOW_TYPE_DECIMAL_in_tokenDataType8362); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_DECIMAL21!=null?TYPE_DECIMAL21.getText():null));
                                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1050:17: TYPE_DATE
                    {
                    TYPE_DATE22=(Token)match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDataType8400); if (state.failed) return dataType;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1056:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
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

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1060:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1061:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1061:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1062:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1062:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            int alt48=4;
            switch ( input.LA(1) ) {
            case LITERAL_INTEGER:
                {
                alt48=1;
                }
                break;
            case LITERAL_STRING:
                {
                alt48=2;
                }
                break;
            case LITERAL_DECIMAL:
                {
                alt48=3;
                }
                break;
            case TYPE_DATE:
                {
                alt48=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return literal;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;

            }

            switch (alt48) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1063:17: tokenIntegerLiteral
                    {
                    pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral8491);
                    tokenIntegerLiteral23=tokenIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenIntegerLiteral23;
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1067:17: tokenStringLiteral
                    {
                    pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral8529);
                    tokenStringLiteral24=tokenStringLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenStringLiteral24;
                                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1071:17: tokenDecimalLiteral
                    {
                    pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral8567);
                    tokenDecimalLiteral25=tokenDecimalLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenDecimalLiteral25;
                                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1075:17: tokenDateLiteral
                    {
                    pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral8605);
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1082:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
    public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
        TokenStringLiteral literal = null;

        int tokenStringLiteral_StartIndex = input.index();

        Token lit1=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return literal; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1086:9: ( (lit1= LITERAL_STRING ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1087:9: (lit1= LITERAL_STRING )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1087:9: (lit1= LITERAL_STRING )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1088:17: lit1= LITERAL_STRING
            {
            lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral8705); if (state.failed) return literal;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1094:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
    public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
        TokenIntegerLiteral literal = null;

        int tokenIntegerLiteral_StartIndex = input.index();

        Token LITERAL_INTEGER27=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return literal; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1098:9: ( ( LITERAL_INTEGER ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1099:9: ( LITERAL_INTEGER )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1099:9: ( LITERAL_INTEGER )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1100:17: LITERAL_INTEGER
            {
            LITERAL_INTEGER27=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral8785); if (state.failed) return literal;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1107:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
    public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
        TokenDecimalLiteral literal = null;

        int tokenDecimalLiteral_StartIndex = input.index();

        Token LITERAL_DECIMAL28=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return literal; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1111:9: ( ( LITERAL_DECIMAL ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1112:9: ( LITERAL_DECIMAL )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1112:9: ( LITERAL_DECIMAL )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1113:17: LITERAL_DECIMAL
            {
            LITERAL_DECIMAL28=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral8875); if (state.failed) return literal;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1119:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( TYPE_DATE LITERAL_STRING ) ;
    public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
        TokenDateLiteral literal = null;

        int tokenDateLiteral_StartIndex = input.index();

        Token LITERAL_STRING29=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return literal; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1123:9: ( ( TYPE_DATE LITERAL_STRING ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1124:9: ( TYPE_DATE LITERAL_STRING )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1124:9: ( TYPE_DATE LITERAL_STRING )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1126:17: TYPE_DATE LITERAL_STRING
            {
            match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDateLiteral8973); if (state.failed) return literal;

            LITERAL_STRING29=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenDateLiteral8991); if (state.failed) return literal;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1133:2: variableText returns [String text] : (var1= IDENTIFIER ) ;
    public final String variableText() throws RecognitionException {
        String text = null;

        int variableText_StartIndex = input.index();

        Token var1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return text; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1134:3: ( (var1= IDENTIFIER ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1135:3: (var1= IDENTIFIER )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1135:3: (var1= IDENTIFIER )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1137:4: var1= IDENTIFIER
            {
            var1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variableText9050); if (state.failed) return text;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1143:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) | ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE ) ) ;
    public final String identifierText() throws RecognitionException {
        String text = null;

        int identifierText_StartIndex = input.index();

        Token id1=null;
        Token id2=null;
        Token id3=null;
        Token id4=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return text; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1144:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) | ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE ) ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1145:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) | ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE ) )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1145:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) | ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE ) )
            int alt50=3;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==IDENTIFIER) ) {
                alt50=1;
            }
            else if ( (LA50_0==QUOTE_DOUBLE) ) {
                int LA50_2 = input.LA(2);

                if ( (LA50_2==IDENTIFIER) ) {
                    int LA50_3 = input.LA(3);

                    if ( (LA50_3==QUOTE_DOUBLE) ) {
                        alt50=2;
                    }
                    else if ( (LA50_3==DIV||LA50_3==DOT) ) {
                        alt50=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return text;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 50, 3, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return text;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return text;}
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;

            }
            switch (alt50) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1146:4: (id1= IDENTIFIER )
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1146:4: (id1= IDENTIFIER )
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1147:4: id1= IDENTIFIER
                    {
                    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText9102); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id1!=null?id1.getText():null).toUpperCase();
                                    }

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1152:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1152:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1153:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText9158); if (state.failed) return text;

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText9174); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id2!=null?id2.getText():null);
                                    }

                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText9194); if (state.failed) return text;

                    }


                    }
                    break;
                case 3 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1160:11: ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE )
                    {
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1160:11: ( QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE )
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1161:12: QUOTE_DOUBLE id2= IDENTIFIER ( DIV id3= IDENTIFIER )* DOT (id4= IDENTIFIER ) QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText9250); if (state.failed) return text;

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText9267); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                    				        text = (id2!=null?id2.getText():null);
                    			}

                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1165:11: ( DIV id3= IDENTIFIER )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==DIV) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1166:13: DIV id3= IDENTIFIER
                    	    {
                    	    match(input,DIV,FOLLOW_DIV_in_identifierText9297); if (state.failed) return text;

                    	    id3=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText9315); if (state.failed) return text;

                    	    if ( state.backtracking==0 ) {
                    	    					        text += "/";
                    	    					        text += (id3!=null?id3.getText():null);
                    	    				}

                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);


                    match(input,DOT,FOLLOW_DOT_in_identifierText9328); if (state.failed) return text;

                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1173:4: (id4= IDENTIFIER )
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1174:5: id4= IDENTIFIER
                    {
                    id4=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText9343); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                    			        	text += ".";
                    			        	text += (id4!=null?id4.getText():null);
                    			        }

                    }


                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText9376); if (state.failed) return text;

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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1184:1: tokenAddOperator : ( PLUS | MINUS ) ;
    public final FunSQLParser.tokenAddOperator_return tokenAddOperator() throws RecognitionException {
        FunSQLParser.tokenAddOperator_return retval = new FunSQLParser.tokenAddOperator_return();
        retval.start = input.LT(1);

        int tokenAddOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1185:5: ( ( PLUS | MINUS ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1192:1: tokenMultOperator : ( MULT | DIV ) ;
    public final FunSQLParser.tokenMultOperator_return tokenMultOperator() throws RecognitionException {
        FunSQLParser.tokenMultOperator_return retval = new FunSQLParser.tokenMultOperator_return();
        retval.start = input.LT(1);

        int tokenMultOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1193:5: ( ( MULT | DIV ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1200:1: tokenCompOperator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | KEYWORD_LIKE ) ;
    public final FunSQLParser.tokenCompOperator_return tokenCompOperator() throws RecognitionException {
        FunSQLParser.tokenCompOperator_return retval = new FunSQLParser.tokenCompOperator_return();
        retval.start = input.LT(1);

        int tokenCompOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }

            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1201:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | KEYWORD_LIKE ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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

    // $ANTLR start synpred43_FunSQL
    public final void synpred43_FunSQL_fragment() throws RecognitionException {
        AbstractPredicate predicate1 =null;


        // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:666:3: (predicate1= parenPredicate )
        // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:666:3: predicate1= parenPredicate
        {
        pushFollow(FOLLOW_parenPredicate_in_synpred43_FunSQL6213);
        predicate1=parenPredicate();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred43_FunSQL

    // $ANTLR start synpred53_FunSQL
    public final void synpred53_FunSQL_fragment() throws RecognitionException {
        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;


        // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:870:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
        // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:870:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
        {
        // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:870:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
        // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:871:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
        {
        // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:871:17: (schema1= tokenIdentifier DOT )?
        int alt60=2;
        alt60 = dfa60.predict(input);
        switch (alt60) {
            case 1 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:872:17: schema1= tokenIdentifier DOT
                {
                pushFollow(FOLLOW_tokenIdentifier_in_synpred53_FunSQL7247);
                schema1=tokenIdentifier();

                state._fsp--;
                if (state.failed) return ;

                match(input,DOT,FOLLOW_DOT_in_synpred53_FunSQL7267); if (state.failed) return ;

                }
                break;

        }


        pushFollow(FOLLOW_tokenIdentifier_in_synpred53_FunSQL7324);
        id1=tokenIdentifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }

    }
    // $ANTLR end synpred53_FunSQL

    // Delegated rules

    public final boolean synpred43_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred43_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred53_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred53_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA8 dfa8 = new DFA8(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA39 dfa39 = new DFA39(this);
    protected DFA42 dfa42 = new DFA42(this);
    protected DFA40 dfa40 = new DFA40(this);
    protected DFA43 dfa43 = new DFA43(this);
    protected DFA60 dfa60 = new DFA60(this);
    static final String DFA8_eotS =
        "\15\uffff";
    static final String DFA8_eofS =
        "\15\uffff";
    static final String DFA8_minS =
        "\1\125\1\41\1\15\1\41\2\uffff\1\21\1\15\2\41\1\21\1\144\1\15";
    static final String DFA8_maxS =
        "\1\125\1\144\1\151\1\41\2\uffff\1\144\1\151\2\41\1\23\1\144\1\151";
    static final String DFA8_acceptS =
        "\4\uffff\1\1\1\2\7\uffff";
    static final String DFA8_specialS =
        "\15\uffff}>";
    static final String[] DFA8_transitionS = {
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

    class DFA8 extends DFA {

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
        public String getDescription() {
            return "307:17: ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )?";
        }
    }
    static final String DFA9_eotS =
        "\15\uffff";
    static final String DFA9_eofS =
        "\15\uffff";
    static final String DFA9_minS =
        "\1\15\1\41\1\uffff\1\15\1\41\1\uffff\1\21\1\15\2\41\1\21\1\144\1"+
        "\15";
    static final String DFA9_maxS =
        "\1\151\1\144\1\uffff\1\151\1\41\1\uffff\1\144\1\151\2\41\1\23\1"+
        "\144\1\151";
    static final String DFA9_acceptS =
        "\2\uffff\1\2\2\uffff\1\1\7\uffff";
    static final String DFA9_specialS =
        "\15\uffff}>";
    static final String[] DFA9_transitionS = {
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

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "335:17: ( COMMA c3= tokenIdentifier )?";
        }
    }
    static final String DFA11_eotS =
        "\15\uffff";
    static final String DFA11_eofS =
        "\15\uffff";
    static final String DFA11_minS =
        "\1\15\1\41\1\uffff\1\15\1\41\1\uffff\1\21\1\15\2\41\1\21\1\144\1"+
        "\15";
    static final String DFA11_maxS =
        "\1\151\1\144\1\uffff\1\151\1\41\1\uffff\1\144\1\151\2\41\1\23\1"+
        "\144\1\151";
    static final String DFA11_acceptS =
        "\2\uffff\1\2\2\uffff\1\1\7\uffff";
    static final String DFA11_specialS =
        "\15\uffff}>";
    static final String[] DFA11_transitionS = {
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

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "352:17: ( COMMA c3= tokenIdentifier )?";
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
            "\1\4\3\uffff\1\4\1\uffff\1\3\2\uffff\1\4\4\uffff\3\4\7\uffff"+
            "\2\4\13\uffff\1\4\1\uffff\2\4\3\uffff\1\4\4\uffff\1\4\20\uffff"+
            "\3\4\5\uffff\1\4\1\uffff\1\4\1\uffff\2\4\3\uffff\1\4\10\uffff"+
            "\1\4\1\uffff\1\4",
            "\1\5",
            "",
            "",
            "\1\7\1\uffff\1\10\120\uffff\1\6",
            "\1\4\3\uffff\1\4\1\uffff\1\3\2\uffff\1\4\4\uffff\3\4\7\uffff"+
            "\2\4\13\uffff\1\4\1\uffff\2\4\3\uffff\1\4\4\uffff\1\4\20\uffff"+
            "\3\4\5\uffff\1\4\1\uffff\1\4\1\uffff\2\4\3\uffff\1\4\10\uffff"+
            "\1\4\1\uffff\1\4",
            "\1\11",
            "\1\12",
            "\1\7\1\uffff\1\10",
            "\1\13",
            "\1\4\3\uffff\1\4\1\uffff\1\3\2\uffff\1\4\4\uffff\3\4\7\uffff"+
            "\2\4\13\uffff\1\4\1\uffff\2\4\3\uffff\1\4\4\uffff\1\4\20\uffff"+
            "\3\4\5\uffff\1\4\1\uffff\1\4\1\uffff\2\4\3\uffff\1\4\10\uffff"+
            "\1\4\1\uffff\1\4"
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

    class DFA39 extends DFA {

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
        public String getDescription() {
            return "851:17: (table1= tokenIdentifier DOT )?";
        }
    }
    static final String DFA42_eotS =
        "\14\uffff";
    static final String DFA42_eofS =
        "\14\uffff";
    static final String DFA42_minS =
        "\1\14\1\0\1\41\2\uffff\1\21\1\0\2\41\1\21\1\144\1\0";
    static final String DFA42_maxS =
        "\1\144\1\0\1\41\2\uffff\1\144\1\0\2\41\1\23\1\144\1\0";
    static final String DFA42_acceptS =
        "\3\uffff\1\2\1\1\7\uffff";
    static final String DFA42_specialS =
        "\1\uffff\1\1\4\uffff\1\2\4\uffff\1\0}>";
    static final String[] DFA42_transitionS = {
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

    class DFA42 extends DFA {

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
        public String getDescription() {
            return "865:1: tokenTable returns [TokenTable table] : ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA42_11 = input.LA(1);

                         
                        int index42_11 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred53_FunSQL()) ) {s = 4;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index42_11);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA42_1 = input.LA(1);

                         
                        int index42_1 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred53_FunSQL()) ) {s = 4;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index42_1);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA42_6 = input.LA(1);

                         
                        int index42_6 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred53_FunSQL()) ) {s = 4;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index42_6);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 42, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA40_eotS =
        "\14\uffff";
    static final String DFA40_eofS =
        "\1\uffff\1\4\4\uffff\1\4\4\uffff\1\4";
    static final String DFA40_minS =
        "\1\41\1\15\1\41\2\uffff\1\21\1\15\2\41\1\21\1\144\1\15";
    static final String DFA40_maxS =
        "\1\144\1\153\1\41\2\uffff\1\144\1\153\2\41\1\23\1\144\1\153";
    static final String DFA40_acceptS =
        "\3\uffff\1\1\1\2\7\uffff";
    static final String DFA40_specialS =
        "\14\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\1\102\uffff\1\2",
            "\1\4\5\uffff\1\3\22\uffff\1\4\15\uffff\3\4\11\uffff\2\4\1\uffff"+
            "\1\4\10\uffff\1\4\10\uffff\1\4\25\uffff\1\4",
            "\1\5",
            "",
            "",
            "\1\7\1\uffff\1\10\120\uffff\1\6",
            "\1\4\5\uffff\1\3\22\uffff\1\4\15\uffff\3\4\11\uffff\2\4\1\uffff"+
            "\1\4\10\uffff\1\4\10\uffff\1\4\25\uffff\1\4",
            "\1\11",
            "\1\12",
            "\1\7\1\uffff\1\10",
            "\1\13",
            "\1\4\5\uffff\1\3\22\uffff\1\4\15\uffff\3\4\11\uffff\2\4\1\uffff"+
            "\1\4\10\uffff\1\4\10\uffff\1\4\25\uffff\1\4"
    };

    static final short[] DFA40_eot = DFA.unpackEncodedString(DFA40_eotS);
    static final short[] DFA40_eof = DFA.unpackEncodedString(DFA40_eofS);
    static final char[] DFA40_min = DFA.unpackEncodedStringToUnsignedChars(DFA40_minS);
    static final char[] DFA40_max = DFA.unpackEncodedStringToUnsignedChars(DFA40_maxS);
    static final short[] DFA40_accept = DFA.unpackEncodedString(DFA40_acceptS);
    static final short[] DFA40_special = DFA.unpackEncodedString(DFA40_specialS);
    static final short[][] DFA40_transition;

    static {
        int numStates = DFA40_transitionS.length;
        DFA40_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA40_transition[i] = DFA.unpackEncodedString(DFA40_transitionS[i]);
        }
    }

    class DFA40 extends DFA {

        public DFA40(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 40;
            this.eot = DFA40_eot;
            this.eof = DFA40_eof;
            this.min = DFA40_min;
            this.max = DFA40_max;
            this.accept = DFA40_accept;
            this.special = DFA40_special;
            this.transition = DFA40_transition;
        }
        public String getDescription() {
            return "871:17: (schema1= tokenIdentifier DOT )?";
        }
    }
    static final String DFA43_eotS =
        "\14\uffff";
    static final String DFA43_eofS =
        "\1\uffff\1\4\4\uffff\1\4\4\uffff\1\4";
    static final String DFA43_minS =
        "\1\41\1\23\1\41\2\uffff\1\21\1\23\2\41\1\21\1\144\1\23";
    static final String DFA43_maxS =
        "\1\144\1\153\1\41\2\uffff\1\144\1\153\2\41\1\23\1\144\1\153";
    static final String DFA43_acceptS =
        "\3\uffff\1\1\1\2\7\uffff";
    static final String DFA43_specialS =
        "\14\uffff}>";
    static final String[] DFA43_transitionS = {
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

    static final short[] DFA43_eot = DFA.unpackEncodedString(DFA43_eotS);
    static final short[] DFA43_eof = DFA.unpackEncodedString(DFA43_eofS);
    static final char[] DFA43_min = DFA.unpackEncodedStringToUnsignedChars(DFA43_minS);
    static final char[] DFA43_max = DFA.unpackEncodedStringToUnsignedChars(DFA43_maxS);
    static final short[] DFA43_accept = DFA.unpackEncodedString(DFA43_acceptS);
    static final short[] DFA43_special = DFA.unpackEncodedString(DFA43_specialS);
    static final short[][] DFA43_transition;

    static {
        int numStates = DFA43_transitionS.length;
        DFA43_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA43_transition[i] = DFA.unpackEncodedString(DFA43_transitionS[i]);
        }
    }

    class DFA43 extends DFA {

        public DFA43(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 43;
            this.eot = DFA43_eot;
            this.eof = DFA43_eof;
            this.min = DFA43_min;
            this.max = DFA43_max;
            this.accept = DFA43_accept;
            this.special = DFA43_special;
            this.transition = DFA43_transition;
        }
        public String getDescription() {
            return "911:33: (schema1= tokenIdentifier DOT )?";
        }
    }
    static final String DFA60_eotS =
        "\14\uffff";
    static final String DFA60_eofS =
        "\1\uffff\1\4\4\uffff\1\4\4\uffff\1\4";
    static final String DFA60_minS =
        "\1\41\1\23\1\41\2\uffff\1\21\1\23\2\41\1\21\1\144\1\23";
    static final String DFA60_maxS =
        "\1\144\1\23\1\41\2\uffff\1\144\1\23\2\41\1\23\1\144\1\23";
    static final String DFA60_acceptS =
        "\3\uffff\1\1\1\2\7\uffff";
    static final String DFA60_specialS =
        "\14\uffff}>";
    static final String[] DFA60_transitionS = {
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

    static final short[] DFA60_eot = DFA.unpackEncodedString(DFA60_eotS);
    static final short[] DFA60_eof = DFA.unpackEncodedString(DFA60_eofS);
    static final char[] DFA60_min = DFA.unpackEncodedStringToUnsignedChars(DFA60_minS);
    static final char[] DFA60_max = DFA.unpackEncodedStringToUnsignedChars(DFA60_maxS);
    static final short[] DFA60_accept = DFA.unpackEncodedString(DFA60_acceptS);
    static final short[] DFA60_special = DFA.unpackEncodedString(DFA60_specialS);
    static final short[][] DFA60_transition;

    static {
        int numStates = DFA60_transitionS.length;
        DFA60_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA60_transition[i] = DFA.unpackEncodedString(DFA60_transitionS[i]);
        }
    }

    class DFA60 extends DFA {

        public DFA60(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 60;
            this.eot = DFA60_eot;
            this.eof = DFA60_eof;
            this.min = DFA60_min;
            this.max = DFA60_max;
            this.accept = DFA60_accept;
            this.special = DFA60_special;
            this.transition = DFA60_transition;
        }
        public String getDescription() {
            return "871:17: (schema1= tokenIdentifier DOT )?";
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
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement2699 = new BitSet(new long[]{0x0044000000000000L,0x000000000000000AL});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2735 = new BitSet(new long[]{0x0000000200001000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2755 = new BitSet(new long[]{0x0040000000000000L,0x000000000000000AL});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2814 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2816 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_REPLICATED_in_createTableStatement2877 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2879 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2881 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2902 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2924 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2946 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_KEYWORD_PARTITIONED_in_createTableStatement3040 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_BY_in_createTableStatement3042 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3063 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement3117 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3137 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement3191 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3211 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement3266 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement3303 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3323 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3362 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3364 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3384 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3407 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3409 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3411 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3432 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement3453 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3458 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement3500 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3520 = new BitSet(new long[]{0x0040000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3558 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3560 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3580 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_REPLICATED_in_createTableStatement3603 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3605 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3607 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3628 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement3649 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3654 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement3697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement3811 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement3829 = new BitSet(new long[]{0x0000000200001000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenTable_in_dropTableStatement3849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createFunctionStatement3915 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement3933 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenFunction_in_createFunctionStatement3953 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_createFunctionStatement3969 = new BitSet(new long[]{0x8040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createFunctionStatement3998 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement4018 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4025 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_createFunctionStatement4031 = new BitSet(new long[]{0x8040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement4069 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement4089 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4096 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_COMMA_in_createFunctionStatement4109 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement4114 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement4128 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement4134 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RPAREN_in_createFunctionStatement4143 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement4147 = new BitSet(new long[]{0x0002040000001000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenAssignment_in_createFunctionStatement4161 = new BitSet(new long[]{0x0002040000001000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenFunctionCall_in_createFunctionStatement4240 = new BitSet(new long[]{0x0002040000001000L,0x0000000000000800L});
    public static final BitSet FOLLOW_KEYWORD_END_in_createFunctionStatement4282 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_createFunctionStatement4289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropFunctionStatement4366 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement4384 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenFunction_in_dropFunctionStatement4404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CALL_in_callFunctionStatement4470 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement4488 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenFunction_in_callFunctionStatement4508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement4585 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement4605 = new BitSet(new long[]{0x0004004000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4660 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4681 = new BitSet(new long[]{0x0004000000002000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement4755 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement4775 = new BitSet(new long[]{0x0004004000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4830 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4851 = new BitSet(new long[]{0x0004000000002000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement4960 = new BitSet(new long[]{0x0000000200001000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement4980 = new BitSet(new long[]{0x0030004000002002L,0x0000000000001000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement5036 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement5057 = new BitSet(new long[]{0x0030000000002002L,0x0000000000001000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement5131 = new BitSet(new long[]{0x0000000200001000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement5151 = new BitSet(new long[]{0x0030004000002002L,0x0000000000001000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement5206 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement5227 = new BitSet(new long[]{0x0030000000002002L,0x0000000000001000L});
    public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement5337 = new BitSet(new long[]{0x2000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement5357 = new BitSet(new long[]{0x0030000000000002L});
    public static final BitSet FOLLOW_KEYWORD_GROUP_in_selectStatement5464 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_BY_in_selectStatement5466 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement5486 = new BitSet(new long[]{0x0020000000002002L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement5540 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement5560 = new BitSet(new long[]{0x0020000000002002L});
    public static final BitSet FOLLOW_KEYWORD_HAVING_in_selectStatement5686 = new BitSet(new long[]{0x2000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement5706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_LOAD_in_loadDataInfileStatement5799 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_KEYWORD_DATA_in_loadDataInfileStatement5809 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_KEYWORD_INFILE_in_loadDataInfileStatement5819 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_identifierText_in_loadDataInfileStatement5831 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_KEYWORD_INTO_in_loadDataInfileStatement5843 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_loadDataInfileStatement5853 = new BitSet(new long[]{0x0000000200001000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenTable_in_loadDataInfileStatement5865 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_KEYWORD_PARTITION_in_loadDataInfileStatement5888 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_loadDataInfileStatement5899 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_identifierText_in_loadDataInfileStatement5912 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_COMMA_in_loadDataInfileStatement5937 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_identifierText_in_loadDataInfileStatement5951 = new BitSet(new long[]{0x0000000000002000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RPAREN_in_loadDataInfileStatement5976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate6017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr6055 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr6066 = new BitSet(new long[]{0x2000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr6074 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd6113 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd6124 = new BitSet(new long[]{0x2000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd6132 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot6173 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot6186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_complexPredicate6213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicate_in_complexPredicate6228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenPredicate6253 = new BitSet(new long[]{0x2000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate6260 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenPredicate6266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate6308 = new BitSet(new long[]{0x0200000038400000L,0x0000000018038000L});
    public static final BitSet FOLLOW_tokenCompOperator_in_simplePredicate6363 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate6401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionAdd_in_abstractExpression6455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd6485 = new BitSet(new long[]{0x0000000000000002L,0x0000000100800000L});
    public static final BitSet FOLLOW_tokenAddOperator_in_complexExpressionAdd6498 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd6506 = new BitSet(new long[]{0x0000000000000002L,0x0000000100800000L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult6546 = new BitSet(new long[]{0x0000000000020002L,0x0000000002000000L});
    public static final BitSet FOLLOW_tokenMultOperator_in_complexExpressionMult6559 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult6567 = new BitSet(new long[]{0x0000000000020002L,0x0000000002000000L});
    public static final BitSet FOLLOW_MINUS_in_complexExpressionSigned6609 = new BitSet(new long[]{0x0000000202000000L,0x00010010003C0000L});
    public static final BitSet FOLLOW_PLUS_in_complexExpressionSigned6618 = new BitSet(new long[]{0x0000000202000000L,0x00010010003C0000L});
    public static final BitSet FOLLOW_complexExpression_in_complexExpressionSigned6630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenExpression_in_complexExpression6665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggregationExpression_in_complexExpression6680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleExpression_in_complexExpression6695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenExpression6720 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_abstractExpression_in_parenExpression6727 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenExpression6733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression6785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_aggregationExpression6793 = new BitSet(new long[]{0x0000000202000000L,0x0001001100BC0000L});
    public static final BitSet FOLLOW_abstractExpression_in_aggregationExpression6800 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RPAREN_in_aggregationExpression6806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simpleExpression6881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenLiteral_in_simpleExpression6937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute7065 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenAttribute7085 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute7124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable7247 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenTable7267 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable7324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenTable7391 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable7433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema7514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction7630 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenFunction7650 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction7689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableText_in_tokenVariable7769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenAssignment7836 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7848 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7854 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment7861 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment7882 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7891 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7897 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment7904 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment7928 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7937 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7943 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_COLON_in_tokenAssignment7954 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7966 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_tokenAssignment7977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CALL_in_tokenFunctionCall8009 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_tokenFunctionCall8014 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenFunction_in_tokenFunctionCall8021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_LPAREN_in_tokenFunctionCall8027 = new BitSet(new long[]{0x0000000000001000L,0x0000000000000800L});
    public static final BitSet FOLLOW_COLON_in_tokenFunctionCall8038 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall8051 = new BitSet(new long[]{0x0000000000001000L,0x0000000000000800L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenFunctionCall8072 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall8082 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_tokenFunctionCall8089 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenFunctionCall8111 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall8121 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RPAREN_in_tokenFunctionCall8132 = new BitSet(new long[]{0x0000000000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_tokenFunctionCall8137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierText_in_tokenIdentifier8205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType8286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType8324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DECIMAL_in_tokenDataType8362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DATE_in_tokenDataType8400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral8491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral8529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral8567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral8605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral8705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral8785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral8875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DATE_in_tokenDateLiteral8973 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenDateLiteral8991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variableText9050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText9102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText9158 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText9174 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText9194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText9250 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText9267 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_DIV_in_identifierText9297 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText9315 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_DOT_in_identifierText9328 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText9343 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText9376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_synpred43_FunSQL6213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_synpred53_FunSQL7247 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_synpred53_FunSQL7267 = new BitSet(new long[]{0x0000000200000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_synpred53_FunSQL7324 = new BitSet(new long[]{0x0000000000000002L});

}