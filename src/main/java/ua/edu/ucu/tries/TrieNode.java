package ua.edu.ucu.tries;

import java.util.*;

public final class TrieNode {
    public HashMap<Character, TrieNode> next = new HashMap<>();
    private boolean endWord;

    public TrieNode() {
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
