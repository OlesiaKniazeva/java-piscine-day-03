package ex01;

public class Printer {
    private String blockTreadName;

    Printer(String name) {
        blockTreadName = name;
    }

    public synchronized void  printData() {
        String name = Thread.currentThread().getName();

        if (name.equals(blockTreadName)) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name);
        blockTreadName = name;
        notifyAll();
    }
}
