import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mybutton extends JButton {
    private final Color COLOR1;
    private final Color COLOR2;
    private final String TEXT1;
    private final String TEXT2;
    private boolean stateis1 = true;


    Mybutton(Color color1, Color color2, String text1, String text2) {
        this.COLOR1 = color1;
        this.COLOR2 = color2;
        this.TEXT1 = text1;
        this.TEXT2 = text2;
        setBackground(COLOR1);
        setText(TEXT1);
        setSize(20,20);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleState1();
            }
        });

        }

    public void toggleState1() {
        if (stateis1) {
            setBackground(COLOR2);
            setText(TEXT2);
            stateis1 = false;
        } else {
            setBackground(COLOR1);
            setText(TEXT1);
            stateis1 = true;
        }
    }


}
