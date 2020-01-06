package lesson_3.dz;

import java.io.*;
import java.nio.charset.StandardCharsets;

//Прочитать содержимое файла в обратном порядке.

public class BackwardReader {
    private static final String path = "src/main/java/lesson_3/dz/";

    public static void main( String[] args ) {
        File file = new File(path, "fileForBackReading.txt");

        String text50Bytes = "This is a great text to read it in backward order!";
        byte[] outData = text50Bytes.getBytes();
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(outData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream in = new FileInputStream(file)) {
            byte[] arr = new byte[512];
            int x;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((x = in.read(arr)) != -1) {
                // берем байты из текста и сохраняем в строку:
                out.write(new String(arr, 0, x, StandardCharsets.UTF_8).getBytes());
            }
            String str = out.toString();
            String reverse = new StringBuffer(str).reverse().toString();
            //переворачиваем строку через StringBuffer(StringToRevers).reverse()
            System.out.println("\nТекст из файла до реверса: " + str);
            System.out.println("Текст из файла, после реверса: " + reverse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
