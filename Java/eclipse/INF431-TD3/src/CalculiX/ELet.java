package CalculiX;

public class ELet extends Expression {
	
	private String x;
	private Expression e;
	private Expression result;
	
	public ELet(String _x, Expression _e, Expression _result) {
		x = _x;
		e = _e;
		result = _result;
	}
	
	@Override
	public Value eval(Environment env) throws EvalException {
		Environment extendedEnv = env.extend(x, e.eval(env));
		return result.eval(extendedEnv);
	}
	
}
