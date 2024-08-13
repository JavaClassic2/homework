package list;
import java.util.NoSuchElementException;

/**
 * todo 코드 최적화
 * todo iteraotr 부분 확인하기
 * 아까 깃허브 연동하다가 예전 버전 파일 덮어쓴거 있는지도 확인해야함
 */
public class SimpleCircularLinkedList implements SimpleList{
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
            this.prev = this;
            this.data = data;
            this.next = this;
        }
    }

    class ListIterator implements SimpleListIterator {
        private Node node;

        public ListIterator() {
            node = head;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public int next() {
            int data = node.data;
            node = node.next;

            return data;
        }

        @Override
        public boolean hasPrevious() {
            return tail != null;
        }

        @Override
        public int previous() {
            int data = node.data;
            node = node.prev;

            return data;
        }

        @Override
        public void remove() {
            if (node == head) {
                head = head.next;
                head.prev = tail;
                tail.next = head;
                
                return;
            }
 
            if (node == tail) {
                head.prev = tail.prev;
                tail.prev.next = null;
                tail = tail.prev;
                tail.next = head;

                return;
            }
        
            Node prev = node.prev;
            Node next = node.next;

            prev.next = next;
            next.prev = prev;
        }

    }

    private Node head;
    private Node tail;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void add(int data) {
        // head가 null이면?
        if (isEmpty()) {
            // head는 data가 data이고, prev와 next가 null인 노드
            head = new Node(data);
            // tail은 head와 동일 객체
            tail = head;
            return;
        }

        // head가 null이 아님 -> 리스트에 적어도 1개는 있다.
        // tail의 다음 노드에 prev = tail, data = data, next = head인 노드 삽입
        // tail -> 새 노드 -> head
        tail.next = new Node(tail, data, head);
        tail = tail.next;
        // head의 prev가 tail을 참조하도록 설정
        head.prev = tail;
    }

    @Override
    public int remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("삭제할 요소가 없습니다.");
        }

        int data = head.data;

        if (head == tail) {
            head = null;
            tail = null;
            
            return data;
        }

        head = head.next;
        head.prev = tail;
        tail.next = head;

        return data;
    }

    @Override
    public int removeAt(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("입력한 index가 음수입니다.");
        }
        
        if (index >= size()) {
            throw new NoSuchElementException("해당 위치에 삭제할 데이터가 없습니다.");
        }

        Node node = head;

        for (int i=0; i<index-1; i++) {
            node = node.next;
        }
        
        int data = node.next.data;
        node.next = node.next.next;
        node.next.prev = node;

        return data;
    }

    @Override
    public boolean remove(int data) {
        if (isEmpty()) {
            return false;
        }

        Node node = head;

        while (node != tail) {
            if (node.data == data) {
                node.prev.next = node.next;
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

        while (node != tail) {
            if (node.data == data) {
                return true;
            }
            node = node.next;
        }

        return false;
    }

    @Override
    public int size() {
        Node node = head;
        int size = 0;

        if (node == null) {
            return size;
        }

        if (node == tail) {
            return ++size;
        }
        
        node = node.next;
        size++;
        
        
        while (node != tail) {
            size++;
            node = node.next;
        }

        if (node == tail) {
            size++;
        }

        return size;
    }

    @Override
    public SimpleListIterator iterator() {
        return new ListIterator();
    }
    
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        // 일단 크기는 1 이상이니까 첫번째 데이터 찍기
        Node node = head;
        StringBuilder builder = new StringBuilder();
        builder.append("[")
            .append(node.data);
        
        node = node.next;

        while (!node.equals(tail)) {
            builder.append(", ").append(node.data);
            node = node.next;
        }

        // 위에서 tail인 경우 data를 안찍어줬으니까 여기서 tail의 데이터를 추가해야함
        if (size() > 1){
            builder.append(", ").append(node.data);
        }
        
        builder.append("]");

        return builder.toString();
        
        // list.add(1)에서 217줄부터 overflow 발생
        // node와 this를 표시하는데 무한로딩이 걸리는것으로 보아
        // node.prev와 node.next가 무한으로 생성되어서 그러는것같기도 함
        // 그런데 이 문제면 list.add(0)부터 멈춰야하는것 아닌가?
        // 여기서 멈추는것 보면 list.add(1)까지는 잘 되는것같은데
        // 아니면 중간에 데이터를 삭제하는 부분이 잘못되어서 여기서 문제가 생길수도?

        // list.add(1)이 이상한거 맞았음
        // tail.next = New Node(tail, data, head)로 설정해야하는데
        // tail = New Node(tail, data, head)로 설정해서 문제가 발생한듯

        // Node node = head;
        // while (node != tail) {
        //     builder.append(", ")
        //             .append(node.data);
        //     node = node.next;
        // }

        // builder.append(tail.data)
        //         .append("]");

        // return builder.toString();
    }
}

class CircularLinkedListTest {
    public static void main(String[] args) {
        System.out.println("========== toString test ==========");
        SimpleCircularLinkedList list = new SimpleCircularLinkedList();
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