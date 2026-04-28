// class MyThread extends Thread {
//     public void run() {
         
//         for(int i=1; i<=5; i++) {

//              try{
//             Thread.sleep(3000);
//             }
//             catch(InterruptedException e)
//             {
//                 System.out.println(e.getMessage());
//             }
//             // Thread.yield();
          
//             System.out.println("Child Thread: " + i+Thread.currentThread().getName());
//         }
//     }
// }

// public class Main {
//     public static void main(String[] args) throws Exception {
//         MyThread t1 = new MyThread();
//         t1.setName("task1");
//         t1.start();
//         MyThread t2=new MyThread();
//         t2.setName("task2");
//         t2.start();

//         // Thread.sleep(5000); 
//         t1.interrupt();
//         t2.interrupt();

//         // t1.join();   // main waits until t1 finishes
//         // here t1.join() is calling inside the main so main thread waits

//         Thread.yield();

//         System.out.println("Main Thread Finished");
//         for(int i=0;i<3;i++)
//         {
//             System.out.println("main thread");
//         }
//     }
// }.



// class MyThread extends Thread {
//     public void run() {
//         for (int i = 1; i <= 3; i++) {
//             try {
//                 Thread.sleep(1000);
//                 System.out.println(i);
//                 Thread.yield();
//             } catch (InterruptedException e) {
//                 System.out.println("Interrupted");
//             }
//         }
//     }
// }

// public class Main {
//     public static void main(String[] args) throws Exception {
//         MyThread t1 = new MyThread();

//         t1.start();

//         t1.join();

//         System.out.println("Main finished");
//     }
// }