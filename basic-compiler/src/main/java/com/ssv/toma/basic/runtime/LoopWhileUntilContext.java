package com.ssv.toma.basic.runtime;

/**
 * Public class contains List's idx, and object IfOperation. The class's object
 * keeps information about a list's idx where the variable is located , and
 * object IfOperation containing "Loop While/Until - condition"
 *
 * @author toma
 *
 */
public class LoopWhileUntilContext extends BreakContext {

    /**
     * Public constructor with parameters
     *
     * @param idx
     *            - int
     */
    public LoopWhileUntilContext(int idx) {
        super(idx);
    }

}
