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

    // BST
    public BST(BinaryNode<E> root){
        this.root = root;
        this.height = 1;
        this.size = 1;
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

    // updateHeight - Update the root height to reflect any changes
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
        if(newHeight != this.height) {
            this.height = newHeight + 1;
        } else {
            this.height = root.height();
        }
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
    //TODO: extractRightMost
    //    This will be called on the left subtree and will get the maximum value.
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
    //TODO: search
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
    //TODO: insert
    public void insert(E elem) {
        if(this.root == null) {
            this.root = new BinaryNode<E>(elem);
            this.size++;
            this.height++;
        } else {
            this.insertHelper(elem, this.root);
            this.size++;
            this.updateHeight();
        }
    }
    public void insertHelper(E elem, BinaryNode<E> curr) {
        int flag = curr.data().compareTo(elem);

        if(flag < 0) {
            //go left
            //Check left child
            //leftChild = null, insert
            //else, travel to leftChild
            if(curr.left() == null) {
                curr.setLeft(new BinaryNode<>(elem));
            } else {
                this.insertHelper(elem, curr.left());
            }
        }
        if(flag > 0) {
            // go right
            //Check right child
            //rightChild = null, insert
            //else travel to right
            if(curr.right() == null) {
                curr.setRight(new BinaryNode<>(elem));
            } else {
                this.insertHelper(elem, curr.right());
            }
        }

        curr.setHeight(curr.height() + 1);
    }

    //TODO : delete
    public BinaryNode<E> delete(E elem) {
        return null;
    }
    public BinaryNode<E> deleteHelper(E elem, BinaryNode<E> curr) {
        BinaryNode<E> target = null;
        if(curr.data() == elem) {
            target = curr;
        } else {
            //Check Children
            int flag = curr.data().compareTo(elem);
            if(flag > 0) {
                //right child
                if(curr.hasRight()) {
                    this.deleteHelper(elem, curr.right());
                }
            }
            if(flag < 0) {
                //left child
                if(curr.hasLeft()) {
                    this.deleteHelper(elem, curr.left());
                }
            }
        }
        return target;
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
