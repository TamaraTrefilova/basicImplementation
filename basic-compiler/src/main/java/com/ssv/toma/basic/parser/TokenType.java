package com.ssv.toma.basic.parser;

/**
 * Enum class carrying information about token type
 *
 * @author toma
 *
 */
public enum TokenType {
    EOF("EOF", -1), AS("AS", -1), DIM("DIM", -1), BREAK("BREAK", -1), COMMA("COMMA", -1), CONTINUE("CONTINUE",
            -1), ELSE("ELSE", -1), LOOP("LOOP", -1), UNTIL("UNTIL", -1), STEP("STEP", -1), WHILE("WHILE", -1), DO("DO",
                    -1), ENDIF("ENDIF", -1), END("END", -1), NEXT("NEXT", -1), TO("TO", -1), FOR("FOR", -1), IF("IF",
                            -1), THEN("then", -1), GOTO("goto", -1), SEMICOLON(";", -1), LET("LET", -1), PRINT("PRINT",
                                    -1), NUMBER("Number", -1), STRING("String", -1), TRUE("true", -1), FALSE("false",
                                            -1), VARIABLE("variable", -1), PLUS("+", 1), MINUS("-", 1), DIV("/",
                                                    0), MUL("*", 0), LBR("(", -1), RBR(")", -1), MORE(">", 2), LESS("<",
                                                            2), MOREOREQUAL(">=", 2), LESSOREQUAL("<=", 2), AND("&&",
                                                                    3), OR("||", 4), EQUAL("=", 2), NOTEQUAL("!=", 2);

    private final String typeCode;
    private final int priority;

    /**
     * Constructor - Each token type contains information about type (Number,
     * String and etc) and priority level which is necessary for computation of
     * node value
     *
     * @param code
     *            - String
     * @param priority
     *            - int
     */
    TokenType(String code, int priority) {
        typeCode = code;
        this.priority = priority;
    }

    /**
     * Returns value of priority
     *
     * @return - int
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns type of Token
     */
    @Override
    public String toString() {
        return typeCode;
    }

}