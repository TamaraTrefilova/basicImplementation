package com.ssv.toma.basic.runtime;

import java.util.Map;

/**
 * Public class implements Operation interface. Creates an object which stops
 * the execution
 *
 * @author toma
 *
 */
public class EndOperation implements Operation {

    /**
     * Returns constant which stops the execution
     */
    @Override
    public int run(BasicRuntime runtime) throws Exception {
        return STOP_OPERATOR;
    }

    /**
     * Sets an index of string which holds the label
     *
     * @param references
     *            - map
     * @return - Map
     */
    @Override
    public void setupLinks(Map<Integer, Integer> references) {
    }

}
