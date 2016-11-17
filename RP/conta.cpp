#include<bits/stdc++.h>

using namespace std;

void conta(vector<int> &, int  );

int main(){
	vector<int> v;
	int nota, n;
	while(cin>>nota){
		while(cin>>n){
			v.push_back(n);		
		}
		conta(v,nota);
		
			
	}
	return 0;

}

void conta(vector<int> &v , int nota){
	int c = 0;
	for(int value: v ){
		if(value >= nota)
			c++;
	}
	cout << c << endl;

}
