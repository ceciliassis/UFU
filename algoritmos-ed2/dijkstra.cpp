#include <bits/stdc++.h>

using namespace std;

#define INF 0x3f3f3f3f

const int N = 101010;

vector< pair<int,int> > g[N];
int st, fs; // st = partida, fs = chegada
int dis[N]; // vetor de menores distancias de st até i

int dijkstra()
{
     memset(dis,INF,sizeof dis); //populando com infinity o vetor distancias
     dis[st] = 0; // a distancia dele pra ele mesmo é 0
     priority_queue< pair<int,int> > q; // no pair do dijkstra guardamos a distancia de st até i
     // e o vertice i
     q.push( make_pair(0,st) );
     while( !q.empty() )
     {
         pair<int,int> foo = q.top(); q.pop();
         int x = foo.second, w = foo.first*-1; // o custo é adicionado negativo na fila
         // pois precisamos dos pesos ordenados do menor para o maior

         if( dis[x] != w ) continue; // aqui é a parte do codigo que evita redundancias.

         for(int i=0;i<g[x].size();++i) //aqui eu passo nas arestas que estão ligadas no x
         {
             pair<int,int> at = g[x][i];
             int y = at.first, c = at.second;

             // tenho uma aresta de x->y com custo c
             if( dis[y] > dis[x]+c ) //se a distancia que eu tenho de st->y for maior que a
             // distancia de st->x + x->y eu tenho que atualizar o caminho de y
             {
                 dis[y] = dis[x]+c;
                 q.push(make_pair(-dis[y],y)); // adiciono na fila o vertice y
             }
         }
     }
     return dis[fs];
}

int main()
{
    return 0;
}
