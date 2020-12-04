class Begin extends Special {
  void print(Node t, int n, boolean p) {
    System.out.print(" ".repeat(n));

    if(!p) {
      System.out.print("(");
    }
    t.getCar().print(0);

    int indentSpace = t.getCdr().isNull() ? 0 : n + 4;
    System.out.println("");
    t.getCdr().print(indentSpace, true);
  }
}
