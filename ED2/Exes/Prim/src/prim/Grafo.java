package prim;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Cecília Assis
 */
public class Grafo {
    static List <Vertice> vertices;
    static List <Aresta> arestas;

    
    public static void main(String[] args) {
        Grafo grafo = new Grafo ();
        //INICIALIZA O GRAFO
        Vertice a = grafo.addVertice('a');
        Vertice b = grafo.addVertice('b');
        Vertice c = grafo.addVertice('c');
        Vertice d = grafo.addVertice('d');
        Vertice e = grafo.addVertice('e');
        Vertice f = grafo.addVertice('f');
       
        
        grafo.addAresta(a,c,1);
        grafo.addAresta(c,a,1);
        grafo.addAresta(a,b,2);
        grafo.addAresta(b,a,2);
        grafo.addAresta(c,b,2);
        grafo.addAresta(b,c,2);
        grafo.addAresta(c,d,2);
        grafo.addAresta(d,c,2);
        grafo.addAresta(b,d,1);
        grafo.addAresta(d,b,1);
        grafo.addAresta(c,e,3);
        grafo.addAresta(e,c,3);
        grafo.addAresta(e,d,3);
        grafo.addAresta(d,e,3);
        grafo.addAresta(e,f,1);
        grafo.addAresta(f,e,1);
        grafo.addAresta(d,f,4);
        grafo.addAresta(f,d,4);
        
        
        
        
      
        
        
        prim();
        
   }
   
    Grafo (){
        vertices = new ArrayList<>();
        arestas =  new ArrayList<>();
    }
 
    static Vertice addVertice (char valor){
        Vertice vert = new Vertice (valor);
        vertices.add(vert);
        return vert;
    }
    
    
    static void addAresta (Vertice ori, Vertice dest, int peso){
        Aresta ar =  new Aresta (ori, dest, peso);
        ori.addAresta(ar);
        arestas.add(ar);
    }
    
    
    static void prim(){     
        Queue<Aresta> safe = new PriorityQueue<>();
        List<Aresta> caminho = new ArrayList<>();
        
        Vertice aux = vertices.get(0); //Depende do vertice que voce pega, pode ser pegado um aleatorio
        vertices.remove(aux);

        while (!vertices.isEmpty()){   
            if(aux.getLinks().isEmpty()){ //Não contem adjacentes
                break;
            }
            else{
                for (Aresta o :aux.getLinks()){
                    if(vertices.contains(o.getDest())) //caminhando dentre as que ja tenho , se o destino existe , posso usar a aresta
                        if(!safe.contains(o))
                            safe.add(o);                     
                }
                
                Aresta u = safe.remove();
                caminho.add(u);

                aux = u.getDest();
                vertices.remove(aux); //retiro para que nao acesse a aresta novamente
            }
                
            

        }
        
        oQueFormei(caminho);
        

    }

    
   
    static void oQueFormei(List<Aresta> aux){
        int acumun = 0;
        for (Aresta e: aux ){
            acumun +=e.getPeso();
            System.out.println("De: "+e.getOri().getValor() + " -> Para: " + e.getDest().getValor() + " \n Peso: " +e.getPeso() );
        }
        System.out.println("Valor final da arvore: "+acumun);
    }
}

