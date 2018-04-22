#include <iostream>
#include <vector>
using namespace std;

class Livre
{
public:
  Livre(string _t, string _auteur, unsigned int _p, bool _estseller)
    : titre(_t), auteur(_auteur), pages(_p), bestseller(_estseller) {}
  virtual double calculer_prix() const {
    double prix(PRIX_PAGE * pages);
    if (bestseller)
      prix += 50.0;
    return prix;
  }
  virtual ~Livre() {cout << titre << " jete a la poubelle." << endl;}
  virtual void afficher() const {
    string bAsString("oui");
    if (!bestseller)
      bAsString = "non";
    cout << "Titre : " << titre << endl;
    cout << "Auteur: " << auteur << endl;
    cout << "Nombre de pages : " << pages << endl;
    cout << "Bestseller : " << bAsString << endl;
    cout << "Prix : " << calculer_prix() << endl;
  }
protected:
  static constexpr double PRIX_PAGE = 0.3;
  string titre;
  string auteur;
  unsigned int pages;
  bool bestseller;
};

class Roman : public Livre
{
public:
  Roman(string _t, string _auteur, unsigned int _p, bool _estseller, bool _biographie)
    : Livre(_t,_auteur,_p,_estseller), biographie(_biographie) {}
  virtual ~Roman() {cout << titre << " jete a la poubelle." << endl;}
  virtual void afficher() const override {
    Livre::afficher();
    if (biographie) cout << "Ce roman est une biographie." << endl;
    else cout << "Ce livre n'est pas une biographie." << endl;
  }
protected:
  bool biographie;
};

class Policier :public Roman
{
public:
  Policier(string _t, string _auteur, unsigned int _p, bool _estseller, bool _biographie)
    : Roman(_t,_auteur,_p, _estseller, _biographie) {}
  virtual double calculer_prix() const override {
    double prix(Livre::calculer_prix() - 10.0);
    if (prix <= 0.0) return 1.0;
    return prix;
  }
  virtual ~Policier() {cout << titre << " jete a la poubelle." << endl;}
};

class BeauLivre : public Livre
{
public:
  BeauLivre(string _t, string _auteur, unsigned int _p, bool _estseller)
    : Livre(_t,_auteur,_p,_estseller) {}  
  virtual double calculer_prix() const override {
    return Livre::calculer_prix() + 30.0;
  }
  virtual ~BeauLivre() {cout << titre << " jete a la poubelle." << endl;}
};

class Librairie
{
public:
  Librairie() : livres() {}
  void ajouter_livre(Livre* livre) {
    livres.push_back(livre);
  }
  void afficher() const {
    for (auto const& livre : livres) {
      livre->afficher();
      cout << endl;
    }
  }
  void vider_stock() {
    for (auto livre : livres)
      delete livre;
    livres.clear();
  }
private:
  vector<Livre*> livres;
};

int main()
{
  Librairie l;
  
  l.ajouter_livre(new Livre("Harry Potter a l'ecole des sorciers", "J.K. Rowling", 308, true));
  l.ajouter_livre(new Livre("Le fou", "Gogol", 252, false));
  l.ajouter_livre(new Policier("Le chien des baskerville", "A.C. Doyle", 221, false, false));
  l.ajouter_livre(new Policier("Le parrain", "A. Cuso", 367, true, false));
  l.ajouter_livre(new Roman("Le baron perche", "I. Calvino", 283, false, false));
  l.ajouter_livre(new Roman("Memoires de Geronimo", "S.M. Barrett", 173, false, true));
  l.ajouter_livre(new BeauLivre("Fleuves d'Europe", "C. Osborne", 150, false));

  l.afficher();
  l.vider_stock();
  
  return 0;
}
