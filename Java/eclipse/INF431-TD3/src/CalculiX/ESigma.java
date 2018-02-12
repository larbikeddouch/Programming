package CalculiX;

public class ESigma extends Expression {
	
	private String x;
	private Expression low;
	private Expression high;
	private Expression body;
	
	public ESigma(String _x, Expression _low, Expression _high, Expression _body) {
		x = _x;
		low= _low;
		high = _high;
		body = _body;
	}
	
	public Value eval(Environment env) throws EvalException {
		
		int lowInt = low.eval(env).asInteger();
		int highInt = high.eval(env).asInteger();
		
		int result = 0;
		
		Environment extendedEnv;
		
		for (int i = lowInt; i <= highInt; i++) {
			extendedEnv = env.extend(x, new Integer(i));
			result += body.eval(extendedEnv).asInteger();
		}
		
		return new Integer(result);
	}
	
}
