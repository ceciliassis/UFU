package RNA;

import java.util.ArrayList;

/**
 *
 * @author cecilia
 */
public class Neuron {

  private double value = 0; //input and output value
  private double lastError;
  private double tendency;
  private ArrayList<Link> links;

  public Neuron() {
  }

  public Neuron(boolean notInput) {
    lastError = 0;
    links = new ArrayList<>();
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public void weightSum(double sum) {
    value += sum;
  }

  public void setTendency(double tendency) {
    this.tendency = tendency;
  }

  public double getTendency() {
    return tendency;
  }

  public ArrayList<Link> getLinks() {
    return links;
  }

  public double getLastError() {
    return lastError;
  }

  public void setLastError(double lastError) {
    this.lastError = lastError;
  }

  static double getRandom() {
    return 1 * (Math.random() * 2 - 1); // [-1;1[
  }

  public double sigmoid(double sum) {
    double f = 1;
    double e = Math.exp(-sum);
    f /= (1 + e);
    return f;
  }

  public void normalizeValue(double value) {
    value /= 10;
    this.value = value;
  }

}
