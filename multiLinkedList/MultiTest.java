package multiLinkedList;

public class MultiTest {
    public static void main(String[] args) {
        MultiLinkedList<Integer> list = new MultiLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        printList(list);

        list.remove(1);
        printList(list);

        list.clear();
        System.out.println("is Empty? : " + list.isEmpty());
        
        list.add(7);
        printList(list);
        System.out.println("size: " + list.size());
    }

    private static void printList(List list) {
        for (int i=0; i<list.size()-1; i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.println(list.get(list.size()));
    }
}
