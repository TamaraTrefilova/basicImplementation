package com.ssv.toma.basic.runtime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssv.toma.basic.parser.ImmutableInputPosition;
import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.parser.TokenType;
import com.ssv.toma.basic.parser.expression.Node;

/**
 * Public class contains Map where contains variables (as a key) and their
 * values(as a value) Also contains a Map which holds information about an array
 * (array's name and values) that can be created by a user
 *
 * @author toma
 *
 */
public class BasicRuntime {
    private final Map<String, Object> runtime;
    private final Map<String, ArrayDefinition> arrays;

    /**
     * Default public constructor
     */
    public BasicRuntime() {
        runtime = new HashMap<>();
        arrays = new HashMap<>();
    }

    /**
     * Checks if array's dimensions (which is being passed as parameters) are
     * correct.
     *
     * @param variable
     *            - array's name
     * @param param
     *            - List of parameters
     * @param dim
     *            - List of dimensions
     * @throws Exception
     */
    public void checkArrayDimension(String variable, List<Node> param, List<Integer> dim) throws Exception {
        for (int i = 0; i < dim.size(); i++) {
            if (dim.get(i) < (Integer) param.get(i).calculateNode(this)) {
                throw new BasicRuntimeException(BasicRuntimeException.INVALID_ARRAY_SIZE,
                        new Token(TokenType.STRING, variable, new ImmutableInputPosition(0, 0)));
            }
        }
    }

    /**
     * Creates an array using information about dimensions and places it inti a
     * Map
     *
     * @param name
     * @param dimensions
     */
    public void createArray(String name, List<Integer> dimensions) {
        ArrayDefinition arrayDefin = new ArrayDefinition(dimensions);
        arrays.put(name, arrayDefin);
    }

    /**
     * Calculates a function or returns a value of a current position in an
     * array
     *
     * @param token
     *            - Token object
     * @param param
     *            - List of nodes with values that should be calculated
     * @return - a function's value or array with values
     * @throws Exception
     */
    public Object executeFunction(Token token, List<Node> param) throws Exception {
        /*
         * returns a value of a current position in an array
         */
        String name = (String) token.getValue();
        if (arrays.containsKey(name)) {
            List<Integer> dim = arrays.get(name).getDimensions();

            if (dim.size() != param.size()) {
                throw new BasicRuntimeException(BasicRuntimeException.INVALID_ARRAY_SIZE, token);
            }
            checkArrayDimension((String) token.getValue(), param, dim);
            int pos = 0;
            int product = 1;
            for (int i = 1; i <= dim.size(); i++) {
                pos += (Integer) param.get(i - 1).calculateNode(this) * product;
                if (dim.size() > 1) {
                    product = dim.get(i - 1);
                }
            }
            Object[] values = arrays.get(name).getValues();
            return values[pos];
        }
        /*
         * Calculates math function
         */
        if ("sin".equals(name)) {
            if (param.size() != 1) {
                throw new BasicRuntimeException(BasicRuntimeException.INVALID_SIGNATURE, token);
            }
            return Math.sin(((Number) param.get(0).calculateNode(this)).doubleValue());
        } else if ("cos".equals(name)) {
            if (param.size() != 1) {
                throw new BasicRuntimeException(BasicRuntimeException.INVALID_SIGNATURE, token);
            }
            return Math.cos(((Number) param.get(0).calculateNode(this)).doubleValue());
        } else if ("min".equals(name)) {
            if (param.size() != 2) {
                throw new BasicRuntimeException(BasicRuntimeException.INVALID_SIGNATURE, token);
            }
            return Math.min(((Number) param.get(0).calculateNode(this)).doubleValue(),
                    ((Number) param.get(1).calculateNode(this)).doubleValue());
        } else if ("max".equals(name)) {
            if (param.size() != 2) {
                throw new BasicRuntimeException(BasicRuntimeException.INVALID_SIGNATURE, token);
            }
            return Math.max(((Number) param.get(0).calculateNode(this)).doubleValue(),
                    ((Number) param.get(1).calculateNode(this)).doubleValue());
        }
        return null;
    }

    /**
     * Returns a map that holds information about an array
     *
     * @return - Map
     */
    public Map<String, ArrayDefinition> getArrays() {
        return arrays;
    }
    /**
     * Returns from a map value which has been assignment for that variable
     *
     * @param object
     *            - String "variable"
     * @return - int
     */
    public Object getValue(Object object) {
        return runtime.get(object);
    }

    /**
     * Sets value(int) to map accordingly its assignment
     *
     * @param variable
     *            - String "variable"
     * @param value
     *            - int
     */
    public void setValue(String variable, Object value) {
        runtime.put(variable, value);
    }

    /**
     * Sets a value into an array which is held BasicRuntime object
     *
     * @param variable
     *            - String (an arrays name) -
     * @param value
     *            - An object (it could be an int, String, etc
     * @param param
     *            - List of int (coordinates in the array where the value should
     *            be set
     * @throws Exception
     */
    public void setValue(String variable, Object value, List<Node> param) throws Exception {
        if (arrays.containsKey(variable)) {
            List<Integer> dim = arrays.get(variable).getDimensions();
            if (dim.size() != param.size()) {
                throw new BasicRuntimeException(BasicRuntimeException.INVALID_ARRAY_SIZE,
                        new Token(TokenType.STRING, variable, new ImmutableInputPosition(0, 0)));
            }
            checkArrayDimension(variable, param, dim);
            int pos = 0;
            int product = 1;
            for (int i = 1; i <= dim.size(); i++) {
                pos += (Integer) param.get(i - 1).calculateNode(this) * product;
                if (dim.size() > 1) {
                    product = dim.get(i - 1);
                }
            }
            arrays.get(variable).getValues()[pos] = value;
        }
    }

}
