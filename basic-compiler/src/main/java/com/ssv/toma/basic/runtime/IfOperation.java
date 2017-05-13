package com.ssv.toma.basic.runtime;

import com.ssv.toma.basic.parser.expression.Node;

/**
 * Public class implements Operation interface. Creates an object which contains
 * an index of a string where our program should go during execution
 *
 * @author toma
 *
 */
public class IfOperation extends GotoOperation {
    private final Node iftrue;

    /**
     * Public constructor
     *
     * @param label
     *            Integer
     * @param iftrue
     *            - Node
     */
    public IfOperation(Node iftrue, Integer label) {
        super(label);
        this.iftrue = iftrue;
    }

    /**
     * Returns a node
     * 
     * @return - Node object
     */
    public Node getNode() {
        return iftrue;
    }

    /**
     * Returns an index of string
     */
    @Override
    public int run(BasicRuntime runtime) throws Exception {
        if ((Boolean) iftrue.calculateNode(runtime)) {
            return super.run(runtime);
        }
        return NEXT_OPERATOR;
    }

    /**
     * Prints the Operator
     */
    @Override
    public String toString() {
        return "IF " + iftrue + " GOTO " + idx;
    }

}
