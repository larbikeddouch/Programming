#include <iostream>
using namespace std;

class Peluche
{
public:
  Peluche(string _e, string _name, double _price)
    : e(_e), name(_name), price(_price)
  { cout << "[Une peluche est creee]" << endl;  }
  Peluche(Peluche const& autrePeluche)
    : e(autrePeluche.e), name(autrePeluche.name + "-copie"), price(autrePeluche.price)
  { cout << "[Une peluche est copiee]" << endl;  }
  ~Peluche() { cout << "[La peluche n'existe plus dans l'ordi]" << endl; }
  string getEspece() const {return e;}
  string getName() const {return name;}
  double getPrix() const {return price;}
  void ChangePrix(double nouveauPrix)  {price = nouveauPrix;}
  void Etiquette()
  {
    cout << "Espece: " << e << endl;
    cout << "Name: "  << name << endl;
    cout << "Prix: "  << price << endl;
  }
private:
  string e;
  string name;
  double price;
};

void Etiquette(const Peluche& p)
{
  cout << "Espece: " << p.getEspece() << endl;
  cout << "Name: "  << p.getName() << endl;
  cout << "Prix: "  << p.getPrix() << endl;
}

int main()
{
  Peluche nounours("Lapinou", "Martin", 20);

  Etiquette(nounours);
  
  return 0;
}
