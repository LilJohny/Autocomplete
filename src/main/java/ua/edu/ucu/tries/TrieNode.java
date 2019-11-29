package ua.edu.ucu.tries;

import java.util.*;

public final class TrieNode {
    public HashMap<Character, TrieNode> next = new HashMap<>();
    public char value;
    private boolean endWord;
    private TrieNode parent;

    public TrieNode() {
    }

    public TrieNode getParent() {
        return parent;
    }

    public void setParent(TrieNode parent) {
        this.parent = parent;
    }


    public boolean getEndWord() {
        return endWord;
    }

    public void setEndWord(boolean value) {
        endWord = value;
    }

    public boolean hasNext() {
        return !next.isEmpty();
    }
    public boolean containsOneWord(){
        TrieNode node = this;
        while (node.hasNext()){
            if(node.next.size() !=1){
                return  false;
            }
            node = (TrieNode) node.next.values().toArray()[0];
        }
        return true;
    }

}
