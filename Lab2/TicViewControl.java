
//ViewControl class for TicTacToe
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
public class TicViewControl extends JFrame implements ActionListener{
    private Boardgame game;
    private Square[][] slots;
    private JTextField message = new JTextField();

    public TicViewControl(Boardgame gm){
        this.game=gm;
        JFrame frame=new JFrame("Tic Tac Toe baby");
        frame.setLayout(new GridLayout (3,3));
        slots=new Square[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String label=game.getStatus(i,j);
                slots[i][j]=new Square(i, j, label);
                frame.add(slots[i][j]);
                slots[i][j].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        Square slot= (Square) e.getSource();
                        game.move(slot.get_x(),slot.get_y());
                        updateMessage();
                        updateButtons();

                    }

                });
            }
        }
        frame.add(message, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); 
        frame.setVisible(true);

    }
    private void updateButtons(){
        for(int x=0; x<3;x++){
          for(int y=0; y<3;y++){
            slots[x][y].setText(game.getStatus(x,y));
          }
        }
      }
      private void updateMessage(){
        message.setText(game.getMessage());
      }
      public static void main(String[] args) {
        Boardgame game1= new TicTacToe();
        new TicViewControl(game1);
    }
}
