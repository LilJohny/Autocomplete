package ua.edu.ucu.autocomplete;

import ua.edu.ucu.iterators.KTrieIterable;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;




public class PrefixMatches {

    private Trie dictTrie;

    public PrefixMatches(Trie trie) {
        dictTrie = trie;
    }

    public int load(String... strings) {
        int added = 0;
        for (String string : strings) {
            String[] wordsTokens = string.split(" ");
            List<String> cleanedWords = Arrays.stream(wordsTokens)
                    .filter(s -> s.length() > 2)
                    .map(String::toLowerCase).collect(Collectors.toList());
            added += cleanedWords.size();
            for (String term : cleanedWords) {
                dictTrie.add(new Tuple(term, term.length()));
            }
        }
        return added;
    }

    public boolean contains(String word) {
        return dictTrie.contains(word);
    }

    public boolean delete(String word) {
        return dictTrie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return dictTrie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        return new KTrieIterable(dictTrie.wordsWithPrefix(pref), k);
    }

    public int size() {
        return dictTrie.size();
    }
}
