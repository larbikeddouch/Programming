package CalculiX;

public class EIOperator extends Expression {
	
	private Operator op;
	private Expression e1;
	private Expression e2;
	
	public EIOperator(Expression _e1, Operator _op, Expression _e2) {
		e1 = _e1;
		op = _op;
		e2 = _e2;
	}
	
	Value eval() throws EvalException {
		int v1 = e1.eval().asInteger();
		int v2 = e2.eval().asInteger();
		switch (op) {
			case ADDITION:
				return new Integer(v1+v2);
			case MULTIPLICATION:
				return new Integer(v1*v2);
			case SUBTRACTION:
				return new Integer(v1-v2);
			default:
				return new Integer(v1/v2);
		}
	}
	
	Value eval(Environment env) throws EvalException {
		EIOperator result = new EIOperator(new EInteger(e1.eval(env).asInteger()), op, new EInteger(e2.eval(env).asInteger()));
		return result.eval();
	}
	
}
