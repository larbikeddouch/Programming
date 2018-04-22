#include <iostream>
#include <vector>
using namespace std;

class Carte
{
public:
  Carte(double _c) : cost(_c) { }
  virtual ~Carte() {}
  virtual void afficher() const { afficher_specifique(); cout << " de cout " << cost << endl;}
  virtual void afficher_specifique() const = 0;
protected:
  double cost;
};

enum TypeTerrain
  {
    BLANC,
    BLEU,
    NOIR,
    ROUGE,
    VERT
  };

class Terrain : public virtual Carte
{
public:
  Terrain(TypeTerrain _t) : Carte(0.0), t(_t) { cout << "Ceci est un terrain" << endl;}
  ~Terrain() {cout << "Destruction d'un terrain" << endl;}
  virtual void afficher() const override {afficher_specifique(); cout << endl;}
  virtual void afficher_specifique() const override {
    cout << "Un terrain ";
    if (t == BLANC) cout << "blanc";
    if (t == BLEU) cout << "bleu";
    if (t == NOIR) cout << "noir";
    if (t == ROUGE) cout << "rouge";
    if (t == VERT) cout << "vert";
  }
protected:
  TypeTerrain t;
};

class Creature : public virtual Carte
{
public:
  Creature(double _c, string _nom, unsigned int _d, unsigned int _p)
    : Carte(_c), nom(_nom), degats(_d), points_de_vie(_p) {
    cout << "Ceci est une creature" << endl;
  }
  ~Creature() {cout << "Destruction d'une creature" << endl;}
  virtual void afficher_specifique() const override {
    cout << "Une creature " << nom << " " << degats << "/" << points_de_vie;;
  }
protected:
  string nom;
  unsigned int degats;
  unsigned int points_de_vie;
};

class Sortilege : public Carte
{
public:
  Sortilege(double _c, string _nom, string _explication)
    : Carte(_c), nom(_nom), explication(_explication) {
    cout << "Ceci est un sortilege" << endl;
  }
  ~Sortilege() {cout << "Destruction d'un sortilege" << endl;}
  virtual void afficher_specifique() const override {
    cout << "Un sortilege " << nom;
  }
private:
  string nom;
  string explication;
};

class CreatureTerrain : public Creature, public Terrain
{
public:
  CreatureTerrain(double _c, string _nom, unsigned int _d, unsigned int _p, TypeTerrain _t)
    : Carte(_c), Creature(_c,_nom,_d,_p), Terrain(_t) {cout << "Houla, une creature/terrain" << endl;}
  virtual void afficher() const override {
    afficher_specifique(); cout << " de cout " << cost << endl;
  }
  virtual void afficher_specifique() const override {
    cout << "Une creature/terrain ";
    if (t == BLANC) cout << "blanche";
    if (t == BLEU) cout << "bleue";
    if (t == NOIR) cout << "noire";
    if (t == ROUGE) cout << "rouge";
    if (t == VERT) cout << "verte";
    cout << " " << Creature::nom << " " << Creature::degats << "/" << Creature::points_de_vie;
  }
};

class Jeu
{
public:
  Jeu() {cout << "On change de main" << endl;}
  void ajoute(Carte* _c) {
    cartes.push_back(_c);
  }
  void afficher() const {
    cout << "La, j'ai en stock : " << endl;
    for (auto const& carte: cartes) {
      cout << "  +  ";
      carte->afficher();
    }
    cout << endl;
  }
  ~Jeu() {
    for (unsigned int i(0); i< cartes.size(); ++i) {
      delete cartes[i];
    }
    cartes.clear();
    cout << "Je jette ma main." << endl;
  }
private:
  vector<Carte*> cartes;
};

int main()
{
  Jeu mamain;
  
  mamain.ajoute(new Terrain(BLEU));
  mamain.ajoute(new Creature(6, "Golem", 4, 6));
  mamain.ajoute(new Sortilege(1, "Croissance Gigantesque",
			      "La créature ciblée gagne +3/+3 jusqu'à la fin du tour"));
  mamain.ajoute(new CreatureTerrain(2, "Ondine", 1, 1, BLEU));
  mamain.afficher();
  
  return 0;
}
