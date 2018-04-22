#include <iostream>
using namespace std;

double surface(double largeur, double longueur);

int main()
{
  double largeur(3.0);
  double longueur(4.0);

  cout << "La surface du rectangle est: " << surface(largeur, longueur) << endl;
}

double surface (double largeur, double longueur) {
  return largeur * longueur;
}
