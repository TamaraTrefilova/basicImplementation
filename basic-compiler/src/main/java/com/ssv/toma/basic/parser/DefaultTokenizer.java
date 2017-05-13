package com.ssv.toma.basic.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Tokenizer reads file or string, extracts tokens from the file(string) and
 * reads next token.
 *
 * @author toma
 *
 */
class DefaultTokenizer implements Tokenizer {

    private static Map<String, TokenType> keyWordsToTOkenType = new HashMap<>();
    static {
        keyWordsToTOkenType.put("if", TokenType.IF);
        keyWordsToTOkenType.put("as", TokenType.AS);
        keyWordsToTOkenType.put("dim", TokenType.DIM);
        keyWordsToTOkenType.put("then", TokenType.THEN);
        keyWordsToTOkenType.put("goto", TokenType.GOTO);
        keyWordsToTOkenType.put("true", TokenType.TRUE);
        keyWordsToTOkenType.put("false", TokenType.FALSE);
        keyWordsToTOkenType.put("let", TokenType.LET);
        keyWordsToTOkenType.put("print", TokenType.PRINT);
        keyWordsToTOkenType.put("for", TokenType.FOR);
        keyWordsToTOkenType.put("to", TokenType.TO);
        keyWordsToTOkenType.put("next", TokenType.NEXT);
        keyWordsToTOkenType.put("end", TokenType.END);
        keyWordsToTOkenType.put("while", TokenType.WHILE);
        keyWordsToTOkenType.put("do", TokenType.DO);
        keyWordsToTOkenType.put("step", TokenType.STEP);
        keyWordsToTOkenType.put("until", TokenType.UNTIL);
        keyWordsToTOkenType.put("else", TokenType.ELSE);
        keyWordsToTOkenType.put("loop", TokenType.LOOP);
        keyWordsToTOkenType.put("endif", TokenType.ENDIF);
        keyWordsToTOkenType.put("break", TokenType.BREAK);
        keyWordsToTOkenType.put("continue", TokenType.CONTINUE);
    }
    /**
     * Class members
     */
    private final ReaderSequencer sequencer;

    private Token token;

    /**
     * Constructor: a sequenser (input stream) is created and reads next token
     *
     * @param source
     * @throws Exception
     */
    DefaultTokenizer(Reader reader) throws Exception {
        sequencer = new ReaderSequencer(reader);
        next();
    }

    /**
     * Accepts tokenType. If the tokeType in current token is not the same as a
     * parameter, an exceptions will throw.
     */
    @Override
    public Object acceptToken(TokenType tokenType) throws ParserException {
        if (token.getTokenType() != tokenType) {
            throw new ParserTokenException(ParserTokenException.INVALID_TOKEN, token());
        }
        Object obj = token.getValue();
        next();
        return obj;
    }

    /**
     * Closes sequencer
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        sequencer.close();
    }
    private boolean isCharacter(int ch) {
        return Character.isAlphabetic(ch);
    }

    private boolean isDigit(int ch) {
        return Character.isDigit((char) ch);
    }

    private boolean isSpace(int ch) {
        return ch == ' ' || ch == '\t' || ch == 0x0A || ch == 0x0D;
    }

    /**
     * Skips spaces, reads file and depending on type of unit of text (digit,
     * character, and etc) creates a new token, returns the token
     */
    @Override
    public void next() throws ParserException {
        skipSpaces();
        InputPosition position = sequencer.copyCurrentPosition();
        if (sequencer.ch() == -1) {
            token = new Token(TokenType.EOF, sequencer.copyCurrentPosition());
            return;
        }

        if (isDigit(sequencer.ch())) {
            StringBuffer sb = new StringBuffer();
            do {
                sb.append((char) sequencer.ch());
                sequencer.next();
            } while (isDigit(sequencer.ch()));
            if (isCharacter(sequencer.ch())) {
                throw new ParserSequenserException(ParserSequenserException.INVALID_SYMBOL, sequencer);
            }
            int num = Integer.parseInt(sb.toString());
            token = new Token(TokenType.NUMBER, num, position);
            return;
        }

        if (isCharacter(sequencer.ch())) {
            StringBuffer sb = new StringBuffer();
            do {
                sb.append((char) sequencer.ch());
                sequencer.next();
            } while (isCharacter(sequencer.ch()) || isDigit(sequencer.ch()) || sequencer.ch() == '#'
                    || sequencer.ch() == '$');
            String str = sb.toString().toLowerCase();
            TokenType type = keyWordsToTOkenType.get(str);
            if (type != null) {
                token = new Token(type, position);
                return;
            }
            token = new Token(TokenType.VARIABLE, sb.toString(), position);
            return;
        }

        if (sequencer.ch() == '"') {
            sequencer.next();
            StringBuffer sb = new StringBuffer();
            while (sequencer.ch() != '"') {
                if (sequencer.ch() == '\r' || sequencer.ch() == '\n' || sequencer.ch() == -1) {
                    throw new ParserSequenserException(ParserSequenserException.QUOTES_ECPECTED, sequencer);
                }
                sb.append((char) sequencer.ch());
                sequencer.next();
            }
            token = new Token(TokenType.STRING, sb.toString(), position);
            sequencer.next();
            return;
        }
        InputPosition pos = sequencer.copyCurrentPosition();
        switch (sequencer.ch()) {
        case '+':
            token = new Token(TokenType.PLUS, pos);
            sequencer.next();
            return;
        case ';':
            token = new Token(TokenType.SEMICOLON, pos);
            sequencer.next();
            return;
        case '-':
            token = new Token(TokenType.MINUS, pos);
            sequencer.next();
            return;
        case '*':
            token = new Token(TokenType.MUL, pos);
            sequencer.next();
            return;
        case '/':
            sequencer.next();
            if (sequencer.ch() == '/') {
                sequencer.next();
                skipLineComments();
                return;
            }
            if (sequencer.ch() == '*') {
                sequencer.next();
                skipComments();
                return;
            }
            token = new Token(TokenType.DIV, pos);
            return;

        case '(':
            token = new Token(TokenType.LBR, pos);
            sequencer.next();
            return;
        case ')':
            token = new Token(TokenType.RBR, pos);
            sequencer.next();
            return;
        case ',':
            token = new Token(TokenType.COMMA, pos);
            sequencer.next();
            return;
        case '<':
            sequencer.next();
            if (sequencer.ch() == '=') {
                token = new Token(TokenType.LESSOREQUAL, pos);
                sequencer.next();
            } else {
                token = new Token(TokenType.LESS, pos);
            }
            return;
        case '>':
            sequencer.next();
            if (sequencer.ch() == '=') {
                token = new Token(TokenType.MOREOREQUAL, pos);
                sequencer.next();
            } else {
                token = new Token(TokenType.MORE, pos);
            }
            return;
        case '&':
            token = new Token(TokenType.AND, pos);
            sequencer.next();
            return;
        case '|':
            token = new Token(TokenType.OR, pos);
            sequencer.next();
            return;
        case '"':
            token = new Token(TokenType.STRING, pos);
            sequencer.next();
            return;
        case '=':
            token = new Token(TokenType.EQUAL, pos);
            sequencer.next();
            return;
        case '!':
            sequencer.next();
            if (sequencer.ch() == '=') {
                token = new Token(TokenType.NOTEQUAL, pos);
                sequencer.next();
                return;
            }
        }

        throw new ParserSequenserException(ParserSequenserException.INVALID_SYMBOL, sequencer);
    }

    private void skipComments() throws ParserException {
        while (sequencer.ch() != -1) {
            while (sequencer.ch() == '*') {
                sequencer.next();
                if (sequencer.ch() == '/') {
                    sequencer.next();
                    next();
                    return;
                }
            }
            sequencer.next();
        }
        throw new ParserSequenserException(ParserSequenserException.COMMENT_LINE, sequencer);
    }

    private void skipLineComments() throws ParserException {
        while (sequencer.ch() != '\n' && sequencer.ch() != '\r') {
            sequencer.next();
            if (sequencer.ch() == -1) {
                next();
                return;
            }
        }
        sequencer.next();
        next();
        return;
    }

    private void skipSpaces() throws ParserException {
        while (isSpace(sequencer.ch())) {
            sequencer.next();
        }
    }

    /**
     * Returns current token
     */
    @Override
    public Token token() {
        return token;
    }
}
