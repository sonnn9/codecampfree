public class RefactorKataDemo {
    private static double DISCOUNT_RATE = 0.10;
    private static double TAX_RATE = 0.08;

    public static void main(String[] args) {
        double price = 120.0;
        int quantity = 3;
        boolean vip = isVip(quantity);
        double total = totalWithTax(applyDiscount(price * quantity, vip));
        printSummary(vip, total);
    }

    static boolean isVip(int quantity) {
        return quantity > 10;
    }
    static double applyDiscount(double subtotal, boolean vip) {
        return vip  ? subtotal * (1 - DISCOUNT_RATE) : subtotal;
    }
    static double totalWithTax(double subtotal) {
        return subtotal * (1 + TAX_RATE);
    }
    static void printSummary(boolean vip, double total) {
        System.out.println("VIP: " + vip);
        System.out.println("Total: " + total);
    }
}
