package lesson_3.dz;

import java.io.*;
import java.nio.charset.StandardCharsets;

// Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
public class ReadFile01 {
    private static final String path = "src/main/java/lesson_3/dz/";

    public static void main( String[] args ) throws IOException {
        File file = new File(path, "fileNew.txt");

        String text50Bytes = "This is a great text to create a file in 50 bytes!";
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
                // Создаем массив байт:
                out.write(new String(arr, 0, x, StandardCharsets.UTF_8).getBytes());
                //System.out.print(new String(arr, 0, x, "UTF-8"));
            }
            byte[] byteArr = out.toByteArray();

            ByteArrayInputStream input = new ByteArrayInputStream(byteArr);
            int y;
            while ((y = input.read()) != -1) {
                System.out.print(y + " ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
