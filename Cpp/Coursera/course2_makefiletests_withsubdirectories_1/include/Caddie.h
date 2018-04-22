#include <Achat.h>
#include <vector>
using namespace std;

class Caddie
{
public:
  Caddie();
  void remplir(Article&);
  void remplir(Article&, int);
  vector<Achat> showAchats();
private:
  vector<Achat> achats;
};
