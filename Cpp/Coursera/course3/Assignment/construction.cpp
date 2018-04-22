#include <iostream>
#include <string>
#include <vector>
using namespace std;

// Pour simplifier
typedef string Forme   ;
typedef string Couleur ;

class Brique
{
private:
  Forme   forme   ;
  Couleur couleur ;

public:
  /*****************************************************
    Compléter le code à partir d'ici
  *******************************************************/
  Brique(Forme _forme, Couleur _couleur) : forme(_forme), couleur(_couleur) {}
  ostream& afficher(ostream& sortie) const {
    if (!couleur.empty())
      sortie << "(" << forme << ", " << couleur << ")";
    else
      sortie << forme;
    return sortie;
  }
};

ostream& operator<<(ostream& sortie, Brique const& b) {
  b.afficher(sortie);
  return sortie;
}

class Construction
{
  friend class Grader;
public:
  Construction(Brique& b) {
    vector<Brique> intermediary1;
    intermediary1.push_back(b);
    vector<vector<Brique>> intermediary2;
    intermediary2.push_back(intermediary1);
    vector<vector<vector<Brique>>> intermediary3;
    contenu = intermediary3;
    contenu.push_back(intermediary2);
  }
  ostream& afficher(ostream& sortie) const {
    for (size_t i(contenu.size()-1); i < contenu.size(); --i) {
      sortie << "Couche " << i << " :" << endl;
      for (size_t j(0); j < contenu[i].size(); ++j) {
	for (size_t k(0); k< contenu[i][j].size(); ++k) {
	  if (k > 0) sortie << " ";
	  sortie << contenu[i][j][k];
	}
	sortie << endl;
      }
    }
    sortie << "Vector Size:" << contenu.size() << "x" << contenu[0].size() << "x" <<contenu[0][0].size() << endl;
    return sortie;
  }
  Construction& operator^=(Construction const& c) {
    for (size_t i(0); i< c.contenu.size(); ++i) {
      contenu.push_back(c.contenu[i]);
    }
    return *this;
  }
  Construction& operator-=(Construction const& c) {
    if (c.contenu.size() < contenu.size())
      return *this;
    for (size_t i(0); i< std::min(contenu.size(),c.contenu.size()); ++i) {
      for (size_t j(0); j < c.contenu[i].size(); ++j) {
	contenu[i].push_back(c.contenu[i][j]);
      }
    }
    return *this;
  }
  Construction& operator+=(Construction const& c) {
    // Check that height for c is at least as big as this
    if (c.contenu.size() < contenu.size())
      return *this;
    if (c.contenu[0].size() < contenu[0].size())
      return *this;
    
    for (size_t i(0); i< contenu.size(); ++i) {
      for (size_t j(0); j < c.contenu[i].size(); ++j) {
	for (size_t k(0); k < c.contenu[i][j].size(); ++k) {
	  contenu[i][j].push_back(c.contenu[i][j][k]);
	}
      }
    }
    return *this;
    
  }
private:
vector<vector<vector<Brique>>> contenu;
};

ostream& operator<<(ostream& sortie, Construction c) {
  c.afficher(sortie);
  return sortie;
}

const Construction operator^(Construction c1, Construction const& c2) {
  Construction tmp(c1);
  tmp ^= c2;
  return tmp;
}
const Construction operator-(Construction c1, Construction const& c2) {
  Construction tmp(c1);
  tmp -= c2;
  return tmp;
}
const Construction operator+(Construction c1, Construction const& c2) {
  Construction tmp(c1);
  tmp += c2;
  return tmp;
}

const Construction operator*(unsigned int n, Construction const& a)
{
  Construction tmp(a);
  for (size_t i(0); i<n-1; ++i) {
    tmp += a;
  }
  return tmp;
}

const Construction operator/(unsigned int n, Construction const& a)
{
  Construction tmp(a);
  for (size_t i(0); i<n-1; ++i) {
    tmp ^= a;
  }
  return tmp;  
}

const Construction operator%(unsigned int n, Construction const& a)
{
  Construction tmp(a);
  for (size_t i(0); i<n-1; ++i) {
    tmp -= a;
  }
  return tmp;
}

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/

int main()
{
  // Modèles de briques
  Brique toitD("obliqueD", "rouge");
  Brique toitG("obliqueG", "rouge");
  Brique toitM(" pleine ", "rouge");
  Brique mur  (" pleine ", "blanc");
  Brique vide ("                 ", "");

  unsigned int largeur(4);
  unsigned int profondeur(3);
  unsigned int hauteur(3); // sans le toit

  /*cout << toitD << endl;
  cout << toitG << endl;
  cout << toitM << endl;
  cout << mur << endl;
  cout << vide << toitD << endl;*/

  /*Construction cmur(mur);
  Construction c1 = mur ^ mur ^ toitD;
  Construction c2 = mur ^ toitD;;
  c1 -= c2;
  Construction c3(c1);
  c1 += c3;
  c1 += c3;
  cout << c1;*/
  
  // on construit les murs
  Construction maison( hauteur / ( profondeur % (largeur * mur) ) );

  // on construit le toit
  Construction toit(profondeur % ( toitG + 2*toitM + toitD ));
  toit ^= profondeur % (vide + toitG + toitD);

  // on pose le toit sur les murs
  maison ^= toit;

  // on admire notre construction
  cout << maison << endl;

  return 0;
}
