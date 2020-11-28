class Let extends Special {
  void print(Node t, int n, boolean p) {
    System.out.print(" ".repeat(n));

    if(!p) {
      System.out.print("(");
    }
    t.getCar().print(0);
    
    Node rest = t.getCdr();
    while(!rest.isNull()) {
      System.out.println();
      rest.getCar().print(4);
      rest = rest.getCdr();
    }
    System.out.println("\n)");
  }
}
