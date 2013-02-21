// $ANTLR 3.4 C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g 2013-02-21 18:41:49
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", "AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", "D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", "F", "FUNCTION_AGGREGATION", "G", "GREATER_EQUAL1", "GREATER_EQUAL2", "GREATER_THAN", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", "KEYWORD_AND", "KEYWORD_AS", "KEYWORD_AVG", "KEYWORD_BEGIN", "KEYWORD_BY", "KEYWORD_CALL", "KEYWORD_CONNECTION", "KEYWORD_COUNT", "KEYWORD_CREATE", "KEYWORD_DISTINCT", "KEYWORD_DROP", "KEYWORD_END", "KEYWORD_FROM", "KEYWORD_FUNCTION", "KEYWORD_GROUP", "KEYWORD_HAVING", "KEYWORD_IN", "KEYWORD_MAX", "KEYWORD_MIN", "KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_OUT", "KEYWORD_PARTITIONED", "KEYWORD_PASSWD", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", "KEYWORD_SUM", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_VAR", "KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", "LESS_THAN", "LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", "MINUS", "MOD", "MULT", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", "QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", "SHIFT_RIGHT", "T", "TILDE", "TYPE_DATE", "TYPE_DECIMAL", "TYPE_INTEGER", "TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
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
    public static final int KEYWORD_SCHEMA=61;
    public static final int KEYWORD_SELECT=62;
    public static final int KEYWORD_STORE=63;
    public static final int KEYWORD_SUM=64;
    public static final int KEYWORD_TABLE=65;
    public static final int KEYWORD_URL=66;
    public static final int KEYWORD_USER=67;
    public static final int KEYWORD_VAR=68;
    public static final int KEYWORD_WHERE=69;
    public static final int L=70;
    public static final int LBRACKET=71;
    public static final int LESS_EQUAL1=72;
    public static final int LESS_EQUAL2=73;
    public static final int LESS_THAN=74;
    public static final int LITERAL_DECIMAL=75;
    public static final int LITERAL_INTEGER=76;
    public static final int LITERAL_STRING=77;
    public static final int LPAREN=78;
    public static final int M=79;
    public static final int MINUS=80;
    public static final int MOD=81;
    public static final int MULT=82;
    public static final int N=83;
    public static final int NOT_EQUAL1=84;
    public static final int NOT_EQUAL2=85;
    public static final int O=86;
    public static final int P=87;
    public static final int PIPE=88;
    public static final int PLUS=89;
    public static final int Q=90;
    public static final int QUESTION=91;
    public static final int QUOTED_STRING=92;
    public static final int QUOTE_DOUBLE=93;
    public static final int QUOTE_SINGLE=94;
    public static final int QUOTE_TRIPLE=95;
    public static final int R=96;
    public static final int RBRACKET=97;
    public static final int RPAREN=98;
    public static final int S=99;
    public static final int SEMI=100;
    public static final int SHIFT_LEFT=101;
    public static final int SHIFT_RIGHT=102;
    public static final int T=103;
    public static final int TILDE=104;
    public static final int TYPE_DATE=105;
    public static final int TYPE_DECIMAL=106;
    public static final int TYPE_INTEGER=107;
    public static final int TYPE_VARCHAR=108;
    public static final int U=109;
    public static final int UNDERSCORE=110;
    public static final int V=111;
    public static final int W=112;
    public static final int WS=113;
    public static final int X=114;
    public static final int Y=115;
    public static final int Z=116;

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
        this.state.ruleMemo = new HashMap[112+1];
         

    }

    public String[] getTokenNames() { return FunSQLParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g"; }


      @Override
      protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
        throw new MismatchedTokenException(ttype, input);
      }

      @Override
      public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow) throws RecognitionException {
        throw e;
      }



    // $ANTLR start "statement"
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:93:1: statement returns [AbstractServerStmt stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? ) ;
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

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:97:9: ( ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:98:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:98:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:99:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )?
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:99:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement )
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:100:17: createSchemaStatement
                    {
                    pushFollow(FOLLOW_createSchemaStatement_in_statement1105);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:106:17: dropSchemaStatement
                    {
                    pushFollow(FOLLOW_dropSchemaStatement_in_statement1160);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:112:17: createConnectionStatement
                    {
                    pushFollow(FOLLOW_createConnectionStatement_in_statement1215);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:118:17: dropConnectionStatement
                    {
                    pushFollow(FOLLOW_dropConnectionStatement_in_statement1270);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:124:17: createTableStatement
                    {
                    pushFollow(FOLLOW_createTableStatement_in_statement1325);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:130:17: dropTableStatement
                    {
                    pushFollow(FOLLOW_dropTableStatement_in_statement1380);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:136:17: createFunctionStatement
                    {
                    pushFollow(FOLLOW_createFunctionStatement_in_statement1435);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:142:17: dropFunctionStatement
                    {
                    pushFollow(FOLLOW_dropFunctionStatement_in_statement1490);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:148:17: callFunctionStatement
                    {
                    pushFollow(FOLLOW_callFunctionStatement_in_statement1545);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:154:17: selectStatement
                    {
                    pushFollow(FOLLOW_selectStatement_in_statement1600);
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


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:160:17: ( SEMI )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==SEMI) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:160:17: SEMI
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:164:1: createSchemaStatement returns [CreateSchemaStmt stmt] : ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) ;
    public final FunSQLParser.createSchemaStatement_return createSchemaStatement() throws RecognitionException {
        FunSQLParser.createSchemaStatement_return retval = new FunSQLParser.createSchemaStatement_return();
        retval.start = input.LT(1);

        int createSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema11 =null;



                	retval.stmt = new CreateSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:168:9: ( ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:169:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:169:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:170:17: KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:178:1: dropSchemaStatement returns [DropSchemaStmt stmt] : ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) ;
    public final FunSQLParser.dropSchemaStatement_return dropSchemaStatement() throws RecognitionException {
        FunSQLParser.dropSchemaStatement_return retval = new FunSQLParser.dropSchemaStatement_return();
        retval.start = input.LT(1);

        int dropSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema12 =null;



                	retval.stmt = new DropSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:182:9: ( ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:183:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:183:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:184:17: KEYWORD_DROP KEYWORD_SCHEMA tokenSchema
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:192:1: createConnectionStatement returns [CreateConnectionStmt stmt] : ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) ;
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

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:196:9: ( ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:197:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:197:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:198:17: KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:222:1: dropConnectionStatement returns [DropConnectionStmt stmt] : ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) ;
    public final FunSQLParser.dropConnectionStatement_return dropConnectionStatement() throws RecognitionException {
        FunSQLParser.dropConnectionStatement_return retval = new FunSQLParser.dropConnectionStatement_return();
        retval.start = input.LT(1);

        int dropConnectionStatement_StartIndex = input.index();

        TokenIdentifier tokenIdentifier14 =null;



                	retval.stmt = new DropConnectionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:226:9: ( ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:227:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:227:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:228:17: KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:236:1: createTableStatement returns [CreateTableStmt stmt] : ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) ) ) ;
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

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:240:9: ( ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) ) ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:241:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) ) )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:241:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:242:17: KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) )
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2360); if (state.failed) return retval;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2378); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_createTableStatement2398);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setTable(table1);
                            	retval.stmt.setSourceTable(table1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2418); if (state.failed) return retval;

            pushFollow(FOLLOW_identifierText_in_createTableStatement2438);
            att1=identifierText();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addAttribute(att1);
                            }

            pushFollow(FOLLOW_tokenDataType_in_createTableStatement2476);
            dataType1=tokenDataType();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addType(dataType1);
                            }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:257:17: ( COMMA att2= identifierText dataType2= tokenDataType )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:258:17: COMMA att2= identifierText dataType2= tokenDataType
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2530); if (state.failed) return retval;

            	    pushFollow(FOLLOW_identifierText_in_createTableStatement2550);
            	    att2=identifierText();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addAttribute(att2);
            	                    }

            	    pushFollow(FOLLOW_tokenDataType_in_createTableStatement2588);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2643); if (state.failed) return retval;

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:269:17: ( KEYWORD_FROM table2= tokenTable )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_FROM) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:270:17: KEYWORD_FROM table2= tokenTable
                    {
                    match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_createTableStatement2679); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenTable_in_createTableStatement2699);
                    table2=tokenTable();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setSourceTable(table2);
                                    }

                    }
                    break;

            }


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:275:17: ( ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) | ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==KEYWORD_IN) ) {
                alt8=1;
            }
            else if ( (LA8_0==KEYWORD_PARTITIONED) ) {
                alt8=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:275:18: ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
                    {
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:275:18: ( KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:276:17: KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier
                    {
                    match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2757); if (state.failed) return retval;

                    match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2759); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2779);
                    connection1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setConnection(connection1);
                                    }

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:282:17: ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN )
                    {
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:282:17: ( KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN )
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:283:17: KEYWORD_PARTITIONED KEYWORD_BY method= identifierText ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )? LPAREN p1= identifierText KEYWORD_IN KEYWORD_CONNECTION c1= tokenIdentifier ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )* RPAREN
                    {
                    match(input,KEYWORD_PARTITIONED,FOLLOW_KEYWORD_PARTITIONED_in_createTableStatement2853); if (state.failed) return retval;

                    match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_createTableStatement2855); if (state.failed) return retval;

                    pushFollow(FOLLOW_identifierText_in_createTableStatement2876);
                    method=identifierText();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setPartitionMethod(method);
                                    }

                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:288:17: ( LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==LPAREN) ) {
                        int LA6_1 = input.LA(2);

                        if ( (LA6_1==IDENTIFIER) ) {
                            int LA6_2 = input.LA(3);

                            if ( (LA6_2==COMMA||LA6_2==RPAREN) ) {
                                alt6=1;
                            }
                        }
                        else if ( (LA6_1==QUOTE_DOUBLE) ) {
                            int LA6_3 = input.LA(3);

                            if ( (LA6_3==IDENTIFIER) ) {
                                int LA6_6 = input.LA(4);

                                if ( (LA6_6==QUOTE_DOUBLE) ) {
                                    int LA6_7 = input.LA(5);

                                    if ( (LA6_7==COMMA||LA6_7==RPAREN) ) {
                                        alt6=1;
                                    }
                                }
                            }
                        }
                    }
                    switch (alt6) {
                        case 1 :
                            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:289:17: LPAREN pcolumn1= identifierText ( COMMA pcolumn2= identifierText )* RPAREN
                            {
                            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2930); if (state.failed) return retval;

                            pushFollow(FOLLOW_identifierText_in_createTableStatement2950);
                            pcolumn1=identifierText();

                            state._fsp--;
                            if (state.failed) return retval;

                            if ( state.backtracking==0 ) {
                                            	retval.stmt.addPartitionColumn(pcolumn1);
                                            }

                            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:294:17: ( COMMA pcolumn2= identifierText )*
                            loop5:
                            do {
                                int alt5=2;
                                int LA5_0 = input.LA(1);

                                if ( (LA5_0==COMMA) ) {
                                    alt5=1;
                                }


                                switch (alt5) {
                            	case 1 :
                            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:295:17: COMMA pcolumn2= identifierText
                            	    {
                            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3004); if (state.failed) return retval;

                            	    pushFollow(FOLLOW_identifierText_in_createTableStatement3024);
                            	    pcolumn2=identifierText();

                            	    state._fsp--;
                            	    if (state.failed) return retval;

                            	    if ( state.backtracking==0 ) {
                            	                    	retval.stmt.addPartitionColumn(pcolumn2);
                            	                    }

                            	    }
                            	    break;

                            	default :
                            	    break loop5;
                                }
                            } while (true);


                            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement3079); if (state.failed) return retval;

                            }
                            break;

                    }


                    match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement3116); if (state.failed) return retval;

                    pushFollow(FOLLOW_identifierText_in_createTableStatement3136);
                    p1=identifierText();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.addPartition(p1);
                                    }

                    match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3172); if (state.failed) return retval;

                    match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3174); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3194);
                    c1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.addConnection(c1);
                                    }

                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:312:17: ( COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==COMMA) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:313:17: COMMA p2= identifierText KEYWORD_IN KEYWORD_CONNECTION c2= tokenIdentifier
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement3232); if (state.failed) return retval;

                    	    pushFollow(FOLLOW_identifierText_in_createTableStatement3252);
                    	    p2=identifierText();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    if ( state.backtracking==0 ) {
                    	                    	retval.stmt.addPartition(p2);
                    	                    }

                    	    match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement3288); if (state.failed) return retval;

                    	    match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3290); if (state.failed) return retval;

                    	    pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement3310);
                    	    c2=tokenIdentifier();

                    	    state._fsp--;
                    	    if (state.failed) return retval;

                    	    if ( state.backtracking==0 ) {
                    	                    	retval.stmt.addConnection(c2);
                    	                    }

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement3349); if (state.failed) return retval;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:329:1: dropTableStatement returns [DropTableStmt stmt] : ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) ;
    public final FunSQLParser.dropTableStatement_return dropTableStatement() throws RecognitionException {
        FunSQLParser.dropTableStatement_return retval = new FunSQLParser.dropTableStatement_return();
        retval.start = input.LT(1);

        int dropTableStatement_StartIndex = input.index();

        TokenTable table1 =null;



                	retval.stmt = new DropTableStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:333:9: ( ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:334:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:334:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:335:17: KEYWORD_DROP KEYWORD_TABLE table1= tokenTable
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement3462); if (state.failed) return retval;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement3480); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_dropTableStatement3500);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:343:1: createFunctionStatement returns [CreateFunctionStmt stmt] : ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END ) ;
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

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:347:9: ( ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:348:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:348:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:349:17: KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )* ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )* KEYWORD_END
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createFunctionStatement3566); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement3584); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_createFunctionStatement3604);
            function1=tokenFunction();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setFunction(function1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createFunctionStatement3620); if (state.failed) return retval;

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:355:10: ( KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==KEYWORD_IN) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:356:17: KEYWORD_IN var1= tokenVariable KEYWORD_TABLE COMMA
            	    {
            	    match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createFunctionStatement3649); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement3669);
            	    var1=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                   		retval.stmt.addInParam(var1);
            	    				}

            	    match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3676); if (state.failed) return retval;

            	    match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement3682); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:363:3: ( KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:364:17: KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
            {
            match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement3720); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement3740);
            var2=tokenVariable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                           		retval.stmt.addInParam(var2);
            				}

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3747); if (state.failed) return retval;

            }


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:370:3: ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==COMMA) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:371:4: COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement3760); if (state.failed) return retval;

            	    match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement3765); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement3781);
            	    var2=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	               		retval.stmt.addOutParam(var2);
            	    			}

            	    match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3787); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createFunctionStatement3796); if (state.failed) return retval;

            match(input,KEYWORD_BEGIN,FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement3800); if (state.failed) return retval;

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:380:3: ( (ass1= tokenAssignment ) | (call1= tokenFunctionCall ) )*
            loop11:
            do {
                int alt11=3;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==COLON||LA11_0==KEYWORD_VAR) ) {
                    alt11=1;
                }
                else if ( (LA11_0==KEYWORD_CALL) ) {
                    alt11=2;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:381:3: (ass1= tokenAssignment )
            	    {
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:381:3: (ass1= tokenAssignment )
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:382:3: ass1= tokenAssignment
            	    {
            	    pushFollow(FOLLOW_tokenAssignment_in_createFunctionStatement3814);
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
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:388:17: (call1= tokenFunctionCall )
            	    {
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:388:17: (call1= tokenFunctionCall )
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:389:17: call1= tokenFunctionCall
            	    {
            	    pushFollow(FOLLOW_tokenFunctionCall_in_createFunctionStatement3893);
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
            	    break loop11;
                }
            } while (true);


            match(input,KEYWORD_END,FOLLOW_KEYWORD_END_in_createFunctionStatement3935); if (state.failed) return retval;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:398:1: dropFunctionStatement returns [DropFunctionStmt stmt] : ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) ;
    public final FunSQLParser.dropFunctionStatement_return dropFunctionStatement() throws RecognitionException {
        FunSQLParser.dropFunctionStatement_return retval = new FunSQLParser.dropFunctionStatement_return();
        retval.start = input.LT(1);

        int dropFunctionStatement_StartIndex = input.index();

        TokenFunction fun1 =null;



                	retval.stmt = new DropFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:402:9: ( ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:403:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:403:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:404:17: KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropFunctionStatement4015); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement4033); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_dropFunctionStatement4053);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:412:1: callFunctionStatement returns [CallFunctionStmt stmt] : ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) ;
    public final FunSQLParser.callFunctionStatement_return callFunctionStatement() throws RecognitionException {
        FunSQLParser.callFunctionStatement_return retval = new FunSQLParser.callFunctionStatement_return();
        retval.start = input.LT(1);

        int callFunctionStatement_StartIndex = input.index();

        TokenFunction fun1 =null;



                	retval.stmt = new CallFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:416:9: ( ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:417:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:417:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:418:17: KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction
            {
            match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_callFunctionStatement4119); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement4137); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_callFunctionStatement4157);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:426:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) ;
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

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:431:9: ( ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:432:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:432:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:434:17: KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )?
            {
            match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement4234); if (state.failed) return retval;

            pushFollow(FOLLOW_abstractExpression_in_selectStatement4254);
            selExpr1=abstractExpression();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addSelExpression(selExpr1);
                            	++i;
                            }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:440:17: ( KEYWORD_AS selAlias1= tokenIdentifier )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==KEYWORD_AS) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:441:18: KEYWORD_AS selAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4309); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4330);
                    selAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    		retval.stmt.setSelAlias(i-1, selAlias1);
                                    	}

                    }
                    break;

            }


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:447:17: ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==COMMA) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:448:17: COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement4404); if (state.failed) return retval;

            	    pushFollow(FOLLOW_abstractExpression_in_selectStatement4424);
            	    selExpr2=abstractExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addSelExpression(selExpr2);
            	                    	++i;
            	                    }

            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:454:17: ( KEYWORD_AS selAlias2= tokenIdentifier )?
            	    int alt13=2;
            	    int LA13_0 = input.LA(1);

            	    if ( (LA13_0==KEYWORD_AS) ) {
            	        alt13=1;
            	    }
            	    switch (alt13) {
            	        case 1 :
            	            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:455:18: KEYWORD_AS selAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4479); if (state.failed) return retval;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4500);
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
            	    break loop14;
                }
            } while (true);


            match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement4609); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_selectStatement4629);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addTable(table1);
                            	i=1;
                            }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:470:17: ( KEYWORD_AS tableAlias1= tokenIdentifier )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==KEYWORD_AS) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:471:18: KEYWORD_AS tableAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4685); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4706);
                    tableAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    		retval.stmt.setTableAlias(i-1, tableAlias1);
                                    	}

                    }
                    break;

            }


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:477:17: ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==COMMA) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:478:17: COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement4780); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenTable_in_selectStatement4800);
            	    table2=tokenTable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addTable(table2);
            	                    	++i;
            	                    }

            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:484:17: ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    int alt16=2;
            	    int LA16_0 = input.LA(1);

            	    if ( (LA16_0==KEYWORD_AS) ) {
            	        alt16=1;
            	    }
            	    switch (alt16) {
            	        case 1 :
            	            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:485:18: KEYWORD_AS tableAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4855); if (state.failed) return retval;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4876);
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
            	    break loop17;
                }
            } while (true);


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:493:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==KEYWORD_WHERE) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:494:17: KEYWORD_WHERE predicate1= abstractPredicate
                    {
                    match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement4986); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement5006);
                    predicate1=abstractPredicate();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setWherePredicate(predicate1);
                                    }

                    }
                    break;

            }


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:502:17: ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==KEYWORD_GROUP) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:503:17: KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )*
                    {
                    match(input,KEYWORD_GROUP,FOLLOW_KEYWORD_GROUP_in_selectStatement5113); if (state.failed) return retval;

                    match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_selectStatement5115); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractExpression_in_selectStatement5135);
                    groupExpr1=abstractExpression();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.addGroupExpression(groupExpr1);
                                    	++i;
                                    }

                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:509:17: ( COMMA groupExpr2= abstractExpression )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==COMMA) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:510:17: COMMA groupExpr2= abstractExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement5189); if (state.failed) return retval;

                    	    pushFollow(FOLLOW_abstractExpression_in_selectStatement5209);
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
                    	    break loop19;
                        }
                    } while (true);


                    }
                    break;

            }


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:520:17: ( KEYWORD_HAVING havingPred= abstractPredicate )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==KEYWORD_HAVING) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:521:17: KEYWORD_HAVING havingPred= abstractPredicate
                    {
                    match(input,KEYWORD_HAVING,FOLLOW_KEYWORD_HAVING_in_selectStatement5335); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement5355);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:531:1: abstractPredicate returns [AbstractPredicate predicate] : predicate1= complexPredicateOr ;
    public final AbstractPredicate abstractPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int abstractPredicate_StartIndex = input.index();

        ComplexPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return predicate; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:532:2: (predicate1= complexPredicateOr )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:533:3: predicate1= complexPredicateOr
            {
            pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate5417);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:538:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
    public final ComplexPredicate complexPredicateOr() throws RecognitionException {
        ComplexPredicate predicateOr = null;

        int complexPredicateOr_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return predicateOr; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:542:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:543:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:543:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:544:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
            {
            pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr5455);
            predicate1=complexPredicateAnd();

            state._fsp--;
            if (state.failed) return predicateOr;

            if ( state.backtracking==0 ) {
            			predicateOr.setPredicate1(predicate1);
            		}

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:547:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==KEYWORD_OR) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:548:4: KEYWORD_OR predicate2= complexPredicateAnd
            	    {
            	    match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr5466); if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addOr();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr5474);
            	    predicate2=complexPredicateAnd();

            	    state._fsp--;
            	    if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:558:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
    public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
        ComplexPredicate predicateAnd = null;

        int complexPredicateAnd_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return predicateAnd; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:562:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:563:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:563:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:564:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
            {
            pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd5513);
            predicate1=complexPredicateNot();

            state._fsp--;
            if (state.failed) return predicateAnd;

            if ( state.backtracking==0 ) {
            			predicateAnd.setPredicate1(predicate1);
            		}

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:567:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==KEYWORD_AND) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:568:4: KEYWORD_AND predicate2= complexPredicateNot
            	    {
            	    match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd5524); if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addAnd();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd5532);
            	    predicate2=complexPredicateNot();

            	    state._fsp--;
            	    if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:578:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
    public final ComplexPredicate complexPredicateNot() throws RecognitionException {
        ComplexPredicate predicateNot = null;

        int complexPredicateNot_StartIndex = input.index();

        AbstractPredicate predicate1 =null;



                	predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return predicateNot; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:582:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:583:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:583:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:584:3: ( KEYWORD_NOT )? predicate1= complexPredicate
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:584:3: ( KEYWORD_NOT )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==KEYWORD_NOT) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:585:3: KEYWORD_NOT
                    {
                    match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot5573); if (state.failed) return predicateNot;

                    if ( state.backtracking==0 ) {
                    			predicateNot.negate();
                    		}

                    }
                    break;

            }


            pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot5586);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:595:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
    public final AbstractPredicate complexPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int complexPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;

        SimplePredicate predicate2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return predicate; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:596:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:597:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:597:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==LPAREN) ) {
                int LA25_1 = input.LA(2);

                if ( (synpred34_FunSQL()) ) {
                    alt25=1;
                }
                else if ( (true) ) {
                    alt25=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA25_0==FUNCTION_AGGREGATION||LA25_0==IDENTIFIER||(LA25_0 >= LITERAL_DECIMAL && LA25_0 <= LITERAL_STRING)||LA25_0==MINUS||LA25_0==PLUS||LA25_0==QUOTE_DOUBLE||LA25_0==TYPE_DATE) ) {
                alt25=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return predicate;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:598:3: predicate1= parenPredicate
                    {
                    pushFollow(FOLLOW_parenPredicate_in_complexPredicate5613);
                    predicate1=parenPredicate();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate1;
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:603:3: predicate2= simplePredicate
                    {
                    pushFollow(FOLLOW_simplePredicate_in_complexPredicate5628);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:610:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
    public final AbstractPredicate parenPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int parenPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return predicate; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:611:2: ( LPAREN predicate1= abstractPredicate RPAREN )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:612:3: LPAREN predicate1= abstractPredicate RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate5653); if (state.failed) return predicate;

            pushFollow(FOLLOW_abstractPredicate_in_parenPredicate5660);
            predicate1=abstractPredicate();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
            			predicate = predicate1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate5666); if (state.failed) return predicate;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:619:1: simplePredicate returns [SimplePredicate predicate] : (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) ;
    public final SimplePredicate simplePredicate() throws RecognitionException {
        SimplePredicate predicate = null;

        int simplePredicate_StartIndex = input.index();

        AbstractExpression expr1 =null;

        FunSQLParser.tokenCompOperator_return comp =null;

        AbstractExpression expr2 =null;



                	predicate = new SimplePredicate();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return predicate; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:623:9: ( (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:624:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:624:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:625:3: expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression
            {
            pushFollow(FOLLOW_abstractExpression_in_simplePredicate5708);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setExpr1(expr1);
                            }

            pushFollow(FOLLOW_tokenCompOperator_in_simplePredicate5763);
            comp=tokenCompOperator();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setComp(EnumCompOperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
                            }

            pushFollow(FOLLOW_abstractExpression_in_simplePredicate5801);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:642:1: abstractExpression returns [AbstractExpression expression] : expression1= complexExpressionAdd ;
    public final AbstractExpression abstractExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int abstractExpression_StartIndex = input.index();

        ComplexExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return expression; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:643:2: (expression1= complexExpressionAdd )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:644:2: expression1= complexExpressionAdd
            {
            pushFollow(FOLLOW_complexExpressionAdd_in_abstractExpression5855);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:649:1: complexExpressionAdd returns [ComplexExpression expression] : (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) ;
    public final ComplexExpression complexExpressionAdd() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionAdd_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenAddOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expression; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:653:2: ( (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:654:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:654:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:655:3: expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )*
            {
            pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd5885);
            expression1=complexExpressionMult();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:658:3: (op1= tokenAddOperator expression2= complexExpressionMult )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==MINUS||LA26_0==PLUS) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:659:4: op1= tokenAddOperator expression2= complexExpressionMult
            	    {
            	    pushFollow(FOLLOW_tokenAddOperator_in_complexExpressionAdd5898);
            	    op1=tokenAddOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd5906);
            	    expression2=complexExpressionMult();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:669:1: complexExpressionMult returns [ComplexExpression expression] : (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) ;
    public final ComplexExpression complexExpressionMult() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionMult_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenMultOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expression; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:673:2: ( (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:674:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:674:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:675:3: expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )*
            {
            pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult5946);
            expression1=complexExpressionSigned();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:678:3: (op1= tokenMultOperator expression2= complexExpressionSigned )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==DIV||LA27_0==MULT) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:679:4: op1= tokenMultOperator expression2= complexExpressionSigned
            	    {
            	    pushFollow(FOLLOW_tokenMultOperator_in_complexExpressionMult5959);
            	    op1=tokenMultOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult5967);
            	    expression2=complexExpressionSigned();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:690:1: complexExpressionSigned returns [ComplexExpression expression] : ( ( MINUS | PLUS )? expression1= complexExpression ) ;
    public final ComplexExpression complexExpressionSigned() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionSigned_StartIndex = input.index();

        AbstractExpression expression1 =null;



                	expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expression; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:694:2: ( ( ( MINUS | PLUS )? expression1= complexExpression ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:695:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:695:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:696:3: ( MINUS | PLUS )? expression1= complexExpression
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:696:3: ( MINUS | PLUS )?
            int alt28=3;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==MINUS) ) {
                alt28=1;
            }
            else if ( (LA28_0==PLUS) ) {
                alt28=2;
            }
            switch (alt28) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:697:3: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_complexExpressionSigned6009); if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression.negate();
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:701:3: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_complexExpressionSigned6018); if (state.failed) return expression;

                    }
                    break;

            }


            pushFollow(FOLLOW_complexExpression_in_complexExpressionSigned6030);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:709:1: complexExpression returns [AbstractExpression expression] : (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) ;
    public final AbstractExpression complexExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int complexExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;

        AggregationExpression expression2 =null;

        SimpleExpression expression3 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expression; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:710:2: ( (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:711:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:711:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
            int alt29=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt29=1;
                }
                break;
            case FUNCTION_AGGREGATION:
                {
                alt29=2;
                }
                break;
            case IDENTIFIER:
            case LITERAL_DECIMAL:
            case LITERAL_INTEGER:
            case LITERAL_STRING:
            case QUOTE_DOUBLE:
            case TYPE_DATE:
                {
                alt29=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }

            switch (alt29) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:712:3: expression1= parenExpression
                    {
                    pushFollow(FOLLOW_parenExpression_in_complexExpression6065);
                    expression1=parenExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression1;
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:717:3: expression2= aggregationExpression
                    {
                    pushFollow(FOLLOW_aggregationExpression_in_complexExpression6080);
                    expression2=aggregationExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression2;
                    		}

                    }
                    break;
                case 3 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:722:3: expression3= simpleExpression
                    {
                    pushFollow(FOLLOW_simpleExpression_in_complexExpression6095);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:729:1: parenExpression returns [AbstractExpression expression] : LPAREN expression1= abstractExpression RPAREN ;
    public final AbstractExpression parenExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int parenExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return expression; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:730:2: ( LPAREN expression1= abstractExpression RPAREN )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:731:3: LPAREN expression1= abstractExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenExpression6120); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_parenExpression6127);
            expression1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression = expression1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenExpression6133); if (state.failed) return expression;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:739:1: aggregationExpression returns [AggregationExpression expression] : (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) ;
    public final AggregationExpression aggregationExpression() throws RecognitionException {
        AggregationExpression expression = null;

        int aggregationExpression_StartIndex = input.index();

        Token agg1=null;
        AbstractExpression expr1 =null;



                	expression = new AggregationExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return expression; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:743:9: ( (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:744:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:744:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:745:3: agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN
            {
            agg1=(Token)match(input,FUNCTION_AGGREGATION,FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression6185); if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setAggregation((agg1!=null?agg1.getText():null).toUpperCase());
            		}

            match(input,LPAREN,FOLLOW_LPAREN_in_aggregationExpression6193); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_aggregationExpression6200);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpression(expr1);
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_aggregationExpression6206); if (state.failed) return expression;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:757:1: simpleExpression returns [SimpleExpression expression] : ( (att1= tokenAttribute |lit1= tokenLiteral ) ) ;
    public final SimpleExpression simpleExpression() throws RecognitionException {
        SimpleExpression expression = null;

        int simpleExpression_StartIndex = input.index();

        TokenAttribute att1 =null;

        TokenLiteral lit1 =null;



                	expression = new SimpleExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return expression; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:761:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:762:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:762:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:763:3: (att1= tokenAttribute |lit1= tokenLiteral )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:763:3: (att1= tokenAttribute |lit1= tokenLiteral )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==IDENTIFIER||LA30_0==QUOTE_DOUBLE) ) {
                alt30=1;
            }
            else if ( ((LA30_0 >= LITERAL_DECIMAL && LA30_0 <= LITERAL_STRING)||LA30_0==TYPE_DATE) ) {
                alt30=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;

            }
            switch (alt30) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:764:3: att1= tokenAttribute
                    {
                    pushFollow(FOLLOW_tokenAttribute_in_simpleExpression6281);
                    att1=tokenAttribute();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                                    	expression.setOper(att1);
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:769:17: lit1= tokenLiteral
                    {
                    pushFollow(FOLLOW_tokenLiteral_in_simpleExpression6337);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:777:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenAttribute tokenAttribute() throws RecognitionException {
        TokenAttribute attribute = null;

        int tokenAttribute_StartIndex = input.index();

        TokenIdentifier table1 =null;

        TokenIdentifier id1 =null;



                	attribute = new TokenAttribute();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return attribute; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:781:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:782:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:782:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:783:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:783:17: (table1= tokenIdentifier DOT )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==IDENTIFIER) ) {
                int LA31_1 = input.LA(2);

                if ( (LA31_1==DOT) ) {
                    alt31=1;
                }
            }
            else if ( (LA31_0==QUOTE_DOUBLE) ) {
                int LA31_2 = input.LA(2);

                if ( (LA31_2==IDENTIFIER) ) {
                    int LA31_5 = input.LA(3);

                    if ( (LA31_5==QUOTE_DOUBLE) ) {
                        int LA31_6 = input.LA(4);

                        if ( (LA31_6==DOT) ) {
                            alt31=1;
                        }
                    }
                }
            }
            switch (alt31) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:784:17: table1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute6465);
                    table1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return attribute;

                    if ( state.backtracking==0 ) {
                                    	TokenTable table = new TokenTable();
                                    	table.setName(table1);
                                    	attribute.setTable(table);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenAttribute6485); if (state.failed) return attribute;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute6524);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:797:1: tokenTable returns [TokenTable table] : ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) );
    public final TokenTable tokenTable() throws RecognitionException {
        TokenTable table = null;

        int tokenTable_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	table = new TokenTable();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return table; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:801:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) )
            int alt34=2;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                int LA34_1 = input.LA(2);

                if ( (synpred44_FunSQL()) ) {
                    alt34=1;
                }
                else if ( (true) ) {
                    alt34=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return table;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 34, 1, input);

                    throw nvae;

                }
                }
                break;
            case QUOTE_DOUBLE:
                {
                int LA34_2 = input.LA(2);

                if ( (LA34_2==IDENTIFIER) ) {
                    int LA34_5 = input.LA(3);

                    if ( (LA34_5==QUOTE_DOUBLE) ) {
                        int LA34_6 = input.LA(4);

                        if ( (synpred44_FunSQL()) ) {
                            alt34=1;
                        }
                        else if ( (true) ) {
                            alt34=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return table;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 34, 6, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return table;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 34, 5, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return table;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 34, 2, input);

                    throw nvae;

                }
                }
                break;
            case COLON:
                {
                alt34=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return table;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;

            }

            switch (alt34) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:802:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
                    {
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:802:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:803:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
                    {
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:803:17: (schema1= tokenIdentifier DOT )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==IDENTIFIER) ) {
                        int LA32_1 = input.LA(2);

                        if ( (LA32_1==DOT) ) {
                            alt32=1;
                        }
                    }
                    else if ( (LA32_0==QUOTE_DOUBLE) ) {
                        int LA32_2 = input.LA(2);

                        if ( (LA32_2==IDENTIFIER) ) {
                            int LA32_5 = input.LA(3);

                            if ( (LA32_5==QUOTE_DOUBLE) ) {
                                int LA32_6 = input.LA(4);

                                if ( (LA32_6==DOT) ) {
                                    alt32=1;
                                }
                            }
                        }
                    }
                    switch (alt32) {
                        case 1 :
                            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:804:17: schema1= tokenIdentifier DOT
                            {
                            pushFollow(FOLLOW_tokenIdentifier_in_tokenTable6647);
                            schema1=tokenIdentifier();

                            state._fsp--;
                            if (state.failed) return table;

                            if ( state.backtracking==0 ) {
                                                    TokenSchema schema = new TokenSchema();
                                            	table.setSchema(schema);
                                            	table.setVariable(false);
                                            }

                            match(input,DOT,FOLLOW_DOT_in_tokenTable6667); if (state.failed) return table;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable6724);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:814:12: ( ( COLON )? id1= tokenIdentifier )
                    {
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:814:12: ( ( COLON )? id1= tokenIdentifier )
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:815:17: ( COLON )? id1= tokenIdentifier
                    {
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:815:17: ( COLON )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==COLON) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:816:17: COLON
                            {
                            match(input,COLON,FOLLOW_COLON_in_tokenTable6791); if (state.failed) return table;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable6833);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:825:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
    public final TokenSchema tokenSchema() throws RecognitionException {
        TokenSchema schema = null;

        int tokenSchema_StartIndex = input.index();

        TokenIdentifier tokenIdentifier15 =null;



                	schema = new TokenSchema();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return schema; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:829:9: ( ( tokenIdentifier ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:830:9: ( tokenIdentifier )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:830:9: ( tokenIdentifier )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:831:17: tokenIdentifier
            {
            pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema6914);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:837:1: tokenFunction returns [TokenFunction function] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenFunction tokenFunction() throws RecognitionException {
        TokenFunction function = null;

        int tokenFunction_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	function = new TokenFunction();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return function; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:841:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:842:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:842:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:843:33: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:843:33: (schema1= tokenIdentifier DOT )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==IDENTIFIER) ) {
                int LA35_1 = input.LA(2);

                if ( (LA35_1==DOT) ) {
                    alt35=1;
                }
            }
            else if ( (LA35_0==QUOTE_DOUBLE) ) {
                int LA35_2 = input.LA(2);

                if ( (LA35_2==IDENTIFIER) ) {
                    int LA35_5 = input.LA(3);

                    if ( (LA35_5==QUOTE_DOUBLE) ) {
                        int LA35_6 = input.LA(4);

                        if ( (LA35_6==DOT) ) {
                            alt35=1;
                        }
                    }
                }
            }
            switch (alt35) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:844:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction7030);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return function;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	function.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenFunction7050); if (state.failed) return function;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction7089);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:856:1: tokenVariable returns [TokenVariable variable] : ( variableText ) ;
    public final TokenVariable tokenVariable() throws RecognitionException {
        TokenVariable variable = null;

        int tokenVariable_StartIndex = input.index();

        String variableText16 =null;



                	variable = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return variable; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:860:9: ( ( variableText ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:861:9: ( variableText )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:861:9: ( variableText )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:862:17: variableText
            {
            pushFollow(FOLLOW_variableText_in_tokenVariable7169);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:868:1: tokenAssignment returns [TokenAssignment ass] : ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) SEMI ;
    public final TokenAssignment tokenAssignment() throws RecognitionException {
        TokenAssignment ass = null;

        int tokenAssignment_StartIndex = input.index();

        TokenVariable var1 =null;

        FunSQLParser.selectStatement_return selstmt1 =null;

        TokenVariable var2 =null;

        FunSQLParser.selectStatement_return selstmt2 =null;



        	 	ass =new TokenAssignment();
        	 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return ass; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:872:3: ( ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) SEMI )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:873:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) SEMI
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:873:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==COLON) ) {
                alt36=1;
            }
            else if ( (LA36_0==KEYWORD_VAR) ) {
                alt36=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ass;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;

            }
            switch (alt36) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:874:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    {
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:874:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:875:4: COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement
                    {
                    match(input,COLON,FOLLOW_COLON_in_tokenAssignment7236); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7248);
                    var1=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setReference(true);
                    		 ass.setVar(var1);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7254); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment7261);
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:886:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    {
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:886:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:887:4: KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement
                    {
                    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment7282); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment7291);
                    var2=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {		 
                    		 ass.setReference(false);
                    		 ass.setVar(var2);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment7297); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment7304);
                    selstmt2=selectStatement();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setSelStmt((selstmt2!=null?selstmt2.stmt:null));
                    		 }

                    }


                    }
                    break;

            }


            match(input,SEMI,FOLLOW_SEMI_in_tokenAssignment7324); if (state.failed) return ass;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:901:1: tokenFunctionCall returns [TokenFunctionCall call] : ( KEYWORD_CALL fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable )* ( COMMA KEYWORD_VAR var3= tokenVariable )? RPAREN ) ;
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

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:905:3: ( ( KEYWORD_CALL fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable )* ( COMMA KEYWORD_VAR var3= tokenVariable )? RPAREN ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:906:3: ( KEYWORD_CALL fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable )* ( COMMA KEYWORD_VAR var3= tokenVariable )? RPAREN )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:906:3: ( KEYWORD_CALL fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable )* ( COMMA KEYWORD_VAR var3= tokenVariable )? RPAREN )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:907:4: KEYWORD_CALL fun1= tokenFunction LPAREN ( COLON var1= tokenVariable )* ( KEYWORD_VAR var2= tokenVariable )* ( COMMA KEYWORD_VAR var3= tokenVariable )? RPAREN
            {
            match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_tokenFunctionCall7356); if (state.failed) return call;

            pushFollow(FOLLOW_tokenFunction_in_tokenFunctionCall7363);
            fun1=tokenFunction();

            state._fsp--;
            if (state.failed) return call;

            if ( state.backtracking==0 ) {
            		 call.setFun(fun1);
            		 }

            match(input,LPAREN,FOLLOW_LPAREN_in_tokenFunctionCall7369); if (state.failed) return call;

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:912:4: ( COLON var1= tokenVariable )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==COLON) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:913:5: COLON var1= tokenVariable
            	    {
            	    match(input,COLON,FOLLOW_COLON_in_tokenFunctionCall7380); if (state.failed) return call;

            	    pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall7393);
            	    var1=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return call;

            	    if ( state.backtracking==0 ) {
            	    			 call.addInVar(var1);
            	    			 }

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:918:4: ( KEYWORD_VAR var2= tokenVariable )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==KEYWORD_VAR) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:919:5: KEYWORD_VAR var2= tokenVariable
            	    {
            	    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7414); if (state.failed) return call;

            	    pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall7424);
            	    var2=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return call;

            	    if ( state.backtracking==0 ) {		
            	    			 call.addOutVar(var2);
            	    			 }

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:924:4: ( COMMA KEYWORD_VAR var3= tokenVariable )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==COMMA) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:925:6: COMMA KEYWORD_VAR var3= tokenVariable
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_tokenFunctionCall7443); if (state.failed) return call;

                    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7449); if (state.failed) return call;

                    pushFollow(FOLLOW_tokenVariable_in_tokenFunctionCall7459);
                    var3=tokenVariable();

                    state._fsp--;
                    if (state.failed) return call;

                    if ( state.backtracking==0 ) {		
                    			 call.addOutVar(var3);
                    			 }

                    }
                    break;

            }


            match(input,RPAREN,FOLLOW_RPAREN_in_tokenFunctionCall7471); if (state.failed) return call;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:935:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
    public final TokenIdentifier tokenIdentifier() throws RecognitionException {
        TokenIdentifier identifier = null;

        int tokenIdentifier_StartIndex = input.index();

        String identifierText17 =null;



                	identifier = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return identifier; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:939:9: ( ( identifierText ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:940:9: ( identifierText )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:940:9: ( identifierText )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:941:17: identifierText
            {
            pushFollow(FOLLOW_identifierText_in_tokenIdentifier7538);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:948:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) ;
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

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:952:9: ( ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:953:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:953:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
            int alt40=4;
            switch ( input.LA(1) ) {
            case TYPE_VARCHAR:
                {
                alt40=1;
                }
                break;
            case TYPE_INTEGER:
                {
                alt40=2;
                }
                break;
            case TYPE_DECIMAL:
                {
                alt40=3;
                }
                break;
            case TYPE_DATE:
                {
                alt40=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return dataType;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }

            switch (alt40) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:954:17: TYPE_VARCHAR
                    {
                    TYPE_VARCHAR18=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType7619); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_VARCHAR18!=null?TYPE_VARCHAR18.getText():null));
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:958:17: TYPE_INTEGER
                    {
                    TYPE_INTEGER19=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType7657); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_INTEGER19!=null?TYPE_INTEGER19.getText():null));
                                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:962:17: TYPE_DECIMAL
                    {
                    TYPE_DECIMAL20=(Token)match(input,TYPE_DECIMAL,FOLLOW_TYPE_DECIMAL_in_tokenDataType7695); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_DECIMAL20!=null?TYPE_DECIMAL20.getText():null));
                                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:966:17: TYPE_DATE
                    {
                    TYPE_DATE21=(Token)match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDataType7733); if (state.failed) return dataType;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:972:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
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

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:976:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:977:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:977:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:978:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:978:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            int alt41=4;
            switch ( input.LA(1) ) {
            case LITERAL_INTEGER:
                {
                alt41=1;
                }
                break;
            case LITERAL_STRING:
                {
                alt41=2;
                }
                break;
            case LITERAL_DECIMAL:
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
                if (state.backtracking>0) {state.failed=true; return literal;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }

            switch (alt41) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:979:17: tokenIntegerLiteral
                    {
                    pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral7824);
                    tokenIntegerLiteral22=tokenIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenIntegerLiteral22;
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:983:17: tokenStringLiteral
                    {
                    pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral7862);
                    tokenStringLiteral23=tokenStringLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenStringLiteral23;
                                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:987:17: tokenDecimalLiteral
                    {
                    pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral7900);
                    tokenDecimalLiteral24=tokenDecimalLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenDecimalLiteral24;
                                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:991:17: tokenDateLiteral
                    {
                    pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral7938);
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:998:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
    public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
        TokenStringLiteral literal = null;

        int tokenStringLiteral_StartIndex = input.index();

        Token lit1=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return literal; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1002:9: ( (lit1= LITERAL_STRING ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1003:9: (lit1= LITERAL_STRING )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1003:9: (lit1= LITERAL_STRING )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1004:17: lit1= LITERAL_STRING
            {
            lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral8038); if (state.failed) return literal;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1010:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
    public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
        TokenIntegerLiteral literal = null;

        int tokenIntegerLiteral_StartIndex = input.index();

        Token LITERAL_INTEGER26=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return literal; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1014:9: ( ( LITERAL_INTEGER ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1015:9: ( LITERAL_INTEGER )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1015:9: ( LITERAL_INTEGER )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1016:17: LITERAL_INTEGER
            {
            LITERAL_INTEGER26=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral8118); if (state.failed) return literal;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1023:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
    public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
        TokenDecimalLiteral literal = null;

        int tokenDecimalLiteral_StartIndex = input.index();

        Token LITERAL_DECIMAL27=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return literal; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1027:9: ( ( LITERAL_DECIMAL ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1028:9: ( LITERAL_DECIMAL )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1028:9: ( LITERAL_DECIMAL )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1029:17: LITERAL_DECIMAL
            {
            LITERAL_DECIMAL27=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral8208); if (state.failed) return literal;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1035:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( TYPE_DATE LITERAL_STRING ) ;
    public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
        TokenDateLiteral literal = null;

        int tokenDateLiteral_StartIndex = input.index();

        Token LITERAL_STRING28=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return literal; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1039:9: ( ( TYPE_DATE LITERAL_STRING ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1040:9: ( TYPE_DATE LITERAL_STRING )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1040:9: ( TYPE_DATE LITERAL_STRING )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1042:17: TYPE_DATE LITERAL_STRING
            {
            match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDateLiteral8306); if (state.failed) return literal;

            LITERAL_STRING28=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenDateLiteral8324); if (state.failed) return literal;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1049:2: variableText returns [String text] : (var1= IDENTIFIER ) ;
    public final String variableText() throws RecognitionException {
        String text = null;

        int variableText_StartIndex = input.index();

        Token var1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return text; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1050:3: ( (var1= IDENTIFIER ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1051:3: (var1= IDENTIFIER )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1051:3: (var1= IDENTIFIER )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1053:4: var1= IDENTIFIER
            {
            var1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variableText8383); if (state.failed) return text;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1059:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) ;
    public final String identifierText() throws RecognitionException {
        String text = null;

        int identifierText_StartIndex = input.index();

        Token id1=null;
        Token id2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return text; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1060:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1061:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1061:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==IDENTIFIER) ) {
                alt42=1;
            }
            else if ( (LA42_0==QUOTE_DOUBLE) ) {
                alt42=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return text;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }
            switch (alt42) {
                case 1 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1062:4: (id1= IDENTIFIER )
                    {
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1062:4: (id1= IDENTIFIER )
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1063:4: id1= IDENTIFIER
                    {
                    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText8435); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id1!=null?id1.getText():null).toUpperCase();
                                    }

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1068:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    {
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1068:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1069:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText8491); if (state.failed) return text;

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText8507); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id2!=null?id2.getText():null);
                                    }

                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText8527); if (state.failed) return text;

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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1078:1: tokenAddOperator : ( PLUS | MINUS ) ;
    public final FunSQLParser.tokenAddOperator_return tokenAddOperator() throws RecognitionException {
        FunSQLParser.tokenAddOperator_return retval = new FunSQLParser.tokenAddOperator_return();
        retval.start = input.LT(1);

        int tokenAddOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1079:5: ( ( PLUS | MINUS ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1086:1: tokenMultOperator : ( MULT | DIV ) ;
    public final FunSQLParser.tokenMultOperator_return tokenMultOperator() throws RecognitionException {
        FunSQLParser.tokenMultOperator_return retval = new FunSQLParser.tokenMultOperator_return();
        retval.start = input.LT(1);

        int tokenMultOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1087:5: ( ( MULT | DIV ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1094:1: tokenCompOperator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN ) ;
    public final FunSQLParser.tokenCompOperator_return tokenCompOperator() throws RecognitionException {
        FunSQLParser.tokenCompOperator_return retval = new FunSQLParser.tokenCompOperator_return();
        retval.start = input.LT(1);

        int tokenCompOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }

            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1095:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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

    // $ANTLR start synpred34_FunSQL
    public final void synpred34_FunSQL_fragment() throws RecognitionException {
        AbstractPredicate predicate1 =null;


        // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:598:3: (predicate1= parenPredicate )
        // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:598:3: predicate1= parenPredicate
        {
        pushFollow(FOLLOW_parenPredicate_in_synpred34_FunSQL5613);
        predicate1=parenPredicate();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred34_FunSQL

    // $ANTLR start synpred44_FunSQL
    public final void synpred44_FunSQL_fragment() throws RecognitionException {
        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;


        // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:802:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
        // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:802:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
        {
        // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:802:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
        // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:803:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
        {
        // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:803:17: (schema1= tokenIdentifier DOT )?
        int alt47=2;
        int LA47_0 = input.LA(1);

        if ( (LA47_0==IDENTIFIER) ) {
            int LA47_1 = input.LA(2);

            if ( (LA47_1==DOT) ) {
                alt47=1;
            }
        }
        else if ( (LA47_0==QUOTE_DOUBLE) ) {
            int LA47_2 = input.LA(2);

            if ( (LA47_2==IDENTIFIER) ) {
                int LA47_5 = input.LA(3);

                if ( (LA47_5==QUOTE_DOUBLE) ) {
                    int LA47_6 = input.LA(4);

                    if ( (LA47_6==DOT) ) {
                        alt47=1;
                    }
                }
            }
        }
        switch (alt47) {
            case 1 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:804:17: schema1= tokenIdentifier DOT
                {
                pushFollow(FOLLOW_tokenIdentifier_in_synpred44_FunSQL6647);
                schema1=tokenIdentifier();

                state._fsp--;
                if (state.failed) return ;

                match(input,DOT,FOLLOW_DOT_in_synpred44_FunSQL6667); if (state.failed) return ;

                }
                break;

        }


        pushFollow(FOLLOW_tokenIdentifier_in_synpred44_FunSQL6724);
        id1=tokenIdentifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }

    }
    // $ANTLR end synpred44_FunSQL

    // Delegated rules

    public final boolean synpred34_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred44_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred44_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_createSchemaStatement_in_statement1105 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_dropSchemaStatement_in_statement1160 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_createConnectionStatement_in_statement1215 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_dropConnectionStatement_in_statement1270 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_createTableStatement_in_statement1325 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_dropTableStatement_in_statement1380 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_createFunctionStatement_in_statement1435 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_dropFunctionStatement_in_statement1490 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_callFunctionStatement_in_statement1545 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_selectStatement_in_statement1600 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});
    public static final BitSet FOLLOW_SEMI_in_statement1654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1732 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1750 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1847 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1865 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1962 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1980 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement1998 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement2018 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2038 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement2058 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2078 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement2098 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2118 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement2138 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2245 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2263 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2360 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2378 = new BitSet(new long[]{0x0000000200001000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2398 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement2418 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2438 = new BitSet(new long[]{0x0000000000000000L,0x00001E0000000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2476 = new BitSet(new long[]{0x0000000000002000L,0x0000000400000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2530 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2550 = new BitSet(new long[]{0x0000000000000000L,0x00001E0000000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2588 = new BitSet(new long[]{0x0000000000002000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement2643 = new BitSet(new long[]{0x0822000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2679 = new BitSet(new long[]{0x0000000200001000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2699 = new BitSet(new long[]{0x0820000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2757 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2759 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_PARTITIONED_in_createTableStatement2853 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_BY_in_createTableStatement2855 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2876 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement2930 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2950 = new BitSet(new long[]{0x0000000000002000L,0x0000000400000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement3004 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3024 = new BitSet(new long[]{0x0000000000002000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement3079 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement3116 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3136 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3172 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3174 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3194 = new BitSet(new long[]{0x0000000000002000L,0x0000000400000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement3232 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement3252 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement3288 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement3290 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement3310 = new BitSet(new long[]{0x0000000000002000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement3349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement3462 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement3480 = new BitSet(new long[]{0x0000000200001000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenTable_in_dropTableStatement3500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createFunctionStatement3566 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement3584 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenFunction_in_createFunctionStatement3604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_createFunctionStatement3620 = new BitSet(new long[]{0x0420000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createFunctionStatement3649 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement3669 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3676 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_COMMA_in_createFunctionStatement3682 = new BitSet(new long[]{0x0420000000000000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement3720 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement3740 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3747 = new BitSet(new long[]{0x0000000000002000L,0x0000000400000000L});
    public static final BitSet FOLLOW_COMMA_in_createFunctionStatement3760 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement3765 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement3781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3787 = new BitSet(new long[]{0x0000000000002000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RPAREN_in_createFunctionStatement3796 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement3800 = new BitSet(new long[]{0x0001040000001000L,0x0000000000000010L});
    public static final BitSet FOLLOW_tokenAssignment_in_createFunctionStatement3814 = new BitSet(new long[]{0x0001040000001000L,0x0000000000000010L});
    public static final BitSet FOLLOW_tokenFunctionCall_in_createFunctionStatement3893 = new BitSet(new long[]{0x0001040000001000L,0x0000000000000010L});
    public static final BitSet FOLLOW_KEYWORD_END_in_createFunctionStatement3935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropFunctionStatement4015 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement4033 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenFunction_in_dropFunctionStatement4053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CALL_in_callFunctionStatement4119 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement4137 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenFunction_in_callFunctionStatement4157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement4234 = new BitSet(new long[]{0x0000000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement4254 = new BitSet(new long[]{0x0002004000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4309 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4330 = new BitSet(new long[]{0x0002000000002000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement4404 = new BitSet(new long[]{0x0000000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement4424 = new BitSet(new long[]{0x0002004000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4479 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4500 = new BitSet(new long[]{0x0002000000002000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement4609 = new BitSet(new long[]{0x0000000200001000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement4629 = new BitSet(new long[]{0x0018004000002002L,0x0000000000000020L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4685 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4706 = new BitSet(new long[]{0x0018000000002002L,0x0000000000000020L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement4780 = new BitSet(new long[]{0x0000000200001000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement4800 = new BitSet(new long[]{0x0018004000002002L,0x0000000000000020L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4855 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4876 = new BitSet(new long[]{0x0018000000002002L,0x0000000000000020L});
    public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement4986 = new BitSet(new long[]{0x0100000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement5006 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_KEYWORD_GROUP_in_selectStatement5113 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_BY_in_selectStatement5115 = new BitSet(new long[]{0x0000000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement5135 = new BitSet(new long[]{0x0010000000002002L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement5189 = new BitSet(new long[]{0x0000000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement5209 = new BitSet(new long[]{0x0010000000002002L});
    public static final BitSet FOLLOW_KEYWORD_HAVING_in_selectStatement5335 = new BitSet(new long[]{0x0100000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement5355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate5417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr5455 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr5466 = new BitSet(new long[]{0x0100000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr5474 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd5513 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd5524 = new BitSet(new long[]{0x0100000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd5532 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot5573 = new BitSet(new long[]{0x0000000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot5586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_complexPredicate5613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicate_in_complexPredicate5628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenPredicate5653 = new BitSet(new long[]{0x0100000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate5660 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenPredicate5666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate5708 = new BitSet(new long[]{0x0000000038400000L,0x0000000000300700L});
    public static final BitSet FOLLOW_tokenCompOperator_in_simplePredicate5763 = new BitSet(new long[]{0x0000000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate5801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionAdd_in_abstractExpression5855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd5885 = new BitSet(new long[]{0x0000000000000002L,0x0000000002010000L});
    public static final BitSet FOLLOW_tokenAddOperator_in_complexExpressionAdd5898 = new BitSet(new long[]{0x0000000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd5906 = new BitSet(new long[]{0x0000000000000002L,0x0000000002010000L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult5946 = new BitSet(new long[]{0x0000000000020002L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenMultOperator_in_complexExpressionMult5959 = new BitSet(new long[]{0x0000000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult5967 = new BitSet(new long[]{0x0000000000020002L,0x0000000000040000L});
    public static final BitSet FOLLOW_MINUS_in_complexExpressionSigned6009 = new BitSet(new long[]{0x0000000202000000L,0x0000020020007800L});
    public static final BitSet FOLLOW_PLUS_in_complexExpressionSigned6018 = new BitSet(new long[]{0x0000000202000000L,0x0000020020007800L});
    public static final BitSet FOLLOW_complexExpression_in_complexExpressionSigned6030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenExpression_in_complexExpression6065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggregationExpression_in_complexExpression6080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleExpression_in_complexExpression6095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenExpression6120 = new BitSet(new long[]{0x0000000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_abstractExpression_in_parenExpression6127 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenExpression6133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression6185 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_aggregationExpression6193 = new BitSet(new long[]{0x0000000202000000L,0x0000020022017800L});
    public static final BitSet FOLLOW_abstractExpression_in_aggregationExpression6200 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RPAREN_in_aggregationExpression6206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simpleExpression6281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenLiteral_in_simpleExpression6337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute6465 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenAttribute6485 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute6524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable6647 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenTable6667 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable6724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenTable6791 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable6833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema6914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction7030 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenFunction7050 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction7089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableText_in_tokenVariable7169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenAssignment7236 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7248 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7254 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment7261 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment7282 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment7291 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment7297 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment7304 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_SEMI_in_tokenAssignment7324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CALL_in_tokenFunctionCall7356 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenFunction_in_tokenFunctionCall7363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_LPAREN_in_tokenFunctionCall7369 = new BitSet(new long[]{0x0000000000003000L,0x0000000400000010L});
    public static final BitSet FOLLOW_COLON_in_tokenFunctionCall7380 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall7393 = new BitSet(new long[]{0x0000000000003000L,0x0000000400000010L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7414 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall7424 = new BitSet(new long[]{0x0000000000002000L,0x0000000400000010L});
    public static final BitSet FOLLOW_COMMA_in_tokenFunctionCall7443 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenFunctionCall7449 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenFunctionCall7459 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RPAREN_in_tokenFunctionCall7471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierText_in_tokenIdentifier7538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType7619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType7657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DECIMAL_in_tokenDataType7695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DATE_in_tokenDataType7733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral7824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral7862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral7900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral7938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral8038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral8118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral8208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DATE_in_tokenDateLiteral8306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenDateLiteral8324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variableText8383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText8435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText8491 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText8507 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText8527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_synpred34_FunSQL5613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_synpred44_FunSQL6647 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_synpred44_FunSQL6667 = new BitSet(new long[]{0x0000000200000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_synpred44_FunSQL6724 = new BitSet(new long[]{0x0000000000000002L});

}