import java.util.Arrays;

public class TypeSnack {
    public static void main(String[] args) {
        int i = 100;
        long L = i;
        int j = (int) 3.9;
        System.out.println(Integer.MAX_VALUE + 1);
        String a = "HI";
        String c = new String("HI");
        System.out.println(a.equals(c));
        StringBuilder sb = new StringBuilder();
        for( int n = 0; n < 3; n++) {
            sb.append(n);
        }
        System.out.println(sb);
        int[] arr = {5, 8, 3, 10, 5};
        java.util.Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
