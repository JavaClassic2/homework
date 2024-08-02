package multiLinkedList;

public class Node<T> {
    private Node<T> prev;
    private Node<T> next;
    private T data;

    public Node(Node<T> prev, T data) {
        this.prev = prev;
        this.data = data;
    }

    public Node(Node<T> prev, Node<T> next, T data) {
        this.prev = prev;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}
