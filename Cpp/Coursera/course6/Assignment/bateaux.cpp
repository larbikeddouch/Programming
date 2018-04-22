#include <iostream>
#include <cmath>
using namespace std;

enum Pavillon { JollyRogers, CompagnieDuSenegal, CompagnieDOstende };

enum Etat { Intact, Endommage, Coule };

int sq(int x)
{
  return x*x;
}

class Coordonnees
{
public:
  Coordonnees(int un_x, int un_y) : x_(un_x), y_(un_y) {}
  int x() const {
    return x_;
  }
  int y() const {
    return y_;
  }
  void operator+=(Coordonnees const& autre); // à définir plus bas
private:
  int x_;
  int y_;
};

class Navire
{
  /*****************************************************
   * Compléter le code à partir d'ici
   *****************************************************/
public:
  Navire(double _x, double _y, Pavillon const& _pavillon)
    : position_(_x,_y), pavillon_(_pavillon), etat_(Intact) {}
  Coordonnees position() const { return position_;}
  void avancer(int de_x, int de_y) {
    if (etat_ != Coule)
      position_ += Coordonnees(de_x,de_y);
  }
  void renflouer() { etat_ = Intact; }
  virtual void afficher() const;
  virtual void afficher_specifique() const = 0;
  virtual void attaque(Navire& n) = 0;
  virtual void replique(Navire& n) = 0;
  virtual void est_touche() = 0;
  virtual void rencontrer(Navire& m);
protected:
  static constexpr double rayon_rencontre = 10.0;
  Coordonnees position_;
  Pavillon pavillon_;
  Etat etat_;
};

void Coordonnees::operator+=(Coordonnees const& autre)
{
  x_ += autre.x_;
  y_ += autre.y_;
}
double distance(Coordonnees const& X, Coordonnees const& Y)
{
  return sqrt((X.x() - Y.x())*(X.x() - Y.x())+(X.y() - Y.y())*(X.y() - Y.y()));
}
ostream& operator<<(ostream& sortie, Coordonnees const& X) {
  sortie << "(" << X.x() << ", " << X.y() << ")";
  return sortie;
}
ostream& operator<<(ostream& sortie, Pavillon const& p) {
  if (p == JollyRogers) sortie << "pirate";
  if (p == CompagnieDuSenegal) sortie << "francais";
  if (p == CompagnieDOstende) sortie << "autrichien";
  if (p != JollyRogers && p != CompagnieDuSenegal && p != CompagnieDOstende)
    sortie << "pavillon inconnu";
  return sortie;
}
ostream& operator<<(ostream& sortie, Etat const& e) {
  if (e == Intact) sortie << "intact";
  if (e == Endommage) sortie << "ayant subi des dommages";
  if (e == Coule) sortie << "coule";
  if (e != Intact && e != Endommage && e != Coule)
    sortie << "etat inconnu";
  return sortie;
}
ostream& operator<<(ostream& sortie, Navire const& nav) {
  nav.afficher();
  return sortie;
}
void Navire::afficher() const {
  afficher_specifique();
  cout << " en " << position_ << " battant pavillon " << pavillon_ << ", " << etat_;
}
double distance(Navire const& n1, Navire const& n2) {
  return distance(n1.position(), n2.position());
}
void Navire::rencontrer(Navire& n) {
  if (distance(*this, n) < Navire::rayon_rencontre) {
    attaque(n);
    n.replique(*this);
  }
}

class Pirate : public virtual Navire
{
public:
  Pirate(double _x, double _y, Pavillon const& _pavillon)
    : Navire(_x, _y, _pavillon) {}
  virtual void afficher_specifique() const override {
    cout << "bateau pirate";
  }
  virtual void attaque(Navire& n) override {
    if (etat_ != Coule) {
      cout << "A l'abordage!" << endl;
      n.est_touche();
    }
  }
  virtual void replique(Navire& n) override {
    cout << "Non mais, ils nous attaquent! On riposte!!" << endl;
    attaque(n);
  }
  virtual void est_touche() override {
    if (etat_==Intact) {
      etat_ = Endommage;
      return;
    }
    if (etat_ == Endommage) {
      etat_ = Coule;
      return;
    }
  }
};

class Marchand : public virtual Navire
{
public:
  Marchand(double _x, double _y, Pavillon const& _pavillon)
    : Navire(_x,_y,_pavillon) {}
  virtual void afficher_specifique() const override {
    cout << "bateau marchand";
  }
  virtual void attaque(Navire& n) override {
    cout << "On vous aura! (insultes)" << endl;
  }
  virtual void replique(Navire& n) override {
    if (etat_ == Endommage) cout << "Meme pas peur !" << endl;
    else cout << "SOS je coule !" << endl;
  }
  virtual void est_touche() override {
    etat_ = Coule;
  }
};

class Felon : public Marchand, public Pirate
{
public:
  /*using Pirate::attaque;
  using Marchand::replique;
  using Pirate::est_touche;*/
  Felon(double _x, double _y, Pavillon const& _pavillon)
    : Navire(_x,_y,_pavillon),
      Marchand(_x,_y,_pavillon),
      Pirate(_x,_y,_pavillon) {}
  virtual void afficher_specifique() const override {
    cout << "bateau felon";
  }
  virtual void attaque(Navire& n) override {
    Pirate::attaque(n);
  }
  virtual void replique(Navire& n) override {
    Marchand::replique(n);
  }
  virtual void est_touche() override {
    Pirate::est_touche();
  }
};

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/

void rencontre(Navire& ship1, Navire& ship2)
{
  cout << "Avant la rencontre :" << endl;
  cout << ship1 << endl;
  cout << ship2 << endl;
  cout << "Distance : " << distance(ship1, ship2) << endl;
  ship1.rencontrer(ship2);
  cout << "Apres la rencontre :" << endl;
  cout << ship1 << endl;
  cout << ship2 << endl;
}

int main()
{
  
  // Test de la partie 1
  cout << "===== Test de la partie 1 =====" << endl << endl;

  // Un bateau pirate 0,0
  Pirate ship1(0, 0, JollyRogers);
  cout << ship1 << endl;

  // Un bateau marchand en 25,0
  Marchand ship2(25, 0, CompagnieDuSenegal);
  cout << ship2 << endl;

  cout << "Distance : " << distance(ship1, ship2) << endl;

  cout << "Quelques déplacements..." << endl;
  cout << "  en haut à droite :" << endl;
  // Se déplace de 75 unités à droite et 10 en haut
  ship1.avancer(75, 10);
  cout << ship1 << endl;

  cout << "  vers le bas :" << endl;
  ship1.avancer(0, -5);
  cout << ship1 << endl;

  cout << endl << "===== Test de la partie 2 =====" << endl << endl;

  cout << "Bateau pirate et marchand ennemis (trop loins) :" << endl;
  rencontre(ship1, ship2);

  cout << endl << "Bateau pirate et marchand ennemis (proches) :" << endl;
  ship1.avancer(-40, -2);
  ship2.avancer(10, 2);
  rencontre(ship1, ship2);

  cout << endl << "Deux bateaux pirates ennemis intacts (proches) :" << endl;
  Pirate ship3(33, 8, CompagnieDOstende);
  rencontre(ship1, ship3);

  cout << endl << "Bateaux pirates avec dommages, ennemis :" << endl;
  rencontre(ship1, ship3);

  cout << endl << "Bateaux marchands ennemis :" << endl;
  Marchand ship4(21, 7, CompagnieDuSenegal);
  Marchand ship5(27, 2, CompagnieDOstende);
  rencontre(ship4, ship5);

  cout << endl << "Pirate vs Felon :" << endl;
  ship3.renflouer();
  Felon ship6(32, 10, CompagnieDuSenegal);
  rencontre(ship3, ship6);

  cout << endl << "Felon vs Pirate :" << endl;
  rencontre(ship6, ship3);
  
  return 0;
}
