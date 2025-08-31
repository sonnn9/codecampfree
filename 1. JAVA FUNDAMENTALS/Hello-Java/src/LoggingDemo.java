import java.util.logging.*;
public class LoggingDemo {
    private static final Logger LOG = Logger.getLogger(LoggingDemo.class.getName());
    public static void main(String[] args) {
        LOG.info("Starting LoggingDemo");
        try {
            runTask(5);
            runTask(-1);
        } catch (IllegalArgumentException e) {
            LOG.log(Level.WARNING, "Bad input {0}" ,e.getMessage());
        }
    }
    static void runTask(int n) {
        if(n < 0) throw new IllegalArgumentException("n must be >= 0");
        LOG.fine("Running task " + n); // enable level FINE
        System.out.println("Running task " + n);
    }
}
