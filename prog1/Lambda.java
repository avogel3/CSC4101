import java.io.*;

class Lambda extends Special {
    // TODO: Add any fields needed.

    public Lambda() {}

    void print(Node t, int n, boolean p) {
        System.out.print(" ".repeat(n));

        if (!p) {
            System.out.print("(");
        }
        t.getCar().print(0);
        if(!t.getCdr().isNull()) System.out.print(" ");
        t.getCdr().print(0, true);
    }
}
