package com.ssv.toma.basic.parser.expression;

import java.util.List;

import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.runtime.BasicRuntime;

/**
 * Public class creates a node which hold a list with parameters have been
 * passed to a function
 *
 * @author toma
 *
 */
public class FunctionNode extends Node {

    private final List<Node> param;

    public FunctionNode(Token token, List<Node> param) {
        super(token);
        this.param = param;
    }

    /**
     * Calculates the function depending what type of function is.
     */
    @Override
    public Object calculateNode(BasicRuntime runtime) throws Exception {
        return runtime.executeFunction(getToken(), param);
    }

    /**
     * Returns amount of parameters in the list
     */
    @Override
    public int childCount() {
        return param.size();
    }

    /**
     * Returns a paticular parameter at current idx
     */
    @Override
    public Node get(int idx) {
        return param.get(idx);
    }

    @Override
    public void printSelf() {
        // TODO Auto-generated method stub

    }

}
