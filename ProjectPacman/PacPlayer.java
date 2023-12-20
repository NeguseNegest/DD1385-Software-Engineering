package ProjectPacman;

import java.util.ArrayList;
import java.util.List;

public class PacPlayer extends Entity{
    private int score;
    private int lives=3;
    
    public PacPlayer(){
        super();
    }
    private List<Ghost> observers = new ArrayList<Ghost>();
    public void notifyPosition(){
        for (Ghost ghost : observers) {
            ghost.positionChanged();
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



}