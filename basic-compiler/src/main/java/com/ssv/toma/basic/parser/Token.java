package com.ssv.toma.basic.parser;

/**
 * Public class Token Object token is created during reading an input. Token is
 * equivalent of unit and it could be String, number, variable
 *
 * @author toma
 *
 */
public class Token {
    /**
     * Class members
     */
    TokenType tokenType;
    Object value;
    InputPosition inputPos;

    /**
     * Constructor for token carrying information about type of operator ("-",
     * "=", "*" and etc)
     *
     * @param type
     *            - information what type of operator
     * @param line
     *            - which line the token is located
     * @param pos
     *            - which position the token is located in the line
     */
    public Token(Object value, TokenType type, InputPosition inputPos) {
        tokenType = type;
        this.value = value;
        this.inputPos = inputPos;
    }

    /**
     * Constructor for token carrying information about type of operator ("-",
     * "=", "*" and etc)
     *
     * @param type
     *            - information what type of operator
     * @param inputPos
     *            - an object of InputPosition class which contains information
     *            about a line and a position the token is located
     */
    public Token(TokenType type, InputPosition inputPos) {
        this(null, type, inputPos);
    }

    /**
     * Constructor for token carrying information about number
     *
     * @param type
     *            - information what type of operator
     * @param line
     *            - which line the token is located
     * @param pos
     *            - which position the token is located in the line
     */
    public Token(TokenType type, Integer number, InputPosition inputPos) {
        this(number, type, inputPos);
    }

    /**
     * Constructor for token carrying information about String
     *
     * @param type
     *            - information what type of operator
     * @param line
     *            - which line the token is located
     * @param pos
     *            - which position the token is located in the line
     */
    public Token(TokenType type, String string, InputPosition inputPos) {
        this(string, type, inputPos);
    }

    /**
     * Returns true if two objects are equal to each other
     */
    @Override
    public boolean equals(Object token) {
        if (getTokenType() != ((Token) token).getTokenType()) {
            return false;
        }
        switch (getTokenType()) {
        case VARIABLE:
        case STRING:
        case NUMBER:
            return getValue().equals(((Token) token).getValue());
        default:
            return true;
        }
    }

    /**
     * Returns object of class InputPosition
     *
     * @return - InputPosition object
     */
    public final InputPosition getInputPos() {
        return inputPos;
    }

    /**
     * Returns type of token
     *
     * @return - tokenType
     */
    public TokenType getTokenType() {
        return tokenType;
    }

    /**
     * Returns value of didgit or string or variable in token
     *
     * @return - Object
     */
    public Object getValue() {
        return value;
    }
    /**
     * Prints the object
     *
     */
    @Override
    public String toString() {
        return "Position: " + inputPos + ", type: " + getTokenType() + " value:" + getValue();
    }

}
