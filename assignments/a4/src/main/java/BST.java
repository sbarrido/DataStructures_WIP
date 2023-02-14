import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BST<E extends Comparable<E>> implements Tree<E> {

    private int height;
    private int size;
    private BinaryNode<E> root;

    public BST(){
        this.root = null;
        this.height = 0;
        this.size = 0;
    }

    // TODO: BST
    public BST(BinaryNode<E> root){
        this.root = root;
    }

    // Access field
    public BinaryNode<E> root() {
        return this.root;
    }

    // Basic properties
    public int height() {
        return this.height;
    }
    public int size() {
        return this.size;
    }
    public boolean isBalanced() {
        return root.isBalanced();
    }

    // TODO: updateHeight - Update the root height to reflect any changes
    public void updateHeight() {
    }

    // Traversals that return lists
    // TODO: Preorder traversal
    public List<E> preOrderList() {
        ArrayList<E> target = new ArrayList<>();

        return target;
    }

    // TODO: Inorder traversal
    public List<E> inOrderList() {
        ArrayList<E> target = new ArrayList<>();

        return target;
    }

    // TODO: Postorder traversal
    public List<E> postOrderList() {
        ArrayList<E> target = new ArrayList<>();

        return target;
    }

    // Helpers for BST/AVL methods
    // TODO: extractRightMost
    //    This will be called on the left subtree and will get the maximum value.
    public BinaryNode<E> extractRightMost(BinaryNode<E> curNode) {
        BinaryNode<E> rightChild = curNode.right();
        BinaryNode<E> target = rightChild;

        if(rightChild == null) {
            target = curNode;
        } else {
            return this.extractRightMost(rightChild);
        }

        return target;
    }

    // AVL & BST Search & insert same
    // TODO: search
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
    // TODO: insert
    public void insert(E elem) {

    }

    // TODO: delete
    public BinaryNode<E> delete(E elem) {
        return null;
    }

    // Stuff to help you debug if you want
    // Can ignore or use to see if it works.
    static <E extends Comparable<E>> Tree<E> mkBST (Collection<E> elems) {
        Tree<E> result = new BST<>();
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
