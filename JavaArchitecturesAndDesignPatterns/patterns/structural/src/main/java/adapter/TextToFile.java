package adapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TextToFile {
    public static void main(String[] args) {
        OutputStreamWriter streamWriter = null;
        try {
            streamWriter = new OutputStreamWriter(new FileOutputStream("test.txt")); // OutputStreamWriter - adapter для записи текста в файл
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert streamWriter != null;
            streamWriter.write("test string for writing"); // использование адаптера по назначению
            streamWriter.close(); // отключение адаптера
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
