import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTableWithChaining<K, V> extends Dictionary<K,V>{

    private int capacity;  // size of the table
    private int size;  // number of elements in the table

    private double loadFactor;
    private List<LinkedList<Entry<K, V>>> table;  // hash table

    // Entry class to hold key-value pairs
    private class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
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
    }

    public HashTableWithChaining() {
        this(11, 0.75);  // default initial capacity of 10

    }

    public HashTableWithChaining(int capacity) {
        this( capacity, 0.75);
    }

    /*
    TODO:
    This constructor takes a capacity and loadFactor, and sets those variables + relevant variables
    according to such. Additionally, it will set up the table according to the capacity.
     */
    public HashTableWithChaining(int capacity, double loadFactor) {

    }

    // TODO:
    //  Put a key, value pair into the table.
    //  If the key already exists, update it with the new value.
    //  If there is no key at that index, add it into the table.
    //  Resize when the size is > the loadFactor * capacity.
    //  Remember that multiple keys can exist at the same index.
    public void put(K key, V value) {

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

    // TODO:
    //  Set the capacity to the nextPrime of the capacity doubled.
    //  Calculate the previousPrime and set up the new table with the old tables'
    //  contents now hashed to the new.
    private void resize() {
        //find the next prime number twice that of capacity...
    }


    // TODO:
    //  Retrieves the value of a key in the table.
    //  Return null if not there.
    public V get(K key) {
        return null;
    }

    // TODO: Searches the table to see if the key exists or not.
    public boolean containsKey(K key) {
        return false;
    }

    // TODO:
    //  Remove the entry under that key. Return true.
    //  If there is no key, return false.
    public boolean remove(K key) {
        return false;
    }

    public void clear() {
        for (LinkedList<Entry<K, V>> list : table) {
            list.clear();
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // TODO: Calculate the absolute hash of the key. Do not overthink this.
    private int hash(K key) {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int index = 0;
        for (LinkedList<Entry<K, V>> list : table) {
            if(list.size() > 0 ) {
                sb.append(index + ": ");
                for (Entry<K, V> entry : list) {
                    sb.append(entry.getKey() + "=" + entry.getValue() + ",");
                }
                index++;
                sb.append(";");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }


    public static void main(String []args ) {
        HashTableWithChaining<String, Integer> hashTable = new HashTableWithChaining<>();

        hashTable.put("Hi", 2);
        hashTable.put("Ih", 2);
        hashTable.put("Hit", 2);
        hashTable.put("Him", 20);
        hashTable.put("His", 1);
        hashTable.put("Hiasdasd", 2);
        hashTable.put("Hiasdasds", 1);
        hashTable.put("Hiasdasadsd", 2);
        hashTable.put("H12is", 1);
        hashTable.put("H123iasdasd", 2);
        hashTable.put("Hita123s1d3asads", 2);
        System.out.println(hashTable);

    }
}

