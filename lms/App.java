import java.util.List;

public class App {
    public static void main(String[] args) {
        String path = "lms/database/student.csv";
        StudentManager manager = new StudentManager(path);
        manager.clear();
        
        manager.addStudent(new Student(0, "taco", 20));
        manager.addStudent(new Student(1, "yakki", 20));

        Department it = new Department(0, "IT");
        manager.addStudent(new Student(2, "mallang", 20, it));
        
        List<Student> studentList = manager.getStudentList();
        for (Student s : studentList) {
            System.out.println(s);
        }
    }
}
