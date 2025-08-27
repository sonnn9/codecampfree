import java.util.*;
public class ImmutabilityDemo {
    public static void main(String[] args) {
        List src = List.of("A","B","C");
        List copy = new ArrayList<>(src);
        copy.add("D");
        System.out.println(src);
        System.out.println(copy);
    }
}
