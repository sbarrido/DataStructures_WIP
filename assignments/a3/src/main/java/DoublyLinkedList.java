class EmptyListE extends Exception{}

public class DoublyLinkedList<E> {

    private NodeDL<E> head;
    private NodeDL<E> tail;
    private int size;

    // TODO: default constructor
    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // TODO: secondary constructor
    public DoublyLinkedList(NodeDL<E> head, NodeDL<E> tail){
        this.head = head;
        this.tail = tail;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    // Insert elem at the start of the DoublyLinkedList
    void insertAtHead(E elem){
        NodeDL target = new NodeDL(elem);
        if(this.size == 0) {
            this.head = target;
        }
        target.next = this.head;
        target.prev = null;
        this.head.prev = target;

        this.size++;
        this.head = target;
        if(this.size == 1) {
            this.tail = this.head;
        }
    }

    //  Insert elem at the end of the DoublyLinkedList
    void insertAtTail(E elem){
        NodeDL target = new NodeDL(elem);
        target.next = null;
        target.prev = this.tail;
        if(this.tail != null) {
            this.tail.next = target;
        }

        this.size++;
        this.tail = target;
        if(this.size == 1){
            this.head = this.tail;
        }
    }

    //  Delete the element from the start of the DoublyLinkedList. Throw an EmptyListE if there's nothing to delete
    E deleteAtHead() throws EmptyListE{
        if(this.size == 0) { throw new EmptyListE(); }

        E target = this.head.data;
        this.head = this.head.next;
        if(this.head != null) {
            this.head.prev = null;
        }

        this.size--;
        return target;
    }


    //  Delete the element from the end of the DoublyLinkedList. Throw an EmptyListE if there's nothing to delete
    E deleteAtTail() throws EmptyListE{
        if(this.size == 0) { throw new EmptyListE(); }

        E target = this.tail.data;
        this.tail = this.tail.prev;
        if(this.tail != null) {
            this.tail.next = null;
        }

        this.size--;
        return target;
    }

    //  Get the element at some position. If it's not possible, throw an IndexOutOfBoundsException
    E get (int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        NodeDL<E> curr = this.head;
        for(int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.data;
    }

    // Search the DoublyLinkedList for elem. If not found, return -1;
    public int search(E elem){
        int index = 0;

        NodeDL curr = this.head;
        boolean found = false;
        while(curr != null) {
            if(curr.data == elem) {
                found = true;
                break;
            }

            curr = curr.next;
            index++;
        }
        if(!found) index = -1;
        return index;
    }

    //  When passed some object, return true if it's a DoublyLinkedList, has the same elements in the same order.
    public boolean equals(Object o){
        boolean isEqual = false;

        if(o instanceof DoublyLinkedList) {
            DoublyLinkedList target = (DoublyLinkedList) o;
            if(this.size == target.size) {
                NodeDL curr = this.head;
                NodeDL other = target.head;

                while(curr != this.tail) {
                    if(curr.data != other.data) {
                        break;
                    }
                    curr = curr.next;
                    other = other.next;
                }

                if(curr == this.tail) { isEqual = true; }
            }
        }
        return isEqual;
    }

    public String toString(){
        String ret = "";
        NodeDL<E> temp = head;
        for(int i = 0; i < size; i++){
            ret += temp.data + " ";
            temp = temp.next;
        }
        return ret;
    }
}
