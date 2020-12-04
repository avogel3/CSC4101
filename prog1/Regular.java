class Regular extends Special {
  void print(Node t, int n, boolean p) {
    System.out.print(" ".repeat(n));

    if (!p) {
      System.out.print("(");
    }
    t.getCar().print(0);
   
    int minNumSpaces = n > 1 ? n : 1;
    int indentSpace = t.getCdr().isNull() ? 0 : minNumSpaces;
    t.getCdr().print(indentSpace, true);
  }
}
