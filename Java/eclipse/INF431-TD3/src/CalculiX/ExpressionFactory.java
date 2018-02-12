package CalculiX;

// This class describes an object that is able to build
// expressions of each kind. We refer to such an object as an
// "expression factory".

// When constructing abstract syntax trees, the parser relies on an
// expression factory instead of creating objects directly via "new".
// This allows the parser to be self-contained (that is, even if the
// various sub-classes of Expression have not been defined yet, the
// parser can be compiled). This is exploited by TD Interpretation.

class ExpressionFactory {

  Value buildValue (int i){return new Integer(i);}

  Value buildValue (boolean b){return new Boolean(b);}

  Expression buildConstant (int i){return new EInteger(i);}

  Expression buildConstant (boolean b){return new EBoolean(b);}

  Expression buildIntegerOperation (Expression left, Operator op, Expression right){return new EIOperator(left,op,right);}

  Expression buildBooleanOperation (Expression left, Connective op, Expression right){return new EBOperator(left,op,right);}

  Expression buildBooleanNegation (Expression e){return new EBOperator(e,Connective.XOR,new EBoolean(true));}

  Expression buildIntegerComparison (Expression left, Comparator op, Expression right){return new EIComparator(left,op,right);}

  Expression buildIf (Expression condition, Expression then_branch, Expression else_branch){return new EIfElse(condition,then_branch,else_branch);}

  Expression buildLet (String x, Expression left, Expression right){return new ELet(x,left,right);}

  Expression buildVarRead (String x){return new EVariable(x);}

  Expression buildSigma (String x, Expression lo, Expression hi, Expression body){return new ESigma(x,lo,hi,body);}

}
