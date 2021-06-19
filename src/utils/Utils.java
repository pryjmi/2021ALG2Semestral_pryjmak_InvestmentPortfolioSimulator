package utils;

import java.util.concurrent.TimeUnit;

public class Utils {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void Wait() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void percentNeg(double percent) {
        System.out.println("Hodnota aktiva se změnila o " + ANSI_RED + percent + " %" + ANSI_RESET + "\n\n");
    }

    public static void percentPos(double percent) {
        System.out.println("Hodnota aktiva se změnila o " + ANSI_GREEN + percent + " %" + ANSI_RESET + "\n\n");
    }
}
