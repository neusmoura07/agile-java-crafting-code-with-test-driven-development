import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    TestHandler testHandler;
    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        logger.warning("watch out");
        logger.warning("watch out again");
        logger.warning("final warning");
    }

    public static int calculateFactorialWhile(int n) {
        int result = 1;
        int count = n;

        if (n == 0) return 1;

        while (true){
            result *= count;
            count--;

            if (count <= 1)
                break;
        }
        return result;
    }

    public static int calculateFactorialFor(int n) {
        int result = 1;
        for(int count = n; count > 1; count--) {
            result *= count;
        }
        return result;
    }

    public static int calculateFactorialDo(int n) {
        int result = 1;
        int count = n;

        if (n == 0)
            return 1;
        do {
            result *= count;
            count --;
        } while (count > 1);
        return result;
    }
    public static String returnString(int n) {
        StringBuilder result = new StringBuilder();
        for (int count = 1; count <= n; count++){
            if (count > 1) {
                result.append(" ");
            }
            if (count % 5 == 0) {
                result.append(count).append("*");
                continue;
            }

            result.append(count);
        }
        return result.toString();
    }
    //Topico 3 Letra A
    public static Vector splitString(String input) {
        Vector tokens = new Vector<>();
        String[] parts = input.split(" ");
        for (int i = 0; i < parts.length; i++){
            tokens.add(parts[i]);
        }
        return tokens;
    }
    //Topico 3 letra B
    public static String recreateString(Vector tokens) {
        StringBuffer sb = new StringBuffer();
        Enumeration e = tokens.elements();
        while(e.hasMoreElements()) {
            sb.append(e.nextElement());
            if(e.hasMoreElements()) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public void blowsUp() {
        throw new RuntimeException("Somebody should catch this!");
    }

    public void rethrows() {
        try {
            blowsUp();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public void logExceptionReversed(Throwable t) {
        Logger logger = Logger.getLogger(Main.class.getName());

        StringBuilder sb = new StringBuilder();
        sb.append("Exceção capturada (stack trace em ordem reversa):\n");
        sb.append(t.toString()).append("\n"); // Adiciona a mensagem da exceção

        StackTraceElement[] trace = t.getStackTrace();
        for (int i = trace.length - 1; i >= 0; i--) {
            sb.append(trace[i].toString()).append(System.lineSeparator());
        }

        logger.log(Level.SEVERE, sb.toString());
    }

}