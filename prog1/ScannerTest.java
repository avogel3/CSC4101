import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/*
 * Test functions prepended with 'progX' denote
 * the program requirements that they satisfy.
 *
 */

public class ScannerTest {
    @Test
    public void getNextTokenTestSpecialChars() {
        // Test parens, dots, quotes
        InputStream sampleInput = stringToByteStream("(.)'");
        Scanner in = new Scanner(sampleInput);
        Token first = in.getNextToken();
        assertEquals(Token.LPAREN, first.getType());
        Token second = in.getNextToken();
        assertEquals(Token.DOT, second.getType());
        Token third = in.getNextToken();
        assertEquals(Token.RPAREN, third.getType());
        Token fourth = in.getNextToken();
        assertEquals(Token.QUOTE, fourth.getType());
        Token last = in.getNextToken();
        assertEquals(null, last); // Expect scanning past end of string to be null
    }

    @Test
    public void getNextTokenTestWhiteSpace() {
        InputStream sampleInput = stringToByteStream(" \t\n A");
        Scanner in = new Scanner(sampleInput);
        Token stringA = in.getNextToken();
        assertEquals(Token.IDENT, stringA.getType());
    }

    @Test
    public void getNextTokenTestComments() {
        // Test comments
        InputStream sampleInput = stringToByteStream("; haha this is a comment\n #t");
        Scanner in = new Scanner(sampleInput);
        Token boolTrue = in.getNextToken(); // reusing the boolTrue variable here
        assertEquals(Token.TRUE, boolTrue.getType());
    }

    @Test
    public void getNextTokenTestBooleans() {
        InputStream sampleInput = stringToByteStream("#t #f");
        Scanner in = new Scanner(sampleInput);
        Token boolTrue = in.getNextToken();
        assertEquals(Token.TRUE, boolTrue.getType());
        Token boolFalse = in.getNextToken();
        assertEquals(Token.FALSE, boolFalse.getType());
    }

    @Test
    public void getNextTokenTestIntegerConstants() {
        InputStream sampleInput = stringToByteStream("425");
        Scanner in = new Scanner(sampleInput);
        Token integerConstant = in.getNextToken();
        assertEquals(Token.INT, integerConstant.getType());
        assertEquals(425, integerConstant.getIntVal());
    }

    @Test
    public void getNextTokenTestStringConstants() {
        InputStream sampleInput = stringToByteStream("\"Hello\"");
        Scanner in = new Scanner(sampleInput);
        Token stringConstant = in.getNextToken();
        assertEquals(Token.STRING, stringConstant.getType());
        assertEquals("\"Hello\"", stringConstant.getStrVal());
    }

    @Test
    public void getNextTokenTestIdentifiers() {
       assertEquals(true, false);
    }


    private InputStream stringToByteStream(String input) {
        return new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    }
}
