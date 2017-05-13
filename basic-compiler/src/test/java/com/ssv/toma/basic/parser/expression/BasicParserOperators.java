package com.ssv.toma.basic.parser.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

import com.ssv.toma.basic.parser.BasicParser;
import com.ssv.toma.basic.parser.Tokenizer;
import com.ssv.toma.basic.runtime.EndOperation;
import com.ssv.toma.basic.runtime.Executor;
import com.ssv.toma.basic.runtime.Operation;

public class BasicParserOperators {
    @Test
    public void basicParserTestForOperator() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forTest.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(10, executor.getRuntime().getValue("i"));
        assertEquals(9, executor.getRuntime().getValue("b"));
        tokenizer.close();
    }
    @Test
    public void basicParserTestReset() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forTest5.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        // executor.reset(list);
        executor.getRuntime();
        assertEquals(30, executor.getRuntime().getValue("a"));
        assertEquals(33, executor.getRuntime().getValue("i"));
        System.out.println("____________________________________");
        executor.run(list);
        executor.getRuntime();
        assertEquals(30, executor.getRuntime().getValue("a"));
        assertEquals(33, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }

    @Test
    public void basicParserTestStep() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forTest6.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        // assertEquals(10, executor.getRuntime().getValue("i"));
    }

    @Test
    public void basicPerserTestActive() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20 print \"a\"\n"+
                "30  let b = a*2\n"+
                "35 print \"b\";b\n"+
                "40  cc= a +b\n"+
                "45 print \"cc\";cc\n"+
                "50   let b=cc\n"+
                "60 print \"b\";b\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        assertEquals(true, executor.active());
        executor.run(list);
        assertFalse(executor.active());
        System.out.println("Executor is active?:"+executor.active());
        tokenizer.close();
    }

    @Test
    public void basicPerserTestActive2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20 print \"a\"\n"+
                "30  let b = a*2\n"+
                "35 print \"b\";b\n"+
                "40  cc= a +b\n"+
                "45 end\n"+
                "50   let b=cc\n"+
                "60 print \"b\";b\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        assertEquals(true, executor.active());
        executor.run(list);
        assertFalse(executor.active());
        tokenizer.close();
    }
    @Test
    public void basicPerserTestActive3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 10\n"+
                "15 let a = 20\n"+
                "20 print \"a\"\n"+
                "30  end\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.reset(list);
        executor.next();
        assertEquals(10, executor.getRuntime().getValue("a"));
        executor.next();
        assertEquals(20, executor.getRuntime().getValue("a"));
        executor.next();
        assertEquals(20, executor.getRuntime().getValue("a"));
        executor.next();
        assertFalse(executor.active());
        tokenizer.close();
    }

    @Test
    public void basicPerserTestCurrentPointer() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20 print \"a\"\n"+
                "30  let b = a*2\n"+
                "35 print \"b\";b\n"+
                "40  cc= a +b\n"+
                "45 print \"cc\";cc\n"+
                "50   let b=cc\n"+
                "60 print \"b\";b\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        for(int i = 0; i<list.size();i++){
            System.out.println("Pointer: "+executor.currentPointer()+". Index of list: "+i);
            executor.next();
        }
        executor.getRuntime();
        assertEquals(36, executor.getRuntime().getValue("b"));
        tokenizer.close();
    }
    @Test
    public void basicPerserTestEnd() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20 print \"a\"\n"+
                "30  let b = a*2\n"+
                "40  cc= a +b\n"+
                "45 print \"b\";b\n"+
                "55 end\n"+
                "59   let b=cc\n"+
                "60 print \"b\";b\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(24, executor.getRuntime().getValue("b"));
        Operation op = list.get(5);
        assertEquals(EndOperation.class, op.getClass());
        tokenizer.close();
    }
    @Test
    public void basicPerserTestForOperator2() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forTest2.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(10, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }

    @Test
    public void basicPerserTestForOperator3() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forTest3.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(10, executor.getRuntime().getValue("i"));
        tokenizer.close();
    }

    @Test
    public void basicPerserTestForOperator4() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/forTest4.basic");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(1, executor.getRuntime().getValue("i"));
        assertEquals(9, executor.getRuntime().getValue("b"));
        tokenizer.close();
    }

}
