package composite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublishNews implements NewsI {
    private final List<NewsI> news = new ArrayList<>();

    @Override
    public void publish(Date date) {
        for (NewsI n : news) {
            n.publish(date);
        }
    }

    public void add(NewsI news) {
        this.news.add(news);
    }

    public void clear() {
        System.out.println();
        this.news.clear();
    }
}
