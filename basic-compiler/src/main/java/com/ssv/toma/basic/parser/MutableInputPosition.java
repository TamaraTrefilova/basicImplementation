package com.ssv.toma.basic.parser;

/**
 * class MutInputPosition. The class's objects is a part of class
 * ReaderSequencer. Shows a line and position where an object (sequencer is
 * located in a file)
 *
 * @author toma
 *
 */
class MutableInputPosition implements InputPosition {
    int line;
    int pos;
    // private String fileName;

    /**
     * Public constructor
     *
     * @param pos
     *            - int
     * @param line
     *            - int
     */
    MutableInputPosition(int line, int pos) {
        this.line = line;
        this.pos = pos;
    }
    /**
     * Returns a line where an object are located (sequencer) at this moment in
     * a file
     *
     * @return - int
     */
    @Override
    public final int getLine() {
        return line;
    }
    /**
     * Returns a position in a line where an object are located (sequencer) at
     * this moment in a file
     *
     * @return - int
     */
    @Override
    public final int getPos() {
        return pos;
    }

    /**
     * Sets values to a MutInputPosition's object from another
     * MutInputPosition's object
     *
     * @param position
     *            - MutInputPosition's object
     */
    public void setPosition(MutableInputPosition position) {
        line = position.getLine();
        pos = position.getPos();
    }
}
