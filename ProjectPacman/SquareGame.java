package ProjectPacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Model
class SquareModel {
    private int x;
    private final int speed = 10;

    public SquareModel() {
        x = 50; // Initial x-coordinate
    }

    public int getX() {
        return x;
    }

    public void moveLeft() {
        x -= speed; // Move 5 units to the left
    }

    public void moveRight() {
        x += speed; // Move 5 units to the right
    }
}

// View
class SquareView extends JPanel {
    private SquareModel model;

    public SquareView(SquareModel model) {
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(model.getX(), 50, 50, 50); // Draw the square at the current x-coordinate
    }

    public void show(){     
        JFrame frame = new JFrame("Square Game");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        frame.add(this);
        frame.setVisible(true);
        
    }
}

// ...

// Controller
class SquareController implements KeyListener, ActionListener {
    private SquareModel model;
    private SquareView view;
    private Timer timer;
    private int direction; // -1 for left, 1 for right, 0 for no movement

    public SquareController(SquareModel model, SquareView view) {
        this.model = model;
        this.view = view;
        direction = 0; // Initially, no movement
        timer = new Timer(16, this); // Timer to update the view every 16 milliseconds
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
 
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            direction = -1;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            direction = 1;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (direction != 0) {
            if (direction == -1) {
                model.moveLeft();
            } else if (direction == 1) {
                model.moveRight();
            }
            view.repaint(); // Updates the view 
        }
    }
}


// Main class
public class SquareGame {
    public static void main(String[] args) {
        SquareModel model = new SquareModel();
        SquareView view = new SquareView(model);
        SquareController controller = new SquareController(model, view);

        view.addKeyListener(controller);
        view.show();
    }
}
