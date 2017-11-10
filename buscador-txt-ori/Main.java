
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * Querido MAIN s2
 *
 * @author Cecília Assis
 */
public abstract class Main {

  /**
   * @param args the command line arguments
   *
   */
  public static void main(String[] args) {
    // para aceitar acentos
    Scanner input = new Scanner(System.in, "latin1");
    Arquivo arq = new Arquivo();
/////////////////////////////////////////////////// índice Ops
    System.out.println("Digite o nome do arquivo de coleção: ");

    Set<Word> words = IndexOps.copyLists(arq.readCollection("src\\"
            + input.nextLine() + ".txt"));
    IndexOps.calculateTF(words);
    IndexOps.calculateIDF(words);
    IndexOps.calculateTF_IDF(words);
    arq.writeNorma(IndexOps.calculateNorma(Arquivo.docsLength, words));
///////////////////////////////////////////////////// query Ops
    String indexPath = arq.writeIndex(words);
    while (true) {
      System.out.println("\nDigite sua consulta: ");
      String query = input.nextLine();
      ArrayList<Double> queryArray
              = QueryOps.buildQueryArray(query, indexPath, 1);

      RankingOps.makeRank(indexPath, queryArray, 1);
      ArrayList<SimilarityRate> rates = RankingOps.makeRank(indexPath,
              QueryOps.buildQueryArray(query, indexPath, 2), 2);
      //////////////////////////////////////////////// rochio
      ArrayList<Integer> relevants = new ArrayList<>();
      System.out.println("\nType the numbers of the relevant documents for"
              + " you | type -1 to stop: ");

      int number = input.nextInt();

      while (number != -1) {
        relevants.add(number);
        number = input.nextInt();
      }

      RankingOps.makeRank(indexPath,
              QueryOps.buildQueryArrayModified(relevants, indexPath,
                      queryArray, rates), 1);

      relevants.clear(); //in case the user wants another relevants
      input.nextLine();
    }

  }
}
