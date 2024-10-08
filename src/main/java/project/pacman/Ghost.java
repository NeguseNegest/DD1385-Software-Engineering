package project.pacman;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import project.pacman.Entity;
import project.pacman.PacmanModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ghost extends Entity implements ObserverOfPlayer {

    private final int cX = 15;
    private final int cY = 15;

    public List<int[]> pathRoute = new ArrayList<>();

    private boolean panicMode;
    private String symbol;
    private String defaultSymbol;
    private int direction;

    private PacmanModel model;
    Timer notificationCoolDown;

    public Ghost(PacmanModel model, String defaultSymbol) {
        this.model = model;
        notificationCoolDown = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.defaultSymbol = defaultSymbol;
        this.symbol = this.defaultSymbol;

    }

    public void SpawnAtCenter() {
        setX(cX);
        setY(cY);
        panicMode = false;
        setSymbol(defaultSymbol);
    }

    public void SpawnAtCenter(int seconds) {
        Timer timer = new Timer(seconds * 5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpawnAtCenter();
                setSymbol(defaultSymbol);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public void playerPositionChanged(int x, int y) {
        // if (notificationCoolDown.isRunning()){
        // return;
        // }
        updatePath(x, y);
        int newDirection = getNextMoveDirection();
        setDirection(newDirection);
        // notificationCoolDown.restart();
    }

    @Override
    public void playerPoweredUp() {
        panicMode = true;
        setSymbol("ScaredGhost");
        Timer panicTimer;
        panicTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSymbol(defaultSymbol);
                panicMode = false;
            }
        });
        panicTimer.setRepeats(false); // Make sure the timer only fires once
        if (panicTimer.isRunning()) {
            panicTimer.restart();
        } else {
            panicTimer.start();
        }

        // Write a random walk that diverges from players path maybe here while
        // panicMode is true
        // Add a timer that turns panicMode to false
    }

    private class Node {
        int x;
        int y;
        Node parent;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, Node parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }
    }

    private void updatePath(int targetX, int targetY) {
        // BFS
        pathRoute = new ArrayList<>();
        // pathRoute.add(0, new int[]{getX(), getY()});
        int boardWidth = model.getBoardWidth();
        int boardHeight = model.getBoardHeight();
        String[][] board = model.getBoard();
        boolean[][] visitedMap = new boolean[boardHeight][boardWidth];

        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(new Node(getX(), getY()));

        int currentX, currentY;

        // System.out.println(pacmanY);
        while (!nodes.isEmpty()) {
            // System.out.println("Update");
            Node currentNode = nodes.pop();
            currentX = currentNode.x;
            currentY = currentNode.y;

            if (currentX == 14 && currentY == 0) {
                continue;
            }
            if (currentX == 14 && currentY == 28 - 1) {
                continue;
            }
            // System.out.println("loop");

            if (currentX == targetX && currentY == targetY) {

                Node endNode = currentNode;
                // System.out.println(endNode.x != getX() && endNode.y != getY());

                while (true) {
                    if (endNode.x == getX() && endNode.y == getY()) {
                        break;
                    }
                    int[] nextCoordinate = new int[] { endNode.x, endNode.y };
                    pathRoute.add(0, nextCoordinate);
                    endNode = endNode.parent;
                }
                break; // Reached the target
            }

            if (!visitedMap[currentX][currentY]) {
                visitedMap[currentX][currentY] = true;

                if (board[currentX][currentY + 1] != "#") {
                    nodes.add(new Node(currentX, currentY + 1, currentNode));
                }
                if (board[currentX - 1][currentY] != "#") {
                    nodes.add(new Node(currentX - 1, currentY, currentNode));
                }
                if (board[currentX][currentY - 1] != "#") {
                    nodes.add(new Node(currentX, currentY - 1, currentNode));
                }
                if (board[currentX + 1][currentY] != "#") {
                    nodes.add(new Node(currentX + 1, currentY, currentNode));
                }
            }
        }
    }

    public int getNextMoveDirection() {
        if (pathRoute.size() == 0) {
            boolean thatPoint = getY() == 0 && getX() == 14;
            boolean thatOtherPoint = getY() == 27 && getX() == 14;
            if (thatPoint || thatOtherPoint) {
                return getDirection();
            }
            List<Integer> choices = new ArrayList<>();
            if (!model.wallCollision(getX(), getY(), "RIGHT") && getDirection() != 3) {
                choices.add(1);
            }
            if (!model.wallCollision(getX(), getY(), "UP") && getDirection() != 4) {
                choices.add(2);
            }
            if (!model.wallCollision(getX(), getY(), "LEFT") && getDirection() != 1) {
                choices.add(3);
            }
            if (!model.wallCollision(getX(), getY(), "DOWN") && getDirection() != 2) {
                choices.add(4);
            }
            int randomChoice = new Random().nextInt(choices.size());
            if (getDirection() == 0) {
                return new Random().nextInt(4) + 1;
            } else {
                return choices.get(randomChoice);
            }
        }
        int nextX = pathRoute.get(0)[0];
        int nextY = pathRoute.get(0)[1];
        pathRoute.remove(0);
        // RIGHT
        if (nextY == y + 1) {
            return 1;
            // UP
        } else if (nextX == x - 1) {
            return 2;
            // LEFT
        } else if (nextY == y - 1) {
            return 3;
            // DOWN
        } else if (nextX == x + 1) {
            return 4;
        }
        return 0;
    }

    private String currentDirection;

    public void setCurrentDirection(String currentDirection) {
        this.currentDirection = currentDirection;
    }

    public String getCurrentDirection() {
        return (currentDirection != null) ? currentDirection
                : new String[] { "none", "RIGHT", "UP", "LEFT", "DOWN" }[getDirection()];
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isPanic() {
        return panicMode;
    }
}
