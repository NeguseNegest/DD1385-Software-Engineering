package ProjectPacman;

public class PacmanModel{

    private final int boardSize = 20;
    private int n = boardSize/2;
    private final String food="●";
    private final String pacman="P";
    private final String interiorWall="#";
    private String[][] Board= new String[boardSize][boardSize];
    private PacPlayer pacmanEntity = new PacPlayer(); 
    
    public PacmanModel() {
        this.initBoard();
        pacmanEntity.setLives(3);
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