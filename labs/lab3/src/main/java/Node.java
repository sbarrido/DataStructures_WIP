public class Node {

    /**
     * The integer to be stored at the current Node.
     */
    int data;
    /**
     * The next Node in the SortedLinkedList.
     */
    Node next;

    /**
     * Creates a new Node and links it to null or the next Node in the SortedLinkedList.
     * @param data The integer to be stored at the current Node.
     * @param next The next Node in the SortedLinkedList.
     */
    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
