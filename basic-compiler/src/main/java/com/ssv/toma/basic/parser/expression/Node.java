package com.ssv.toma.basic.parser.expression;

import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.parser.Tokenizer;
import com.ssv.toma.basic.runtime.BasicRuntime;

/**
 * Abstract class Node Allows create a node containing Token
 *
 * @author toma
 *
 */
public abstract class Node {
    public static Object createNodeFromFile(String fileName) throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile(fileName);
        ExpressionParser expressionParser = new ExpressionParser();
        Node node = expressionParser.expression(tokenizer);
        tokenizer.close();
        return node;
    }

    public static Object createNodeFromString(String stringName) throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(stringName);
        ExpressionParser expressionParser = new ExpressionParser();
        Node node = expressionParser.expression(tokenizer);
        tokenizer.close();
        return node;
    }

    private final Token token;

    /**
     * Constructor
     *
     * @param token
     */
    public Node(Token token) {
        this.token = token;
    }

    private void _postOrderTraverse(int tabs) {
        for (int i = 0; i < tabs; i++) {
            System.out.print("\t");
        }
        for (int i = 0; i < childCount(); i++) {
            get(i)._postOrderTraverse(tabs + 1);
        }
        printSelf();
    }

    private void _preOrderTraverse(int tabs) {
        for (int i = 0; i < tabs; i++) {
            System.out.print("\t");
        }
        printSelf();
        for (int i = 0; i < childCount(); i++) {
            get(i)._preOrderTraverse(tabs + 1);
        }
    }

    /**
     * Returns result- value of the node
     *
     * @return Integer, String, Variable, Boolean
     * @throws Exception
     */
    public abstract Object calculateNode(BasicRuntime runtime) throws Exception;

    /**
     * Returns amount node's child ( 0 - if no child, 1- if 1 child and etc)
     *
     * @return
     */
    public abstract int childCount();

    /**
     * Returns a child. If binary tree returns left child if parameter is 0 and
     * returns right child if parameter is 1 and etc
     *
     * @param idx
     *            - int
     * @return - Node
     */
    public abstract Node get(int idx);

    /**
     * Returns Token associated with the Node
     *
     * @return - Token
     */
    public final Token getToken() {
        return token;
    }

    /**
     * Performs postfix traverse
     */
    public void postOrderTraverse() {
        _postOrderTraverse(0);
    }

    /**
     * Performs prefix traverse
     */
    public void preOrderTraverse() {
        _preOrderTraverse(0);
    }

    /**
     * Prints itself
     */
    public abstract void printSelf();

}
