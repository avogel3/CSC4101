import java.io.*;

class Set extends Special {
 
    // TODO: Add any fields needed.

    public Set() {}

    void print(Node t, int n, boolean p) {
        for(int i = 0; i < n; i++) {
            System.out.print(" ");
        }
        if (!p) {
            System.out.print("(");
        }
        t.getCar().print(0);
        if(!t.getCdr().isNull()) System.out.print(" ");
        t.getCdr().print(0, true);
    }
}
