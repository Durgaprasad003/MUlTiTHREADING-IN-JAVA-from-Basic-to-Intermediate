import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class mul3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//         Thread t = new Thread(() -> {
//     System.out.println("Child Thread");
// });

// t.start();


// for(int i=1;i<=5;i++) {
//     System.out.println(i);
//     Thread.sleep(1000);
// }



// Thread x = new Thread(() -> {
//     try {
//         Thread.sleep(5000);
//         System.out.println('h');
//     } catch (InterruptedException e) {
//         System.out.println("Interrupted");
//     }
// });

// x.start();
// Thread.sleep(1000);
// x.interrupt();




// Thread t1 = new Thread(() -> {
//     try {
//         for(int i=1;i<=3;i++) {
//             System.out.println("A " + i);
//             Thread.sleep(500);
//         }
//     } catch(Exception e){}
// });

// Thread t2 = new Thread(() -> {
//     try {
//         for(int i=1;i<=3;i++) {
//             System.out.println("B " + i);
//             Thread.sleep(500);
//         }
//     } catch(Exception e){}
// });

// t1.start();
// t2.start();



Thread t = new Thread(() -> {
    try {
        Thread.sleep(2000);
        System.out.println("Done");
    } catch(Exception e){}
});

// t.start();
// t.join();

// System.out.println("Finished");


// Thread t1 = new Thread(() -> System.out.println("Low"));
// Thread t2 = new Thread(() -> System.out.println("High"));

// t1.setPriority(1);
// t2.setPriority(10);

// t1.start();
// t2.start();



// for(int i=1;i<=3;i++) {
//     int n = i;
//     new Thread(() ->
//         System.out.println("Task " + n)
//     ).start();
// }






// ******************************************* 

    // Callable<Integer> task = () -> {
    //         return 10 + 20;
    //     };

    //     ExecutorService service = Executors.newSingleThreadExecutor();

    //     Future<Integer> result = service.submit(task);

    //     System.out.println("Result: " + result.get());

    //     service.shutdown();



//   Callable<String> task = () -> {
//             Thread.sleep(2000);
//             return "Task Completed";
//         };

//         ExecutorService service = Executors.newSingleThreadExecutor();

//         Future<String> future = service.submit(task);

//         System.out.println("Waiting...");
//         System.out.println(future.get());

//         service.shutdown();


ExecutorService ex = Executors.newSingleThreadExecutor();


Future<Integer> f = ex.submit(() -> {
    Thread.sleep(2000);
    return 10;
});

while(!f.isDone()) {
    System.out.println("Waiting...");
}

System.out.println(f.get());



    }
}



