package ProjectPacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacmanView extends JPanel{
    private PacmanModel model;
   //private JLabel message = new JLabel("");
    public JButton startButton = new JButton("Start");
    public JButton resetButton= new JButton("Reset");
    
    private ImageIcon PacManRight,SmallFood,BigFood,RedGhost,PacManUp,PacManDown,PacManLeft, ScaredGhost;
    private JLabel message = new JLabel("", SwingConstants.CENTER);


    
    private ClassLoader classLoader = getClass().getClassLoader();
    public PacmanView(PacmanModel model){

        PacManRight = new ImageIcon(classLoader.getResource("ProjectPacman/assets/PacManRight.gif"));
        PacManUp = new ImageIcon(classLoader.getResource("ProjectPacman/assets/PacManUp.gif"));
        PacManDown = new ImageIcon(classLoader.getResource("ProjectPacman/assets/down.gif"));
        PacManLeft = new ImageIcon(classLoader.getResource("ProjectPacman/assets/left.gif"));
        SmallFood = new ImageIcon(classLoader.getResource("ProjectPacman/assets/SmallFood.png"));
        BigFood = new ImageIcon(classLoader.getResource("ProjectPacman/assets/BigFood.png"));
        RedGhost = new ImageIcon(classLoader.getResource("ProjectPacman/assets/RedGhost.png"));
        ScaredGhost = new ImageIcon(classLoader.getResource("ProjectPacman/assets/ScaredGhost.gif"));
        this.model = model;
        this.setBackground(Color.BLUE);
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
                    PacManRight.paintIcon(this, g, j * cellSize, i * cellSize);
                } 
                else if (status.equals("PD")){
                    PacManDown.paintIcon(this, g, j * cellSize, i * cellSize);
                }
                else if (status.equals("PL")){
                    PacManLeft.paintIcon(this, g, j * cellSize, i * cellSize);

                }
                else if (status.equals("PU")){
                    PacManUp.paintIcon(this, g, j * cellSize, i * cellSize);

                }
                else if (status.equals("#")){
                    g.setColor(Color.BLACK);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals("-")){
                   SmallFood.paintIcon(this, g, j * cellSize, i * cellSize);
                } else if (status.equals("d")){
                    g.setColor(new Color(139, 69, 19));
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals("R")){
                    BigFood.paintIcon(this, g, j * cellSize, i * cellSize);

                } else if (status.equals(".")){
                    g.setColor(Color.BLUE);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals("RedGhost")){
                    
                   RedGhost.paintIcon(this, g, j * cellSize, i * cellSize);
                    // redGhostIcon.paintIcon(this, g, j * cellSize, i * cellSize);
                } else if (status.equals("ScaredGhost")){
                    ScaredGhost.paintIcon(this, g, j*cellSize, i*cellSize);
                }
            }
        }   
    }

    public void displayMessage(String filename){
        ImageIcon image = new ImageIcon(classLoader.getResource(filename));
        message.setIcon(image);
    }
    
    public void clearMessage(){
        message.setIcon(null);
    }
    public void clearMessage(int seconds){
        Timer messageTimer;
        messageTimer = new Timer(seconds*1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMessage();
            }
        });
        messageTimer.setRepeats(false); // Make sure the timer only fires once
        messageTimer.start();
    }

    public void update(){
        // displayInTerminal();
        // System.out.flush();
        this.repaint();
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
    public void initGUI() {
        JFrame frame = new JFrame("Game");
        frame.setSize(670, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
    
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(resetButton);
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.EAST);
    
        // Add message label to the center
        add(message, BorderLayout.CENTER);
    
        frame.add(this);
        frame.setVisible(true);
        frame.setSize(1000, 1000);
    }
    
}
