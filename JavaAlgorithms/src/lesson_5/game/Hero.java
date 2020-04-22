package lesson_5.game;

public abstract class Hero {

    protected int health;
    protected final int initHealth;
    protected final String name;
    protected final int damage;
    protected final int addHeal;

    public Hero( int health, String name, int damage, int addHeal ) {
        this.health = health;
        this.initHealth = health;
        this.name = name;
        this.damage = damage;
        this.addHeal = addHeal;
    }

    abstract void hit( Hero hero );

    abstract void healing( Hero hero );

    void causeDamage( int damage ) {
        if (health <= 0) {
            System.out.println("Герой уже мертвый!");
        } else {
            health -= damage;
            if (health < 0) health = 0;
        }

    }

    public int getHealth() {
        return health;
    }

    void addHealth( int health ) {
        if ((health + this.health) < initHealth)
            this.health += health;
        else
            this.health = this.initHealth;
    }

    void info() {
        System.out.println(name + " " + (health <= 0 ? "Герой мертвый" : health) + " " + damage);
    }

    String getInfo() {
        return name + " " + (health <= 0 ? "Герой мертвый" : health) + " " + damage;
    }
}