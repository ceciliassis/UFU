
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * Classe de cuida de toda escrita e leitura de arquivo.
 *
 * @author Cecília Assis
 */
public final class Arquivo {

  public static int docsLength = 0;

////////////////////////OK
  /**
   * Lê o arquivo que contêm os documentos a serem processados.
   *
   * @param path Caminho do arquivo que contêm os documentos a serem
   * processados.
   * @return Índice.
   */
  @SuppressWarnings("ConvertToTryWithResources")
  public TreeMap<Word, ArrayList<Document>> readCollection(String path) {
    try {
      FileReader fr = new FileReader(path);
      BufferedReader r = new BufferedReader(fr);

      String line;
      TreeMap<Word, ArrayList<Document>> map = new TreeMap<>();
      //enquanto houver linhas
      while ((line = r.readLine()) != null) {
//CRANFIELD
//tag que marca o começo de um novo documento
//        if (line.equals(".W")) {
//          docsLength++;
//          String next = r.readLine();
//          while (!next.contains(".I")) {
//            next = next.toLowerCase();
//            map = IndexOps.createMap(map, next, docsLength);
//            next = r.readLine();
//            if (next == null) {
//              break;
//            }
//
//          }
//        }
//TOBE
        line = line.toLowerCase();
        if (line.equals("<>")) {
          docsLength++;
        } else {
          map = IndexOps.createMap(map, line, docsLength);
        }
      }
      r.close();
      fr.close();

      return map;

    } catch (FileNotFoundException o) {
      System.err.println("Arquivo não encontrado!");
      System.exit(1);
    } catch (IOException o) {
      System.err.println("Erro ao ler arquivo!");
      System.exit(1);
    }
    return null;

  }

////////////////////////OK
  /**
   * Lê o arquivo que contém o índice a ser processado.
   *
   * @param path Caminho do arquivo que contém o índice a ser processado.
   * @return Lista contendo os termos avaliados no índice.
   *
   */
  @SuppressWarnings("ConvertToTryWithResources")
  public ArrayList<Word> readIndex(String path) {
    try {
      FileReader fr = new FileReader(path);
      BufferedReader r = new BufferedReader(fr);
      String line;
      ArrayList<Word> words = new ArrayList<>();

      //enquanto houverem linhas
      while ((line = r.readLine()) != null) {
        //separa a linha pelo separador setado
        String[] parts = line.split(";");
        //primeira parte é a palavra
        Word word = new Word(parts[0]);
        //segunda parte -> N
        word.addSizeOfDocs(Integer.parseInt(parts[1]));
        //terceira parte é o IDF para auxiliar no cálculo do vetor query
        word.addIdf(Double.parseDouble(parts[2]));
        //inicializa a lista tf-idf
        word.initTf_idf();
        //pega cada tf-idf ja calculado esse é adicionado
        for (int i = 3; i < parts.length; i++) {
          word.addTF_IDF(i - 2, Double.parseDouble(parts[i]));
        }

        //diminui o array para o numero de objs nele existentes
        word.getTf_idf().trimToSize();
        words.add(word);
      }

      //diminui o array para o numero de objs nele existentes
      words.trimToSize();

      r.close();
      fr.close();

      return words;

    } catch (FileNotFoundException o) {
      System.err.println("Arquivo não encontrado!");
      System.exit(1);
    } catch (IOException o) {
      System.err.println("Erro ao ler arquivo!");
      System.exit(1);
    }

    return null;
  }

////////////////////////OK
  /**
   * Escreve um arquivo txt com os termos indexados e seus pesos TF-IDFs.
   *
   * @param words Conjunto de termos indexados.
   * @return Caminho do arquivo de índice.
   */
  @SuppressWarnings("ConvertToTryWithResources")
  public String writeIndex(Set<Word> words) {
    System.out.println("Digite o nome do índice: ");
    Scanner input = new Scanner(System.in);
    String index = input.nextLine();

    try {
      FileWriter fw = new FileWriter("src/" + index + ".txt");
      BufferedWriter b = new BufferedWriter(fw);

      for (Word word : words) {
        //escreve o termo
        b.write(word.getWord() + ";");
        //escreve o numero de docs que o termo aparece
        b.write(word.getDocuments() + ";");
        //escreve o idf
        b.write(word.getIdf() + ";");
        for (TF_IDF d : word.getTf_idf()) {
          if (d.getSeqNumber() == Arquivo.docsLength) {
            //se tiver chegado no último termo não precisa de
            //separador
            b.write("" + d.getValue());
          } else {
            //de resto precisa
            b.write(d.getValue() + ";");
          }
        }
        b.newLine();

      }

      b.close();
      fw.close();

      System.out.printf("\nIndexador nomeado \"%s.txt\" "
              + "criado com sucesso!\n", index);

      //salva o caminho bonitinho
      index = "src/" + index + ".txt";
      return index;

    } catch (Exception ex) {
      System.err.println("Erro ao criar indexador!");
      System.exit(1);
    }

    return null;

  }

////////////////////////OK
  /**
   * Lê o arquivo que possui a norma de cada documento processado.
   *
   * @return Lista contendo todas as normas processadas.
   */
  public ArrayList<Double> readNorma() {
    ArrayList<Double> normas = new ArrayList<>(Arquivo.docsLength);
    try {
      FileReader fr = new FileReader("src/normas.txt");
      BufferedReader r = new BufferedReader(fr);
      String line;
      while ((line = r.readLine()) != null) {
        String parts[] = line.split(";");
        normas.add(Double.parseDouble(parts[1]));
      }

    } catch (IOException | NumberFormatException ex) {
      System.err.println("Erro ao ler arquivo das normas.");
    }

    return normas;

  }

////////////////////////OK
  /**
   * Escreve um arquivo txt com a norma de cada documento processado.
   *
   * @param normas Mapa contendo a norma de cada documento processado.
   */
  @SuppressWarnings("ConvertToTryWithResources")
  public void writeNorma(HashMap<Integer, Double> normas) {
    try {
      FileWriter fw = new FileWriter("src/normas.txt");
      BufferedWriter b = new BufferedWriter(fw);

      normas.entrySet().stream().forEach((e) -> {
        try {
          b.write(e.getKey() + ";"
                  + e.getValue() + "\n");
        } catch (IOException ex) {
          System.err.println("Erro ao criar arquivo das normas");
        }
      });

      b.close();
      fw.close();

    } catch (Exception ex) {
      System.err.println("Erro ao criar arquivo das normas");
    }

  }
}
