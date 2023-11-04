import java.awt.Color;

import javax.swing.*;

public class Square extends JButton {
    private int x; 
    private int y; 
    public Square(int x, int y, String text) {
        this.x = x;
        this.y = y;
        setText(text);
        setBackground(Color.red);
    }

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }
}
