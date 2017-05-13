package com.ssv.toma.basic.runtime;

import java.util.ArrayList;
import java.util.List;

/**
 * Public class is created for implementing "while-for-if-else" looping. Every
 * time when we meet a "break" or "continue" in our code, we create a
 * GotoOperator and hold it in a list till we know what index should be put
 * there.
 *
 * @author toma
 *
 */
public class BreakContext implements OperationContext {
    private int idx;

    private List<GotoOperation> brakes;
    private List<GotoOperation> forContiniue;

    /**
     * Public constructor
     *
     * @param idx
     *            - int
     */
    public BreakContext(int idx) {
        this.idx = idx;
        brakes = new ArrayList<>();
        forContiniue = new ArrayList<>();
    }

    /**
     * List with GotoOperators which have been created when a break operator is
     * met in a code
     *
     * @return List
     */
    public List<GotoOperation> getBrakes() {
        return brakes;
    }

    /**
     * List with GotoOperators which have been created when a continue operator
     * is met in a code
     *
     * @return List
     */
    public List<GotoOperation> getForContiniue() {
        return forContiniue;
    }

    /**
     * Returns idx
     *
     * @return
     */
    public int getIdx() {
        return idx;
    }

    /**
     * Adds a GotoOperator into a list
     *
     * @param gotoOper
     *            - GotoOpeartor object
     */
    public void setForContinius(GotoOperation gotoOper) {
        forContiniue.add(gotoOper);
    }

    /**
     * Adds a GotoOperator into a list
     *
     * @param gotoOper
     *            - GotoOpeartor object
     */
    public void setGotoForBreak(GotoOperation gotoOper) {
        brakes.add(gotoOper);
    }

    /**
     * Sets idx (int) into the object
     *
     * @param idx
     *            - int
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }

}
