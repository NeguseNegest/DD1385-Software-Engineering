package Extra1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
public class Composite implements Component, Iterable<Component>{
    private List<Component> children = new ArrayList<>();
    private int weight;
    private String name;
    private Iterator<Component> compositesIterator = BFSIterator();
    //private Iterator<Component> compositeIterator2 = DepthFirst();

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
        StringBuilder names= new StringBuilder(this.name+" Contains: "); //get the suitcase name first 
        for(Component child:children){ // iterate through the children array
            if (child instanceof Composite){
                names.append(child.toString());
            }
            else if (child instanceof Component){
                names.append(" ").append(child.toString()).append(" ");
            }
        }
        return names.toString();// return names in string format 
    }

    public void add(Component component){
        children.add(component);
    }

    public Component getChild(int index){
        return children.get(index);
    }

    public void setIterator(String type){
        if (type=="BFS"){
            this.compositesIterator = BFSIterator();
        }
        else if (type == "DFS"){}
        // DFS call function here
        else if (type == "preOrder"){}
    }

    @Override
    public Iterator<Component> iterator() {
        // return compositesIterator;
        //return BFSIterator();
        return DFSIterator();
        // return blah1;

    } 


    public Iterator<Component> BFSIterator(){
        LinkedList<Component> Queue= new LinkedList<Component>();
        Queue.add(this); // this adds the head of the Composite i.e suitcase
        return new Iterator<Component>() { // Why this why do we create a new iterator 
            @Override
            public boolean hasNext(){
                return (!Queue.isEmpty());
            }
            
            @Override
            public Component next(){ // BfS implementation inside the next() method
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                Component currentChild = Queue.pop(); // removes head
                if(currentChild instanceof Composite){
                    Composite composite = (Composite) currentChild;
                    Queue.addAll(composite.children); // adds all children at tail
                }
                return currentChild; // next() returns currentChild
            }  
        };  
    }
    // public Iterator<Component> blah1
    // public Iterator<Component> blah2...

    // TODO: Make DFSIterator
    @Override
    public String getName(){
        return this.name;
    }

    //TODO-deepth first

    public Iterator<Component> DFSIterator(){
        LinkedList<Component> Queue = new LinkedList<Component>();
        Queue.add(this);
        return new Iterator<Component>() {
            public boolean hasNext(){
                return(!Queue.isEmpty());
            
            }

            public Component next(){
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                Component currentChild=Queue.pop(); // removes head
                if(currentChild instanceof Composite){
                    Composite composite = (Composite) currentChild;
                    Queue.addAll(0,composite.children); // adds all children at head
                }
                return currentChild;
            }
            
        };
    }

}
