package CalculiX;

public class Integer extends Value {
	
	private int intValue;
	
	Integer(int v) {
		intValue = v;
	}
	
	@Override
	int asInteger() {
		return intValue;
	}
	
}
