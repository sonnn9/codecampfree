import java.io.*;

public class ExceptionsDemo {
    public static void main(String[] args) {
        try {
            System.out.println(readFirstLine("Readme.txt"));
        } catch (IOException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }

    static String readFirstLine(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        }
    }

}
