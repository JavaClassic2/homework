
public class DepartmentAlreadyExistException extends RuntimeException{
    public DepartmentAlreadyExistException(int no) {
        System.out.println("Department Already Exist! no: " + no);
    }
}
