package com.ssv.toma.basic.runtime;

/**
 * Public class contains extends IfContext class. Contains an object IfOperation
 * containing "if - condition"
 * 
 * @author toma
 *
 */
public class ElseContext extends IfContext {
    /**
     * Public constructor with parameters
     *
     * @param idx
     *            - int
     * @param oper
     *            - an IfOperation objects
     */
    public ElseContext(GotoOperation oper) {
        super(oper);
    }
}
