public class BinaryNode<E extends Comparable<E>> implements TreePrinter.PrintableNode{
    private E data;
    private BinaryNode<E> left;
    private BinaryNode<E> right;
    private int height;
    private int size;
    private BinaryNode<E> parent;

    public BinaryNode(E data){
       this(data, null, null, null);
       this.height = 1;
       this.size = 1;
    }

    // Set up the BinaryNode
    public BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right, BinaryNode<E> parent){
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;

        int lHeight = 0;
        int rHeight = 0;
        int lSize = 0;
        int rSize = 0;
        if(left != null) {
            lHeight = left.height;
            lSize = left.size;
        }
        if(right != null) {
            rHeight = right.height;
            rSize = right.size;
        }
        this.height = Math.max(lHeight, rHeight) + 1;
        this.size = lSize + rSize + 1;
    }
    // Access fields
    E data() { return this.data; };
    BinaryNode<E> left() {
        return this.left;
    }
    BinaryNode<E> right() {
        return this.right;
    }
    BinaryNode<E> parent() { return this.parent; }

    // Setter fields
    void setLeft(BinaryNode<E> left) {
        this.left = left;
        if(left != null) left.setParent(this);
    }
    void setRight(BinaryNode<E> right) {
        this.right = right;
        if(right != null) right.setParent(this);
    }
    void setParent(BinaryNode<E> parent) {
        this.parent = parent;
    }
    void setData(E data) { this.data = data; }
    void setHeight(int h){
        this.height = h;
    }

    // Basic properties
    int height() {
        return this.height;
    }
    int size() { return this.size; }
    void setSize(int val) { this.size = val; }
    boolean isBalanced() {
        int leftHeight = (hasLeft()) ? left.height() : 0;
        int rightHeight = (hasRight()) ? right.height() : 0;

        boolean balance = Math.abs(leftHeight - rightHeight) < 2;
        return balance;
    }
    boolean hasLeft(){
        return left == null ? false : true;
    }
    boolean hasRight(){
        return right == null ? false :true;
    }
    boolean hasParent(){
        return parent == null ? false :true;
    }

    public boolean equals(BinaryNode<E> other){
        if(other == null) return false;
        return other.data.equals(this.data);
    }

    public int compareTo(Object o) {
        int result = -1;
        if(o instanceof BinaryNode) {
            E other = (E) ((BinaryNode) o).data;
            result = this.data.compareTo(other);
        }
        return result;
    }
    // Can use these to help debug
    public TreePrinter.PrintableNode getLeft() {
        return left == null ? null :  left;
    }
    public TreePrinter.PrintableNode getRight() {
        return right == null ? null : right;
    }
    public String getText() {
        return String.valueOf(data);
    }
    public String toString(){
        String ret = "";
        return "root " + this.data + " Left: " +(hasLeft() ? left.data : null)  + " Right: " +(hasRight() ? right.data : null) +
                " parent: " + (hasParent() ? parent.data : null) ;
    }

}
