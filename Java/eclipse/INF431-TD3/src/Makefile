.PHONY: all clean test

# ------------------------------------------------------------------------------
# Variables.

CLASSPATH := -cp ./lib/java-cup-11a-runtime.jar:.
JAVA      := java -ea $(CLASSPATH)
JAVAC     := javac $(CLASSPATH)
JAVACUP   := java -jar ../lib/java-cup-11a.jar
# Add -dump to dump the grammar and the automaton.
JFLEX     := jflex
SRC       := CalculiX
MAIN      := $(SRC).Main

# ------------------------------------------------------------------------------
# Targets.

all: $(SRC)/MyLexer.java $(SRC)/parser.java $(wildcard $(SRC)/*.java)
	$(JAVAC) CalculiX/*.java

clean:
	rm -f $(SRC)/*~ $(SRC)/*.class
	rm -f $(SRC)/parser.java $(SRC)/sym.java
# We do not remove MyLexer.java, so it is distributed in the archive and
# people don't need jflex to build CalculiX.

test: all
	$(JAVA) $(MAIN)

# ------------------------------------------------------------------------------
# (Semi-)Generic rules.

$(SRC)/parser.java $(SRC)/sym.java: $(SRC)/Parser.cup
	cd $(SRC) && $(JAVACUP) Parser.cup

%.java: %.flex
	rm -f $@
	$(JFLEX) $<

