@CreationType(type = "SingleTone")
public class NoGenerator extends DataBeam{
    private int no;

    public int getNo() {
        return no++;
    }
}
