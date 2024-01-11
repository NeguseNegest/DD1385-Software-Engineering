package labb5;
import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;        
import java.awt.*;
import java.awt.event.*;

// Class TreeFrame is a JFrame with additional functionality for a JTree and its controls.
class TreeFrame extends JFrame implements ActionListener {
    // Swing components for the tree and the control panel.
    JCheckBox box;
    JTree tree;
    DefaultMutableTreeNode root;
    DefaultTreeModel treeModel;
    JPanel controls;
	DefaultMutableTreeNode Vaxter;
	DefaultMutableTreeNode Djur;
	DefaultMutableTreeNode Svamp;




    // Constants for the action commands.
    static final String closeString = " Close ";
    static final String showString = " Show Details ";

    // Constructor for TreeFrame.
    TreeFrame() {
        Container c = getContentPane();

        // Initialize the tree component.
        initTree();

        // Add a mouse listener to handle clicks on the tree.
        tree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Show details if the checkbox is selected and an element is clicked.
                if (box.isSelected())
                    showDetails(tree.getPathForLocation(e.getX(), e.getY()));
            }
        });
        
        // Initialize the controls panel.
        controls = new JPanel();
        box = new JCheckBox(showString);
        initGUI();

        // Add the controls and tree to the container.
        c.add(controls, BorderLayout.NORTH);
        c.add(tree, BorderLayout.CENTER);   
        setVisible(true);
    } 

    // Handles action events (e.g., button clicks).
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals(closeString))
            dispose();  // Close the frame.
    }

    // Initializes the GUI components for the controls panel.
    void initGUI() {
        tree.setFont(new Font("Dialog", Font.BOLD, 12));
        controls.add(box);
        addButton(closeString); // Add the close button.
        controls.setBackground(Color.lightGray);
        controls.setLayout(new FlowLayout());    
        setSize(400, 400);
    }
 
    // Adds a button to the controls panel.
    void addButton(String n) {
        JButton b = new JButton(n);
        b.setFont(new Font("Dialog", Font.BOLD, 12));
        b.addActionListener(this);
        controls.add(b);
    }

    // Initializes the tree. Can be overridden in a subclass.
    void initTree(){
        root = new DefaultMutableTreeNode("Liv");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
		Vaxter = new DefaultMutableTreeNode("Växter");
		root.add(Vaxter);
		Djur = new DefaultMutableTreeNode("Djur");
		Svamp = new DefaultMutableTreeNode("Svamp");
		root.add(Djur);
		root.add(Svamp);


    }

    // Shows details of the selected tree node. Can be overridden in a subclass.
    void showDetails(TreePath path){
        if (path == null)
            return;
        String info = path.getLastPathComponent().toString();
        JOptionPane.showMessageDialog(this, info);
    }

    // Main method to run the application.
    public static void main(String[] u) {
        new TreeFrame();
    }
}
