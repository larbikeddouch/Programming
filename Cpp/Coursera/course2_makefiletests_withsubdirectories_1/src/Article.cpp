#include <Article.h>

Article::Article(string _nom, double _prix)
  : nom(_nom), prix(_prix), enSolde(false) {}
Article::Article(string _nom, double _prix, bool _enSolde)
  : nom(_nom), prix(_prix), enSolde(_enSolde) {}
Article::Article(const Article& _article)
  : nom(_article.nom), prix(_article.prix), enSolde(_article.enSolde) {}
string Article::getNom() const { return nom;}
double Article::getPrixUnitaire() const {return prix;}
bool Article::getEnSolde() const {return enSolde;}
