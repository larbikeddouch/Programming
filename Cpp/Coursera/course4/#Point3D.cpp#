#include <iostream>
#include <math.h>
using namespace std;

class Point3D
{
public:
  Point3D(double _x, double _y, double _z)
    : x(_x), y(_y), z(_z) {}
  void affiche(ostream& sortie) const
  {
    sortie << "( " << x << ", " << y << ", " << z <<")";
  }
  bool compare(Point3D p)
  {
    return p.x == x && p.y == y && p.z == z;
  }
  double getSquaredDistance(Point3D p) {
    return (p.x-x)*(p.x-x) + (p.y-y)*(p.y-y) + (p.z-z)*(p.z-z);
  }
protected:
  double x;
  double y;
  double z;
};

class Vecteur : public Point3D
{
public:
  Vecteur(double _x, double _y, double _z)
    : Point3D(_x,_y,_z) {}
  Vecteur& operator+=(const Vecteur& v) {
    x += v.x; y += v.y; z += v.z; return *this;
  }
  Vecteur& operator-=(const Vecteur& v) {
    x -= v.x; y -= v.y; z -= v.z; return *this;
  }
  const Vecteur operator-() const {
    return Vecteur(-x,-y,-z);
  }
  Vecteur& operator*=(double d) {
    x *= d; y *= d; z *= d; return *this;
  }
  double operator*(const Vecteur& v) const {
    return x*v.x+y*v.y+z*v.z;
  }
  double norme() const { return sqrt(*this * *this);}
};

ostream& operator<<(ostream& sortie, const Vecteur& v) {
  v.affiche(sortie);
  return sortie;
}
const Vecteur operator+(Vecteur v1, const Vecteur& v2) {
  v1+=v2; return v1;
}
const Vecteur operator-(Vecteur v1, const Vecteur& v2) {
  v1-=v2; return v1;
}
const Vecteur operator*(Vecteur v1, double d) {
  v1*=d; return v1;
}
const Vecteur operator*(double d, const Vecteur& v1) {
  return v1 * d;
}
double operator*(const Vecteur& v1, const Vecteur& v2) {
  return v1.scalarproduct(v2);
}
double angle(const Vecteur& v1, const Vecteur& v2) {
  return acos((v1 * v2) / (v1.norme() * v2.norme()));
}

class VecteurUnitaire : public Vecteur
{
public:
  VecteurUnitaire(double _x, double _y, double _z)
    : Vecteur(_x,_y,_z)
  {
    normalise();
  }
  void normalise() {
    this *= 1.0/Vecteur::norme();
  }
  double norme() {return 1.0;}
  double angle() {return acos(v1 * v2);}
};

int main()
{
  Vecteur v1(1.0,2.0,-0.1);
  Vecteur v2(2.6,3.5,4.1);
  Vecteur v3(0.0,0.0,0.0);
  double d(3.0);

  cout << v1 << " + " << v2 << " = " << v1+v2 << endl;
  cout << v2 << " + " << v1 << " = " << v2+v1 << endl;
  cout << v1 << " + " << v3 << " = " << v1+v3 << endl;
  cout << v3 << " + " << v1 << " = " << v3+v1 << endl;
  cout << v1 << " - " << v2 << " = " << v1-v2 << endl;
  cout << v2 << " - " << v2 << " = " << v2-v2 << endl;
  cout << "- " << v1 << " = " << -v1 << endl;
  cout << "- " << v2 << " + " << v1 << " = " << -v2+v1 << endl;
  cout << d << " * " << v1 << " = " << d*v1 << endl;
  cout << v1 << " * " << v2 << " = " << v1*v2 << endl;
  cout << v2 << " * " << v1 << " = " << v2*v1 << endl;
  cout << "||" << v1 << "|| = " << v1.norme() << endl;
  cout << "||" << v2 << "|| = " << v2.norme() << endl;
  cout << "angle( " << v2 << ", " << v1 << ") = " << angle(v2,v1) << endl;
  
  return 0;
}
