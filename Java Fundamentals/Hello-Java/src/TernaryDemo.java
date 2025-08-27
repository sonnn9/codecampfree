public class TernaryDemo {
    public static void main(String[] args) {
        int x = -5;
        int abs = (x >= 0) ? x : -x;
        System.out.println(abs); // 5
    }
}
