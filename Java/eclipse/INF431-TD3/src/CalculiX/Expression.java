package CalculiX;

abstract class Expression {

  // An expression can be evaluated, to produce a value.

  // Evaluation can fail. In that case, an EvalException exception is thrown.

    Value eval () throws EvalException{
	return eval(Environment.empty);
    }

    // In presence of variables, evaluation requires an environment. The environment maps variables to
    // values. The environment should contain an entry for each of the variables
    // that appear free in the expression.

    Value eval (Environment env) throws EvalException{ 
	throw new EvalException("Evaluation has not been implemented for this kind of expression");
    }

}

