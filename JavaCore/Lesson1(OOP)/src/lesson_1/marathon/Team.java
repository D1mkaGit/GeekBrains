package lesson_1.marathon;

import lesson_1.marathon.competitors.Cat;
import lesson_1.marathon.competitors.Competitor;
import lesson_1.marathon.competitors.Dog;
import lesson_1.marathon.competitors.Human;

/**
 * 2. Добавить класс Team, который будет содержать название команды,
 * массив из четырех участников (в конструкторе можно сразу указыватьвсех участников ),
 * метод для вывода информации о членах команды, прошедших дистанцию,
 * метод вывода информации обо всех членах команды.
 */

public class Team {
    private String teamName = "Банда Дяди Фёдора";
    private Competitor[] competitors;

    public Team() {
        this.competitors = new Competitor[]{ // команда по умолчанию
                new Human("Дядя Фёдор"),
                new Cat("Матроскин"),
                new Dog("Шарик"),
                new Human("Почтальон Печкин")
        };
    }

    public Team(Competitor[] competitors) { //если захотим новую команду создать
        this.competitors = competitors;
    }

    public Team showWhoPassDistance() {
        for (Competitor c : competitors) {
            if (c.isOnDistance()) c.info();
        }
        return this;
    }

    public Team showWhoFailDistance() {
        for (Competitor c : competitors) {
            if (!c.isOnDistance()) c.info();
        }
        return this;
    }

    public Team showResults() {
        System.out.println("Результаты членов команды:");
        for (Competitor c : competitors) {
            c.info();
        }
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) { // если будет создаваться новая команда, надо ей и имя новое задать
        this.teamName = teamName;
    }

    public Competitor[] getCompetitors() {
        return competitors;
    }
}
