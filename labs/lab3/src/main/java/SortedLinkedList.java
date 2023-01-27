public class SortedLinkedList {

    /**
     * The 0th indexed Node in the list, the nth indexed Node in the list.
     */
    Node head, tail;

    /**
     * Creates a SortedLinkedList.
     */
    public SortedLinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Inserts the given data at a location that maintains sorted order (ascending).
     * @param data The value to be inserted into the list.
     */
    public void insertSorted(int data) {
    }

    /**
     * Deletes the Node at the given index.
     * @param data Index of the int to be deleted.
     * @return If the data was deleted.
     */
    public boolean delete(int data) {
        return false;
    }

    /**
     * Gets the int at the given index. Throws an IndexOutOfBoundException if index
     * is negative or too large.
     * @param idx Index of the int to be return.
     * @return The int at the given index
     */
    public int get(int idx) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * Searches for the given int and returns its index if found. If the int is not
     * found, returns -1.
     * @param data The int to search for.
     * @return The data at the given index
     */
    public int search(int data) {
        return -1;
    }

    @Override
    public String toString() {
        return printList();
    }

    /**
     * A recursive helper for toString that generates a String representation of this.
     * @return A String representation of the this.
     */
    private String printList() {
        String listStr = "";
        String delimiter = ", ";
        Node cur = head;
        while (cur != null) {
            listStr += cur.data + delimiter;
            cur = cur.next;
        }

        return listStr.substring(0, listStr.length()-delimiter.length());
    }
}
