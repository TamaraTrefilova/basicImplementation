package com.ssv.toma.basic.parser.customtest;

import com.ssv.toma.basic.parser.Tokenizer;
import com.ssv.toma.basic.parser.expression.ExpressionParser;
import com.ssv.toma.basic.parser.expression.Node;

public class ExpressionParserTest {

    public static void main(String[] args) throws Exception {
        // Tokenizer tokenizer =
        // Tokenizer.createFromFile("data/testMulDiv.txt");
        Tokenizer tokenizer = Tokenizer.createFromString("5>5>7");
        ExpressionParser expressionParser = new ExpressionParser();
        Node node1 = expressionParser.expression(tokenizer);
        node1.preOrderTraverse();
        // System.out.println(node1.calculateNode());
        tokenizer.close();
        //
        // OperationNode node = new OperationNode(new Token(TokenType.PLUS, 1,
        // 0));
        // node.setLeftNode(new ConstantNode(new Token(TokenType.NUMBER, 7, 0,
        // 0)));
        // node.setRightNode(new ConstantNode(new Token(TokenType.NUMBER, 5, 2,
        // 0)));
        // Node node2 = node;
        // node1.preOrderTraverse();
        // System.out.println("___________________");
        // node1.postOrderTraverse();
        // ;
        // System.out.println("Result: " + node1.calculateNode());
        //
        // System.out.println(tokenizer.token());
        // System.out.println(node1.equals(node2));
        // System.out.println(node2.equals(node1));

    }

}
