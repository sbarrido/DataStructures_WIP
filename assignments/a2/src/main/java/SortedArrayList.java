import java.lang.reflect.Array;

public class SortedArrayList<E extends Comparable> extends List<E> {

    private int size;
    private int capacity;
    private Object[] ls;

    // TODO: default: should create a sortedarraylist that is capable of holding 10 element
    public SortedArrayList(){
        this((Class<E>) Comparable.class, 10);
    }

    // TODO: second constructor - should create a sortedarraylist that is capable of holding x element that size
    public SortedArrayList(Class<E> c, int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.ls = new Comparable[this.capacity];
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
        //If Full, size == capacity
        //- double capacity
        //- reload array
        boolean full = this.size == this.capacity;
        if(full) {
            this.capacity = this.capacity * 2;
            Object[] tmp = new Object[this.capacity];

            for(int i = 0; i < this.size; i++) {
                tmp[i] = this.ls[i];
            }

            this.ls = tmp;
        }
        //Empty Array
        if(this.size == 0){
            this.ls[this.size] = value;
        }

        //Add to non-empty array
        int index = this.size - 1;
        for(int i = index; i >= 0; i--, index--) {
            if(this.ls[i] != null) {
                boolean isBig = value.compareTo(this.ls[i]) > 0;
                //If value is big insert after
                //Case: insert mid and end
                if(isBig) {
                    this.ls[i+1] = value;
                    break;
                }
                //Shift Right
                this.ls[i+1] = this.ls[i];

                //Edge Case: Smallest - insert beginning
                if(i == 0) {
                    this.ls[i] = value;
                }
            }
        }
        //Increase Size all cases of Add
        this.size++;
    }


    // TODO: delete - deletes an element at said index; moves elements such that there are no gaps in between them
    public void delete(int index) throws IndexOutOfBoundsException{
    }

    // TODO: search - binary search O(log(n)) for the element; returns -1 if not found
    public int search(E value){
        return binSearch(value, this.size / 2);
    }

    private int binSearch(E target, int index) {
        int result = -1;

        //TODO FIX FOR INDEX = 0 NEVER CHECKS
        int flag = target.compareTo(this.ls[index]);
        switch(flag) {
            case 0:
                result = index;
                break;
            case -1:
                return binSearch(target, index - index / 2);
            case 1:
                return binSearch(target, index + index / 2);
        }


        return result;
    }

    // TODO: given some other sortedarraylist, compare it to see if it has the same contents (also in same order)
    public boolean equals(Object o){
        return false;
    }

    public int getCapacity() {
        return this.capacity;
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
