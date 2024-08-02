import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentManager {
    private File file;

    public DepartmentManager(String path) {
        this.file = new File(path);
    }

    public List<Department> getDepartmentList() {
        List<Department> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] lines = line.split(", ");

                int no = Integer.parseInt(lines[0]);
                String name = lines[1];
                
                list.add(new Department(no, name));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean isExist(Department department) {
        List<Department> list = getDepartmentList();
        for(Department d : list) {
            if (department.getNo() == d.getNo()) {
                return true;
            }
        }
        return false;
    }

    public void adddepartment(Department department) {
        if (isExist(department)) {
            throw new DepartmentAlreadyExistException(department.getNo());
        }
        writeFile(department, true);
    }

    public void removeDepartment(Department department) {
        List<Department> list = getDepartmentList();

        for (Department d : list) {
            if (d.equals(department)) {
                continue;
            }

            writeFile(department, false);
        }
    }

    public void clear() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFile(Department d, boolean append) {
        String line = String.format("%d, %s", d.getNo(), d.getName());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, append))) {
            bw.write(line);
            bw.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
