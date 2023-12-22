package ProjectPacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer implements ActionListener {
    private GameLoopListener gameLoopListener;

    public GameTimer(GameLoopListener gameLoopListener) {
        this.gameLoopListener = gameLoopListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameLoopListener.onGameTick();
    }
}
