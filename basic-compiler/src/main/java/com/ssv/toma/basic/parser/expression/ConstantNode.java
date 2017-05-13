package com.ssv.toma.basic.parser.expression;

import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.runtime.BasicRuntime;

/**
 * Class ConstantNode extends abstract Node. Creates a node carrying only
 * terminal type (digit, String, variable, boolean) Every node doesn't have
 * children
 *
 * @author toma
 *
 */
public class ConstantNode extends Node {

    /**
     * Constructor - contains only token
     *
     * @param token
     */
    public ConstantNode(Token token) {
        super(token);
    }

    /**
     * Returns value of token, if token type false or true - returns true or
     * false
     */
    @Override
    public Object calculateNode(BasicRuntime runtime) throws Exception {
        switch (getToken().getTokenType()) {
        case TRUE:
            return true;
        case FALSE:
            return false;
        default:
            return getToken().getValue();
        }
    }

    /**
     * Returns 0 because the type of node doesn't have child
     */
    @Override
    public int childCount() {
        return 0;
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
        if (!getToken().equals(((ConstantNode) node).getToken())) {
            return false;
        }
        return true;
    }

    /**
     * Returns information if a node has children or not. The node doesn't have
     * children
     */
    @Override
    public Node get(int idx) {
        throw new RuntimeException("This node doesn't have children");
    }

    /**
     * Prints itself
     */
    @Override
    public void printSelf() {
        switch (getToken().getTokenType()) {
        case TRUE:
            System.out.println(true);
            break;
        case FALSE:
            System.out.println(false);
            break;
        default:
            System.out.println(getToken().getValue());
            break;
        }
    }

    @Override
    public String toString() {
        switch (getToken().getTokenType()) {
        case TRUE:
            return "true";
        case FALSE:
            return "false";
        default:
            return getToken().getValue().toString();
        }
    }

}
