#include <Caisse.h>
using namespace std;

int nextNumero(1);

Caisse::Caisse() : total(0) {
  numero = nextNumero;
  nextNumero++;
}
void Caisse::scanner(Caddie& c) {
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
void Caisse::afficher() {
  cout << "La caisse " << numero << " a encaisse " << total << " Frs aujourd'hui.";
}
