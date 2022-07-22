package ex03;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static final String fileName = "files_urls.txt";
    public static int AMOUNT_OF_THREADS;

    public static void main(String[] args) {
        findAmountOfThreads(args);

        Path path = Paths.get(fileName);
        Path absPath = path.toAbsolutePath();

       Downloader download = new Downloader(absPath);
       download.printList();
       download.start();

        Runnable task = () -> {
            download.start();
        };

        Thread[] threads = new Thread[AMOUNT_OF_THREADS];
        for (int i = 0; i < AMOUNT_OF_THREADS; ++i) {
            threads[i] = new Thread(task, String.valueOf(i));
        }

        for (int i = 0; i < AMOUNT_OF_THREADS; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void findAmountOfThreads(String[] args) {
        if (args.length != 1) {
            System.out.println("Enter argument in format, --threadsCount=NUM," +
                    "where NUM is amount of threads to run");
            System.exit(1);
        }
        String arg = "--threadsCount=";
        if (!args[0].startsWith(arg) || arg.length() >= args[0].length()) {
            System.out.println("Not valid argument.\nEnter argument in format, --threadsCount=NUM," +
                    "\nwhere NUM is amount of threads to run");
            System.exit(1);
        }
        if (new Scanner(args[0].substring(args[0].indexOf('=') + 1)).hasNextInt()) {
            AMOUNT_OF_THREADS = new Integer(args[0].substring(args[0].indexOf('=') + 1));
            if (AMOUNT_OF_THREADS < 1) {
                System.out.println("Not valid argument.\nEnter argument in format, --threadsCount=NUM," +
                        "\nwhere NUM is amount of threads to run");
                System.exit(1);
            }
        } else {
            System.out.println("Not valid argument.\nEnter argument in format, --threadsCount=NUM," +
                    "\nwhere NUM is amount of threads to run");
            System.exit(1);
        }
    }

}
