import java.lang.reflect.Array;

public class ArrayList<E extends Comparable> extends List<E> {
    private int size;
    private int capacity;
    private Object[] ls;

    // TODO: default: should create an arraylist that is capable of holding 10 element
    public ArrayList(){

    }

    // TODO: second constructor - should create an arraylist that is capable of holding x element
    public ArrayList(int capacity){

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

    // TODO: insert --> takes some element and inserts it at the end of the arraylist, resizes to double capacity if no space
    public void add(E value){
    }

    // TODO: delete - deletes an element at said index; moves elements such that there are no gaps in between them
    public void delete(int index) throws IndexOutOfBoundsException{
    }

    // TODO: searches one by one to find the element, if it doesn't exist then return -1
    public int search(E value){
        return -1;
    }

    // TODO: given some other arraylist, compare it to see if it has the same contents
    public boolean equals(Object o){
        return false;
    }

    // to help you
    public String toString(){
        String ret = "";
        for(int i = 0; i < this.size; i++){
            ret += i + ": "+ this.ls[i] + "\n";
        }
        return ret;
    }

}
