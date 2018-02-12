package CalculiX;

// This exception can be thrown by the interpreter.

class EvalException extends Exception {

  // A trivial constructor.
  EvalException (String message) { super(message); }

}
