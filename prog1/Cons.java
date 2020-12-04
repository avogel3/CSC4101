class Cons extends Node {
  private Node car;
  private Node cdr;
  private Special form;

  // parseList() `parses' special forms, constructs an appropriate
  // object of a subclass of Special, and stores a pointer to that
  // object in variable form.  It would be possible to fully parse
  // special forms at this point.  Since this causes complications
  // when using (incorrect) programs as data, it is easiest to let
  // parseList only look at the car for selecting the appropriate
  // object from the Special hierarchy and to leave the rest of
  // parsing up to the interpreter.
  private Special parseList() {
    if (!car.isSymbol()) {
      return new Regular();
    }

    switch (car.getName()) {
      case "quote":
        return new Quote();
      case "lambda":
        return new Lambda();
      case "begin":
        return new Begin();
      case "if":
        return new If();
      case "let":
        return new Let();
      case "cond":
        return new Cond();
      case "define":
        return new Define();
      case "set!":
        return new Set();
      default:
        return new Regular();
    }
  }

  public Cons(Node a, Node d) {
    car = a;
    cdr = d;
    form = parseList();
  }

  void print(int n) {
    form.print(this, n, false);
  }

  void print(int n, boolean openParenWasPrinted) {
    form.print(this, n, openParenWasPrinted);
  }

  public Node getCar() {
    return car;
  }

  public Node getCdr() {
    return cdr;
  }
  
  public Node getCddr() {
    return cdr.getCdr();
  }
  
  public Node getCdar() {
    return cdr.getCar();
  }

  public boolean isPair() {
    return true;
  }
}
