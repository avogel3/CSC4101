class Begin extends Special {
  void print(Node t, int n, boolean p, boolean q) {
    if(q) System.out.println();
    System.out.print(" ".repeat(n));

    if(!p) {
      System.out.print("(");
    }
    t.getCar().print(0);

    int indentSpace = t.getCdr().isNull() ? 0 : n + 4;
    
    // NOTE: last bool on this line probs shouldn't be hard coded, but w/e
    t.getCdr().print(indentSpace, true, true);
  }
}
