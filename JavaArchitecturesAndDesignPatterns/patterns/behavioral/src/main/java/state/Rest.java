package state;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Rest implements Activity {
    @Override
    public void doActivity() {
        System.out.println("- " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + ": Начинаем отдыхать!");
        System.out.println("Отдыхаем!!!");
    }
}
