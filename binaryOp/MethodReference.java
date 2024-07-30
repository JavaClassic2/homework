interface BinaryOp {
    int apply(int i, int j);
}

class Calc {
    public static int add(int x, int y) {
        return x + y;
    }

    public static int multy(int x, int y) {
        return x * y;
    }
}

// 메소드 참조
public class MethodReference {
    public static int calc(BinaryOp binder,int init, int start, int end, int step) {
        int result = init;

        for (int i=start; i<end; i+=step) {
            binder.apply(result, i);
        }

        return result;
    }

    public int sigma(int start, int end, int step) {
        return MethodReference.calc(Calc::add, 0, start, end, step);
    }
    
    public int pi(int start, int end, int step) {
        return MethodReference.calc(Calc::multy, 1, start, end, step);
    }
}