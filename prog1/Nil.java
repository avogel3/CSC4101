class Nil extends Node {
  public Nil() { }

  public void print(int n)		{ print(n, false, false); }
  public void print(int n, boolean openParenWasPrinted, boolean q) {
    if(q) System.out.println();
    System.out.print(" ".repeat(n));
   
    if (openParenWasPrinted) { 
      System.out.print(")");
    } else {
      System.out.print("()");
    }
  }

  public boolean isNull() { return true; }
}
