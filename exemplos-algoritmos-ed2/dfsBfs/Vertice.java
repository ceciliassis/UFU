package dfsBfs;

import java.util.ArrayList;
import java.util.List;

class Vertice{
    private final int valor; // valor do vertice
    private final List <Aresta> links = new ArrayList<>(); // Lista de arestas
    private boolean visit;
        
    Vertice (int valor){
        this.valor = valor;
    }

    void addAresta(Aresta nova){
        links.add(nova); // Pode ter varias arestas
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public boolean getVisit() {
        return visit;
    }

    public List<Aresta> getLinks() {
        return links;
    }

    public int getValor() {
        return valor;
    }

   
        
    
        
}