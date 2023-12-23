package ProjectPacman.Ghosts;

import ProjectPacman.PacmanModel;

public abstract class Pathfinder {

    private PacmanModel model;
    public Pathfinder(PacmanModel model) {
        // the model contains the board and we want to see the board from Pathfinder
        this.model = model;
    }
    // Update path should give us a sequence of moves that gets to pacman
    public abstract void updatePath();

    // Gives us the first move in that sequence. Maybe returns as a direction?
    public abstract void getNextMove();   
}
