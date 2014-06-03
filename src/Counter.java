import java.util.concurrent.locks.*;

public class Counter {
    private long counter = 0;
    public final Lock lock;

    public Counter(Lock lock) {
        this.lock = lock;
    }

    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public long getCount() {
        return counter;
    }
}
