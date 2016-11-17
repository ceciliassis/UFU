package RNA;

import java.util.ArrayList;

/**
 *
 * @author cecilia
 */
public class ANN {

  static int BIN1, BIN2;
  final double MARGIN = 0.5f;
  ArrayList<Neuron> input = new ArrayList<>();
  ArrayList<Neuron> hidden = new ArrayList<>();
  ArrayList<Neuron> output = new ArrayList<>();

  double learningRate = 0.3d; //salto pequeno

  public void run() {
    int acuracy = 0;
    boolean acu = false;
    int epoch = 50000;
    int j = 0;
    boolean erro = true;

    DataReader dr = new DataReader();
    ArrayList<String> data = dr.readFile(DataReader.TREINOFILE);

    initLayers(4, 4, 2);
//    initLayers(4, 2, 2);
//    initLayers(4, 8, 2);

    //a cada iteração é bom que randomize dados de treinamento
    while (erro && (j < epoch)) {
      erro = false;
      for (String line : data) {
        String[] parts = line.split(",");

        for (int i = 0; i < input.size(); i++) {
          Neuron o = input.get(i);
          o.normalizeValue(Double.parseDouble(parts[i])); 
        }

        if (null != parts[parts.length - 1]) {
          switch (parts[parts.length - 1]) {
            case "Iris-setosa": //0
              BIN1 = 0;
              BIN2 = 0;
              break;
            case "Iris-versicolor": //1
              BIN1 = 1;
              BIN2 = 0;
              break;
            case "Iris-virginica": //2
              BIN1 = 0;
              BIN2 = 1;
              break;
          }
        }
        going();
        backPropagation();
        erro = validation();
      }
      j++;
    }

    //DADO que a rede esta treinada
    data = dr.readFile(DataReader.TESTEFILE);
    int cont = 1;
    for (String line : data) {
      String[] parts = line.split(",");
      for (int i = 0; i < input.size(); i++) {
        Neuron o = input.get(i);
        o.normalizeValue(Double.parseDouble(parts[i]));
      }

      if (null != parts[parts.length - 1]) {
        switch (parts[parts.length - 1]) {
          case "Iris-setosa": //0
            BIN1 = 0;
            BIN2 = 0;
            break;
          case "Iris-versicolor": //1
            BIN1 = 1;
            BIN2 = 0;
            break;
          case "Iris-virginica": //2
            BIN1 = 0;
            BIN2 = 1;
            break;
        }
      }

      going();

      System.out.println("Dado: " + line + " Linha:  " + cont);
      int conntt = 0;
      for (int i = 0; i < output.size(); i++) {
        int haha;
        if (output.get(i).getValue() >= MARGIN) {
          haha = 1;
        } else {
          haha = 0;
        }
        
        if (i == 0) {//primeiro neuron
          System.out.print("Expected " + BIN1 + " ---- ");
          System.out.print("Result: " + haha + "\n");
          if (BIN1 == haha) {
            conntt++;
          }
        } else {
          System.out.print("Expected " + BIN2 + " ---- ");
          System.out.print("Result: " + haha + "\n");
          if (BIN2 == haha) {
            conntt++;
          }
        }
      }
      if (conntt == 2) {
        acuracy++;
      }
      cont++;
    }
    System.out.println("\nAcuracy = " + acuracy);
  }

  private void initLayers(int inputSize, int hiddenSize, int outputSize) {
    for (int i = 0; i < inputSize; i++) {
      input.add(new Neuron());
    }
    for (int i = 0; i < hiddenSize; i++) {
      hidden.add(new Neuron(true));
    }

    for (int i = 0; i < outputSize; i++) {
      output.add(new Neuron(true));
      output.get(i).setTendency(Neuron.getRandom());
    }

    for (Neuron n : input) {
      for (Neuron hd : hidden) {
        ArrayList<Link> links = hd.getLinks();
        links.add(new Link(Neuron.getRandom(), n));
      }
    }

    for (Neuron hd : hidden) {
      for (Neuron ou : output) {
        Link link = new Link(Neuron.getRandom(), hd, ou);
        ou.getLinks().add(link);
        hd.getLinks().add(link);
      }
    }

  }

  /**
   * Ida do algoritmo
   */
  private void going() {
    for (Neuron hd : hidden) {
      double sum = 0;
      ArrayList<Link> links = hd.getLinks();
      for (int i = 0; i < input.size(); i++) {
        Link link = links.get(i);
        Neuron o = link.getLeftNeuron();
        sum = sum + (link.getWeight() * o.getValue());
      }
      hd.setValue(hd.sigmoid(sum));
    }

    for (Neuron ou : output) {
      double sum = 0;
      ArrayList<Link> links = ou.getLinks();
      for (Link link : links) {
        Neuron o = link.getLeftNeuron();
        sum = sum + (link.getWeight() * o.getValue());
      }
      sum = sum + ou.getTendency();
      ou.setValue(ou.sigmoid(sum));
    }
  }

  private boolean validation() {
    boolean error = false;
    for (int i = 0; i < output.size(); i++) {
      int haha;
      if ( output.get(i).getValue() >= MARGIN) {
        haha = 1;
      } else {
        haha = 0;
      }

      if (i == 0) {//primeiro neuron
        if (BIN1 != haha);
        error = true;
      } else {
        if (BIN2 != haha);
        error = true;
      }
    }
    return error;
  }

  private void backPropagation() {
    ///////////////////////////////////////// output backpropagation
    for (int i = 0; i < output.size(); i++) {
      double error;
      Neuron ou = output.get(i);
      double value = ou.getValue();
      if (i == 0) {//primeiro neuron
        error = (1 - value * value) * (BIN1 - value);
        ou.setLastError(error);
      } else {
        error = (1 - value * value) * (BIN2 - value);
        ou.setLastError(error);
      }
    }

    //AJUSTE
    ajusteOutput();

    ///////////////////////////////////////// hidden backpropagation
    for (Neuron hd : hidden) {
      double finalError = 0;
      ArrayList<Link> links = hd.getLinks();
      for (int i = input.size(); i < links.size(); i++) {
        Link link = links.get(i);
        Neuron o = link.getRightNeuron();
        finalError += o.getLastError() * link.getWeight();
      }
      double hdValue = hd.getValue(); //valor de output
      finalError = hdValue * (1 - hdValue) * finalError;
      hd.setLastError(finalError);
    }

    ajusteHidden();

  }

  /**
   * Ajuste pesos e tendencia da camada de saida no backpropagation, modo padrão
   */
  private void ajusteOutput() {
    for (Neuron ou : output) {
      double lastError = ou.getLastError();
      for (Link link : ou.getLinks()) {
        double newWeight = link.getWeight() + (learningRate
                * lastError * link.getLeftNeuron().getValue());
        link.setWeight(newWeight);
      }
      ///////////////////TENDENCIA
      double newTendency = ou.getTendency() + (learningRate * lastError);
      ou.setTendency(newTendency);
    }

  }

  /**
   * Ajuste pesos e tendencia da camada escondida no backpropagation, modo
   * padrão
   */
  private void ajusteHidden() {
    for (Neuron hd : hidden) {
      double lastError = hd.getLastError();
      ArrayList<Link> links = hd.getLinks();
      for (int i = 0; i < input.size(); i++) {
        Link link = links.get(i);
        double newWeight = link.getWeight() + (learningRate
                * lastError * link.getLeftNeuron().getValue());
        link.setWeight(newWeight);
      }
    }

  }

}
