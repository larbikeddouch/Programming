package CalculiX;

public class EIfElse extends Expression {
	
	private Expression c;
	private Expression e1;
	private Expression e2;
	
	public EIfElse(Expression _c, Expression _e1, Expression _e2) {
		this.c = _c;
		this.e1 = _e1;
		this.e2 = _e2;
	}
	
	public Value eval() throws EvalException {
		
		boolean condition = c.eval().asBoolean();
		
		if (condition) {
			return e1.eval();
		}
		else
			return e2.eval();
	}
	
	public Value eval(Environment env) throws EvalException {
		boolean condition = c.eval(env).asBoolean();
		
		if (condition) {
			return e1.eval(env);
		}
		else
			return e2.eval(env);
	}
	
}
