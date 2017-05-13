package com.ssv.toma.basic.parser.variablenode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.ssv.toma.basic.parser.ImmutableInputPosition;
import com.ssv.toma.basic.parser.InputPosition;
import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.parser.TokenType;
import com.ssv.toma.basic.parser.Tokenizer;
import com.ssv.toma.basic.parser.expression.ConstantNode;
import com.ssv.toma.basic.parser.expression.ExpressionParser;
import com.ssv.toma.basic.parser.expression.Node;
import com.ssv.toma.basic.parser.expression.OperationNode;
import com.ssv.toma.basic.parser.expression.VariableNode;
import com.ssv.toma.basic.runtime.BasicRuntime;
import com.ssv.toma.basic.runtime.BasicRuntimeException;

public class VariableNodeTest {

    @Test
    public void throwExceptionWhenInvalidVariableToken() throws Exception {

        try {
            BasicRuntime runtime = new BasicRuntime();
            runtime.setValue("abc#", 2);
            runtime.setValue("dc$", 3);
            InputPosition inputPos = new ImmutableInputPosition(0, 0);
            VariableNode node = new VariableNode(new Token(TokenType.VARIABLE, "abc", inputPos));
            ConstantNode node1 = new ConstantNode(new Token(TokenType.NUMBER, 15, inputPos));
            OperationNode node3 = new OperationNode(new Token(TokenType.MUL, inputPos));
            node3.setLeftNode(node);
            node3.setRightNode(node1);
            node3.calculateNode(runtime);
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getReason(), "The variable doesn't have a value");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 0);
        }
    }

    @Test
    public void throwExceptionWhenInvalidVariableToken2() throws Exception {

        try {
            BasicRuntime runtime = new BasicRuntime();
            runtime.setValue("abc#", 2);
            runtime.setValue("dc$", 3);
            InputPosition inputPos = new ImmutableInputPosition(0, 0);
            VariableNode node = new VariableNode(new Token(TokenType.VARIABLE, "abc#", inputPos));
            VariableNode node1 = new VariableNode(new Token(TokenType.VARIABLE, "abc", inputPos));
            OperationNode node3 = new OperationNode(new Token(TokenType.MUL, inputPos));
            node3.setLeftNode(node);
            node3.setRightNode(node1);
            node3.calculateNode(runtime);
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getReason(), "The variable doesn't have a value");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 0);
        }
    }

    @Test
    public void throwExceptionWhenInvalidVariableToken3() throws Exception {

        try {
            BasicRuntime runtime = new BasicRuntime();
            runtime.setValue("abc#", 2);
            runtime.setValue("dc$", 3);
            Node node1 = (Node) Node.createNodeFromString("abc#-abc");
            node1.calculateNode(runtime);
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getReason(), "The variable doesn't have a value");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 5);
        }
    }

    @Test
    public void throwExceptionWhenInvalidVariableToken4() throws Exception {

        try {
            BasicRuntime runtime = new BasicRuntime();
            runtime.setValue("abc#", 2);
            runtime.setValue("dc$", 3);
            Node node1 = (Node) Node.createNodeFromString("abc#-abc");
            node1.calculateNode(runtime);
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getReason(), "The variable doesn't have a value");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 5);
        }
    }
    @Test
    public void variableNodetest1() throws Exception {
        BasicRuntime runtime = new BasicRuntime();
        runtime.setValue("abc#", 2);
        runtime.setValue("dc$", 3);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        Token token = new Token(TokenType.VARIABLE, "abc#", inputPos);
        VariableNode node = new VariableNode(token);
        VariableNode node1 = new VariableNode(new Token(TokenType.VARIABLE, "dc$", inputPos));
        OperationNode node3 = new OperationNode(new Token(TokenType.PLUS, inputPos));
        node3.setLeftNode(node);
        node3.setRightNode(node1);
        assertEquals(5, node3.calculateNode(runtime));

    }

    @Test
    public void variableNodetest2() throws Exception {
        BasicRuntime runtime = new BasicRuntime();
        runtime.setValue("abc#", 2);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        Token token = new Token(TokenType.VARIABLE, "abc#", inputPos);
        VariableNode node = new VariableNode(token);
        ConstantNode node1 = new ConstantNode(new Token(TokenType.NUMBER, 15, inputPos));
        OperationNode node3 = new OperationNode(new Token(TokenType.MUL, inputPos));
        node3.setLeftNode(node);
        node3.setRightNode(node1);
        assertEquals(30, node3.calculateNode(runtime));

    }

    @Test
    public void variableNodetest3() throws Exception {
        BasicRuntime runtime = new BasicRuntime();
        runtime.setValue("abc13#", 5);
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        Token token = new Token(TokenType.VARIABLE, "abc13#", inputPos);
        VariableNode node = new VariableNode(token);
        ConstantNode node1 = new ConstantNode(new Token(TokenType.NUMBER, 15, inputPos));
        OperationNode node3 = new OperationNode(new Token(TokenType.MINUS, inputPos));
        node3.setLeftNode(node);
        node3.setRightNode(node1);
        assertEquals(-10, node3.calculateNode(runtime));
    }

    @Test
    public void variableNodeTest4() throws Exception {
        Tokenizer tokens = Tokenizer.createFromString("a+b+15");
        ExpressionParser parser = new ExpressionParser();
        Node node = parser.expression(tokens);
        BasicRuntime runtime = new BasicRuntime();
        runtime.setValue("a", 2);
        runtime.setValue("b", 8);
        assertEquals(25, node.calculateNode(runtime));
    }

    @Test
    public void variableNodetest5() throws Exception {
        BasicRuntime runtime = new BasicRuntime();
        runtime.setValue("abc#", 2);
        runtime.setValue("dc$", 3);
        Node node1 = (Node) Node.createNodeFromFile("data/test2.txt");
        node1.calculateNode(runtime);
        assertEquals(-411, node1.calculateNode(runtime));
    }

    @Test
    public void variableNodeTest5() throws Exception {
        Tokenizer tokens = Tokenizer.createFromString("alpha+(beta-m3321*25)-pi");
        ExpressionParser parser = new ExpressionParser();
        Node node = parser.expression(tokens);
        BasicRuntime runtime = new BasicRuntime();
        runtime.setValue("alpha", 10);
        runtime.setValue("beta", 300);
        runtime.setValue("m3321", 10);
        runtime.setValue("pi", 100);
        tokens.close();
        assertEquals(-40, node.calculateNode(runtime));
    }
}
