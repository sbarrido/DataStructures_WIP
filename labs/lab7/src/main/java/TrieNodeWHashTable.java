import java.util.HashTable;

public class TrieNodeWHashTable {
    boolean isWord;
    HashTable<Character, TrieNodeWHashTable> children;

    public TrieNodeWHashTable() {
        this.isWord = false;
        children = new HashTable<>();
    }
}