#include <iostream>
using namespace std;

class Animal
{
public:
  Animal(string _n, string _continent) : nom(_n), continent(_continent) {}
  void affiche() const { cout << "Je suis un " << nom << " et je vis en " << continent << endl;}
protected:
  string nom;
  string continent;
};


class EnDanger : public Animal
{
public:
  EnDanger(string _n, string _continent, unsigned int _nombre)
    : Animal(_n,_continent), nombre(_nombre) {}
  void affiche() const {
    cout << "Il ne reste que " << nombre << " individus de mon espece sur Terre!" << endl;
  }
protected:
  unsigned int nombre;
};

class Gadget
{
public:
  Gadget(string _n, double _p) : nom(_n), prix(_p) {}
  void affiche() const {
    cout << "Mon nom est " << nom << endl;
  }
  void affiche_prix() const {
    cout << "Achetez moi pour " << prix << " francs et vous contribuerez a me sauver !" << endl;
  }
protected:
  string nom;
  double prix;
};
