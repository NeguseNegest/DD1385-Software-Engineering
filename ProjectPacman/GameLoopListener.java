package ProjectPacman;

public interface GameLoopListener {
    void onGameTick();

    void onGameWin();

    void onGameReset();

    void onGameLoss();
}