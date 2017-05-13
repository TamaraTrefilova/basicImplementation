package com.ssv.toma.basic.parser.expression;

import static com.ssv.toma.basic.parser.TokenType.RBR;

import java.util.List;

import com.ssv.toma.basic.parser.BasicParser;
import com.ssv.toma.basic.parser.ParserException;
import com.ssv.toma.basic.parser.ParserTokenException;
import com.ssv.toma.basic.parser.Token;
import com.ssv.toma.basic.parser.TokenType;
import com.ssv.toma.basic.parser.Tokenizer;

/**
 * Parses an expression< transform it from input stream to tree
 *
 * @author toma
 *
 */
public class ExpressionParser {
    // <expression>::=<expressionLog>}
    // <expressionAdd>::=<expressionMul>{<logicPlusMinus><expressionMul>}
    // <operPlusMin>::='+' | '-'
    // <expressionMul>::=<terminal>{<operMulDiv><terminal>}
    // <operMulDiv>::='*' | '/'
    // <terminal>::=<number>|'('<expression>')'

    // Just a comment
    private boolean checkIfCompareOperation(Tokenizer input) {
        return input.token().getTokenType().getPriority() == 2;
    }

    private void checkIfOnce(Tokenizer input, int countMore) throws ParserTokenException {
        if (countMore > 1) {
            throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, input.token());
        }
    }

    /**
     * Calculates the whole expression
     *
     * @param input
     * @return - Node
     * @throws Exception
     */
    public Node expression(Tokenizer input) throws ParserException {
        return expressionStep(input, 4);
    }

    /**
     * Calculates expression by dividing the expression in several parts
     * depending on the operation priority (level)
     *
     * @param input
     * @param level
     * @return Node
     * @throws Exception
     */
    private Node expressionStep(Tokenizer input, int level) throws ParserException {
        int countMore = 0;
        if (level < 0) {
            return terminal(input);
        }
        Node root = expressionStep(input, level - 1);
        while (input.token().getTokenType().getPriority() == level) {
            OperationNode node = new OperationNode(input.token());
            if (checkIfCompareOperation(input)) {
                checkIfOnce(input, ++countMore);
            }
            node.setLeftNode(root);
            input.next();
            Node rnode = expressionStep(input, level - 1);
            node.setRightNode(rnode);
            root = node;
        }
        return root;
    }

    /**
     * Returns a Node. Type of node depends on what part of expression it is
     *
     * @param input
     * @return - Node
     * @throws Exception
     */
    public Node terminal(Tokenizer input) throws ParserException {
        /*
         * если скобка, то вычесляем полностью все выражение, если число, то его
         * возвращаем.
         */
        switch (input.token().getTokenType()) {
        case LBR:
            input.next();
            Node rez = expression(input);
            if (input.token().getTokenType() != RBR) {
                throw new ParserTokenException(ParserTokenException.BRAKET_EXPECTED, input.token());
            }
            input.next();
            return rez;
        case VARIABLE:
            Token var = input.token();
            input.next();
            if (input.token().getTokenType() == TokenType.LBR) {
                List<Node> param = BasicParser.parseListParams(input);
                return new FunctionNode(var, param);
            } else {
                return new VariableNode(var);
            }
        case TRUE:
        case NUMBER:
        case STRING:
        case FALSE:
            rez = new ConstantNode(input.token());
            input.next();
            return rez;
        default:
            throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, input.token());
        }
    }
}
