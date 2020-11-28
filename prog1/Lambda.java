class Lambda extends Special {
    // TODO: Add any fields needed.

    public Lambda() {}

    void print(Node t, int n, boolean p) {
      String indent = " ".repeat(n);
      System.out.print(indent);

      if(!p) {
        System.out.print("(");
      }
      t.getCar().print(0);
      if(!t.getCdar().isNull()) {
        t.getCdar().print(1);
      }

      Node rest = t.getCddr();
      while(!rest.isNull()) {
        System.out.println();
        rest.getCar().print(n + 4);
        rest = rest.getCdr();
      }
      System.out.println("\n" + indent + ")");
    }
}
