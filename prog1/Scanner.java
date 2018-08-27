// Scanner.java -- the implementation of class Scanner

import java.io.*;

class Scanner {
    private PushbackInputStream in;
    private byte[] buf = new byte[1000];

    public Scanner(InputStream i) {
        in = new PushbackInputStream(i);
    }

    // Return the bite character in ASCII form
    // - OR - return -1
    private int nextCharacterFromStream() {
        int bite = -1;
        try {
            bite = in.read();
        } catch (IOException e) {
            System.err.println("We fail:" + e.getMessage());
        }
        return bite;
    }

    private int readToEndOfLine(int bite) {
        while (bite != -1 && bite != 10) {
            bite = nextCharacterFromStream();
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

    private String newStringFromBuffer(int stringLength) {
        byte[] newString = new byte[stringLength];
        System.arraycopy(buf,0, newString,0, stringLength);
        return new String(newString);
    }

    public Token getNextToken() {
        int bite = nextCharacterFromStream();

        // Skip White Space
        if ((bite >= 9 && bite <= 13) || bite == 32) {
            return getNextToken();
        }

        // Skip Comments. Discard from `;` to EOL
        if (bite == 59) {
            bite = readToEndOfLine(bite);
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
            bite = nextCharacterFromStream();
            ch = (char) bite;
            int charCount = 0;
            while(ch != '"' && bite != -1) {
                buf[charCount] = (byte) ch;
                bite = nextCharacterFromStream();
                ch = (char) bite;
                charCount++;
            }
            String stringConstant = newStringFromBuffer(charCount);
            return new StrToken(stringConstant);
        }

        // Integer constants
        else if (ch >= '0' && ch <= '9') {
            StringBuilder sb = new StringBuilder();
            while(ch >= '0' && ch <= '9' && bite != -1) {
                ch = (char) bite;
                sb.append(ch);
                bite = nextCharacterFromStream();
            }

            try {
                in.unread(bite);
            } catch (java.io.IOException e) {
                System.err.println(e);
            }

            String stringNum = sb.toString();
            System.out.println(stringNum);
            return new IntToken(Integer.parseInt(stringNum));
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
}
