#include <iostream>
#include <cmath>
using namespace std;

class Figure
{
public:
  Figure(double _x=0.0, double _y=0.0) : x(_x), y(_y) {}
  void affiche(ostream& sortie) { sortie << "(" << x << ", " << y << ")";}
protected:
  double x;
  double y;
};

class Rectangle : public Figure
{
public:
  Rectangle(double _x=0.0, double _y=0.0, double h = 0.0, double l=0.0)
    : Figure(_x, _y), hauteur(h), largeur(l) {}
  double surface() const { return hauteur * largeur;}
  double getHauteur() const { return hauteur;}
  double getLargeur() const { return largeur;}
  void setHauteur(double h) { hauteur = h;}
  void setLargeur(double l) { largeur = l;}
protected:
  double hauteur;
  double largeur;
};


class RectangleColore : public Rectangle
{
public:
  RectangleColore(double _x=0.0, double _y=0.0, double h=0.0, double l=0.0, unsigned int _c=0.0)
    : Rectangle(_x,_y,h,l), c(_c) {}
private:
  unsigned int c;
};

class Cercle : public Figure
{
public:
  Cercle(double _x=0.0, double y=0.0, double _r=0.0)
    : Figure(x,y),  r(_r) {}
  double surface() const {return M_PI * r * r;}
  double getRayon() const {return r;}
  void setRayon(double _r) {
    if (_r <0.0) r = 0.0;
    else r = _r;
  }
private:
  double r;
};

int main()
{
  RectangleColore r(4.3,12.5,4);
  cout << r.getLargeur() << endl;
  return 0;
}
