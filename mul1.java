
// THIS IS  EXTENDS

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// public class mul1 extends Thread {

//     @Override
//     public void run()
//     {
//         System.out.println(Thread.currentThread().getName());
//         for(int i=0;i<5;i++)
//         {
//             System.out.println("thi is thread"+i);
//         }
//     }
//     public static void main(String[] args) {
//         System.out.println("hello this is main");
//         System.out.println(Thread.currentThread().getName());
//         // Thread t=new mul1();
//         mul1 t=new mul1();
//         t.start();
//             System.out.println("Main ends");
//             ExecutorService ex=Executors.newFixedThreadPool(5);
//             ex.submit(() -> System.out.println("Task"));
// ex.shutdown();

//     }
    
// }

// THIS IS IMPLEMENTS


// public class mul1 implements Runnable{

//     @Override
//     public void run() {
//         // TODO Auto-generated method stub
//         System.out.println(Thread.currentThread().getName());
//         for (int i = 0; i < 5; i++) {
//             System.out.println("Thread 1"+i);
//         }
//     }
//     public static void main(String[] args) {
//          System.out.println("this is main ");
//          System.out.println(Thread.currentThread().getName());
//          Thread obThread=new Thread(new mul1());
//          obThread.start();
//          for(int i=0;i<5;i++)
//          {
//             System.out.println("main thread");
//          }

//          System.out.println("main ended");




//     }

    
// }

// public class mul1 {

//      public static void main(String[] args) {

//         ExecutorService ex =
//             Executors.newFixedThreadPool(3);

//         for (int i = 1; i <= 5; i++) {
//             int taskNo = i;

//             ex.submit(() -> {
//                 System.out.println(
//                     "Task " + taskNo +
//                     " executed by " +
//                     Thread.currentThread().getName()
//                 );
//             });
//         }

//         ex.shutdown();
//     }
// }


public class mul1 extends Thread  {
 
    public void run()
    {
        System.out.println(Thread.currentThread().getName());
        for(int i=0;i<5;i++)
        {
            System.out.println("Thread "+i);
        }
    }
    public static void main(String[] args) {
        System.out.println("main method started");
        System.out.println(Thread.currentThread().getName());
        Thread t1=new mul1();
        t1.start();
        // t1.run();
    }
    
}