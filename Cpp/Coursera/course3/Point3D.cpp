#include <iostream>
using namespace std;

class Point3D
{
public:
  void init(double _x, double _y, double _z)
  {
    x= _x;
    y= _y;
    z= _z;
  }
  void affiche()
  {
    cout << "( " << x << ", " << y << ", " << z <<")" << endl;
  }
  string to_string() const
  {
    return "( " + std::to_string(x) + ", " + std::to_string(y) + ", " + std::to_string(z) + ")";
  }
  bool compare(Point3D p)
  {
    return p.x == x && p.y == y && p.z == z;
  }
  void getpointfromconsole() {
    double _x;
    double _y;
    double _z;
    cout << "Construction d'un nouveau point" << endl;
    cout << "     Veuillez entrer x: "; cin >> _x;
    cout << "     Veuillez entrer y: "; cin >> _y;
    cout << "     Veuillez entrer z: "; cin >> _z;
    init(_x,_y,_z);
  }
  double getSquaredDistance(Point3D p) {
    return (p.x-x)*(p.x-x) + (p.y-y)*(p.y-y) + (p.z-z)*(p.z-z);
  }
private:
  double x;
  double y;
  double z;
};

ostream& operator<<(ostream& output, Point3D const& p)
{
  output << p.to_string();
  return output;
}

class Triangle
{
public:
  void init(){
    point1.getpointfromconsole();
    point2.getpointfromconsole();
    point3.getpointfromconsole();
  }
  bool isIsocele() {
    double d1, d2, d3;
    d1 = point1.getSquaredDistance(point2);
    d2 = point1.getSquaredDistance(point3);
    d3 = point2.getSquaredDistance(point3);
    return d1 == d2 || d1 == d3 || d2 == d3;
  }
  void affiche() const {
    cout << "Point 1: " << point1 << endl;
    cout << "Point 2: " << point2 << endl;
    cout << "Point 3: " << point3 << endl;
  }
  string to_string() const {
    string result = "Point 1: " + point1.to_string() + "\n";
    result += "Point 2: " + point2.to_string() + "\n";
    result += "Point 3: " + point3.to_string() + "\n";
    return result;
  }
private:
  Point3D point1;
  Point3D point2;
  Point3D point3;  
};

ostream& operator<<(ostream& output, Triangle const& triangle)
{
  output << triangle.to_string();
  return output;
}

int main()
{
  /*Point3D point1;
  Point3D point2;
  Point3D point3;

  point1.init(1.0,2.0,-0.1);
  point2.init(2.6,3.5,4.1);
  point3 = point1;

  cout << "Point 1: ";
  point1.affiche();

  cout << "Point 2: ";
  point2.affiche();

  cout << "Le point 1 est ";
  if (point1.compare(point2)){
    cout << "identique au";
  }
  else {
    cout << "different du";
  }
  cout << " point 2" << endl;

  cout << "Le point 1 est ";
  if (point1.compare(point3)){
    cout << "identique au";
  }
  else {
    cout << "different du";
  }
  cout << " point 3" << endl;*/

  Triangle triangle;
  triangle.init();

  cout << "Depuis la methode afficher de la classe Triangle" << endl;
  triangle.affiche();
  cout << "================================================" << endl;
  cout << "Depuis cout << surcharge pour la classe Triangle" << endl;
  cout << triangle;
  cout << "================================================" << endl;
  
  
  if (triangle.isIsocele()) {
    cout << "Ce triangle est isocele!" << endl;
  }
  else{
    cout << "Ce triangle n'est pas isocele!" << endl;
  }
    
  return 0;
}
