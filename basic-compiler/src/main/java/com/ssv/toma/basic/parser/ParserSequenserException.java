package com.ssv.toma.basic.parser;

import java.io.IOException;

/**
 * Public class. A class's object is thrown then an exception are occurred in
 * DefaultSequencer class. A class object carries information about a line,
 * position and character where a mistake is located.
 *
 * @author toma
 * @param <MutInputPosition>
 *
 */
public class ParserSequenserException extends ParserException {

    private static final String COLON = ":";
    private static final long serialVersionUID = 6291134782916916018L;
    public static final String INVALID_SYMBOL = "Invalid symbol";
    public static final String QUOTES_ECPECTED = "Qoutes expected";
    public static final String COMMENT_LINE = "You reach EOF. Your comment line is not comleted correctly";
    private final String reason;
    private final int ch;
    private InputPosition inputPosition;

    /**
     * Public constructor
     *
     * @param reason
     *            - String that carries information about exception's reason
     * @param input
     *            - object of InputPosition interface that carries information
     *            about exception's location in a file (line, position in the
     *            line)
     * @param c
     *            - symbol at the position
     */
    public ParserSequenserException(String reason, InputPosition input, int c) {
        this.reason = reason;
        inputPosition = input;
        ch = c;
    }

    /**
     * Public constructor
     *
     * @param message
     *            - String
     *
     * @param sequencer
     *            - object of Sequencer class
     */
    public ParserSequenserException(String message, Sequencer sequencer) {
        reason = message;
        inputPosition = sequencer.copyCurrentPosition();
        ch = sequencer.ch();
    }

    /**
     * Returns true if two objects are equal to each other
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ParserSequenserException other = (ParserSequenserException) obj;
        if (ch != other.ch) {
            return false;
        }
        if (inputPosition == null) {
            if (other.inputPosition != null) {
                return false;
            }
        } else if (!inputPosition.equals(other.inputPosition)) {
            return false;
        }
        if (reason == null) {
            if (other.reason != null) {
                return false;
            }
        } else if (!reason.equals(other.reason)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a current character
     *
     * @return - char
     * @throws IOException
     */
    public char getChar() {
        return (char) ch;
    }

    /**
     * Returns current InputPosition
     */
    @Override
    public InputPosition getPosition() {
        return inputPosition;
    }

    /**
     * Returns a message contained in the object
     *
     * @return - String
     * @throws IOException
     */
    @Override
    public String getReason() {
        if (ch != -1) {
            return reason + " " + COLON + " " + getChar();
        }
        return reason;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ch;
        result = prime * result + (inputPosition == null ? 0 : inputPosition.hashCode());
        result = prime * result + (reason == null ? 0 : reason.hashCode());
        return result;
    }

    /**
     * Print the object
     */
    @Override
    public String toString() {
        return "ParserSequenserException [reason=" + reason + ", ch=" + ch + ", inputPosition=" + inputPosition + "]";
    }

}
