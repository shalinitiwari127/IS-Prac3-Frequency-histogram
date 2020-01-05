
package ispracs;
import java.io.*;
import java.util.*;

public class Histogram {

    static int[] freq = new int[26];

    public static void main(String[] args) throws IOException {
      
            FileReader fr1 = new FileReader("C:\\Users\\vision\\Documents\\NetBeansProjects\\ISPRACS\\src\\input.txt");
            BufferedReader br = new BufferedReader(fr1);
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Key : ");
            int key = sc.nextInt();
            String text = br.readLine(), ciphertext = "", plain = text;
            text = text.toLowerCase();
            //CEASAR CIPHER ENCRYPTION
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z') {
                    int index = ((int) text.charAt(i) + key);
                    if (index > 'z') {
                        index = index - 26;
                    }
                    ciphertext += (char) (index);
                } else {
                    ciphertext += text.charAt(i);
                }
            }
            System.out.println("\nPLAIN TEXT : \t" + plain + "\nCIPHER TEXT : \t" + ciphertext + "\n");
            //HISTOGERAM GENERATION
            for (int i = 0; i < ciphertext.length(); i++) {
                if (ciphertext.charAt(i) >= 'a' && ciphertext.charAt(i) <= 'z') {
                    freq[(int) (ciphertext.charAt(i) - 'a')]++;
                }
            }
            //PRINT HISTOGRAM
            for (int i = 0; i < 26; i++) {
                char x = (char) ('a' + i);
                System.out.print(x + " : \t");
                for (int j = 0; j < freq[i]; j++) {
                    System.out.print("#");
                }
                System.out.println("");
            }
            fr1.close();
            //BRUTE FORCE TECHNIQUE TO BREAK THE CIPHER TEXT
            System.out.println("\nBRUTE FORCE TECHNIQUE : \n");
            //SAVING CIPHER TEXT INTO A NEW FILE
            File f = new File("C:\\Users\\vision\\Documents\\NetBeansProjects\\ISPRACS\\src\\enc.txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            fw.append(ciphertext);
            fw.close();
            //READING CIPHER FROM NEW FILE CREATED
            FileReader fr2 = new FileReader(f);
            BufferedReader br2 = new BufferedReader(fr2);
            String newText = br2.readLine();
            String temp = "";
            //RUNNING MULTIPLE ITERATIONS TO FIND ACTUAL TEXT
            for (int i = 0; i < 26; i++) {
                temp = "";
                for (int j = 0; j < newText.length(); j++) {
                    if (newText.charAt(j) < 'a' || newText.charAt(j) > 'z') {
                        temp += newText.charAt(j);
                    } else {
                        int ans;
                        if ((int) newText.charAt(j) - i > 'z') {
                            ans = ((int) newText.charAt(j) - i) % 26;
                        } else if ((int) newText.charAt(j) - i < 'a') {
                            ans = ((int) newText.charAt(j) - i) + 26;
                        } else {
                            ans = (int) newText.charAt(j) - i;
                        }
                        temp += (char) (ans);
                    }
                }
                System.out.println("ITERATION " + (i + 1) + " : \t" + temp);
                //WHEN ACTUAL TEXT Histogram FOUND
                if (temp.equalsIgnoreCase(plain)) {
                    System.out.println("\nCipher broke at Iteration " + (i + 1) + "\n");
                    System.exit(0);
                }
            }
       
    }
}

