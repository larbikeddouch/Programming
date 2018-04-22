#include <iostream>
#include <string>
#include <cmath>
using namespace std;

class Flacon
{
private:
  string nom;
  double volume;
  double pH;

public:
  /*****************************************************
    Compléter le code à partir d'ici
  *******************************************************/
  Flacon(string _nom, double _volume, double _pH)
    : nom(_nom), volume(_volume), pH(_pH) {}
  ostream& etiquette(ostream& sortie) const {
    sortie << nom << " : " << volume << " ml, pH " << pH;
    return sortie;
  }
  const Flacon operator+(Flacon const& f2) const {
    Flacon result(nom + " + " + f2.nom, volume + f2.volume, -log10((volume*pow(10.0,-pH)+f2.volume*pow(10.0,-f2.pH))/(volume+f2.volume)));
    return result;
  }

};

ostream& operator<<(ostream& sortie, Flacon const& flacon) {
  flacon.etiquette(sortie);
  return sortie;
}

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/

void afficher_melange(Flacon const& f1, Flacon const& f2)
{
  cout << "Si je mélange " << endl;
  cout << "\t\"" << f1 << "\"" << endl;
  cout << "avec" << endl;
  cout << "\t\"" << f2 << "\"" << endl;
  cout << "j'obtiens :" << endl;
  cout << "\t\"" << (f1 + f2) << "\"" << endl;
}

int main()
{
  Flacon flacon1("Eau", 600.0, 7.0);
  Flacon flacon2("Acide chlorhydrique", 500.0, 2.0);
  Flacon flacon3("Acide perchlorique",  800.0, 1.5);

  afficher_melange(flacon1, flacon2);
  afficher_melange(flacon2, flacon3);

  return 0;

}
