package ua.edu.ucu.iterators;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.TrieNode;
import ua.edu.ucu.utils.Queue;

public class TrieIterable implements Iterable<String> {
    private RWayTrie iterTrie;
    private String iterPrefix = "";

    public TrieIterable(RWayTrie trie) {
        iterTrie = trie;
    }

    public TrieIterable(RWayTrie trie, String prefix) {
        iterTrie = trie;
        iterPrefix = prefix;
    }

    @Override
    public TrieIterator iterator() {
        if (iterPrefix.equals("")) {
            return new TrieIterator(iterTrie);
        } else {
            return new TrieIterator(iterTrie, iterPrefix);
        }
    }
}

class TrieIterator implements java.util.Iterator<String> {
    private RWayTrie iterTrie;
    private TrieNode currentNode;
    private Queue queue = new Queue();
    private String iterPrefix = "";
    private boolean returnedPrefix = false;
    private boolean shouldReturnPrefix;

    public TrieIterator(RWayTrie trie) {
        iterTrie = trie;
        currentNode = iterTrie.getRoot();
        initializeQueue();
    }

    public TrieIterator(RWayTrie trie, String prefix) {
        iterTrie = trie;
        iterPrefix = prefix;
        currentNode = iterTrie.get(prefix);
        shouldReturnPrefix = iterTrie.contains(prefix);
        initializeQueue();
    }

    private void initializeQueue() {
        Object[] keys = currentNode.next.keySet().toArray();
        for (Object key : keys) {
            String strKey = String.valueOf(key);
            queue.enqueue(strKey.charAt(strKey.length() - 1));
        }
    }

    @Override
    public boolean hasNext() {
        return queue.size() != 0;
    }

    @Override
    public String next() {
        if(!returnedPrefix && shouldReturnPrefix){
            returnedPrefix = true;
            return iterPrefix;
        }
        while (true) {
            String queueValue = String.valueOf(queue.dequeue());
            String currentWord = iterPrefix + queueValue;
            TrieNode node;
            if (iterTrie.contains(currentWord)) {
                node = iterTrie.get(currentWord);
            } else {
                node = iterTrie.get(queueValue);
                currentWord = queueValue;
            }
            Object[] keys = node.next.keySet().toArray();
            for (Object key : keys) {
                queue.enqueue(currentWord + String.valueOf(key));
            }
            if (node.getEndWord()) {
                return currentWord;
            }
        }
    }
}
