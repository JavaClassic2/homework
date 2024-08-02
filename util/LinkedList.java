package util;

import java.util.Iterator;

public class LinkedList<E> implements List<E>{
    private Node<E> head;

    @Override
    public void add(E element) {
        if (head == null) {
            head = new Node<E>(element);
        }
        else {
            Node<E> lastNode = findNode(size()-1);
            lastNode.setNext(new Node<E>(element));
        }
    }

    private Node<E> findNode(int index) {
        if (index < 0 || index > size()) {
            throw new IllegalArgumentException("index out of bound");
        }

        Node<E> findNode = head;

        for (int i=0; i<index; i++) {
            findNode = findNode.getNext();
        }

        return findNode;
    }

    @Override
    public E get(int index) {
        return findNode(index).getData();
    }

    @Override
    public void remove(int index) {
        Node<E> preNode = findNode(index-1);
        Node<E> nextNode = findNode(index+1);
        preNode.setNext(nextNode);
    }

    @Override
    public void clear() {
        this.head = null;
    }

    @Override
    public int size() {
        Node<E> node = head;
        int size = 0;

        while (node.getNext() != null) {
            node = node.getNext();
            size++;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(head);
    }
    
}

class ListTest2{
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
    }
}
