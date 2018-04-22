#include <Caddie.h>
using namespace std;

Caddie::Caddie() {}
void Caddie::remplir(Article& article) {
  Achat newAchat(article, 1);
  achats.push_back(newAchat);
}
void Caddie::remplir(Article& article,int quantite) {
  Achat newAchat(article, quantite);
  achats.push_back(newAchat);
}
vector<Achat> Caddie::showAchats(){
  vector<Achat> copyOfAchats(achats);
  return copyOfAchats;
}
