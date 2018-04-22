#include <iostream>
#include <string>
using namespace std;

class Souris
{
  /*****************************************************
    Compléter le code à partir d'ici
  *******************************************************/
public:
  Souris(double _poids, string _couleur, unsigned int _age = 0, unsigned int _esperance_vie = 36)
    : poids(_poids), couleur(_couleur), age(_age),
      esperance_vie(_esperance_vie), clonee(false) {cout << "Une nouvelle souris !"<< endl;}
  Souris(Souris const& s)
    : poids(s.poids), couleur(s.couleur), age(0),
      esperance_vie(s.esperance_vie*4.0/5.0), clonee(true) {cout << "Clonage d'une souris !"<< endl;}
  ~Souris() {
    cout << "Fin d'une souris..." << endl;
  }
  void afficher() {
    cout << "Une souris " << couleur;
    if (clonee)
      cout << ", clonee,";
    cout << " de " << age << " mois  et pesant " << poids << " grammes" << endl;
  }
  void vieillir() {
    age += 1;
    if (age >= esperance_vie * 0.5  && clonee)
      couleur = "verte";
  }
  void evolue() {
    while (age < esperance_vie)
      vieillir();
  }
private:
  double poids;
  string couleur;
  unsigned int age;
  unsigned int esperance_vie;
  bool clonee;
  /*******************************************
   * Ne rien modifier après cette ligne.
   *******************************************/
  
}; // fin de la classe Souris

int main()
{
  Souris s1(50.0, "blanche", 2);
  Souris s2(45.0, "grise");
  Souris s3(s2);
  // ... un tableau peut-être...
  s1.afficher();
  s2.afficher();
  s3.afficher();
  s1.evolue();
  s2.evolue();
  s3.evolue();
  s1.afficher();
  s2.afficher();
  s3.afficher();
  return 0;
}
