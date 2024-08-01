@CreationType(type = ClassType.SingleTone)
public class NoGenerator implements DataBean{
    private int no;

    public int getNo() {
        return no++;
    }

    @Override
    public DataBean clone() {
        return new NoGenerator();
    }
}
