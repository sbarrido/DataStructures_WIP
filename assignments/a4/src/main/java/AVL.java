import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AVL<E extends Comparable<E>> implements Tree<E>{

    private int height;
    private int size;
    private BinaryNode<E> root;
    private int RRotations; // this will be used to see if the amount of rotations was correct
    private int LRotations; // this will be used to see if the amount of rotations was correct

    public AVL(){
        this.root = null;
        this.height = 0;
        this.size = 0;
        this.RRotations = 0;
        this.LRotations = 0;
    }

    public AVL(BinaryNode<E> root){
        this.root = root;
        this.height = root.height();
        this.size = root.size();
        this.RRotations = 0;
        this.LRotations = 0;
    }

    // Access fields
    public int getRRotations(){
        return this.RRotations;
    }
    public int getLRotations(){
        return this.LRotations;
    }
    public BinaryNode<E> root() {
        return this.root;
    }
    public int height() {
        return this.height;
    }
    public int size() {
        return this.size;
    }
    public boolean isBalanced() {
        return root.isBalanced();
    }

    //  updateHeight - same as BST
    public void updateHeight() {
        int lHeight, rHeight;
        if(this.root.hasRight()) {
            rHeight = this.root.right().height();
        } else {
            rHeight = 0;
        }
        if(this.root.hasLeft()) {
            lHeight = this.root.left().height();
        } else {
            lHeight = 0;
        }

        int newHeight = Math.max(lHeight, rHeight);

        if(newHeight != this.root.height()) {
            this.height = newHeight + 1;
        } else {
            this.height = newHeight;
        }

        this.root.setHeight(this.height);
    }

    // Traversals that return lists
    // Preorder traversal
    public List<E> preOrderList() {
        ArrayList<E> curr = new ArrayList<>();
        ArrayList<E> target = (ArrayList<E>) preOrderHelper(this.root, curr);

        return target;
    }
    public List<E> preOrderHelper(BinaryNode<E> node, List<E> curr) {
        ArrayList<E> target = (ArrayList<E>) curr;
        //Node - Left - Right
        if(node != null) {
            target.add(node.data());
        }
        if(node.hasLeft()) {preOrderHelper(node.left(), target);}
        if(node.hasRight()) { preOrderHelper(node.right(), target); }

        return target;
    }
    // Inorder traversal
    public List<E> inOrderList() {
        ArrayList<E> curr = new ArrayList<>();
        ArrayList<E> target = (ArrayList<E>) inOrderHelper(this.root, curr);

        return target;
    }

    public List<E> inOrderHelper(BinaryNode<E> node, List<E> curr) {
        ArrayList<E> target = (ArrayList<E>) curr;

        //Node - Left - Right
        if(node.hasLeft()) { inOrderHelper(node.left(), target); }
        if(node != null) {
            target.add(node.data());
        }
        if(node.hasRight()) { inOrderHelper(node.right(), target); }

        return target;
    }
    // Postorder traversal
    public List<E> postOrderList() {
        ArrayList<E> curr = new ArrayList<>();
        ArrayList<E> target = (ArrayList<E>) postOrderHelper(this.root, curr);

        return target;
    }
    public List<E> postOrderHelper(BinaryNode<E> node, List<E> curr) {
        ArrayList<E> target = (ArrayList<E>) curr;

        //Node - Left - Right
        if(node.hasLeft()) { postOrderHelper(node.left(), target); }
        if(node.hasRight()) { postOrderHelper(node.right(), target); }
        if(node != null) {
            target.add(node.data());
        }

        return target;
    }

    /*
    TODO: rotateRight
     *              x                          y
     *            /   \                      /   \
     *           y     C     ===>           A     x
     *         /   \                             /  \
     *        A    B                            B    C
     * You should never rotateRight if the left subtree is empty.
     * Make sure you increment the RRotations.
    */
    public void rotateRight(BinaryNode<E> node){

    }

    /*
     TODO: rotateLeft
      *              x                          y
      *            /   \                      /   \
      *           y     C     <==           A     x
      *         /   \                             /  \
      *        A    B                            B    C
      * You should never rotateLeft if the right subtree is empty.
      * Make sure you increment the LRotations.
      * Symmetrical to above.
     */
    public void rotateLeft(BinaryNode<E> node){
    }

    /*
     TODO: possibleRotateRight
      * If the current node is unbalanced with the right tree height being smaller
      * than the left subtree height, rotate right. Otherwise, don't do anything.
    */
    public void possibleRotateRight(BinaryNode<E> node){
    }

    /*
     TODO: possibleRotateLeft
      * If the current node is unbalanced with the left tree height being smaller
      * than the right subtree height, rotate left. Otherwise, don't do anything.
    */
    public void possibleRotateLeft(BinaryNode<E> node){
    }

    /*
     TODO: mkBalanced
      * Given a node, balance it if the heights are unbalanced.
      * Hint: rotations!!!
    */
    public void mkBalanced(BinaryNode<E> node){
    }


    // Helpers for BST/AVL methods
    // extractRightMost - identical to BST
    public BinaryNode<E> extractRightMost(BinaryNode<E> curNode) {
        BinaryNode<E> target = null;
        if(curNode != null) {
            BinaryNode<E> rightChild = curNode.right();

            if(rightChild == null) {
                target = curNode;
            } else {
                return this.extractRightMost(rightChild);
            }
        }

        return target;
    }

    // AVL & BST Search & insert same
    //  search - identical to BST
    public BinaryNode<E> search(E elem) {
        return searchHelper(elem, this.root);
    }

    public BinaryNode<E> searchHelper(E elem, BinaryNode<E> curr) {
        BinaryNode<E> target = curr;
        if(curr == null) {
            return null;
        }

        int flag = target.data().compareTo(elem);
        if(flag < 0) return searchHelper(elem, target.left());
        if(flag > 0) return searchHelper(elem, target.right());

        return target;
    }

    /*
     TODO: insert - slightly different from BST but similar logic
      * Hint: mkBalanced will be your best friend here.
    */
    public void insert(E elem) {
    }


    /*
     TODO: delete - slightly different from BST but similar logic
      * Hint: mkBalanced will be your best friend here.
    */
    public BinaryNode<E> delete(E elem) {
        return null;
    }

    // Stuff to help you debug if you want
    // Can ignore or use to see if it works.
    static <E extends Comparable<E>> Tree<E> mkAVL (Collection<E> elems) {
        Tree<E> result = new AVL<>();
        for (E e : elems) result.insert(e);
        return result;
    }
    public TreePrinter.PrintableNode getLeft() {
        return this.root.hasLeft() ? this.root.left() : null;
    }
    public TreePrinter.PrintableNode getRight() {
        return this.root.hasRight() ? this.root.right() : null;
    }
    public String getText() {
        return (this.root != null) ? this.root.getText() : "";
    }

}
