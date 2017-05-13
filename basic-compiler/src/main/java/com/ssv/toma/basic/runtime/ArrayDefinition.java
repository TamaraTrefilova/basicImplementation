package com.ssv.toma.basic.runtime;

import java.util.List;

/**
 * Public class which holds information about array's characteristics:
 * dimensions and values
 *
 * @author toma
 *
 */
public class ArrayDefinition {
    private final List<Integer> dimensions; // If it is one-dimensional - just 1
                                            // value,
    // two-dimensional - two values etc.
    private final Object[] values; // List of values for this array. The

    /**
     * Public constructor. Creates an array depending on its dimensions and
     * initializes it
     *
     * @param listInt
     *            List of nodes
     */
    public ArrayDefinition(List<Integer> listInt) {
        super();
        dimensions = listInt;
        int size = 1;
        for (int i = 0; i < listInt.size(); i++) {
            size *= listInt.get(i);
        }
        values = new Object[size];
        for (int i = 0; i < size; i++) {
            values[i] = 0;
        }

    }

    /**
     * Returns a list with integers (which means dimentions for the array)
     * 
     * @return - List of int's
     */
    public List<Integer> getDimensions() {
        return dimensions;
    }

    /**
     * Returns an array of values which has been held in a array
     * 
     * @return
     */
    public Object[] getValues() {
        return values;
    }

}
