package ua.edu.ucu.tries;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class RWayTrieTest {
    @Test
    public void testSize(){
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        int s = trie.size();
        Assert.assertEquals(s, 3);
    }
    @Test
    public void testContains(){
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        Assert.assertTrue(trie.contains("tail"));
    }
    @Test
    public void testContainsSecond(){
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        Assert.assertTrue(trie.contains("hi"));
    }
    @Test
    public void testContainsThird(){
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        Assert.assertTrue(trie.contains("hello"));
    }
    @Test
    public void testDelete(){
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hell"), "hell".length()));
        Assert.assertTrue(trie.delete("hell"));
        Assert.assertTrue(trie.contains("hello"));
        ArrayList<String> w = (ArrayList<String>) trie.words();
    }
    @Test
    public void testWords(){
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("hit"), "hit".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        trie.add(new Tuple(("Hitler"), "Hitler".length()));
        trie.words();
    }
    @Test
    public void testPrefixWords(){
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("hit"), "hit".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        trie.wordsWithPrefix("he");
        int k = 0;
    }
}
