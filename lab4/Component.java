package lab4;
public interface Component{
    int getWeight();
    void add(Component component);
    void remove(Component component);
    Component getChild(int index);
    String toString(); // Operation method
}
