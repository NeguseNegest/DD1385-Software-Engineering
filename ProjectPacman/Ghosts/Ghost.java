package ProjectPacman.Ghosts;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ProjectPacman.Entity;
import ProjectPacman.ObserverOfPlayer;
import ProjectPacman.PacmanModel;

public class Ghost extends Entity implements ObserverOfPlayer{
    
    private final int cX=13;
    private final int cY=14;

    public  List<int[]> pathRoute = new ArrayList<>();

    private boolean panicMode;
    
    private int direction;
    
    private PacmanModel model;
    
    
    public Ghost(PacmanModel model){
        this.model = model;
    }
    

    public void SpawnAtCenter(){
        if(Eaten()){
            setX(cX);
            setY(cY);
        }
        return;
    }

    public boolean Eaten(){
        return true;
                
    }
    
    
    public void setDirection(int direction){
        this.direction = direction;
    }
    public int getDirection(){
        return direction;
    }

    @Override
    public void playerPositionChanged(int x, int y){
        updatePath(x, y);
        // int newDirection = getNextMoveDirection();
        // setDirection(newDirection);
    }

    @Override
    public void playerPoweredUp(){
        panicMode=true;
        while(panicMode){
            break;
        }

        // Write a random walk that diverges from players path maybe here while panicMode is true
        // Add a timer that turns panicMode to false
    }

    private class Node{
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

    private void updatePath(int pacmanX, int pacmanY) {
        // BFS
        int boardWidth = model.getBoardWidth();
        int boardHeight = model.getBoardHeight();
        String[][] board = model.getBoard();
        boolean[][] visitedMap = new boolean[boardHeight][boardWidth];

        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(new Node(x, y));

        int currentX, currentY;

        while (!nodes.isEmpty()) {
            Node currentNode = nodes.pop();
            currentX = currentNode.x;
            currentY = currentNode.y;
            if (currentX == pacmanX && currentY == pacmanY) {
                Node endNode = currentNode;
                while (endNode != null) {
                    int[] nextCoordinate = new int[]{endNode.x, endNode.y};
                    pathRoute.add(0, nextCoordinate);
                    endNode = endNode.parent;
                }
                break; // Reached the target
            }
            
            
            if (!visitedMap[currentX][currentY]) {
                visitedMap[currentX][currentY] = true;

                if (board[currentX][currentY + 1] != "#" && currentY+1 != 28) {
                    nodes.add(0, new Node(currentX, currentY + 1, currentNode));
                } 
                if (board[currentX - 1][currentY] != "#") {
                    nodes.add(0, new Node(currentX - 1, currentY, currentNode));
                } 
                if (board[currentX][currentY - 1] != "#" && currentY-1!=0) {
                    nodes.add(0, new Node(currentX, currentY - 1, currentNode));
                }
                if (board[currentX + 1][currentY] != "#") {
                    nodes.add(0, new Node(currentX + 1, currentY, currentNode));
                }
            }
        }

        // Node findingNode = nodes.pop();
        // while (findingNode != null) {
        //     int[] nextCoordinate = new int[]{findingNode.x, findingNode.y};
        //     pathRoute.add(0, nextCoordinate);
        //     findingNode = findingNode.parent;
        // }
        pathRoute.add(0, new int[]{x, y});
    }

    public int getNextMoveDirection(){
        int nextX = pathRoute.get(0)[0];
        int nextY = pathRoute.get(0)[1];
        pathRoute.remove(0);
        // RIGHT
        if (nextY==y+1){
            return 1;
        // UP
        }else if(nextX==x-1){
            return 2;
        //LEFT
        }else if (nextY==y-1){
            return 3;
        //DOWN
        }else if (nextX==x+1){
            return 4;
        }
        return 1;
    }
}
