#include <iostream>
#include <vector>
using namespace std;

class Employe
{
public:
  Employe(string _nom, string _prenom, int _age, string _e) :
    nom(_nom), prenom(_prenom), age(_age), entree(_e) {}
  virtual double calculer_salaires() const = 0;
  virtual string get_nom() const { return "L'employe " + prenom + " " + nom; }
  virtual ~Employe() {}
protected:
  string nom;
  string prenom;
  int age;
  string entree;
};

class EmployeARsique
{
public:
  EmployeARsique(double _prime = 100.0) : primeMensuelle(_prime) {}
  virtual ~EmployeARsique() {}
protected:
  double primeMensuelle;
};

class Vente : public Employe
{
public:
  Vente(string _nom, string _prenom, int _age, string _e, double _ca)
    : Employe(_nom, _prenom,_age,_e), ca(_ca) {}
  virtual double calculer_salaires() const override { return 0.2*ca + 400.0; }
  virtual string get_nom() const override { return "Le vendeur " + prenom + " " + nom; }
  virtual ~Vente() {}
private:
  double ca;
};

class Representation : public Employe
{
public:
  Representation(string _nom, string _prenom, int _age, string _e, double _ca)
    : Employe(_nom, _prenom,_age,_e), ca(_ca) {}
  virtual double calculer_salaires() const override { return 0.2*ca + 800.0; }
  virtual string get_nom() const override { return "Le representant " + prenom + " " + nom; }
  virtual ~Representation() {}
private:
  double ca;
};

class Producteur : public Employe
{
public:
  Producteur(string _nom, string _prenom, int _age, string _e,  unsigned int _nbUnites)
    : Employe(_nom, _prenom,_age,_e), nbUnites(_nbUnites) {}
  virtual double calculer_salaires() const override { return 5.0 * nbUnites; }
  virtual string get_nom() const override { return "Le producteur " + prenom + " " + nom; }
protected:
  unsigned int nbUnites;
};

class Manut : public Employe
{
public:
  Manut(string _nom, string _prenom, int _age, string _e, unsigned int _nbHeures)
    : Employe(_nom, _prenom,_age,_e), nbHeures(_nbHeures) {}
  virtual double calculer_salaires() const override { return 65.0 * nbHeures; }
  virtual string get_nom() const override { return "Le manut " + prenom + " " + nom; }
protected:
  unsigned int nbHeures;
};

class ProducteurARsique : public Producteur, public EmployeARsique
{
public:
  ProducteurARsique(string _nom, string _prenom, int _age, string _e,
		    unsigned int _nbUnites, double _prime)
    : Producteur(_nom,_prenom,_age,_e,_nbUnites),
      EmployeARsique(_prime) {}
  virtual double calculer_salaires() const {
    return Producteur::calculer_salaires() + primeMensuelle;
  }
  virtual ~ProducteurARsique() {}
};

class ManutARisque : public Manut, public EmployeARsique
{
public:
  ManutARisque(string _nom, string _prenom, int _age, string _e,
	       unsigned int _nbHeures, double _prime)
    : Manut(_nom,_prenom,_age,_e,_nbHeures),
      EmployeARsique(_prime) {}
  virtual double calculer_salaires() const {
    return Manut::calculer_salaires() + primeMensuelle;
  }
  virtual ~ManutARisque() { cout << "Destructeur Manut a risque" << endl;}
};

class Personnel
{
public:
  Personnel() {}
  void ajouter_employe(Employe* e) { personnel.push_back(e); }
  void afficher_salaires() const {
    for (auto const& e : personnel)
      cout << e->get_nom() << " a un salaire de " << e->calculer_salaires() << endl;
  }
  double salaire_moyen() const {
    double salaire_moyen(0.0);
    for (auto const& e : personnel)
      salaire_moyen += e->calculer_salaires();
    return salaire_moyen / personnel.size();
  }
  void licencie() {
    for (auto const& e : personnel)
      delete e;
    personnel.clear();
  }
private:
  vector<Employe*> personnel;
};

int main()
{
  Personnel p;
  p.ajouter_employe(new Vente("Pierre", "Business", 45, "1995", 30000));
  p.ajouter_employe(new Representation("Léon", "Vendtout", 25, "2001", 20000));
  p.ajouter_employe(new Producteur("Yves", "Bosseur", 28, "1998", 1000));
  p.ajouter_employe(new Manut("Jeanne", "Stocketout", 32, "1998", 45));
  p.ajouter_employe(new ProducteurARsique("Jean", "Flippe", 28, "2000", 1000, 200));
  p.ajouter_employe(new ManutARisque("Al", "Abordage", 30, "2001", 45, 120));

  p.afficher_salaires();
  // comment
  cout << "Le salaire moyen dans l'entreprise est de " << p.salaire_moyen() << " francs." << endl;
  // libération mémoire
  p.licencie();
  
  return 0;
}
