package RNA;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author cecilia
 */
public class DataReader {

  static final File INPUTFILE = new File("src/iris.txt");
  static final File TESTEFILE = new File("src/teste.txt");
  static final File TREINOFILE = new File("src/treino.txt");

  public boolean randomFile() {

    try {
      int lines = countLines(INPUTFILE);
      int treinoSize = (lines * 2) / 3;
      chooseLines(readFile(INPUTFILE), treinoSize);
      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return false;
  }

  public ArrayList<String> readFile(File f) {

    ArrayList<String> lines = new ArrayList<>();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(f));
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }

      lines.trimToSize();

      reader.close();
      return lines;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public int countLines(File f) throws IOException {
    InputStream is = new BufferedInputStream(new FileInputStream(f));
    try {
      byte[] c = new byte[1024];
      int count = 0;
      int readChars = 0;
      boolean empty = true;

      while ((readChars = is.read(c)) != -1) {
        empty = false;
        for (int i = 0; i < readChars; ++i) {
          if (c[i] == '\n') {
            ++count;
          }
        }
      }
      return (count == 0 && !empty) ? 1 : count + 1;
    } finally {
      is.close();
    }
  }

  private void chooseLines(ArrayList<String> lines, int treinoSize) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(TREINOFILE));
      BufferedWriter writer2 = new BufferedWriter(new FileWriter(TESTEFILE));

      int cont = 0;
      while (!lines.isEmpty()) {
        int lineNumber = (int) (Math.random() * lines.size());
        String line = lines.remove(lineNumber);

        if (cont < treinoSize) {
          writer.write(line + System.getProperty("line.separator"));
          cont++;
          continue;
        }
        writer2.write(line + System.getProperty("line.separator"));

      }

      writer.close();
      writer2.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
