package ProjectPacman;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class PacmanModel{

    private final int boardSize = 20;
    private int n = boardSize/2;
    private final String food="●";
    private final String pacman="P";
    private final String interiorWall="#";
    private String[][] Board= new String[boardSize][boardSize];
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
                pacmanEntity.setX(x-1);
            }
            if(direction.equals("DOWN")){
                Board[x][y]=" ";
                Board[x+1][y]=pacman;
                pacmanEntity.setX(x+1);
            }
            if(direction.equals("LEFT")){
                Board[x][y]=" ";
                Board[x][y-1]=pacman;
                pacmanEntity.setY(y-1);
            }
            if(direction.equals("RIGHT")){
                Board[x][y]=" ";
                Board[x][y+1]=pacman;
                pacmanEntity.setY(y+1);
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
        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
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
        for(int j=0; j<boardSize; j++){
            Board[j][0]=interiorWall;
            Board[0][j]=interiorWall;
            Board[boardSize-1][j]=interiorWall;
            Board[j][boardSize-1]=interiorWall;
        }
        
        // Pacman should be placed at the bottom or top corner at each start of the game. 
        Board[boardSize-2][1]=pacman;
        pacmanEntity.setX(boardSize-2);
        pacmanEntity.setY(1);
    }
    public String getStatus(int i, int j){
        String result = Board[i][j];
        return result;
    }

    public int getboardSize(){
        return boardSize;
    }
}

class PacmanView extends JPanel{
    private PacmanModel model;
    public PacmanView(PacmanModel model){
        this.model = model;
    }

    public void displayInTerminal(){
        int boardSize = model.getboardSize();
        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++)
                System.out.print("  " + model.getStatus(i,j));
            System.out.println();
        }
    }
    
    public void show(){
        JFrame frame = new JFrame("Game");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int cellSize = 20;
        int boardSize = model.getboardSize();
        for (int i= 0; i<boardSize; i++){
            for (int j=0; j<boardSize; j++){
                String status = model.getStatus(i, j);
                if (status.equals("P")){
                    g.setColor(Color.YELLOW);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    // g.fillArc(j * cellSize, i * cellSize, cellSize, cellSize, 45, 270);
                } else if (status.equals("#")){
                    g.setColor(Color.BLACK);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else{
                    g.setColor(Color.BLUE);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    public void update(){
        displayInTerminal();
        System.out.flush();
    }
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
        timer = new Timer(80, this);
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
        }
        // view.update(); // Updates the view 
        view.repaint();
        // view.update();
    }
    
}



public class PacmanGame {
    public static void main(String[] args) {
        PacmanModel model = new PacmanModel();
        PacmanView view = new PacmanView(model);
        PacmanController controller = new PacmanController(model, view);
        view.addKeyListener(controller);
        view.show();

    }
}
