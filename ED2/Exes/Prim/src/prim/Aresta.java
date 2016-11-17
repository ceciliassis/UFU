package prim;

public class Aresta implements Comparable<Aresta>{
    private final Vertice ori;
    private final Vertice dest;
    private int peso;


    public Aresta(Vertice ori, Vertice dest, int peso) {
        this.ori = ori;
        this.dest = dest;
        this.peso = peso;
        
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Vertice getOri() {
        return ori;
    }

    public Vertice getDest() {
        return dest;
    }

   @Override
    public int compareTo(Aresta o) {
        if (this.peso > o.getPeso())
            return 1;
        else if (this.peso < o.getPeso())
            return -1;
        else 
            return 0;
        
    }
    
    
        
}