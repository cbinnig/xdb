// $ANTLR 3.4 C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g 2012-11-06 22:32:36
 
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
    public String getGrammarFileName() { return "C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g"; }

    // $ANTLR start "AMPERSAND"
    public final void mAMPERSAND() throws RecognitionException {
        try {
            int _type = AMPERSAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:6:11: ( '&' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:6:13: '&'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:7:12: ( '`' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:7:14: '`'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:8:4: ( '@' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:8:6: '@'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:9:11: ( '\\\\' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:9:13: '\\\\'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:10:7: ( ':' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:10:9: ':'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:11:7: ( ',' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:11:9: ','
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:12:5: ( '/' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:12:7: '/'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:13:8: ( '$' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:13:10: '$'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:14:5: ( '.' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:14:7: '.'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:15:13: ( '||' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:15:15: '||'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:16:8: ( '=' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:16:10: '='
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:17:8: ( '==' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:17:10: '=='
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:18:16: ( '>=' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:18:18: '>='
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:19:16: ( '!<' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:19:18: '!<'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:20:14: ( '>' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:20:16: '>'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:21:5: ( '^' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:21:7: '^'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:22:10: ( '[' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:22:12: '['
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:23:13: ( '<=' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:23:15: '<='
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:24:13: ( '!>' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:24:15: '!>'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:25:11: ( '<' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:25:13: '<'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:26:8: ( '(' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:26:10: '('
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:27:7: ( '-' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:27:9: '-'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:28:5: ( '%' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:28:7: '%'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:29:6: ( '*' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:29:8: '*'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:30:12: ( '!=' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:30:14: '!='
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:31:12: ( '<>' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:31:14: '<>'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:32:6: ( '|' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:32:8: '|'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:33:6: ( '+' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:33:8: '+'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:34:10: ( '?' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:34:12: '?'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:35:14: ( '\"' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:35:16: '\"'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:36:14: ( '\\'' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:36:16: '\\''
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:37:14: ( '\\'\\'\\'' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:37:16: '\\'\\'\\''
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:38:10: ( ']' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:38:12: ']'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:39:8: ( ')' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:39:10: ')'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:40:6: ( ';' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:40:8: ';'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:41:12: ( '<<' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:41:14: '<<'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:42:13: ( '>>' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:42:15: '>>'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:43:7: ( '~' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:43:9: '~'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:44:12: ( '_' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:44:14: '_'
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:925:2: ( ( KEYWORD_SUM | KEYWORD_MIN | KEYWORD_MAX | KEYWORD_AVG | KEYWORD_COUNT ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:926:2: ( KEYWORD_SUM | KEYWORD_MIN | KEYWORD_MAX | KEYWORD_AVG | KEYWORD_COUNT )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:926:2: ( KEYWORD_SUM | KEYWORD_MIN | KEYWORD_MAX | KEYWORD_AVG | KEYWORD_COUNT )
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:926:3: KEYWORD_SUM
                    {
                    mKEYWORD_SUM(); 


                    }
                    break;
                case 2 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:926:15: KEYWORD_MIN
                    {
                    mKEYWORD_MIN(); 


                    }
                    break;
                case 3 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:926:27: KEYWORD_MAX
                    {
                    mKEYWORD_MAX(); 


                    }
                    break;
                case 4 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:926:39: KEYWORD_AVG
                    {
                    mKEYWORD_AVG(); 


                    }
                    break;
                case 5 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:926:51: KEYWORD_COUNT
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

    // $ANTLR start "KEYWORD_CREATE"
    public final void mKEYWORD_CREATE() throws RecognitionException {
        try {
            int _type = KEYWORD_CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:929:15: ( C R E A T E )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:929:17: C R E A T E
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:930:13: ( D R O P )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:930:15: D R O P
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:931:15: ( S E L E C T )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:931:17: S E L E C T
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:932:13: ( F R O M )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:932:15: F R O M
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:933:14: ( W H E R E )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:933:16: W H E R E
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:934:15: ( H A V I N G )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:934:17: H A V I N G
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:935:14: ( G R O U P )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:935:16: G R O U P
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:936:11: ( B Y )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:936:13: B Y
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:937:11: ( I N )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:937:13: I N
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:938:12: ( O U T )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:938:14: O U T
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:939:12: ( A N D )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:939:14: A N D
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:940:11: ( O R )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:940:13: O R
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:941:12: ( N O T )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:941:14: N O T
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:942:11: ( A S )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:942:13: A S
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

    // $ANTLR start "KEYWORD_SUM"
    public final void mKEYWORD_SUM() throws RecognitionException {
        try {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:943:21: ( S U M )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:943:23: S U M
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:944:21: ( M I N )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:944:23: M I N
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:945:21: ( M A X )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:945:23: M A X
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:946:21: ( A V G )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:946:23: A V G
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:947:23: ( C O U N T )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:947:25: C O U N T
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:948:17: ( D I S T I N C T )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:948:19: D I S T I N C T
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:950:19: ( C O N N E C T I O N )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:950:21: C O N N E C T I O N
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

    // $ANTLR start "KEYWORD_SCHEMA"
    public final void mKEYWORD_SCHEMA() throws RecognitionException {
        try {
            int _type = KEYWORD_SCHEMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:951:15: ( S C H E M A )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:951:17: S C H E M A
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:952:14: ( T A B L E )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:952:16: T A B L E
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:953:17: ( F U N C T I O N )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:953:19: F U N C T I O N
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:954:14: ( B E G I N )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:954:16: B E G I N
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:955:12: ( E N D )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:955:14: E N D
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:956:12: ( V A R )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:956:14: V A R
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:958:12: ( U R L )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:958:14: U R L
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:959:13: ( U S E R )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:959:15: U S E R
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:960:15: ( P A S S W O R D )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:960:17: P A S S W O R D
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:961:14: ( S T O R E )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:961:16: S T O R E
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:963:13: ( V A R C H A R )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:963:15: V A R C H A R
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:964:13: ( ( I N T | I N T E G E R ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:964:15: ( I N T | I N T E G E R )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:964:15: ( I N T | I N T E G E R )
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
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:964:16: I N T
                    {
                    mI(); 


                    mN(); 


                    mT(); 


                    }
                    break;
                case 2 :
                    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:964:24: I N T E G E R
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

    // $ANTLR start "LITERAL_DATE"
    public final void mLITERAL_DATE() throws RecognitionException {
        try {
            int _type = LITERAL_DATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:967:5: ( ( D A T E QUOTED_STRING ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:968:5: ( D A T E QUOTED_STRING )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:968:5: ( D A T E QUOTED_STRING )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:968:6: D A T E QUOTED_STRING
            {
            mD(); 


            mA(); 


            mT(); 


            mE(); 


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
    // $ANTLR end "LITERAL_DATE"

    // $ANTLR start "LITERAL_STRING"
    public final void mLITERAL_STRING() throws RecognitionException {
        try {
            int _type = LITERAL_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:972:5: ( ( QUOTED_STRING ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:973:5: ( QUOTED_STRING )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:973:5: ( QUOTED_STRING )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:973:6: QUOTED_STRING
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:977:5: ( ( DIGIT )+ DOT ( DIGIT )* )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:978:5: ( DIGIT )+ DOT ( DIGIT )*
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:978:5: ( DIGIT )+
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
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:978:18: ( DIGIT )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:982:5: ( ( DIGIT )+ )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:983:5: ( DIGIT )+
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:983:5: ( DIGIT )+
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
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:987:5: ( ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:988:5: ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* )
            {
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:988:5: ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:988:7: CHAR ( CHAR | DIGIT | '_' | '$' | '#' )*
            {
            mCHAR(); 


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:988:12: ( CHAR | DIGIT | '_' | '$' | '#' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0 >= '#' && LA6_0 <= '$')||(LA6_0 >= '0' && LA6_0 <= '9')||(LA6_0 >= 'A' && LA6_0 <= 'Z')||LA6_0=='_'||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:992:12: ( ( WS | CONTROL_CHAR ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:992:14: ( WS | CONTROL_CHAR )
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:994:12: ( ( ' ' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:994:14: ( ' ' )
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:995:22: ( ( '\\r' | '\\t' | '\\u000B' | '\\f' | '\\n' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:996:23: ( QUOTE_SINGLE (~ QUOTE_SINGLE )* QUOTE_SINGLE )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:996:25: QUOTE_SINGLE (~ QUOTE_SINGLE )* QUOTE_SINGLE
            {
            mQUOTE_SINGLE(); 


            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:996:38: (~ QUOTE_SINGLE )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= '\u0000' && LA7_0 <= '&')||(LA7_0 >= '(' && LA7_0 <= '\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:997:17: ( '0' .. '9' )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:998:15: ( ( 'A' .. 'Z' | 'a' .. 'z' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1000:11: ( ( 'a' | 'A' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1001:11: ( ( 'b' | 'B' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1002:11: ( ( 'c' | 'C' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1003:11: ( ( 'd' | 'D' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1004:11: ( ( 'e' | 'E' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1005:11: ( ( 'f' | 'F' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1006:11: ( ( 'g' | 'G' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1007:11: ( ( 'h' | 'H' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1008:11: ( ( 'i' | 'I' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1009:11: ( ( 'j' | 'J' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1010:11: ( ( 'k' | 'K' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1011:11: ( ( 'l' | 'L' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1012:11: ( ( 'm' | 'M' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1013:11: ( ( 'n' | 'N' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1014:11: ( ( 'o' | 'O' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1015:11: ( ( 'p' | 'P' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1016:11: ( ( 'q' | 'Q' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1017:11: ( ( 'r' | 'R' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1018:11: ( ( 's' | 'S' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1019:11: ( ( 't' | 'T' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1020:11: ( ( 'u' | 'U' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1021:11: ( ( 'v' | 'V' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1022:11: ( ( 'w' | 'W' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1023:11: ( ( 'x' | 'X' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1024:11: ( ( 'y' | 'Y' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1025:11: ( ( 'z' | 'Z' ) )
            // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:
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
        // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:8: ( AMPERSAND | APOSTROPHE | AT | BACKSLASH | COLON | COMMA | DIV | DOLLAR | DOT | DOUBLE_PIPE | EQUAL1 | EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | HAT | LBRACKET | LESS_EQUAL1 | LESS_EQUAL2 | LESS_THAN | LPAREN | MINUS | MOD | MULT | NOT_EQUAL1 | NOT_EQUAL2 | PIPE | PLUS | QUESTION | QUOTE_DOUBLE | QUOTE_SINGLE | QUOTE_TRIPLE | RBRACKET | RPAREN | SEMI | SHIFT_LEFT | SHIFT_RIGHT | TILDE | UNDERSCORE | FUNCTION_AGGREGATION | KEYWORD_CREATE | KEYWORD_DROP | KEYWORD_SELECT | KEYWORD_FROM | KEYWORD_WHERE | KEYWORD_HAVING | KEYWORD_GROUP | KEYWORD_BY | KEYWORD_IN | KEYWORD_OUT | KEYWORD_AND | KEYWORD_OR | KEYWORD_NOT | KEYWORD_AS | KEYWORD_DISTINCT | KEYWORD_CONNECTION | KEYWORD_SCHEMA | KEYWORD_TABLE | KEYWORD_FUNCTION | KEYWORD_BEGIN | KEYWORD_END | KEYWORD_VAR | KEYWORD_URL | KEYWORD_USER | KEYWORD_PASSWD | KEYWORD_STORE | TYPE_VARCHAR | TYPE_INTEGER | LITERAL_DATE | LITERAL_STRING | LITERAL_DECIMAL | LITERAL_INTEGER | IDENTIFIER | IGNORE_CHAR )
        int alt8=74;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:10: AMPERSAND
                {
                mAMPERSAND(); 


                }
                break;
            case 2 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:20: APOSTROPHE
                {
                mAPOSTROPHE(); 


                }
                break;
            case 3 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:31: AT
                {
                mAT(); 


                }
                break;
            case 4 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:34: BACKSLASH
                {
                mBACKSLASH(); 


                }
                break;
            case 5 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:44: COLON
                {
                mCOLON(); 


                }
                break;
            case 6 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:50: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 7 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:56: DIV
                {
                mDIV(); 


                }
                break;
            case 8 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:60: DOLLAR
                {
                mDOLLAR(); 


                }
                break;
            case 9 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:67: DOT
                {
                mDOT(); 


                }
                break;
            case 10 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:71: DOUBLE_PIPE
                {
                mDOUBLE_PIPE(); 


                }
                break;
            case 11 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:83: EQUAL1
                {
                mEQUAL1(); 


                }
                break;
            case 12 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:90: EQUAL2
                {
                mEQUAL2(); 


                }
                break;
            case 13 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:97: GREATER_EQUAL1
                {
                mGREATER_EQUAL1(); 


                }
                break;
            case 14 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:112: GREATER_EQUAL2
                {
                mGREATER_EQUAL2(); 


                }
                break;
            case 15 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:127: GREATER_THAN
                {
                mGREATER_THAN(); 


                }
                break;
            case 16 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:140: HAT
                {
                mHAT(); 


                }
                break;
            case 17 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:144: LBRACKET
                {
                mLBRACKET(); 


                }
                break;
            case 18 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:153: LESS_EQUAL1
                {
                mLESS_EQUAL1(); 


                }
                break;
            case 19 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:165: LESS_EQUAL2
                {
                mLESS_EQUAL2(); 


                }
                break;
            case 20 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:177: LESS_THAN
                {
                mLESS_THAN(); 


                }
                break;
            case 21 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:187: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 22 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:194: MINUS
                {
                mMINUS(); 


                }
                break;
            case 23 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:200: MOD
                {
                mMOD(); 


                }
                break;
            case 24 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:204: MULT
                {
                mMULT(); 


                }
                break;
            case 25 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:209: NOT_EQUAL1
                {
                mNOT_EQUAL1(); 


                }
                break;
            case 26 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:220: NOT_EQUAL2
                {
                mNOT_EQUAL2(); 


                }
                break;
            case 27 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:231: PIPE
                {
                mPIPE(); 


                }
                break;
            case 28 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:236: PLUS
                {
                mPLUS(); 


                }
                break;
            case 29 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:241: QUESTION
                {
                mQUESTION(); 


                }
                break;
            case 30 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:250: QUOTE_DOUBLE
                {
                mQUOTE_DOUBLE(); 


                }
                break;
            case 31 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:263: QUOTE_SINGLE
                {
                mQUOTE_SINGLE(); 


                }
                break;
            case 32 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:276: QUOTE_TRIPLE
                {
                mQUOTE_TRIPLE(); 


                }
                break;
            case 33 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:289: RBRACKET
                {
                mRBRACKET(); 


                }
                break;
            case 34 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:298: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 35 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:305: SEMI
                {
                mSEMI(); 


                }
                break;
            case 36 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:310: SHIFT_LEFT
                {
                mSHIFT_LEFT(); 


                }
                break;
            case 37 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:321: SHIFT_RIGHT
                {
                mSHIFT_RIGHT(); 


                }
                break;
            case 38 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:333: TILDE
                {
                mTILDE(); 


                }
                break;
            case 39 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:339: UNDERSCORE
                {
                mUNDERSCORE(); 


                }
                break;
            case 40 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:350: FUNCTION_AGGREGATION
                {
                mFUNCTION_AGGREGATION(); 


                }
                break;
            case 41 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:371: KEYWORD_CREATE
                {
                mKEYWORD_CREATE(); 


                }
                break;
            case 42 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:386: KEYWORD_DROP
                {
                mKEYWORD_DROP(); 


                }
                break;
            case 43 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:399: KEYWORD_SELECT
                {
                mKEYWORD_SELECT(); 


                }
                break;
            case 44 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:414: KEYWORD_FROM
                {
                mKEYWORD_FROM(); 


                }
                break;
            case 45 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:427: KEYWORD_WHERE
                {
                mKEYWORD_WHERE(); 


                }
                break;
            case 46 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:441: KEYWORD_HAVING
                {
                mKEYWORD_HAVING(); 


                }
                break;
            case 47 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:456: KEYWORD_GROUP
                {
                mKEYWORD_GROUP(); 


                }
                break;
            case 48 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:470: KEYWORD_BY
                {
                mKEYWORD_BY(); 


                }
                break;
            case 49 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:481: KEYWORD_IN
                {
                mKEYWORD_IN(); 


                }
                break;
            case 50 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:492: KEYWORD_OUT
                {
                mKEYWORD_OUT(); 


                }
                break;
            case 51 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:504: KEYWORD_AND
                {
                mKEYWORD_AND(); 


                }
                break;
            case 52 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:516: KEYWORD_OR
                {
                mKEYWORD_OR(); 


                }
                break;
            case 53 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:527: KEYWORD_NOT
                {
                mKEYWORD_NOT(); 


                }
                break;
            case 54 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:539: KEYWORD_AS
                {
                mKEYWORD_AS(); 


                }
                break;
            case 55 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:550: KEYWORD_DISTINCT
                {
                mKEYWORD_DISTINCT(); 


                }
                break;
            case 56 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:567: KEYWORD_CONNECTION
                {
                mKEYWORD_CONNECTION(); 


                }
                break;
            case 57 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:586: KEYWORD_SCHEMA
                {
                mKEYWORD_SCHEMA(); 


                }
                break;
            case 58 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:601: KEYWORD_TABLE
                {
                mKEYWORD_TABLE(); 


                }
                break;
            case 59 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:615: KEYWORD_FUNCTION
                {
                mKEYWORD_FUNCTION(); 


                }
                break;
            case 60 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:632: KEYWORD_BEGIN
                {
                mKEYWORD_BEGIN(); 


                }
                break;
            case 61 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:646: KEYWORD_END
                {
                mKEYWORD_END(); 


                }
                break;
            case 62 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:658: KEYWORD_VAR
                {
                mKEYWORD_VAR(); 


                }
                break;
            case 63 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:670: KEYWORD_URL
                {
                mKEYWORD_URL(); 


                }
                break;
            case 64 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:682: KEYWORD_USER
                {
                mKEYWORD_USER(); 


                }
                break;
            case 65 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:695: KEYWORD_PASSWD
                {
                mKEYWORD_PASSWD(); 


                }
                break;
            case 66 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:710: KEYWORD_STORE
                {
                mKEYWORD_STORE(); 


                }
                break;
            case 67 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:724: TYPE_VARCHAR
                {
                mTYPE_VARCHAR(); 


                }
                break;
            case 68 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:737: TYPE_INTEGER
                {
                mTYPE_INTEGER(); 


                }
                break;
            case 69 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:750: LITERAL_DATE
                {
                mLITERAL_DATE(); 


                }
                break;
            case 70 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:763: LITERAL_STRING
                {
                mLITERAL_STRING(); 


                }
                break;
            case 71 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:778: LITERAL_DECIMAL
                {
                mLITERAL_DECIMAL(); 


                }
                break;
            case 72 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:794: LITERAL_INTEGER
                {
                mLITERAL_INTEGER(); 


                }
                break;
            case 73 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:810: IDENTIFIER
                {
                mIDENTIFIER(); 


                }
                break;
            case 74 :
                // C:\\Users\\Larissa\\workspace\\XDB\\src\\org\\xdb\\funsql\\compile\\antlr\\FunSQL.g:1:821: IGNORE_CHAR
                {
                mIGNORE_CHAR(); 


                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\12\uffff\1\64\1\66\1\71\3\uffff\1\100\7\uffff\1\102\5\uffff\22"+
        "\61\1\144\20\uffff\1\103\2\uffff\10\61\1\156\12\61\1\172\1\61\1"+
        "\174\1\61\1\177\7\61\3\uffff\1\u0087\3\61\3\u0087\1\u008b\1\uffff"+
        "\13\61\1\uffff\1\61\1\uffff\1\u0098\1\u009a\1\uffff\1\u009b\1\61"+
        "\1\u009d\1\u009e\1\u00a0\2\61\1\uffff\3\61\1\uffff\3\61\1\u00a9"+
        "\2\61\1\u00ac\5\61\1\uffff\1\61\2\uffff\1\61\2\uffff\1\61\1\uffff"+
        "\1\u00b5\3\61\1\u00b9\1\u0087\2\61\1\uffff\1\61\2\uffff\1\61\1\u00be"+
        "\1\61\1\u00c0\1\u00c1\1\61\1\u00c3\1\61\1\uffff\1\61\1\u00c6\1\u00c7"+
        "\1\uffff\1\61\1\u00c9\2\61\1\uffff\1\u00cc\2\uffff\1\61\1\uffff"+
        "\2\61\2\uffff\1\61\1\uffff\2\61\1\uffff\1\u0098\1\u00d3\2\61\1\u00d6"+
        "\1\u00d7\1\uffff\1\u00d8\1\61\3\uffff\1\u00da\1\uffff";
    static final String DFA8_eofS =
        "\u00db\uffff";
    static final String DFA8_minS =
        "\1\11\11\uffff\1\174\2\75\1\74\2\uffff\1\74\7\uffff\1\0\5\uffff"+
        "\1\103\1\101\1\116\1\117\1\101\1\122\1\110\1\101\1\122\1\105\1\116"+
        "\1\122\1\117\1\101\1\116\1\101\1\122\1\101\1\56\20\uffff\1\47\2"+
        "\uffff\1\115\1\114\1\110\1\117\1\116\1\130\1\107\1\104\1\43\1\116"+
        "\1\105\1\117\1\123\1\124\1\117\1\116\1\105\1\126\1\117\1\43\1\107"+
        "\1\43\1\124\1\43\1\124\1\102\1\104\1\122\1\114\1\105\1\123\3\uffff"+
        "\1\43\2\105\1\122\4\43\1\uffff\2\116\1\101\1\120\1\124\1\105\1\115"+
        "\1\103\1\122\1\111\1\125\1\uffff\1\111\1\uffff\2\43\1\uffff\1\43"+
        "\1\114\3\43\1\122\1\123\1\uffff\1\103\1\115\1\105\1\uffff\1\124"+
        "\1\105\1\124\1\43\1\111\1\47\1\43\1\124\1\105\1\116\1\120\1\116"+
        "\1\uffff\1\107\2\uffff\1\105\2\uffff\1\110\1\uffff\1\43\1\127\1"+
        "\124\1\101\2\43\1\103\1\105\1\uffff\1\116\2\uffff\1\111\1\43\1\107"+
        "\2\43\1\105\1\43\1\101\1\uffff\1\117\2\43\1\uffff\1\124\1\43\1\103"+
        "\1\117\1\uffff\1\43\2\uffff\1\122\1\uffff\2\122\2\uffff\1\111\1"+
        "\uffff\1\124\1\116\1\uffff\2\43\1\104\1\117\2\43\1\uffff\1\43\1"+
        "\116\3\uffff\1\43\1\uffff";
    static final String DFA8_maxS =
        "\1\176\11\uffff\1\174\1\75\2\76\2\uffff\1\76\7\uffff\1\uffff\5\uffff"+
        "\1\165\1\151\1\166\2\162\1\165\1\150\1\141\1\162\1\171\1\156\1\165"+
        "\1\157\1\141\1\156\1\141\1\163\1\141\1\71\20\uffff\1\47\2\uffff"+
        "\1\155\1\154\1\150\1\157\1\156\1\170\1\147\1\144\1\172\1\165\1\145"+
        "\1\157\1\163\1\164\1\157\1\156\1\145\1\166\1\157\1\172\1\147\1\172"+
        "\1\164\1\172\1\164\1\142\1\144\1\162\1\154\1\145\1\163\3\uffff\1"+
        "\172\2\145\1\162\4\172\1\uffff\2\156\1\141\1\160\1\164\1\145\1\155"+
        "\1\143\1\162\1\151\1\165\1\uffff\1\151\1\uffff\2\172\1\uffff\1\172"+
        "\1\154\3\172\1\162\1\163\1\uffff\1\143\1\155\1\145\1\uffff\1\164"+
        "\1\145\1\164\1\172\1\151\1\47\1\172\1\164\1\145\1\156\1\160\1\156"+
        "\1\uffff\1\147\2\uffff\1\145\2\uffff\1\150\1\uffff\1\172\1\167\1"+
        "\164\1\141\2\172\1\143\1\145\1\uffff\1\156\2\uffff\1\151\1\172\1"+
        "\147\2\172\1\145\1\172\1\141\1\uffff\1\157\2\172\1\uffff\1\164\1"+
        "\172\1\143\1\157\1\uffff\1\172\2\uffff\1\162\1\uffff\2\162\2\uffff"+
        "\1\151\1\uffff\1\164\1\156\1\uffff\2\172\1\144\1\157\2\172\1\uffff"+
        "\1\172\1\156\3\uffff\1\172\1\uffff";
    static final String DFA8_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\4\uffff\1\20\1\21"+
        "\1\uffff\1\25\1\26\1\27\1\30\1\34\1\35\1\36\1\uffff\1\41\1\42\1"+
        "\43\1\46\1\47\23\uffff\1\111\1\112\1\12\1\33\1\14\1\13\1\15\1\45"+
        "\1\17\1\16\1\23\1\31\1\22\1\32\1\44\1\24\1\uffff\1\37\1\106\37\uffff"+
        "\1\107\1\110\1\40\10\uffff\1\66\13\uffff\1\60\1\uffff\1\61\2\uffff"+
        "\1\64\7\uffff\1\50\3\uffff\1\63\14\uffff\1\104\1\uffff\1\62\1\65"+
        "\1\uffff\1\75\1\76\1\uffff\1\77\10\uffff\1\52\1\uffff\1\105\1\54"+
        "\10\uffff\1\100\3\uffff\1\102\4\uffff\1\55\1\uffff\1\57\1\74\1\uffff"+
        "\1\72\2\uffff\1\53\1\71\1\uffff\1\51\2\uffff\1\56\6\uffff\1\103"+
        "\2\uffff\1\67\1\73\1\101\1\uffff\1\70";
    static final String DFA8_specialS =
        "\30\uffff\1\0\u00c2\uffff}>";
    static final String[] DFA8_transitionS = {
            "\5\62\22\uffff\1\62\1\15\1\27\1\uffff\1\10\1\23\1\1\1\30\1\21"+
            "\1\32\1\24\1\25\1\6\1\22\1\11\1\7\12\60\1\5\1\33\1\20\1\13\1"+
            "\14\1\26\1\3\1\40\1\47\1\41\1\42\1\54\1\43\1\46\1\45\1\50\3"+
            "\61\1\37\1\52\1\51\1\57\2\61\1\36\1\53\1\56\1\55\1\44\3\61\1"+
            "\17\1\4\1\31\1\16\1\35\1\2\1\40\1\47\1\41\1\42\1\54\1\43\1\46"+
            "\1\45\1\50\3\61\1\37\1\52\1\51\1\57\2\61\1\36\1\53\1\56\1\55"+
            "\1\44\3\61\1\uffff\1\12\1\uffff\1\34",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\63",
            "\1\65",
            "\1\67\1\70",
            "\1\72\1\74\1\73",
            "",
            "",
            "\1\77\1\75\1\76",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\47\103\1\101\uffd8\103",
            "",
            "",
            "",
            "",
            "",
            "\1\106\1\uffff\1\105\16\uffff\1\107\1\104\15\uffff\1\106\1"+
            "\uffff\1\105\16\uffff\1\107\1\104",
            "\1\111\7\uffff\1\110\27\uffff\1\111\7\uffff\1\110",
            "\1\113\4\uffff\1\114\2\uffff\1\112\27\uffff\1\113\4\uffff\1"+
            "\114\2\uffff\1\112",
            "\1\115\2\uffff\1\116\34\uffff\1\115\2\uffff\1\116",
            "\1\121\7\uffff\1\120\10\uffff\1\117\16\uffff\1\121\7\uffff"+
            "\1\120\10\uffff\1\117",
            "\1\122\2\uffff\1\123\34\uffff\1\122\2\uffff\1\123",
            "\1\124\37\uffff\1\124",
            "\1\125\37\uffff\1\125",
            "\1\126\37\uffff\1\126",
            "\1\130\23\uffff\1\127\13\uffff\1\130\23\uffff\1\127",
            "\1\131\37\uffff\1\131",
            "\1\133\2\uffff\1\132\34\uffff\1\133\2\uffff\1\132",
            "\1\134\37\uffff\1\134",
            "\1\135\37\uffff\1\135",
            "\1\136\37\uffff\1\136",
            "\1\137\37\uffff\1\137",
            "\1\140\1\141\36\uffff\1\140\1\141",
            "\1\142\37\uffff\1\142",
            "\1\143\1\uffff\12\60",
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
            "\1\145",
            "",
            "",
            "\1\146\37\uffff\1\146",
            "\1\147\37\uffff\1\147",
            "\1\150\37\uffff\1\150",
            "\1\151\37\uffff\1\151",
            "\1\152\37\uffff\1\152",
            "\1\153\37\uffff\1\153",
            "\1\154\37\uffff\1\154",
            "\1\155\37\uffff\1\155",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\160\6\uffff\1\157\30\uffff\1\160\6\uffff\1\157",
            "\1\161\37\uffff\1\161",
            "\1\162\37\uffff\1\162",
            "\1\163\37\uffff\1\163",
            "\1\164\37\uffff\1\164",
            "\1\165\37\uffff\1\165",
            "\1\166\37\uffff\1\166",
            "\1\167\37\uffff\1\167",
            "\1\170\37\uffff\1\170",
            "\1\171\37\uffff\1\171",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\173\37\uffff\1\173",
            "\2\61\13\uffff\12\61\7\uffff\23\61\1\175\6\61\4\uffff\1\61"+
            "\1\uffff\23\61\1\175\6\61",
            "\1\176\37\uffff\1\176",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u0080\37\uffff\1\u0080",
            "\1\u0081\37\uffff\1\u0081",
            "\1\u0082\37\uffff\1\u0082",
            "\1\u0083\37\uffff\1\u0083",
            "\1\u0084\37\uffff\1\u0084",
            "\1\u0085\37\uffff\1\u0085",
            "\1\u0086\37\uffff\1\u0086",
            "",
            "",
            "",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u0088\37\uffff\1\u0088",
            "\1\u0089\37\uffff\1\u0089",
            "\1\u008a\37\uffff\1\u008a",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "",
            "\1\u008c\37\uffff\1\u008c",
            "\1\u008d\37\uffff\1\u008d",
            "\1\u008e\37\uffff\1\u008e",
            "\1\u008f\37\uffff\1\u008f",
            "\1\u0090\37\uffff\1\u0090",
            "\1\u0091\37\uffff\1\u0091",
            "\1\u0092\37\uffff\1\u0092",
            "\1\u0093\37\uffff\1\u0093",
            "\1\u0094\37\uffff\1\u0094",
            "\1\u0095\37\uffff\1\u0095",
            "\1\u0096\37\uffff\1\u0096",
            "",
            "\1\u0097\37\uffff\1\u0097",
            "",
            "\2\61\13\uffff\12\61\7\uffff\4\61\1\u0099\25\61\4\uffff\1\61"+
            "\1\uffff\4\61\1\u0099\25\61",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u009c\37\uffff\1\u009c",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\2\61\13\uffff\12\61\7\uffff\2\61\1\u009f\27\61\4\uffff\1\61"+
            "\1\uffff\2\61\1\u009f\27\61",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\u00a2\37\uffff\1\u00a2",
            "",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\u00a4\37\uffff\1\u00a4",
            "\1\u00a5\37\uffff\1\u00a5",
            "",
            "\1\u00a6\37\uffff\1\u00a6",
            "\1\u00a7\37\uffff\1\u00a7",
            "\1\u00a8\37\uffff\1\u00a8",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00aa\37\uffff\1\u00aa",
            "\1\u00ab",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00ad\37\uffff\1\u00ad",
            "\1\u00ae\37\uffff\1\u00ae",
            "\1\u00af\37\uffff\1\u00af",
            "\1\u00b0\37\uffff\1\u00b0",
            "\1\u00b1\37\uffff\1\u00b1",
            "",
            "\1\u00b2\37\uffff\1\u00b2",
            "",
            "",
            "\1\u00b3\37\uffff\1\u00b3",
            "",
            "",
            "\1\u00b4\37\uffff\1\u00b4",
            "",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00b6\37\uffff\1\u00b6",
            "\1\u00b7\37\uffff\1\u00b7",
            "\1\u00b8\37\uffff\1\u00b8",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00ba\37\uffff\1\u00ba",
            "\1\u00bb\37\uffff\1\u00bb",
            "",
            "\1\u00bc\37\uffff\1\u00bc",
            "",
            "",
            "\1\u00bd\37\uffff\1\u00bd",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00bf\37\uffff\1\u00bf",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00c2\37\uffff\1\u00c2",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00c4\37\uffff\1\u00c4",
            "",
            "\1\u00c5\37\uffff\1\u00c5",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "",
            "\1\u00c8\37\uffff\1\u00c8",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00ca\37\uffff\1\u00ca",
            "\1\u00cb\37\uffff\1\u00cb",
            "",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "",
            "",
            "\1\u00cd\37\uffff\1\u00cd",
            "",
            "\1\u00ce\37\uffff\1\u00ce",
            "\1\u00cf\37\uffff\1\u00cf",
            "",
            "",
            "\1\u00d0\37\uffff\1\u00d0",
            "",
            "\1\u00d1\37\uffff\1\u00d1",
            "\1\u00d2\37\uffff\1\u00d2",
            "",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00d4\37\uffff\1\u00d4",
            "\1\u00d5\37\uffff\1\u00d5",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
            "\1\u00d9\37\uffff\1\u00d9",
            "",
            "",
            "",
            "\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32"+
            "\61",
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
            return "1:1: Tokens : ( AMPERSAND | APOSTROPHE | AT | BACKSLASH | COLON | COMMA | DIV | DOLLAR | DOT | DOUBLE_PIPE | EQUAL1 | EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | HAT | LBRACKET | LESS_EQUAL1 | LESS_EQUAL2 | LESS_THAN | LPAREN | MINUS | MOD | MULT | NOT_EQUAL1 | NOT_EQUAL2 | PIPE | PLUS | QUESTION | QUOTE_DOUBLE | QUOTE_SINGLE | QUOTE_TRIPLE | RBRACKET | RPAREN | SEMI | SHIFT_LEFT | SHIFT_RIGHT | TILDE | UNDERSCORE | FUNCTION_AGGREGATION | KEYWORD_CREATE | KEYWORD_DROP | KEYWORD_SELECT | KEYWORD_FROM | KEYWORD_WHERE | KEYWORD_HAVING | KEYWORD_GROUP | KEYWORD_BY | KEYWORD_IN | KEYWORD_OUT | KEYWORD_AND | KEYWORD_OR | KEYWORD_NOT | KEYWORD_AS | KEYWORD_DISTINCT | KEYWORD_CONNECTION | KEYWORD_SCHEMA | KEYWORD_TABLE | KEYWORD_FUNCTION | KEYWORD_BEGIN | KEYWORD_END | KEYWORD_VAR | KEYWORD_URL | KEYWORD_USER | KEYWORD_PASSWD | KEYWORD_STORE | TYPE_VARCHAR | TYPE_INTEGER | LITERAL_DATE | LITERAL_STRING | LITERAL_DECIMAL | LITERAL_INTEGER | IDENTIFIER | IGNORE_CHAR );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_24 = input.LA(1);

                        s = -1;
                        if ( (LA8_24=='\'') ) {s = 65;}

                        else if ( ((LA8_24 >= '\u0000' && LA8_24 <= '&')||(LA8_24 >= '(' && LA8_24 <= '\uFFFF')) ) {s = 67;}

                        else s = 66;

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