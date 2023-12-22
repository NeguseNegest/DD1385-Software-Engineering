package ProjectPacman;

import java.util.ArrayList;
import java.util.List;

public class PacPlayer extends Entity{
    private int score;
    private int direction;
    private int lives=3;
    private List<ObserverOfPlayer> observers = new ArrayList<ObserverOfPlayer>();
    
    public PacPlayer(){
        super();
    }

    
    public void notifyPosition(){
        for (ObserverOfPlayer observer : observers) {
            observer.playerPositionChanged();
        }
    }
    
    @Override
	public void setX(int x) {
		this.x = x;
        notifyPosition();
	}

    @Override
	public void setY(int y) {
		this.y = y;
        notifyPosition();
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