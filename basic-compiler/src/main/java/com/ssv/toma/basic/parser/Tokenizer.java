package com.ssv.toma.basic.parser;

import java.io.FileReader;
import java.io.StringReader;

/**
 * Interface Tokenizer allows to implement tokenizer which is able to read
 * tokens from input
 *
 * @author toma
 *
 */
public interface Tokenizer {

    /**
     * Returns DefaultTokenizer which reads tokens from file
     *
     * @param fileName
     * @return DefaultTokenizer
     * @throws Exception
     */
    static Tokenizer createFromFile(String fileName) throws Exception {
        return new DefaultTokenizer(new FileReader(fileName));
    }

    /**
     * Returns DefaultTokenizer which reads tokens from string
     *
     * @param string
     * @return DefaultTokenizer
     * @throws Exception
     */
    static Tokenizer createFromString(String string) throws Exception {
        return new DefaultTokenizer(new StringReader(string));
    }
    /**
     * Accepts tokenType. If the tokeType in current token is not the same as a
     * parameter, an exceptions will throw.
     *
     * @param tokenType
     *            - TokenType
     * @throws Exception
     */
    Object acceptToken(TokenType tokenType) throws ParserException;

    /**
     * Close input
     *
     * @throws Exception
     */
    void close() throws Exception;

    /**
     * Reads next token
     *
     * @throws Exception
     */
    void next() throws ParserException;

    /**
     * Token is created during reading from input
     *
     * @return
     *
     */
    Token token();
}
