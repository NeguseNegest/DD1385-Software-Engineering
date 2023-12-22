package ProjectPacman;

public class PacmanGame {

    public static void main(String[] args) {
        PacPlayer player = new PacPlayer();
        PacmanModel model = new PacmanModel(player);
        PacmanView view = new PacmanView(model);
        PacmanController controller = new PacmanController(model, view, player);
        view.initGUI();
    }
}
