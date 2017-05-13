package com.ssv.toma.basic.runtime;

import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.parser.expression.Node;

/**
 * Public class contains Token, List's idx, and object IfOperation. The class's
 * object keeps information about variable, list's idx where the variable is
 * located , and object IfOperation containing "if - condition"
 *
 * @author toma
 *
 */
public class ForOperationContext extends BreakContext {

    private Token variableToken;
    private Node step;
    private GotoOperation oper;

    /**
     * Public constructor with parameters
     *
     * @param variableToken
     *            - Token
     * @param idx
     *            - int
     * @param oper
     *            - IfOperation object
     */
    public ForOperationContext(Token variableToken, int idx, GotoOperation oper, Node step) {
        super(idx);
        this.variableToken = variableToken;
        this.oper = oper;
        this.step = step;
    }

    /**
     * Returns IfOperation object
     *
     * @return
     */
    public GotoOperation getOper() {
        return oper;
    }

    /**
     * Returns a Node with expression for condition clause
     *
     * @return - Node
     */
    public Node getStep() {
        return step;
    }

    /**
     * Returns Token
     *
     * @return
     */
    public Token getVariableToken() {
        return variableToken;
    }
}
