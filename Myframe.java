import javax.swing.*;
import java.awt.*;

public class Myframe extends JFrame {
    Mybutton button1 = new Mybutton(Color.red, Color.blue, "Tillstånd 1", "Tillstånd 2");
    Mybutton button2 = new Mybutton(Color.yellow, Color.orange, "Tillstånd 3", "Tillstånd 4");

    Myframe() {
        setTitle("Jonathan Tadesse");
        setSize(500, 500);
        setBackground(Color.red);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout manager to FlowLayout
        setLayout(new FlowLayout());

        // Add the button to the frame
        add(button1);
        add(button2);


        // Set the visibility of the frame to true after all components are added
        setVisible(true);
    }

    public static void main(String[] u) {
        Myframe window = new Myframe();
    }
}
