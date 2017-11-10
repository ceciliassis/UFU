package dijkstra;

import java.util.ArrayList;
import java.util.List;

class Vertice {
    private final char valor; // valor do vertice
    private final List <Aresta> links = new ArrayList<>(); // Lista de arestas
    private double minDist;
    private Vertice pred;
        
    Vertice (char valor){
        this.valor = valor;
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

     public double getminDist() {
        return minDist;
    }

    public void setminDist(double minDist) {
        this.minDist = minDist;
    }

    public Vertice getPred() {
        return pred;
    }

    public void setPred(Vertice pred) {
        this.pred = pred;
    }
       
}