package ProjectPacman.Ghosts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import ProjectPacman.Entity;
import ProjectPacman.ObserverOfPlayer;
import ProjectPacman.PacmanModel;

public class Ghost extends Entity implements ObserverOfPlayer{
    
    private final int cX=13;
    private final int cY=14;

    private  List<int[]> pathRoute = new ArrayList<>();

    private boolean panicMode;
    private Pathfinder pathfinder;
    
    private int direction;
    
    private PacmanModel model;
    
    
    public Ghost(PacmanModel model){
        this.model = model;
        // this.pathfinder = pathfinder; 
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
        int newDirection = getNextMoveDirection();
        setDirection(newDirection);
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
    }

    private void updatePath(int pacmanX, int pacmanY){
        // BFS
        int boardWidth = model.getBoardWidth();
        int boardHeight = model.getBoardHeight();
        boolean[][] visitedMap = new boolean[boardHeight][boardWidth];
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                visitedMap[i][j] = false;
            }
        }

        String [][] board = model.getBoard();
        LinkedList<Node> nodes = new LinkedList<>();

        nodes.add(new Node(x,y));
        
        int currentX = x;
        int currentY = y;
        while (currentX!=pacmanX && currentY != pacmanY){
            Node currentNode = nodes.pop();
            currentX = currentNode.x;
            currentY = currentNode.y;
            
            if (board[currentX][currentY+1]!="#"){
                Node nextNode = new Node(currentX, currentY+1);
                nextNode.parent = currentNode;
                nodes.add(nextNode);
            
            }else if (board[currentX-1][currentY]!="#"){
                Node nextNode = new Node(currentX, currentY);
                nextNode.parent = currentNode;
                nodes.add(nextNode);
            
            }else if (board[currentX][currentY-1]!="#"){
                Node nextNode = new Node(currentX, currentY-1);
                nextNode.parent = currentNode;
                nodes.add(nextNode);
            
            }else if (board[currentX+1][currentY]!="#"){
                Node nextNode = new Node(currentX+1, currentY);
                nextNode.parent = currentNode;
                nodes.add(nextNode);
            }
        }

        Node findingNode = nodes.pop();
        while(findingNode.x != this.x && findingNode.y != this.y){
            int[] nextCoordinate = new int[]{findingNode.x,findingNode.y}; 
            pathRoute.add(0,nextCoordinate);
        }
        pathRoute.add(0,new int[]{x,y});

    }
    private int getNextMoveDirection(){
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
        return 0;
    }
}
