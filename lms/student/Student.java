
public class Student {
    private int no;
    private String name;
    private int age;
    private Department department;

    public Student(int no, String name, int age, Department department) {
        this.no = no;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public Student(int no, String name, int age) {
        this(no, name, age, null);
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("%d.%s, age: %d, department: %s", getNo(), getName(), getAge(), getDepartment());
    }
}
