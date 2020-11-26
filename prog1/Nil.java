class Nil extends Node {
  public Nil() { }

  public void print(int n)		{ print(n, false); }
  public void print(int n, boolean p) {
    System.out.print(" ".repeat(n));
    
    if (p) {
      System.out.print(")");
    } else {
      System.out.println("()");
    }
  }

  public boolean isNull() { return true; }
}
