package lesson_1.marathon;

import lesson_1.marathon.competitors.Competitor;
import lesson_1.marathon.obstacles.Cross;
import lesson_1.marathon.obstacles.Obstacle;
import lesson_1.marathon.obstacles.Wall;
import lesson_1.marathon.obstacles.Water;

/**
 * 3. Добавить класс Course (полоса препятствий),
 * в котором будут находиться массив препятствий
 * и метод, который будет просить команду пройти всю полосу;
 */

public class Course {

    private Obstacle[] course;

    public Course() {
        this.course = new Obstacle[]{ // препядствия по умолчанию
                new Cross(80),
                new Water(2),
                new Wall(1),
                new Cross(120)
        };
    }

    public Course(Obstacle[] course) { // если захотим создать новые препятствия
        this.course = course;
    }

    public Course doIt(Team team) {
        System.out.println("На дистанции команда под именем: " + team.getTeamName() + "!");
        for (Competitor c : team.getCompetitors()) {
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
        return this;
    }
}
