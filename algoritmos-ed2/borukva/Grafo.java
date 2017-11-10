
package borukva;

  

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.graph.builder.UndirectedGraphBuilderBase;
import org.jgrapht.*;
import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author Cecília Assis
 */
public class Grafo {
    static List <Vertice> vertices;
    static List <Aresta> arestas;
    String nome ;
    static Graph<Object, DefaultEdge> completeGraph;
    
    public static void main(String[] args) {
        Grafo grafo = new Grafo ("original");
        //INICIALIZA O GRAFO
        Vertice s = grafo.addVertice(1, vertices);
        Vertice t = grafo.addVertice(3, vertices);
        Vertice y = grafo.addVertice(2, vertices);
        grafo.addAresta(s, t,1, arestas);
        grafo.addAresta(s, s,1, arestas);
        grafo.addAresta(t, y,1, arestas);
        
   }
   
    Grafo (){
        vertices = new ArrayList<>();
        arestas =  new ArrayList<>();
    }
       
    Grafo (List<Aresta> ars, List<Vertice> verts, String nome ){
        ars = new ArrayList<>();
        verts =  new ArrayList<>();
        this.nome =  nome;
    }
    
    Grafo (String nome){
        this.nome =  nome;
    }
    
    static Vertice addVertice (int valor, List<Vertice> verts){
        Vertice vert = new Vertice (valor);
        verts.add(vert);
        return vert;
    }
    
    static void addAresta (Vertice ori, Vertice dest, double peso, List<Aresta> ars){
        Aresta ar =  new Aresta (ori, dest, peso);
        ori.addAresta(ar);
        ars.add(ar);
    }
    
  
    static void boruvka(){
        int i = 1;
        Aresta a =  null;
        List<Aresta> ares = null;
        List<Vertice> vertis = null;
        List<Grafo> grafos = new ArrayList<>();
        
        for(Vertice v : vertices){
                v.setVisit(true);
                a = calcMin(v);
                
        }
        
        for(Aresta e : arestas){
            if(e.isMatch()){
                
            }
            
            
        }

        
    }
    
    static Aresta calcMin (Vertice v){
        double min = Double.POSITIVE_INFINITY;
        Aresta def = null;
        
        for (Aresta e : v.getLinks() ){
            if (e.getPeso()<min){
                min = e.getPeso();
                def = e;
            }
        }
        
        def.setMatch(true);
        return def;
    }
   
    
}

