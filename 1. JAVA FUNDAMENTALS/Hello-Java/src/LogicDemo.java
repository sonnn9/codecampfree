public class LogicDemo {
    static class Foo { boolean ready() { return true; }}
    public static void main(String[] args) {
        Foo obj = null;
        boolean ok = (obj != null) && (obj.ready());
        System.out.println(ok);

    }
}
