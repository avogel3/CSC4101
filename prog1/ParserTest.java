import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;

public class ParserTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testParseExpBooleans() {
        Parser parser;

        parser = stubSampleParseInput("#f");
        assertThat(parser.parseExp(), instanceOf(BooleanLit.class));

        parser = stubSampleParseInput("#t");
        assertThat(parser.parseExp(), instanceOf(BooleanLit.class));
    }

    @Test
    public void testParseExpLiteral() {
        Parser parser;

        parser = stubSampleParseInput("99");
        assertThat(parser.parseExp(), instanceOf(IntLit.class));

        parser = stubSampleParseInput("\"Hello\"");
        assertThat(parser.parseExp(), instanceOf(StrLit.class));
    }

    @Test
    public void testParseExpIdent() {
        Parser parser = stubSampleParseInput("A");
        assertThat(parser.parseExp(), instanceOf(Ident.class));
    }

    @Test
    public void testParseExpQuote() {
        Parser parser;

        parser = stubSampleParseInput("'15");
        assertThat(parser.parseExp(), instanceOf(Cons.class));

        parser = stubSampleParseInput("'a");
        assertThat(parser.parseExp(), instanceOf(Cons.class));
    }

    @Test
    public void testParseExpRest() {
        Parser parser;

        parser = stubSampleParseInput("'()");
        assertThat(parser.parseExp(), instanceOf(Cons.class));

        parser = stubSampleParseInput("'(1 2 4)");
        assertThat(parser.parseExp(), instanceOf(Cons.class));
    }

    @Test
    public void testParseExpNumber() {
        parseAndPrintInput("123");
        assertEquals(outputStreamCaptor.toString().trim(), "123");
    }
    
    @Test
    public void testParseExpString() {
        parseAndPrintInput("\"some string\"\n");
        assertEquals(outputStreamCaptor.toString().trim(), "\"some string\"");
    }
    
    @Test
    public void testParseExpTruthy() {
        parseAndPrintInput("#t");
        assertEquals(outputStreamCaptor.toString().trim(), "#t");
    }
    
    @Test
    public void testParseExpFalsey() {
        parseAndPrintInput("#f");
        assertEquals(outputStreamCaptor.toString().trim(), "#f");
    }
    
    @Test
    public void testParseExpEmpty() {
        parseAndPrintInput("()");
        assertEquals(outputStreamCaptor.toString().trim(), "()");
    }
    
    @Test
    public void testParseExpSingleLineWithComment() {
        parseAndPrintInput("(+2 3) ;a comment\n");
        assertEquals(outputStreamCaptor.toString().trim(), "(+ 2 3)");
    }
    
    @Test
    public void testParseExpQuotedLiteral() {
        parseAndPrintInput("'foo");
        assertEquals(outputStreamCaptor.toString().trim(), "'foo");
    }
    
    @Test
    public void testParseExpQuotedList() {
        parseAndPrintInput("'(1 2 3)");
        assertEquals(outputStreamCaptor.toString().trim(), "'(1 2 3)");
    }
    
    @Test
    public void testParseExpSingleLineDefine() {
        parseAndPrintInput("(define x 0)\n");
        assertEquals(outputStreamCaptor.toString().trim(), "(define x 0)");
    }
    
    @Test
    public void testParseExpSingleLineNestedList() {
        parseAndPrintInput("(set! x (+ 2 3))");
        assertEquals(outputStreamCaptor.toString().trim(), "(set! x (+ 2 3))");
    }
    
    @Test
    public void testParseExpNestedBegin() {
        String input = "(begin (set! x 6) (set! y 7) (* x y))";
        parseAndPrintInput(input);

        String expectedOutput = "(begin\n" +
        "    (set! x 6)\n" + 
        "    (set! y 7)\n" + 
        "    (* x y)\n" +
        ")";
        // NOTE: Non-unix platorms use a newline of `\r\n`
        assertEquals(outputStreamCaptor.toString().trim().replaceAll("\r\n", "\n"), expectedOutput);
    }

    @Test
    public void testParseExpNestedDefine() {
        String input = "(define (fac n) (if (= n 0) 1 (* n (fac (- n 1)))))";
        parseAndPrintInput(input);

        String expectedOutput = "(define (fac n)\n" +
        "    (if (= n 0)\n" +
        "        1\n" +
        "        (* n (fac (- n 1)))\n" +
        "    )\n" +
        ")";
        assertEquals(outputStreamCaptor.toString().trim().replaceAll("\r\n", "\n"), expectedOutput);
    }

    private void parseAndPrintInput(String input) {
        Parser parser = stubSampleParseInput(input);
        // NOTE: This a copy pasta from Main.java.
        // TODO: Probs should be a method on parser
        Node root = parser.parseExp();
        while (root != null) {
            root.print(0);
            root = parser.parseExp();
        }
    }

    private Parser stubSampleParseInput(String input) {
        InputStream sampleInput = stringToByteStream(input);
        Scanner in = new Scanner(sampleInput);
        return new Parser(in);
    }

    private InputStream stringToByteStream(String input) {
        return new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    }
}
