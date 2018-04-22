#include <iostream>
using namespace std;

class EnsembleFini
{
public:
  EnsembleFini(unsigned int _p)
    : p(_p) {}
protected:
  unsigned int p;
};

class Groupe : public EnsembleFini
{
public:
  Groupe(unsigned int _p) : EnsembleFini(_p) {}
  unsigned int add(unsigned int x, unsigned int y) const { return (x+y) % p;}
};

class Anneau : public Groupe
{
public:
  Anneau(unsigned int _p) : Groupe(_p) {}
  unsigned int multiply(unsigned int x, unsigned int y) const { return (x*y);}
};

class Corps : public Anneau
{
public:
  Corps(unsigned int _p) : Anneau(_p) {}
  unsigned int div(unsigned int x, unsigned int y) const {return multiply(x, inv(y));}
  unsigned int inv(unsigned int x) const {
    if (x > p)
      return inv(x % p);
    long v1(0), u1(1), v2(1), u2(0), b(x), a(p), tmpv2, tmpu2, tmpb, tmpa;
    while (b != 0) {
      // Put computations in temporary variables
      tmpa = b; tmpb = a % b; tmpu2 = u1 - u2 * (a / b); tmpv2 = v1 - v2 * (a / b);
      // Assign the new values for the iteration variables;
      a = tmpa; b = tmpb; u1 = u2; v1 = v2; u2 = tmpu2; v2 = tmpv2;
    }
    if (v1 < 0) return p + (v1 %p);
    return (v1 % p);
  }
};

int main()
{
  Corps k(5);

  cout << "0 + 1 = " << k.add(0,1) << endl;
  cout << "3 + 3 = " << k.add(3,3) << endl;
  cout << "3 * 2 = " << k.multiply(3,2) << endl;
  cout << "1 / 2 = " << k.div(1,2) << endl;
  cout << "3 * 4 = " << k.multiply(3,4) << endl;
  
  return 0;
}
