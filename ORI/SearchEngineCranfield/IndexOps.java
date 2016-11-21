
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que trabalha com as operações sonre o índice.
 *
 * @author Cecília Assis
 */
public abstract class IndexOps {

////////////////////////OK
  /**
   * Criação do índice.
   *
   * @param map Índice que está sendo criado.
   * @param line Linha que está sendo processada.
   * @param seqNumber Número do documento que está sendo processado.
   * @return Índice.
   */
  public static TreeMap<Word, ArrayList<Document>> createMap(
          TreeMap<Word, ArrayList<Document>> map, String line, int seqNumber) {
    // Pattern p = Pattern.compile("[a-záéíóúçãõôê]{3,}");
    Pattern p = Pattern.compile("[a-záéíóúçãõôê]{1,}");
    Matcher m = p.matcher(line);

    while (m.find()) {

      String matcherWord = m.group();
      ArrayList<Document> documents = null;
      //para cada palavra
      for (Word otherWord : map.keySet()) {
        // se a palavra no grupo ja existe no mapa
        if (otherWord.getWord().equals(matcherWord)) {
          //pego seu conjunto de documentos
          documents = map.get(otherWord);
        }
      }
      //se possuir documentos
      if (documents != null) {
        Document other_doc = null;
        //para cada um do conjunto
        for (Document doc : documents) {
          //se o numero do documento lido for o mesmo do no laco
          if (doc.getSeqNumber() == seqNumber) {
            other_doc = doc;
          }
        }

        if (other_doc != null) {
                    //se eu tiver achado alguem que bate com o numero do doc lido
          //aumento a frequencia daquele palavra naquele doc
          other_doc.increaseFreq();
        } else {
          //se nao, faco um novo doc com o numero lido
          other_doc = new Document(seqNumber);
          //add aquele doc a lista de docs da palavra
          documents.add(other_doc);
        }
      } else { // se nao possuir conjunto de docs algum
        //faco um novo doc com o numero lido
        Document doc = new Document(seqNumber);
        // a lista recebe uma nova lista
        documents = new ArrayList<>();
        //add o novo doc
        documents.add(doc);
        //coloco a nova palavra no mapa
        map.put(new Word(matcherWord), documents);
      }

    }

    return map;

  }

////////////////////////OK
  /**
   * Copia a lista de documentos dos termos para cada termo do parâmentro mapa.
   *
   * @param map Mapa que contém o índice.
   * @return Conjunto de termos no mapa com suas listas de documentos
   * atualizadas.
   */
  public static Set<Word> copyLists(TreeMap<Word, ArrayList<Document>> map) {
    map.keySet().stream().forEach((otherWord) -> {
      //para cada palavra o valor do mapa eh repassado
      otherWord.setDocuments(map.get(otherWord));
    });

    return map.keySet();

  }

////////////////////////OK
  /**
   * Cálculo do TF de cada documento de cada termo do índice.
   *
   * @param words Conjunto de termos do índice.
   */
  public static void calculateTF(Set<Word> words) {
    words.stream().forEach((word) -> {
      word.setTf(Arquivo.docsLength);
    });

  }

////////////////////////OK
  /**
   * Cálculo do IDF de cada termo do índice.
   *
   * @param words Conjunto de termos do índice.
   */
  public static void calculateIDF(Set<Word> words) {
    words.stream().forEach((word) -> {
      word.setIdf(Arquivo.docsLength);
    });

  }

////////////////////////OK
  /**
   * Cálculo do TF-IDF de cada documento de cada termo do índice.
   *
   * @param words Conjunto de termos do índice.
   */
  public static void calculateTF_IDF(Set<Word> words) {
    words.stream().forEach((word) -> {
      word.setTf_idf(Arquivo.docsLength);
    });

  }

////////////////////////OK
  /**
   * Cálculo da NORMA de cada documento.
   *
   * @param words Conjunto de termos do índice.
   * @param docsLength Quantidade de documentos processados.
   * @return Mapa contendo a norma de cada documento.
   */
  public static HashMap<Integer, Double> calculateNorma(int docsLength, Set<Word> words) {
    //interger - numero de seq | double - valor do norma
    HashMap<Integer, Double> normas = new HashMap<>(docsLength);

    words.stream().forEach((word) -> {
      word.getTf_idf().stream().forEach((tfidf) -> {
        int seqNumber = tfidf.getSeqNumber();
        Double norma = normas.get(seqNumber);
        double value = tfidf.getValue();
                //se o numero de sequencia ja esta no mapa
        //necessario somente recalcular sua norma
        if (norma != null) {
          if (norma > 0.0) {
            normas.put(seqNumber, sqrt(pow(norma, 2) + pow(value, 2)));
          }
        } else { // se nao temos que comecar o calculo
          if (value > 0.0) {
            normas.put(seqNumber, sqrt(pow(value, 2)));
          }
        }
      });
    });

    return normas;

  }

  /* public static <K, V> void printMap(Map<K, V> map) {
   map.entrySet().stream().forEach((e) -> {
   System.out.println(e.getKey().toString());
   });
   }*/
}
