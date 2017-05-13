package com.ssv.toma.basic.parser;

/**
 * Abstract class ParserExcepton.
 * 
 * @author toma
 *
 */
public abstract class ParserException extends Exception {

    private static final long serialVersionUID = 3463798636467288212L;

    /**
     * Returns string which contains exception's reason and position in a file
     * where the exception has occurred
     */
    @Override
    public String getMessage() {
        return getReason() + " " + getPosition();

    }

    public abstract InputPosition getPosition();

    public abstract String getReason();
}
