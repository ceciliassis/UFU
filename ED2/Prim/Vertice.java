package prim;

import java.util.ArrayList;
import java.util.List;

class Vertice{
    private char valor; // valor do vertice
    private List <Aresta> links = new ArrayList<>(); // Lista de arestas
    

    Vertice (int valor){
        this.valor = (char) valor;
    }

    void addAresta(Aresta nova){
        links.add(nova); // Pode ter varias arestas
    }

    public List<Aresta> getLinks() {
        return links;
    }

    public char getValor() {
        return valor;
    }

   
        
    
        
}