package ProjectPacman;

public abstract class Entity {
	protected int x;
	protected int y;
	protected int state = 1;


	public int[] getPosition(){
        int[] result = {getX(),getY()};
        return result;
    }
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setState(int state){
		this.state = state;
	}

	public int getState(){
		return state;
	}	
}
