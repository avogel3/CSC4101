import java.io.*;
class IntLit extends Node {
  private int intVal;

  public IntLit(int i) {
    intVal = i;
  }

  public void print(int n) {
    System.out.print(" ".repeat(n));

    System.out.print(intVal);
  }

  public boolean isNumber() {
    return true;
  }
}
