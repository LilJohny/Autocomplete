package ua.edu.ucu.utils.immutable;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.utils.immutable.ImmutableLinkedList;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private Object[] simpleArray;
    private Object[] simpleArrayContinued;
    private Object[] longArray;
    private Object[] simpleArrayFirst;
    private Object[] simpleArrayLast;
    private Object[] simpleArrayExtended;
    private Object[] insertedArray;
    private Object[] insertedArrayRemoved;
    private Object[] insertedOutOfRangeArray;

    @Before
    public void setUp() {
        simpleArray = new Object[]{1, 2, 3};
        simpleArrayFirst = new Object[]{2,3};
        simpleArrayLast = new Object[]{1,2};
        simpleArrayExtended = new Object[]{0,1,2,3};
        longArray = new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        insertedArray = new Object[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3};
        insertedArrayRemoved = new Object[]{ 1, 2, 3, 5, 6, 7, 8, 9, 10, 2, 3};
        simpleArrayContinued = new Object[]{1, 2, 3, 4};
        insertedOutOfRangeArray = new Object[]{1, 2, 3, null, null, null, null, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3};
    }
    @Test
    public void testRemoveFirst() {
        ImmutableLinkedList list = new ImmutableLinkedList(simpleArray);
        ImmutableLinkedList result = list.removeFirst();
        assertArrayEquals(result.toArray(), simpleArrayFirst);
    }
    @Test
    public void testRemoveLast() {
        ImmutableLinkedList list = new ImmutableLinkedList(simpleArray);
        ImmutableLinkedList result = list.removeLast();
        assertArrayEquals(result.toArray(), simpleArrayLast);
    }
    @Test
    public void testAddFirst(){
        ImmutableLinkedList list = new ImmutableLinkedList(simpleArray);
        ImmutableLinkedList result = list.addFirst(0);
        assertArrayEquals(result.toArray(), simpleArrayExtended);
    }
    @Test
    public void testAddLast(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        ImmutableLinkedList result = list.addLast(2);
        result.size();
        result = result.addLast(3);
        assertArrayEquals(result.toArray(), insertedArray);
    }
    @Test
    public void testGetFirst(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        Object first = list.getFirst();
        assertEquals(first, 1);
    }
    @Test
    public void testGetFirstNull(){
        ImmutableLinkedList list = new ImmutableLinkedList();
        Object first = list.getFirst();
        assertNull(first);
    }
    @Test
    public void testGetLast(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        Object last = list.getLast();
        assertEquals(last, 10);
    }
    @Test
    public void testGetLastNull(){
        ImmutableLinkedList list = new ImmutableLinkedList();
        Object last = list.getLast();
        assertNull(last);
    }
    @Test
    public void testGet(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        Object element = list.get(2);
        assertEquals(element, 3);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException(){
        ImmutableLinkedList list = new ImmutableLinkedList();
        Object element = list.get(2);
    }
    @Test
    public void testIndexOf(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        int index = list.indexOf(3);
        assertEquals(index, 2);
    }
    @Test
    public void testIndexOfOutOfBounds(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        int index = list.indexOf(11);
        assertEquals(index, -1);
    }
    @Test
    public void IsEmpty(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        assertFalse(list.isEmpty());
    }
    @Test
    public void testClear(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        list = (ImmutableLinkedList) list.clear();
        boolean result = list.isEmpty();
        assertTrue(result);
    }
    @Test
    public void testSet(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        list = (ImmutableLinkedList) list.set(0, 5);
        assertEquals(list.get(0), 5);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAll(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        list = (ImmutableLinkedList) list.addAll(20, new Object[]{1,2,3});
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetException(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        list = (ImmutableLinkedList) list.set(20, 5);
    }
    @Test
    public void testAddAllEnd(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        list  = (ImmutableLinkedList) list.addAll(new Object[]{2,3});
        assertArrayEquals(list.toArray(), insertedArray);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveException(){
        ImmutableLinkedList list = new ImmutableLinkedList(longArray);
        list = (ImmutableLinkedList) list.remove(16);
    }
    @Test
    public void testRemove(){
        ImmutableLinkedList list = new ImmutableLinkedList(insertedArray);
        ImmutableLinkedList result = (ImmutableLinkedList) list.remove(3);
        assertArrayEquals(result.toArray(), insertedArrayRemoved);
    }
}
