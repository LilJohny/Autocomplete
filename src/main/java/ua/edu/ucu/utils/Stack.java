package ua.edu.ucu.utils;
import ua.edu.ucu.utils.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList list;
    public Stack() {
        list = new ImmutableLinkedList();
    }
    public Object peek() {
        return list.getLast();
    }

    public Object pop() {
        Object last = list.getLast();
        list = list.removeLast();
        return last;
    }

    public void push(Object e) {
        list = list.addLast(e);

    }

}
