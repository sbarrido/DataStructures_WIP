public class TrieWithHashTable {
    TrieNodeWHashTable root;

    public TrieWithHashTable() {
        root = new TrieNodeWHashTable();
    }

    /**
     * Insert the word into the Trie by making new TrieNodes and marking the last TrieNode as a word.
     * @param word The word to be inserted
     */
    void insert( String word ) {
        //TODO
    }

    void insertHelper(TrieNodeWHashTable node, String word) {
        //TODO
    }

    /**
     * Given a word, returns if it is represented in this Trie.
     * @param word The word to be searched for
     */
    boolean search(String word) {
        //TODO
        return false;
    }

    boolean searchHelper(TrieNodeWHashTable node, String word) {
        //TODO
        return false;
    }

    /**
     * Marks the TrieNode representing the last char in the given word is no longer a word.
     * @param word The word to be deleted
     */
    void delete(String word) {
        //TODO
    }

    void deleteHelper(TrieNodeWHashTable node, String word) {
        //TODO
    }
}