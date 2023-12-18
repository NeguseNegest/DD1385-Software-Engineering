package ProjectPacman;

public abstract class Entity {
	protected int x;
	protected int y;
	protected int state = 1;
	protected boolean panick;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setState(int state){
		this.state = state;
	}

	public int getState(){
		return state;
	}

	public void getEmotion(){
		this.panick=panick;
		
	}

	public boolean bePanick(){
		return panick=true;
	}
	
}
