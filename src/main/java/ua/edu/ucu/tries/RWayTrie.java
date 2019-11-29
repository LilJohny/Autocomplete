package ua.edu.ucu.tries;

import ua.edu.ucu.iterators.TrieIterable;


public class RWayTrie implements Trie {
    private TrieNode root;
    private int wordsAmount;

    public RWayTrie() {
        root = new TrieNode();
        wordsAmount = 0;
    }


    public TrieNode getRoot() {
        return root;
    }

    @Override
    public void add(Tuple tuple) {
        root = insert(root, tuple, 0);
        wordsAmount += 1;
    }

    private TrieNode insert(TrieNode node, Tuple tuple, int destination) {
        if (node == null) {
            node = new TrieNode();
        }
        if (destination == tuple.weight) {
            node.setEndWord(true);
            return node;
        }
        char c = tuple.term.charAt(destination);
        TrieNode insertValue = insert(node.next.get(c), tuple, destination + 1);
        node.next.putIfAbsent(c, insertValue);
        return node;
    }


    @Override
    public boolean contains(String word) {
        TrieNode start = search(root, word, 0);
        return start != null && start.getEndWord();
    }

    private TrieNode search(TrieNode node, String word, int i) {
        if (node == null) {
            return null;
        }
        if (i == word.length()) {
            return node;
        }
        char character = word.charAt(i);
        return search(node.next.get(character), word, i + 1);
    }

    @Override
    public boolean delete(String word) {
        if (!contains(word)) {
            return false;
        }
        TrieNode node = this.root;
        int index = 0;
        while (node.hasNext()) {
            if (!node.getEndWord()) {
                node = node.next.get(word.charAt(index));
                index++;
            } else if (!node.next.isEmpty()) {
                node.setEndWord(false);
                wordsAmount--;
                break;
            } else {
                node = null;
                wordsAmount--;
                break;
            }
        }
        return true;
    }
    public TrieNode get(String word){
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if(node == null){
                return null;
            }
            node = node.next.get(word.charAt(i));
        }
        return node;
    }
    @Override
    public Iterable<String> words() {
        return new TrieIterable(this);
    }


    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        return new TrieIterable(this, pref);
    }

    @Override
    public int size() {
        return wordsAmount;
    }

}

