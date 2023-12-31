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

    private int gameTickCounter;
    private Timer timer;
    
    public PacmanController(PacmanModel model, PacmanView view, PacPlayer pacmanEntity){
        gameTickCounter = 0;
        this.model = model;
        this.view = view;
        this.pacmanEntity = pacmanEntity;
        this.view.resetButton.addActionListener(resetButtonPressed);
        this.view.addKeyListener(arrowKeyPressed); // You need a frame listen to Keys
        timer = new Timer(80, gameLoop);
        this.view.startButton.addActionListener(startButtonPressed);
        timer.start();
    }
    

    final private ActionListener gameLoop = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            onGameTick();
        };
    };

    final private ActionListener startButtonPressed = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.start();
            view.requestFocusInWindow();
        };
    };

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

            // pacmanEntity.q();
            if (gameTickCounter%2==0){
                model.moveGhosts();
            }
            gameTickCounter++;
            if (model.checkWinCondition()) {
                onGameWin();
            } else if (model.checkLossCondition()) {
                onGameLoss();
            }
            view.update();

        }
    }

    @Override
    public void onGameReset() {
        model.resetGame();
        timer.restart();
        view.requestFocusInWindow();
        view.clearMessage();
        // view.displayMessage("Game was reset");
    }
    
    @Override
    public void onGameWin() {
        timer.stop();
        pacmanEntity.setScore(0);
        view.displayMessage("ProjectPacman/assets/YouWon.png");
    }

    @Override
    public void onGameLoss() {
        timer.stop();
        view.displayMessage("ProjectPacman/assets/GameOver.png");
    }

}