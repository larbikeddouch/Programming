package CalculiX;

public class EInteger extends Expression {
	
	private int val;
	
	public EInteger(int i) {
		val = i;
	}
	
	public Value eval() {
		return new Integer(val);
	}
	
	@Override
	public Value eval(Environment env) {
		return new Integer(val);
	}
	
}
