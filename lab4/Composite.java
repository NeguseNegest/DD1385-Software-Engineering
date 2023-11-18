package lab4;
import java.util.ArrayList;
import java.util.List;
public class Composite implements Component {
    private List<Component> children = new ArrayList<>();
    private int weight;
    private String name;

    public Composite(String name,int weight){ // le constructor
        this.name=name;
        this.weight=weight;
    }


    public void remove(Component component){
        children.remove(component);

    }
    

    public int getWeight(){ // typo 
        int totWeight=this.weight; //get the weight of the suitcase first
        for(Component child : children){ //iterate throigh each child leaf
            totWeight+=child.getWeight(); // total weight
        }
        return totWeight;
    }

    @Override
    public String toString(){
        StringBuilder names= new StringBuilder(this.name+":"); //get the suitcase name first 
        for(Component child:children){ // iterate through the children array
            names.append(" ").append(child.toString()); // add names, if component object got a child go through them
        }
        return names.toString();// return names in string format 
    }

    public void add(Component component){
        children.add(component);
    }

    public Component getChild(int index){
        return children.get(index);
    }

    public static void main(String[] args) {
        
    }
}
