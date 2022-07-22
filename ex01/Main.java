package ex01;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static int AMOUNT = 0;

    public static void main(String[] args) {
        checkArgument(args);
        AMOUNT = returnNumFromStr(args[0]);

        Thread human = Thread.currentThread();
        human.setName("Human");

        Lock bankLock = new ReentrantLock();

        Runnable task  = () -> {
            for (int i = 0; i < AMOUNT; ++i) {
                printData(Thread.currentThread().getName(), bankLock);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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


        for (int i = 0; i < AMOUNT; ++i) {
            System.out.println(human.getName());
        }
    }

    public static  void printData(String name, Lock bankLock) {
        bankLock.lock();
        try {
            System.out.println(name);
        } finally {
            bankLock.unlock();
        }
    }

    public void run() {
        for (int i = 0; i < AMOUNT; ++i) {
            System.out.println(Thread.currentThread().getName());
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
