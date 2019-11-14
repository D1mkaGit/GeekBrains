package lesson_1.marathon;

import lesson_1.marathon.competitors.Cat;
import lesson_1.marathon.competitors.Competitor;
import lesson_1.marathon.competitors.Dog;
import lesson_1.marathon.competitors.Human;
import lesson_1.marathon.obstacles.Cross;
import lesson_1.marathon.obstacles.Obstacle;
import lesson_1.marathon.obstacles.Wall;
import lesson_1.marathon.obstacles.Water;

/*
В итоге должно быть что-то вроде:

public static void main(String[] args) {
Course c = new Course(...); // Создаем полосу препятствий
Team team = new Team(...); // Создаем команду
c.doIt(team); // Просим команду пройти полосу
team.showResults(); // Показываем результаты
}
 */

public class Main {
    public static void main(String[] args) {
        Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
        Obstacle[] course = {new Cross(80), new Water(2), new Wall(1), new Cross(120)};
        for (Competitor c : competitors) {
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
        for (Competitor c : competitors) {
            c.info();
        }
    }
}