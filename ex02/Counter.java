package ex02;

public class Counter {
    private final int arraySize;

    private int sum;

    private final int sizeBlock;
    private final int threadAmount;

    private final int[] arr;

    Counter(int arraySize, int threadCount, int[] arr) {
        this.arraySize = arraySize;
        sizeBlock = (int) Math.ceil(arraySize / threadCount);
        this.threadAmount = threadCount;
        this.arr = arr;
        sum = 0;
    }

    public void count() {
        int id = Integer.parseInt(Thread.currentThread().getName());

        int startIdx = id * sizeBlock;
        int endIdx;
        if (id != threadAmount - 1) {
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
        addSum(res);
    }

    private synchronized void addSum(int res) {
        sum += res;
    }

    public int getSum() {
        return sum;
    }
}
