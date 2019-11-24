package ua.edu.ucu.utils.immutable;

public final class ImmutableArrayList implements ImmutableList {
    private Object[] array;
    private int length;


    public ImmutableArrayList(Object[] objects) {
        array = new Object[objects.length];
        System.arraycopy(objects, 0, array, 0, objects.length);
        length = objects.length;
    }

    public ImmutableArrayList() {
        array = new Object[0];
        length = array.length;
    }

    @Override
    public ImmutableArrayList add(Object e) {
        return addAll(new Object[]{e});
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return addAll(length, c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        int newLength;
        if (index > length) {
            newLength = index + c.length;
        } else {
            newLength = length + c.length;
        }
        Object[] returnArray = new Object[newLength];
        int first = Math.min(index, length);
        System.arraycopy(array, 0, returnArray, 0, first);
        System.arraycopy(c, 0, returnArray, index, c.length);
        int last = length - first;
        if (last != 0) {
            System.arraycopy(array, index, returnArray, index + c.length, last);
        }
        return new ImmutableArrayList(returnArray);
    }

    @Override
    public Object get(int index) {
        if (length - index > 0) {
            return array[index];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableArrayList remove(int index) {
        if (index < this.length) {
            Object[] newArray = new Object[this.array.length - 1];
            System.arraycopy(this.array, 0, newArray, 0, index);
            int copyStart = index + 1;
            int copyEnd = this.array.length - 1 - (index);
            System.arraycopy(this.array, copyStart, newArray, index, copyEnd);
            return new ImmutableArrayList(newArray);
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index < this.length) {
            ImmutableArrayList returnObject = new ImmutableArrayList(array);
            returnObject.array[index] = e;
            return returnObject;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList(new Object[]{});
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] returnArray = new Object[length];
        System.arraycopy(array, 0, returnArray, 0, length);
        return returnArray;
    }
}
