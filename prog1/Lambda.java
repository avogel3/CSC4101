class Lambda extends Special {
  void print(Node t, int n, boolean p, boolean q) {
    if(q) System.out.println();
    String indent = " ".repeat(n);
    System.out.print(indent);

    if(!p) {
      System.out.print("(");
    }
    t.getCar().print(0);
    if(!t.getCdar().isNull()) {
      t.getCdar().print(1);
    }

    if(!t.getCddr().isNull()) {
      t.getCddr().print(n + 4);
    }
  }
}
