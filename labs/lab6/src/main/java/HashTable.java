import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HashTable {
    private final int INITIAL_CAPACITY = 11;
    private List<Entry> entries;
    private int size;
    private int capacity = INITIAL_CAPACITY;

    public HashTable() {
        this.entries = new ArrayList<>();
        for(int i = 0; i < INITIAL_CAPACITY; i++) {
            this.entries.add(null);
        }
        this.size = 0;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public int getSize() {
        return size;
    }

    /**
     * Generates the hash (index) for the given key and the number of collisions encountered. This should be computed
     * using double hashing; please implement the function according to the following requirements:
     *  - generate the previous prime from the capacity (given) and call it 'prevPrime'
     *  - 'hash1' is an int that is the hashcode of the key
     *  - 'hash2' is an int that is: 'prevPrime' - (hash1 % 'prevPrime')
     *  - the returned hash is ('hash1' + collisions * 'hash2') % capacity
     */
    private int hash(String key, int collisions) {
        int prevPrime = this.previousPrime(this.capacity);
        int hash1 = key.hashCode();
        if(hash1 < 0) { hash1 *= -1; }
        int hash2 = prevPrime - (hash1 % prevPrime);

        return (hash1 + collisions * hash2) % this.capacity;
    }

    /**
     * Finds the prime immediately before the given number.
     */
    private int previousPrime(int number) {
        //https://stackoverflow.com/a/62823875
        for(int i = number - 1; i >= 2; i--) {
            boolean prime = true;
            for(int j = 2; j <= Math.sqrt(i); j++ ) {
                if(i % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime == true) {
                return i;
            }
        }
        return 2;
    }

    /**
     * Inserts the given key-value pair into the HashTable or updates the value of the current Entry if the key is
     * already stored.
     */
    public void put(String key, String value) {
        //init index w/ hash
        int collCount = 0;
        int index = hash(key, collCount);

        //Valid index
        //(1) Null entry
        //(2) Entry.key = key
        //(3) Tombstone Entry
        Entry target = this.entries.get(index);
        boolean valid = target == null ||
                target.getKey().equals(key) ||
                target.getType() == Entry.Type.TOMBSTONE;
        while(!valid) {
            //!valid
            //Find new Hashed index, increment collision counter
            index = hash(key, collCount++);
            target = this.entries.get(index);
            valid = target == null ||
                    target.getKey().equals(key) ||
                    target.getType() == Entry.Type.TOMBSTONE;
        }

        //Null or TOMBSTONE
        //create new Entry w/ Key and value
        if(target == null || target.getType() == Entry.Type.TOMBSTONE) {
            target = new Entry(key, value);
        } else {
            //Found Entry.key
            //Update Value
            target.setValue(value);
            this.size--;
        }

        //Add Altered Entry at index
        this.size++;
        this.entries.set(index, target);
        if(this.size*2 >= this.capacity) this.rehash();
    }

    /**
     * Gets the value from the Entry in the HashTable containing the given key and returns it. Returns null if the key
     * was not found.
     */
    public String get(String key) {
        //init hash index
        int collCount = 0;
        int index = hash(key, collCount);
        Entry target = this.entries.get(index);
        //Valid entry is
        //(1) null
        //(2) found same Key
        boolean valid = target == null ||
                        target.getKey() == key;
        while(!valid) {
            //Not Valid:
            //call hash with incremented collision (doubleHash)
            index = hash(key, collCount++);
            target = this.entries.get(index);
            valid = target == null ||
                    target.getKey() == key;
        }

        //Init string value
        //(1) return null, target not found
        //(2) return target.value, target found
        String targetVal = null;
        if(target != null) {
            if(target.getType() == Entry.Type.TOMBSTONE) {
                targetVal = null;
            } else {
                targetVal = target.getValue();
            }
        }

        return targetVal;
    }

    /**
     * If the key is found in the HashTable, marks the corresponding entry as a deleted (a tombstone).
     */
    public void remove(String key) {
        int collCount = 0;
        int index = hash(key, collCount);
        Entry target = this.entries.get(index);
        boolean valid = target == null ||
                        target.getKey().equals(key);

        //Loop until Stop Conditions:
        //(1) Null Entry = stop loop
        //(2) Entry.key.equals(key) = stop loop
        while(!valid) {
            index = hash(key, collCount++);
            target = this.entries.get(index);
            valid = target == null ||
                    target.getKey().equals(key);
        }

        //Target Found - kill
        if(target != null) {
            target.markTombstone();
            this.size--;
        }
    }

    /**
     * Increase the capacity of the 'entries' ArrayList (double the capacity then find the next prime) and puts the
     * key-value pairs of the smaller 'entries' into the
     */
    private void rehash() {
        this.capacity *= 2;
        this.capacity = this.nextPrime(this.capacity);
        ArrayList<Entry> tmp = new ArrayList<>(this.capacity);
        for(int i = 0; i < this.capacity; i++) {
            tmp.add(null);
        }
        //Iterate through current non-null entries
        for(Entry entry : this.entries) {
            if(entry != null) {
                //Hashes with new capacity
                int collCount = 0;
                int index = this.hash(entry.getKey(), collCount);

                //!valid
                //call hash with incremented collision
                //end case: found index with null entry
                boolean valid = tmp.get(index) == null;
                while(!valid) {
                    index = this.hash(entry.getKey(), collCount++);
                    valid = tmp.get(index) == null;
                }

                //Valid index adds entry
                tmp.add(index, entry);
            }
        }
        
        this.entries = tmp;
    }

    /**
     * Finds the prime immediately following the given number.
     */
    private int nextPrime(int number) {
        // https://stackoverflow.com/a/57904191
        BigInteger b = new BigInteger(String.valueOf(number));
        return (int) Long.parseLong(b.nextProbablePrime().toString());
    }
}
