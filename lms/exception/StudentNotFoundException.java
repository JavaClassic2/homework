public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(int no) {
        System.out.println("Student Not Found! no."+no);
    }
}
