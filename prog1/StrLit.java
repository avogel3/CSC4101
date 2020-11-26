class StrLit extends Node {
  private String strVal;

  public StrLit(String s) { strVal = s; }

  public void print(int n) {
    System.out.print(" ".repeat(n));

    System.out.println("\"" + strVal + "\"");
  }

  public boolean isString() { return true; }
}
