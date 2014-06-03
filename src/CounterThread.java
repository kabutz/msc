import java.util.concurrent.locks.*;

public class CounterThread extends Thread {
    private final Counter counter;
    private long count = 0;
    private final long countMax;

    public CounterThread(Counter counter, long countMax) {
        this.counter = counter;
        this.countMax = countMax;
    }

    public Lock getLock() {
        return counter.lock;
    }

    public long getExecutions() {
        return count;
    }

    public long getCounterCount() {
        return counter.getCount();
    }

    @Override
    public void run() {
        try {
            while (count < countMax) {
                counter.increment();
                count++;
            }
        } catch(Throwable t) {
            System.err.println("Error in CounterThread: " + t);
        }
    }
}
