package ua.edu.ucu.tries;

import org.junit.Assert;
import org.junit.Test;

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
        trie.add(new Tuple(("hi"), "hi".length()));
        Assert.assertTrue(trie.delete("hello"));
        Assert.assertTrue(trie.contains("hi"));

    }
    @Test
    public void testWords(){
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("hit"), "hit".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        trie.add(new Tuple(("hitler"), "hitler".length()));
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
