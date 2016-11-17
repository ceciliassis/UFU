#include<iostream>
#include <list>
 
using namespace std;
 
class Graph
{
    int V;    // No. de vertices
    list<int> *adj;    // Array de vertices adjacentes
    void DFSUtil(int v, bool visited[]); 
public:
    Graph(int V);  
    void addEdge(int v, int w);   //Add arestas
    void DFS(int v);   
};
 
Graph::Graph(int V)
{
    this->V = V;
    adj = new list<int>[V];
}
 
void Graph::addEdge(int v, int w)
{
    adj[v].push_back(w); // Add w to v’s list.
}
 
void Graph::DFSUtil(int v, bool visited[])
{
    // Marca o no atual como visitado e imprime ele
    visited[v] = true;
    cout << v << " ";
 
    // Busca todos os vertices adjacentes do no
    list<int>::iterator i;
    for(i = adj[v].begin(); i != adj[v].end(); ++i)
        if(!visited[*i])
            DFSUtil(*i, visited);
}
 

void Graph::DFS(int v)
{
    // Marca os vertices como nao visitados
    bool *visited = new bool[V];
    for(int i = 0; i < V; i++)
        visited[i] = false;
 
    //Imprime os vertices
    DFSUtil(v, visited);
}