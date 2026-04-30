import java.util.*;
import java.util.concurrent.locks.*;

class SharedData {
    private Map<Integer, String> map = new HashMap<>();
    private ReadWriteLock rw = new ReentrantReadWriteLock();

    public void write(int key, String value) {
        rw.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " writing...");
            map.put(key, value);
            try { Thread.sleep(1000); } catch (Exception e) {}
        } finally {
            rw.writeLock().unlock();
        }
    }

    public void read(int key) {
        rw.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading: " + map.get(key));
            try { Thread.sleep(500); } catch (Exception e) {}
        } finally {
            rw.readLock().unlock();
        }
    }
}

public class ReadWritLock {
    public static void main(String[] args) {

        SharedData data = new SharedData();

        Thread w1 = new Thread(() -> data.write(1, "A"), "Writer-1");

        Thread r1 = new Thread(() -> data.read(1), "Reader-1");
        Thread r2 = new Thread(() -> data.read(1), "Reader-2");

        w1.start();
        r1.start();
        r2.start();
    }
}