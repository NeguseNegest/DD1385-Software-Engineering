package ProjectPacman;

public interface ObserverOfPlayer {
    public void playerPositionChanged(int x, int y);

    public void playerPoweredUp();
}
