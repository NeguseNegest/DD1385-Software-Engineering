package labb5;
import javax.swing.*;
import javax.swing.tree.*;
import java.io.*; 

// Class DirTree2 extends TreeFrame, focusing on representing file system directories.
class DirTree2 extends TreeFrame {

    // Default directory to start the tree from, can be overridden by command-line arguments.
    static String directory=".";

    // Overrides initTree() from TreeFrame to initialize the tree with a root node representing the directory.
    void initTree() {
        root = new DefaultMutableTreeNode(directory); // Create root node with the directory path.
        treeModel = new DefaultTreeModel(root); // Set up the tree model.
        tree = new JTree(treeModel); // Create the tree.
        buildTree(); // Populate the tree with the file system's structure.
    }

    // Builds the tree structure starting from the directory.
    private void buildTree() {
        File f = new File(directory); // Create a File object for the directory.
        String[] list = f.list(); // List all files and directories in the current directory.
        for (int i = 0; i < list.length; i++)
            buildTree(new File(f, list[i]), root); // Recursively build the tree.
    }

    // Recursive method to build the tree from a file/directory and its parent node.
    private void buildTree(File f, DefaultMutableTreeNode parent) {  
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(f.toString()); // Create a new tree node for the file/directory.
        parent.add(child); // Add the new node to its parent.
        if (f.isDirectory()) { // If it's a directory, recurse into it.
            String[] list = f.list(); // List all files and directories in it.
            for (int i = 0; i < list.length; i++)
                buildTree(new File(f, list[i]), child); // Recursively build tree for each item.           
        }        
    }  

    // Overrides showDetails() from TreeFrame to display file/directory attributes.
    void showDetails(TreePath p){
        if (p == null)
            return; // Do nothing if the path is null.
        File f = new File(p.getLastPathComponent().toString()); // Create a File object from the selected path.
        JOptionPane.showMessageDialog(this, f.getPath() + "\n   " + getAttributes(f)); // Show file/directory attributes in a dialog.
    }

    // Constructs a string containing attributes of a file or directory.
    private String getAttributes(File f) {
        String t = "";
        if (f.isDirectory())
            t += "Directory"; // Mark as directory.
        else
            t += "Nondirectory file"; // Mark as nondirectory (file).
        t += "\n   ";
        if (!f.canRead())
            t += "not "; // Check read permission.
        t += "Readable\n   ";
        if (!f.canWrite())
            t += "not "; // Check write permission.
        t += "Writeable\n  ";
        if (!f.isDirectory())
            t += "Size in bytes: " + f.length() + "\n   "; // Display size for files.
        else {
            t += "Contains files: \n     ";
            String[] contents = f.list();
            for (int i = 0; i < contents.length; i++)
                t += contents[i] + ", "; // List contents for directories.
            t += "\n";
        } 
        return t;
    }

    // Main method to run the application, allows specifying the starting directory via command-line arguments.
    public static void main(String[] args) {
        if(args.length > 0) directory = args[0]; // Override default directory if an argument is provided.
        new DirTree2(); // Create an instance of DirTree2.
    }
}
