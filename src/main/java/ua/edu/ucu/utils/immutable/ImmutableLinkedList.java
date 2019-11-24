package ua.edu.ucu.utils.immutable;

public final class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private int length;


    public ImmutableLinkedList(ImmutableLinkedList linkedList) {
        if (linkedList.length == 1) {
            Node node = new Node(linkedList.head);
            head = node;
            length = 1;
        } else {
            head = new Node(linkedList.head);
            Node previousNode = head;
            Node currentNode = linkedList.head.getNext();
            Node newNode = null;
            while (currentNode.getNext() != null) {
                newNode = new Node(currentNode);
                previousNode.setNext(newNode);
                newNode.setPrevious(previousNode);
                previousNode = newNode;
                currentNode = currentNode.getNext();
            }
            tail = new Node(currentNode);
            tail.setPrevious(previousNode);
            length = linkedList.size();
        }
    }


    public ImmutableLinkedList(Object[] objects) {
        Node[] nodes = convertToNodes(objects);
        head = nodes[0];
        if (objects.length != 1) {
            tail = nodes[nodes.length - 1];
        }
        length = objects.length;
    }

    public ImmutableLinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    private Node[] convertToNodes(Object[] objects) {
        Node[] nodes = new Node[objects.length];
        for (int i = 0; i < objects.length; i++) {
            nodes[i] = new Node(objects[i]);
            if (i - 1 >= 0) {
                nodes[i - 1].setNext(nodes[i]);
            }
        }
        return nodes;
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(e);
    }

    public Object getFirst() {
        if (head == null) {
            return null;
        }
        return head.getValue();
    }

    public Object getLast() {
        if (tail == null && head == null) {
            return null;
        } else if (tail == null) {
            return head.getValue();
        }
        return tail.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(this);
        linkedList.head = linkedList.head.getNext();
        if (linkedList.head != null) {
            linkedList.head.setPrevious(null);
        }
        linkedList.length--;
        return linkedList;
    }

    public ImmutableLinkedList removeLast() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(this);
        linkedList.tail = linkedList.tail.getPrevious();
        linkedList.tail.setNext(null);
        linkedList.length--;
        return linkedList;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(size(), e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        Object[] objectArray = new Object[]{e};
        return addAll(index, objectArray);
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size(), c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        if (this.length == 0) {
            ImmutableLinkedList linkedList = new ImmutableLinkedList(c);
            return linkedList;
        }
        if (index <= this.length) {
            ImmutableLinkedList linkedList = new ImmutableLinkedList(this);
            Node[] nodes = convertToNodes(c);
            if (index + c.length < linkedList.length) {
                Node finishNode = linkedList.getNode(index + c.length - 1);
                finishNode.setPrevious(nodes[nodes.length - 1]);
                nodes[nodes.length - 1].setNext(finishNode);
            }
            if (index != 0) {
                Node startNode = linkedList.getNode(index - 1);
                startNode.setNext(nodes[0]);
                nodes[0].setPrevious(startNode);
            } else {
                nodes[nodes.length - 1].setNext(linkedList.head);
                linkedList.head = nodes[nodes.length - 1];
            }

            linkedList.length += c.length;
            if (linkedList.tail == null) {
                linkedList.tail = nodes[nodes.length - 1];
            } else if (index == size() - 1) {
                nodes[nodes.length - 1].setNext(linkedList.tail);
            }
            return linkedList;
        }
        throw new IndexOutOfBoundsException();

    }

    @Override
    public Object get(int index) {
        if (index < this.size()) {
            int currentIndex = 0;
            Node currentNode = this.head;
            while (currentIndex != index) {
                currentNode = currentNode.getNext();
                currentIndex++;
            }
            return currentNode.getValue();
        }
        throw new IndexOutOfBoundsException();
    }

    private Node getNode(int index) {
        if (index < this.size()) {
            int currentIndex = 0;
            Node currentNode = this.head;
            while (currentIndex != index) {
                currentNode = currentNode.getNext();
                currentIndex++;
            }
            return currentNode;
        }
        throw new IndexOutOfBoundsException();
    }


    @Override
    public ImmutableLinkedList remove(int index) {
        if (index < size()) {
            ImmutableLinkedList linkedList = new ImmutableLinkedList(this);
            Node currentNode = (Node) linkedList.getNode(index);
            Node parent = currentNode.getPrevious();
            Node child = currentNode.getNext();
            parent.setNext(child);
            child.setPrevious(parent);
            linkedList.length--;
            return linkedList;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        if (index < size()) {
            ImmutableLinkedList linkedList = new ImmutableLinkedList(this);
            Node currentNode = (Node) linkedList.getNode(index);
            currentNode.setValue(e);
            return linkedList;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int indexOf(Object e) {
        int index = 0;
        Node currentNode = this.head;
        while (index != this.size()) {
            if (currentNode.getValue().equals(e)) {
                return index;
            } else {
                index++;
                currentNode = currentNode.getNext();
            }
        }
        if (index == this.size()) {
            index = -1;
        }
        return index;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node currentNode = head;
        int i = 0;
        while (currentNode != null) {
            array[i] = currentNode.getValue();
            i++;
            currentNode = currentNode.getNext();
        }
        return array;
    }
}
