import java.lang.reflect.Array;

public class SortedArrayList<E extends Comparable> extends List<E> {

    private int size;
    private int capacity;
    private Object[] ls;

    // TODO: default: should create a sortedarraylist that is capable of holding 10 element
    public SortedArrayList(){

    }

    // TODO: second constructor - should create a sortedarraylist that is capable of holding x element that size
    public SortedArrayList(Class<E> c, int capacity){
    }

    public int size(){
        return this.size;
    }

    public E get(int index) throws IndexOutOfBoundsException{
        if(index >= this.size){
            throw new IndexOutOfBoundsException();
        }
        return (E) this.ls[index];
    }

    // TODO: inserts element while maintaining the sorted order of the contents; resize to double capacity if no space
    public void add(E value) {
    }


    // TODO: delete - deletes an element at said index; moves elements such that there are no gaps in between them
    public void delete(int index) throws IndexOutOfBoundsException{
    }

    // TODO: search - binary search O(log(n)) for the element; returns -1 if not found
    public int search(E value){
        return -1;
    }

    // TODO: given some other sortedarraylist, compare it to see if it has the same contents (also in same order)
    public boolean equals(Object o){
        return false;
    }

    // helper
    public String toString(){
        String ret = "";
        for(int i = 0; i < this.capacity; i++){
            ret += i + ": "+ this.ls[i] + "\n";
        }
        return ret;
    }
}
