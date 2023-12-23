package ProjectPacman;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacPlayer extends Entity{
    private int score;
    private int direction;
    private int lives=3;
    private List<ObserverOfPlayer> observers = new ArrayList<ObserverOfPlayer>();
    private Timer notificationCoolDown; 
    public PacPlayer(){
        super();
        notificationCoolDown = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notificationCoolDown.stop();
            }
        });
    }

    public void addObserver(ObserverOfPlayer observer){
        this.observers.add(observer);
    }
    public void notifyPosition(){
        notificationCoolDown.start();
        for (ObserverOfPlayer observer : observers) {
            observer.playerPositionChanged(x,y);
        }
    }

    public void notifyPowerUp(){
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
    

    
    public void setScore(int score){
        this.score=score;
    }

    public int getScore(){
        return this.score;
    }

    public void setLives(int lives){
        this.lives=lives;
    }

    public int getLives(){
        return this.lives;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }
    public int getDirection(){
        return direction;
    }


}