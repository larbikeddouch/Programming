#include <iostream>
#include <vector>
#include <sstream>

using namespace std;

/*****************************************************
  * Compléter le code à partir d'ici
 *****************************************************/

class Produit
{
public:
  Produit(string _nom, string _unites="")
    : nom(_nom), unites(_unites) {}
  string getNom() const { return nom;}
  string getUnite() const {return unites;}
  virtual string toString() const {return nom;}
  //virtual const Produit* adapter(double n) const {return new Produit(nom,unites);}
  virtual const Produit* adapter(double n) const {return this;}
  virtual double quantiteTotale(const string& nomProduit) const {
    if (nom == nomProduit)
      return 1.0;
    return 0.0;
  }
  virtual ~Produit() {}
  protected:
  string nom;
  string unites;
};

// Will need implementation of method adapter() defined later in Produit
class Ingredient
{
public:
  Ingredient(const Produit& _p, double _q)
    : p(&_p), q(_q) {}
  const Produit& getProduit() const {return *p;}
  double getQuantite() const { return q;}
  string descriptionAdaptee() {
    stringstream result;
    const Produit prod;
    prod =*(p->adapter(q));
    result << q << " " << prod.getUnite() << " de " << prod.toString();
    return result.str();
  }
  double quantiteTotale(const string& nomProduit) const {
    return p->quantiteTotale(nomProduit) * q;
  }
  virtual ~Ingredient() {
    //cout << "Detruit " << p->getNom() << endl; delete p;
  }
private:
  const Produit* p;
  double q;
};

class Recette
{
public:
  Recette(string _nom, double _nbFois = 1.0)
    : nom(_nom), nbFois_(_nbFois) {}
  void ajouter(const Produit& p, double quantite) {
    ingredients.push_back(new Ingredient(p,quantite));
  }
  Recette adapter(double n) const {
    Recette result(nom, n*nbFois_);
    for (auto const& ingredient : ingredients) {
      result.ajouter(ingredient->getProduit(), ingredient->getQuantite() * n);
    }
    return result;
  }
  string toString() const {
    stringstream result;
    result << "  Recette \"" << nom << "\" x " << nbFois_ << ":"<< endl;
    for (unsigned int i(0); i< ingredients.size(); ++i) {
      if (i > 0) result << endl;
      result << "  " << (i+1) << ". " << ingredients[i]->descriptionAdaptee();
    }
    return result.str();
  }
  double quantiteTotale(const string& nomProduit) const {
    double result(0.0);
    for (auto const& i : ingredients)
      result += i->quantiteTotale(nomProduit);
    return result;
  }
  virtual ~Recette() {
    //for (unsigned int i(0); i< ingredients.size(); ++i)
    //  delete ingredients[i];
  }
private:
  string nom;
  double nbFois_;
  vector<Ingredient*> ingredients;
};

class ProduitCuisine : public Produit
{
public:
  ProduitCuisine(string _nom)
    : Produit(_nom, "portions(s)"), r(nom,1) {}
  void ajouterARecette(const Produit& produit, double quantite) {
    r.ajouter(produit, quantite);
  }
  virtual const ProduitCuisine* adapter(double n) const override {
    ProduitCuisine* result = new ProduitCuisine(nom);
    result->r = r.adapter(n);
    return result;
  }
  virtual string toString() const override {
    stringstream result;
    result << Produit::toString() << endl;
    result << r.toString();
    return result.str();
  }
  virtual double quantiteTotale(const string& nomProduit) const {
    if (nom == nomProduit)
      return 1.0;
    else
      return r.quantiteTotale(nomProduit);
  }
  virtual ~ProduitCuisine() {}
private:
  Recette r;
};

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/
void afficherQuantiteTotale(const Recette& recette, const Produit& produit)
{
  string nom = produit.getNom();
  cout << "Cette recette contient " << recette.quantiteTotale(nom)
       << " " << produit.getUnite() << " de " << nom << endl;
}

int main()
{
  // quelques produits de base
  Produit oeufs("oeufs");
  Produit farine("farine", "grammes");
  Produit beurre("beurre", "grammes");
  Produit sucreGlace("sucre glace", "grammes");
  Produit chocolatNoir("chocolat noir", "grammes");
  Produit amandesMoulues("amandes moulues", "grammes");
  Produit extraitAmandes("extrait d'amandes", "gouttes");

  ProduitCuisine glacage("glaçage au chocolat");
  // recette pour une portion de glaçage:
  glacage.ajouterARecette(chocolatNoir, 200);
  glacage.ajouterARecette(beurre, 25);
  glacage.ajouterARecette(sucreGlace, 100);
  cout << glacage.toString() << endl;

  ProduitCuisine glacageParfume("glaçage au chocolat parfumé");
  // besoin de 1 portions de glaçage au chocolat et de 2 gouttes
  // d'extrait d'amandes pour 1 portion de glaçage parfumé

  glacageParfume.ajouterARecette(extraitAmandes, 2);
  glacageParfume.ajouterARecette(glacage, 1);
  cout << glacageParfume.toString() << endl;

  Recette recette("tourte glacée au chocolat");
  recette.ajouter(oeufs, 5);
  recette.ajouter(farine, 150);
  recette.ajouter(beurre, 100);
  recette.ajouter(amandesMoulues, 50);
  recette.ajouter(glacageParfume, 2);

  cout << "===  Recette finale  =====" << endl;
  cout << recette.toString() << endl;
  afficherQuantiteTotale(recette, beurre);
  cout << endl;

  // double recette
  Recette doubleRecette = recette.adapter(2);
  cout << "===  Recette finale x 2 ===" << endl;
  cout << doubleRecette.toString() << endl;

  afficherQuantiteTotale(doubleRecette, beurre);
  afficherQuantiteTotale(doubleRecette, oeufs);
  afficherQuantiteTotale(doubleRecette, extraitAmandes);
  afficherQuantiteTotale(doubleRecette, glacage);
  cout << endl;

  cout << "===========================\n" << endl;
  cout << "Vérification que le glaçage n'a pas été modifié :\n";
  cout << glacage.toString() << endl;

  return 0;
}
