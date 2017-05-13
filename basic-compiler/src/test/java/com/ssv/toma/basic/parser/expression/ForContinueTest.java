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

public class ForContinueTest {
    @Test
    public void ifTest1() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/ForContinueTest1.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        BasicParser.printList(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(3, executor.getRuntime().getValue("b"));
        assertEquals(3, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/ForContinueTest2.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        BasicParser.printList(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(3, executor.getRuntime().getValue("b"));
        assertEquals(3, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/ForContinueTest3.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        BasicParser.printList(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(1, executor.getRuntime().getValue("b"));
        assertEquals(3, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest4() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/ForContinueTest4.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        BasicParser.printList(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(3, executor.getRuntime().getValue("b"));
        assertEquals(3, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void ifTest5() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/ForContinueTest5.basic");
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
            assertEquals(e.getPosition().getPos(), 3);
        }
    }
}
