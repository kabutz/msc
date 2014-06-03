import java.util.concurrent.locks.*;

public class Sample {
    static int result = 0;

    public static void main(String args[]) {
        final Lock lock = new safe.ReentrantLock();

        Thread t = new Thread(new Runnable() {
            public void run() {
                lock.lock();
                try {
                    lock.lock();
                    try {
                        ++result;
                    } finally {
                        lock.unlock();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
        );
        t.start();
        try {
            t.join();
        } catch (Exception e) {
        }
        System.out.println(result);
    }
}
