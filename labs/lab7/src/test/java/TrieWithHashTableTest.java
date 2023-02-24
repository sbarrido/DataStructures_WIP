import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrieWithHashTableTest {
    TrieWithHashTable trie;
    @BeforeEach
    void initTest() {
        trie = new TrieWithHashTable();
        assertEquals(true, trie.root instanceof TrieNodeWHashTable);
    }
    void loadStr() {
        trie.insert("a");
        assertEquals(true, trie.root.children.containsKey('a'));
        assertEquals(false, trie.root.children.containsKey('b'));

        trie.insert("b");
        assertEquals(true, trie.root.children.containsKey('a'));
        assertEquals(true, trie.root.children.containsKey('b'));

        trie.insert("ant");
        Hashtable<Character, TrieNodeWHashTable> children = trie.root.children;
        assertEquals(true, children.containsKey('a'));
        assertEquals(true, children.get('a').children.get('n') instanceof TrieNodeWHashTable);
        assertEquals(true, children.get('a').children.get('n').children.get('t') instanceof TrieNodeWHashTable);
    }
    @Test
    void insertTest() {
        loadStr();
        trie.insert("ant");
    }
    @Test
    void searchTest() {
        loadStr();

        assertEquals(false, trie.search("Pickle"));
        assertEquals(true, trie.search("a"));
        assertEquals(true, trie.search("ant"));
        assertEquals(false, trie.search("antman"));
    }
    @Test
    void deleteTest() {
        loadStr();
        trie.delete("BUTT");
        assertEquals(false, trie.root.children.containsKey('B'));
        assertEquals(true, trie.root.children.containsKey('b'));
        trie.delete("b");
        assertEquals(false, trie.root.children.get('b').isWord);

        trie.delete("a");
        assertEquals(false, trie.root.children.get('a').isWord);
        assertEquals(true, trie.search("ant"));
    }
}