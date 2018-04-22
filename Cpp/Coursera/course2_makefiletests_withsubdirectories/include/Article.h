#include <iostream>
using namespace std;

class Article
{
public:
  Article(string, double);
  Article(string, double, bool);
  Article(const Article&);
  string getNom() const;
  double getPrixUnitaire() const;
  bool getEnSolde() const;
private:
  string nom;
  double prix;
  bool enSolde;
};
