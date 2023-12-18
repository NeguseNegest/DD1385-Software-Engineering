package ProjectPacman;




public class PacPlayer extends Entity{
    private int score;
    private int lives=3;

    public  PacPlayer(){
        super();
    }

    public int[] getPosition(){
        int[] result = {getX(),getY()};
        return result;
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