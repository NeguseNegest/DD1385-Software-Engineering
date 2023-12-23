package ProjectPacman.Ghosts;

import ProjectPacman.PacmanModel;

public abstract class Pathfinder {

    public abstract void updatePath();
    public abstract int getNextMove();   
}
