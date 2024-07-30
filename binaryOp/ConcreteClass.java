interface BinaryOp {
    int apply(int i, int j);
}

// concrete class 구현
public class ConcreteClass {
    public static int calc(BinaryOp binder,int init, int start, int end, int step) {
        int result = init;

        for (int i=start; i<end; i+=step) {
            binder.apply(result, i);
        }

        return result;
    }

    public int sigma(int start, int end, int step) {
        return ConcreteClass.calc(new Adder(), 0, start, end, step);
    }
    
    public int pi(int start, int end, int step) {
        return ConcreteClass.calc(new Multiply(), 1, start, end, step);
    }
}

class Adder implements BinaryOp {

    @Override
    public int apply(int i, int j) {
        return i + j;
    }

}

class Multiply implements BinaryOp {

    @Override
    public int apply(int i, int j) {
        return i * j;
    }

}