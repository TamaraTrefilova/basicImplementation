package com.ssv.toma.basic.runtime;

import java.util.List;
import java.util.Map;

import com.ssv.toma.basic.parser.expression.Node;

/**
 * Public class implements Operation interface. Creates an object which contains
 * a variable name, a variable's value, and next string's number(label)
 *
 * @author toma
 *
 */
public class AssignmentOperation implements Operation {
    private final String variable;
    private final Node expression;
    private final List<Node> arrayNodes;

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
    public AssignmentOperation(String variable, Node expression) {
        this.variable = variable;
        this.expression = expression;
        arrayNodes = null;
    }

    /**
     * Public constructor
     *
     * @param variable
     *            - String
     * @param expression
     *            -Node
     * @param list
     *            - List of Nodes
     */
    public AssignmentOperation(String variable, Node expression, List<Node> list) {
        this.variable = variable;
        this.expression = expression;
        arrayNodes = list;
    }

    /**
     * Returns list of nodes
     * 
     * @return - List
     */
    public List<Node> getArrayNodes() {
        return arrayNodes;
    }

    /**
     * Sets a variable name and its value into a map Return int
     */
    @Override
    public int run(BasicRuntime runtime) throws Exception {
        if (arrayNodes != null) {
            runtime.setValue(variable, expression.calculateNode(runtime), arrayNodes);
        } else {
            runtime.setValue(variable, expression.calculateNode(runtime));
        }
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
     * Prints an object
     */
    @Override
    public String toString() {
        return variable + "=" + expression;
    }

}
