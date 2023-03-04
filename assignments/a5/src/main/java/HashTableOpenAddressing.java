import static java.lang.Math.abs;

public class HashTableOpenAddressing<K, V> extends Dictionary<K,V>{

    private int capacity;  // size of the table
    private int size;  // number of elements in the table
    private int previousPrime; //store prev prime so that it is not calculated again and again in double hashing.
    private int mode;

    public static int LINEARPROBING = 1;
    public static int QUADRATICPROBING = 2;
    public static int DOUBLEHASHING = 3;
    private double loadFactor;
    private Entry<K, V>[] table;

    public HashTableOpenAddressing() {
        this(DOUBLEHASHING, 11, 0.75);  // default initial capacity of 11
    }

    public HashTableOpenAddressing(int mode) {
        this( mode, 11, 0.75);
    }

    public HashTableOpenAddressing(int capacity, double loadFactor) {
        this(DOUBLEHASHING, capacity, loadFactor);
    }

    /*
    This constructor takes a mode, capacity, loadFactor, and sets those variables + relevant variables
    according to such. Additionally, it will set up the table according to the capacity.
    If the mode is DOUBLEHASHING, please calculate the previousPrime and set it.
     */
    public HashTableOpenAddressing(int mode, int capacity, double loadFactor) {
        this.size = 0;
        this.mode = mode;
        this.capacity = capacity;
        this.loadFactor = loadFactor;

        if(this.mode == DOUBLEHASHING) {
            this.previousPrime = this.previousPrime(this.capacity);
        }

        this.table = new Entry[this.capacity];
    }

    private int previousPrime(int number) {
        while( true ) {
            if( isPrime( number ) ) {
                return number;
            }
            number--;
        }
    }


    //
    //  second hash should be prevPrime - (key % prevPrime)...shouldn't be negative
    private int hash2(K key) {
        return this.previousPrime - (hash(key) % this.previousPrime);
    }


    //  gets the next index given the index and the offset. Please take into account the mode.
    private int getNextIndex(K key, int collCount) {
        int index = hash(key) % this.capacity;
        switch(this.mode) {
            case 1:
                //Linear Probe
                index += collCount;
                break;
            case 2:
                //Quad probe
                index += (collCount * collCount);
                break;
            case 3:
                //Double Hash
                index += (collCount * hash2(key));
                break;
        }
        return index % this.capacity;
    }

    //  Put a key, value pair into the table.
    //  If the key already exists/inactive, override it. Else, put it into the table.
    //  Throw a RuntimeException if there is an infinite loops.
    public void put(K key, V value) {
        int firstIndex = hash(key) % this.capacity;
        Entry<K, V> entry = this.table[firstIndex];

        while(firstIndex >= this.capacity) {
            firstIndex = this.capacity - firstIndex;
        }
        if(entry == null || !entry.isActive) {
            this.table[firstIndex] = new Entry(key, value);
            this.size++;
        } else if(entry.getKey().equals(key)) {
            entry.setValue(value);
            this.table[firstIndex] = entry;
        } else {
            int collCount = 0;
            int index = firstIndex;
            boolean foundKey = false;
            while(entry.isActive) {
                collCount++;

                //While looping check if new entry has same key
                //if true, update value and break loop
                if(entry.getKey().equals(key)) {
                    entry.setValue(value);
                    this.table[index] = entry;
                    foundKey = true;
                    break;
                } else {
                    //Calculate new index to check based on collCount / mode
                    //Retrieve Entry at new index
                    index = this.getNextIndex(key, collCount);
                    while(index >= this.capacity) { index = index - this.capacity; }

                    entry = this.table[index];
                    if(entry == null || !entry.isActive) { break; }
                    //calculated nextIndex must not be same as stored firstIndex
                    //results in loop, throw runtime exception
                    if(index == firstIndex) {
                        throw new RuntimeException();
                    }
                }
            }

            //Exited while loop
            //if Key was not found, then add entry at found index
            if(!foundKey) {
                this.table[index] = new Entry(key, value);
                this.size++;
            }
        }

        //Resize if necessary
        if(this.size > this.loadFactor * this.capacity) {
            this.resize();
        }
    }

    //
    //  Retrieves the value of a key in the table.
    //  If there is an infinite loop, throw a RuntimeException.
    //  Return null if not there.
    public V get(K key) {
        int index = hash(key) % this.capacity;
        Entry<K, V> entry = this.table[index];
        V target = null;

        int collCount = 0;
        while(entry != null) {
            //Entry.key found, retrieve value
            //break loop
            if(entry.getKey().equals(key)) {
                target = entry.getValue();
                break;
            } else {
                //Check new index based on collCount
                collCount++;
                int probeIndex = this.getNextIndex(key, collCount);
                while(probeIndex >= this.capacity) { probeIndex = probeIndex - this.capacity; }
                entry = this.table[probeIndex];

                //Check for looped index
                if(probeIndex == index) {
                    throw new RuntimeException();
                }
            }
        }
        return target;
    }

    //  Searches the table to see if the key exists or not.
    public boolean containsKey(K key) {
        boolean found = false;
        for(Entry<K, V> e : this.table) {
            if(e != null && e.isActive) {
                if(e.getKey().equals(key)) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    // TODO:
    //  Set the key as inactive if it exists in the table. Return true.
    //  If there is no key, return false.
    //  If there's an infinite loop, throw a RuntimeException.
    public boolean remove(K key) {
        boolean removed = false;
        int index = this.hash(key) % this.capacity();
        Entry<K, V> entry = this.table[index];
        if(entry != null) {
            if(entry.getKey().equals(key)) {
                entry.isActive = false;
                this.table[index] = entry;
                removed = true;
                this.size--;
            } else {
                int collCount = 0;
                while(entry != null) {
                    int probeIndex = this.getNextIndex(key, collCount);
                    while(probeIndex >= this.capacity) { probeIndex = probeIndex - this.capacity; }
                    entry = this.table[probeIndex];

                    if(entry.getKey().equals(key)) {
                        entry.isActive = false;
                        this.table[probeIndex] = entry;
                        removed = true;
                        this.size--;
                        break;
                    }

                    if(probeIndex == index) {
                        throw new RuntimeException();
                    }
                }
            }
        }
        return removed;
    }

    public int size() {
        return size;
    }
    public int capacity() {
        return this.capacity;
    }
    public Entry[] getTable() {
        return this.table;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //
    //  Calculate the absolute hash of the key. Do not overthink this.
    private int hash(K key) {
        int hash = key.hashCode();
        if(hash < 0 ) { hash *= -1; }

        return hash;
    }


    private boolean isPrime(int number) {
        for( int i = 2; i <= number / 2; i++ ) {
            if( number % i == 0 ) {
                return false;
            }
        }
        return true;
    }

    private int nextPrime(int number) {
        while( true ) {
            if( isPrime( number ) ) {
                return number;
            }
            number++;
        }
    }

    //
    //  Set the capacity to the nextPrime of the capacity doubled.
    //  Calculate the previousPrime and set up the new table with the old tables'
    //  contents now hashed to the new.
    private void resize() {
        this.capacity = nextPrime(this.capacity * 2);

        Entry<K, V>[] tmp = new Entry[this.capacity];
        for(int i = 0; i < this.table.length; i++) {
            Entry<K, V> item = this.table[i];
            if(item != null) {
                int index = this.hash(item.getKey()) % this.capacity;
                int collCount = 0;

                boolean empty = tmp[index] == null;
                while(!empty) {
                    collCount++;
                    index = this.getNextIndex(item.getKey(), collCount) % this.capacity;

                    empty = tmp[index] == null;
                }

                tmp[index] = item;
            }
        }

        this.table = tmp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int index = 0;

        for (Entry<K, V> entry : table) {
            sb.append(index + ": ");
            index++;
            if (entry != null) {
                sb.append(entry.getKey() + "=" + entry.getValue() + ",");
            }
            sb.append(";");
        }

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }

    public class Entry<K, V> {
        private K key;
        private V value;

        private boolean isActive;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            isActive = true;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean getActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }
    }

    public static void main(String []args ) {
        HashTableOpenAddressing<Integer, Integer> hashTable = new HashTableOpenAddressing<>(QUADRATICPROBING, 10, 1);

        hashTable.put(2,2);
        System.out.println(hashTable);
        for (int i = 0; i < 280; i += 10) {
            hashTable.put(i, i);
            System.out.println(hashTable.get(i));
        }
    }

}
