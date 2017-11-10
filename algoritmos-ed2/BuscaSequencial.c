#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// BUSCA SEQUENCIAL

void pesquisa(int vet[], int num);
void main (){

	int vetor [100], i, val;

	for (i=0; i<100; i++){
		vetor[i]= rand() % 100;

	}
    for (i=0; i<100; i++){
		printf("Posicao = %d, Valor = %d\n",i,vetor[i]);

	}
	printf(" Entre com o valor a ser pequisado: ");
	scanf("%d", &val);

    pesquisa(vetor, val);

}

void pesquisa(int vet[], int num){

    bool achou=false;
    int i, p;

    for (i=0; i<100 && achou == false; i++){
		if (num==vet[i]){
            achou=true;
            p=i;
		}
        else
            achou = false;
	}

    if (achou)
        printf("O valor desejado foi encontrado na -> Posicao : %d com verificacoes feitas: %d", p,p);

    else{
        printf(" O valor desejado nao foi achado.");
        printf(" Posicao : %d, Verificacoes feitas: %d", i,i);

    }
}
