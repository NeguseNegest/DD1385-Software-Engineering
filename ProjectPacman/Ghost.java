package ProjectPacman;


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
                
    }


    @Override
    public void playerPositionChanged(){
        //Sees where pacman is atm
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
