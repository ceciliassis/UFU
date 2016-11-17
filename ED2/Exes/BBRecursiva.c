#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void bubble(int *a);
int busca(int *a, int x , int inicio, int fim);

int main() {
    int vetor [100], i, val, res;

	for (i=0; i<100; i++)
		vetor[i]= rand() % 100;

	bubble(vetor);

	for (i=0; i<100; i++)
        printf("Posicao = %d ; Valor = %d\n", i,vetor[i]);

    printf("Entre com o valor a ser procurado: ");
    scanf("%d", &val);

    res = busca(vetor, val, 0, 99);

    if (res==-1)
        printf("Numero nao encontrado");
    else printf ("Numero encontrado na posicao: %d", res);

    return 0;
}

void bubble(int *a) {
     int i, j;
	 int temp;
	 int troca;

	 troca = true;

	 for (i= 99; (i >=0 ) && (troca == true); i--) {
		 troca = false;
		 for (j= 0; j < i ;j++) {
			 if (a[j] > a[j+1]) {
				 temp = a[j];
				 a[j] = a[j+1];
				 a[j+1]= temp;
				 troca = true;
	 		}
		}
	}
}

int busca(int *a, int x , int inicio, int fim){
    if (inicio>fim)
        return -1;
    int meio;
    meio = (inicio+fim)/2;
    if(a[meio]==x)
        return meio;
    else if (x>a[meio])
        return busca(a,x,meio+1,fim);
        else
            return busca(a,x,inicio,meio-1);
}
