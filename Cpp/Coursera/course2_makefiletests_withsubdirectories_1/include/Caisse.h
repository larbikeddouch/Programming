#include <Caddie.h>
using namespace std;

class Caisse
{
public:
  Caisse();
  void scanner(Caddie&);
  void afficher();
private:
  int numero;
  double total;
};
