import java.util.Arrays;

public class SortedArrayList {
    private int size;
    private int capacity;
    private int[] ls;

    // TODO: default constructor - store 10 elements with nothing in the array
    // Calls Constructor(int capacity); capacity = 10;
    public SortedArrayList(){
        this(10);
    }

    // TODO: secondary constructor - store some capacity elements with nothing in the array
    // Constructor: int, sets the capacity
    // initializes the internal array to capacity
    // initializes the size to 0
    public SortedArrayList(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.ls = new int[this.capacity];
    }

    // TODO: insertSorted - inserts the value but the array should be sorted; resize to double capacity if needed
    public void insertSorted(int value){
        boolean full = this.size == this.capacity;
        if(full) {
            //Double internal array
            this.capacity *= 2;
            int[] tmpDouble = new int[this.capacity];
            tmpDouble = Arrays.copyOfRange(this.ls, 0, this.size- 1);
            this.ls = tmpDouble;
        }
        //Insert value into array
        for(int i = 0; i < this.capacity; i++) {
            if(this.ls[i] > value) {
                int tmpStore = this.ls[i];
                this.ls[i] = value;

                this.insertSorted(tmpStore);
            } else {
                this.ls[i+1] = value;
            }
        }

        this.size += 1;
    }

    // TODO: delete - deletes a value at the index; if index is not possible throw the exception
    // No empty spaces in the array should be left between elements
    public void delete(int index) throws IndexOutOfBoundsException{
        if(isValid(index)) {
            for(int i = index; i < this.size; i++) {
                this.ls[i] = this.ls[i + 1];
            }
            this.size -= 1;
        }
    }

    // TODO: get - returns the value at the index; if index not possible throw exception
    public int get(int index) throws IndexOutOfBoundsException{
        int target = -1;
        if(isValid(index)) {
            target = this.ls[index];
        }
        return target;
    }
    //Helper function
    // Returns true if given index is within bounds
    // throws IndexOutOfBoundsException
    private boolean isValid(int index) throws IndexOutOfBoundsException{
        boolean valid = true;
        if(index < 0 || index >= this.size) {
            valid = false;
        }

        return valid;
    }

    // TODO: search - searches for the value and returns its found index; if not found return -1
    public int search(int target){
        int found = binSearch(target, this.size / 2);
        return found;
    }
    private int binSearch(int target, int index) {
        int rslt = -1;
        if(index < 0 || index > -1 ){
            return rslt;
        } else {
            if(this.ls[index] == target) {
                rslt = index;
            }

            if(this.ls[index] > target) return binSearch(target, index + index / 2);
            if(this.ls[index] < target) return binSearch(target, index - index / 2);
        }

        return rslt;
    }
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
