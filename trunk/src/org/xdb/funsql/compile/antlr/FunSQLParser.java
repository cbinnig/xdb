// $ANTLR 3.4 /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g 2012-10-19 14:43:10
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", "AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", "D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", "F", "FUNCTION_AGGREGATION", "G", "GREATER_EQUAL1", "GREATER_EQUAL2", "GREATER_THAN", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", "KEYWORD_AND", "KEYWORD_AS", "KEYWORD_AVG", "KEYWORD_BEGIN", "KEYWORD_BY", "KEYWORD_CONNECTION", "KEYWORD_COUNT", "KEYWORD_CREATE", "KEYWORD_DISTINCT", "KEYWORD_DROP", "KEYWORD_END", "KEYWORD_FROM", "KEYWORD_FUNCTION", "KEYWORD_GROUP", "KEYWORD_HAVING", "KEYWORD_IN", "KEYWORD_MAX", "KEYWORD_MIN", "KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_OUT", "KEYWORD_PASSWD", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", "KEYWORD_SUM", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_VAR", "KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", "LESS_THAN", "LITERAL_DATE", "LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", "MINUS", "MOD", "MULT", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", "QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", "SHIFT_RIGHT", "T", "TILDE", "TYPE_INTEGER", "TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
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
    public static final int KEYWORD_CONNECTION=42;
    public static final int KEYWORD_COUNT=43;
    public static final int KEYWORD_CREATE=44;
    public static final int KEYWORD_DISTINCT=45;
    public static final int KEYWORD_DROP=46;
    public static final int KEYWORD_END=47;
    public static final int KEYWORD_FROM=48;
    public static final int KEYWORD_FUNCTION=49;
    public static final int KEYWORD_GROUP=50;
    public static final int KEYWORD_HAVING=51;
    public static final int KEYWORD_IN=52;
    public static final int KEYWORD_MAX=53;
    public static final int KEYWORD_MIN=54;
    public static final int KEYWORD_NOT=55;
    public static final int KEYWORD_OR=56;
    public static final int KEYWORD_OUT=57;
    public static final int KEYWORD_PASSWD=58;
    public static final int KEYWORD_SCHEMA=59;
    public static final int KEYWORD_SELECT=60;
    public static final int KEYWORD_STORE=61;
    public static final int KEYWORD_SUM=62;
    public static final int KEYWORD_TABLE=63;
    public static final int KEYWORD_URL=64;
    public static final int KEYWORD_USER=65;
    public static final int KEYWORD_VAR=66;
    public static final int KEYWORD_WHERE=67;
    public static final int L=68;
    public static final int LBRACKET=69;
    public static final int LESS_EQUAL1=70;
    public static final int LESS_EQUAL2=71;
    public static final int LESS_THAN=72;
    public static final int LITERAL_DATE=73;
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
    public static final int TYPE_INTEGER=104;
    public static final int TYPE_VARCHAR=105;
    public static final int U=106;
    public static final int UNDERSCORE=107;
    public static final int V=108;
    public static final int W=109;
    public static final int WS=110;
    public static final int X=111;
    public static final int Y=112;
    public static final int Z=113;

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
        this.state.ruleMemo = new HashMap[93+1];
         

    }

    public String[] getTokenNames() { return FunSQLParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g"; }



    // $ANTLR start "statement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:68:1: statement returns [AbstractServerStmt stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement ) ( SEMI )? ) ;
    public final AbstractServerStmt statement() throws RecognitionException {
        AbstractServerStmt stmt = null;

        int statement_StartIndex = input.index();

        CreateSchemaStmt createSchemaStatement1 =null;

        DropSchemaStmt dropSchemaStatement2 =null;

        CreateConnectionStmt createConnectionStatement3 =null;

        DropConnectionStmt dropConnectionStatement4 =null;

        CreateTableStmt createTableStatement5 =null;

        DropTableStmt dropTableStatement6 =null;

        CreateFunctionStmt createFunctionStatement7 =null;

        SelectStmt selectStatement8 =null;



                	stmt = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:72:9: ( ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement ) ( SEMI )? ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:73:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement ) ( SEMI )? )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:73:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement ) ( SEMI )? )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:74:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement ) ( SEMI )?
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:74:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement )
            int alt1=8;
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
                default:
                    if (state.backtracking>0) {state.failed=true; return stmt;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 2, input);

                    throw nvae;

                }

                }
                break;
            case KEYWORD_FUNCTION:
                {
                alt1=7;
                }
                break;
            case KEYWORD_SELECT:
                {
                alt1=8;
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:75:17: createSchemaStatement
                    {
                    pushFollow(FOLLOW_createSchemaStatement_in_statement1078);
                    createSchemaStatement1=createSchemaStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = createSchemaStatement1;
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:80:17: dropSchemaStatement
                    {
                    pushFollow(FOLLOW_dropSchemaStatement_in_statement1133);
                    dropSchemaStatement2=dropSchemaStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = dropSchemaStatement2;
                                    }

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:85:17: createConnectionStatement
                    {
                    pushFollow(FOLLOW_createConnectionStatement_in_statement1188);
                    createConnectionStatement3=createConnectionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = createConnectionStatement3;
                                    }

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:90:17: dropConnectionStatement
                    {
                    pushFollow(FOLLOW_dropConnectionStatement_in_statement1243);
                    dropConnectionStatement4=dropConnectionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = dropConnectionStatement4;
                                    }

                    }
                    break;
                case 5 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:95:17: createTableStatement
                    {
                    pushFollow(FOLLOW_createTableStatement_in_statement1298);
                    createTableStatement5=createTableStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = createTableStatement5;
                                    }

                    }
                    break;
                case 6 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:100:17: dropTableStatement
                    {
                    pushFollow(FOLLOW_dropTableStatement_in_statement1353);
                    dropTableStatement6=dropTableStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = dropTableStatement6;
                                    }

                    }
                    break;
                case 7 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:105:17: createFunctionStatement
                    {
                    pushFollow(FOLLOW_createFunctionStatement_in_statement1408);
                    createFunctionStatement7=createFunctionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = createFunctionStatement7;
                                    }

                    }
                    break;
                case 8 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:110:17: selectStatement
                    {
                    pushFollow(FOLLOW_selectStatement_in_statement1463);
                    selectStatement8=selectStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = selectStatement8;
                                    }

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:115:17: ( SEMI )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==SEMI) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:115:17: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_statement1517); if (state.failed) return stmt;

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



    // $ANTLR start "createSchemaStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:119:1: createSchemaStatement returns [CreateSchemaStmt stmt] : ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) ;
    public final CreateSchemaStmt createSchemaStatement() throws RecognitionException {
        CreateSchemaStmt stmt = null;

        int createSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema9 =null;



                	stmt = new CreateSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:123:9: ( ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:124:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:124:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:125:17: KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1595); if (state.failed) return stmt;

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1613); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenSchema_in_createSchemaStatement1631);
            tokenSchema9=tokenSchema();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setSchema(tokenSchema9);
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
            if ( state.backtracking>0 ) { memoize(input, 2, createSchemaStatement_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "createSchemaStatement"



    // $ANTLR start "dropSchemaStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:133:1: dropSchemaStatement returns [DropSchemaStmt stmt] : ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) ;
    public final DropSchemaStmt dropSchemaStatement() throws RecognitionException {
        DropSchemaStmt stmt = null;

        int dropSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema10 =null;



                	stmt = new DropSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:137:9: ( ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:138:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:138:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:139:17: KEYWORD_DROP KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1710); if (state.failed) return stmt;

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1728); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenSchema_in_dropSchemaStatement1746);
            tokenSchema10=tokenSchema();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setSchema(tokenSchema10);
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
            if ( state.backtracking>0 ) { memoize(input, 3, dropSchemaStatement_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "dropSchemaStatement"



    // $ANTLR start "createConnectionStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:147:1: createConnectionStatement returns [CreateConnectionStmt stmt] : ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) ;
    public final CreateConnectionStmt createConnectionStatement() throws RecognitionException {
        CreateConnectionStmt stmt = null;

        int createConnectionStatement_StartIndex = input.index();

        TokenStringLiteral litURL =null;

        TokenStringLiteral litUser =null;

        TokenStringLiteral litPasswd =null;

        TokenStringLiteral litStore =null;

        TokenIdentifier tokenIdentifier11 =null;



                	stmt = new CreateConnectionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:151:9: ( ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:152:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:152:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:153:17: KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1825); if (state.failed) return stmt;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1843); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenIdentifier_in_createConnectionStatement1861);
            tokenIdentifier11=tokenIdentifier();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setConnection(tokenIdentifier11);
                            }

            match(input,KEYWORD_URL,FOLLOW_KEYWORD_URL_in_createConnectionStatement1881); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1901);
            litURL=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setURL(litURL);
                            }

            match(input,KEYWORD_USER,FOLLOW_KEYWORD_USER_in_createConnectionStatement1921); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1941);
            litUser=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setUser(litUser);
                            }

            match(input,KEYWORD_PASSWD,FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1961); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1981);
            litPasswd=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setPasswd(litPasswd);
                            }

            match(input,KEYWORD_STORE,FOLLOW_KEYWORD_STORE_in_createConnectionStatement2001); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2021);
            litStore=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setStore(litStore);
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
            if ( state.backtracking>0 ) { memoize(input, 4, createConnectionStatement_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "createConnectionStatement"



    // $ANTLR start "dropConnectionStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:177:1: dropConnectionStatement returns [DropConnectionStmt stmt] : ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) ;
    public final DropConnectionStmt dropConnectionStatement() throws RecognitionException {
        DropConnectionStmt stmt = null;

        int dropConnectionStatement_StartIndex = input.index();

        TokenIdentifier tokenIdentifier12 =null;



                	stmt = new DropConnectionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:181:9: ( ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:182:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:182:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:183:17: KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2108); if (state.failed) return stmt;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2126); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenIdentifier_in_dropConnectionStatement2144);
            tokenIdentifier12=tokenIdentifier();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setConnection(tokenIdentifier12);
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
            if ( state.backtracking>0 ) { memoize(input, 5, dropConnectionStatement_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "dropConnectionStatement"



    // $ANTLR start "createTableStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:191:1: createTableStatement returns [CreateTableStmt stmt] : ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) ;
    public final CreateTableStmt createTableStatement() throws RecognitionException {
        CreateTableStmt stmt = null;

        int createTableStatement_StartIndex = input.index();

        TokenTable table1 =null;

        String att1 =null;

        TokenDataType dataType1 =null;

        String att2 =null;

        TokenDataType dataType2 =null;

        TokenTable table2 =null;

        TokenIdentifier connection1 =null;



                	stmt = new CreateTableStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:195:9: ( ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:196:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:196:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:197:17: KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2223); if (state.failed) return stmt;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2241); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenTable_in_createTableStatement2261);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setTable(table1);
                            	stmt.setSourceTable(table1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2281); if (state.failed) return stmt;

            pushFollow(FOLLOW_identifierText_in_createTableStatement2301);
            att1=identifierText();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addAttribute(att1);
                            }

            pushFollow(FOLLOW_tokenDataType_in_createTableStatement2339);
            dataType1=tokenDataType();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addType(dataType1);
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:212:17: ( COMMA att2= identifierText dataType2= tokenDataType )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:213:17: COMMA att2= identifierText dataType2= tokenDataType
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2393); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_identifierText_in_createTableStatement2413);
            	    att2=identifierText();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addAttribute(att2);
            	                    }

            	    pushFollow(FOLLOW_tokenDataType_in_createTableStatement2451);
            	    dataType2=tokenDataType();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addType(dataType2);
            	                    }

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2506); if (state.failed) return stmt;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:224:17: ( KEYWORD_FROM table2= tokenTable )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_FROM) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:225:17: KEYWORD_FROM table2= tokenTable
                    {
                    match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_createTableStatement2542); if (state.failed) return stmt;

                    pushFollow(FOLLOW_tokenTable_in_createTableStatement2562);
                    table2=tokenTable();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt.setSourceTable(table2);
                                    }

                    }
                    break;

            }


            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2601); if (state.failed) return stmt;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2603); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2623);
            connection1=tokenIdentifier();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setConnection(connection1);
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
            if ( state.backtracking>0 ) { memoize(input, 6, createTableStatement_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "createTableStatement"



    // $ANTLR start "dropTableStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:237:1: dropTableStatement returns [DropTableStmt stmt] : ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) ;
    public final DropTableStmt dropTableStatement() throws RecognitionException {
        DropTableStmt stmt = null;

        int dropTableStatement_StartIndex = input.index();

        TokenTable table1 =null;



                	stmt = new DropTableStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:241:9: ( ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:242:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:242:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:243:17: KEYWORD_DROP KEYWORD_TABLE table1= tokenTable
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement2702); if (state.failed) return stmt;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement2720); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenTable_in_dropTableStatement2740);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setTable(table1);
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
            if ( state.backtracking>0 ) { memoize(input, 7, dropTableStatement_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "dropTableStatement"



    // $ANTLR start "createFunctionStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:251:1: createFunctionStatement returns [CreateFunctionStmt stmt] : ( KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END ) ;
    public final CreateFunctionStmt createFunctionStatement() throws RecognitionException {
        CreateFunctionStmt stmt = null;

        int createFunctionStatement_StartIndex = input.index();

        TokenFunction function1 =null;

        TokenVariable var1 =null;

        TokenAssignment ass1 =null;



                	stmt = new CreateFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:255:9: ( ( KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:256:9: ( KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:256:9: ( KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:257:17: KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END
            {
            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement2806); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenFunction_in_createFunctionStatement2826);
            function1=tokenFunction();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setFunction(function1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createFunctionStatement2845); if (state.failed) return stmt;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:262:17: ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==KEYWORD_OUT) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:263:17: KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE
            	    {
            	    match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement2881); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement2901);
            	    var1=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                   		stmt.addParam(var1);
            	    		}

            	    match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement2906); if (state.failed) return stmt;

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createFunctionStatement2915); if (state.failed) return stmt;

            match(input,KEYWORD_BEGIN,FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement2919); if (state.failed) return stmt;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:271:3: (ass1= tokenAssignment )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COLON||LA6_0==KEYWORD_VAR) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:272:3: ass1= tokenAssignment
            	    {
            	    pushFollow(FOLLOW_tokenAssignment_in_createFunctionStatement2929);
            	    ass1=tokenAssignment();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addAssignment(ass1.getVar(), ass1.getSelStmt());
            	                    }

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            match(input,KEYWORD_END,FOLLOW_KEYWORD_END_in_createFunctionStatement2939); if (state.failed) return stmt;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, createFunctionStatement_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "createFunctionStatement"



    // $ANTLR start "selectStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:280:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) ;
    public final SelectStmt selectStatement() throws RecognitionException {
        SelectStmt stmt = null;

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



                	stmt = new SelectStmt();
                	int i=0;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:285:9: ( ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:286:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:286:9: ( KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )? )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:288:17: KEYWORD_SELECT selExpr1= abstractExpression ( KEYWORD_AS selAlias1= tokenIdentifier )? ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )? ( KEYWORD_HAVING havingPred= abstractPredicate )?
            {
            match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement3030); if (state.failed) return stmt;

            pushFollow(FOLLOW_abstractExpression_in_selectStatement3050);
            selExpr1=abstractExpression();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addSelExpression(selExpr1);
                            	++i;
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:294:17: ( KEYWORD_AS selAlias1= tokenIdentifier )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==KEYWORD_AS) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:295:18: KEYWORD_AS selAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3105); if (state.failed) return stmt;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3126);
                    selAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    		stmt.setSelAlias(i-1, selAlias1);
                                    	}

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:301:17: ( COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )? )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==COMMA) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:302:17: COMMA selExpr2= abstractExpression ( KEYWORD_AS selAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement3200); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_abstractExpression_in_selectStatement3220);
            	    selExpr2=abstractExpression();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addSelExpression(selExpr2);
            	                    	++i;
            	                    }

            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:308:17: ( KEYWORD_AS selAlias2= tokenIdentifier )?
            	    int alt8=2;
            	    int LA8_0 = input.LA(1);

            	    if ( (LA8_0==KEYWORD_AS) ) {
            	        alt8=1;
            	    }
            	    switch (alt8) {
            	        case 1 :
            	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:309:18: KEYWORD_AS selAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3275); if (state.failed) return stmt;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3296);
            	            selAlias2=tokenIdentifier();

            	            state._fsp--;
            	            if (state.failed) return stmt;

            	            if ( state.backtracking==0 ) {
            	                            		stmt.setSelAlias(i-1, selAlias2);
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


            match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement3405); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenTable_in_selectStatement3425);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addTable(table1);
                            	i=1;
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:324:17: ( KEYWORD_AS tableAlias1= tokenIdentifier )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==KEYWORD_AS) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:325:18: KEYWORD_AS tableAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3481); if (state.failed) return stmt;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3502);
                    tableAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    		stmt.setTableAlias(i-1, tableAlias1);
                                    	}

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:331:17: ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==COMMA) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:332:17: COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement3576); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_tokenTable_in_selectStatement3596);
            	    table2=tokenTable();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addTable(table2);
            	                    	++i;
            	                    }

            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:338:17: ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    int alt11=2;
            	    int LA11_0 = input.LA(1);

            	    if ( (LA11_0==KEYWORD_AS) ) {
            	        alt11=1;
            	    }
            	    switch (alt11) {
            	        case 1 :
            	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:339:18: KEYWORD_AS tableAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3651); if (state.failed) return stmt;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3672);
            	            tableAlias2=tokenIdentifier();

            	            state._fsp--;
            	            if (state.failed) return stmt;

            	            if ( state.backtracking==0 ) {
            	                            		stmt.setTableAlias(i-1, tableAlias2);
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


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:347:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==KEYWORD_WHERE) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:348:17: KEYWORD_WHERE predicate1= abstractPredicate
                    {
                    match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement3782); if (state.failed) return stmt;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement3802);
                    predicate1=abstractPredicate();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt.setWherePredicate(predicate1);
                                    }

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:356:17: ( KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )* )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==KEYWORD_GROUP) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:357:17: KEYWORD_GROUP KEYWORD_BY groupExpr1= abstractExpression ( COMMA groupExpr2= abstractExpression )*
                    {
                    match(input,KEYWORD_GROUP,FOLLOW_KEYWORD_GROUP_in_selectStatement3909); if (state.failed) return stmt;

                    match(input,KEYWORD_BY,FOLLOW_KEYWORD_BY_in_selectStatement3911); if (state.failed) return stmt;

                    pushFollow(FOLLOW_abstractExpression_in_selectStatement3931);
                    groupExpr1=abstractExpression();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt.addGroupExpression(groupExpr1);
                                    	++i;
                                    }

                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:363:17: ( COMMA groupExpr2= abstractExpression )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==COMMA) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:364:17: COMMA groupExpr2= abstractExpression
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement3985); if (state.failed) return stmt;

                    	    pushFollow(FOLLOW_abstractExpression_in_selectStatement4005);
                    	    groupExpr2=abstractExpression();

                    	    state._fsp--;
                    	    if (state.failed) return stmt;

                    	    if ( state.backtracking==0 ) {
                    	                    	stmt.addSelExpression(groupExpr2);
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


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:374:17: ( KEYWORD_HAVING havingPred= abstractPredicate )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==KEYWORD_HAVING) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:375:17: KEYWORD_HAVING havingPred= abstractPredicate
                    {
                    match(input,KEYWORD_HAVING,FOLLOW_KEYWORD_HAVING_in_selectStatement4131); if (state.failed) return stmt;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement4151);
                    havingPred=abstractPredicate();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt.setHavingPredicate(havingPred);
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
            if ( state.backtracking>0 ) { memoize(input, 9, selectStatement_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "selectStatement"



    // $ANTLR start "abstractPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:385:1: abstractPredicate returns [AbstractPredicate predicate] : predicate1= complexPredicateOr ;
    public final AbstractPredicate abstractPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int abstractPredicate_StartIndex = input.index();

        ComplexPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:386:2: (predicate1= complexPredicateOr )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:387:3: predicate1= complexPredicateOr
            {
            pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate4213);
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
            if ( state.backtracking>0 ) { memoize(input, 10, abstractPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "abstractPredicate"



    // $ANTLR start "complexPredicateOr"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:392:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
    public final ComplexPredicate complexPredicateOr() throws RecognitionException {
        ComplexPredicate predicateOr = null;

        int complexPredicateOr_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return predicateOr; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:396:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:397:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:397:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:398:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
            {
            pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr4251);
            predicate1=complexPredicateAnd();

            state._fsp--;
            if (state.failed) return predicateOr;

            if ( state.backtracking==0 ) {
            			predicateOr.setPredicate1(predicate1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:401:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==KEYWORD_OR) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:402:4: KEYWORD_OR predicate2= complexPredicateAnd
            	    {
            	    match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr4262); if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addOr();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr4270);
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
            if ( state.backtracking>0 ) { memoize(input, 11, complexPredicateOr_StartIndex); }

        }
        return predicateOr;
    }
    // $ANTLR end "complexPredicateOr"



    // $ANTLR start "complexPredicateAnd"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:412:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
    public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
        ComplexPredicate predicateAnd = null;

        int complexPredicateAnd_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return predicateAnd; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:416:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:417:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:417:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:418:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
            {
            pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd4309);
            predicate1=complexPredicateNot();

            state._fsp--;
            if (state.failed) return predicateAnd;

            if ( state.backtracking==0 ) {
            			predicateAnd.setPredicate1(predicate1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:421:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==KEYWORD_AND) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:422:4: KEYWORD_AND predicate2= complexPredicateNot
            	    {
            	    match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd4320); if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addAnd();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd4328);
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
            if ( state.backtracking>0 ) { memoize(input, 12, complexPredicateAnd_StartIndex); }

        }
        return predicateAnd;
    }
    // $ANTLR end "complexPredicateAnd"



    // $ANTLR start "complexPredicateNot"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:432:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
    public final ComplexPredicate complexPredicateNot() throws RecognitionException {
        ComplexPredicate predicateNot = null;

        int complexPredicateNot_StartIndex = input.index();

        AbstractPredicate predicate1 =null;



                	predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return predicateNot; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:436:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:437:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:437:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:438:3: ( KEYWORD_NOT )? predicate1= complexPredicate
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:438:3: ( KEYWORD_NOT )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==KEYWORD_NOT) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:439:3: KEYWORD_NOT
                    {
                    match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot4369); if (state.failed) return predicateNot;

                    if ( state.backtracking==0 ) {
                    			predicateNot.negate();
                    		}

                    }
                    break;

            }


            pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot4382);
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
            if ( state.backtracking>0 ) { memoize(input, 13, complexPredicateNot_StartIndex); }

        }
        return predicateNot;
    }
    // $ANTLR end "complexPredicateNot"



    // $ANTLR start "complexPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:449:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
    public final AbstractPredicate complexPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int complexPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;

        SimplePredicate predicate2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:450:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:451:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:451:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==LPAREN) ) {
                int LA20_1 = input.LA(2);

                if ( (synpred26_FunSQL()) ) {
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
            else if ( (LA20_0==FUNCTION_AGGREGATION||LA20_0==IDENTIFIER||(LA20_0 >= LITERAL_DATE && LA20_0 <= LITERAL_STRING)||LA20_0==MINUS||LA20_0==PLUS||LA20_0==QUOTE_DOUBLE) ) {
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:452:3: predicate1= parenPredicate
                    {
                    pushFollow(FOLLOW_parenPredicate_in_complexPredicate4409);
                    predicate1=parenPredicate();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate1;
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:457:3: predicate2= simplePredicate
                    {
                    pushFollow(FOLLOW_simplePredicate_in_complexPredicate4424);
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
            if ( state.backtracking>0 ) { memoize(input, 14, complexPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "complexPredicate"



    // $ANTLR start "parenPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:464:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
    public final AbstractPredicate parenPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int parenPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:465:2: ( LPAREN predicate1= abstractPredicate RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:466:3: LPAREN predicate1= abstractPredicate RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate4449); if (state.failed) return predicate;

            pushFollow(FOLLOW_abstractPredicate_in_parenPredicate4456);
            predicate1=abstractPredicate();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
            			predicate = predicate1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate4462); if (state.failed) return predicate;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, parenPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "parenPredicate"



    // $ANTLR start "simplePredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:473:1: simplePredicate returns [SimplePredicate predicate] : (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) ;
    public final SimplePredicate simplePredicate() throws RecognitionException {
        SimplePredicate predicate = null;

        int simplePredicate_StartIndex = input.index();

        AbstractExpression expr1 =null;

        FunSQLParser.tokenCompOperator_return comp =null;

        AbstractExpression expr2 =null;



                	predicate = new SimplePredicate();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:477:9: ( (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:478:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:478:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:479:3: expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression
            {
            pushFollow(FOLLOW_abstractExpression_in_simplePredicate4504);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setExpr1(expr1);
                            }

            pushFollow(FOLLOW_tokenCompOperator_in_simplePredicate4559);
            comp=tokenCompOperator();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setComp(EnumCompOperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
                            }

            pushFollow(FOLLOW_abstractExpression_in_simplePredicate4597);
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
            if ( state.backtracking>0 ) { memoize(input, 16, simplePredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "simplePredicate"



    // $ANTLR start "abstractExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:496:1: abstractExpression returns [AbstractExpression expression] : expression1= complexExpressionAdd ;
    public final AbstractExpression abstractExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int abstractExpression_StartIndex = input.index();

        ComplexExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:497:2: (expression1= complexExpressionAdd )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:498:2: expression1= complexExpressionAdd
            {
            pushFollow(FOLLOW_complexExpressionAdd_in_abstractExpression4651);
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
            if ( state.backtracking>0 ) { memoize(input, 17, abstractExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "abstractExpression"



    // $ANTLR start "complexExpressionAdd"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:503:1: complexExpressionAdd returns [ComplexExpression expression] : (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) ;
    public final ComplexExpression complexExpressionAdd() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionAdd_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenAddOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:507:2: ( (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:508:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:508:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:509:3: expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )*
            {
            pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd4681);
            expression1=complexExpressionMult();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:512:3: (op1= tokenAddOperator expression2= complexExpressionMult )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==MINUS||LA21_0==PLUS) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:513:4: op1= tokenAddOperator expression2= complexExpressionMult
            	    {
            	    pushFollow(FOLLOW_tokenAddOperator_in_complexExpressionAdd4694);
            	    op1=tokenAddOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd4702);
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
            if ( state.backtracking>0 ) { memoize(input, 18, complexExpressionAdd_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionAdd"



    // $ANTLR start "complexExpressionMult"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:523:1: complexExpressionMult returns [ComplexExpression expression] : (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) ;
    public final ComplexExpression complexExpressionMult() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionMult_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenMultOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:527:2: ( (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:528:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:528:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:529:3: expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )*
            {
            pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult4742);
            expression1=complexExpressionSigned();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:532:3: (op1= tokenMultOperator expression2= complexExpressionSigned )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==DIV||LA22_0==MULT) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:533:4: op1= tokenMultOperator expression2= complexExpressionSigned
            	    {
            	    pushFollow(FOLLOW_tokenMultOperator_in_complexExpressionMult4755);
            	    op1=tokenMultOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult4763);
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
            if ( state.backtracking>0 ) { memoize(input, 19, complexExpressionMult_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionMult"



    // $ANTLR start "complexExpressionSigned"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:544:1: complexExpressionSigned returns [ComplexExpression expression] : ( ( MINUS | PLUS )? expression1= complexExpression ) ;
    public final ComplexExpression complexExpressionSigned() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionSigned_StartIndex = input.index();

        AbstractExpression expression1 =null;



                	expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:548:2: ( ( ( MINUS | PLUS )? expression1= complexExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:549:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:549:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:550:3: ( MINUS | PLUS )? expression1= complexExpression
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:550:3: ( MINUS | PLUS )?
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:551:3: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_complexExpressionSigned4805); if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression.negate();
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:555:3: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_complexExpressionSigned4814); if (state.failed) return expression;

                    }
                    break;

            }


            pushFollow(FOLLOW_complexExpression_in_complexExpressionSigned4826);
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
            if ( state.backtracking>0 ) { memoize(input, 20, complexExpressionSigned_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionSigned"



    // $ANTLR start "complexExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:563:1: complexExpression returns [AbstractExpression expression] : (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) ;
    public final AbstractExpression complexExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int complexExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;

        AggregationExpression expression2 =null;

        SimpleExpression expression3 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:564:2: ( (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:565:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:565:2: (expression1= parenExpression |expression2= aggregationExpression |expression3= simpleExpression )
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
            case LITERAL_DATE:
            case LITERAL_DECIMAL:
            case LITERAL_INTEGER:
            case LITERAL_STRING:
            case QUOTE_DOUBLE:
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:566:3: expression1= parenExpression
                    {
                    pushFollow(FOLLOW_parenExpression_in_complexExpression4861);
                    expression1=parenExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression1;
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:571:3: expression2= aggregationExpression
                    {
                    pushFollow(FOLLOW_aggregationExpression_in_complexExpression4876);
                    expression2=aggregationExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression2;
                    		}

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:576:3: expression3= simpleExpression
                    {
                    pushFollow(FOLLOW_simpleExpression_in_complexExpression4891);
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
            if ( state.backtracking>0 ) { memoize(input, 21, complexExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpression"



    // $ANTLR start "parenExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:583:1: parenExpression returns [AbstractExpression expression] : LPAREN expression1= abstractExpression RPAREN ;
    public final AbstractExpression parenExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int parenExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:584:2: ( LPAREN expression1= abstractExpression RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:585:3: LPAREN expression1= abstractExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenExpression4916); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_parenExpression4923);
            expression1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression = expression1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenExpression4929); if (state.failed) return expression;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 22, parenExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "parenExpression"



    // $ANTLR start "aggregationExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:593:1: aggregationExpression returns [AggregationExpression expression] : (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) ;
    public final AggregationExpression aggregationExpression() throws RecognitionException {
        AggregationExpression expression = null;

        int aggregationExpression_StartIndex = input.index();

        Token agg1=null;
        AbstractExpression expr1 =null;



                	expression = new AggregationExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:597:9: ( (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:598:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:598:9: (agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:599:3: agg1= FUNCTION_AGGREGATION LPAREN expr1= abstractExpression RPAREN
            {
            agg1=(Token)match(input,FUNCTION_AGGREGATION,FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression4981); if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setAggregation((agg1!=null?agg1.getText():null));
            		}

            match(input,LPAREN,FOLLOW_LPAREN_in_aggregationExpression4989); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_aggregationExpression4996);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpression(expr1);
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_aggregationExpression5002); if (state.failed) return expression;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 23, aggregationExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "aggregationExpression"



    // $ANTLR start "simpleExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:611:1: simpleExpression returns [SimpleExpression expression] : ( (att1= tokenAttribute |lit1= tokenLiteral ) ) ;
    public final SimpleExpression simpleExpression() throws RecognitionException {
        SimpleExpression expression = null;

        int simpleExpression_StartIndex = input.index();

        TokenAttribute att1 =null;

        TokenLiteral lit1 =null;



                	expression = new SimpleExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:615:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:616:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:616:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:617:3: (att1= tokenAttribute |lit1= tokenLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:617:3: (att1= tokenAttribute |lit1= tokenLiteral )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==IDENTIFIER||LA25_0==QUOTE_DOUBLE) ) {
                alt25=1;
            }
            else if ( ((LA25_0 >= LITERAL_DATE && LA25_0 <= LITERAL_STRING)) ) {
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:618:3: att1= tokenAttribute
                    {
                    pushFollow(FOLLOW_tokenAttribute_in_simpleExpression5077);
                    att1=tokenAttribute();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                                    	expression.setOper(att1);
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:623:17: lit1= tokenLiteral
                    {
                    pushFollow(FOLLOW_tokenLiteral_in_simpleExpression5133);
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
            if ( state.backtracking>0 ) { memoize(input, 24, simpleExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "simpleExpression"



    // $ANTLR start "tokenAttribute"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:631:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenAttribute tokenAttribute() throws RecognitionException {
        TokenAttribute attribute = null;

        int tokenAttribute_StartIndex = input.index();

        TokenIdentifier table1 =null;

        TokenIdentifier id1 =null;



                	attribute = new TokenAttribute();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return attribute; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:635:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:636:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:636:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:637:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:637:17: (table1= tokenIdentifier DOT )?
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:638:17: table1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute5261);
                    table1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return attribute;

                    if ( state.backtracking==0 ) {
                                    	TokenTable table = new TokenTable();
                                    	table.setName(table1);
                                    	attribute.setTable(table);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenAttribute5281); if (state.failed) return attribute;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute5320);
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
            if ( state.backtracking>0 ) { memoize(input, 25, tokenAttribute_StartIndex); }

        }
        return attribute;
    }
    // $ANTLR end "tokenAttribute"



    // $ANTLR start "tokenTable"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:651:1: tokenTable returns [TokenTable table] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenTable tokenTable() throws RecognitionException {
        TokenTable table = null;

        int tokenTable_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	table = new TokenTable();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return table; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:655:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:656:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:656:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:657:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:657:17: (schema1= tokenIdentifier DOT )?
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:658:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable5428);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return table;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	table.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenTable5448); if (state.failed) return table;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenTable5487);
            id1=tokenIdentifier();

            state._fsp--;
            if (state.failed) return table;

            if ( state.backtracking==0 ) {
                            	table.setName(id1);
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
            if ( state.backtracking>0 ) { memoize(input, 26, tokenTable_StartIndex); }

        }
        return table;
    }
    // $ANTLR end "tokenTable"



    // $ANTLR start "tokenSchema"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:670:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
    public final TokenSchema tokenSchema() throws RecognitionException {
        TokenSchema schema = null;

        int tokenSchema_StartIndex = input.index();

        TokenIdentifier tokenIdentifier13 =null;



                	schema = new TokenSchema();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return schema; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:674:9: ( ( tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:675:9: ( tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:675:9: ( tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:676:17: tokenIdentifier
            {
            pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema5567);
            tokenIdentifier13=tokenIdentifier();

            state._fsp--;
            if (state.failed) return schema;

            if ( state.backtracking==0 ) {
                            	schema.setName(tokenIdentifier13);
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
            if ( state.backtracking>0 ) { memoize(input, 27, tokenSchema_StartIndex); }

        }
        return schema;
    }
    // $ANTLR end "tokenSchema"



    // $ANTLR start "tokenFunction"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:682:1: tokenFunction returns [TokenFunction function] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenFunction tokenFunction() throws RecognitionException {
        TokenFunction function = null;

        int tokenFunction_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	function = new TokenFunction();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return function; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:686:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:687:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:687:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:688:33: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:688:33: (schema1= tokenIdentifier DOT )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==IDENTIFIER) ) {
                int LA28_1 = input.LA(2);

                if ( (LA28_1==DOT) ) {
                    alt28=1;
                }
            }
            else if ( (LA28_0==QUOTE_DOUBLE) ) {
                int LA28_2 = input.LA(2);

                if ( (LA28_2==IDENTIFIER) ) {
                    int LA28_5 = input.LA(3);

                    if ( (LA28_5==QUOTE_DOUBLE) ) {
                        int LA28_6 = input.LA(4);

                        if ( (LA28_6==DOT) ) {
                            alt28=1;
                        }
                    }
                }
            }
            switch (alt28) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:689:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction5683);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return function;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	function.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenFunction5703); if (state.failed) return function;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction5742);
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
            if ( state.backtracking>0 ) { memoize(input, 28, tokenFunction_StartIndex); }

        }
        return function;
    }
    // $ANTLR end "tokenFunction"



    // $ANTLR start "tokenVariable"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:701:1: tokenVariable returns [TokenVariable variable] : ( variableText ) ;
    public final TokenVariable tokenVariable() throws RecognitionException {
        TokenVariable variable = null;

        int tokenVariable_StartIndex = input.index();

        String variableText14 =null;



                	variable = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return variable; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:705:9: ( ( variableText ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:706:9: ( variableText )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:706:9: ( variableText )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:707:17: variableText
            {
            pushFollow(FOLLOW_variableText_in_tokenVariable5822);
            variableText14=variableText();

            state._fsp--;
            if (state.failed) return variable;

            if ( state.backtracking==0 ) {
                            variable = new TokenVariable(variableText14);	
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
            if ( state.backtracking>0 ) { memoize(input, 29, tokenVariable_StartIndex); }

        }
        return variable;
    }
    // $ANTLR end "tokenVariable"



    // $ANTLR start "tokenAssignment"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:713:1: tokenAssignment returns [TokenAssignment ass] : ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) ;
    public final TokenAssignment tokenAssignment() throws RecognitionException {
        TokenAssignment ass = null;

        int tokenAssignment_StartIndex = input.index();

        TokenVariable var1 =null;

        SelectStmt selstmt1 =null;

        TokenVariable var2 =null;

        SelectStmt selstmt2 =null;



        	 	ass =new TokenAssignment();
        	 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return ass; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:717:3: ( ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:718:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:718:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==COLON) ) {
                alt29=1;
            }
            else if ( (LA29_0==KEYWORD_VAR) ) {
                alt29=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ass;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }
            switch (alt29) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:719:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:719:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:720:4: COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement
                    {
                    match(input,COLON,FOLLOW_COLON_in_tokenAssignment5889); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment5901);
                    var1=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setReference(true);
                    		 ass.setVar(var1);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment5907); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment5914);
                    selstmt1=selectStatement();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setSelStmt(selstmt1);
                    		 }

                    }


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:731:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:731:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:732:4: KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement
                    {
                    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment5935); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment5944);
                    var2=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {		 
                    		 ass.setReference(false);
                    		 ass.setVar(var2);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment5950); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment5957);
                    selstmt2=selectStatement();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setSelStmt(selstmt2);
                    		 }

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
            if ( state.backtracking>0 ) { memoize(input, 30, tokenAssignment_StartIndex); }

        }
        return ass;
    }
    // $ANTLR end "tokenAssignment"



    // $ANTLR start "tokenIdentifier"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:746:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
    public final TokenIdentifier tokenIdentifier() throws RecognitionException {
        TokenIdentifier identifier = null;

        int tokenIdentifier_StartIndex = input.index();

        String identifierText15 =null;



                	identifier = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return identifier; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:750:9: ( ( identifierText ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:751:9: ( identifierText )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:751:9: ( identifierText )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:752:17: identifierText
            {
            pushFollow(FOLLOW_identifierText_in_tokenIdentifier6032);
            identifierText15=identifierText();

            state._fsp--;
            if (state.failed) return identifier;

            if ( state.backtracking==0 ) {
                            	identifier = new TokenIdentifier(identifierText15);
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
            if ( state.backtracking>0 ) { memoize(input, 31, tokenIdentifier_StartIndex); }

        }
        return identifier;
    }
    // $ANTLR end "tokenIdentifier"



    // $ANTLR start "tokenDataType"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:759:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER ) ;
    public final TokenDataType tokenDataType() throws RecognitionException {
        TokenDataType dataType = null;

        int tokenDataType_StartIndex = input.index();

        Token TYPE_VARCHAR16=null;
        Token TYPE_INTEGER17=null;


                	dataType = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return dataType; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:763:9: ( ( TYPE_VARCHAR | TYPE_INTEGER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:764:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:764:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==TYPE_VARCHAR) ) {
                alt30=1;
            }
            else if ( (LA30_0==TYPE_INTEGER) ) {
                alt30=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return dataType;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;

            }
            switch (alt30) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:765:17: TYPE_VARCHAR
                    {
                    TYPE_VARCHAR16=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType6113); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_VARCHAR16!=null?TYPE_VARCHAR16.getText():null));
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:769:17: TYPE_INTEGER
                    {
                    TYPE_INTEGER17=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType6151); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_INTEGER17!=null?TYPE_INTEGER17.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 32, tokenDataType_StartIndex); }

        }
        return dataType;
    }
    // $ANTLR end "tokenDataType"



    // $ANTLR start "tokenLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:775:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
    public final TokenLiteral tokenLiteral() throws RecognitionException {
        TokenLiteral literal = null;

        int tokenLiteral_StartIndex = input.index();

        TokenIntegerLiteral tokenIntegerLiteral18 =null;

        TokenStringLiteral tokenStringLiteral19 =null;

        TokenDecimalLiteral tokenDecimalLiteral20 =null;

        TokenDateLiteral tokenDateLiteral21 =null;



                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:779:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:780:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:780:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:781:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:781:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            int alt31=4;
            switch ( input.LA(1) ) {
            case LITERAL_INTEGER:
                {
                alt31=1;
                }
                break;
            case LITERAL_STRING:
                {
                alt31=2;
                }
                break;
            case LITERAL_DECIMAL:
                {
                alt31=3;
                }
                break;
            case LITERAL_DATE:
                {
                alt31=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return literal;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }

            switch (alt31) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:782:17: tokenIntegerLiteral
                    {
                    pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral6242);
                    tokenIntegerLiteral18=tokenIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenIntegerLiteral18;
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:786:17: tokenStringLiteral
                    {
                    pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral6280);
                    tokenStringLiteral19=tokenStringLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenStringLiteral19;
                                    }

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:790:17: tokenDecimalLiteral
                    {
                    pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral6318);
                    tokenDecimalLiteral20=tokenDecimalLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenDecimalLiteral20;
                                    }

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:794:17: tokenDateLiteral
                    {
                    pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral6356);
                    tokenDateLiteral21=tokenDateLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenDateLiteral21;
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
            if ( state.backtracking>0 ) { memoize(input, 33, tokenLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenLiteral"



    // $ANTLR start "tokenStringLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:801:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
    public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
        TokenStringLiteral literal = null;

        int tokenStringLiteral_StartIndex = input.index();

        Token lit1=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:805:9: ( (lit1= LITERAL_STRING ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:806:9: (lit1= LITERAL_STRING )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:806:9: (lit1= LITERAL_STRING )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:807:17: lit1= LITERAL_STRING
            {
            lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral6456); if (state.failed) return literal;

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
            if ( state.backtracking>0 ) { memoize(input, 34, tokenStringLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenStringLiteral"



    // $ANTLR start "tokenIntegerLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:813:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
    public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
        TokenIntegerLiteral literal = null;

        int tokenIntegerLiteral_StartIndex = input.index();

        Token LITERAL_INTEGER22=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:817:9: ( ( LITERAL_INTEGER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:818:9: ( LITERAL_INTEGER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:818:9: ( LITERAL_INTEGER )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:819:17: LITERAL_INTEGER
            {
            LITERAL_INTEGER22=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral6536); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenIntegerLiteral((LITERAL_INTEGER22!=null?LITERAL_INTEGER22.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 35, tokenIntegerLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenIntegerLiteral"



    // $ANTLR start "tokenDecimalLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:826:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
    public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
        TokenDecimalLiteral literal = null;

        int tokenDecimalLiteral_StartIndex = input.index();

        Token LITERAL_DECIMAL23=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:830:9: ( ( LITERAL_DECIMAL ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:831:9: ( LITERAL_DECIMAL )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:831:9: ( LITERAL_DECIMAL )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:832:17: LITERAL_DECIMAL
            {
            LITERAL_DECIMAL23=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral6626); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenDecimalLiteral((LITERAL_DECIMAL23!=null?LITERAL_DECIMAL23.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 36, tokenDecimalLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenDecimalLiteral"



    // $ANTLR start "tokenDateLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:838:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( LITERAL_DATE ) ;
    public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
        TokenDateLiteral literal = null;

        int tokenDateLiteral_StartIndex = input.index();

        Token LITERAL_DATE24=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:842:9: ( ( LITERAL_DATE ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:843:9: ( LITERAL_DATE )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:843:9: ( LITERAL_DATE )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:844:17: LITERAL_DATE
            {
            LITERAL_DATE24=(Token)match(input,LITERAL_DATE,FOLLOW_LITERAL_DATE_in_tokenDateLiteral6707); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenDateLiteral((LITERAL_DATE24!=null?LITERAL_DATE24.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 37, tokenDateLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenDateLiteral"



    // $ANTLR start "variableText"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:850:2: variableText returns [String text] : (var1= IDENTIFIER ) ;
    public final String variableText() throws RecognitionException {
        String text = null;

        int variableText_StartIndex = input.index();

        Token var1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return text; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:851:3: ( (var1= IDENTIFIER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:852:3: (var1= IDENTIFIER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:852:3: (var1= IDENTIFIER )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:854:4: var1= IDENTIFIER
            {
            var1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variableText6766); if (state.failed) return text;

            if ( state.backtracking==0 ) {
             		text = (var1!=null?var1.getText():null);
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
            if ( state.backtracking>0 ) { memoize(input, 38, variableText_StartIndex); }

        }
        return text;
    }
    // $ANTLR end "variableText"



    // $ANTLR start "identifierText"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:860:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) ;
    public final String identifierText() throws RecognitionException {
        String text = null;

        int identifierText_StartIndex = input.index();

        Token id1=null;
        Token id2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return text; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:861:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:862:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:862:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==IDENTIFIER) ) {
                alt32=1;
            }
            else if ( (LA32_0==QUOTE_DOUBLE) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return text;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }
            switch (alt32) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:863:4: (id1= IDENTIFIER )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:863:4: (id1= IDENTIFIER )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:864:4: id1= IDENTIFIER
                    {
                    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText6818); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id1!=null?id1.getText():null).toUpperCase();
                                    }

                    }


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:869:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:869:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:870:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText6874); if (state.failed) return text;

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText6890); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id2!=null?id2.getText():null);
                                    }

                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText6910); if (state.failed) return text;

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
            if ( state.backtracking>0 ) { memoize(input, 39, identifierText_StartIndex); }

        }
        return text;
    }
    // $ANTLR end "identifierText"


    public static class tokenAddOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenAddOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:879:1: tokenAddOperator : ( PLUS | MINUS ) ;
    public final FunSQLParser.tokenAddOperator_return tokenAddOperator() throws RecognitionException {
        FunSQLParser.tokenAddOperator_return retval = new FunSQLParser.tokenAddOperator_return();
        retval.start = input.LT(1);

        int tokenAddOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:880:5: ( ( PLUS | MINUS ) )
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
            if ( state.backtracking>0 ) { memoize(input, 40, tokenAddOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenAddOperator"


    public static class tokenMultOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenMultOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:887:1: tokenMultOperator : ( MULT | DIV ) ;
    public final FunSQLParser.tokenMultOperator_return tokenMultOperator() throws RecognitionException {
        FunSQLParser.tokenMultOperator_return retval = new FunSQLParser.tokenMultOperator_return();
        retval.start = input.LT(1);

        int tokenMultOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:888:5: ( ( MULT | DIV ) )
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
            if ( state.backtracking>0 ) { memoize(input, 41, tokenMultOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenMultOperator"


    public static class tokenCompOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenCompOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:895:1: tokenCompOperator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) ;
    public final FunSQLParser.tokenCompOperator_return tokenCompOperator() throws RecognitionException {
        FunSQLParser.tokenCompOperator_return retval = new FunSQLParser.tokenCompOperator_return();
        retval.start = input.LT(1);

        int tokenCompOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:896:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) )
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
            if ( state.backtracking>0 ) { memoize(input, 42, tokenCompOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenCompOperator"

    // $ANTLR start synpred26_FunSQL
    public final void synpred26_FunSQL_fragment() throws RecognitionException {
        AbstractPredicate predicate1 =null;


        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:452:3: (predicate1= parenPredicate )
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:452:3: predicate1= parenPredicate
        {
        pushFollow(FOLLOW_parenPredicate_in_synpred26_FunSQL4409);
        predicate1=parenPredicate();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred26_FunSQL

    // Delegated rules

    public final boolean synpred26_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred26_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_createSchemaStatement_in_statement1078 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_dropSchemaStatement_in_statement1133 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_createConnectionStatement_in_statement1188 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_dropConnectionStatement_in_statement1243 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_createTableStatement_in_statement1298 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_dropTableStatement_in_statement1353 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_createFunctionStatement_in_statement1408 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_selectStatement_in_statement1463 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
    public static final BitSet FOLLOW_SEMI_in_statement1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1595 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1613 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1710 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1728 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1825 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1843 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement1861 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement1881 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1901 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement1921 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1941 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1961 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1981 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement2001 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2108 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2126 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2223 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2241 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2261 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement2281 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2301 = new BitSet(new long[]{0x0000000000000000L,0x0000030000000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2339 = new BitSet(new long[]{0x0000000000002000L,0x0000000200000000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2393 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2413 = new BitSet(new long[]{0x0000000000000000L,0x0000030000000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2451 = new BitSet(new long[]{0x0000000000002000L,0x0000000200000000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement2506 = new BitSet(new long[]{0x0011000000000000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2542 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2562 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2601 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2603 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement2702 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement2720 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenTable_in_dropTableStatement2740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement2806 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenFunction_in_createFunctionStatement2826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LPAREN_in_createFunctionStatement2845 = new BitSet(new long[]{0x0200000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement2881 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement2901 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement2906 = new BitSet(new long[]{0x0200000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_RPAREN_in_createFunctionStatement2915 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement2919 = new BitSet(new long[]{0x0000800000001000L,0x0000000000000004L});
    public static final BitSet FOLLOW_tokenAssignment_in_createFunctionStatement2929 = new BitSet(new long[]{0x0000800000001000L,0x0000000000000004L});
    public static final BitSet FOLLOW_KEYWORD_END_in_createFunctionStatement2939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement3030 = new BitSet(new long[]{0x0000000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement3050 = new BitSet(new long[]{0x0001004000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3105 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3126 = new BitSet(new long[]{0x0001000000002000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement3200 = new BitSet(new long[]{0x0000000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement3220 = new BitSet(new long[]{0x0001004000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3275 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3296 = new BitSet(new long[]{0x0001000000002000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement3405 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement3425 = new BitSet(new long[]{0x000C004000002002L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3481 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3502 = new BitSet(new long[]{0x000C000000002002L,0x0000000000000008L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement3576 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement3596 = new BitSet(new long[]{0x000C004000002002L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3651 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3672 = new BitSet(new long[]{0x000C000000002002L,0x0000000000000008L});
    public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement3782 = new BitSet(new long[]{0x0080000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement3802 = new BitSet(new long[]{0x000C000000000002L});
    public static final BitSet FOLLOW_KEYWORD_GROUP_in_selectStatement3909 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_BY_in_selectStatement3911 = new BitSet(new long[]{0x0000000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement3931 = new BitSet(new long[]{0x0008000000002002L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement3985 = new BitSet(new long[]{0x0000000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement4005 = new BitSet(new long[]{0x0008000000002002L});
    public static final BitSet FOLLOW_KEYWORD_HAVING_in_selectStatement4131 = new BitSet(new long[]{0x0080000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement4151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate4213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr4251 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr4262 = new BitSet(new long[]{0x0080000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr4270 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd4309 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd4320 = new BitSet(new long[]{0x0080000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd4328 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot4369 = new BitSet(new long[]{0x0000000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot4382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_complexPredicate4409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicate_in_complexPredicate4424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenPredicate4449 = new BitSet(new long[]{0x0080000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate4456 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenPredicate4462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate4504 = new BitSet(new long[]{0x0000000018400000L,0x00000000001801C0L});
    public static final BitSet FOLLOW_tokenCompOperator_in_simplePredicate4559 = new BitSet(new long[]{0x0000000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate4597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionAdd_in_abstractExpression4651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd4681 = new BitSet(new long[]{0x0000000000000002L,0x0000000001008000L});
    public static final BitSet FOLLOW_tokenAddOperator_in_complexExpressionAdd4694 = new BitSet(new long[]{0x0000000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd4702 = new BitSet(new long[]{0x0000000000000002L,0x0000000001008000L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult4742 = new BitSet(new long[]{0x0000000000020002L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenMultOperator_in_complexExpressionMult4755 = new BitSet(new long[]{0x0000000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult4763 = new BitSet(new long[]{0x0000000000020002L,0x0000000000020000L});
    public static final BitSet FOLLOW_MINUS_in_complexExpressionSigned4805 = new BitSet(new long[]{0x0000000202000000L,0x0000000010003E00L});
    public static final BitSet FOLLOW_PLUS_in_complexExpressionSigned4814 = new BitSet(new long[]{0x0000000202000000L,0x0000000010003E00L});
    public static final BitSet FOLLOW_complexExpression_in_complexExpressionSigned4826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenExpression_in_complexExpression4861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggregationExpression_in_complexExpression4876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleExpression_in_complexExpression4891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenExpression4916 = new BitSet(new long[]{0x0000000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_abstractExpression_in_parenExpression4923 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_RPAREN_in_parenExpression4929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_AGGREGATION_in_aggregationExpression4981 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_LPAREN_in_aggregationExpression4989 = new BitSet(new long[]{0x0000000202000000L,0x000000001100BE00L});
    public static final BitSet FOLLOW_abstractExpression_in_aggregationExpression4996 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_RPAREN_in_aggregationExpression5002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simpleExpression5077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenLiteral_in_simpleExpression5133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute5261 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenAttribute5281 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute5320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable5428 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenTable5448 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable5487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema5567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction5683 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenFunction5703 = new BitSet(new long[]{0x0000000200000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction5742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableText_in_tokenVariable5822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenAssignment5889 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment5901 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment5907 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment5914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment5935 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment5944 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment5950 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment5957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierText_in_tokenIdentifier6032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType6113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType6151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral6242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral6280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral6318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral6356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral6456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral6536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral6626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DATE_in_tokenDateLiteral6707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variableText6766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText6818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText6874 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText6890 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText6910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_synpred26_FunSQL4409 = new BitSet(new long[]{0x0000000000000002L});

}