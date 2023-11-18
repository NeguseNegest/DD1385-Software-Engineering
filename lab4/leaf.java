package lab4;
public class leaf implements Component{
    private String name;
    private int weight;
    public leaf(String name, int weight){
        this.weight=weight;
        this.name=name;
    }


    public int getWeight(){
        return this.weight;
    }

    public String toString(){
        return this.name;
    }


    @Override
    public void add(Component component) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }


    @Override
    public void remove(Component component) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }


    @Override
    public Component getChild(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChild'");
    }
}
