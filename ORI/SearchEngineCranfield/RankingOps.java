
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que trabalha com as operações sobre o grau de similaridade.
 *
 * @author Cecília Assis
 */
public abstract class RankingOps {

  //////////////////////////OK
  /**
   * Cálculo da similaridade entre vetores.
   *
   * @param index Caminho do arquivo de índice.
   * @param queryArray Vetor consulta com seus respectivos pesos tf-idfs.
   * @param model Número marcando qual modelo será usado.
   * @return
   */
  public static ArrayList<SimilarityRate> makeRank(String index,
          ArrayList<Double> queryArray, int model) {

    Arquivo arq = new Arquivo();
    //lê o index
    ArrayList<Word> words = arq.readIndex(index);

    if (model == 1) {
      //lê o arquivo das normas
      ArrayList<Double> docsNormas = arq.readNorma();
      //calcula a norma do vetor consulta
      double queryNorma = QueryOps.calculateNorma(queryArray);
      return printRanking(calculateRatesVector(words, docsNormas, queryArray,
              queryNorma), model);
    } else {
      return printRanking(calculateRatesProb(words, queryArray), model);
    }
  }

  //////////////////////////OK
  /**
   * Cálculo do grau de similaridade pra o modelo vetorial.
   *
   * @param words Conjunto de termos do índice.
   * @param docsNormas Conjunto das normas de cada documento.
   * @param queryArray Vetor consulta pelos pesos tf-idf.
   * @param queryNorma Tamanho da norma da consulta.
   */
  private static ArrayList<SimilarityRate>
          calculateRatesVector(ArrayList<Word> words,
                  ArrayList<Double> docsNormas, ArrayList<Double> queryArray,
                  double queryNorma) {

    ArrayList<SimilarityRate> rates = new ArrayList<>(Arquivo.docsLength);

    //para cada documento
    for (int i = 0; i < Arquivo.docsLength; i++) {
      double internalProduct = 0.0;

      //para cada palavra no vetor consulta
      for (int j = 0; j < queryArray.size(); j++) {
        double queryTfIdf = queryArray.get(j);
        // se esse peso for mais que 0 | evita cálculo desnecessário
        if (queryTfIdf > 0.0) {
          //pego o termo do índice que bate com minha consulta
          Word word = words.get(j);
          //faço o produto interno entre tfidf do termo indexado
          // e termo da consulta
          internalProduct += (queryTfIdf
                  * word.getTf_idf().get(i).getValue());
        }
      }
      //multiplicacao entre norma da consulta e norma do documento
      double denominator = queryNorma * docsNormas.get(i);
      //divisao normal
      double value = internalProduct / denominator;
      //grau na lista, pelo documento
      SimilarityRate rate = new SimilarityRate(i + 1, value);
      rates.add(rate);
    }
    rates.trimToSize();
    return rates;

  }

  //////////////////////////OK
  /**
   * Cálculo do grau de similaridade para o modelo probabilístico.
   *
   * @param words Conjunto de termos do índice.
   * @param queryArray Vetor consulta pelos pesos tf-idf.
   */
  private static ArrayList<SimilarityRate>
          calculateRatesProb(ArrayList<Word> words,
                  ArrayList<Double> queryArray) {
    ArrayList<SimilarityRate> rates = new ArrayList<>(Arquivo.docsLength);

    boolean biggerThanN = false;

    for (Word word : words) {
      int n = Arquivo.docsLength / 2;
      if (word.getSizeOfDocs() > n) {
        biggerThanN = true;
        break;
      }
    }

    double numerator = Arquivo.docsLength + 0.5;

    for (int i = 0; i < Arquivo.docsLength; i++) {
      double value = 0;
      for (int j = 0; j < queryArray.size(); j++) {
        Word word = words.get(j);
        if ((queryArray.get(j) > 0) //tem no query array
                && (word.getTf_idf().get(i).getValue() > 0.0)) { //tem no documento

          double ni = word.getSizeOfDocs();
          double denominator = ni + 0.5;

          if (biggerThanN) {
            value += (Math.log(numerator) / Math.log(2));
          } else {
            value += (Math.log((numerator - ni) / denominator) / Math.log(2));
          }
        }
      }
      SimilarityRate rate = new SimilarityRate(i + 1, value);
      rates.add(rate);
    }

    rates.trimToSize();
    return rates;
  }

  //////////////////////////OK
  /**
   * Imprime o ranking gerado.
   *
   * @param rates Lista com os graus de similaridade processados.s
   */
  private static ArrayList<SimilarityRate> printRanking(ArrayList<SimilarityRate> rates,
          int model) {
    if (model == 1) {
      System.out.println("\n\t********* Ranking vetorial *********");
    } else {
      System.out.println("\n\t********* Ranking probabilístico *****"
              + "****");
    }

    //ordem decrescente
    Collections.sort(rates);

    for (SimilarityRate rate : rates) {
      if (rate.getValue() > 0) {
        System.out.printf("\nDocumento: %d | Grau de similaridade: %f",
                rate.getSeqNumber(), rate.getValue());
      }
    }

    System.out.println();

    return rates;
  }

}
