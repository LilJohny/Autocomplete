package ua.edu.ucu.tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class TrieNode {
    private TrieNode[] next = new TrieNode[R];
    private boolean flag;
    private static final int R = 123;

    public TrieNode() {
    }

    public TrieNode[] getNext() {
        return next;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean value) {
        flag = value;
    }

    public boolean hasNext() {
        Object[] filteredNodes = Arrays.stream(getNext())
                .filter(Objects::nonNull).toArray();
        return filteredNodes.length != 0;
    }

    public boolean containsOneWord() {
        TrieNode node = this;
        while (node.hasNext()) {
            Object[] filteredNodes = Arrays.stream(node.getNext())
                    .filter(Objects::nonNull).toArray();
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
                ArrayList<String> intermediateSuffixes = nodes[i]
                        .getSuffixMany();
                int finalI = i;
                List<String> fixedIntermediateSuffixes = intermediateSuffixes
                        .stream()
                        .map(s -> (char) finalI + s)
                        .collect(Collectors.toList());
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
