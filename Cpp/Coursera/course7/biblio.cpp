#include <iostream>
#include <memory>
#include <vector>
using namespace std;

class Ouvrage
{
public:
  Ouvrage(string _nom,string _editeur, string _emprunteur = "", string _date_emprunt ="")
    : nom(_nom), editeur(_editeur), dispo(_emprunteur == ""),
      emprunteur(_emprunteur), date_emprunt(_date_emprunt) {}
  virtual ~Ouvrage() {}
  virtual void afficher() const final {
    afficher_type();
    afficher_ouvrage();
    afficher_dispo();
  }
  virtual void afficher_type() const = 0;
  virtual void afficher_ouvrage() const = 0;
  virtual void afficher_dispo() const {
    if (dispo) cout << "Disponible" << endl;
    else {
      cout << "Emprunte le " << date_emprunt << " par " << emprunteur << endl;
    }
  }
  void alignment() const { cout << "       "; }
  void emprunt(string _emprunteur, string _date_emprunt) {
    dispo = false;
    emprunteur = _emprunteur;
    date_emprunt = _date_emprunt;
  }
protected:
  string nom;
  string editeur;
  bool dispo;
  string emprunteur;
  string date_emprunt;
};

class Publication : public Ouvrage
{
public:
  Publication(string _nom, string _auteur, string _annee_parution,
	      string _editeur, string _emprunteur = "", string _date_emprunt = "")
    : Ouvrage(_nom, _editeur, _emprunteur, _date_emprunt),
      annee_parution(_annee_parution), auteur(_auteur) {}
  virtual void afficher_ouvrage() const override {
    Ouvrage::alignment(); cout << nom << endl;
    Ouvrage::alignment(); cout << "par " << auteur << endl;
    Ouvrage::alignment(); cout << "publie en " << annee_parution << " par " << editeur << endl;
  }
protected:
  string annee_parution;
  string auteur;
};

class Periodique : public Ouvrage
{
public:
  Periodique(string _nom, string _date_parution,
	     string _editeur, string _emprunteur = "", string _date_emprunt = "")
    : Ouvrage(_nom, _editeur, _emprunteur, _date_emprunt),
      date_parution(_date_parution) {}
  virtual void afficher_type() const override {
    cout << "Journal :" << endl;
  }
  virtual void afficher_ouvrage() const override {
    Ouvrage::alignment(); cout << nom << " de " << date_parution << endl;
    Ouvrage::alignment(); cout << "publie par " << editeur << endl;
  }
protected:
  string date_parution;
};

class Livre : public Publication
{
public:
  Livre(string _nom, string _auteur, string _annee_parution,
	      string _editeur, string _emprunteur = "", string _date_emprunt = "")
      : Publication(_nom, _auteur, _annee_parution,
		    _editeur, _emprunteur,_date_emprunt) {}
  virtual ~Livre() {}
  virtual void afficher_type() const override {
    cout << "Livre :" << endl;
  }
};

class Video : public Publication
{
public:
  Video(string _nom, string _auteur, string _annee_parution,
	string _editeur, int _duree, string _emprunteur = "", string _date_emprunt = "")
      : Publication(_nom, _auteur, _annee_parution,
		    _editeur, _emprunteur,_date_emprunt), duree(_duree) {}
  virtual ~Video() {}
  virtual void afficher_type() const override {
    cout << "Video :" << endl;
  }
  virtual void afficher_ouvrage() const override {
    Publication::afficher_ouvrage();
    Ouvrage::alignment(); cout << "duree : " << duree << " minutes" << endl;
  }
private:
  int duree;
};

class Externe
{
public:
  Externe(string _bib) : bib(_bib) {}
  virtual ~Externe() {}
protected:
  string bib;
};

class LivreExterne : public Livre, public Externe
{
public:
  LivreExterne(string _nom, string _auteur, string _annee_parution,
	       string _editeur, string _date_emprunt, string bib, string _emprunteur)
    : Livre(_nom,_auteur,_annee_parution,_editeur,
	    _emprunteur,_date_emprunt),
      Externe(bib) {}
  virtual void afficher_dispo() const override {
    Ouvrage::afficher_dispo();
    cout << "provenant de " << bib << endl;
  }
};

class Bibliotheque
{
public:
  Bibliotheque() {}
  virtual ~Bibliotheque() {}
  int ajoute(Ouvrage* o) {
    ouvrages.push_back(unique_ptr<Ouvrage>(o));
    return ouvrages.size()-1;
  }
  void affiche() const {
    for (unsigned int i(0); i< ouvrages.size(); ++i) {
      cout << i << "- ";
      ouvrages[i]->afficher();
    }
  }
  void supprime(unsigned int o) {
    ouvrages.erase(ouvrages.begin()+o);
  }
  void vider() {
    ouvrages.clear();
  }
  unique_ptr<Ouvrage>& operator[](unsigned int i) {
    return ouvrages[i];
  }
private:
  vector<unique_ptr<Ouvrage>> ouvrages;
};


int main()
{
  Bibliotheque mabib;
  mabib.ajoute(new Livre("Programmation orientee objets en C++ (2nd edition)",
			 "Micheloud et Rieder","2003", "PPUR"));
  mabib.ajoute(new Periodique("Computational Linguistics","12/2003", "MIT Press"));
  unsigned int o1, o2;
  o1 = mabib.ajoute(new Video("Nanometers and Gigabucks",
			      "G. E. Moore", "1996",
			      "University Video Communications", 61));
  o2 = mabib.ajoute(new LivreExterne("Elements of Information Theory",
				     "Cover and Thomas", "1991",
				     "Wiley", "25/02/2004",
				     "ETHZ-BC", "C. Chatnonne"));
  mabib.affiche();
  cout << "==================================================" << endl;

  mabib[o1]->emprunt("J.-C. Chappelier", "05/07/2004");
  mabib.supprime(o2);
  mabib.affiche();
  mabib.vider();
  
  return 0;
}
