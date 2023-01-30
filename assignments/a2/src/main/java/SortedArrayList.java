import java.lang.reflect.Array;

public class SortedArrayList<E extends Comparable> extends List<E> {

    private int size;
    private int capacity;
    private Object[] ls;

    // default: should create a sortedarraylist that is capable of holding 10 element
    public SortedArrayList(){
        this((Class<E>) Comparable.class, 10);
    }

    // second constructor - should create a sortedarraylist that is capable of holding x element that size
    public SortedArrayList(Class<E> c, int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.ls = new Comparable[this.capacity];
    }

    public int size(){
        return this.size;
    }

    public E get(int index) throws IndexOutOfBoundsException{
        if(index >= this.size | index < 0){
            throw new IndexOutOfBoundsException();
        }
        return (E) this.ls[index];
    }

    //inserts element while maintaining the sorted order of the contents; resize to double capacity if no space
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


    //delete - deletes an element at said index; moves elements such that there are no gaps in between them
    public void delete(int index) throws IndexOutOfBoundsException{
        //Case: bad index
        //throw ex
        boolean badIndex = index < 0 || index >= this.size;
        if(badIndex) throw new IndexOutOfBoundsException();

        //Case: Delete
        for(int i = index; i < this.size; i++) {
            this.ls[i] = this.ls[i+1];
        }

        //Reduce Size
        size--;
    }

    //search - binary search O(log(n)) for the element; returns -1 if not found
    public int search(E value){
        return binSearch(value, this.size / 2);
    }

    private int binSearch(E target, int index) {
        int result = -1;

        //INDEX OUT OF BOUNDS
        if(index >= this.size) {
            return result;
        }
        //compareTo
        // flag = 0 -> target == this.ls[index]
        // flag < 0 -> target < this.ls[index]
        //flag > 0 -> target > this.ls[index]
        int flag = target.compareTo(this.ls[index]);
        boolean equal = flag == 0;
        boolean small = flag < 0;
        boolean big = flag > 0;

        //TARGET FOUND
        //RETURN RESULT = INDEX
        if(equal){
            result = index;
            return result;
        } else if(small) {
            //Target < this.ls[index]
            //Check if index 1, capture 0th item
            //else check pivot in -half
            if(index == 1) {
                return binSearch(target, 0);
            } else {
                return binSearch(target, index - index / 2);
            }
        } else if(big) {
            //Target > this.ls[index]
            //Check if 2nd to last index, capture last item
            //Check pivot in +half
            if(index == this.size - 2) {
                return binSearch(target, this.size - 1);
            } else {
                return binSearch(target, index + index / 2);
            }
        }

        //Always return result
        //index OR -1
        return result;
    }

    // TODO: given some other sortedarraylist, compare it to see if it has the same contents (also in same order)
    public boolean equals(Object o){
        boolean isEqual = false;
        if(o instanceof SortedArrayList<?>) {
            SortedArrayList tmp = (SortedArrayList) o;
            if(tmp.get(0) instanceof Comparable) {
                Comparable val = (Comparable) tmp.get(0);
                if(val.compareTo(this.ls[0]) == 0) {
                    isEqual = true;
                }
            }
        }

        return isEqual;
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
