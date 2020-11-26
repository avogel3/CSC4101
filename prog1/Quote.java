class Quote extends Special {

  // TODO: Add any fields needed.

  public Quote() {}

  void print(Node t, int n, boolean p) {
    System.out.println("Quote.print called");
    t.getCar().print(1);
    t.getCdr().print(0, true);
  }
}
