import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class ScannerTest {
    @Test
    public void getNextToken() {
        // Test parentheses and dot
        InputStream sampleInput = stringToByteStream("(.)");
        Scanner in = new Scanner(sampleInput);
        Token first = in.getNextToken();
        assertEquals(first.getType(), Token.LPAREN);
        Token second = in.getNextToken();
        assertEquals(second.getType(), Token.DOT);
        Token third = in.getNextToken();
        assertEquals(third.getType(), Token.RPAREN);
        Token last = in.getNextToken();
        assertEquals(last, null); // Expect scanning past end of string to be null

        // Test whitespace and first character of identifier
        sampleInput = stringToByteStream(" \t\n A");
        in = new Scanner(sampleInput);
        Token stringA = in.getNextToken();
        assertEquals(stringA.getType(), Token.IDENT);

        // Test booleans
        sampleInput = stringToByteStream("#t #f");
        in = new Scanner(sampleInput);
        Token boolTrue = in.getNextToken();
        assertEquals(boolTrue.getType(), Token.TRUE);
        Token boolFalse = in.getNextToken();
        assertEquals(boolFalse.getType(), Token.FALSE);

        // Test comments
        sampleInput = stringToByteStream("; haha this is a comment\n #t");
        in = new Scanner(sampleInput);
        boolTrue = in.getNextToken(); // reusing the boolTrue variable here
        assertEquals(boolTrue.getType(), Token.TRUE);
    }


    private InputStream stringToByteStream(String input) {
        return new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    }
}
