
/**
 * Classe do peso TF-IDF.
 *
 * @author Cecília Assis
 */
public class TF_IDF extends Weight {

  /**
   * @param seqNumber Documento sendo processado.
   * @param value Valor do peso.
   */
  public TF_IDF(int seqNumber, double value) {
    super(seqNumber, value);
  }

}
