package com.ssv.toma.basic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TokenizerTest {

    @Test
    public void skipCommentTest1() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("aaa /*mama*/ + abc");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
    }

    @Test
    public void skipCommentTest2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("aaa *mama*/ + abc");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.MUL, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.MUL, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.DIV, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
    }

    @Test
    public void skipCommentTest3() throws Exception { /**/
        Tokenizer tokenizer = Tokenizer.createFromString("aaa /**/ + abc");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
    }

    @Test
    public void skipCommentTest4() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("aaa /*mama////******/ + abc");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
    }

    @Test
    public void skipCommentTest5() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("aaa /*mama///////////*/ + abc");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
    }

    @Test
    public void skipCommentTest6() throws Exception { /**/
        Tokenizer tokenizer = Tokenizer.createFromString("aaa /***/ + abc");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
    }

    @Test
    public void testEOF() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("");
        assertEquals(TokenType.EOF, tokenizer.token().getTokenType());
        tokenizer.close();
    }

    @Test
    public void testMultyEOF() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("abc     ");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.EOF, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.EOF, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.EOF, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.EOF, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.EOF, tokenizer.token().getTokenType());
        tokenizer.close();
    }

    @Test
    public void testToken() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("+-1>2=4>=0!=7&|<8()true 1 false\"abc\"2*/unut1$");
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.MINUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.NUMBER, tokenizer.token().getTokenType());
        assertEquals(1, tokenizer.token().getValue());
        tokenizer.next();
        assertEquals(TokenType.MORE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.NUMBER, tokenizer.token().getTokenType());
        assertEquals(2, tokenizer.token().getValue());
        tokenizer.next();
        assertEquals(TokenType.EQUAL, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.NUMBER, tokenizer.token().getTokenType());
        assertEquals(4, tokenizer.token().getValue());
        tokenizer.next();
        assertEquals(TokenType.MOREOREQUAL, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.NUMBER, tokenizer.token().getTokenType());
        assertEquals(0, tokenizer.token().getValue());
        tokenizer.next();
        assertEquals(TokenType.NOTEQUAL, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.NUMBER, tokenizer.token().getTokenType());
        assertEquals(7, tokenizer.token().getValue());
        tokenizer.next();
        assertEquals(TokenType.AND, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.OR, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.LESS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.NUMBER, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.LBR, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.RBR, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.TRUE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.NUMBER, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.FALSE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.STRING, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.NUMBER, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.MUL, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.DIV, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.EOF, tokenizer.token().getTokenType());
        tokenizer.close();
    }

    @Test
    public void throwsExceptionWhenException() throws Exception {
        try {
            Tokenizer.createFromString("\"anc");
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserSequenserException e) {
            assertEquals(e.getReason(), "Qoutes expected");

        }
    }

    @Test
    public void throwsExceptionWhenInvalidSymbol() throws Exception {
        try {
            Tokenizer.createFromString("%anc");
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserSequenserException e) {
            assertEquals(new ParserSequenserException(ParserSequenserException.INVALID_SYMBOL,
                    new ImmutableInputPosition(0, 0), '%'), e);
        }
    }

    @Test
    public void throwsExceptionWhenInvalidSymbolCharacter() throws Exception {
        try {
            Tokenizer.createFromString("12a34");
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserSequenserException e) {
            assertEquals(new ParserSequenserException(ParserSequenserException.INVALID_SYMBOL,
                    new ImmutableInputPosition(0, 2), 'a'), e);
        }
    }

    @Test
    public void throwsExceptionWhenWrongComment() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("aaa /* mama * + abc");
            tokenizer.next();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserSequenserException e) {
            assertEquals(new ParserSequenserException(ParserSequenserException.COMMENT_LINE,
                    new ImmutableInputPosition(0, 19), -1), e);
        }
    }

    @Test
    public void throwsExceptionWhenWrongComment2() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("aaa /*mama/ + abc");
            tokenizer.next();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserSequenserException e) {
            assertEquals(new ParserSequenserException(ParserSequenserException.COMMENT_LINE,
                    new ImmutableInputPosition(0, 17), -1), e);
        }
    }

    @Test
    public void throwsExceptionWhenWrongComment3() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("aaa /*mama****** + abc");
            tokenizer.next();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserSequenserException e) {
            assertEquals(new ParserSequenserException(ParserSequenserException.COMMENT_LINE,
                    new ImmutableInputPosition(0, 22), -1), e);
        }
    }

    @Test
    public void throwsExceptionWhenWrongComment4() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("aaa /*mama/////// + abc");
            tokenizer.next();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserSequenserException e) {
            assertEquals(new ParserSequenserException(ParserSequenserException.COMMENT_LINE,
                    new ImmutableInputPosition(0, 23), -1), e);
        }
    }

    @Test
    public void throwsExceptionWhenWrongComment5() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("aaa /*mama + abc");
            tokenizer.next();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserSequenserException e) {
            assertEquals(new ParserSequenserException(ParserSequenserException.COMMENT_LINE,
                    new ImmutableInputPosition(0, 16), -1), e);
        }
    }

    @Test
    public void throwsExceptionWhenWrongComment6() throws Exception { /**/
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("aaa /*/ + abc");
            tokenizer.next();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserSequenserException e) {
            assertEquals(new ParserSequenserException(ParserSequenserException.COMMENT_LINE,
                    new ImmutableInputPosition(0, 13), -1), e);
        }
    }

}
