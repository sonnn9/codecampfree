public class CompareDemo {
    public static void main(String[] args) {
        String s1 = "Hi";
        String s2 = new String("Hi");
        System.out.println(s1 == s2); // false
        System.out.println(s1.equals(s2)); // true
    }
}
