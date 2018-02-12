package CalculiX;

public class EBoolean extends Expression {
	
	private boolean b;
	
	public EBoolean(boolean _b) {
		this.b = _b;
	}
	
	Value eval() {
		return new Boolean(b);
	}
	
	@Override
	public Value eval(Environment env) {
		return new Boolean(b);
	}
	
}
