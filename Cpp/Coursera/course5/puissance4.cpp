#include <iostream>
#include <vector>
using namespace std;

enum Couleur
  {
    JAUNE,
    ROUGE
  };

class Point
{
public:
  Point() : c(JAUNE), vide(true) {}
  bool estVide() const {return vide;}
  void setCouleur(Couleur _c) {c = _c; vide = false;}
  void afficher(ostream& sortie) const {
    if (vide) cout << "V";
    else {
      if (c == JAUNE) cout << "J";
      else cout << "R";
    }
  }
  Couleur getCouleur() const { return c;}
private:
  Couleur c;
  bool vide;
};


class Jeu
{
public:
  Jeu(unsigned int n = 8) : plateau() {
    for (unsigned int i(0); i< n; ++i) {
      vector<Point*> ligne;
      for (unsigned int j(0); j<n; ++j) {
	ligne.push_back(new Point());
      }
      plateau.push_back(ligne);
    }
  }
  void afficher(ostream& sortie) const {
    for (unsigned int i(0); i< plateau.size(); ++i) cout << i << " ";
    cout << endl;
    for (auto const& ligne : plateau) {
      for (auto const& p : ligne) {
	p->afficher(sortie);
	sortie << " ";
      }
      sortie << endl;
    }
  }
  bool jouer(unsigned int n, Couleur c) {
    // column number starts at 0;
    if (!couleurGagnant.estVide()) {
      cout << "La partie est deja termine. Merci d'arreter de jouer." << endl;
      return false;
    }
    if (n < 0 || n >= plateau.size()) return false;
    if (!plateau[0][n]->estVide()) return false;

    for (unsigned int i(plateau.size()-1); i>= 0 && i <=plateau.size(); --i) {
      if (plateau[i][n]->estVide()) {
	plateau[i][n]->setCouleur(c);
	aGagne(i,n);
	break;
      }
    }
    return true;
  }
  Point gagnant() {
    return couleurGagnant;
  }
  void aGagne(unsigned int i,unsigned int n) {
    if (!couleurGagnant.estVide())
      return;
    for (unsigned int j(0); j < 4; ++j) {
      for (unsigned int k(0); k < 4; ++k) {
	check(i,n,j,k);
      }
    }
  }
  void check(int i, int n, int j, int k) {
    // Define all possible directions
    //cout << "Check Test " << i << ", " << j << endl;
    vector<int> v1, v2, v3, v4;
    v1.push_back(1); v1.push_back(0);
    v2.push_back(1); v2.push_back(1);
    v3.push_back(0); v3.push_back(1);
    v4.push_back(1); v4.push_back(-1);
    //cout << "Check Test1" << endl;
    check(i,n,j,k,v1);
    //cout << "Check Test2" << endl;
    check(i,n,j,k,v2);
    //cout << "Check Test3" << endl;
    check(i,n,j,k,v3);
    //cout << "Check Test4" << endl;
    check(i,n,j,k,v4);
  }  
  void check(int i, int n, int j, int k, vector<int> dir) {
    int s(plateau.size());
    
    if ( i - j*dir[0] <  0 || i - (j - 3) * dir[0] >= s) return;
    if ( n - k*dir[1] <  0
	 || n - (k - 3) * dir[1] < 0
	 || n - k*dir[1] >= s
	 || n - (k - 3) * dir[1] >= s) return;

    if (plateau[i-j*dir[0]][n-k*dir[1]]->estVide()
	|| plateau[i-(j-1)*dir[0]][n-(k-1)*dir[1]]->estVide()
	|| plateau[i-(j-2)*dir[0]][n-(k-2)*dir[1]]->estVide()
	|| plateau[i-(j-3)*dir[0]][n-(k-3)*dir[1]]->estVide()) return;

    Couleur c(plateau[i][n]->getCouleur());
    if (plateau[i-j*dir[0]][n-k*dir[1]]->getCouleur() == c
	&& plateau[i-(j-1)*dir[0]][n-(k-1)*dir[1]]->getCouleur() == c
	&& plateau[i-(j-2)*dir[0]][n-(k-2)*dir[1]]->getCouleur() == c
	&& plateau[i-(j-3)*dir[0]][n-(k-3)*dir[1]]->getCouleur() == c) {
      couleurGagnant = *plateau[i][n];
    }
  }
  unsigned int getTaille() const { return plateau.size();}
  unsigned int nextAvailableColumn() const {
    for (unsigned int i(0); i < plateau.size(); ++i) {
      if (plateau[0][i]->estVide())
	return i;
    }
    return plateau.size() - 1;
  }
  ~Jeu() {
    for (unsigned int i(0); i < plateau.size(); ++i) {
      for (unsigned int j(0); j< plateau.size(); ++j) {
	delete plateau[i][j];
      }
    }
    plateau.clear();
  }
private:
  vector<vector<Point*>> plateau;
  unsigned int lastLigne;
  unsigned int lastColumn;
  Point couleurGagnant;
};

class Joueur
{
public:
  Joueur(Couleur _c, string _n) : c(_c), nom(_n) {}
  virtual void jouer(Jeu&) = 0;
protected:
  Couleur c;
  string nom;
};

class Humain : public Joueur
{
public:
  Humain(Couleur _c, string _n) : Joueur(_c,_n) {}
  virtual void jouer(Jeu& jeu) override {
    cout << endl;
    bool valide(false);
    int n;
    cout << "Au tour de " << nom << endl;
    while (!valide) {
      cout << "Donner la colonne du prochain coup : ";
      cin >> n;
      valide = jeu.jouer(n,c);
      if (!valide) {
	cout << "Veuillez donne un nombre valide sur une colonne non pleine (entre 0 et ";
	cout << jeu.getTaille()-1 << ")" << endl;
      }
    }
    jeu.afficher(cout);
    cout << nom << " a fini de jouer." << endl;
  }
};

class Ordinateur : public Joueur
{
public:
  Ordinateur(Couleur _c) : Joueur(_c,"Ordinateur") {}
  virtual void jouer(Jeu& jeu) override{
    cout << endl;
    cout << "Au tour de " << nom << endl;
    unsigned int next(jeu.nextAvailableColumn());
    jeu.jouer(next,c);
    jeu.afficher(cout);
    cout << nom << " a fini de jouer." << endl;
  }
};

class Partie
{
public:
  Partie(unsigned int n=8, string _nom = "Xavier") : jeu(n) {
    joueurs.push_back(new Ordinateur(ROUGE));
    joueurs.push_back(new Humain(JAUNE,_nom));
  }
  void jouer() {
    int i(0);
    while (true) {
      joueurs[i]->jouer(jeu);
      Point gagnantPotentiel(jeu.gagnant());
      if (!gagnantPotentiel.estVide()) {
	gagnantPotentiel.afficher(cout);
	cout << " a gagne la partie !" << endl;
	return;
      }
      i++; i = i % 2;
    }
  }
private:
  Jeu jeu;
  vector<Joueur*> joueurs;
};

int main()
{
  Partie partie;
  partie.jouer();
  
  return 0;
}
