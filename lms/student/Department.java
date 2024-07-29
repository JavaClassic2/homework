import java.util.ArrayList;
import java.util.Iterator;

public class Department implements Iterable<Student>{
    private int no;
    private String name;
    private ArrayList<Student> list;
    
    public Department(int no, String name) {
        this.list = new ArrayList<Student>();
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student s) {
        list.add(s);
    }

    public void removeStudent(int no) {
        for (Student s : list) {
            if (s.getNo() == no) {
                list.remove(s);
                return;
            }
        }
    }

    public Student getStudent(int no) {
        for (Student s : list) {
            if (s.getNo() == no) {
                return s;
            }
        }
        throw new StudentNotFoundException(no);
    }

    @Override
    public Iterator<Student> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return String.format("%d.%s", getNo(), getName());
    }
}