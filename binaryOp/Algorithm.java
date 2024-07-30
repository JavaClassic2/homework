interface BinaryOp {
    int calc(int start, int end, int step);
}

// BinaryOp를 구현하는 class 사용
public class Algorithm {

    public int sigma(BinaryOp binder, int start, int end, int step) {
        return binder.calc(start, end, step);
    }
    
    public int pi(BinaryOp binder, int start, int end, int step) {
        return binder.calc(start, end, step);
    }
}

class Adder implements BinaryOp {

    @Override
    public int calc(int start, int end, int step) {
        int result = 0;

        for (int i=start; i<end; i+=step) {
            result += i;
        }

        return result;
    }

}

class Multiply implements BinaryOp {

    @Override
    public int calc(int start, int end, int step) {
        int result = 1;

        for (int i=start; i<end; i+=step) {
            result *= i;
        }

        return result;
    }
}
