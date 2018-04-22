#include <iostream>
using namespace std;

/*****************************************************
 * Compléter le code à partir d'ici
 *****************************************************/

class Patient
{
public:
  void init(double _poids, double _taille)
  {
    if (_poids >= 0 && _taille >= 0) {
      masse = _poids;
      hauteur = _taille;
    }
    else {
      masse = 0.0;
      hauteur = 0.0;
    }
  }
  void afficher()
  {
    cout << "Patient : " << masse << " kg pour " << hauteur << " m" << endl;
  }
  double poids() {return masse;}
  double taille() {return hauteur;}
  double imc()
  {
    if (hauteur == 0.0)
      return 0.0;
    return masse / (hauteur * hauteur);
  }
private:
  double hauteur;
  double masse;
};

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/

int main()
{
  Patient quidam;
  double poids, taille;
  do {
    cout << "Entrez un poids (kg) et une taille (m) : ";
    cin >> poids >> taille;
    quidam.init(poids, taille);
    quidam.afficher();
    cout << "IMC : " << quidam.imc() << endl;
  } while (poids * taille != 0.0);
  return 0;
}
