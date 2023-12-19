package ProjectPacman;

import javax.swing.*;
import java.awt.*;


public class PacmanView extends JPanel{
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

