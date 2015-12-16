// Let -- Parse tree node strategy for printing the special form let

using System;

namespace Tree
{
    public class Let : Special
    {
	public Let() { }

        public override void print(Node t, int n, bool p)
        {
            Printer.printLet(t, n, p);
        }

        public override Node eval(Node t, Environment env) 
        {
          Environment local = new Environment(env);
          Node args = t.getCdr().getCar();
          Node exp = t.getCdr().getCdr().getCar();
          args = evalBody(args, local);
          return exp.eval(local);
        }

        public Node evalBody(Node t, Environment env) {
          if (t.isNull()) {
            return Nil.getInstance();
          }
          else {
            Node arg = t.getCar().getCar();
            Node func = t.getCar().getCdr().getCar();
            Node rest = t.getCdr();

            if (arg.isSymbol()) {
              env.define(arg, func.eval(env));
              return evalBody(rest, env);
            }
            else if (arg.isPair()) {
              return arg.eval(env);
            }
            else if (arg.isNull()) {
              return Nil.getInstance();
            }
            return null;
          }
        }
    }
}


