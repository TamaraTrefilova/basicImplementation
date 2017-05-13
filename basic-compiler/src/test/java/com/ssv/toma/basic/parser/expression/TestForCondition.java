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

public class TestForCondition {

    @Test
    public void basicParserDoLoopUntil1() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/TestForCondition1.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(6, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void basicParserDoLoopUntil2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/TestForCondition2.basic");
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
            assertEquals(e.getPosition().getLine(), 9);
            assertEquals(e.getPosition().getPos(), 5);
        }
    }

    @Test
    public void basicParserDoLoopUntil3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/TestForCondition3.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();

        tokenizer.close();
    }
}
