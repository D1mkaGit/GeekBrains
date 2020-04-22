package lesson_5.game;

import javax.swing.*;
import java.util.Random;

class Game {

    private final Random randomStep = new Random();
    private final Random randomHealing = new Random();

    private final GameWindow gameWindow;

    public Game( GameWindow gameWindow ) {
        this.gameWindow = gameWindow;
    }

    public void fight() {

        gameWindow.clearMessages();
        int round = gameWindow.getRounds();
        boolean gameOver = false;
        boolean nobodyWon = false;
        int looserTeam = 1;


        Hero[] team1 = new Hero[gameWindow.getTeam1().size()];
        team1 = gameWindow.getTeam1().toArray(team1);

        Hero[] team2 = new Hero[gameWindow.getTeam2().size()];
        team2 = gameWindow.getTeam2().toArray(team2);


        for (int j = 0; j < round; j++) {
            int sumOfHealthTeam1 = 0;
            int sumOfHealthTeam2 = 0;
            for (int i = 0; i < team1.length; i++) {
                if (randomStep.nextInt(2) == 0) {
                    if (team1[i].health > 0) {
                        if (team1[i] instanceof Doctor) {
                            healLowHealthMember(team1, team2, i);
                        } else {
                            makeHitOnLowerHealth(team1[i], team2, i);
                        }
                    }
                } else {
                    if (team1[i].health > 0) {
                        if (team2[i] instanceof Doctor) {
                            healLowHealthMember(team2, team1, i);
                        } else {
                            makeHitOnLowerHealth(team2[i], team1, i);
                        }
                    }
                }
            }

            boolean onlyDoctorAlive1 = isOnlyDoctorAlive(team1);
            boolean onlyDoctorAlive2 = isOnlyDoctorAlive(team2);
            if (onlyDoctorAlive2 && onlyDoctorAlive1) {
                nobodyWon = true;
                break;
            }

            for (int i = 0; i < team1.length; i++) {
                sumOfHealthTeam1 += team1[i].health;
                sumOfHealthTeam2 += team2[i].health;
            }

            if (sumOfHealthTeam1 <= 0 || sumOfHealthTeam2 <= 0) {
                gameOver = true;
                if (sumOfHealthTeam2 < sumOfHealthTeam1) looserTeam = 2;
                break;
            }
        }

        gameWindow.addMessage("----------- Результаты-------------");
        gameWindow.addMessage("Первая команда:");
        for (Hero t1 : team1) {
            t1.info();
            gameWindow.addMessage(t1.getInfo());
        }
        gameWindow.addMessage("Вторая команда:");
        for (Hero t2 : team2) {
            t2.info();
            gameWindow.addMessage(t2.getInfo());
        }
        if (gameOver) {
            String output = "Команда номер " + looserTeam + " проиграла";
            gameWindow.addMessage(output);
            System.out.println(output);
            JOptionPane.showMessageDialog(gameWindow, output, "Game Over", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        if (nobodyWon) {
            String output = "Ничья, остались только доктора";
            gameWindow.addMessage(output);
            System.out.println(output);
            JOptionPane.showMessageDialog(gameWindow, output, "Nobody Won", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private boolean isOnlyDoctorAlive( Hero[] team1 ) {
        boolean onlyDoctorAlive1 = false;
        for (Hero hero : team1) {
            if (hero.health > 0 && !(hero instanceof Doctor)) break;
            else onlyDoctorAlive1 = true;
        }
        return onlyDoctorAlive1;
    }

    private void healLowHealthMember( Hero[] team1, Hero[] team2, int i ) {
        int targetId = i;
        int lowHealth = 0;
        for (int k = 0; k < team2.length; k++) {
            if (team2[k].health > lowHealth && team2[k].health != 0) targetId = k;
        }
        team1[i].healing(team1[targetId]);
    }

    private void makeHitOnLowerHealth( Hero hero, Hero[] team2, int i ) {
        int targetId = i;
        int lowHealth = 999;
        for (int k = 0; k < team2.length; k++) {
            if (team2[k].health < lowHealth && team2[k].health > 0) targetId = k;
        }
        hero.hit(team2[targetId]);
    }
}