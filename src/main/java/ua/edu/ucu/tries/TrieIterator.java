package ua.edu.ucu.tries;

import java.util.ArrayList;
import java.util.Iterator;

public class TrieIterator implements Iterable<String> {
    private ArrayList<String> container;

    public TrieIterator(ArrayList<String> arrayList) {
        container = arrayList;
    }

    @Override
    public Iterator<String> iterator() {
        return container.listIterator();
    }

}
