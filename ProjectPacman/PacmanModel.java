package ProjectPacman;


import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import ProjectPacman.Ghosts.Ghost;

public class PacmanModel {

    private final String interiorWall = "#";
    private final String food = "-";
    private final String emptyTile = ".";
    private final String door = "d";
    private final String powerUp = "R";

    private boolean[][] foodWasEaten;
    private boolean[][] powerUpExists;
    private String[][] board;
    private int boardWidth; // Array length
    private int boardHeight; // Number of arrays
    private PacPlayer pacmanEntity;
    private Ghost ghost;

    private List<Ghost> ghostList = new ArrayList<Ghost>(); 
    private int beanAmount;

    public PacmanModel(PacPlayer pacmanEntity) {
        this.pacmanEntity = pacmanEntity;
        this.pacmanEntity.setLives(3);
        this.pacmanEntity.setDirection(0);

        ghost = new Ghost(this);
        ghost.setDirection(0);
        
        pacmanEntity.addObserver(ghost);
        this.initBoard();
    }

    public void resetGame(){
        this.pacmanEntity.setLives(3);
        this.pacmanEntity.setDirection(0);
        this.pacmanEntity.setScore(0);
        this.initBoard();
    }

    public void movePacman() {
        String direction="none";
        int intDirection = pacmanEntity.getDirection();
        if (intDirection != 0){
            if (intDirection == 1) {
                direction = "RIGHT";
            } else if (intDirection == 2) {
                direction = "UP";
            } else if (intDirection==3){
                direction = "LEFT";
            } else if (intDirection==4){
                direction = "DOWN";
            }

        int x = pacmanEntity.getX();
        int y = pacmanEntity.getY();
        if (x == 14 && y == 0 && direction.equals("LEFT")) { // Check if Pac-Man is at the left tunnel entrance and if so run
            movePacmanTo(14, 27,"PL");
            return;
        }
        if (x == 14 && y == 27 && direction.equals("RIGHT")) { // Check if Pac-Man is at the right tunnel entrance and if so continue 
            movePacmanTo(14, 0,"P");
            return;
        }

        if (!wallCollision(x,y,direction)) {
            pacmanEntity.setCurrentDirection(direction);; // Update current direction if no collision
        }

        switch (pacmanEntity.getCurrentDirection()) {
            case "UP":
                if (!wallCollision(x,y,"UP")) {
                    movePacmanTo(x - 1, y,"PU");
                    pacmanEntity.setSymbol("PU");
                }
                break;
            case "DOWN":
                if (!wallCollision(x,y,"DOWN")) {
                    movePacmanTo(x + 1, y,"PD");
                    pacmanEntity.setSymbol("PD");
                }
                break;
            case "LEFT":
                if (!wallCollision(x,y,"LEFT")) {
                    movePacmanTo(x, y - 1,"PL");
                    pacmanEntity.setSymbol("PL");
                }
                break;
            case "RIGHT":
                if (!wallCollision(x,y,"RIGHT")) {
                    movePacmanTo(x, y + 1,"P");
                    pacmanEntity.setSymbol("P");
                }
                break;
        }

        
    }
    }

    private void movePacmanTo(int newX, int newY,String pacman) {
        int x = pacmanEntity.getX();
        int y = pacmanEntity.getY();
        board[x][y] = emptyTile; // Clear the old position
    
        if (board[newX][newY].equals(food)) {
            foodWasEaten[newX][newY] = true;
            pacmanEntity.setScore(pacmanEntity.getScore() + 1); 
        }
    
        if (board[newX][newY].equals(powerUp)) {
            // pacmanEntity.setScore(pacmanEntity.getScore() + 5); 
            powerUpExists[newX][newY] = false;
            pacmanEntity.notifyPowerUp();
        }
    
        // board[newX][newY] = pacman; // Move to the new position
    
        pacmanEntity.setX(newX);
        pacmanEntity.setY(newY);
    
        if (Math.abs(pacmanEntity.getX() - ghost.getX()) < 7 && Math.abs(pacmanEntity.getY() - ghost.getY()) < 7) {
            if (!ghost.isPanic()){
                pacmanEntity.notifyPosition();
            }
        }
    }
    


    public void moveGhosts(){
        int x = ghost.getX();
        int y = ghost.getY();
        String direction="none";
        int intDirection = ghost.getDirection(); 
        if (intDirection != 0){
            if (intDirection == 1) {
                direction = "RIGHT";
            } else if (intDirection == 2) {
                direction = "UP";
            } else if (intDirection==3){
                direction = "LEFT";
            } else if (intDirection==4){
                direction = "DOWN";
            }
        if (x == 14 && y == 0 && direction.equals("LEFT")) { // Check if Pac-Man is at the left tunnel entrance and if so run
            // board[14][27] = "RedGhost";
            ghost.setY(27);
            return;
        }
        if (x == 14 && y == 28 - 1 && direction.equals("RIGHT")) { // Check if Pac-Man is at the right tunnel entrance and if so continue 

            // board[14][0] = "RedGhost";
            ghost.setY(0);
            return;
        }

        if (!wallCollision(x,y,direction)) {
            ghost.setCurrentDirection(direction);
        }

        switch (ghost.getCurrentDirection()) {
            case "UP":
                if (!wallCollision(x,y,"UP")) {
                    // board[x][y] = "-";
                    // if (foodWasEaten[x][y]){
                    //     board[x][y] = ".";
                    // }else if (powerUpExists[x][y]){
                    //     board[x][y] = "R";
                    // }
                    // board[x-1][y] = "RedGhost";
                    ghost.setX(x-1);
                }
                break;
            case "DOWN":
                if (!wallCollision(x,y,"DOWN")) {
                    // board[x][y] = "-";
                    // if (foodWasEaten[x][y]){
                    //     board[x][y] = ".";
                    // }else if (powerUpExists[x][y]){
                    //     board[x][y] = "R";
                    // }
                    // board[x+1][y] = "RedGhost";
                    ghost.setX(x+1);
                }
                break;
            case "LEFT":
                if (!wallCollision(x,y,"LEFT")) {
                    // board[x][y] = "-";
                    // if (foodWasEaten[x][y]){
                    //     board[x][y] = ".";
                    // }else if (powerUpExists[x][y]){
                    //     board[x][y] = "R";
                    // }
                    // board[x][y-1] = "RedGhost";
                    ghost.setY(y-1);
                }
                break;
            case "RIGHT":
                if (!wallCollision(x,y,"RIGHT")) {
                    // board[x][y] = "-";
                    // if (foodWasEaten[x][y]){
                    //     board[x][y] = ".";
                    // }else if (powerUpExists[x][y]){
                    //     board[x][y] = "R";
                    // }
                    // board[x][y+1] = "RedGhost";
                    ghost.setY(y+1);
                }
                break;
        }
    }
    ghost.setDirection(ghost.getNextMoveDirection());


    }

    public boolean wallCollision(int x, int y, String direction) {
        // int x = pacmanEntity.getX();
        // int y = pacmanEntity.getY();

        // Check if the next move is valid based on the direction input
        if (direction.equals("UP") && !board[x - 1][y].equals(interiorWall) && !board[x - 1][y].equals(door)) {
            return false;
        } else if (direction.equals("DOWN") && !board[x + 1][y].equals(interiorWall) && !board[x + 1][y].equals(door)) {
            return false;
        } else if (direction.equals("LEFT") && !board[x][y - 1].equals(interiorWall) && !board[x][y - 1].equals(door)) {
            return false;
        } else if (direction.equals("RIGHT") && !board[x][y + 1].equals(interiorWall) && !board[x][y + 1].equals(door)) {
            return false;
        }
        return true;
    }

    public void resetPositions(){
        pacmanEntity.setX(boardWidth-2);
        pacmanEntity.setY(1);
        pacmanEntity.setDirection(0);

        ghost.setX(1);
        ghost.setY(1);
    }
    
    public boolean checkWinCondition(){
        // if pacman score equal to amount of beans
        // Count how many beans in board i.e "-" symbol
        // returns true if pacMan has eaten all beans buddy. 
        if(pacmanEntity.getScore()==((beanAmount))){
            return true;
        }
        return false;
    }

    public boolean checkGhostPlayerCollision(){
        if ((ghost.getX()==pacmanEntity.getX()) && (ghost.getY()==pacmanEntity.getY())){
            if (ghost.isPanic()){
                ghost.SpawnAtCenter();
            } else{
                pacmanEntity.setLives(pacmanEntity.getLives()-1);
                resetPositions();
            }
            return true;
        }
        return false;
    }

    public boolean checkLossCondition(){
        if (pacmanEntity.getLives()==0){
            return true;
        }
        return false;
    }

    public String getStatus(int i, int j) {
        if (ghost.getX()==i && ghost.getY()==j){
            return ghost.getSymbol();
        }
        if (pacmanEntity.getX()==i && pacmanEntity.getY()==j){
            return pacmanEntity.getSymbol();
        }
        return board[i][j];
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public String[][] getBoard(){
        return board;
    }

  public  void initBoard(){    
        board = new String[][] {
        // "#" = wall, "-" = Pellet, "." = empty, "d" = door， "R" ＝ Power up
        {"#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#"},
        {"#","-","-","-","-","-","-","-","-","-","-","-","-","#","#","-","-","-","-","-","-","-","-","-","-","-","-","#"},
        {"#","-","#","#","#","#","-","#","#","#","#","#","-","#","#","-","#","#","#","#","#","-","#","#","#","#","-","#"},
        {"#","R","#","#","#","#","-","#","#","#","#","#","-","#","#","-","#","#","#","#","#","-","#","#","#","#","R","#"},
        {"#","-","#","#","#","#","-","#","#","#","#","#","-","#","#","-","#","#","#","#","#","-","#","#","#","#","-","#"},
        {"#","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","#"},
        {"#","-","#","#","#","#","-","#","#","-","#","#","#","#","#","#","#","#","-","#","#","-","#","#","#","#","-","#"},
        {"#","-","#","#","#","#","-","#","#","-","#","#","#","#","#","#","#","#","-","#","#","-","#","#","#","#","-","#"},
        {"#","-","-","-","-","-","-","#","#","-","-","-","-","#","#","-","-","-","-","#","#","-","-","-","-","-","-","#"},
        {"#","#","#","#","#","#","-","#","#","#","#","#",".","#","#",".","#","#","#","#","#","-","#","#","#","#","#","#"},
        {"#","#","#","#","#","#","-","#","#","#","#","#",".","#","#",".","#","#","#","#","#","-","#","#","#","#","#","#"},
        {"#","#","#","#","#","#","-","#","#",".",".",".",".",".",".",".",".",".",".","#","#","-","#","#","#","#","#","#"},
        {"#","#","#","#","#","#","-","#","#",".","#","#","#",".",".","#","#","#",".","#","#","-","#","#","#","#","#","#"},
        {"#","#","#","#","#","#","-","#","#",".","#",".",".",".",".",".",".","#",".","#","#","-","#","#","#","#","#","#"},
        {".",".",".",".",".",".","-",".",".",".","#",".",".",".",".",".",".","#",".",".",".","-",".",".",".",".",".","."},
        {"#","#","#","#","#","#","-","#","#",".","#",".",".",".",".",".",".","#",".","#","#","-","#","#","#","#","#","#"},
        {"#","#","#","#","#","#","-","#","#",".","#","#","#","#","#","#","#","#",".","#","#","-","#","#","#","#","#","#"},
        {"#","#","#","#","#","#","-","#","#",".",".",".",".",".",".",".",".",".",".","#","#","-","#","#","#","#","#","#"},
        {"#","#","#","#","#","#","-","#","#",".","#","#","#","#","#","#","#","#",".","#","#","-","#","#","#","#","#","#"},
        {"#","#","#","#","#","#","-","#","#",".","#","#","#","#","#","#","#","#",".","#","#","-","#","#","#","#","#","#"},
        {"#","-","-","-","-","-","-","-","-","-","-","-","-","#","#","-","-","-","-","-","-","-","-","-","-","-","-","#"},
        {"#","-","#","#","#","#","-","#","#","#","#","#","-","#","#","-","#","#","#","#","#","-","#","#","#","#","-","#"},
        {"#","-","#","#","#","#","-","#","#","#","#","#","-","#","#","-","#","#","#","#","#","-","#","#","#","#","-","#"},
        {"#","R","-","-","#","#","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","#","#","-","-","R","#"},
        {"#","#","#","-","#","#","-","#","#","-","#","#","#","#","#","#","#","#","-","#","#","-","#","#","-","#","#","#"},
        {"#","#","#","-","#","#","-","#","#","-","#","#","#","#","#","#","#","#","-","#","#","-","#","#","-","#","#","#"},
        {"#","-","-","-","-","-","-","#","#","-","-","-","-","#","#","-","-","-","-","#","#","-","-","-","-","-","-","#"},
        {"#","-","#","#","#","#","#","#","#","#","#","#","-","#","#","-","#","#","#","#","#","#","#","#","#","#","-","#"},
        {"#","-","#","#","#","#","#","#","#","#","#","#","-","#","#","-","#","#","#","#","#","#","#","#","#","#","-","#"},
        {"#","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","#"},
        {"#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#"}};
        

        
        boardWidth = board[0].length;
        boardHeight = board.length;
        
        foodWasEaten = new boolean[boardHeight][boardWidth];
        powerUpExists = new boolean[boardHeight][boardWidth];

        beanAmount=-1;
        for (int i=0;i<boardHeight; i++){
            for (int j = 0; j<boardWidth; j++){
                if (board[i][j].equals("R")){
                    powerUpExists[i][j] = true;
                }
                foodWasEaten[i][j] = false;

                if (board[i][j].equals("-")){
                    beanAmount+=1;
                }
            }
        }

        // Pacman should be placed at the bottom or top corner at each start of the game. 
        // board[boardWidth-2][1]=pacman;
        // board[1][1] = "RedGhost";
        // movePacmanTo(boardWidth-2, 1);
        pacmanEntity.setX(boardWidth-2);
        pacmanEntity.setY(1);
        pacmanEntity.setSymbol("P");

        ghost.setX(1);
        ghost.setY(1);
        ghost.setSymbol("RedGhost");
    }}
