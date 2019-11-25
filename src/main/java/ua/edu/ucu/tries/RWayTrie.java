package ua.edu.ucu.tries;


import ua.edu.ucu.utils.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.StreamSupport;


public class RWayTrie implements Trie {
    private static final int ENDLINE = 10;
    private TrieNode root;
    private int wordsAmount;

    public RWayTrie() {
        root = new TrieNode();
        wordsAmount = 0;
    }

    @Override
    public void add(Tuple tuple) {
        Tuple escapedTuple = new Tuple(tuple.term + "\n", tuple.weight + 1);
        root = insert(root, escapedTuple, 0);
        wordsAmount += 1;
    }

    private TrieNode insert(TrieNode node, Tuple tuple, int destination) {
        if (node == null) {
            node = new TrieNode();
        }
        if (destination == tuple.weight) {
            node.setFlag(true);
            return node;
        }
        char c = tuple.term.charAt(destination);
        node.getNext()[c] = insert(node.getNext()[c], tuple, destination + 1);
        return node;
    }


    @Override
    public boolean contains(String word) {
        String escapedWord = word+"\n";
        TrieNode start = search(root, escapedWord, 0);
        return start != null && start.getFlag();
    }

    private TrieNode search(TrieNode node, String word, int i) {
        if (node == null) {
            return null;
        }
        if (i == word.length()) {
            return node;
        }
        char c = word.charAt(i);
        return search(node.getNext()[c], word, i + 1);
    }

    @Override
    public boolean delete(String word) {
        if (contains(word)) {
            //word += "\n";
            String escapedWord = word+"\n";
            TrieNode[] nodes = root.getNext();
            int i = 0;
            while (!nodes[escapedWord.charAt(i)].containsOneWord()) {
                nodes = nodes[escapedWord.charAt(i)].getNext();
                i++;
            }
            nodes[escapedWord.charAt(i)] = null;
            wordsAmount -= 1;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterable<String> words() {
        Queue queue = new Queue();
        TrieNode[] nodes = root.getNext();
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                queue.enqueue(i);
            }
        }
        while (queue.size() != 0) {
            String stringCurrentIndex = "";
            Object currentIndex = queue.dequeue();
            if (currentIndex instanceof String) {
                stringCurrentIndex = (String) currentIndex;
                currentIndex = stringCurrentIndex
                        .charAt(stringCurrentIndex.length() - 1);
                TrieNode[] rootNodes = root.getNext();
                nodes = rootNodes;
                for (int i = 0; i < stringCurrentIndex.length() - 1; i++) {
                    nodes = nodes[stringCurrentIndex.charAt(i)].getNext();
                }
                currentIndex = (int) ((Character) currentIndex).charValue();
            }
            TrieNode[] currentNodes = null;
            int index = ((Integer) currentIndex).intValue();
            if (nodes[index] != null) {
                currentNodes = nodes[index].getNext();
            } else {
                result.add(stringCurrentIndex);
                continue;
            }
            for (int i = 0; i < currentNodes.length; i++) {
                if (currentNodes[i] != null) {
                    if (stringCurrentIndex.equals("")) {
                        String seq = (String.valueOf((char) index)
                                + (String.valueOf((char) i)));
                        queue.enqueue(seq);
                    } else {
                        String seq = stringCurrentIndex
                                + (String.valueOf((char) i));
                        queue.enqueue(seq);
                        if (i == ENDLINE) {
                            result.add(stringCurrentIndex);
                        }
                    }
                }
            }
        }
        return  new TrieIterator(result);
    }


    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Iterable<String> wordsIterator = words();
        String[] prefixWords = StreamSupport
                .stream(wordsIterator.spliterator(), false)
                .filter(word -> word.startsWith(s)).toArray(String[]::new);
        return (Iterable<String>) Arrays.asList(prefixWords);
    }

    @Override
    public int size() {
        return wordsAmount;
    }

}

