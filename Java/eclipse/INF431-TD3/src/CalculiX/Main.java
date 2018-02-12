package CalculiX;

public class Main {

    // Set up the factory.
    static ExpressionFactory factory = new ExpressionFactory();

  // The main method.
  // It reads an integer j from the standard input channel.
  // It executes the j first tests from the table of tests below.

  // Note that the parser and the interpreter can throw an exception.
  // We let this exception escape.

    public static void main (String[] argv) throws Exception
    {
	// Performs the first j tests
	// Change 0 into whichever number of tests you want to perform
	int j = 30;
	
	System.out.println(ex1());
	System.out.println(ex2());
	System.out.println("######################");

	if (argv.length>0) j = java.lang.Integer.parseInt(argv[0]);
	for(int i=0;i<j;i++)
	    interpretAndValidate(input[i],output[i]);
	
	// // Read from the standard input channel.
	// java.io.Reader reader = new java.io.InputStreamReader (System.in);
	// // Parse and interpret.
	// Value v = parseAndInterpret (reader);
	// // Display the value.
	System.out.println("All ok, testing from test0 to test"+(j-1));
    }


  // This method initializes and runs the parser.
  // It requires a lexer and uses the factory.
  // It returns an expression.

  static Expression parse (MyLexer lexer) throws Exception
  {
	// Set up the parser.
	MyParser parser = new MyParser (lexer);
	// Allow the parser to access the expression factory.
	CUP$parser$actions.factory = factory;
	// Run the parser.
	// The method parse() produces a Symbol, whose semantic value is
	// an abstract syntax tree for an expression.
	return (Expression) parser.parse().value;
  }

  // This method runs the parser and the interpreter.
  // It requires a Reader.
  // It produces a Value.

  static Value parseAndInterpret (java.io.Reader reader) throws Exception
  {
    // Set up the lexer.
    MyLexer lexer = new MyLexer (reader);
    // Set up and run the parser.
    Expression e = parse (lexer);
    // Interpret the expression.
    //Value v = e.eval();
    // Value v = e.eval(Environment.empty);
    Value v = e.eval();
    // Return the result.
    return v;
  }
  
  static int ex1() {
	  int result = 0;
	  for (int i = 1; i <=1000; i++) {
		  if ((i%2 == 0) || (i%3==0))
			  result +=i;
	  }
	  return result;
  }
  
  static int ex2() {
	  int result = 0;
	  for (int i = 2; i <=1000; i++) {
		  boolean isNotPrime = false;
		  for (int j = 2; j < i ; j++) {
			  isNotPrime |= (i%j == 0);
		  }
		  if (!isNotPrime)
			  result += i;
	  }
	  return result;
  }

    
    // A test method.
  // It reads from a string.
  // It parses an expression, evaluates it, and compares its value with
  // the expected value (which must be an integer value).

  static void interpretAndValidate (String text, int expectedResult) throws Exception
  {
    // Read from the supplied string.
    java.io.Reader reader = new java.io.StringReader (text);
    // Parse and interpret.
    Value v = parseAndInterpret (reader);
    // Make sure that the actual result is an integer value,
    // and compare with the expected result.
    System.out.println(text + " evaluated as "+v.asInteger()+ ", expecting "+ expectedResult );
    if (expectedResult!=v.asInteger()) throw new Exception();
  }

    static String[] input = 
    {"0",
     "7",
     "2+3",
     "6+(3*8)",

     "2-3",
     "3-2",
     "3-2-1",
     "8/4/2",
     "1-2+3",
     "8/2*4",
     "1+2*3",
     "1+3/2",
     "2*3-1",
     "16/4-1",
     "2 + 3*5 - 30/2 - 2",
     "(5 - 2) * 2 - 24 / (2 + 2)",

     "if true then 0 else 1",
     "if (if false then true else false) then 8 else 1",
     "if 5<4 then 3 else 2",
     "if 1+1<1 || 4<4*4 then 3 else 4",

     "let x = 2 * 21 in x",
     "let x = 2 in let y = 21 in x * y",
     "let x = 2 in let x = 21 in x * x",
     "let x = 2 in let y = x + 19 in x * y",
     "let x = 2 in let x = x + 19 in x * x",
     "(let x = 2 in x + x) * (let x = 3 in x + x)",
     "for x = 0 to 10 sum x",
     "1 + (for x = 0 to 10 sum x * x)",
     "for x = 1 to 1000 sum if x/2*2 == x || x/3*3 ==x then x else 0",
     "2 + (for x = 3 to 1000 sum (if (0 < (for y = 2 to (x-1) sum if x/y*y == x then 1 else 0)) then 0 else x))"
     };

    static int[] output = 
    {0,
     7,
     5,
     30,

     -1,
     1,
     0,
     1,
     2,
     16,
     7,
     2,
     5,
     3,
     0, 
     0,
     
     0,
     1,
     2,
     3,

     42,
     42,
     441,
     42,
     441,
     24,
     55,
     386,
     334167,
     76127
    };

}
