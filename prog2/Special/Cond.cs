// Cond -- Parse tree node strategy for printing the special form cond

using System;

namespace Tree
{
    public class Cond : Special
    {
	public Cond() { }

        public override void print(Node t, int n, bool p)
        { 
            Printer.printCond(t, n, p);
        }

        public override Node eval(Node t, Environment env) 
        {
          Node cdr = t.getCdr();
          while ((!cdr.getCar().getCar().eval(env).isBool()) && (!cdr.isNull()))
          {
            cdr = cdr.getCdr();
          }
          if (cdr.isNull()) 
          {
            Console.Error.WriteLine("Cond (cdr) is null");
            return Nil.getInstance();
          }
          else
          {
            return (cdr.getCar().getCdr().getCar().eval(env));
          }
        }
    }
}


