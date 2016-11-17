package dfsBfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Cecília Assis
 */
public class Grafo {

    
    public static void main(String[] args) {
        Grafo grafo = new Grafo ();
        //BFS
        //DFS
        
        
        
    }
   
    static List <Vertice> vertices;
    static List <Aresta> arestas;
    
    Grafo (){
        vertices = new ArrayList<>();
        arestas =  new ArrayList<>();
    }
       
    static Vertice addVertice (int valor){
        Vertice vert = new Vertice (valor);
        vertices.add(vert);
        return vert;
    }
    
    static Aresta addAresta (Vertice org, Vertice dest){
        Aresta ar =  new Aresta (org, dest);
        org.addAresta(ar);
        arestas.add(ar);
        return ar;
    }
    
    static void BuscaGrafoDFS(){
        for (Vertice v : vertices){//Para cada vertice do grafo
            v.setVisit(false); // Marca v como não visitado;
        }
        for (Vertice v : vertices){//Para cada vertice do grafo
            if (!v.getVisit()) // Não visitado
                BuscaProfDFS(v);
            
        }
    }
    
    static void BuscaProfDFS(Vertice v){
        
        v.setVisit(true); 
        List <Aresta> adjs = new ArrayList<>();
        adjs = v.getLinks();
        
        for (Aresta adj : adjs){
            Vertice aux = adj.getDest();
            if (!aux.getVisit()){
                    System.out.println(aux.getValor());
                    BuscaProfDFS(aux); 
            }
                
				
        }   
    }
      
    static void BuscaLarguraBFS(Vertice v){
        List <Aresta> adjs = new ArrayList<>();
        Queue<Vertice> fila = new LinkedList<>(); //Inicializa a Fila
        v.setVisit(true); // Marca V como visitado
        fila.add(v);
               
        while (!fila.isEmpty()){    
            Vertice u = fila.remove(); //primeiro elemento da fila
            adjs = u.getLinks(); //pega as arestas vertice que saiu da fila
            
            for (Aresta adj : adjs){ //para cada vertice w adj a u
                Vertice w = adj.getDest();
                if (!w.getVisit()){ //se nao foi visitado
                    w.setVisit(true); //marca como visitado
                    System.out.println(w.getValor()); //mostra
                    fila.add(w); // coloca ele no final da fila
                }
            }
            
            adjs = null;
        }
            
    }
    
}

