// Regular -- Parse tree node strategy for printing regular lists

using System;

namespace Tree
{
public class Regular : Special
{
  public Regular() { }

  public override void print(Node t, int n, bool p)
  {
    Printer.printRegular(t, n, p);
  }

  public override Node eval(Node t, Environment env)
  {
    Node first = t.getCar();
    Node args = evalRegList(t.getCdr(), env);

    while (first.isSymbol())
    {
      first = env.lookup(first);
    }
    if (first == null || first.isNull())
    {
      Console.Error.WriteLine("Error, undefined function");
      return Nil.getInstance();
    }
    else if (first.isProcedure())
    {
      return first.apply(args);
    }
    else
    {
      return first.eval(env).apply(args);
    }
  }

  public Node evalRegList(Node t, Environment env)
  {
    if (t == null || t.isNull())
    {
      Node list = new Cons(Nil.getInstance(), Nil.getInstance());
      return list;
    }
    else {
      Node arg = t.getCar();
      Node rest = t.getCdr();
      if (arg.isSymbol())
      {
        arg = env.lookup(arg);
      }
      if (arg == null || arg.isNull())
      {
        return Nil.getInstance();
      }
      Node list = new Cons(arg.eval(env), evalRegList(rest, env));
      return list;
    }
  }
}
}


