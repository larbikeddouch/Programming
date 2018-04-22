#include <iostream>
#include <array>
using namespace std;

long compteur(0);

class Rectangle
{
public:
  Rectangle(double l, double h) : largeur(l), hauteur(h) {compteur++;}
  ~Rectangle() {compteur--;}
  double surface() const;
  double getHauteur() const {return hauteur;}
  double getLargeur() const {return largeur;}
  string getLabel() const {return label;}
  array<double,2> getDims() const {return dims;}
  void setHauteur(double h) {hauteur = h;}
  void setLargeur(double l) {largeur = l;}
  void setLabel(string s) {label = s;}
  void setDims(array<double,2> d) {dims = d;}
private:
  double largeur;
  double hauteur;
  string label;
  array<double,2> dims;
};

class Couleur
{
public:
  Couleur(const Couleur& c);
private:
  long couleurasint;
};

class RectangleColore
{
public:
  RectangleColore(double largeur, double hauteur, Couleur c)
    : rect(largeur, hauteur),  couleur(c)
  {}
private:
  Rectangle rect;
  Couleur couleur;
};

double Rectangle::surface() const
{
  return largeur * hauteur;
}

int main()
{
  Rectangle rect1(4.0,5.0);
  cout << "Nombre de rectangles: " << compteur << endl;
  Rectangle r2(3.0,4.0);
  cout << "Nombre de rectangles: " << compteur << endl;
  {
    Rectangle r3(6.0,7.0);
    cout << "Nombre de rectangles: " << compteur << endl;
  }
  cout << "Nombre de rectangles: " << compteur << endl;
  //rect1.setHauteur(3.0);
  //rect1.setLargeur(4.0);
  rect1.setLabel("Toto");
  rect1.setDims({3.0,4.0});
 
  cout << "Hauteur: " << rect1.getHauteur() << endl;
  cout << "Surface: " << rect1.surface() << endl;
  cout << "Label: " << rect1.getLabel() << endl;
  cout << "Dims 1: " << rect1.getDims()[0] << endl;
  cout << "Dims 2: " << rect1.getDims()[1] << endl;

  return 0;
}
