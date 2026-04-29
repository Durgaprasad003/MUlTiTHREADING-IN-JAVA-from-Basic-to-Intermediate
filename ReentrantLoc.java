import java.util.concurrent.locks.ReentrantLock;

class Counter {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();

        try {
            count++;
            System.out.println(count);
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}


// ReentrantLock gives:

// manual lock/unlock
// tryLock()
// timed lock waiting
// fairness policy

public class ReentrantLoc {
    public static void main(String[] args) throws Exception {

        Counter c = new Counter();

        Thread t1 = new Thread(() -> {
            for(int i=0; i<10000; i++) c.increment();
        });

        Thread t2 = new Thread(() -> {
            for(int i=0; i<10000; i++) c.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(c.getCount());
    }
}