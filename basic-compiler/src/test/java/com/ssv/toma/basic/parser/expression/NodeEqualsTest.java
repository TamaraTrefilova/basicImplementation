package com.ssv.toma.basic.parser.expression;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ssv.toma.basic.parser.InputPosition;
import com.ssv.toma.basic.parser.ImmutableInputPosition;
import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.parser.TokenType;
import com.ssv.toma.basic.parser.expression.ConstantNode;
import com.ssv.toma.basic.parser.expression.OperationNode;

public class NodeEqualsTest {

    @Test
    public void testConstatNode() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(1, 0);
        Token token = new Token(TokenType.NUMBER, 5, inputPos);
        ConstantNode node = new ConstantNode(token);
        ConstantNode node1 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        assertEquals(node, node1);
    }

    @Test
    public void testConstatNode1() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(1, 0);
        Token token = new Token(TokenType.NUMBER, 5, inputPos);
        ConstantNode node = new ConstantNode(token);
        ConstantNode node1 = new ConstantNode(new Token(TokenType.NUMBER, 7, inputPos));
        assertThat(node, is(not(node1)));
    }

    @Test
    public void testConstatNodeEquals() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(1, 0);
        Token token = new Token(TokenType.NUMBER, 5, inputPos);
        ConstantNode node = new ConstantNode(token);
        ConstantNode node1 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        assertEquals(node, node1);
    }

    @Test
    public void testConstatNodeString() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(1, 0);
        Token token = new Token(TokenType.STRING, "mama", inputPos);
        ConstantNode node = new ConstantNode(token);
        ConstantNode node1 = new ConstantNode(new Token(TokenType.STRING, "mama", inputPos));
        assertEquals(node, node1);
    }

    @Test
    public void testConstatNodeTF() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(1, 0);
        Token token = new Token(TokenType.TRUE, inputPos);
        ConstantNode node = new ConstantNode(token);
        ConstantNode node1 = new ConstantNode(new Token(TokenType.TRUE, inputPos));
        assertEquals(node, node1);
    }

    @Test
    public void testConstatNodeVariable() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(1, 0);
        Token token = new Token(TokenType.VARIABLE, "mama1#", inputPos);
        ConstantNode node = new ConstantNode(token);
        ConstantNode node1 = new ConstantNode(new Token(TokenType.VARIABLE, "mama1#", inputPos));
        assertEquals(true, node.equals(node1));
        node1 = new ConstantNode(new Token(TokenType.VARIABLE, "mam1", inputPos));
        assertThat(node, is(not(node1)));
    }

    @Test
    public void testOperationNode1() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node1 = new ConstantNode(new Token(TokenType.NUMBER, 15, inputPos));
        ConstantNode node2 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        node.setLeftNode(node1);
        node.setRightNode(node2);
        OperationNode node0 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node01 = new ConstantNode(new Token(TokenType.NUMBER, 15, inputPos));
        ConstantNode node02 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        node.setLeftNode(node1);
        node.setRightNode(node2);
        node0.setLeftNode(node01);
        node0.setRightNode(node02);
        assertEquals(node, node0);
    }

    @Test
    public void testOperationNode2() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node1 = new ConstantNode(new Token(TokenType.NUMBER, 15, inputPos));
        ConstantNode node2 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        node.setLeftNode(node1);
        node.setRightNode(node2);
        OperationNode node0 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node01 = new ConstantNode(new Token(TokenType.NUMBER, 15, inputPos));
        ConstantNode node02 = new ConstantNode(new Token(TokenType.NUMBER, 0, inputPos));
        node.setLeftNode(node1);
        node.setRightNode(node2);
        node0.setLeftNode(node01);
        node0.setRightNode(node02);
        assertThat(node, is(not(node0)));
    }

    @Test
    public void testOperationNodeNull() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node1 = null;
        ConstantNode node2 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        node.setLeftNode(node1);
        node.setRightNode(node2);
        OperationNode node0 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node01 = new ConstantNode(new Token(TokenType.NUMBER, 15, inputPos));
        ConstantNode node02 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        node.setLeftNode(node1);
        node.setRightNode(node2);
        node0.setLeftNode(node01);
        node0.setRightNode(node02);
        assertThat(node, is(not(node0)));
    }

    @Test
    public void testOperationNodeNulls() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node1 = null;
        ConstantNode node2 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        node.setLeftNode(node1);
        node.setRightNode(node2);
        OperationNode node0 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        ConstantNode node01 = null;
        ConstantNode node02 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        node.setLeftNode(node1);
        node.setRightNode(node2);
        node0.setLeftNode(node01);
        node0.setRightNode(node02);
        assertEquals(node, node0);
    }

    @Test
    public void testOperationNodeTree() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        OperationNode node = new OperationNode(new Token(TokenType.MINUS, inputPos));
        OperationNode node1 = new OperationNode(new Token(TokenType.DIV, inputPos));
        OperationNode node2 = new OperationNode(new Token(TokenType.MUL, inputPos));
        ConstantNode node3 = new ConstantNode(new Token(TokenType.NUMBER, 15, inputPos));
        ConstantNode node4 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        node1.setLeftNode(node3);
        node1.setRightNode(node4);
        node.setLeftNode(node1);
        node.setRightNode(node2);
        OperationNode node0 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        OperationNode node01 = new OperationNode(new Token(TokenType.DIV, inputPos));
        OperationNode node02 = new OperationNode(new Token(TokenType.MUL, inputPos));

        ConstantNode node03 = new ConstantNode(new Token(TokenType.NUMBER, 15, inputPos));
        ConstantNode node04 = new ConstantNode(new Token(TokenType.NUMBER, 5, inputPos));
        node01.setLeftNode(node03);
        node01.setRightNode(node04);
        node0.setLeftNode(node01);
        node0.setRightNode(node02);

        assertEquals(node, node0);
    }

    @Test
    public void testOperationNodeWithKids() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        Token token = new Token(TokenType.PLUS, inputPos);
        OperationNode node = new OperationNode(token);
        OperationNode node1 = new OperationNode(new Token(TokenType.PLUS, inputPos));
        inputPos = new ImmutableInputPosition(1, 0);
        Token forLeft = new Token(TokenType.NUMBER, 5, inputPos);
        inputPos = new ImmutableInputPosition(2, 0);
        Token forRight = new Token(TokenType.NUMBER, 10, inputPos);
        ConstantNode left = new ConstantNode(forLeft);
        ConstantNode right = new ConstantNode(forRight);
        node.setLeftNode(left);
        node.setRightNode(right);
        node1.setLeftNode(left);
        node1.setRightNode(new ConstantNode(new Token(TokenType.NUMBER, 3, inputPos)));
        assertThat(node, is(not(node1)));
    }

    @Test
    public void testOperationNodeWithKids1() throws Exception {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        Token token = new Token(TokenType.PLUS, inputPos);
        OperationNode node = new OperationNode(token);
        OperationNode node1 = new OperationNode(new Token(TokenType.PLUS, inputPos));
        inputPos = new ImmutableInputPosition(1, 0);
        Token forLeft = new Token(TokenType.NUMBER, 5, inputPos);
        inputPos = new ImmutableInputPosition(2, 0);
        Token forRight = new Token(TokenType.NUMBER, 10, inputPos);
        ConstantNode left = new ConstantNode(forLeft);
        ConstantNode right = new ConstantNode(forRight);
        node.setLeftNode(left);
        node.setRightNode(right);
        node1.setLeftNode(left);
        node1.setRightNode(right);
        assertEquals(node, node1);
    }
}