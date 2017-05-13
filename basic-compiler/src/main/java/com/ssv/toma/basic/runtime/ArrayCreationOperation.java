package com.ssv.toma.basic.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssv.toma.basic.parser.expression.Node;

/**
 * Public class implements Operation interface. Creates an object which holds an
 * array' name, types of objects in the array, and a list with array's
 * dimensions
 *
 * @author toma
 *
 */
public class ArrayCreationOperation implements Operation {

    private final String name;
    private final String typeName;
    private final List<Node> listNode;

    /**
     * Public constructor
     *
     * @param name
     *            - String
     * @param typeName
     *            - String
     * @param listNode
     *            - List with nodes
     */
    public ArrayCreationOperation(String name, String typeName, List<Node> listNode) {
        super();
        this.name = name;
        this.typeName = typeName;
        this.listNode = listNode;
    }

    /**
     * Returns a list with nodes (dimensions for array)
     * 
     * @return
     */
    public List<Node> getListNode() {
        return listNode;
    }

    /**
     * Returns array's name
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns type of array (it could be String, Integer or any other object)
     * 
     * @return
     */
    public String getTypeName() {
        return typeName;
    }
    /**
     * Passes information about array's dimensions and name to a Map in
     * BasicRuntime class. Returns next idx from a list of operations
     */
    @Override
    public int run(BasicRuntime runtime) throws Exception {
        List<Integer> listInt = new ArrayList<>();
        for (Node node : listNode) {
            Object inter = node.calculateNode(runtime);
            if (inter instanceof Integer) {
                listInt.add((Integer) inter);
            } else {
                throw new BasicRuntimeException(BasicRuntimeException.INVALID_TOKEN, node.getToken());
            }
        }
        runtime.createArray(name, listInt);
        return NEXT_OPERATOR;
    }

    /**
     * The method is not used in the class
     */
    @Override
    public void setupLinks(Map<Integer, Integer> references) {

    }

}
