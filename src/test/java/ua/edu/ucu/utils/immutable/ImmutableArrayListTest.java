package ua.edu.ucu.utils.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public  class ImmutableArrayListTest {
    private Object[] simpleArray;
    private Object[] simpleArrayContinued;
    private Object[] longArray;
    private Object[] insertedArray;
    private Object[] insertedArrayRemoved;
    private Object[] insertedOutOfRangeArray;

    @Before
    public void setUp() {
        simpleArray = new Object[]{1, 2, 3};
        longArray = new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        insertedArray = new Object[]{1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3};
        insertedArrayRemoved = new Object[]{1, 1, 2, 3, 5, 6, 7, 8, 9, 10, 2, 3};
        simpleArrayContinued = new Object[]{1, 2, 3, 4};
        insertedOutOfRangeArray = new Object[]{1, 2, 3, null, null, null, null, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3};
    }

    @Test
    public void testAddAllIndexSimple() {
        ImmutableArrayList array = new ImmutableArrayList(new Object[]{});
        ImmutableArrayList result = (ImmutableArrayList) array.addAll(0, simpleArray);
        assertArrayEquals(result.toArray(), simpleArray);
    }

    @Test
    public void testAddAllIndexLong() {
        ImmutableArrayList array = new ImmutableArrayList(new Object[]{});
        ImmutableArrayList result = (ImmutableArrayList) array.addAll(0, simpleArray);
        result = (ImmutableArrayList) result.addAll(1, longArray);
        assertArrayEquals(result.toArray(), insertedArray);
    }

    @Test
    public void testAddAllIndexFar() {
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        ImmutableArrayList result = (ImmutableArrayList) array.addAll(1, longArray);
        assertArrayEquals(result.toArray(), insertedArray);
    }

    @Test
    public void testRemove() {
        ImmutableArrayList array = new ImmutableArrayList(insertedArray);
        ImmutableArrayList result = (ImmutableArrayList) array.remove(4);
        assertArrayEquals(result.toArray(), insertedArrayRemoved);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveException() {
        ImmutableArrayList array = new ImmutableArrayList(insertedArray);
        ImmutableArrayList result = (ImmutableArrayList) array.remove(15);
    }

    @Test
    public void testAddIndex() {
        ImmutableArrayList array = new ImmutableArrayList(insertedArrayRemoved);
        ImmutableArrayList result = (ImmutableArrayList) array.add(4, 4);
        assertArrayEquals(result.toArray(), insertedArray);
    }

    @Test
    public void testAdd() {
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        ImmutableArrayList result = (ImmutableArrayList) array.add(4);
        assertArrayEquals(result.toArray(), simpleArrayContinued);
    }
    @Test
    public void testAddAllOutOfRange() {
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        ImmutableArrayList result = (ImmutableArrayList) array.addAll(7, insertedArray);
        assertArrayEquals(result.toArray(), insertedOutOfRangeArray);

    }
    @Test
    public  void testIsEmptyFalse(){
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        assertFalse(array.isEmpty());
    }
    @Test
    public  void testIsEmptyTrue(){
        ImmutableArrayList array = new ImmutableArrayList(new Object[]{});
        assertTrue(array.isEmpty());
    }
    @Test
    public void testClear(){
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        ImmutableArrayList result = (ImmutableArrayList) array.clear();
        assertTrue(result.isEmpty());
    }
    @Test
    public void testSize(){
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        assertEquals(array.size(), 3);
    }
    @Test
    public void testSizeInserted(){
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        ImmutableArrayList result = (ImmutableArrayList) array.add(5);
        assertEquals(result.size(), 4);
    }
    @Test
    public void testGet(){
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        Object result = array.get(0);
        assertEquals(result, 1);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetexception(){
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        Object result = array.get(5);
    }
    @Test
    public void testSet(){
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        ImmutableArrayList result = (ImmutableArrayList) array.set(0, 4);
        assertEquals(result.get(0), 4);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetException(){
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        ImmutableArrayList result = (ImmutableArrayList) array.set(5, 4);
    }
    @Test
    public void testIndexOf(){
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        int index = array.indexOf(2);
        assertEquals(index, 1);
    }
    @Test
    public void testIndexOfNoElement(){
        ImmutableArrayList array = new ImmutableArrayList(simpleArray);
        int index = array.indexOf(5);
        assertEquals(index, -1);
    }
    @Test
    public void testCreateEmpty(){
        ImmutableArrayList array = new ImmutableArrayList();
        assertTrue(array.isEmpty());
    }
}
