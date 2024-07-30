interface BinaryOp {
    int apply(int i, int j);
}

// Lambda Expression
public class LambdaExpression {
    public static int calc(BinaryOp binder,int init, int start, int end, int step) {
        int result = init;

        for (int i=start; i<end; i+=step) {
            binder.apply(result, i);
        }

        return result;
    }

    public int sigma(int start, int end, int step) {
        return LambdaExpression.calc((x, y) -> x + y, 0, start, end, step);
    }
    
    public int pi(int start, int end, int step) {
        return LambdaExpression.calc((x, y) -> x * y, 1, start, end, step);
    }
}
