import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyframeB extends JFrame {
    MybuttonB button3 = new MybuttonB(Color.green, Color.red, "Tillstånd 1", "Tillstånd 2");

    MyframeB() {
        setTitle("UML B");
        setSize(300, 300);
        getContentPane().setBackground(Color.blue);  // Set background color of content pane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(button3);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyframeB();
    }
}

class MybuttonB extends JButton implements ActionListener {
    private final Color COLOR1;
    private final Color COLOR2;
    private final String TEXT1;
    private final String TEXT2;
    private boolean stateis2 = true;  // Initialized to true

    MybuttonB(Color color1, Color color2, String text1, String text2) {
        this.COLOR1 = color1;
        this.COLOR2 = color2;
        this.TEXT1 = text1;
        this.TEXT2 = text2;
        setBackground(COLOR1);
        setText(TEXT1);
        setOpaque(true);
        setContentAreaFilled(true);
        setBorderPainted(false);
        addActionListener(this);  // Add the ActionListener to the button
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toggleState3();
    }

    public void toggleState3() {
        if (stateis2) {
            setBackground(COLOR2);
            setText(TEXT2);
            stateis2 = false;
        } else {
            setBackground(COLOR1);
            setText(TEXT1);
            stateis2 = true;
        }
    }
}
