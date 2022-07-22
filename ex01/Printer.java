package ex01;

public class Printer {
    private String blockTreadName;

    Printer(String name) {
        blockTreadName = name;
    }

    public synchronized void  printData() {
        String name = Thread.currentThread().getName();

        while (name.equals(blockTreadName)) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
                System.exit(1);
            }
        }
        System.out.println(name);
        blockTreadName = name;
        notifyAll();
    }
}
