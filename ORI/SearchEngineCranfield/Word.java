
import static java.lang.Math.log;
import java.util.ArrayList;

/**
 * Classe de termos.
 *
 * @author Cecília Assis
 */
public class Word implements Comparable<Word> {

  private final double TWO_BASE = log(2);
  private String word;
  private double idf;
  private ArrayList<TF_IDF> tf_idf;
  private ArrayList<TF> tf;
  private int sizeOfDocs;
  private ArrayList<Document> documents;

  public Word() {
  }

  public Word(String word) {
    this.word = word;
  }
  //////////////////////////OK

  public void setWord(String word) {
    this.word = word;
  }

  public String getWord() {
    return word;
  }

  ////////////////////////// OK
  public double getIdf() {
    return idf;
  }

  /**
   * Cálculo do peso IDF do termo.
   *
   * @param docsLength Quantidade de documentos processados.
   */
  public void setIdf(int docsLength) {
    double value = log((double) docsLength / this.documents.size());
    this.idf = value / TWO_BASE;
  }

  /**
   * @see Arquivo#readIndex(java.lang.String) Leitura do índice | Adiciona o
   * peso IDF do termo.
   * @param idf Peso IDF processado.
   */
  public void addIdf(double idf) {
    this.idf = idf;
  }
  ///////////////////////// OK

  public ArrayList<TF_IDF> getTf_idf() {
    return tf_idf;
  }

  /**
   * Cálculo do peso TF-IDF de cada documento em que o termo está presente.
   *
   * @param docsLength Quantidade de documentos processados.
   */
  public void setTf_idf(int docsLength) {
    this.tf_idf = new ArrayList<>(docsLength);
    this.tf.stream().forEach((tf1) -> {
      this.tf_idf.add(new TF_IDF(tf1.getSeqNumber(),
              tf1.getValue() * this.idf));
    });
  }

  /**
   * Inicializa a lista de pesos tf-idfs.
   */
  public void initTf_idf() {
    this.tf_idf = new ArrayList<>(Arquivo.docsLength);
  }

  /**
   * @see Arquivo#readIndex(java.lang.String) Leitura do índice | Adiciona cada
   * peso TF-IDF do termo.
   * @param weight Peso TF-IDF processado.
   * @param seqNumber Documento a qual pertence o peso processado.
   */
  public void addTF_IDF(int seqNumber, double weight) {
    this.tf_idf.add(new TF_IDF(seqNumber, weight));
  }

  ///////////////////////// OK
  public ArrayList<TF> getTf() {
    return tf;
  }

  /**
   * Cálculo do peso TF de cada documento em que o termo está presente.
   *
   * @param docsLength Quantidade de documentos processados.
   */
  public void setTf(int docsLength) {
    this.tf = new ArrayList<>(docsLength);

    for (int i = 1; i <= docsLength; i++) {
      boolean flag = false;
      for (Document doc : this.documents) {
        if (doc.getSeqNumber() == i) {
          this.tf.add(new TF(doc.getSeqNumber(),
                  1 + (log(doc.getFreq()) / TWO_BASE)));
          flag = true;
        }

      }
      if (!flag) {
        this.tf.add(new TF(i, 0.0));
      }
    }

  }

  /////////////////////////OK
  /**
   * @see IndexOps#copyLists(java.util.TreeMap) Atualiza a lista de documentos
   * em que o termo aparece.
   * @param documents Conjuntos de documentos que o termo faz parte.
   */
  public void setDocuments(ArrayList<Document> documents) {
    this.documents = documents;
  }

  /**
   * @return número de documentos que o termo aparece
   */
  public int getDocuments() {
    return this.documents.size();
  }
//

  public void addSizeOfDocs(int sizeOfDocs) {
    this.sizeOfDocs = sizeOfDocs;

  }

  public int getSizeOfDocs() {
    return sizeOfDocs;
  }

  ////////////////////////OK
  @Override
  public int compareTo(Word o) {
    return word.compareTo(o.word);
  }

}
