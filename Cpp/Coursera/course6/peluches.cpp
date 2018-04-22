#include <iostream>
using namespace std;

class Animal
{
public:
  Animal(string _n, string _continent) : nom(_n), continent(_continent) {
    cout << "Nouvel animal protege" << endl;
  }
  void affiche() const { cout << "Je suis un " << nom << " et je vis en " << continent << endl;}
  ~Animal() { cout << "Je ne suis plus protege" << endl;}
protected:
  string nom;
  string continent;
};


class EnDanger
{
public:
  EnDanger(unsigned int _nombre) : nombre(_nombre) {
    cout << "Nouvel animal en danger" << endl;
  }
  void affiche() const {
    cout << "Il ne reste que " << nombre << " individus de mon espece sur Terre!" << endl;
  }
  ~EnDanger() { cout << "Ouf! Je ne suis plus en danger" << endl;}
protected:
  unsigned int nombre;
};

class Gadget
{
public:
  Gadget(string _n, double _p) : nom(_n), prix(_p) {
    cout << "Nouveau gadget" << endl;
  }
  void affiche() const {
    cout << "Mon nom est " << nom << endl;
  }
  void affiche_prix() const {
    cout << "Achetez moi pour " << prix << " francs et vous contribuerez a me sauver !" << endl;
  }
  ~Gadget() { cout << "Je ne suis plus un gadget" << endl;}
protected:
  string nom;
  double prix;
};

class Peluche : public Animal, public EnDanger, public Gadget
{
public:
  Peluche(string _nom, string _nomGadget, string _continent,
	  unsigned int _nombre, double _prix)
    : Animal(_nom, _continent), EnDanger(_nombre), Gadget(_nomGadget,_prix) {
    cout << "Creation d'une peluche." << endl;
  }
  void etiquette() const {
    cout << "Hello," << endl;
    Gadget::affiche();
    Animal::affiche();
    EnDanger::affiche();
    affiche_prix();
  }
  ~Peluche() {cout << "Fin d'une peluche!" << endl;}
};

int main()
{
  Peluche panda("Panda","Ming","Asie", 200, 20.0);
  Peluche serpent("Cobra","Ssss","Asie", 500, 10.0);
  Peluche toucan("Toucan","Bello","Amérique", 1000, 15.0);
  
  panda.etiquette();
  serpent.etiquette();
  toucan.etiquette();
  
  return 0;
}
