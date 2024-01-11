package ProjectPacman;

public class PacmanGame {
    private PacPlayer player;
    private PacmanModel model;
    private PacmanView view;
    private PacmanSound sound;
    private PacmanController controller;
    public PacmanGame() {
        player = new PacPlayer();
        model = new PacmanModel(player);
        view = new PacmanView(model);
        sound = new PacmanSound();
        controller = new PacmanController(model, view, player, sound);
    }

    public void showGame(){
        view.initGUI();
    }

    public static void main(String[] args) {
        PacPlayer player = new PacPlayer();
        PacmanModel model = new PacmanModel(player);
        PacmanView view = new PacmanView(model);
        PacmanSound sound= new PacmanSound();
        PacmanController controller = new PacmanController(model, view, player,sound);
        view.initGUI();
        // PacmanGame game = new PacmanGame();
        // game.showGame();
    }
}
