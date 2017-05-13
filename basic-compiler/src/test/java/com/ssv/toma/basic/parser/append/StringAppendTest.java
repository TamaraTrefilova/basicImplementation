package com.ssv.toma.basic.parser.append;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ssv.toma.basic.parser.expression.Node;
import com.ssv.toma.basic.runtime.BasicRuntime;

public class StringAppendTest {
    @Test
    public void throwExceptionWhenAppend() throws Exception {
        BasicRuntime runtime = new BasicRuntime();
        runtime.setValue("abc#", 2);
        runtime.setValue("dc$", 3);
        Node node1 = (Node) Node.createNodeFromString("\"abc\"+abc#");
        assertEquals("abc2", node1.calculateNode(runtime));
    }

    @Test
    public void throwExceptionWhenAppend1() throws Exception {
        BasicRuntime runtime = new BasicRuntime();
        runtime.setValue("abc#", 2);
        runtime.setValue("dc$", 3);
        Node node1 = (Node) Node.createNodeFromString("\"abc\"+\"abc\"");
        assertEquals("abcabc", node1.calculateNode(runtime));
    }

    @Test
    public void throwExceptionWhenAppend2() throws Exception {
        BasicRuntime runtime = new BasicRuntime();
        runtime.setValue("abc#", 2);
        runtime.setValue("dc$", 3);
        Node node1 = (Node) Node.createNodeFromString("\"abc\"+40");
        assertEquals("abc40", node1.calculateNode(runtime));
    }
}
