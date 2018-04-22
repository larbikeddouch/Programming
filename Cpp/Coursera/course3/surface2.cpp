#include <iostream>
#include <array>
using namespace std;

class Rectangle
{
public:
  Rectangle(double l, double h) : largeur(l), hauteur(h) {compteur++;}
  ~Rectangle() {compteur--;}
  double surface() const;
  double getHauteur() const {return hauteur;}
  double getLargeur() const {return largeur;}
  string getLabel() const {return label;}
  void setHauteur(double h) {hauteur = h;}
  void setLargeur(double l) {largeur = l;}
  void setLabel(string s) {label = s;}
  // compteur is here for the example. it should also be private
  static int compteur;
private:
  double largeur;
  double hauteur;
  string label;
};

int Rectangle::compteur(10);

double Rectangle::surface() const
{
  return largeur * hauteur;
}

int main()
{
  Rectangle rect1(4.0,5.0);
  cout << "Nombre de rectangles: " << Rectangle::compteur << endl;
  Rectangle r2(3.0,4.0);
  cout << "Nombre de rectangles: " << Rectangle::compteur << endl;
  {
    Rectangle r3(6.0,7.0);
    cout << "Nombre de rectangles: " << Rectangle::compteur << endl;
  }
  cout << "Nombre de rectangles: " << Rectangle::compteur << endl;
  //rect1.setHauteur(3.0);
  //rect1.setLargeur(4.0);
  rect1.setLabel("Toto");
 
  cout << "Hauteur: " << rect1.getHauteur() << endl;
  cout << "Surface: " << rect1.surface() << endl;
  cout << "Label: " << rect1.getLabel() << endl;
  
  return 0;
}
