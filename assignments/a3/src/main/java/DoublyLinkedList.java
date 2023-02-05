class EmptyListE extends Exception{}

public class DoublyLinkedList<E> {

    private NodeDL<E> head;
    private NodeDL<E> tail;
    private int size;

    // TODO: default constructor
    public DoublyLinkedList(){
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
        target.next = this.head;
        target.prev = null;

        this.head = target;
    }

    //  Insert elem at the end of the DoublyLinkedList
    void insertAtTail(E elem){
        NodeDL target = new NodeDL(elem);
        target.next = null;
        target.prev = this.tail;

        this.tail = target;
    }

    // TODO: Delete the element from the start of the DoublyLinkedList. Throw an EmptyListE if there's nothing to delete
    E deleteAtHead() throws EmptyListE{
        if(this.size == 0) { throw new EmptyListE(); }

        E target = this.head.data;
        this.head = this.head.next;
        this.head.prev = null;

        return target;
    }


    // TODO: Delete the element from the end of the DoublyLinkedList. Throw an EmptyListE if there's nothing to delete
    E deleteAtTail() throws EmptyListE{
        if(this.size == 0) { throw new EmptyListE(); }

        E target = this.tail.data;
        this.tail = this.tail.prev;
        this.tail.next = null;

        return target;
    }

    // TODO: Get the element at some position. If it's not possible, throw an IndexOutOfBoundsException
    E get (int index) throws IndexOutOfBoundsException{
        if(index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        NodeDL curr = this.head;
        while(index > 0) {
            curr = curr.next;
        }

        return (E) curr.data;
    }

    // TODO: Search the DoublyLinkedList for elem. If not found, return -1;
    public int search(E elem){
        int index = 0;

        NodeDL curr = this.head;
        boolean found = false;
        while(curr != this.tail & !found) {
            if(curr.data == elem) {
                found = true;
            }

            curr = curr.next;
            index++;
        }
        return index;
    }

    // TODO: When passed some object, return true if it's a DoublyLinkedList, has the same elements in the same order.
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
