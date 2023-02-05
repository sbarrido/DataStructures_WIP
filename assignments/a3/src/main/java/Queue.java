class EmptyQueueE extends Exception{}

public class Queue<E> {
    private DoublyLinkedList<E> q;
    private int size;

    // TODO: default constructor
    public Queue(){

    }

    // TODO: Put element at end of queue
    public void enqueue(E elem){

    }

    // TODO: Return the head of the queue; If there's nothing to return then throw EmptyQueueE
    public E dequeue() throws EmptyQueueE {
        return null;
    }

    // TODO: Without affecting the queue, return the element at the top of the queue
    public E peek() throws IndexOutOfBoundsException{
        return null;
    }

    public int size() {
        return this.size;
    }

    // TODO: Checks if inputted is the same Queue
    public boolean equals(Object o){
        return false;
    }

    public String toString(){
        return "" + q.size();
    }
}
