public class InstanceofDemo {
    public static void main(String[] args) {
        Object obj = "Hello";
        // instanceof + pattern matching
        if (obj instanceof String s && !s.isEmpty()) {
            System.out.println(s.toUpperCase());
        }
    }
}
