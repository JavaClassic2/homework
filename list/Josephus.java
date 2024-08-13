import list.SimpleCircularLinkedList;
import list.SimpleListIterator;

public class Josephus {
    static SimpleCircularLinkedList list;    
    SimpleListIterator iterator;

    public Josephus(int n, int k) {
        list = new SimpleCircularLinkedList();
        
        for (int i=1; i<=n; i++) {
            list.add(i);
        }
    }
    
    int getLast() {
        iterator = list.iterator();
        
        while (list.size() > 1) {
            removeNext(iterator, 2);
        }
        
        return iterator.next();
    }
    
    int removeNext(SimpleListIterator iterator, int k) {
        for (int i=0; i<k-1; i++) {
            if (iterator.hasNext()) {
                iterator.next();
            }
        }
        
        int data = iterator.next();
        iterator.previous();
        iterator.remove();
        iterator.next();
        
        return data;
    }
    
    void printProcess() {
        iterator = list.iterator();

        System.out.println("first list : " + list);
        
        while (list.size() > 1) {
            int removed = removeNext(iterator, 2);
            System.out.printf("removed : %d : %s%n", removed, list);
        }
    }

    public static void main(String[] args) {
        Josephus josephus = new Josephus(42, 2);
        josephus.printProcess();
        System.out.println("last: " + josephus.getLast());
    }

}
