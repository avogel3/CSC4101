// SPP -- The main program of the Scheme pretty printer.

using System;
using Parse;
using Tokens;
using Tree;

public class Scheme4101
{
    public static int Main(string[] args)
    {
        // Create scanner that reads from standard input
        Scanner scanner = new Scanner(Console.In);
        
        if (args.Length > 1 ||
            (args.Length == 1 && ! args[0].Equals("-d")))
        {
            Console.Error.WriteLine("Usage: mono SPP [-d]");
            return 1;
        }
        
        // If command line option -d is provided, debug the scanner.
        if (args.Length == 1 && args[0].Equals("-d"))
        {
            // Console.Write("Scheme 4101> ");
            Token tok = scanner.getNextToken();
            while (tok != null)
            {
                TokenType tt = tok.getType();

                Console.Write(tt);
                if (tt == TokenType.INT)
                    Console.WriteLine(", intVal = " + tok.getIntVal());
                else if (tt == TokenType.STRING)
                    Console.WriteLine(", stringVal = " + tok.getStringVal());
                else if (tt == TokenType.IDENT)
                    Console.WriteLine(", name = " + tok.getName());
                else
                    Console.WriteLine();

                // Console.Write("Scheme 4101> ");
                tok = scanner.getNextToken();
            }
            return 0;
        }

        // Create parser
        TreeBuilder builder = new TreeBuilder();
        Parser parser = new Parser(scanner, builder);
        Node root;

        // Read-eval-print loop

        Tree.Environment builtin = new Tree.Environment();
        
        Ident id = new Ident("symbol?");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("number?");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("b+");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("b-");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("b*");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("b/");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("b=");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("b<");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("car");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("cdr");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("set-car!");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("set-cdr!");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("null?");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("pair?");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("eq?");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("procedure?");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("read");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("write");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("display");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("newline");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("eval");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("apply");
        builtin.define(id, new BuiltIn(id));
        id = new Ident("interaction-environment");
        builtin.define(id, new BuiltIn(id));


        Tree.Environment env = new Tree.Environment(builtin);
        root = (Node) parser.parseExp();
        while (root != null) 
        {
            root.eval(env).print(0);
            root = (Node) parser.parseExp();
        }

        return 0;
    }
}
