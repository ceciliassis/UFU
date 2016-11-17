package dijkstra;

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
        Vertice a = Grafo.addVertice('a'); 
        Vertice b = grafo.addVertice('b');
        Vertice c = grafo.addVertice('c');
        Vertice d = grafo.addVertice('d');
        Vertice e = grafo.addVertice('e');
        Vertice f = grafo.addVertice('f');
        Vertice g = grafo.addVertice('g');

        Aresta ab = grafo.addAresta(a,b,1);
        Aresta ba = grafo.addAresta(b,a,1);
        Aresta bd = grafo.addAresta(b,d,2);
        Aresta db = grafo.addAresta(d,b,2);
        Aresta ac = grafo.addAresta(a,c,6);
        Aresta ca = grafo.addAresta(c,a,6);
        Aresta de = grafo.addAresta(d,e,3);
        Aresta ed = grafo.addAresta(e,d,3);
        Aresta eg = grafo.addAresta(e,g,7);
        Aresta ge = grafo.addAresta(g,e,7);
        Aresta ef = grafo.addAresta(e,f,2);
        Aresta fe = grafo.addAresta(f,e,2);
        Aresta gf = grafo.addAresta(g,f,5);
        Aresta fg = grafo.addAresta(f,g,5);
        Aresta ae = grafo.addAresta (a,e,7);
        Aresta ea = grafo.addAresta (e,a,7);
        Aresta cd = grafo.addAresta (c,d,1);
        Aresta dc = grafo.addAresta (d,c,1);
        
        dijkstra(a);
        
        for (Vertice v : vertices){
            System.out.println("Distancia para: " + v.getValor() + "-> " + v.getminDist());
            List<Vertice> caminho = porOndeAndei(v);
            System.out.print("Caminho: ");
            for (Vertice o : caminho)
                System.out.print(o.getValor()+ " - ");
            System.out.println();
        }

    }
   
    static List <Vertice> vertices;
    static List <Aresta> arestas;
   
    Grafo (){
        vertices = new ArrayList<>();
        arestas =  new ArrayList<>();
    }
       
    static Vertice addVertice (char valor){
        Vertice vert = new Vertice (valor);
        vertices.add(vert);
        return vert;
    }
    
    static Aresta addAresta (Vertice org, Vertice dest, int peso){
        Aresta ar =  new Aresta (org, dest, peso);
        org.addAresta(ar);
        arestas.add(ar);
        return ar;
    }
    
    static void dijkstra (Vertice s){
        start(s);
        go(s);
    }
    
    static void start (Vertice s){
        for (Vertice e : vertices){
            e.setminDist(Double.POSITIVE_INFINITY);
            e.setPred(null);
        }
        s.setminDist(0);
    }
    
    static void go (Vertice s){
        Queue<Vertice> verts = new LinkedList<>(); 
        verts.add(s);
        
        while(!verts.isEmpty()){            
            Vertice aux = verts.remove() ;
            List<Aresta> adjs = aux.getLinks();
                      
            for(Aresta adj: adjs){
                Vertice aux2 = adj.getDest();
                
                double custo = aux.getminDist() + adj.getPeso();
                
                if(custo < aux2.getminDist()){
                    verts.remove(aux2); //removo
                    aux2.setminDist(custo); //atualizo os dados
                    aux2.setPred(aux); //
                    verts.add(aux2); // insiro em sua ordem correta
                }
            }
        }
    }
    
    static List<Vertice> porOndeAndei (Vertice s){
       List<Vertice> caminho =  new ArrayList<>();
       for (Vertice v = s; v !=null ; v = v.getPred()) //loop que volta, recursivo
           caminho.add(v);  
       return caminho;
       
    }  
    
}

