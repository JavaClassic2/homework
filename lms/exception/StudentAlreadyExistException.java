public class StudentAlreadyExistException extends RuntimeException {
    public StudentAlreadyExistException(int no) {
        System.out.println("no : "+ no +" is Already Exist!");
    }
}
