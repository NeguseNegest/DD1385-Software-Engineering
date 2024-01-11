package labb6;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class SparseVecTest {

    @Test
    public void testEmpty() {
        SparseVec<Integer> myVec = new MySparseVec<>();
        
        assertEquals(0, myVec.size());
        assertEquals(-1, myVec.minIndex());
        assertEquals(-1, myVec.maxIndex());

        assertNull(myVec.get(0));
        assertNull(myVec.get(1));

        assertArrayEquals(new Object[0], myVec.toArray());
        assertEquals(0, myVec.sortedValues().size());
    }

    @Test
    public void testIndex() {
        SparseVec<String> myVec = new MySparseVec<>();
        myVec.add("a");
        myVec.add("b");
        myVec.add("c");

        assertEquals(3, myVec.size());
        assertEquals(0, myVec.minIndex());
        assertEquals(2, myVec.maxIndex());

        assertNull(myVec.get(-1));
        assertNull(myVec.get(3));
    }

    @Test
    public void testInsertAtPosition() {
        SparseVec<Integer> myVec = new MySparseVec<>();
        
        myVec.add(0, 1);
        assertEquals(1, myVec.size());
        assertEquals(0, myVec.minIndex());
        assertEquals(0, myVec.maxIndex());

        myVec.add(2, 2);
        assertEquals(2, myVec.size());
        assertEquals(0, myVec.minIndex());
        assertEquals(2, myVec.maxIndex());

        myVec.add(1, 3);
        assertEquals(3, myVec.size());
        assertEquals(0, myVec.minIndex());
        assertEquals(2, myVec.maxIndex());

        assertEquals(3, (int) myVec.get(1));
    }

    @Test
    public void testAddAtUnspecifiedPosition() {
        SparseVec<String> myVec = new MySparseVec<>();
        
        myVec.add("a");
        myVec.add("b");
        myVec.add("c");

        assertEquals(3, myVec.size());
        assertEquals("a", myVec.get(0));
        assertEquals("b", myVec.get(1));
        assertEquals("c", myVec.get(2));
        assertNull(myVec.get(3));
    }

    @Test
    public void testRemove() {
        SparseVec<String> myVec = new MySparseVec<>();
        myVec.add("a");
        myVec.add("b");
        myVec.add("c");

        myVec.removeAt(1);
        assertEquals(2, myVec.size());
        assertNull(myVec.get(1));
        assertEquals("c", myVec.get(2));

        myVec.removeElem("c");
        assertEquals(1, myVec.size());
        assertNull(myVec.get(2));
    }

    @Test
    public void testMainMethod() {
        SparseVec<Integer> myVec = new MySparseVec<>();
        myVec.add(5);
        myVec.add(2, 8);
        myVec.add(15, 3);
        myVec.add(10, 1);
        myVec.add(7, 6);

        assertEquals("Index: 0 Value: 5\nIndex: 2 Value: 8\nIndex: 7 Value: 6\nIndex: 10 Value: 1\nIndex: 15 Value: 3",
                myVec.toString());

        assertArrayEquals(new Object[]{5, null, 8, null, null, null, null, 6, null, null, 1, null, null, null, null, 3},
                myVec.toArray());

        assertEquals(List.of(1, 3, 5, 6, 8), myVec.sortedValues());
    }
}
