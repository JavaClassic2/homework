package multiLinkedList;

public interface List<T> {
    void add(T t);
    T get(int index);
    int size();
    T remove(int index);
    boolean isEmpty();
    void clear();
}
