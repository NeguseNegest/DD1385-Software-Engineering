package project.pacman;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacPlayer extends Entity {
    private int score = 0;
    private int direction;
    private String currentDirection;
    private String symbol;
    private int lives = 3;
    private List<ObserverOfPlayer> observers = new ArrayList<ObserverOfPlayer>();
    private Timer notificationCoolDown;
    private boolean Ate;

    public PacPlayer() {
        super();
        notificationCoolDown = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notificationCoolDown.stop();
            }
        });
    }

    public void addObserver(ObserverOfPlayer observer) {
        this.observers.add(observer);
    }

    public void notifyPosition() {
        if (!notificationCoolDown.isRunning()) {
            notificationCoolDown.start();
        }
        for (ObserverOfPlayer observer : observers) {
            observer.playerPositionChanged(x, y);
        }
    }

    public void notifyPowerUp() {
        for (ObserverOfPlayer observer : observers) {
            observer.playerPoweredUp();
        }
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public void setScore(int score) {
        this.score = score;

    }

    public int getScore() {

        return this.score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return this.lives;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void setCurrentDirection(String currentDirection) {
        this.currentDirection = currentDirection;
    }

    public String getCurrentDirection() {
        return (currentDirection != null) ? currentDirection
                : new String[] { "none", "RIGHT", "UP", "LEFT", "DOWN" }[getDirection()];
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    private boolean isDead = false;

    public void dies() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public void revive() {
        isDead = false;
    }

}
