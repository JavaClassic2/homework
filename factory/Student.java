@CreationType(type = "ProtoType")
public class Student extends DataBeam{
    private int no;
    private String name;

    public Student(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    @Override
    public Student clone() {
        return new Student(getNo(), getName());
    }
}
