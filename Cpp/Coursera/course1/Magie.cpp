#include <iostream>
using namespace std;

class Papier {
public:
  void setContent(long _age, long _argent)
  {
    age = _age;
    argent = _argent;
  }
  void getContent(long& _age, long& _argent) const
  {
    _age = age;
    _argent = argent;
  }
private:
  long age;
  long argent;
};

class Spectateur
{
public:
  void getAgeArgent() {
    long _age;
    long _argent;
    cout << "[Spectateur] (j'entre en scene)" << endl;
    bool isAgeOk = false;
    while (!isAgeOk){
      cout << "Quel age avez-vous? "; cin >> _age;
      isAgeOk = _age >= 0 && _age <= 100;
    }
    age = _age;
    bool isArgentOk = false;
    while (!isArgentOk) {
      cout << "Combien d'argent avez-vous? "; cin >> _argent;
      isArgentOk = _argent >= 0 && _argent <100;
    }
    argent = _argent;
    cout << "[Spectateur] (je suis la)." << endl;
  }
  void EcritPapier(Papier& papier) const
  {
    cout << "[Spectateur] (j'ecris le papier)" << endl;
    papier.setContent(age,argent);
  }
private:
  long age;
  long argent;
};

class Assistant
{
public:
  void lirePapier(Papier papier)
  {
    cout << "[Assistant] (je lis le papier)" << endl;;
    papier.getContent(age_spectateur, argent_spectateur);
  }
  int faireCalcul()
  {
    cout << "[Assistant] (je calcul mentalement)" << endl;
    long result = 100*age_spectateur+argent_spectateur-115;
    cout << "[Assistant] J'annonce: " << result << endl;
    return result;
  }
private:
  long age_spectateur;
  long argent_spectateur;
};

class Magicien
{
public:
  void donnerResultat(long operation, long& age, long& argent)
  {
    long temp = operation + 115;
    argent = temp % 100;
    age = (temp - argent) / 100;
  }
};

int main()
{
  Spectateur spectateur;
  spectateur.getAgeArgent();
  cout << "[Magicien] Un petit tour de magie." << endl;
  Papier papier;
  spectateur.EcritPapier(papier);
  cout << "[Spectateur] (je montre le papier)" << endl;
  Assistant assistant;
  assistant.lirePapier(papier);
  long operationresult;
  operationresult = assistant.faireCalcul();
  long magicien_age, magicien_argent;
  Magicien magicien;
  magicien.donnerResultat(operationresult,magicien_age,magicien_argent);
  
  cout << "Age spectateur: " << magicien_age << endl;
  cout << "Argent spectateur: " << magicien_argent << endl;
  
  return 0;
}
