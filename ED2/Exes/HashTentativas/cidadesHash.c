#include <stdio.h>
//ENTRANDO CHAVE POR CHAVE
#include <stdlib.h>
#define M 1021

int hash(char v[], int M);

int main()
	
{

	int tab[M],  h, soma=0, n=10;
	char nome [50];
	for(h=0;h<M;h++){
		tab[h]=0;
	}
	while (n>0){
		gets(nome);
		h = hash(nome, M);
		if(tab[h]==0)
			tab[h]=1;
		else
			tab[h]++;
		n--;
	}
	for (h=0;h<M;h++)
		soma=soma+tab[h]; //  quantidade total de colisões
	for (h=0;h<M;h++)
		printf("Endereco: %d , numero de cidades associadas: %d ", h, tab[h]);
    return 0;
}

int hash(char v[], int M) {
   int i, h = v[0];
   for (i = 1; v[i] != '\0'; i++)
      h = (h * 251 + v[i]) % M;
   return h;
}
