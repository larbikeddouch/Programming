package CalculiX;

abstract class Environment {

  // The empty environment.
  static final Environment empty = new EnvironmentNil ();

  // Extending an environment with a new entry.
  Environment extend (String variable, Value value)
  {
    return new EnvironmentCell (variable, value, this);
  }

  // Searching an environment for the definition of a variable.
  // The search returns the value associated with this variable.
  // A search can fail if a variable is used without having been
  // declared.
  abstract Value search (String variable) throws EvalException;

}

class EnvironmentNil extends Environment {

  EnvironmentNil ()
  {
  }

  Value search (String x) throws EvalException
  {
    // If we reach this point, then the program contains a reference
    // to a variable that has not been declared.
    throw new EvalException (
      "The variable " + x + " has not been declared."
    );
  }

}

class EnvironmentCell extends Environment {

    // The name of the variable.
    final String variable;

    // The value of this variable.
    final Value value;

    // The rest of the environment.
    // Environments form linked lists. This is not very efficient, but
    // it is very simple.
    final Environment tail;

    EnvironmentCell (String variable, Value value, Environment tail)
    {
	this.variable = variable;
	this.value = value;
	this.tail = tail;
    }

    Value search (String variable) throws EvalException
    {
	if (this.variable == variable)
	    return value;
	else
	    return tail.search(variable);
    }
}

