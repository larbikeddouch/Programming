#include <Achat.h>
using namespace std;

Achat:: Achat(Article& _article, int _quantite)
  : article(_article), quantite(_quantite) {}
void Achat::afficher() {
  cout << article.getNom() << " : ";
  cout << article.getPrixUnitaire() << " x " << quantite << " = " ;
  cout << totalPrice() << " F";
  if (article.getEnSolde())
    cout << " {en action)";
  cout << endl;
}
double Achat::totalPrice() {
  if (article.getEnSolde())
    return quantite * article.getPrixUnitaire() * 0.5;
  return quantite * article.getPrixUnitaire();
}
