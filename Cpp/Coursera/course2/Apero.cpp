#include <iostream>
using namespace std;

class Apero
{
public:
  Apero() { cout << "L'heure de l'apero a sonne !" << endl;}
  void bis() {cout << "Encore une ?" << endl;}
  ~Apero() { cout << "A table !"<< endl;}
};

int main() {
  Apero bic;
  cout << "Super !" << endl;
  bic.bis();
  cout << "Non merci." << endl;  
  return 0;
}
