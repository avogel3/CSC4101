// Parser.java -- the implementation of class Parser
//
// Defines
//
//   class Parser;
//
// Parses the language
//
//   exp  ->  ( rest
//         |  #f
//         |  #t
//         |  ' exp
//         |  integer_constant
//         |  string_constant
//         |  identifier
//    rest -> )
//         |  exp+ [. exp] )
//
// and builds a parse tree.  Lists of the form (rest) are further
// `parsed' into regular lists and special forms in the constructor
// for the parse tree node class Cons.  See Cons.parseList() for
// more information.
//
// The parser is implemented as an LL(0) recursive descent parser.
// I.e., parseExp() expects that the first token of an exp has not
// been read yet.  If parseRest() reads the first token of an exp
// before calling parseExp(), that token must be put back so that
// it can be reread by parseExp() or an alternative version of
// parseExp() must be called.
//
// If EOF is reached (i.e., if the scanner returns a NULL) token,
// the parser returns a NULL tree.  In case of a parse error, the
// parser discards the offending token (which probably was a DOT
// or an RPAREN) and attempts to continue parsing with the next token.

class Parser {
    private Scanner scanner;

    /*
     NOTE: this is from the Project 1 Outline
     Build your parse tree such that only a single object of class Nil and only two objects of class BoolLit
     get created. E.g., multiple occurrences of Nil should be pointers pointing to the same object.
    */
    private BooleanLit falseyBooleanLit = new BooleanLit(false);
    private BooleanLit truthyBooleanLit = new BooleanLit(true);
    private Nil nil = new Nil();

    public Parser(Scanner s) {
        scanner = s;
    }


    // TODO: Gotta figure out where the QUOTE token fits in all of this
    public Node parseExp() {
        Token token = scanner.getNextToken();
        switch (token.getType()) {
            case Token.LPAREN:
                return parseRest();
            case Token.FALSE:
                return falseyBooleanLit;
            case Token.TRUE:
                return truthyBooleanLit;
            case Token.QUOTE:
                return parseExp();
            case Token.INT:
                return new IntLit(token.getIntVal());
            case Token.STRING:
                return new StrLit(token.getStrVal());
            case Token.IDENT:
                return new Ident(token.getName());
            default:
                return null;
        }
    }

    protected Node parseRest() {
        // TODO: See below
        // RPAREN ->
        // EXP + ->
        // DOT EXP ->
        // default/else ->

        Token token = scanner.getNextToken();
        switch(token.getType()) {
            case Token.RPAREN:
                return null;
            case Token.DOT:
                // FIXME: Next line is untested
                // return new Cons(new Ident(token.getName()), parseExp());
                return null;
            default:
                return new Cons(parseExp(), nil);
        }
    }
};
