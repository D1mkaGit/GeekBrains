package composite;

import java.util.Date;

public class FinanceNews implements NewsI {
    @Override
    public void publish(Date date) {
        System.out.println(date + ": Finance news: Finance specific text goes here");
    }
}
