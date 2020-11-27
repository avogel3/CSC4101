class Nil extends Node {
  public Nil() { }

  public void print(int n)		{ print(n, false); }
  public void print(int n, boolean shouldCloseParens) {
    System.out.print(" ".repeat(n));
   
    if (shouldCloseParens) { 
      System.out.print(")");
    } else {
      System.out.println("()");
    }
  }

  public boolean isNull() { return true; }
}
