class EmptyStackE extends Exception{}

public class Stack<E>{
    private DoublyLinkedList<E> st;
    private int size;

    //  default constructor
    public Stack(){
        st = new DoublyLinkedList<>();
        this.size = 0;
    }

    // TODO: Push the element to the top of stack
    public void push(E elem){
        st.insertAtHead(elem);
        this.size++;
    }

    // TODO: Pop the element off the top of the stack. If nothing to pop, throw EmptyStackE
    public E pop() throws EmptyStackE {
        E target;
        try {
            target = st.deleteAtHead();
            this.size--;
        } catch(Exception ex ) {
            throw new EmptyStackE();
        }

        return target;
    }

    // TODO: Without affecting the stack, return the element at the top of the stack
    public E peek() throws IndexOutOfBoundsException{
        if(this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return st.get(0);
    }

    public int size() {
        return this.size;
    }

    // Check if some other object is the same Stack
    public boolean equals(Object o){
        boolean target = false;
        if(o instanceof Stack) {
            Stack other = (Stack) o;
            target = this.st.equals(other.st);
        }
        return target;
    }

    public String toString(){
        return st.toString();
    }

}
