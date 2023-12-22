package ProjectPacman;

public class Ghost extends Entity implements ObserverOfPlayer{
    
    private int direction;
    
    private boolean panicState;
    
    public Ghost() {
        super();
    }
    
    public void setDirection(int direction){
        this.direction = direction;
    }
    public int getDirection(){
        return direction;
    }

    @Override
    public void playerPositionChanged(){}
}
