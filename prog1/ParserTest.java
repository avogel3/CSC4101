import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class ParserTest {

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
        assertThat(parser.parseExp(), instanceOf(IntLit.class));

        parser = stubSampleParseInput("'a");
        assertThat(parser.parseExp(), instanceOf(Ident.class));
    }

    @Test
    public void testParseExpRest() {
        // TODO: ( rest
        Parser parser = stubSampleParseInput("(+ 1 2)");
        // assertThat(parser.parseExp(), )
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
