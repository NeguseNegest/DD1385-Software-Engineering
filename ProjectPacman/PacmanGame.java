package ProjectPacman;

public class PacmanGame {


    public static void main(String[] args) {
        PacPlayer player = new PacPlayer();
        PacmanModel model = new PacmanModel(player);
        PacmanView view = new PacmanView(model);
        PacmanSound sound= new PacmanSound();
        new PacmanController(model, view, player,sound);
        view.initGUI();
        // PacmanGame game = new PacmanGame();
        // game.showGame();
    }
}
