package com.ssv.toma.basic.runtime;

import java.util.Map;

/**
 * Public interface for AssignmentOperation and OutputOperation classes
 *
 * @author toma
 *
 */
public interface Operation {
    public static final int NEXT_OPERATOR = -1;
    public static final int STOP_OPERATOR = -2;

    /**
     * Sets a variable name and its value into a map Return int
     *
     * @param runtime
     *            - map
     * @return - int
     * @throws Exception
     */
    public int run(BasicRuntime runtime) throws Exception;

    /**
     * Sets an index of string which holds the label
     *
     * @param references
     *            - map
     * @return - Map
     */
    void setupLinks(Map<Integer, Integer> references);
}
