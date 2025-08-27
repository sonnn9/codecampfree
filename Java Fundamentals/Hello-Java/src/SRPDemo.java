import java.util.Scanner;

public class SRPDemo {
    public static void main(String[] args) {
        // good srp
        User user = readUser();
        System.out.println(isAdult(user.age) ? welcome(user.name) : "Denied");
    }
    static User readUser() {
        try (Scanner sc = new Scanner(System.in)){
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            return new User(username, age);
        }
    }
    static boolean isAdult(int age) { return age >= 18; }
    static String welcome(String name) { return "Welcome " + name; }
}

class User {
    final String name;
    final int age;
    User(String n, int a) {
        name = n;
        age = a;
    }
}
