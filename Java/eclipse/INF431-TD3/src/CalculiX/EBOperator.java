package CalculiX;

public class EBOperator extends Expression {
	private Connective op;
	private Expression e1;
	private Expression e2;
	
	public EBOperator(Expression _e1, Connective _op, Expression _e2) {
		e1 = _e1;
		op = _op;
		e2 = _e2;
	}
	
	Value eval() throws EvalException {
		boolean v1 = e1.eval().asBoolean();
		boolean v2 = e2.eval().asBoolean();
		switch (op) {
			case AND:
				return new Boolean(v1 && v2);
			case OR:
				return new Boolean(v1 || v2);
			default:
				return new Boolean(v1 ^ v2);
		}
	}
	
	Value eval(Environment env) throws EvalException {
		EBOperator result = new EBOperator(new EBoolean(e1.eval(env).asBoolean()), op, new EBoolean(e2.eval(env).asBoolean()));
		return result.eval();
	}
}
