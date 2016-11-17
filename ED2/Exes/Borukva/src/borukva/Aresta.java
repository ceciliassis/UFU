package borukva;

public class Aresta{
    private final Vertice ori;
    private final Vertice dest;
    private double peso;
    private boolean match;

    public Aresta(Vertice ori, Vertice dest, double peso) {
        this.ori = ori;
        this.dest = dest;
        this.peso = peso;
        
    }

    public boolean isMatch() {
        return match;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }
      
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Vertice getOri() {
        return ori;
    }

    public Vertice getDest() {
        return dest;
    }
    
    
        
}