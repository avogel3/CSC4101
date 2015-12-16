// BuiltIn -- the data structure for built-in functions

// Class BuiltIn is used for representing the value of built-in functions
// such as +.  Populate the initial environment with
// (name, new BuiltIn(name)) pairs.

// The object-oriented style for implementing built-in functions would be
// to include the C# methods for implementing a Scheme built-in in the
// BuiltIn object.  This could be done by writing one subclass of class
// BuiltIn for each built-in function and implementing the method apply
// appropriately.  This requires a large number of classes, though.
// Another alternative is to program BuiltIn.apply() in a functional
// style by writing a large if-then-else chain that tests the name of
// the function symbol.

using System;
using Parse;

namespace Tree
{
public class BuiltIn : Node
{
    private Node symbol;            // the Ident for the built-in function
    private Environment env = new Environment();

    public BuiltIn(Node s)      { symbol = s; }

    public BuiltIn(Environment env) {
        env = this.env;
    }

    public Node getSymbol()     { return symbol; }


    public override bool isProcedure()  { return true; }

    public override void print(int n)
    {
        // there got to be a more efficient way to print n spaces
        for (int i = 0; i < n; i++)
            Console.Write(' ');
        Console.Write("#{Built-in Procedure ");
        if (symbol != null)
            symbol.print(-Math.Abs(n));
        Console.Write('}');
        if (n >= 0)
            Console.WriteLine();
    }

    public override Node apply(Node args)
    {
        if (args == null) { return null; }

        String symbolType = symbol.getName();
        Node arg1 = args.getCar();
        Node arg2 = args.getCdr();

        if (arg1.isNull()) {
            arg1 = Nil.getInstance();
        }
        if (arg2.isNull()) {
            arg2 = Nil.getInstance();
        }
        else {
            arg2 = args.getCdr().getCar();
        }

        if (symbolType.Equals("b+")) {
            if (arg1.isNumber() & arg2.isNumber()) {
                return new IntLit(arg1.getValue() + arg2.getValue());
            }
            else {
                return new StringLit("Error: Both arguments must be numbers for b+");
            }
        }
        if (symbolType.Equals("b-")) {
            if (arg1.isNumber() & arg2.isNumber()) {
                return new IntLit(arg1.getValue() - arg2.getValue());
            }
            else {
                return new StringLit("Error: Both arguments must be numbers for b-");
            }
        }
        if (symbolType.Equals("b*")) {
            if (arg1.isNumber() & arg2.isNumber()) {
                return new IntLit(arg1.getValue() * arg2.getValue());
            }
            else {
                return new StringLit("Error: Both arguments must be numbers for b*");
            }
        }
        if (symbolType.Equals("b/")) {
            if (arg1.isNumber() & arg2.isNumber()) {
                return new IntLit(arg1.getValue() / arg2.getValue());
            }
            else {
                return new StringLit("Error: Both arguments must be numbers for b/");
            }
        }
        if (symbolType.Equals("b=")) {
            if (arg1.isNumber() & arg2.isNumber()) {
                return new BoolLit(arg1.getValue() == arg2.getValue());
            }
            else {
                return new StringLit("Error: Both arguments must be numbers for b=");
            }
        }
        if (symbolType.Equals("b<")) {
            if (arg1.isNumber() & arg2.isNumber()) {
                return new BoolLit(arg1.getValue() < arg2.getValue());
            }
            else {
                return new StringLit("Error: Both arguments must be numbers for b<");
            }
        }

        if (symbolType.Equals("null?")) {
            return new BoolLit(arg1.isNull());
        }
        if (symbolType.Equals("number?")) {
            return new BoolLit(arg1.isNumber());
        }
        if (symbolType.Equals("pair?")) {
            return new BoolLit(arg1.isPair());
        }
        if (symbolType.Equals("symbol?")) {
            return new BoolLit(arg1.isSymbol());
        }
        if (symbolType.Equals("procedure?")) {
            return new BoolLit(arg1.isProcedure());
        }

        if (symbolType.Equals("car")) {
            if (arg1.isNull()) {
                return arg1;
            }
            return arg1.getCar();
        }
        if (symbolType.Equals("cdr")) {
            if (arg1.isNull()) {
                return arg1;
            }
            return arg1.getCdr();
        }
        if (symbolType.Equals("set-car!")) {
            arg1.setCar(arg2);
            return arg1;
        }
        if (symbolType.Equals("set-cdr!")) {
            arg1.setCdr(arg2);
            return arg1;
        }

        if (symbolType.Equals("cons")) {
            return new Cons(arg1, arg2);
        }

        if (symbolType.Equals("eq?")) {
            return new BoolLit(arg1 == arg2);
        }

        if (symbolType.Equals("display")) {
            return arg1;
        }

        if (symbolType.Equals("write")) {
            //write calls your pretting printer
            arg1.print(0);
            return new StringLit("");
        }

        if (symbolType.Equals("eval")) {
            //eval calls your C# eval() function   
            return eval(arg1, env);
        }

        if (symbolType.Equals("apply")) {
            //apply calls your C# apply function
            return arg1.apply(arg2);
        }

        if (symbolType.Equals("newline")) {
            return new StringLit("");
        }

        if (symbolType.Equals("read")) {
            Scanner io = new Scanner(Console.In);
            TreeBuilder tree = new TreeBuilder();
            Parser parse = new Parser(io, tree);
            Node theNode = (Node)parse.parseExp();
            return theNode;
        }

        if (symbolType.Equals("interaction-environment")) {
            env.print(0);
        }
        else {
            arg1.print(0);
            return Nil.getInstance();
        }

        return Nil.getInstance();
    }

    public Node eval(Node t, Environment env) {
        Console.Error.WriteLine("Error in eval BuiltIN");
        return Nil.getInstance();
    }
}
}

