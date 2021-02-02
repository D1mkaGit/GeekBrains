package composite;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        NewsI financeNews = new FinanceNews();
        NewsI sportNews = new SportNews();
        NewsI techNews = new TechNews();
        PublishNews publishNews = new PublishNews();
        publishNews.add(financeNews);
        publishNews.publish(new Date());
        publishNews.clear();
        Thread.sleep(1000); // эулируем задержку в публикации статтей
        publishNews.add(techNews);
        publishNews.add(sportNews);
        publishNews.publish(new Date());
        publishNews.clear();
        Thread.sleep(1000); // эулируем задержку в публикации статтей
        publishNews.add(sportNews);
        publishNews.add(techNews);
        publishNews.add(financeNews);
        publishNews.publish(new Date());
        publishNews.clear();
    }
}
