import javax.swing.*;
import java.awt.*;

public class Interface {
    public static void main(String... args) {
        JFrame f = new JFrame();
        JLabel y;
        JLabel hor;
        JLabel vert;
        JTextField equation;
        JButton add;
        JSlider vertshift;
        JSlider horshift;
        Canvas canvas;

        JPanel p = new JPanel();
        y = new JLabel("Y= ");
        hor = new JLabel("Horizontal Shift");
        vert = new JLabel("Vertical Shift");
        equation = new JTextField();
        add = new JButton("add");
        vertshift = new JSlider();
        horshift = new JSlider();
        canvas = new Canvas();

        canvas.setBackground(Color.blue);

        y.setBounds(200, 405, 100, 30);
        equation.setBounds(225, 400, 400, 40);
        add.setBounds(75, 405, 100, 30);
        vertshift.setBounds(200, 450, 450, 30);
        horshift.setBounds(200, 500, 450, 30);
        vert.setBounds(85,450,100,30);
        hor.setBounds(85,500,100,30);
        canvas.setBounds(20, 20, 710, 375);

//        canvas.setBackground(Color.blue);

        p.add(canvas);

        f.add(hor);
        f.add(y);
        f.add(equation);
        f.add(add);
        f.add(vertshift);
        f.add(horshift);
        f.add(canvas);
        f.add(vert);

        f.add(p);

        f.setResizable(false);
        f.setSize(750, 550);
        f.setVisible(true);
    }
}
