
public class ArrayList<E extends Comparable> extends List<E> {
    private int size;
    private int capacity;
    private Object[] ls;

    // TODO: default: should create an arraylist that is capable of holding 10 element
    public ArrayList(){
        this(10);
    }

    // TODO: second constructor - should create an arraylist that is capable of holding x element
    public ArrayList(int capacity){
        this.capacity = capacity;
        this.size = 0;
        ls = new Object[this.capacity];
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
    public int getCapacity() {
        return this.capacity;
    }
    // TODO: insert --> takes some element and inserts it at the end of the arraylist, resizes to double capacity if no space
    public void add(E value){
        //Case Full:
        // - resize to 2x capacity
        // - copy original
        boolean full = this.size == this.capacity;
        if(full) {
            this.capacity = this.capacity * 2;
            Object[] tmp = new Object[this.capacity];

            for(int i = 0; i < this.size - 1; i++) {
                tmp[i] = this.ls[i];
            }

            this.ls = tmp;
        }
        //Case default(empty, partial, full)
        // - Add to End
        this.ls[this.size++] = value;
    }

    // TODO: delete - deletes an element at said index; moves elements such that there are no gaps in between them
    public void delete(int index) throws IndexOutOfBoundsException{
        //Case OutOBounds:
        // - indexOutOfBounds
        boolean invalid = index < 0 || index >= this.size;
        if(invalid) {
            throw new IndexOutOfBoundsException();
        }
        //Case Default (Beginning,mid, end)
        // - delete at i
        // - shift left
        // - reduce size
        for(int i = index; i < this.size; i++) {
            this.ls[i] = this.ls[i+1];
        }
        this.size--;
    }

    // TODO: searches one by one to find the element, if it doesn't exist then return -1
    public int search(E value){
        int found = -1;
        for(int i = 0; i < this.size; i++) {
            if(this.ls[i] != null) {
                if(value.compareTo(this.ls[i]) == 0) {
                    found = i;
                    break;
                }
            }
        }
        return found;
    }

    //HelperFunction to Search
    //Binary searches for given E target
    // TODO: given some other arraylist, compare it to see if it has the same contents
    public boolean equals(Object o){
        boolean isEqual = false;
        if(o instanceof ArrayList) {

            //POTENTIAL CHECK FOR OBJECT THEN E
            ArrayList<E> otherList = (ArrayList<E>) o;
            int curr = 0;
            for(int i = curr; i < otherList.size(); i++, curr++) {
                isEqual = this.ls[i] == otherList.get(i);
                if(!isEqual) break;
            }
            if(curr == this.size) {
                isEqual = true;
            }
        }
        return isEqual;
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
