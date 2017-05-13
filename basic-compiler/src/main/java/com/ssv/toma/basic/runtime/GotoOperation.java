package com.ssv.toma.basic.runtime;

import java.util.Map;

/**
 * Public class implements Operation interface. Creates an object which contains
 * an index of a string where our program should go during execution
 *
 * @author toma
 *
 */
public class GotoOperation implements Operation {
    private int label;

    protected int idx = -1;

    /**
     * Public constructor
     *
     * @param label
     *            Integer
     */
    public GotoOperation(Integer label) {
        this.label = label;
    }

    /**
     * Returns an index of string
     */
    @Override
    public int run(BasicRuntime runtime) throws Exception {
        return idx;
    }

    /**
     * Sets idx value into object
     *
     * @param idx
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }

    /**
     * Sets an index of string accordingly the key (which is a label of string)
     */
    @Override
    public void setupLinks(Map<Integer, Integer> references) {
        if (idx == -1) {
            idx = references.get(label);
        }
    }

    /**
     * Prints the Operator
     */
    @Override
    public String toString() {
        return "GOTO " + idx;
    }

}
