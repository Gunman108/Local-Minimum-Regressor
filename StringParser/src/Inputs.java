import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class Inputs {
    public static void main(String... args) {

        //Init. variables
        JFrame f = new JFrame();
        JButton enterbutton1;
        JTextField numberfield;
        JLabel numberlabel;
        JLabel inputlabel;
        JLabel ylabel;
        JButton buttons[];
        JTextField text[];
        final String[] Equationstring = new String[1];
        final int[] a = {0};
        final boolean[] inputed = {false};
        //Creates variables
        buttons = new JButton[99];
        text = new JTextField[99];
        enterbutton1 = new JButton("Enter");
        numberfield = new JTextField();
        numberlabel = new JLabel("How many equations do you want?");
        numberlabel.setFont(new Font("Verdana", Font.BOLD,14));
        numberlabel.setForeground(Color.white);
        inputlabel = new JLabel("Please input equations");
        inputlabel.setFont(new Font("Verdana", Font.BOLD,14));
        inputlabel.setForeground(Color.white);


//Sets location and size
        numberfield.setBounds(10,50,200,30);
        enterbutton1.setBounds(10,80,100,30);
        numberlabel.setBounds(10,10,400,30);
        inputlabel.setBounds(10,100,400,30);

        //Adds everything

        f.setDefaultCloseOperation(3);
        f.setSize(1300, 700);
        f.setVisible(true);
        f.setResizable(false);
        f.getContentPane().setBackground(Color.CYAN);

        f.add(numberlabel);
        f.add(enterbutton1);
        f.add(numberfield);


        enterbutton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                a[0] = Integer.parseInt(numberfield.getText());
                System.out.println(a[0]);
                inputed[0] = true;

            }
        });


//Why won't it get here
        System.out.println("I got here 1");
        while(a[0]==0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    if (a[0] >= 1 && inputed[0]==true) {
        f.add(inputlabel);
        for (int i = 0; i < a[0]; i++) {
            text[i] = new JTextField();
            text[i].setBounds(10,(i*50)+150,200,30);
            buttons[i] = new JButton("Enter");
            buttons[i].setBounds(240,(i*50)+150,100,30);
            f.add(text[i]);
            f.add(buttons[i]);
        }
    }


        //Formats Frame

    }
}
