package list;
import java.util.NoSuchElementException;
import java.util.Stack;

public class SimpleSingleLinkedList implements SimpleList{
    class Node {
        Node next;
        int data;
    
        public Node(int data, Node next) {
            this.next = next;
            this.data = data;
        }
    
        public Node(int data) {
            this.data = data;
        }
    }

    class ListIterator implements SimpleListIterator {
        private Stack<Node> prevNode;
        private Node current;

        public ListIterator() {
            prevNode = new Stack<>();
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public int next() {
            prevNode.push(current);
            current = current.next;

            return prevNode.peek().data;
        }

        @Override
        public boolean hasPrevious() {
            return prevNode.size() != 0;
        }

        @Override
        public int previous() {
            int data = current.data;
            current = prevNode.pop();

            return data;
        }

        @Override
        public void remove() {
            if (hasPrevious()) {
                Node prev = prevNode.pop();
                prev.next = current.next;
                current = prev;
                return;
            } else {
                current = current.next;
            }
        }

    }

    private Node head;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void add(int data) {
        if (head == null) {
            head = new Node(data);
            return;
        }

        Node node = head;
        while (node.next != null) {
            node = node.next;
        }

        node.next = new Node(data);
    }

    @Override
    public int remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("삭제할 요소가 없습니다.");
        }

        int data = head.data;
        head = head.next;

        return data;
    }

    @Override
    public int removeAt(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("입력한 index가 음수입니다.");
        }
        
        if (index > size()) {
            throw new NoSuchElementException("해당 위치에 삭제할 데이터가 없습니다.");
        }

        Node node = head;

        for (int i=0; i<index-1; i++) {
            node = node.next;
        }

        int data = node.next.data;
        node.next = node.next.next;

        return data;
    }

    @Override
    public boolean remove(int data) {
        if (isEmpty()) {
            return false;
        }

        if (head.data == data) {
            head = null;
        }

        Node node = head;

        while (node.next != null) {
            if (node.next.data == data) {
                node.next = node.next.next;
                return true;
            }
            node = node.next;
        }

        return false;
    }

    @Override
    public boolean contains(int data) {
        Node node = head;

        while (node != null) {
            if (node.data == data) {
                return true;
            }
            node = node.next;
        }

        return false;
    }

    @Override
    public int size() {
        int size = 0;
        Node node = head;

        while (node != null) {
            size++;
            node = node.next;
        }

        return size;
    }

    @Override
    public SimpleListIterator iterator() {
        return new ListIterator();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        
        Node node = head;

        if (node != null) {
            builder.append(node.data);
            node = node.next;
        }

        while (node != null) {
            builder.append(", ")
                    .append(node.data);
            node = node.next;
        }

        builder.append("]");

        return builder.toString();
    }
}

class SingleLinkedListTest {
    public static void main(String[] args) {
        System.out.println("========== toString test ==========");
        SimpleSingleLinkedList list = new SimpleSingleLinkedList();
        System.out.printf("list : %s%n", list);
        System.out.printf("list.size() : %d%n", list.size());
        System.out.printf("list.isEmpty() : %b%n", list.isEmpty());
        
        System.out.println("========== add test ==========");
        for (int i=0; i<10; i++) {
            list.add(i);
            System.out.printf("list.add(%d) : %s%n", i, list);
        }
        
        System.out.printf("list.size() : %d%n", list.size());
        System.out.printf("list.isEmpty() : %b%n", list.isEmpty());
        
        System.out.println("========== remove test ==========");
        int data = list.remove();
        System.out.printf("list.remove() : %s%n", list);
        System.out.printf("removed data : %d%n", data);
        
        System.out.println("========== removeAt test ==========");
        data = list.removeAt(3);
        System.out.printf("list.removeAt(%d) : %s%n", 3, list);
        System.out.printf("removed data : %d%n", data);
        
        System.out.println("========== remove data test ==========");
        boolean remove = list.remove(7);
        System.out.printf("list.remove(%d) : %s%n", 7, list);
        System.out.printf("removed : %b%n", remove);

        System.out.println("========== contains test ==========");
        System.out.printf("list.contains(%d) : %b%n", 7, list.contains(7));
        System.out.printf("list.contains(%d) : %b%n", 8, list.contains(8));
    
        System.out.println("========== size & isEmpty test ==========");
        System.out.printf("list.size() : %d%n", list.size());
        System.out.printf("list.isEmpty() : %b%n", list.isEmpty());
    }
}