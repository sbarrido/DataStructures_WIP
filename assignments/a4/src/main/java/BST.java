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
        this.height = root.height();
        this.size = root.size();
    }

    // Access field
    public BinaryNode<E> root() {
        return this.root;
    }

    // Basic properties
    public int height() { return this.height; }
    public int size() {
        return this.size;
    }
    public boolean isBalanced() {
        return root.isBalanced();
    }

    // updateHeight - Update the root height to reflect any changes
    public void updateHeight() {
        this.heightHelper(this.root);
    }
    private void heightHelper(BinaryNode<E> node) {
        int lHeight = 0;
        int rHeight = 0;
        if(node == null) return;
        if(node.hasLeft()) { lHeight = node.left().height(); }
        if(node.hasRight()) { rHeight = node.right().height(); }

        node.setHeight(Math.max(lHeight, rHeight) + 1);
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
    // TODO: Inorder traversal
    public List<E> inOrderList() {
        ArrayList<E> curr = new ArrayList<>();
        ArrayList<E> target = (ArrayList<E>) inOrderHelper(this.root, curr);

        return target;
    }

    public List<E> inOrderHelper(BinaryNode<E> node, List<E> curr) {

        //Node - Left - Right
        if(node.hasLeft()) { inOrderHelper(node.left(), curr); }
        if(node != null) {
            curr.add(node.data());
        }
        if(node.hasRight()) { inOrderHelper(node.right(), curr); }

        return curr;
    }
    // TODO: Postorder traversal
    public List<E> postOrderList() {
        ArrayList<E> curr = new ArrayList<>();
        ArrayList<E> target = (ArrayList<E>) postOrderHelper(this.root, curr);

        return target;
    }
    public List<E> postOrderHelper(BinaryNode<E> node, List<E> curr) {
        //Node - Left - Right
        if(node.hasLeft()) { postOrderHelper(node.left(), curr); }
        if(node.hasRight()) { postOrderHelper(node.right(), curr); }
        if(node != null) {
            curr.add(node.data());
        }

        return curr;
    }
    // Helpers for BST/AVL methods
    //TODO: extractRightMost
    //    This will be called on the left subtree and will get the maximum value.
    public BinaryNode<E> extractRightMost(BinaryNode<E> curNode) {
        BinaryNode<E> target = curNode;
        if(target != null) {
            BinaryNode<E> rightChild = curNode.right();

            if(rightChild == null) {
                target = curNode;
            } else {
                return this.extractRightMost(rightChild);
            }
        }

        return target;
    }
    public BinaryNode<E> extractLeftMost(BinaryNode<E> curNode) {
        BinaryNode<E> target = curNode;
        if(target != null) {
            BinaryNode<E> leftChild = curNode.left();

            if(leftChild == null) {
                target = curNode;
            } else {
                return this.extractLeftMost(leftChild);
            }
        }

        return target;
    }
    // AVL & BST Search & insert same
    // search
    public BinaryNode<E> search(E elem) {
        return searchHelper(elem, this.root);
    }
    public BinaryNode<E> searchHelper(E elem, BinaryNode<E> curr) {
        if(curr == null) {
            return curr;
        }

        int flag = elem.compareTo(curr.data());
        if(flag < 0) return searchHelper(elem, curr.left());
        if(flag > 0) return searchHelper(elem, curr.right());

        return curr;
    }
    //insert
    public void insert(E elem) {
        if(this.root == null) {
            this.root = new BinaryNode<E>(elem);
        } else {
            this.insertHelper(elem, this.root);
        }

        this.size = this.root.size();
        this.updateHeight();
        this.height = this.root.height();
    }
    public void insertHelper(E elem, BinaryNode<E> curr) {
        /* ORGANIZING MY THOUGHTS THAT ARE HANGING BY A THREAD
        flag < 0 : elem is smaller than current data
              1. Curr.Left = null; empty
                  1a. Insert New Node - data: elem, children: null, parent: curr
                  *2a. Increase curr.size
                  *3a. Increase curr.height
              2. Non-Empty;
                  1a. Recursive Call - push curr.left
                  *2a. Increase curr.size
                  *3a. Increase curr.height
         flag > 0 : elem is bigger than current data
               Reflection of above
               * <- denotes extract outside
         */
        int flag = elem.compareTo(curr.data());
        if(flag < 0) {
            if(curr.hasLeft()) {
                //recursive
                insertHelper(elem, curr.left());
            } else {
                //Insert
                curr.setLeft(new BinaryNode<>(elem, null, null, curr));
            }

        }
        if(flag > 0) {
            if(curr.hasRight()) {
                //recursive
                insertHelper(elem, curr.right());
            } else {
                //Insert
                curr.setRight(new BinaryNode<>(elem, null, null, curr));
            }
        }

        // * extracted
        this.heightHelper(curr);
        curr.setSize(curr.size() + 1);
    }

    // delete
    public BinaryNode<E> delete(E elem) {
        BinaryNode<E> target = null;
        if(this.root != null) {
            target = this.deleteHelper(elem, this.root);
            this.updateHeight();
        }
        return target;
    }
    public BinaryNode<E> deleteHelper(E elem, BinaryNode<E> curr) {
        /* ORGANIZING MY THOUGHTS FOR THE HELL THAT IS DELETION
            flag == 0: EXCECUTE ORDER 66
                1. CURR NO CHILDREN
                    A. FIND PARENT.child = CURR set to NULL
                2. CURR HAS CHILDREN
                    A. extractRightMost(curr) : find furthest right Node
                        a. PARENT.child = rightMostNode ; FIND WHICH CHILD
                        b. rightMostNode.right = curr.Right IFF not itself
                        d. if rightMostNode.parent != curr
                            d1. LeftMost of rightMost = rightMost.parent
            flag < 0 elem is smaller than curr.data
                1. recursive call - curr.left
            flag > 0 elem is bigger than curr.data
                1. recursive call - curr.right

            *EXCTRACT OUT:
                - reduce size of curr
                - reduce height of curr
         */
        int flag = elem.compareTo(curr.data());
        if(flag == 0) {
            //EXECUTE ORDER 66
            BinaryNode<E> parent = curr.parent();
            if(parent == null) {
                //CURR IS ROOT
                if(curr.hasLeft() || curr.hasRight()) {
                    this.root = extractRightMost(curr);
                } else {
                    this.root = null;
                }

                this.size--;
                this.height--;
            } else {
                if(!curr.hasRight() && !curr.hasLeft()) {
                    //CURR NO CHILDREN
                    int parentFlag = curr.data().compareTo(parent.data());
                    if(parentFlag > 0) { parent.setRight(null); }
                    if(parentFlag < 0) { parent.setLeft(null); }
                } else {
                    //CURR HAS CHILDREN
                    BinaryNode<E> rightMost = extractRightMost(curr);

                    //find which child of parent
                    int parentFlag = rightMost.data().compareTo(parent.data());
                    if(parentFlag > 0) { parent.setRight(rightMost); }
                    if(parentFlag < 0) { parent.setLeft(rightMost); }
                    rightMost.setParent(parent);
                    //Assign rightMost.right = curr.right IFF not itself
                    if(rightMost.compareTo(curr.right()) != 0) {
                        rightMost.setRight(curr.right());
                        curr.right().setParent(rightMost);
                    }

                    //IF rightMost.parent != curr
                    // LeftMost(rightMost) = rightMost.parent
                    if(rightMost.parent() != curr) {
                        extractLeftMost(rightMost).setLeft(rightMost.parent());
                    }
                }

            }
            if(flag < 0) {
                // recurse: elem is smaller than curr.data
                return deleteHelper(elem, curr.left());
            }
            if(flag > 0) {
                //recurse: elem is bigger than curr.data
                return deleteHelper(elem, curr.right());
            }

        }

        //*Extracted
        int lHeight = 0;
        int rHeight = 0;

        if(curr.hasLeft()) { lHeight = curr.left().height(); }
        if(curr.hasRight()) { rHeight = curr.right().height(); }

        curr.setHeight(Math.max(lHeight, rHeight) - 1);
        curr.setSize(curr.size() - 1);
        return curr;
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
