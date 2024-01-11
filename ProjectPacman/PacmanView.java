package ProjectPacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacmanView extends JPanel{
    private PacmanModel model;
    public JButton startButton = new JButton("Start");
    public JButton resetButton= new JButton("Reset");

    
    private ImageIcon PacManRight,SmallFood,BigFood,RedGhost,PacManUp,PacManDown,PacManLeft, ScaredGhost, BlueGhost, YellowGhost, PinkGhost,Life, Hearts;
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
        BlueGhost = new ImageIcon(classLoader.getResource("ProjectPacman/assets/Inky.png"));
        YellowGhost= new ImageIcon(classLoader.getResource("ProjectPacman/assets/YelloClyde.png"));
        PinkGhost = new ImageIcon(classLoader.getResource("ProjectPacman/assets/Pinky.png"));
       // Life= new ImageIcon()
        
        ScaredGhost = new ImageIcon(classLoader.getResource("ProjectPacman/assets/ScaredGhost.gif"));
        Hearts = new ImageIcon(classLoader.getResource("ProjectPacman/assets/heart.png"));
        this.model = model;
        this.setBackground(Color.BLACK);
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
                    g.setColor(Color.BLUE);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals("-")){
                   SmallFood.paintIcon(this, g, j * cellSize, i * cellSize);
                } else if (status.equals("d")){
                    g.setColor(new Color(139, 69, 19));
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals("R")){
                    BigFood.paintIcon(this, g, j * cellSize, i * cellSize);

                } else if (status.equals(".")){
                    g.setColor(Color.BLACK);
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                } else if (status.equals("RedGhost")){
                   RedGhost.paintIcon(this, g, j * cellSize, i * cellSize);
                } else if (status.equals("ScaredGhost")){
                    ScaredGhost.paintIcon(this, g, j*cellSize, i*cellSize);
                } else if (status.equals("BlueGhost")){
                    BlueGhost.paintIcon(this, g, j * cellSize, i * cellSize);
                }
                else if (status.equals("YellowGhost")){
                    YellowGhost.paintIcon(this, g, j * cellSize, i * cellSize);
                }
                else if(status.equals("PinkGhost")){
                    PinkGhost.paintIcon(this, g, j * cellSize, i * cellSize);
                }
    
            }
        }
        
        g.setColor(Color.YELLOW);
        Font font = new Font("Arial", Font.BOLD, 12);
        g.setFont(font);
        g.drawString("Lives", (boardWidth + 4) * cellSize, 15);
        g.drawString("Score:", (boardWidth + 1) * cellSize, 15);

    String predefinedText = String.valueOf(model.getModelScore());; // Replace with your actual text
     g.drawString(predefinedText, (boardWidth + 1) * cellSize, 35);
        int lives = model.getPlayerLives();
        for (int i=0; i<lives; i++){
            g.setColor(Color.YELLOW);
            //g.fillRect((boardWidth+5)*cellSize, (i+1)*(cellSize), 10, 10);
            Hearts.paintIcon(this, g,(boardWidth+5)*cellSize, (i+1)*(cellSize));
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
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
    
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(resetButton);
        // buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.EAST);
    
        // Add message label to the center
        add(message, BorderLayout.CENTER);
    
        frame.add(this);
        frame.setVisible(true);
    }
}
