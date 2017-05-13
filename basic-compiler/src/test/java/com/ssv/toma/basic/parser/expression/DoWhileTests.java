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

public class DoWhileTests {

    @Test
    public void basicParserDoLoopUntil() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forDoLoopUntil.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(10, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }

    @Test
    public void basicParserDoLoopUntil2() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromFile("data/forDoLoopUntil2.basic");
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid operator in condition clause");
            assertEquals(e.getPosition().getLine(), 4);
            assertEquals(e.getPosition().getPos(), 5);
        }
    }

    @Test
    public void basicParserDoLoopUntil3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forDoLoopUntil3.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(100, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }
    @Test
    public void basicParserDoLoopWhile() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forDoLoopWhile.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(10, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }

    @Test
    public void basicParserDoLoopWhile2() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromFile("data/forDoLoopWhile2.basic");
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid operator in condition clause");
            assertEquals(e.getPosition().getLine(), 4);
            assertEquals(e.getPosition().getPos(), 5);
        }
    }

    @Test
    public void basicParserDoLoopWhile3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forDoLoopWhile3.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(110, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }
    @Test
    public void basicParserDoLoopWhile4() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromFile("data/forDoLoopWhile4.basic");
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid operator in condition clause");
            assertEquals(e.getPosition().getLine(), 4);
            assertEquals(e.getPosition().getPos(), 5);
        }
    }
    @Test
    public void basicParserDoUntil() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forDoUntil.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(10, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }

    @Test
    public void basicParserDoUntil2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forDoUntil2.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(15, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }

    @Test
    public void basicParserDoWhile() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forDoWhile.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(10, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }
    @Test
    public void basicParserDoWhile2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forDoWhile2.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(3, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }
}
