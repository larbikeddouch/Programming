#include <iostream>
#include <vector>
using namespace std;

class Article
{
public:
  Article(string _nom, double _prix)
    : nom(_nom), prix(_prix), enSolde(false) {}
  Article(string _nom, double _prix, bool _enSolde)
    : nom(_nom), prix(_prix), enSolde(_enSolde) {}
  Article(const Article& _article)
    : nom(_article.nom), prix(_article.prix), enSolde(_article.enSolde) {}
  string getNom() const { return nom;}
  double getPrixUnitaire() const {return prix;}
  bool getEnSolde() const {return enSolde;}
private:
  string nom;
  double prix;
  bool enSolde;
};

class Achat
{
public:
  Achat(Article& _article, int _quantite)
    : article(_article), quantite(_quantite) {}
  void afficher() {
    cout << article.getNom() << " : ";
    cout << article.getPrixUnitaire() << " x " << quantite << " = " ;
    cout << totalPrice() << " F";
    if (article.getEnSolde())
      cout << " {en action)";
    cout << endl;
  }
  double totalPrice() {
    if (article.getEnSolde())
      return quantite * article.getPrixUnitaire() * 0.5;
    return quantite * article.getPrixUnitaire();
  }
private:
  Article article;
  int quantite;
};

class Caddie
{
public:
  Caddie() {}
  void remplir(Article& article) {
    Achat newAchat(article, 1);
    achats.push_back(newAchat);
  }
  void remplir(Article& article,int quantite) {
    Achat newAchat(article, quantite);
    achats.push_back(newAchat);
  }
  vector<Achat> showAchats(){
    vector<Achat> copyOfAchats(achats);
    return copyOfAchats;
  }
private:
  vector<Achat> achats;
};

int nextNumero(1);

class Caisse
{
public:
  Caisse() : total(0) {
    numero = nextNumero;
    nextNumero++;
  }
  void scanner(Caddie& c) {
    vector<Achat> cAchats(c.showAchats());
    double totalCaddie(0.0);
    for (size_t i(0); i < cAchats.size(); ++i) {
      cAchats[i].afficher();
      totalCaddie += cAchats[i].totalPrice();
    }
    total += totalCaddie;
    cout << "-----" << endl;
    cout << "Total a Payer : " << totalCaddie << " F." << endl;
  }
  void afficher() {
    cout << "La caisse " << numero << " a encaisse " << total << " Frs aujourd'hui.";
  }
private:
  int numero;
  double total;
};

// ======================================================================
int main()
{
  // Les articles vendus dans le supermarché
  Article choufleur ("Chou-fleur extra"      ,  3.50 );
  Article roman     ("Les malheurs de Sophie", 16.50, true );
  Article camembert ("Cremeux 100%MG"        ,  5.80 );
  Article cdrom     ("C++ en trois jours"    , 48.50 );
  Article boisson   ("Petit-lait"            ,  2.50, true);
  Article petitspois("Pois surgeles"         ,  4.35 );
  Article poisson   ("Sardines"              ,  6.50 );
  Article biscuits  ("Cookies de grand-mere" ,  3.20 );
  Article poires    ("Poires Williams"       ,  4.80 );
  Article cafe      ("100% Arabica"          ,  6.90, true);
  Article pain      ("Pain d'epautre"        ,  6.90 );

  // Les caddies du supermarché, disons 3 ici
  vector<Caddie> caddies(3);

  // Les caisses du supermarché, disons 2
  vector<Caisse> caisses(2);

  // Les clients font leurs achats :
  // le second argument de la méthode remplir correspond à une quantité

  // remplissage du 1er caddie
  caddies[0].remplir(choufleur, 2);
  caddies[0].remplir(cdrom       );
  caddies[0].remplir(biscuits , 4);
  caddies[0].remplir(boisson  , 6);
  caddies[0].remplir(poisson  , 2);

  // remplissage du 2eme caddie
  caddies[1].remplir(roman        );
  caddies[1].remplir(camembert    );
  caddies[1].remplir(petitspois, 2);
  caddies[1].remplir(poires    , 2);

  // remplissage du 3eme caddie
  caddies[2].remplir(cafe     , 2);
  caddies[2].remplir(pain        );
  caddies[2].remplir(camembert, 2);

  // Les clients passent à la caisse :
  caisses[0].scanner(caddies[0]);
  cout << "=========================================" << endl;
  caisses[0].scanner(caddies[1]);
  cout << "=========================================" << endl;
  caisses[1].scanner(caddies[2]);
  cout << "=========================================" << endl;

  // Affichage du résultat des caisses
  cout << "Résultats du jour :" << endl;
  for (size_t i(0); i < caisses.size(); ++i) {
    cout << "Caisse " << i+1 << " : " ;
    caisses[i].afficher();
    cout << endl;
  }

  return 0;
}
