#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

class Forme
{
public:
  Forme() {}
  virtual void description() const {cout << "Ceci est une forme !"<< endl;}
  virtual double aire() const = 0;
};

class Cercle : public Forme
{
public:
  Cercle(double _r) : Forme(), r(max(0.0,_r)) {}
  void description() const override {cout << "Ceci est un cercle de rayon " << r << endl;}
  double aire() const override { return M_PI * r * r;}
private:
  double r;
};

class Triangle : public Forme
{
public:
  Triangle(double _b, double _h) : Forme(), base(_b), hauteur(_h) {}
  void description() const override { cout << "Ceci est un triangle de base " << base;
    cout << " et de hauteur " << hauteur << endl;}
  double aire() const override { return base * hauteur; }
private:
  double base;
  double hauteur;
};

void affichageDesc(Forme& f)
{
  f.description();
  cout << "Son aire est : " << f.aire() << endl;
}

int main()
{
  Cercle c(5.0);
  Triangle t(10,2);
  affichageDesc(c);
  affichageDesc(t);
  
  return 0;
}
