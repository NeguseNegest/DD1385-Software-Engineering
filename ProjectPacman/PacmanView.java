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
    private Timer messageTimer;
    private ImageIcon PacManRight,SmallFood,BigFood,RedGhost,PacManUp,PacManDown,PacManLeft;
    private JLabel message = new JLabel("", SwingConstants.CENTER);


    // private ImageIcon redGhostIcon = new ImageIcon("red_ghost_image.png");
    // private ImageIcon redGhostIcon = new ImageIcon(new ImageIcon("red_ghost_image.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

    public PacmanView(PacmanModel model){
        messageTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMessage();
            }
        });
        messageTimer.setRepeats(false); // Make sure the timer only fires once
        this.model = model;
        PacManRight = new ImageIcon("PacManRight.gif");
        SmallFood = new ImageIcon("SmallFood.png");
        BigFood = new ImageIcon("BigFood.png");
        RedGhost= new ImageIcon("RedGhost.png");
        PacManDown= new ImageIcon("down.gif");
        PacManLeft = new ImageIcon("left.gif");
        PacManUp= new ImageIcon("PacManUp.gif");
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
                }
            }
        }   
    }

    public void displayMessage(String filename){
        ImageIcon image = new ImageIcon(filename);
        message.setIcon(image);
        messageTimer.start();
    }
    
    public void clearMessage(){
        message.setText("");
        messageTimer.stop();
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
