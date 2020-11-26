class BooleanLit extends Node {
  private boolean booleanVal;

  public BooleanLit(boolean b) {
    booleanVal = b;
  }

  public void print(int n) {
    System.out.print(" ".repeat(n));
    
    if (booleanVal) {
      System.out.println("#t");
    } else {
      System.out.println("#f");
    }
  }

  public boolean isBoolean() { return true; }
}
