package lesson_5.game;

import java.util.Random;

class Game {

    private  Random randomStep = new Random();
    private  Random randomHealing = new Random();

    private GameWindow gameWindow;

    public Game(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void fight() {

        gameWindow.clearMessages();
        int round = gameWindow.getRounds();


        Hero[] team1 = new Hero[gameWindow.getTeam1().size()];
        team1 = gameWindow.getTeam1().toArray(team1);

        Hero[] team2 = new Hero[gameWindow.getTeam2().size()];
        team2 = gameWindow.getTeam2().toArray(team2);

        for (int j = 0; j < round; j++) {
            for (int i = 0; i < team1.length; i++) {
                if(randomStep.nextInt(2) == 0) {
                    if(team1[i] instanceof Doctor) {
                        team1[i].healing(team1[randomHealing.nextInt(2)]);
                    } else {
                        team1[i].hit(team2[i]);
                    }
                } else {
                    if(team2[i] instanceof Doctor) {
                        team2[i].healing(team2[randomHealing.nextInt(2)]);
                    } else {
                        team2[i].hit(team1[i]);
                    }
                }
            }
        }

        gameWindow.addMessage("----------- Результаты-------------");
        gameWindow.addMessage("Первая команда:");
        for (Hero t1: team1) {
            t1.info();
            gameWindow.addMessage(t1.getInfo());
        }
        gameWindow.addMessage("Вторая команда:");
        for (Hero t2: team2) {
            t2.info();
            gameWindow.addMessage(t2.getInfo());
        }
    }
}