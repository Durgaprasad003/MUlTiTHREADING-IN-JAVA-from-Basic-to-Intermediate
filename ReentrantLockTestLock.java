import java.util.concurrent.locks.ReentrantLock;

class Counter {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment(String threadName) {
        lock.lock(); // acquire lock
        try {
            System.out.println(threadName + " got lock");
            for (int i = 1; i <= 3; i++) {
                count++;
                System.out.println(threadName + " count = " + count);
                try { Thread.sleep(500); } catch (Exception e) {}
            }
        } finally {
            lock.unlock(); // release lock (VERY IMPORTANT)
        }
    }
}

public class ReentrantLockTestLock {
    public static void main(String[] args) {

        Counter c = new Counter();

        Thread t1 = new Thread(() -> c.increment("T1"));
        Thread t2 = new Thread(() -> c.increment("T2"));

        t1.start();
        t2.start();
    }
}