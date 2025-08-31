public class StringDemo {
    public static void main(String[] args) {
        String a = "hi";
        String b = "hi";
        String c = new String("hi");
        System.out.println(a == b); // true
        System.out.println(a == c); // false
        System.out.println(a.equals(c)); // true
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) sb.append(i);
        System.out.println(sb.toString());
    }
}
