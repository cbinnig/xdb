// $ANTLR 3.4 C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g 2013-05-29 18:26:16
 
package org.xdb.funsql.compile.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class FunSQLLexer extends Lexer {
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

        @Override
        public void reportError(RecognitionException e) {
            throw new RuntimeException(e);
        }


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public FunSQLLexer() {} 
    public FunSQLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public FunSQLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g"; }

    // $ANTLR start "AMPERSAND"
    public final void mAMPERSAND() throws RecognitionException {
        try {
            int _type = AMPERSAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:12:11: ( '&' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:12:13: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AMPERSAND"

    // $ANTLR start "APOSTROPHE"
    public final void mAPOSTROPHE() throws RecognitionException {
        try {
            int _type = APOSTROPHE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:13:12: ( '`' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:13:14: '`'
            {
            match('`'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "APOSTROPHE"

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:14:4: ( '@' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:14:6: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "BACKSLASH"
    public final void mBACKSLASH() throws RecognitionException {
        try {
            int _type = BACKSLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:15:11: ( '\\\\' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:15:13: '\\\\'
            {
            match('\\'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BACKSLASH"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:16:7: ( ':' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:16:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:17:7: ( ',' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:17:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:18:5: ( '/' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:18:7: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            int _type = DOLLAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:19:8: ( '$' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:19:10: '$'
            {
            match('$'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOLLAR"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:20:5: ( '.' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:20:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "DOUBLE_PIPE"
    public final void mDOUBLE_PIPE() throws RecognitionException {
        try {
            int _type = DOUBLE_PIPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:21:13: ( '||' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:21:15: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOUBLE_PIPE"

    // $ANTLR start "EQUAL1"
    public final void mEQUAL1() throws RecognitionException {
        try {
            int _type = EQUAL1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:22:8: ( '=' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:22:10: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUAL1"

    // $ANTLR start "EQUAL2"
    public final void mEQUAL2() throws RecognitionException {
        try {
            int _type = EQUAL2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:23:8: ( '==' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:23:10: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQUAL2"

    // $ANTLR start "GREATER_EQUAL1"
    public final void mGREATER_EQUAL1() throws RecognitionException {
        try {
            int _type = GREATER_EQUAL1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:24:16: ( '>=' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:24:18: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATER_EQUAL1"

    // $ANTLR start "GREATER_EQUAL2"
    public final void mGREATER_EQUAL2() throws RecognitionException {
        try {
            int _type = GREATER_EQUAL2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:25:16: ( '!<' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:25:18: '!<'
            {
            match("!<"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATER_EQUAL2"

    // $ANTLR start "GREATER_THAN"
    public final void mGREATER_THAN() throws RecognitionException {
        try {
            int _type = GREATER_THAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:26:14: ( '>' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:26:16: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GREATER_THAN"

    // $ANTLR start "HAT"
    public final void mHAT() throws RecognitionException {
        try {
            int _type = HAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:27:5: ( '^' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:27:7: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HAT"

    // $ANTLR start "LBRACKET"
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:28:10: ( '[' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:28:12: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LBRACKET"

    // $ANTLR start "LESS_EQUAL1"
    public final void mLESS_EQUAL1() throws RecognitionException {
        try {
            int _type = LESS_EQUAL1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:29:13: ( '<=' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:29:15: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESS_EQUAL1"

    // $ANTLR start "LESS_EQUAL2"
    public final void mLESS_EQUAL2() throws RecognitionException {
        try {
            int _type = LESS_EQUAL2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:30:13: ( '!>' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:30:15: '!>'
            {
            match("!>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESS_EQUAL2"

    // $ANTLR start "LESS_THAN"
    public final void mLESS_THAN() throws RecognitionException {
        try {
            int _type = LESS_THAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:31:11: ( '<' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:31:13: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LESS_THAN"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:32:8: ( '(' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:32:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:33:7: ( '-' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:33:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:34:5: ( '%' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:34:7: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MOD"

    // $ANTLR start "MULT"
    public final void mMULT() throws RecognitionException {
        try {
            int _type = MULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:35:6: ( '*' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:35:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MULT"

    // $ANTLR start "NOT_EQUAL1"
    public final void mNOT_EQUAL1() throws RecognitionException {
        try {
            int _type = NOT_EQUAL1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:36:12: ( '!=' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:36:14: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT_EQUAL1"

    // $ANTLR start "NOT_EQUAL2"
    public final void mNOT_EQUAL2() throws RecognitionException {
        try {
            int _type = NOT_EQUAL2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:37:12: ( '<>' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:37:14: '<>'
            {
            match("<>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT_EQUAL2"

    // $ANTLR start "PIPE"
    public final void mPIPE() throws RecognitionException {
        try {
            int _type = PIPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:38:6: ( '|' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:38:8: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PIPE"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:39:6: ( '+' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:39:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:40:10: ( '?' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:40:12: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUESTION"

    // $ANTLR start "QUOTE_DOUBLE"
    public final void mQUOTE_DOUBLE() throws RecognitionException {
        try {
            int _type = QUOTE_DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:41:14: ( '\"' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:41:16: '\"'
            {
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUOTE_DOUBLE"

    // $ANTLR start "QUOTE_SINGLE"
    public final void mQUOTE_SINGLE() throws RecognitionException {
        try {
            int _type = QUOTE_SINGLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:42:14: ( '\\'' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:42:16: '\\''
            {
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUOTE_SINGLE"

    // $ANTLR start "QUOTE_TRIPLE"
    public final void mQUOTE_TRIPLE() throws RecognitionException {
        try {
            int _type = QUOTE_TRIPLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:43:14: ( '\\'\\'\\'' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:43:16: '\\'\\'\\''
            {
            match("'''"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUOTE_TRIPLE"

    // $ANTLR start "RBRACKET"
    public final void mRBRACKET() throws RecognitionException {
        try {
            int _type = RBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:44:10: ( ']' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:44:12: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RBRACKET"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:45:8: ( ')' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:45:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:46:6: ( ';' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:46:8: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "SHIFT_LEFT"
    public final void mSHIFT_LEFT() throws RecognitionException {
        try {
            int _type = SHIFT_LEFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:47:12: ( '<<' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:47:14: '<<'
            {
            match("<<"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHIFT_LEFT"

    // $ANTLR start "SHIFT_RIGHT"
    public final void mSHIFT_RIGHT() throws RecognitionException {
        try {
            int _type = SHIFT_RIGHT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:48:13: ( '>>' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:48:15: '>>'
            {
            match(">>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHIFT_RIGHT"

    // $ANTLR start "TILDE"
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:49:7: ( '~' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:49:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TILDE"

    // $ANTLR start "UNDERSCORE"
    public final void mUNDERSCORE() throws RecognitionException {
        try {
            int _type = UNDERSCORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:50:12: ( '_' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:50:14: '_'
            {
            match('_'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNDERSCORE"

    // $ANTLR start "FUNCTION_AGGREGATION"
    public final void mFUNCTION_AGGREGATION() throws RecognitionException {
        try {
            int _type = FUNCTION_AGGREGATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1220:2: ( ( KEYWORD_SUM | KEYWORD_MIN | KEYWORD_MAX | KEYWORD_AVG | KEYWORD_COUNT ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1221:2: ( KEYWORD_SUM | KEYWORD_MIN | KEYWORD_MAX | KEYWORD_AVG | KEYWORD_COUNT )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1221:2: ( KEYWORD_SUM | KEYWORD_MIN | KEYWORD_MAX | KEYWORD_AVG | KEYWORD_COUNT )
            int alt1=5;
            switch ( input.LA(1) ) {
            case 'S':
            case 's':
                {
                alt1=1;
                }
                break;
            case 'M':
            case 'm':
                {
                int LA1_2 = input.LA(2);

                if ( (LA1_2=='I'||LA1_2=='i') ) {
                    alt1=2;
                }
                else if ( (LA1_2=='A'||LA1_2=='a') ) {
                    alt1=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 2, input);

                    throw nvae;

                }
                }
                break;
            case 'A':
            case 'a':
                {
                alt1=4;
                }
                break;
            case 'C':
            case 'c':
                {
                alt1=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }

            switch (alt1) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1221:3: KEYWORD_SUM
                    {
                    mKEYWORD_SUM(); 


                    }
                    break;
                case 2 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1221:15: KEYWORD_MIN
                    {
                    mKEYWORD_MIN(); 


                    }
                    break;
                case 3 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1221:27: KEYWORD_MAX
                    {
                    mKEYWORD_MAX(); 


                    }
                    break;
                case 4 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1221:39: KEYWORD_AVG
                    {
                    mKEYWORD_AVG(); 


                    }
                    break;
                case 5 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1221:51: KEYWORD_COUNT
                    {
                    mKEYWORD_COUNT(); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FUNCTION_AGGREGATION"

    // $ANTLR start "KEYWORD_PARTITION"
    public final void mKEYWORD_PARTITION() throws RecognitionException {
        try {
            int _type = KEYWORD_PARTITION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1224:18: ( P A R T I T I O N )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1224:20: P A R T I T I O N
            {
            mP(); 


            mA(); 


            mR(); 


            mT(); 


            mI(); 


            mT(); 


            mI(); 


            mO(); 


            mN(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_PARTITION"

    // $ANTLR start "KEYWORD_INTO"
    public final void mKEYWORD_INTO() throws RecognitionException {
        try {
            int _type = KEYWORD_INTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1225:13: ( I N T O )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1225:15: I N T O
            {
            mI(); 


            mN(); 


            mT(); 


            mO(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_INTO"

    // $ANTLR start "KEYWORD_INFILE"
    public final void mKEYWORD_INFILE() throws RecognitionException {
        try {
            int _type = KEYWORD_INFILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1226:15: ( I N F I L E )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1226:17: I N F I L E
            {
            mI(); 


            mN(); 


            mF(); 


            mI(); 


            mL(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_INFILE"

    // $ANTLR start "KEYWORD_DATA"
    public final void mKEYWORD_DATA() throws RecognitionException {
        try {
            int _type = KEYWORD_DATA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1227:13: ( D A T A )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1227:15: D A T A
            {
            mD(); 


            mA(); 


            mT(); 


            mA(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_DATA"

    // $ANTLR start "KEYWORD_LOAD"
    public final void mKEYWORD_LOAD() throws RecognitionException {
        try {
            int _type = KEYWORD_LOAD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1228:13: ( L O A D )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1228:15: L O A D
            {
            mL(); 


            mO(); 


            mA(); 


            mD(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_LOAD"

    // $ANTLR start "KEYWORD_CALL"
    public final void mKEYWORD_CALL() throws RecognitionException {
        try {
            int _type = KEYWORD_CALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1229:13: ( C A L L )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1229:15: C A L L
            {
            mC(); 


            mA(); 


            mL(); 


            mL(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_CALL"

    // $ANTLR start "KEYWORD_CREATE"
    public final void mKEYWORD_CREATE() throws RecognitionException {
        try {
            int _type = KEYWORD_CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1230:15: ( C R E A T E )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1230:17: C R E A T E
            {
            mC(); 


            mR(); 


            mE(); 


            mA(); 


            mT(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_CREATE"

    // $ANTLR start "KEYWORD_DROP"
    public final void mKEYWORD_DROP() throws RecognitionException {
        try {
            int _type = KEYWORD_DROP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1231:13: ( D R O P )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1231:15: D R O P
            {
            mD(); 


            mR(); 


            mO(); 


            mP(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_DROP"

    // $ANTLR start "KEYWORD_SELECT"
    public final void mKEYWORD_SELECT() throws RecognitionException {
        try {
            int _type = KEYWORD_SELECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1232:15: ( S E L E C T )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1232:17: S E L E C T
            {
            mS(); 


            mE(); 


            mL(); 


            mE(); 


            mC(); 


            mT(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_SELECT"

    // $ANTLR start "KEYWORD_FROM"
    public final void mKEYWORD_FROM() throws RecognitionException {
        try {
            int _type = KEYWORD_FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1233:13: ( F R O M )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1233:15: F R O M
            {
            mF(); 


            mR(); 


            mO(); 


            mM(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_FROM"

    // $ANTLR start "KEYWORD_WHERE"
    public final void mKEYWORD_WHERE() throws RecognitionException {
        try {
            int _type = KEYWORD_WHERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1234:14: ( W H E R E )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1234:16: W H E R E
            {
            mW(); 


            mH(); 


            mE(); 


            mR(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_WHERE"

    // $ANTLR start "KEYWORD_HAVING"
    public final void mKEYWORD_HAVING() throws RecognitionException {
        try {
            int _type = KEYWORD_HAVING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1235:15: ( H A V I N G )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1235:17: H A V I N G
            {
            mH(); 


            mA(); 


            mV(); 


            mI(); 


            mN(); 


            mG(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_HAVING"

    // $ANTLR start "KEYWORD_GROUP"
    public final void mKEYWORD_GROUP() throws RecognitionException {
        try {
            int _type = KEYWORD_GROUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1236:14: ( G R O U P )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1236:16: G R O U P
            {
            mG(); 


            mR(); 


            mO(); 


            mU(); 


            mP(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_GROUP"

    // $ANTLR start "KEYWORD_BY"
    public final void mKEYWORD_BY() throws RecognitionException {
        try {
            int _type = KEYWORD_BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1237:11: ( B Y )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1237:13: B Y
            {
            mB(); 


            mY(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_BY"

    // $ANTLR start "KEYWORD_IN"
    public final void mKEYWORD_IN() throws RecognitionException {
        try {
            int _type = KEYWORD_IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1238:11: ( I N )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1238:13: I N
            {
            mI(); 


            mN(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_IN"

    // $ANTLR start "KEYWORD_OUT"
    public final void mKEYWORD_OUT() throws RecognitionException {
        try {
            int _type = KEYWORD_OUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1239:12: ( O U T )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1239:14: O U T
            {
            mO(); 


            mU(); 


            mT(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_OUT"

    // $ANTLR start "KEYWORD_AND"
    public final void mKEYWORD_AND() throws RecognitionException {
        try {
            int _type = KEYWORD_AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1240:12: ( A N D )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1240:14: A N D
            {
            mA(); 


            mN(); 


            mD(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_AND"

    // $ANTLR start "KEYWORD_OR"
    public final void mKEYWORD_OR() throws RecognitionException {
        try {
            int _type = KEYWORD_OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1241:11: ( O R )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1241:13: O R
            {
            mO(); 


            mR(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_OR"

    // $ANTLR start "KEYWORD_NOT"
    public final void mKEYWORD_NOT() throws RecognitionException {
        try {
            int _type = KEYWORD_NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1242:12: ( N O T )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1242:14: N O T
            {
            mN(); 


            mO(); 


            mT(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_NOT"

    // $ANTLR start "KEYWORD_AS"
    public final void mKEYWORD_AS() throws RecognitionException {
        try {
            int _type = KEYWORD_AS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1243:11: ( A S )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1243:13: A S
            {
            mA(); 


            mS(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_AS"

    // $ANTLR start "KEYWORD_LIKE"
    public final void mKEYWORD_LIKE() throws RecognitionException {
        try {
            int _type = KEYWORD_LIKE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1244:13: ( L I K E )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1244:15: L I K E
            {
            mL(); 


            mI(); 


            mK(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_LIKE"

    // $ANTLR start "KEYWORD_SUM"
    public final void mKEYWORD_SUM() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1245:21: ( S U M )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1245:23: S U M
            {
            mS(); 


            mU(); 


            mM(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_SUM"

    // $ANTLR start "KEYWORD_MIN"
    public final void mKEYWORD_MIN() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1246:21: ( M I N )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1246:23: M I N
            {
            mM(); 


            mI(); 


            mN(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_MIN"

    // $ANTLR start "KEYWORD_MAX"
    public final void mKEYWORD_MAX() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1247:21: ( M A X )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1247:23: M A X
            {
            mM(); 


            mA(); 


            mX(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_MAX"

    // $ANTLR start "KEYWORD_AVG"
    public final void mKEYWORD_AVG() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1248:21: ( A V G )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1248:23: A V G
            {
            mA(); 


            mV(); 


            mG(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_AVG"

    // $ANTLR start "KEYWORD_COUNT"
    public final void mKEYWORD_COUNT() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1249:23: ( C O U N T )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1249:25: C O U N T
            {
            mC(); 


            mO(); 


            mU(); 


            mN(); 


            mT(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_COUNT"

    // $ANTLR start "KEYWORD_DISTINCT"
    public final void mKEYWORD_DISTINCT() throws RecognitionException {
        try {
            int _type = KEYWORD_DISTINCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1250:17: ( D I S T I N C T )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1250:19: D I S T I N C T
            {
            mD(); 


            mI(); 


            mS(); 


            mT(); 


            mI(); 


            mN(); 


            mC(); 


            mT(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_DISTINCT"

    // $ANTLR start "KEYWORD_CONNECTION"
    public final void mKEYWORD_CONNECTION() throws RecognitionException {
        try {
            int _type = KEYWORD_CONNECTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1252:19: ( C O N N E C T I O N )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1252:21: C O N N E C T I O N
            {
            mC(); 


            mO(); 


            mN(); 


            mN(); 


            mE(); 


            mC(); 


            mT(); 


            mI(); 


            mO(); 


            mN(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_CONNECTION"

    // $ANTLR start "KEYWORD_PARTITIONED"
    public final void mKEYWORD_PARTITIONED() throws RecognitionException {
        try {
            int _type = KEYWORD_PARTITIONED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1253:20: ( P A R T I O N E D )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1253:22: P A R T I O N E D
            {
            mP(); 


            mA(); 


            mR(); 


            mT(); 


            mI(); 


            mO(); 


            mN(); 


            mE(); 


            mD(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_PARTITIONED"

    // $ANTLR start "KEYWORD_REPLICATED"
    public final void mKEYWORD_REPLICATED() throws RecognitionException {
        try {
            int _type = KEYWORD_REPLICATED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1254:19: ( R E P L I C A T E D )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1254:21: R E P L I C A T E D
            {
            mR(); 


            mE(); 


            mP(); 


            mL(); 


            mI(); 


            mC(); 


            mA(); 


            mT(); 


            mE(); 


            mD(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_REPLICATED"

    // $ANTLR start "KEYWORD_SCHEMA"
    public final void mKEYWORD_SCHEMA() throws RecognitionException {
        try {
            int _type = KEYWORD_SCHEMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1255:15: ( S C H E M A )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1255:17: S C H E M A
            {
            mS(); 


            mC(); 


            mH(); 


            mE(); 


            mM(); 


            mA(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_SCHEMA"

    // $ANTLR start "KEYWORD_TABLE"
    public final void mKEYWORD_TABLE() throws RecognitionException {
        try {
            int _type = KEYWORD_TABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1256:14: ( T A B L E )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1256:16: T A B L E
            {
            mT(); 


            mA(); 


            mB(); 


            mL(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_TABLE"

    // $ANTLR start "KEYWORD_FUNCTION"
    public final void mKEYWORD_FUNCTION() throws RecognitionException {
        try {
            int _type = KEYWORD_FUNCTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1257:17: ( F U N C T I O N )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1257:19: F U N C T I O N
            {
            mF(); 


            mU(); 


            mN(); 


            mC(); 


            mT(); 


            mI(); 


            mO(); 


            mN(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_FUNCTION"

    // $ANTLR start "KEYWORD_BEGIN"
    public final void mKEYWORD_BEGIN() throws RecognitionException {
        try {
            int _type = KEYWORD_BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1258:14: ( B E G I N )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1258:16: B E G I N
            {
            mB(); 


            mE(); 


            mG(); 


            mI(); 


            mN(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_BEGIN"

    // $ANTLR start "KEYWORD_END"
    public final void mKEYWORD_END() throws RecognitionException {
        try {
            int _type = KEYWORD_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1259:12: ( E N D )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1259:14: E N D
            {
            mE(); 


            mN(); 


            mD(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_END"

    // $ANTLR start "KEYWORD_VAR"
    public final void mKEYWORD_VAR() throws RecognitionException {
        try {
            int _type = KEYWORD_VAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1260:12: ( V A R )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1260:14: V A R
            {
            mV(); 


            mA(); 


            mR(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_VAR"

    // $ANTLR start "KEYWORD_URL"
    public final void mKEYWORD_URL() throws RecognitionException {
        try {
            int _type = KEYWORD_URL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1262:12: ( U R L )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1262:14: U R L
            {
            mU(); 


            mR(); 


            mL(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_URL"

    // $ANTLR start "KEYWORD_USER"
    public final void mKEYWORD_USER() throws RecognitionException {
        try {
            int _type = KEYWORD_USER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1263:13: ( U S E R )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1263:15: U S E R
            {
            mU(); 


            mS(); 


            mE(); 


            mR(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_USER"

    // $ANTLR start "KEYWORD_PASSWD"
    public final void mKEYWORD_PASSWD() throws RecognitionException {
        try {
            int _type = KEYWORD_PASSWD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1264:15: ( P A S S W O R D )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1264:17: P A S S W O R D
            {
            mP(); 


            mA(); 


            mS(); 


            mS(); 


            mW(); 


            mO(); 


            mR(); 


            mD(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_PASSWD"

    // $ANTLR start "KEYWORD_STORE"
    public final void mKEYWORD_STORE() throws RecognitionException {
        try {
            int _type = KEYWORD_STORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1265:14: ( S T O R E )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1265:16: S T O R E
            {
            mS(); 


            mT(); 


            mO(); 


            mR(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "KEYWORD_STORE"

    // $ANTLR start "TYPE_VARCHAR"
    public final void mTYPE_VARCHAR() throws RecognitionException {
        try {
            int _type = TYPE_VARCHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1267:13: ( V A R C H A R )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1267:15: V A R C H A R
            {
            mV(); 


            mA(); 


            mR(); 


            mC(); 


            mH(); 


            mA(); 


            mR(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TYPE_VARCHAR"

    // $ANTLR start "TYPE_INTEGER"
    public final void mTYPE_INTEGER() throws RecognitionException {
        try {
            int _type = TYPE_INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1268:13: ( ( I N T | I N T E G E R ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1268:15: ( I N T | I N T E G E R )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1268:15: ( I N T | I N T E G E R )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='I'||LA2_0=='i') ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1=='N'||LA2_1=='n') ) {
                    int LA2_2 = input.LA(3);

                    if ( (LA2_2=='T'||LA2_2=='t') ) {
                        int LA2_3 = input.LA(4);

                        if ( (LA2_3=='E'||LA2_3=='e') ) {
                            alt2=2;
                        }
                        else {
                            alt2=1;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1268:16: I N T
                    {
                    mI(); 


                    mN(); 


                    mT(); 


                    }
                    break;
                case 2 :
                    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1268:24: I N T E G E R
                    {
                    mI(); 


                    mN(); 


                    mT(); 


                    mE(); 


                    mG(); 


                    mE(); 


                    mR(); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TYPE_INTEGER"

    // $ANTLR start "TYPE_DECIMAL"
    public final void mTYPE_DECIMAL() throws RecognitionException {
        try {
            int _type = TYPE_DECIMAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1269:13: ( D E C I M A L )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1269:15: D E C I M A L
            {
            mD(); 


            mE(); 


            mC(); 


            mI(); 


            mM(); 


            mA(); 


            mL(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TYPE_DECIMAL"

    // $ANTLR start "TYPE_DATE"
    public final void mTYPE_DATE() throws RecognitionException {
        try {
            int _type = TYPE_DATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1270:10: ( D A T E )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1270:12: D A T E
            {
            mD(); 


            mA(); 


            mT(); 


            mE(); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TYPE_DATE"

    // $ANTLR start "LITERAL_STRING"
    public final void mLITERAL_STRING() throws RecognitionException {
        try {
            int _type = LITERAL_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1273:5: ( ( QUOTED_STRING ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1274:5: ( QUOTED_STRING )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1274:5: ( QUOTED_STRING )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1274:6: QUOTED_STRING
            {
            mQUOTED_STRING(); 


            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LITERAL_STRING"

    // $ANTLR start "LITERAL_DECIMAL"
    public final void mLITERAL_DECIMAL() throws RecognitionException {
        try {
            int _type = LITERAL_DECIMAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1278:5: ( ( DIGIT )+ DOT ( DIGIT )* )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1279:5: ( DIGIT )+ DOT ( DIGIT )*
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1279:5: ( DIGIT )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            mDOT(); 


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1279:18: ( DIGIT )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LITERAL_DECIMAL"

    // $ANTLR start "LITERAL_INTEGER"
    public final void mLITERAL_INTEGER() throws RecognitionException {
        try {
            int _type = LITERAL_INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1283:5: ( ( DIGIT )+ )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1284:5: ( DIGIT )+
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1284:5: ( DIGIT )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LITERAL_INTEGER"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1288:5: ( ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1289:5: ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* )
            {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1289:5: ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1289:7: CHAR ( CHAR | DIGIT | '_' | '$' | '#' )*
            {
            mCHAR(); 


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1289:12: ( CHAR | DIGIT | '_' | '$' | '#' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0 >= '#' && LA6_0 <= '$')||(LA6_0 >= '0' && LA6_0 <= '9')||(LA6_0 >= 'A' && LA6_0 <= 'Z')||LA6_0=='_'||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            	    {
            	    if ( (input.LA(1) >= '#' && input.LA(1) <= '$')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "IGNORE_CHAR"
    public final void mIGNORE_CHAR() throws RecognitionException {
        try {
            int _type = IGNORE_CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1293:12: ( ( WS | CONTROL_CHAR ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1293:14: ( WS | CONTROL_CHAR )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IGNORE_CHAR"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1295:12: ( ( ' ' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1295:14: ( ' ' )
            {
            if ( input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "CONTROL_CHAR"
    public final void mCONTROL_CHAR() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1296:22: ( ( '\\r' | '\\t' | '\\u000B' | '\\f' | '\\n' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\r') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONTROL_CHAR"

    // $ANTLR start "QUOTED_STRING"
    public final void mQUOTED_STRING() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1297:23: ( QUOTE_SINGLE (~ QUOTE_SINGLE )* QUOTE_SINGLE )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1297:25: QUOTE_SINGLE (~ QUOTE_SINGLE )* QUOTE_SINGLE
            {
            mQUOTE_SINGLE(); 


            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1297:38: (~ QUOTE_SINGLE )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= '\u0000' && LA7_0 <= '&')||(LA7_0 >= '(' && LA7_0 <= '\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            mQUOTE_SINGLE(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUOTED_STRING"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1298:17: ( '0' .. '9' )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1299:15: ( ( 'A' .. 'Z' | 'a' .. 'z' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "A"
    public final void mA() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1301:11: ( ( 'a' | 'A' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "A"

    // $ANTLR start "B"
    public final void mB() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1302:11: ( ( 'b' | 'B' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "B"

    // $ANTLR start "C"
    public final void mC() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1303:11: ( ( 'c' | 'C' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "C"

    // $ANTLR start "D"
    public final void mD() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1304:11: ( ( 'd' | 'D' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "D"

    // $ANTLR start "E"
    public final void mE() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1305:11: ( ( 'e' | 'E' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "E"

    // $ANTLR start "F"
    public final void mF() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1306:11: ( ( 'f' | 'F' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "F"

    // $ANTLR start "G"
    public final void mG() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1307:11: ( ( 'g' | 'G' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "G"

    // $ANTLR start "H"
    public final void mH() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1308:11: ( ( 'h' | 'H' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "H"

    // $ANTLR start "I"
    public final void mI() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1309:11: ( ( 'i' | 'I' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "I"

    // $ANTLR start "J"
    public final void mJ() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1310:11: ( ( 'j' | 'J' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "J"

    // $ANTLR start "K"
    public final void mK() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1311:11: ( ( 'k' | 'K' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "K"

    // $ANTLR start "L"
    public final void mL() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1312:11: ( ( 'l' | 'L' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "L"

    // $ANTLR start "M"
    public final void mM() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1313:11: ( ( 'm' | 'M' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "M"

    // $ANTLR start "N"
    public final void mN() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1314:11: ( ( 'n' | 'N' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "N"

    // $ANTLR start "O"
    public final void mO() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1315:11: ( ( 'o' | 'O' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "O"

    // $ANTLR start "P"
    public final void mP() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1316:11: ( ( 'p' | 'P' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "P"

    // $ANTLR start "Q"
    public final void mQ() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1317:11: ( ( 'q' | 'Q' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Q"

    // $ANTLR start "R"
    public final void mR() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1318:11: ( ( 'r' | 'R' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "R"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1319:11: ( ( 's' | 'S' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "T"
    public final void mT() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1320:11: ( ( 't' | 'T' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T"

    // $ANTLR start "U"
    public final void mU() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1321:11: ( ( 'u' | 'U' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "U"

    // $ANTLR start "V"
    public final void mV() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1322:11: ( ( 'v' | 'V' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "V"

    // $ANTLR start "W"
    public final void mW() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1323:11: ( ( 'w' | 'W' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "W"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1324:11: ( ( 'x' | 'X' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "X"

    // $ANTLR start "Y"
    public final void mY() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1325:11: ( ( 'y' | 'Y' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Y"

    // $ANTLR start "Z"
    public final void mZ() throws RecognitionException {
        try {
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1326:11: ( ( 'z' | 'Z' ) )
            // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
            {
            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Z"

    public void mTokens() throws RecognitionException {
        // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:8: ( AMPERSAND | APOSTROPHE | AT | BACKSLASH | COLON | COMMA | DIV | DOLLAR | DOT | DOUBLE_PIPE | EQUAL1 | EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | HAT | LBRACKET | LESS_EQUAL1 | LESS_EQUAL2 | LESS_THAN | LPAREN | MINUS | MOD | MULT | NOT_EQUAL1 | NOT_EQUAL2 | PIPE | PLUS | QUESTION | QUOTE_DOUBLE | QUOTE_SINGLE | QUOTE_TRIPLE | RBRACKET | RPAREN | SEMI | SHIFT_LEFT | SHIFT_RIGHT | TILDE | UNDERSCORE | FUNCTION_AGGREGATION | KEYWORD_PARTITION | KEYWORD_INTO | KEYWORD_INFILE | KEYWORD_DATA | KEYWORD_LOAD | KEYWORD_CALL | KEYWORD_CREATE | KEYWORD_DROP | KEYWORD_SELECT | KEYWORD_FROM | KEYWORD_WHERE | KEYWORD_HAVING | KEYWORD_GROUP | KEYWORD_BY | KEYWORD_IN | KEYWORD_OUT | KEYWORD_AND | KEYWORD_OR | KEYWORD_NOT | KEYWORD_AS | KEYWORD_LIKE | KEYWORD_DISTINCT | KEYWORD_CONNECTION | KEYWORD_PARTITIONED | KEYWORD_REPLICATED | KEYWORD_SCHEMA | KEYWORD_TABLE | KEYWORD_FUNCTION | KEYWORD_BEGIN | KEYWORD_END | KEYWORD_VAR | KEYWORD_URL | KEYWORD_USER | KEYWORD_PASSWD | KEYWORD_STORE | TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE | LITERAL_STRING | LITERAL_DECIMAL | LITERAL_INTEGER | IDENTIFIER | IGNORE_CHAR )
        int alt8=84;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:10: AMPERSAND
                {
                mAMPERSAND(); 


                }
                break;
            case 2 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:20: APOSTROPHE
                {
                mAPOSTROPHE(); 


                }
                break;
            case 3 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:31: AT
                {
                mAT(); 


                }
                break;
            case 4 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:34: BACKSLASH
                {
                mBACKSLASH(); 


                }
                break;
            case 5 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:44: COLON
                {
                mCOLON(); 


                }
                break;
            case 6 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:50: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 7 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:56: DIV
                {
                mDIV(); 


                }
                break;
            case 8 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:60: DOLLAR
                {
                mDOLLAR(); 


                }
                break;
            case 9 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:67: DOT
                {
                mDOT(); 


                }
                break;
            case 10 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:71: DOUBLE_PIPE
                {
                mDOUBLE_PIPE(); 


                }
                break;
            case 11 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:83: EQUAL1
                {
                mEQUAL1(); 


                }
                break;
            case 12 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:90: EQUAL2
                {
                mEQUAL2(); 


                }
                break;
            case 13 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:97: GREATER_EQUAL1
                {
                mGREATER_EQUAL1(); 


                }
                break;
            case 14 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:112: GREATER_EQUAL2
                {
                mGREATER_EQUAL2(); 


                }
                break;
            case 15 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:127: GREATER_THAN
                {
                mGREATER_THAN(); 


                }
                break;
            case 16 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:140: HAT
                {
                mHAT(); 


                }
                break;
            case 17 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:144: LBRACKET
                {
                mLBRACKET(); 


                }
                break;
            case 18 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:153: LESS_EQUAL1
                {
                mLESS_EQUAL1(); 


                }
                break;
            case 19 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:165: LESS_EQUAL2
                {
                mLESS_EQUAL2(); 


                }
                break;
            case 20 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:177: LESS_THAN
                {
                mLESS_THAN(); 


                }
                break;
            case 21 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:187: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 22 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:194: MINUS
                {
                mMINUS(); 


                }
                break;
            case 23 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:200: MOD
                {
                mMOD(); 


                }
                break;
            case 24 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:204: MULT
                {
                mMULT(); 


                }
                break;
            case 25 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:209: NOT_EQUAL1
                {
                mNOT_EQUAL1(); 


                }
                break;
            case 26 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:220: NOT_EQUAL2
                {
                mNOT_EQUAL2(); 


                }
                break;
            case 27 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:231: PIPE
                {
                mPIPE(); 


                }
                break;
            case 28 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:236: PLUS
                {
                mPLUS(); 


                }
                break;
            case 29 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:241: QUESTION
                {
                mQUESTION(); 


                }
                break;
            case 30 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:250: QUOTE_DOUBLE
                {
                mQUOTE_DOUBLE(); 


                }
                break;
            case 31 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:263: QUOTE_SINGLE
                {
                mQUOTE_SINGLE(); 


                }
                break;
            case 32 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:276: QUOTE_TRIPLE
                {
                mQUOTE_TRIPLE(); 


                }
                break;
            case 33 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:289: RBRACKET
                {
                mRBRACKET(); 


                }
                break;
            case 34 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:298: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 35 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:305: SEMI
                {
                mSEMI(); 


                }
                break;
            case 36 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:310: SHIFT_LEFT
                {
                mSHIFT_LEFT(); 


                }
                break;
            case 37 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:321: SHIFT_RIGHT
                {
                mSHIFT_RIGHT(); 


                }
                break;
            case 38 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:333: TILDE
                {
                mTILDE(); 


                }
                break;
            case 39 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:339: UNDERSCORE
                {
                mUNDERSCORE(); 


                }
                break;
            case 40 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:350: FUNCTION_AGGREGATION
                {
                mFUNCTION_AGGREGATION(); 


                }
                break;
            case 41 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:371: KEYWORD_PARTITION
                {
                mKEYWORD_PARTITION(); 


                }
                break;
            case 42 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:389: KEYWORD_INTO
                {
                mKEYWORD_INTO(); 


                }
                break;
            case 43 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:402: KEYWORD_INFILE
                {
                mKEYWORD_INFILE(); 


                }
                break;
            case 44 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:417: KEYWORD_DATA
                {
                mKEYWORD_DATA(); 


                }
                break;
            case 45 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:430: KEYWORD_LOAD
                {
                mKEYWORD_LOAD(); 


                }
                break;
            case 46 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:443: KEYWORD_CALL
                {
                mKEYWORD_CALL(); 


                }
                break;
            case 47 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:456: KEYWORD_CREATE
                {
                mKEYWORD_CREATE(); 


                }
                break;
            case 48 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:471: KEYWORD_DROP
                {
                mKEYWORD_DROP(); 


                }
                break;
            case 49 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:484: KEYWORD_SELECT
                {
                mKEYWORD_SELECT(); 


                }
                break;
            case 50 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:499: KEYWORD_FROM
                {
                mKEYWORD_FROM(); 


                }
                break;
            case 51 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:512: KEYWORD_WHERE
                {
                mKEYWORD_WHERE(); 


                }
                break;
            case 52 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:526: KEYWORD_HAVING
                {
                mKEYWORD_HAVING(); 


                }
                break;
            case 53 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:541: KEYWORD_GROUP
                {
                mKEYWORD_GROUP(); 


                }
                break;
            case 54 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:555: KEYWORD_BY
                {
                mKEYWORD_BY(); 


                }
                break;
            case 55 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:566: KEYWORD_IN
                {
                mKEYWORD_IN(); 


                }
                break;
            case 56 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:577: KEYWORD_OUT
                {
                mKEYWORD_OUT(); 


                }
                break;
            case 57 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:589: KEYWORD_AND
                {
                mKEYWORD_AND(); 


                }
                break;
            case 58 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:601: KEYWORD_OR
                {
                mKEYWORD_OR(); 


                }
                break;
            case 59 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:612: KEYWORD_NOT
                {
                mKEYWORD_NOT(); 


                }
                break;
            case 60 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:624: KEYWORD_AS
                {
                mKEYWORD_AS(); 


                }
                break;
            case 61 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:635: KEYWORD_LIKE
                {
                mKEYWORD_LIKE(); 


                }
                break;
            case 62 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:648: KEYWORD_DISTINCT
                {
                mKEYWORD_DISTINCT(); 


                }
                break;
            case 63 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:665: KEYWORD_CONNECTION
                {
                mKEYWORD_CONNECTION(); 


                }
                break;
            case 64 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:684: KEYWORD_PARTITIONED
                {
                mKEYWORD_PARTITIONED(); 


                }
                break;
            case 65 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:704: KEYWORD_REPLICATED
                {
                mKEYWORD_REPLICATED(); 


                }
                break;
            case 66 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:723: KEYWORD_SCHEMA
                {
                mKEYWORD_SCHEMA(); 


                }
                break;
            case 67 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:738: KEYWORD_TABLE
                {
                mKEYWORD_TABLE(); 


                }
                break;
            case 68 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:752: KEYWORD_FUNCTION
                {
                mKEYWORD_FUNCTION(); 


                }
                break;
            case 69 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:769: KEYWORD_BEGIN
                {
                mKEYWORD_BEGIN(); 


                }
                break;
            case 70 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:783: KEYWORD_END
                {
                mKEYWORD_END(); 


                }
                break;
            case 71 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:795: KEYWORD_VAR
                {
                mKEYWORD_VAR(); 


                }
                break;
            case 72 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:807: KEYWORD_URL
                {
                mKEYWORD_URL(); 


                }
                break;
            case 73 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:819: KEYWORD_USER
                {
                mKEYWORD_USER(); 


                }
                break;
            case 74 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:832: KEYWORD_PASSWD
                {
                mKEYWORD_PASSWD(); 


                }
                break;
            case 75 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:847: KEYWORD_STORE
                {
                mKEYWORD_STORE(); 


                }
                break;
            case 76 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:861: TYPE_VARCHAR
                {
                mTYPE_VARCHAR(); 


                }
                break;
            case 77 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:874: TYPE_INTEGER
                {
                mTYPE_INTEGER(); 


                }
                break;
            case 78 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:887: TYPE_DECIMAL
                {
                mTYPE_DECIMAL(); 


                }
                break;
            case 79 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:900: TYPE_DATE
                {
                mTYPE_DATE(); 


                }
                break;
            case 80 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:910: LITERAL_STRING
                {
                mLITERAL_STRING(); 


                }
                break;
            case 81 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:925: LITERAL_DECIMAL
                {
                mLITERAL_DECIMAL(); 


                }
                break;
            case 82 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:941: LITERAL_INTEGER
                {
                mLITERAL_INTEGER(); 


                }
                break;
            case 83 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:957: IDENTIFIER
                {
                mIDENTIFIER(); 


                }
                break;
            case 84 :
                // C:\\Users\\Rene\\Desktop\\DHBW-MA\\5. Semester\\Studienarbeit\\Workspace\\xdb\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:968: IGNORE_CHAR
                {
                mIGNORE_CHAR(); 


                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\12\uffff\1\66\1\70\1\73\3\uffff\1\102\7\uffff\1\104\5\uffff\24"+
        "\63\1\153\20\uffff\1\105\2\uffff\10\63\1\165\4\63\1\174\13\63\1"+
        "\u008a\2\63\1\u008d\7\63\3\uffff\1\u0095\3\63\3\u0095\1\u0099\1"+
        "\uffff\6\63\1\uffff\1\u00a0\14\63\1\uffff\1\63\1\u00b1\1\uffff\1"+
        "\u00b2\2\63\1\u00b5\1\u00b6\1\u00b8\1\63\1\uffff\3\63\1\uffff\2"+
        "\63\1\u00bf\3\63\1\uffff\1\u00c3\2\63\1\u00c6\1\u00c7\1\u00c8\2"+
        "\63\1\u00cb\1\u00cc\1\u00cd\5\63\2\uffff\2\63\2\uffff\1\63\1\uffff"+
        "\1\u00d6\2\63\1\u00d9\1\u0095\1\63\1\uffff\3\63\1\uffff\2\63\3\uffff"+
        "\2\63\3\uffff\1\63\1\u00e4\1\63\1\u00e6\1\u00e7\1\63\1\u00e9\1\63"+
        "\1\uffff\1\u00eb\1\u00ec\1\uffff\1\63\1\u00ee\4\63\1\u00f3\3\63"+
        "\1\uffff\1\u00f7\2\uffff\1\63\1\uffff\1\63\2\uffff\1\63\1\uffff"+
        "\3\63\1\u00a0\1\uffff\1\63\1\u00ff\1\63\1\uffff\1\63\1\u0102\3\63"+
        "\1\u0106\1\u0107\1\uffff\1\u0108\1\63\1\uffff\1\63\1\u010b\1\u010c"+
        "\3\uffff\1\63\1\u010e\2\uffff\1\u010f\2\uffff";
    static final String DFA8_eofS =
        "\u0110\uffff";
    static final String DFA8_minS =
        "\1\11\11\uffff\1\174\2\75\1\74\2\uffff\1\74\7\uffff\1\0\5\uffff"+
        "\1\103\1\101\1\116\2\101\1\116\1\101\1\111\1\122\1\110\1\101\1\122"+
        "\1\105\1\122\1\117\1\105\1\101\1\116\1\101\1\122\1\56\20\uffff\1"+
        "\47\2\uffff\1\115\1\114\1\110\1\117\1\116\1\130\1\107\1\104\1\43"+
        "\1\116\1\114\1\105\1\122\1\43\1\124\1\117\1\123\1\103\1\101\1\113"+
        "\1\117\1\116\1\105\1\126\1\117\1\43\1\107\1\124\1\43\1\124\1\120"+
        "\1\102\1\104\1\122\1\114\1\105\3\uffff\1\43\2\105\1\122\4\43\1\uffff"+
        "\2\116\1\114\1\101\1\124\1\123\1\uffff\1\43\1\111\1\101\1\120\1"+
        "\124\1\111\1\104\1\105\1\115\1\103\1\122\1\111\1\125\1\uffff\1\111"+
        "\1\43\1\uffff\1\43\2\114\3\43\1\122\1\uffff\1\103\1\115\1\105\1"+
        "\uffff\1\124\1\105\1\43\1\124\1\111\1\127\1\uffff\1\43\1\107\1\114"+
        "\3\43\1\111\1\115\3\43\1\124\1\105\1\116\1\120\1\116\2\uffff\1\111"+
        "\1\105\2\uffff\1\110\1\uffff\1\43\1\124\1\101\2\43\1\103\1\uffff"+
        "\1\105\2\117\1\uffff\2\105\3\uffff\1\116\1\101\3\uffff\1\111\1\43"+
        "\1\107\2\43\1\103\1\43\1\101\1\uffff\2\43\1\uffff\1\124\1\43\1\111"+
        "\1\116\2\122\1\43\1\103\1\114\1\117\1\uffff\1\43\2\uffff\1\101\1"+
        "\uffff\1\122\2\uffff\1\111\1\uffff\1\117\1\105\1\104\1\43\1\uffff"+
        "\1\124\1\43\1\116\1\uffff\1\124\1\43\1\117\1\116\1\104\2\43\1\uffff"+
        "\1\43\1\105\1\uffff\1\116\2\43\3\uffff\1\104\1\43\2\uffff\1\43\2"+
        "\uffff";
    static final String DFA8_maxS =
        "\1\176\11\uffff\1\174\1\75\2\76\2\uffff\1\76\7\uffff\1\uffff\5\uffff"+
        "\1\165\1\151\1\166\1\162\1\141\1\156\1\162\1\157\1\165\1\150\1\141"+
        "\1\162\1\171\1\165\1\157\1\145\1\141\1\156\1\141\1\163\1\71\20\uffff"+
        "\1\47\2\uffff\1\155\1\154\1\150\1\157\1\156\1\170\1\147\1\144\1"+
        "\172\1\165\1\154\1\145\1\163\1\172\1\164\1\157\1\163\1\143\1\141"+
        "\1\153\1\157\1\156\1\145\1\166\1\157\1\172\1\147\1\164\1\172\1\164"+
        "\1\160\1\142\1\144\1\162\1\154\1\145\3\uffff\1\172\2\145\1\162\4"+
        "\172\1\uffff\2\156\1\154\1\141\1\164\1\163\1\uffff\1\172\1\151\1"+
        "\145\1\160\1\164\1\151\1\144\1\145\1\155\1\143\1\162\1\151\1\165"+
        "\1\uffff\1\151\1\172\1\uffff\1\172\2\154\3\172\1\162\1\uffff\1\143"+
        "\1\155\1\145\1\uffff\1\164\1\145\1\172\1\164\1\151\1\167\1\uffff"+
        "\1\172\1\147\1\154\3\172\1\151\1\155\3\172\1\164\1\145\1\156\1\160"+
        "\1\156\2\uffff\1\151\1\145\2\uffff\1\150\1\uffff\1\172\1\164\1\141"+
        "\2\172\1\143\1\uffff\1\145\1\164\1\157\1\uffff\2\145\3\uffff\1\156"+
        "\1\141\3\uffff\1\151\1\172\1\147\2\172\1\143\1\172\1\141\1\uffff"+
        "\2\172\1\uffff\1\164\1\172\1\151\1\156\2\162\1\172\1\143\1\154\1"+
        "\157\1\uffff\1\172\2\uffff\1\141\1\uffff\1\162\2\uffff\1\151\1\uffff"+
        "\1\157\1\145\1\144\1\172\1\uffff\1\164\1\172\1\156\1\uffff\1\164"+
        "\1\172\1\157\1\156\1\144\2\172\1\uffff\1\172\1\145\1\uffff\1\156"+
        "\2\172\3\uffff\1\144\1\172\2\uffff\1\172\2\uffff";
    static final String DFA8_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\4\uffff\1\20\1\21"+
        "\1\uffff\1\25\1\26\1\27\1\30\1\34\1\35\1\36\1\uffff\1\41\1\42\1"+
        "\43\1\46\1\47\25\uffff\1\123\1\124\1\12\1\33\1\14\1\13\1\15\1\45"+
        "\1\17\1\16\1\23\1\31\1\22\1\32\1\44\1\24\1\uffff\1\37\1\120\44\uffff"+
        "\1\121\1\122\1\40\10\uffff\1\74\6\uffff\1\67\15\uffff\1\66\2\uffff"+
        "\1\72\7\uffff\1\50\3\uffff\1\71\6\uffff\1\115\20\uffff\1\70\1\73"+
        "\2\uffff\1\106\1\107\1\uffff\1\110\6\uffff\1\56\3\uffff\1\52\2\uffff"+
        "\1\54\1\117\1\60\2\uffff\1\55\1\75\1\62\10\uffff\1\111\2\uffff\1"+
        "\113\12\uffff\1\63\1\uffff\1\65\1\105\1\uffff\1\103\1\uffff\1\61"+
        "\1\102\1\uffff\1\57\4\uffff\1\53\3\uffff\1\64\7\uffff\1\116\2\uffff"+
        "\1\114\3\uffff\1\112\1\76\1\104\2\uffff\1\51\1\100\1\uffff\1\77"+
        "\1\101";
    static final String DFA8_specialS =
        "\30\uffff\1\0\u00f7\uffff}>";
    static final String[] DFA8_transitionS = {
            "\5\64\22\uffff\1\64\1\15\1\27\1\uffff\1\10\1\23\1\1\1\30\1\21"+
            "\1\32\1\24\1\25\1\6\1\22\1\11\1\7\12\62\1\5\1\33\1\20\1\13\1"+
            "\14\1\26\1\3\1\40\1\52\1\41\1\44\1\57\1\46\1\51\1\50\1\43\2"+
            "\63\1\45\1\37\1\54\1\53\1\42\1\63\1\55\1\36\1\56\1\61\1\60\1"+
            "\47\3\63\1\17\1\4\1\31\1\16\1\35\1\2\1\40\1\52\1\41\1\44\1\57"+
            "\1\46\1\51\1\50\1\43\2\63\1\45\1\37\1\54\1\53\1\42\1\63\1\55"+
            "\1\36\1\56\1\61\1\60\1\47\3\63\1\uffff\1\12\1\uffff\1\34",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\65",
            "\1\67",
            "\1\71\1\72",
            "\1\74\1\76\1\75",
            "",
            "",
            "\1\101\1\77\1\100",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\47\105\1\103\uffd8\105",
            "",
            "",
            "",
            "",
            "",
            "\1\110\1\uffff\1\107\16\uffff\1\111\1\106\15\uffff\1\110\1"+
            "\uffff\1\107\16\uffff\1\111\1\106",
            "\1\113\7\uffff\1\112\27\uffff\1\113\7\uffff\1\112",
            "\1\115\4\uffff\1\116\2\uffff\1\114\27\uffff\1\115\4\uffff\1"+
            "\116\2\uffff\1\114",
            "\1\120\15\uffff\1\117\2\uffff\1\121\16\uffff\1\120\15\uffff"+
            "\1\117\2\uffff\1\121",
            "\1\122\37\uffff\1\122",
            "\1\123\37\uffff\1\123",
            "\1\124\3\uffff\1\127\3\uffff\1\126\10\uffff\1\125\16\uffff"+
            "\1\124\3\uffff\1\127\3\uffff\1\126\10\uffff\1\125",
            "\1\131\5\uffff\1\130\31\uffff\1\131\5\uffff\1\130",
            "\1\132\2\uffff\1\133\34\uffff\1\132\2\uffff\1\133",
            "\1\134\37\uffff\1\134",
            "\1\135\37\uffff\1\135",
            "\1\136\37\uffff\1\136",
            "\1\140\23\uffff\1\137\13\uffff\1\140\23\uffff\1\137",
            "\1\142\2\uffff\1\141\34\uffff\1\142\2\uffff\1\141",
            "\1\143\37\uffff\1\143",
            "\1\144\37\uffff\1\144",
            "\1\145\37\uffff\1\145",
            "\1\146\37\uffff\1\146",
            "\1\147\37\uffff\1\147",
            "\1\150\1\151\36\uffff\1\150\1\151",
            "\1\152\1\uffff\12\62",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\154",
            "",
            "",
            "\1\155\37\uffff\1\155",
            "\1\156\37\uffff\1\156",
            "\1\157\37\uffff\1\157",
            "\1\160\37\uffff\1\160",
            "\1\161\37\uffff\1\161",
            "\1\162\37\uffff\1\162",
            "\1\163\37\uffff\1\163",
            "\1\164\37\uffff\1\164",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\167\6\uffff\1\166\30\uffff\1\167\6\uffff\1\166",
            "\1\170\37\uffff\1\170",
            "\1\171\37\uffff\1\171",
            "\1\172\1\173\36\uffff\1\172\1\173",
            "\2\63\13\uffff\12\63\7\uffff\5\63\1\176\15\63\1\175\6\63\4"+
            "\uffff\1\63\1\uffff\5\63\1\176\15\63\1\175\6\63",
            "\1\177\37\uffff\1\177",
            "\1\u0080\37\uffff\1\u0080",
            "\1\u0081\37\uffff\1\u0081",
            "\1\u0082\37\uffff\1\u0082",
            "\1\u0083\37\uffff\1\u0083",
            "\1\u0084\37\uffff\1\u0084",
            "\1\u0085\37\uffff\1\u0085",
            "\1\u0086\37\uffff\1\u0086",
            "\1\u0087\37\uffff\1\u0087",
            "\1\u0088\37\uffff\1\u0088",
            "\1\u0089\37\uffff\1\u0089",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u008b\37\uffff\1\u008b",
            "\1\u008c\37\uffff\1\u008c",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u008e\37\uffff\1\u008e",
            "\1\u008f\37\uffff\1\u008f",
            "\1\u0090\37\uffff\1\u0090",
            "\1\u0091\37\uffff\1\u0091",
            "\1\u0092\37\uffff\1\u0092",
            "\1\u0093\37\uffff\1\u0093",
            "\1\u0094\37\uffff\1\u0094",
            "",
            "",
            "",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u0096\37\uffff\1\u0096",
            "\1\u0097\37\uffff\1\u0097",
            "\1\u0098\37\uffff\1\u0098",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "",
            "\1\u009a\37\uffff\1\u009a",
            "\1\u009b\37\uffff\1\u009b",
            "\1\u009c\37\uffff\1\u009c",
            "\1\u009d\37\uffff\1\u009d",
            "\1\u009e\37\uffff\1\u009e",
            "\1\u009f\37\uffff\1\u009f",
            "",
            "\2\63\13\uffff\12\63\7\uffff\4\63\1\u00a2\11\63\1\u00a1\13"+
            "\63\4\uffff\1\63\1\uffff\4\63\1\u00a2\11\63\1\u00a1\13\63",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\u00a4\3\uffff\1\u00a5\33\uffff\1\u00a4\3\uffff\1\u00a5",
            "\1\u00a6\37\uffff\1\u00a6",
            "\1\u00a7\37\uffff\1\u00a7",
            "\1\u00a8\37\uffff\1\u00a8",
            "\1\u00a9\37\uffff\1\u00a9",
            "\1\u00aa\37\uffff\1\u00aa",
            "\1\u00ab\37\uffff\1\u00ab",
            "\1\u00ac\37\uffff\1\u00ac",
            "\1\u00ad\37\uffff\1\u00ad",
            "\1\u00ae\37\uffff\1\u00ae",
            "\1\u00af\37\uffff\1\u00af",
            "",
            "\1\u00b0\37\uffff\1\u00b0",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00b3\37\uffff\1\u00b3",
            "\1\u00b4\37\uffff\1\u00b4",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\2\63\1\u00b7\27\63\4\uffff\1\63"+
            "\1\uffff\2\63\1\u00b7\27\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00b9\37\uffff\1\u00b9",
            "",
            "\1\u00ba\37\uffff\1\u00ba",
            "\1\u00bb\37\uffff\1\u00bb",
            "\1\u00bc\37\uffff\1\u00bc",
            "",
            "\1\u00bd\37\uffff\1\u00bd",
            "\1\u00be\37\uffff\1\u00be",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00c0\37\uffff\1\u00c0",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\u00c2\37\uffff\1\u00c2",
            "",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00c4\37\uffff\1\u00c4",
            "\1\u00c5\37\uffff\1\u00c5",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00c9\37\uffff\1\u00c9",
            "\1\u00ca\37\uffff\1\u00ca",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00ce\37\uffff\1\u00ce",
            "\1\u00cf\37\uffff\1\u00cf",
            "\1\u00d0\37\uffff\1\u00d0",
            "\1\u00d1\37\uffff\1\u00d1",
            "\1\u00d2\37\uffff\1\u00d2",
            "",
            "",
            "\1\u00d3\37\uffff\1\u00d3",
            "\1\u00d4\37\uffff\1\u00d4",
            "",
            "",
            "\1\u00d5\37\uffff\1\u00d5",
            "",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00d7\37\uffff\1\u00d7",
            "\1\u00d8\37\uffff\1\u00d8",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00da\37\uffff\1\u00da",
            "",
            "\1\u00db\37\uffff\1\u00db",
            "\1\u00dd\4\uffff\1\u00dc\32\uffff\1\u00dd\4\uffff\1\u00dc",
            "\1\u00de\37\uffff\1\u00de",
            "",
            "\1\u00df\37\uffff\1\u00df",
            "\1\u00e0\37\uffff\1\u00e0",
            "",
            "",
            "",
            "\1\u00e1\37\uffff\1\u00e1",
            "\1\u00e2\37\uffff\1\u00e2",
            "",
            "",
            "",
            "\1\u00e3\37\uffff\1\u00e3",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00e5\37\uffff\1\u00e5",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00e8\37\uffff\1\u00e8",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00ea\37\uffff\1\u00ea",
            "",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "",
            "\1\u00ed\37\uffff\1\u00ed",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00ef\37\uffff\1\u00ef",
            "\1\u00f0\37\uffff\1\u00f0",
            "\1\u00f1\37\uffff\1\u00f1",
            "\1\u00f2\37\uffff\1\u00f2",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u00f4\37\uffff\1\u00f4",
            "\1\u00f5\37\uffff\1\u00f5",
            "\1\u00f6\37\uffff\1\u00f6",
            "",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "",
            "",
            "\1\u00f8\37\uffff\1\u00f8",
            "",
            "\1\u00f9\37\uffff\1\u00f9",
            "",
            "",
            "\1\u00fa\37\uffff\1\u00fa",
            "",
            "\1\u00fb\37\uffff\1\u00fb",
            "\1\u00fc\37\uffff\1\u00fc",
            "\1\u00fd\37\uffff\1\u00fd",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "",
            "\1\u00fe\37\uffff\1\u00fe",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u0100\37\uffff\1\u0100",
            "",
            "\1\u0101\37\uffff\1\u0101",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u0103\37\uffff\1\u0103",
            "\1\u0104\37\uffff\1\u0104",
            "\1\u0105\37\uffff\1\u0105",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\1\u0109\37\uffff\1\u0109",
            "",
            "\1\u010a\37\uffff\1\u010a",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "",
            "",
            "",
            "\1\u010d\37\uffff\1\u010d",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "",
            "",
            "\2\63\13\uffff\12\63\7\uffff\32\63\4\uffff\1\63\1\uffff\32"+
            "\63",
            "",
            ""
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
            return "1:1: Tokens : ( AMPERSAND | APOSTROPHE | AT | BACKSLASH | COLON | COMMA | DIV | DOLLAR | DOT | DOUBLE_PIPE | EQUAL1 | EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | HAT | LBRACKET | LESS_EQUAL1 | LESS_EQUAL2 | LESS_THAN | LPAREN | MINUS | MOD | MULT | NOT_EQUAL1 | NOT_EQUAL2 | PIPE | PLUS | QUESTION | QUOTE_DOUBLE | QUOTE_SINGLE | QUOTE_TRIPLE | RBRACKET | RPAREN | SEMI | SHIFT_LEFT | SHIFT_RIGHT | TILDE | UNDERSCORE | FUNCTION_AGGREGATION | KEYWORD_PARTITION | KEYWORD_INTO | KEYWORD_INFILE | KEYWORD_DATA | KEYWORD_LOAD | KEYWORD_CALL | KEYWORD_CREATE | KEYWORD_DROP | KEYWORD_SELECT | KEYWORD_FROM | KEYWORD_WHERE | KEYWORD_HAVING | KEYWORD_GROUP | KEYWORD_BY | KEYWORD_IN | KEYWORD_OUT | KEYWORD_AND | KEYWORD_OR | KEYWORD_NOT | KEYWORD_AS | KEYWORD_LIKE | KEYWORD_DISTINCT | KEYWORD_CONNECTION | KEYWORD_PARTITIONED | KEYWORD_REPLICATED | KEYWORD_SCHEMA | KEYWORD_TABLE | KEYWORD_FUNCTION | KEYWORD_BEGIN | KEYWORD_END | KEYWORD_VAR | KEYWORD_URL | KEYWORD_USER | KEYWORD_PASSWD | KEYWORD_STORE | TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE | LITERAL_STRING | LITERAL_DECIMAL | LITERAL_INTEGER | IDENTIFIER | IGNORE_CHAR );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_24 = input.LA(1);

                        s = -1;
                        if ( (LA8_24=='\'') ) {s = 67;}

                        else if ( ((LA8_24 >= '\u0000' && LA8_24 <= '&')||(LA8_24 >= '(' && LA8_24 <= '\uFFFF')) ) {s = 69;}

                        else s = 68;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}