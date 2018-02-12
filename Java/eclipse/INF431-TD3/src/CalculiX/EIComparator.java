package CalculiX;

public class EIComparator extends Expression {
	
	private Comparator op;
	private Expression e1;
	private Expression e2;
	
	public EIComparator(Expression _e1, Comparator _op, Expression _e2) {
		e1 = _e1;
		op = _op;
		e2 = _e2;
	}
	
	Value eval() throws EvalException {
		int v1 = e1.eval().asInteger();
		int v2 = e2.eval().asInteger();
		switch (op) {
			case LESSTHAN:
				return new Boolean(v1 < v2);
			case LESSTHANOREQUALTO:
				return new Boolean(v1 <= v2);
			default:
				return new Boolean(v1 == v2);
		}
	}
	
	Value eval(Environment env) throws EvalException {
		EIComparator result = new EIComparator(new EInteger(e1.eval(env).asInteger()), op, new EInteger(e2.eval(env).asInteger()));
		return result.eval();
	}
	
}