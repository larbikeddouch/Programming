package CalculiX;

// This enumeration represents the binary Boolean operators.

// We do not include negation because it is a unary operator!
// Of course we could adopt a more general approach and allow
// operators of arbitrary arity, but let's keep things simple
// and pretty.

enum Connective {
  AND,
  OR,
  XOR
}
