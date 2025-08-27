import java.math.BigDecimal;

public class MoneyDemo {
    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);  // 0.30000000000000004

        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
    }
}
