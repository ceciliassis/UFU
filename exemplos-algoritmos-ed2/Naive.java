
package naive;

import java.util.Scanner;


public class Naivve {

    public static void main(String[] args) {
        String texto, padrao;
        Scanner leitor = new Scanner (System.in);

        System.out.print("Digite a texto: ");
        texto = leitor.next();

        System.out.print("Digite o padrão: ");
        padrao = leitor.next();

        naiveStringMatcher (texto,padrao);

    }


    public static void naiveStringMatcher (String texto, String padrao){
        int tamT , tamP;

        tamT= texto.length();
        tamP = padrao.length();

        for (int shift=0 ; shift< tamP - tamT-1 ; shift ++ ){
            int i =0;
            int j = shift;

            if (texto.charAt(i) == padrao.charAt(shift) ){

                System.out.println("Padrão ocorre com deslocamento: " + shift);


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
