package CalculiX;

public class Test{

    static ExpressionFactory factory = new ExpressionFactory();

    static public void main(String[] argv) throws EvalException {
	test4();
    }

    static void test1() throws EvalException  {
	Value v1 = factory.buildValue(999);
	Value v2 = factory.buildValue(true);
	System.out.println(v1.asInteger());
	System.out.println(v2.asBoolean());
	try {
	    System.out.println(v1.asBoolean());
	    System.err.println("Erreur une exception est attendue");
	} catch (EvalException e) {
	}
	try {
	    System.out.println(v2.asInteger());
	    System.err.println("Erreur une exception est attendue");
	} catch (EvalException e) {
	}
    }

    static void test2() throws EvalException {
	Expression e1 = factory.buildConstant(999);
	Expression e2 = factory.buildConstant(true);
	System.out.println(e1.eval().asInteger());
	System.out.println(e2.eval().asBoolean());
	try {
	    System.out.println(e1.eval().asBoolean());
	    System.err.println("Erreur une exception est attendue");
	} catch (EvalException e) {
	}
	try {
	    System.out.println(e2.eval().asInteger());
	    System.err.println("Erreur une exception est attendue");
	} catch (EvalException e) {
	}
    }

    static void test3() throws EvalException {
	Expression e = factory.buildIntegerOperation(factory.buildConstant(6),
						     Operator.ADDITION, factory.buildIntegerOperation(
												      factory.buildConstant(3), Operator.MULTIPLICATION,
												      factory.buildConstant(8)));
	System.out.println(e.eval().asInteger());
    }

    static void test4() throws EvalException {
	Expression e = 
	    factory.buildBooleanNegation(
				  factory.buildBooleanOperation(factory.buildConstant(true),
								Connective.AND, factory.buildBooleanOperation(
													 factory.buildConstant(false), Connective.OR,
													 factory.buildConstant(true))));
	System.out.println(e.eval().asBoolean());
    }

}