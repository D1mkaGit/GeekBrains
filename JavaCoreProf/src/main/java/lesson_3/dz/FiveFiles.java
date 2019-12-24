package lesson_3.dz;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

// Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
// Может пригодиться следующая конструкция: ArrayList<InputStream> al = new ArrayList<>();
// ...
// Enumeration<InputStream> e = Collections.enumeration(al);
public class FiveFiles {
    private static final String path = "src/main/java/lesson_3/dz/";

    public static void main( String[] args ) throws IOException {

        ArrayList<InputStream> ali = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ali.add(new FileInputStream(createFile(100, "file" + i + ".txt", 65 + i)));
        }

        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));

        int x;
        while ((x = in.read()) != -1) {
            System.out.print((char) x);
        }
        in.close();
    }

    private static File createFile( int byteCount, String name, int byteId ) {
        File file = new File(path, name);
        byte[] outData = new byte[byteCount];

        for (int i = 0; i < outData.length; i++) {
            outData[i] =
                    (byte) byteId;
            //(byte) (1000 * random());
            //System.out.println(outData[i]);
        }
        try (FileOutputStream out = new FileOutputStream(file)) {
            for (byte outDatum : outData) {
                out.write(outDatum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
