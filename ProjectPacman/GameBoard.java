package PacMan;
import java.util.Scanner;

public class GameBoard {
    private final int BoardSize=20;
    private int n=BoardSize/2;
    private final String emptySlot="●";
    private final String pacman="P";
    private final String interiorWall="#";
    private int Score=0;
    private int health =0;
    private String[][] Board= new String[BoardSize][BoardSize];
    private final String UP="w";
    private final String DOWN="s";
    private final String LEFT="a";
    private final String RIGHT="d";
    private String ghost="0";
    private boolean GameOver=false;


public  void initBoard(){
    //TODO- initialize the board and print it 

    //  I need to figure out how i should place the obstacles and the walls 

    // Populate the slots
    for(int i=0; i<BoardSize; i++){
        for(int j=0; j<BoardSize; j++){
            Board[i][j]=emptySlot;
        }
    }
    // Pacman should be placed at the bottom or top corner at each start of the game. 
    Board[BoardSize-2][1]=pacman;

    //------------------------------Spawnign place for ghosts-------------------------
    // The ghosts should be placed at the center where they have their spawning area.
    for (int i = 0; i < 5; i++) {
            Board[n - 2 + i][n - 2] = interiorWall;
            Board[n - 2 + i][n + 2] = interiorWall;
            Board[n + 2][n - 2 + i] = interiorWall;
        }
//------------End of spawning place for ghosts-----------

// Boundaries, i.e the edge walls.
for(int j=0; j<BoardSize; j++){
    Board[j][0]=interiorWall;
    Board[0][j]=interiorWall;
    Board[BoardSize-1][j]=interiorWall;
    Board[j][BoardSize-1]=interiorWall;

}



    for (int i = 0; i < BoardSize; i++) {
        for (int j = 0; j < BoardSize; j++) {
            System.out.print(Board[i][j] + " ");
        }
        System.out.println();
    }
}

private int[] getPacPosition(){

    for(int i=0;i<BoardSize;i++){
        for(int j=0;j<BoardSize;j++){
            if(Board[i][j].equals(pacman)){
                return new int[]{i, j};
            }
        }
    }
    return new int[]{-1, -1};
}






public void move(String direction){
    // I need to figure out how i should move the places 
    int [] PacPos =getPacPosition();
    int x=PacPos[0];
    int y=PacPos[1];
    if(isValidMove(direction)){
        if(direction.equals(UP)){
            //Board[x-1][y]
            Board[x][y]=" ";
            Board[x-1][y]=pacman;
            Score+=1;
        }
        if(direction.equals(DOWN)){
            //Board[x+1][y]
            Board[x][y]=" ";
            Board[x+1][y]=pacman;
            Score+=1;

        }
        if(direction.equals(LEFT)){
            //Board[x][y-1]
            Board[x][y]=" ";
            Board[x][y-1]=pacman;
            Score+=1;

        }
        if(direction.equals(RIGHT)){
            //Board[x][y+1]
            Board[x][y]=" ";
            Board[x][y+1]=pacman;
            Score+=1;

        }

    }
    
}

public boolean isValidMove(String direction) {
    int[] pacPos = getPacPosition();
    int x = pacPos[0];
    int y = pacPos[1];

    // check if the next move is valid based on the direction input
    if (direction.equals(UP) && !Board[x - 1][y].equals(interiorWall)) {
        return true;
    } else if (direction.equals(DOWN) && !Board[x + 1][y].equals(interiorWall)) {
        return true;
    } else if (direction.equals(LEFT) && !Board[x][y - 1].equals(interiorWall)) {
        return true;
    } else if (direction.equals(RIGHT) && !Board[x][y + 1].equals(interiorWall)) {
        return true;
    }
    return false;
}


public void getStatus() {
    for (int i = 0; i < BoardSize; i++) {
        for (int j = 0; j < BoardSize; j++) {
            System.out.print(Board[i][j] + " ");
        }
        System.out.println();
    }
    System.out.println("Score: " + getScore() + ", Health: " + getHealth());
}

public int getHealth(){
    return health;
}

public int getScore(){
    return Score;
}



public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    GameBoard gameBoard = new GameBoard();
    gameBoard.initBoard();

    String input;
    System.out.println("Enter a direction (w=up, s=down, a=left, d=right) or 'stop' to end:");
    while (!(input = scanner.nextLine()).equals("stop")) {
        if (input.matches("[wasd]")) {
            gameBoard.move(input);
            gameBoard.getScore(); 
            gameBoard.getStatus();
        } else {
            System.out.println("Invalid input, please enter one of 'w', 'a', 's', 'd' or 'stop'.");
        }
    }

    scanner.close();
    System.out.println("Game Over");
}

}
