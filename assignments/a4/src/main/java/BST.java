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

        if(newHeight != this.root.height()) {
            this.height = newHeight + 1;
        } else {
            this.height = newHeight;
        }

        this.root.setHeight(this.height);
    }

    // Traversals that return lists
    // TODO: Preorder traversal
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
    //insert
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
                if(!curr.hasRight()) {
                    curr.setHeight(curr.height() + 1);
                }
            } else {
                curr.setHeight(curr.height() + 1);
                this.insertHelper(elem, curr.left());
            }
        } else if(flag > 0) {
            // go right
            //Check right child
            //rightChild = null, insert
            //else travel to right
            if(curr.right() == null) {
                curr.setRight(new BinaryNode<>(elem));
                if(!curr.hasLeft()) {
                    curr.setHeight(curr.height() + 1);
                }
            } else {
                curr.setHeight(curr.height() + 1);
                this.insertHelper(elem, curr.right());
            }
        }
    }

    // delete
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
