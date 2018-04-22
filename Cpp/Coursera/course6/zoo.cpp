#include <iostream>
using namespace std;

class Animal
{
public:
  Animal() {cout << "Naissance d'un animal." << endl;}
  ~Animal() { cout << "Mort d'un animal." << endl;}
};

class Vivipare : public virtual Animal
{
public:
  Vivipare(unsigned int g=270) : gestation(g) {cout << "Constructeur Vivipare" << endl;}
  void naissance() const { cout << "Apres " << gestation;
    cout << " jours de gestation, je viens de mettre au monde un nouveau bebe." << endl;
  }
protected:
  unsigned int gestation;
};

class Ovipare : public virtual Animal
{
public:
  Ovipare(unsigned int n = 12) : nboeufs(n) {cout << "Constructeur Ovipare" << endl;}
  void naissance() const {
    cout << "Je viens de pondre environ " << nboeufs << " oeuf(s)." << endl;
  }
protected:
  unsigned int nboeufs;
};

class Ovovivipare : public Vivipare, public Ovipare
{
public:
  Ovovivipare(unsigned int g, unsigned int n, bool _rare = false)
    : Vivipare(g), Ovipare(n), especerare(_rare)
  {cout << "Constructeur Ovovivipare" << endl;}
  void naissance() const {
    cout << "Apres " << gestation << " jours de gestation, je viens de mettre au monde ";
    cout << nboeufs << " nouveau(x) bebe(s)." << endl;
  }
protected:
  bool especerare;
};

int main()
{
  Ovovivipare animalbizarre(250,12,true);
  animalbizarre.Vivipare::naissance();
  animalbizarre.naissance();
  return 0;
}
    
