public class BinarySearch<C extends Comparable<C>> {
    C[] list;

    public BinarySearch(C[] list) {
        this.list = list;
    }

    public C search(C value) {
        int low = 0;
        int high = list.length - 1;

        while(low <= high) {
            int middle = (low + high) / 2;
            
            if (value.compareTo(list[middle]) < 0) {
                high = middle;
            } else if (value.compareTo(list[middle]) > 0) {
                low = middle;
            } else {
                return list[middle];
            }
        }
        return null;
    }
}

class Test {
    public static void main(String[] args) {
        Integer[] list = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BinarySearch<Integer> search = new BinarySearch<>(list);
        int v = search.search(3);
        System.out.println(v);
    }
}