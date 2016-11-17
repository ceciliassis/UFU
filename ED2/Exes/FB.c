#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define max 50

int forma (int num);
int bin_aux(int num, unsigned int parcial, int controle);

int main()
{
    int val;
    double res;
    printf("Entre com o numero que deseja transformar: ");
    scanf("%d", &val);
    res=forma(val);
    printf("Forma binaria: %.0lf", res);
    return 0;
}

int bin_aux(int num, unsigned int parcial, int controle)
{
    if ( num < 2 )
        return pow(10, ++controle) + parcial;

    return bin_aux( (num / 2), ( pow(10, controle) * (num % 2) ) + parcial, ++controle );
}

int forma(int num)
{
    return bin_aux(num, 0, -1);
}


