//game logic of fifteenmodel

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FifteenModel implements Boardgame {
    String boardArray[][] = new String[4][4]; // 4x4 grid

    private void initBoardarray() {
        int count = 1;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (count <= 15) {
                    boardArray[row][col] = Integer.toString(count);
                    count += 1;
                } else {
                    boardArray[row][col] = " ";
                }
            }
        }
    }

    private void shuffleBoard() {
        List<String> boardlist = new ArrayList<>();
        for (String[] row : boardArray) {
            boardlist.addAll(Arrays.asList(row));
        }
        Collections.shuffle(boardlist, new Random());
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                boardArray[row][col] = boardlist.remove(0);
            }
        }
    }

    public void display_board() {
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray.length; j++) {
                System.out.print(boardArray[i][j] + " ");
            }
            System.out.println();
        }
    }


    public String getStatus(int i, int j){
        if (i<=3 && j<=3 && 0<=i && 0<=j){
            return boardArray[i][j];
        }
        return "0";
    }


    public int[] empty_slot() {
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (boardArray[row][column].equals(" ")) {
                    return new int[] { row, column };
                }
            }
        }
        return new int[] { -1, -1 }; 
    }

    void swapTecken(int i, int j,int i_other,int j_other){
        String temp = boardArray[i][j];
        boardArray[i][j] = boardArray[i_other][j_other];
        boardArray[i_other][j_other] = temp;
    }
    private boolean move_sucess;
    public boolean move(int i, int j) {
        int[] slotidx = empty_slot();
        int row_slotidx = slotidx[0];
        int col_slotidx = slotidx[1];
        
        // RIGHT neighbor
        if (row_slotidx== i && col_slotidx==j+1){
            move_sucess = true;
            swapTecken(i, j, row_slotidx, col_slotidx);
            return true;
        } 
        // LEFT neighbor
        if (row_slotidx == i && col_slotidx == j-1){
            move_sucess = true;
            swapTecken(i, j, row_slotidx, col_slotidx);

            return true;
        }
        // UP neighbor
        if ( row_slotidx==i-1 && col_slotidx==j){
            move_sucess = true;
            swapTecken(i, j, row_slotidx, col_slotidx);

            return true;
        }
        // DOWN neighbor
        if (row_slotidx==i+1 && col_slotidx==j){
            move_sucess = true;
             swapTecken(i, j, row_slotidx, col_slotidx);

            return true;
        }
        move_sucess = false;
        return false;
    }
    public String getMessage(){
    if (move_sucess){
        return "OK";
    }
    else{
            return "You need to make a valid move";
        }
        
    }

    FifteenModel() {
        initBoardarray();
        shuffleBoard();
    }

    public static void main(String[] args) {
        FifteenModel A = new FifteenModel();
        System.out.println(A.getStatus(3, 4));
        A.display_board();
        int[] emptySlot = A.empty_slot();
        System.out.println("Empty Slot: [" + emptySlot[0] + ", " + emptySlot[1] + "]");
    }
}

// interface Boardgame {
// }
