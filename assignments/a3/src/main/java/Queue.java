class EmptyQueueE extends Exception{}

public class Queue<E> {
    private DoublyLinkedList<E> q;
    private int size;

    // TODO: default constructor
    public Queue(){
        q = new DoublyLinkedList<>();
        this.size = 0;
    }

    // TODO: Put element at end of queue
    public void enqueue(E elem){
        this.q.insertAtTail(elem);
        this.size++;
    }

    // TODO: Return the head of the queue; If there's nothing to return then throw EmptyQueueE
    public E dequeue() throws EmptyQueueE {
        if(this.size == 0) {
            throw new EmptyQueueE();
        }

        E target;
        try {
            target = this.q.deleteAtHead();
            this.size--;
        }catch(Exception ex) {
            throw new EmptyQueueE();
        }

        return target;
    }

    // TODO: Without affecting the queue, return the element at the top of the queue
    public E peek() throws IndexOutOfBoundsException{
        return this.q.get(0);
    }

    public int size() {
        return this.size;
    }

    // TODO: Checks if inputted is the same Queue
    public boolean equals(Object o){
        boolean target = false;
        if(o instanceof Queue) {
            Queue other = (Queue) o;
            target = this.q.equals(other);
        }

        return target;
    }

    public String toString(){
        return "" + q.size();
    }
}
