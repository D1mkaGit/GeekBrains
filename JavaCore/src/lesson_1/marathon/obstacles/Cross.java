package lesson_1.marathon.obstacles;

import lesson_1.marathon.competitors.Competitor;

public class Cross extends Obstacle {
    private final int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}
