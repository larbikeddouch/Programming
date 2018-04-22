#include <iostream>
#include <vector>
using namespace std;

class Forme
{
public:
  Forme() {}
  virtual void description() const {cout << "Ceci est une forme !"<< endl;}
};

class Cercle : public Forme
{
public:
  Cercle() : Forme() {}
  void description() const {cout << "Ceci est un cercle." << endl;}
};

void affichageDesc(Forme& f)
{
  f.description();
}

int main()
{
  Forme f;
  Cercle c;
  affichageDesc(f);
  affichageDesc(c);
  
  return 0;
}
