package com.ssv.toma.basic.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.ssv.toma.basic.parser.expression.ConstantNode;
import com.ssv.toma.basic.parser.expression.ExpressionParser;
import com.ssv.toma.basic.parser.expression.Node;
import com.ssv.toma.basic.parser.expression.OperationNode;

public class ParserEqualsTest {
    @Test
    public void parserTest() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("7+5");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node1 = expressionParser.expression(tokenizer);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node2 = new OperationNode(new Token(TokenType.PLUS, inputPos));
        ConstantNode node21 = new ConstantNode(new Token(TokenType.NUMBER, 7, inputPos));
        ConstantNode node22 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        node2.setLeftNode(node21);
        node2.setRightNode(node22);
        tokenizer.close();
        assertEquals(node2, node1);
    }

    @Test
    public void parserTest6() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("true=true");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node0 = expressionParser.expression(tokenizer);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node1 = new OperationNode(new Token(TokenType.EQUAL, inputPos));
        ConstantNode node2 = new ConstantNode(new Token(TokenType.TRUE, inputPos));
        ConstantNode node3 = new ConstantNode(new Token(TokenType.TRUE, inputPos));
        node1.setLeftNode(node2);
        node1.setRightNode(node3);
        tokenizer.close();
        assertEquals(node0, node1);
    }

    @Test
    public void parserTestString() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("\"mama\"+\"papa\"");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node1 = expressionParser.expression(tokenizer);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node2 = new OperationNode(new Token(TokenType.PLUS, inputPos));
        ConstantNode node21 = new ConstantNode(new Token(TokenType.STRING, "mama", inputPos));
        ConstantNode node22 = new ConstantNode(new Token(TokenType.STRING, "papa", inputPos));
        node2.setLeftNode(node21);
        node2.setRightNode(node22);
        tokenizer.close();
        assertEquals(node2, node1);
    }

    @Test
    public void parserTestString04() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("6=12-6");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node0 = expressionParser.expression(tokenizer);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node1 = new OperationNode(new Token(TokenType.EQUAL, inputPos));
        OperationNode node2 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node3 = new ConstantNode(new Token(TokenType.NUMBER, 6, inputPos));
        ConstantNode node4 = new ConstantNode(new Token(TokenType.NUMBER, 6, inputPos));
        ConstantNode node5 = new ConstantNode(new Token(TokenType.NUMBER, 12, inputPos));
        node2.setLeftNode(node4);
        node2.setRightNode(node5);
        node1.setLeftNode(node3);
        node1.setRightNode(node2);
        tokenizer.close();
        assertThat(node0, is(not(node1)));
    }

    @Test
    public void parserTestString1() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("\"mama\"+\"paa\"");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node1 = expressionParser.expression(tokenizer);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node2 = new OperationNode(new Token(TokenType.PLUS, inputPos));
        ConstantNode node21 = new ConstantNode(new Token(TokenType.STRING, "mama", inputPos));
        ConstantNode node22 = new ConstantNode(new Token(TokenType.STRING, "papa", inputPos));
        node2.setLeftNode(node21);
        node2.setRightNode(node22);
        tokenizer.close();
        assertThat(node2, is(not(node1)));
    }

    @Test
    public void parserTestString3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("6+6=12");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node0 = expressionParser.expression(tokenizer);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node1 = new OperationNode(new Token(TokenType.EQUAL, inputPos));
        OperationNode node2 = new OperationNode(new Token(TokenType.PLUS, inputPos));
        ConstantNode node3 = new ConstantNode(new Token(TokenType.NUMBER, 6, inputPos));
        ConstantNode node4 = new ConstantNode(new Token(TokenType.NUMBER, 6, inputPos));
        ConstantNode node5 = new ConstantNode(new Token(TokenType.NUMBER, 12, inputPos));
        node2.setLeftNode(node3);
        node2.setRightNode(node4);
        node1.setLeftNode(node2);
        node1.setRightNode(node5);
        tokenizer.close();
        assertEquals(node0, node1);
    }

    @Test
    public void parserTestString4() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("6=12-6");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node0 = expressionParser.expression(tokenizer);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node1 = new OperationNode(new Token(TokenType.EQUAL, inputPos));
        OperationNode node2 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node3 = new ConstantNode(new Token(TokenType.NUMBER, 6, inputPos));
        ConstantNode node4 = new ConstantNode(new Token(TokenType.NUMBER, 6, inputPos));
        ConstantNode node5 = new ConstantNode(new Token(TokenType.NUMBER, 12, inputPos));
        node2.setLeftNode(node5);
        node2.setRightNode(node4);
        node1.setLeftNode(node3);
        node1.setRightNode(node2);
        tokenizer.close();
        assertEquals(node0, node1);
    }

    @Test
    public void parserTestString5() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("(5+7<5)=(7-4=1)");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node0 = expressionParser.expression(tokenizer);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node1 = new OperationNode(new Token(TokenType.EQUAL, inputPos));
        OperationNode node2 = new OperationNode(new Token(TokenType.LESS, inputPos));
        OperationNode node4 = new OperationNode(new Token(TokenType.PLUS, inputPos));
        OperationNode node7 = new OperationNode(new Token(TokenType.EQUAL, inputPos));
        OperationNode node9 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node3 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        ConstantNode node5 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        ConstantNode node6 = new ConstantNode(new Token(TokenType.NUMBER, 7, inputPos));
        ConstantNode node8 = new ConstantNode(new Token(TokenType.NUMBER, 1, inputPos));
        ConstantNode node10 = new ConstantNode(new Token(TokenType.NUMBER, 7, inputPos));
        ConstantNode node11 = new ConstantNode(new Token(TokenType.NUMBER, 4, inputPos));
        node4.setLeftNode(node5);
        node4.setRightNode(node6);
        node2.setLeftNode(node4);
        node2.setRightNode(node3);
        node9.setLeftNode(node10);
        node9.setRightNode(node11);
        node7.setLeftNode(node9);
        node7.setRightNode(node8);
        node1.setLeftNode(node2);
        node1.setRightNode(node7);
        tokenizer.close();
        assertEquals(node0, node1);
    }

    @Test
    public void parserTestString7() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("(5+7*9-10>100)|(6-3>4)=(5-2>1)");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node0 = expressionParser.expression(tokenizer);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node1 = new OperationNode(new Token(TokenType.OR, inputPos));
        OperationNode node2 = new OperationNode(new Token(TokenType.MORE, inputPos));
        OperationNode node3 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        OperationNode node4 = new OperationNode(new Token(TokenType.PLUS, inputPos));
        OperationNode node5 = new OperationNode(new Token(TokenType.MUL, inputPos));
        OperationNode node11 = new OperationNode(new Token(TokenType.EQUAL, inputPos));
        OperationNode node12 = new OperationNode(new Token(TokenType.MORE, inputPos));
        OperationNode node13 = new OperationNode(new Token(TokenType.MORE, inputPos));
        OperationNode node14 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        OperationNode node15 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node8 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        ConstantNode node6 = new ConstantNode(new Token(TokenType.NUMBER, 7, inputPos));
        ConstantNode node7 = new ConstantNode(new Token(TokenType.NUMBER, 9, inputPos));
        ConstantNode node9 = new ConstantNode(new Token(TokenType.NUMBER, 10, inputPos));
        ConstantNode node10 = new ConstantNode(new Token(TokenType.NUMBER, 100, inputPos));
        ConstantNode node16 = new ConstantNode(new Token(TokenType.NUMBER, 6, inputPos));
        ConstantNode node17 = new ConstantNode(new Token(TokenType.NUMBER, 3, inputPos));
        ConstantNode node18 = new ConstantNode(new Token(TokenType.NUMBER, 4, inputPos));
        ConstantNode node19 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        ConstantNode node20 = new ConstantNode(new Token(TokenType.NUMBER, 2, inputPos));
        ConstantNode node21 = new ConstantNode(new Token(TokenType.NUMBER, 1, inputPos));
        node5.setLeftNode(node6);
        node5.setRightNode(node7);
        node4.setLeftNode(node8);
        node4.setRightNode(node5);
        node3.setLeftNode(node4);
        node3.setRightNode(node9);
        node2.setLeftNode(node3);
        node2.setRightNode(node10);
        node14.setLeftNode(node16);
        node14.setRightNode(node17);
        node13.setLeftNode(node14);
        node13.setRightNode(node18);
        node15.setLeftNode(node19);
        node15.setRightNode(node20);
        node12.setLeftNode(node15);
        node12.setRightNode(node21);
        node11.setLeftNode(node13);
        node11.setRightNode(node12);
        node1.setLeftNode(node2);
        node1.setRightNode(node11);
        tokenizer.close();
        assertEquals(node0, node1);
    }

    @Test
    public void parserTestString8() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString("((5+7*9-10>100)|(6-3>4))=(5-2>1)");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node0 = expressionParser.expression(tokenizer);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node1 = new OperationNode(new Token(TokenType.OR, inputPos));
        OperationNode node2 = new OperationNode(new Token(TokenType.MORE, inputPos));
        OperationNode node3 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        OperationNode node4 = new OperationNode(new Token(TokenType.PLUS, inputPos));
        OperationNode node5 = new OperationNode(new Token(TokenType.MUL, inputPos));
        OperationNode node11 = new OperationNode(new Token(TokenType.EQUAL, inputPos));
        OperationNode node12 = new OperationNode(new Token(TokenType.MORE, inputPos));
        OperationNode node13 = new OperationNode(new Token(TokenType.MORE, inputPos));
        OperationNode node14 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        OperationNode node15 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node8 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        ConstantNode node6 = new ConstantNode(new Token(TokenType.NUMBER, 7, inputPos));
        ConstantNode node7 = new ConstantNode(new Token(TokenType.NUMBER, 9, inputPos));
        ConstantNode node9 = new ConstantNode(new Token(TokenType.NUMBER, 10, inputPos));
        ConstantNode node10 = new ConstantNode(new Token(TokenType.NUMBER, 100, inputPos));
        ConstantNode node16 = new ConstantNode(new Token(TokenType.NUMBER, 6, inputPos));
        ConstantNode node17 = new ConstantNode(new Token(TokenType.NUMBER, 3, inputPos));
        ConstantNode node18 = new ConstantNode(new Token(TokenType.NUMBER, 4, inputPos));
        ConstantNode node19 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        ConstantNode node20 = new ConstantNode(new Token(TokenType.NUMBER, 2, inputPos));
        ConstantNode node21 = new ConstantNode(new Token(TokenType.NUMBER, 1, inputPos));
        node5.setLeftNode(node6);
        node5.setRightNode(node7);
        node4.setLeftNode(node8);
        node4.setRightNode(node5);
        node3.setLeftNode(node4);
        node3.setRightNode(node9);
        node12.setLeftNode(node3);
        node12.setRightNode(node10);
        node14.setLeftNode(node16);
        node14.setRightNode(node17);
        node2.setLeftNode(node14);
        node2.setRightNode(node18);
        node15.setLeftNode(node19);
        node15.setRightNode(node20);
        node13.setLeftNode(node15);
        node13.setRightNode(node21);
        node1.setLeftNode(node12);
        node1.setRightNode(node2);
        node11.setLeftNode(node1);
        node11.setRightNode(node13);
        tokenizer.close();
        assertEquals(node0, node11);
    }

    @Test
    public void throwExceptionWhenInvalidTokenTestIncorrectPriority() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("4+5 * / && 4");
            ExpressionParser expressionParser = new ExpressionParser();
            expressionParser.expression(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid token");
        }
    }

    @Test
    public void throwsExceptionWhenFirstInvalidBraket() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString("(7+5*2-1&3");
            ExpressionParser expressionParser = new ExpressionParser();
            expressionParser.expression(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Braket expected");
        }
    }

}
