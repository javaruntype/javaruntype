// $ANTLR 3.1.2 org/javaruntype/type/parser/Type.g 2010-02-02 00:25:32

/*
 * =============================================================================
 * 
 *   Copyright (c) 2009, The JAVARUNTYPE team (http://www.javaruntype.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.javaruntype.type.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TypeLexer extends Lexer {
    public static final int WS=14;
    public static final int UNKNOWN=5;
    public static final int COMMA=8;
    public static final int EXT=11;
    public static final int ENDTYPEPARAM=7;
    public static final int SUPER=10;
    public static final int EOF=-1;
    public static final int EXTENDS=9;
    public static final int ARRAY=4;
    public static final int BEGINTYPEPARAM=6;
    public static final int SUP=12;
    public static final int CLASSNAME=13;

    // delegates
    // delegators

    public TypeLexer() {;} 
    public TypeLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TypeLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "org/javaruntype/type/parser/Type.g"; }

    // $ANTLR start "ARRAY"
    public final void mARRAY() throws RecognitionException {
        try {
            int _type = ARRAY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:30:7: ( '[]' )
            // org/javaruntype/type/parser/Type.g:30:9: '[]'
            {
            match("[]"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ARRAY"

    // $ANTLR start "UNKNOWN"
    public final void mUNKNOWN() throws RecognitionException {
        try {
            int _type = UNKNOWN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:31:9: ( '?' )
            // org/javaruntype/type/parser/Type.g:31:11: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNKNOWN"

    // $ANTLR start "BEGINTYPEPARAM"
    public final void mBEGINTYPEPARAM() throws RecognitionException {
        try {
            int _type = BEGINTYPEPARAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:32:16: ( '<' )
            // org/javaruntype/type/parser/Type.g:32:18: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BEGINTYPEPARAM"

    // $ANTLR start "ENDTYPEPARAM"
    public final void mENDTYPEPARAM() throws RecognitionException {
        try {
            int _type = ENDTYPEPARAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:33:14: ( '>' )
            // org/javaruntype/type/parser/Type.g:33:16: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENDTYPEPARAM"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:34:7: ( ',' )
            // org/javaruntype/type/parser/Type.g:34:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "EXTENDS"
    public final void mEXTENDS() throws RecognitionException {
        try {
            int _type = EXTENDS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:35:9: ( ' extends ' )
            // org/javaruntype/type/parser/Type.g:35:11: ' extends '
            {
            match(" extends "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXTENDS"

    // $ANTLR start "SUPER"
    public final void mSUPER() throws RecognitionException {
        try {
            int _type = SUPER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:36:7: ( ' super ' )
            // org/javaruntype/type/parser/Type.g:36:9: ' super '
            {
            match(" super "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SUPER"

    // $ANTLR start "EXT"
    public final void mEXT() throws RecognitionException {
        try {
            int _type = EXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:37:5: ( 'EXT' )
            // org/javaruntype/type/parser/Type.g:37:7: 'EXT'
            {
            match("EXT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXT"

    // $ANTLR start "SUP"
    public final void mSUP() throws RecognitionException {
        try {
            int _type = SUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:38:5: ( 'SUP' )
            // org/javaruntype/type/parser/Type.g:38:7: 'SUP'
            {
            match("SUP"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SUP"

    // $ANTLR start "CLASSNAME"
    public final void mCLASSNAME() throws RecognitionException {
        try {
            int _type = CLASSNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:120:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '.' | '$' )+ )
            // org/javaruntype/type/parser/Type.g:120:7: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '.' | '$' )+
            {
            // org/javaruntype/type/parser/Type.g:120:7: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '-' | '_' | '.' | '$' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='$'||(LA1_0>='-' && LA1_0<='.')||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // org/javaruntype/type/parser/Type.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLASSNAME"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // org/javaruntype/type/parser/Type.g:124:5: ( ( ' ' )+ )
            // org/javaruntype/type/parser/Type.g:124:7: ( ' ' )+
            {
            // org/javaruntype/type/parser/Type.g:124:7: ( ' ' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // org/javaruntype/type/parser/Type.g:124:8: ' '
            	    {
            	    match(' '); 

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

            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // org/javaruntype/type/parser/Type.g:1:8: ( ARRAY | UNKNOWN | BEGINTYPEPARAM | ENDTYPEPARAM | COMMA | EXTENDS | SUPER | EXT | SUP | CLASSNAME | WS )
        int alt3=11;
        alt3 = dfa3.predict(input);
        switch (alt3) {
            case 1 :
                // org/javaruntype/type/parser/Type.g:1:10: ARRAY
                {
                mARRAY(); 

                }
                break;
            case 2 :
                // org/javaruntype/type/parser/Type.g:1:16: UNKNOWN
                {
                mUNKNOWN(); 

                }
                break;
            case 3 :
                // org/javaruntype/type/parser/Type.g:1:24: BEGINTYPEPARAM
                {
                mBEGINTYPEPARAM(); 

                }
                break;
            case 4 :
                // org/javaruntype/type/parser/Type.g:1:39: ENDTYPEPARAM
                {
                mENDTYPEPARAM(); 

                }
                break;
            case 5 :
                // org/javaruntype/type/parser/Type.g:1:52: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 6 :
                // org/javaruntype/type/parser/Type.g:1:58: EXTENDS
                {
                mEXTENDS(); 

                }
                break;
            case 7 :
                // org/javaruntype/type/parser/Type.g:1:66: SUPER
                {
                mSUPER(); 

                }
                break;
            case 8 :
                // org/javaruntype/type/parser/Type.g:1:72: EXT
                {
                mEXT(); 

                }
                break;
            case 9 :
                // org/javaruntype/type/parser/Type.g:1:76: SUP
                {
                mSUP(); 

                }
                break;
            case 10 :
                // org/javaruntype/type/parser/Type.g:1:80: CLASSNAME
                {
                mCLASSNAME(); 

                }
                break;
            case 11 :
                // org/javaruntype/type/parser/Type.g:1:90: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\6\uffff\1\14\2\11\4\uffff\2\11\1\21\1\22\2\uffff";
    static final String DFA3_eofS =
        "\23\uffff";
    static final String DFA3_minS =
        "\1\40\5\uffff\1\145\1\130\1\125\4\uffff\1\124\1\120\2\44\2\uffff";
    static final String DFA3_maxS =
        "\1\172\5\uffff\1\163\1\130\1\125\4\uffff\1\124\1\120\2\172\2\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\3\uffff\1\12\1\6\1\7\1\13\4\uffff"+
        "\1\10\1\11";
    static final String DFA3_specialS =
        "\23\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\6\3\uffff\1\11\7\uffff\1\5\2\11\1\uffff\12\11\2\uffff\1"+
            "\3\1\uffff\1\4\1\2\1\uffff\4\11\1\7\15\11\1\10\7\11\1\1\3\uffff"+
            "\1\11\1\uffff\32\11",
            "",
            "",
            "",
            "",
            "",
            "\1\12\15\uffff\1\13",
            "\1\15",
            "\1\16",
            "",
            "",
            "",
            "",
            "\1\17",
            "\1\20",
            "\1\11\10\uffff\2\11\1\uffff\12\11\7\uffff\32\11\4\uffff\1"+
            "\11\1\uffff\32\11",
            "\1\11\10\uffff\2\11\1\uffff\12\11\7\uffff\32\11\4\uffff\1"+
            "\11\1\uffff\32\11",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( ARRAY | UNKNOWN | BEGINTYPEPARAM | ENDTYPEPARAM | COMMA | EXTENDS | SUPER | EXT | SUP | CLASSNAME | WS );";
        }
    }
 

}