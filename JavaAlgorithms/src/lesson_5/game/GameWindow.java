package lesson_5.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameWindow extends JFrame {
    private static final int WIN_HEIGHT = 500;
    private static final int WIN_WIDTH = 800;
    private int ROUNDS = 0;
    private final DefaultListModel<String> modelFirstCommand = new DefaultListModel<>();
    private final DefaultListModel<String> modelSecondCommand = new DefaultListModel<>();
    private final ArrayList<Hero> team1 = new ArrayList<>();
    private final ArrayList<Hero> team2 = new ArrayList<>();
    private final Game game;
    private final Random randomHeal = new Random();
    private final Random randomDamage = new Random();
    private final Random randomAddHeel = new Random();
    private final JTextArea textArea;

    private final ArrayList<String> names = new ArrayList<>();


    public int getRounds() {
        return ROUNDS;
    }

    public ArrayList<Hero> getTeam1() {
        return team1;
    }

    public ArrayList<Hero> getTeam2() {
        return team2;
    }

    public boolean checkCountCircle( String str ) {
        int d;
        try {
            d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            System.out.println("Вводимое значение должно быть числом.");
            return false;
        }
        if (d < 0) {
            System.out.println("Вводимое значение должно быть положительным.");
            return false;
        }
        if (d > 10) {
            System.out.println("Вводимое значение должно быть меньше 10.");
            return false;
        }
        ROUNDS = d;
        return true;
    }

    public String getName( ArrayList<Hero> team, String name ) {
        int count = 0;
        for (Hero item : team) {
            if (item.name.contains(name)) count++;
        }
        return count == 0 ? name : name + " " + count;
    }

    public String initHero( String typeHero, ArrayList<Hero> team ) {
        int heal = randomHeal.nextInt(50);
        int damage = randomDamage.nextInt(20);

        names.add("Tigril");
        names.add("Akali");
        names.add("Jana");
        names.add("Plato");
        names.add("Victor");
        names.add("Maximus");

        String name;
        switch (typeHero) {
            case "Warrior":
                heal += 200;
                damage += 50;
                name = setNameFromArrayList(team);
                return addHerro(typeHero, team, heal, damage, name, new Warrior(heal, name, damage, 0));
            case "Assasin":
                heal += 100;
                damage += 70;
                name = setNameFromArrayList(team);
                return addHerro(typeHero, team, heal, damage, name, new Assasin(heal, name, damage, 0));
            case "Doctor":
                heal += 100;
                name = setNameFromArrayList(team);
                int addHeal = randomAddHeel.nextInt(20) + 40;
                return addHerro(typeHero, team, heal, addHeal, name, new Doctor(heal, name, 0, 60));
        }
        return null;
    }

    private String setNameFromArrayList( ArrayList<Hero> team ) {
        if (names.isEmpty()) return getName(team, "Player");
        String name;
        name = getName(team, names.get(0));
        names.remove(names.get(0));
        return name;
    }

    private String addHerro( String typeHero, ArrayList<Hero> team, int heal, int damage, String name, Hero hero ) {
        team.add(hero);
        String damageOrHealString = "damage";
        if (hero.addHeal > 0) damageOrHealString = "addHeal";
        return typeHero + ": " + name + " (heal = " + heal + ", " + damageOrHealString + " = " + damage + ")";
    }

    public void addMessage( String message ) {
        textArea.append(message);
        textArea.append("\n");
    }

    public void clearMessages() {
        textArea.setText("");
    }

    public GameWindow() {

        String[] heros = {"Warrior", "Assasin", "Doctor"};
        game = new Game(this);

        setTitle("Hero Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);

        JComboBox<String> bookList1 = new JComboBox<>(heros);
        JComboBox<String> bookList2 = new JComboBox<>(heros);

        JButton butLeft = new JButton("add");
        JButton butRight = new JButton("add");

        JList<String> listLeft = new JList<>(modelFirstCommand);
        JList<String> listRight = new JList<>(modelSecondCommand);

        JTextField textField = new JTextField("1");
        JButton butStart = new JButton("start");
        JButton butClose = new JButton("close");

        textArea = new JTextArea(12, 1);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        //выподающий JComboBox первой команды
        c.weightx = 0.45;
        c.weighty = 0.02;
        c.gridx = 0;
        c.gridy = 0;
        add(bookList1, c);

        c.weightx = 0.05;
        c.gridx = 1;
        add(butLeft, c);

        //выподающий JComboBox второй команды
        c.weightx = 0.45;
        c.gridx = 2;
        add(bookList2, c);

        c.weightx = 0.05;
        c.gridx = 3;
        add(butRight, c);

        //состав первой команды
        c.weightx = 1;
        c.weighty = 0.4;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0, 5, 0, 5);
        add(new JScrollPane(listLeft), c);

        //состав второй команды
        c.gridx = 2;
        add(new JScrollPane(listRight), c);

        //количесво раундов
        c.weighty = 0.01;
        c.weightx = 0.2;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(new JLabel("round"), c);

        c.gridx = 1;
        add(textField, c);

        c.gridx = 2;
        add(butStart, c);

        c.gridx = 3;
        add(butClose, c);

        //табло результатов
        c.weighty = 0.4;
        c.weightx = 1;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 3;
        add(textArea, c);

        //Повешаем слушателей на кнопки
        butLeft.addActionListener(e -> {
            String selected = (String) bookList1.getSelectedItem();
            if (modelFirstCommand.getSize() < 3) {
                assert selected != null;
                modelFirstCommand.addElement(initHero(selected, team1));
            }
            switchSelectedIndex(bookList1);
        });

        butRight.addActionListener(e -> {
            String selected = (String) bookList2.getSelectedItem();
            if (modelSecondCommand.getSize() < 3) {
                assert selected != null;
                modelSecondCommand.addElement(initHero(selected, team2));
            }
            switchSelectedIndex(bookList2);
        });

        butStart.addActionListener(e -> {
            if (!checkCountCircle(textField.getText())) return;
            if (team1.size() != team2.size()) {
                System.out.println("В командах должно быть равное количество игроков.");
                return;
            }
            if (team1.size() == 0) {
                System.out.println("Нужно подобрать команды");
                return;
            }
            game.fight();
        });

        butClose.addActionListener(e -> System.exit(0));


        setVisible(true);

    }

    private void switchSelectedIndex( JComboBox<String> bookList1 ) {
        int id = bookList1.getSelectedIndex() + 1;
        if ((bookList1.getItemCount() - 1) == bookList1.getSelectedIndex())
            id = 0;
        bookList1.setSelectedIndex(id);
    }
}