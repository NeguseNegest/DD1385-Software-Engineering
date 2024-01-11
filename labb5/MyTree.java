package labb5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class MyTree extends TreeFrame {
    public final String directory;
    private String suggestion;
    BufferedReader reader;
    public MyTree() {
        directory = "LillaLiv.txt";
    }
    public MyTree(String directory){
        this.directory = directory;
    }
    String [] MajorBranch={"Biosfar","Rike",};
      void initTree() {
        root = new DefaultMutableTreeNode(directory); 
        treeModel = new DefaultTreeModel(root); 
        tree = new JTree(treeModel); 
        tree.setBackground(Color.GREEN); // Example color
        buildTree(); 
    }


    void buildTree() {
        try {
            reader = new BufferedReader(new FileReader("labb5/LillaLiv.txt"));
            DefaultMutableTreeNode currentNode = root;
            String line;
            while ((line = reader.readLine()) != null) {
                currentNode = buildTreeRecursive(line.trim(), currentNode);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DefaultMutableTreeNode buildTreeRecursive(String line, DefaultMutableTreeNode parentNode) {
        if (line.isEmpty()) {
            return parentNode;
        }

        if (line.startsWith("<") && !line.startsWith("</")) {
            String level = extractLevel(line);
            String name = extractName(line);
            String description = extractDescription(line);

            MyTreeNode newNode = new MyTreeNode(level, name, description);
            parentNode.add(newNode);
            return newNode; 
        } else if (line.startsWith("</")) {
            return (DefaultMutableTreeNode) parentNode.getParent(); // Go back to the parent node
        }

        return parentNode;
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


    @Override
void showDetails(TreePath p) {
    if (p == null) {
        return; 
    }

    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) p.getLastPathComponent();

    MyTreeNode myNode = (MyTreeNode) selectedNode;

    String description = myNode.NodeDescription();
    String further = myNode.getParent().toString();
    String newO=further+":"+description;
    //String ne1= myNode.getParent();
    JOptionPane.showMessageDialog(this, newO);
    
}

    
    public static void main(String[] u) {
        MyTree treeApp = new MyTree();
        treeApp.setVisible(true);
    }
}