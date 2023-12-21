package ProjectPacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PacmanController{
    private PacmanModel model;
    private PacmanView view;
    private Timer timer;
    private int direction;


    
    PacmanController(PacmanModel model, PacmanView view){
        direction = 0;
        this.model = model;
        this.view = view;
        this.view.resetButton.addActionListener(resetButtonPressed);
        this.view.addKeyListener(arrowKeyPressed); // You need a frame listen to Keys
        timer = new Timer(80, gameLoop);
        timer.start();
    }
    
    final private ActionListener resetButtonPressed = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            model.resetGame();
        }
    };
    
    final private KeyListener arrowKeyPressed = new KeyListener(){
        @Override
        public void keyTyped(KeyEvent e) {
            // Do nothing
        }
        @Override
        public void keyReleased(KeyEvent e) {
            // Do nothing
        }
     
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
    }; 




    final private ActionListener gameLoop = new ActionListener() {        
        @Override // Will not be called unless added as an argument to a keyListener
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
            view.update();
        }
    };

    
}