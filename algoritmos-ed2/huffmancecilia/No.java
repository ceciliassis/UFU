
package huffmancecilia;

/**
 *
 * @author Cecília Assis
 */
public class No /*implements Comparable<No> */{
    private char letra;
    private No direito, esquerdo;
    private Integer pesoLetra; //porque int nao deu certo?
    //public int valAresta;

    public No(char letra, Integer pesoLetra) {
        this.letra = letra;
        this.pesoLetra = pesoLetra;
    }
	
	
    public No (char letra, Integer pesoLetra, No direito, No esquerdo){
            this.letra = letra;
            this.pesoLetra = pesoLetra;
            this.direito = direito;
            this.esquerdo = esquerdo;
    }

    public No() {
        
    }
    
    public char getLetra() {
        return letra;
    }

    public No getDireito() {
        return direito;
    }

    public No getEsquerdo() {
        return esquerdo;
    }

    public Integer getPesoLetra() {
        return pesoLetra;
    }

    public void setDireito(No direito) {
        this.direito = direito;
    }

    public void setEsquerdo(No esquerdo) {
        this.esquerdo = esquerdo;
    }   

    public void setLetra(char letra) {
        this.letra = letra;
    }

   /* @Override
    public int compareTo(No o) {  
        return this.pesoLetra - o.getPesoLetra();  
        
    }*/

}
