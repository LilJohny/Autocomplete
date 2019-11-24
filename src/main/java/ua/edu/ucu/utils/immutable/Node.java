package ua.edu.ucu.utils.immutable;

final class Node {
    private Object value;
    private Node next;
    private Node previous;

    Node(Object value) {
        this.value = value;
    }

    Node(Node node) {
        value = node.value;
        next = node.next;
        previous = node.previous;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object data) {
        value = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node nextVal) {
        next = nextVal;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previousVal) {
        previous = previousVal;
    }


}
