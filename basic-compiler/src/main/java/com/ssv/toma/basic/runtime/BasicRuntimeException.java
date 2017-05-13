package com.ssv.toma.basic.runtime;

import com.ssv.toma.basic.parser.InputPosition;
import com.ssv.toma.basic.parser.Token;

/**
 * Public class. A class's object is thrown then an exception are occurred in
 * Expression parser class class. A class object carries information about a
 * line, position and character where a mistake is located.
 *
 * @author toma
 *
 */
public class BasicRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -7484527214004527858L;

    public static final String INVALID_SYMBOL = "The variable doesn't have a value";
    public static final String INVALID_SIGNATURE = "Number of arguments doesn't correspond to this funcion";
    public static final String INVALID_TOKEN = "The variable supposed to be an Integer";
    public static final String INVALID_ARRAY_SIZE = "Number of arguments doesn't correspond to this array's size";
    private String reason;
    private Token token;

    /**
     * Public constructor
     *
     * @param reason
     *            - String that carries information about exception's reason
     * @param token
     *            - object of Token class that carries information about symbol
     *            which can cause an exception
     *
     */
    public BasicRuntimeException(String message, Token token) {
        reason = message;
        this.token = token;
    }

    /**
     * Returns string which contains exception's reason and position in a file
     * where the exception has occurred
     */
    @Override
    public String getMessage() {
        return getReason();

    }

    /**
     * Returns current InputPosition
     */
    public InputPosition getPosition() {
        return token.getInputPos();

    }

    /**
     * Returns a message contained in the object
     *
     * @return - String
     */
    public String getReason() {
        return reason;
    }
}
