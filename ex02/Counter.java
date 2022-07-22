package ex02;

public class Counter {
    private final int arraySize;

    private int sum;
    private int lastId;
    private int nowId;

    private final int sizeBlock;

    private final int[] arr;

    Counter(int arraySize, int threadCount, int[] arr) {
        this.arraySize = arraySize;
        sizeBlock = (int) Math.ceil(arraySize / threadCount);
        lastId = threadCount - 1;
        nowId = 0;
        this.arr = arr;
        sum = 0;
    }

    public synchronized void count() {
        int id = Integer.parseInt(Thread.currentThread().getName());

        while (id != nowId) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
                System.exit(1);
            }
        }

        int startIdx = id * sizeBlock;
        int endIdx;
        if (id != lastId) {
            endIdx = startIdx + sizeBlock;
        } else {
            endIdx = arraySize;
        }

        int res = 0;

        for (int i = startIdx; i < endIdx; ++i) {
            res += arr[i];
        }

        System.out.println("Thread " + (id + 1) + ": from " + startIdx
                + " to " + endIdx + " sum is " + res);
        sum += res;
        nowId++;

        notifyAll();
    }

    public int getSum() {
        return sum;
    }
}
