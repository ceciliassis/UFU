package dijkstra;

public class Aresta{
    private final  Vertice org;
    private final  Vertice dest;
    private double peso;

    public Aresta(Vertice org, Vertice dest, double peso) {
        this.org = org;
        this.dest = dest;
        this.peso = peso;
    }
    
    public Vertice getOrg() {
        return org;
    }

    public Vertice getDest() {
        return dest;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    
}