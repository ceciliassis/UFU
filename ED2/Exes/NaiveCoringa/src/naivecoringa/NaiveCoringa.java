/*
    N�o pode ocorrer no texto, mas pode no padr�o!
*/
package naiveCoringa;

import java.util.Scanner;

public class NaiveCoringa {

    public static void main(String[] args) {
        String texto, padrao;
        Scanner leitor = new Scanner (System.in);
        
        System.out.print("Digite a texto: ");
        texto = leitor.next();
        
        System.out.print("Digite o padr�o: ");
        padrao = leitor.next();
        
        naiveStringMatcherCoringa (texto,padrao); // porque nao pode ser chamada se nao usar o static?
                
    }
        
    
    
    public static void naiveStringMatcherCoringa (String texto, String padrao){ //praque tem qe colocar static aqui?
        int tamT , tamP;

        tamT= texto.length();
        tamP = padrao.length();

        for (int shift=0 ; shift< tamP - tamT-1 ; shift ++ ){
            int i =0;
            int j = shift;
            
            if (texto.charAt(i) == padrao.charAt(shift) ){
                
                System.out.println("Padr�o de deslocamento: " + shift);
                // N�o entendi essa parte! -> aonde eu deveria imprimir o padr�o de deslocamento???
                
                while(texto.charAt(i) == padrao.charAt(j)){
                    j++;
                    i++;
					
					
                    
                    if (i==tamT){
                        System.out.println("Sucesso!");
                        break;
                    }
                      
                }
            }
        }
    }

}
