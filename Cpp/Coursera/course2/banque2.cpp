#include <iostream>
#include <string>
#include <list>
using namespace std;

class Compte
{
public:
  Compte(double initialSolde, double clientRate)
    : solde(initialSolde), soldefinal(initialSolde * (1+ clientRate)),
      compteboucle(false) {}
  void affichersolde() const {
    if (!compteboucle)
      cout << solde;
    else
      cout << soldefinal;
    cout << " francs" << endl;
  }
  void bouclercompte() {
    compteboucle=true;
  }
private:
  double solde;
  double soldefinal;
  bool compteboucle;
};


class Client
{
public:
  // Using this type of constructor, how to enforce the fact that savings rate should be
  // higher than private rate?
  Client(string _name, string _city, double soldeprivate, double tauxprivate,
	 double savingssolde, double savingsrate, bool _male)
    : name(_name), city(_city),
      privateAccount(soldeprivate, tauxprivate),
      savingsAccount(savingssolde, savingsrate),
      male(_male)
  {}
  void afficherinfoclients() const {
    if (male)
      cout << "Client ";
    else 
      cout << "Cliente ";
    cout << name << " de " << city << endl;
    cout << "   Compte courant : ";
    privateAccount.affichersolde();
    cout << "   Compte épargne : ";
    savingsAccount.affichersolde();
  }
  void bouclercompte(){
    privateAccount.bouclercompte();
    savingsAccount.bouclercompte();
  }  
private:
  string name;
  string city;
  Compte privateAccount;
  Compte savingsAccount;
  bool male;
};

class Banque
{
public:
  Banque() : clients(), compteboucle(false) {}
  void ajouterclient(Client& c)
  {
    clients.push_back(c);
  }
  void bouclercompte() {
    list<Client>::iterator clientiterator;
    for (clientiterator=clients.begin(); clientiterator!=clients.end(); ++clientiterator){
      (*clientiterator).bouclercompte();
    }
    compteboucle=true;
  }
  void afficherBanque() {
    if (compteboucle)
      cout << "Donnees apres le bouclement des comptes :" << endl;
    else
      cout << "Donnees avant le bouclement des comptes :" << endl;
    list<Client>::iterator clientiterator;
    for (clientiterator=clients.begin(); clientiterator!=clients.end(); ++clientiterator){
      (*clientiterator).afficherinfoclients();
    }
  }
private:
  list<Client> clients;
  bool compteboucle;
};

int main()
{
  Client pedro("Pedro", "Geneve", 1000.0, 0.01, 2000.0, 0.02, true);
  Client alexandra("Alexandra", "Lausanne", 3000.0, 0.01, 4000.0, 0.02, false);

  Banque banque;
  banque.ajouterclient(pedro);
  banque.ajouterclient(alexandra);

  banque.afficherBanque();
  banque.bouclercompte();
  banque.afficherBanque();
  
  return 0;
}
