package com.ssv.toma.basic.parser;

import java.io.IOException;
import java.io.Reader;

/**
 * Class ReaderSequencer extends interface Sequencer. Takes input as a Reader,
 * reads symbol by symbol from input stream
 *
 * @author toma
 *
 */
class ReaderSequencer implements Sequencer {

    private final Reader input;
    private int symbol;
    private final MutableInputPosition position = new MutableInputPosition(0, 0);
    private final MutableInputPosition preposition = new MutableInputPosition(0, 0);

    ReaderSequencer(Reader reader) throws ParserException {
        input = reader;
        next();
    }

    /**
     * Returns current symbol
     */
    @Override
    public int ch() {
        return symbol;
    }

    /**
     * Closes input
     */
    @Override
    public void close() throws IOException {
        input.close();
    }

    /**
     * Returns copy of current position.
     */
    @Override
    public InputPosition copyCurrentPosition() {
        return new ImmutableInputPosition(position);
    }

    /**
     * Reads next symbol, also keeps tracking on current line and position in a
     * row
     */
    @Override
    public void next() throws ParserException {
        try {
            position.setPosition(preposition);
            symbol = input.read();
            preposition.pos++;
            if (symbol == '\r') {
                symbol = input.read();
            }
            if (symbol == '\n') {
                preposition.line++;
                preposition.pos = 0;
            }
            if (symbol == '\t') {
                preposition.pos += 7;
            }
        } catch (IOException io) {
            throw new ParserSequenserException(io.getMessage(), copyCurrentPosition(), -1);
        }
    }

}
