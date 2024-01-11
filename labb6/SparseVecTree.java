package labb6;

import java.util.*;
public class SparseVecTree<E extends Comparable<E>> extends TreeMap<Integer,E> implements SparseVec<E>{

    public SparseVecTree() {
        super();
    }
    @Override
    public void add(E elem) {
        Integer minIndexBeingUsed = minIndex();
        Integer maxIndexBeingUsed = maxIndex();
        if (minIndexBeingUsed==-1){
            this.put(0,elem);
            return;
        }
        for (int i = minIndexBeingUsed; i <= maxIndexBeingUsed+1; i++) {
            if (this.get(i)==null){
                this.put(i, elem);
                return;
            }
        }
    }

    @Override
    public void add(int pos, E elem) {
        this.put(pos,elem);
    }

    @Override
    public E get(int pos) {
        E itemAtPos = this.get(pos);
        return itemAtPos;
    }

    @Override
    public int indexOf(E elem) {

        if (!this.containsValue(elem)){
            System.err.println("This element doesn't exist in the sparse vec");
            return 0;
        }

        for (Integer index : this.keySet()) {
            if (this.get(index).equals(elem)){
                return index;
            }
        }
        return 0;
    }

    @Override
    public int maxIndex() {
        if (this.size()==0){
            return -1;
        }
        int lastIndex = this.lastKey();        
        return lastIndex;
    }

    @Override
    public int minIndex() {
        if (this.size()==0){
            return -1;
        }
        int firstIndex = this.firstKey();

        return firstIndex;
    }

    @Override
    public void removeAt(int pos) {
        this.remove(pos);
    }

    @Override
    public void removeElem(E elem) {
        Integer indexOfElem = indexOf(elem);
        this.remove(indexOfElem);
    }

    @Override
    public int size() {
        return this.size();
    }

    @Override
    public List<E> sortedValues() {
        Collection<E> values = this.values();
        List<E> listOfVals = new ArrayList<>();
        listOfVals.addAll(values);
        Collections.sort(listOfVals);
        return listOfVals;
    }

    @Override
    public Object[] toArray() {
        Integer maxIndex = maxIndex();
        Object[] result = new Object[maxIndex+1];
        for (int i = 0; i <= maxIndex(); i++) {
            
            result[i] = this.get(i);
            System.out.println(this.get(i));
        }
        return result;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        E nextElement;
        String nextLine;
        for (Integer index : this.keySet()) {
            builder.append("\n");            
            nextElement = this.get(index);
            nextLine = "Index: "+index.toString()+" Value: "+nextElement.toString();
            builder.append(nextLine);
        }
        builder.deleteCharAt(0);
        String result = builder.toString();
        return result;
    }
    
}
