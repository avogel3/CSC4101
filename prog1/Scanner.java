// Scanner.java -- the implementation of class Scanner

import java.io.*;

class Scanner {
    private PushbackInputStream in;
    private byte[] buf = new byte[1000];

    public Scanner(InputStream i) {
        in = new PushbackInputStream(i);
    }


    public void pushBackStream(char ch) {
        try {
            int bite = (int) ch;
            in.unread(bite);
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }

    public Token getNextToken() {
        char ch = nextCharacterFromStream();

        // Skip White Space
        if (isWhiteSpace(ch)) {
            return getNextToken();
        }

        // Skip Comments. Discard from `;` to EOL
        if (ch == ';') {
            readToEndOfLine(ch);
            return getNextToken();
        }

        if (ch == Character.MIN_VALUE) {
            return null;
        }

        if (ch == '\'')
            return new Token(Token.QUOTE);
        else if (ch == '(')
            return new Token(Token.LPAREN);
        else if (ch == ')')
            return new Token(Token.RPAREN);
        else if (ch == '.')
            // We ignore the special identifier `...'.
            return new Token(Token.DOT);
        else if (ch == '#')
            return identifyBoolean();
        else if (ch == '"') {
            ch = nextCharacterFromStream();
            String stringConstant = readInputIntoBuffer("STRING", ch);
            return new StrToken(stringConstant);
        } else if (ch >= '0' && ch <= '9') {
            String integerConstant = readInputIntoBuffer("INTEGER", ch);
            return new IntToken(Integer.parseInt(integerConstant));
        } else if (isValidIdentifierInitial(ch)) {
            String identifier = readInputIntoBuffer("IDENTIFIER", ch);
            return new IdentToken(identifier);
        } else {
            // Illegal character
            System.err.println("Illegal input character '" + (byte) ch + '\'');
            return getNextToken();
        }
    }

    // Helper Methods

    // Return the bite character in ASCII form
    private char nextCharacterFromStream() {
        int bite = -1;
        try {
            bite = in.read();
        } catch (IOException e) {
            System.err.println("We fail:" + e.getMessage());
        }

        if (bite == -1) {
            return Character.MIN_VALUE;
        }

        return (char) bite;
    }

    private void readToEndOfLine(char ch) {
        while (ch != '\n') {
            ch = nextCharacterFromStream();
        }
    }

    private Token identifyBoolean() {
        char ch = nextCharacterFromStream();
        if (ch == 't')
            return new Token(Token.TRUE);
        else if (ch == 'f')
            return new Token(Token.FALSE);
        else {
            System.err.println("Illegal character '" + (char) ch + "' following #");
            return getNextToken();
        }
    }

    private boolean isWhiteSpace(char ch) {
        return (ch == ' '
                || ch == '\t'
                || ch == '\n'
                || ch == '\r'
                || ch == '\f');
    }

    private boolean isValidIdentifierInitial(char ch) {
        return (ch == '!'
                || ch == '$'
                || ch == '%'
                || ch == '&'
                || ch == '*'
                || ch == '+'
                || ch == '-'
                || ch == '/'
                || ch == ':'
                || ch == '<'
                || ch == '='
                || ch == '>'
                || ch == '?'
                || ch == '^'
                || ch == '_'
                || ch == '~'
                || (ch >= 'a' && ch <= 'z')
                || (ch >= 'A' && ch <= 'Z'));
    }

    private String getStringFromBuffer(int stringLength) {
        byte[] newString = new byte[stringLength];
        System.arraycopy(buf, 0, newString, 0, stringLength);
        return new String(newString);
    }

    private boolean inputBufferType(String type, char ch) {
        switch (type) {
            case "STRING":
                return ch != '"';
            case "INTEGER":
                return (ch >= '0' && ch <= '9');
            case "IDENTIFIER":
                return isValidIdentifierInitial(ch);
            default:
                return false;
        }
    }

    private String readInputIntoBuffer(String type, char ch) {
        int charCount = 0;
        while (inputBufferType(type, ch)) {
            buf[charCount] = (byte) ch;
            ch = nextCharacterFromStream();
            charCount++;
        }

        pushBackStream(ch);

        return getStringFromBuffer(charCount);
    }
}
