// $ANTLR 3.4 C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g 2012-10-01 17:47:07
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", "AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", "D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", "F", "G", "GREATER_EQUAL1", "GREATER_EQUAL2", "GREATER_THAN", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", "KEYWORD_AND", "KEYWORD_BEGIN", "KEYWORD_CONNECTION", "KEYWORD_CREATE", "KEYWORD_DROP", "KEYWORD_END", "KEYWORD_FROM", "KEYWORD_FUNCTION", "KEYWORD_IN", "KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_OUT", "KEYWORD_PASSWD", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_VAR", "KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", "LESS_THAN", "LITERAL_DATE", "LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", "MINUS", "MOD", "MULT", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", "QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", "SHIFT_RIGHT", "T", "TILDE", "TYPE_INTEGER", "TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
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
    public static final int G=25;
    public static final int GREATER_EQUAL1=26;
    public static final int GREATER_EQUAL2=27;
    public static final int GREATER_THAN=28;
    public static final int H=29;
    public static final int HAT=30;
    public static final int I=31;
    public static final int IDENTIFIER=32;
    public static final int IGNORE_CHAR=33;
    public static final int J=34;
    public static final int K=35;
    public static final int KEYWORD_AND=36;
    public static final int KEYWORD_BEGIN=37;
    public static final int KEYWORD_CONNECTION=38;
    public static final int KEYWORD_CREATE=39;
    public static final int KEYWORD_DROP=40;
    public static final int KEYWORD_END=41;
    public static final int KEYWORD_FROM=42;
    public static final int KEYWORD_FUNCTION=43;
    public static final int KEYWORD_IN=44;
    public static final int KEYWORD_NOT=45;
    public static final int KEYWORD_OR=46;
    public static final int KEYWORD_OUT=47;
    public static final int KEYWORD_PASSWD=48;
    public static final int KEYWORD_SCHEMA=49;
    public static final int KEYWORD_SELECT=50;
    public static final int KEYWORD_STORE=51;
    public static final int KEYWORD_TABLE=52;
    public static final int KEYWORD_URL=53;
    public static final int KEYWORD_USER=54;
    public static final int KEYWORD_VAR=55;
    public static final int KEYWORD_WHERE=56;
    public static final int L=57;
    public static final int LBRACKET=58;
    public static final int LESS_EQUAL1=59;
    public static final int LESS_EQUAL2=60;
    public static final int LESS_THAN=61;
    public static final int LITERAL_DATE=62;
    public static final int LITERAL_DECIMAL=63;
    public static final int LITERAL_INTEGER=64;
    public static final int LITERAL_STRING=65;
    public static final int LPAREN=66;
    public static final int M=67;
    public static final int MINUS=68;
    public static final int MOD=69;
    public static final int MULT=70;
    public static final int N=71;
    public static final int NOT_EQUAL1=72;
    public static final int NOT_EQUAL2=73;
    public static final int O=74;
    public static final int P=75;
    public static final int PIPE=76;
    public static final int PLUS=77;
    public static final int Q=78;
    public static final int QUESTION=79;
    public static final int QUOTED_STRING=80;
    public static final int QUOTE_DOUBLE=81;
    public static final int QUOTE_SINGLE=82;
    public static final int QUOTE_TRIPLE=83;
    public static final int R=84;
    public static final int RBRACKET=85;
    public static final int RPAREN=86;
    public static final int S=87;
    public static final int SEMI=88;
    public static final int SHIFT_LEFT=89;
    public static final int SHIFT_RIGHT=90;
    public static final int T=91;
    public static final int TILDE=92;
    public static final int TYPE_INTEGER=93;
    public static final int TYPE_VARCHAR=94;
    public static final int U=95;
    public static final int UNDERSCORE=96;
    public static final int V=97;
    public static final int W=98;
    public static final int WS=99;
    public static final int X=100;
    public static final int Y=101;
    public static final int Z=102;

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
        this.state.ruleMemo = new HashMap[84+1];
         

    }

    public String[] getTokenNames() { return FunSQLParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g"; }



    // $ANTLR start "statement"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:68:1: statement returns [AbstractServerStmt stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement ) ( SEMI )? ) ;
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

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:72:9: ( ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement ) ( SEMI )? ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:73:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement ) ( SEMI )? )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:73:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement ) ( SEMI )? )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:74:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement ) ( SEMI )?
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:74:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | createFunctionStatement | selectStatement )
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:75:17: createSchemaStatement
                    {
                    pushFollow(FOLLOW_createSchemaStatement_in_statement1079);
                    createSchemaStatement1=createSchemaStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = createSchemaStatement1;
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:80:17: dropSchemaStatement
                    {
                    pushFollow(FOLLOW_dropSchemaStatement_in_statement1134);
                    dropSchemaStatement2=dropSchemaStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = dropSchemaStatement2;
                                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:85:17: createConnectionStatement
                    {
                    pushFollow(FOLLOW_createConnectionStatement_in_statement1189);
                    createConnectionStatement3=createConnectionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = createConnectionStatement3;
                                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:90:17: dropConnectionStatement
                    {
                    pushFollow(FOLLOW_dropConnectionStatement_in_statement1244);
                    dropConnectionStatement4=dropConnectionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = dropConnectionStatement4;
                                    }

                    }
                    break;
                case 5 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:95:17: createTableStatement
                    {
                    pushFollow(FOLLOW_createTableStatement_in_statement1299);
                    createTableStatement5=createTableStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = createTableStatement5;
                                    }

                    }
                    break;
                case 6 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:100:17: dropTableStatement
                    {
                    pushFollow(FOLLOW_dropTableStatement_in_statement1354);
                    dropTableStatement6=dropTableStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = dropTableStatement6;
                                    }

                    }
                    break;
                case 7 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:105:17: createFunctionStatement
                    {
                    pushFollow(FOLLOW_createFunctionStatement_in_statement1409);
                    createFunctionStatement7=createFunctionStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = createFunctionStatement7;
                                    }

                    }
                    break;
                case 8 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:110:17: selectStatement
                    {
                    pushFollow(FOLLOW_selectStatement_in_statement1464);
                    selectStatement8=selectStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = selectStatement8;
                                    }

                    }
                    break;

            }


            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:115:17: ( SEMI )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==SEMI) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:115:17: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_statement1518); if (state.failed) return stmt;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:119:1: createSchemaStatement returns [CreateSchemaStmt stmt] : ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) ;
    public final CreateSchemaStmt createSchemaStatement() throws RecognitionException {
        CreateSchemaStmt stmt = null;

        int createSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema9 =null;



                	stmt = new CreateSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return stmt; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:123:9: ( ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:124:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:124:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:125:17: KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1596); if (state.failed) return stmt;

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1614); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenSchema_in_createSchemaStatement1632);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:133:1: dropSchemaStatement returns [DropSchemaStmt stmt] : ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) ;
    public final DropSchemaStmt dropSchemaStatement() throws RecognitionException {
        DropSchemaStmt stmt = null;

        int dropSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema10 =null;



                	stmt = new DropSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return stmt; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:137:9: ( ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:138:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:138:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:139:17: KEYWORD_DROP KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1711); if (state.failed) return stmt;

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1729); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenSchema_in_dropSchemaStatement1747);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:147:1: createConnectionStatement returns [CreateConnectionStmt stmt] : ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) ;
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

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:151:9: ( ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:152:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:152:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:153:17: KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1826); if (state.failed) return stmt;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1844); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenIdentifier_in_createConnectionStatement1862);
            tokenIdentifier11=tokenIdentifier();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setConnection(tokenIdentifier11);
                            }

            match(input,KEYWORD_URL,FOLLOW_KEYWORD_URL_in_createConnectionStatement1882); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1902);
            litURL=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setURL(litURL);
                            }

            match(input,KEYWORD_USER,FOLLOW_KEYWORD_USER_in_createConnectionStatement1922); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1942);
            litUser=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setUser(litUser);
                            }

            match(input,KEYWORD_PASSWD,FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1962); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1982);
            litPasswd=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setPasswd(litPasswd);
                            }

            match(input,KEYWORD_STORE,FOLLOW_KEYWORD_STORE_in_createConnectionStatement2002); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement2022);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:177:1: dropConnectionStatement returns [DropConnectionStmt stmt] : ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) ;
    public final DropConnectionStmt dropConnectionStatement() throws RecognitionException {
        DropConnectionStmt stmt = null;

        int dropConnectionStatement_StartIndex = input.index();

        TokenIdentifier tokenIdentifier12 =null;



                	stmt = new DropConnectionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return stmt; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:181:9: ( ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:182:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:182:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:183:17: KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2109); if (state.failed) return stmt;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2127); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenIdentifier_in_dropConnectionStatement2145);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:191:1: createTableStatement returns [CreateTableStmt stmt] : ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) ;
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

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:195:9: ( ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:196:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:196:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:197:17: KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2224); if (state.failed) return stmt;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2242); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenTable_in_createTableStatement2262);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setTable(table1);
                            	stmt.setSourceTable(table1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2282); if (state.failed) return stmt;

            pushFollow(FOLLOW_identifierText_in_createTableStatement2302);
            att1=identifierText();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addAttribute(att1);
                            }

            pushFollow(FOLLOW_tokenDataType_in_createTableStatement2340);
            dataType1=tokenDataType();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addType(dataType1);
                            }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:212:17: ( COMMA att2= identifierText dataType2= tokenDataType )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:213:17: COMMA att2= identifierText dataType2= tokenDataType
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2394); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_identifierText_in_createTableStatement2414);
            	    att2=identifierText();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addAttribute(att2);
            	                    }

            	    pushFollow(FOLLOW_tokenDataType_in_createTableStatement2452);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2507); if (state.failed) return stmt;

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:224:17: ( KEYWORD_FROM table2= tokenTable )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_FROM) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:225:17: KEYWORD_FROM table2= tokenTable
                    {
                    match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_createTableStatement2543); if (state.failed) return stmt;

                    pushFollow(FOLLOW_tokenTable_in_createTableStatement2563);
                    table2=tokenTable();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt.setSourceTable(table2);
                                    }

                    }
                    break;

            }


            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2602); if (state.failed) return stmt;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2604); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2624);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:237:1: dropTableStatement returns [DropTableStmt stmt] : ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) ;
    public final DropTableStmt dropTableStatement() throws RecognitionException {
        DropTableStmt stmt = null;

        int dropTableStatement_StartIndex = input.index();

        TokenTable table1 =null;



                	stmt = new DropTableStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return stmt; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:241:9: ( ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:242:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:242:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:243:17: KEYWORD_DROP KEYWORD_TABLE table1= tokenTable
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement2703); if (state.failed) return stmt;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement2721); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenTable_in_dropTableStatement2741);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:251:1: createFunctionStatement returns [CreateFunctionStmt stmt] : ( KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END ) ;
    public final CreateFunctionStmt createFunctionStatement() throws RecognitionException {
        CreateFunctionStmt stmt = null;

        int createFunctionStatement_StartIndex = input.index();

        TokenFunction function1 =null;

        TokenVariable var1 =null;

        TokenAssignment ass1 =null;



                	stmt = new CreateFunctionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return stmt; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:255:9: ( ( KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:256:9: ( KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:256:9: ( KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:257:17: KEYWORD_FUNCTION function1= tokenFunction LPAREN ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )* RPAREN KEYWORD_BEGIN (ass1= tokenAssignment )* KEYWORD_END
            {
            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement2807); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenFunction_in_createFunctionStatement2827);
            function1=tokenFunction();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setFunction(function1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createFunctionStatement2846); if (state.failed) return stmt;

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:262:17: ( KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==KEYWORD_OUT) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:263:17: KEYWORD_OUT var1= tokenVariable KEYWORD_TABLE
            	    {
            	    match(input,KEYWORD_OUT,FOLLOW_KEYWORD_OUT_in_createFunctionStatement2882); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_tokenVariable_in_createFunctionStatement2902);
            	    var1=tokenVariable();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                   		stmt.addParam(var1);
            	    		}

            	    match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createFunctionStatement2907); if (state.failed) return stmt;

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createFunctionStatement2916); if (state.failed) return stmt;

            match(input,KEYWORD_BEGIN,FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement2920); if (state.failed) return stmt;

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:271:3: (ass1= tokenAssignment )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COLON||LA6_0==KEYWORD_VAR) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:272:3: ass1= tokenAssignment
            	    {
            	    pushFollow(FOLLOW_tokenAssignment_in_createFunctionStatement2930);
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


            match(input,KEYWORD_END,FOLLOW_KEYWORD_END_in_createFunctionStatement2940); if (state.failed) return stmt;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:280:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT att1= tokenAttribute ( COMMA att2= tokenAttribute )* KEYWORD_FROM table1= tokenTable ( COMMA table2= tokenTable )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ) ;
    public final SelectStmt selectStatement() throws RecognitionException {
        SelectStmt stmt = null;

        int selectStatement_StartIndex = input.index();

        TokenAttribute att1 =null;

        TokenAttribute att2 =null;

        TokenTable table1 =null;

        TokenTable table2 =null;

        AbstractPredicate predicate1 =null;



                	stmt = new SelectStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return stmt; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:284:9: ( ( KEYWORD_SELECT att1= tokenAttribute ( COMMA att2= tokenAttribute )* KEYWORD_FROM table1= tokenTable ( COMMA table2= tokenTable )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:285:9: ( KEYWORD_SELECT att1= tokenAttribute ( COMMA att2= tokenAttribute )* KEYWORD_FROM table1= tokenTable ( COMMA table2= tokenTable )* ( KEYWORD_WHERE predicate1= abstractPredicate )? )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:285:9: ( KEYWORD_SELECT att1= tokenAttribute ( COMMA att2= tokenAttribute )* KEYWORD_FROM table1= tokenTable ( COMMA table2= tokenTable )* ( KEYWORD_WHERE predicate1= abstractPredicate )? )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:286:17: KEYWORD_SELECT att1= tokenAttribute ( COMMA att2= tokenAttribute )* KEYWORD_FROM table1= tokenTable ( COMMA table2= tokenTable )* ( KEYWORD_WHERE predicate1= abstractPredicate )?
            {
            match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement3021); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenAttribute_in_selectStatement3041);
            att1=tokenAttribute();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addAttribute(att1);
                            }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:291:17: ( COMMA att2= tokenAttribute )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==COMMA) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:292:17: COMMA att2= tokenAttribute
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement3095); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_tokenAttribute_in_selectStatement3115);
            	    att2=tokenAttribute();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addAttribute(att2);
            	                    }

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement3170); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenTable_in_selectStatement3190);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addTable(table1);
                            }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:303:17: ( COMMA table2= tokenTable )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==COMMA) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:304:17: COMMA table2= tokenTable
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement3245); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_tokenTable_in_selectStatement3265);
            	    table2=tokenTable();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addTable(table2);
            	                    }

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:310:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==KEYWORD_WHERE) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:311:17: KEYWORD_WHERE predicate1= abstractPredicate
                    {
                    match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement3338); if (state.failed) return stmt;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement3358);
                    predicate1=abstractPredicate();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt.setPredicate(predicate1);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:322:1: abstractPredicate returns [AbstractPredicate predicate] : predicate1= complexPredicateOr ;
    public final AbstractPredicate abstractPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int abstractPredicate_StartIndex = input.index();

        ComplexPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return predicate; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:323:2: (predicate1= complexPredicateOr )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:323:4: predicate1= complexPredicateOr
            {
            pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate3434);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:328:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
    public final ComplexPredicate complexPredicateOr() throws RecognitionException {
        ComplexPredicate predicateOr = null;

        int complexPredicateOr_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return predicateOr; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:332:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:333:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:333:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:334:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
            {
            pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr3472);
            predicate1=complexPredicateAnd();

            state._fsp--;
            if (state.failed) return predicateOr;

            if ( state.backtracking==0 ) {
            			predicateOr.setPredicate1(predicate1);
            		}

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:337:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==KEYWORD_OR) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:338:4: KEYWORD_OR predicate2= complexPredicateAnd
            	    {
            	    match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr3483); if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addOr();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr3491);
            	    predicate2=complexPredicateAnd();

            	    state._fsp--;
            	    if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop10;
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:348:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
    public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
        ComplexPredicate predicateAnd = null;

        int complexPredicateAnd_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return predicateAnd; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:352:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:353:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:353:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:354:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
            {
            pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd3531);
            predicate1=complexPredicateNot();

            state._fsp--;
            if (state.failed) return predicateAnd;

            if ( state.backtracking==0 ) {
            			predicateAnd.setPredicate1(predicate1);
            		}

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:357:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==KEYWORD_AND) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:358:4: KEYWORD_AND predicate2= complexPredicateNot
            	    {
            	    match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd3542); if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addAnd();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd3550);
            	    predicate2=complexPredicateNot();

            	    state._fsp--;
            	    if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop11;
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:368:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
    public final ComplexPredicate complexPredicateNot() throws RecognitionException {
        ComplexPredicate predicateNot = null;

        int complexPredicateNot_StartIndex = input.index();

        AbstractPredicate predicate1 =null;



                	predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return predicateNot; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:372:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:373:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:373:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:374:3: ( KEYWORD_NOT )? predicate1= complexPredicate
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:374:3: ( KEYWORD_NOT )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==KEYWORD_NOT) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:375:3: KEYWORD_NOT
                    {
                    match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot3591); if (state.failed) return predicateNot;

                    if ( state.backtracking==0 ) {
                    			predicateNot.negate();
                    		}

                    }
                    break;

            }


            pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot3604);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:385:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
    public final AbstractPredicate complexPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int complexPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;

        SimplePredicate predicate2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return predicate; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:386:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:387:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:387:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==LPAREN) ) {
                int LA13_1 = input.LA(2);

                if ( (synpred19_FunSQL()) ) {
                    alt13=1;
                }
                else if ( (true) ) {
                    alt13=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA13_0==IDENTIFIER||(LA13_0 >= LITERAL_DATE && LA13_0 <= LITERAL_STRING)||LA13_0==MINUS||LA13_0==PLUS||LA13_0==QUOTE_DOUBLE) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return predicate;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:388:3: predicate1= parenPredicate
                    {
                    pushFollow(FOLLOW_parenPredicate_in_complexPredicate3631);
                    predicate1=parenPredicate();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate1;
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:393:3: predicate2= simplePredicate
                    {
                    pushFollow(FOLLOW_simplePredicate_in_complexPredicate3646);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:400:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
    public final AbstractPredicate parenPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int parenPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return predicate; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:401:2: ( LPAREN predicate1= abstractPredicate RPAREN )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:402:3: LPAREN predicate1= abstractPredicate RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate3671); if (state.failed) return predicate;

            pushFollow(FOLLOW_abstractPredicate_in_parenPredicate3678);
            predicate1=abstractPredicate();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
            			predicate = predicate1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate3684); if (state.failed) return predicate;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:409:1: simplePredicate returns [SimplePredicate predicate] : (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) ;
    public final SimplePredicate simplePredicate() throws RecognitionException {
        SimplePredicate predicate = null;

        int simplePredicate_StartIndex = input.index();

        AbstractExpression expr1 =null;

        FunSQLParser.tokenCompOperator_return comp =null;

        AbstractExpression expr2 =null;



                	predicate = new SimplePredicate();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return predicate; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:413:9: ( (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:414:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:414:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:415:3: expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression
            {
            pushFollow(FOLLOW_abstractExpression_in_simplePredicate3726);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setExpr1(expr1);
                            }

            pushFollow(FOLLOW_tokenCompOperator_in_simplePredicate3781);
            comp=tokenCompOperator();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setComp(EnumCompOperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
                            }

            pushFollow(FOLLOW_abstractExpression_in_simplePredicate3819);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:432:1: abstractExpression returns [AbstractExpression expression] : expression1= complexExpressionAdd ;
    public final AbstractExpression abstractExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int abstractExpression_StartIndex = input.index();

        ComplexExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:433:2: (expression1= complexExpressionAdd )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:433:4: expression1= complexExpressionAdd
            {
            pushFollow(FOLLOW_complexExpressionAdd_in_abstractExpression3872);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:438:1: complexExpressionAdd returns [ComplexExpression expression] : (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) ;
    public final ComplexExpression complexExpressionAdd() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionAdd_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenAddOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:442:2: ( (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:443:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:443:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:444:3: expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )*
            {
            pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd3902);
            expression1=complexExpressionMult();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:447:3: (op1= tokenAddOperator expression2= complexExpressionMult )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==MINUS||LA14_0==PLUS) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:448:4: op1= tokenAddOperator expression2= complexExpressionMult
            	    {
            	    pushFollow(FOLLOW_tokenAddOperator_in_complexExpressionAdd3915);
            	    op1=tokenAddOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd3923);
            	    expression2=complexExpressionMult();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop14;
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:458:1: complexExpressionMult returns [ComplexExpression expression] : (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) ;
    public final ComplexExpression complexExpressionMult() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionMult_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenMultOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:462:2: ( (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:463:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:463:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:464:3: expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )*
            {
            pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult3963);
            expression1=complexExpressionSigned();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:467:3: (op1= tokenMultOperator expression2= complexExpressionSigned )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==DIV||LA15_0==MULT) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:468:4: op1= tokenMultOperator expression2= complexExpressionSigned
            	    {
            	    pushFollow(FOLLOW_tokenMultOperator_in_complexExpressionMult3976);
            	    op1=tokenMultOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult3984);
            	    expression2=complexExpressionSigned();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop15;
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:479:1: complexExpressionSigned returns [ComplexExpression expression] : ( ( MINUS | PLUS )? expression1= complexExpression ) ;
    public final ComplexExpression complexExpressionSigned() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionSigned_StartIndex = input.index();

        AbstractExpression expression1 =null;



                	expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:483:2: ( ( ( MINUS | PLUS )? expression1= complexExpression ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:484:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:484:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:485:3: ( MINUS | PLUS )? expression1= complexExpression
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:485:3: ( MINUS | PLUS )?
            int alt16=3;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==MINUS) ) {
                alt16=1;
            }
            else if ( (LA16_0==PLUS) ) {
                alt16=2;
            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:486:3: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_complexExpressionSigned4026); if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression.negate();
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:490:3: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_complexExpressionSigned4035); if (state.failed) return expression;

                    }
                    break;

            }


            pushFollow(FOLLOW_complexExpression_in_complexExpressionSigned4047);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:498:1: complexExpression returns [AbstractExpression expression] : (expression1= parenExpression |expression2= simpleExpression ) ;
    public final AbstractExpression complexExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int complexExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;

        SimpleExpression expression2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:499:2: ( (expression1= parenExpression |expression2= simpleExpression ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:500:2: (expression1= parenExpression |expression2= simpleExpression )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:500:2: (expression1= parenExpression |expression2= simpleExpression )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==LPAREN) ) {
                alt17=1;
            }
            else if ( (LA17_0==IDENTIFIER||(LA17_0 >= LITERAL_DATE && LA17_0 <= LITERAL_STRING)||LA17_0==QUOTE_DOUBLE) ) {
                alt17=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:501:3: expression1= parenExpression
                    {
                    pushFollow(FOLLOW_parenExpression_in_complexExpression4082);
                    expression1=parenExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression1;
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:506:3: expression2= simpleExpression
                    {
                    pushFollow(FOLLOW_simpleExpression_in_complexExpression4097);
                    expression2=simpleExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression2;
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:513:1: parenExpression returns [AbstractExpression expression] : LPAREN expression1= abstractExpression RPAREN ;
    public final AbstractExpression parenExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int parenExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:514:2: ( LPAREN expression1= abstractExpression RPAREN )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:515:3: LPAREN expression1= abstractExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenExpression4122); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_parenExpression4129);
            expression1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression = expression1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenExpression4135); if (state.failed) return expression;

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



    // $ANTLR start "simpleExpression"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:522:1: simpleExpression returns [SimpleExpression expression] : ( (att1= tokenAttribute |lit1= tokenLiteral ) ) ;
    public final SimpleExpression simpleExpression() throws RecognitionException {
        SimpleExpression expression = null;

        int simpleExpression_StartIndex = input.index();

        TokenAttribute att1 =null;

        TokenLiteral lit1 =null;



                	expression = new SimpleExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:526:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:527:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:527:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:528:3: (att1= tokenAttribute |lit1= tokenLiteral )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:528:3: (att1= tokenAttribute |lit1= tokenLiteral )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==IDENTIFIER||LA18_0==QUOTE_DOUBLE) ) {
                alt18=1;
            }
            else if ( ((LA18_0 >= LITERAL_DATE && LA18_0 <= LITERAL_STRING)) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:529:3: att1= tokenAttribute
                    {
                    pushFollow(FOLLOW_tokenAttribute_in_simpleExpression4189);
                    att1=tokenAttribute();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                                    	expression.setOper(att1);
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:534:17: lit1= tokenLiteral
                    {
                    pushFollow(FOLLOW_tokenLiteral_in_simpleExpression4245);
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
            if ( state.backtracking>0 ) { memoize(input, 23, simpleExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "simpleExpression"



    // $ANTLR start "tokenAttribute"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:542:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenAttribute tokenAttribute() throws RecognitionException {
        TokenAttribute attribute = null;

        int tokenAttribute_StartIndex = input.index();

        TokenIdentifier table1 =null;

        TokenIdentifier id1 =null;



                	attribute = new TokenAttribute();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return attribute; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:546:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:547:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:547:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:548:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:548:17: (table1= tokenIdentifier DOT )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==IDENTIFIER) ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==DOT) ) {
                    alt19=1;
                }
            }
            else if ( (LA19_0==QUOTE_DOUBLE) ) {
                int LA19_2 = input.LA(2);

                if ( (LA19_2==IDENTIFIER) ) {
                    int LA19_5 = input.LA(3);

                    if ( (LA19_5==QUOTE_DOUBLE) ) {
                        int LA19_6 = input.LA(4);

                        if ( (LA19_6==DOT) ) {
                            alt19=1;
                        }
                    }
                }
            }
            switch (alt19) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:549:17: table1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute4379);
                    table1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return attribute;

                    if ( state.backtracking==0 ) {
                                    	TokenTable table = new TokenTable();
                                    	table.setName(table1);
                                    	attribute.setTable(table);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenAttribute4399); if (state.failed) return attribute;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute4438);
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
            if ( state.backtracking>0 ) { memoize(input, 24, tokenAttribute_StartIndex); }

        }
        return attribute;
    }
    // $ANTLR end "tokenAttribute"



    // $ANTLR start "tokenTable"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:562:1: tokenTable returns [TokenTable table] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenTable tokenTable() throws RecognitionException {
        TokenTable table = null;

        int tokenTable_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	table = new TokenTable();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return table; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:566:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:567:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:567:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:568:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:568:17: (schema1= tokenIdentifier DOT )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==IDENTIFIER) ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==DOT) ) {
                    alt20=1;
                }
            }
            else if ( (LA20_0==QUOTE_DOUBLE) ) {
                int LA20_2 = input.LA(2);

                if ( (LA20_2==IDENTIFIER) ) {
                    int LA20_5 = input.LA(3);

                    if ( (LA20_5==QUOTE_DOUBLE) ) {
                        int LA20_6 = input.LA(4);

                        if ( (LA20_6==DOT) ) {
                            alt20=1;
                        }
                    }
                }
            }
            switch (alt20) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:569:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable4546);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return table;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	table.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenTable4566); if (state.failed) return table;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenTable4605);
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
            if ( state.backtracking>0 ) { memoize(input, 25, tokenTable_StartIndex); }

        }
        return table;
    }
    // $ANTLR end "tokenTable"



    // $ANTLR start "tokenSchema"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:581:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
    public final TokenSchema tokenSchema() throws RecognitionException {
        TokenSchema schema = null;

        int tokenSchema_StartIndex = input.index();

        TokenIdentifier tokenIdentifier13 =null;



                	schema = new TokenSchema();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return schema; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:585:9: ( ( tokenIdentifier ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:586:9: ( tokenIdentifier )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:586:9: ( tokenIdentifier )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:587:17: tokenIdentifier
            {
            pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema4685);
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
            if ( state.backtracking>0 ) { memoize(input, 26, tokenSchema_StartIndex); }

        }
        return schema;
    }
    // $ANTLR end "tokenSchema"



    // $ANTLR start "tokenFunction"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:593:1: tokenFunction returns [TokenFunction function] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenFunction tokenFunction() throws RecognitionException {
        TokenFunction function = null;

        int tokenFunction_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	function = new TokenFunction();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return function; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:597:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:598:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:598:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:599:33: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:599:33: (schema1= tokenIdentifier DOT )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==IDENTIFIER) ) {
                int LA21_1 = input.LA(2);

                if ( (LA21_1==DOT) ) {
                    alt21=1;
                }
            }
            else if ( (LA21_0==QUOTE_DOUBLE) ) {
                int LA21_2 = input.LA(2);

                if ( (LA21_2==IDENTIFIER) ) {
                    int LA21_5 = input.LA(3);

                    if ( (LA21_5==QUOTE_DOUBLE) ) {
                        int LA21_6 = input.LA(4);

                        if ( (LA21_6==DOT) ) {
                            alt21=1;
                        }
                    }
                }
            }
            switch (alt21) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:600:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction4801);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return function;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	function.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenFunction4821); if (state.failed) return function;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction4860);
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
            if ( state.backtracking>0 ) { memoize(input, 27, tokenFunction_StartIndex); }

        }
        return function;
    }
    // $ANTLR end "tokenFunction"



    // $ANTLR start "tokenVariable"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:612:1: tokenVariable returns [TokenVariable variable] : ( variableText ) ;
    public final TokenVariable tokenVariable() throws RecognitionException {
        TokenVariable variable = null;

        int tokenVariable_StartIndex = input.index();

        String variableText14 =null;



                	variable = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return variable; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:616:9: ( ( variableText ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:617:9: ( variableText )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:617:9: ( variableText )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:618:17: variableText
            {
            pushFollow(FOLLOW_variableText_in_tokenVariable4940);
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
            if ( state.backtracking>0 ) { memoize(input, 28, tokenVariable_StartIndex); }

        }
        return variable;
    }
    // $ANTLR end "tokenVariable"



    // $ANTLR start "tokenAssignment"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:624:1: tokenAssignment returns [TokenAssignment ass] : ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) ;
    public final TokenAssignment tokenAssignment() throws RecognitionException {
        TokenAssignment ass = null;

        int tokenAssignment_StartIndex = input.index();

        TokenVariable var1 =null;

        SelectStmt selstmt1 =null;

        TokenVariable var2 =null;

        SelectStmt selstmt2 =null;



        	 	ass =new TokenAssignment();
        	 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return ass; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:628:3: ( ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:629:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:629:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==COLON) ) {
                alt22=1;
            }
            else if ( (LA22_0==KEYWORD_VAR) ) {
                alt22=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ass;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }
            switch (alt22) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:630:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    {
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:630:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:631:4: COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement
                    {
                    match(input,COLON,FOLLOW_COLON_in_tokenAssignment5007); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment5016);
                    var1=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setVar(var1);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment5022); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment5029);
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:641:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    {
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:641:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:642:4: KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement
                    {
                    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment5050); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment5059);
                    var2=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setVar(var2);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment5065); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment5072);
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
            if ( state.backtracking>0 ) { memoize(input, 29, tokenAssignment_StartIndex); }

        }
        return ass;
    }
    // $ANTLR end "tokenAssignment"



    // $ANTLR start "tokenIdentifier"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:655:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
    public final TokenIdentifier tokenIdentifier() throws RecognitionException {
        TokenIdentifier identifier = null;

        int tokenIdentifier_StartIndex = input.index();

        String identifierText15 =null;



                	identifier = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return identifier; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:659:9: ( ( identifierText ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:660:9: ( identifierText )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:660:9: ( identifierText )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:661:17: identifierText
            {
            pushFollow(FOLLOW_identifierText_in_tokenIdentifier5147);
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
            if ( state.backtracking>0 ) { memoize(input, 30, tokenIdentifier_StartIndex); }

        }
        return identifier;
    }
    // $ANTLR end "tokenIdentifier"



    // $ANTLR start "tokenDataType"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:668:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER ) ;
    public final TokenDataType tokenDataType() throws RecognitionException {
        TokenDataType dataType = null;

        int tokenDataType_StartIndex = input.index();

        Token TYPE_VARCHAR16=null;
        Token TYPE_INTEGER17=null;


                	dataType = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return dataType; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:672:9: ( ( TYPE_VARCHAR | TYPE_INTEGER ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:673:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:673:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==TYPE_VARCHAR) ) {
                alt23=1;
            }
            else if ( (LA23_0==TYPE_INTEGER) ) {
                alt23=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return dataType;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:674:17: TYPE_VARCHAR
                    {
                    TYPE_VARCHAR16=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType5228); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_VARCHAR16!=null?TYPE_VARCHAR16.getText():null));
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:678:17: TYPE_INTEGER
                    {
                    TYPE_INTEGER17=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType5266); if (state.failed) return dataType;

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
            if ( state.backtracking>0 ) { memoize(input, 31, tokenDataType_StartIndex); }

        }
        return dataType;
    }
    // $ANTLR end "tokenDataType"



    // $ANTLR start "tokenLiteral"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:684:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
    public final TokenLiteral tokenLiteral() throws RecognitionException {
        TokenLiteral literal = null;

        int tokenLiteral_StartIndex = input.index();

        TokenIntegerLiteral tokenIntegerLiteral18 =null;

        TokenStringLiteral tokenStringLiteral19 =null;

        TokenDecimalLiteral tokenDecimalLiteral20 =null;

        TokenDateLiteral tokenDateLiteral21 =null;



                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return literal; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:688:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:689:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:689:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:690:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:690:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            int alt24=4;
            switch ( input.LA(1) ) {
            case LITERAL_INTEGER:
                {
                alt24=1;
                }
                break;
            case LITERAL_STRING:
                {
                alt24=2;
                }
                break;
            case LITERAL_DECIMAL:
                {
                alt24=3;
                }
                break;
            case LITERAL_DATE:
                {
                alt24=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return literal;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:691:17: tokenIntegerLiteral
                    {
                    pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral5357);
                    tokenIntegerLiteral18=tokenIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenIntegerLiteral18;
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:695:17: tokenStringLiteral
                    {
                    pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral5395);
                    tokenStringLiteral19=tokenStringLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenStringLiteral19;
                                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:699:17: tokenDecimalLiteral
                    {
                    pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral5433);
                    tokenDecimalLiteral20=tokenDecimalLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenDecimalLiteral20;
                                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:703:17: tokenDateLiteral
                    {
                    pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral5471);
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
            if ( state.backtracking>0 ) { memoize(input, 32, tokenLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenLiteral"



    // $ANTLR start "tokenStringLiteral"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:710:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
    public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
        TokenStringLiteral literal = null;

        int tokenStringLiteral_StartIndex = input.index();

        Token lit1=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return literal; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:714:9: ( (lit1= LITERAL_STRING ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:715:9: (lit1= LITERAL_STRING )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:715:9: (lit1= LITERAL_STRING )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:716:17: lit1= LITERAL_STRING
            {
            lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral5571); if (state.failed) return literal;

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
            if ( state.backtracking>0 ) { memoize(input, 33, tokenStringLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenStringLiteral"



    // $ANTLR start "tokenIntegerLiteral"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:722:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
    public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
        TokenIntegerLiteral literal = null;

        int tokenIntegerLiteral_StartIndex = input.index();

        Token LITERAL_INTEGER22=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return literal; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:726:9: ( ( LITERAL_INTEGER ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:727:9: ( LITERAL_INTEGER )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:727:9: ( LITERAL_INTEGER )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:728:17: LITERAL_INTEGER
            {
            LITERAL_INTEGER22=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral5651); if (state.failed) return literal;

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
            if ( state.backtracking>0 ) { memoize(input, 34, tokenIntegerLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenIntegerLiteral"



    // $ANTLR start "tokenDecimalLiteral"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:735:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
    public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
        TokenDecimalLiteral literal = null;

        int tokenDecimalLiteral_StartIndex = input.index();

        Token LITERAL_DECIMAL23=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return literal; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:739:9: ( ( LITERAL_DECIMAL ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:740:9: ( LITERAL_DECIMAL )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:740:9: ( LITERAL_DECIMAL )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:741:17: LITERAL_DECIMAL
            {
            LITERAL_DECIMAL23=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral5741); if (state.failed) return literal;

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
            if ( state.backtracking>0 ) { memoize(input, 35, tokenDecimalLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenDecimalLiteral"



    // $ANTLR start "tokenDateLiteral"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:747:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( LITERAL_DATE ) ;
    public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
        TokenDateLiteral literal = null;

        int tokenDateLiteral_StartIndex = input.index();

        Token LITERAL_DATE24=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return literal; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:751:9: ( ( LITERAL_DATE ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:752:9: ( LITERAL_DATE )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:752:9: ( LITERAL_DATE )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:753:17: LITERAL_DATE
            {
            LITERAL_DATE24=(Token)match(input,LITERAL_DATE,FOLLOW_LITERAL_DATE_in_tokenDateLiteral5822); if (state.failed) return literal;

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
            if ( state.backtracking>0 ) { memoize(input, 36, tokenDateLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenDateLiteral"



    // $ANTLR start "variableText"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:759:2: variableText returns [String text] : (var1= IDENTIFIER ) ;
    public final String variableText() throws RecognitionException {
        String text = null;

        int variableText_StartIndex = input.index();

        Token var1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return text; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:760:3: ( (var1= IDENTIFIER ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:761:3: (var1= IDENTIFIER )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:761:3: (var1= IDENTIFIER )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:763:4: var1= IDENTIFIER
            {
            var1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variableText5881); if (state.failed) return text;

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
            if ( state.backtracking>0 ) { memoize(input, 37, variableText_StartIndex); }

        }
        return text;
    }
    // $ANTLR end "variableText"



    // $ANTLR start "identifierText"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:769:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) ;
    public final String identifierText() throws RecognitionException {
        String text = null;

        int identifierText_StartIndex = input.index();

        Token id1=null;
        Token id2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return text; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:770:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:771:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:771:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==IDENTIFIER) ) {
                alt25=1;
            }
            else if ( (LA25_0==QUOTE_DOUBLE) ) {
                alt25=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return text;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }
            switch (alt25) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:772:4: (id1= IDENTIFIER )
                    {
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:772:4: (id1= IDENTIFIER )
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:773:4: id1= IDENTIFIER
                    {
                    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText5933); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id1!=null?id1.getText():null).toUpperCase();
                                    }

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:778:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    {
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:778:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:779:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText5989); if (state.failed) return text;

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText6005); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id2!=null?id2.getText():null);
                                    }

                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText6025); if (state.failed) return text;

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
            if ( state.backtracking>0 ) { memoize(input, 38, identifierText_StartIndex); }

        }
        return text;
    }
    // $ANTLR end "identifierText"


    public static class tokenAddOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenAddOperator"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:788:1: tokenAddOperator : ( PLUS | MINUS ) ;
    public final FunSQLParser.tokenAddOperator_return tokenAddOperator() throws RecognitionException {
        FunSQLParser.tokenAddOperator_return retval = new FunSQLParser.tokenAddOperator_return();
        retval.start = input.LT(1);

        int tokenAddOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:789:5: ( ( PLUS | MINUS ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            if ( state.backtracking>0 ) { memoize(input, 39, tokenAddOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenAddOperator"


    public static class tokenMultOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenMultOperator"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:796:1: tokenMultOperator : ( MULT | DIV ) ;
    public final FunSQLParser.tokenMultOperator_return tokenMultOperator() throws RecognitionException {
        FunSQLParser.tokenMultOperator_return retval = new FunSQLParser.tokenMultOperator_return();
        retval.start = input.LT(1);

        int tokenMultOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:797:5: ( ( MULT | DIV ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            if ( state.backtracking>0 ) { memoize(input, 40, tokenMultOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenMultOperator"


    public static class tokenCompOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenCompOperator"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:804:1: tokenCompOperator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) ;
    public final FunSQLParser.tokenCompOperator_return tokenCompOperator() throws RecognitionException {
        FunSQLParser.tokenCompOperator_return retval = new FunSQLParser.tokenCompOperator_return();
        retval.start = input.LT(1);

        int tokenCompOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:805:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            if ( state.backtracking>0 ) { memoize(input, 41, tokenCompOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenCompOperator"

    // $ANTLR start synpred19_FunSQL
    public final void synpred19_FunSQL_fragment() throws RecognitionException {
        AbstractPredicate predicate1 =null;


        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:388:3: (predicate1= parenPredicate )
        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:388:3: predicate1= parenPredicate
        {
        pushFollow(FOLLOW_parenPredicate_in_synpred19_FunSQL3631);
        predicate1=parenPredicate();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred19_FunSQL

    // Delegated rules

    public final boolean synpred19_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred19_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_createSchemaStatement_in_statement1079 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_dropSchemaStatement_in_statement1134 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_createConnectionStatement_in_statement1189 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_dropConnectionStatement_in_statement1244 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_createTableStatement_in_statement1299 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_dropTableStatement_in_statement1354 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_createFunctionStatement_in_statement1409 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_selectStatement_in_statement1464 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_SEMI_in_statement1518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1596 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1614 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1711 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1729 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1826 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1844 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement1862 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement1882 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1902 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement1922 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1942 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1962 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1982 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement2002 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2109 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2127 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2224 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2242 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2262 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement2282 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2302 = new BitSet(new long[]{0x0000000000000000L,0x0000000060000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2340 = new BitSet(new long[]{0x0000000000002000L,0x0000000000400000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2394 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2414 = new BitSet(new long[]{0x0000000000000000L,0x0000000060000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2452 = new BitSet(new long[]{0x0000000000002000L,0x0000000000400000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement2507 = new BitSet(new long[]{0x0000140000000000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2543 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2563 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2602 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2604 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement2703 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement2721 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenTable_in_dropTableStatement2741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement2807 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenFunction_in_createFunctionStatement2827 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_LPAREN_in_createFunctionStatement2846 = new BitSet(new long[]{0x0000800000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement2882 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement2902 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement2907 = new BitSet(new long[]{0x0000800000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_RPAREN_in_createFunctionStatement2916 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement2920 = new BitSet(new long[]{0x0080020000001000L});
    public static final BitSet FOLLOW_tokenAssignment_in_createFunctionStatement2930 = new BitSet(new long[]{0x0080020000001000L});
    public static final BitSet FOLLOW_KEYWORD_END_in_createFunctionStatement2940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement3021 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenAttribute_in_selectStatement3041 = new BitSet(new long[]{0x0000040000002000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement3095 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenAttribute_in_selectStatement3115 = new BitSet(new long[]{0x0000040000002000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement3170 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement3190 = new BitSet(new long[]{0x0100000000002002L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement3245 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement3265 = new BitSet(new long[]{0x0100000000002002L});
    public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement3338 = new BitSet(new long[]{0xC000200100000000L,0x0000000000022017L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement3358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate3434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr3472 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr3483 = new BitSet(new long[]{0xC000200100000000L,0x0000000000022017L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr3491 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd3531 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd3542 = new BitSet(new long[]{0xC000200100000000L,0x0000000000022017L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd3550 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot3591 = new BitSet(new long[]{0xC000000100000000L,0x0000000000022017L});
    public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot3604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_complexPredicate3631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicate_in_complexPredicate3646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenPredicate3671 = new BitSet(new long[]{0xC000200100000000L,0x0000000000022017L});
    public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate3678 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_RPAREN_in_parenPredicate3684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate3726 = new BitSet(new long[]{0x380000000C400000L,0x0000000000000300L});
    public static final BitSet FOLLOW_tokenCompOperator_in_simplePredicate3781 = new BitSet(new long[]{0xC000000100000000L,0x0000000000022017L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate3819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionAdd_in_abstractExpression3872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd3902 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002010L});
    public static final BitSet FOLLOW_tokenAddOperator_in_complexExpressionAdd3915 = new BitSet(new long[]{0xC000000100000000L,0x0000000000022017L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd3923 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002010L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult3963 = new BitSet(new long[]{0x0000000000020002L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenMultOperator_in_complexExpressionMult3976 = new BitSet(new long[]{0xC000000100000000L,0x0000000000022017L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult3984 = new BitSet(new long[]{0x0000000000020002L,0x0000000000000040L});
    public static final BitSet FOLLOW_MINUS_in_complexExpressionSigned4026 = new BitSet(new long[]{0xC000000100000000L,0x0000000000020007L});
    public static final BitSet FOLLOW_PLUS_in_complexExpressionSigned4035 = new BitSet(new long[]{0xC000000100000000L,0x0000000000020007L});
    public static final BitSet FOLLOW_complexExpression_in_complexExpressionSigned4047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenExpression_in_complexExpression4082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleExpression_in_complexExpression4097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenExpression4122 = new BitSet(new long[]{0xC000000100000000L,0x0000000000022017L});
    public static final BitSet FOLLOW_abstractExpression_in_parenExpression4129 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_RPAREN_in_parenExpression4135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simpleExpression4189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenLiteral_in_simpleExpression4245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute4379 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenAttribute4399 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute4438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable4546 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenTable4566 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable4605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema4685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction4801 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenFunction4821 = new BitSet(new long[]{0x0000000100000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction4860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableText_in_tokenVariable4940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenAssignment5007 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment5016 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment5022 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment5029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment5050 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment5059 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment5065 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment5072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierText_in_tokenIdentifier5147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType5228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType5266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral5357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral5395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral5433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral5471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral5571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral5651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral5741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DATE_in_tokenDateLiteral5822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variableText5881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText5933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText5989 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText6005 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText6025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_synpred19_FunSQL3631 = new BitSet(new long[]{0x0000000000000002L});

}