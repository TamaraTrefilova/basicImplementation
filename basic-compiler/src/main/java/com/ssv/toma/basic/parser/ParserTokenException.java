package com.ssv.toma.basic.parser;

/**
 * Public class. A class's object is thrown then an exception are occurred in
 * Expression parser class. A class object carries information about a line,
 * position and token where a mistake is located.
 *
 * @author toma
 *
 */
public class ParserTokenException extends ParserException {
    private static final long serialVersionUID = -4872892711173035244L;

    public static final String INVALID_TOKEN = "Invalid token";
    public static final String INVALID_OPERATOR = "Invalid operator in condition clause";
    public static final String BRAKET_EXPECTED = "Braket expected";
    public static final String EXPRESSION_IS_NOT_VALID = "Expression is not valid. Wrong number of parameters";

    public static final String QUOTES_ECPECTED = "Qoutes expected";

    private String reason;
    private Token token;

    /**
     * Public constructor
     *
     * @param token
     *            -Token
     */
    public ParserTokenException(String message, Token token) {
        reason = message;
        this.token = token;
    }

    /**
     * Returns an InputPosition object containing in a Token
     */
    @Override
    public final InputPosition getPosition() {
        return token.getInputPos();
    }

    /**
     * Returns a message contained in the object
     *
     * @return - String
     */

    @Override
    public final String getReason() {
        return reason;
    }

    /**
     * Returns a Token is located in an object
     *
     * @return - Token
     */
    public final Token getToken() {
        return token;
    }

}
