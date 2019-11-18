package lesson_1.marathon.obstacles;

import lesson_1.marathon.competitors.Competitor;

public class Wall extends Obstacle {
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.jump(height);
    }
}