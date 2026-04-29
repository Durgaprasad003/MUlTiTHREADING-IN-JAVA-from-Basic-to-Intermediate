import java.util.LinkedList;
import java.util.Queue;

import ticket_booking_system.main;

class Buffer {
    int data;
    boolean available = false;

    synchronized void produce(int value) throws InterruptedException {

        while(available) {
            wait();
        }

        data = value;
        available = true;

        System.out.println("Produced: " + value);

        notify();
    }

    synchronized int consume() throws InterruptedException {

        while(!available) {
            wait();
        }

        int value = data;
        available = false;

        System.out.println("Consumed: " + value);

        notify();

        return value;
    }
}



class Producer extends Thread {
    Buffer buffer;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for(int i=1; i<=5; i++) {
                buffer.produce(i);
                Thread.sleep(1000);
            }
        } catch(Exception e) {}
    }
}


class Consumer extends Thread {
    Buffer buffer;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for(int i=1; i<=5; i++) {
                buffer.consume();
                Thread.sleep(1500);
            }
        } catch(Exception e) {}
    }
}
public class producerconsumer {

    public static void main(String[] args) {
         Buffer b = new Buffer();

        new Producer(b).start();
        new Consumer(b).start();

        // System.out.println("end");
    }
    
}


class Buffers {

    Queue<Integer> queue = new LinkedList<>();
    int capacity = 5;

    synchronized void produce(int value) throws Exception {

        while(queue.size() == capacity) {
            wait();
        }

        queue.add(value);

        System.out.println("Produced: " + value);

        notifyAll();
    }

    synchronized void consume() throws Exception {

        while(queue.isEmpty()) {
            wait();
        }

        int item = queue.remove();

        System.out.println("Consumed: " + item);

        notifyAll();
    }
}

// 10. Multiple Producers and Consumers
// public class Main {
//     public static void main(String[] args) {

//         Buffer b = new Buffer();

//         for(int i=1; i<=2; i++)
//             new Producer(b).start();

//         for(int i=1; i<=2; i++)
//             new Consumer(b).start();
//     }
// }



