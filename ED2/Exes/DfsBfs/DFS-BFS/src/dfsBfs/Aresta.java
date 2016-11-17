package dfsBfs;

public class Aresta{
    private final Vertice org;
    private final Vertice dest;
        
    Aresta (Vertice org, Vertice dest){
        this.org = org;
        this.dest = dest;
    }

    public Vertice getOrg() {
        return org;
    }

    public Vertice getDest() {
        return dest;
    }
        
}