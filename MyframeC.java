import javax.swing.*;
import java.awt.*;

public class Myframe extends JFrame {
    Mybutton button1 = new Mybutton(Color.green, Color.red, "Tillstånd 1", "Tillstånd 2");
    Mybutton button2 = new Mybutton(Color.yellow, Color.black, "Tillstånd 3", "Tillstånd 4");

    Myframe() {
        setTitle("Jonathan  1");
        setSize(300, 300);
        getContentPane().setBackground(Color.blue);  // Set background color of content pane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        add(button1);
        add(button2);

        setVisible(true);
    }

    public static void main(String[] u) {
        Myframe window = new Myframe();
    }
}
