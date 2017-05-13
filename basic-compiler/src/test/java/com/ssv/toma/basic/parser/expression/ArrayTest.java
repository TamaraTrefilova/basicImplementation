package com.ssv.toma.basic.parser.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.ssv.toma.basic.parser.BasicParser;
import com.ssv.toma.basic.parser.ParserTokenException;
import com.ssv.toma.basic.parser.Tokenizer;
import com.ssv.toma.basic.runtime.BasicRuntimeException;
import com.ssv.toma.basic.runtime.Executor;
import com.ssv.toma.basic.runtime.Operation;

public class ArrayTest {
    @Test
    public void arrayTest1() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest1.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(10, executor.getRuntime().getValue("b"));
        tokenizer.close();
    }

    @Test
    public void arrayTest10() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest10.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(15, executor.getRuntime().getValue("b"));
        assertEquals(10, executor.getRuntime().getValue("c"));
        tokenizer.close();
    }

    @Test
    public void arrayTest11() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest11.basic");
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
            assertEquals(e.getPosition().getLine(), 1);
            assertEquals(e.getPosition().getPos(), 0);
        }
    }
    @Test
    public void arrayTest12() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest12.basic");
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
            assertEquals(e.getPosition().getLine(), 1);
            assertEquals(e.getPosition().getPos(), 4);
        }
    }
    @Test
    public void arrayTest13() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest13.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(20, executor.getRuntime().getValue("b"));
        assertEquals("mama", executor.getRuntime().getValue("c"));
        tokenizer.close();
    }

    @Test
    public void arrayTest14() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest14.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(20, executor.getRuntime().getValue("b"));
        assertEquals(18, executor.getRuntime().getValue("d"));
        assertEquals(6, executor.getRuntime().getValue("g"));
        tokenizer.close();
    }

    @Test
    public void arrayTest15() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest15.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(20, executor.getRuntime().getValue("b"));
        assertEquals(13, executor.getRuntime().getValue("d"));
        assertEquals(6, executor.getRuntime().getValue("g"));
        tokenizer.close();
    }

    @Test
    public void arrayTest16() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest16.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        // assertEquals(20, executor.getRuntime().getValue("b"));
        // assertEquals("mama", executor.getRuntime().getValue("c"));
        tokenizer.close();
    }

    @Test
    public void arrayTest17() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest17.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Expression is not valid. Wrong number of parameters");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 0);
        }
    }

    @Test
    public void arrayTest18() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest18.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getReason(), "Number of arguments doesn't correspond to this array's size");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 0);
        }
    }

    @Test
    public void arrayTest19() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest19.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getReason(), "Number of arguments doesn't correspond to this array's size");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 0);
        }
    }

    @Test
    public void arrayTest2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest2.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(10, executor.getRuntime().getValue("b"));
        tokenizer.close();
    }

    @Test
    public void arrayTest20() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest20.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getReason(), "Number of arguments doesn't correspond to this array's size");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 0);
        }
    }
    @Test
    public void arrayTest3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest3.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(12, executor.getRuntime().getValue("b"));
        tokenizer.close();
    }
    @Test
    public void arrayTest4() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest4.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(15, executor.getRuntime().getValue("b"));
        assertEquals(10, executor.getRuntime().getValue("c"));
        tokenizer.close();
    }
    @Test
    public void arrayTest5() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest5.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(21, executor.getRuntime().getValue("b"));
        assertEquals(15, executor.getRuntime().getValue("c"));
        tokenizer.close();
    }

    @Test
    public void arrayTest6() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest6.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(21, executor.getRuntime().getValue("b"));
        assertEquals(15, executor.getRuntime().getValue("c"));
        tokenizer.close();
    }
    @Test
    public void arrayTest7() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest7.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getReason(), "Number of arguments doesn't correspond to this array's size");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 0);
        }
    }

    @Test
    public void arrayTest8() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest8.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getReason(), "Number of arguments doesn't correspond to this array's size");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 0);
        }
    }

    @Test
    public void arrayTest9() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/arrayTest9.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getReason(), "The variable doesn't have a value");
            assertEquals(e.getPosition().getLine(), 1);
            assertEquals(e.getPosition().getPos(), 14);
        }
    }
}
