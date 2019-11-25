package Lesson_4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainRex {
    public static void main(String[] args) {
        System.out.println(find("jjjjj"));
        System.out.println(find("Gava"));
        System.out.println(find("ava"));
        System.out.println(find("Javaasdsad"));

    }

    public static boolean find(String str) {
        Pattern p = Pattern.compile("^[.*]{8,20}$");
        //  Pattern p = Pattern.compile("^.*\\d$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
