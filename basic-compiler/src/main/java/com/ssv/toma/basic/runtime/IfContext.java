package com.ssv.toma.basic.runtime;

/**
 * Public class contains an object IfOperation. The class's object keeps
 * information about the object IfOperation containing "if - condition"
 *
 * @author toma
 *
 */
public class IfContext implements OperationContext {
    private GotoOperation oper;

    /**
     * Public constructor with parameter
     * 
     * @param oper
     *            - an IfOperation objects
     */
    public IfContext(GotoOperation oper) {
        this.oper = oper;
    }

    /**
     * Returns IfOperation object
     *
     * @return
     */

    public GotoOperation getOper() {
        return oper;
    }

}
