class Let extends Special {
  void print(Node t, int n, boolean p, boolean q) {
    if(q) System.out.println();
    System.out.print(" ".repeat(n));

    if(!p) {
      System.out.print("(");
    }
    t.getCar().print(n);
    
    Node rest = t.getCdr();
    while(!rest.isNull()) {
      System.out.println();
      rest.getCar().print(n + 4);
      rest = rest.getCdr();
    }
    System.out.println("\n)");
  }
}
