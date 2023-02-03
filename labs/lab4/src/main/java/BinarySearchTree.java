public class BinarySearchTree {

    private int size;
    private BinaryTreeNode root;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public BinaryTreeNode getRoot() {
        return this.root;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Inserts the given integer and return nothing. It inserts this int such that the tree remains a BST.
     * @param data The integer to be inserted
     */
    public void insert(int data) {
        // TODO
    }

    /**
     * Inserts the given integer and return nothing. It inserts this int such that the tree remains a BST.
     * @param data The integer to be inserted
     * @param curNode The current Node in the traversal
     */
    private void insert(int data, BinaryTreeNode curNode) {
        // TODO
    }

    /**
     * Deletes a Node containing the given integer. If the Node has 2 children, replaces with the Node of the minimum
     * value in the right child of the node. If the data is not present, returns null.
     * @param data The integer to be removed
     * @return The Node containing the integer to remove or null if one is not found
     */
    public BinaryTreeNode remove(int data) {
        // TODO
        return null;
    }


    /**
     * Deletes a Node containing the given integer. If the Node has 2 children, replaces with the Node of the maximum
     * value in the left child of the node. If the data is not present, returns null.
     * @param data The integer to be removed
     * @param curNode The current Node in the traversal
     * @return The Node containing the integer to remove or null if one is not found
     */
    private BinaryTreeNode remove(int data, BinaryTreeNode curNode) {
        // TODO
        return null;
    }

    /**
     * A recursive method that starts at the left child of a parent and extracts the maximum from this child's tree.
     * @param curNode The current Node in the traversal
     * @return The minimum Node in the child's tree
     */
    private BinaryTreeNode extractLeftMax(BinaryTreeNode curNode) {
        // TODO
        return null;
    }

    /**
     * Returns a Node containing the given integer or null if one is not found
     * @param data The integer to search for
     * @return A Node containing the given integer or null if one is not found
     */
    public BinaryTreeNode search(int data) {
        // TODO
        return null;
    }

    /**
     * Returns a Node containing the given integer or null if one is not found
     * @param data The integer to search for
     * @param curNode The current Node in the traversal
     * @return A Node containing the given integer or null if one is not found
     */
    private BinaryTreeNode search(int data, BinaryTreeNode curNode) {
        // TODO
        return null;
    }

    /**
     * Returns the pre-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getPreOrderStr() {
        // TODO
        return null;
    }

    /**
     * Returns the pre-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getPreOrderStr(BinaryTreeNode curNode) {
        // TODO
        return null;
    }

    /**
     * Returns the in-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getInOrderStr() {
        // TODO
        return null;
    }

    /**
     * Returns the in-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getInOrderStr(BinaryTreeNode curNode) {
        // TODO
        return null;
    }

    /**
     * Returns the post-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getPostOrderStr() {
        // TODO
        return null;
    }

    /**
     * Returns the post-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getPostOrderStr(BinaryTreeNode curNode) {
        // TODO
        return null;
    }

    public static void main(String[] args){
        System.out.println("Hello");
    }
}
