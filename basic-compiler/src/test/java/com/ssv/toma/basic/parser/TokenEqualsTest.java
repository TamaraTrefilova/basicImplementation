package com.ssv.toma.basic.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TokenEqualsTest {
    @Test
    public void checkToken() {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        Token t1 = new Token(TokenType.AND, inputPos);
        Token t2 = new Token(TokenType.NUMBER, 12, inputPos);
        assertThat(t2, is(not(t1)));
    }

    @Test
    public void checkToken2() {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        Token t1 = new Token(TokenType.STRING, "mama", inputPos);
        Token t2 = new Token(TokenType.STRING, "mama", inputPos);
        assertEquals(t1, t2);
    }

    @Test
    public void checkToken3() {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        Token t1 = new Token(TokenType.EQUAL, inputPos);
        Token t2 = new Token(TokenType.EQUAL, inputPos);
        assertEquals(t2, t1);
    }

    @Test
    public void checkToken4() {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        Token t1 = new Token(TokenType.EQUAL, inputPos);
        Token t2 = new Token(TokenType.AND, inputPos);
        assertThat(t2, is(not(t1)));
    }

    @Test
    public void checkToken5() {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        Token t1 = new Token(TokenType.EQUAL, inputPos);
        Token t2 = new Token(TokenType.NUMBER, 7, inputPos);
        assertThat(t2, is(not(t1)));
    }

    @Test
    public void testEqualsToken() {
        InputPosition inputPos = new ImmutableInputPosition(1, 0);
        Token token = new Token(TokenType.NUMBER, 5, inputPos);
        Token token1 = new Token(TokenType.NUMBER, 5, inputPos);
        assertEquals(token, token1);
        inputPos = new ImmutableInputPosition(0, 0);
        token = new Token(TokenType.FALSE, inputPos);
        token1 = new Token(TokenType.FALSE, inputPos);
        assertEquals(token, token1);
        token = new Token(TokenType.AND, inputPos);
        token1 = new Token(TokenType.AND, inputPos);
        assertEquals(token, token1);
        token = new Token(TokenType.AND, inputPos);
        token1 = new Token(TokenType.LBR, inputPos);
        assertThat(token, is(not(token1)));
        token1 = new Token(TokenType.LBR, inputPos);
        token = new Token(TokenType.LBR, inputPos);
        assertEquals(token, token1);
        token1 = new Token(TokenType.STRING, "abc", inputPos);
        token = new Token(TokenType.STRING, "abc", inputPos);
        assertEquals(token, token1);
        token1 = new Token(TokenType.STRING, "abc", inputPos);
        token = new Token(TokenType.STRING, "ab", inputPos);
        assertThat(token, is(not(token1)));
        token1 = new Token(TokenType.VARIABLE, "abc1#", inputPos);
        token = new Token(TokenType.VARIABLE, "abc1#", inputPos);
        assertEquals(token, token1);
        token1 = new Token(TokenType.VARIABLE, "abc1#", inputPos);
        token = new Token(TokenType.VARIABLE, "ab1#", inputPos);
        assertThat(token, is(not(token1)));

    }

}
