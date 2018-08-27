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

  public Parser(Scanner s) { scanner = s; }


  // TODO: Gotta figure out where the QUOTE token fits in all of this
  public Node parseExp() {
    // TODO: See below
    // STRING, INT, BOOLEAN, IDENT -> Call new for literals
    // LPAREN -> Call parseRest
    // default/else -> null
    return null;
  }
  
  protected Node parseRest() {
    // TODO: See below
    // RPAREN ->
    // DOT ->
    // default/else ->
    return null;
  }
};
