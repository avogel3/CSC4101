class Nil extends Node {
  public Nil() { }

  public void print(int n)		{ print(n, false); }
  public void print(int n, boolean openParenWasPrinted) {
    System.out.print(" ".repeat(n));
   
    if (openParenWasPrinted) { 
      System.out.print(")");
    } else {
      System.out.print("()");
    }
  }

  public boolean isNull() { return true; }
}
