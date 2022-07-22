package ex03;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final String fileName = "files_urls.txt";
    public static int AMOUNT_OF_THREADS;

    public static void main(String[] args) {
        findAmountOfThreads(args);

        Path path = Paths.get(fileName);
        Path absPath = path.toAbsolutePath();

        Links links = new Links(absPath);
        ArrayList<String> urls = links.getUrls();

        ExecutorService executor = Executors.newFixedThreadPool(AMOUNT_OF_THREADS);

        for (int i = 0; i < urls.size(); ++i) {
            executor.submit(new Downloader(urls.get(i), i + 1));
        }

        executor.shutdown();
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
