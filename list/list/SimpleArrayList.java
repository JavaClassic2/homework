package list;
import java.util.NoSuchElementException;

public class SimpleArrayList implements SimpleList{
    class ListIterator implements SimpleListIterator {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public int next() {
            return datas[index++];
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public int previous() {
            return datas[--index];
        }

        @Override
        public void remove() {
            System.arraycopy(datas, index+1, datas, index, --size - index);
        }
    }

    static final int DEFAULT_CAPACITY = 10;
    private int[] datas;
    private int size = 0;

    public SimpleArrayList() {
        datas = new int[DEFAULT_CAPACITY];
    }

    public SimpleArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        
        datas = new int[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void add(int data) {
        if (size() == datas.length) {
            throw new IndexOutOfBoundsException("Out of capacity");
        }

        datas[size++] = data;
    }

    @Override
    public int remove() {
        if (size() == 0) {
            throw new NoSuchElementException("삭제할 요소가 존재하지 않습니다.");
        }

        int data = datas[0];
        System.arraycopy(datas, 1, datas, 0, --size);

        return data;
    }

    @Override
    public int removeAt(int index) {
        if (index <0) {
            throw new IllegalArgumentException("입력한 index 값이 음수입니다.");
        }

        if (index > size()) {
            throw new NoSuchElementException("해당 인덱스에 삭제할 데이터가 존재하지 않습니다.");
        }

        int data = datas[index];
        System.arraycopy(datas, index+1, datas, index, --size - index);

        return data;
    }

    @Override
    public boolean remove(int data) {
        if (!contains(data)){
            return false;
        }

        for (int i=0; i<size(); i++) {
            if (datas[i] == data) {
                removeAt(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean contains(int data) {
        for (int i=0; i<size(); i++) {
            if (datas[i] == data){
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
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

        if (size() > 0) {
            builder.append(datas[0]);
        }

        for (int i=1; i<size(); i++) {
            builder.append(", ")
                    .append(datas[i]);
        }

        builder.append("]");

        return builder.toString();
    }
}

class ArrayListTest {
    public static void main(String[] args) {
        System.out.println("========== toString test ==========");
        SimpleArrayList list = new SimpleArrayList();
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