public class BinaryTreeNode {

    private int item;
    private BinaryTreeNode parent, left, right;

    public BinaryTreeNode(int item, BinaryTreeNode parent, BinaryTreeNode left, BinaryTreeNode right) {
        this.item = item;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public int getItem() {
        return item;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}