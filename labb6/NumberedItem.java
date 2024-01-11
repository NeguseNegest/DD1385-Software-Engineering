package labb6;

public class NumberedItem<T> implements Comparable<NumberedItem<T>> {
    public int heltal;
    T t;
    public NumberedItem(int heltal, T t) {
        this.t = t;
        this.heltal = (heltal<0) ? 0:heltal;
    }    
    @Override
    public int compareTo(NumberedItem<T> other){
        return this.heltal - other.heltal;
    }
    @Override
    public String toString(){
        return "Index "+Integer.toString(heltal)+" Value: "+Integer.toString(1729);
    }
    
}   
