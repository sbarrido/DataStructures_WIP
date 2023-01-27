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
        Node newNode = new Node(data, null);
        Node curr = head;

        boolean isStart = curr.data > newNode.data;
        if(!isStart) {
            //Iterate through list
            //check next data if smaller
            boolean found = false;
            boolean isEnd = curr == tail;
            boolean finish = found || isEnd;

            while(!finish) {
                //Check if the next data point is greater than target
                //else, move along pointer
                boolean isSmall = curr.next.data > newNode.data;
                if(isSmall) {
                    //Temp storage of next Node
                    Node tmp = curr.next;

                    //Assign curr.next pointer to newNode
                    //Assign newNode.next to temp storage
                    curr.next = newNode;
                    newNode.next = tmp;
                    found = true;
                } else {
                    //Move pointer to next
                    curr = curr.next;
                }
            }
            //Edge case: Insert at End
            if(isEnd) {
                curr.next = newNode;
                tail = newNode;
            }
        } else {
            //Edge Case: Insert at beginning
            //curr node == head
            newNode.next = curr;
            head = newNode;
        }

    }

    /**
     * Deletes the Node at the given index.
     * @param data Index of the int to be deleted.
     * @return If the data was deleted.
     */
    public boolean delete(int data) {
        Node curr = head;
        boolean success = false;
        boolean isEnd = curr == tail;
        boolean finish = success || isEnd;

        //Loop until finish condition
        //cond: deleted or at end
        while(!finish) {

            //Check next data if target
            //sets curr.next and assigns to targetNode.next
            //'skips' target node
            boolean found = curr.next.data = data;
            if(found) {
                curr.next = cur.next.next;
                success = true;
            }
        }
        return success;
    }

    /**
     * Gets the int at the given index. Throws an IndexOutOfBoundException if index
     * is negative or too large.
     * @param idx Index of the int to be return.
     * @return The int at the given index
     */
    public int get(int idx) {
        //Edge Case: negative indx
        if(indx < 0 ) throw new IndexOutOfBoundsException();

        Node curr = head;
        boolean found = indx == 0;
        boolean isBig = curr == null;
        boolean finish = found || isBig;
        while(!finish) {
            curr = curr.next;
            indx--;
        }
        //Edge Case: Big indx
        if(isBig) throw new IndexOutOfBoundsException();

        return curr.data;
    }

    /**
     * Searches for the given int and returns its index if found. If the int is not
     * found, returns -1.
     * @param data The int to search for.
     * @return The data at the given index
     */
    public int search(int data) {
        //init target index
        //assign curr pointer
        int target = 0;
        Node curr = head;
        //boolean conditions
        //cond: found data, end of list
        boolean found = curr.data == data;
        boolean isEnd = curr == null;
        boolean finish = found || isEnd;

        //Iterate through list while not found && not at end
        while(!finish) {
            curr = curr.next;
            target++;
        }
        //Edge case: pointer is at end
        if(isEnd) {
            target = -1;
        }
        return target;
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
