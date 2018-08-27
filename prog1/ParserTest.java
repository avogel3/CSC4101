import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parseExpTest() {
        InputStream sampleInput = stringToByteStream("#t");
        Scanner in = new Scanner(sampleInput);
        Parser parser = new Parser(in);
        assertThat(parser.parseExp(), instanceOf(BooleanLit.class));
        // TODO: Figure out better way to test behavior here
    }


    private InputStream stringToByteStream(String input) {
        return new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    }
}