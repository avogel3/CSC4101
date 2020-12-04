// Main.java -- the main program

public class Main {
  // Array of token names used for debugging the scanner
  public static final String TokenName[] = {
    "QUOTE", // '
    "LPAREN", // (
    "RPAREN", // )
    "DOT", // .
    "TRUE", // #t
    "FALSE", // #f
    "INT", // integer constant
    "STRING", // string constant
    "IDENT" // identifier
  };

  public static void main(String argv[]) {
    // create scanner that reads from standard input
    Scanner scanner = new Scanner(System.in);

    if (argv.length > 2) {
      System.err.println("Usage: java Main " + "[-d]");
      System.exit(1);
    }

    // if commandline option -d is provided, debug the scanner
    if (argv.length == 1 && argv[0].equals("-d")) {
      // debug scanner
      Token tok = scanner.getNextToken();
      while (tok != null) {
        int tt = tok.getType();
        System.out.print(TokenName[tt]);
        if (tt == TokenType.INT) System.out.println(", intVal = " + tok.getIntVal());
        else if (tt == TokenType.STRING) System.out.println(", strVal = " + tok.getStrVal());
        else if (tt == TokenType.IDENT) System.out.println(", name = " + tok.getName());
        else System.out.println();

        tok = scanner.getNextToken();
      }
    }

    System.out.println("Starting parser...");

    // Create parser
    Parser parser = new Parser(scanner);
    Node root = parser.parseExp();

    // Parse and pretty-print each input expression
    while (root != null) {
      System.out.print("=> ");
      root.print(0);
      System.out.println();
      root = parser.parseExp();
    }
    System.exit(0);
  }
}
