package ua.edu.ucu.tries;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class RWayTrieTest {
    @Test
    public void testSize() {
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        int s = trie.size();
        Assert.assertEquals(s, 3);
    }

    @Test
    public void testContains() {
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        Assert.assertTrue(trie.contains("tail"));
    }

    @Test
    public void testContainsSecond() {
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        Assert.assertTrue(trie.contains("hi"));
    }

    @Test
    public void testContainsThird() {
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        Assert.assertTrue(trie.contains("hello"));
    }

    @Test
    public void testDelete() {
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hell"), "hell".length()));
        Assert.assertTrue(trie.delete("hell"));
        Assert.assertTrue(trie.contains("hello"));
        assertFalse(trie.contains("hell"));
    }

    @Test
    public void testWords() {
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("hit"), "hit".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        trie.add(new Tuple(("Hitler"), "Hitler".length()));
        Iterable<String> wordsAll = trie.words();
        String[] expResult = {"hi","hit","tail","hello","Hitler"};
        assertThat(wordsAll, containsInAnyOrder(expResult));
    }

    @Test
    public void testPrefixWords() {
        RWayTrie trie = new RWayTrie();

        trie.add(new Tuple(("hello"), "hello".length()));
        trie.add(new Tuple(("hi"), "hi".length()));
        trie.add(new Tuple(("hit"), "hit".length()));
        trie.add(new Tuple(("tail"), "tail".length()));
        Iterable<String> result = trie.wordsWithPrefix("he");
        String[] expResult = {"hello"};
        assertThat(result, containsInAnyOrder(expResult));
    }
}
