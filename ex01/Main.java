package ex01;

import java.util.Scanner;

public class Main {

    private static int AMOUNT = 0;

    public static void main(String[] args) {
        checkArgument(args);
        AMOUNT = returnNumFromStr(args[0]);

        Printer printer = new Printer("Hen");

        Runnable task  = () -> {
            for (int i = 0; i < AMOUNT; ++i) {
                    printer.printData();
            }
        };

        Thread egg = new Thread(task, "Egg");
        Thread hen = new Thread(task, "Hen");

        egg.start();
        hen.start();

        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private static int returnNumFromStr(String arg) {
        String num = arg.substring(arg.indexOf('=') + 1);

        if (new Scanner(num).hasNextInt()) {
            return Integer.parseInt(num);
        }

        System.out.println("Wrong argument");
        System.exit(1);
        return 0;
    }

    private static void checkArgument(String[] args) {
        if (args.length != 1) {
            System.out.println("Enter argument --count=NUM, " +
                    "NUM is amount of times thread will display answer");
            System.exit(1);
        }
        final String arg = "--count=";

        if (!args[0].startsWith(arg) || args[0].length() <= arg.length()) {
            System.out.println("Enter argument --count=num," +
                    "num is amount of times thread will display answer");
            System.exit(1);
        }
    }
}
