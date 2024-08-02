import java.util.Iterator;
import java.util.LinkedList;

public class Stack<E>{
    LinkedList<E> list;

    public Stack() {
        list = new LinkedList<>();
    }
    
    public Iterator<E> iterator() {
        return list.iterator();
    }

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public E pop() {
        return list.pop();
    }

    public E peek() {
        return list.peek();
    }

    public void push(E e) {
        list.push(e);
    }

    public E pull() {
        return list.poll();
    }
}