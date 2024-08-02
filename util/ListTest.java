package util;

public class ListTest {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        // list.add(1);
        // list.add(3);

        // printList(list);
    }

    private static void printList(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
