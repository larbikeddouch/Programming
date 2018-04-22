#include <iostream>
#include <vector>
#include <memory>
using namespace std;


class Position
{
public:
  Position(int _L, int _C) : L(_L), C(_C) {}
  virtual ~Position() {}
  int getL() const { return L;}
  int getC() const { return C;}
private:
  int L;
  int C;
};

enum Couleur{
  BLANC,
  NOIR
};

class Piece
{
public:
  Piece(int _L, int _C, Couleur _c)
    : c(_c), p(_L,_C) {}
  virtual bool deplace(const Position&) = 0;
  bool checkCoordinate(const Position& pos) {
    return !(pos.getL() > 8 || pos.getL() < 1 || pos.getC() > 8 || pos.getC() < 1);
  }
  virtual void afficher() const {
    afficher_specifique();
    if (c == BLANC) cout << " de couleur blanche";
    if (c == NOIR) cout << " de couleur noire";
    cout << " en position (" << p.getL() << ", " << p.getC() << ")" << endl;
  }
  virtual void afficher_specifique() const = 0;
  virtual ~Piece() {}
protected:
  Couleur c;
  Position p;
};

class Tour : public Piece
{
public:
  Tour(int _L, int _C, Couleur _c) : Piece(_L,_C,_c) {}
  virtual ~Tour() {}
  virtual bool deplace(const Position& pos) override {
    if (!Piece::checkCoordinate(pos))
      return false;
    if (p.getL() == pos.getL() || p.getC() == pos.getC()) {
      p = pos;
      return true;
    }
    return false;
  }
  virtual void afficher_specifique() const override {
    cout << "Une Tour";
  }
};

class Cheval : public Piece
{
public:
  Cheval(int _L, int _C, Couleur _c) : Piece(_L,_C,_c) {}
  virtual ~Cheval() {}
  virtual bool deplace(const Position& pos) override {
    if (!Piece::checkCoordinate(pos))
      return false;

    if ((abs(p.getL() - pos.getL()) == 1 && abs(p.getC() - pos.getC()) == 2) ||
	(abs(p.getL() - pos.getL()) == 2 && abs(p.getC() - pos.getC()) == 1)) {
      p = pos;
      return true;
    }
    return false;
  }
  virtual void afficher_specifique() const override {
    cout << "Un Cheval";
  }
};

class Jeu
{
public:
  Jeu() {}
  virtual ~Jeu() {}
  void ajouter_piece(Piece* p) {
    pieces.push_back(unique_ptr<Piece>(p));
  }
  void afficher() const {
    for (auto const& p : pieces)
      p->afficher();
  }
  void bouger_pieces(vector<Position> positions) {
    for (unsigned int i(0); i < positions.size(); i++) {
      pieces[i]->deplace(positions[i]);
    }
  }
private:
  vector<unique_ptr<Piece>> pieces;
};


int main() {
  Jeu jeu;

  jeu.ajouter_piece(new Cheval(5,6,NOIR));
  jeu.ajouter_piece(new Cheval(1,2,BLANC));
  jeu.ajouter_piece(new Tour(7,6,NOIR));
  jeu.ajouter_piece(new Tour(3,2,BLANC));
  cout << "Before moving anything" << endl;
  jeu.afficher();

  cout << endl;
  Position posc(6,4);
  Position post(7,1);
  vector<Position> positions;
  positions.push_back(posc);
  positions.push_back(posc);
  positions.push_back(post);
  positions.push_back(post);
  
  jeu.bouger_pieces(positions);
  jeu.afficher();
  return 0;
}
