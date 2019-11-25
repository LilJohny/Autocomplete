package ua.edu.ucu.tries;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class TrieNodeTest {
    @Test
    public void testSuffixes(){
        TrieNode node = new TrieNode();
        TrieNode [] nodes = node.getNext();
        nodes['a'] = new TrieNode();
        nodes['a'].getNext()['b']= new TrieNode();
        nodes['b'] = new TrieNode();
        ArrayList<String> result = node.getSuffixes();
        String [] expresult = {"ab","b"};
        Assert.assertArrayEquals(result.toArray(), expresult);
    }
}
