import javax.swing.*;
import java.awt.*;

public class Myframe extends JFrame {
    Mybutton button1 = new Mybutton(Color.green, Color.red, "Tillstånd 1", "Tillstånd 2");
    Mybutton button2 = new Mybutton(Color.yellow, Color.black, "Tillstånd 3", "Tillstånd 4");

    Myframe() {
        setTitle("Jonathan Tadesse 1");
        setSize(300, 300);
        getContentPane().setBackground(Color.blue);  // Set background color of content pane
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
