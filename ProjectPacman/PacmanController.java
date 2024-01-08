package ProjectPacman;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.sound.sampled.*;


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
        timer = new Timer(80, gameLoop); // 12.5 game ticks per second
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


    // sound 
    private void playSound(String soundFileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    


    private Clip backgroundMusicClip;

    private void startBackgroundMusic() {
        try {
            if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
                backgroundMusicClip.stop(); // Stop if already playing
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                new File("ProjectPacman/assets/NewBackMusic.wav").getAbsoluteFile());
            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(audioInputStream);
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop continuously
            backgroundMusicClip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing background music.");
            ex.printStackTrace();
        }
    }

    private void stopBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.stop();
        }
    }


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
            //playSound("ProjectPacman/assets/Slower-Tempo-2020-03-22_-_8_Bit_Surf_-_FesliyanStudios.com_-_David_Renda.mp3");           
            
            // Move pacman
            model.movePacman();
            model.handleGhostPlayerCollision();
            if (model.checkWinCondition()) {
                onGameWin();
                playSound("ProjectPacman/assets/pacman_intermission.wav");
            } else if (pacmanEntity.isDead()){
                onPlayerDeath();
                playSound("ProjectPacman/assets/pacman_death.wav");
                return;
            }
            }
            view.update();

            // Move Ghosts
            if (gameTickCounter%2==0){
                model.moveGhosts();
            }
            gameTickCounter++;
            
            model.handleGhostPlayerCollision();
            if (model.checkWinCondition()) {
                onGameWin();
            } else if (pacmanEntity.isDead()){
                onPlayerDeath();
                playSound("ProjectPacman/assets/pacman_death.wav");
                return;
            }
            view.update();

        }
    

    @Override
    public void onGameReset() {
        model.resetGame();
        view.clearMessage();
        view.displayMessage("ProjectPacman/assets/reset.png");
        view.clearMessage(1);
        timer.restart();
        view.requestFocusInWindow();
        startBackgroundMusic();
    }
    
    @Override
    public void onGameWin() {
        timer.stop();
        pacmanEntity.setScore(0);
        view.displayMessage("ProjectPacman/assets/YouWon.png");
        stopBackgroundMusic(); 

    }

    @Override
    public void onGameLoss() {
        timer.stop();
        view.displayMessage("ProjectPacman/assets/GameOver.png");
        stopBackgroundMusic(); 
    }

    

    public void onPlayerDeath(){
        pacmanEntity.setLives(pacmanEntity.getLives()-1);
        if (model.checkLossCondition()){
            onGameLoss();
        }else{
            timer.stop();
            Timer deathTimer = new Timer(1500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pacmanEntity.revive();
                    
                    // System.out.println(pacmanEntity.getLives());
                    model.resetPositions();
                    timer.start();
                    view.requestFocusInWindow();
                    view.displayMessage("ProjectPacman/assets/Start.png");
                    view.clearMessage(1);
                }
            });
            deathTimer.setRepeats(false);
            deathTimer.start();
        }

    }

}