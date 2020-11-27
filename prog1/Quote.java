class Quote extends Special {

  public Quote() {}

  void print(Node t, int n, boolean p) {
    System.out.print(" ".repeat(n));

    if(!p) {
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
