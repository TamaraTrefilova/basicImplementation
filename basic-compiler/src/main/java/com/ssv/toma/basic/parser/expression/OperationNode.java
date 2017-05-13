package com.ssv.toma.basic.parser.expression;

import com.ssv.toma.basic.parser.ParserTokenException;
import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.runtime.BasicRuntime;

/**
 * Class OperationNode extends abstract Node. Creates a node carrying only
 * operations type (+,-,*,=, boolean and etc) Have 2 children
 *
 * @author toma
 *
 */
public class OperationNode extends Node {

    private Node leftNode;

    private Node rightNode;

    public OperationNode(Token token) {
        super(token);
    }

    /**
     * Calculates a trees's value including all nodes
     */
    @Override
    public Object calculateNode(BasicRuntime runtime) throws Exception {
        Object left = leftNode.calculateNode(runtime);
        Object right = rightNode.calculateNode(runtime);
        Object rez = calculus(left, right);
        return rez;
    }

    /**
     * Calculates a node's value including all children
     *
     * @param left
     *            - can be a OperationNode or ConstantNode
     * @param right
     *            - can be a OperationNode or ConstantNode
     * @param operator
     *            - type of operator (*,+,-,=, > and etc) in the node
     * @return - Object
     * @throws Exception
     *             (if type of operator is not correct for the expression or if
     *             the expression is not correct itself)
     */
    @SuppressWarnings("incomplete-switch")
    public Object calculus(Object ileft, Object iright) throws Exception {
        if (ileft instanceof String && iright instanceof String) {
            String left = (String) ileft;
            String right = (String) iright;
            switch (getToken().getTokenType()) {
            case PLUS:
                return left + right;
            case NOTEQUAL:
                return !left.equals(right);
            case EQUAL:
                return left.equals(right);
            }

        } else if (ileft instanceof Integer && iright instanceof Integer) {
            Integer left = (Integer) ileft;
            Integer right = (Integer) iright;
            switch (getToken().getTokenType()) {
            case MUL:
                return left * right;
            case PLUS:
                return left + right;
            case MINUS:
                return left - right;
            case DIV:
                return left / right;
            case MORE:
                return left > right;
            case LESS:
                return left < right;
            case MOREOREQUAL:
                return left >= right;
            case LESSOREQUAL:
                return left <= right;
            case EQUAL:
                return left == right;
            case NOTEQUAL:
                return left != right;
            }

        } else if (ileft instanceof Boolean && iright instanceof Boolean) {
            Boolean left = (Boolean) ileft;
            Boolean right = (Boolean) iright;
            switch (getToken().getTokenType()) {
            case OR:
                return left || right;
            case AND:
                return left && right;
            case EQUAL:
                return left.equals(right);
            case NOTEQUAL:
                return !left.equals(right);
            }

        } else if (ileft instanceof String) {
            String left = (String) ileft;
            switch (getToken().getTokenType()) {
            case PLUS:
                return left + iright;
            }
        }
        throw new ParserTokenException(ParserTokenException.EXPRESSION_IS_NOT_VALID, getToken());
    }

    /**
     * Returns 2 because its a binary tree node
     */
    @Override
    public int childCount() {
        return 2;
    }

    /**
     * Returns true if two objects are equal to each other
     */
    @Override
    public boolean equals(Object node) {
        if (node == null) {
            return false;
        }
        if (!this.getClass().equals(node.getClass())) {
            return false;
        }

        Token token = getToken();
        Token token1 = ((OperationNode) node).getToken();
        if (!token.equals(token1)) {
            return false;
        }
        for (int i = 0; i < childCount(); i++) {
            Node n1 = get(i);
            Node n2 = ((OperationNode) node).get(i);
            if (n1 == null) {
                if (n2 != null) {
                    return false;
                }
            } else {
                if (n2 == null || !n1.equals(n2)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns left and right child.
     *
     * @param idx
     *            - int
     * @return - Node
     */
    @Override
    public Node get(int idx) {
        if (idx == 0) {
            return leftNode;
        } else if (idx == 1) {
            return rightNode;
        } else {
            throw new RuntimeException("Wrong value entered");
        }
    }

    @Override
    public void printSelf() {
        System.out.println(getToken().getTokenType());
    }

    /**
     * Sets left node
     *
     * @param node
     */
    public void setLeftNode(Node node) {
        leftNode = node;
    }

    /**
     * Sets right node
     *
     * @param node
     */
    public void setRightNode(Node node) {
        rightNode = node;
    }

    @Override
    public String toString() {
        return leftNode.toString() + getToken().getTokenType() + rightNode.toString();
    }

}
