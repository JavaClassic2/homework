package util;

import java.util.Iterator;

public class LinkedListIterator<E> implements Iterator<E>{
    private Node<E> head;

    public LinkedListIterator(Node<E> head) {
        this.head = head;
    }


    @Override
    public boolean hasNext() {
        return head.getNext() != null;
    }

    @Override
    public E next() {
        E element = head.getData();
        head = head.next;

        return element;
    }
    
}
