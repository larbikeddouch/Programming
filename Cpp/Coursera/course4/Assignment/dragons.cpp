#include <iostream>
#include <string>
#include <cmath>
using namespace std;

int distance(int x, int y)
{
  return abs(x - y);
}

class Creature
{
  /*****************************************************
   * Compléter le code à partir d'ici
   *****************************************************/
public:
  Creature(string _nom, int _niveau, int _points_de_vie, int _force, int _position=0.0)
    : nom_(_nom), niveau_(_niveau), points_de_vie_(_points_de_vie),
      force_(_force), position_(_position) {}
  bool vivant() const {return points_de_vie_ > 0;}
  int points_attaques() const {
    if (vivant()) return niveau_ * force_;
    return 0;
  }
  void deplacer(int x) { position_+=x; }
  void adieux() const { cout << nom_ << " n'est plus!" << endl;}
  void faiblir(int x) {
    if (points_de_vie_ > 0) {
      points_de_vie_ -= x;
      if (points_de_vie_ <= 0) {
	points_de_vie_ = 0;
	adieux();
      }
    }
  }
  void afficher() const {
    cout << nom_ << ", niveau: " << niveau_ ;
    cout << ", points de vie: " << points_de_vie_;
    cout << ", force: " << force_;
    cout << ", points d'attaque: " << points_attaques();
    cout << ", position: " << position_ << endl;
  }
  int position() const {return position_;}
protected:
  string nom_;
  int niveau_;
  int points_de_vie_;
  int force_;
  int position_;
};

class Dragon : public Creature
{
public:
  Dragon(string _nom,int _niveau, int _points_de_vie, int _force,
	 int _portee_flamme, int _position = 0)
    : Creature(_nom,_niveau,_points_de_vie,_force,_position),
      portee_flamme_(_portee_flamme) {}
  void voler(int pos) {position_ = pos;}
  // Implemented a method called getPosition for that
  // Otherwise dragon class does not have access to bete position
  // Rather implement getDistance() in Creature Class?
  void souffler_sur(Creature& bete) {
    int d(distance(position_,bete.position()));
    if (vivant() && bete.vivant() && d <= portee_flamme_) {
      bete.faiblir(points_attaques());
      faiblir(d);
      if (!bete.vivant() && vivant()) {
	niveau_ += 1;
      }
    }
  }
private:
  int portee_flamme_;
};
 
class Hydre : public Creature
{
public:
  Hydre(string _nom,int _niveau, int _points_de_vie, int _force,
	int _longeur_cou, int _dose_poison, int _position = 0)
    : Creature(_nom,_niveau,_points_de_vie,_force,_position),
      longueur_cou_(_longeur_cou), dose_poison_(_dose_poison) {}
  void empoisonne(Creature& bete) {
    int d(distance(position_,bete.position()));
    if (vivant() && bete.vivant() && d <= longueur_cou_) {
      bete.faiblir(points_attaques() + dose_poison_);
      if (!bete.vivant() && vivant()) {
	niveau_ += 1;
      }
    }
  }
private:
  int longueur_cou_;
  int dose_poison_;
};

void combat(Dragon& d, Hydre& h) {
  h.empoisonne(d);
  d.souffler_sur(h);
}

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/
int main()
{
  Dragon dragon("Dragon rouge"   , 2, 10, 3, 20         );
  Hydre  hydre ("Hydre maléfique", 2, 10, 1, 10, 1,  42 );
  
  dragon.afficher();
  cout << "se prépare au combat avec :" << endl;
  hydre.afficher();

  cout << endl;
  cout << "1er combat :" << endl;
  cout << "    les créatures ne sont pas à portée, donc ne peuvent pas s'attaquer."
       << endl;
  combat(dragon, hydre);

  cout << "Après le combat :" << endl;
  dragon.afficher();
  hydre.afficher();

  cout << endl;
  cout << "Le dragon vole à proximité de l'hydre :" << endl;
  dragon.voler(hydre.position() - 1);
  dragon.afficher();

  cout << endl;
  cout << "L'hydre recule d'un pas :" << endl;
  hydre.deplacer(1);
  hydre.afficher();

  cout << endl;
  cout << "2e combat :" << endl;
  cout << "\
  + l'hydre inflige au dragon une attaque de 3 points\n\
      [ niveau (2) * force (1) + poison (1) = 3 ] ;\n\
  + le dragon inflige à l'hydre une attaque de 6 points\n\
      [ niveau (2) * force (3) = 6 ] ;\n\
  + pendant son attaque, le dragon perd 2 points de vie supplémentaires\n\
       [ correspondant à la distance entre le dragon et l'hydre : 43 - 41 = 2 ].\
" << endl;
  combat(dragon, hydre);

  cout << "Après le combat :" << endl;
  dragon.afficher();
  hydre.afficher();

  cout << endl;
  cout << "Le dragon avance d'un pas :" << endl;
  dragon.deplacer(1);
  dragon.afficher();

  cout << endl;
  cout << "3e combat :" << endl;
  cout << "\
  + l'hydre inflige au dragon une attaque de 3 points\n\
      [ niveau (2) * force (1) + poison (1) = 3 ] ;\n\
  + le dragon inflige à l'hydre une attaque de 6 points\n\
      [ niveau (2) * force (3) = 6 ] ;\n\
  + pendant son attaque, le dragon perd 1 point de vie supplémentaire\n\
       [ correspondant à la distance entre le dragon et l'hydre : 43 - 42 = 1 ] ;\n\
  + l'hydre est vaincue et le dragon monte au niveau 3.\
" << endl;
  combat(dragon, hydre);

  cout << "Après le combat :" << endl;
  dragon.afficher();
  hydre.afficher();

  cout << endl;
  cout << "4e Combat :" << endl;
  cout << "    quand une créature est vaincue, rien ne se passe." << endl;
  combat(dragon, hydre);

  cout << "Après le combat :" << endl;
  dragon.afficher();
  hydre.afficher();

  return 0;
}
