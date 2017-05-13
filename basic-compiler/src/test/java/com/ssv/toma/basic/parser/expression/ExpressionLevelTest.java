package com.ssv.toma.basic.parser.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.ssv.toma.basic.parser.ParserTokenException;
import com.ssv.toma.basic.parser.Tokenizer;

public class ExpressionLevelTest {

    @Test
    public void throwExceptionWhenInvalidOperationToken() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("4>5>4");
            ExpressionParser expressionParser = new ExpressionParser();
            expressionParser.expression(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid token");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 3);
        }
    }

    @Test
    public void throwExceptionWhenInvalidOperationToken1() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("4>5>=4");
            ExpressionParser expressionParser = new ExpressionParser();
            expressionParser.expression(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid token");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 3);
        }
    }

    @Test
    public void throwExceptionWhenInvalidOperationToken2() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("4>=5<=4");
            ExpressionParser expressionParser = new ExpressionParser();
            expressionParser.expression(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid token");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 4);
        }
    }

    @Test
    public void throwExceptionWhenInvalidOperationToken3() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("4<5<=4");
            ExpressionParser expressionParser = new ExpressionParser();
            expressionParser.expression(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid token");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 3);
        }
    }
}
