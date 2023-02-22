

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
    // Inorder traversal
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
    // Postorder traversal
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
        BinaryNode<E> leftChild = node.left();
        BinaryNode<E> adoptedRightTree = leftChild.right();

        if(adoptedRightTree!= null) {
            node.setLeft(adoptedRightTree);
            adoptedRightTree.setParent(node);
        } else {
            node.setLeft(null);
            node.setParent(leftChild);
        }

        this.heightHelper(node);
        int lSize = 0;
        int rSize = 0;
        if(node.hasLeft()) { lSize = node.left().size(); }
        if(node.hasRight()) { rSize = node.right().size(); }
        node.setSize(lSize + rSize + 1);

        leftChild.setHeight(leftChild.height() + 1);
        leftChild.setRight(node);

        if(leftChild.hasLeft()) { lSize = leftChild.left().size(); }
        if(leftChild.hasRight()) { rSize = leftChild.right().size(); }
        leftChild.setSize(lSize + rSize + 1);

        this.heightHelper(leftChild);
        if(this.root == node) {
            leftChild.setParent(null);
            this.root = leftChild;
        }

        this.updateHeight();
        this.RRotations++;
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
        BinaryNode<E> rightChild = node.right();
        BinaryNode<E> adoptedLeftTree = rightChild.left();

        if(adoptedLeftTree != null) {
            node.setRight(adoptedLeftTree);
            adoptedLeftTree.setParent(node);
        } else {
            node.setRight(null);
            node.setParent(rightChild);
        }

        this.heightHelper(node);
        int lSize = 0;
        int rSize = 0;
        if(node.hasLeft()) { lSize = node.left().size(); }
        if(node.hasRight()) { rSize = node.right().size(); }
        node.setSize(lSize + rSize + 1);

        rightChild.setHeight(rightChild.height() + 1);
        rightChild.setLeft(node);

        if(rightChild.hasLeft()) { lSize = rightChild.left().size(); }
        if(rightChild.hasRight()) { rSize = rightChild.right().size(); }
        rightChild.setSize(lSize + rSize + 1);

        this.heightHelper(rightChild);
        if(this.root == node) {
            rightChild.setParent(null);
            this.root = rightChild;
        }
        this.updateHeight();
        this.LRotations++;
    }

    /*
     TODO: possibleRotateRight
      * If the current node is unbalanced with the right tree height being smaller
      * than the left subtree height, rotate right. Otherwise, don't do anything.
    */
    public void possibleRotateRight(BinaryNode<E> node){
        boolean isBalanced = node.isBalanced();
        int lHeight, rHeight;
        if(!node.hasRight()) {
            rHeight = 0;
        } else {
            rHeight = node.right().height();
        }
        if(!node.hasLeft()) {
            lHeight = 0;
        } else {
            lHeight = node.left().height();
        }
        boolean bigLeft = lHeight > rHeight;

        if(!isBalanced && bigLeft) {
            rotateRight(node);
        }
    }

    /*
     TODO: possibleRotateLeft
      * If the current node is unbalanced with the left tree height being smaller
      * than the right subtree height, rotate left. Otherwise, don't do anything.
    */
    public void possibleRotateLeft(BinaryNode<E> node){
        boolean isBalanced = node.isBalanced();
        int lHeight, rHeight;
        if(!node.hasRight()) {
            rHeight = 0;
        } else {
            rHeight = node.right().height();
        }
        if(!node.hasLeft()) {
            lHeight = 0;
        } else {
            lHeight = node.left().height();
        }
        boolean bigRight = rHeight > lHeight;

        if(!isBalanced && bigRight) {
            rotateLeft(node);
        }
    }

    /*
     TODO: mkBalanced
      * Given a node, balance it if the heights are unbalanced.
      * Hint: rotations!!!
    */
    public void mkBalanced(BinaryNode<E> node){
        possibleRotateLeft(node);
        possibleRotateRight(node);
    }


    // Helpers for BST/AVL methods
    // extractRightMost - identical to BST
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
    //  search - identical to BST
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

    /*
     TODO: insert - slightly different from BST but similar logic
      * Hint: mkBalanced will be your best friend here.
    */
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
        this.mkBalanced(curr);
    }

    /*
     TODO: delete - slightly different from BST but similar logic
      * Hint: mkBalanced will be your best friend here.
    */
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
        if(flag < 0) {
            // recurse: elem is smaller than curr.data
            return deleteHelper(elem, curr.left());
        }
        if(flag > 0) {
            //recurse: elem is bigger than curr.data
            return deleteHelper(elem, curr.right());
        }
        if(flag == 0) {
            //EXECUTE ORDER 66
            BinaryNode<E> parent = curr.parent();
            if(parent == null) {
                //CURR IS ROOT
                if(curr.hasLeft() || curr.hasRight()) {
                    if(curr.hasLeft()) { this.root = extractRightMost(curr.left()); }
                    if(curr.hasRight()) { this.root = extractRightMost(curr.right()); }

                    updateHeight();
                    this.height = this.root.height();
                } else {
                    this.root = null;
                    this.height = 0;
                }

            } else {
                if(!curr.hasRight() && !curr.hasLeft()) {
                    //CURR NO CHILDREN
                    int parentFlag = curr.data().compareTo(parent.data());
                    if(parentFlag > 0) { parent.setRight(null); }
                    if(parentFlag < 0) { parent.setLeft(null); }
                } else {
                    //CURR HAS CHILDREN
                    BinaryNode<E> rightMost = null;
                    if(curr.hasLeft()) {
                        rightMost = extractRightMost(curr.left());
                    } else if (curr.hasRight()) {
                        rightMost = extractRightMost(curr.right());
                    }
                    //find which child of parent
                    int parentFlag = rightMost.data().compareTo(parent.data());
                    if(parentFlag > 0) { parent.setRight(rightMost); }
                    if(parentFlag < 0) { parent.setLeft(rightMost); }
                    rightMost.setParent(parent);
                    //Assign rightMost.right = curr.right IFF not itself
                    if(rightMost.compareTo(curr.right()) != 0) {
                        rightMost.setRight(curr.right());
                        if(curr.right() != null) {
                            curr.right().setParent(rightMost);
                        }
                    }

                    //IF rightMost.parent != curr
                    // LeftMost(rightMost) = rightMost.parent
                    if(rightMost.parent() != curr) {
                        if(extractLeftMost(rightMost).compareTo(rightMost) != 0) {
                            extractLeftMost(rightMost).setLeft(rightMost.parent());
                        }
                    }
                }
            }
            this.size--;
            this.updateHeight();
            if(this.root != null) {
                this.height = this.root.height();
            }
        }

        //*Extracted
        int lHeight = 0;
        int rHeight = 0;

        if(curr.hasLeft()) { lHeight = curr.left().height(); }
        if(curr.hasRight()) { rHeight = curr.right().height(); }

        curr.setHeight(Math.max(lHeight, rHeight) - 1);
        curr.setSize(curr.size() - 1);
        this.mkBalanced(curr);
        return curr;
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
