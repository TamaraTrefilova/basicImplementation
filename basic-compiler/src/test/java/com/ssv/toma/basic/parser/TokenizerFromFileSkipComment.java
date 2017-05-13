package com.ssv.toma.basic.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TokenizerFromFileSkipComment {
    @Test
    public void skipLineCommentTest1() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/TestForSkipLine.txt");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
    }

    @Test
    public void skipLineCommentTest2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/TestForSkipLine2.txt");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.NUMBER, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
    }

    @Test
    public void skipLineCommentTest3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/TestForSkipLine3.txt");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.NUMBER, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
    }

    @Test
    public void skipLineCommentTest4() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("aaa //*mama///////////*/");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
        assertEquals(TokenType.EOF, tokenizer.token().getTokenType());
        // assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        // tokenizer.next();
        // assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        // tokenizer.next();
    }

    @Test
    public void skipLineCommentTest5() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("aaa //*mama///////////*/\n + abc");
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.PLUS, tokenizer.token().getTokenType());
        tokenizer.next();
        assertEquals(TokenType.VARIABLE, tokenizer.token().getTokenType());
        tokenizer.next();
        tokenizer.close();
    }
}
