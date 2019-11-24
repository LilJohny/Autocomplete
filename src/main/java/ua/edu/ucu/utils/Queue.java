package ua.edu.ucu.utils;

import ua.edu.ucu.utils.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList list;
    public Queue() {
        list = new ImmutableLinkedList();
    }
    public Object peek() {
        return list.getFirst();
    }

    public Object dequeue() {
        Object first = list.getFirst();
        list = list.removeFirst();
        return first;
    }

    public void enqueue(Object e) {
        list = list.addLast(e);
    }

}
