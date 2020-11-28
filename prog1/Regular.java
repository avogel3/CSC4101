class Regular extends Special {
  void print(Node t, int n, boolean p) {
    System.out.print(" ".repeat(n));

    if (!p) {
      System.out.print("(");
    }
    t.getCar().print(0);
    // We know car is going to be the second to last char in the evaluated string
    // for example (+ 1 2), car would be 2, cdr would be null
    // We need to look ahead and see if the next one is a paren
    if(!t.getCdr().isNull()) System.out.print(" ");
    t.getCdr().print(0, true);
  }
}
