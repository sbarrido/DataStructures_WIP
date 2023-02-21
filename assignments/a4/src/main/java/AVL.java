

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
        BinaryNode<E> leftChild = node.left();
        BinaryNode<E> adoptedRightTree = leftChild.right();

        if(adoptedRightTree!= null) {
            adoptedRightTree.setParent(node);
        }
        node.setLeft(adoptedRightTree);
        node.setParent(leftChild);
        leftChild.setRight(node);
        if(this.root == node) {
            node.setHeight(node.height() - 1);
            this.root = leftChild;
            this.updateHeight();
        }

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
            adoptedLeftTree.setParent(node);
        }
        node.setRight(adoptedLeftTree);
        node.setParent(rightChild);
        rightChild.setLeft(node);

        if(this.root == node) {
            node.setHeight(node.height() - 1);
            this.root = rightChild;
            this.updateHeight();
        }
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
        //Empty Case
        if(this.root == null) {
            this.root = new BinaryNode<>(elem);
            this.size++;
            this.height++;
        } else {
            this.insertHelper(elem, this.root());
            this.size++;
            this.updateHeight();
        }
        //Non-Empty
    }
    public void insertHelper(E elem, BinaryNode<E> curr) {
        int flag = curr.data().compareTo(elem);

        if(flag < 0){
            //Check Left

            if(!curr.hasLeft()) {
                //Empty Left - insert
                curr.setLeft(new BinaryNode<>(elem, null, null, curr));
                if(!curr.hasRight()) {
                    curr.setHeight(curr.height() + 1);
                }
            } else {
                //Non-empty left
                //recursive
                curr.setHeight(curr.height() + 1);
                this.insertHelper(elem, curr.left());
            }
        } else if(flag > 0) {
            //Check Right
            if(!curr.hasRight()) {
                //Empty Right - insert
                curr.setRight(new BinaryNode<>(elem, null, null, curr));
                if(!curr.hasLeft()) {
                    curr.setHeight(curr.height() + 1);
                }
            } else {
                //Non-empty Right
                //Recurisve
                curr.setHeight(curr.height() + 1);
                this.insertHelper(elem, curr.right());
            }
        }
        this.mkBalanced(curr);
    }

    /*
     TODO: delete - slightly different from BST but similar logic
      * Hint: mkBalanced will be your best friend here.
    */
    public BinaryNode<E> delete(E elem) {
        BinaryNode<E> target = this.deleteHelper(elem, this.root);
        if(target != null) {
            BinaryNode<E> parent = target.parent();

            //Target is Root
            if(parent == null) {
                if(target.hasRight()) {
                    //Find home for target.left
                    BinaryNode<E> rightChild = target.right();
                    if(rightChild.hasLeft()) {
                        BinaryNode<E> leftTree = rightChild.left();
                        while(leftTree.hasLeft()) {
                            leftTree.setHeight(leftTree.height() + target.left().height());
                            leftTree = leftTree.left();
                        }

                        leftTree.setLeft(target.left());
                    }

                    this.root = target.right();
                    this.height = this.root.height();
                } else if(target.hasLeft()) {
                    this.root = target.left();
                    this.height = this.root.height();
                } else {
                    this.root = null;
                    this.height = 0;
                    this.size = 0;
                }
            } else {
                int flag = parent.compareTo(target);

                //Target is RightChild
                if(flag > 0) {
                    if(target.hasLeft()) {
                        //Reassign Parent's right child to target.left tree
                        this.size--;
                        parent.setHeight(parent.height() - 1);
                        parent.setRight(target.left());
                        target.left().setParent(parent);

                        //Find Rightmost Home for Target.right
                        if(target.hasRight()) {
                            BinaryNode<E> leftTargetTree = target.left();

                            while(leftTargetTree.hasRight()) {
                                leftTargetTree.setHeight(leftTargetTree.height() + target.right().height());
                                leftTargetTree = leftTargetTree.right();
                            }
                            //Place target.right in Home
                            leftTargetTree.setRight(target.right());
                            target.right().setParent(leftTargetTree);
                        }
                    } else {
                        //Target Left Empty
                        //Replace Parent.right with Target.right
                        this.size--;
                        parent.setRight(target.right());
                        if(target.right() != null) {
                            target.right().setParent(parent);
                        }
                    }
                }
                //Target is Left Child
                if(flag < 0) {
                    if(target.hasRight()) {
                        //Reassign parent's Left-child to target.right tree
                        this.size--;
                        parent.setHeight(parent.height() - 1);
                        parent.setLeft(target.right());
                        target.right().setParent(parent);
                        if(target.hasLeft()) {
                            BinaryNode<E> rightTargetTree = target.right();

                            //Find leftMost Home for Target.left
                            while(rightTargetTree.hasLeft()) {
                                rightTargetTree.setHeight(rightTargetTree.height() + target.left().height());
                                rightTargetTree = rightTargetTree.left();
                            }
                            //Place Target.left in home
                            rightTargetTree.setLeft(target.left());
                            target.left().setParent(rightTargetTree);
                        }
                    } else {
                        //Target.right empty
                        //Replace parent left child with target.leftChild
                        this.size--;
                        parent.setLeft(target.left());
                        target.left().setParent(parent);
                    }
                }
            }
        }

        if(this.root != null) {
            this.updateHeight();
        }
        return target;
    }

    public BinaryNode<E> deleteHelper(E elem, BinaryNode<E> curr) {
        BinaryNode<E> target = curr;
        if(curr == null) {
            return null;
        }
        if(curr.data() == elem) {
            target = curr;
        } else {
            //Check Children
            int flag = curr.data().compareTo(elem);
            if(flag > 0) {
                //right child
                if(curr.hasRight()) {
                    return this.deleteHelper(elem, curr.right());
                }
            }
            if(flag < 0) {
                //left child
                if(curr.hasLeft()) {
                    return this.deleteHelper(elem, curr.left());
                }
            }
        }

        return target;
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
