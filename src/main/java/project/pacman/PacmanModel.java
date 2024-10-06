package project.pacman;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import project.pacman.Ghost;

public class PacmanModel {

    private final String interiorWall = "#";
    private final String food = "-";
    private final String emptyTile = ".";
    private final String door = "d";
    private final String powerUp = "R";

    private boolean[][] foodWasEaten;
    private boolean[][] powerUpExists;
    private String[][] board;
    private int boardWidth; // Array length
    private int boardHeight; // Number of arrays
    private PacPlayer pacmanEntity;
    // private Ghost ghost;

    private List<Ghost> ghostList = new ArrayList<Ghost>();
    private int beanAmount;

    public PacmanModel(PacPlayer pacmanEntity) {
        this.pacmanEntity = pacmanEntity;
        this.pacmanEntity.setLives(3);
        this.pacmanEntity.setDirection(0);

        Ghost redGhost = new Ghost(this, "RedGhost");
        Ghost blueGhost = new Ghost(this, "BlueGhost");
        Ghost pinkGhost = new Ghost(this, "PinkGhost");
        Ghost yellowGhost = new Ghost(this, "YellowGhost");

        pacmanEntity.addObserver(redGhost);
        pacmanEntity.addObserver(blueGhost);
        pacmanEntity.addObserver(yellowGhost);
        pacmanEntity.addObserver(pinkGhost);

        ghostList.add(redGhost);
        ghostList.add(blueGhost);
        ghostList.add(yellowGhost);
        ghostList.add(pinkGhost);
        for (Ghost ghost : ghostList) {
            ghost.setDirection(0);
        }
        this.initBoard();
    }

    public void resetGame() {
        this.pacmanEntity.revive();
        this.pacmanEntity.setLives(3);
        this.pacmanEntity.setDirection(0);
        this.pacmanEntity.setScore(0);
        this.initBoard();
    }

    public void movePacman() {
        String direction = "none";
        int intDirection = pacmanEntity.getDirection();
        if (intDirection != 0) {
            if (intDirection == 1) {
                direction = "RIGHT";
            } else if (intDirection == 2) {
                direction = "UP";
            } else if (intDirection == 3) {
                direction = "LEFT";
            } else if (intDirection == 4) {
                direction = "DOWN";
            }

            int x = pacmanEntity.getX();
            int y = pacmanEntity.getY();

            if (x == 14 && y == 0 && pacmanEntity.getCurrentDirection().equals("LEFT")) { // Check if Pac-Man is at the
                                                                                          // left tunnel entrance and if
                                                                                          // so run
                movePacmanTo(14, 27, "PL");
                return;
            }
            if (x == 14 && y == 27 && pacmanEntity.getCurrentDirection().equals("RIGHT")) { // Check if Pac-Man is at
                                                                                            // the right tunnel entrance
                                                                                            // and if so continue
                movePacmanTo(14, 0, "P");
                return;
            }
            if (!wallCollision(x, y, direction)) {
                pacmanEntity.setCurrentDirection(direction);
                ; // Update current direction if no collision
            }

            switch (pacmanEntity.getCurrentDirection()) {
                case "UP":
                    if (!wallCollision(x, y, "UP")) {
                        movePacmanTo(x - 1, y, "PU");
                        pacmanEntity.setSymbol("PU");
                    }
                    break;
                case "DOWN":
                    if (!wallCollision(x, y, "DOWN")) {
                        movePacmanTo(x + 1, y, "PD");
                        pacmanEntity.setSymbol("PD");
                    }
                    break;
                case "LEFT":
                    if (!wallCollision(x, y, "LEFT")) {
                        movePacmanTo(x, y - 1, "PL");
                        pacmanEntity.setSymbol("PL");
                    }
                    break;
                case "RIGHT":
                    if (!wallCollision(x, y, "RIGHT")) {
                        movePacmanTo(x, y + 1, "P");
                        pacmanEntity.setSymbol("P");
                    }
                    break;
            }
        }
    }

    private void movePacmanTo(int newX, int newY, String pacman) {
        int x = pacmanEntity.getX();
        int y = pacmanEntity.getY();

        if (board[newX][newY].equals(food)) {
            foodWasEaten[newX][newY] = true;
            pacmanEntity.setScore(pacmanEntity.getScore() + 1);
        }

        if (board[newX][newY].equals(powerUp)) {
            powerUpExists[newX][newY] = false;
            pacmanEntity.notifyPowerUp();
            pacmanEntity.setScore(pacmanEntity.getScore() + 5);
        }

        pacmanEntity.setX(newX);
        pacmanEntity.setY(newY);
        board[newX][newY] = emptyTile;

        for (Ghost ghost : ghostList) {
            if (Math.abs(pacmanEntity.getX() - ghost.getX()) < 10
                    && Math.abs(pacmanEntity.getY() - ghost.getY()) < 10) {
                if (!ghost.isPanic()) {
                    pacmanEntity.notifyPosition();
                }
            }
        }
    }

    public void moveGhosts() {
        for (Ghost ghost : ghostList) {
            moveGhost(ghost);
        }
    }

    public void moveGhost(Ghost ghost) {
        int x = ghost.getX();
        int y = ghost.getY();
        String direction = "none";
        int intDirection = ghost.getDirection();
        if (intDirection != 0) {
            if (intDirection == 1) {
                direction = "RIGHT";
            } else if (intDirection == 2) {
                direction = "UP";
            } else if (intDirection == 3) {
                direction = "LEFT";
            } else if (intDirection == 4) {
                direction = "DOWN";
            }
            if (x == 14 && y == 0 && direction.equals("LEFT")) { // Check if Pac-Man is at the left tunnel entrance and
                                                                 // if so run
                ghost.setY(27);
                return;
            }
            if (x == 14 && y == 28 - 1 && direction.equals("RIGHT")) { // Check if Pac-Man is at the right tunnel
                                                                       // entrance and if so continue
                ghost.setY(0);
                return;
            }

            if (!wallCollision(x, y, direction)) {
                ghost.setCurrentDirection(direction);
            }

            switch (ghost.getCurrentDirection()) {
                case "UP":
                    if (!wallCollision(x, y, "UP")) {
                        ghost.setX(x - 1);
                    }
                    break;
                case "DOWN":
                    if (!wallCollision(x, y, "DOWN")) {
                        ghost.setX(x + 1);
                    }
                    break;
                case "LEFT":
                    if (!wallCollision(x, y, "LEFT")) {
                        ghost.setY(y - 1);
                    }
                    break;
                case "RIGHT":
                    if (!wallCollision(x, y, "RIGHT")) {
                        ghost.setY(y + 1);
                    }
                    break;
            }
        }
        ghost.setDirection(ghost.getNextMoveDirection());

    }

    public boolean wallCollision(int x, int y, String direction) {
        if (x == 14 && y == 27 && direction.equals("RIGHT")) {
            return true;
        } else if (x == 14 && y == 0 && direction.equals("LEFT")) {
            return true;
        } else if (direction.equals("UP") && !board[x - 1][y].equals(interiorWall)) {
            return false;
        } else if (direction.equals("DOWN") && !board[x + 1][y].equals(interiorWall) && !board[x + 1][y].equals(door)) {
            return false;
        } else if (direction.equals("LEFT") && !board[x][y - 1].equals(interiorWall) && !board[x][y - 1].equals(door)) {
            return false;
        } else if (direction.equals("RIGHT") && !board[x][y + 1].equals(interiorWall)
                && !board[x][y + 1].equals(door)) {
            return false;
        }
        return true;
    }

    public void resetPositions() {
        pacmanEntity.setX(boardWidth - 2);
        pacmanEntity.setY(1);
        pacmanEntity.setDirection(0);

        for (Ghost ghost : ghostList) {
            ghost.SpawnAtCenter();
            ghost.setDirection(0);
        }
    }

    public int getModelScore() {
        return pacmanEntity.getScore();
    }

    public boolean checkWinCondition() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board[i][j].equals("-")) {
                    return false;
                }
            }
        }
        return true;

    }

    public void handleGhostPlayerCollision() {
        for (Ghost ghost : ghostList) {
            if ((ghost.getX() == pacmanEntity.getX()) && (ghost.getY() == pacmanEntity.getY())) {
                if (ghost.isPanic()) {
                    ghost.SpawnAtCenter();
                } else {
                    pacmanEntity.dies();
                    // resetPositions();
                    // pacmanEntity.setLives(pacmanEntity.getLives()-1);
                }
            }
        }
    }

    public boolean checkLossCondition() {
        if (pacmanEntity.getLives() == 0) {
            return true;
        }
        return false;
    }

    public int getPlayerLives() {
        return pacmanEntity.getLives();
    }

    public String getStatus(int i, int j) {
        for (Ghost ghost : ghostList) {
            if (ghost.getX() == i && ghost.getY() == j) {
                return ghost.getSymbol();
            }
        }
        if (pacmanEntity.getX() == i && pacmanEntity.getY() == j) {
            return pacmanEntity.getSymbol();
        }
        return board[i][j];
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public String[][] getBoard() {
        return board;
    }

    public void initBoard() {
        board = new String[][] {
                // "#" = wall, "-" = Pellet, "." = empty, "d" = door， "R" ＝ Power up
                { "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#",
                        "#", "#", "#", "#", "#", "#", "#", "#" },
                { "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "#", "-", "-", "-", "-", "-",
                        "-", "-", "-", "-", "-", "-", "-", "#" },
                { "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#",
                        "#", "-", "#", "#", "#", "#", "-", "#" },
                { "#", "R", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#",
                        "#", "-", "#", "#", "#", "#", "R", "#" },
                { "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#",
                        "#", "-", "#", "#", "#", "#", "-", "#" },
                { "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
                        "-", "-", "-", "-", "-", "-", "-", "#" },
                { "#", "-", "#", "#", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#",
                        "#", "-", "#", "#", "#", "#", "-", "#" },
                { "#", "-", "#", "#", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#",
                        "#", "-", "#", "#", "#", "#", "-", "#" },
                { "#", "-", "-", "-", "-", "-", "-", "#", "#", "-", "-", "-", "-", "#", "#", "-", "-", "-", "-", "#",
                        "#", "-", "-", "-", "-", "-", "-", "#" },
                { "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", ".", "#", "#", ".", "#", "#", "#", "#",
                        "#", "-", "#", "#", "#", "#", "#", "#" },
                { "#", "#", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", ".", "#", "#", ".", "#", "#", "#", "#",
                        "#", "-", "#", "#", "#", "#", "#", "#" },
                { "#", "#", "#", "#", "#", "#", "-", "#", "#", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "#",
                        "#", "-", "#", "#", "#", "#", "#", "#" },
                { "#", "#", "#", "#", "#", "#", "-", "#", "#", ".", "#", "#", "#", "d", "d", "#", "#", "#", ".", "#",
                        "#", "-", "#", "#", "#", "#", "#", "#" },
                { "#", "#", "#", "#", "#", "#", "-", "#", "#", ".", "#", ".", ".", ".", ".", ".", ".", "#", ".", "#",
                        "#", "-", "#", "#", "#", "#", "#", "#" },
                { ".", ".", ".", ".", ".", ".", "-", ".", ".", ".", "#", ".", ".", ".", ".", ".", ".", "#", ".", ".",
                        ".", "-", ".", ".", ".", ".", ".", "." },
                { "#", "#", "#", "#", "#", "#", "-", "#", "#", ".", "#", ".", ".", ".", ".", ".", ".", "#", ".", "#",
                        "#", "-", "#", "#", "#", "#", "#", "#" },
                { "#", "#", "#", "#", "#", "#", "-", "#", "#", ".", "#", "#", "#", "#", "#", "#", "#", "#", ".", "#",
                        "#", "-", "#", "#", "#", "#", "#", "#" },
                { "#", "#", "#", "#", "#", "#", "-", "#", "#", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "#",
                        "#", "-", "#", "#", "#", "#", "#", "#" },
                { "#", "#", "#", "#", "#", "#", "-", "#", "#", ".", "#", "#", "#", "#", "#", "#", "#", "#", ".", "#",
                        "#", "-", "#", "#", "#", "#", "#", "#" },
                { "#", "#", "#", "#", "#", "#", "-", "#", "#", ".", "#", "#", "#", "#", "#", "#", "#", "#", ".", "#",
                        "#", "-", "#", "#", "#", "#", "#", "#" },
                { "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#", "#", "-", "-", "-", "-", "-",
                        "-", "-", "-", "-", "-", "-", "-", "#" },
                { "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#",
                        "#", "-", "#", "#", "#", "#", "-", "#" },
                { "#", "-", "#", "#", "#", "#", "-", "#", "#", "#", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#",
                        "#", "-", "#", "#", "#", "#", "-", "#" },
                { "#", "R", "-", "-", "#", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
                        "-", "-", "#", "#", "-", "-", "R", "#" },
                { "#", "#", "#", "-", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#",
                        "#", "-", "#", "#", "-", "#", "#", "#" },
                { "#", "#", "#", "-", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#",
                        "#", "-", "#", "#", "-", "#", "#", "#" },
                { "#", "-", "-", "-", "-", "-", "-", "#", "#", "-", "-", "-", "-", "#", "#", "-", "-", "-", "-", "#",
                        "#", "-", "-", "-", "-", "-", "-", "#" },
                { "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#",
                        "#", "#", "#", "#", "#", "#", "-", "#" },
                { "#", "-", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "-", "#", "#", "-", "#", "#", "#", "#",
                        "#", "#", "#", "#", "#", "#", "-", "#" },
                { "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
                        "-", "-", "-", "-", "-", "-", "-", "#" },
                { "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#",
                        "#", "#", "#", "#", "#", "#", "#", "#" } };

        boardWidth = board[0].length;
        boardHeight = board.length;

        foodWasEaten = new boolean[boardHeight][boardWidth];
        powerUpExists = new boolean[boardHeight][boardWidth];

        beanAmount = 0;
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board[i][j].equals("R")) {
                    powerUpExists[i][j] = true;
                }
                foodWasEaten[i][j] = false;

                if (board[i][j].equals("-")) {
                    beanAmount += 1;
                }
            }
        }

        // Pacman should be placed at the bottom or top corner at each start of the
        // game.
        pacmanEntity.setX(boardWidth - 2);
        pacmanEntity.setY(1);
        pacmanEntity.setSymbol("P");

        for (Ghost ghost : ghostList) {
            ghost.SpawnAtCenter();
        }
    }
}
