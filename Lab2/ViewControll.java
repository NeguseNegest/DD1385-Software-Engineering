//ViewControll for fifteengame


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;

// lämpliga import-satser här
class ViewControl extends JFrame implements ActionListener {

    private Boardgame game;
    private int size;
    private Square[][] buttons;        // Square är subklass till JButton
    private JTextField message = new JTextField();  // JLabel funkar också

    public ViewControl (Boardgame gm, int n){  // OK med fler parametrar om ni vill!
         this.game=gm;
         this.size=n;
         JFrame frame=new JFrame("Femtonspell goofy");
         frame.setLayout(new GridLayout (n,n));

         buttons = new Square[n][n];
         for(int x=0; x<n;x++){
          for(int y=0;y<n;y++){
            String label=game.getStatus(x,y);
            buttons[x][y]=new Square(x, y, label);
            frame.add(buttons[x][y]);
            buttons[x][y].addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e){
                Square button = (Square) e.getSource(); // Get the button that was clicked
                game.move(button.get_x(), button.get_y());
                updateMessage();
                updateButtons();
                
                
              }
            });
          }

         }
        frame.add(message, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // Set an appropriate window size
        frame.setVisible(true);
    }
    private void updateButtons(){
      for(int x=0; x<size;x++){
        for(int y=0; y<size;y++){
          buttons[x][y].setText(game.getStatus(x,y));
        }
      }
    }
    private void updateMessage(){
      message.setText(game.getMessage());
    }
    public static void main(String[] args) {
        Boardgame game1= new FifteenModel();
        new ViewControl(game1,3);
    }
    
    }
