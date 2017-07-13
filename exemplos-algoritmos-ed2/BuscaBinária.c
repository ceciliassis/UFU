#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// BUSCA BINARIA

void bubble(int *a);
void busca(int *a);

int main()
{
    int vetor [100], i, val;

	for (i=0; i<100; i++)
		vetor[i]= rand() % 100;

	bubble(vetor);
	for (i=0; i<100; i++)
        printf("Posicao = %d ; Valor = %d\n", i,vetor[i]);
    busca(vetor);
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

void busca(int *a){
    int val, inicio, fim, meio, cont=0, posicao;
    bool achou;
    achou = false;

    printf(" Entre com o valor a ser buscado: ");
    scanf("%d", &val);

    inicio=0;
    fim=99;

    while(inicio<=fim && !achou){
        cont++;
        meio=(inicio+fim)/2;

		if(a[meio]==val)
            achou=true;

        else if(val>a[meio]){
            achou=false;
            inicio=meio+1;
        }
            else {
                achou=false;
                fim=meio-1;
            }

    }
    if (achou){
        printf(" Numero encontrado!\n Quantidade de verificacoes executadas: %d\n Posicao:%d", cont,meio);
        //arredondando para cima
    }
    else{
        printf(" Numero nao encontrado!");
    }

}
