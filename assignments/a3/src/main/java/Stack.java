class EmptyStackE extends Exception{}

public class Stack<E>{
    private DoublyLinkedList<E> st;
    private int size;

    // TODO: default constructor
    public Stack(){

    }

    // TODO: Push the element to the top of stack
    public void push(E elem){

    }

    // TODO: Pop the element off the top of the stack. If nothing to pop, throw EmptyStackE
    public E pop() throws EmptyStackE {
        return null;
    }

    // TODO: Without affecting the stack, return the element at the top of the stack
    public E peek() throws IndexOutOfBoundsException{
        return null;
    }

    public int size() {
        return this.size;
    }

    // TODO: Check if some other object is the same Stack
    public boolean equals(Object o){
        return false;
    }

    public String toString(){
        return st.toString();
    }

}
