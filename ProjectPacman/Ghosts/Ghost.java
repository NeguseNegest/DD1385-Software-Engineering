package ProjectPacman.Ghosts;

import ProjectPacman.Entity;
import ProjectPacman.ObserverOfPlayer;


public class Ghost extends Entity implements ObserverOfPlayer{
    private final int cX=13;
    private final int cY=14;
    public Ghost(){
        super();
        
    }

    public void SpawnAtCenter(){
        if(Eaten()){
            setX(cX);
            setY(cY);
        }
        return;
    }

    public boolean Eaten(){
        return true;
                
    }



    @Override
    public void playerPositionChanged(int x,int y){
        // pathfinder.updatePath();
        // newDirection = pathfinder.getNextMove();
        // setDirection(newDirection);
    }


    public void setDirection(int direction){
        this.direction = direction;
    }
    public int getDirection(){
        return direction;
    }

    public void PanicState(){
    }

    public void chasePac(){}


    public void getPacPos(){

    }

    @Override
    public void playerPoweredUp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playerPoweredUp'");
    }

}
