package util;

public class Node<D> {
    Node<D> next;
    D data;

    public Node(D data) {
        this.data = data;
    }

    public D getData() {
        return data;
    }

    public Node<D> getNext() {
        return next;
    }

    public void setNext(Node<D> next) {
        this.next = next;
    }
}
