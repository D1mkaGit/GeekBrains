package lesson_4.pwd_check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordChecker {

    private final String password;

    public PasswordChecker(String pwd) {
        this.password = pwd;
    }

    //Пароль должен быть не менее 8ми символов.
    public PasswordChecker checkNumOfLetters(int numOfLetters) {
        if (isNumOfLetters(numOfLetters)) {
            System.out.println("Количество букв верное: " + numOfLetters + " или более символов.");
        } else {
            System.err.println("Количество букв не верное, должно быть более " + numOfLetters + " символов.");
        }
        return this;
    }

    //В пароле должно быть число
    public PasswordChecker checkIfPwdHaveNumber() {
        if (isPwdHaveNumber()) {
            System.out.println("Пароль содержит цифры");
        } else {
            System.err.println("Пароль должен содержать минимум 1 цифру.");
        }
        return this;
    }

    //В пароле должна быть хотя бы 1 заглавная буква
    public PasswordChecker checkIfPwdHaveUpperCaseLetters() {
        if (isPwdHaveUpperCaseLetters()) {
            System.out.println("Пароль содержит строчные буквы");
        } else {
            System.err.println("Пароль должен содержать минимум 1 строчную букву.");
        }
        return this;
    }

    //В пароле должна быть хотя бы 1 строчная буква
    public PasswordChecker checkIfPwdHaveLowerCaseLetters() {
        if (isPwdHaveLowerCaseLetters()) {
            System.out.println("Пароль содержит прописные буквы");
        } else {
            System.err.println("Пароль должен содержать минимум 1 прописную букву.");
        }
        return this;
    }

    public PasswordChecker checkIfPwdHaveOnlyLatinLetters() { // на всякий случай, может понадобится :)
        if (match("^[A-Za-z]+$")) {
            System.out.println("Пароль содержит только латинские буквы");
        } else {
            System.err.println("Пароль должен содержать только латинские буквы.");
        }
        return this;
    }

    public PasswordChecker checkIfPwdHaveSpecificSymbols() { // на всякий случай
        if (match("^.*\\W.*$")) {
            System.out.println("Пароль содержит спецсимволы");
        } else {
            System.err.println("Пароль должен содержать минимум 1 спецсимвол.");
        }
        return this;
    }

    // Не должно быть пробелов \\s*
    public PasswordChecker checkIfPwdHaveSpaces() {
        if (isPwdHaveNoSpaces()) {
            System.out.println("Пароль не содержит пробелы");
        } else {
            System.err.println("Пароль не должен содержать пробелы.");
        }
        return this;
    }

    // true / false part
    //Пароль должен быть не менее 8ми символов.
    private boolean isNumOfLetters(int numOfLetters) {
        return match("^.{" + numOfLetters + ",}$");
    }

    //В пароле должно быть число
    private boolean isPwdHaveNumber() {
        return match("^.*\\d.*$");
    }

    //В пароле должна быть хотя бы 1 заглавная буква
    private boolean isPwdHaveUpperCaseLetters() {
        return match("^.*[A-ZА-ЯЁ].*$");
    }

    //В пароле должна быть хотя бы 1 строчная буква
    private boolean isPwdHaveLowerCaseLetters() {
        return match("^.*[a-zа-яё].*$");
    }

    // Не должно быть пробелов
    private boolean isPwdHaveNoSpaces() {
        return !match("^.*\\s.*$");
    }

    public boolean isCommonChecksAraOk() {
        return isNumOfLetters(8) &&
                isPwdHaveNumber() &&
                isPwdHaveUpperCaseLetters() &&
                isPwdHaveLowerCaseLetters() &&
                isPwdHaveNoSpaces();
    }

    private boolean match(String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
