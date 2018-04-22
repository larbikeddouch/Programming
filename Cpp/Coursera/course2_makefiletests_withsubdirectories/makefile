# Couple of flags that can be used in the whole rest of the file
# Overall they are global variables
# Name of compiler to use
CC=g++
# -c is a variable that allows g++ to compile without doing any linkage
# ie g++ will not look for a main function of something like that
# -Wall is here for compilation error messages (classic tag)
# -std is the type of C++ that we want to use to compile
# -I lists all of the subdirectories to use to compile
# in this case, we included only the header files directory (include)
CFLAGS=-c -Wall -std=c++11 -I./include
# makefile is in a directory. VPATH allows it to know that where to find source files
VPATH=./src

.PHONY: clean

# Main Target. This is the only one that will be compiled using make command
# Dependencies will be naturally found
all: main

# $^ is a way to specify all the prerequisites mentioned in the first line of the recipe
# It is a type of automated variable. Just like $@ is an alias for the name of the targrt file
main: supermarche.o Article.o Achat.o Caddie.o Caisse.o
	$(CC) $^ -o $@

supermarche.o: supermarche.cpp
	$(CC) $(CFLAGS) $^

Caisse.o: Caisse.cpp
	$(CC) $(CFLAGS) $^

Caddie.o: Caddie.cpp
	$(CC) $(CFLAGS) $^

Achat.o: Achat.cpp
	$(CC) $(CFLAGS) $^

Article.o: Article.cpp
	$(CC) $(CFLAGS) $^

# Use "make clean" command if you want to use this line
clean:
	rm *o
	rm main
