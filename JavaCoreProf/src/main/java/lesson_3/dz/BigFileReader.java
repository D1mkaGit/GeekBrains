package lesson_3.dz;

import java.io.*;
import java.util.Scanner;

// Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
// Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
// Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
public class BigFileReader {
    private static final String path = "src/main/java/lesson_3/dz/";

    public static void main( String[] args ) throws IOException {
        String filePath = path + "bigTxtFile.txt";
        FileInputStream fileStream = new FileInputStream(filePath);
        InputStreamReader input = new InputStreamReader(fileStream);
        BufferedReader reader = new BufferedReader(input);

        int charCount = 0;
        String data;
        while ((data = reader.readLine()) != null) {
            charCount += data.length();
        }
        // System.out.println(charCount);
        int pageNumbersCount = charCount / 1800;

        Scanner sc = new Scanner(System.in);

        System.out.print("Введите номер страницы, от 0 до " + pageNumbersCount + ": ");
        while (!sc.hasNextInt()) sc.next();
        int pageNumber = sc.nextInt();
        if (pageNumber < 0 && pageNumber > pageNumbersCount) {
            System.out.print("Номер страницы, должен быть от 0 до " + pageNumbersCount + ": ");
            sc.next();
        } else {
            try (RandomAccessFile raf = new RandomAccessFile(path + "bigTxtFile.txt", "r")) {
                int numOfIntPerPage = 1800;
                int numOfStart = pageNumber * numOfIntPerPage;
                for (int i = numOfStart; i < numOfStart + numOfIntPerPage; i++) {
                    raf.seek(i);
                    System.out.print((char) raf.read());
                }
            }
        }
    }
}
