#include <iostream>
using namespace std;

class Document
{
public:
  Document(int _taille, string _nom, string _extension)
    : taille(_taille), nom(_nom), extension(_extension)
  {cout << "Creation d'un document" << endl;}
  virtual ~Document() { cout << "Destruction d'un document" << endl; }
  virtual void afficher() const {
    cout << "Je suis le fichier " << nom << extension << endl;
    cout << "Ma taille est de : " << taille << "KB" << endl;
  }
private:
  int taille;
  string nom;
  string extension;
};

class Programme
{
public:
  Programme(string _langage, string _auteur, string _descriptif)
    : langage(_langage), auteur(_auteur), descriptif(_descriptif)
  {cout << "Creation d'un programme" << endl; }
  virtual ~Programme()  { cout << "Destruction d'un programme" << endl; }
  virtual void afficher() const {
    cout << "Je suis ecrit en " << langage << endl;
    cout << "Mon auteur est : " << auteur << endl;
    cout << "Je fais du : " << descriptif << endl;;
  }
private:
  string langage;
  string auteur;
  string descriptif;
};

class FichierCPP : public Programme, public Document
{
public:
  FichierCPP(int _taille, string _nom, string _extension,
	     string _langage, string _auteur, string _descriptif)
    : Programme(_langage,_auteur,_descriptif),
      Document(_taille, _nom, _extension)
  {cout << "Construction d'un fichier C++" << endl;}
  ~FichierCPP() {cout << "Destruction d'un fichier C++" << endl;}
  virtual void afficher() const override {
    Document::afficher();
    Programme::afficher();
  }
};

int main() {
  FichierCPP f(1600,"numeric",".cc","C++","C.Hacker", "calcul numerique");
  f.afficher();
  return 0;
}
