public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        Factory factory = new Factory();
        factory.registerType(new NoGenerator());
        factory.registerType(new Student(0, "taco"));

        DataBeam generator = factory.getClass("NoGenerator");
        System.out.println(generator);
        DataBeam generator2 = factory.getClass("NoGenerator");
        System.out.println(generator2);
        
        DataBeam s1 = factory.getClass("Student");
        System.out.println(s1);
        System.out.println(((Student)s1).getName());
        DataBeam s2 = factory.getClass("Student");
        System.out.println(s2);
        System.out.println(((Student)s2).getName());
    }
}
