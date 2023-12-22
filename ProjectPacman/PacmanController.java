package ProjectPacman;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PacmanController implements GameLoopListener{
    private PacmanModel model;
    private PacmanView view;
    private PacPlayer pacmanEntity;

    private Timer timer;
    
    public PacmanController(PacmanModel model, PacmanView view, PacPlayer pacmanEntity){
        
        this.model = model;
        this.view = view;
        this.pacmanEntity = pacmanEntity;
        pacmanEntity.setDirection(0);
        this.view.resetButton.addActionListener(resetButtonPressed);
        this.view.addKeyListener(arrowKeyPressed); // You need a frame listen to Keys
        timer = new Timer(80, new GameTimer(this));
        timer.start();
    }
    
    final private ActionListener resetButtonPressed = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            onGameReset();
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
                pacmanEntity.setDirection(1);
            } else if (keyCode == KeyEvent.VK_UP) {
                pacmanEntity.setDirection(2);
            }else if (keyCode == KeyEvent.VK_LEFT){
                pacmanEntity.setDirection(3);
            }else if (keyCode == KeyEvent.VK_DOWN){
                pacmanEntity.setDirection(4);
            }
        }
    }; 


    @Override
    public void onGameTick() {
        if (!model.checkLossCondition()) { 
            model.movePacman();
            // model.moveGhosts();
            if (model.checkWinCondition()) {
                onGameWin();
            } else if (model.checkLossCondition()) {
                onGameLoss();
            }
            view.update();

        }
    }

    // Handle the game win event
    @Override
    public void onGameWin() {
        timer.stop();
    }

    // Handle the game reset event
    @Override
    public void onGameReset() {
        model.resetGame();
        timer.restart();
        view.requestFocusInWindow();
    }

    // Handle the game loss event
    @Override
    public void onGameLoss() {
        timer.stop();
    }

}