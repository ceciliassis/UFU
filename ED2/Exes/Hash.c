#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#define TABLE_SIZE 853
struct cidades{

    char nome[100];

};
typedef struct Hash{
    int qtd,SIZE;
    struct cidades **itens;
}Hash;

int buscaHash(Hash* ha, char *cidade, struct cidades *c){

    if (ha == NULL){
        return 0;
    }

    int i, pos, newPos;
    pos = vHash(valorString(cidade),TABLE_SIZE);

    for(i = 0; i < TABLE_SIZE;i++){
        newPos = sondagemQuadratica(pos,i,TABLE_SIZE);

        if(ha->itens[newPos] == NULL){
            printf("\nNão encontrado!\n");
            return 0;
        }
        if (strcmp(ha->itens[newPos]->nome,cidade)==0){
            *c = *(ha->itens[newPos]);
                printf("Posição %d com %d colisões\n",newPos,i);
            return i;
        }
    }
    return 0;
}

Hash* criaHash(int SIZE){
    Hash* ha = (Hash*) malloc (sizeof(Hash));
    if(ha != NULL){
        int i;

    SIZE = TABLE_SIZE;
        ha->itens = (struct cidades**)malloc(SIZE * sizeof(struct cidades*));

            if(ha->itens == NULL){
                free(ha);
                return NULL;
            }
            ha->qtd=0;
            for(i=0; i < TABLE_SIZE; i++){
                ha->itens[i] = NULL;
            }
    }
    return ha;
}
int chaveMultiplicacao(int chave, int SIZE){
    SIZE = TABLE_SIZE;
    float a = 0.6180339887;
    float val = chave * a;
    return (int)(SIZE * val);
}

int insereHash(Hash* ha,struct cidades c){
    if (ha == NULL || ha->qtd == TABLE_SIZE){
        return 0;
    }
    char chave[100];
    strcpy(chave,c.nome);

    int i , pos , newPos;
    pos = vHash(valorString(chave),TABLE_SIZE);
    for(i = 0; i < TABLE_SIZE; i++){
        newPos = sondagemQuadratica(pos,i,TABLE_SIZE);

        if(ha->itens[newPos] == NULL){
            struct cidades* novo;
            novo = (struct cidades*)malloc(sizeof(struct cidades));
            if(novo == NULL){
                return 0;
            }
            *novo = c;
            ha->itens[newPos] = novo;
            ha->qtd++;
            return 1;
        }
    }
    return 0;
}
int sondagemLinear(int pos, int i, int SIZE){
    SIZE = TABLE_SIZE;
    pos = pos + i;
    return (pos & 0x7FFFFFFF) % SIZE;
}
int sondagemQuadratica(int pos,int i, int SIZE){
    SIZE = TABLE_SIZE;
    pos = pos + 2 * i + 7 * i * i;
    return (pos & 0x7FFFFFFF) % SIZE;
}
int chaveDivisao(int chave, int SIZE){
    SIZE = TABLE_SIZE;
    return (chave & 0x7FFFFFFF) % SIZE;
}

int vHash(int f, int m){

    m = TABLE_SIZE;

    f = ((f * 31 + 347)/7) % m;

    return f;
}
int valorString(char *str){
    int i, valor = 3459;
    int tam = strlen(str);

    for(i=0;i<tam;i++)
        valor = (31 * valor + (int)str[i]);

    return valor;
}
int duploHash(int h1, int chave, int i, int SIZE){
    SIZE= TABLE_SIZE;
    int h2 = chaveDivisao(chave,TABLE_SIZE-1)+1;
    return ((h1+ i*h2) & 0x7FFFFFFF) % SIZE;
}
int main()
{
    struct cidades c[TABLE_SIZE];
    Hash *h = criaHash(TABLE_SIZE);

    setlocale(LC_ALL,"portuguese");
    int i,x;
    char cidade[100];
    FILE *file;
    file = fopen("C:\\Users\\ViníciusR\\Desktop\\cidades.txt","r");

    for(i = 0; i < TABLE_SIZE; i ++){
        fgets(c[i].nome,100,file);
        //printf("%s",c[i].nome);
    }

    for(i = 0; i < TABLE_SIZE; i++){
        x = insereHash(h,c[i]);
    }
    if (x == 1)
        printf("\nCidades inseridas com sucesso!\n");


        /*printf("Entre com a cidade q deseja buscar: ");
        gets(cidade);
        buscaHash(h,cidade,&c);*/
        x = 0;
        for(i = 0 ; i < 853; i++)
            x = x + buscaHash(h,c[i].nome,&c);

        printf("Total de colisões: %d\n",x);
    return 0;
}
