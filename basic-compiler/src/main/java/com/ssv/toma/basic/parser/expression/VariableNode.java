package com.ssv.toma.basic.parser.expression;

import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.runtime.BasicRuntime;
import com.ssv.toma.basic.runtime.BasicRuntimeException;

/**
 * Class VariableNode extends abstract Node. Creates a node carrying only
 * terminal type (sequences of characters, digits and # or $) Every node doesn't
 * have children
 *
 * @author toma
 *
 */
public class VariableNode extends Node {

    /**
     * Constructor - contains only token
     *
     * @param token
     */
    public VariableNode(Token token) {
        super(token);
    }

    /**
     * Returns value of variable containing in Token
     */
    @Override
    public Object calculateNode(BasicRuntime runtime) {
        Object value = runtime.getValue(getToken().getValue());
        if (value == null) {
            throw new BasicRuntimeException(BasicRuntimeException.INVALID_SYMBOL, getToken());
        }
        return value;
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
        if (!getToken().equals(((VariableNode) node).getToken())) {
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
        System.out.println(getToken().getValue());
    }

    @Override
    public String toString() {
        return getToken().getValue().toString();
    }

}
