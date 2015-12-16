// Define -- Parse tree node strategy for printing the special form define

using System;

namespace Tree
{
    public class Define : Special
    {
	public Define() { }

        public override void print(Node t, int n, bool p)
        {
            Printer.printDefine(t, n, p);
        }

        public override Node eval(Node t, Environment env)
        {
          Node iden = t.getCdr().getCar();
          Node val = t.getCdr().getCdr().getCar();

          if (iden.isSymbol()) 
          {
            env.define(iden, val);
          }
          else 
          {
            Closure close = new Closure(new Cons(t.getCdr().getCar().getCdr(), t.getCdr().getCdr()), env);
            env.define(iden.getCar(), close);
          }
          if(iden.isPair()) {
            Node func = iden.getCar();

            Cons list = new Cons(new Ident("lambda"), new Cons(val, t.getCdr().getCar().getCdr()));
            env.define(func, list);
          }
          return new StringLit("Values are null (Define)");
        }
    }
}


