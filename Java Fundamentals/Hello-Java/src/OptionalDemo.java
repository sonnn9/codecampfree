import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        System.out.println(getDomain(null));
        System.out.println(getDomain("hell@gmail.com"));
    }

    static String getDomain(String email) {
        return Optional.ofNullable(email)
                .filter(s -> s.contains("@"))
                .map(s -> s.substring(s.indexOf('@') + 1))
                .orElse("unknown");
    }
}
