#include <iostream>
#include <array>
using namespace std;

class Rectangle
{
public:
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

double Rectangle::surface() const
{
  return largeur * hauteur;
}

int main()
{
  Rectangle rect1;

  rect1.setHauteur(3.0);
  rect1.setLargeur(4.0);
  rect1.setLabel("Toto");
  rect1.setDims({3.0,4.0});

  cout << "Hauteur: " << rect1.getHauteur() << endl;
  cout << "Surface: " << rect1.surface() << endl;
  cout << "Label: " << rect1.getLabel() << endl;
  cout << "Dims 1: " << rect1.getDims()[0] << endl;
  cout << "Dims 2: " << rect1.getDims()[1] << endl;

  return 0;
}
