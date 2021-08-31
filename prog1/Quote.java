class Quote extends Special {
  void print(Node t, int n, boolean p, boolean q) {
    if(q) System.out.println();
    System.out.print(" ".repeat(n));

    t.getCar().print(0);
    // FIXME: this isn't recursive, but this works for now
    Node rest = t.getCdr();
    while(!rest.isNull()) {
      rest.getCar().print(n);
      rest = rest.getCdr();
    }
  }
}
