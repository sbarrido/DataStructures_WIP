import java.util.Stack;

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
        insertHelper(this.root, word);
    }

    void insertHelper(TrieNodeWHashTable node, String word) {
        if(word.length() == 0) {
            return;
        }
        char target = word.charAt(0);
        if(!node.children.containsKey(target)) {
            //put target with new node
            if(word.length() == 1) {
                node.isWord = true;
            }
            node.children.put(target, new TrieNodeWHashTable());

        }
        insertHelper(node.children.get(target), word.substring(1));

    }

    /**
     * Given a word, returns if it is represented in this Trie.
     * @param word The word to be searched for
     */
    boolean search(String word) {
        return searchHelper(this.root, word, false);
    }

    boolean searchHelper(TrieNodeWHashTable node, String word, boolean found) {
        if(word.length() == 0) {
            return found;
        }
        char target = word.charAt(0);
        if(node.children.containsKey(target)) {
            //Check word length
            if(word.length() == 1) {
                //Check node - bool
                if(node.isWord) found = true;
            }
            //Word contains more -> recurse
            return this.searchHelper(node.children.get(target), word.substring(1), found);

        }
        return found;
    }

    /**
     * Marks the TrieNode representing the last char in the given word is no longer a word.
     * @param word The word to be deleted
     */
    void delete(String word) {
        deleteHelper(this.root, word, 0);
    }

    void deleteHelper(TrieNodeWHashTable node, String word, int index) {
        if(index >= word.length()) {
            return;
        }

        char target = word.charAt(index);
        if(node.children.containsKey(target)) {
            //Check word length - 1 == index
            if(word.length() - 1 == index) {
                //check node boolean
               node.isWord = false;
            } else {
                //recurse not end of word
                deleteHelper(node.children.get(target), word, index + 1);
            }
        }
    }
}