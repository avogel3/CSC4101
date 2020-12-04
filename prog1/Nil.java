class Nil extends Node {
  public Nil() { }

  public void print(int n)		{ print(n, false); }
  public void print(int n, boolean openParenWasPrinted) {
    System.out.print(" ".repeat(n));
   
    if (openParenWasPrinted) { 
      System.out.println(")");
    } else {
      System.out.println("()");
    }
  }

  public boolean isNull() { return true; }
}
