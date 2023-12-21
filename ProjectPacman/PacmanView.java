package ProjectPacman;

import javax.swing.*;
import java.awt.*;

public class PacmanView extends JPanel{
    private PacmanModel model;
    public JButton resetButton= new JButton("Reset");
    public JButton scoreDisplay=new JButton("Score");

    public PacmanView(PacmanModel model){
        this.model = model;
    }

    public void displayInTerminal(){
        int boardWidth = model.getBoardWidth();
        int boardHeight = model.getBoardHeight();
        for(int i=0; i<boardHeight; i++){
            for(int j=0; j<boardWidth; j++)
                System.out.print("  " + model.getStatus(i,j));
            System.out.println();
        }
    }
    
    public void initGUI(){
        JFrame frame = new JFrame("Game");
        
        frame.setSize(570,650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        
        setLayout(new BorderLayout());
        add(resetButton, BorderLayout.NORTH);
        add(scoreDisplay, BorderLayout.SOUTH);
        
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int cellSize = 20;
        int boardWidth = model.getBoardWidth();
        int boardHeight = model.getBoardHeight();
        for (int i= 0; i<boardHeight; i++){
            for (int j=0; j<boardWidth; j++){
                String status = model.getStatus(i, j);
                if (status.equals("P")){
                    g.setColor(Color.YELLOW);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals("#")){
                    g.setColor(Color.BLACK);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals("-")){
                    g.setColor(Color.BLUE);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals("d")){
                    g.setColor(new Color(139, 69, 19));
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals("R")){
                    g.setColor(Color.RED);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals(".")){
                    g.setColor(Color.WHITE);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    public void update(){
        displayInTerminal();
        System.out.flush();
        this.repaint();
    }
}

