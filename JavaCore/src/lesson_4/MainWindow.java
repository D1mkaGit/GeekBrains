package lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    public static void main(String[] args) {
        new MyWindow();
    }
}

class MyWindow extends JFrame {
    public MyWindow() {
        setTitle("Java Chat");
        setBounds(800, 300, 400, 400);

        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        centerPanel.setBackground(Color.gray);
        bottomPanel.setBackground(Color.green);

        bottomPanel.setPreferredSize(new Dimension(1, 40));

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        centerPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        JButton jb = new JButton("Send");

        JTextArea jta = new JTextArea();
        JScrollPane jsp = new JScrollPane(jta);
        centerPanel.add(jsp, BorderLayout.CENTER);
        JTextField jtf = new JTextField();

        bottomPanel.add(jtf);
        bottomPanel.add(jb);

        jtf.setPreferredSize(new Dimension(300, 28));
        jta.setEditable(false);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.append(jtf.getText() + "\n");
                jtf.setText("");
                jtf.grabFocus();
            }
        });

//        JPanel panel = new JPanel(new GridLayout(1,2));
//        panel.add(jbt1);
//        panel.add(jbt2);
//
//        add(panel, BorderLayout.SOUTH);

//        JPanel panel = new JPanel();
//        panel.setBackground(Color.YELLOW);
//        add(panel, BorderLayout.CENTER);
//
//        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                System.out.println(e.getX() + " " + e.getY());
//            }
//        });
//
//        jbt1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Ok");
//            }
//        });

//        ImageIcon icon = new ImageIcon("file.jpg");
//        setIconImage(icon.getImage());

//        add(jbt1, BorderLayout.SOUTH);
//        add(jbt2, BorderLayout.SOUTH);
//
//        setLayout(null);
//        jbt1.setBounds(50,50,150,80);
//        jbt2.setBounds(150,150,150,80);
//
//        add(jbt1);
//        add(jbt2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setResizable(false);
        setVisible(true);
    }
}