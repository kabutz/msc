import java.lang.management.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class OverheadExperiment {

    private static void runExecutionBoundedTest(int nthreads, List<Counter> counters,
                                                int nexecutions) throws InterruptedException {
        final List<CounterThread> threads = new ArrayList<CounterThread>();
        for (int i = 0; i < nthreads; i++) {
            CounterThread t;
            t = new CounterThread(counters.get(i % counters.size()), nexecutions);
            t.start();
            threads.add(t);
        }

        CounterThread.shoot(); // let all the threads go crazy at the same time

        for (int i = 0; i < nthreads; i++) {
            CounterThread t = threads.get(i);
            t.join(30000);
            if (t.isAlive()) {
                System.out.println("stuck thread name: " + t.toString());
                System.out.println("stuck thread state: " + t.getState());
                safe.ReentrantLock l = (safe.ReentrantLock) t.getLock();
                System.out.println("thread waiting lock: " + l);
                System.out.println("stuck thread increments: " + t.getExecutions());
                System.out.println("stuck thread counter value: " + t.getCounterCount());
                Thread other = l.getOwner();
                System.out.println("lock owner: " + other);
                if (other != null) {
                    System.out.println("owner name: " + other.toString());
                    System.out.println("state owner: " + other.getState());
                }
                // Keep program alive to dump thread stacks with: jstack -l $(pidof java)
                System.out.println(java.lang.management.ManagementFactory.getThreadMXBean().getPeakThreadCount());
                while (true) {
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < counters.size(); ++i) {
            sum += counters.get(i).getCount();
        }
        System.out.println(sum);
        System.out.println(java.lang.management.ManagementFactory.getThreadMXBean().getPeakThreadCount());
    }

    public static void main(String[] args) throws InterruptedException, NoSuchMethodException {
        int nthreads = Integer.parseInt(args[0]);
        int ncounters = Integer.parseInt(args[1]);
        String type = args[2];
        int nexecutions = Integer.parseInt(args[3]);

        List<Counter> counters = new ArrayList<Counter>();
        for (int i = 0; i < ncounters; i++) {
            Lock lock;
            if (type.equals("s"))
                lock = new safe.ReentrantLock();
            else if (type.equals("d"))
                lock = new safe.ReentrantLockDirect();
            else
                lock = new java.util.concurrent.locks.ReentrantLock();
            counters.add(new Counter(lock));
        }

        runExecutionBoundedTest(nthreads, counters, nexecutions);
    }
}
