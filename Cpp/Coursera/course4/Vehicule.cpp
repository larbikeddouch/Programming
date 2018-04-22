#include <iostream>
#include <math.h>
#include <vector>
using namespace std;

unsigned int aujourdhui(2015);

class Vehicule
{
public:
  Vehicule(string _m, unsigned int _date, double _p)
    : marque(_m), date(_date), prixAchat(_p), prixActuel(_p) {}
  //void setAujourdhui(unsigned int _a) {aujourdhui = _a;}
  void affiche(ostream& sortie) const {
    sortie << "Marque : " << marque;
    sortie << ", date d'achat : " << date;
    sortie << ", prix d'achat : " << prixAchat;
    sortie << ", prix actuel : " << prixActuel;
    sortie << endl;
  }
  void calculePrix() {
    prixActuel -= prixAchat * 1.0/100.0 * (aujourdhui - date);
    prixActuel = max(prixActuel, 0.0);
  }
protected:
  //static unsigned int aujourdhui;
  string marque;
  unsigned int date;
  double prixAchat;
  double prixActuel;
};

class Voiture : public Vehicule
{
public:
  Voiture(string _m, unsigned int _date, double _p, double _c,
	  unsigned int _nbportes, double _pu, unsigned int _k)
    : Vehicule(_m,_date,_p), cylindree(_c), nbportes(_nbportes),
      puissance(_pu), kilometrage(_k) {}
  void affiche(ostream& sortie) const {
    sortie << " ---- Voiture ----"<< endl;
    Vehicule::affiche(sortie);
    sortie << cylindree << " litres, ";
    sortie << nbportes << " portes, ";
    sortie << puissance << " CV, ";
    sortie << kilometrage << " km";;
    sortie << endl;
  }
  void calculePrix() {
    prixActuel -= 2.0/100.0*(aujourdhui-date)*prixAchat;
    double nb10K(0.0);
    nb10K=round(kilometrage/10000.0);
    prixActuel -= 5.0/100.0*nb10K*prixAchat;
    if (marque == "Renault" || marque == "Fiat")
      prixActuel -= prixAchat * 0.1;
    if (marque == "Ferrari" || marque == "Porsche")
      prixActuel += prixAchat * 0.2;
    prixActuel = max(prixActuel, 0.0);
  }
private:
  double cylindree;
  unsigned int nbportes;
  double puissance;
  unsigned int kilometrage;
};

enum type_avion
  {
    HELICES,
    REACTION
  };

class Avion : public Vehicule
{
public:
  Avion(string _m, unsigned int _date, double _p, type_avion _t, unsigned int _h)
    : Vehicule(_m, _date, _p), type(_t), heuresvol(_h) {}
  void affiche(ostream& sortie) const {
    if (type == HELICES) sortie << " ---- Avion a helices ----" << endl;
    if (type == REACTION) sortie << " ---- Avion a reaction ----" << endl;
    Vehicule::affiche(sortie);
    sortie << heuresvol << " heures de vol.";
    sortie << endl;
  }
  void calculePrix() {
    Vehicule::calculePrix();
    if (type == HELICES) {
      double nb100(round(heuresvol/100.0));
      prixActuel -= nb100 * prixAchat * 10.0/100.0;
    }
    if (type == REACTION) {
      double nb1000(round(heuresvol/1000.0));
      prixActuel -= nb1000 * prixAchat * 10.0/100.0;
    }
    prixActuel = max(prixActuel, 0.0);
  }
private:
  type_avion type;
  unsigned int heuresvol;
};

int main()
{
  vector<Voiture> garage;
  vector<Avion> hangar;
  
  garage.push_back(Voiture("Peugeot", 1998, 147325.79, 2.5, 5, 180.0,
			   12000));
  garage.push_back(Voiture("Porsche", 1985, 250000.00, 6.5, 2, 280.0,
			   81320));
  garage.push_back(Voiture("Fiat",
			   2001,
			   7327.30, 1.6, 3, 65.0,
			   3000));
  hangar.push_back(Avion("Cessna",
			 1972, 1230673.90, HELICES,
			 250));
  hangar.push_back(Avion("Nain Connu", 1992, 4321098.00, REACTION,
			 1300));

  // Vehicule::setAujourdhui(2015);
  
  for (auto voiture : garage) {
    voiture.calculePrix();
    voiture.affiche(cout);
  }
  for (auto avion : hangar) {
    avion.calculePrix();
    avion.affiche(cout);
  }
  
  return 0;
}
