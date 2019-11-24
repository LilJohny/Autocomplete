package ua.edu.ucu.tries;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class RWayTrie implements Trie {
    private TrieNode root;
    private int wordsAmount;

    public RWayTrie() {
        root = new TrieNode();
        wordsAmount = 0;
    }

    @Override
    public void add(Tuple tuple) {
        tuple = new Tuple(tuple.term + "\n", tuple.weight + 1);
        root = insert(root, tuple, 0);
        wordsAmount += 1;
    }

    private TrieNode insert(TrieNode node, Tuple tuple, int destination) {
        if (node == null) {
            node = new TrieNode();
        }
        if (destination == tuple.weight) {
            node.setValue(true);
            return node;
        }
        char c = tuple.term.charAt(destination);
        node.getNext()[c] = insert(node.getNext()[c], tuple, destination + 1);
        return node;
    }


    @Override
    public boolean contains(String word) {
        word += "\n";
        TrieNode start = search(root, word, 0);
        return start != null && start.getValue();
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
            word += "\n";
            TrieNode[] nodes = root.getNext();
            int i = 0;
            while (!nodes[word.charAt(i)].containsOneWord()) {
                nodes = nodes[word.charAt(i)].getNext();
                i++;
                boolean fl = nodes[word.charAt(i)].containsOneWord();
            }
            nodes[i] = null;
            wordsAmount -= 1;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterable<String> words() {
        ArrayList<String> words = new ArrayList<>();
        TrieNode[] nodes = root.getNext();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                int finalI = i;
                List<String> wordsNode = nodes[i].getSuffixes().stream().map(word -> (char) finalI + word).collect(Collectors.toList());
                words.addAll(wordsNode);
            }
        }
        return words.stream().map(word -> word.replace("\n", "")).collect(Collectors.toList());
    }


    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Iterable<String> wordsIterator = words();
        String[] prefixWords = StreamSupport.stream(wordsIterator.spliterator(), false).filter(word -> word.startsWith(s)).toArray(String[]::new);
        return Arrays.asList(prefixWords);
    }

    @Override
    public int size() {
        return wordsAmount;
    }

}

class TrieNode {
    private final static int R = 256;
    private TrieNode[] next = new TrieNode[R];

    private boolean value;

    public TrieNode() {
    }

    public TrieNode[] getNext() {
        return next;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean hasNext() {
        Object[] filteredNodes = Arrays.stream(getNext()).filter(Objects::nonNull).toArray();
        return filteredNodes.length != 0;
    }

    public boolean containsOneWord() {
        TrieNode node = this;
        while (node.hasNext()) {
            Object[] filteredNodes = Arrays.stream(node.getNext()).filter(Objects::nonNull).toArray();
            if (filteredNodes.length == 1) {
                TrieNode[] nodes = node.getNext();
                for (int i = 0; i < nodes.length; i++) {
                    if (nodes[i] != null) {
                        node = nodes[i];
                        break;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getSuffixes() {
        if (!containsOneWord()) {
            return getSuffixMany();
        } else {
            ArrayList<String> suffixes = new ArrayList<String>();
            suffixes.add(getSuffixSingle());
            return suffixes;
        }
    }

    private ArrayList<String> getSuffixMany() {
        ArrayList<String> suffixes = new ArrayList<String>();
        TrieNode[] nodes = this.getNext();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null && nodes[i].containsOneWord()) {
                suffixes.add((char) i + nodes[i].getSuffixSingle());
            } else if (nodes[i] != null && !(nodes[i].containsOneWord())) {
                ArrayList<String> intermediateSuffixes = nodes[i].getSuffixMany();
                int finalI = i;
                List<String> fixedIntermediateSuffixes = intermediateSuffixes.stream().map(s -> (char) finalI + s).collect(Collectors.toList());
                suffixes.addAll(fixedIntermediateSuffixes);
            }
        }
        return suffixes;
    }

    private String getSuffixSingle() {
        TrieNode node = this;
        TrieNode[] nextNodes = node.getNext();
        StringBuilder suffix = new StringBuilder();
        while (node.hasNext()) {
            for (int i = 0; i < nextNodes.length; i++) {
                if (nextNodes[i] != null) {
                    suffix.append((char) i);
                    node = nextNodes[i];
                    nextNodes = node.getNext();
                    break;
                }
            }
        }
        return suffix.toString();
    }

}

