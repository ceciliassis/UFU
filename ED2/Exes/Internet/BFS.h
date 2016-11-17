#include<iostream>
#include <list>
 
using namespace std;
 

class Graph
{
    int V;    // No. de vertices
    list<int> *adj;   
public:
    Graph(int V);  
    void addEdge(int v, int w); 
    void BFS(int s);  
};
 
Graph::Graph(int V)
{
    this->V = V;
    adj = new list<int>[V];
}
 
void Graph::addEdge(int v, int w)
{
    adj[v].push_back(w); 
}
 
void Graph::BFS(int s)
{
    // Marca todos como não visitados
    bool *visited = new bool[V];
    for(int i = 0; i < V; i++)
        visited[i] = false;
 
    // Cria a pilha
    list<int> queue;
 
    // Marca como visitado e poe na pilha
    visited[s] = true;
    queue.push_back(s);
 
    // Pega todos os vertices adjacentes
    list<int>::iterator i;
 
    while(!queue.empty())
    {
        //Tira da pilha e imprime
        s = queue.front();
        cout << s << " ";
        queue.pop_front();
 
        //Pega todos os vertices adjacentes do vertice retirado da pilha
        // Se algum adjacente nao foi visitado, marca como visitado
        // E coloca na pilha
        for(i = adj[s].begin(); i != adj[s].end(); ++i)
        {
            if(!visited[*i])
            {
                visited[*i] = true;
                queue.push_back(*i);
            }
        }
    }
}
 