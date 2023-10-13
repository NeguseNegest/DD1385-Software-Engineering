import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyframeA extends JFrame implements ActionListener {
    private MybuttonA button;
    private MybuttonA button2;

    MyframeA(){
        setTitle("Jonathan");
        setSize(300, 300);
        getContentPane().setBackground(Color.blue);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        button = new MybuttonA(Color.RED, Color.GREEN, "Text1", "Text2");
        button2 = new MybuttonA(Color.GRAY, Color.BLACK, "Yeah", "Buddy");
        button2.addActionListener(this);
        add(button2);
        add(button);
        button.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            button.toggleState1();
        } else if (e.getSource() == button2) {
            button2.toggleState1();
        }
    }

    public static void main(String[] args) {
        new MyframeA();
    }


}

class MybuttonA extends JButton{
    private final Color COLOR1;
    private final Color COLOR2;
    private final String TEXT1;
    private final String TEXT2;
    private boolean stateis5=true;
    MybuttonA(Color color1, Color color2, String text1, String text2){
        this.COLOR1 = color1;
        this.COLOR2 = color2;
        this.TEXT1 = text1;
        this.TEXT2 = text2;
        setBackground(COLOR1);
        setText(TEXT1);
        setOpaque(true);
        setContentAreaFilled(true);
        setBorderPainted(false);

    }
    public void toggleState1() {
        if (stateis5) {
            setBackground(COLOR2);
            setText(TEXT2);
            stateis5 = false;
        } else {
            setBackground(COLOR1);
            setText(TEXT1);
            stateis5 = true;
        }
    }

}
