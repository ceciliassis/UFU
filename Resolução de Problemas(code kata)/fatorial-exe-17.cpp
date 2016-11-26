/*
Crie um algoritmo que determina se um numero n e' ou nao um numero fatorial.
(um numero e' fatorial se existe algum x tal que x! = n)
*/

#include <iostream>
#include <string>

using namespace std;

bool ehFatorial(float);

int main(){
  float n;
  cin >> n;
  string resp = ehFatorial(n) ? "é Fatorial" : "não é Fatorial";
  cout << resp << endl;
}

bool ehFatorial(float n){
  float soma=1;
  bool flag = false;   
  for( int i=1;i<=n;i++){
    soma*=i;
    if(soma==n){
     // cout << i << endl; de quem?
        flag = true;
        break;
    }
  }
  return flag;
}
