// $ANTLR 3.4 C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g 2012-10-04 21:43:06
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", "AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", "D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", "F", "G", "GREATER_EQUAL1", "GREATER_EQUAL2", "GREATER_THAN", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", "KEYWORD_AND", "KEYWORD_AS", "KEYWORD_BEGIN", "KEYWORD_CONNECTION", "KEYWORD_CREATE", "KEYWORD_DROP", "KEYWORD_END", "KEYWORD_FROM", "KEYWORD_FUNCTION", "KEYWORD_IN", "KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_OUT", "KEYWORD_PASSWD", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_VAR", "KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", "LESS_THAN", "LITERAL_DATE", "LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", "MINUS", "MOD", "MULT", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", "QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", "SHIFT_RIGHT", "T", "TILDE", "TYPE_INTEGER", "TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
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
    public static final int KEYWORD_AS=37;
    public static final int KEYWORD_BEGIN=38;
    public static final int KEYWORD_CONNECTION=39;
    public static final int KEYWORD_CREATE=40;
    public static final int KEYWORD_DROP=41;
    public static final int KEYWORD_END=42;
    public static final int KEYWORD_FROM=43;
    public static final int KEYWORD_FUNCTION=44;
    public static final int KEYWORD_IN=45;
    public static final int KEYWORD_NOT=46;
    public static final int KEYWORD_OR=47;
    public static final int KEYWORD_OUT=48;
    public static final int KEYWORD_PASSWD=49;
    public static final int KEYWORD_SCHEMA=50;
    public static final int KEYWORD_SELECT=51;
    public static final int KEYWORD_STORE=52;
    public static final int KEYWORD_TABLE=53;
    public static final int KEYWORD_URL=54;
    public static final int KEYWORD_USER=55;
    public static final int KEYWORD_VAR=56;
    public static final int KEYWORD_WHERE=57;
    public static final int L=58;
    public static final int LBRACKET=59;
    public static final int LESS_EQUAL1=60;
    public static final int LESS_EQUAL2=61;
    public static final int LESS_THAN=62;
    public static final int LITERAL_DATE=63;
    public static final int LITERAL_DECIMAL=64;
    public static final int LITERAL_INTEGER=65;
    public static final int LITERAL_STRING=66;
    public static final int LPAREN=67;
    public static final int M=68;
    public static final int MINUS=69;
    public static final int MOD=70;
    public static final int MULT=71;
    public static final int N=72;
    public static final int NOT_EQUAL1=73;
    public static final int NOT_EQUAL2=74;
    public static final int O=75;
    public static final int P=76;
    public static final int PIPE=77;
    public static final int PLUS=78;
    public static final int Q=79;
    public static final int QUESTION=80;
    public static final int QUOTED_STRING=81;
    public static final int QUOTE_DOUBLE=82;
    public static final int QUOTE_SINGLE=83;
    public static final int QUOTE_TRIPLE=84;
    public static final int R=85;
    public static final int RBRACKET=86;
    public static final int RPAREN=87;
    public static final int S=88;
    public static final int SEMI=89;
    public static final int SHIFT_LEFT=90;
    public static final int SHIFT_RIGHT=91;
    public static final int T=92;
    public static final int TILDE=93;
    public static final int TYPE_INTEGER=94;
    public static final int TYPE_VARCHAR=95;
    public static final int U=96;
    public static final int UNDERSCORE=97;
    public static final int V=98;
    public static final int W=99;
    public static final int WS=100;
    public static final int X=101;
    public static final int Y=102;
    public static final int Z=103;

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
        this.state.ruleMemo = new HashMap[94+1];
         

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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:80:17: dropSchemaStatement
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:85:17: createConnectionStatement
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:90:17: dropConnectionStatement
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:95:17: createTableStatement
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:100:17: dropTableStatement
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:105:17: createFunctionStatement
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:110:17: selectStatement
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
            match(input,KEYWORD_FUNCTION,FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement2806); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenFunction_in_createFunctionStatement2826);
            function1=tokenFunction();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setFunction(function1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createFunctionStatement2845); if (state.failed) return stmt;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:280:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT expr1= abstractExpression ( KEYWORD_AS exprAlias1= tokenIdentifier )? ( COMMA expr2= abstractExpression ( KEYWORD_AS exprAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ) ;
    public final SelectStmt selectStatement() throws RecognitionException {
        SelectStmt stmt = null;

        int selectStatement_StartIndex = input.index();

        AbstractExpression expr1 =null;

        TokenIdentifier exprAlias1 =null;

        AbstractExpression expr2 =null;

        TokenIdentifier exprAlias2 =null;

        TokenTable table1 =null;

        TokenIdentifier tableAlias1 =null;

        TokenTable table2 =null;

        TokenIdentifier tableAlias2 =null;

        AbstractPredicate predicate1 =null;



                	stmt = new SelectStmt();
                	int i=0;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return stmt; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:285:9: ( ( KEYWORD_SELECT expr1= abstractExpression ( KEYWORD_AS exprAlias1= tokenIdentifier )? ( COMMA expr2= abstractExpression ( KEYWORD_AS exprAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:286:9: ( KEYWORD_SELECT expr1= abstractExpression ( KEYWORD_AS exprAlias1= tokenIdentifier )? ( COMMA expr2= abstractExpression ( KEYWORD_AS exprAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:286:9: ( KEYWORD_SELECT expr1= abstractExpression ( KEYWORD_AS exprAlias1= tokenIdentifier )? ( COMMA expr2= abstractExpression ( KEYWORD_AS exprAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:287:17: KEYWORD_SELECT expr1= abstractExpression ( KEYWORD_AS exprAlias1= tokenIdentifier )? ( COMMA expr2= abstractExpression ( KEYWORD_AS exprAlias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS tableAlias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )?
            {
            match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement3020); if (state.failed) return stmt;

            pushFollow(FOLLOW_abstractExpression_in_selectStatement3040);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addExpression(expr1);
                            	++i;
                            }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:293:17: ( KEYWORD_AS exprAlias1= tokenIdentifier )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==KEYWORD_AS) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:294:18: KEYWORD_AS exprAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3095); if (state.failed) return stmt;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3116);
                    exprAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    		stmt.setExpressionAlias(i-1, exprAlias1);
                                    	}

                    }
                    break;

            }


            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:300:17: ( COMMA expr2= abstractExpression ( KEYWORD_AS exprAlias2= tokenIdentifier )? )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==COMMA) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:301:17: COMMA expr2= abstractExpression ( KEYWORD_AS exprAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement3190); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_abstractExpression_in_selectStatement3210);
            	    expr2=abstractExpression();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addExpression(expr2);
            	                    	++i;
            	                    }

            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:307:17: ( KEYWORD_AS exprAlias2= tokenIdentifier )?
            	    int alt8=2;
            	    int LA8_0 = input.LA(1);

            	    if ( (LA8_0==KEYWORD_AS) ) {
            	        alt8=1;
            	    }
            	    switch (alt8) {
            	        case 1 :
            	            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:308:18: KEYWORD_AS exprAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3265); if (state.failed) return stmt;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3286);
            	            exprAlias2=tokenIdentifier();

            	            state._fsp--;
            	            if (state.failed) return stmt;

            	            if ( state.backtracking==0 ) {
            	                            		stmt.setExpressionAlias(i-1, exprAlias2);
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


            match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement3361); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenTable_in_selectStatement3381);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addTable(table1);
                            	i=1;
                            }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:321:17: ( KEYWORD_AS tableAlias1= tokenIdentifier )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==KEYWORD_AS) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:322:18: KEYWORD_AS tableAlias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3437); if (state.failed) return stmt;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3458);
                    tableAlias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    		stmt.setTableAlias(i-1, tableAlias1);
                                    	}

                    }
                    break;

            }


            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:328:17: ( COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )? )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==COMMA) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:329:17: COMMA table2= tokenTable ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement3532); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_tokenTable_in_selectStatement3552);
            	    table2=tokenTable();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addTable(table2);
            	                    	++i;
            	                    }

            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:335:17: ( KEYWORD_AS tableAlias2= tokenIdentifier )?
            	    int alt11=2;
            	    int LA11_0 = input.LA(1);

            	    if ( (LA11_0==KEYWORD_AS) ) {
            	        alt11=1;
            	    }
            	    switch (alt11) {
            	        case 1 :
            	            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:336:18: KEYWORD_AS tableAlias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3607); if (state.failed) return stmt;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3628);
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


            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:343:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==KEYWORD_WHERE) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:344:17: KEYWORD_WHERE predicate1= abstractPredicate
                    {
                    match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement3721); if (state.failed) return stmt;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement3741);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:355:1: abstractPredicate returns [AbstractPredicate predicate] : (predicate1= complexPredicate |predicate2= complexPredicateNot |predicate3= complexPredicateAnd |predicate4= complexPredicateOr );
    public final AbstractPredicate abstractPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int abstractPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;

        ComplexPredicate predicate3 =null;

        ComplexPredicate predicate4 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return predicate; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:356:2: (predicate1= complexPredicate |predicate2= complexPredicateNot |predicate3= complexPredicateAnd |predicate4= complexPredicateOr )
            int alt14=4;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                int LA14_1 = input.LA(2);

                if ( (synpred20_FunSQL()) ) {
                    alt14=1;
                }
                else if ( (synpred21_FunSQL()) ) {
                    alt14=2;
                }
                else if ( (synpred22_FunSQL()) ) {
                    alt14=3;
                }
                else if ( (true) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;

                }
                }
                break;
            case IDENTIFIER:
                {
                int LA14_2 = input.LA(2);

                if ( (synpred20_FunSQL()) ) {
                    alt14=1;
                }
                else if ( (synpred21_FunSQL()) ) {
                    alt14=2;
                }
                else if ( (synpred22_FunSQL()) ) {
                    alt14=3;
                }
                else if ( (true) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 2, input);

                    throw nvae;

                }
                }
                break;
            case QUOTE_DOUBLE:
                {
                int LA14_3 = input.LA(2);

                if ( (synpred20_FunSQL()) ) {
                    alt14=1;
                }
                else if ( (synpred21_FunSQL()) ) {
                    alt14=2;
                }
                else if ( (synpred22_FunSQL()) ) {
                    alt14=3;
                }
                else if ( (true) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 3, input);

                    throw nvae;

                }
                }
                break;
            case LITERAL_INTEGER:
                {
                int LA14_4 = input.LA(2);

                if ( (synpred20_FunSQL()) ) {
                    alt14=1;
                }
                else if ( (synpred21_FunSQL()) ) {
                    alt14=2;
                }
                else if ( (synpred22_FunSQL()) ) {
                    alt14=3;
                }
                else if ( (true) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 4, input);

                    throw nvae;

                }
                }
                break;
            case LITERAL_STRING:
                {
                int LA14_5 = input.LA(2);

                if ( (synpred20_FunSQL()) ) {
                    alt14=1;
                }
                else if ( (synpred21_FunSQL()) ) {
                    alt14=2;
                }
                else if ( (synpred22_FunSQL()) ) {
                    alt14=3;
                }
                else if ( (true) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 5, input);

                    throw nvae;

                }
                }
                break;
            case LITERAL_DECIMAL:
                {
                int LA14_6 = input.LA(2);

                if ( (synpred20_FunSQL()) ) {
                    alt14=1;
                }
                else if ( (synpred21_FunSQL()) ) {
                    alt14=2;
                }
                else if ( (synpred22_FunSQL()) ) {
                    alt14=3;
                }
                else if ( (true) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 6, input);

                    throw nvae;

                }
                }
                break;
            case LITERAL_DATE:
                {
                int LA14_7 = input.LA(2);

                if ( (synpred20_FunSQL()) ) {
                    alt14=1;
                }
                else if ( (synpred21_FunSQL()) ) {
                    alt14=2;
                }
                else if ( (synpred22_FunSQL()) ) {
                    alt14=3;
                }
                else if ( (true) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 7, input);

                    throw nvae;

                }
                }
                break;
            case MINUS:
                {
                int LA14_8 = input.LA(2);

                if ( (synpred20_FunSQL()) ) {
                    alt14=1;
                }
                else if ( (synpred21_FunSQL()) ) {
                    alt14=2;
                }
                else if ( (synpred22_FunSQL()) ) {
                    alt14=3;
                }
                else if ( (true) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 8, input);

                    throw nvae;

                }
                }
                break;
            case PLUS:
                {
                int LA14_9 = input.LA(2);

                if ( (synpred20_FunSQL()) ) {
                    alt14=1;
                }
                else if ( (synpred21_FunSQL()) ) {
                    alt14=2;
                }
                else if ( (synpred22_FunSQL()) ) {
                    alt14=3;
                }
                else if ( (true) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 9, input);

                    throw nvae;

                }
                }
                break;
            case KEYWORD_NOT:
                {
                int LA14_10 = input.LA(2);

                if ( (synpred21_FunSQL()) ) {
                    alt14=2;
                }
                else if ( (synpred22_FunSQL()) ) {
                    alt14=3;
                }
                else if ( (true) ) {
                    alt14=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 10, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return predicate;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }

            switch (alt14) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:357:3: predicate1= complexPredicate
                    {
                    pushFollow(FOLLOW_complexPredicate_in_abstractPredicate3820);
                    predicate1=complexPredicate();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate1;
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:361:3: predicate2= complexPredicateNot
                    {
                    pushFollow(FOLLOW_complexPredicateNot_in_abstractPredicate3831);
                    predicate2=complexPredicateNot();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate2;
                    		}

                    }
                    break;
                case 3 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:365:3: predicate3= complexPredicateAnd
                    {
                    pushFollow(FOLLOW_complexPredicateAnd_in_abstractPredicate3842);
                    predicate3=complexPredicateAnd();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate3;
                    		}

                    }
                    break;
                case 4 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:369:3: predicate4= complexPredicateOr
                    {
                    pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate3853);
                    predicate4=complexPredicateOr();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate4;
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
            if ( state.backtracking>0 ) { memoize(input, 10, abstractPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "abstractPredicate"



    // $ANTLR start "complexPredicateOr"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:374:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
    public final ComplexPredicate complexPredicateOr() throws RecognitionException {
        ComplexPredicate predicateOr = null;

        int complexPredicateOr_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return predicateOr; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:378:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:379:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:379:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:380:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
            {
            pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr3891);
            predicate1=complexPredicateAnd();

            state._fsp--;
            if (state.failed) return predicateOr;

            if ( state.backtracking==0 ) {
            			predicateOr.setPredicate1(predicate1);
            		}

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:383:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==KEYWORD_OR) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:384:4: KEYWORD_OR predicate2= complexPredicateAnd
            	    {
            	    match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr3902); if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addOr();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr3910);
            	    predicate2=complexPredicateAnd();

            	    state._fsp--;
            	    if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addPredicate2(predicate2);
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
            if ( state.backtracking>0 ) { memoize(input, 11, complexPredicateOr_StartIndex); }

        }
        return predicateOr;
    }
    // $ANTLR end "complexPredicateOr"



    // $ANTLR start "complexPredicateAnd"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:394:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
    public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
        ComplexPredicate predicateAnd = null;

        int complexPredicateAnd_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return predicateAnd; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:398:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:399:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:399:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:400:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
            {
            pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd3950);
            predicate1=complexPredicateNot();

            state._fsp--;
            if (state.failed) return predicateAnd;

            if ( state.backtracking==0 ) {
            			predicateAnd.setPredicate1(predicate1);
            		}

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:403:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==KEYWORD_AND) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:404:4: KEYWORD_AND predicate2= complexPredicateNot
            	    {
            	    match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd3961); if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addAnd();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd3969);
            	    predicate2=complexPredicateNot();

            	    state._fsp--;
            	    if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop16;
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:414:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
    public final ComplexPredicate complexPredicateNot() throws RecognitionException {
        ComplexPredicate predicateNot = null;

        int complexPredicateNot_StartIndex = input.index();

        AbstractPredicate predicate1 =null;



                	predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return predicateNot; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:418:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:419:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:419:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:420:3: ( KEYWORD_NOT )? predicate1= complexPredicate
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:420:3: ( KEYWORD_NOT )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==KEYWORD_NOT) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:421:3: KEYWORD_NOT
                    {
                    match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot4010); if (state.failed) return predicateNot;

                    if ( state.backtracking==0 ) {
                    			predicateNot.negate();
                    		}

                    }
                    break;

            }


            pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot4023);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:431:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
    public final AbstractPredicate complexPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int complexPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;

        SimplePredicate predicate2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return predicate; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:432:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:433:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:433:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==LPAREN) ) {
                int LA18_1 = input.LA(2);

                if ( (synpred26_FunSQL()) ) {
                    alt18=1;
                }
                else if ( (true) ) {
                    alt18=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA18_0==IDENTIFIER||(LA18_0 >= LITERAL_DATE && LA18_0 <= LITERAL_STRING)||LA18_0==MINUS||LA18_0==PLUS||LA18_0==QUOTE_DOUBLE) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return predicate;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }
            switch (alt18) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:434:3: predicate1= parenPredicate
                    {
                    pushFollow(FOLLOW_parenPredicate_in_complexPredicate4050);
                    predicate1=parenPredicate();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate1;
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:439:3: predicate2= simplePredicate
                    {
                    pushFollow(FOLLOW_simplePredicate_in_complexPredicate4065);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:446:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
    public final AbstractPredicate parenPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int parenPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return predicate; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:447:2: ( LPAREN predicate1= abstractPredicate RPAREN )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:448:3: LPAREN predicate1= abstractPredicate RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate4090); if (state.failed) return predicate;

            pushFollow(FOLLOW_abstractPredicate_in_parenPredicate4097);
            predicate1=abstractPredicate();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
            			predicate = predicate1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate4103); if (state.failed) return predicate;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:455:1: simplePredicate returns [SimplePredicate predicate] : (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) ;
    public final SimplePredicate simplePredicate() throws RecognitionException {
        SimplePredicate predicate = null;

        int simplePredicate_StartIndex = input.index();

        AbstractExpression expr1 =null;

        FunSQLParser.tokenCompOperator_return comp =null;

        AbstractExpression expr2 =null;



                	predicate = new SimplePredicate();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return predicate; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:459:9: ( (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:460:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:460:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:461:3: expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression
            {
            pushFollow(FOLLOW_abstractExpression_in_simplePredicate4145);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setExpr1(expr1);
                            }

            pushFollow(FOLLOW_tokenCompOperator_in_simplePredicate4200);
            comp=tokenCompOperator();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setComp(EnumCompOperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
                            }

            pushFollow(FOLLOW_abstractExpression_in_simplePredicate4238);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:478:1: abstractExpression returns [AbstractExpression expression] : (expression1= complexExpression |expression2= complexExpressionSigned |expression3= complexExpressionMult |expression4= complexExpressionAdd );
    public final AbstractExpression abstractExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int abstractExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;

        ComplexExpression expression2 =null;

        ComplexExpression expression3 =null;

        ComplexExpression expression4 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:479:2: (expression1= complexExpression |expression2= complexExpressionSigned |expression3= complexExpressionMult |expression4= complexExpressionAdd )
            int alt19=4;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                int LA19_1 = input.LA(2);

                if ( (synpred27_FunSQL()) ) {
                    alt19=1;
                }
                else if ( (synpred28_FunSQL()) ) {
                    alt19=2;
                }
                else if ( (synpred29_FunSQL()) ) {
                    alt19=3;
                }
                else if ( (true) ) {
                    alt19=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expression;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 1, input);

                    throw nvae;

                }
                }
                break;
            case IDENTIFIER:
                {
                int LA19_2 = input.LA(2);

                if ( (synpred27_FunSQL()) ) {
                    alt19=1;
                }
                else if ( (synpred28_FunSQL()) ) {
                    alt19=2;
                }
                else if ( (synpred29_FunSQL()) ) {
                    alt19=3;
                }
                else if ( (true) ) {
                    alt19=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expression;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 2, input);

                    throw nvae;

                }
                }
                break;
            case QUOTE_DOUBLE:
                {
                int LA19_3 = input.LA(2);

                if ( (synpred27_FunSQL()) ) {
                    alt19=1;
                }
                else if ( (synpred28_FunSQL()) ) {
                    alt19=2;
                }
                else if ( (synpred29_FunSQL()) ) {
                    alt19=3;
                }
                else if ( (true) ) {
                    alt19=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expression;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 3, input);

                    throw nvae;

                }
                }
                break;
            case LITERAL_INTEGER:
                {
                int LA19_4 = input.LA(2);

                if ( (synpred27_FunSQL()) ) {
                    alt19=1;
                }
                else if ( (synpred28_FunSQL()) ) {
                    alt19=2;
                }
                else if ( (synpred29_FunSQL()) ) {
                    alt19=3;
                }
                else if ( (true) ) {
                    alt19=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expression;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 4, input);

                    throw nvae;

                }
                }
                break;
            case LITERAL_STRING:
                {
                int LA19_5 = input.LA(2);

                if ( (synpred27_FunSQL()) ) {
                    alt19=1;
                }
                else if ( (synpred28_FunSQL()) ) {
                    alt19=2;
                }
                else if ( (synpred29_FunSQL()) ) {
                    alt19=3;
                }
                else if ( (true) ) {
                    alt19=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expression;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 5, input);

                    throw nvae;

                }
                }
                break;
            case LITERAL_DECIMAL:
                {
                int LA19_6 = input.LA(2);

                if ( (synpred27_FunSQL()) ) {
                    alt19=1;
                }
                else if ( (synpred28_FunSQL()) ) {
                    alt19=2;
                }
                else if ( (synpred29_FunSQL()) ) {
                    alt19=3;
                }
                else if ( (true) ) {
                    alt19=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expression;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 6, input);

                    throw nvae;

                }
                }
                break;
            case LITERAL_DATE:
                {
                int LA19_7 = input.LA(2);

                if ( (synpred27_FunSQL()) ) {
                    alt19=1;
                }
                else if ( (synpred28_FunSQL()) ) {
                    alt19=2;
                }
                else if ( (synpred29_FunSQL()) ) {
                    alt19=3;
                }
                else if ( (true) ) {
                    alt19=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expression;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 7, input);

                    throw nvae;

                }
                }
                break;
            case MINUS:
                {
                int LA19_8 = input.LA(2);

                if ( (synpred28_FunSQL()) ) {
                    alt19=2;
                }
                else if ( (synpred29_FunSQL()) ) {
                    alt19=3;
                }
                else if ( (true) ) {
                    alt19=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expression;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 8, input);

                    throw nvae;

                }
                }
                break;
            case PLUS:
                {
                int LA19_9 = input.LA(2);

                if ( (synpred28_FunSQL()) ) {
                    alt19=2;
                }
                else if ( (synpred29_FunSQL()) ) {
                    alt19=3;
                }
                else if ( (true) ) {
                    alt19=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expression;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 9, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:480:2: expression1= complexExpression
                    {
                    pushFollow(FOLLOW_complexExpression_in_abstractExpression4293);
                    expression1=complexExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    		expression = expression1;
                    	}

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:484:2: expression2= complexExpressionSigned
                    {
                    pushFollow(FOLLOW_complexExpressionSigned_in_abstractExpression4302);
                    expression2=complexExpressionSigned();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    		expression = expression2;
                    	}

                    }
                    break;
                case 3 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:488:2: expression3= complexExpressionMult
                    {
                    pushFollow(FOLLOW_complexExpressionMult_in_abstractExpression4311);
                    expression3=complexExpressionMult();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    		expression = expression3;
                    	}

                    }
                    break;
                case 4 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:492:2: expression4= complexExpressionAdd
                    {
                    pushFollow(FOLLOW_complexExpressionAdd_in_abstractExpression4320);
                    expression4=complexExpressionAdd();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    		expression = expression4;
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
            if ( state.backtracking>0 ) { memoize(input, 17, abstractExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "abstractExpression"



    // $ANTLR start "complexExpressionAdd"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:497:1: complexExpressionAdd returns [ComplexExpression expression] : (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) ;
    public final ComplexExpression complexExpressionAdd() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionAdd_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenAddOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:501:2: ( (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:502:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:502:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:503:3: expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )*
            {
            pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd4350);
            expression1=complexExpressionMult();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:506:3: (op1= tokenAddOperator expression2= complexExpressionMult )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==MINUS||LA20_0==PLUS) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:507:4: op1= tokenAddOperator expression2= complexExpressionMult
            	    {
            	    pushFollow(FOLLOW_tokenAddOperator_in_complexExpressionAdd4363);
            	    op1=tokenAddOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd4371);
            	    expression2=complexExpressionMult();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop20;
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:517:1: complexExpressionMult returns [ComplexExpression expression] : (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) ;
    public final ComplexExpression complexExpressionMult() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionMult_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenMultOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:521:2: ( (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:522:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:522:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:523:3: expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )*
            {
            pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult4411);
            expression1=complexExpressionSigned();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:526:3: (op1= tokenMultOperator expression2= complexExpressionSigned )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==DIV||LA21_0==MULT) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:527:4: op1= tokenMultOperator expression2= complexExpressionSigned
            	    {
            	    pushFollow(FOLLOW_tokenMultOperator_in_complexExpressionMult4424);
            	    op1=tokenMultOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult4432);
            	    expression2=complexExpressionSigned();

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
            if ( state.backtracking>0 ) { memoize(input, 19, complexExpressionMult_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionMult"



    // $ANTLR start "complexExpressionSigned"
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:538:1: complexExpressionSigned returns [ComplexExpression expression] : ( ( MINUS | PLUS )? expression1= complexExpression ) ;
    public final ComplexExpression complexExpressionSigned() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionSigned_StartIndex = input.index();

        AbstractExpression expression1 =null;



                	expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:542:2: ( ( ( MINUS | PLUS )? expression1= complexExpression ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:543:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:543:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:544:3: ( MINUS | PLUS )? expression1= complexExpression
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:544:3: ( MINUS | PLUS )?
            int alt22=3;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==MINUS) ) {
                alt22=1;
            }
            else if ( (LA22_0==PLUS) ) {
                alt22=2;
            }
            switch (alt22) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:545:3: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_complexExpressionSigned4474); if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression.negate();
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:549:3: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_complexExpressionSigned4483); if (state.failed) return expression;

                    }
                    break;

            }


            pushFollow(FOLLOW_complexExpression_in_complexExpressionSigned4495);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:557:1: complexExpression returns [AbstractExpression expression] : (expression1= parenExpression |expression2= simpleExpression ) ;
    public final AbstractExpression complexExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int complexExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;

        SimpleExpression expression2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:558:2: ( (expression1= parenExpression |expression2= simpleExpression ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:559:2: (expression1= parenExpression |expression2= simpleExpression )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:559:2: (expression1= parenExpression |expression2= simpleExpression )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==LPAREN) ) {
                alt23=1;
            }
            else if ( (LA23_0==IDENTIFIER||(LA23_0 >= LITERAL_DATE && LA23_0 <= LITERAL_STRING)||LA23_0==QUOTE_DOUBLE) ) {
                alt23=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:560:3: expression1= parenExpression
                    {
                    pushFollow(FOLLOW_parenExpression_in_complexExpression4530);
                    expression1=parenExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression1;
                    		}

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:565:3: expression2= simpleExpression
                    {
                    pushFollow(FOLLOW_simpleExpression_in_complexExpression4545);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:572:1: parenExpression returns [AbstractExpression expression] : LPAREN expression1= abstractExpression RPAREN ;
    public final AbstractExpression parenExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int parenExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:573:2: ( LPAREN expression1= abstractExpression RPAREN )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:574:3: LPAREN expression1= abstractExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenExpression4570); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_parenExpression4577);
            expression1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression = expression1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenExpression4583); if (state.failed) return expression;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:581:1: simpleExpression returns [SimpleExpression expression] : ( (att1= tokenAttribute |lit1= tokenLiteral ) ) ;
    public final SimpleExpression simpleExpression() throws RecognitionException {
        SimpleExpression expression = null;

        int simpleExpression_StartIndex = input.index();

        TokenAttribute att1 =null;

        TokenLiteral lit1 =null;



                	expression = new SimpleExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expression; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:585:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:586:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:586:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:587:3: (att1= tokenAttribute |lit1= tokenLiteral )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:587:3: (att1= tokenAttribute |lit1= tokenLiteral )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==IDENTIFIER||LA24_0==QUOTE_DOUBLE) ) {
                alt24=1;
            }
            else if ( ((LA24_0 >= LITERAL_DATE && LA24_0 <= LITERAL_STRING)) ) {
                alt24=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }
            switch (alt24) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:588:3: att1= tokenAttribute
                    {
                    pushFollow(FOLLOW_tokenAttribute_in_simpleExpression4637);
                    att1=tokenAttribute();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                                    	expression.setOper(att1);
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:593:17: lit1= tokenLiteral
                    {
                    pushFollow(FOLLOW_tokenLiteral_in_simpleExpression4693);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:601:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenAttribute tokenAttribute() throws RecognitionException {
        TokenAttribute attribute = null;

        int tokenAttribute_StartIndex = input.index();

        TokenIdentifier table1 =null;

        TokenIdentifier id1 =null;



                	attribute = new TokenAttribute();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return attribute; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:605:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:606:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:606:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:607:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:607:17: (table1= tokenIdentifier DOT )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==IDENTIFIER) ) {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==DOT) ) {
                    alt25=1;
                }
            }
            else if ( (LA25_0==QUOTE_DOUBLE) ) {
                int LA25_2 = input.LA(2);

                if ( (LA25_2==IDENTIFIER) ) {
                    int LA25_5 = input.LA(3);

                    if ( (LA25_5==QUOTE_DOUBLE) ) {
                        int LA25_6 = input.LA(4);

                        if ( (LA25_6==DOT) ) {
                            alt25=1;
                        }
                    }
                }
            }
            switch (alt25) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:608:17: table1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute4827);
                    table1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return attribute;

                    if ( state.backtracking==0 ) {
                                    	TokenTable table = new TokenTable();
                                    	table.setName(table1);
                                    	attribute.setTable(table);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenAttribute4847); if (state.failed) return attribute;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute4886);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:621:1: tokenTable returns [TokenTable table] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenTable tokenTable() throws RecognitionException {
        TokenTable table = null;

        int tokenTable_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	table = new TokenTable();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return table; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:625:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:626:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:626:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:627:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:627:17: (schema1= tokenIdentifier DOT )?
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:628:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable4994);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return table;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	table.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenTable5014); if (state.failed) return table;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenTable5053);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:640:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
    public final TokenSchema tokenSchema() throws RecognitionException {
        TokenSchema schema = null;

        int tokenSchema_StartIndex = input.index();

        TokenIdentifier tokenIdentifier13 =null;



                	schema = new TokenSchema();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return schema; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:644:9: ( ( tokenIdentifier ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:645:9: ( tokenIdentifier )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:645:9: ( tokenIdentifier )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:646:17: tokenIdentifier
            {
            pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema5133);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:652:1: tokenFunction returns [TokenFunction function] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenFunction tokenFunction() throws RecognitionException {
        TokenFunction function = null;

        int tokenFunction_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	function = new TokenFunction();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return function; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:656:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:657:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:657:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:658:33: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:658:33: (schema1= tokenIdentifier DOT )?
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:659:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction5249);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return function;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	function.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenFunction5269); if (state.failed) return function;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenFunction5308);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:671:1: tokenVariable returns [TokenVariable variable] : ( variableText ) ;
    public final TokenVariable tokenVariable() throws RecognitionException {
        TokenVariable variable = null;

        int tokenVariable_StartIndex = input.index();

        String variableText14 =null;



                	variable = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return variable; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:675:9: ( ( variableText ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:676:9: ( variableText )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:676:9: ( variableText )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:677:17: variableText
            {
            pushFollow(FOLLOW_variableText_in_tokenVariable5388);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:683:1: tokenAssignment returns [TokenAssignment ass] : ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) ;
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

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:687:3: ( ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:688:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:688:3: ( ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement ) | ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement ) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==COLON) ) {
                alt28=1;
            }
            else if ( (LA28_0==KEYWORD_VAR) ) {
                alt28=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ass;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;

            }
            switch (alt28) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:689:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    {
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:689:4: ( COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement )
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:690:4: COLON var1= tokenVariable EQUAL1 selstmt1= selectStatement
                    {
                    match(input,COLON,FOLLOW_COLON_in_tokenAssignment5455); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment5467);
                    var1=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {
                    		 ass.setReference(true);
                    		 ass.setVar(var1);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment5473); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment5480);
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
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:701:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    {
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:701:4: ( KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement )
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:702:4: KEYWORD_VAR var2= tokenVariable EQUAL1 selstmt2= selectStatement
                    {
                    match(input,KEYWORD_VAR,FOLLOW_KEYWORD_VAR_in_tokenAssignment5501); if (state.failed) return ass;

                    pushFollow(FOLLOW_tokenVariable_in_tokenAssignment5510);
                    var2=tokenVariable();

                    state._fsp--;
                    if (state.failed) return ass;

                    if ( state.backtracking==0 ) {		 
                    		 ass.setReference(false);
                    		 ass.setVar(var2);
                    		 }

                    match(input,EQUAL1,FOLLOW_EQUAL1_in_tokenAssignment5516); if (state.failed) return ass;

                    pushFollow(FOLLOW_selectStatement_in_tokenAssignment5523);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:716:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
    public final TokenIdentifier tokenIdentifier() throws RecognitionException {
        TokenIdentifier identifier = null;

        int tokenIdentifier_StartIndex = input.index();

        String identifierText15 =null;



                	identifier = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return identifier; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:720:9: ( ( identifierText ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:721:9: ( identifierText )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:721:9: ( identifierText )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:722:17: identifierText
            {
            pushFollow(FOLLOW_identifierText_in_tokenIdentifier5598);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:729:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER ) ;
    public final TokenDataType tokenDataType() throws RecognitionException {
        TokenDataType dataType = null;

        int tokenDataType_StartIndex = input.index();

        Token TYPE_VARCHAR16=null;
        Token TYPE_INTEGER17=null;


                	dataType = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return dataType; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:733:9: ( ( TYPE_VARCHAR | TYPE_INTEGER ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:734:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:734:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==TYPE_VARCHAR) ) {
                alt29=1;
            }
            else if ( (LA29_0==TYPE_INTEGER) ) {
                alt29=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return dataType;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }
            switch (alt29) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:735:17: TYPE_VARCHAR
                    {
                    TYPE_VARCHAR16=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType5679); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_VARCHAR16!=null?TYPE_VARCHAR16.getText():null));
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:739:17: TYPE_INTEGER
                    {
                    TYPE_INTEGER17=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType5717); if (state.failed) return dataType;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:745:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
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

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:749:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:750:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:750:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:751:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:751:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            int alt30=4;
            switch ( input.LA(1) ) {
            case LITERAL_INTEGER:
                {
                alt30=1;
                }
                break;
            case LITERAL_STRING:
                {
                alt30=2;
                }
                break;
            case LITERAL_DECIMAL:
                {
                alt30=3;
                }
                break;
            case LITERAL_DATE:
                {
                alt30=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return literal;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;

            }

            switch (alt30) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:752:17: tokenIntegerLiteral
                    {
                    pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral5808);
                    tokenIntegerLiteral18=tokenIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenIntegerLiteral18;
                                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:756:17: tokenStringLiteral
                    {
                    pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral5846);
                    tokenStringLiteral19=tokenStringLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenStringLiteral19;
                                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:760:17: tokenDecimalLiteral
                    {
                    pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral5884);
                    tokenDecimalLiteral20=tokenDecimalLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenDecimalLiteral20;
                                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:764:17: tokenDateLiteral
                    {
                    pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral5922);
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:771:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
    public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
        TokenStringLiteral literal = null;

        int tokenStringLiteral_StartIndex = input.index();

        Token lit1=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return literal; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:775:9: ( (lit1= LITERAL_STRING ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:776:9: (lit1= LITERAL_STRING )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:776:9: (lit1= LITERAL_STRING )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:777:17: lit1= LITERAL_STRING
            {
            lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral6022); if (state.failed) return literal;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:783:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
    public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
        TokenIntegerLiteral literal = null;

        int tokenIntegerLiteral_StartIndex = input.index();

        Token LITERAL_INTEGER22=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return literal; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:787:9: ( ( LITERAL_INTEGER ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:788:9: ( LITERAL_INTEGER )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:788:9: ( LITERAL_INTEGER )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:789:17: LITERAL_INTEGER
            {
            LITERAL_INTEGER22=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral6102); if (state.failed) return literal;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:796:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
    public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
        TokenDecimalLiteral literal = null;

        int tokenDecimalLiteral_StartIndex = input.index();

        Token LITERAL_DECIMAL23=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return literal; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:800:9: ( ( LITERAL_DECIMAL ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:801:9: ( LITERAL_DECIMAL )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:801:9: ( LITERAL_DECIMAL )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:802:17: LITERAL_DECIMAL
            {
            LITERAL_DECIMAL23=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral6192); if (state.failed) return literal;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:808:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( LITERAL_DATE ) ;
    public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
        TokenDateLiteral literal = null;

        int tokenDateLiteral_StartIndex = input.index();

        Token LITERAL_DATE24=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return literal; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:812:9: ( ( LITERAL_DATE ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:813:9: ( LITERAL_DATE )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:813:9: ( LITERAL_DATE )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:814:17: LITERAL_DATE
            {
            LITERAL_DATE24=(Token)match(input,LITERAL_DATE,FOLLOW_LITERAL_DATE_in_tokenDateLiteral6273); if (state.failed) return literal;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:820:2: variableText returns [String text] : (var1= IDENTIFIER ) ;
    public final String variableText() throws RecognitionException {
        String text = null;

        int variableText_StartIndex = input.index();

        Token var1=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return text; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:821:3: ( (var1= IDENTIFIER ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:822:3: (var1= IDENTIFIER )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:822:3: (var1= IDENTIFIER )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:824:4: var1= IDENTIFIER
            {
            var1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variableText6332); if (state.failed) return text;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:830:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) ;
    public final String identifierText() throws RecognitionException {
        String text = null;

        int identifierText_StartIndex = input.index();

        Token id1=null;
        Token id2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return text; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:831:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) )
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:832:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            {
            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:832:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==IDENTIFIER) ) {
                alt31=1;
            }
            else if ( (LA31_0==QUOTE_DOUBLE) ) {
                alt31=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return text;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }
            switch (alt31) {
                case 1 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:833:4: (id1= IDENTIFIER )
                    {
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:833:4: (id1= IDENTIFIER )
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:834:4: id1= IDENTIFIER
                    {
                    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText6384); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id1!=null?id1.getText():null).toUpperCase();
                                    }

                    }


                    }
                    break;
                case 2 :
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:839:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    {
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:839:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:840:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText6440); if (state.failed) return text;

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText6456); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id2!=null?id2.getText():null);
                                    }

                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText6476); if (state.failed) return text;

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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:849:1: tokenAddOperator : ( PLUS | MINUS ) ;
    public final FunSQLParser.tokenAddOperator_return tokenAddOperator() throws RecognitionException {
        FunSQLParser.tokenAddOperator_return retval = new FunSQLParser.tokenAddOperator_return();
        retval.start = input.LT(1);

        int tokenAddOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:850:5: ( ( PLUS | MINUS ) )
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:857:1: tokenMultOperator : ( MULT | DIV ) ;
    public final FunSQLParser.tokenMultOperator_return tokenMultOperator() throws RecognitionException {
        FunSQLParser.tokenMultOperator_return retval = new FunSQLParser.tokenMultOperator_return();
        retval.start = input.LT(1);

        int tokenMultOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:858:5: ( ( MULT | DIV ) )
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
    // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:865:1: tokenCompOperator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) ;
    public final FunSQLParser.tokenCompOperator_return tokenCompOperator() throws RecognitionException {
        FunSQLParser.tokenCompOperator_return retval = new FunSQLParser.tokenCompOperator_return();
        retval.start = input.LT(1);

        int tokenCompOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }

            // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:866:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) )
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

    // $ANTLR start synpred20_FunSQL
    public final void synpred20_FunSQL_fragment() throws RecognitionException {
        AbstractPredicate predicate1 =null;


        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:357:3: (predicate1= complexPredicate )
        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:357:3: predicate1= complexPredicate
        {
        pushFollow(FOLLOW_complexPredicate_in_synpred20_FunSQL3820);
        predicate1=complexPredicate();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred20_FunSQL

    // $ANTLR start synpred21_FunSQL
    public final void synpred21_FunSQL_fragment() throws RecognitionException {
        ComplexPredicate predicate2 =null;


        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:361:3: (predicate2= complexPredicateNot )
        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:361:3: predicate2= complexPredicateNot
        {
        pushFollow(FOLLOW_complexPredicateNot_in_synpred21_FunSQL3831);
        predicate2=complexPredicateNot();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred21_FunSQL

    // $ANTLR start synpred22_FunSQL
    public final void synpred22_FunSQL_fragment() throws RecognitionException {
        ComplexPredicate predicate3 =null;


        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:365:3: (predicate3= complexPredicateAnd )
        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:365:3: predicate3= complexPredicateAnd
        {
        pushFollow(FOLLOW_complexPredicateAnd_in_synpred22_FunSQL3842);
        predicate3=complexPredicateAnd();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred22_FunSQL

    // $ANTLR start synpred26_FunSQL
    public final void synpred26_FunSQL_fragment() throws RecognitionException {
        AbstractPredicate predicate1 =null;


        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:434:3: (predicate1= parenPredicate )
        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:434:3: predicate1= parenPredicate
        {
        pushFollow(FOLLOW_parenPredicate_in_synpred26_FunSQL4050);
        predicate1=parenPredicate();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred26_FunSQL

    // $ANTLR start synpred27_FunSQL
    public final void synpred27_FunSQL_fragment() throws RecognitionException {
        AbstractExpression expression1 =null;


        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:480:2: (expression1= complexExpression )
        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:480:2: expression1= complexExpression
        {
        pushFollow(FOLLOW_complexExpression_in_synpred27_FunSQL4293);
        expression1=complexExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred27_FunSQL

    // $ANTLR start synpred28_FunSQL
    public final void synpred28_FunSQL_fragment() throws RecognitionException {
        ComplexExpression expression2 =null;


        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:484:2: (expression2= complexExpressionSigned )
        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:484:2: expression2= complexExpressionSigned
        {
        pushFollow(FOLLOW_complexExpressionSigned_in_synpred28_FunSQL4302);
        expression2=complexExpressionSigned();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred28_FunSQL

    // $ANTLR start synpred29_FunSQL
    public final void synpred29_FunSQL_fragment() throws RecognitionException {
        ComplexExpression expression3 =null;


        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:488:2: (expression3= complexExpressionMult )
        // C:\\Users\\media\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:488:2: expression3= complexExpressionMult
        {
        pushFollow(FOLLOW_complexExpressionMult_in_synpred29_FunSQL4311);
        expression3=complexExpressionMult();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred29_FunSQL

    // Delegated rules

    public final boolean synpred29_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred29_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred22_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred22_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_FunSQL_fragment(); // can never throw exception
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
    public final boolean synpred20_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
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
    public final boolean synpred27_FunSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_FunSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_createSchemaStatement_in_statement1078 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_dropSchemaStatement_in_statement1133 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_createConnectionStatement_in_statement1188 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_dropConnectionStatement_in_statement1243 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_createTableStatement_in_statement1298 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_dropTableStatement_in_statement1353 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_createFunctionStatement_in_statement1408 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_selectStatement_in_statement1463 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_SEMI_in_statement1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1595 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1613 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1710 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1728 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1825 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1843 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement1861 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement1881 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1901 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement1921 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1941 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1961 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1981 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement2001 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement2021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2108 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2126 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2223 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2241 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2261 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement2281 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2301 = new BitSet(new long[]{0x0000000000000000L,0x00000000C0000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2339 = new BitSet(new long[]{0x0000000000002000L,0x0000000000800000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2393 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2413 = new BitSet(new long[]{0x0000000000000000L,0x00000000C0000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2451 = new BitSet(new long[]{0x0000000000002000L,0x0000000000800000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement2506 = new BitSet(new long[]{0x0000280000000000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2542 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2562 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2601 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2603 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement2702 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement2720 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenTable_in_dropTableStatement2740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_FUNCTION_in_createFunctionStatement2806 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenFunction_in_createFunctionStatement2826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_LPAREN_in_createFunctionStatement2845 = new BitSet(new long[]{0x0001000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_KEYWORD_OUT_in_createFunctionStatement2881 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_tokenVariable_in_createFunctionStatement2901 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createFunctionStatement2906 = new BitSet(new long[]{0x0001000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_RPAREN_in_createFunctionStatement2915 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_KEYWORD_BEGIN_in_createFunctionStatement2919 = new BitSet(new long[]{0x0100040000001000L});
    public static final BitSet FOLLOW_tokenAssignment_in_createFunctionStatement2929 = new BitSet(new long[]{0x0100040000001000L});
    public static final BitSet FOLLOW_KEYWORD_END_in_createFunctionStatement2939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement3020 = new BitSet(new long[]{0x8000000100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement3040 = new BitSet(new long[]{0x0000082000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3095 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3116 = new BitSet(new long[]{0x0000080000002000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement3190 = new BitSet(new long[]{0x8000000100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement3210 = new BitSet(new long[]{0x0000082000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3265 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3286 = new BitSet(new long[]{0x0000080000002000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement3361 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement3381 = new BitSet(new long[]{0x0200002000002002L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3437 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3458 = new BitSet(new long[]{0x0200000000002002L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement3532 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement3552 = new BitSet(new long[]{0x0200002000002002L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3607 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3628 = new BitSet(new long[]{0x0200000000002002L});
    public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement3721 = new BitSet(new long[]{0x8000400100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement3741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicate_in_abstractPredicate3820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_abstractPredicate3831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_abstractPredicate3842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate3853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr3891 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr3902 = new BitSet(new long[]{0x8000400100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr3910 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd3950 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd3961 = new BitSet(new long[]{0x8000400100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd3969 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot4010 = new BitSet(new long[]{0x8000000100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot4023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_complexPredicate4050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicate_in_complexPredicate4065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenPredicate4090 = new BitSet(new long[]{0x8000400100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate4097 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_RPAREN_in_parenPredicate4103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate4145 = new BitSet(new long[]{0x700000000C400000L,0x0000000000000600L});
    public static final BitSet FOLLOW_tokenCompOperator_in_simplePredicate4200 = new BitSet(new long[]{0x8000000100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate4238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpression_in_abstractExpression4293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_abstractExpression4302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionMult_in_abstractExpression4311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionAdd_in_abstractExpression4320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd4350 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004020L});
    public static final BitSet FOLLOW_tokenAddOperator_in_complexExpressionAdd4363 = new BitSet(new long[]{0x8000000100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd4371 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004020L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult4411 = new BitSet(new long[]{0x0000000000020002L,0x0000000000000080L});
    public static final BitSet FOLLOW_tokenMultOperator_in_complexExpressionMult4424 = new BitSet(new long[]{0x8000000100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult4432 = new BitSet(new long[]{0x0000000000020002L,0x0000000000000080L});
    public static final BitSet FOLLOW_MINUS_in_complexExpressionSigned4474 = new BitSet(new long[]{0x8000000100000000L,0x000000000004000FL});
    public static final BitSet FOLLOW_PLUS_in_complexExpressionSigned4483 = new BitSet(new long[]{0x8000000100000000L,0x000000000004000FL});
    public static final BitSet FOLLOW_complexExpression_in_complexExpressionSigned4495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenExpression_in_complexExpression4530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleExpression_in_complexExpression4545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenExpression4570 = new BitSet(new long[]{0x8000000100000000L,0x000000000004402FL});
    public static final BitSet FOLLOW_abstractExpression_in_parenExpression4577 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_RPAREN_in_parenExpression4583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simpleExpression4637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenLiteral_in_simpleExpression4693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute4827 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenAttribute4847 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute4886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable4994 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenTable5014 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable5053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema5133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction5249 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenFunction5269 = new BitSet(new long[]{0x0000000100000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenFunction5308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableText_in_tokenVariable5388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_tokenAssignment5455 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment5467 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment5473 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment5480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_VAR_in_tokenAssignment5501 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_tokenVariable_in_tokenAssignment5510 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_tokenAssignment5516 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_selectStatement_in_tokenAssignment5523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierText_in_tokenIdentifier5598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType5679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType5717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral5808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral5846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral5884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral5922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral6022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral6102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral6192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DATE_in_tokenDateLiteral6273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variableText6332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText6384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText6440 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText6456 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText6476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicate_in_synpred20_FunSQL3820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_synpred21_FunSQL3831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_synpred22_FunSQL3842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_synpred26_FunSQL4050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpression_in_synpred27_FunSQL4293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_synpred28_FunSQL4302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionMult_in_synpred29_FunSQL4311 = new BitSet(new long[]{0x0000000000000002L});

}