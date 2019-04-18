package sample;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Home extends JFrame {

    JPanel panel;

    public Home() {
        drawLine();
    }

    public void drawLine() {
        panel = new JPanel() {
            
            public void paintComponent(Graphics g, int i) {

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());

                g.setColor(Color.RED);
                // g.drawLine(100,10,100,100);

                g.drawArc(100, 100, 450, 450, i, (i + 1));





            }
        };
        for (int i = 0; i <360; i++) {
            add(panel);
            this.setSize(800, 800);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        }
    }


    public static void main(String[] args) {
        new Home();
    }
}