package com.ssv.toma.basic.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.ssv.toma.basic.parser.expression.ConstantNode;
import com.ssv.toma.basic.parser.expression.ExpressionParser;
import com.ssv.toma.basic.parser.expression.Node;
import com.ssv.toma.basic.parser.expression.OperationNode;
import com.ssv.toma.basic.parser.expression.VariableNode;
import com.ssv.toma.basic.runtime.ArrayCreationOperation;
import com.ssv.toma.basic.runtime.AssignmentOperation;
import com.ssv.toma.basic.runtime.BreakContext;
import com.ssv.toma.basic.runtime.DoWhileUntilContext;
import com.ssv.toma.basic.runtime.ElseContext;
import com.ssv.toma.basic.runtime.EndOperation;
import com.ssv.toma.basic.runtime.ForOperationContext;
import com.ssv.toma.basic.runtime.GotoOperation;
import com.ssv.toma.basic.runtime.IfContext;
import com.ssv.toma.basic.runtime.IfOperation;
import com.ssv.toma.basic.runtime.LoopWhileUntilContext;
import com.ssv.toma.basic.runtime.Operation;
import com.ssv.toma.basic.runtime.OperationContext;
import com.ssv.toma.basic.runtime.PrintOperation;

/**
 * Public class reads from input and fills list of Operations with variables and
 * their values
 *
 * @author toma
 *
 */

public class BasicParser {

    /**
     * Interface contains one method 'parse' that reads tokens from
     * input,creates Operation's objects, and puts them into list
     *
     * @author toma
     *
     */
    static interface OperParser {
        void parse(Tokenizer tokens, List<Operation> list, Stack<OperationContext> stack) throws ParserException;
    }

    /**
     * Static map holds as a key a TokenType's object and as a value an
     * OperParser's object.
     *
     */
    static Map<TokenType, OperParser> operationParsers = new HashMap<>();

    /**
     * Static initialization block that initializes objects implementing
     * OperParser interface. Each object creates on Operation's class objects
     * and puts them into list of Operation's
     */
    static {
        /*
         * Creating an AssignmentOperation object for Let operator
         *
         */
        operationParsers.put(TokenType.LET, (tokens, list, stack) -> {
            tokens.next();
            if (tokens.token().getTokenType() != TokenType.VARIABLE) {
                throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokens.token());
            }

            parseAssignment(tokens, list);
        });

        /*
         * Creating an AssignmentOperation object
         *
         */
        operationParsers.put(TokenType.VARIABLE, new OperParser() {
            @Override
            public void parse(Tokenizer tokens, List<Operation> list, Stack<OperationContext> stack)
                    throws ParserException {
                parseAssignment(tokens, list);
            }
        });

        /*
         * Reading FOR operator from input and creating an AssignmentOperation
         * and IfOperation
         *
         */
        operationParsers.put(TokenType.FOR, (tokens, list, stack) -> {
            /*
             * creating an AssignmentOperation
             *
             */
            tokens.next();
            Token varToken = tokens.token();
            tokens.acceptToken(TokenType.VARIABLE);
            tokens.acceptToken(TokenType.EQUAL);
            Node node1 = new ExpressionParser().expression(tokens);
            list.add(new AssignmentOperation((String) varToken.getValue(), node1));
            tokens.acceptToken(TokenType.TO);
            Node node2 = new ExpressionParser().expression(tokens);
            InputPosition inputPos = new ImmutableInputPosition(0, 0);
            VariableNode varNode = new VariableNode(varToken);
            /*
             * Now we are creating condition for FOR operator. When Step is
             * provided, creates a node with a step's value
             */
            Node step = null;
            if (tokens.token().tokenType == TokenType.STEP) {
                tokens.next();
                step = new ExpressionParser().expression(tokens);
            } else {
                /*
                 * When Step is not provided, Step = +
                 */
                ConstantNode node = new ConstantNode(new Token(TokenType.NUMBER, 1, inputPos));
                step = node;
            }
            OperationNode operNode = new OperationNode(new Token(TokenType.EQUAL, inputPos));
            operNode.setLeftNode(varNode);
            operNode.setRightNode(node2);
            IfOperation ifOper = new IfOperation(operNode, -1);
            stack.push(new ForOperationContext(varToken, list.size(), ifOper, step));
            list.add(ifOper);
        });

        /*
         * Reading Do operator from input and creating IfOperation
         */
        operationParsers.put(TokenType.DO, (tokens, list, stack) -> {
            tokens.next();
            TokenType token = tokens.token().getTokenType();
            boolean isWhile = token == TokenType.WHILE;
            if (!isWhile && token != TokenType.UNTIL) {
                stack.push(new LoopWhileUntilContext(list.size()));
            } else {
                tokens.next();
                Node nodeForCondtion = new ExpressionParser().expression(tokens);
                if (isWhile) {
                    nodeForCondtion = reverseNode(nodeForCondtion);
                }
                IfOperation ifOper = new IfOperation(nodeForCondtion, -1);
                stack.push(new DoWhileUntilContext(list.size(), ifOper));
                list.add(ifOper);
            }

        });

        /*
         * Reading Dim operator from input and creating ArrayCreationOperation
         */
        operationParsers.put(TokenType.DIM, (tokens, list, stack) -> {
            tokens.next();
            String name = (String) tokens.acceptToken(TokenType.VARIABLE);
            List<Node> listNode = parseListParams(tokens);
            if (listNode.size() == 0) {
                throw new ParserTokenException(ParserTokenException.EXPRESSION_IS_NOT_VALID,
                        new Token(TokenType.STRING, name, new ImmutableInputPosition(0, 0)));
            }
            tokens.acceptToken(TokenType.AS);
            String typeName = (String) tokens.acceptToken(TokenType.VARIABLE);
            ArrayCreationOperation arrayOper = new ArrayCreationOperation(name, typeName, listNode);
            list.add(arrayOper);
        });
        /*
         * Reading Break operator from input and creating GotoOperation
         */
        operationParsers.put(TokenType.BREAK, (tokens, list, stack) -> {

            for (int i = stack.size() - 1; i >= 0; i--) {
                if (stack.elementAt(i) instanceof BreakContext) {
                    tokens.next();
                    BreakContext context = (BreakContext) stack.elementAt(i);
                    GotoOperation gotoOper = new GotoOperation(-1);
                    list.add(gotoOper);
                    context.getBrakes().add(gotoOper);
                    return;
                }
            }
            throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokens.token());
        });

        /*
         * Reading Continue operator from input and creating GotoOperation
         */
        operationParsers.put(TokenType.CONTINUE, (tokens, list, stack) -> {
            for (int i = stack.size() - 1; i >= 0; i--) {
                if (stack.elementAt(i) instanceof BreakContext) {
                    tokens.next();
                    BreakContext context = (BreakContext) stack.elementAt(i);
                    GotoOperation gotoOper = new GotoOperation(-1);
                    /*
                     * If continue occurs in ForOperation, created GotoOperator
                     * is being added into a list which in a context object for
                     * this occasion
                     *
                     */
                    if (context instanceof ForOperationContext) {
                        context.getForContiniue().add(gotoOper);
                    } else {
                        gotoOper.setIdx(context.getIdx());
                    }
                    list.add(gotoOper);
                    return;
                }
            }
            throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokens.token());
        });

        /*
         * Reading Loop operator and creating GotoOperation
         */
        operationParsers.put(TokenType.LOOP, (tokens, list, stack) -> {
            tokens.next();
            TokenType token = tokens.token().getTokenType();
            boolean isUntil = token == TokenType.UNTIL;
            if (!isUntil && token != TokenType.WHILE) {
                DoWhileUntilContext context = popContext(tokens, DoWhileUntilContext.class, stack);
                List<GotoOperation> gotoList = context.getBrakes();
                for (int i = 0; i < gotoList.size(); i++) {
                    gotoList.get(i).setIdx(list.size());
                }
                GotoOperation gotoOper = new GotoOperation(-1);
                gotoOper.setIdx(context.getIdx());
                list.add(gotoOper);
                context.getOper().setIdx(list.size());
            } else {
                LoopWhileUntilContext context = popContext(tokens, LoopWhileUntilContext.class, stack);
                tokens.next();
                Node nodeForCondtion = new ExpressionParser().expression(tokens);
                if (isUntil) {
                    nodeForCondtion = reverseNode(nodeForCondtion);
                }
                IfOperation ifOper = new IfOperation(nodeForCondtion, -1);
                ifOper.setIdx(context.getIdx());
                list.add(ifOper);
                GotoOperation gotoOper = new GotoOperation(-1);
                gotoOper.setIdx(list.size() + 1);
                list.add(gotoOper);
            }
        });

        /*
         * Reading Next operator from input
         *
         */
        operationParsers.put(TokenType.NEXT, (tokens, list, stack) -> {
            tokens.next();
            /*
             * Retrieve from stack a Token's variable and check if the variable
             * is identical with variable from input.
             *
             */
            ForOperationContext context = popContext(tokens, ForOperationContext.class, stack);
            Node varNode = new ExpressionParser().expression(tokens);
            InputPosition inputPos = new ImmutableInputPosition(0, 0);
            VariableNode vn = (VariableNode) varNode;
            if (!vn.getToken().getValue().equals(context.getVariableToken().getValue())) {
                throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, vn.getToken());
            }

            /*
             * Now we are performing an increment of a value by 1.
             *
             */
            OperationNode operNode = new OperationNode(new Token(TokenType.PLUS, inputPos));
            operNode.setLeftNode(varNode);
            operNode.setRightNode(context.getStep());
            /*
             * Now we are creating an AssignmentOperation with new varaible's
             * value
             *
             */
            list.add(new AssignmentOperation((String) vn.getToken().getValue(), operNode));
            /*
             * Creating a GotoOperation where we put an idx of list. When
             * condition is still working our code will be executed from For
             * operator again
             *
             */
            List<GotoOperation> gotoList = context.getForContiniue();
            for (int i = 0; i < gotoList.size(); i++) {
                gotoList.get(i).setIdx(list.size() - 1);
            }
            GotoOperation gotoOper = new GotoOperation(-1);
            gotoOper.setIdx(context.getIdx());
            list.add(gotoOper);
            context.getOper().setIdx(list.size());
        });

        /*
         * Creating an PrintOperation
         *
         */
        operationParsers.put(TokenType.PRINT, (tokens, list, stack) -> {
            tokens.next();
            String variable = null;
            Node node = null;
            if (tokens.token().getTokenType() == TokenType.STRING) {
                variable = (String) tokens.token().getValue();
                tokens.next();
                if (tokens.token().getTokenType() == TokenType.SEMICOLON) {
                    tokens.next();
                    node = new ExpressionParser().expression(tokens);
                }
            } else {
                node = new ExpressionParser().expression(tokens);
            }
            list.add(new PrintOperation(variable, node));
        });
        /*
         * Creating EndOperation
         */
        operationParsers.put(TokenType.END, (tokens, list, stack) -> {
            tokens.next();
            list.add(new EndOperation());
        });

        /*
         * Creating an IfOperation
         *
         */
        operationParsers.put(TokenType.IF, (tokens, list, stack) -> {
            tokens.next();
            IfOperation ifOper = null;
            Node node = new ExpressionParser().expression(tokens);
            if (tokens.token().getTokenType() == TokenType.THEN) {
                tokens.next();
                if (tokens.token().getTokenType() != TokenType.GOTO) {
                    node = reverseNode(node);
                    ifOper = new IfOperation(node, -1);
                    stack.push(new IfContext(ifOper));
                } else {
                    ifOper = proceedLabel(tokens, node);
                }
            } else if (tokens.token().getTokenType() == TokenType.GOTO) {
                ifOper = proceedLabel(tokens, node);
            } else {
                throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokens.token());
            }
            list.add(ifOper);
        });

        /*
         * Creating a GotoOperation
         */
        operationParsers.put(TokenType.ELSE, (tokens, list, stack) -> {
            tokens.next();
            IfContext context = popContext(tokens, IfContext.class, stack);
            GotoOperation goToOper = new GotoOperation(-1);
            list.add(goToOper);
            context.getOper().setIdx(list.size());
            stack.push(new ElseContext(goToOper));
        });

        /*
         * Depending on a type of OperationContext sets an idx into an
         * GotoOperation or IfOperation object
         */
        operationParsers.put(TokenType.ENDIF, (tokens, list, stack) -> {
            tokens.next();
            OperationContext context = stack.pop();
            if (context.getClass() == ElseContext.class) {
                ((ElseContext) context).getOper().setIdx(list.size());
            } else if (context.getClass() == IfContext.class) {
                ((IfContext) context).getOper().setIdx(list.size());
            } else {
                throw new ParserTokenException(ParserTokenException.INVALID_OPERATOR, tokens.token());
            }
        });

        /*
         * Creating a GotoOperaton
         *
         */
        operationParsers.put(TokenType.GOTO, (tokens, list, stack) -> {
            tokens.next();
            list.add(new GotoOperation((Integer) tokens.acceptToken(TokenType.NUMBER)));
        });
    }

    /**
     * Creates an AssignmentOperation object from token's stream
     *
     * @param tokens
     *            - Tokenizer
     * @param list
     *            - List of Operation's objects
     * @throws ParserException
     */
    public static void parseAssignment(Tokenizer tokens, List<Operation> list) throws ParserException {
        String variable = (String) tokens.token().getValue();
        tokens.next();
        List<Node> listNode = null;
        if (tokens.token().tokenType == TokenType.LBR) {
            listNode = parseListParams(tokens);
        }
        tokens.acceptToken(TokenType.EQUAL);
        Node node = new ExpressionParser().expression(tokens);
        list.add(new AssignmentOperation(variable, node, listNode));
    }

    /***
     * Reads and parses a parameters from input and puts them into a list of
     * nodes
     *
     * @param tokens
     *            - input
     * @return List of nodes
     * @throws ParserException
     */
    public static List<Node> parseListParams(Tokenizer tokens) throws ParserException {
        List<Node> listNode = new ArrayList<>();
        tokens.acceptToken(TokenType.LBR);
        if (tokens.token().tokenType != TokenType.RBR) {
            Node node = new ExpressionParser().expression(tokens);
            listNode.add(node);
            while (tokens.token().getTokenType() == TokenType.COMMA) {
                tokens.next();
                node = new ExpressionParser().expression(tokens);
                listNode.add(node);
            }
        }
        tokens.acceptToken(TokenType.RBR);
        return listNode;
    }

    /**
     * Retrieves an OperationContext's object from stack and checks if the
     * object is suitable for a current condition clause
     *
     * @param tokens
     *            - Tokenizer
     * @param expectedContext
     *            - OpeartionContext's interface implementing Class
     * @param stack
     *            - Stack of OperationContext's objects
     * @return - an OperationContext's object
     * @throws ParserTokenException
     */
    @SuppressWarnings("unchecked")
    private static <T extends OperationContext> T popContext(Tokenizer tokens, Class<T> expectedContext,
            Stack<OperationContext> stack) throws ParserTokenException {
        OperationContext context = stack.pop();
        if (context.getClass() != expectedContext) {
            throw new ParserTokenException(ParserTokenException.INVALID_OPERATOR, tokens.token());
        }
        return (T) context;
    }

    /**
     * Prints a list of Operators from a program
     *
     * @param list
     */
    public static void printList(List<Operation> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("" + i + " " + list.get(i));
        }
    }

    /**
     * Checks the next token and if it is a Number, creates an IfOpearation
     * object. Otherwise throws an exception
     *
     * @param tokens
     *            - Tokenizer
     * @param node
     *            - node
     * @return IfOperation object
     * @throws ParserException
     */
    public static IfOperation proceedLabel(Tokenizer tokens, Node node) throws ParserException {
        IfOperation ifOper;
        tokens.next();
        ifOper = new IfOperation(node, (Integer) tokens.acceptToken(TokenType.NUMBER));
        return ifOper;
    }

    /**
     * Creates a node from an input expression. Condition in the node is being
     * kept false depends on an operator.
     *
     * @param nodeForCondtion
     *            - node
     * @return
     */
    public static Node reverseNode(Node nodeForCondtion) {
        InputPosition inputPos = new ImmutableInputPosition(0, 0);
        ConstantNode boolNode = new ConstantNode(new Token(TokenType.FALSE, inputPos));
        OperationNode opNode = new OperationNode(new Token(TokenType.EQUAL, inputPos));
        opNode.setLeftNode(nodeForCondtion);
        opNode.setRightNode(boolNode);
        nodeForCondtion = opNode;
        return nodeForCondtion;
    }

    /**
     * Skips Then operator and checks if wrong operators after Then (throws
     * exception if any of them are occurred)
     *
     * @param tokens
     *            Tokenizer
     * @throws ParserException
     * @throws ParserTokenException
     */
    public static void skipThen(Tokenizer tokens) throws ParserException, ParserTokenException {
        if (tokens.token().getTokenType() == TokenType.THEN) {
            tokens.next();
            if (tokens.token().getTokenType() == TokenType.VARIABLE) {
                throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokens.token());
            }
        } else if (tokens.token().getTokenType() == TokenType.NUMBER) {
            throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokens.token());
        }
    }

    /**
     * Reads label from input
     *
     * @param tokens
     *            Tokenizer
     * @return - int
     * @throws Exception
     */
    private Integer parseLabel(Tokenizer tokens) throws ParserException {
        Integer label = null;
        if (tokens.token().tokenType == TokenType.NUMBER) {
            label = (Integer) tokens.token().getValue();
            tokens.next();
        }
        return label;
    }

    /**
     * Methods accepts correct token from input and adds its variable and its
     * value into a list
     *
     * @param tokens
     *            - Tokenizer
     * @param list
     *            - List<Operations>
     * @throws Exception
     */
    public void parseOperation(Tokenizer tokens, List<Operation> list, Stack<OperationContext> stack)
            throws ParserException {
        OperParser parser = operationParsers.get(tokens.token().getTokenType());
        if (parser == null) {
            throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, tokens.token());
        }
        parser.parse(tokens, list, stack);
    }

    /**
     * Fills out a list of Operation reading from input
     *
     * @param tokens
     *            - Tokenizer
     * @return list of Operation
     * @throws Exception
     */
    public List<Operation> parseOperations(Tokenizer tokens) throws ParserException {

        List<Operation> list = new ArrayList<>();
        Map<Integer, Integer> references = new HashMap<>();
        Stack<OperationContext> stack = new Stack<>();
        while (tokens.token().tokenType != TokenType.EOF) {
            Token labelToken = tokens.token();
            Integer label = parseLabel(tokens);
            if (label != null) {
                if (!references.containsKey(label)) {
                    references.put(label, list.size());
                } else {
                    throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, labelToken);
                }
            }
            parseOperation(tokens, list, stack);
        }
        for (Operation op : list) {
            op.setupLinks(references);
        }
        return list;
    }
}
