package ProjectPacman;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class PacmanModel{

    private final int BoardSize=20;
    private int n=BoardSize/2;
    private final String food="●";
    private final String pacman="P";
    private final String interiorWall="#";
    private String[][] Board= new String[BoardSize][BoardSize];
    private PacPlayer pacmanEntity = new PacPlayer(); 



    public PacmanModel() {
        this.initBoard();
    }
    
    public void move(String direction){
        int x = pacmanEntity.getX();
        int y = pacmanEntity.getY();
        if(isValidMove(direction)){
            if(direction.equals("UP")){
                Board[x][y]=" ";
                Board[x-1][y]=pacman;
            }
            if(direction.equals("DOWN")){
                //Board[x+1][y]
                Board[x][y]=" ";
                Board[x+1][y]=pacman;

            }
            if(direction.equals("LEFT")){
                Board[x][y]=" ";
                Board[x][y-1]=pacman;
            }
            if(direction.equals("RIGHT")){
                Board[x][y]=" ";
                Board[x][y+1]=pacman;
            }
        }
    }

    public boolean isValidMove(String direction) {
        int x = pacmanEntity.getX();
        int y = pacmanEntity.getY();

        // check if the next move is valid based on the direction input
        if (direction.equals("UP") && !Board[x - 1][y].equals(interiorWall)) {
            return true;
        } else if (direction.equals("DOWN") && !Board[x + 1][y].equals(interiorWall)) {
            return true;
        } else if (direction.equals("LEFT") && !Board[x][y - 1].equals(interiorWall)) {
            return true;
        } else if (direction.equals("RIGHT") && !Board[x][y + 1].equals(interiorWall)) {
            return true;
        }
        return false;
    }


    public  void initBoard(){
        // Populate the board
        for(int i=0; i<BoardSize; i++){
            for(int j=0; j<BoardSize; j++){
                Board[i][j]=food;
            }
        }
        
        // Ghost spawning area
        for (int i = 0; i < 5; i++) {
            Board[n - 2 + i][n - 2] = interiorWall;
            Board[n - 2 + i][n + 2] = interiorWall;
            Board[n + 2][n - 2 + i] = interiorWall;
        }
        
        
        // Make Walls
        for(int j=0; j<BoardSize; j++){
            Board[j][0]=interiorWall;
            Board[0][j]=interiorWall;
            Board[BoardSize-1][j]=interiorWall;
            Board[j][BoardSize-1]=interiorWall;
        }
        
        // Pacman should be placed at the bottom or top corner at each start of the game. 
        Board[BoardSize-2][1]=pacman;
        pacmanEntity.setX(BoardSize-2);
        pacmanEntity.setY(1);
    }

}

class PacmanView{
    private PacmanModel model;
    public PacmanView(PacmanModel model){
        this.model = model;
    }

    public void update(){}

}

class PacmanController implements KeyListener, ActionListener{
    private PacmanModel model;
    private PacmanView view;
    private Timer timer;
    private int direction;

    PacmanController(PacmanModel model, PacmanView view){
        this.model = model;
        this.view = view;
        direction = 0;
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
 
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_RIGHT) {
            direction = 1;
        } else if (keyCode == KeyEvent.VK_UP) {
            direction = 2;
        }else if (keyCode == KeyEvent.VK_LEFT){
            direction = 3;
        }else if (keyCode == KeyEvent.VK_DOWN){
            direction = 4;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (direction != 0){
            if (direction == 1) {
                model.move("RIGHT");
            } else if (direction == 2) {
                model.move("UP");
            } else if (direction==3){
                model.move("LEFT");
            } else if (direction==4){
                model.move("DOWN");
            }
            view.update(); // Updates the view 
        }
    }
    
}



public class PacmanGame {
    public static void main(String[] args) {

    }
}
