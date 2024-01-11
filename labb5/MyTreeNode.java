package labb5;

import javax.swing.tree.DefaultMutableTreeNode;

public class MyTreeNode extends DefaultMutableTreeNode {
    private String level; // T.ex. Biosfär, Rike
    private String name; 
    private String description;

    public MyTreeNode(String level, String name, String description) {
        super(name); // Anropar konstruktorn i DefaultMutableTreeNode med namnet.
        this.level = level;
        this.name = name;
        this.description = description;
    }


    public String NodeLevel(){
        return level;

    }
    public String NodeDescription(){
        return description;
    }
  
}
