package multiLinkedList;

public class MultiLinkedList<T> implements List<T>{
    Node<T> head;

    @Override
    public void add(T t) {
        if (isEmpty()) {
            head = new Node<>(null, t);
            return;
        }
        
        Node<T> lastNode = findNode(size()-1);
        lastNode.setNext(new Node<T>(lastNode, null, t));
    }

    private Node<T> findNode(int index) {
        if (index<0 || index > size()) {
            throw new IllegalArgumentException("index out of bounds");
        }

        Node<T> findNode = head;
        for (int i=0; i<index; i++) {
            findNode = findNode.getNext();
        }
        
        return findNode;
    }

    @Override
    public T get(int index) {
        return findNode(index).getData();
    }

    @Override
    public int size() {
        Node<T> node = head;
        int size = 0;
        
        while (node.getNext() != null) {
            node = node.getNext();
            size++;
        }

        return size;
    }

    @Override
    public T remove(int index) {
        Node<T> prev = findNode(index-1);
        Node<T> next = findNode(index+1);

        T data = findNode(index).getData();

        prev.setNext(next);
        next.setPrev(prev);

        return data;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
    }
    
}
