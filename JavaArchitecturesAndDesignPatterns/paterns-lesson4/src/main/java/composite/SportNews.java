package composite;

import java.util.Date;

public class SportNews implements NewsI {
    @Override
    public void publish(Date date) {
        System.out.println(date + ": Sport news: Sport specific text goes here");
    }
}
