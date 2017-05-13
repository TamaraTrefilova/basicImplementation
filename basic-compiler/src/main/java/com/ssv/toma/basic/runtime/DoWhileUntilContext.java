package com.ssv.toma.basic.runtime;

/**
 * Public class contains List's idx, and object IfOperation. The class's object
 * keeps information about a list's idx where the variable is located , and
 * object IfOperation containing "Do While/Until - condition"
 *
 * @author toma
 *
 */
public class DoWhileUntilContext extends BreakContext {

    private IfOperation oper;

    /**
     * Public constructor with parameters
     *
     * @param idx
     *            - int
     */
    public DoWhileUntilContext(int idx) {
        super(idx);
        oper = null;
    }

    /**
     * Public constructor with parameters
     *
     * @param idx
     *            - int
     * @param oper
     *            - an IfOperation objects
     */
    public DoWhileUntilContext(int idx, IfOperation oper) {
        super(idx);
        this.oper = oper;
    }

    /**
     * Returns IfOperation object
     *
     * @return
     */
    public IfOperation getOper() {
        return oper;
    }

    /**
     * Sets an IfOpration object into the object
     *
     * @param oper
     */
    public void setOper(IfOperation oper) {
        this.oper = oper;
    }
}
