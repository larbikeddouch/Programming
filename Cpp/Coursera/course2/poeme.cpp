#include <iostream>
using namespace std;

class Fleur
{
public:
  Fleur(string _espece, string _couleur)
    : espece(_espece), couleur(_couleur) {cout << _espece << " fraichement cueillie" << endl;}
  Fleur(Fleur const& f)
    : espece(f.espece), couleur(f.couleur) {cout << "Fragile corolle taillee" <<endl;}
  void eclore() {cout << "veine de " << couleur << endl;}
  ~Fleur() { cout << "qu'un simple souffle..." << endl;}
private:
  string espece;
  string couleur;
};

int main() {
  Fleur f1("Violette", "bleu");
  Fleur f2(f1);
  cout << "dans un cristal ";
  f2.eclore();
  cout << "Donne un poeme un peu fleur bleue" << endl << "ne laissaint plus ";
  return 0;
}
