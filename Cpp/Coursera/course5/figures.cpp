#include <iostream>
#include <vector>
#include <math.h>
#include <memory>
using namespace std;

class Figure
{
public:
  Figure() {}
  virtual void affiche() const = 0;
  virtual Figure* copie() const = 0;
  virtual ~Figure() {}
};

class Cercle : public Figure
{
public:
  Cercle(double _r) : Figure(), r(max(0.0,_r))  {
    cout << "Constructeur avec argument du Cercle" << endl;}
  Cercle() : Figure(), r(0.0) { cout << "Constructeur par defaut du Cercle" << endl;}
  Cercle(const Cercle& c) : Figure(), r(c.r) { cout << "Constructeur par copie du Cercle" << endl;}
  ~Cercle() { cout << "Destructeur du Cercle" << endl;}
  void affiche() const override {cout << "Ceci est un cercle de rayon " << r << endl;}
  Figure* copie() const { return new Cercle(*this); }
  //double aire() const override { return M_PI * r * r;}
private:
  double r;
};

class Triangle : public Figure
{
public:
  Triangle(double _b, double _h) : Figure(), base(_b), hauteur(_h) {
    cout << "Constructeur avec argument du Triangle" << endl;}
  Triangle() : Figure(), base(0.0), hauteur(0.0) {
    cout << "Constructeur par defaut du Triangle" << endl ;}
  Triangle(const Triangle& t) : Figure(), base(t.base), hauteur(t.hauteur) {
    cout << "Constructeur par copie du Triangle" << endl;}
  ~Triangle() { cout << "Destructeur du Triangle" << endl;}
  void affiche() const override { cout << "Ceci est un triangle de base " << base;
    cout << " et de hauteur " << hauteur << endl;}
  Figure* copie() const { return new Triangle(*this); }
  //double aire() const override { return base * hauteur; }
private:
  double base;
  double hauteur;
};

class Carre : public Figure
{
public:
  Carre(double _c) : Figure(), cote(_c) {
    cout << "Constructeur avec argument du Carre" << endl;}
  Carre() : Figure(), cote(0.0) { cout << "Constructeur par defaut du Carre" << endl ;}
  Carre(const Carre& c) : Figure(), cote(c.cote) {
    cout << "Constructeur par copie du Carre" << endl ;}
  ~Carre() { cout << "Destructeur du Carre" << endl ;}
  void affiche() const override { cout << "Ceci est un carre de cote " << cote << endl ;}
  Figure* copie() const { return new Carre(*this);}
  //double aire() const override { return cote * cote; }
private:
  double cote;
};

class Dessin
{
public:
  Dessin() {}
  void ajouteFigure(Figure const& f) {
    fs.push_back(f.copie());
  }
  void affiche() const {
    for (auto const& f : fs)
      f->affiche();
  }
  ~Dessin() {
    for (auto f : fs) {
      delete f;
    }
  }
private:
  vector<Figure*> fs;
};

void unCercleDePlus(Dessin const& img) {
  Dessin tmp(img);
  tmp.ajouteFigure(Cercle(1));
  cout << "Affichage de 'tmp' : " << endl;
  tmp.affiche();
}

int main()
{
  Dessin dessin;

  dessin.ajouteFigure(Triangle(3,4));
  //unCercleDePlus(dessin);
  dessin.ajouteFigure(Carre(2));
  dessin.ajouteFigure(Triangle(6,1));
  dessin.ajouteFigure(Cercle(12));

  cout << endl << "Affichage du dessin : " << endl;
  dessin.affiche();
  
  return 0;
}
