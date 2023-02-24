import java.util.Hashtable;

public class TrieNodeWHashTable {
    boolean isWord;
    Hashtable<Character, TrieNodeWHashTable> children;

    public TrieNodeWHashTable() {
        this.isWord = false;
        children = new Hashtable<Character, TrieNodeWHashTable>();
    }
}