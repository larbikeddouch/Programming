#include <iostream>
#include <cmath>
using namespace std;

class Cercle
{
public:
  void getCentre(double& x, double& y) const;
  void getRayon(double& r) const;
  void setCentre(double x, double y);
  void setRayon(double r);
  double surface() const;
  bool estInterieur(double x, double y) const;
private:
  double x_centre;
  double y_centre;
  double rayon;
};

void Cercle::getCentre(double& x, double& y) const
{
  x = x_centre;
  y = y_centre;
}
void Cercle::getRayon(double& r) const
{
  r = rayon;
}
void Cercle::setCentre(double x, double y)
{
  x_centre = x;
  y_centre = y;
}
void Cercle::setRayon(double r)
{
  rayon = r;
}
double Cercle::surface() const
{
  return M_PI * rayon * rayon;
}
bool Cercle::estInterieur(double x, double y) const
{
  return (x-x_centre)*(x-x_centre) + (y-y_centre)*(y-y_centre) <= rayon*rayon;
}

int main()
{
  Cercle c;
  c.setCentre(1.0,2.0);
  c.setRayon(3.0);
  
  cout << "The value of pi is: " << M_PI << endl;
  cout << "Surface is: " << c.surface() << endl;

  double x,y;
  c.getCentre(x,y);

  cout << "The coordinates of the center are x = " << x << " and y = " << y << endl;

  cout << boolalpha << "Point 1,3 is inside? " << c.estInterieur(1,3) << endl;
  cout << boolalpha << "Point 10,3 is inside? " << c.estInterieur(10,3) << endl;  
  
  return 0;
}
