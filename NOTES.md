1. What is Thread?

A thread is a lightweight sub-process.

It allows program to perform multiple tasks simultaneously.

Example:

One thread downloads file
One thread updates UI
One thread logs data

Java supports multithreading.

2. Process vs Thread
Process	             Thread
Independent          program	Small unit inside process
Heavyweight	        Lightweight
Separate memory	    Shared memory
Slow creation	    Fast creation

Interview favorite.

3. Why Multithreading?

Used for:

✅ Performance
✅ Parallel tasks
✅ Better CPU usage
✅ Responsive applications
✅ Async processing
✅ Background jobs


class MyThread extends Thread {
   public void run() {
      System.out.println("Running");
   }
}

Because main thread and child thread run concurrently. Once you call start(), the JVM scheduler decides when each thread gets CPU time. There is no guarantee that main ended prints last.************************************

When a class implements Runnable, that class is not a thread. It only contains the task/code to run.
To actually run that task in a new thread, you must give the Runnable object to a Thread.******************************
Core Difference

If class extends Thread
class Mul1 extends Thread
Then object itself is a thread:
new Mul1().start();
If class implements Runnable
class Mul1 implements Runnable
Then object is only a task:
new Thread(new Mul1()).start();




ExecutorService is one of the most important concurrency utilities in Java. It manages a pool of threads so you don’t have to create and control threads manually.
Instead of doing this repeatedly:
new Thread(task1).start();
new Thread(task2).start();
new Thread(task3).start();

1. Why ExecutorService Exists

Creating threads manually has problems:

Expensive to create many threads
Hard to manage lifecycle
Hard to limit concurrent threads
Hard to collect results
Hard to stop gracefully



ExecutorService service = Executors.newFixedThreadPool(3);

This creates a thread pool with 3 worker threads.
Meaning: maximum 3 tasks run at the same time.

. Submit a Task
service.execute(() -> {
    System.out.println("Task running");
});

or

service.submit(() -> {
    System.out.println("Task running");
});

5. execute() vs submit()

execute()
Used for Runnable

service.execute(task);
No return value
Fire and forget

submit()
Used for Runnable or Callable

Future<Integer> f = service.submit(task);
Can return result
Gives Future

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService service =
            Executors.newFixedThreadPool(2);

        for(int i = 1; i <= 5; i++) {
            int num = i;

            service.execute(() -> {
                System.out.println(
                    "Task " + num +
                    " " +
                    Thread.currentThread().getName()
                );
            });
        }

        service.shutdown();
    }
}



