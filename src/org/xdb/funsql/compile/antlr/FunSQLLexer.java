// $ANTLR 3.4 /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g 2012-08-23 22:17:05
 
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
    public String getGrammarFileName() { return "/Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g"; }

    // $ANTLR start "AMPERSAND"
    public final void mAMPERSAND() throws RecognitionException {
        try {
            int _type = AMPERSAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:6:11: ( '&' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:6:13: '&'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:7:12: ( '`' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:7:14: '`'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:8:4: ( '@' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:8:6: '@'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:9:11: ( '\\\\' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:9:13: '\\\\'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:10:7: ( ':' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:10:9: ':'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:11:7: ( ',' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:11:9: ','
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:12:5: ( '/' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:12:7: '/'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:13:8: ( '$' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:13:10: '$'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:14:5: ( '.' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:14:7: '.'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:15:13: ( '||' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:15:15: '||'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:16:8: ( '=' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:16:10: '='
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:17:8: ( '==' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:17:10: '=='
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

    // $ANTLR start "GREATER"
    public final void mGREATER() throws RecognitionException {
        try {
            int _type = GREATER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:18:9: ( '>' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:18:11: '>'
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
    // $ANTLR end "GREATER"

    // $ANTLR start "GREATER_OR_EQ1"
    public final void mGREATER_OR_EQ1() throws RecognitionException {
        try {
            int _type = GREATER_OR_EQ1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:19:16: ( '>=' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:19:18: '>='
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
    // $ANTLR end "GREATER_OR_EQ1"

    // $ANTLR start "GREATER_OR_EQ2"
    public final void mGREATER_OR_EQ2() throws RecognitionException {
        try {
            int _type = GREATER_OR_EQ2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:20:16: ( '!<' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:20:18: '!<'
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
    // $ANTLR end "GREATER_OR_EQ2"

    // $ANTLR start "HAT"
    public final void mHAT() throws RecognitionException {
        try {
            int _type = HAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:21:5: ( '^' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:21:7: '^'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:22:10: ( '[' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:22:12: '['
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

    // $ANTLR start "LESS"
    public final void mLESS() throws RecognitionException {
        try {
            int _type = LESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:23:6: ( '<' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:23:8: '<'
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
    // $ANTLR end "LESS"

    // $ANTLR start "LESS_OR_EQ1"
    public final void mLESS_OR_EQ1() throws RecognitionException {
        try {
            int _type = LESS_OR_EQ1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:24:13: ( '<=' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:24:15: '<='
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
    // $ANTLR end "LESS_OR_EQ1"

    // $ANTLR start "LESS_OR_EQ2"
    public final void mLESS_OR_EQ2() throws RecognitionException {
        try {
            int _type = LESS_OR_EQ2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:25:13: ( '!>' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:25:15: '!>'
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
    // $ANTLR end "LESS_OR_EQ2"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:26:8: ( '(' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:26:10: '('
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:27:7: ( '-' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:27:9: '-'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:28:5: ( '%' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:28:7: '%'
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

    // $ANTLR start "NOT_EQUAL1"
    public final void mNOT_EQUAL1() throws RecognitionException {
        try {
            int _type = NOT_EQUAL1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:29:12: ( '!=' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:29:14: '!='
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:30:12: ( '<>' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:30:14: '<>'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:31:6: ( '|' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:31:8: '|'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:32:6: ( '+' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:32:8: '+'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:33:10: ( '?' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:33:12: '?'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:34:14: ( '\"' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:34:16: '\"'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:35:14: ( '\\'' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:35:16: '\\''
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:36:14: ( '\\'\\'\\'' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:36:16: '\\'\\'\\''
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:37:10: ( ']' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:37:12: ']'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:38:8: ( ')' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:38:10: ')'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:39:6: ( ';' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:39:8: ';'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:40:12: ( '<<' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:40:14: '<<'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:41:13: ( '>>' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:41:15: '>>'
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

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:42:6: ( '*' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:42:8: '*'
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
    // $ANTLR end "STAR"

    // $ANTLR start "TILDE"
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:43:7: ( '~' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:43:9: '~'
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:44:12: ( '_' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:44:14: '_'
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

    // $ANTLR start "KEYWORD_CREATE"
    public final void mKEYWORD_CREATE() throws RecognitionException {
        try {
            int _type = KEYWORD_CREATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:481:15: ( C R E A T E )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:481:17: C R E A T E
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:482:13: ( D R O P )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:482:15: D R O P
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:483:15: ( S E L E C T )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:483:17: S E L E C T
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:484:13: ( F R O M )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:484:15: F R O M
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:485:14: ( W H E R E )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:485:16: W H E R E
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

    // $ANTLR start "KEYWORD_IN"
    public final void mKEYWORD_IN() throws RecognitionException {
        try {
            int _type = KEYWORD_IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:486:11: ( I N )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:486:13: I N
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

    // $ANTLR start "KEYWORD_CONNECTION"
    public final void mKEYWORD_CONNECTION() throws RecognitionException {
        try {
            int _type = KEYWORD_CONNECTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:488:19: ( C O N N E C T I O N )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:488:21: C O N N E C T I O N
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:489:15: ( S C H E M A )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:489:17: S C H E M A
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:490:14: ( T A B L E )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:490:16: T A B L E
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

    // $ANTLR start "KEYWORD_URL"
    public final void mKEYWORD_URL() throws RecognitionException {
        try {
            int _type = KEYWORD_URL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:492:12: ( U R L )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:492:14: U R L
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:493:13: ( U S E R )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:493:15: U S E R
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:494:15: ( P A S S W O R D )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:494:17: P A S S W O R D
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:495:14: ( S T O R E )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:495:16: S T O R E
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:497:13: ( V A R C H A R )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:497:15: V A R C H A R
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:498:13: ( ( I N T | I N T E G E R ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:498:15: ( I N T | I N T E G E R )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:498:15: ( I N T | I N T E G E R )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='I'||LA1_0=='i') ) {
                int LA1_1 = input.LA(2);

                if ( (LA1_1=='N'||LA1_1=='n') ) {
                    int LA1_2 = input.LA(3);

                    if ( (LA1_2=='T'||LA1_2=='t') ) {
                        int LA1_3 = input.LA(4);

                        if ( (LA1_3=='E'||LA1_3=='e') ) {
                            alt1=2;
                        }
                        else {
                            alt1=1;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 1, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:498:16: I N T
                    {
                    mI(); 


                    mN(); 


                    mT(); 


                    }
                    break;
                case 2 :
                    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:498:24: I N T E G E R
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

    // $ANTLR start "LITERAL_STRING"
    public final void mLITERAL_STRING() throws RecognitionException {
        try {
            int _type = LITERAL_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:501:5: ( ( QUOTED_STRING ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:502:5: ( QUOTED_STRING )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:502:5: ( QUOTED_STRING )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:502:6: QUOTED_STRING
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

    // $ANTLR start "LITERAL_INTEGER"
    public final void mLITERAL_INTEGER() throws RecognitionException {
        try {
            int _type = LITERAL_INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:506:5: ( ( DIGIT )+ )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:507:5: ( DIGIT )+
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:507:5: ( DIGIT )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:511:5: ( ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:512:5: ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* )
            {
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:512:5: ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:512:7: CHAR ( CHAR | DIGIT | '_' | '$' | '#' )*
            {
            mCHAR(); 


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:512:12: ( CHAR | DIGIT | '_' | '$' | '#' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '#' && LA3_0 <= '$')||(LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            	    break loop3;
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:516:12: ( ( WS | CONTROL_CHAR ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:516:14: ( WS | CONTROL_CHAR )
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:519:12: ( ( ' ' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:519:14: ( ' ' )
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:520:22: ( ( '\\r' | '\\t' | '\\u000B' | '\\f' | '\\n' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:521:23: ( QUOTE_SINGLE (~ QUOTE_SINGLE )* QUOTE_SINGLE )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:521:25: QUOTE_SINGLE (~ QUOTE_SINGLE )* QUOTE_SINGLE
            {
            mQUOTE_SINGLE(); 


            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:521:38: (~ QUOTE_SINGLE )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '\u0000' && LA4_0 <= '&')||(LA4_0 >= '(' && LA4_0 <= '\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            	    break loop4;
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:522:17: ( '0' .. '9' )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:523:15: ( ( 'A' .. 'Z' | 'a' .. 'z' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:525:11: ( ( 'a' | 'A' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:526:11: ( ( 'b' | 'B' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:527:11: ( ( 'c' | 'C' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:528:11: ( ( 'd' | 'D' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:529:11: ( ( 'e' | 'E' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:530:11: ( ( 'f' | 'F' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:531:11: ( ( 'g' | 'G' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:532:11: ( ( 'h' | 'H' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:533:11: ( ( 'i' | 'I' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:534:11: ( ( 'j' | 'J' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:535:11: ( ( 'k' | 'K' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:536:11: ( ( 'l' | 'L' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:537:11: ( ( 'm' | 'M' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:538:11: ( ( 'n' | 'N' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:539:11: ( ( 'o' | 'O' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:540:11: ( ( 'p' | 'P' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:541:11: ( ( 'q' | 'Q' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:542:11: ( ( 'r' | 'R' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:543:11: ( ( 's' | 'S' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:544:11: ( ( 't' | 'T' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:545:11: ( ( 'u' | 'U' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:546:11: ( ( 'v' | 'V' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:547:11: ( ( 'w' | 'W' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:548:11: ( ( 'x' | 'X' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:549:11: ( ( 'y' | 'Y' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:550:11: ( ( 'z' | 'Z' ) )
            // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
        // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:8: ( AMPERSAND | APOSTROPHE | AT | BACKSLASH | COLON | COMMA | DIV | DOLLAR | DOT | DOUBLE_PIPE | EQUAL1 | EQUAL2 | GREATER | GREATER_OR_EQ1 | GREATER_OR_EQ2 | HAT | LBRACKET | LESS | LESS_OR_EQ1 | LESS_OR_EQ2 | LPAREN | MINUS | MOD | NOT_EQUAL1 | NOT_EQUAL2 | PIPE | PLUS | QUESTION | QUOTE_DOUBLE | QUOTE_SINGLE | QUOTE_TRIPLE | RBRACKET | RPAREN | SEMI | SHIFT_LEFT | SHIFT_RIGHT | STAR | TILDE | UNDERSCORE | KEYWORD_CREATE | KEYWORD_DROP | KEYWORD_SELECT | KEYWORD_FROM | KEYWORD_WHERE | KEYWORD_IN | KEYWORD_CONNECTION | KEYWORD_SCHEMA | KEYWORD_TABLE | KEYWORD_URL | KEYWORD_USER | KEYWORD_PASSWD | KEYWORD_STORE | TYPE_VARCHAR | TYPE_INTEGER | LITERAL_STRING | LITERAL_INTEGER | IDENTIFIER | IGNORE_CHAR )
        int alt5=58;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:10: AMPERSAND
                {
                mAMPERSAND(); 


                }
                break;
            case 2 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:20: APOSTROPHE
                {
                mAPOSTROPHE(); 


                }
                break;
            case 3 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:31: AT
                {
                mAT(); 


                }
                break;
            case 4 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:34: BACKSLASH
                {
                mBACKSLASH(); 


                }
                break;
            case 5 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:44: COLON
                {
                mCOLON(); 


                }
                break;
            case 6 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:50: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 7 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:56: DIV
                {
                mDIV(); 


                }
                break;
            case 8 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:60: DOLLAR
                {
                mDOLLAR(); 


                }
                break;
            case 9 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:67: DOT
                {
                mDOT(); 


                }
                break;
            case 10 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:71: DOUBLE_PIPE
                {
                mDOUBLE_PIPE(); 


                }
                break;
            case 11 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:83: EQUAL1
                {
                mEQUAL1(); 


                }
                break;
            case 12 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:90: EQUAL2
                {
                mEQUAL2(); 


                }
                break;
            case 13 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:97: GREATER
                {
                mGREATER(); 


                }
                break;
            case 14 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:105: GREATER_OR_EQ1
                {
                mGREATER_OR_EQ1(); 


                }
                break;
            case 15 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:120: GREATER_OR_EQ2
                {
                mGREATER_OR_EQ2(); 


                }
                break;
            case 16 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:135: HAT
                {
                mHAT(); 


                }
                break;
            case 17 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:139: LBRACKET
                {
                mLBRACKET(); 


                }
                break;
            case 18 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:148: LESS
                {
                mLESS(); 


                }
                break;
            case 19 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:153: LESS_OR_EQ1
                {
                mLESS_OR_EQ1(); 


                }
                break;
            case 20 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:165: LESS_OR_EQ2
                {
                mLESS_OR_EQ2(); 


                }
                break;
            case 21 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:177: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 22 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:184: MINUS
                {
                mMINUS(); 


                }
                break;
            case 23 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:190: MOD
                {
                mMOD(); 


                }
                break;
            case 24 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:194: NOT_EQUAL1
                {
                mNOT_EQUAL1(); 


                }
                break;
            case 25 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:205: NOT_EQUAL2
                {
                mNOT_EQUAL2(); 


                }
                break;
            case 26 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:216: PIPE
                {
                mPIPE(); 


                }
                break;
            case 27 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:221: PLUS
                {
                mPLUS(); 


                }
                break;
            case 28 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:226: QUESTION
                {
                mQUESTION(); 


                }
                break;
            case 29 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:235: QUOTE_DOUBLE
                {
                mQUOTE_DOUBLE(); 


                }
                break;
            case 30 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:248: QUOTE_SINGLE
                {
                mQUOTE_SINGLE(); 


                }
                break;
            case 31 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:261: QUOTE_TRIPLE
                {
                mQUOTE_TRIPLE(); 


                }
                break;
            case 32 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:274: RBRACKET
                {
                mRBRACKET(); 


                }
                break;
            case 33 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:283: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 34 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:290: SEMI
                {
                mSEMI(); 


                }
                break;
            case 35 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:295: SHIFT_LEFT
                {
                mSHIFT_LEFT(); 


                }
                break;
            case 36 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:306: SHIFT_RIGHT
                {
                mSHIFT_RIGHT(); 


                }
                break;
            case 37 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:318: STAR
                {
                mSTAR(); 


                }
                break;
            case 38 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:323: TILDE
                {
                mTILDE(); 


                }
                break;
            case 39 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:329: UNDERSCORE
                {
                mUNDERSCORE(); 


                }
                break;
            case 40 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:340: KEYWORD_CREATE
                {
                mKEYWORD_CREATE(); 


                }
                break;
            case 41 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:355: KEYWORD_DROP
                {
                mKEYWORD_DROP(); 


                }
                break;
            case 42 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:368: KEYWORD_SELECT
                {
                mKEYWORD_SELECT(); 


                }
                break;
            case 43 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:383: KEYWORD_FROM
                {
                mKEYWORD_FROM(); 


                }
                break;
            case 44 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:396: KEYWORD_WHERE
                {
                mKEYWORD_WHERE(); 


                }
                break;
            case 45 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:410: KEYWORD_IN
                {
                mKEYWORD_IN(); 


                }
                break;
            case 46 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:421: KEYWORD_CONNECTION
                {
                mKEYWORD_CONNECTION(); 


                }
                break;
            case 47 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:440: KEYWORD_SCHEMA
                {
                mKEYWORD_SCHEMA(); 


                }
                break;
            case 48 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:455: KEYWORD_TABLE
                {
                mKEYWORD_TABLE(); 


                }
                break;
            case 49 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:469: KEYWORD_URL
                {
                mKEYWORD_URL(); 


                }
                break;
            case 50 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:481: KEYWORD_USER
                {
                mKEYWORD_USER(); 


                }
                break;
            case 51 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:494: KEYWORD_PASSWD
                {
                mKEYWORD_PASSWD(); 


                }
                break;
            case 52 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:509: KEYWORD_STORE
                {
                mKEYWORD_STORE(); 


                }
                break;
            case 53 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:523: TYPE_VARCHAR
                {
                mTYPE_VARCHAR(); 


                }
                break;
            case 54 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:536: TYPE_INTEGER
                {
                mTYPE_INTEGER(); 


                }
                break;
            case 55 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:549: LITERAL_STRING
                {
                mLITERAL_STRING(); 


                }
                break;
            case 56 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:564: LITERAL_INTEGER
                {
                mLITERAL_INTEGER(); 


                }
                break;
            case 57 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:580: IDENTIFIER
                {
                mIDENTIFIER(); 


                }
                break;
            case 58 :
                // /Users/cbinnig/Workspace/XDB/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:591: IGNORE_CHAR
                {
                mIGNORE_CHAR(); 


                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\12\uffff\1\54\1\56\1\61\3\uffff\1\70\6\uffff\1\72\6\uffff\12\51"+
        "\21\uffff\1\73\2\uffff\10\51\1\123\5\51\1\uffff\10\51\1\uffff\1"+
        "\142\1\51\1\145\5\51\1\153\3\51\1\157\1\51\1\uffff\2\51\1\uffff"+
        "\1\163\4\51\1\uffff\2\51\1\172\1\uffff\1\173\1\51\1\175\1\uffff"+
        "\2\51\1\u0080\1\51\1\u0082\1\u0083\2\uffff\1\51\1\uffff\2\51\1\uffff"+
        "\1\51\2\uffff\1\142\1\51\1\u0089\1\51\1\u008b\1\uffff\1\51\1\uffff"+
        "\1\u008d\1\uffff";
    static final String DFA5_eofS =
        "\u008e\uffff";
    static final String DFA5_minS =
        "\1\11\11\uffff\1\174\2\75\1\74\2\uffff\1\74\6\uffff\1\0\6\uffff"+
        "\1\117\1\122\1\103\1\122\1\110\1\116\1\101\1\122\2\101\21\uffff"+
        "\1\47\2\uffff\1\105\1\116\1\117\1\114\1\110\2\117\1\105\1\43\1\102"+
        "\1\114\1\105\1\123\1\122\1\uffff\1\101\1\116\1\120\2\105\1\122\1"+
        "\115\1\122\1\uffff\1\43\1\114\1\43\1\122\1\123\1\103\1\124\1\105"+
        "\1\43\1\103\1\115\1\105\1\43\1\105\1\uffff\1\107\1\105\1\uffff\1"+
        "\43\1\127\1\110\1\105\1\103\1\uffff\1\124\1\101\1\43\1\uffff\1\43"+
        "\1\105\1\43\1\uffff\1\117\1\101\1\43\1\124\2\43\2\uffff\1\122\1"+
        "\uffff\2\122\1\uffff\1\111\2\uffff\1\43\1\104\1\43\1\117\1\43\1"+
        "\uffff\1\116\1\uffff\1\43\1\uffff";
    static final String DFA5_maxS =
        "\1\176\11\uffff\1\174\1\75\2\76\2\uffff\1\76\6\uffff\1\uffff\6\uffff"+
        "\2\162\1\164\1\162\1\150\1\156\1\141\1\163\2\141\21\uffff\1\47\2"+
        "\uffff\1\145\1\156\1\157\1\154\1\150\2\157\1\145\1\172\1\142\1\154"+
        "\1\145\1\163\1\162\1\uffff\1\141\1\156\1\160\2\145\1\162\1\155\1"+
        "\162\1\uffff\1\172\1\154\1\172\1\162\1\163\1\143\1\164\1\145\1\172"+
        "\1\143\1\155\1\145\1\172\1\145\1\uffff\1\147\1\145\1\uffff\1\172"+
        "\1\167\1\150\1\145\1\143\1\uffff\1\164\1\141\1\172\1\uffff\1\172"+
        "\1\145\1\172\1\uffff\1\157\1\141\1\172\1\164\2\172\2\uffff\1\162"+
        "\1\uffff\2\162\1\uffff\1\151\2\uffff\1\172\1\144\1\172\1\157\1\172"+
        "\1\uffff\1\156\1\uffff\1\172\1\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\4\uffff\1\20\1\21"+
        "\1\uffff\1\25\1\26\1\27\1\33\1\34\1\35\1\uffff\1\40\1\41\1\42\1"+
        "\45\1\46\1\47\12\uffff\1\70\1\71\1\72\1\12\1\32\1\14\1\13\1\16\1"+
        "\44\1\15\1\17\1\24\1\30\1\23\1\31\1\43\1\22\1\uffff\1\36\1\67\16"+
        "\uffff\1\37\10\uffff\1\55\16\uffff\1\66\2\uffff\1\61\5\uffff\1\51"+
        "\3\uffff\1\53\3\uffff\1\62\6\uffff\1\64\1\54\1\uffff\1\60\2\uffff"+
        "\1\50\1\uffff\1\52\1\57\5\uffff\1\65\1\uffff\1\63\1\uffff\1\56";
    static final String DFA5_specialS =
        "\27\uffff\1\0\166\uffff}>";
    static final String[] DFA5_transitionS = {
            "\5\52\22\uffff\1\52\1\15\1\26\1\uffff\1\10\1\23\1\1\1\27\1\21"+
            "\1\31\1\33\1\24\1\6\1\22\1\11\1\7\12\50\1\5\1\32\1\20\1\13\1"+
            "\14\1\25\1\3\2\51\1\36\1\37\1\51\1\41\2\51\1\43\6\51\1\46\2"+
            "\51\1\40\1\44\1\45\1\47\1\42\3\51\1\17\1\4\1\30\1\16\1\35\1"+
            "\2\2\51\1\36\1\37\1\51\1\41\2\51\1\43\6\51\1\46\2\51\1\40\1"+
            "\44\1\45\1\47\1\42\3\51\1\uffff\1\12\1\uffff\1\34",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\53",
            "\1\55",
            "\1\57\1\60",
            "\1\62\1\64\1\63",
            "",
            "",
            "\1\67\1\65\1\66",
            "",
            "",
            "",
            "",
            "",
            "",
            "\47\73\1\71\uffd8\73",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\75\2\uffff\1\74\34\uffff\1\75\2\uffff\1\74",
            "\1\76\37\uffff\1\76",
            "\1\100\1\uffff\1\77\16\uffff\1\101\16\uffff\1\100\1\uffff\1"+
            "\77\16\uffff\1\101",
            "\1\102\37\uffff\1\102",
            "\1\103\37\uffff\1\103",
            "\1\104\37\uffff\1\104",
            "\1\105\37\uffff\1\105",
            "\1\106\1\107\36\uffff\1\106\1\107",
            "\1\110\37\uffff\1\110",
            "\1\111\37\uffff\1\111",
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
            "",
            "\1\112",
            "",
            "",
            "\1\113\37\uffff\1\113",
            "\1\114\37\uffff\1\114",
            "\1\115\37\uffff\1\115",
            "\1\116\37\uffff\1\116",
            "\1\117\37\uffff\1\117",
            "\1\120\37\uffff\1\120",
            "\1\121\37\uffff\1\121",
            "\1\122\37\uffff\1\122",
            "\2\51\13\uffff\12\51\7\uffff\23\51\1\124\6\51\4\uffff\1\51"+
            "\1\uffff\23\51\1\124\6\51",
            "\1\125\37\uffff\1\125",
            "\1\126\37\uffff\1\126",
            "\1\127\37\uffff\1\127",
            "\1\130\37\uffff\1\130",
            "\1\131\37\uffff\1\131",
            "",
            "\1\132\37\uffff\1\132",
            "\1\133\37\uffff\1\133",
            "\1\134\37\uffff\1\134",
            "\1\135\37\uffff\1\135",
            "\1\136\37\uffff\1\136",
            "\1\137\37\uffff\1\137",
            "\1\140\37\uffff\1\140",
            "\1\141\37\uffff\1\141",
            "",
            "\2\51\13\uffff\12\51\7\uffff\4\51\1\143\25\51\4\uffff\1\51"+
            "\1\uffff\4\51\1\143\25\51",
            "\1\144\37\uffff\1\144",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\146\37\uffff\1\146",
            "\1\147\37\uffff\1\147",
            "\1\150\37\uffff\1\150",
            "\1\151\37\uffff\1\151",
            "\1\152\37\uffff\1\152",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\154\37\uffff\1\154",
            "\1\155\37\uffff\1\155",
            "\1\156\37\uffff\1\156",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\160\37\uffff\1\160",
            "",
            "\1\161\37\uffff\1\161",
            "\1\162\37\uffff\1\162",
            "",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\164\37\uffff\1\164",
            "\1\165\37\uffff\1\165",
            "\1\166\37\uffff\1\166",
            "\1\167\37\uffff\1\167",
            "",
            "\1\170\37\uffff\1\170",
            "\1\171\37\uffff\1\171",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\174\37\uffff\1\174",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\176\37\uffff\1\176",
            "\1\177\37\uffff\1\177",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u0081\37\uffff\1\u0081",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "",
            "\1\u0084\37\uffff\1\u0084",
            "",
            "\1\u0085\37\uffff\1\u0085",
            "\1\u0086\37\uffff\1\u0086",
            "",
            "\1\u0087\37\uffff\1\u0087",
            "",
            "",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u0088\37\uffff\1\u0088",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u008a\37\uffff\1\u008a",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\u008c\37\uffff\1\u008c",
            "",
            "\2\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( AMPERSAND | APOSTROPHE | AT | BACKSLASH | COLON | COMMA | DIV | DOLLAR | DOT | DOUBLE_PIPE | EQUAL1 | EQUAL2 | GREATER | GREATER_OR_EQ1 | GREATER_OR_EQ2 | HAT | LBRACKET | LESS | LESS_OR_EQ1 | LESS_OR_EQ2 | LPAREN | MINUS | MOD | NOT_EQUAL1 | NOT_EQUAL2 | PIPE | PLUS | QUESTION | QUOTE_DOUBLE | QUOTE_SINGLE | QUOTE_TRIPLE | RBRACKET | RPAREN | SEMI | SHIFT_LEFT | SHIFT_RIGHT | STAR | TILDE | UNDERSCORE | KEYWORD_CREATE | KEYWORD_DROP | KEYWORD_SELECT | KEYWORD_FROM | KEYWORD_WHERE | KEYWORD_IN | KEYWORD_CONNECTION | KEYWORD_SCHEMA | KEYWORD_TABLE | KEYWORD_URL | KEYWORD_USER | KEYWORD_PASSWD | KEYWORD_STORE | TYPE_VARCHAR | TYPE_INTEGER | LITERAL_STRING | LITERAL_INTEGER | IDENTIFIER | IGNORE_CHAR );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA5_23 = input.LA(1);

                        s = -1;
                        if ( (LA5_23=='\'') ) {s = 57;}

                        else if ( ((LA5_23 >= '\u0000' && LA5_23 <= '&')||(LA5_23 >= '(' && LA5_23 <= '\uFFFF')) ) {s = 59;}

                        else s = 58;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 5, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}