package ProjectPacman;




public class PacmanGame {
    public static void main(String[] args) {
        PacmanModel model = new PacmanModel();
        
        // PacPlayer pacPlayer = new PacPlayer();
        // pacPlayer.addObserver(model);
        
        PacmanView view = new PacmanView(model);
        PacmanController controller = new PacmanController(model, view);
        view.addKeyListener(controller);
        view.show();
    }
}
