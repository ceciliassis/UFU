#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <limits>

using namespace std;

constexpr int INFINITY = numeric_limits<int>::max(); //maior numero entre os ints

void mergin(vector<int> &a , int begin,int middle, int end);
void mergin_sort(vector<int> &a, int begin, int end);

int main(){
  fstream file;
  int i;
  vector<int> vec1;
  string nomearq1, nomearq2;
  cin >> nomearq1 >> nomearq2;
  file.open(nomearq1, ios_base::in);
  if(!file){
    cerr << "erro ao abrir arquivo\n";
    return -1;
  }
  
  while(!file.eof()){
    file >> i;
    vec1.push_back(i);
  }
  file.close();
  
  file.open(nomearq2, ios_base::in);
  if(!file){
    cerr << "erro ao abrir arquivo\n";
    return -1;
  }
  
  while(!file.eof()){
    file >> i;
    vec1.push_back(i);
  }
  file.close();
  
  mergin_sort(vec1,0,vec1.size());
  
  for(auto j : vec1){
        if(j!=INFINITY)
             cout << j <<' ';
    }
       
  cout << endl;
    
}

