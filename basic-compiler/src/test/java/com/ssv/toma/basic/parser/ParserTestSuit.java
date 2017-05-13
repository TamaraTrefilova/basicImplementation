package com.ssv.toma.basic.parser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ssv.toma.basic.parser.append.StringAppendTest;
import com.ssv.toma.basic.parser.expression.ArrayTest;
import com.ssv.toma.basic.parser.expression.BasicParserOperators;
import com.ssv.toma.basic.parser.expression.BasicParserTest;
import com.ssv.toma.basic.parser.expression.BreakTest;
import com.ssv.toma.basic.parser.expression.DoWhileTests;
import com.ssv.toma.basic.parser.expression.ExpressionLevelTest;
import com.ssv.toma.basic.parser.expression.ForContinueTest;
import com.ssv.toma.basic.parser.expression.FunctionsTest;
import com.ssv.toma.basic.parser.expression.IfElseTest;
import com.ssv.toma.basic.parser.expression.NodeEqualsTest;
import com.ssv.toma.basic.parser.variablenode.VariableNodeTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TokenizerTest.class, TokenizerFromFileSkipComment.class, ParserEqualsTest.class,
        NodeEqualsTest.class, TokenEqualsTest.class, StringAppendTest.class, ExpressionLevelTest.class,
        VariableNodeTest.class, BasicParserTest.class, BasicParserOperators.class, DoWhileTests.class, IfElseTest.class,
        BreakTest.class, ForContinueTest.class, FunctionsTest.class, ArrayTest.class })

public class ParserTestSuit {

}
