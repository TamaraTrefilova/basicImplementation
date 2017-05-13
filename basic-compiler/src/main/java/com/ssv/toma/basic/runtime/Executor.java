package com.ssv.toma.basic.runtime;

import java.util.List;

/**
 * Public class fills out a map with variable's values getting them from list of
 * Operation
 *
 * @author toma
 *
 */
public class Executor {
    private final BasicRuntime runtime;
    private List<Operation> opers;
    private int opCounter = 0;

    /**
     * Public constructor initializes a BasicRuntime map
     */
    public Executor(List<Operation> opers) {
        runtime = new BasicRuntime();
        this.opers = opers;
    }

    /**
     * Returns false if END statement has been executed or if a current pointer
     * is out of scope.
     *
     * @return
     */
    public boolean active() {
        return opCounter != opers.size();
    }

    /**
     * Returns a current list's idx during execution
     *
     * @return
     */
    public int currentPointer() {
        return opCounter;
    }

    /**
     * Returns a map with variables and values
     *
     * @return a map
     */
    public final BasicRuntime getRuntime() {
        return runtime;
    }

    /**
     * Run only current statement and modify current pointer so it point to the
     * next operation
     *
     * @throws Exception
     */
    public void next() throws Exception {
        int nextOpIdx = opers.get(opCounter).run(runtime);
        if (nextOpIdx == Operation.NEXT_OPERATOR) {
            opCounter++;
        } else if (nextOpIdx == Operation.STOP_OPERATOR) {
            opCounter = opers.size();
        } else {
            opCounter = nextOpIdx;
        }
    }

    /**
     * Sets list of operation, reinitializes the program
     *
     * @param operations
     */
    public void reset(List<Operation> operations) {
        opers = operations;
        opCounter = 0;
    }

    /**
     * Takes a from list value that has been assigned to a variable and puts
     * them into a map
     *
     * @param opers
     *            - list of Operators
     * @throws Exception
     */
    public void run(List<Operation> operations) throws Exception {
        reset(operations);
        while (active()) {
            next();
        }
    }

}
