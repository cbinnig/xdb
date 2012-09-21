// $ANTLR 3.4 /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g 2012-09-21 14:50:35
 
package org.xdb.funsql.compile.antlr; 

import org.xdb.funsql.compile.predicate.*;
import org.xdb.funsql.compile.tokens.*;
import org.xdb.funsql.statement.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class FunSQLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", "AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", "D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", "F", "G", "GREATER_EQUAL1", "GREATER_EQUAL2", "GREATER_THAN", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", "KEYWORD_AND", "KEYWORD_CONNECTION", "KEYWORD_CREATE", "KEYWORD_DROP", "KEYWORD_FROM", "KEYWORD_IN", "KEYWORD_NOT", "KEYWORD_OR", "KEYWORD_PASSWD", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_WHERE", "L", "LBRACKET", "LESS_EQUAL1", "LESS_EQUAL2", "LESS_THAN", "LITERAL_DATE", "LITERAL_DECIMAL", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", "MINUS", "MOD", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", "QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", "SHIFT_RIGHT", "STAR", "T", "TILDE", "TYPE_INTEGER", "TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
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
    public static final int KEYWORD_CONNECTION=37;
    public static final int KEYWORD_CREATE=38;
    public static final int KEYWORD_DROP=39;
    public static final int KEYWORD_FROM=40;
    public static final int KEYWORD_IN=41;
    public static final int KEYWORD_NOT=42;
    public static final int KEYWORD_OR=43;
    public static final int KEYWORD_PASSWD=44;
    public static final int KEYWORD_SCHEMA=45;
    public static final int KEYWORD_SELECT=46;
    public static final int KEYWORD_STORE=47;
    public static final int KEYWORD_TABLE=48;
    public static final int KEYWORD_URL=49;
    public static final int KEYWORD_USER=50;
    public static final int KEYWORD_WHERE=51;
    public static final int L=52;
    public static final int LBRACKET=53;
    public static final int LESS_EQUAL1=54;
    public static final int LESS_EQUAL2=55;
    public static final int LESS_THAN=56;
    public static final int LITERAL_DATE=57;
    public static final int LITERAL_DECIMAL=58;
    public static final int LITERAL_INTEGER=59;
    public static final int LITERAL_STRING=60;
    public static final int LPAREN=61;
    public static final int M=62;
    public static final int MINUS=63;
    public static final int MOD=64;
    public static final int N=65;
    public static final int NOT_EQUAL1=66;
    public static final int NOT_EQUAL2=67;
    public static final int O=68;
    public static final int P=69;
    public static final int PIPE=70;
    public static final int PLUS=71;
    public static final int Q=72;
    public static final int QUESTION=73;
    public static final int QUOTED_STRING=74;
    public static final int QUOTE_DOUBLE=75;
    public static final int QUOTE_SINGLE=76;
    public static final int QUOTE_TRIPLE=77;
    public static final int R=78;
    public static final int RBRACKET=79;
    public static final int RPAREN=80;
    public static final int S=81;
    public static final int SEMI=82;
    public static final int SHIFT_LEFT=83;
    public static final int SHIFT_RIGHT=84;
    public static final int STAR=85;
    public static final int T=86;
    public static final int TILDE=87;
    public static final int TYPE_INTEGER=88;
    public static final int TYPE_VARCHAR=89;
    public static final int U=90;
    public static final int UNDERSCORE=91;
    public static final int V=92;
    public static final int W=93;
    public static final int WS=94;
    public static final int X=95;
    public static final int Y=96;
    public static final int Z=97;

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
    }

    public String[] getTokenNames() { return FunSQLParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g"; }



    // $ANTLR start "statement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:61:1: statement returns [AbstractServerStmt stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )? ) ;
    public final AbstractServerStmt statement() throws RecognitionException {
        AbstractServerStmt stmt = null;


        CreateSchemaStmt createSchemaStatement1 =null;

        DropSchemaStmt dropSchemaStatement2 =null;

        CreateConnectionStmt createConnectionStatement3 =null;

        DropConnectionStmt dropConnectionStatement4 =null;

        CreateTableStmt createTableStatement5 =null;

        DropTableStmt dropTableStatement6 =null;

        SelectStmt selectStatement7 =null;



                	stmt = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:65:9: ( ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )? ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:66:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )? )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:66:9: ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )? )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:67:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )?
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:67:10: ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement )
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
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }

            switch (alt1) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:68:17: createSchemaStatement
                    {
                    pushFollow(FOLLOW_createSchemaStatement_in_statement1055);
                    createSchemaStatement1=createSchemaStatement();

                    state._fsp--;



                                    	stmt = createSchemaStatement1;
                                    

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:73:17: dropSchemaStatement
                    {
                    pushFollow(FOLLOW_dropSchemaStatement_in_statement1110);
                    dropSchemaStatement2=dropSchemaStatement();

                    state._fsp--;



                                    	stmt = dropSchemaStatement2;
                                    

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:78:17: createConnectionStatement
                    {
                    pushFollow(FOLLOW_createConnectionStatement_in_statement1165);
                    createConnectionStatement3=createConnectionStatement();

                    state._fsp--;



                                    	stmt = createConnectionStatement3;
                                    

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:83:17: dropConnectionStatement
                    {
                    pushFollow(FOLLOW_dropConnectionStatement_in_statement1220);
                    dropConnectionStatement4=dropConnectionStatement();

                    state._fsp--;



                                    	stmt = dropConnectionStatement4;
                                    

                    }
                    break;
                case 5 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:88:17: createTableStatement
                    {
                    pushFollow(FOLLOW_createTableStatement_in_statement1275);
                    createTableStatement5=createTableStatement();

                    state._fsp--;



                                    	stmt = createTableStatement5;
                                    

                    }
                    break;
                case 6 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:93:17: dropTableStatement
                    {
                    pushFollow(FOLLOW_dropTableStatement_in_statement1330);
                    dropTableStatement6=dropTableStatement();

                    state._fsp--;



                                    	stmt = dropTableStatement6;
                                    

                    }
                    break;
                case 7 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:98:17: selectStatement
                    {
                    pushFollow(FOLLOW_selectStatement_in_statement1385);
                    selectStatement7=selectStatement();

                    state._fsp--;



                                    	stmt = selectStatement7;
                                    

                    }
                    break;

            }


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:103:17: ( SEMI )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==SEMI) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:103:17: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_statement1439); 

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
        }
        return stmt;
    }
    // $ANTLR end "statement"



    // $ANTLR start "createSchemaStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:107:1: createSchemaStatement returns [CreateSchemaStmt stmt] : ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) ;
    public final CreateSchemaStmt createSchemaStatement() throws RecognitionException {
        CreateSchemaStmt stmt = null;


        TokenSchema tokenSchema8 =null;



                	stmt = new CreateSchemaStmt();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:111:9: ( ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:112:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:112:9: ( KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:113:17: KEYWORD_CREATE KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1517); 

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1535); 

            pushFollow(FOLLOW_tokenSchema_in_createSchemaStatement1553);
            tokenSchema8=tokenSchema();

            state._fsp--;



                            	stmt.setSchema(tokenSchema8);
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "createSchemaStatement"



    // $ANTLR start "dropSchemaStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:121:1: dropSchemaStatement returns [DropSchemaStmt stmt] : ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) ;
    public final DropSchemaStmt dropSchemaStatement() throws RecognitionException {
        DropSchemaStmt stmt = null;


        TokenSchema tokenSchema9 =null;



                	stmt = new DropSchemaStmt();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:125:9: ( ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:126:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:126:9: ( KEYWORD_DROP KEYWORD_SCHEMA tokenSchema )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:127:17: KEYWORD_DROP KEYWORD_SCHEMA tokenSchema
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1632); 

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1650); 

            pushFollow(FOLLOW_tokenSchema_in_dropSchemaStatement1668);
            tokenSchema9=tokenSchema();

            state._fsp--;



                            	stmt.setSchema(tokenSchema9);
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "dropSchemaStatement"



    // $ANTLR start "createConnectionStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:135:1: createConnectionStatement returns [CreateConnectionStmt stmt] : ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) ;
    public final CreateConnectionStmt createConnectionStatement() throws RecognitionException {
        CreateConnectionStmt stmt = null;


        TokenStringLiteral litURL =null;

        TokenStringLiteral litUser =null;

        TokenStringLiteral litPasswd =null;

        TokenStringLiteral litStore =null;

        TokenIdentifier tokenIdentifier10 =null;



                	stmt = new CreateConnectionStmt();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:139:9: ( ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:140:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:140:9: ( KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:141:17: KEYWORD_CREATE KEYWORD_CONNECTION tokenIdentifier KEYWORD_URL litURL= tokenStringLiteral KEYWORD_USER litUser= tokenStringLiteral KEYWORD_PASSWD litPasswd= tokenStringLiteral KEYWORD_STORE litStore= tokenStringLiteral
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1747); 

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1765); 

            pushFollow(FOLLOW_tokenIdentifier_in_createConnectionStatement1783);
            tokenIdentifier10=tokenIdentifier();

            state._fsp--;



                            	stmt.setConnection(tokenIdentifier10);
                            

            match(input,KEYWORD_URL,FOLLOW_KEYWORD_URL_in_createConnectionStatement1803); 

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1823);
            litURL=tokenStringLiteral();

            state._fsp--;



                            	stmt.setURL(litURL);
                            

            match(input,KEYWORD_USER,FOLLOW_KEYWORD_USER_in_createConnectionStatement1843); 

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1863);
            litUser=tokenStringLiteral();

            state._fsp--;



                            	stmt.setUser(litUser);
                            

            match(input,KEYWORD_PASSWD,FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1883); 

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1903);
            litPasswd=tokenStringLiteral();

            state._fsp--;



                            	stmt.setPasswd(litPasswd);
                            

            match(input,KEYWORD_STORE,FOLLOW_KEYWORD_STORE_in_createConnectionStatement1923); 

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1943);
            litStore=tokenStringLiteral();

            state._fsp--;



                            	stmt.setStore(litStore);
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "createConnectionStatement"



    // $ANTLR start "dropConnectionStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:165:1: dropConnectionStatement returns [DropConnectionStmt stmt] : ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) ;
    public final DropConnectionStmt dropConnectionStatement() throws RecognitionException {
        DropConnectionStmt stmt = null;


        TokenIdentifier tokenIdentifier11 =null;



                	stmt = new DropConnectionStmt();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:169:9: ( ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:170:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:170:9: ( KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:171:17: KEYWORD_DROP KEYWORD_CONNECTION tokenIdentifier
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2030); 

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2048); 

            pushFollow(FOLLOW_tokenIdentifier_in_dropConnectionStatement2066);
            tokenIdentifier11=tokenIdentifier();

            state._fsp--;



                            	stmt.setConnection(tokenIdentifier11);
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "dropConnectionStatement"



    // $ANTLR start "createTableStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:179:1: createTableStatement returns [CreateTableStmt stmt] : ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) ;
    public final CreateTableStmt createTableStatement() throws RecognitionException {
        CreateTableStmt stmt = null;


        TokenTable table1 =null;

        String att1 =null;

        TokenDataType dataType1 =null;

        String att2 =null;

        TokenDataType dataType2 =null;

        TokenTable table2 =null;

        TokenIdentifier connection1 =null;



                	stmt = new CreateTableStmt();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:183:9: ( ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:184:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:184:9: ( KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:185:17: KEYWORD_CREATE KEYWORD_TABLE table1= tokenTable LPAREN att1= identifierText dataType1= tokenDataType ( COMMA att2= identifierText dataType2= tokenDataType )* RPAREN ( KEYWORD_FROM table2= tokenTable )? KEYWORD_IN KEYWORD_CONNECTION connection1= tokenIdentifier
            {
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2145); 

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2163); 

            pushFollow(FOLLOW_tokenTable_in_createTableStatement2183);
            table1=tokenTable();

            state._fsp--;



                            	stmt.setTable(table1);
                            	stmt.setSourceTable(table1);
                            

            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2203); 

            pushFollow(FOLLOW_identifierText_in_createTableStatement2223);
            att1=identifierText();

            state._fsp--;



                            	stmt.addAttribute(att1);
                            

            pushFollow(FOLLOW_tokenDataType_in_createTableStatement2261);
            dataType1=tokenDataType();

            state._fsp--;



                            	stmt.addType(dataType1);
                            

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:200:17: ( COMMA att2= identifierText dataType2= tokenDataType )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==COMMA) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:201:17: COMMA att2= identifierText dataType2= tokenDataType
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2315); 

            	    pushFollow(FOLLOW_identifierText_in_createTableStatement2335);
            	    att2=identifierText();

            	    state._fsp--;



            	                    	stmt.addAttribute(att2);
            	                    

            	    pushFollow(FOLLOW_tokenDataType_in_createTableStatement2373);
            	    dataType2=tokenDataType();

            	    state._fsp--;



            	                    	stmt.addType(dataType2);
            	                    

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2428); 

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:212:17: ( KEYWORD_FROM table2= tokenTable )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==KEYWORD_FROM) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:213:17: KEYWORD_FROM table2= tokenTable
                    {
                    match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_createTableStatement2464); 

                    pushFollow(FOLLOW_tokenTable_in_createTableStatement2484);
                    table2=tokenTable();

                    state._fsp--;



                                    	stmt.setSourceTable(table2);
                                    

                    }
                    break;

            }


            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2523); 

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2525); 

            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2545);
            connection1=tokenIdentifier();

            state._fsp--;



                            	stmt.setConnection(connection1);
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "createTableStatement"



    // $ANTLR start "dropTableStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:225:1: dropTableStatement returns [DropTableStmt stmt] : ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) ;
    public final DropTableStmt dropTableStatement() throws RecognitionException {
        DropTableStmt stmt = null;


        TokenTable table1 =null;



                	stmt = new DropTableStmt();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:229:9: ( ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:230:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:230:9: ( KEYWORD_DROP KEYWORD_TABLE table1= tokenTable )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:231:17: KEYWORD_DROP KEYWORD_TABLE table1= tokenTable
            {
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement2624); 

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement2642); 

            pushFollow(FOLLOW_tokenTable_in_dropTableStatement2662);
            table1=tokenTable();

            state._fsp--;



                            	stmt.setTable(table1);
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return stmt;
    }
    // $ANTLR end "dropTableStatement"



    // $ANTLR start "selectStatement"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:239:1: selectStatement returns [SelectStmt stmt] : ( KEYWORD_SELECT att1= tokenAttribute ( COMMA att2= tokenAttribute )* KEYWORD_FROM table1= tokenTable ( COMMA table2= tokenTable )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ) ;
    public final SelectStmt selectStatement() throws RecognitionException {
        SelectStmt stmt = null;


        TokenAttribute att1 =null;

        TokenAttribute att2 =null;

        TokenTable table1 =null;

        TokenTable table2 =null;

        AbstractPredicate predicate1 =null;



                	stmt = new SelectStmt();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:243:9: ( ( KEYWORD_SELECT att1= tokenAttribute ( COMMA att2= tokenAttribute )* KEYWORD_FROM table1= tokenTable ( COMMA table2= tokenTable )* ( KEYWORD_WHERE predicate1= abstractPredicate )? ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:244:9: ( KEYWORD_SELECT att1= tokenAttribute ( COMMA att2= tokenAttribute )* KEYWORD_FROM table1= tokenTable ( COMMA table2= tokenTable )* ( KEYWORD_WHERE predicate1= abstractPredicate )? )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:244:9: ( KEYWORD_SELECT att1= tokenAttribute ( COMMA att2= tokenAttribute )* KEYWORD_FROM table1= tokenTable ( COMMA table2= tokenTable )* ( KEYWORD_WHERE predicate1= abstractPredicate )? )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:245:17: KEYWORD_SELECT att1= tokenAttribute ( COMMA att2= tokenAttribute )* KEYWORD_FROM table1= tokenTable ( COMMA table2= tokenTable )* ( KEYWORD_WHERE predicate1= abstractPredicate )?
            {
            match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement2728); 

            pushFollow(FOLLOW_tokenAttribute_in_selectStatement2748);
            att1=tokenAttribute();

            state._fsp--;



                            	stmt.addAttribute(att1);
                            

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:250:17: ( COMMA att2= tokenAttribute )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==COMMA) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:251:17: COMMA att2= tokenAttribute
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement2802); 

            	    pushFollow(FOLLOW_tokenAttribute_in_selectStatement2822);
            	    att2=tokenAttribute();

            	    state._fsp--;



            	                    	stmt.addAttribute(att2);
            	                    

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement2877); 

            pushFollow(FOLLOW_tokenTable_in_selectStatement2897);
            table1=tokenTable();

            state._fsp--;



                            	stmt.addTable(table1);
                            

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:262:17: ( COMMA table2= tokenTable )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COMMA) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:263:17: COMMA table2= tokenTable
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement2952); 

            	    pushFollow(FOLLOW_tokenTable_in_selectStatement2972);
            	    table2=tokenTable();

            	    state._fsp--;



            	                    	stmt.addTable(table2);
            	                    

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:269:17: ( KEYWORD_WHERE predicate1= abstractPredicate )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==KEYWORD_WHERE) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:270:17: KEYWORD_WHERE predicate1= abstractPredicate
                    {
                    match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement3045); 

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement3065);
                    predicate1=abstractPredicate();

                    state._fsp--;



                                    	stmt.setPredicate(predicate1);
                                    

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
        }
        return stmt;
    }
    // $ANTLR end "selectStatement"



    // $ANTLR start "abstractPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:281:1: abstractPredicate returns [AbstractPredicate predicate] : predicate1= complexPredicateOr ;
    public final AbstractPredicate abstractPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;


        ComplexPredicate predicate1 =null;


        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:282:2: (predicate1= complexPredicateOr )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:282:4: predicate1= complexPredicateOr
            {
            pushFollow(FOLLOW_complexPredicateOr_in_abstractPredicate3141);
            predicate1=complexPredicateOr();

            state._fsp--;



            			predicate = predicate1;
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return predicate;
    }
    // $ANTLR end "abstractPredicate"



    // $ANTLR start "complexPredicateOr"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:287:1: complexPredicateOr returns [ComplexPredicate predicateOr] : (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) ;
    public final ComplexPredicate complexPredicateOr() throws RecognitionException {
        ComplexPredicate predicateOr = null;


        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateOr = new ComplexPredicate(EnumBoolOperator.SQL_OR);
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:291:2: ( (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:292:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:292:2: (predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:293:3: predicate1= complexPredicateAnd ( KEYWORD_OR predicate2= complexPredicateAnd )*
            {
            pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr3179);
            predicate1=complexPredicateAnd();

            state._fsp--;



            			predicateOr.setPredicate1(predicate1);
            		

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:296:3: ( KEYWORD_OR predicate2= complexPredicateAnd )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==KEYWORD_OR) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:297:4: KEYWORD_OR predicate2= complexPredicateAnd
            	    {
            	    match(input,KEYWORD_OR,FOLLOW_KEYWORD_OR_in_complexPredicateOr3190); 

            	    pushFollow(FOLLOW_complexPredicateAnd_in_complexPredicateOr3198);
            	    predicate2=complexPredicateAnd();

            	    state._fsp--;



            	    				predicateOr.setPredicate2(predicate2);
            	    			

            	    }
            	    break;

            	default :
            	    break loop8;
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
        }
        return predicateOr;
    }
    // $ANTLR end "complexPredicateOr"



    // $ANTLR start "complexPredicateAnd"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:305:1: complexPredicateAnd returns [ComplexPredicate predicateAnd] : (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) ;
    public final ComplexPredicate complexPredicateAnd() throws RecognitionException {
        ComplexPredicate predicateAnd = null;


        ComplexPredicate predicate1 =null;

        ComplexPredicate predicate2 =null;



                	predicateAnd = new ComplexPredicate(EnumBoolOperator.SQL_AND);
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:309:2: ( (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:310:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:310:2: (predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:311:3: predicate1= complexPredicateNot ( KEYWORD_AND predicate2= complexPredicateNot )*
            {
            pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd3238);
            predicate1=complexPredicateNot();

            state._fsp--;



            			predicateAnd.setPredicate1(predicate1);
            		

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:314:3: ( KEYWORD_AND predicate2= complexPredicateNot )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==KEYWORD_AND) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:315:4: KEYWORD_AND predicate2= complexPredicateNot
            	    {
            	    match(input,KEYWORD_AND,FOLLOW_KEYWORD_AND_in_complexPredicateAnd3249); 

            	    pushFollow(FOLLOW_complexPredicateNot_in_complexPredicateAnd3257);
            	    predicate2=complexPredicateNot();

            	    state._fsp--;



            	    				predicateAnd.setPredicate2(predicate2);
            	    			

            	    }
            	    break;

            	default :
            	    break loop9;
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
        }
        return predicateAnd;
    }
    // $ANTLR end "complexPredicateAnd"



    // $ANTLR start "complexPredicateNot"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:323:1: complexPredicateNot returns [ComplexPredicate predicateNot] : ( ( KEYWORD_NOT )? predicate1= complexPredicate ) ;
    public final ComplexPredicate complexPredicateNot() throws RecognitionException {
        ComplexPredicate predicateNot = null;


        AbstractPredicate predicate1 =null;



                	predicateNot = new ComplexPredicate();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:327:2: ( ( ( KEYWORD_NOT )? predicate1= complexPredicate ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:328:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:328:2: ( ( KEYWORD_NOT )? predicate1= complexPredicate )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:329:3: ( KEYWORD_NOT )? predicate1= complexPredicate
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:329:3: ( KEYWORD_NOT )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==KEYWORD_NOT) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:330:3: KEYWORD_NOT
                    {
                    match(input,KEYWORD_NOT,FOLLOW_KEYWORD_NOT_in_complexPredicateNot3298); 


                    			predicateNot.negate();
                    		

                    }
                    break;

            }


            pushFollow(FOLLOW_complexPredicate_in_complexPredicateNot3311);
            predicate1=complexPredicate();

            state._fsp--;



            			predicateNot.setPredicate1(predicate1);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return predicateNot;
    }
    // $ANTLR end "complexPredicateNot"



    // $ANTLR start "complexPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:340:1: complexPredicate returns [AbstractPredicate predicate] : (predicate1= parenPredicate |predicate2= simplePredicate ) ;
    public final AbstractPredicate complexPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;


        AbstractPredicate predicate1 =null;

        SimplePredicate predicate2 =null;


        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:341:2: ( (predicate1= parenPredicate |predicate2= simplePredicate ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:342:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:342:2: (predicate1= parenPredicate |predicate2= simplePredicate )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==LPAREN) ) {
                alt11=1;
            }
            else if ( (LA11_0==IDENTIFIER||(LA11_0 >= LITERAL_DATE && LA11_0 <= LITERAL_STRING)||LA11_0==QUOTE_DOUBLE) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:343:3: predicate1= parenPredicate
                    {
                    pushFollow(FOLLOW_parenPredicate_in_complexPredicate3338);
                    predicate1=parenPredicate();

                    state._fsp--;



                    			predicate = predicate1;
                    		

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:348:3: predicate2= simplePredicate
                    {
                    pushFollow(FOLLOW_simplePredicate_in_complexPredicate3353);
                    predicate2=simplePredicate();

                    state._fsp--;



                    			predicate = predicate2;
                    		

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
        }
        return predicate;
    }
    // $ANTLR end "complexPredicate"



    // $ANTLR start "parenPredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:355:1: parenPredicate returns [AbstractPredicate predicate] : LPAREN predicate1= abstractPredicate RPAREN ;
    public final AbstractPredicate parenPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;


        AbstractPredicate predicate1 =null;


        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:356:2: ( LPAREN predicate1= abstractPredicate RPAREN )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:357:3: LPAREN predicate1= abstractPredicate RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_parenPredicate3378); 

            pushFollow(FOLLOW_abstractPredicate_in_parenPredicate3385);
            predicate1=abstractPredicate();

            state._fsp--;



            			predicate = predicate1;
            		

            match(input,RPAREN,FOLLOW_RPAREN_in_parenPredicate3391); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return predicate;
    }
    // $ANTLR end "parenPredicate"



    // $ANTLR start "simplePredicate"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:364:1: simplePredicate returns [SimplePredicate predicate] : ( (att1= tokenAttribute |lit1= tokenLiteral ) comp= tokenComparator (att2= tokenAttribute |lit2= tokenLiteral ) ) ;
    public final SimplePredicate simplePredicate() throws RecognitionException {
        SimplePredicate predicate = null;


        TokenAttribute att1 =null;

        TokenLiteral lit1 =null;

        FunSQLParser.tokenComparator_return comp =null;

        TokenAttribute att2 =null;

        TokenLiteral lit2 =null;



                	predicate = new SimplePredicate();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:368:9: ( ( (att1= tokenAttribute |lit1= tokenLiteral ) comp= tokenComparator (att2= tokenAttribute |lit2= tokenLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:369:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) comp= tokenComparator (att2= tokenAttribute |lit2= tokenLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:369:9: ( (att1= tokenAttribute |lit1= tokenLiteral ) comp= tokenComparator (att2= tokenAttribute |lit2= tokenLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:370:3: (att1= tokenAttribute |lit1= tokenLiteral ) comp= tokenComparator (att2= tokenAttribute |lit2= tokenLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:370:3: (att1= tokenAttribute |lit1= tokenLiteral )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==IDENTIFIER||LA12_0==QUOTE_DOUBLE) ) {
                alt12=1;
            }
            else if ( ((LA12_0 >= LITERAL_DATE && LA12_0 <= LITERAL_STRING)) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:371:3: att1= tokenAttribute
                    {
                    pushFollow(FOLLOW_tokenAttribute_in_simplePredicate3437);
                    att1=tokenAttribute();

                    state._fsp--;



                                    	predicate.setOper1(att1);
                                    

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:376:17: lit1= tokenLiteral
                    {
                    pushFollow(FOLLOW_tokenLiteral_in_simplePredicate3493);
                    lit1=tokenLiteral();

                    state._fsp--;



                                    	predicate.setOper1(lit1);
                                    

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenComparator_in_simplePredicate3566);
            comp=tokenComparator();

            state._fsp--;



                            	predicate.setComp(EnumComperator.get((comp!=null?input.toString(comp.start,comp.stop):null)));
                            

            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:386:17: (att2= tokenAttribute |lit2= tokenLiteral )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==IDENTIFIER||LA13_0==QUOTE_DOUBLE) ) {
                alt13=1;
            }
            else if ( ((LA13_0 >= LITERAL_DATE && LA13_0 <= LITERAL_STRING)) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:387:17: att2= tokenAttribute
                    {
                    pushFollow(FOLLOW_tokenAttribute_in_simplePredicate3622);
                    att2=tokenAttribute();

                    state._fsp--;



                                    	predicate.setOper2(att2);
                                    

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:392:17: lit2= tokenLiteral
                    {
                    pushFollow(FOLLOW_tokenLiteral_in_simplePredicate3678);
                    lit2=tokenLiteral();

                    state._fsp--;



                                    	predicate.setOper2(lit2);
                                    

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
        }
        return predicate;
    }
    // $ANTLR end "simplePredicate"



    // $ANTLR start "tokenAttribute"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:400:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenAttribute tokenAttribute() throws RecognitionException {
        TokenAttribute attribute = null;


        TokenIdentifier table1 =null;

        TokenIdentifier id1 =null;



                	attribute = new TokenAttribute();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:404:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:405:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:405:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:406:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:406:17: (table1= tokenIdentifier DOT )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==IDENTIFIER) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==DOT) ) {
                    alt14=1;
                }
            }
            else if ( (LA14_0==QUOTE_DOUBLE) ) {
                int LA14_2 = input.LA(2);

                if ( (LA14_2==IDENTIFIER) ) {
                    int LA14_5 = input.LA(3);

                    if ( (LA14_5==QUOTE_DOUBLE) ) {
                        int LA14_6 = input.LA(4);

                        if ( (LA14_6==DOT) ) {
                            alt14=1;
                        }
                    }
                }
            }
            switch (alt14) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:407:17: table1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute3812);
                    table1=tokenIdentifier();

                    state._fsp--;



                                    	TokenTable table = new TokenTable();
                                    	table.setName(table1);
                                    	attribute.setTable(table);
                                    

                    match(input,DOT,FOLLOW_DOT_in_tokenAttribute3832); 

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute3871);
            id1=tokenIdentifier();

            state._fsp--;



                            	attribute.setName(id1);
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return attribute;
    }
    // $ANTLR end "tokenAttribute"



    // $ANTLR start "tokenTable"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:420:1: tokenTable returns [TokenTable table] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenTable tokenTable() throws RecognitionException {
        TokenTable table = null;


        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	table = new TokenTable();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:424:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:425:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:425:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:426:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:426:17: (schema1= tokenIdentifier DOT )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==IDENTIFIER) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==DOT) ) {
                    alt15=1;
                }
            }
            else if ( (LA15_0==QUOTE_DOUBLE) ) {
                int LA15_2 = input.LA(2);

                if ( (LA15_2==IDENTIFIER) ) {
                    int LA15_5 = input.LA(3);

                    if ( (LA15_5==QUOTE_DOUBLE) ) {
                        int LA15_6 = input.LA(4);

                        if ( (LA15_6==DOT) ) {
                            alt15=1;
                        }
                    }
                }
            }
            switch (alt15) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:427:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable3979);
                    schema1=tokenIdentifier();

                    state._fsp--;



                                            TokenSchema schema = new TokenSchema();
                                    	table.setSchema(schema);
                                    

                    match(input,DOT,FOLLOW_DOT_in_tokenTable3999); 

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenTable4038);
            id1=tokenIdentifier();

            state._fsp--;



                            	table.setName(id1);
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return table;
    }
    // $ANTLR end "tokenTable"



    // $ANTLR start "tokenSchema"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:439:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
    public final TokenSchema tokenSchema() throws RecognitionException {
        TokenSchema schema = null;


        TokenIdentifier tokenIdentifier12 =null;



                	schema = new TokenSchema();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:443:9: ( ( tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:444:9: ( tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:444:9: ( tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:445:17: tokenIdentifier
            {
            pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema4118);
            tokenIdentifier12=tokenIdentifier();

            state._fsp--;



                            	schema.setName(tokenIdentifier12);
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return schema;
    }
    // $ANTLR end "tokenSchema"



    // $ANTLR start "tokenIdentifier"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:451:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
    public final TokenIdentifier tokenIdentifier() throws RecognitionException {
        TokenIdentifier identifier = null;


        String identifierText13 =null;



                	identifier = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:455:9: ( ( identifierText ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:456:9: ( identifierText )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:456:9: ( identifierText )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:457:17: identifierText
            {
            pushFollow(FOLLOW_identifierText_in_tokenIdentifier4198);
            identifierText13=identifierText();

            state._fsp--;



                            	identifier = new TokenIdentifier(identifierText13);
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return identifier;
    }
    // $ANTLR end "tokenIdentifier"



    // $ANTLR start "tokenDataType"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:464:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER ) ;
    public final TokenDataType tokenDataType() throws RecognitionException {
        TokenDataType dataType = null;


        Token TYPE_VARCHAR14=null;
        Token TYPE_INTEGER15=null;


                	dataType = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:468:9: ( ( TYPE_VARCHAR | TYPE_INTEGER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:469:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:469:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==TYPE_VARCHAR) ) {
                alt16=1;
            }
            else if ( (LA16_0==TYPE_INTEGER) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:470:17: TYPE_VARCHAR
                    {
                    TYPE_VARCHAR14=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType4279); 


                                    	dataType = new TokenDataType((TYPE_VARCHAR14!=null?TYPE_VARCHAR14.getText():null));
                                    

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:474:17: TYPE_INTEGER
                    {
                    TYPE_INTEGER15=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType4317); 


                                    	dataType = new TokenDataType((TYPE_INTEGER15!=null?TYPE_INTEGER15.getText():null));
                                    

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
        }
        return dataType;
    }
    // $ANTLR end "tokenDataType"



    // $ANTLR start "tokenLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:480:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) ;
    public final TokenLiteral tokenLiteral() throws RecognitionException {
        TokenLiteral literal = null;


        TokenIntegerLiteral tokenIntegerLiteral16 =null;

        TokenStringLiteral tokenStringLiteral17 =null;

        TokenDecimalLiteral tokenDecimalLiteral18 =null;

        TokenDateLiteral tokenDateLiteral19 =null;



                	literal = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:484:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:485:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:485:9: ( ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:486:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:486:10: ( tokenIntegerLiteral | tokenStringLiteral | tokenDecimalLiteral | tokenDateLiteral )
            int alt17=4;
            switch ( input.LA(1) ) {
            case LITERAL_INTEGER:
                {
                alt17=1;
                }
                break;
            case LITERAL_STRING:
                {
                alt17=2;
                }
                break;
            case LITERAL_DECIMAL:
                {
                alt17=3;
                }
                break;
            case LITERAL_DATE:
                {
                alt17=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }

            switch (alt17) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:487:17: tokenIntegerLiteral
                    {
                    pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral4408);
                    tokenIntegerLiteral16=tokenIntegerLiteral();

                    state._fsp--;



                                    	literal = tokenIntegerLiteral16;
                                    

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:491:17: tokenStringLiteral
                    {
                    pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral4446);
                    tokenStringLiteral17=tokenStringLiteral();

                    state._fsp--;



                                    	literal = tokenStringLiteral17;
                                    

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:495:17: tokenDecimalLiteral
                    {
                    pushFollow(FOLLOW_tokenDecimalLiteral_in_tokenLiteral4484);
                    tokenDecimalLiteral18=tokenDecimalLiteral();

                    state._fsp--;



                                    	literal = tokenDecimalLiteral18;
                                    

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:499:17: tokenDateLiteral
                    {
                    pushFollow(FOLLOW_tokenDateLiteral_in_tokenLiteral4522);
                    tokenDateLiteral19=tokenDateLiteral();

                    state._fsp--;



                                    	literal = tokenDateLiteral19;
                                    

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
        }
        return literal;
    }
    // $ANTLR end "tokenLiteral"



    // $ANTLR start "tokenStringLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:506:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
    public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
        TokenStringLiteral literal = null;


        Token lit1=null;


                	literal = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:510:9: ( (lit1= LITERAL_STRING ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:511:9: (lit1= LITERAL_STRING )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:511:9: (lit1= LITERAL_STRING )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:512:17: lit1= LITERAL_STRING
            {
            lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral4622); 


                            	literal = new TokenStringLiteral((lit1!=null?lit1.getText():null).substring(1, (lit1!=null?lit1.getText():null).length()-1));
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return literal;
    }
    // $ANTLR end "tokenStringLiteral"



    // $ANTLR start "tokenIntegerLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:518:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
    public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
        TokenIntegerLiteral literal = null;


        Token LITERAL_INTEGER20=null;


                	literal = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:522:9: ( ( LITERAL_INTEGER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:523:9: ( LITERAL_INTEGER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:523:9: ( LITERAL_INTEGER )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:524:17: LITERAL_INTEGER
            {
            LITERAL_INTEGER20=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral4702); 


                            	literal = new TokenIntegerLiteral((LITERAL_INTEGER20!=null?LITERAL_INTEGER20.getText():null));
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return literal;
    }
    // $ANTLR end "tokenIntegerLiteral"



    // $ANTLR start "tokenDecimalLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:531:2: tokenDecimalLiteral returns [TokenDecimalLiteral literal] : ( LITERAL_DECIMAL ) ;
    public final TokenDecimalLiteral tokenDecimalLiteral() throws RecognitionException {
        TokenDecimalLiteral literal = null;


        Token LITERAL_DECIMAL21=null;


                	literal = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:535:9: ( ( LITERAL_DECIMAL ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:536:9: ( LITERAL_DECIMAL )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:536:9: ( LITERAL_DECIMAL )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:537:17: LITERAL_DECIMAL
            {
            LITERAL_DECIMAL21=(Token)match(input,LITERAL_DECIMAL,FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral4792); 


                            	literal = new TokenDecimalLiteral((LITERAL_DECIMAL21!=null?LITERAL_DECIMAL21.getText():null));
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return literal;
    }
    // $ANTLR end "tokenDecimalLiteral"



    // $ANTLR start "tokenDateLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:543:2: tokenDateLiteral returns [TokenDateLiteral literal] : ( LITERAL_DATE ) ;
    public final TokenDateLiteral tokenDateLiteral() throws RecognitionException {
        TokenDateLiteral literal = null;


        Token LITERAL_DATE22=null;


                	literal = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:547:9: ( ( LITERAL_DATE ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:548:9: ( LITERAL_DATE )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:548:9: ( LITERAL_DATE )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:549:17: LITERAL_DATE
            {
            LITERAL_DATE22=(Token)match(input,LITERAL_DATE,FOLLOW_LITERAL_DATE_in_tokenDateLiteral4873); 


                            	literal = new TokenDateLiteral((LITERAL_DATE22!=null?LITERAL_DATE22.getText():null));
                            

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return literal;
    }
    // $ANTLR end "tokenDateLiteral"



    // $ANTLR start "identifierText"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:555:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) ;
    public final String identifierText() throws RecognitionException {
        String text = null;


        Token id1=null;
        Token id2=null;

        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:556:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:557:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:557:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==IDENTIFIER) ) {
                alt18=1;
            }
            else if ( (LA18_0==QUOTE_DOUBLE) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }
            switch (alt18) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:558:4: (id1= IDENTIFIER )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:558:4: (id1= IDENTIFIER )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:559:4: id1= IDENTIFIER
                    {
                    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText4934); 


                                    	text = (id1!=null?id1.getText():null).toUpperCase();
                                    

                    }


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:564:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:564:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:565:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText4990); 

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText5006); 


                                    	text = (id2!=null?id2.getText():null);
                                    

                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText5026); 

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
        }
        return text;
    }
    // $ANTLR end "identifierText"


    public static class tokenComparator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "tokenComparator"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:575:1: tokenComparator : ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) ;
    public final FunSQLParser.tokenComparator_return tokenComparator() throws RecognitionException {
        FunSQLParser.tokenComparator_return retval = new FunSQLParser.tokenComparator_return();
        retval.start = input.LT(1);


        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:576:5: ( ( EQUAL1 | NOT_EQUAL1 | NOT_EQUAL2 | LESS_THAN | LESS_EQUAL1 | LESS_EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
            {
            if ( input.LA(1)==EQUAL1||(input.LA(1) >= GREATER_EQUAL1 && input.LA(1) <= GREATER_EQUAL2)||(input.LA(1) >= LESS_EQUAL1 && input.LA(1) <= LESS_THAN)||(input.LA(1) >= NOT_EQUAL1 && input.LA(1) <= NOT_EQUAL2) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
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
        }
        return retval;
    }
    // $ANTLR end "tokenComparator"

    // Delegated rules


 

    public static final BitSet FOLLOW_createSchemaStatement_in_statement1055 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_dropSchemaStatement_in_statement1110 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_createConnectionStatement_in_statement1165 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_dropConnectionStatement_in_statement1220 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_createTableStatement_in_statement1275 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_dropTableStatement_in_statement1330 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_selectStatement_in_statement1385 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
    public static final BitSet FOLLOW_SEMI_in_statement1439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1517 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1535 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1632 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1650 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1747 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1765 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement1783 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement1803 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1823 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement1843 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1863 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1883 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1903 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement1923 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2030 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2048 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2145 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2163 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2183 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement2203 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2223 = new BitSet(new long[]{0x0000000000000000L,0x0000000003000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2261 = new BitSet(new long[]{0x0000000000002000L,0x0000000000010000L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2315 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2335 = new BitSet(new long[]{0x0000000000000000L,0x0000000003000000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2373 = new BitSet(new long[]{0x0000000000002000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement2428 = new BitSet(new long[]{0x0000030000000000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2464 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2484 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2523 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2525 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement2624 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement2642 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenTable_in_dropTableStatement2662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement2728 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenAttribute_in_selectStatement2748 = new BitSet(new long[]{0x0000010000002000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement2802 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenAttribute_in_selectStatement2822 = new BitSet(new long[]{0x0000010000002000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement2877 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement2897 = new BitSet(new long[]{0x0008000000002002L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement2952 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement2972 = new BitSet(new long[]{0x0008000000002002L});
    public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement3045 = new BitSet(new long[]{0x3E00040100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement3065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateOr_in_abstractPredicate3141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr3179 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_KEYWORD_OR_in_complexPredicateOr3190 = new BitSet(new long[]{0x3E00040100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_complexPredicateAnd_in_complexPredicateOr3198 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd3238 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEYWORD_AND_in_complexPredicateAnd3249 = new BitSet(new long[]{0x3E00040100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_complexPredicateNot_in_complexPredicateAnd3257 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_KEYWORD_NOT_in_complexPredicateNot3298 = new BitSet(new long[]{0x3E00000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_complexPredicate_in_complexPredicateNot3311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenPredicate_in_complexPredicate3338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicate_in_complexPredicate3353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parenPredicate3378 = new BitSet(new long[]{0x3E00040100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_abstractPredicate_in_parenPredicate3385 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_RPAREN_in_parenPredicate3391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simplePredicate3437 = new BitSet(new long[]{0x01C000000C400000L,0x000000000000000CL});
    public static final BitSet FOLLOW_tokenLiteral_in_simplePredicate3493 = new BitSet(new long[]{0x01C000000C400000L,0x000000000000000CL});
    public static final BitSet FOLLOW_tokenComparator_in_simplePredicate3566 = new BitSet(new long[]{0x1E00000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenAttribute_in_simplePredicate3622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenLiteral_in_simplePredicate3678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute3812 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenAttribute3832 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute3871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable3979 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenTable3999 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable4038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema4118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierText_in_tokenIdentifier4198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType4279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType4317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral4408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral4446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDecimalLiteral_in_tokenLiteral4484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenDateLiteral_in_tokenLiteral4522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral4622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral4702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DECIMAL_in_tokenDecimalLiteral4792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_DATE_in_tokenDateLiteral4873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText4934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText4990 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText5006 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText5026 = new BitSet(new long[]{0x0000000000000002L});

}