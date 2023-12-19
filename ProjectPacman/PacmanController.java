package ProjectPacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PacmanController implements KeyListener, ActionListener{
    private PacmanModel model;
    private PacmanView view;
    private Timer timer;
    private int direction;

    PacmanController(PacmanModel model, PacmanView view){
        this.model = model;
        this.view = view;
        direction = 0;
        timer = new Timer(80, this);
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
 
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_RIGHT) {
            direction = 1;
        } else if (keyCode == KeyEvent.VK_UP) {
            direction = 2;
        }else if (keyCode == KeyEvent.VK_LEFT){
            direction = 3;
        }else if (keyCode == KeyEvent.VK_DOWN){
            direction = 4;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (direction != 0){
            if (direction == 1) {
                model.move("RIGHT");
            } else if (direction == 2) {
                model.move("UP");
            } else if (direction==3){
                model.move("LEFT");
            } else if (direction==4){
                model.move("DOWN");
            }
        }
        // view.update(); // Updates the view 
        view.repaint();
        view.update();
    }
    
}