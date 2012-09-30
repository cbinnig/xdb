// $ANTLR 3.4 /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g 2012-09-30 07:47:01
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", "AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", "D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", "F", "G", "GREATER_EQUAL1", "GREATER_EQUAL2", "GREATER_THAN", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", "KEYWORD_AND", "KEYWORD_AS", "KEYWORD_CONNECTION", "KEYWORD_CREATE", "KEYWORD_DROP", "KEYWORD_FROM", "KEYWORD_IN", "KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_PASSWD", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", "LESS_THAN", "LITERAL_DATE", "LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", "MINUS", "MOD", "MULT", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", "QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", "SHIFT_RIGHT", "T", "TILDE", "TYPE_INTEGER", "TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
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
    public static final int KEYWORD_CONNECTION=38;
    public static final int KEYWORD_CREATE=39;
    public static final int KEYWORD_DROP=40;
    public static final int KEYWORD_FROM=41;
    public static final int KEYWORD_IN=42;
    public static final int KEYWORD_NOT=43;
    public static final int KEYWORD_OR=44;
    public static final int KEYWORD_PASSWD=45;
    public static final int KEYWORD_SCHEMA=46;
    public static final int KEYWORD_SELECT=47;
    public static final int KEYWORD_STORE=48;
    public static final int KEYWORD_TABLE=49;
    public static final int KEYWORD_URL=50;
    public static final int KEYWORD_USER=51;
    public static final int KEYWORD_WHERE=52;
    public static final int L=53;
    public static final int LBRACKET=54;
    public static final int LESS_EQUAL1=55;
    public static final int LESS_EQUAL2=56;
    public static final int LESS_THAN=57;
    public static final int LITERAL_DATE=58;
    public static final int LITERAL_DECIMAL=59;
    public static final int LITERAL_INTEGER=60;
    public static final int LITERAL_STRING=61;
    public static final int LPAREN=62;
    public static final int M=63;
    public static final int MINUS=64;
    public static final int MOD=65;
    public static final int MULT=66;
    public static final int N=67;
    public static final int NOT_EQUAL1=68;
    public static final int NOT_EQUAL2=69;
    public static final int O=70;
    public static final int P=71;
    public static final int PIPE=72;
    public static final int PLUS=73;
    public static final int Q=74;
    public static final int QUESTION=75;
    public static final int QUOTED_STRING=76;
    public static final int QUOTE_DOUBLE=77;
    public static final int QUOTE_SINGLE=78;
    public static final int QUOTE_TRIPLE=79;
    public static final int R=80;
    public static final int RBRACKET=81;
    public static final int RPAREN=82;
    public static final int S=83;
    public static final int SEMI=84;
    public static final int SHIFT_LEFT=85;
    public static final int SHIFT_RIGHT=86;
    public static final int T=87;
    public static final int TILDE=88;
    public static final int TYPE_INTEGER=89;
    public static final int TYPE_VARCHAR=90;
    public static final int U=91;
    public static final int UNDERSCORE=92;
    public static final int V=93;
    public static final int W=94;
    public static final int WS=95;
    public static final int X=96;
    public static final int Y=97;
    public static final int Z=98;

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
        this.state.ruleMemo = new HashMap[78+1];
         

    }

    public String[] getTokenNames() { return FunSQLParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g"; }



    // $ANTLR start "statement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:68:1: statement returns [AbstractServerStmt stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )? ) ;
    public final AbstractServerStmt statement() throws RecognitionException {
        AbstractServerStmt stmt = null;

        int statement_StartIndex = input.index();

        CreateSchemaStmt createSchemaStatement1 =null;

        DropSchemaStmt dropSchemaStatement2 =null;

        CreateConnectionStmt createConnectionStatement3 =null;

        DropConnectionStmt dropConnectionStatement4 =null;

        CreateTableStmt createTableStatement5 =null;

        DropTableStmt dropTableStatement6 =null;

        SelectStmt selectStatement7 =null;



                	stmt = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:72:9: ( ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )? ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:73:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )? )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:73:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )? )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:74:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )?
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:74:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement )
            int alt1=7;
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
            case KEYWORD_SELECT:
                {
                alt1=7;
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:105:17: selectStatement
                    {
                    pushFollow(FOLLOW_selectStatement_in_statement1408);
                    selectStatement7=selectStatement();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt = selectStatement7;
                                    }

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:110:17: ( SEMI )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==SEMI) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:110:17: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_statement1462); if (state.failed) return stmt;

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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:114:1: createSchemaStatement returns [CreateSchemaStmt stmt] : ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) ;
    public final CreateSchemaStmt createSchemaStatement() throws RecognitionException {
        CreateSchemaStmt stmt = null;

        int createSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema8 =null;



                	stmt = new CreateSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:118:9: ( ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:119:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:119:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:120:17: KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1540); if (state.failed) return stmt;

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1558); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenSchema_in_createSchemaStatement1576);
            tokenSchema8=tokenSchema();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setSchema(tokenSchema8);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:128:1: dropSchemaStatement returns [DropSchemaStmt stmt] : ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) ;
    public final DropSchemaStmt dropSchemaStatement() throws RecognitionException {
        DropSchemaStmt stmt = null;

        int dropSchemaStatement_StartIndex = input.index();

        TokenSchema tokenSchema9 =null;



                	stmt = new DropSchemaStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:132:9: ( ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:133:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:133:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:134:17: KEYWORD_DROP KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1655); if (state.failed) return stmt;

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1673); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenSchema_in_dropSchemaStatement1691);
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
            if ( state.backtracking>0 ) { memoize(input, 3, dropSchemaStatement_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "dropSchemaStatement"



    // $ANTLR start "createConnectionStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:142:1: createConnectionStatement returns [CreateConnectionStmt stmt] : ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) ;
    public final CreateConnectionStmt createConnectionStatement() throws RecognitionException {
        CreateConnectionStmt stmt = null;

        int createConnectionStatement_StartIndex = input.index();

        TokenStringLiteral litURL =null;

        TokenStringLiteral litUser =null;

        TokenStringLiteral litPasswd =null;

        TokenStringLiteral litStore =null;

        TokenIdentifier tokenIdentifier10 =null;



                	stmt = new CreateConnectionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:146:9: ( ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:147:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:147:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:148:17: KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1770); if (state.failed) return stmt;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1788); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenIdentifier_in_createConnectionStatement1806);
            tokenIdentifier10=tokenIdentifier();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setConnection(tokenIdentifier10);
                            }

            match(input,KEYWORD_URL,FOLLOW_KEYWORD_URL_in_createConnectionStatement1826); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1846);
            litURL=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setURL(litURL);
                            }

            match(input,KEYWORD_USER,FOLLOW_KEYWORD_USER_in_createConnectionStatement1866); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1886);
            litUser=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setUser(litUser);
                            }

            match(input,KEYWORD_PASSWD,FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1906); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1926);
            litPasswd=tokenStringLiteral();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setPasswd(litPasswd);
                            }

            match(input,KEYWORD_STORE,FOLLOW_KEYWORD_STORE_in_createConnectionStatement1946); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1966);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:172:1: dropConnectionStatement returns [DropConnectionStmt stmt] : ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) ;
    public final DropConnectionStmt dropConnectionStatement() throws RecognitionException {
        DropConnectionStmt stmt = null;

        int dropConnectionStatement_StartIndex = input.index();

        TokenIdentifier tokenIdentifier11 =null;



                	stmt = new DropConnectionStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:176:9: ( ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:177:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:177:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:178:17: KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2053); if (state.failed) return stmt;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2071); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenIdentifier_in_dropConnectionStatement2089);
            tokenIdentifier11=tokenIdentifier();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setConnection(tokenIdentifier11);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:186:1: createTableStatement returns [CreateTableStmt stmt] : ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) ;
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

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:190:9: ( ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:191:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:191:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:192:17: KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2168); if (state.failed) return stmt;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2186); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenTable_in_createTableStatement2206);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.setTable(table1);
                            	stmt.setSourceTable(table1);
                            }

            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2226); if (state.failed) return stmt;

            pushFollow(FOLLOW_identifierText_in_createTableStatement2246);
            att1=identifierText();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addAttribute(att1);
                            }

            pushFollow(FOLLOW_tokenDataType_in_createTableStatement2284);
            dataType1=tokenDataType();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addType(dataType1);
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:207:17: ( COMMA att2= identifierText dataType2= tokenDataType )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:208:17: COMMA att2= identifierText dataType2= tokenDataType
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2338); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_identifierText_in_createTableStatement2358);
            	    att2=identifierText();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addAttribute(att2);
            	                    }

            	    pushFollow(FOLLOW_tokenDataType_in_createTableStatement2396);
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


            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2451); if (state.failed) return stmt;

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:219:17: ( KEYWORD_FROM table2= tokenTable )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_FROM) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:220:17: KEYWORD_FROM table2= tokenTable
                    {
                    match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_createTableStatement2487); if (state.failed) return stmt;

                    pushFollow(FOLLOW_tokenTable_in_createTableStatement2507);
                    table2=tokenTable();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                                    	stmt.setSourceTable(table2);
                                    }

                    }
                    break;

            }


            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2546); if (state.failed) return stmt;

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2548); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2568);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:232:1: dropTableStatement returns [DropTableStmt stmt] : ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) ;
    public final DropTableStmt dropTableStatement() throws RecognitionException {
        DropTableStmt stmt = null;

        int dropTableStatement_StartIndex = input.index();

        TokenTable table1 =null;



                	stmt = new DropTableStmt();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:236:9: ( ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:237:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:237:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:238:17: KEYWORD_DROP KEYWORD_TABLE table1= tokenTable
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement2647); if (state.failed) return stmt;

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement2665); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenTable_in_dropTableStatement2685);
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



    // $ANTLR start "selectStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:246:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT expr1= abstractExpression ( KEYWORD_AS alias1= tokenIdentifier )? ( COMMA expr2= abstractExpression ( KEYWORD_AS alias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS talias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS talias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ) ;
    public final SelectStmt selectStatement() throws RecognitionException {
        SelectStmt stmt = null;

        int selectStatement_StartIndex = input.index();

        AbstractExpression expr1 =null;

        TokenIdentifier alias1 =null;

        AbstractExpression expr2 =null;

        TokenIdentifier alias2 =null;

        TokenTable table1 =null;

        TokenIdentifier talias1 =null;

        TokenTable table2 =null;

        TokenIdentifier talias2 =null;

        AbstractPredicate predicate1 =null;



                	stmt = new SelectStmt();
                	int expressionNumber = 0;
                	int tableNumber = 0;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return stmt; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:252:9: ( ( KEYWORD_SELECT expr1= abstractExpression ( KEYWORD_AS alias1= tokenIdentifier )? ( COMMA expr2= abstractExpression ( KEYWORD_AS alias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS talias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS talias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:253:9: ( KEYWORD_SELECT expr1= abstractExpression ( KEYWORD_AS alias1= tokenIdentifier )? ( COMMA expr2= abstractExpression ( KEYWORD_AS alias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS talias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS talias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:253:9: ( KEYWORD_SELECT expr1= abstractExpression ( KEYWORD_AS alias1= tokenIdentifier )? ( COMMA expr2= abstractExpression ( KEYWORD_AS alias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS talias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS talias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )? )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:254:17: KEYWORD_SELECT expr1= abstractExpression ( KEYWORD_AS alias1= tokenIdentifier )? ( COMMA expr2= abstractExpression ( KEYWORD_AS alias2= tokenIdentifier )? )* KEYWORD_FROM table1= tokenTable ( KEYWORD_AS talias1= tokenIdentifier )? ( COMMA table2= tokenTable ( KEYWORD_AS talias2= tokenIdentifier )? )* ( KEYWORD_WHERE predicate1= abstractPredicate )?
            {
            match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement2751); if (state.failed) return stmt;

            pushFollow(FOLLOW_abstractExpression_in_selectStatement2771);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addExpression(expr1);
                            	expressionNumber++;
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:260:17: ( KEYWORD_AS alias1= tokenIdentifier )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==KEYWORD_AS) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:261:17: KEYWORD_AS alias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement2826); if (state.failed) return stmt;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement2832);
                    alias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                    			stmt.setExpressionAlias(expressionNumber-1, alias1);
                    		}

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:267:17: ( COMMA expr2= abstractExpression ( KEYWORD_AS alias2= tokenIdentifier )? )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==COMMA) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:268:17: COMMA expr2= abstractExpression ( KEYWORD_AS alias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement2908); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_abstractExpression_in_selectStatement2928);
            	    expr2=abstractExpression();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addExpression(expr2);
            	                    	expressionNumber++;
            	                    }

            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:274:17: ( KEYWORD_AS alias2= tokenIdentifier )?
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==KEYWORD_AS) ) {
            	        alt6=1;
            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:275:17: KEYWORD_AS alias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement2983); if (state.failed) return stmt;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement2989);
            	            alias2=tokenIdentifier();

            	            state._fsp--;
            	            if (state.failed) return stmt;

            	            if ( state.backtracking==0 ) {
            	            			stmt.setExpressionAlias(expressionNumber-1, alias2);
            	            		}

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement3066); if (state.failed) return stmt;

            pushFollow(FOLLOW_tokenTable_in_selectStatement3086);
            table1=tokenTable();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
                            	stmt.addTable(table1);
                            	tableNumber++;
                            }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:288:17: ( KEYWORD_AS talias1= tokenIdentifier )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==KEYWORD_AS) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:289:17: KEYWORD_AS talias1= tokenIdentifier
                    {
                    match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3142); if (state.failed) return stmt;

                    pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3148);
                    talias1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) {
                    			stmt.setTableAlias(tableNumber-1, talias1);
                    		}

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:295:17: ( COMMA table2= tokenTable ( KEYWORD_AS talias2= tokenIdentifier )? )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==COMMA) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:296:17: COMMA table2= tokenTable ( KEYWORD_AS talias2= tokenIdentifier )?
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement3224); if (state.failed) return stmt;

            	    pushFollow(FOLLOW_tokenTable_in_selectStatement3244);
            	    table2=tokenTable();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) {
            	                    	stmt.addTable(table2);
            	                    	tableNumber++;
            	                    }

            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:302:17: ( KEYWORD_AS talias2= tokenIdentifier )?
            	    int alt9=2;
            	    int LA9_0 = input.LA(1);

            	    if ( (LA9_0==KEYWORD_AS) ) {
            	        alt9=1;
            	    }
            	    switch (alt9) {
            	        case 1 :
            	            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:303:17: KEYWORD_AS talias2= tokenIdentifier
            	            {
            	            match(input,KEYWORD_AS,FOLLOW_KEYWORD_AS_in_selectStatement3298); if (state.failed) return stmt;

            	            pushFollow(FOLLOW_tokenIdentifier_in_selectStatement3304);
            	            talias2=tokenIdentifier();

            	            state._fsp--;
            	            if (state.failed) return stmt;

            	            if ( state.backtracking==0 ) {
            	            			stmt.setTableAlias(tableNumber-1, talias2);
            	            		}

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:310:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==KEYWORD_WHERE) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:311:17: KEYWORD_WHERE predicate1= abstractPredicate
                    {
                    match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement3399); if (state.failed) return stmt;

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement3419);
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
            if ( state.backtracking>0 ) { memoize(input, 8, selectStatement_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "selectStatement"



    // $ANTLR start "abstractPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:322:1: abstractPredicate returns [AbstractPredicate predicate] : predicate1= complexPredicateOr ;
    public final AbstractPredicate abstractPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int abstractPredicate_StartIndex = input.index();

        ComplexPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:323:2: (predicate1= complexPredicateOr )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:323:4: predicate1= complexPredicateOr
            {
            pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate3495);
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
            if ( state.backtracking>0 ) { memoize(input, 9, abstractPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "abstractPredicate"



    // $ANTLR start "complexPredicateOr"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:328:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
    public final ComplexPredicate complexPredicateOr() throws RecognitionException {
        ComplexPredicate predicateOr = null;

        int complexPredicateOr_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateOr = new ComplexPredicate(EnumPredicateType.OR_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return predicateOr; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:332:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:333:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:333:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:334:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
            {
            pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr3533);
            predicate1=complexPredicateAnd();

            state._fsp--;
            if (state.failed) return predicateOr;

            if ( state.backtracking==0 ) {
            			predicateOr.setPredicate1(predicate1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:337:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==KEYWORD_OR) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:338:4: KEYWORD_OR predicate2= complexPredicateAnd
            	    {
            	    match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr3544); if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addOr();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr3552);
            	    predicate2=complexPredicateAnd();

            	    state._fsp--;
            	    if (state.failed) return predicateOr;

            	    if ( state.backtracking==0 ) {
            	    				predicateOr.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop12;
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
            if ( state.backtracking>0 ) { memoize(input, 10, complexPredicateOr_StartIndex); }

        }
        return predicateOr;
    }
    // $ANTLR end "complexPredicateOr"



    // $ANTLR start "complexPredicateAnd"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:348:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
    public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
        ComplexPredicate predicateAnd = null;

        int complexPredicateAnd_StartIndex = input.index();

        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateAnd = new ComplexPredicate(EnumPredicateType.AND_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return predicateAnd; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:352:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:353:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:353:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:354:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
            {
            pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd3592);
            predicate1=complexPredicateNot();

            state._fsp--;
            if (state.failed) return predicateAnd;

            if ( state.backtracking==0 ) {
            			predicateAnd.setPredicate1(predicate1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:357:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==KEYWORD_AND) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:358:4: KEYWORD_AND predicate2= complexPredicateNot
            	    {
            	    match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd3603); if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addAnd();
            	    			}

            	    pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd3611);
            	    predicate2=complexPredicateNot();

            	    state._fsp--;
            	    if (state.failed) return predicateAnd;

            	    if ( state.backtracking==0 ) {
            	    				predicateAnd.addPredicate2(predicate2);
            	    			}

            	    }
            	    break;

            	default :
            	    break loop13;
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
            if ( state.backtracking>0 ) { memoize(input, 11, complexPredicateAnd_StartIndex); }

        }
        return predicateAnd;
    }
    // $ANTLR end "complexPredicateAnd"



    // $ANTLR start "complexPredicateNot"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:368:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
    public final ComplexPredicate complexPredicateNot() throws RecognitionException {
        ComplexPredicate predicateNot = null;

        int complexPredicateNot_StartIndex = input.index();

        AbstractPredicate predicate1 =null;



                	predicateNot = new ComplexPredicate(EnumPredicateType.NOT_PREDICATE);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return predicateNot; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:372:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:373:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:373:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:374:3: ( KEYWORD_NOT )? predicate1= complexPredicate
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:374:3: ( KEYWORD_NOT )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==KEYWORD_NOT) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:375:3: KEYWORD_NOT
                    {
                    match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot3652); if (state.failed) return predicateNot;

                    if ( state.backtracking==0 ) {
                    			predicateNot.negate();
                    		}

                    }
                    break;

            }


            pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot3665);
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
            if ( state.backtracking>0 ) { memoize(input, 12, complexPredicateNot_StartIndex); }

        }
        return predicateNot;
    }
    // $ANTLR end "complexPredicateNot"



    // $ANTLR start "complexPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:385:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
    public final AbstractPredicate complexPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int complexPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;

        SimplePredicate predicate2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:386:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:387:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:387:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==LPAREN) ) {
                int LA15_1 = input.LA(2);

                if ( (synpred20_FunSQL()) ) {
                    alt15=1;
                }
                else if ( (true) ) {
                    alt15=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return predicate;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA15_0==IDENTIFIER||(LA15_0 >= LITERAL_DATE && LA15_0 <= LITERAL_STRING)||LA15_0==MINUS||LA15_0==PLUS||LA15_0==QUOTE_DOUBLE) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return predicate;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:388:3: predicate1= parenPredicate
                    {
                    pushFollow(FOLLOW_parenPredicate_in_complexPredicate3692);
                    predicate1=parenPredicate();

                    state._fsp--;
                    if (state.failed) return predicate;

                    if ( state.backtracking==0 ) {
                    			predicate = predicate1;
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:393:3: predicate2= simplePredicate
                    {
                    pushFollow(FOLLOW_simplePredicate_in_complexPredicate3707);
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
            if ( state.backtracking>0 ) { memoize(input, 13, complexPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "complexPredicate"



    // $ANTLR start "parenPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:400:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
    public final AbstractPredicate parenPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;

        int parenPredicate_StartIndex = input.index();

        AbstractPredicate predicate1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:401:2: ( LPAREN predicate1= abstractPredicate RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:402:3: LPAREN predicate1= abstractPredicate RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate3732); if (state.failed) return predicate;

            pushFollow(FOLLOW_abstractPredicate_in_parenPredicate3739);
            predicate1=abstractPredicate();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
            			predicate = predicate1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate3745); if (state.failed) return predicate;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, parenPredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "parenPredicate"



    // $ANTLR start "simplePredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:409:1: simplePredicate returns [SimplePredicate predicate] : (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) ;
    public final SimplePredicate simplePredicate() throws RecognitionException {
        SimplePredicate predicate = null;

        int simplePredicate_StartIndex = input.index();

        AbstractExpression expr1 =null;

        FunSQLParser.tokenCompOperator_return comp =null;

        AbstractExpression expr2 =null;



                	predicate = new SimplePredicate();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return predicate; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:413:9: ( (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:414:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:414:9: (expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:415:3: expr1= abstractExpression comp= tokenCompOperator expr2= abstractExpression
            {
            pushFollow(FOLLOW_abstractExpression_in_simplePredicate3787);
            expr1=abstractExpression();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setExpr1(expr1);
                            }

            pushFollow(FOLLOW_tokenCompOperator_in_simplePredicate3842);
            comp=tokenCompOperator();

            state._fsp--;
            if (state.failed) return predicate;

            if ( state.backtracking==0 ) {
                            	predicate.setComp(EnumCompOperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
                            }

            pushFollow(FOLLOW_abstractExpression_in_simplePredicate3880);
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
            if ( state.backtracking>0 ) { memoize(input, 15, simplePredicate_StartIndex); }

        }
        return predicate;
    }
    // $ANTLR end "simplePredicate"



    // $ANTLR start "abstractExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:432:1: abstractExpression returns [AbstractExpression expression] : expression1= complexExpressionAdd ;
    public final AbstractExpression abstractExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int abstractExpression_StartIndex = input.index();

        ComplexExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:433:2: (expression1= complexExpressionAdd )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:433:4: expression1= complexExpressionAdd
            {
            pushFollow(FOLLOW_complexExpressionAdd_in_abstractExpression3933);
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
            if ( state.backtracking>0 ) { memoize(input, 16, abstractExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "abstractExpression"



    // $ANTLR start "complexExpressionAdd"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:438:1: complexExpressionAdd returns [ComplexExpression expression] : (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) ;
    public final ComplexExpression complexExpressionAdd() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionAdd_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenAddOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:442:2: ( (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:443:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:443:2: (expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:444:3: expression1= complexExpressionMult (op1= tokenAddOperator expression2= complexExpressionMult )*
            {
            pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd3963);
            expression1=complexExpressionMult();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:447:3: (op1= tokenAddOperator expression2= complexExpressionMult )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==MINUS||LA16_0==PLUS) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:448:4: op1= tokenAddOperator expression2= complexExpressionMult
            	    {
            	    pushFollow(FOLLOW_tokenAddOperator_in_complexExpressionAdd3976);
            	    op1=tokenAddOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionMult_in_complexExpressionAdd3984);
            	    expression2=complexExpressionMult();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
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
            if ( state.backtracking>0 ) { memoize(input, 17, complexExpressionAdd_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionAdd"



    // $ANTLR start "complexExpressionMult"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:458:1: complexExpressionMult returns [ComplexExpression expression] : (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) ;
    public final ComplexExpression complexExpressionMult() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionMult_StartIndex = input.index();

        ComplexExpression expression1 =null;

        FunSQLParser.tokenMultOperator_return op1 =null;

        ComplexExpression expression2 =null;



                	expression = new ComplexExpression(EnumExprType.MULT_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:462:2: ( (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:463:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:463:2: (expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:464:3: expression1= complexExpressionSigned (op1= tokenMultOperator expression2= complexExpressionSigned )*
            {
            pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult4024);
            expression1=complexExpressionSigned();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression.setExpr1(expression1);
            		}

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:467:3: (op1= tokenMultOperator expression2= complexExpressionSigned )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==DIV||LA17_0==MULT) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:468:4: op1= tokenMultOperator expression2= complexExpressionSigned
            	    {
            	    pushFollow(FOLLOW_tokenMultOperator_in_complexExpressionMult4037);
            	    op1=tokenMultOperator();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	                    		expression.addOp(EnumExprOperator.get((op1!=null?input.toString(op1.start,op1.stop):null)));
            	                    	}

            	    pushFollow(FOLLOW_complexExpressionSigned_in_complexExpressionMult4045);
            	    expression2=complexExpressionSigned();

            	    state._fsp--;
            	    if (state.failed) return expression;

            	    if ( state.backtracking==0 ) {
            	    				expression.addExpr2(expression2);
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
            if ( state.backtracking>0 ) { memoize(input, 18, complexExpressionMult_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionMult"



    // $ANTLR start "complexExpressionSigned"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:479:1: complexExpressionSigned returns [ComplexExpression expression] : ( ( MINUS | PLUS )? expression1= complexExpression ) ;
    public final ComplexExpression complexExpressionSigned() throws RecognitionException {
        ComplexExpression expression = null;

        int complexExpressionSigned_StartIndex = input.index();

        AbstractExpression expression1 =null;



                	expression = new ComplexExpression(EnumExprType.SIGNED_EXPRESSION);
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:483:2: ( ( ( MINUS | PLUS )? expression1= complexExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:484:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:484:2: ( ( MINUS | PLUS )? expression1= complexExpression )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:485:3: ( MINUS | PLUS )? expression1= complexExpression
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:485:3: ( MINUS | PLUS )?
            int alt18=3;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==MINUS) ) {
                alt18=1;
            }
            else if ( (LA18_0==PLUS) ) {
                alt18=2;
            }
            switch (alt18) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:486:3: MINUS
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_complexExpressionSigned4087); if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression.negate();
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:490:3: PLUS
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_complexExpressionSigned4096); if (state.failed) return expression;

                    }
                    break;

            }


            pushFollow(FOLLOW_complexExpression_in_complexExpressionSigned4108);
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
            if ( state.backtracking>0 ) { memoize(input, 19, complexExpressionSigned_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpressionSigned"



    // $ANTLR start "complexExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:498:1: complexExpression returns [AbstractExpression expression] : (expression1= parenExpression |expression2= simpleExpression ) ;
    public final AbstractExpression complexExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int complexExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;

        SimpleExpression expression2 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:499:2: ( (expression1= parenExpression |expression2= simpleExpression ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:500:2: (expression1= parenExpression |expression2= simpleExpression )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:500:2: (expression1= parenExpression |expression2= simpleExpression )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==LPAREN) ) {
                alt19=1;
            }
            else if ( (LA19_0==IDENTIFIER||(LA19_0 >= LITERAL_DATE && LA19_0 <= LITERAL_STRING)||LA19_0==QUOTE_DOUBLE) ) {
                alt19=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }
            switch (alt19) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:501:3: expression1= parenExpression
                    {
                    pushFollow(FOLLOW_parenExpression_in_complexExpression4143);
                    expression1=parenExpression();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                    			expression = expression1;
                    		}

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:506:3: expression2= simpleExpression
                    {
                    pushFollow(FOLLOW_simpleExpression_in_complexExpression4158);
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
            if ( state.backtracking>0 ) { memoize(input, 20, complexExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "complexExpression"



    // $ANTLR start "parenExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:513:1: parenExpression returns [AbstractExpression expression] : LPAREN expression1= abstractExpression RPAREN ;
    public final AbstractExpression parenExpression() throws RecognitionException {
        AbstractExpression expression = null;

        int parenExpression_StartIndex = input.index();

        AbstractExpression expression1 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:514:2: ( LPAREN expression1= abstractExpression RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:515:3: LPAREN expression1= abstractExpression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenExpression4183); if (state.failed) return expression;

            pushFollow(FOLLOW_abstractExpression_in_parenExpression4190);
            expression1=abstractExpression();

            state._fsp--;
            if (state.failed) return expression;

            if ( state.backtracking==0 ) {
            			expression = expression1;
            		}

            match(input,RPAREN,FOLLOW_RPAREN_in_parenExpression4196); if (state.failed) return expression;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 21, parenExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "parenExpression"



    // $ANTLR start "simpleExpression"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:522:1: simpleExpression returns [SimpleExpression expression] : ( (att1= tokenAttribute |lit1= tokenLiteral ) ) ;
    public final SimpleExpression simpleExpression() throws RecognitionException {
        SimpleExpression expression = null;

        int simpleExpression_StartIndex = input.index();

        TokenAttribute att1 =null;

        TokenLiteral lit1 =null;



                	expression = new SimpleExpression();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expression; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:526:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:527:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:527:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:528:3: (att1= tokenAttribute |lit1= tokenLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:528:3: (att1= tokenAttribute |lit1= tokenLiteral )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==IDENTIFIER||LA20_0==QUOTE_DOUBLE) ) {
                alt20=1;
            }
            else if ( ((LA20_0 >= LITERAL_DATE && LA20_0 <= LITERAL_STRING)) ) {
                alt20=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return expression;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:529:3: att1= tokenAttribute
                    {
                    pushFollow(FOLLOW_tokenAttribute_in_simpleExpression4250);
                    att1=tokenAttribute();

                    state._fsp--;
                    if (state.failed) return expression;

                    if ( state.backtracking==0 ) {
                                    	expression.setOper(att1);
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:534:17: lit1= tokenLiteral
                    {
                    pushFollow(FOLLOW_tokenLiteral_in_simpleExpression4306);
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
            if ( state.backtracking>0 ) { memoize(input, 22, simpleExpression_StartIndex); }

        }
        return expression;
    }
    // $ANTLR end "simpleExpression"



    // $ANTLR start "tokenAttribute"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:542:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenAttribute tokenAttribute() throws RecognitionException {
        TokenAttribute attribute = null;

        int tokenAttribute_StartIndex = input.index();

        TokenIdentifier table1 =null;

        TokenIdentifier id1 =null;



                	attribute = new TokenAttribute();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return attribute; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:546:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:547:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:547:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:548:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:548:17: (table1= tokenIdentifier DOT )?
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:549:17: table1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute4440);
                    table1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return attribute;

                    if ( state.backtracking==0 ) {
                                    	TokenTable table = new TokenTable();
                                    	table.setName(table1);
                                    	attribute.setTable(table);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenAttribute4460); if (state.failed) return attribute;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute4499);
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
            if ( state.backtracking>0 ) { memoize(input, 23, tokenAttribute_StartIndex); }

        }
        return attribute;
    }
    // $ANTLR end "tokenAttribute"



    // $ANTLR start "tokenTable"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:562:1: tokenTable returns [TokenTable table] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenTable tokenTable() throws RecognitionException {
        TokenTable table = null;

        int tokenTable_StartIndex = input.index();

        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	table = new TokenTable();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return table; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:566:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:567:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:567:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:568:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:568:17: (schema1= tokenIdentifier DOT )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==IDENTIFIER) ) {
                int LA22_1 = input.LA(2);

                if ( (LA22_1==DOT) ) {
                    alt22=1;
                }
            }
            else if ( (LA22_0==QUOTE_DOUBLE) ) {
                int LA22_2 = input.LA(2);

                if ( (LA22_2==IDENTIFIER) ) {
                    int LA22_5 = input.LA(3);

                    if ( (LA22_5==QUOTE_DOUBLE) ) {
                        int LA22_6 = input.LA(4);

                        if ( (LA22_6==DOT) ) {
                            alt22=1;
                        }
                    }
                }
            }
            switch (alt22) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:569:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable4607);
                    schema1=tokenIdentifier();

                    state._fsp--;
                    if (state.failed) return table;

                    if ( state.backtracking==0 ) {
                                            TokenSchema schema = new TokenSchema();
                                    	table.setSchema(schema);
                                    }

                    match(input,DOT,FOLLOW_DOT_in_tokenTable4627); if (state.failed) return table;

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenTable4666);
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
            if ( state.backtracking>0 ) { memoize(input, 24, tokenTable_StartIndex); }

        }
        return table;
    }
    // $ANTLR end "tokenTable"



    // $ANTLR start "tokenSchema"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:581:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
    public final TokenSchema tokenSchema() throws RecognitionException {
        TokenSchema schema = null;

        int tokenSchema_StartIndex = input.index();

        TokenIdentifier tokenIdentifier12 =null;



                	schema = new TokenSchema();
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return schema; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:585:9: ( ( tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:586:9: ( tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:586:9: ( tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:587:17: tokenIdentifier
            {
            pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema4746);
            tokenIdentifier12=tokenIdentifier();

            state._fsp--;
            if (state.failed) return schema;

            if ( state.backtracking==0 ) {
                            	schema.setName(tokenIdentifier12);
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
            if ( state.backtracking>0 ) { memoize(input, 25, tokenSchema_StartIndex); }

        }
        return schema;
    }
    // $ANTLR end "tokenSchema"



    // $ANTLR start "tokenIdentifier"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:593:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
    public final TokenIdentifier tokenIdentifier() throws RecognitionException {
        TokenIdentifier identifier = null;

        int tokenIdentifier_StartIndex = input.index();

        String identifierText13 =null;



                	identifier = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return identifier; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:597:9: ( ( identifierText ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:598:9: ( identifierText )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:598:9: ( identifierText )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:599:17: identifierText
            {
            pushFollow(FOLLOW_identifierText_in_tokenIdentifier4826);
            identifierText13=identifierText();

            state._fsp--;
            if (state.failed) return identifier;

            if ( state.backtracking==0 ) {
                            	identifier = new TokenIdentifier(identifierText13);
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
            if ( state.backtracking>0 ) { memoize(input, 26, tokenIdentifier_StartIndex); }

        }
        return identifier;
    }
    // $ANTLR end "tokenIdentifier"



    // $ANTLR start "tokenDataType"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:606:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER ) ;
    public final TokenDataType tokenDataType() throws RecognitionException {
        TokenDataType dataType = null;

        int tokenDataType_StartIndex = input.index();

        Token TYPE_VARCHAR14=null;
        Token TYPE_INTEGER15=null;


                	dataType = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return dataType; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:610:9: ( ( TYPE_VARCHAR | TYPE_INTEGER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:611:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:611:9: ( TYPE_VARCHAR | TYPE_INTEGER )
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:612:17: TYPE_VARCHAR
                    {
                    TYPE_VARCHAR14=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType4907); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_VARCHAR14!=null?TYPE_VARCHAR14.getText():null));
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:616:17: TYPE_INTEGER
                    {
                    TYPE_INTEGER15=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType4945); if (state.failed) return dataType;

                    if ( state.backtracking==0 ) {
                                    	dataType = new TokenDataType((TYPE_INTEGER15!=null?TYPE_INTEGER15.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 27, tokenDataType_StartIndex); }

        }
        return dataType;
    }
    // $ANTLR end "tokenDataType"



    // $ANTLR start "tokenLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:622:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
    public final TokenLiteral tokenLiteral() throws RecognitionException {
        TokenLiteral literal = null;

        int tokenLiteral_StartIndex = input.index();

        TokenIntegerLiteral tokenIntegerLiteral16 =null;

        TokenStringLiteral tokenStringLiteral17 =null;

        TokenDecimalLiteral tokenDecimalLiteral18 =null;

        TokenDateLiteral tokenDateLiteral19 =null;



                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:626:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:627:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:627:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:628:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:628:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:629:17: tokenIntegerLiteral
                    {
                    pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral5036);
                    tokenIntegerLiteral16=tokenIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenIntegerLiteral16;
                                    }

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:633:17: tokenStringLiteral
                    {
                    pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral5074);
                    tokenStringLiteral17=tokenStringLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenStringLiteral17;
                                    }

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:637:17: tokenDecimalLiteral
                    {
                    pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral5112);
                    tokenDecimalLiteral18=tokenDecimalLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenDecimalLiteral18;
                                    }

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:641:17: tokenDateLiteral
                    {
                    pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral5150);
                    tokenDateLiteral19=tokenDateLiteral();

                    state._fsp--;
                    if (state.failed) return literal;

                    if ( state.backtracking==0 ) {
                                    	literal = tokenDateLiteral19;
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
            if ( state.backtracking>0 ) { memoize(input, 28, tokenLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenLiteral"



    // $ANTLR start "tokenStringLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:648:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
    public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
        TokenStringLiteral literal = null;

        int tokenStringLiteral_StartIndex = input.index();

        Token lit1=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:652:9: ( (lit1= LITERAL_STRING ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:653:9: (lit1= LITERAL_STRING )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:653:9: (lit1= LITERAL_STRING )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:654:17: lit1= LITERAL_STRING
            {
            lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral5250); if (state.failed) return literal;

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
            if ( state.backtracking>0 ) { memoize(input, 29, tokenStringLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenStringLiteral"



    // $ANTLR start "tokenIntegerLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:660:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
    public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
        TokenIntegerLiteral literal = null;

        int tokenIntegerLiteral_StartIndex = input.index();

        Token LITERAL_INTEGER20=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:664:9: ( ( LITERAL_INTEGER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:665:9: ( LITERAL_INTEGER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:665:9: ( LITERAL_INTEGER )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:666:17: LITERAL_INTEGER
            {
            LITERAL_INTEGER20=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral5330); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenIntegerLiteral((LITERAL_INTEGER20!=null?LITERAL_INTEGER20.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 30, tokenIntegerLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenIntegerLiteral"



    // $ANTLR start "tokenDecimalLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:673:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
    public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
        TokenDecimalLiteral literal = null;

        int tokenDecimalLiteral_StartIndex = input.index();

        Token LITERAL_DECIMAL21=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:677:9: ( ( LITERAL_DECIMAL ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:678:9: ( LITERAL_DECIMAL )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:678:9: ( LITERAL_DECIMAL )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:679:17: LITERAL_DECIMAL
            {
            LITERAL_DECIMAL21=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral5420); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenDecimalLiteral((LITERAL_DECIMAL21!=null?LITERAL_DECIMAL21.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 31, tokenDecimalLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenDecimalLiteral"



    // $ANTLR start "tokenDateLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:685:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( LITERAL_DATE ) ;
    public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
        TokenDateLiteral literal = null;

        int tokenDateLiteral_StartIndex = input.index();

        Token LITERAL_DATE22=null;


                	literal = null;
                
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return literal; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:689:9: ( ( LITERAL_DATE ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:690:9: ( LITERAL_DATE )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:690:9: ( LITERAL_DATE )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:691:17: LITERAL_DATE
            {
            LITERAL_DATE22=(Token)match(input,LITERAL_DATE,FOLLOW_LITERAL_DATE_in_tokenDateLiteral5501); if (state.failed) return literal;

            if ( state.backtracking==0 ) {
                            	literal = new TokenDateLiteral((LITERAL_DATE22!=null?LITERAL_DATE22.getText():null));
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
            if ( state.backtracking>0 ) { memoize(input, 32, tokenDateLiteral_StartIndex); }

        }
        return literal;
    }
    // $ANTLR end "tokenDateLiteral"



    // $ANTLR start "identifierText"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:697:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) ;
    public final String identifierText() throws RecognitionException {
        String text = null;

        int identifierText_StartIndex = input.index();

        Token id1=null;
        Token id2=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return text; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:698:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:699:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:699:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
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
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:700:4: (id1= IDENTIFIER )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:700:4: (id1= IDENTIFIER )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:701:4: id1= IDENTIFIER
                    {
                    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText5562); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id1!=null?id1.getText():null).toUpperCase();
                                    }

                    }


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:706:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:706:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:707:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText5618); if (state.failed) return text;

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText5634); if (state.failed) return text;

                    if ( state.backtracking==0 ) {
                                    	text = (id2!=null?id2.getText():null);
                                    }

                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText5654); if (state.failed) return text;

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
            if ( state.backtracking>0 ) { memoize(input, 33, identifierText_StartIndex); }

        }
        return text;
    }
    // $ANTLR end "identifierText"


    public static class tokenAddOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenAddOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:716:1: tokenAddOperator : ( PLUS | MINUS ) ;
    public final FunSQLParser.tokenAddOperator_return tokenAddOperator() throws RecognitionException {
        FunSQLParser.tokenAddOperator_return retval = new FunSQLParser.tokenAddOperator_return();
        retval.start = input.LT(1);

        int tokenAddOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:717:5: ( ( PLUS | MINUS ) )
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
            if ( state.backtracking>0 ) { memoize(input, 34, tokenAddOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenAddOperator"


    public static class tokenMultOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenMultOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:724:1: tokenMultOperator : ( MULT | DIV ) ;
    public final FunSQLParser.tokenMultOperator_return tokenMultOperator() throws RecognitionException {
        FunSQLParser.tokenMultOperator_return retval = new FunSQLParser.tokenMultOperator_return();
        retval.start = input.LT(1);

        int tokenMultOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:725:5: ( ( MULT | DIV ) )
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
            if ( state.backtracking>0 ) { memoize(input, 35, tokenMultOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenMultOperator"


    public static class tokenCompOperator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenCompOperator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:732:1: tokenCompOperator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) ;
    public final FunSQLParser.tokenCompOperator_return tokenCompOperator() throws RecognitionException {
        FunSQLParser.tokenCompOperator_return retval = new FunSQLParser.tokenCompOperator_return();
        retval.start = input.LT(1);

        int tokenCompOperator_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:733:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) )
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
            if ( state.backtracking>0 ) { memoize(input, 36, tokenCompOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "tokenCompOperator"

    // $ANTLR start synpred20_FunSQL
    public final void synpred20_FunSQL_fragment() throws RecognitionException {
        AbstractPredicate predicate1 =null;


        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:388:3: (predicate1= parenPredicate )
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:388:3: predicate1= parenPredicate
        {
        pushFollow(FOLLOW_parenPredicate_in_synpred20_FunSQL3692);
        predicate1=parenPredicate();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred20_FunSQL

    // Delegated rules

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


 

    public static final BitSet FOLLOW_createSchemaStatement_in_statement1078 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_dropSchemaStatement_in_statement1133 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_createConnectionStatement_in_statement1188 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_dropConnectionStatement_in_statement1243 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_createTableStatement_in_statement1298 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_dropTableStatement_in_statement1353 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_selectStatement_in_statement1408 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
    public static final BitSet FOLLOW_SEMI_in_statement1462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1540 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1558 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1655 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1673 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1770 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1788 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement1806 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement1826 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1846 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement1866 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1886 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1906 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1926 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement1946 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1966 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2053 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2071 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2168 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2186 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2206 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement2226 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2246 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2284 = new BitSet(new long[]{0x0000000000002000L,0x0000000000040000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2338 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2358 = new BitSet(new long[]{0x0000000000000000L,0x0000000006000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2396 = new BitSet(new long[]{0x0000000000002000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement2451 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2487 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2507 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2546 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2548 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement2647 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement2665 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenTable_in_dropTableStatement2685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement2751 = new BitSet(new long[]{0x7C00000100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement2771 = new BitSet(new long[]{0x0000022000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement2826 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement2832 = new BitSet(new long[]{0x0000020000002000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement2908 = new BitSet(new long[]{0x7C00000100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_abstractExpression_in_selectStatement2928 = new BitSet(new long[]{0x0000022000002000L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement2983 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement2989 = new BitSet(new long[]{0x0000020000002000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement3066 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement3086 = new BitSet(new long[]{0x0010002000002002L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3142 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3148 = new BitSet(new long[]{0x0010000000002002L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement3224 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement3244 = new BitSet(new long[]{0x0010002000002002L});
    public static final BitSet FOLLOW_KEYWORD_AS_in_selectStatement3298 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_selectStatement3304 = new BitSet(new long[]{0x0010000000002002L});
    public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement3399 = new BitSet(new long[]{0x7C00080100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement3419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate3495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr3533 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr3544 = new BitSet(new long[]{0x7C00080100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr3552 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd3592 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd3603 = new BitSet(new long[]{0x7C00080100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd3611 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot3652 = new BitSet(new long[]{0x7C00000100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot3665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_complexPredicate3692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicate_in_complexPredicate3707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenPredicate3732 = new BitSet(new long[]{0x7C00080100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate3739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RPAREN_in_parenPredicate3745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate3787 = new BitSet(new long[]{0x038000000C400000L,0x0000000000000030L});
    public static final BitSet FOLLOW_tokenCompOperator_in_simplePredicate3842 = new BitSet(new long[]{0x7C00000100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_abstractExpression_in_simplePredicate3880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionAdd_in_abstractExpression3933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd3963 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000201L});
    public static final BitSet FOLLOW_tokenAddOperator_in_complexExpressionAdd3976 = new BitSet(new long[]{0x7C00000100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_complexExpressionMult_in_complexExpressionAdd3984 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000201L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult4024 = new BitSet(new long[]{0x0000000000020002L,0x0000000000000004L});
    public static final BitSet FOLLOW_tokenMultOperator_in_complexExpressionMult4037 = new BitSet(new long[]{0x7C00000100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_complexExpressionSigned_in_complexExpressionMult4045 = new BitSet(new long[]{0x0000000000020002L,0x0000000000000004L});
    public static final BitSet FOLLOW_MINUS_in_complexExpressionSigned4087 = new BitSet(new long[]{0x7C00000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_PLUS_in_complexExpressionSigned4096 = new BitSet(new long[]{0x7C00000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_complexExpression_in_complexExpressionSigned4108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenExpression_in_complexExpression4143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simpleExpression_in_complexExpression4158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenExpression4183 = new BitSet(new long[]{0x7C00000100000000L,0x0000000000002201L});
    public static final BitSet FOLLOW_abstractExpression_in_parenExpression4190 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RPAREN_in_parenExpression4196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simpleExpression4250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenLiteral_in_simpleExpression4306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute4440 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenAttribute4460 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute4499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable4607 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenTable4627 = new BitSet(new long[]{0x0000000100000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable4666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema4746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierText_in_tokenIdentifier4826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType4907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType4945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral5036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral5074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral5112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral5150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral5250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral5330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral5420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DATE_in_tokenDateLiteral5501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText5562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText5618 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText5634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText5654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_synpred20_FunSQL3692 = new BitSet(new long[]{0x0000000000000002L});

}