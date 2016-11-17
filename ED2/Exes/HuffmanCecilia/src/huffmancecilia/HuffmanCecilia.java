
package huffmancecilia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Cecília Assis
 */
public class HuffmanCecilia  {
	
    static ArrayList <NoTab> tabel = new ArrayList<>();

    public static void main(String[] args) {
       
        ArrayList <No> lista = new ArrayList<>();
      		//huffmancecilia
        lista.add(new No('e',2, null, null));
        lista.add(new No('s',2, null, null));
        lista.add(new No(' ',2, null, null));
        lista.add(new No('b',3, null, null));
        lista.add(new No('o',3, null, null));
        lista.add(new No('m',3, null, null));
        
    
        codifica(lista);
        criaTabela(lista);
        decodifica("111 00 01 110 100 101 101 100 110 111 00 01 111 00 01");
        
    }

        
     private static void ordenaPorNumero(ArrayList<No> lista) {  
        Collections.sort(lista, new Comparator<No>() {  
            @Override  
            public int compare(No o1, No o2) {  
                return o1.getPesoLetra().compareTo(o2.getPesoLetra());  
            }
        }); 
     
     }
    
    //VERIFICAR ESSA PARTE, ORDENAR NA MAO PARA VER
    private static void codifica(ArrayList<No> lista){
	No esq ;
        No dir ;
		
        while (lista.size()>1){
            ordenaPorNumero(lista); // VERIFICAR, com o slide dela
            
            esq = lista.get(0);
            dir = lista.get(1);
                  
            No novo = new No('#',esq.getPesoLetra()+dir.getPesoLetra()); //não da pra passar um caracter vazio
           
            
            novo.setEsquerdo(esq);
            novo.setDireito(dir);
     
            lista.add(novo);
            
            lista.remove(esq); 
            lista.remove(dir); 
               
        }
    }
	
	
    private static void criaTabela(ArrayList<No> lista){
        for (No letra : lista)
            percorreArvore(letra, new String());     
    }
    
    private static void percorreArvore(No aux, String s){
        
        if(aux.getDireito()==null && aux.getEsquerdo()==null){
            System.out.println("Letra: " + aux.getLetra());
            System.out.println("Código: " + s);
          	    
            tabel.add(new NoTab(aux.getLetra(),s));
        }
    
        else{
            percorreArvore(aux.getEsquerdo(),s+"0");
            System.out.println();
            percorreArvore(aux.getDireito(), s+"1");                
        }
       
    }
    
    private static void decodifica(String word){
       System.out.println("\n----- Decodificando ---- \n");
       String[] aux1 = word.split(" ");
       for (String token: aux1){
           for (NoTab aux : tabel){
                if (aux.getCode().equals(token))
                    System.out.print(aux.getLetra());
           }   
       }
       System.out.println("\n");
       
        
    }
    
	
    
}

