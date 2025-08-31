public class GuardClausesDemo {
    public static void main(String[] args) {
        System.out.println(getting(null));
        System.out.println(getting("Son"));
    }
    static String getting(String name) {
        if (name == null || name.isBlank()) return "Hello, guest";
        return "Hello, " + name + "!";
    }
}
