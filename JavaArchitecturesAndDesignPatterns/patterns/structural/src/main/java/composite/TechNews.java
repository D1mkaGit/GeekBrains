package composite;

import java.util.Date;

public class TechNews implements NewsI {
    @Override
    public void publish(Date date) {
        System.out.println(date + ": Tech news: Technical specific text goes here");
    }
}
