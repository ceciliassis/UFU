
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que trabalha com as operações sobre a consulta.
 *
 * @author Cecília Assis
 */
public abstract class QueryOps {

////////////////////////OK
  /**
   * Constroi o vetor consulta com seus devidos pesos.
   *
   * @param query Consulta fornecida pelo usuário.
   * @param index Caminho do arquivo de índice.
   * @param model Modelo que está sendo usado.
   * @return Vetor consulta com seus respectivos pesos TF-IDFs.
   */
  public static ArrayList<Double> buildQueryArray(String query, String index,
          int model) {
    query = query.toLowerCase();

    //procura por palavras na consulta
    //ja que não acheie nenhum regex que desse para dar um slip correto
    Pattern p = Pattern.compile("[a-záéíóúçãõôê]{1,}");
    Matcher m = p.matcher(query);

    //string - termo | integer - frequência do termo na consulta
    HashMap<String, Integer> queryMap = new HashMap<>();

    while (m.find()) {
      String word = m.group();
      //procura o termo no mapa 
      Integer cont = queryMap.get(word);
      //se achar , aumenta a frequencia desse
      if (cont != null) {
        queryMap.put(word, cont + 1);
      } else {
        //novo termo
        queryMap.put(word, 1);
      }

    }

    if (model == 1) {
      return calculateTF_IDF(queryMap, index);
    } else {
      return calculatePresence(queryMap, index);
    }
  }

////////////////////////OK
  /**
   * Cálculo do TF-IDF de cada termo da consulta.
   *
   * @param queryMap Mapa de termos da consulta e suas respectivas frequências.
   * @param index Caminho do arquivo de índice.
   * @return Vetor consulta com seus respectivos pesos TF-IDFs.
   */
  private static ArrayList<Double>
          calculateTF_IDF(HashMap<String, Integer> queryMap, String index) {

    final double TWO_BASE = log(2);
    Arquivo arq = new Arquivo();

    //possui todas as palavras existentes no índice.
    ArrayList<Word> words = arq.readIndex(index);
    ArrayList<String> wordsString = new ArrayList<>(words.size());

    //passa cada string do words para um array de string
    words.stream().forEach((word) -> {
      wordsString.add(word.getWord());
    });

    ArrayList<Double> queryArray = new ArrayList<>(words.size());

    //caminhando no array de string de termos do índice
    for (int i = 0; i < wordsString.size(); i++) {
      String word = wordsString.get(i);
      //comparando com a minha consulta
      if (queryMap.containsKey(word)) {
        //se achei , pego a word que está naquele local
        Word otherWord = words.get(i);
        //calculo seu tf para a consulta
        //pega a frequencia da word
        double tf = 1 + (log(queryMap.get(word)) / TWO_BASE);
        double tfIdf = tf * otherWord.getIdf();

        queryArray.add(tfIdf);

      } else {
        queryArray.add(0.0);
      }

    }

    return queryArray;

  }

  ////////////////////////OK
  /**
   * Cálculo da norma do vetor consulta.
   *
   * @param queryArray Vetor consulta com seus respectivos pesos TF-IDFs.
   * @return Valor da norma do vetor consulta.
   */
  public static double calculateNorma(ArrayList<Double> queryArray) {
    double norma = 0.0;
    for (Double value : queryArray) {
      if (value > 0.0) {
        norma = sqrt(pow(norma, 2) + pow(value, 2));
      }
    }
    return norma;

  }

  //////////////////////
  /**
   * Making a new querry array, here for the Probabilistic Model
   *
   * @param queryMap Made here {@link QueryOps#buildQueryArray(java.lang.String,
   * java.lang.String, int) }
   * @param index the path of the index file that will be read
   * @return The new query array of the Probabilistic Model
   *
   */
  private static ArrayList<Double> calculatePresence(
          HashMap<String, Integer> queryMap, String index) {
    Arquivo arq = new Arquivo();

    ArrayList<Word> words = arq.readIndex(index);
    ArrayList<String> wordsString = new ArrayList<>(words.size());

    words.stream().forEach((word) -> {
      wordsString.add(word.getWord());
    });

    ArrayList<Double> queryArray = new ArrayList<>();

    wordsString.stream().forEach((word) -> {
      if (queryMap.containsKey(word)) {
        queryArray.add(1.0);
      } else {
        queryArray.add(0.0);
      }
    });

    return queryArray;
  }

  public static ArrayList<Double> buildQueryArrayModified(
          ArrayList<Integer> relevants, String index,
          ArrayList<Double> queryArray, ArrayList<SimilarityRate> rates) {

    Arquivo arq = new Arquivo();
    ArrayList<Word> words = arq.readIndex(index);

    ArrayList<Integer> notRelsRecovered = new ArrayList<>();
    ArrayList<Integer> relsRecovered = new ArrayList<>();

    //definindo relevantes recuperados e nao recuperados
    for (SimilarityRate rate : rates) {
      if (rate.getValue() > 0) {
        int number = rate.getSeqNumber();
        if (!relevants.contains(number)) { //relevante recuperado
          notRelsRecovered.add(number);
        } else {
          relsRecovered.add(number);
        }
      }
    }

    double beta = 0.5 / relsRecovered.size();
    double gama = 0.25 / notRelsRecovered.size();

    for (int i = 0; i < words.size(); i++) {
      double value = queryArray.get(i);
      //dado que o vetor consulta nao muda, é preciso mexer só nos outros valores
      //alta == 1
      Word word = words.get(i);
      for (TF_IDF weigth : word.getTf_idf()) {
        int number = weigth.getSeqNumber();
        //if it's one of the user's relevant, plus
        if (relsRecovered.contains(number)) {
          value += (beta * weigth.getValue());
        }
        //se for um dos nao relevants recuperados
        if (notRelsRecovered.contains(number)) {//otherwise minus
          value -= (gama * weigth.getValue());
        }
      }
      queryArray.set(i, value); // substituindo o valor na posicao correta
    }

    for (int i = 0; i < queryArray.size(); i++) {
      if (queryArray.get(i) < 0.0) {
        queryArray.set(i, 0.0);
      }
    }
    return queryArray;
  }

}
