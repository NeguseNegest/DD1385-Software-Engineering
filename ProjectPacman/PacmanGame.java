package ProjectPacman;


public class PacmanGame {

    public static void main(String[] args) {
        // PacPlayer pacPlayer = new PacPlayer();
        // pacPlayer.addObserver(model);
        PacmanModel model = new PacmanModel();
        PacmanView view = new PacmanView(model);
        PacmanController controller = new PacmanController(model, view);
        view.initGUI();
    }
}
