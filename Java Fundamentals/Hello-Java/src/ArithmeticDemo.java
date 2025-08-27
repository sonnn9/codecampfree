public class ArithmeticDemo {
    public static void main(String[] args) {
        int a = 7, b = 3;
        System.out.println(a / b);  //2
        System.out.println(a / 3.0); // 2.3333

        //Unary
        int x = 1;
        System.out.println(x++); // print x -> x+1 // 1
        System.out.println(x);
        System.out.println(++x); // x=x+1 -> print x
    }
}
