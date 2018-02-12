package CalculiX;

public class EVariable extends Expression {
	
	private String x;
	
	public EVariable(String _x) {
		x = _x;
	}
	
	public Value eval(Environment env) throws EvalException {
		Value v = env.search(x);
		return v;
	}
	
}
