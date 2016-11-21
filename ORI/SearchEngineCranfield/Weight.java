
/**
 * Superclasse dos Pesos
 *
 * @author Cecília Assis
 */
public class Weight {

  private int seqNumber;
  private double value;

  public Weight(int seqNumber, double value) {
    this.seqNumber = seqNumber;
    this.value = value;
  }

  public int getSeqNumber() {
    return seqNumber;
  }

  public void setSeqNumber(int seqNumber) {
    this.seqNumber = seqNumber;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
