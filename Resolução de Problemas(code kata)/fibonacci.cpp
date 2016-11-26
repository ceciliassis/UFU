#include <iostream>
using namespace std;


void fibonacci();
int main(){
  fibonacci();
}

void fibonacci(){
  int t1 = 1, t2 = 3;
  cout << t1 << endl;
  cout << t2 << endl;
  while(true){
    int soma = t1 + t2;
    t1 = t2;
    t2 = soma;
    cout << soma << endl;    
  }    
}
