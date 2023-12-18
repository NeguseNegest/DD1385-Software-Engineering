import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MyframeFinal extends JFrame {
    private List<MybuttonFinal> buttons = new ArrayList<>();

    MyframeFinal(String[] args) {
        setTitle("Extra");
        setSize(300, 300);
        getContentPane().setBackground(Color.blue);  // Set background color of content pane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        int numButtons = Integer.parseInt(args[0]);
        for (int i = 0; i < numButtons; i++) {
            MybuttonFinal button = new MybuttonFinal(Color.green, Color.red, args[2 * i + 1], args[2 * i + 2], buttons);
            buttons.add(button);
            add(button);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new MyframeFinal(args);
    }
}

class MybuttonFinal extends JButton implements ActionListener {
    private final Color COLOR1;
    private final Color COLOR2;
    private final String TEXT1;
    private final String TEXT2;
    private boolean stateis2 = true;
    private List<MybuttonFinal> allButtons;

    MybuttonFinal(Color color1, Color color2, String text1, String text2, List<MybuttonFinal> allButtons) {
        this.COLOR1 = color1;
        this.COLOR2 = color2;
        this.TEXT1 = text1;
        this.TEXT2 = text2;
        this.allButtons = allButtons;
        setBackground(COLOR1);
        setText(TEXT1);
        setOpaque(true);
        setContentAreaFilled(true);
        setBorderPainted(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (MybuttonFinal button : allButtons) {
            if (button != this) {
                button.toggleState3();
            }
        }
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
