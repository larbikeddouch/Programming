# Couple of flags that can be used in the whole rest of the file
# Overall they are global variables
CC=g++
CFLAGS=-c -Wall -std=c++11 -I .

# Main Target. This is the only one that will be compiled using make command
# Dependencies will be naturally found
all: main

main: supermarche.o Article.o Achat.o Caddie.o Caisse.o
	$(CC) -o main supermarche.o Article.o Achat.o Caddie.o Caisse.o -I .

supermarche.o: supermarche.cpp
	$(CC) $(CFLAGS) supermarche.cpp

Caisse.o: Caisse.cpp
	$(CC) $(CFLAGS) Caisse.cpp

Caddie.o: Caddie.cpp
	$(CC) $(CFLAGS) Caddie.cpp

Achat.o: Achat.cpp
	$(CC) $(CFLAGS) Achat.cpp

Article.o: Article.cpp
	$(CC) $(CFLAGS) Article.cpp

# Use "make clean" command if you want to use this line
clean:
	rm *o main
