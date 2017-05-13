package com.ssv.toma.basic.parser.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.ssv.toma.basic.parser.BasicParser;
import com.ssv.toma.basic.parser.ParserTokenException;
import com.ssv.toma.basic.parser.Tokenizer;
import com.ssv.toma.basic.runtime.Executor;
import com.ssv.toma.basic.runtime.Operation;

public class BreakTest {
    @Test
    public void basicParserBreak1() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/break1.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(6, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void basicParserBreak2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/break2.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(5, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void basicParserBreak3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/break3.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(10, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void basicParserBreak4() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/break4.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid token");
            assertEquals(e.getPosition().getLine(), 5);
            assertEquals(e.getPosition().getPos(), 4);
        }
    }

    @Test
    public void basicParserBreak5() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/break5.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid token");
            assertEquals(e.getPosition().getLine(), 4);
            assertEquals(e.getPosition().getPos(), 4);
        }
    }

    @Test
    public void basicParserBreak6() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/break6.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(20, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }
    @Test
    public void basicParserBreak7() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/break7.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid token");
            assertEquals(e.getPosition().getLine(), 7);
            assertEquals(e.getPosition().getPos(), 3);
        }
    }
}
