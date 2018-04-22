#include <Article.h>
using namespace std;

class Achat
{
public:
  Achat(Article&, int);
  void afficher();
  double totalPrice();
private:
  Article article;
  int quantite;
};
