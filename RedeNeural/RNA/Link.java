package RNA;

/**
 *
 * @author cecilia
 */
public class Link {

  private double weight;
  private Neuron leftNeuron;
  private Neuron rightNeuron;

  public Link() {

  }

  public Link(double weight, Neuron linked) {
    this.weight = weight;
    this.leftNeuron = linked;
  }

  public Link(double weight, Neuron leftNeuron, Neuron rightNeuron) {
    this.weight = weight;
    this.leftNeuron = leftNeuron;
    this.rightNeuron = rightNeuron;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public Neuron getLeftNeuron() {
    return leftNeuron;
  }

  public void setLeftNeuron(Neuron leftNeuron) {
    this.leftNeuron = leftNeuron;
  }

  public Neuron getRightNeuron() {
    return rightNeuron;
  }

  public void setRightNeuron(Neuron rightNeuron) {
    this.rightNeuron = rightNeuron;
  }

}
