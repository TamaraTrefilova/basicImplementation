package com.ssv.toma.basic.parser;

import java.io.IOException;

/**
 * Interface Sequencer allows to implement a sequencer which can read symbols
 * from input
 *
 * @author toma
 *
 */
public interface Sequencer {
    /**
     * Returns current symbol from input
     *
     * @return - symbol
     * @throws Exception
     */
    int ch();

    /**
     * Closes input
     *
     * @throws Exception
     */
    void close() throws Exception;

    /**
     * Returns object of class MutInputPosition
     *
     * @return object of class MutInputPosition
     */
    public InputPosition copyCurrentPosition();

    /**
     * Reads next symbol
     *
     * @throws IOException
     */
    void next() throws ParserException;
}
