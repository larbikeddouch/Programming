#include <iostream>
using namespace std;

class Complexe
{
public:
  Complexe() : x(0.0), y(0.0) {}
  Complexe(double _x, double _y)
    : x(_x), y(_y) {}
  Complexe(Complexe const& z)
    : x(z.x), y(z.y) {}

  const Complexe operator+=(Complexe const& z){
    x+=z.x;
    y+=z.y;
    return *this;
  }
  const Complexe operator+=(double d){
    x+=d;
    return *this;
  }
  const Complexe operator-=(Complexe const& z){
    x-=z.x;
    y-=z.y;
    return *this;}
  const Complexe operator*=(Complexe const& z){
    double xtmp = x * z.x - y * z.y;
    double ytmp = x * z.y + y * z.x;
    x = xtmp;
    y = ytmp;
    return *this;
  }
  const Complexe operator*=(double d){
    x*=d;
    y*=d;
    return *this;
  }
  const Complexe operator/=(Complexe const& z){
    double xtmp = (x * z.x + y * z.y) / (z.x*z.x + z.y*z.y);
    double ytmp = ( - x * z.y + y * z.x) / (z.x*z.x + z.y*z.y);
    x = xtmp;
    y = ytmp;
    return *this;
  }
  const Complexe operator/=(double d){
    x /= d;
    y /= d;
    return *this;
  }
  const Complexe operator+(Complexe const& z) const
  {
    Complexe tmp(*this);
    tmp += z;
    return tmp;
  }
  const Complexe operator+(double d) const {
    Complexe tmp(*this);
    tmp += d;
    return tmp;
  }
  const Complexe operator*(Complexe const& z) const
  {
    Complexe tmp(*this);
    tmp *= z;
    return tmp;
  }
  const Complexe operator*(double d) const
  {
    Complexe tmp(*this);
    tmp *= d;
    return tmp;
  }
  const Complexe operator/(Complexe const& z) const
  {
    Complexe tmp(*this);
    tmp /= z;
    return tmp;
  }
  const Complexe operator/(double d) const
  {
    Complexe tmp(*this);
    tmp /= d;
    return tmp;
  }
  double getX() const {return x;}
  double getY() const {return y;}
  void afficher()
  {
    cout << x << "+" << y << "i" << endl;
  }
  bool operator==(Complexe const& z) const{
    return (x == z.x) && (y == z.y);
  }
private:
  double x;
  double y;
};

const Complexe operator+(double d, Complexe const& z) {
  return z.operator+(d);
}
const Complexe operator*(double d, Complexe const& z) {
  return z.operator*(d);
}

ostream& operator<<(ostream& op, Complexe const& z){
  op << z.getX() << '+' << z.getY() << 'i';
  return op;
}

int main()
{
  /* Initial Personal Tests during the class
  Complexe z1(1.0,2.0);
  Complexe z2(2.0,3.0);

  Complexe z3 = z1+z2;
  Complexe z4 = z1*z2;
  
  z1.afficher();
  z2.afficher();
  z3.afficher();
  z4.afficher();

  cout << z1 << endl;*/

  Complexe defaut;
  Complexe zero(0.0, 0.0);
  Complexe un(1.0, 0.0);
  Complexe i(0.0, 1.0);
  Complexe j;

  cout << zero << " ==? " << defaut;
  if (zero == defaut)
    cout << " oui" << endl;
  else
    cout << " non" << endl;

  cout << zero << " ==? " << i;
  if (zero == i)
    cout << " oui" << endl;
  else
    cout << " non" << endl;

  j = un + i;
  cout << un << " + " << i << " = " << j << endl;

  Complexe trois(un);
  trois += un;
  trois += 1.0;
  cout << un << " + " << un << " + " << 1.0 << " = " << trois << endl;

  Complexe deux(trois);
  deux -= un;
  cout << trois << " - " << un << " = " << deux << endl;

  trois = 1.0 + deux;;
  cout << "1.0 + " << deux << " = " << trois << endl;

  Complexe z(i*i);
  cout << i << " * " << i << " = " << z << endl;
  cout << z << " / " << i << " = " << z/i << endl;
  cout << (z/=i) << endl;

  Complexe k(2.0,3.0);
  z = k;
  z *= 2.0;
  z *= i;
  cout << k << " * 2.0 * " << i << " = " << z << endl;
  z = 2.0 * k * i /1.0;
  cout << "2.0 * " << k << " * " << i << " / 1 = " << z << endl;
  
  return 0;
}
