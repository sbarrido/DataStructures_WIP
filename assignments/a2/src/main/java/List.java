abstract class List<E>{

    abstract void add(E value);

    abstract void delete(int index) throws IndexOutOfBoundsException;

    abstract E get(int index) throws IndexOutOfBoundsException;

    abstract int search(E value);

    public abstract boolean equals(Object o);

    public abstract int size();
}
