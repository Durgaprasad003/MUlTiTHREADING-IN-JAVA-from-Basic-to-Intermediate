class Counter {
   private int count = 0;

    void increment() {
        count++;
    }

     int getCount() {
        return count;
    }
}

public class mul2 {
    public static void main(String[] args) throws Exception {

        Counter obj = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized(obj) {
                    obj.increment();
                    System.out.println("Thread 1 : " + obj.getCount());
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized(obj) {
                    obj.increment();
                    System.out.println("Thread 2 : " + obj.getCount());
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}















// class Counter {
//     int count = 0;

//    synchronized void increment() {
//         count++;
//     }
// }

// public class mul2 {
//     public static void main(String[] args) throws Exception {

//         Counter c = new Counter();

//         Thread t1 = new Thread(() -> {
//             for(int i=0;i<1000;i++)
//                 c.increment();
//             System.out.println(c.count);
//         });

//         Thread t2 = new Thread(() -> {
//             for(int i=0;i<1000;i++)
//                 c.increment();
//             System.out.println(c.count);
//         });

//         t1.start();
//         t2.start();

//         t1.join();
//         t2.join();

//         System.out.println(c.count);
//     }
// }



