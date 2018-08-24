// Scanner.java -- the implementation of class Scanner

import java.io.*;

class Scanner {
    private PushbackInputStream in;
    private byte[] buf = new byte[1000];

    public Scanner(InputStream i) {
        in = new PushbackInputStream(i);
    }

    // It would be more efficient if we'd maintain our own input buffer
    // and read characters out of that buffer, but reading individual
    // characters from the input stream is easier.
    private int nextCharacterFromStream() {
        int bite = -1;
        try {
            bite = in.read();
        } catch (IOException e) {
            System.err.println("We fail:" + e.getMessage());
        }
        return bite;
    }

    private Token identifyBoolean(int bite) {
        char ch = (char) bite;
        if (ch == 't')
            return new Token(Token.TRUE);
        else if (ch == 'f')
            return new Token(Token.FALSE);
        else {
            System.err.println("Illegal character '" + (char) ch + "' following #");
            return getNextToken();
        }
    }

    public Token getNextToken() {
        int bite = nextCharacterFromStream();

        // Skip White Space
        if ((bite >= 9 && bite <= 13) || bite == 32) {
            return getNextToken();
        }

        // Skip Comments. Discard from `;` to EOL
        if (bite == 59) {
            while (bite != -1 && bite != 13) {
                bite = nextCharacterFromStream();
            }
        }

        if (bite == -1)
            return null;

        char ch = (char) bite;

        // Special characters
        if (ch == '\'')
            return new Token(Token.QUOTE);
        else if (ch == '(')
            return new Token(Token.LPAREN);
        else if (ch == ')')
            return new Token(Token.RPAREN);
        else if (ch == '.')
            // We ignore the special identifier `...'.
            return new Token(Token.DOT);

        // Boolean constants
        else if (ch == '#') {
            bite = nextCharacterFromStream();
            if (bite == -1) {
                System.err.println("Unexpected EOF following #");
                return null;
            }
            return identifyBoolean(bite);
        }

        // String constants
        else if (ch == '"') {
            // TODO: scan a string into the buffer variable buf
            return new StrToken(buf.toString());
        }

        // Integer constants
        else if (ch >= '0' && ch <= '9') {
            int i = ch - '0';
            // TODO: scan the number and convert it to an integer

            // put the character after the integer back into the input
            // in->putback(ch);
            return new IntToken(i);
        }

        // Identifiers
        else if (ch >= 'A' && ch <= 'Z'
         /* or ch is some other valid first character for an identifier */) {
            // TODO: scan an identifier into the buffer

            // put the character after the identifier back into the input
            // in->putback(ch);
            return new IdentToken(buf.toString());
        }

        // Illegal character
        else {
            System.err.println("Illegal input character '" + (char) ch + '\'');
            return getNextToken();
        }
    }

    ;
}
