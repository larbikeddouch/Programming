package CalculiX;

abstract class Value {

  // Values include Boolean values and integer values.

  // The following methods attempt to cast a value down to one of
  // these forms, and fail if the value is not of the expected form.

  // The default implementations of these methods cause a failure.
  // Each of them is redefined in one sub-class only, as appropriate.

  boolean asBoolean () throws EvalException
  {
    throw new EvalException (
      "Expected a boolean value as object for asBoolean(), but got a " + this.getClass().getName() + " object instead."
    );
  }

  int asInteger () throws EvalException
  {
    throw new EvalException (
      "Expected an integer value as object for asInteger(), but got a " + this.getClass().getName() + " object instead."
    );
  }

}

