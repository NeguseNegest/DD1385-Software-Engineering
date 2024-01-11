package labb6;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class TreeSparseVecTest {

    //För en tom SparseVec, testa size(), minIndex(), maxIndex() som ju alla ska ge heltalsvärden. 
    // Testa get(k), där k är ett godtyckligt int-värde. Anropa toArray() och sortedValues() på tom vektor och gör lämpliga tester på resultaten. 
    @Test
    public void testEmpty() {
        SparseVec<Integer> vec = new SparseVecTree<>();
        
        assertEquals(0, vec.size());
        assertEquals(-1, vec.minIndex());
        assertEquals(-1, vec.maxIndex());

        assertNull(vec.get(0));
        assertNull(vec.get(1));

        assertArrayEquals(new Object[0], vec.toArray());
        assertEquals(0, vec.sortedValues().size());
    }
// Lägg till element på specificerade positioner, några på samma index och testa size() efter varje insättning. 
// Testa värdena på minIndex() och maxIndex().

    @Test
    public void testIndex() {
        SparseVec<String> vec = new SparseVecTree<>();
        vec.add("pizza");
        vec.add("gabagool");
        vec.add("mozarella");

        assertEquals(3, vec.size());
        assertEquals(0, vec.minIndex());
        assertEquals(2, vec.maxIndex());

        assertNull(vec.get(-1));
        assertNull(vec.get(3));
    }
// Lägg till element på ospecificerad position. Testa size() och get() så att det stämmer med förväntade värden. 
// Kom ihåg att testa get() med ett index som inte används. Den ska gå ge null.
    @Test
    public void testInsertAtPosition() {
        SparseVec<Integer> vec = new SparseVecTree<>();
        
        vec.add(0, 1);
        assertEquals(1, vec.size());
        assertEquals(0, vec.minIndex());
        assertEquals(0, vec.maxIndex());

        vec.add(2, 2);
        assertEquals(2, vec.size());
        assertEquals(0, vec.minIndex());
        assertEquals(2, vec.maxIndex());

        vec.add(1, 3);
        assertEquals(3, vec.size());
        assertEquals(0, vec.minIndex());
        assertEquals(2, vec.maxIndex());

        assertEquals(3, (int) vec.get(1));
    }

    @Test
    public void testAddAtUnspecifiedPosition() {
        SparseVec<String> vec = new SparseVecTree<>();
        
        vec.add("pizza");
        vec.add("mama");
        vec.add("mia");

        assertEquals(3, vec.size());
        assertEquals("pizza", vec.get(0));
        assertEquals("mama", vec.get(1));
        assertEquals("mia", vec.get(2));
        assertNull(vec.get(3));
    }

    @Test
    public void testRemove() {
        SparseVec<String> vec = new SparseVecTree<>();
        vec.add("pizza");
        vec.add("mama");
        vec.add("mia");

        vec.removeAt(1);
        assertEquals(2, vec.size());
        assertNull(vec.get(1));
        assertEquals("mia", vec.get(2));

        vec.removeElem("mia");
        assertEquals(1, vec.size());
        assertNull(vec.get(2));
    }


}
