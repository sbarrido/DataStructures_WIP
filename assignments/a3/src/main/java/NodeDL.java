public class NodeDL<E> {
    E data;
    NodeDL<E> prev;
    NodeDL<E> next;

    public NodeDL(E elem) {
        this.data = elem;
    }

    public String toString() {
        return "" + this.data;
    }

    // TODO: Return whether the other has same type and same pointers
    public boolean equals(Object o){
        return false;
    }
}
