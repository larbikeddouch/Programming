/* This is the specification of the lexical analyzer, whose job is to turn
   a text file into a stream of tokens (also known as terminal symbols). */

/* The terminal symbols are declared inside the file Parser.cup. Out of
   this file, CUP produces (in particular) sym.java, where we find a Java
   version of the symbol definitions. These are just integer constants. */

package CalculiX;

import java_cup.runtime.*;
import static CalculiX.sym.*;
import java.lang.Integer;

%%

%class MyLexer
%unicode
%cup
%cupdebug
%line
%column
%yylexthrow Exception

/* The symbols produced by the lexical analyzer not just integers, but objects
   of type java_cup.runtime.Symbol. To create such an object, one invokes the
   function symbol(), defined below, and supplies an integer constant, which
   identifies a terminal symbol; if necessary, one also supplies a semantic
   value, of an appropriate type -- this must match the type declared for this
   terminal symbol in Parser.cup. */

/* See https://www2.in.tum.de/repos/cup/develop/src/java_cup/runtime/ */

/* Technical note: CUP seems to assume that the two integer parameters
   passed to the Symbol constructor are character counts for the left
   and right positions. Instead, we choose to provide line and column
   information. Accordingly, we will replace CUP's error reporting
   routine with our own. */

%{

    private Symbol symbol (int id)
    {
	return new Symbol (id, yyline, yycolumn);
    }

    private Symbol symbol (int id, Object value)
    {
	return new Symbol (id, yyline, yycolumn, value);
    }

%}

/* Definitions of regular expressions. */

LineTerminator     = \r | \n | \r\n
InputCharacter     = [^\r\n]
WhiteSpace         = {LineTerminator} | [ \t\f]

Comment            = {TraditionalComment} | {EndOfLineComment}
TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment   = "//" {InputCharacter}* {LineTerminator}

Identifier         = [:jletter:] [:jletterdigit:]*

Integer            = [:digit:]+

%%

/* A specification of which regular expressions to recognize and what
   symbols to produce. */

<YYINITIAL> {

    "="
    { return symbol(EQ); }

    "("
    { return symbol(LPAREN); }

    ")"
    { return symbol(RPAREN); }

    "+"
    { return symbol(PLUS); }

    "-"
    { return symbol(MINUS); }

    "*"
    { return symbol(STAR); }

    "/"
    { return symbol(SLASH); }

    "let"
    { return symbol(LET); }

    "in"
    { return symbol(IN); }

    "true"
    { return symbol(TRUE); }

    "false"
    { return symbol(FALSE); }

    "if"
    { return symbol(IF); }

    "then"
    { return symbol(THEN); }

    "else"
    { return symbol(ELSE); }

    "for"
    { return symbol(FOR); }

    "to"
    { return symbol(TO); }

    "sum"
    { return symbol(SUM); }

    "<"
    { return symbol(LT); }

    "<="
    { return symbol(LE); }

    "=="
    { return symbol(EQEQ); }

    "&&"
    { return symbol(AND); }

    "||"
    { return symbol(OR); }

    "!"
    { return symbol(NOT); }

    {Identifier}
    { return symbol(IDENT, yytext().intern()); }
    // The call to intern() allows identifiers to be compared using == .

    {Integer}
    { return symbol(INTEGER, new Integer (yytext())); }

    {Comment}
    { /* ignore */ }

    {WhiteSpace}
    { /* ignore */ }

    .
    { throw new Exception (String.format (
        "Line %d, column %d: illegal character: '%s'\n", yyline, yycolumn, yytext()
      ));
    }

}

