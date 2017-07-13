
/**
 * Classe do grau de similaridade.
 *
 * @author Cecília Assis
 */
public class SimilarityRate extends Weight implements Comparable<SimilarityRate> {

  public SimilarityRate(int seqNumber, double value) {
    super(seqNumber, value);
  }

  /**
   * @see VectorRankingOps#printRanking(java.util.ArrayList) Método para usar o
   * Collections.sort e ordenar de forma decrescente os graus.
   *
   * @param o Grau sendo comparado.
   * @return Resultado da comparação.
   */
  @Override
  public int compareTo(SimilarityRate o) {
    if (o.getValue() > this.getValue()) {
      return 1;
    } else if (o.getValue() == this.getValue()) {
      return 0;
    } else {
      return -1;
    }

  }

}
