public class withoutvolatile {
    // static boolean flag = true;
    volatile static boolean flag = true;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("Thread started...");
            while (flag) {
                // Busy waiting
            }
            System.out.println("Thread stopped!");
        });

        Thread t2 = new Thread(() -> {
            try { Thread.sleep(2000); } catch (Exception e) {}
            flag = false;
            System.out.println("Flag changed to false");
        });

        t1.start();
        t2.start();
    }
} 
















// class Shared {
//     boolean flag = false;
// }

// public class Test {
//     public static void main(String[] args) {

//         Shared obj = new Shared();

//         Thread t1 = new Thread(() -> {
//             synchronized(obj) {
//                 try {
//                     System.out.println("T1 waiting...");
//                     obj.wait(); // releases obj lock
//                     System.out.println("T1 resumed");
//                 } catch (Exception e) {}
//             }
//         });

//         Thread t2 = new Thread(() -> {
//             synchronized(obj) {
//                 System.out.println("T2 notifying...");
//                 obj.notify(); // same object ✅
//             }
//         });

//         t1.start();

//         try { Thread.sleep(1000); } catch (Exception e) {}

//         t2.start();
//     }
// }