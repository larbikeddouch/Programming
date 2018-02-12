package CalculiX;

public class Boolean extends Value {
	
	private boolean boolValue;
	
	public Boolean(boolean b) {
		boolValue = b;
	}
	
	@Override
	boolean asBoolean() {
		return boolValue;
	}
}
