import java.util.*;
import java.io.*;
 
public class Huffman {  
 
    public static PriorityQueue<Node> q;
    public static HashMap<Character, String> charToCode;
    public static HashMap<String, Character> codeToChar;
 
    public static void main(String[] args) throws FileNotFoundException {
        
        String text = new Scanner(new File("input.txt")).useDelimiter("\\A").next();
 
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
 
        for(int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
 
            if(dict.containsKey(a))
                dict.put(a, dict.get(a)+1);
            else
                dict.put(a, 1);
        }
 
        q = new PriorityQueue<Node>(100, new FrequencyComparator());
        int n = 0;
 
        for(Character c : dict.keySet()) {
            q.add(new Node(c, dict.get(c)));
            n++;
        }
 
      
        Node root = huffman(n);
 
        
        buildTable(root);
 
        String compressed = compress(text);
        System.out.println("The compressed used a total of " + compressed.length() + " bits");
        System.out.println(compressed);
 
        String decompressed = decompress(compressed);
        System.out.println("The original text used a total of " + decompressed.length() + " characters");
        System.out.println(decompressed);
 
        
    }
 
   
    public static Node huffman(int n) {
        Node x, y;
 
        for(int i = 1; i <= n-1; i++) {
            Node z = new Node();
            z.left = x = q.poll();
            z.right = y = q.poll();
            z.freq = x.freq + y.freq;
            q.add(z);
        }
 
        return q.poll();
    }
 
   
    public static void buildTable(Node root) {
        charToCode = new HashMap<Character, String>();
        codeToChar = new HashMap<String, Character>();
 
        postorder(root, new String());
    }
 
    
    public static void postorder(Node n, String s) {
        if(n == null)
            return;
 
        postorder(n.left, s+"0");
        postorder(n.right, s+"1");
 
      
        if(n.alpha != '&#092;&#048;') {
            System.out.println("\'" + n.alpha + "\' -> " + s);
            charToCode.put(n.alpha, s);
            codeToChar.put(s, n.alpha);
        }
    }
 
    
    public static String compress(String s) {
        String c = new String();
 
        for(int i = 0; i < s.length(); i++)
            c = c + charToCode.get(s.charAt(i));
 
        return c;
    }
 
   
    public static String decompress(String s) {
        String temp = new String();
        String result = new String();
 
        for(int i = 0; i < s.length(); i++) {
            temp = temp + s.charAt(i);
 
            if(codeToChar.containsKey(temp)) {
                result = result + codeToChar.get(temp);
                temp = new String();
            }
        }
 
        return result;
    }
 
    public static void saveToFile(String l) throws FileNotFoundException {
        PrintWriter oFile = new PrintWriter("output.txt");
 
        for(String s : codeToChar.keySet())
            oFile.println(s + "->" + codeToChar.get(s));
 
        oFile.println(l);
 
        oFile.close();
    }
 
}
 
class Node {
 
    public char alpha;
    public int freq;
    public Node left, right;
 
    public Node(char a, int f) {
        alpha = a;
        freq = f;
    }
 
    public Node() {
 
    }
 
    public String toString() {
        return alpha + " " + freq;
    }
 
}
 
class FrequencyComparator implements Comparator<Node> {
 
    public int compare(Node a, Node b) {
        int freqA = a.freq;
        int freqB = b.freq;
 
        return freqA-freqB;
    }
 
}