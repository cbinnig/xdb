// $ANTLR 3.5 /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g 2013-02-20 09:37:36
 
package org.xdb.funsql.compile.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
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
	public static final int KEYWORD_PARTITIONED=59;
	public static final int KEYWORD_PASSWD=60;
	public static final int KEYWORD_SCHEMA=61;
	public static final int KEYWORD_SELECT=62;
	public static final int KEYWORD_STORE=63;
	public static final int KEYWORD_SUM=64;
	public static final int KEYWORD_TABLE=65;
	public static final int KEYWORD_URL=66;
	public static final int KEYWORD_USER=67;
	public static final int KEYWORD_VAR=68;
	public static final int KEYWORD_WHERE=69;
	public static final int L=70;
	public static final int LBRACKET=71;
	public static final int LESS_EQUAL1=72;
	public static final int LESS_EQUAL2=73;
	public static final int LESS_THAN=74;
	public static final int LITERAL_DECIMAL=75;
	public static final int LITERAL_INTEGER=76;
	public static final int LITERAL_STRING=77;
	public static final int LPAREN=78;
	public static final int M=79;
	public static final int MINUS=80;
	public static final int MOD=81;
	public static final int MULT=82;
	public static final int N=83;
	public static final int NOT_EQUAL1=84;
	public static final int NOT_EQUAL2=85;
	public static final int O=86;
	public static final int P=87;
	public static final int PIPE=88;
	public static final int PLUS=89;
	public static final int Q=90;
	public static final int QUESTION=91;
	public static final int QUOTED_STRING=92;
	public static final int QUOTE_DOUBLE=93;
	public static final int QUOTE_SINGLE=94;
	public static final int QUOTE_TRIPLE=95;
	public static final int R=96;
	public static final int RBRACKET=97;
	public static final int RPAREN=98;
	public static final int S=99;
	public static final int SEMI=100;
	public static final int SHIFT_LEFT=101;
	public static final int SHIFT_RIGHT=102;
	public static final int T=103;
	public static final int TILDE=104;
	public static final int TYPE_DATE=105;
	public static final int TYPE_DECIMAL=106;
	public static final int TYPE_INTEGER=107;
	public static final int TYPE_VARCHAR=108;
	public static final int U=109;
	public static final int UNDERSCORE=110;
	public static final int V=111;
	public static final int W=112;
	public static final int WS=113;
	public static final int X=114;
	public static final int Y=115;
	public static final int Z=116;

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
	@Override public String getGrammarFileName() { return "/Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g"; }

	// $ANTLR start "AMPERSAND"
	public final void mAMPERSAND() throws RecognitionException {
		try {
			int _type = AMPERSAND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:12:11: ( '&' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:12:13: '&'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:13:12: ( '`' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:13:14: '`'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:14:4: ( '@' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:14:6: '@'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:15:11: ( '\\\\' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:15:13: '\\\\'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:16:7: ( ':' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:16:9: ':'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:17:7: ( ',' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:17:9: ','
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:18:5: ( '/' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:18:7: '/'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:19:8: ( '$' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:19:10: '$'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:20:5: ( '.' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:20:7: '.'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:21:13: ( '||' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:21:15: '||'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:22:8: ( '=' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:22:10: '='
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:23:8: ( '==' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:23:10: '=='
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:24:16: ( '>=' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:24:18: '>='
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:25:16: ( '!<' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:25:18: '!<'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:26:14: ( '>' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:26:16: '>'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:27:5: ( '^' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:27:7: '^'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:28:10: ( '[' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:28:12: '['
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:29:13: ( '<=' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:29:15: '<='
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:30:13: ( '!>' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:30:15: '!>'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:31:11: ( '<' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:31:13: '<'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:32:8: ( '(' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:32:10: '('
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:33:7: ( '-' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:33:9: '-'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:34:5: ( '%' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:34:7: '%'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:35:6: ( '*' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:35:8: '*'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:36:12: ( '!=' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:36:14: '!='
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:37:12: ( '<>' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:37:14: '<>'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:38:6: ( '|' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:38:8: '|'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:39:6: ( '+' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:39:8: '+'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:40:10: ( '?' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:40:12: '?'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:41:14: ( '\"' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:41:16: '\"'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:42:14: ( '\\'' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:42:16: '\\''
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:43:14: ( '\\'\\'\\'' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:43:16: '\\'\\'\\''
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:44:10: ( ']' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:44:12: ']'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:45:8: ( ')' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:45:10: ')'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:46:6: ( ';' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:46:8: ';'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:47:12: ( '<<' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:47:14: '<<'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:48:13: ( '>>' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:48:15: '>>'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:49:7: ( '~' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:49:9: '~'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:50:12: ( '_' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:50:14: '_'
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1064:2: ( ( KEYWORD_SUM | KEYWORD_MIN | KEYWORD_MAX | KEYWORD_AVG | KEYWORD_COUNT ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1065:2: ( KEYWORD_SUM | KEYWORD_MIN | KEYWORD_MAX | KEYWORD_AVG | KEYWORD_COUNT )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1065:2: ( KEYWORD_SUM | KEYWORD_MIN | KEYWORD_MAX | KEYWORD_AVG | KEYWORD_COUNT )
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
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
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
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1065:3: KEYWORD_SUM
					{
					mKEYWORD_SUM(); 

					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1065:15: KEYWORD_MIN
					{
					mKEYWORD_MIN(); 

					}
					break;
				case 3 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1065:27: KEYWORD_MAX
					{
					mKEYWORD_MAX(); 

					}
					break;
				case 4 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1065:39: KEYWORD_AVG
					{
					mKEYWORD_AVG(); 

					}
					break;
				case 5 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1065:51: KEYWORD_COUNT
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

	// $ANTLR start "KEYWORD_CALL"
	public final void mKEYWORD_CALL() throws RecognitionException {
		try {
			int _type = KEYWORD_CALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1068:13: ( C A L L )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1068:15: C A L L
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1069:15: ( C R E A T E )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1069:17: C R E A T E
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1070:13: ( D R O P )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1070:15: D R O P
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1071:15: ( S E L E C T )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1071:17: S E L E C T
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1072:13: ( F R O M )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1072:15: F R O M
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1073:14: ( W H E R E )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1073:16: W H E R E
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1074:15: ( H A V I N G )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1074:17: H A V I N G
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1075:14: ( G R O U P )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1075:16: G R O U P
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1076:11: ( B Y )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1076:13: B Y
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1077:11: ( I N )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1077:13: I N
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1078:12: ( O U T )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1078:14: O U T
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1079:12: ( A N D )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1079:14: A N D
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1080:11: ( O R )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1080:13: O R
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1081:12: ( N O T )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1081:14: N O T
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1082:11: ( A S )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1082:13: A S
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1083:21: ( S U M )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1083:23: S U M
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1084:21: ( M I N )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1084:23: M I N
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1085:21: ( M A X )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1085:23: M A X
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1086:21: ( A V G )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1086:23: A V G
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1087:23: ( C O U N T )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1087:25: C O U N T
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1088:17: ( D I S T I N C T )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1088:19: D I S T I N C T
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1090:19: ( C O N N E C T I O N )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1090:21: C O N N E C T I O N
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1091:20: ( P A R T I O N E D )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1091:22: P A R T I O N E D
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

	// $ANTLR start "KEYWORD_SCHEMA"
	public final void mKEYWORD_SCHEMA() throws RecognitionException {
		try {
			int _type = KEYWORD_SCHEMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1092:15: ( S C H E M A )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1092:17: S C H E M A
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1093:14: ( T A B L E )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1093:16: T A B L E
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1094:17: ( F U N C T I O N )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1094:19: F U N C T I O N
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1095:14: ( B E G I N )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1095:16: B E G I N
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1096:12: ( E N D )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1096:14: E N D
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1097:12: ( V A R )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1097:14: V A R
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1099:12: ( U R L )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1099:14: U R L
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1100:13: ( U S E R )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1100:15: U S E R
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1101:15: ( P A S S W O R D )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1101:17: P A S S W O R D
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1102:14: ( S T O R E )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1102:16: S T O R E
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1104:13: ( V A R C H A R )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1104:15: V A R C H A R
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1105:13: ( ( I N T | I N T E G E R ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1105:15: ( I N T | I N T E G E R )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1105:15: ( I N T | I N T E G E R )
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
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 2, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1105:16: I N T
					{
					mI(); 

					mN(); 

					mT(); 

					}
					break;
				case 2 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1105:24: I N T E G E R
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1106:13: ( D E C I M A L )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1106:15: D E C I M A L
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1107:10: ( D A T E )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1107:12: D A T E
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1110:5: ( ( QUOTED_STRING ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1111:5: ( QUOTED_STRING )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1111:5: ( QUOTED_STRING )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1111:6: QUOTED_STRING
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1115:5: ( ( DIGIT )+ DOT ( DIGIT )* )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1116:5: ( DIGIT )+ DOT ( DIGIT )*
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1116:5: ( DIGIT )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			mDOT(); 

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1116:18: ( DIGIT )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			}

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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1120:5: ( ( DIGIT )+ )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1121:5: ( DIGIT )+
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1121:5: ( DIGIT )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
			}

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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1125:5: ( ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1126:5: ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* )
			{
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1126:5: ( CHAR ( CHAR | DIGIT | '_' | '$' | '#' )* )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1126:7: CHAR ( CHAR | DIGIT | '_' | '$' | '#' )*
			{
			mCHAR(); 

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1126:12: ( CHAR | DIGIT | '_' | '$' | '#' )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( ((LA6_0 >= '#' && LA6_0 <= '$')||(LA6_0 >= '0' && LA6_0 <= '9')||(LA6_0 >= 'A' && LA6_0 <= 'Z')||LA6_0=='_'||(LA6_0 >= 'a' && LA6_0 <= 'z')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			}

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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1130:12: ( ( WS | CONTROL_CHAR ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1130:14: ( WS | CONTROL_CHAR )
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1132:12: ( ( ' ' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1132:14: ( ' ' )
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1133:22: ( ( '\\r' | '\\t' | '\\u000B' | '\\f' | '\\n' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1134:23: ( QUOTE_SINGLE (~ QUOTE_SINGLE )* QUOTE_SINGLE )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1134:25: QUOTE_SINGLE (~ QUOTE_SINGLE )* QUOTE_SINGLE
			{
			mQUOTE_SINGLE(); 

			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1134:38: (~ QUOTE_SINGLE )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( ((LA7_0 >= '\u0000' && LA7_0 <= '&')||(LA7_0 >= '(' && LA7_0 <= '\uFFFF')) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			}

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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1135:17: ( '0' .. '9' )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1136:15: ( ( 'A' .. 'Z' | 'a' .. 'z' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1138:11: ( ( 'a' | 'A' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1139:11: ( ( 'b' | 'B' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1140:11: ( ( 'c' | 'C' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1141:11: ( ( 'd' | 'D' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1142:11: ( ( 'e' | 'E' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1143:11: ( ( 'f' | 'F' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1144:11: ( ( 'g' | 'G' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1145:11: ( ( 'h' | 'H' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1146:11: ( ( 'i' | 'I' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1147:11: ( ( 'j' | 'J' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1148:11: ( ( 'k' | 'K' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1149:11: ( ( 'l' | 'L' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1150:11: ( ( 'm' | 'M' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1151:11: ( ( 'n' | 'N' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1152:11: ( ( 'o' | 'O' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1153:11: ( ( 'p' | 'P' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1154:11: ( ( 'q' | 'Q' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1155:11: ( ( 'r' | 'R' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1156:11: ( ( 's' | 'S' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1157:11: ( ( 't' | 'T' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1158:11: ( ( 'u' | 'U' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1159:11: ( ( 'v' | 'V' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1160:11: ( ( 'w' | 'W' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1161:11: ( ( 'x' | 'X' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1162:11: ( ( 'y' | 'Y' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1163:11: ( ( 'z' | 'Z' ) )
			// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:
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

	@Override
	public void mTokens() throws RecognitionException {
		// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:8: ( AMPERSAND | APOSTROPHE | AT | BACKSLASH | COLON | COMMA | DIV | DOLLAR | DOT | DOUBLE_PIPE | EQUAL1 | EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | HAT | LBRACKET | LESS_EQUAL1 | LESS_EQUAL2 | LESS_THAN | LPAREN | MINUS | MOD | MULT | NOT_EQUAL1 | NOT_EQUAL2 | PIPE | PLUS | QUESTION | QUOTE_DOUBLE | QUOTE_SINGLE | QUOTE_TRIPLE | RBRACKET | RPAREN | SEMI | SHIFT_LEFT | SHIFT_RIGHT | TILDE | UNDERSCORE | FUNCTION_AGGREGATION | KEYWORD_CALL | KEYWORD_CREATE | KEYWORD_DROP | KEYWORD_SELECT | KEYWORD_FROM | KEYWORD_WHERE | KEYWORD_HAVING | KEYWORD_GROUP | KEYWORD_BY | KEYWORD_IN | KEYWORD_OUT | KEYWORD_AND | KEYWORD_OR | KEYWORD_NOT | KEYWORD_AS | KEYWORD_DISTINCT | KEYWORD_CONNECTION | KEYWORD_PARTITIONED | KEYWORD_SCHEMA | KEYWORD_TABLE | KEYWORD_FUNCTION | KEYWORD_BEGIN | KEYWORD_END | KEYWORD_VAR | KEYWORD_URL | KEYWORD_USER | KEYWORD_PASSWD | KEYWORD_STORE | TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE | LITERAL_STRING | LITERAL_DECIMAL | LITERAL_INTEGER | IDENTIFIER | IGNORE_CHAR )
		int alt8=77;
		alt8 = dfa8.predict(input);
		switch (alt8) {
			case 1 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:10: AMPERSAND
				{
				mAMPERSAND(); 

				}
				break;
			case 2 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:20: APOSTROPHE
				{
				mAPOSTROPHE(); 

				}
				break;
			case 3 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:31: AT
				{
				mAT(); 

				}
				break;
			case 4 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:34: BACKSLASH
				{
				mBACKSLASH(); 

				}
				break;
			case 5 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:44: COLON
				{
				mCOLON(); 

				}
				break;
			case 6 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:50: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 7 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:56: DIV
				{
				mDIV(); 

				}
				break;
			case 8 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:60: DOLLAR
				{
				mDOLLAR(); 

				}
				break;
			case 9 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:67: DOT
				{
				mDOT(); 

				}
				break;
			case 10 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:71: DOUBLE_PIPE
				{
				mDOUBLE_PIPE(); 

				}
				break;
			case 11 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:83: EQUAL1
				{
				mEQUAL1(); 

				}
				break;
			case 12 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:90: EQUAL2
				{
				mEQUAL2(); 

				}
				break;
			case 13 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:97: GREATER_EQUAL1
				{
				mGREATER_EQUAL1(); 

				}
				break;
			case 14 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:112: GREATER_EQUAL2
				{
				mGREATER_EQUAL2(); 

				}
				break;
			case 15 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:127: GREATER_THAN
				{
				mGREATER_THAN(); 

				}
				break;
			case 16 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:140: HAT
				{
				mHAT(); 

				}
				break;
			case 17 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:144: LBRACKET
				{
				mLBRACKET(); 

				}
				break;
			case 18 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:153: LESS_EQUAL1
				{
				mLESS_EQUAL1(); 

				}
				break;
			case 19 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:165: LESS_EQUAL2
				{
				mLESS_EQUAL2(); 

				}
				break;
			case 20 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:177: LESS_THAN
				{
				mLESS_THAN(); 

				}
				break;
			case 21 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:187: LPAREN
				{
				mLPAREN(); 

				}
				break;
			case 22 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:194: MINUS
				{
				mMINUS(); 

				}
				break;
			case 23 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:200: MOD
				{
				mMOD(); 

				}
				break;
			case 24 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:204: MULT
				{
				mMULT(); 

				}
				break;
			case 25 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:209: NOT_EQUAL1
				{
				mNOT_EQUAL1(); 

				}
				break;
			case 26 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:220: NOT_EQUAL2
				{
				mNOT_EQUAL2(); 

				}
				break;
			case 27 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:231: PIPE
				{
				mPIPE(); 

				}
				break;
			case 28 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:236: PLUS
				{
				mPLUS(); 

				}
				break;
			case 29 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:241: QUESTION
				{
				mQUESTION(); 

				}
				break;
			case 30 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:250: QUOTE_DOUBLE
				{
				mQUOTE_DOUBLE(); 

				}
				break;
			case 31 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:263: QUOTE_SINGLE
				{
				mQUOTE_SINGLE(); 

				}
				break;
			case 32 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:276: QUOTE_TRIPLE
				{
				mQUOTE_TRIPLE(); 

				}
				break;
			case 33 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:289: RBRACKET
				{
				mRBRACKET(); 

				}
				break;
			case 34 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:298: RPAREN
				{
				mRPAREN(); 

				}
				break;
			case 35 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:305: SEMI
				{
				mSEMI(); 

				}
				break;
			case 36 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:310: SHIFT_LEFT
				{
				mSHIFT_LEFT(); 

				}
				break;
			case 37 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:321: SHIFT_RIGHT
				{
				mSHIFT_RIGHT(); 

				}
				break;
			case 38 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:333: TILDE
				{
				mTILDE(); 

				}
				break;
			case 39 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:339: UNDERSCORE
				{
				mUNDERSCORE(); 

				}
				break;
			case 40 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:350: FUNCTION_AGGREGATION
				{
				mFUNCTION_AGGREGATION(); 

				}
				break;
			case 41 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:371: KEYWORD_CALL
				{
				mKEYWORD_CALL(); 

				}
				break;
			case 42 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:384: KEYWORD_CREATE
				{
				mKEYWORD_CREATE(); 

				}
				break;
			case 43 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:399: KEYWORD_DROP
				{
				mKEYWORD_DROP(); 

				}
				break;
			case 44 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:412: KEYWORD_SELECT
				{
				mKEYWORD_SELECT(); 

				}
				break;
			case 45 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:427: KEYWORD_FROM
				{
				mKEYWORD_FROM(); 

				}
				break;
			case 46 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:440: KEYWORD_WHERE
				{
				mKEYWORD_WHERE(); 

				}
				break;
			case 47 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:454: KEYWORD_HAVING
				{
				mKEYWORD_HAVING(); 

				}
				break;
			case 48 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:469: KEYWORD_GROUP
				{
				mKEYWORD_GROUP(); 

				}
				break;
			case 49 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:483: KEYWORD_BY
				{
				mKEYWORD_BY(); 

				}
				break;
			case 50 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:494: KEYWORD_IN
				{
				mKEYWORD_IN(); 

				}
				break;
			case 51 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:505: KEYWORD_OUT
				{
				mKEYWORD_OUT(); 

				}
				break;
			case 52 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:517: KEYWORD_AND
				{
				mKEYWORD_AND(); 

				}
				break;
			case 53 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:529: KEYWORD_OR
				{
				mKEYWORD_OR(); 

				}
				break;
			case 54 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:540: KEYWORD_NOT
				{
				mKEYWORD_NOT(); 

				}
				break;
			case 55 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:552: KEYWORD_AS
				{
				mKEYWORD_AS(); 

				}
				break;
			case 56 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:563: KEYWORD_DISTINCT
				{
				mKEYWORD_DISTINCT(); 

				}
				break;
			case 57 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:580: KEYWORD_CONNECTION
				{
				mKEYWORD_CONNECTION(); 

				}
				break;
			case 58 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:599: KEYWORD_PARTITIONED
				{
				mKEYWORD_PARTITIONED(); 

				}
				break;
			case 59 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:619: KEYWORD_SCHEMA
				{
				mKEYWORD_SCHEMA(); 

				}
				break;
			case 60 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:634: KEYWORD_TABLE
				{
				mKEYWORD_TABLE(); 

				}
				break;
			case 61 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:648: KEYWORD_FUNCTION
				{
				mKEYWORD_FUNCTION(); 

				}
				break;
			case 62 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:665: KEYWORD_BEGIN
				{
				mKEYWORD_BEGIN(); 

				}
				break;
			case 63 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:679: KEYWORD_END
				{
				mKEYWORD_END(); 

				}
				break;
			case 64 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:691: KEYWORD_VAR
				{
				mKEYWORD_VAR(); 

				}
				break;
			case 65 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:703: KEYWORD_URL
				{
				mKEYWORD_URL(); 

				}
				break;
			case 66 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:715: KEYWORD_USER
				{
				mKEYWORD_USER(); 

				}
				break;
			case 67 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:728: KEYWORD_PASSWD
				{
				mKEYWORD_PASSWD(); 

				}
				break;
			case 68 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:743: KEYWORD_STORE
				{
				mKEYWORD_STORE(); 

				}
				break;
			case 69 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:757: TYPE_VARCHAR
				{
				mTYPE_VARCHAR(); 

				}
				break;
			case 70 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:770: TYPE_INTEGER
				{
				mTYPE_INTEGER(); 

				}
				break;
			case 71 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:783: TYPE_DECIMAL
				{
				mTYPE_DECIMAL(); 

				}
				break;
			case 72 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:796: TYPE_DATE
				{
				mTYPE_DATE(); 

				}
				break;
			case 73 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:806: LITERAL_STRING
				{
				mLITERAL_STRING(); 

				}
				break;
			case 74 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:821: LITERAL_DECIMAL
				{
				mLITERAL_DECIMAL(); 

				}
				break;
			case 75 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:837: LITERAL_INTEGER
				{
				mLITERAL_INTEGER(); 

				}
				break;
			case 76 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:853: IDENTIFIER
				{
				mIDENTIFIER(); 

				}
				break;
			case 77 :
				// /Users/mueller/Documents/workspace/xdb/src/org/xdb/funsql/compile/antlr/FunSQL.g:1:864: IGNORE_CHAR
				{
				mIGNORE_CHAR(); 

				}
				break;

		}
	}


	protected DFA8 dfa8 = new DFA8(this);
	static final String DFA8_eotS =
		"\12\uffff\1\64\1\66\1\71\3\uffff\1\100\7\uffff\1\102\5\uffff\22\61\1\146"+
		"\20\uffff\1\103\2\uffff\10\61\1\160\14\61\1\176\1\61\1\u0080\1\61\1\u0083"+
		"\7\61\3\uffff\1\u008c\3\61\3\u008c\1\u0090\1\uffff\15\61\1\uffff\1\61"+
		"\1\uffff\1\u009f\1\u00a1\1\uffff\1\u00a2\3\61\1\u00a6\1\u00a7\1\u00a9"+
		"\1\61\1\uffff\3\61\1\uffff\2\61\1\u00b0\1\61\1\u00b2\2\61\1\u00b5\1\u00b6"+
		"\5\61\1\uffff\1\61\2\uffff\3\61\2\uffff\1\61\1\uffff\1\u00c1\2\61\1\u00c4"+
		"\1\u008c\1\61\1\uffff\1\61\1\uffff\2\61\2\uffff\1\61\1\u00ca\1\61\1\u00cc"+
		"\1\u00cd\3\61\1\u00d1\1\61\1\uffff\1\u00d3\1\u00d4\1\uffff\1\61\1\u00d6"+
		"\3\61\1\uffff\1\u00da\2\uffff\3\61\1\uffff\1\61\2\uffff\1\61\1\uffff\1"+
		"\61\1\u00e1\1\61\1\uffff\1\u009f\2\61\1\u00e5\1\61\1\u00e7\1\uffff\1\u00e8"+
		"\1\61\1\u00ea\1\uffff\1\61\2\uffff\1\u00ec\1\uffff\1\u00ed\2\uffff";
	static final String DFA8_eofS =
		"\u00ee\uffff";
	static final String DFA8_minS =
		"\1\11\11\uffff\1\174\2\75\1\74\2\uffff\1\74\7\uffff\1\0\5\uffff\1\103"+
		"\1\101\1\116\2\101\1\122\1\110\1\101\1\122\1\105\1\116\1\122\1\117\2\101"+
		"\1\116\1\101\1\122\1\56\20\uffff\1\47\2\uffff\1\115\1\114\1\110\1\117"+
		"\1\116\1\130\1\107\1\104\1\43\1\116\1\114\1\105\1\117\1\123\1\103\1\124"+
		"\1\117\1\116\1\105\1\126\1\117\1\43\1\107\1\43\1\124\1\43\1\124\1\122"+
		"\1\102\1\104\1\122\1\114\1\105\3\uffff\1\43\2\105\1\122\4\43\1\uffff\2"+
		"\116\1\114\1\101\1\120\1\124\1\111\1\105\1\115\1\103\1\122\1\111\1\125"+
		"\1\uffff\1\111\1\uffff\2\43\1\uffff\1\43\1\124\1\123\1\114\3\43\1\122"+
		"\1\uffff\1\103\1\115\1\105\1\uffff\1\124\1\105\1\43\1\124\1\43\1\111\1"+
		"\115\2\43\1\124\1\105\1\116\1\120\1\116\1\uffff\1\107\2\uffff\1\111\1"+
		"\127\1\105\2\uffff\1\110\1\uffff\1\43\1\124\1\101\2\43\1\103\1\uffff\1"+
		"\105\1\uffff\1\116\1\101\2\uffff\1\111\1\43\1\107\2\43\1\105\2\117\1\43"+
		"\1\101\1\uffff\2\43\1\uffff\1\124\1\43\1\103\1\114\1\117\1\uffff\1\43"+
		"\2\uffff\1\122\1\116\1\122\1\uffff\1\122\2\uffff\1\111\1\uffff\1\124\1"+
		"\43\1\116\1\uffff\1\43\1\105\1\104\1\43\1\117\1\43\1\uffff\1\43\1\104"+
		"\1\43\1\uffff\1\116\2\uffff\1\43\1\uffff\1\43\2\uffff";
	static final String DFA8_maxS =
		"\1\176\11\uffff\1\174\1\75\2\76\2\uffff\1\76\7\uffff\1\uffff\5\uffff\1"+
		"\165\1\151\1\166\2\162\1\165\1\150\1\141\1\162\1\171\1\156\1\165\1\157"+
		"\2\141\1\156\1\141\1\163\1\71\20\uffff\1\47\2\uffff\1\155\1\154\1\150"+
		"\1\157\1\156\1\170\1\147\1\144\1\172\1\165\1\154\1\145\1\157\1\163\1\143"+
		"\1\164\1\157\1\156\1\145\1\166\1\157\1\172\1\147\1\172\1\164\1\172\1\164"+
		"\1\163\1\142\1\144\1\162\1\154\1\145\3\uffff\1\172\2\145\1\162\4\172\1"+
		"\uffff\2\156\1\154\1\141\1\160\1\164\1\151\1\145\1\155\1\143\1\162\1\151"+
		"\1\165\1\uffff\1\151\1\uffff\2\172\1\uffff\1\172\1\164\1\163\1\154\3\172"+
		"\1\162\1\uffff\1\143\1\155\1\145\1\uffff\1\164\1\145\1\172\1\164\1\172"+
		"\1\151\1\155\2\172\1\164\1\145\1\156\1\160\1\156\1\uffff\1\147\2\uffff"+
		"\1\151\1\167\1\145\2\uffff\1\150\1\uffff\1\172\1\164\1\141\2\172\1\143"+
		"\1\uffff\1\145\1\uffff\1\156\1\141\2\uffff\1\151\1\172\1\147\2\172\1\145"+
		"\2\157\1\172\1\141\1\uffff\2\172\1\uffff\1\164\1\172\1\143\1\154\1\157"+
		"\1\uffff\1\172\2\uffff\1\162\1\156\1\162\1\uffff\1\162\2\uffff\1\151\1"+
		"\uffff\1\164\1\172\1\156\1\uffff\1\172\1\145\1\144\1\172\1\157\1\172\1"+
		"\uffff\1\172\1\144\1\172\1\uffff\1\156\2\uffff\1\172\1\uffff\1\172\2\uffff";
	static final String DFA8_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\4\uffff\1\20\1\21\1\uffff"+
		"\1\25\1\26\1\27\1\30\1\34\1\35\1\36\1\uffff\1\41\1\42\1\43\1\46\1\47\23"+
		"\uffff\1\114\1\115\1\12\1\33\1\14\1\13\1\15\1\45\1\17\1\16\1\23\1\31\1"+
		"\22\1\32\1\44\1\24\1\uffff\1\37\1\111\41\uffff\1\112\1\113\1\40\10\uffff"+
		"\1\67\15\uffff\1\61\1\uffff\1\62\2\uffff\1\65\10\uffff\1\50\3\uffff\1"+
		"\64\16\uffff\1\106\1\uffff\1\63\1\66\3\uffff\1\77\1\100\1\uffff\1\101"+
		"\6\uffff\1\51\1\uffff\1\53\2\uffff\1\110\1\55\12\uffff\1\102\2\uffff\1"+
		"\104\5\uffff\1\56\1\uffff\1\60\1\76\3\uffff\1\74\1\uffff\1\54\1\73\1\uffff"+
		"\1\52\3\uffff\1\57\6\uffff\1\107\3\uffff\1\105\1\uffff\1\70\1\75\1\uffff"+
		"\1\103\1\uffff\1\72\1\71";
	static final String DFA8_specialS =
		"\30\uffff\1\0\u00d5\uffff}>";
	static final String[] DFA8_transitionS = {
			"\5\62\22\uffff\1\62\1\15\1\27\1\uffff\1\10\1\23\1\1\1\30\1\21\1\32\1"+
			"\24\1\25\1\6\1\22\1\11\1\7\12\60\1\5\1\33\1\20\1\13\1\14\1\26\1\3\1\40"+
			"\1\47\1\41\1\42\1\55\1\43\1\46\1\45\1\50\3\61\1\37\1\52\1\51\1\53\2\61"+
			"\1\36\1\54\1\57\1\56\1\44\3\61\1\17\1\4\1\31\1\16\1\35\1\2\1\40\1\47"+
			"\1\41\1\42\1\55\1\43\1\46\1\45\1\50\3\61\1\37\1\52\1\51\1\53\2\61\1\36"+
			"\1\54\1\57\1\56\1\44\3\61\1\uffff\1\12\1\uffff\1\34",
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
			"\1\106\1\uffff\1\105\16\uffff\1\107\1\104\15\uffff\1\106\1\uffff\1\105"+
			"\16\uffff\1\107\1\104",
			"\1\111\7\uffff\1\110\27\uffff\1\111\7\uffff\1\110",
			"\1\113\4\uffff\1\114\2\uffff\1\112\27\uffff\1\113\4\uffff\1\114\2\uffff"+
			"\1\112",
			"\1\116\15\uffff\1\115\2\uffff\1\117\16\uffff\1\116\15\uffff\1\115\2"+
			"\uffff\1\117",
			"\1\123\3\uffff\1\122\3\uffff\1\121\10\uffff\1\120\16\uffff\1\123\3\uffff"+
			"\1\122\3\uffff\1\121\10\uffff\1\120",
			"\1\124\2\uffff\1\125\34\uffff\1\124\2\uffff\1\125",
			"\1\126\37\uffff\1\126",
			"\1\127\37\uffff\1\127",
			"\1\130\37\uffff\1\130",
			"\1\132\23\uffff\1\131\13\uffff\1\132\23\uffff\1\131",
			"\1\133\37\uffff\1\133",
			"\1\135\2\uffff\1\134\34\uffff\1\135\2\uffff\1\134",
			"\1\136\37\uffff\1\136",
			"\1\137\37\uffff\1\137",
			"\1\140\37\uffff\1\140",
			"\1\141\37\uffff\1\141",
			"\1\142\37\uffff\1\142",
			"\1\143\1\144\36\uffff\1\143\1\144",
			"\1\145\1\uffff\12\60",
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
			"\1\147",
			"",
			"",
			"\1\150\37\uffff\1\150",
			"\1\151\37\uffff\1\151",
			"\1\152\37\uffff\1\152",
			"\1\153\37\uffff\1\153",
			"\1\154\37\uffff\1\154",
			"\1\155\37\uffff\1\155",
			"\1\156\37\uffff\1\156",
			"\1\157\37\uffff\1\157",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\162\6\uffff\1\161\30\uffff\1\162\6\uffff\1\161",
			"\1\163\37\uffff\1\163",
			"\1\164\37\uffff\1\164",
			"\1\165\37\uffff\1\165",
			"\1\166\37\uffff\1\166",
			"\1\167\37\uffff\1\167",
			"\1\170\37\uffff\1\170",
			"\1\171\37\uffff\1\171",
			"\1\172\37\uffff\1\172",
			"\1\173\37\uffff\1\173",
			"\1\174\37\uffff\1\174",
			"\1\175\37\uffff\1\175",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\177\37\uffff\1\177",
			"\2\61\13\uffff\12\61\7\uffff\23\61\1\u0081\6\61\4\uffff\1\61\1\uffff"+
			"\23\61\1\u0081\6\61",
			"\1\u0082\37\uffff\1\u0082",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u0084\37\uffff\1\u0084",
			"\1\u0085\1\u0086\36\uffff\1\u0085\1\u0086",
			"\1\u0087\37\uffff\1\u0087",
			"\1\u0088\37\uffff\1\u0088",
			"\1\u0089\37\uffff\1\u0089",
			"\1\u008a\37\uffff\1\u008a",
			"\1\u008b\37\uffff\1\u008b",
			"",
			"",
			"",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u008d\37\uffff\1\u008d",
			"\1\u008e\37\uffff\1\u008e",
			"\1\u008f\37\uffff\1\u008f",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"\1\u0091\37\uffff\1\u0091",
			"\1\u0092\37\uffff\1\u0092",
			"\1\u0093\37\uffff\1\u0093",
			"\1\u0094\37\uffff\1\u0094",
			"\1\u0095\37\uffff\1\u0095",
			"\1\u0096\37\uffff\1\u0096",
			"\1\u0097\37\uffff\1\u0097",
			"\1\u0098\37\uffff\1\u0098",
			"\1\u0099\37\uffff\1\u0099",
			"\1\u009a\37\uffff\1\u009a",
			"\1\u009b\37\uffff\1\u009b",
			"\1\u009c\37\uffff\1\u009c",
			"\1\u009d\37\uffff\1\u009d",
			"",
			"\1\u009e\37\uffff\1\u009e",
			"",
			"\2\61\13\uffff\12\61\7\uffff\4\61\1\u00a0\25\61\4\uffff\1\61\1\uffff"+
			"\4\61\1\u00a0\25\61",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00a3\37\uffff\1\u00a3",
			"\1\u00a4\37\uffff\1\u00a4",
			"\1\u00a5\37\uffff\1\u00a5",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\2\61\13\uffff\12\61\7\uffff\2\61\1\u00a8\27\61\4\uffff\1\61\1\uffff"+
			"\2\61\1\u00a8\27\61",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00aa\37\uffff\1\u00aa",
			"",
			"\1\u00ab\37\uffff\1\u00ab",
			"\1\u00ac\37\uffff\1\u00ac",
			"\1\u00ad\37\uffff\1\u00ad",
			"",
			"\1\u00ae\37\uffff\1\u00ae",
			"\1\u00af\37\uffff\1\u00af",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00b1\37\uffff\1\u00b1",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00b3\37\uffff\1\u00b3",
			"\1\u00b4\37\uffff\1\u00b4",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00b7\37\uffff\1\u00b7",
			"\1\u00b8\37\uffff\1\u00b8",
			"\1\u00b9\37\uffff\1\u00b9",
			"\1\u00ba\37\uffff\1\u00ba",
			"\1\u00bb\37\uffff\1\u00bb",
			"",
			"\1\u00bc\37\uffff\1\u00bc",
			"",
			"",
			"\1\u00bd\37\uffff\1\u00bd",
			"\1\u00be\37\uffff\1\u00be",
			"\1\u00bf\37\uffff\1\u00bf",
			"",
			"",
			"\1\u00c0\37\uffff\1\u00c0",
			"",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00c2\37\uffff\1\u00c2",
			"\1\u00c3\37\uffff\1\u00c3",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00c5\37\uffff\1\u00c5",
			"",
			"\1\u00c6\37\uffff\1\u00c6",
			"",
			"\1\u00c7\37\uffff\1\u00c7",
			"\1\u00c8\37\uffff\1\u00c8",
			"",
			"",
			"\1\u00c9\37\uffff\1\u00c9",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00cb\37\uffff\1\u00cb",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00ce\37\uffff\1\u00ce",
			"\1\u00cf\37\uffff\1\u00cf",
			"\1\u00d0\37\uffff\1\u00d0",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00d2\37\uffff\1\u00d2",
			"",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"\1\u00d5\37\uffff\1\u00d5",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00d7\37\uffff\1\u00d7",
			"\1\u00d8\37\uffff\1\u00d8",
			"\1\u00d9\37\uffff\1\u00d9",
			"",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"",
			"\1\u00db\37\uffff\1\u00db",
			"\1\u00dc\37\uffff\1\u00dc",
			"\1\u00dd\37\uffff\1\u00dd",
			"",
			"\1\u00de\37\uffff\1\u00de",
			"",
			"",
			"\1\u00df\37\uffff\1\u00df",
			"",
			"\1\u00e0\37\uffff\1\u00e0",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00e2\37\uffff\1\u00e2",
			"",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00e3\37\uffff\1\u00e3",
			"\1\u00e4\37\uffff\1\u00e4",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00e6\37\uffff\1\u00e6",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"\1\u00e9\37\uffff\1\u00e9",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"\1\u00eb\37\uffff\1\u00eb",
			"",
			"",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
			"",
			"\2\61\13\uffff\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61",
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

	protected class DFA8 extends DFA {

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
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( AMPERSAND | APOSTROPHE | AT | BACKSLASH | COLON | COMMA | DIV | DOLLAR | DOT | DOUBLE_PIPE | EQUAL1 | EQUAL2 | GREATER_EQUAL1 | GREATER_EQUAL2 | GREATER_THAN | HAT | LBRACKET | LESS_EQUAL1 | LESS_EQUAL2 | LESS_THAN | LPAREN | MINUS | MOD | MULT | NOT_EQUAL1 | NOT_EQUAL2 | PIPE | PLUS | QUESTION | QUOTE_DOUBLE | QUOTE_SINGLE | QUOTE_TRIPLE | RBRACKET | RPAREN | SEMI | SHIFT_LEFT | SHIFT_RIGHT | TILDE | UNDERSCORE | FUNCTION_AGGREGATION | KEYWORD_CALL | KEYWORD_CREATE | KEYWORD_DROP | KEYWORD_SELECT | KEYWORD_FROM | KEYWORD_WHERE | KEYWORD_HAVING | KEYWORD_GROUP | KEYWORD_BY | KEYWORD_IN | KEYWORD_OUT | KEYWORD_AND | KEYWORD_OR | KEYWORD_NOT | KEYWORD_AS | KEYWORD_DISTINCT | KEYWORD_CONNECTION | KEYWORD_PARTITIONED | KEYWORD_SCHEMA | KEYWORD_TABLE | KEYWORD_FUNCTION | KEYWORD_BEGIN | KEYWORD_END | KEYWORD_VAR | KEYWORD_URL | KEYWORD_USER | KEYWORD_PASSWD | KEYWORD_STORE | TYPE_VARCHAR | TYPE_INTEGER | TYPE_DECIMAL | TYPE_DATE | LITERAL_STRING | LITERAL_DECIMAL | LITERAL_INTEGER | IDENTIFIER | IGNORE_CHAR );";
		}
		@Override
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
