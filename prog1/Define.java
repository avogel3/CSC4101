class Define extends Special {
  // TODO: Add any fields needed.

 
  // TODO: Add an appropriate constructor.

  void print(Node t, int n, boolean p) {
    System.out.print(" ".repeat(n));

    if (!p) {
      System.out.print("(");
    }
    t.getCar().print(0);

    Node rest = t.getCdr();
    while(!rest.isNull()) {
      rest.getCar().print(1);
      rest = rest.getCdr();
    }
    System.out.println(")");
  }
}
