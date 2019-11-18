package lesson_1.marathon;

import lesson_1.marathon.competitors.Cat;
import lesson_1.marathon.competitors.Competitor;
import lesson_1.marathon.competitors.Dog;
import lesson_1.marathon.competitors.Human;

public class Main {
    public static void main(String[] args) {

        Course c1 = new Course(); // Создаем полосу препятствий

        Team team1 = new Team(); // Создаем команду
        c1.doIt(team1); // Просим команду пройти полосу
        team1.showResults(); // Показываем результаты

        Team team2 = new Team(new Competitor[]{ // Создаем вторую команду
                new Human("Боря"),
                new Cat("Барсик"),
                new Dog("Бобик"),
        });
        team2.setTeamName("Буряты"); // задаем имя команде
        c1.doIt(team2); // Просим команду пройти полосу
        team2.showResults(); // Показываем результаты

        System.out.println("Из двух команд прошли дистанцию:");
        team1.showWhoPassDistance();
        team2.showWhoPassDistance();

        System.out.println("Не прошли дистанцию:");
        team1.showWhoFailDistance();
        team2.showWhoFailDistance();

        // далее можно сделать второй раунд на новой дистанции
        /*Course c2 = new Course(new Obstacle[]{
                new Cross(90),
                new Water(3),
                new Wall(2),
                new Cross(130)});*/

    }
}