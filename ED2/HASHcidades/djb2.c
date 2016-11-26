
//LENDO O ARQUIVO TXT

#include <stdio.h>
#include <stdlib.h>

const int m = 1021;

int hash(char v[]);

int main ()

{
	int cidades[m], i=0, h, soma=0;
	int zero=0,um=0, dois=0,tres=0, quatro=0, cinco=0, seis=0, sete=0, oito=0, nove=0 , dez=0, mais=0;
	char url[]="cidades.txt";  //caminho do txt
	char nome[50]; //string a ser lida
	FILE *arq; //arquivo

	for(h=0;h<m;h++){
		cidades[h]=0;
	}

	arq = fopen(url,"r"); // funcao que abre o arquivo

	if(arq==NULL)
		printf("Nao foi possivel abrir o arquivo\n");
	else {
		while((fgets(nome, sizeof(nome),arq)) !=NULL ){
			h = hash(nome);
			cidades[h]++;
			i++;
		}
	}

	fclose(arq);

	for (h=0; h<m; h++){
		if(cidades[h]>1) // se for 1 só tem 1 cidade associada, se nao, teve colisao
			soma = soma + cidades[h]; //  quantidade total de colisoes
	}

	for (h=0; h<m; h++){
		if (cidades[h]==0)
			zero++;
		else if(cidades[h]==1)
			um++;
			else if(cidades[h]==2)
				dois++;
				else if(cidades[h]==3)
					tres++;
					else if(cidades[h]==4)
						quatro++;
						else if(cidades[h]==5)
							cinco++;
							else if(cidades[h]==6)
								seis++;
								else if(cidades[h]==7)
									sete++;
									else if(cidades[h]==8)
										oito++;
										else if(cidades[h]==9)
											nove++;
											else if(cidades[h]==10)
												dez++;
												else if(cidades[h]>10)
													mais++;
	}
	printf(" ----- ESTATISTICAS -----\n\n");
	printf("Tamanho do registro: %d\n", i);
	printf("Numero total de colisoes: %d\n", soma);
	printf("Numero de endereco com:\n	 1 cidade associada: %d \n	 2 cidades associadas: %d\n	 3 cidades associadas: %d\n",um, dois,tres);
	printf("	 4 cidades associadas: %d \n	 5 cidades associadas: %d\n	 6 cidades associadas: %d\n",quatro,cinco,seis);
	printf(" 	 7 cidades associadas: %d \n	 8 cidades associadas: %d\n	 9 cidades associadas: %d\n",sete,oito,nove);
	printf(" 	 10 cidades associadas: %d \n	 Mais de 10 cidades associadas: %d\n",dez,mais);
	return 0;
}

int hash(char v[]) {
   int i;
   unsigned int h = 5381;
   for (i = 0; v[i] != '\0'; i++)
      h = ( (h << 5 ) + h) + v[i];
   return h;
}
