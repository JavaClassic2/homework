package util;

public interface List<E> extends Iterable<E>{
    void add(E item);
    E get(int index);
    void remove(int index);
    void clear();
    int size();
    boolean isEmpty();
}