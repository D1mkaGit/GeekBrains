package lesson_4.pwd_check;

import java.util.Scanner;

public class Main {

    private static String passwordToCheck;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите пароль для проверки: ");
        String str = in.nextLine();

        PasswordChecker passwordChecker = new PasswordChecker(str);
        System.out.println(passwordChecker.isCommonChecksAraOk());

        in.close();

        // Начал не по заданию из txt файлу, потом нашел перед сдачей, пришлось доработать :)
        // Ниже первоначальный вариант, который просто красиво делает проверку, а не возвращает булевы значения, как
        // оказалось, требуется по ТЗ
        /*
        passwordToCheck = "!ыQWЙХжЁ! \"№;%:?*()_+UIOP1f";
        PasswordChecker passwordChecker = new PasswordChecker(passwordToCheck);
        passwordChecker
                .checkNumOfLetters(8)
                .checkIfPswdHaveNumber()
                .checkIfPswdHaveUpperCaseLetters()
                .checkIfPswdHaveLowerCaseLetters()
                //.checkIfPswdHaveOnlyLatinLetters()
                //.checkIfPswdHaveSpecificSymbols()
                .checkIfPswdHaveSpaces();*/

    }
}
