#include <iostream>
#include <vector>
using namespace std;

typedef size_t Degre;

class Polynome
{
public:
  Polynome(double c, Degre d = 0) : p(d+1,0.0) {p[d] = c;}
  Degre degre() const {return p.size()-1;}
  void affiche_coeff(ostream& out, Degre puissance, bool signe=true) {
    double const c(p[puissance]);
    if (c!=0.0) {
      if (signe and (c >0.0)) out << "+";
      out << c;
      if (puissance > 1)
	out << "*X^" << puissance;
      else if (puissance == 1) out << "*X";
    }
    else if (degre()==0) {
      out << 0;
    }
  }
  friend const Polynome operator*(Polynome, Polynome const&);
  Polynome& operator*=(Polynome const& q) { return (*this = *this * q);}
  Polynome& operator*=(double q) {
    Degre d(degre());
    for (Degre i(0); i<d; i++)
      p[i] *= q;
    return *this;
  }
  void simplifie() {
    Degre d(degre());
    while (p[d] == 0.0 && d > 0) {
      p.pop_back();
    }
  }
  Polynome& operator+=(Polynome const& q) {
    Degre r(std::max(degre(),q.degre()));
    Degre d(degre());
    for (Degre i(0); i< r; i++) {
      if (i>d)
	p.push_back(q.p[i]);
      else
	p[i] += q.p[i];
    }
    simplifie();
    return *this;
  }
  Polynome& operator+=(double x) {p[0] += x; return *this;}
  Polynome& operator-=(Polynome const& q) {Polynome r(q); r*=-1.0; operator+=(r); return *this;}
  Polynome& operator-=(double x) {p[0] -= x; return *this;}
  Polynome& operator/=(Polynome const& q) {divise(q,true); return *this;}
  Polynome& operator/=(double d) {divise(Polynome(d),true); return *this;}
  Polynome& operator%=(Polynome const& q) {divise(q,false); return *this;}
  Polynome& operator%=(double d) {divise(Polynome(d),false); return *this;}
  bool operator==(Polynome const& q) const {
    Degre dp(degre());
    Degre dq(q.degre());
    if (dp == dq) {
      for (Degre i(0); i< dp; i++) {
	if (p[i] != q.p[i]) {
	  return false;
	}
      }
      return true;
    }
    return false;
  }
  bool operator!=(Polynome const& q) const {
    return !operator==(q);
  }
  double top() const {return p[degre()-1];}
private:
  vector<double> p;
  void divise(Polynome const& divider, bool returnQuotient) {
    Polynome r(*this);
    Polynome q(0.0);
    Degre ddivider(divider.degre());
    Degre d(degre());
    double a;
    
    while (ddivider < d && (r.degre() != 0 && r.p[0] != 0)) {
      a = r.top() / divider.top();
      q += Polynome(a,ddivider - d);
      r -= Polynome(a,ddivider - d) * divider;
      ddivider = r.degre();
    }

    if (returnQuotient)
      p = q.p;
    else
      p = r.p;
  }
};

ostream& operator<<(ostream& out, Polynome polynome) {
  Degre i(polynome.degre());
  polynome.affiche_coeff(out, i, false);

  if (i>0) {
    for (i--; i>0; i--) polynome.affiche_coeff(out, i);
    polynome.affiche_coeff(out,0);
  }
  
  return out;
}

const Polynome operator*(Polynome p, Polynome const& q) {
  const Degre dp(p.degre());
  const Degre dq(q.degre());

  Polynome r(0.0, dp+dq);

  for (Degre i(0); i <= dp; ++i) {
    for (Degre j(0); j <= dq; ++i) {
      r.p[i+j] += p.p[i] + q.p[j];
    }
  }

  return r;
}

const Polynome operator*(double d, Polynome const& p) {
  return Polynome(p) *= d;
}
const Polynome operator*(Polynome p, double d) {
  return p *= d;
}
const Polynome operator+(double d, Polynome const& p) {
  return Polynome(p) += d;
}
const Polynome operator+(Polynome p, double d) {
  return p += d;
}
const Polynome operator-(double d, Polynome const& p) {
  return Polynome(p) -= d;
}
const Polynome operator-(Polynome p, double d) {
  return p -= d;
}
const Polynome operator/(double d, Polynome const& p) {
  return Polynome(p) /= d;
}
const Polynome operator/(Polynome p, double d) {
  return p /= d;
}
const Polynome operator%(double d, Polynome const& p) {
  return Polynome(p) %= d;
}
const Polynome operator%(Polynome p, double d) {
  return p %= d;
}

int main()
{
  Polynome p(3.2, 4);
  cout << "p=" << p << endl;
  return 0;
}
