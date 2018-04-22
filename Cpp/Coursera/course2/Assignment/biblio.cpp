#include <iostream>
#include <vector>
#include <string>
using namespace std;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
// Chaines de caractères à utiliser pour les affichages:
/*

été jeté

d'un

n'est

L'oeuvre

bibliothèque

détruit

*/

class Auteur
{
public:
  Auteur(string _nom, bool _prime = false) : nom(_nom), prime(_prime) {}
  Auteur(Auteur const&) = delete;
  string getNom() const {return nom;}
  bool getPrix() const {return prime;}
private:
  string nom;
  bool prime;
};

class Oeuvre
{
public:
  // Pas besoin d'un constructeur de copie ici?
  Oeuvre(string _titre, Auteur& _a, string _langue)
    : titre(_titre), a(&_a), langue(_langue) {}
  Oeuvre(const Oeuvre& o) = delete;
  string getTitre() const {return titre;}
  // Est ce bien une reference constante sur l'auteur?
  const Auteur& getAuteur() const {return *a;}
  string getLangue() const {return langue;}
  void affiche() const {
    cout << titre;
    cout << ", " << a->getNom();
    cout << ", en " << langue;
  }
  ~Oeuvre() {
    cout << "L'oeuvre \"";
    affiche();
    cout << "\" n'est plus disponible." << endl;
  }
private:
  string titre;
  const Auteur* a;
  string langue;
};

class Exemplaire
{
public:
  Exemplaire(const Oeuvre& _o) : o(&_o)
  {
    cout << "Nouvel exemplaire de : ";
    o->affiche();
    cout << endl;
  }
  Exemplaire(const Exemplaire& _e) : o(_e.o)
  {
    cout << "Copie d'un exemplaire de : ";
    o->affiche();
    cout << endl;
  }
  ~Exemplaire()
  {
    cout << "Un exemplaire de \"";
    o->affiche();
    cout << "\" a ete jete !"<< endl;
  }
  const Oeuvre& getOeuvre() const {return *o;}
  void affiche() const {
    cout << "Exemplaire de : ";
    o->affiche();
  }
private:
  const Oeuvre* o;
};

class Bibliotheque
{
public:
  Bibliotheque(string _nom) : nom(_nom), e() {
    cout << "La bibliotheque " << nom;
    cout<< " est ouverte !"<< endl;
  }
  string getNom() const {return nom;}
  void stocker(Oeuvre& o, unsigned int n = 1) {
    for (unsigned int i(0); i < n; ++i) {
      Exemplaire* exemplaire = new Exemplaire(o);
      e.push_back(exemplaire);
    }
  }
  void lister_exemplaires(string langue="") const {
    for (size_t i(0); i< e.size(); ++i) {
      if (langue.empty() || (*e[i]).getOeuvre().getLangue() == langue) {
	e[i]->affiche();
	cout << endl;
      }
      
    }
  }
  unsigned int compter_exemplaires(const Oeuvre& o) const {
    unsigned int compteur(0);
    for (size_t i(0); i< e.size(); ++i) {
      if (&e[i]->getOeuvre() == &o)
	compteur += 1;
    }
    return compteur;
  }
  void afficher_auteurs(bool prime=false) const {
    vector<const Auteur *> auteur_liste;
    
    for (size_t i(0); i< e.size(); ++i) {
      bool auteurdejadanslaliste(false);
      if (auteur_liste.size() > 0) {
	for (size_t j(0); j<auteur_liste.size(); ++j) {
	  if (auteur_liste[j] == &((*e[i]).getOeuvre().getAuteur()))
	    auteurdejadanslaliste = true;
	}
      }
      if (!auteurdejadanslaliste &&
	  ((prime && (*e[i]).getOeuvre().getAuteur().getPrix()) || !prime)) {
	auteur_liste.push_back(&((*e[i]).getOeuvre().getAuteur()));
	cout << (*e[i]).getOeuvre().getAuteur().getNom() << endl;
      }
    }
  }
  ~Bibliotheque() {
    cout << "La bibliotheque " << nom << "ferme ses ports et detruit ses exemplaires : " << endl;
    for (size_t i(0); i < e.size() ; ++i) {
      delete e[i];
    }
  }
private:
  string nom;
  vector<const Exemplaire *> e;
};

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

int main()
{
  Auteur a1("Victor Hugo"),
         a2("Alexandre Dumas"),
         a3("Raymond Queneau", true);

  Oeuvre o1("Les Misérables"           , a1, "français" ),
         o2("L'Homme qui rit"          , a1, "français" ),
         o3("Le Comte de Monte-Cristo" , a2, "français" ),
         o4("Zazie dans le métro"      , a3, "français" ),
         o5("The Count of Monte-Cristo", a2, "anglais" );

  Bibliotheque biblio("municipale");
  biblio.stocker(o1, 2);
  biblio.stocker(o2);
  biblio.stocker(o3, 3);
  biblio.stocker(o4);
  biblio.stocker(o5);

  cout << "La bibliothèque " << biblio.getNom()
       << " offre les exemplaires suivants :" << endl;
  biblio.lister_exemplaires();

  const string langue("anglais");
  cout << " Les exemplaires en "<< langue << " sont :" << endl;
  biblio.lister_exemplaires(langue);

  cout << " Les auteurs à succès sont :" << endl;
  biblio.afficher_auteurs(true);

  cout << " Il y a " << biblio.compter_exemplaires(o3) << " exemplaires de "
  << o3.getTitre() << endl;

  return 0;
}
