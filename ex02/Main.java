package ex02;

import java.util.Random;

public class Main {

    private static int ARRAY_SIZE;
    private static int THREAD_COUNT;

    private static final int sizeOFArrEl = 1000;

    public static void main(String[] args) {
        checkIsValidArgument(args);

        int[] arr = new int[ARRAY_SIZE];

        generateArr(arr);
        printArr(arr);
        int sum = sum(arr);
        System.out.println("Sum: " + sum);

        Counter counter = new Counter(ARRAY_SIZE, THREAD_COUNT, arr);

        Runnable runnable = () -> {
            counter.count();
        };

        Thread[] tr = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; ++i) {
            tr[i] = new Thread(runnable, String.valueOf(i) );
            tr[i].start();
        }
        for (int i = 0; i < THREAD_COUNT; ++i) {
            try {
                tr[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Sum by threads: " + counter.getSum());
    }

    private static int sum(int[] arr) {
        int sum = 0;

        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void generateArr(int[] arr) {
        Random r = new Random();

        for (int i = 0; i < arr.length; ++i) {
            arr[i] = r.nextInt(2 * sizeOFArrEl) - sizeOFArrEl;
        }
    }

    private static void checkIsValidArgument(String[] args) {
        if (args.length != 2) {
            System.out.println("Not valid arguments");
            System.exit(1);
        }
        try {
            ARRAY_SIZE = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
            THREAD_COUNT = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1));
        } catch (Exception e) {
            System.out.println("Not valid arguments");
            System.exit(1);
        }

    }
}
