import java.util.*;
public class CollectionsStreamsDemo {
    public static void main(String[] args) {
        List<String> names = List.of(" ana", "Bob", "   cat");
        List<String> normalized = names.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase())
                .sorted()
                .toList();
        System.out.println(normalized);
    }
}
