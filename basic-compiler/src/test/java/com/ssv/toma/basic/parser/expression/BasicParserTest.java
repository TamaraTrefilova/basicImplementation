package com.ssv.toma.basic.parser.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.ssv.toma.basic.parser.BasicParser;
import com.ssv.toma.basic.parser.ImmutableInputPosition;
import com.ssv.toma.basic.parser.ParserTokenException;
import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.parser.TokenType;
import com.ssv.toma.basic.parser.Tokenizer;
import com.ssv.toma.basic.runtime.Executor;
import com.ssv.toma.basic.runtime.Operation;
import com.ssv.toma.basic.runtime.PrintOperation;

public class BasicParserTest {

    @Test
    public void basicPerserTest1() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/test1.txt");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        for (Operation op : list) {
            System.out.println(op);
        }
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(12, executor.getRuntime().getValue("a"));
        tokenizer.close();
    }

    @Test
    public void basicPerserTest10() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString( /* @formatter:off */
                    "10 let a = 12\n"+
                    "20 print \"a\"\n"+
                    "30  let b = a*2\n"+
                    "40  let cc = a*3\n"+
                    "if 9>4 goto a 60\n"+
                    " 50   let b=cc\n"+
                    " 60   print \"b\";b\n");
                    /* @formatter:off */
            BasicParser bp = new BasicParser();
            bp.parseOperations(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid token");
            assertEquals(e.getPosition().getLine(), 4);
            assertEquals(e.getPosition().getPos(), 12);
        }
    }

    @Test
    public void basicPerserTest11() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromString( /* @formatter:off */
                    "10 let a = 12\n"+
                    "20 print \"a\"\n"+
                    "30  let b = a*2\n"+
                    "40  let cc = a*3\n"+
                    "   if (5+7*9-10>100)|(6-3>4)=(5-2>1) 60\n"+
                    " 50   let b=cc\n"+
                    " 60   print \"b\";b\n");
                    /* @formatter:off */
            BasicParser bp = new BasicParser();
            bp.parseOperations(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.getReason(), "Invalid token");
            assertEquals(e.getPosition().getLine(), 4);
            assertEquals(e.getPosition().getPos(), 37);
        }
    }

    @Test
       public void basicPerserTest12() throws Exception {
            Tokenizer tokenizer = null;
          try {
               tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
               "20  let b = a*2\n"+
                "     cc= a +b\n"+
                "20   let bb=cc\n"+
                "30 goto 40\n"+
               " 40   print \"cc\";cc\n");
               /* @formatter:off */
       BasicParser bp = new BasicParser();
       List<Operation> list = bp.parseOperations(tokenizer);
       Executor executor = new Executor(list);
       executor.run(list);
               fail("Should throw an exception if one Exception is occured");
            } catch (ParserTokenException e) {
               assertEquals(e.toString(),
                       new ParserTokenException(ParserTokenException.INVALID_TOKEN,
                               new Token(20,TokenType.NUMBER, new ImmutableInputPosition(3,0))).toString());
            }
   }

    @Test
       public void basicPerserTest13() throws Exception {
            Tokenizer tokenizer = null;
            try {
                tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20  let b = a*2\n"+
                "     cc= a +b\n"+
                "   let bb=cc\n"+
                "30 goto a\n"+
                " 40   print \"cc\";cc\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
                fail("Should throw an exception if one Exception is occured");
            } catch (ParserTokenException e) {
                assertEquals(e.toString(),
                        new ParserTokenException(ParserTokenException.INVALID_TOKEN,
                                new Token(20,TokenType.NUMBER, new ImmutableInputPosition(4,8))).toString());
            }
    }


    @Test
    public void basicPerserTest14() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20 print \"a\"\n"+
                "30  let b = a*2\n"+
                "40  let cc = a*3\n"+
                "   if b = 240/(2*5) then goto 60\n"+
                " 50   let b=cc\n"+
                " 60   print \"b\";b\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(24, executor.getRuntime().getValue("b"));
        Operation op = list.get(1);
        assertEquals(PrintOperation.class, op.getClass());
        tokenizer.close();
    }

    @Test
    public void basicPerserTest15() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20 print \"a\"\n"+
                "30  let b = a*2\n"+
                "40  let cc = a*3\n"+
                "   if b = 240/2 then goto 60\n"+
                " 50   let b=cc\n"+
                " 60   print \"b\";b\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(36, executor.getRuntime().getValue("b"));
        Operation op = list.get(1);
        assertEquals(PrintOperation.class, op.getClass());
        tokenizer.close();
    }
    @Test
    public void basicPerserTest16() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20 print \"a\"\n"+
                "30  let b = a*2\n"+
                "40  let cc = a*3\n"+
                "   if (5+7<5)=(7-4=1) then goto 60\n"+
                " 50   let b=cc\n"+
                " 60   print \"b\";b\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(24, executor.getRuntime().getValue("b"));
        Operation op = list.get(1);
        assertEquals(PrintOperation.class, op.getClass());
        tokenizer.close();
    }


    @Test
    public void basicPerserTest17() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20 print \"a\"\n"+
                "30  let b = a*2\n"+
                "40  let cc = a*3\n"+
                "   if (5+7*9-10>100)|(6-3>4)=(5-2>1) then goto 60\n"+
                " 50   let b=cc\n"+
                " 60   print \"b\";b\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(36, executor.getRuntime().getValue("b"));
        Operation op = list.get(1);
        assertEquals(PrintOperation.class, op.getClass());
        tokenizer.close();
    }


    @Test
    public void basicPerserTest18() throws Exception {
            Tokenizer tokenizer = null;
            try {
              tokenizer = Tokenizer.createFromString(
                        /* @formatter:off */
                        "10 let a = 12\n"+
                        "20 print \"a\"\n"+
                        "30  let b = a*2\n"+
                        "40  let cc = a*3\n"+
                        "   if b = 240/(2*5) then a\n"+
                        " 50   let b=cc\n"+
                        " 60   print \"b\";b\n");
                        /* @formatter:off */
                BasicParser bp = new BasicParser();
                bp.parseOperations(tokenizer);
                tokenizer.close();
                fail("Should throw an exception if one Exception is occured");
            } catch (ParserTokenException e) {
                assertEquals(e.getReason(), "Invalid token");
                assertEquals(e.getPosition().getLine(), 5);
                assertEquals(e.getPosition().getPos(),1);
            }
    }

    @Test
    public void basicPerserTest2() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromFile("data/test4.txt");
            BasicParser bp = new BasicParser();
            bp.parseOperations(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.toString(),
                    new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokenizer.token()).toString());
        }
    }

    @Test
    public void basicPerserTest3() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromFile("data/test5.txt");
            BasicParser bp = new BasicParser();
            bp.parseOperations(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.toString(),
                    new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokenizer.token()).toString());
        }
    }

    @Test
    public void basicPerserTest4() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromFile("data/test6.txt");
            BasicParser bp = new BasicParser();
            bp.parseOperations(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.toString(),
                    new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokenizer.token()).toString());
        }
    }

    @Test
    public void basicPerserTest5() throws Exception {
        Tokenizer tokenizer = null;
        try {
            tokenizer = Tokenizer.createFromFile("data/test7.txt");
            BasicParser bp = new BasicParser();
            bp.parseOperations(tokenizer);
            tokenizer.close();
            fail("Should throw an exception if one Exception is occured");
        } catch (ParserTokenException e) {
            assertEquals(e.toString(),
                    new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokenizer.token()).toString());
        }
    }

    @Test
    public void basicPerserTest6() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromFile("data/test8.txt");
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(36, executor.getRuntime().getValue("bb"));
        tokenizer.close();
    }


    @Test
    public void basicPerserTest7() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20 print \"a\"\n"+
                "30  let b = a*2\n"+
                "     cc= a +b\n"+
                "    let bb=cc\n"+
                "    print \"bb\";bb\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(12, executor.getRuntime().getValue("a"));
        Operation op = list.get(1);
        assertEquals(PrintOperation.class, op.getClass());
        tokenizer.close();
    }
    @Test
        public void basicPerserTest8() throws Exception {
            Tokenizer tokenizer = Tokenizer.createFromString(
                   /* @formatter:off */
                   "10 let a = 12\n"+
                   "20 goto 40\n"+
                    "30  let b = a*2\n"+
                    "     cc= a +b\n"+
                    "    let bb=cc\n"+
                    " 40   print a\n");
                    /* @formatter:off */
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
            assertEquals(12, executor.getRuntime().getValue("a"));

        }
    @Test
    public void basicPerserTest9() throws Exception {
        Tokenizer tokenizer = Tokenizer.createFromString(
                /* @formatter:off */
                "10 let a = 12\n"+
                "20  let  b = a*2\n"+
                "    goto 40\n"+
                "    let bb=cc\n"+
                "30   let a = 5\n"+
                " 40   print \"a\";a\n");
                /* @formatter:off */
        BasicParser bp = new BasicParser();
        List<Operation> list = bp.parseOperations(tokenizer);
        Executor executor = new Executor(list);
        executor.run(list);
        executor.getRuntime();
        assertEquals(12, executor.getRuntime().getValue("a"));

    }
        @Test
        public void basicPerserTestFactorial() throws Exception {
            Tokenizer tokenizer = Tokenizer.createFromFile("data/factorial.basic");
            BasicParser bp = new BasicParser();
            List<Operation> list = bp.parseOperations(tokenizer);
            Executor executor = new Executor(list);
            executor.run(list);
            executor.getRuntime();
//            assertEquals(3628800, executor.getRuntime().getValue("a"));
            tokenizer.close();
        }


}
