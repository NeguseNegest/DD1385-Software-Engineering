package labb5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MyTree extends TreeFrame {
    public final String directory="Lillaliv.txt";
    BufferedReader reader;
    String [] MajorBranch={"Biosfar","Rike",};
      void initTree() {
        root = new DefaultMutableTreeNode(directory); 
        treeModel = new DefaultTreeModel(root); 
        tree = new JTree(treeModel); 
        buildTree(); 
    }


    void buildTree() {
        Stack<DefaultMutableTreeNode> nodeStack = new Stack<>();
        nodeStack.push(root); // start with root node ie Liv 

        try {
            reader = new BufferedReader(new FileReader("labb5/LillaLiv.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    //line.hashCode()
                    if (line.startsWith("<") && !line.startsWith("</")) {
                        String level = extractLevel(line); 
                        String name = extractName(line);
                        String description = extractDescription(line); 
                        MyTreeNode newNode = new MyTreeNode(level, name, description);
                        nodeStack.peek().add(newNode); 
                        nodeStack.push(newNode); 
                    } else if (line.startsWith("</")) {
                        nodeStack.pop();
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractLevel(String line) {
        int startIdx = line.indexOf('<') + 1; 
        int endIdx = line.indexOf(' '); 
        if (endIdx == -1) { 
            endIdx = line.indexOf('>');
        }
        return line.substring(startIdx, endIdx);
    }
    

    private String extractName(String line) {
        int startIdx = line.indexOf("namn=\"") + 6; 
        int endIdx = line.indexOf('"', startIdx); 
        return line.substring(startIdx, endIdx);
    }
    private String extractDescription(String line) {
        int startIdx = line.indexOf('>') + 1; 
        return line.substring(startIdx).trim(); 
    }
    
    public static void main(String[] u) {
        // Create and display the tree
        MyTree treeApp = new MyTree();
        treeApp.setVisible(true);
    }
}