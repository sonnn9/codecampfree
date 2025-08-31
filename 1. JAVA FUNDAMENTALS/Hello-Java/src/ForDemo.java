public class ForDemo {
    public static void main(String[] args) {
        for (int x = 0; x < 10; x++) {
            System.out.print(x + " ");
        }

        System.out.println();

        String[] names = {"An", "Bob", "John"};
        for (String name : names) {
            System.out.print(name + " ");
        }
    }
}
