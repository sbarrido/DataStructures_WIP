
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
        insert(data, this.root);
        this.size++;
    }

    /**
     * Inserts the given integer and return nothing. It inserts this int such that the tree remains a BST.
     * @param data The integer to be inserted
     * @param curNode The current Node in the traversal
     */
    private void insert(int data, BinaryTreeNode curNode) {
        //Data Small check left
        //Data Big check right
        //Do nothing if ==
        if(curNode == null) {
            this.root = new BinaryTreeNode(data, null, null, null);
        } else {
            if(curNode.getItem() > data) {
                if(curNode.getLeft() == null) {
                    curNode.setLeft(new BinaryTreeNode(data, curNode, null, null));
                } else {
                    insert(data, curNode.getLeft());
                }
            } else if (curNode.getItem() < data) {
                if(curNode.getRight() == null) {
                    curNode.setRight(new BinaryTreeNode(data, curNode, null, null));
                } else {
                    insert(data, curNode.getRight());
                }
            }
        }
    }

    /**
     * Deletes a Node containing the given integer. If the Node has 2 children, replaces with the Node of the minimum
     * value in the right child of the node. If the data is not present, returns null.
     * @param data The integer to be removed
     * @return The Node containing the integer to remove or null if one is not found
     */
    public BinaryTreeNode remove(int data) {
        return remove(data, this.root);
    }


    /**
     * Deletes a Node containing the given integer. If the Node has 2 children, replaces with the Node of the maximum
     * value in the left child of the node. If the data is not present, returns null.
     * @param data The integer to be removed
     * @param curNode The current Node in the traversal
     * @return The Node containing the integer to remove or null if one is not found
     */
    private BinaryTreeNode remove(int data, BinaryTreeNode curNode) {
        BinaryTreeNode target = null;
        if(curNode != null) {
            //Target found
            if (curNode.getItem() == data) {
                target = curNode;

                BinaryTreeNode leftMax = extractLeftMax(target);
                BinaryTreeNode targetParent = target.getParent();
                if(target == leftMax) {
                    leftMax = null;
                }

                if(leftMax != null) {
                    if(leftMax.getItem() < targetParent.getItem()) {
                        targetParent.setLeft(leftMax);
                    } else {
                        targetParent.setRight(leftMax);
                    }

                    leftMax.setParent(targetParent);
                } else {
                    if(target.getItem() < targetParent.getItem()) {
                        targetParent.setLeft(leftMax);
                    } else {
                        targetParent.setRight(leftMax);
                    }
                }
                this.size--;
            } else if (data < curNode.getItem()) {
                return remove(data, curNode.getLeft());
            } else if (data > curNode.getItem()) {
                return remove(data, curNode.getRight());
            }
        }

        return target;
    }

    /**
     * A recursive method that starts at the left child of a parent and extracts the maximum from this child's tree.
     * @param curNode The current Node in the traversal
     * @return The minimum Node in the child's tree
     */
    private BinaryTreeNode extractLeftMax(BinaryTreeNode curNode) {
        BinaryTreeNode target = null;

        if(curNode.getLeft() == null) {
            if(curNode.getRight() != null) {
                target = curNode.getRight();
            } else {
                target = curNode;
            }
        } else {
            return extractLeftMax(curNode.getLeft());
        }

        return target;
    }

    /**
     * Returns a Node containing the given integer or null if one is not found
     * @param data The integer to search for
     * @return A Node containing the given integer or null if one is not found
     */
    public BinaryTreeNode search(int data) {
        //TODO
        return null;
    }

    /**
     * Returns a Node containing the given integer or null if one is not found
     * @param data The integer to search for
     * @param curNode The current Node in the traversal
     * @return A Node containing the given integer or null if one is not found
     */
    private BinaryTreeNode search(int data, BinaryTreeNode curNode) {
        BinaryTreeNode target = null;
        if(curNode != null) {
            //Target found
            if (curNode.getItem() == data) {
                target = curNode;
            } else if (data < curNode.getItem()) {
                return remove(data, curNode.getLeft());
            } else if (data > curNode.getItem()) {
                return remove(data, curNode.getRight());
            }
        }
        return target;
    }

    /**
     * Returns the pre-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getPreOrderStr() {
        return getPreOrderStr(this.root);
    }

    /**
     * Returns the pre-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getPreOrderStr(BinaryTreeNode curNode) {
        String target = "";
        if(curNode == null) {
            return target;
        }
        target += curNode.getItem();

        if(curNode.getLeft() != null) {
            target += ", " + this.getPreOrderStr(curNode.getLeft());
        }
        if(curNode.getRight() != null) {
            target += ", " + this.getPreOrderStr(curNode.getRight());
        }
        return target;
    }

    /**
     * Returns the in-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getInOrderStr() {
        return getInOrderStr(this.root);
    }

    /**
     * Returns the in-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getInOrderStr(BinaryTreeNode curNode) {
        String target = "";
        if(curNode == null) {
            return target;
        }

        if(curNode.getLeft() != null) {
            target += this.getInOrderStr(curNode.getLeft()) + ", ";
        }
        target += curNode.getItem();
        if(curNode.getRight() != null) {
            target += ", " + this.getPreOrderStr(curNode.getRight());
        }
        return target;
    }

    /**
     * Returns the post-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getPostOrderStr() {
        return this.getPostOrderStr(this.root);
    }

    /**
     * Returns the post-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getPostOrderStr(BinaryTreeNode curNode) {
        String target = "";
        if(curNode == null) {
            return target;
        }

        if(curNode.getLeft() != null) {
            target += this.getPreOrderStr(curNode.getLeft()) + ", ";
        }
        if(curNode.getRight() != null) {
            target += this.getPreOrderStr(curNode.getRight()) + ", ";
        }
        target += curNode.getItem();
        return target;
    }
}
