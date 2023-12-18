package ProjectPacman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;


public class PacPlayer extends Entity {
    private int lives=3;
    private int score;
    private int state_temp=1;

    public  PacPlayer(int x, int y, int state){
        super();
        this.x=x;
        this.y=y;
        this.state=state;
        
    }

    public int getScore(){
        return score;
    }

    public void changeLives(int lives){
        this.lives=lives;
    }

    public void changeScore(int score){
        this.score=score;
    }

    // Now comes the logic for movement.

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    state_temp = 4; // Down key pressed
                    break;
                case KeyEvent.VK_LEFT:
                    state_temp = 1; // Left key pressed
                    break;
                case KeyEvent.VK_UP:
                    state_temp = 2; // Up key pressed
                    break;
                case KeyEvent.VK_RIGHT:
                    state_temp = 3; // Right key pressed
                    break;
            }

            turnFace(); //  turnFace to update the state based on key press
        }
    }
private void turnFace(){

    switch(state_temp){

        case 1:
        if(x!=0 && Maze.Maze[y][x-1]!=0){
            state=1;
        }else if (x==0){
            state=1;
            break;
        }
        case 2:
        if( Maze.Maze[y-1][x]!=0){
            state=2;
            break;
        }
        case 3:
        if (x!=27){
            if(Maze.Maze[y][x+1]!=0)
                state = 3;
        }else
            state = 3; 
        break;
        case 4:
			if(Maze.Maze[y+1][x]!=0 && Maze.Maze[y+1][x]!=3)
				state = 4;
			break;
		}

    }


}


