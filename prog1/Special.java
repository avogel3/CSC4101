// Special.h -- the parse tree node data structure for special forms

// There are several different approaches for how to implement the Special
// hierarchy.  We'll discuss some of them in class.  The easiest solution
// is to not add any fields and to use empty constructors.

abstract class Special {
    /**
     * @param t the node to begin printing
     * @param n the number of spaces to indent
     * @param parenWasPrinted true if the starting paren was printed  
     * @param printNewLine true if a new line character needs to be printed
     */
    abstract void print(Node t, int n, boolean parenWasPrinted, boolean printNewLine);
}

