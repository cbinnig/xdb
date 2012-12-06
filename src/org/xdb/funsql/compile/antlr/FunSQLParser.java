// $ANTLR 3.4 /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g 2012-12-06 22:53:52
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", "AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", "D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", "F", "FUNCTION_AGGREGATION", "G", "GREATER_EQUAL1", "GREATER_EQUAL2", "GREATER_THAN", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", "KEYWORD_AND", "KEYWORD_AS", "KEYWORD_AVG", "KEYWORD_BEGIN", "KEYWORD_BY", "KEYWORD_CALL", "KEYWORD_CONNECTION", "KEYWORD_COUNT", "KEYWORD_CREATE", "KEYWORD_DISTINCT", "KEYWORD_DROP", "KEYWORD_END", "KEYWORD_FROM", "KEYWORD_FUNCTION", "KEYWORD_GROUP", "KEYWORD_HAVING", "KEYWORD_IN", "KEYWORD_MAX", "KEYWORD_MIN", "KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_OUT", "KEYWORD_PASSWD", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", "KEYWORD_SUM", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_VAR", "KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", "LESS_THAN", "LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", "MINUS", "MOD", "MULT", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", "QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", "SHIFT_RIGHT", "T", "TILDE", "TYPE_DATE", "TYPE_DECIMAL", "TYPE_INTEGER", "TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
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
    public static final int KEYWORD_PASSWD=59;
    public static final int KEYWORD_SCHEMA=60;
    public static final int KEYWORD_SELECT=61;
    public static final int KEYWORD_STORE=62;
    public static final int KEYWORD_SUM=63;
    public static final int KEYWORD_TABLE=64;
    public static final int KEYWORD_URL=65;
    public static final int KEYWORD_USER=66;
    public static final int KEYWORD_VAR=67;
    public static final int KEYWORD_WHERE=68;
    public static final int L=69;
    public static final int LBRACKET=70;
    public static final int LESS_EQUAL1=71;
    public static final int LESS_EQUAL2=72;
    public static final int LESS_THAN=73;
    public static final int LITERAL_DECIMAL=74;
    public static final int LITERAL_INTEGER=75;
    public static final int LITERAL_STRING=76;
    public static final int LPAREN=77;
    public static final int M=78;
    public static final int MINUS=79;
    public static final int MOD=80;
    public static final int MULT=81;
    public static final int N=82;
    public static final int NOT_EQUAL1=83;
    public static final int NOT_EQUAL2=84;
    public static final int O=85;
    public static final int P=86;
    public static final int PIPE=87;
    public static final int PLUS=88;
    public static final int Q=89;
    public static final int QUESTION=90;
    public static final int QUOTED_STRING=91;
    public static final int QUOTE_DOUBLE=92;
    public static final int QUOTE_SINGLE=93;
    public static final int QUOTE_TRIPLE=94;
    public static final int R=95;
    public static final int RBRACKET=96;
    public static final int RPAREN=97;
    public static final int S=98;
    public static final int SEMI=99;
    public static final int SHIFT_LEFT=100;
    public static final int SHIFT_RIGHT=101;
    public static final int T=102;
    public static final int TILDE=103;
    public static final int TYPE_DATE=104;
    public static final int TYPE_DECIMAL=105;
    public static final int TYPE_INTEGER=106;
    public static final int TYPE_VARCHAR=107;
    public static final int U=108;
    public static final int UNDERSCORE=109;
    public static final int V=110;
    public static final int W=111;
    public static final int WS=112;
    public static final int X=113;
    public static final int Y=114;
    public static final int Z=115;

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
        this.state.ruleMemo = new HashMap[101+1];
         

    }

    public String[] getTokenNames() { return FunSQLParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g"; }



    // $ANTLR start "statement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:93:1: statement returns [AbstractServerStmt stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? ) ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:97:9: ( ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:98:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:98:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )? )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:99:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement ) ( SEMI )?
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:99:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | dropFunctionStatement | callFunctionStatement | selectStatement )
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:100:17: createSchemaStatement
                    {
                    pushFollow(FOLLOW_createSchemaStatement_in_statement1090);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:106:17: dropSchemaStatement
                    {
                    pushFollow(FOLLOW_dropSchemaStatement_in_statement1145);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:112:17: createConnectionStatement
                    {
                    pushFollow(FOLLOW_createConnectionStatement_in_statement1200);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:118:17: dropConnectionStatement
                    {
                    pushFollow(FOLLOW_dropConnectionStatement_in_statement1255);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:124:17: createTableStatement
                    {
                    pushFollow(FOLLOW_createTableStatement_in_statement1310);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:130:17: dropTableStatement
                    {
                    pushFollow(FOLLOW_dropTableStatement_in_statement1365);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:136:17: createFunctionStatement
                    {
                    pushFollow(FOLLOW_createFunctionStatement_in_statement1420);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:142:17: dropFunctionStatement
                    {
                    pushFollow(FOLLOW_dropFunctionStatement_in_statement1475);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:148:17: callFunctionStatement
                    {
                    pushFollow(FOLLOW_callFunctionStatement_in_statement1530);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:154:17: selectStatement
                    {
                    pushFollow(FOLLOW_selectStatement_in_statement1585);
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


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:160:17: ( SEMI )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==SEMI) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:160:17: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_statement1639); if (state.failed) return stmt;

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:164:1: createSchemaStatement returns [CreateSchemaStmt stmt] : ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) ;
    public final FunSQLParser.createSchemaStatement_return createSchemaStatement() throws RecognitionException {
        FunSQLParser.createSchemaStatement_return retval = new FunSQLParser.createSchemaStatement_return();
        retval.start = input.LT(1);

        int createSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema11 =null;



                	retval.stmt = new CreateSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:168:9: ( ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:169:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:169:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:170:17: KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1717); if (state.failed) return retval;

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1735); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenSchema_in_createSchemaStatement1753);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:178:1: dropSchemaStatement returns [DropSchemaStmt stmt] : ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) ;
    public final FunSQLParser.dropSchemaStatement_return dropSchemaStatement() throws RecognitionException {
        FunSQLParser.dropSchemaStatement_return retval = new FunSQLParser.dropSchemaStatement_return();
        retval.start = input.LT(1);

        int dropSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema12 =null;



                	retval.stmt = new DropSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:182:9: ( ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:183:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:183:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:184:17: KEYWORD_DROP KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1832); if (state.failed) return retval;

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1850); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenSchema_in_dropSchemaStatement1868);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:192:1: createConnectionStatement returns [CreateConnectionStmt stmt] : ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:196:9: ( ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:197:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:197:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:198:17: KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1947); if (state.failed) return retval;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1965); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenIdentifier_in_createConnectionStatement1983);
            tokenIdentifier13=tokenIdentifier();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setConnection(tokenIdentifier13);
                            }

            match(input,KEYWORD_URL,FOLLOW_KEYWORD_URL_in_createConnectionStatement2003); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2023);
            litURL=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setURL(litURL);
                            }

            match(input,KEYWORD_USER,FOLLOW_KEYWORD_USER_in_createConnectionStatement2043); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2063);
            litUser=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setUser(litUser);
                            }

            match(input,KEYWORD_PASSWD,FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement2083); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2103);
            litPasswd=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setPasswd(litPasswd);
                            }

            match(input,KEYWORD_STORE,FOLLOW_KEYWORD_STORE_in_createConnectionStatement2123); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2143);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:222:1: dropConnectionStatement returns [DropConnectionStmt stmt] : ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) ;
    public final FunSQLParser.dropConnectionStatement_return dropConnectionStatement() throws RecognitionException {
        FunSQLParser.dropConnectionStatement_return retval = new FunSQLParser.dropConnectionStatement_return();
        retval.start = input.LT(1);

        int dropConnectionStatement_StartIndex = input.index();

        TokenIdentifier tokenIdentifier14 =null;



                	retval.stmt = new DropConnectionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:226:9: ( ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:227:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:227:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:228:17: KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2230); if (state.failed) return retval;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2248); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenIdentifier_in_dropConnectionStatement2266);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:236:1: createTableStatement returns [CreateTableStmt stmt] : ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) ;
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



                	retval.stmt = new CreateTableStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:240:9: ( ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:241:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:241:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:242:17: KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2345); if (state.failed) return retval;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2363); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_createTableStatement2383);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setTable(table1);
                            	retval.stmt.setSourceTable(table1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2403); if (state.failed) return retval;

            pushFollow(FOLLOW_identifierText_in_createTableStatement2423);
            att1=identifierText();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addAttribute(att1);
                            }

            pushFollow(FOLLOW_tokenDataType_in_createTableStatement2461);
            dataType1=tokenDataType();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addType(dataType1);
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:257:17: ( COMMA att2= identifierText dataType2= tokenDataType )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:258:17: COMMA att2= identifierText dataType2= tokenDataType
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2515); if (state.failed) return retval;

            	    pushFollow(FOLLOW_identifierText_in_createTableStatement2535);
            	    att2=identifierText();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addAttribute(att2);
            	                    }

            	    pushFollow(FOLLOW_tokenDataType_in_createTableStatement2573);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2628); if (state.failed) return retval;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:269:17: ( KEYWORD_FROM table2= tokenTable )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_FROM) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:270:17: KEYWORD_FROM table2= tokenTable
                    {
                    match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_createTableStatement2664); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenTable_in_createTableStatement2684);
                    table2=tokenTable();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setSourceTable(table2);
                                    }

                    }
                    break;

            }


            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2723); if (state.failed) return retval;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2725); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2745);
            connection1=tokenIdentifier();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setConnection(connection1);
                            }

            }


            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:282:1: dropTableStatement returns [DropTableStmt stmt] : ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) ;
    public final FunSQLParser.dropTableStatement_return dropTableStatement() throws RecognitionException {
        FunSQLParser.dropTableStatement_return retval = new FunSQLParser.dropTableStatement_return();
        retval.start = input.LT(1);

        int dropTableStatement_StartIndex = input.index();

        TokenTable table1 =null;



                	retval.stmt = new DropTableStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:286:9: ( ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:287:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:287:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:288:17: KEYWORD_DROP KEYWORD_TABLE table1= tokenTable
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement2824); if (state.failed) return retval;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement2842); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_dropTableStatement2862);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:296:1: createFunctionStatement returns [CreateFunctionStmt stmt] : ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END ) ;
    public final FunSQLParser.createFunctionStatement_return createFunctionStatement() throws RecognitionException {
        FunSQLParser.createFunctionStatement_return retval = new FunSQLParser.createFunctionStatement_return();
        retval.start = input.LT(1);

        int createFunctionStatement_StartIndex = input.index();

        TokenFunction function1 =null;

        TokenVariable var1 =null;

        TokenVariable var2 =null;

        TokenAssignment ass1 =null;



                	retval.stmt = new CreateFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:300:9: ( ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:301:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:301:9: ( KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:302:17: KEYWORD_CREATE KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE ) ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createFunctionStatement2928); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement2946); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_createFunctionStatement2966);
            function1=tokenFunction();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.setFunction(function1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createFunctionStatement2985); if (state.failed) return retval;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:308:17: ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:309:17: KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE
            {
            match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement3021); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement3041);
            var1=tokenVariable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                           		retval.stmt.addOutParam(var1);
            		}

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3046); if (state.failed) return retval;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:315:3: ( COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==COMMA) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:316:3: COMMA KEYWORD_OUT var2= tokenVariable KEYWORD_TABLE
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createFunctionStatement3058); if (state.failed) return retval;

            	    match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement3062); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement3082);
            	    var2=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                   		retval.stmt.addOutParam(var2);
            	    		}

            	    match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3087); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createFunctionStatement3096); if (state.failed) return retval;

            match(input,KEYWORD_BEGIN,FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement3100); if (state.failed) return retval;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:325:3: (ass1= tokenAssignment )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COLON||LA6_0==KEYWORD_VAR) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:326:3: ass1= tokenAssignment
            	    {
            	    pushFollow(FOLLOW_tokenAssignment_in_createFunctionStatement3110);
            	    ass1=tokenAssignment();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addAssignment(ass1.getVar(), ass1.getSelStmt());
            	                    	retval.stmt.addAssignment(ass1);
            	                    }

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            match(input,KEYWORD_END,FOLLOW_KEYWORD_END_in_createFunctionStatement3136); if (state.failed) return retval;

            }


            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:335:1: dropFunctionStatement returns [DropFunctionStmt stmt] : ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) ;
    public final FunSQLParser.dropFunctionStatement_return dropFunctionStatement() throws RecognitionException {
        FunSQLParser.dropFunctionStatement_return retval = new FunSQLParser.dropFunctionStatement_return();
        retval.start = input.LT(1);

        int dropFunctionStatement_StartIndex = input.index();

        TokenFunction fun1 =null;



                	retval.stmt = new DropFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:339:9: ( ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:340:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:340:9: ( KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:341:17: KEYWORD_DROP KEYWORD_FUNCTION fun1= tokenFunction
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropFunctionStatement3216); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement3234); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_dropFunctionStatement3254);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:349:1: callFunctionStatement returns [CallFunctionStmt stmt] : ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) ;
    public final FunSQLParser.callFunctionStatement_return callFunctionStatement() throws RecognitionException {
        FunSQLParser.callFunctionStatement_return retval = new FunSQLParser.callFunctionStatement_return();
        retval.start = input.LT(1);

        int callFunctionStatement_StartIndex = input.index();

        TokenFunction fun1 =null;



                	retval.stmt = new CallFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:353:9: ( ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:354:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:354:9: ( KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:355:17: KEYWORD_CALL KEYWORD_FUNCTION fun1= tokenFunction
            {
            match(input,KEYWORD_CALL,FOLLOW_KEYWORD_CALL_in_callFunctionStatement3320); if (state.failed) return retval;

            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement3338); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenFunction_in_callFunctionStatement3358);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:363:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:368:9: ( ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:369:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:369:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:371:17: KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )?
            {
            match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement3435); if (state.failed) return retval;

            pushFollow(FOLLOW_abstractExpression_in_selectStatement3455);
            selExpr1=abstractExpression();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addSelExpression(selExpr1);
                            	++i;
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:377:17: ( KEYWORD_AS selAlias1= tokenIdentifier )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==KEYWORD_AS) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:378:18: KEYWORD_AS selAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3510); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3531);
                    selAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    		retval.stmt.setSelAlias(i-1, selAlias1);
                                    	}

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:384:17: ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==COMMA) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:385:17: COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement3605); if (state.failed) return retval;

            	    pushFollow(FOLLOW_abstractExpression_in_selectStatement3625);
            	    selExpr2=abstractExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addSelExpression(selExpr2);
            	                    	++i;
            	                    }

            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:391:17: ( KEYWORD_AS selAlias2= tokenIdentifier )?
            	    int alt8=2;
            	    int LA8_0 = input.LA(1);

            	    if ( (LA8_0==KEYWORD_AS) ) {
            	        alt8=1;
            	    }
            	    switch (alt8) {
            	        case 1 :
            	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:392:18: KEYWORD_AS selAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3680); if (state.failed) return retval;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3701);
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
            	    break loop9;
                }
            } while (true);


            match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement3810); if (state.failed) return retval;

            pushFollow(FOLLOW_tokenTable_in_selectStatement3830);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return retval;

            if ( state.backtracking==0 ) {
                            	retval.stmt.addTable(table1);
                            	i=1;
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:407:17: ( KEYWORD_AS tableAlias1= tokenIdentifier )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==KEYWORD_AS) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:408:18: KEYWORD_AS tableAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3886); if (state.failed) return retval;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3907);
                    tableAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    		retval.stmt.setTableAlias(i-1, tableAlias1);
                                    	}

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:414:17: ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==COMMA) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:415:17: COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement3981); if (state.failed) return retval;

            	    pushFollow(FOLLOW_tokenTable_in_selectStatement4001);
            	    table2=tokenTable();

            	    state._fsp--;
            	    if (state.failed) return retval;

            	    if ( state.backtracking==0 ) {
            	                    	retval.stmt.addTable(table2);
            	                    	++i;
            	                    }

            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:421:17: ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    int alt11=2;
            	    int LA11_0 = input.LA(1);

            	    if ( (LA11_0==KEYWORD_AS) ) {
            	        alt11=1;
            	    }
            	    switch (alt11) {
            	        case 1 :
            	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:422:18: KEYWORD_AS tableAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement4056); if (state.failed) return retval;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement4077);
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
            	    break loop12;
                }
            } while (true);


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:430:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==KEYWORD_WHERE) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:431:17: KEYWORD_WHERE predicate1= abstractPredicate
                    {
                    match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement4187); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement4207);
                    predicate1=abstractPredicate();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.setWherePredicate(predicate1);
                                    }

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:439:17: ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==KEYWORD_GROUP) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:440:17: KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )*
                    {
                    match(input,KEYWORD_GROUP,FOLLOW_KEYWORD_GROUP_in_selectStatement4314); if (state.failed) return retval;

                    match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_selectStatement4316); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractExpression_in_selectStatement4336);
                    groupExpr1=abstractExpression();

                    state._fsp--;
                    if (state.failed) return retval;

                    if ( state.backtracking==0 ) {
                                    	retval.stmt.addGroupExpression(groupExpr1);
                                    	++i;
                                    }

                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:446:17: ( COMMA groupExpr2= abstractExpression )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==COMMA) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:447:17: COMMA groupExpr2= abstractExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement4390); if (state.failed) return retval;

                    	    pushFollow(FOLLOW_abstractExpression_in_selectStatement4410);
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
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:457:17: ( KEYWORD_HAVING havingPred= abstractPredicate )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==KEYWORD_HAVING) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:458:17: KEYWORD_HAVING havingPred= abstractPredicate
                    {
                    match(input,KEYWORD_HAVING,FOLLOW_KEYWORD_HAVING_in_selectStatement4536); if (state.failed) return retval;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement4556);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, selectStatement_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "selectStatement"



    // $ANTLR start "abstractPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:468:1: abstractPredicate returns [AbstractPredicate predicate] : predicate1= complexPredicateOr ;
    public final AbstractPredicate abstractPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int abstractPredicate_StartIndex = input.index();

        ComplexPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:469:2: (predicate1= complexPredicateOr )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:470:3: predicate1= complexPredicateOr
            {
            pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate4618);
            predicate1=complexPredicateOr();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
            			predicate = predicate1;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, abstractPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "abstractPredicate"



    // $ANTLR start "complexPredicateOr"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:475:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
    public final ComplexPredicate complexPredicateOr() throws RecognitionException {
        ComplexPredicate predicateOr = null;

        int complexPredicateOr_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return predicateOr; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:479:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:480:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:480:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:481:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
            {
            pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr4656);
            predicate1=complexPredicateAnd();

            state._fsp--;
            if (state.failed) return predicateOr;

            if ( state.backtracking==0 ) {
            			predicateOr.setPredicate1(predicate1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:484:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==KEYWORD_OR) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:485:4: KEYWORD_OR predicate2= complexPredicateAnd
            	    {
            	    match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr4667); if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addOr();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr4675);
            	    predicate2=complexPredicateAnd();

            	    state._fsp--;
            	    if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, complexPredicateOr_StartIndex); }

        }
        return predicateOr;
    }
    // $ANTLR end "complexPredicateOr"



    // $ANTLR start "complexPredicateAnd"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:495:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
    public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
        ComplexPredicate predicateAnd = null;

        int complexPredicateAnd_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return predicateAnd; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:499:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:500:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:500:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:501:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
            {
            pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd4714);
            predicate1=complexPredicateNot();

            state._fsp--;
            if (state.failed) return predicateAnd;

            if ( state.backtracking==0 ) {
            			predicateAnd.setPredicate1(predicate1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:504:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==KEYWORD_AND) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:505:4: KEYWORD_AND predicate2= complexPredicateNot
            	    {
            	    match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd4725); if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addAnd();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd4733);
            	    predicate2=complexPredicateNot();

            	    state._fsp--;
            	    if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, complexPredicateAnd_StartIndex); }

        }
        return predicateAnd;
    }
    // $ANTLR end "complexPredicateAnd"



    // $ANTLR start "complexPredicateNot"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:515:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
    public final ComplexPredicate complexPredicateNot() throws RecognitionException {
        ComplexPredicate predicateNot = null;

        int complexPredicateNot_StartIndex = input.index();

        AbstractPredicate predicate1 =null;



                	predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return predicateNot; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:519:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:520:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:520:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:521:3: ( KEYWORD_NOT )? predicate1= complexPredicate
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:521:3: ( KEYWORD_NOT )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==KEYWORD_NOT) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:522:3: KEYWORD_NOT
                    {
                    match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot4774); if (state.failed) return predicateNot;

                    if ( state.backtracking==0 ) {
                    			predicateNot.negate();
                    		}

                    }
                    break;

            }


            pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot4787);
            predicate1=complexPredicate();

            state._fsp--;
            if (state.failed) return predicateNot;

            if ( state.backtracking==0 ) {
            			predicateNot.setPredicate1(predicate1);
            		}

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, complexPredicateNot_StartIndex); }

        }
        return predicateNot;
    }
    // $ANTLR end "complexPredicateNot"



    // $ANTLR start "complexPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:532:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
    public final AbstractPredicate complexPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int complexPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;

        SimplePredicate predicate2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:533:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:534:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:534:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==LPAREN) ) {
                int LA20_1 = input.LA(2);

                if ( (synpred28_FunSQL()) ) {
                    alt20=1;
                }
                else if ( (true) ) {
                    alt20=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA20_0==FUNCTION_AGGREGATION||LA20_0==IDENTIFIER||(LA20_0 >= LITERAL_DECIMAL && LA20_0 <= LITERAL_STRING)||LA20_0==MINUS||LA20_0==PLUS||LA20_0==QUOTE_DOUBLE||LA20_0==TYPE_DATE) ) {
                alt20=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return predicate;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:535:3: predicate1= parenPredicate
                    {
                    pushFollow(FOLLOW_parenPredicate_in_complexPredicate4814);
                    predicate1=parenPredicate();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate1;
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:540:3: predicate2= simplePredicate
                    {
                    pushFollow(FOLLOW_simplePredicate_in_complexPredicate4829);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 16, complexPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "complexPredicate"



    // $ANTLR start "parenPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:547:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
    public final AbstractPredicate parenPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int parenPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:548:2: ( LPAREN predicate1= abstractPredicate RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:549:3: LPAREN predicate1= abstractPredicate RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate4854); if (state.failed) return predicate;

            pushFollow(FOLLOW_abstractPredicate_in_parenPredicate4861);
            predicate1=abstractPredicate();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
            			predicate = predicate1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate4867); if (state.failed) return predicate;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 17, parenPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "parenPredicate"



    // $ANTLR start "simplePredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:556:1: simplePredicate returns [SimplePredicate predicate] : (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) ;
    public final SimplePredicate simplePredicate() throws RecognitionException {
        SimplePredicate predicate = null;

        int simplePredicate_StartIndex = input.index();

        AbstractExpression expr1 =null;

        FunSQLParser.tokenCompOperator_return comp =null;

        AbstractExpression expr2 =null;



                	predicate = new SimplePredicate();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:560:9: ( (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:561:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:561:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:562:3: expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression
            {
            pushFollow(FOLLOW_abstractExpression_in_simplePredicate4909);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setExpr1(expr1);
                            }

            pushFollow(FOLLOW_tokenCompOperator_in_simplePredicate4964);
            comp=tokenCompOperator();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setComp(EnumCompOperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
                            }

            pushFollow(FOLLOW_abstractExpression_in_simplePredicate5002);
            expr2=abstractExpression();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setExpr2(expr2);
                            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 18, simplePredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "simplePredicate"



    // $ANTLR start "abstractExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:579:1: abstractExpression returns [AbstractExpression expression] : expression1= complexExpressionAdd ;
    public final AbstractExpression abstractExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int abstractExpression_StartIndex = input.index();

        ComplexExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:580:2: (expression1= complexExpressionAdd )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:581:2: expression1= complexExpressionAdd
            {
            pushFollow(FOLLOW_complexExpressionAdd_in_abstractExpression5056);
            expression1=complexExpressionAdd();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            		expression = expression1;
            	}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 19, abstractExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "abstractExpression"



    // $ANTLR start "complexExpressionAdd"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:586:1: complexExpressionAdd returns [ComplexExpression expression] : (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) ;
    public final ComplexExpression complexExpressionAdd() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionAdd_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenAddOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:590:2: ( (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:591:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:591:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:592:3: expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )*
            {
            pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd5086);
            expression1=complexExpressionMult();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:595:3: (op1= tokenAddOperator expression2= complexExpressionMult )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==MINUS||LA21_0==PLUS) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:596:4: op1= tokenAddOperator expression2= complexExpressionMult
            	    {
            	    pushFollow(FOLLOW_tokenAddOperator_in_complexExpressionAdd5099);
            	    op1=tokenAddOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd5107);
            	    expression2=complexExpressionMult();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 20, complexExpressionAdd_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionAdd"



    // $ANTLR start "complexExpressionMult"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:606:1: complexExpressionMult returns [ComplexExpression expression] : (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) ;
    public final ComplexExpression complexExpressionMult() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionMult_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenMultOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:610:2: ( (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:611:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:611:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:612:3: expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )*
            {
            pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult5147);
            expression1=complexExpressionSigned();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:615:3: (op1= tokenMultOperator expression2= complexExpressionSigned )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==DIV||LA22_0==MULT) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:616:4: op1= tokenMultOperator expression2= complexExpressionSigned
            	    {
            	    pushFollow(FOLLOW_tokenMultOperator_in_complexExpressionMult5160);
            	    op1=tokenMultOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult5168);
            	    expression2=complexExpressionSigned();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 21, complexExpressionMult_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionMult"



    // $ANTLR start "complexExpressionSigned"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:627:1: complexExpressionSigned returns [ComplexExpression expression] : ( ( MINUS | PLUS )? expression1= complexExpression ) ;
    public final ComplexExpression complexExpressionSigned() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionSigned_StartIndex = input.index();

        AbstractExpression expression1 =null;



                	expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:631:2: ( ( ( MINUS | PLUS )? expression1= complexExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:632:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:632:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:633:3: ( MINUS | PLUS )? expression1= complexExpression
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:633:3: ( MINUS | PLUS )?
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==MINUS) ) {
                alt23=1;
            }
            else if ( (LA23_0==PLUS) ) {
                alt23=2;
            }
            switch (alt23) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:634:3: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_complexExpressionSigned5210); if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression.negate();
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:638:3: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_complexExpressionSigned5219); if (state.failed) return expression;

                    }
                    break;

            }


            pushFollow(FOLLOW_complexExpression_in_complexExpressionSigned5231);
            expression1=complexExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 22, complexExpressionSigned_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionSigned"



    // $ANTLR start "complexExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:646:1: complexExpression returns [AbstractExpression expression] : (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) ;
    public final AbstractExpression complexExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int complexExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;

        AggregationExpression expression2 =null;

        SimpleExpression expression3 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:647:2: ( (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:648:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:648:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
            int alt24=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt24=1;
                }
                break;
            case FUNCTION_AGGREGATION:
                {
                alt24=2;
                }
                break;
            case IDENTIFIER:
            case LITERAL_DECIMAL:
            case LITERAL_INTEGER:
            case LITERAL_STRING:
            case QUOTE_DOUBLE:
            case TYPE_DATE:
                {
                alt24=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:649:3: expression1= parenExpression
                    {
                    pushFollow(FOLLOW_parenExpression_in_complexExpression5266);
                    expression1=parenExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression1;
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:654:3: expression2= aggregationExpression
                    {
                    pushFollow(FOLLOW_aggregationExpression_in_complexExpression5281);
                    expression2=aggregationExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression2;
                    		}

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:659:3: expression3= simpleExpression
                    {
                    pushFollow(FOLLOW_simpleExpression_in_complexExpression5296);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 23, complexExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpression"



    // $ANTLR start "parenExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:666:1: parenExpression returns [AbstractExpression expression] : LPAREN expression1= abstractExpression RPAREN ;
    public final AbstractExpression parenExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int parenExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:667:2: ( LPAREN expression1= abstractExpression RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:668:3: LPAREN expression1= abstractExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenExpression5321); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_parenExpression5328);
            expression1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression = expression1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenExpression5334); if (state.failed) return expression;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 24, parenExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "parenExpression"



    // $ANTLR start "aggregationExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:676:1: aggregationExpression returns [AggregationExpression expression] : (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) ;
    public final AggregationExpression aggregationExpression() throws RecognitionException {
        AggregationExpression expression = null;

        int aggregationExpression_StartIndex = input.index();

        Token agg1=null;
        AbstractExpression expr1 =null;



                	expression = new AggregationExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:680:9: ( (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:681:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:681:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:682:3: agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN
            {
            agg1=(Token)match(input,FUNCTION_AGGREGATION,FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression5386); if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setAggregation((agg1!=null?agg1.getText():null).toUpperCase());
            		}

            match(input,LPAREN,FOLLOW_LPAREN_in_aggregationExpression5394); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_aggregationExpression5401);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpression(expr1);
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_aggregationExpression5407); if (state.failed) return expression;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 25, aggregationExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "aggregationExpression"



    // $ANTLR start "simpleExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:694:1: simpleExpression returns [SimpleExpression expression] : ( (att1= tokenAttribute |lit1= tokenLiteral ) ) ;
    public final SimpleExpression simpleExpression() throws RecognitionException {
        SimpleExpression expression = null;

        int simpleExpression_StartIndex = input.index();

        TokenAttribute att1 =null;

        TokenLiteral lit1 =null;



                	expression = new SimpleExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:698:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:699:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:699:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:700:3: (att1= tokenAttribute |lit1= tokenLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:700:3: (att1= tokenAttribute |lit1= tokenLiteral )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==IDENTIFIER||LA25_0==QUOTE_DOUBLE) ) {
                alt25=1;
            }
            else if ( ((LA25_0 >= LITERAL_DECIMAL && LA25_0 <= LITERAL_STRING)||LA25_0==TYPE_DATE) ) {
                alt25=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:701:3: att1= tokenAttribute
                    {
                    pushFollow(FOLLOW_tokenAttribute_in_simpleExpression5482);
                    att1=tokenAttribute();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                                    	expression.setOper(att1);
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:706:17: lit1= tokenLiteral
                    {
                    pushFollow(FOLLOW_tokenLiteral_in_simpleExpression5538);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 26, simpleExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "simpleExpression"



    // $ANTLR start "tokenAttribute"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:714:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenAttribute tokenAttribute() throws RecognitionException {
        TokenAttribute attribute = null;

        int tokenAttribute_StartIndex = input.index();

        TokenIdentifier table1 =null;

        TokenIdentifier id1 =null;



                	attribute = new TokenAttribute();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return attribute; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:718:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:719:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:719:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:720:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:720:17: (table1= tokenIdentifier DOT )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==IDENTIFIER) ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==DOT) ) {
                    alt26=1;
                }
            }
            else if ( (LA26_0==QUOTE_DOUBLE) ) {
                int LA26_2 = input.LA(2);

                if ( (LA26_2==IDENTIFIER) ) {
                    int LA26_5 = input.LA(3);

                    if ( (LA26_5==QUOTE_DOUBLE) ) {
                        int LA26_6 = input.LA(4);

                        if ( (LA26_6==DOT) ) {
                            alt26=1;
                        }
                    }
                }
            }
            switch (alt26) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:721:17: table1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute5666);
                    table1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return attribute;

                    if ( state.backtracking==0 ) {
                                    	TokenTable table = new TokenTable();
                                    	table.setName(table1);
                                    	attribute.setTable(table);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenAttribute5686); if (state.failed) return attribute;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute5725);
            id1=tokenIdentifier();

            state._fsp--;
            if (state.failed) return attribute;

            if ( state.backtracking==0 ) {
                            	attribute.setName(id1);
                            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 27, tokenAttribute_StartIndex); }

        }
        return attribute;
    }
    // $ANTLR end "tokenAttribute"



    // $ANTLR start "tokenTable"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:734:1: tokenTable returns [TokenTable table] : ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) );
    public final TokenTable tokenTable() throws RecognitionException {
        TokenTable table = null;

        int tokenTable_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	table = new TokenTable();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return table; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:738:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) | ( ( COLON )? id1= tokenIdentifier ) )
            int alt29=2;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                int LA29_1 = input.LA(2);

                if ( (synpred38_FunSQL()) ) {
                    alt29=1;
                }
                else if ( (true) ) {
                    alt29=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return table;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 29, 1, input);

                    throw nvae;

                }
                }
                break;
            case QUOTE_DOUBLE:
                {
                int LA29_2 = input.LA(2);

                if ( (LA29_2==IDENTIFIER) ) {
                    int LA29_5 = input.LA(3);

                    if ( (LA29_5==QUOTE_DOUBLE) ) {
                        int LA29_6 = input.LA(4);

                        if ( (synpred38_FunSQL()) ) {
                            alt29=1;
                        }
                        else if ( (true) ) {
                            alt29=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return table;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 29, 6, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return table;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 5, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return table;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 29, 2, input);

                    throw nvae;

                }
                }
                break;
            case COLON:
                {
                alt29=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return table;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }

            switch (alt29) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:739:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:739:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:740:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:740:17: (schema1= tokenIdentifier DOT )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==IDENTIFIER) ) {
                        int LA27_1 = input.LA(2);

                        if ( (LA27_1==DOT) ) {
                            alt27=1;
                        }
                    }
                    else if ( (LA27_0==QUOTE_DOUBLE) ) {
                        int LA27_2 = input.LA(2);

                        if ( (LA27_2==IDENTIFIER) ) {
                            int LA27_5 = input.LA(3);

                            if ( (LA27_5==QUOTE_DOUBLE) ) {
                                int LA27_6 = input.LA(4);

                                if ( (LA27_6==DOT) ) {
                                    alt27=1;
                                }
                            }
                        }
                    }
                    switch (alt27) {
                        case 1 :
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:741:17: schema1= tokenIdentifier DOT
                            {
                            pushFollow(FOLLOW_tokenIdentifier_in_tokenTable5848);
                            schema1=tokenIdentifier();

                            state._fsp--;
                            if (state.failed) return table;

                            if ( state.backtracking==0 ) {
                                                    TokenSchema schema = new TokenSchema();
                                            	table.setSchema(schema);
                                            	table.setVariable(false);
                                            }

                            match(input,DOT,FOLLOW_DOT_in_tokenTable5868); if (state.failed) return table;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable5925);
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:751:12: ( ( COLON )? id1= tokenIdentifier )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:751:12: ( ( COLON )? id1= tokenIdentifier )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:752:17: ( COLON )? id1= tokenIdentifier
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:752:17: ( COLON )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==COLON) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:753:17: COLON
                            {
                            match(input,COLON,FOLLOW_COLON_in_tokenTable5992); if (state.failed) return table;

                            }
                            break;

                    }


                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable6034);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 28, tokenTable_StartIndex); }

        }
        return table;
    }
    // $ANTLR end "tokenTable"



    // $ANTLR start "tokenSchema"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:762:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
    public final TokenSchema tokenSchema() throws RecognitionException {
        TokenSchema schema = null;

        int tokenSchema_StartIndex = input.index();

        TokenIdentifier tokenIdentifier15 =null;



                	schema = new TokenSchema();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return schema; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:766:9: ( ( tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:767:9: ( tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:767:9: ( tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:768:17: tokenIdentifier
            {
            pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema6115);
            tokenIdentifier15=tokenIdentifier();

            state._fsp--;
            if (state.failed) return schema;

            if ( state.backtracking==0 ) {
                            	schema.setName(tokenIdentifier15);
                            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 29, tokenSchema_StartIndex); }

        }
        return schema;
    }
    // $ANTLR end "tokenSchema"



    // $ANTLR start "tokenFunction"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:774:1: tokenFunction returns [TokenFunction function] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenFunction tokenFunction() throws RecognitionException {
        TokenFunction function = null;

        int tokenFunction_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	function = new TokenFunction();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return function; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:778:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:779:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:779:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:780:33: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:780:33: (schema1= tokenIdentifier DOT )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==IDENTIFIER) ) {
                int LA30_1 = input.LA(2);

                if ( (LA30_1==DOT) ) {
                    alt30=1;
                }
            }
            else if ( (LA30_0==QUOTE_DOUBLE) ) {
                int LA30_2 = input.LA(2);

                if ( (LA30_2==IDENTIFIER) ) {
                    int LA30_5 = input.LA(3);

                    if ( (LA30_5==QUOTE_DOUBLE) ) {
                        int LA30_6 = input.LA(4);

                        if ( (LA30_6==DOT) ) {
                            alt30=1;
                        }
                    }
                }
            }
            switch (alt30) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:781:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction6231);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return function;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	function.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenFunction6251); if (state.failed) return function;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction6290);
            id1=tokenIdentifier();

            state._fsp--;
            if (state.failed) return function;

            if ( state.backtracking==0 ) {
                            	function.setName(id1);
                            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 30, tokenFunction_StartIndex); }

        }
        return function;
    }
    // $ANTLR end "tokenFunction"



    // $ANTLR start "tokenVariable"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:793:1: tokenVariable returns [TokenVariable variable] : ( variableText ) ;
    public final TokenVariable tokenVariable() throws RecognitionException {
        TokenVariable variable = null;

        int tokenVariable_StartIndex = input.index();

        String variableText16 =null;



                	variable = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return variable; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:797:9: ( ( variableText ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:798:9: ( variableText )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:798:9: ( variableText )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:799:17: variableText
            {
            pushFollow(FOLLOW_variableText_in_tokenVariable6370);
            variableText16=variableText();

            state._fsp--;
            if (state.failed) return variable;

            if ( state.backtracking==0 ) {
                            variable = new TokenVariable(variableText16);	
                            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 31, tokenVariable_StartIndex); }

        }
        return variable;
    }
    // $ANTLR end "tokenVariable"



    // $ANTLR start "tokenAssignment"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:805:1: tokenAssignment returns [TokenAssignment ass] : ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) SEMI ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:809:3: ( ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) SEMI )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:810:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) SEMI
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:810:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==COLON) ) {
                alt31=1;
            }
            else if ( (LA31_0==KEYWORD_VAR) ) {
                alt31=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ass;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }
            switch (alt31) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:811:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:811:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:812:4: COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement
                    {
                    match(input,COLON,FOLLOW_COLON_in_tokenAssignment6437); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment6449);
                    var1=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setReference(true);
                    		 ass.setVar(var1);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment6455); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment6462);
                    selstmt1=selectStatement();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setSelStmt(selstmt1.stmt);
                    		 }

                    }


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:823:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:823:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:824:4: KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement
                    {
                    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment6483); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment6492);
                    var2=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {		 
                    		 ass.setReference(false);
                    		 ass.setVar(var2);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment6498); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment6505);
                    selstmt2=selectStatement();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setSelStmt(selstmt2.stmt);
                    		 }

                    }


                    }
                    break;

            }


            match(input,SEMI,FOLLOW_SEMI_in_tokenAssignment6525); if (state.failed) return ass;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 32, tokenAssignment_StartIndex); }

        }
        return ass;
    }
    // $ANTLR end "tokenAssignment"



    // $ANTLR start "tokenIdentifier"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:839:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
    public final TokenIdentifier tokenIdentifier() throws RecognitionException {
        TokenIdentifier identifier = null;

        int tokenIdentifier_StartIndex = input.index();

        String identifierText17 =null;



                	identifier = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return identifier; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:843:9: ( ( identifierText ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:844:9: ( identifierText )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:844:9: ( identifierText )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:845:17: identifierText
            {
            pushFollow(FOLLOW_identifierText_in_tokenIdentifier6590);
            identifierText17=identifierText();

            state._fsp--;
            if (state.failed) return identifier;

            if ( state.backtracking==0 ) {
                            	identifier = new TokenIdentifier(identifierText17);
                            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 33, tokenIdentifier_StartIndex); }

        }
        return identifier;
    }
    // $ANTLR end "tokenIdentifier"



    // $ANTLR start "tokenDataType"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:852:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) ;
    public final TokenDataType tokenDataType() throws RecognitionException {
        TokenDataType dataType = null;

        int tokenDataType_StartIndex = input.index();

        Token TYPE_VARCHAR18=null;
        Token TYPE_INTEGER19=null;
        Token TYPE_DECIMAL20=null;
        Token TYPE_DATE21=null;


                	dataType = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return dataType; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:856:9: ( ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:857:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:857:9: ( TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE )
            int alt32=4;
            switch ( input.LA(1) ) {
            case TYPE_VARCHAR:
                {
                alt32=1;
                }
                break;
            case TYPE_INTEGER:
                {
                alt32=2;
                }
                break;
            case TYPE_DECIMAL:
                {
                alt32=3;
                }
                break;
            case TYPE_DATE:
                {
                alt32=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return dataType;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }

            switch (alt32) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:858:17: TYPE_VARCHAR
                    {
                    TYPE_VARCHAR18=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType6671); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_VARCHAR18!=null?TYPE_VARCHAR18.getText():null));
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:862:17: TYPE_INTEGER
                    {
                    TYPE_INTEGER19=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType6709); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_INTEGER19!=null?TYPE_INTEGER19.getText():null));
                                    }

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:866:17: TYPE_DECIMAL
                    {
                    TYPE_DECIMAL20=(Token)match(input,TYPE_DECIMAL,FOLLOW_TYPE_DECIMAL_in_tokenDataType6747); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_DECIMAL20!=null?TYPE_DECIMAL20.getText():null));
                                    }

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:870:17: TYPE_DATE
                    {
                    TYPE_DATE21=(Token)match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDataType6785); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_DATE21!=null?TYPE_DATE21.getText():null));
                                    }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 34, tokenDataType_StartIndex); }

        }
        return dataType;
    }
    // $ANTLR end "tokenDataType"



    // $ANTLR start "tokenLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:876:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
    public final TokenLiteral tokenLiteral() throws RecognitionException {
        TokenLiteral literal = null;

        int tokenLiteral_StartIndex = input.index();

        TokenIntegerLiteral tokenIntegerLiteral22 =null;

        TokenStringLiteral tokenStringLiteral23 =null;

        TokenDecimalLiteral tokenDecimalLiteral24 =null;

        TokenDateLiteral tokenDateLiteral25 =null;



                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:880:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:881:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:881:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:882:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:882:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            int alt33=4;
            switch ( input.LA(1) ) {
            case LITERAL_INTEGER:
                {
                alt33=1;
                }
                break;
            case LITERAL_STRING:
                {
                alt33=2;
                }
                break;
            case LITERAL_DECIMAL:
                {
                alt33=3;
                }
                break;
            case TYPE_DATE:
                {
                alt33=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return literal;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }

            switch (alt33) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:883:17: tokenIntegerLiteral
                    {
                    pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral6876);
                    tokenIntegerLiteral22=tokenIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenIntegerLiteral22;
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:887:17: tokenStringLiteral
                    {
                    pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral6914);
                    tokenStringLiteral23=tokenStringLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenStringLiteral23;
                                    }

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:891:17: tokenDecimalLiteral
                    {
                    pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral6952);
                    tokenDecimalLiteral24=tokenDecimalLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenDecimalLiteral24;
                                    }

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:895:17: tokenDateLiteral
                    {
                    pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral6990);
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 35, tokenLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenLiteral"



    // $ANTLR start "tokenStringLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:902:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
    public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
        TokenStringLiteral literal = null;

        int tokenStringLiteral_StartIndex = input.index();

        Token lit1=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:906:9: ( (lit1= LITERAL_STRING ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:907:9: (lit1= LITERAL_STRING )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:907:9: (lit1= LITERAL_STRING )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:908:17: lit1= LITERAL_STRING
            {
            lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral7090); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenStringLiteral((lit1!=null?lit1.getText():null).substring(1, (lit1!=null?lit1.getText():null).length()-1));
                            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 36, tokenStringLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenStringLiteral"



    // $ANTLR start "tokenIntegerLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:914:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
    public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
        TokenIntegerLiteral literal = null;

        int tokenIntegerLiteral_StartIndex = input.index();

        Token LITERAL_INTEGER26=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:918:9: ( ( LITERAL_INTEGER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:919:9: ( LITERAL_INTEGER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:919:9: ( LITERAL_INTEGER )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:920:17: LITERAL_INTEGER
            {
            LITERAL_INTEGER26=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral7170); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenIntegerLiteral((LITERAL_INTEGER26!=null?LITERAL_INTEGER26.getText():null));
                            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 37, tokenIntegerLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenIntegerLiteral"



    // $ANTLR start "tokenDecimalLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:927:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
    public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
        TokenDecimalLiteral literal = null;

        int tokenDecimalLiteral_StartIndex = input.index();

        Token LITERAL_DECIMAL27=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:931:9: ( ( LITERAL_DECIMAL ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:932:9: ( LITERAL_DECIMAL )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:932:9: ( LITERAL_DECIMAL )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:933:17: LITERAL_DECIMAL
            {
            LITERAL_DECIMAL27=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral7260); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenDecimalLiteral((LITERAL_DECIMAL27!=null?LITERAL_DECIMAL27.getText():null));
                            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 38, tokenDecimalLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenDecimalLiteral"



    // $ANTLR start "tokenDateLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:939:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( TYPE_DATE LITERAL_STRING ) ;
    public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
        TokenDateLiteral literal = null;

        int tokenDateLiteral_StartIndex = input.index();

        Token LITERAL_STRING28=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:943:9: ( ( TYPE_DATE LITERAL_STRING ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:944:9: ( TYPE_DATE LITERAL_STRING )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:944:9: ( TYPE_DATE LITERAL_STRING )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:946:17: TYPE_DATE LITERAL_STRING
            {
            match(input,TYPE_DATE,FOLLOW_TYPE_DATE_in_tokenDateLiteral7358); if (state.failed) return literal;

            LITERAL_STRING28=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenDateLiteral7376); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenDateLiteral("date "+(LITERAL_STRING28!=null?LITERAL_STRING28.getText():null));
                            }

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 39, tokenDateLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenDateLiteral"



    // $ANTLR start "variableText"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:953:2: variableText returns [String text] : (var1= IDENTIFIER ) ;
    public final String variableText() throws RecognitionException {
        String text = null;

        int variableText_StartIndex = input.index();

        Token var1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return text; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:954:3: ( (var1= IDENTIFIER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:955:3: (var1= IDENTIFIER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:955:3: (var1= IDENTIFIER )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:957:4: var1= IDENTIFIER
            {
            var1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variableText7435); if (state.failed) return text;

            if ( state.backtracking==0 ) {
             		text = (var1!=null?var1.getText():null).toUpperCase();
             		}

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 40, variableText_StartIndex); }

        }
        return text;
    }
    // $ANTLR end "variableText"



    // $ANTLR start "identifierText"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:963:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) ;
    public final String identifierText() throws RecognitionException {
        String text = null;

        int identifierText_StartIndex = input.index();

        Token id1=null;
        Token id2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return text; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:964:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:965:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:965:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==IDENTIFIER) ) {
                alt34=1;
            }
            else if ( (LA34_0==QUOTE_DOUBLE) ) {
                alt34=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return text;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;

            }
            switch (alt34) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:966:4: (id1= IDENTIFIER )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:966:4: (id1= IDENTIFIER )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:967:4: id1= IDENTIFIER
                    {
                    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText7487); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id1!=null?id1.getText():null).toUpperCase();
                                    }

                    }


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:972:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:972:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:973:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText7543); if (state.failed) return text;

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText7559); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id2!=null?id2.getText():null);
                                    }

                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText7579); if (state.failed) return text;

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 41, identifierText_StartIndex); }

        }
        return text;
    }
    // $ANTLR end "identifierText"


    public static class tokenAddOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenAddOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:982:1: tokenAddOperator : ( PLUS | MINUS ) ;
    public final FunSQLParser.tokenAddOperator_return tokenAddOperator() throws RecognitionException {
        FunSQLParser.tokenAddOperator_return retval = new FunSQLParser.tokenAddOperator_return();
        retval.start = input.LT(1);

        int tokenAddOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:983:5: ( ( PLUS | MINUS ) )
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 42, tokenAddOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenAddOperator"


    public static class tokenMultOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenMultOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:990:1: tokenMultOperator : ( MULT | DIV ) ;
    public final FunSQLParser.tokenMultOperator_return tokenMultOperator() throws RecognitionException {
        FunSQLParser.tokenMultOperator_return retval = new FunSQLParser.tokenMultOperator_return();
        retval.start = input.LT(1);

        int tokenMultOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:991:5: ( ( MULT | DIV ) )
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 43, tokenMultOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenMultOperator"


    public static class tokenCompOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenCompOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:998:1: tokenCompOperator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) ;
    public final FunSQLParser.tokenCompOperator_return tokenCompOperator() throws RecognitionException {
        FunSQLParser.tokenCompOperator_return retval = new FunSQLParser.tokenCompOperator_return();
        retval.start = input.LT(1);

        int tokenCompOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:999:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
            {
            if ( input.LA(1)==EQUAL1||(input.LA(1) >= GREATER_EQUAL1 && input.LA(1) <= GREATER_EQUAL2)||(input.LA(1) >= LESS_EQUAL1 && input.LA(1) <= LESS_THAN)||(input.LA(1) >= NOT_EQUAL1 && input.LA(1) <= NOT_EQUAL2) ) {
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
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 44, tokenCompOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenCompOperator"

    // $ANTLR start synpred28_FunSQL
    public final void synpred28_FunSQL_fragment() throws RecognitionException {
        AbstractPredicate predicate1 =null;


        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:535:3: (predicate1= parenPredicate )
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:535:3: predicate1= parenPredicate
        {
        pushFollow(FOLLOW_parenPredicate_in_synpred28_FunSQL4814);
        predicate1=parenPredicate();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred28_FunSQL

    // $ANTLR start synpred38_FunSQL
    public final void synpred38_FunSQL_fragment() throws RecognitionException {
        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;


        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:739:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:739:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
        {
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:739:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:740:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
        {
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:740:17: (schema1= tokenIdentifier DOT )?
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
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:741:17: schema1= tokenIdentifier DOT
                {
                pushFollow(FOLLOW_tokenIdentifier_in_synpred38_FunSQL5848);
                schema1=tokenIdentifier();

                state._fsp--;
                if (state.failed) return ;

                match(input,DOT,FOLLOW_DOT_in_synpred38_FunSQL5868); if (state.failed) return ;

                }
                break;

        }


        pushFollow(FOLLOW_tokenIdentifier_in_synpred38_FunSQL5925);
        id1=tokenIdentifier();

        state._fsp--;
        if (state.failed) return ;

        }


        }

    }
    // $ANTLR end synpred38_FunSQL

    // Delegated rules

    public final boolean synpred38_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred38_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred28_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred28_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_createSchemaStatement_in_statement1090 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_dropSchemaStatement_in_statement1145 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_createConnectionStatement_in_statement1200 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_dropConnectionStatement_in_statement1255 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_createTableStatement_in_statement1310 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_dropTableStatement_in_statement1365 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_createFunctionStatement_in_statement1420 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_dropFunctionStatement_in_statement1475 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_callFunctionStatement_in_statement1530 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_selectStatement_in_statement1585 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_SEMI_in_statement1639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1717 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1735 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1832 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1850 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1947 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1965 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement1983 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement2003 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2023 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement2043 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2063 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement2083 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2103 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement2123 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2230 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2248 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2363 = new BitSet(new long[]{0x0000000200001000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2383 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement2403 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2423 = new BitSet(new long[]{0x0000000000000000L,0x00000F0000000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2461 = new BitSet(new long[]{0x0000000000002000L,0x0000000200000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2515 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2535 = new BitSet(new long[]{0x0000000000000000L,0x00000F0000000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2573 = new BitSet(new long[]{0x0000000000002000L,0x0000000200000000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement2628 = new BitSet(new long[]{0x0022000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2664 = new BitSet(new long[]{0x0000000200001000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2684 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2723 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2725 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement2824 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement2842 = new BitSet(new long[]{0x0000000200001000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenTable_in_dropTableStatement2862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createFunctionStatement2928 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement2946 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenFunction_in_createFunctionStatement2966 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LPAREN_in_createFunctionStatement2985 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement3021 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement3041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3046 = new BitSet(new long[]{0x0000000000002000L,0x0000000200000000L});
    public static final BitSet FOLLOW_COMMA_in_createFunctionStatement3058 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement3062 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement3082 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement3087 = new BitSet(new long[]{0x0000000000002000L,0x0000000200000000L});
    public static final BitSet FOLLOW_RPAREN_in_createFunctionStatement3096 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement3100 = new BitSet(new long[]{0x0001000000001000L,0x0000000000000008L});
    public static final BitSet FOLLOW_tokenAssignment_in_createFunctionStatement3110 = new BitSet(new long[]{0x0001000000001000L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_END_in_createFunctionStatement3136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropFunctionStatement3216 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_dropFunctionStatement3234 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenFunction_in_dropFunctionStatement3254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CALL_in_callFunctionStatement3320 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_callFunctionStatement3338 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenFunction_in_callFunctionStatement3358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement3435 = new BitSet(new long[]{0x0000000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement3455 = new BitSet(new long[]{0x0002004000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3510 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3531 = new BitSet(new long[]{0x0002000000002000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement3605 = new BitSet(new long[]{0x0000000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement3625 = new BitSet(new long[]{0x0002004000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3680 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3701 = new BitSet(new long[]{0x0002000000002000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement3810 = new BitSet(new long[]{0x0000000200001000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement3830 = new BitSet(new long[]{0x0018004000002002L,0x0000000000000010L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3886 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3907 = new BitSet(new long[]{0x0018000000002002L,0x0000000000000010L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement3981 = new BitSet(new long[]{0x0000000200001000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement4001 = new BitSet(new long[]{0x0018004000002002L,0x0000000000000010L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement4056 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement4077 = new BitSet(new long[]{0x0018000000002002L,0x0000000000000010L});
    public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement4187 = new BitSet(new long[]{0x0100000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement4207 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_KEYWORD_GROUP_in_selectStatement4314 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_BY_in_selectStatement4316 = new BitSet(new long[]{0x0000000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement4336 = new BitSet(new long[]{0x0010000000002002L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement4390 = new BitSet(new long[]{0x0000000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement4410 = new BitSet(new long[]{0x0010000000002002L});
    public static final BitSet FOLLOW_KEYWORD_HAVING_in_selectStatement4536 = new BitSet(new long[]{0x0100000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement4556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate4618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr4656 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr4667 = new BitSet(new long[]{0x0100000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr4675 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd4714 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd4725 = new BitSet(new long[]{0x0100000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd4733 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot4774 = new BitSet(new long[]{0x0000000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot4787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_complexPredicate4814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicate_in_complexPredicate4829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenPredicate4854 = new BitSet(new long[]{0x0100000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate4861 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenPredicate4867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate4909 = new BitSet(new long[]{0x0000000018400000L,0x0000000000180380L});
    public static final BitSet FOLLOW_tokenCompOperator_in_simplePredicate4964 = new BitSet(new long[]{0x0000000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate5002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionAdd_in_abstractExpression5056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd5086 = new BitSet(new long[]{0x0000000000000002L,0x0000000001008000L});
    public static final BitSet FOLLOW_tokenAddOperator_in_complexExpressionAdd5099 = new BitSet(new long[]{0x0000000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd5107 = new BitSet(new long[]{0x0000000000000002L,0x0000000001008000L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult5147 = new BitSet(new long[]{0x0000000000020002L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenMultOperator_in_complexExpressionMult5160 = new BitSet(new long[]{0x0000000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult5168 = new BitSet(new long[]{0x0000000000020002L,0x0000000000020000L});
    public static final BitSet FOLLOW_MINUS_in_complexExpressionSigned5210 = new BitSet(new long[]{0x0000000202000000L,0x0000010010003C00L});
    public static final BitSet FOLLOW_PLUS_in_complexExpressionSigned5219 = new BitSet(new long[]{0x0000000202000000L,0x0000010010003C00L});
    public static final BitSet FOLLOW_complexExpression_in_complexExpressionSigned5231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenExpression_in_complexExpression5266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggregationExpression_in_complexExpression5281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleExpression_in_complexExpression5296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenExpression5321 = new BitSet(new long[]{0x0000000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_abstractExpression_in_parenExpression5328 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenExpression5334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression5386 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LPAREN_in_aggregationExpression5394 = new BitSet(new long[]{0x0000000202000000L,0x000001001100BC00L});
    public static final BitSet FOLLOW_abstractExpression_in_aggregationExpression5401 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_RPAREN_in_aggregationExpression5407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simpleExpression5482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenLiteral_in_simpleExpression5538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute5666 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenAttribute5686 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute5725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable5848 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenTable5868 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable5925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenTable5992 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable6034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema6115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction6231 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenFunction6251 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction6290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableText_in_tokenVariable6370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenAssignment6437 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment6449 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment6455 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment6462 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment6483 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment6492 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment6498 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment6505 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_SEMI_in_tokenAssignment6525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierText_in_tokenIdentifier6590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType6671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType6709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DECIMAL_in_tokenDataType6747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DATE_in_tokenDataType6785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral6876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral6914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral6952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral6990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral7090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral7170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral7260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_DATE_in_tokenDateLiteral7358 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenDateLiteral7376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variableText7435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText7487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText7543 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText7559 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText7579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_synpred28_FunSQL4814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_synpred38_FunSQL5848 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_synpred38_FunSQL5868 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_synpred38_FunSQL5925 = new BitSet(new long[]{0x0000000000000002L});

}