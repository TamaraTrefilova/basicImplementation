package com.ssv.toma.basic.runtime;

import java.util.Map;

import com.ssv.toma.basic.parser.expression.Node;

/**
 * Public class implements Operation interface. Creates an object which contains
 * a variable name, a variable's value.
 *
 * @author toma
 *
 */
public class PrintOperation implements Operation {
    private final String variable;
    private final Node expression;

    /**
     * Public constructor
     *
     * @param variable
     *            - String
     * @param expression
     *            - Node
     * @param nextOperator
     *            - int
     */
    public PrintOperation(String variable, Node expression) {
        this.variable = variable;
        this.expression = expression;
    }

    /**
     * Prints a variable name and its value. Return -1
     */
    @Override
    public int run(BasicRuntime runtime) throws Exception {
        if (variable != null) {
            System.out.print(variable + " ");
        }
        if (expression != null) {
            System.out.println(expression.calculateNode(runtime));
        }
        System.out.println();
        return NEXT_OPERATOR;
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

    /**
     * Prints the Operator
     */
    @Override
    public String toString() {
        return "PRINT " + (variable == null ? "" : "'" + variable + "';")
                + (expression == null ? "" : expression.toString());
    }

}
