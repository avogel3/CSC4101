class Ident extends Node {
  private String name;

  public Ident(String n) {
    name = n;
  }

  public void print(int n) {
    System.out.print(" ".repeat(n));

    System.out.print(name);
  }

  public String getName() {
    return name;
  }

  public boolean isSymbol() {
    return true;
  }
}
