package lesson_3.dz_prepod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class ReverseLineInputStream {

    public static void main( String[] args ) throws IOException {
        ReverseLineInputStream reverseLineInputStream = new ReverseLineInputStream();
        reverseLineInputStream.readAndPrintInReverseOrder();
    }

    public void readAndPrintInReverseOrder() throws IOException {

        String path = "123/test1.txt";

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path));
            Stack<String> lines = new Stack<String>();
            String line = br.readLine();
            while (line != null) {
                lines.push(line);
                line = br.readLine();
            }

            while (!lines.empty()) {
                System.out.println(lines.pop());
            }

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
    }
}