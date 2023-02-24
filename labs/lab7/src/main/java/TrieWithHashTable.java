import java.util.Stack;

public class TrieWithHashTable {
    TrieNodeWHashTable root;
    Stack<TrieNodeWHashTable> rmvStack;
    public TrieWithHashTable() {
        root = new TrieNodeWHashTable();
        rmvStack = new Stack<TrieNodeWHashTable>();
        rmvStack.push(root);
    }

    /**
     * Insert the word into the Trie by making new TrieNodes and marking the last TrieNode as a word.
     * @param word The word to be inserted
     */
    void insert( String word ) {
        insertHelper(this.root, word);
    }

    void insertHelper(TrieNodeWHashTable node, String word) {
        char target = word.charAt(0);
        if(!node.children.containsKey(target)) {
            //put target with new node
            if(word.length() == 1) { this.root.isWord = true; }
            node.children.put(target, new TrieNodeWHashTable());
        } else {
            //Recursive
            insertHelper(node.children.get(target), word.substring(1));
        }
    }

    /**
     * Given a word, returns if it is represented in this Trie.
     * @param word The word to be searched for
     */
    boolean search(String word) {
        return searchHelper(this.root, word);
    }

    boolean searchHelper(TrieNodeWHashTable node, String word) {
        boolean found = false;

        char target = word.charAt(0);
        if(node.children.containsKey(target)) {
            //Check word length
            if(word.length() == 1) {
                //Check node - bool
                if(node.isWord) found = true;
            } else {
                //Word contains more -> recurse
                this.searchHelper(node.children.get(target), word.substring(1));
            }
        }
        return found;
    }

    /**
     * Marks the TrieNode representing the last char in the given word is no longer a word.
     * @param word The word to be deleted
     */
    void delete(String word) {
        //TODO
    }

    void deleteHelper(TrieNodeWHashTable node, String word, int index) {
        if(index >= word.length()) {
            return;
        }

        //BEGIN STACKING NODES TO BE DELETED
        boolean found = false;
        char target = word.charAt(index);
        if(node.children.containsKey(target)) {
            //Add to Stack to check delete
            rmvStack.push(node);

            //Check word length - 1 == index
            if(word.length() - 1 == index) {
                //check node boolean
                if(node.isWord) {
                    //begin delete
                    found = true;
                }
            } else {
                //recurse not end of word
                deleteHelper(node.children.get(target), word, index + 1);
            }
        }

        //IF FOUND FINAL NODE THAT IS WORD
        //POP OFF MF
        boolean initFound = false;
        if(found) {
            while(!rmvStack.isEmpty()) {
                TrieNodeWHashTable popped = rmvStack.pop();

                if(popped.isWord && !initFound) {
                    initFound = true;
                    rmvStack.pop().children.remove(word.charAt(index--));
                } else if(initFound){
                    break;
                } else {
                    if(!rmvStack.isEmpty()) {
                        if(popped.children.isEmpty()) {
                            TrieNodeWHashTable parent = rmvStack.pop();
                            parent.children.remove(word.charAt(index--));
                            if(parent.isWord) {
                                break;
                            }
                        } else {
                            popped.isWord = false;
                        }
                    }
                }
            }
        }
    }
}