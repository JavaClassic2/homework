package list;
import java.util.NoSuchElementException;

public class SimpleDoubleLinkedList implements SimpleList{
    class Node {
        Node prev;
        Node next;
        int data;
    
        public Node(Node prev, int data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    
        public Node(int data) {
            this.data = data;
        }
    }

    class ListIterator implements SimpleListIterator {
        private Node node;

        public ListIterator() {
            node = head;
        }

        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public int next() {
            int data = node.data;
            node = node.next;

            return data;
        }

        @Override
        public boolean hasPrevious() {
            return node.prev != null;
        }

        @Override
        public int previous() {
            int data = node.data;
            node = node.prev;

            return data;
        }

        @Override
        public void remove() {
            Node prev = node.prev;
            Node next = node.next;

            node = next;
            node.prev = prev;
        }

    }

    private Node head;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void add(int data) {
        if (isEmpty()) {
            head = new Node(data);
            return;
        }

        Node node = head;
        while (node.next != null) {
            node = node.next;
        }

        node.next = new Node(node, data, null);
    }

    @Override
    public int remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("삭제할 요소가 없습니다.");
        }

        int data = head.data;
        head = head.next;
        head.prev = null;

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

        // if 1번째 삭제
        // node = 0번째 노드
        for (int i=0; i<index-1; i++) {
            node = node.next;
        }
        
        // data = 1번째 노드의 데이터
        // 삭제할 데이터 : 검색한 노드의 다음 데이터
        int data = node.next.data;

        // 0번째의 next = 1번째의 다음 노드
        // 검색한 노드의 다음 노드를 삭제할 노드의 다음 노드로 지정
        // node.next.next가 null일 수 있지만 null이어도 상관없음
        node.next = node.next.next;

        // 0 -> 2
        // 2번째 노드의 이전 노드를 0번째 노드로 지정
        // 
        // 위에서 node.next.next가 null일수도 있으므로
        // 다음 노드의 prev를 설정하기 전에
        // node.next가 null이 아닌경우를 확인해야함
        if (node.next != null){
            node.next.prev = node;
        }

        return data;
    }

    @Override
    public boolean remove(int data) {
        if (isEmpty()) {
            return false;
        }

        Node node = head;

        while (node != null) {
            if (node.data == data) {
                // 0번재 노드의 다음 = 2번째 노드
                node.prev.next = node.next;
                // 2번째 노드의 이전 노드 = 0번째 노드
                node.next.prev = node.prev;
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

class DoubleLinkedListTest {
    public static void main(String[] args) {
        System.out.println("========== toString test ==========");
        SimpleDoubleLinkedList list = new SimpleDoubleLinkedList();
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