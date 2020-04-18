package lesson_5.game;

import java.util.Random;

public class Assasin extends Hero {

    int cricitalHit;
    Random random = new Random();

    public Assasin(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
        this.cricitalHit = random.nextInt(20);
    }

    @Override
    void hit(Hero hero) {
        if (hero != this) {
            if(health < 0) {
                System.out.println("Герой погиб");
            } else {
                hero.causeDamage(damage + cricitalHit);
            }
            System.out.println(this.name + "нанес урон" + hero.name);
        }
    }

    @Override
    void healing(Hero hero) {
        System.out.println("Убийцы не умеют лечить!");
    }
}