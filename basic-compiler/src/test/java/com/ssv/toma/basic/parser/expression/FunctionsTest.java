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

public class FunctionsTest {
    @Test
    public void ifTest1() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/function1.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        // assertEquals(0, executor.getRuntime().getValue("i"));
        // assertEquals(0, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/function2.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        // assertEquals(0, executor.getRuntime().getValue("i"));
        // assertEquals(0, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }
    @Test
    public void ifTest3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/function3.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        // assertEquals(0, executor.getRuntime().getValue("i"));
        assertEquals(4.0, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }
    @Test
    public void ifTest4() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/function4.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        // assertEquals(0, executor.getRuntime().getValue("i"));
        assertEquals(10.0, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest5() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/function5.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getMessage(), "Number of arguments doesn't correspond to this funcion");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 9);
        }
    }

    @Test
    public void ifTest6() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/function6.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getMessage(), "Number of arguments doesn't correspond to this funcion");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 9);
        }
    }

    @Test
    public void ifTest7() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/function7.basic");
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
            assertEquals(e.getPosition().getPos(), 13);
        }
    }

    @Test
    public void ifTest8() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/function8.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (BasicRuntimeException e) {
            assertEquals(e.getMessage(), "Number of arguments doesn't correspond to this funcion");
            assertEquals(e.getPosition().getLine(), 0);
            assertEquals(e.getPosition().getPos(), 9);
        }
    }

    @Test
    public void ifTest9() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/function9.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        // assertEquals(0, executor.getRuntime().getValue("i"));
        // assertEquals(0, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }
}
