
package com.ssv.toma.basic.parser;

/**
 * Public class InputTokenPosition. The class's objects is a part of class
 * Token. Shows a line and position where an object (token is located in a file)
 *
 * @author toma
 *
 */
public class ImmutableInputPosition implements InputPosition {
    private final int line;
    private final int pos;
    // private String fileName;

    public ImmutableInputPosition(InputPosition position) {
        this(position.getLine(), position.getPos());
    }
    /**
     * Public constructor
     *
     * @param pos
     *            - int
     * @param line
     *            - int
     */
    public ImmutableInputPosition(int line, int pos) {
        this.line = line;
        this.pos = pos;
    }
    /**
     * Return true if two objects are equal t each other
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
        ImmutableInputPosition other = (ImmutableInputPosition) obj;
        if (line != other.line) {
            return false;
        }
        if (pos != other.pos) {
            return false;
        }
        return true;
    }

    /**
     * Returns a line where an object are located (token) at this moment in a
     * file
     *
     * @return - int
     */
    @Override
    public final int getLine() {
        return line;
    }

    /**
     * Returns a position in a line where an object are located ( token) at this
     * moment in a file
     *
     * @return - int
     */
    @Override
    public final int getPos() {
        return pos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + line;
        result = prime * result + pos;
        return result;
    }

    /**
     * Prints the object
     */
    @Override
    public String toString() {
        return "[" + line + ", " + pos + "]";
    }

}
