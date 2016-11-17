#include <stdio.h>
#include <stdlib.h>

int main()
{
    int moedas[10], troco;
    int qntMoedas, i, vals, j, flag=1, aux;
    float valF;
    printf("Quantos valores de moedas voce tem? ");
    scanf("%d", &qntMoedas);
    printf("\nSe o valor da moeda for 0.01, digite 1: se for 1: digite 100 !!\n\n");
    //alguma forma de não ter que fazer isso??
    for(i=0;i<qntMoedas;i++){
        printf("Entre com o valor das moedas: ");
        scanf("%d", &moedas[i]);
    }
    printf("Entre com o troco: ");
    scanf("%d", &troco);
//ordenando pela menor
    for(i = 1; i<qntMoedas && flag==1; i++){
            flag=0;
            for( j = 0; j<qntMoedas-1; j++){
                if(moedas[j] < moedas[j + 1]){
                    aux = moedas[j];
                    moedas[j] = moedas[j+1];
                    moedas[j+1] = aux;
                    flag=1;
                }
            }
    }
    printf("TROCO!!  \n");
    troco=troco*100;
    while (troco >= 0){
        for(i=0;i<qntMoedas;i++){
            if (troco >=  moedas[i]){
                vals  = troco/ moedas[i];
                troco = troco - (vals * moedas[i]);
                valF = moedas[i]/100;
                printf("  - %d moedas de %.2f", vals, valF);
            }

        }
    }



    return 0;
}
