package ua.edu.ucu.iterators;

import java.util.Iterator;

public class KTrieIterable implements Iterable<String> {
    private Iterable<String> trieIterable;
    private int limit;
    public KTrieIterable(Iterable<String> iterable, int k){
        trieIterable = iterable;
        limit = k;
    }
    @Override
    public Iterator<String> iterator() {
        return new KTrieIterator(trieIterable, limit);
    }
}

class KTrieIterator implements Iterator<String> {
    private String nextWord = null;
    private Iterator<String> trieIterator;
    private int limit;
    private int previousLength;

    public KTrieIterator(Iterable<String> iterable, int k) {
        trieIterator = iterable.iterator();
        limit = k;
        nextWord = "";
    }

    @Override
    public boolean hasNext() {
        return trieIterator.hasNext() && limit!=0;
    }
    private void setNext(){
        if (!trieIterator.hasNext()){
            nextWord = null;
            return;
        }
        String word = trieIterator.next();
        if (word.length()!= previousLength){
            previousLength = word.length();
            limit--;
        }
        if(limit <0){
            nextWord = null;
        } else {
            nextWord = word;
        }
    }
    @Override
    public String next() {
        setNext();
        return nextWord;
    }
}
