package rabinkarp;

/**
 * @author Cecília Assis
 */
public class RabinKarp {
    static int d = 256; // quantidade de letras no alfabeto
    static int q = 101; //numero primo prox da quantidade de letrass
    
    public static void main(String[] args) {
        procura("oi", "uiuioioi"); // inicialização do programa
    }
    
    static void procura (String padrao, String texto){
        int n = texto.length(); // pega o tamanho do texto onde o padrao será procurado
        int m = padrao.length(); // pega o tamanho do padrao
        int p = 0; //inicializa o hash do padrao
        int t = 0; //inicializa o hash do texto
        int j; 
        int h = hash(m); // chama a funcao que calcula o hash
        
        for (int i=0; i< m; i++){
            p = (d*p + padrao.indexOf(i)) % q; // calcula o hash do padrao
            t = (d*t + texto.indexOf(i)) % q; // calcula do hash do texto
        }
        
        for (int s=0; s<= n-m ;s++){ // começa a procurar o padrao
            if (p == t){
                for ( j=0; j<m ;j++){
                    if(padrao.charAt(j) != texto.charAt(j+s)) //compara posicao por posição caso o hash entre p e t seja igual
                        break; // se a palavra for diferente quebra o laço
                }
                if (j == m) // se o tamanho do padrao for igual ao tamanho do laço o padrao foi encontrado.
                    System.out.println("Padrão encontrado na posição: "+s);
            }
            
            if (s < n-m){ // recalcula o hash
                t = ((d*(t - texto.indexOf(s) *h ) + texto.indexOf(s+m) ) % q);
            }
        }
             
        
    }
    
    static int hash (int m){
        double h = Math.pow(d,m-1);
        h = h%q;
        return (int) h;
    }

}

