#include<stdio.h>
#include<string.h>
#include <stdlib.h>
 
#define d 256 
  
void search(char *pad, char *txt, int q)
{
    int M = strlen(pad);
    int N = strlen(txt);
    int i, j;
    int p = 0;  
    int t = 0; 
    int h = 1;

    for (i = 0; i < M-1; i++)
        h = (h*d)%q;
  
 
    for (i = 0; i < M; i++) {
        p = (d*p + pad[i])%q;
        t = (d*t + txt[i])%q;
    }
    printf("t %d \n", t);
  
    for (i = 0; i <= N - M; i++) {
    	printf("comeco %d \n", i);
    	printf("t %d \n", t);

        if ( p == t ) {
            for (j = 0; j < M; j++) {
                if (txt[i+j] != pad[j])
                    break;
            }
            if (j == M)             
                printf("Padrao encontrado na posicao %d \n", i);
            
        }
                    
        if ( i < N-M ) {
        	printf("fiz %d \n", i);
            t = (d*(t - txt[i]*h) + txt[i+M])%q; 
             
            if(t < 0) 
              t = (t + q); 
        }
        
        printf("fim %d \n", i);
    }
}
  

int main()
{
    char *txt = "123456";
    char *pad = "345";
    int q = 101;
    search(pad, txt, q);
    getchar();
    return 0;
}
