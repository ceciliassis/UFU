
/**
 * Classe de documentos
 *
 * @author Cecília Assis
 */
public class Document {

  private final int seqNumber;
  private int freq;

  /**
   * @param seqNumber Número do documento sendo processado.
   */
  public Document(int seqNumber) {
    this.seqNumber = seqNumber;
    this.freq = 1;
  }
/////////////////OK

  public int getSeqNumber() {
    return seqNumber;
  }
/////////////////OK

  /**
   * @return Frequência do termo no documento.
   */
  public int getFreq() {
    return freq;
  }

  /**
   * Aumenta a frequência do termo no documento.
   */
  public void increaseFreq() {
    this.freq++;
  }

}
