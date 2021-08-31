class Set extends Special {
  void print(Node t, int n, boolean p, boolean q) {
    if(q) System.out.println();
    System.out.print(" ".repeat(n));

    if (!p) {
        System.out.print("(");
    }
    t.getCar().print(n);
    if(!t.getCdr().isNull()) System.out.print(" ");
    t.getCdr().print(n, true, q);
  }
}
