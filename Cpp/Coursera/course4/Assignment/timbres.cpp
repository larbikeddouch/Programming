#include <iostream>
#include <string>
using namespace std;

class Timbre
{
private:
  static constexpr unsigned int ANNEE_COURANTE = 2016;

  /*****************************************************
   * Compléter le code à partir d'ici
   *****************************************************/
public:
  Timbre(string _nom, unsigned int _annee,
	 string _pays = "Suisse", double _valeur_faciale = 1.0)
    : nom(_nom), annee(_annee),
      pays(_pays), valeur_faciale(_valeur_faciale) {}
  double vente() const {
    double prixActuel(valeur_faciale);
    if (age() > 5)
      prixActuel *= 2.5 * age();
    return prixActuel;
  }
  unsigned int age() const {
    return ANNEE_COURANTE - annee;
  }
  virtual void affiche(ostream& sortie) const {
    sortie << "Timbre";
    afficher(sortie);
  }
  void afficher(ostream& sortie) const {
    sortie << " de nom " << nom << " datant de " << annee;
    sortie << " (provenance " << pays << ") ayant pour valeur faciale ";
    sortie << valeur_faciale << " francs";
  }
protected:
  string nom;
  unsigned int annee;
  string pays;
  double valeur_faciale;
};

ostream& operator<<(ostream& sortie, const Timbre& t) {
  t.affiche(sortie);
  return sortie;
}

class Rare : public Timbre
{
public:
  Rare(string _nom, unsigned int _annee,
       string _pays="Suisse", double _valeur_faciale = 1.0,
       unsigned int _nombre=100)
    : Timbre(_nom,_annee,_pays,_valeur_faciale), nombre(_nombre) {}
  unsigned int nb_exemplaires() const { return nombre;}
  double vente() const {
    if (nombre < 100) return PRIX_BASE_TRES_RARE * Timbre::age() /10.0;
    if (nombre < 1000) return PRIX_BASE_RARE * Timbre::age() /10.0;
    return PRIX_BASE_PEU_RARE * Timbre::age() /10.0;
  }
  void affiche(ostream& sortie) const {
    sortie << "Timbre rare (" << nombre << " ex.)";
    Timbre::afficher(sortie);
  }
private:
  static constexpr double PRIX_BASE_TRES_RARE = 600.0;
  static constexpr double PRIX_BASE_RARE = 400.0;
  static constexpr double PRIX_BASE_PEU_RARE = 50.0;
  unsigned int nombre;
};

//ostream& operator<<(ostream& sortie, const Rare& t) {
//  t.affiche(sortie);
//  return sortie;
//}

class Commemoratif : public Timbre
{
public:
  Commemoratif(string _nom, unsigned int _annee,
	       string _pays="Suisse", double _valeur_faciale = 1.0)
    : Timbre(_nom,_annee,_pays,_valeur_faciale) {}
  double vente() const { return 2.0*Timbre::vente();}
  void affiche(ostream& sortie) const {
    sortie << "Timbre commemoratif";
    Timbre::afficher(sortie);
  }
};

/*ostream& operator<<(ostream& sortie, const Commemoratif& t) {
  t.affiche(sortie);
  return sortie;
  }*/

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/
int main()
{
  /* Ordre des arguments :
  *  nom, année d'émission, pays, valeur faciale, nombre d'exemplaires
  */
  Rare t1( "Guarana-4574", 1960, "Mexique", 0.2, 98 );
  Rare t2( "Yoddle-201"  , 1916, "Suisse" , 0.8,  3 );

  /* Ordre des arguments :
  *  nom, année d'émission, pays, valeur faciale, nombre d'exemplaires
  */
  Commemoratif t3( "700eme-501"  , 2002, "Suisse", 1.5 );
  Timbre       t4( "Setchuan-302", 2004, "Chine" , 0.2 );

  /* Nous n'avons pas encore le polymorphisme :-(
   * (=> pas moyen de faire sans copie ici :-( )  */
  cout << t1 << endl;
  cout << "Prix vente : " << t1.vente() << " francs" << endl;
  cout << t2 << endl;
  cout << "Prix vente : " << t2.vente() << " francs" << endl;
  cout << t3 << endl;
  cout << "Prix vente : " << t3.vente() << " francs" << endl;
  cout << t4 << endl;
  cout << "Prix vente : " << t4.vente() << " francs" << endl;

  return 0;
}
