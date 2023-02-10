import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable {
    private final int INITIAL_CAPACITY = 11;
    private int capacity = INITIAL_CAPACITY;
    private int size = 0;
    private List<LinkedList<Entry>> entries;

    public HashTable() {
        this.entries = new ArrayList<>(INITIAL_CAPACITY);
        for(int i = 0; i < INITIAL_CAPACITY; i++) {
            this.entries.add(i, new LinkedList<Entry>());
        }
    }

    public List<LinkedList<Entry>> getEntries() {
        return entries;
    }

    public int getSize() {
        return size;
    }
    public int getCapacity() { return this.capacity; }
    private int hash(String key) {
        int index = key.hashCode() % capacity;
        if(index < 0) index *= - 1;

        return index;
    }


    public void put(String key, String value) {
        //Find index using Hash
        int index = this.hash(key);
        if(this.entries.get(index) != null) {
            LinkedList<Entry> bin = this.entries.get(index);
            //Search in bin
            boolean found = false;
            for(Entry e : bin) {
                //If found == update entry
                if(e.key.equals(key)) {
                    e.value = value;
                    found = true;
                    break;
                }
            }
            //!found = new entry added
            if(!found) {
                this.size++;
                double loadFactor = (double) this.size / this.capacity;
                if(loadFactor >= 0.5) {
                    this.rehash();
                }
                bin.add(new Entry(key, value));
            }
        }
    }

    public String get(String key) {
        //Init Target and Index - hashing key
        String target = null;
        int index = this.hash(key);

        //Search non-null bins
        //Return Entry Found
        //else null
        if(this.entries.get(index) != null) {
            LinkedList<Entry> bin = this.entries.get(index);
            for(Entry e : bin) {
                if(e.key.equals(key)) {
                    target = e.value;
                    break;
                }
            }
        }

        return target;
    }

    public void remove(String key) {
        //Hashed index
        int index = hash(key);

        //Check for non-null bin
        if(this.entries.get(index) != null) {
            LinkedList<Entry> bin = this.entries.get(index);
            Entry target = null;

            //search for entry with same key in bin
            //assigns target if found
            for(Entry e : bin) {
                if(e.key.equals(key)) {
                    target = e;
                    break;
                }
            }

            //Found target is removed
            //size decrement
            if(target != null) {
                bin.remove(target);
                this.size--;
            }
        }
    }

    private void rehash() {
        //Double capacity
        //Create temp List
        //Init LinkedList<Entry> within temp List
        this.capacity = capacity * 2;
        ArrayList<LinkedList<Entry>> tmp = new ArrayList<>(this.capacity);
        for(int i = 0; i < this.capacity; i++) {
            tmp.add(new LinkedList<>());
        }

        //Iterate through current entries
        //Check non-null 'bins'
        //Each Entry is rehashed and stored within temp List
        for(int i = 0; i < this.entries.size(); i++) {
            LinkedList<Entry> bin = this.entries.get(i);
            if(bin != null) {
                for(Entry e : bin) {
                    int rehash = this.hash(e.key);
                    tmp.get(rehash).add(e);
                }
            }
        }

        //Reassign entries to 2x capacity tmp
        this.entries = tmp;
    }
}
