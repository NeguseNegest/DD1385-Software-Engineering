public class TicTacToe implements Boardgame {

    private String[][] boardArray = new String[3][3];
    private boolean valid_move; // senaste dragets validitet
    private boolean user_turn = false;

    public TicTacToe() {
        fill_squares_empty(); // skapa vårt bord
    }
    
    private void fill_squares_empty() {
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[i].length; j++) {
                boardArray[i][j] = " ";
            }
        }
    }
   
    private boolean _valid_move(int i, int j) {
        return boardArray[i][j].equals(" "); 
    }

    public boolean user_turn() {
        user_turn = !user_turn;
        return user_turn;
    }
    
    public void swap_signs(int i, int j) {
        boardArray[i][j] = user_turn ? "X" : "O";
        user_turn(); 
    }

    public boolean move(int i, int j) {
        if (_valid_move(i, j)) {
            swap_signs(i, j);
            valid_move = true;
            getWinner(i,j);
        } else {
            valid_move = false;
        }
        return valid_move;
    }

    public String getWinner(int i, int j) {
        String current_type = boardArray[i][j];
        if (current_type.equals(boardArray[i][0]) && current_type.equals(boardArray[i][1]) && current_type.equals(boardArray[i][2])) {
            return current_type + " is the winner";
        } else if (current_type.equals(boardArray[0][j]) && current_type.equals(boardArray[1][j]) && current_type.equals(boardArray[2][j])) {
            return current_type + " is the winner";
        } else if ((current_type.equals(boardArray[0][0]) && current_type.equals(boardArray[1][1]) && current_type.equals(boardArray[2][2])) ||
                   (current_type.equals(boardArray[0][2]) && current_type.equals(boardArray[1][1]) && current_type.equals(boardArray[2][0]))) {
            return current_type + " is the winner";
        }
        return "No winner yet";
    }


    public boolean check_if_full(){
        int count=1;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if( boardArray[i][j].equals("X") || boardArray[i][j].equals("O")){
                    count=count+1;
                }    
            }
        }
         if (count==9){
            return true;
        }
        return false;
    }

    public String getStatus(int i, int j) {
        return boardArray[i][j];
    }

    public String getMessage() {
        return valid_move ? "Move accepted." : "Invalid move, try again.";
    }
}
