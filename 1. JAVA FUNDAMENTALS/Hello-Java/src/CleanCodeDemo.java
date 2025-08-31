public class CleanCodeDemo {
    public static final double DISCOUNT_RATE = 0.15;
    public static void main(String[] args) {

        // good magic numbers
        double price = 100;
        double finalPrice = applyDiscount(price);
        System.out.println(finalPrice);
    }

    static double applyDiscount(double price) {
        return price - price * DISCOUNT_RATE;
    }
}

