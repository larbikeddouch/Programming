
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Tue Feb 12 22:18:34 CET 2013
//----------------------------------------------------

package CalculiX;

import java_cup.runtime.*;
import java.lang.Integer;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Tue Feb 12 22:18:34 CET 2013
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\024\000\002\002\004\000\002\002\005\000\002\002" +
    "\003\000\002\002\003\000\002\002\003\000\002\002\003" +
    "\000\002\002\005\000\002\002\005\000\002\002\005\000" +
    "\002\002\005\000\002\002\005\000\002\002\005\000\002" +
    "\002\005\000\002\002\005\000\002\002\005\000\002\002" +
    "\004\000\002\003\003\000\002\003\010\000\002\003\010" +
    "\000\002\003\012" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\063\000\024\004\004\005\005\007\016\011\015\013" +
    "\007\014\011\015\010\024\006\034\014\001\002\000\042" +
    "\002\uffff\010\uffff\012\uffff\016\uffff\017\uffff\020\uffff\021" +
    "\uffff\022\uffff\023\uffff\025\uffff\026\uffff\027\uffff\030\uffff" +
    "\031\uffff\032\uffff\033\uffff\001\002\000\042\002\ufffe\010" +
    "\ufffe\012\ufffe\016\ufffe\017\ufffe\020\ufffe\021\ufffe\022\ufffe" +
    "\023\ufffe\025\ufffe\026\ufffe\027\ufffe\030\ufffe\031\ufffe\032" +
    "\ufffe\033\ufffe\001\002\000\004\004\057\001\002\000\042" +
    "\002\ufffd\010\ufffd\012\ufffd\016\ufffd\017\ufffd\020\ufffd\021" +
    "\ufffd\022\ufffd\023\ufffd\025\ufffd\026\ufffd\027\ufffd\030\ufffd" +
    "\031\ufffd\032\ufffd\033\ufffd\001\002\000\024\004\004\005" +
    "\005\007\016\011\015\013\007\014\011\015\010\024\006" +
    "\034\014\001\002\000\042\002\ufffc\010\ufffc\012\ufffc\016" +
    "\ufffc\017\ufffc\020\ufffc\021\ufffc\022\ufffc\023\ufffc\025\ufffc" +
    "\026\ufffc\027\ufffc\030\ufffc\031\ufffc\032\ufffc\033\ufffc\001" +
    "\002\000\004\002\051\001\002\000\042\002\ufff1\010\ufff1" +
    "\012\ufff1\016\ufff1\017\ufff1\020\034\021\027\022\033\023" +
    "\032\025\ufff1\026\ufff1\027\036\030\035\031\031\032\030" +
    "\033\037\001\002\000\016\004\004\005\005\007\016\013" +
    "\007\014\011\034\014\001\002\000\004\004\021\001\002" +
    "\000\024\004\004\005\005\007\016\011\015\013\007\014" +
    "\011\015\010\024\006\034\014\001\002\000\004\010\020" +
    "\001\002\000\042\002\000\010\000\012\000\016\000\017" +
    "\000\020\000\021\000\022\000\023\000\025\000\026\000" +
    "\027\000\030\000\031\000\032\000\033\000\001\002\000" +
    "\004\006\022\001\002\000\024\004\004\005\005\007\016" +
    "\011\015\013\007\014\011\015\010\024\006\034\014\001" +
    "\002\000\004\012\024\001\002\000\024\004\004\005\005" +
    "\007\016\011\015\013\007\014\011\015\010\024\006\034" +
    "\014\001\002\000\020\002\uffef\010\uffef\012\uffef\016\uffef" +
    "\017\uffef\025\uffef\026\uffef\001\002\000\042\002\ufff2\010" +
    "\ufff2\012\ufff2\016\ufff2\017\ufff2\020\ufff2\021\ufff2\022\ufff2" +
    "\023\ufff2\025\ufff2\026\ufff2\027\ufff2\030\ufff2\031\ufff2\032" +
    "\ufff2\033\ufff2\001\002\000\016\004\004\005\005\007\016" +
    "\013\007\014\011\034\014\001\002\000\016\004\004\005" +
    "\005\007\016\013\007\014\011\034\014\001\002\000\016" +
    "\004\004\005\005\007\016\013\007\014\011\034\014\001" +
    "\002\000\016\004\004\005\005\007\016\013\007\014\011" +
    "\034\014\001\002\000\016\004\004\005\005\007\016\013" +
    "\007\014\011\034\014\001\002\000\016\004\004\005\005" +
    "\007\016\013\007\014\011\034\014\001\002\000\016\004" +
    "\004\005\005\007\016\013\007\014\011\034\014\001\002" +
    "\000\016\004\004\005\005\007\016\013\007\014\011\034" +
    "\014\001\002\000\016\004\004\005\005\007\016\013\007" +
    "\014\011\034\014\001\002\000\042\002\ufff3\010\ufff3\012" +
    "\ufff3\016\ufff3\017\ufff3\020\034\021\027\022\033\023\032" +
    "\025\ufff3\026\ufff3\027\036\030\035\031\031\032\030\033" +
    "\ufff3\001\002\000\034\002\ufff7\010\ufff7\012\ufff7\016\ufff7" +
    "\017\ufff7\020\034\021\027\022\033\023\032\025\ufff7\026" +
    "\ufff7\032\ufff7\033\ufff7\001\002\000\034\002\ufff6\010\ufff6" +
    "\012\ufff6\016\ufff6\017\ufff6\020\034\021\027\022\033\023" +
    "\032\025\ufff6\026\ufff6\032\ufff6\033\ufff6\001\002\000\042" +
    "\002\ufff9\010\ufff9\012\ufff9\016\ufff9\017\ufff9\020\ufff9\021" +
    "\ufff9\022\033\023\032\025\ufff9\026\ufff9\027\ufff9\030\ufff9" +
    "\031\ufff9\032\ufff9\033\ufff9\001\002\000\042\002\ufffb\010" +
    "\ufffb\012\ufffb\016\ufffb\017\ufffb\020\ufffb\021\ufffb\022\ufffb" +
    "\023\ufffb\025\ufffb\026\ufffb\027\ufffb\030\ufffb\031\ufffb\032" +
    "\ufffb\033\ufffb\001\002\000\042\002\ufffa\010\ufffa\012\ufffa" +
    "\016\ufffa\017\ufffa\020\ufffa\021\ufffa\022\ufffa\023\ufffa\025" +
    "\ufffa\026\ufffa\027\ufffa\030\ufffa\031\ufffa\032\ufffa\033\ufffa" +
    "\001\002\000\034\002\ufff5\010\ufff5\012\ufff5\016\ufff5\017" +
    "\ufff5\020\034\021\027\022\033\023\032\025\ufff5\026\ufff5" +
    "\032\ufff5\033\ufff5\001\002\000\042\002\ufff4\010\ufff4\012" +
    "\ufff4\016\ufff4\017\ufff4\020\034\021\027\022\033\023\032" +
    "\025\ufff4\026\ufff4\027\036\030\035\031\031\032\ufff4\033" +
    "\ufff4\001\002\000\042\002\ufff8\010\ufff8\012\ufff8\016\ufff8" +
    "\017\ufff8\020\ufff8\021\ufff8\022\033\023\032\025\ufff8\026" +
    "\ufff8\027\ufff8\030\ufff8\031\ufff8\032\ufff8\033\ufff8\001\002" +
    "\000\004\002\001\001\002\000\004\016\053\001\002\000" +
    "\024\004\004\005\005\007\016\011\015\013\007\014\011" +
    "\015\010\024\006\034\014\001\002\000\004\017\055\001" +
    "\002\000\024\004\004\005\005\007\016\011\015\013\007" +
    "\014\011\015\010\024\006\034\014\001\002\000\020\002" +
    "\ufff0\010\ufff0\012\ufff0\016\ufff0\017\ufff0\025\ufff0\026\ufff0" +
    "\001\002\000\004\006\060\001\002\000\024\004\004\005" +
    "\005\007\016\011\015\013\007\014\011\015\010\024\006" +
    "\034\014\001\002\000\004\025\062\001\002\000\024\004" +
    "\004\005\005\007\016\011\015\013\007\014\011\015\010" +
    "\024\006\034\014\001\002\000\004\026\064\001\002\000" +
    "\024\004\004\005\005\007\016\011\015\013\007\014\011" +
    "\015\010\024\006\034\014\001\002\000\020\002\uffee\010" +
    "\uffee\012\uffee\016\uffee\017\uffee\025\uffee\026\uffee\001\002" +
    "" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\063\000\006\002\012\003\011\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\006\002\012\003\051\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\002\025\001\001" +
    "\000\002\001\001\000\006\002\012\003\016\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\006" +
    "\002\012\003\022\001\001\000\002\001\001\000\006\002" +
    "\012\003\024\001\001\000\002\001\001\000\002\001\001" +
    "\000\004\002\047\001\001\000\004\002\046\001\001\000" +
    "\004\002\045\001\001\000\004\002\044\001\001\000\004" +
    "\002\043\001\001\000\004\002\042\001\001\000\004\002" +
    "\041\001\001\000\004\002\040\001\001\000\004\002\037" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\006\002\012\003\053" +
    "\001\001\000\002\001\001\000\006\002\012\003\055\001" +
    "\001\000\002\001\001\000\002\001\001\000\006\002\012" +
    "\003\060\001\001\000\002\001\001\000\006\002\012\003" +
    "\062\001\001\000\002\001\001\000\006\002\012\003\064" +
    "\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {



  static ExpressionFactory factory;


  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // expression ::= FOR IDENT EQ expression TO expression SUM expression 
            {
              Expression RESULT =null;
		int xleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)).left;
		int xright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)).right;
		String x = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-6)).value;
		int loleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
		int loright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
		Expression lo = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		int hileft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int hiright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression hi = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int bodyleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bodyright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression body = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildSigma(x, lo, hi, body); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-7)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // expression ::= LET IDENT EQ expression IN expression 
            {
              Expression RESULT =null;
		int xleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
		int xright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
		String x = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildLet(x, e1, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // expression ::= IF expression THEN expression ELSE expression 
            {
              Expression RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
		Expression b = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildIf(b, e1, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // expression ::= simple_expression 
            {
              Expression RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = e; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // simple_expression ::= NOT simple_expression 
            {
              Expression RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildBooleanNegation(e); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // simple_expression ::= simple_expression OR simple_expression 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildBooleanOperation(e1, Connective.OR, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // simple_expression ::= simple_expression AND simple_expression 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildBooleanOperation(e1, Connective.AND, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // simple_expression ::= simple_expression EQEQ simple_expression 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildIntegerComparison(e1, Comparator.EQUALS, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // simple_expression ::= simple_expression LE simple_expression 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildIntegerComparison(e1, Comparator.LESSTHANOREQUALTO, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // simple_expression ::= simple_expression LT simple_expression 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildIntegerComparison(e1, Comparator.LESSTHAN, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // simple_expression ::= simple_expression MINUS simple_expression 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildIntegerOperation(e1, Operator.SUBTRACTION, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // simple_expression ::= simple_expression PLUS simple_expression 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildIntegerOperation(e1, Operator.ADDITION, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // simple_expression ::= simple_expression SLASH simple_expression 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildIntegerOperation(e1, Operator.DIVISION, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // simple_expression ::= simple_expression STAR simple_expression 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildIntegerOperation(e1, Operator.MULTIPLICATION, e2); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // simple_expression ::= FALSE 
            {
              Expression RESULT =null;
		 RESULT = factory.buildConstant(false); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // simple_expression ::= TRUE 
            {
              Expression RESULT =null;
		 RESULT = factory.buildConstant(true); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // simple_expression ::= INTEGER 
            {
              Expression RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Integer c = (Integer)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildConstant(c); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // simple_expression ::= IDENT 
            {
              Expression RESULT =null;
		int xleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int xright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String x = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = factory.buildVarRead(x); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // simple_expression ::= LPAREN expression RPAREN 
            {
              Expression RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = e; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("simple_expression",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= expression EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Expression start_val = (Expression)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

