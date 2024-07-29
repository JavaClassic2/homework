import java.io.*;
import java.util.*;

public class StudentManager {
    private File file;

    public StudentManager(String path) {
        this.file = new File(path);
    }

    public List<Student> getStudentList() {
        List<Student> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] lines = line.split(", ");
                int no = Integer.parseInt(lines[0]);
                String name = lines[1];
                int age = Integer.parseInt(lines[2]);

                if (lines.length == 5) {
                    int departmentNo = Integer.parseInt(lines[3]);
                    String departmentName = lines[4];
                    Department department = new Department(departmentNo, departmentName);
                    list.add(new Student(no, name, age, department));
                } else {
                    list.add(new Student(no, name, age));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean isExist(Student student) {
        List<Student> list = getStudentList();
        for(Student s : list) {
            if (student.getNo() == s.getNo()) {
                return true;
            }
        }
        return false;
    }

    public void addStudent(Student s) {
        if (isExist(s)) {
            throw new StudentAlreadyExistException(s.getNo());
        }
        writeFile(s, true);
    }

    public void removeStudent(Student s) {
        List<Student> list = getStudentList();

        for (Student student : list) {
            if (student.equals(s)) {
                continue;
            }

            writeFile(s, false);
        }
    }

    public void clear() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFile(Student s, boolean append) {
        String line = String.format("%d, %s, %d", s.getNo(), s.getName(), s.getAge());
        if (s.getDepartment() != null) {
            line += String.format(", %d, %s", s.getDepartment().getNo(), s.getDepartment().getName());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, append))) {
            bw.write(line);
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
