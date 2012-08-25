// $ANTLR 3.4 /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g 2012-08-23 22:17:05
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "A", "AMPERSAND", "APOSTROPHE", "AT", "B", "BACKSLASH", "C", "CHAR", "COLON", "COMMA", "CONTROL_CHAR", "D", "DIGIT", "DIV", "DOLLAR", "DOT", "DOUBLE_PIPE", "E", "EQUAL1", "EQUAL2", "F", "G", "GREATER", "GREATER_OR_EQ1", "GREATER_OR_EQ2", "H", "HAT", "I", "IDENTIFIER", "IGNORE_CHAR", "J", "K", "KEYWORD_CONNECTION", "KEYWORD_CREATE", "KEYWORD_DROP", "KEYWORD_FROM", "KEYWORD_IN", "KEYWORD_PASSWD", "KEYWORD_SCHEMA", "KEYWORD_SELECT", "KEYWORD_STORE", "KEYWORD_TABLE", "KEYWORD_URL", "KEYWORD_USER", "KEYWORD_WHERE", "L", "LBRACKET", "LESS", "LESS_OR_EQ1", "LESS_OR_EQ2", "LITERAL_INTEGER", "LITERAL_STRING", "LPAREN", "M", "MINUS", "MOD", "N", "NOT_EQUAL1", "NOT_EQUAL2", "O", "P", "PIPE", "PLUS", "Q", "QUESTION", "QUOTED_STRING", "QUOTE_DOUBLE", "QUOTE_SINGLE", "QUOTE_TRIPLE", "R", "RBRACKET", "RPAREN", "S", "SEMI", "SHIFT_LEFT", "SHIFT_RIGHT", "STAR", "T", "TILDE", "TYPE_INTEGER", "TYPE_VARCHAR", "U", "UNDERSCORE", "V", "W", "WS", "X", "Y", "Z"
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
    public static final int GREATER=26;
    public static final int GREATER_OR_EQ1=27;
    public static final int GREATER_OR_EQ2=28;
    public static final int H=29;
    public static final int HAT=30;
    public static final int I=31;
    public static final int IDENTIFIER=32;
    public static final int IGNORE_CHAR=33;
    public static final int J=34;
    public static final int K=35;
    public static final int KEYWORD_CONNECTION=36;
    public static final int KEYWORD_CREATE=37;
    public static final int KEYWORD_DROP=38;
    public static final int KEYWORD_FROM=39;
    public static final int KEYWORD_IN=40;
    public static final int KEYWORD_PASSWD=41;
    public static final int KEYWORD_SCHEMA=42;
    public static final int KEYWORD_SELECT=43;
    public static final int KEYWORD_STORE=44;
    public static final int KEYWORD_TABLE=45;
    public static final int KEYWORD_URL=46;
    public static final int KEYWORD_USER=47;
    public static final int KEYWORD_WHERE=48;
    public static final int L=49;
    public static final int LBRACKET=50;
    public static final int LESS=51;
    public static final int LESS_OR_EQ1=52;
    public static final int LESS_OR_EQ2=53;
    public static final int LITERAL_INTEGER=54;
    public static final int LITERAL_STRING=55;
    public static final int LPAREN=56;
    public static final int M=57;
    public static final int MINUS=58;
    public static final int MOD=59;
    public static final int N=60;
    public static final int NOT_EQUAL1=61;
    public static final int NOT_EQUAL2=62;
    public static final int O=63;
    public static final int P=64;
    public static final int PIPE=65;
    public static final int PLUS=66;
    public static final int Q=67;
    public static final int QUESTION=68;
    public static final int QUOTED_STRING=69;
    public static final int QUOTE_DOUBLE=70;
    public static final int QUOTE_SINGLE=71;
    public static final int QUOTE_TRIPLE=72;
    public static final int R=73;
    public static final int RBRACKET=74;
    public static final int RPAREN=75;
    public static final int S=76;
    public static final int SEMI=77;
    public static final int SHIFT_LEFT=78;
    public static final int SHIFT_RIGHT=79;
    public static final int STAR=80;
    public static final int T=81;
    public static final int TILDE=82;
    public static final int TYPE_INTEGER=83;
    public static final int TYPE_VARCHAR=84;
    public static final int U=85;
    public static final int UNDERSCORE=86;
    public static final int V=87;
    public static final int W=88;
    public static final int WS=89;
    public static final int X=90;
    public static final int Y=91;
    public static final int Z=92;

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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:61:1: statement returns [AbstractStatement stmt] : ( ( createSchemaStatement | dropSchemaStatement | createConnectionStatement | dropConnectionStatement | createTableStatement | dropTableStatement | selectStatement ) ( SEMI )? ) ;
    public final AbstractStatement statement() throws RecognitionException {
        AbstractStatement stmt = null;


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
                    pushFollow(FOLLOW_createSchemaStatement_in_statement1065);
                    createSchemaStatement1=createSchemaStatement();

                    state._fsp--;



                                    	stmt = createSchemaStatement1;
                                    

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:73:17: dropSchemaStatement
                    {
                    pushFollow(FOLLOW_dropSchemaStatement_in_statement1120);
                    dropSchemaStatement2=dropSchemaStatement();

                    state._fsp--;



                                    	stmt = dropSchemaStatement2;
                                    

                    }
                    break;
                case 3 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:78:17: createConnectionStatement
                    {
                    pushFollow(FOLLOW_createConnectionStatement_in_statement1175);
                    createConnectionStatement3=createConnectionStatement();

                    state._fsp--;



                                    	stmt = createConnectionStatement3;
                                    

                    }
                    break;
                case 4 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:83:17: dropConnectionStatement
                    {
                    pushFollow(FOLLOW_dropConnectionStatement_in_statement1230);
                    dropConnectionStatement4=dropConnectionStatement();

                    state._fsp--;



                                    	stmt = dropConnectionStatement4;
                                    

                    }
                    break;
                case 5 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:88:17: createTableStatement
                    {
                    pushFollow(FOLLOW_createTableStatement_in_statement1285);
                    createTableStatement5=createTableStatement();

                    state._fsp--;



                                    	stmt = createTableStatement5;
                                    

                    }
                    break;
                case 6 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:93:17: dropTableStatement
                    {
                    pushFollow(FOLLOW_dropTableStatement_in_statement1340);
                    dropTableStatement6=dropTableStatement();

                    state._fsp--;



                                    	stmt = dropTableStatement6;
                                    

                    }
                    break;
                case 7 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:98:17: selectStatement
                    {
                    pushFollow(FOLLOW_selectStatement_in_statement1395);
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
                    match(input,SEMI,FOLLOW_SEMI_in_statement1449); 

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
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1527); 

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1545); 

            pushFollow(FOLLOW_tokenSchema_in_createSchemaStatement1563);
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
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1642); 

            match(input,KEYWORD_SCHEMA,FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1660); 

            pushFollow(FOLLOW_tokenSchema_in_dropSchemaStatement1678);
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
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1757); 

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1775); 

            pushFollow(FOLLOW_tokenIdentifier_in_createConnectionStatement1793);
            tokenIdentifier10=tokenIdentifier();

            state._fsp--;



                            	stmt.setConnection(tokenIdentifier10);
                            

            match(input,KEYWORD_URL,FOLLOW_KEYWORD_URL_in_createConnectionStatement1813); 

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1833);
            litURL=tokenStringLiteral();

            state._fsp--;



                            	stmt.setURL(litURL);
                            

            match(input,KEYWORD_USER,FOLLOW_KEYWORD_USER_in_createConnectionStatement1853); 

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1873);
            litUser=tokenStringLiteral();

            state._fsp--;



                            	stmt.setUser(litUser);
                            

            match(input,KEYWORD_PASSWD,FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1893); 

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1913);
            litPasswd=tokenStringLiteral();

            state._fsp--;



                            	stmt.setPasswd(litPasswd);
                            

            match(input,KEYWORD_STORE,FOLLOW_KEYWORD_STORE_in_createConnectionStatement1933); 

            pushFollow(FOLLOW_tokenStringLiteral_in_createConnectionStatement1953);
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
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2040); 

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2058); 

            pushFollow(FOLLOW_tokenIdentifier_in_dropConnectionStatement2076);
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
            match(input,KEYWORD_CREATE,FOLLOW_KEYWORD_CREATE_in_createTableStatement2155); 

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_createTableStatement2173); 

            pushFollow(FOLLOW_tokenTable_in_createTableStatement2193);
            table1=tokenTable();

            state._fsp--;



                            	stmt.setTable(table1);
                            	stmt.setSourceTable(table1);
                            

            match(input,LPAREN,FOLLOW_LPAREN_in_createTableStatement2213); 

            pushFollow(FOLLOW_identifierText_in_createTableStatement2233);
            att1=identifierText();

            state._fsp--;



                            	stmt.addAttribute(att1);
                            

            pushFollow(FOLLOW_tokenDataType_in_createTableStatement2271);
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
            	    match(input,COMMA,FOLLOW_COMMA_in_createTableStatement2325); 

            	    pushFollow(FOLLOW_identifierText_in_createTableStatement2345);
            	    att2=identifierText();

            	    state._fsp--;



            	                    	stmt.addAttribute(att2);
            	                    

            	    pushFollow(FOLLOW_tokenDataType_in_createTableStatement2383);
            	    dataType2=tokenDataType();

            	    state._fsp--;



            	                    	stmt.addType(dataType2);
            	                    

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            match(input,RPAREN,FOLLOW_RPAREN_in_createTableStatement2438); 

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
                    match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_createTableStatement2474); 

                    pushFollow(FOLLOW_tokenTable_in_createTableStatement2494);
                    table2=tokenTable();

                    state._fsp--;



                                    	stmt.setSourceTable(table2);
                                    

                    }
                    break;

            }


            match(input,KEYWORD_IN,FOLLOW_KEYWORD_IN_in_createTableStatement2533); 

            match(input,KEYWORD_CONNECTION,FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2535); 

            pushFollow(FOLLOW_tokenIdentifier_in_createTableStatement2555);
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
            match(input,KEYWORD_DROP,FOLLOW_KEYWORD_DROP_in_dropTableStatement2634); 

            match(input,KEYWORD_TABLE,FOLLOW_KEYWORD_TABLE_in_dropTableStatement2652); 

            pushFollow(FOLLOW_tokenTable_in_dropTableStatement2672);
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
            match(input,KEYWORD_SELECT,FOLLOW_KEYWORD_SELECT_in_selectStatement2738); 

            pushFollow(FOLLOW_tokenAttribute_in_selectStatement2758);
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
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement2812); 

            	    pushFollow(FOLLOW_tokenAttribute_in_selectStatement2832);
            	    att2=tokenAttribute();

            	    state._fsp--;



            	                    	stmt.addAttribute(att2);
            	                    

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match(input,KEYWORD_FROM,FOLLOW_KEYWORD_FROM_in_selectStatement2887); 

            pushFollow(FOLLOW_tokenTable_in_selectStatement2907);
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
            	    match(input,COMMA,FOLLOW_COMMA_in_selectStatement2962); 

            	    pushFollow(FOLLOW_tokenTable_in_selectStatement2982);
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
                    match(input,KEYWORD_WHERE,FOLLOW_KEYWORD_WHERE_in_selectStatement3055); 

                    pushFollow(FOLLOW_abstractPredicate_in_selectStatement3075);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:280:1: abstractPredicate returns [AbstractPredicate predicate] : ( (predicate1= simplePredicateLiteral |predicate2= simplePredicateAttribute ) ) ;
    public final AbstractPredicate abstractPredicate() throws RecognitionException {
        AbstractPredicate predicate = null;


        SimplePredicateLiteral predicate1 =null;

        SimplePredicateAttribute predicate2 =null;



                	predicate = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:284:9: ( ( (predicate1= simplePredicateLiteral |predicate2= simplePredicateAttribute ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:285:9: ( (predicate1= simplePredicateLiteral |predicate2= simplePredicateAttribute ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:285:9: ( (predicate1= simplePredicateLiteral |predicate2= simplePredicateAttribute ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:286:10: (predicate1= simplePredicateLiteral |predicate2= simplePredicateAttribute )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:286:10: (predicate1= simplePredicateLiteral |predicate2= simplePredicateAttribute )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==IDENTIFIER) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==DOT) ) {
                    int LA8_3 = input.LA(3);

                    if ( (LA8_3==IDENTIFIER) ) {
                        int LA8_6 = input.LA(4);

                        if ( (LA8_6==EQUAL1) ) {
                            int LA8_4 = input.LA(5);

                            if ( ((LA8_4 >= LITERAL_INTEGER && LA8_4 <= LITERAL_STRING)) ) {
                                alt8=1;
                            }
                            else if ( (LA8_4==IDENTIFIER||LA8_4==QUOTE_DOUBLE) ) {
                                alt8=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 8, 4, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 8, 6, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA8_3==QUOTE_DOUBLE) ) {
                        int LA8_7 = input.LA(4);

                        if ( (LA8_7==IDENTIFIER) ) {
                            int LA8_11 = input.LA(5);

                            if ( (LA8_11==QUOTE_DOUBLE) ) {
                                int LA8_12 = input.LA(6);

                                if ( (LA8_12==EQUAL1) ) {
                                    int LA8_4 = input.LA(7);

                                    if ( ((LA8_4 >= LITERAL_INTEGER && LA8_4 <= LITERAL_STRING)) ) {
                                        alt8=1;
                                    }
                                    else if ( (LA8_4==IDENTIFIER||LA8_4==QUOTE_DOUBLE) ) {
                                        alt8=2;
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 8, 4, input);

                                        throw nvae;

                                    }
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 8, 12, input);

                                    throw nvae;

                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 8, 11, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 8, 7, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 3, input);

                        throw nvae;

                    }
                }
                else if ( (LA8_1==EQUAL1) ) {
                    int LA8_4 = input.LA(3);

                    if ( ((LA8_4 >= LITERAL_INTEGER && LA8_4 <= LITERAL_STRING)) ) {
                        alt8=1;
                    }
                    else if ( (LA8_4==IDENTIFIER||LA8_4==QUOTE_DOUBLE) ) {
                        alt8=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 4, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA8_0==QUOTE_DOUBLE) ) {
                int LA8_2 = input.LA(2);

                if ( (LA8_2==IDENTIFIER) ) {
                    int LA8_5 = input.LA(3);

                    if ( (LA8_5==QUOTE_DOUBLE) ) {
                        int LA8_10 = input.LA(4);

                        if ( (LA8_10==DOT) ) {
                            int LA8_3 = input.LA(5);

                            if ( (LA8_3==IDENTIFIER) ) {
                                int LA8_6 = input.LA(6);

                                if ( (LA8_6==EQUAL1) ) {
                                    int LA8_4 = input.LA(7);

                                    if ( ((LA8_4 >= LITERAL_INTEGER && LA8_4 <= LITERAL_STRING)) ) {
                                        alt8=1;
                                    }
                                    else if ( (LA8_4==IDENTIFIER||LA8_4==QUOTE_DOUBLE) ) {
                                        alt8=2;
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 8, 4, input);

                                        throw nvae;

                                    }
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 8, 6, input);

                                    throw nvae;

                                }
                            }
                            else if ( (LA8_3==QUOTE_DOUBLE) ) {
                                int LA8_7 = input.LA(6);

                                if ( (LA8_7==IDENTIFIER) ) {
                                    int LA8_11 = input.LA(7);

                                    if ( (LA8_11==QUOTE_DOUBLE) ) {
                                        int LA8_12 = input.LA(8);

                                        if ( (LA8_12==EQUAL1) ) {
                                            int LA8_4 = input.LA(9);

                                            if ( ((LA8_4 >= LITERAL_INTEGER && LA8_4 <= LITERAL_STRING)) ) {
                                                alt8=1;
                                            }
                                            else if ( (LA8_4==IDENTIFIER||LA8_4==QUOTE_DOUBLE) ) {
                                                alt8=2;
                                            }
                                            else {
                                                NoViableAltException nvae =
                                                    new NoViableAltException("", 8, 4, input);

                                                throw nvae;

                                            }
                                        }
                                        else {
                                            NoViableAltException nvae =
                                                new NoViableAltException("", 8, 12, input);

                                            throw nvae;

                                        }
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 8, 11, input);

                                        throw nvae;

                                    }
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 8, 7, input);

                                    throw nvae;

                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 8, 3, input);

                                throw nvae;

                            }
                        }
                        else if ( (LA8_10==EQUAL1) ) {
                            int LA8_4 = input.LA(5);

                            if ( ((LA8_4 >= LITERAL_INTEGER && LA8_4 <= LITERAL_STRING)) ) {
                                alt8=1;
                            }
                            else if ( (LA8_4==IDENTIFIER||LA8_4==QUOTE_DOUBLE) ) {
                                alt8=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 8, 4, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 8, 10, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 5, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:287:17: predicate1= simplePredicateLiteral
                    {
                    pushFollow(FOLLOW_simplePredicateLiteral_in_abstractPredicate3199);
                    predicate1=simplePredicateLiteral();

                    state._fsp--;



                                    	predicate = predicate1;
                                    

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:291:17: predicate2= simplePredicateAttribute
                    {
                    pushFollow(FOLLOW_simplePredicateAttribute_in_abstractPredicate3239);
                    predicate2=simplePredicateAttribute();

                    state._fsp--;



                                    	predicate = predicate2;
                                    

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
    // $ANTLR end "abstractPredicate"



    // $ANTLR start "simplePredicateLiteral"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:298:1: simplePredicateLiteral returns [SimplePredicateLiteral predicate] : (att1= tokenAttribute EQUAL1 lit1= tokenLiteral ) ;
    public final SimplePredicateLiteral simplePredicateLiteral() throws RecognitionException {
        SimplePredicateLiteral predicate = null;


        TokenAttribute att1 =null;

        TokenLiteral lit1 =null;



                	predicate = new SimplePredicateLiteral();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:302:9: ( (att1= tokenAttribute EQUAL1 lit1= tokenLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:303:2: (att1= tokenAttribute EQUAL1 lit1= tokenLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:303:2: (att1= tokenAttribute EQUAL1 lit1= tokenLiteral )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:304:3: att1= tokenAttribute EQUAL1 lit1= tokenLiteral
            {
            pushFollow(FOLLOW_tokenAttribute_in_simplePredicateLiteral3310);
            att1=tokenAttribute();

            state._fsp--;



                            	predicate.setAttribute(att1);
                            

            match(input,EQUAL1,FOLLOW_EQUAL1_in_simplePredicateLiteral3346); 

            pushFollow(FOLLOW_tokenLiteral_in_simplePredicateLiteral3366);
            lit1=tokenLiteral();

            state._fsp--;



                            	predicate.setLiteral(lit1);
                            

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
    // $ANTLR end "simplePredicateLiteral"



    // $ANTLR start "simplePredicateAttribute"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:317:1: simplePredicateAttribute returns [SimplePredicateAttribute predicate] : (att1= tokenAttribute EQUAL1 att2= tokenAttribute ) ;
    public final SimplePredicateAttribute simplePredicateAttribute() throws RecognitionException {
        SimplePredicateAttribute predicate = null;


        TokenAttribute att1 =null;

        TokenAttribute att2 =null;



                	predicate = new SimplePredicateAttribute();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:321:9: ( (att1= tokenAttribute EQUAL1 att2= tokenAttribute ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:322:2: (att1= tokenAttribute EQUAL1 att2= tokenAttribute )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:322:2: (att1= tokenAttribute EQUAL1 att2= tokenAttribute )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:323:3: att1= tokenAttribute EQUAL1 att2= tokenAttribute
            {
            pushFollow(FOLLOW_tokenAttribute_in_simplePredicateAttribute3439);
            att1=tokenAttribute();

            state._fsp--;



                            	predicate.setLeftAtt(att1);
                            

            match(input,EQUAL1,FOLLOW_EQUAL1_in_simplePredicateAttribute3475); 

            pushFollow(FOLLOW_tokenAttribute_in_simplePredicateAttribute3495);
            att2=tokenAttribute();

            state._fsp--;



                            	predicate.setRightAtt(att2);
                            

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
    // $ANTLR end "simplePredicateAttribute"



    // $ANTLR start "tokenAttribute"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:336:1: tokenAttribute returns [TokenAttribute attribute] : ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenAttribute tokenAttribute() throws RecognitionException {
        TokenAttribute attribute = null;


        TokenIdentifier table1 =null;

        TokenIdentifier id1 =null;



                	attribute = new TokenAttribute();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:340:9: ( ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:341:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:341:9: ( (table1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:342:17: (table1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:342:17: (table1= tokenIdentifier DOT )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==IDENTIFIER) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==DOT) ) {
                    alt9=1;
                }
            }
            else if ( (LA9_0==QUOTE_DOUBLE) ) {
                int LA9_2 = input.LA(2);

                if ( (LA9_2==IDENTIFIER) ) {
                    int LA9_5 = input.LA(3);

                    if ( (LA9_5==QUOTE_DOUBLE) ) {
                        int LA9_6 = input.LA(4);

                        if ( (LA9_6==DOT) ) {
                            alt9=1;
                        }
                    }
                }
            }
            switch (alt9) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:343:17: table1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute3598);
                    table1=tokenIdentifier();

                    state._fsp--;



                                    	TokenTable table = new TokenTable();
                                    	table.setName(table1);
                                    	attribute.setTable(table);
                                    

                    match(input,DOT,FOLLOW_DOT_in_tokenAttribute3618); 

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenAttribute3657);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:356:1: tokenTable returns [TokenTable table] : ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) ;
    public final TokenTable tokenTable() throws RecognitionException {
        TokenTable table = null;


        TokenIdentifier schema1 =null;

        TokenIdentifier id1 =null;



                	table = new TokenTable();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:360:9: ( ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:361:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:361:9: ( (schema1= tokenIdentifier DOT )? id1= tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:362:17: (schema1= tokenIdentifier DOT )? id1= tokenIdentifier
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:362:17: (schema1= tokenIdentifier DOT )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==IDENTIFIER) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==DOT) ) {
                    alt10=1;
                }
            }
            else if ( (LA10_0==QUOTE_DOUBLE) ) {
                int LA10_2 = input.LA(2);

                if ( (LA10_2==IDENTIFIER) ) {
                    int LA10_5 = input.LA(3);

                    if ( (LA10_5==QUOTE_DOUBLE) ) {
                        int LA10_6 = input.LA(4);

                        if ( (LA10_6==DOT) ) {
                            alt10=1;
                        }
                    }
                }
            }
            switch (alt10) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:363:17: schema1= tokenIdentifier DOT
                    {
                    pushFollow(FOLLOW_tokenIdentifier_in_tokenTable3765);
                    schema1=tokenIdentifier();

                    state._fsp--;



                                            TokenSchema schema = new TokenSchema();
                                    	table.setSchema(schema);
                                    

                    match(input,DOT,FOLLOW_DOT_in_tokenTable3785); 

                    }
                    break;

            }


            pushFollow(FOLLOW_tokenIdentifier_in_tokenTable3824);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:375:1: tokenSchema returns [TokenSchema schema] : ( tokenIdentifier ) ;
    public final TokenSchema tokenSchema() throws RecognitionException {
        TokenSchema schema = null;


        TokenIdentifier tokenIdentifier12 =null;



                	schema = new TokenSchema();
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:379:9: ( ( tokenIdentifier ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:380:9: ( tokenIdentifier )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:380:9: ( tokenIdentifier )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:381:17: tokenIdentifier
            {
            pushFollow(FOLLOW_tokenIdentifier_in_tokenSchema3904);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:387:1: tokenIdentifier returns [TokenIdentifier identifier] : ( identifierText ) ;
    public final TokenIdentifier tokenIdentifier() throws RecognitionException {
        TokenIdentifier identifier = null;


        String identifierText13 =null;



                	identifier = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:391:9: ( ( identifierText ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:392:9: ( identifierText )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:392:9: ( identifierText )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:393:17: identifierText
            {
            pushFollow(FOLLOW_identifierText_in_tokenIdentifier3984);
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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:400:1: tokenDataType returns [TokenDataType dataType] : ( TYPE_VARCHAR | TYPE_INTEGER ) ;
    public final TokenDataType tokenDataType() throws RecognitionException {
        TokenDataType dataType = null;


        Token TYPE_VARCHAR14=null;
        Token TYPE_INTEGER15=null;


                	dataType = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:404:9: ( ( TYPE_VARCHAR | TYPE_INTEGER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:405:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:405:9: ( TYPE_VARCHAR | TYPE_INTEGER )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==TYPE_VARCHAR) ) {
                alt11=1;
            }
            else if ( (LA11_0==TYPE_INTEGER) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:406:17: TYPE_VARCHAR
                    {
                    TYPE_VARCHAR14=(Token)match(input,TYPE_VARCHAR,FOLLOW_TYPE_VARCHAR_in_tokenDataType4065); 


                                    	dataType = new TokenDataType((TYPE_VARCHAR14!=null?TYPE_VARCHAR14.getText():null));
                                    

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:410:17: TYPE_INTEGER
                    {
                    TYPE_INTEGER15=(Token)match(input,TYPE_INTEGER,FOLLOW_TYPE_INTEGER_in_tokenDataType4103); 


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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:416:1: tokenLiteral returns [TokenLiteral literal] : ( ( tokenIntegerLiteral | tokenStringLiteral ) ) ;
    public final TokenLiteral tokenLiteral() throws RecognitionException {
        TokenLiteral literal = null;


        TokenIntegerLiteral tokenIntegerLiteral16 =null;

        TokenStringLiteral tokenStringLiteral17 =null;



                	literal = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:420:9: ( ( ( tokenIntegerLiteral | tokenStringLiteral ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:421:9: ( ( tokenIntegerLiteral | tokenStringLiteral ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:421:9: ( ( tokenIntegerLiteral | tokenStringLiteral ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:422:10: ( tokenIntegerLiteral | tokenStringLiteral )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:422:10: ( tokenIntegerLiteral | tokenStringLiteral )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==LITERAL_INTEGER) ) {
                alt12=1;
            }
            else if ( (LA12_0==LITERAL_STRING) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:423:17: tokenIntegerLiteral
                    {
                    pushFollow(FOLLOW_tokenIntegerLiteral_in_tokenLiteral4194);
                    tokenIntegerLiteral16=tokenIntegerLiteral();

                    state._fsp--;



                                    	literal = tokenIntegerLiteral16;
                                    

                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:427:17: tokenStringLiteral
                    {
                    pushFollow(FOLLOW_tokenStringLiteral_in_tokenLiteral4232);
                    tokenStringLiteral17=tokenStringLiteral();

                    state._fsp--;



                                    	literal = tokenStringLiteral17;
                                    

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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:434:1: tokenStringLiteral returns [TokenStringLiteral literal] : (lit1= LITERAL_STRING ) ;
    public final TokenStringLiteral tokenStringLiteral() throws RecognitionException {
        TokenStringLiteral literal = null;


        Token lit1=null;


                	literal = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:438:9: ( (lit1= LITERAL_STRING ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:439:9: (lit1= LITERAL_STRING )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:439:9: (lit1= LITERAL_STRING )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:440:17: lit1= LITERAL_STRING
            {
            lit1=(Token)match(input,LITERAL_STRING,FOLLOW_LITERAL_STRING_in_tokenStringLiteral4332); 


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
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:446:1: tokenIntegerLiteral returns [TokenIntegerLiteral literal] : ( LITERAL_INTEGER ) ;
    public final TokenIntegerLiteral tokenIntegerLiteral() throws RecognitionException {
        TokenIntegerLiteral literal = null;


        Token LITERAL_INTEGER18=null;


                	literal = null;
                
        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:450:9: ( ( LITERAL_INTEGER ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:451:9: ( LITERAL_INTEGER )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:451:9: ( LITERAL_INTEGER )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:452:17: LITERAL_INTEGER
            {
            LITERAL_INTEGER18=(Token)match(input,LITERAL_INTEGER,FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral4412); 


                            	literal = new TokenIntegerLiteral((LITERAL_INTEGER18!=null?LITERAL_INTEGER18.getText():null));
                            

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



    // $ANTLR start "identifierText"
    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:458:2: identifierText returns [String text] : ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) ;
    public final String identifierText() throws RecognitionException {
        String text = null;


        Token id1=null;
        Token id2=null;

        try {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:459:3: ( ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:460:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:460:3: ( (id1= IDENTIFIER ) | ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==IDENTIFIER) ) {
                alt13=1;
            }
            else if ( (LA13_0==QUOTE_DOUBLE) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:461:4: (id1= IDENTIFIER )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:461:4: (id1= IDENTIFIER )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:462:4: id1= IDENTIFIER
                    {
                    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText4473); 


                                    	text = (id1!=null?id1.getText():null).toUpperCase();
                                    

                    }


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:467:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    {
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:467:11: ( QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE )
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:468:11: QUOTE_DOUBLE id2= IDENTIFIER QUOTE_DOUBLE
                    {
                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText4529); 

                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_identifierText4545); 


                                    	text = (id2!=null?id2.getText():null);
                                    

                    match(input,QUOTE_DOUBLE,FOLLOW_QUOTE_DOUBLE_in_identifierText4565); 

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

    // Delegated rules


 

    public static final BitSet FOLLOW_createSchemaStatement_in_statement1065 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_dropSchemaStatement_in_statement1120 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_createConnectionStatement_in_statement1175 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_dropConnectionStatement_in_statement1230 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_createTableStatement_in_statement1285 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_dropTableStatement_in_statement1340 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_selectStatement_in_statement1395 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_SEMI_in_statement1449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createSchemaStatement1527 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_createSchemaStatement1545 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenSchema_in_createSchemaStatement1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropSchemaStatement1642 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_KEYWORD_SCHEMA_in_dropSchemaStatement1660 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenSchema_in_dropSchemaStatement1678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createConnectionStatement1757 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createConnectionStatement1775 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createConnectionStatement1793 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_KEYWORD_URL_in_createConnectionStatement1813 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1833 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_KEYWORD_USER_in_createConnectionStatement1853 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1873 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_KEYWORD_PASSWD_in_createConnectionStatement1893 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1913 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_KEYWORD_STORE_in_createConnectionStatement1933 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_createConnectionStatement1953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropConnectionStatement2040 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_dropConnectionStatement2058 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenIdentifier_in_dropConnectionStatement2076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_CREATE_in_createTableStatement2155 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_createTableStatement2173 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2193 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_LPAREN_in_createTableStatement2213 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2233 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2271 = new BitSet(new long[]{0x0000000000002000L,0x0000000000000800L});
    public static final BitSet FOLLOW_COMMA_in_createTableStatement2325 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_identifierText_in_createTableStatement2345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_tokenDataType_in_createTableStatement2383 = new BitSet(new long[]{0x0000000000002000L,0x0000000000000800L});
    public static final BitSet FOLLOW_RPAREN_in_createTableStatement2438 = new BitSet(new long[]{0x0000018000000000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_createTableStatement2474 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenTable_in_createTableStatement2494 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_KEYWORD_IN_in_createTableStatement2533 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_KEYWORD_CONNECTION_in_createTableStatement2535 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenIdentifier_in_createTableStatement2555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_DROP_in_dropTableStatement2634 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_KEYWORD_TABLE_in_dropTableStatement2652 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenTable_in_dropTableStatement2672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_SELECT_in_selectStatement2738 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenAttribute_in_selectStatement2758 = new BitSet(new long[]{0x0000008000002000L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement2812 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenAttribute_in_selectStatement2832 = new BitSet(new long[]{0x0000008000002000L});
    public static final BitSet FOLLOW_KEYWORD_FROM_in_selectStatement2887 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement2907 = new BitSet(new long[]{0x0001000000002002L});
    public static final BitSet FOLLOW_COMMA_in_selectStatement2962 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenTable_in_selectStatement2982 = new BitSet(new long[]{0x0001000000002002L});
    public static final BitSet FOLLOW_KEYWORD_WHERE_in_selectStatement3055 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_abstractPredicate_in_selectStatement3075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicateLiteral_in_abstractPredicate3199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simplePredicateAttribute_in_abstractPredicate3239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simplePredicateLiteral3310 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_simplePredicateLiteral3346 = new BitSet(new long[]{0x00C0000000000000L});
    public static final BitSet FOLLOW_tokenLiteral_in_simplePredicateLiteral3366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenAttribute_in_simplePredicateAttribute3439 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_EQUAL1_in_simplePredicateAttribute3475 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenAttribute_in_simplePredicateAttribute3495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute3598 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenAttribute3618 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenAttribute3657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable3765 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_tokenTable3785 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenTable3824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIdentifier_in_tokenSchema3904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierText_in_tokenIdentifier3984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_VARCHAR_in_tokenDataType4065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_INTEGER_in_tokenDataType4103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenIntegerLiteral_in_tokenLiteral4194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tokenStringLiteral_in_tokenLiteral4232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_STRING_in_tokenStringLiteral4332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LITERAL_INTEGER_in_tokenIntegerLiteral4412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText4473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText4529 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_identifierText4545 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_QUOTE_DOUBLE_in_identifierText4565 = new BitSet(new long[]{0x0000000000000002L});

}