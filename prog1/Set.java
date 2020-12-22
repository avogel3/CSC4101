class Set extends Special {
  void print(Node t, int n, boolean p, boolean q) {
    if(q) System.out.println();
    System.out.print(" ".repeat(n));

    if (!p) {
        System.out.print("(");
    }
    t.getCar().print(0);
    if(!t.getCdr().isNull()) System.out.print(" ");
    t.getCdr().print(0, true, q);
  }
}
