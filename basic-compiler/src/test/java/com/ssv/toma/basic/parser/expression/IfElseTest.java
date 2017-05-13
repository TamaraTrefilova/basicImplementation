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

public class IfElseTest {

    @Test
    public void ifTest1() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/if.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(0, executor.getRuntime().getValue("i"));
        assertEquals(0, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/if2.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(0, executor.getRuntime().getValue("i"));
        assertEquals(5, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/if3.basic");
        try {
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid operator in condition clause");
            assertEquals(e.getPosition().getLine(), 10);
            assertEquals(e.getPosition().getPos(), 13);
        }
    }

    @Test
    public void ifTest4() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/if4.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(0, executor.getRuntime().getValue("i"));
        assertEquals(5, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest5() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/if5.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(0, executor.getRuntime().getValue("i"));
        assertEquals(5, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest6() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/if6.basic");
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
            assertEquals(e.getPosition().getLine(), 2);
            assertEquals(e.getPosition().getPos(), 4);
        }
    }

    @Test
    public void ifTest7() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/if7.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        // assertEquals(0, executor.getRuntime().getValue("i"));
        // assertEquals(5, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest8() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/if8.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        // assertEquals(0, executor.getRuntime().getValue("i"));
        // assertEquals(5, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

}
