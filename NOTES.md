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

No. Runnable is not a thread. It is an interface in Java that represents a task or job to be executed.*****
Because Mul implements Runnable, it is not a thread. It is only a task that contains the run() method.
start() method belongs to the Thread class, not Runnable. So Mul obj = new Mul(); obj.start(); is invalid.


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



t.start(); // new thread created
t.run();   // normal method call******************

THREAD LIFE CYLCE ******************************************
NEW → RUNNABLE → RUNNING → WAITING/BLOCKED/TIMED_WAITING → TERMINATED

1. NEW State

Thread object is created, but not started yet.

Thread t = new Thread();

Now thread is in NEW state.

It has not begun execution.

2. RUNNABLE State

After calling:

t.start();

Thread enters RUNNABLE state.

Means:

Ready to run
Waiting for CPU scheduling

Java combines “ready” and “running” under RUNNABLE.

3. RUNNING State

When CPU scheduler selects thread, it executes run().

public void run() {
   // code executing
}

This is commonly called running state.

(Official Java enum still reports RUNNABLE.)

4. BLOCKED State

Thread waits to acquire a monitor lock.

Example:

synchronized(obj) {
}

If another thread already holds lock, it becomes BLOCKED.

5. WAITING State

Thread waits indefinitely until another thread wakes it.

Examples:

obj.wait();
t.join();

Needs another thread action like notify() or thread completion.

6. TIMED_WAITING State

Thread waits for a specific time.

Examples:

Thread.sleep(1000);
t.join(2000);

After time ends, it returns to runnable.

7. TERMINATED State

When run() finishes or thread dies due to exception.

Then thread cannot restart.

t.start(); // once only

Calling start() again causes exception



Thread t = new Thread(() -> {
    try {
        Thread.sleep(1000);
    } catch(Exception e) {}
});

System.out.println(t.getState()); // NEW

t.start();

System.out.println(t.getState()); // RUNNABLE / TIMED_WAITING

t.join();

System.out.println(t.getState()); // TERMINATED




THREAD METHODS

start()
run()
sleep(ms)
Pauses current thread for some time.
Thread.sleep(2000);   // sleep for 2 seconds
When to Use:
Delay execution
Timer tasks
Animation
Retry after waiting
Reduce CPU usage in loops


join()
join() makes the current thread wait until another thread finishes.
When to Use:
Use when:
One task depends on another task finishing first
Main thread should wait for worker thread
Need ordered execution


yield()
Current thread says:
"If another thread needs CPU, I can pause."   
It gives chance to other same-priority threads.
When to Use:
Improve fairness between threads
Long-running loop thread can give chance to others



interrupt()
isAlive()
getName()
setName()
currentThread()
getPriority()
setPriority()




wait() is in the Object class because it is used for object-level synchronization, not for thread execution control.
Methods like start(), run(), sleep(), and yield() control the behavior of the current thread, so they belong to the Thread class.
But wait() is different—it makes a thread wait on a shared object's lock (monitor) until another thread calls notify() or notifyAll() on the same object. Since every Java object can be used as a lock, wait() is placed in the Object class.
One-line answer:
“Thread methods control thread execution, but wait() works with an object's monitor lock, so it belongs to Object class, not Thread class.”


volatile vs synchronized
volatile	synchronized
Visibility only	Visibility + atomicity
No lock	Locking


Race Condition
Multiple threads modify shared data incorrectly.

6. Solution = Synchronization
Synchronization means:
Only one thread can access critical section at a time.



🔹 What is a Lock?
A lock is like a permission token that allows only one thread at a time to use shared code/data.
If one thread has the lock:
other threads must wait
they cannot enter that synchronized area




How synchronized Works Internally******************************

When thread enters:

synchronized void increment()

Java gives lock of object.
Other thread must wait.
When first thread exits:
Lock released.
Second thread enters.



A synchronized block lets you lock only a specific section of code instead of an entire method.

It means:

Only one thread at a time can execute that block using the same lock object.

Basic Syntax
synchronized(lockObject) {
    // critical section
}
lockObject = object used as monitor/lock
Other threads using the same lock must wait
Your Example

Use obj as lock:

synchronized(obj) {
    obj.increment();
    System.out.println(obj.count);
}

Now increment + print happen together safely.




class Counter {
    int count = 0;

    void increment() {
        count++;
    }
}

public class Mul2 {
    public static void main(String[] args) throws Exception {

        Counter obj = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized(obj) {        ********************
                    obj.increment();
                    System.out.println("Thread 1 : " + obj.count);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized(obj) {
                    obj.increment();
                    System.out.println("Thread 2 : " + obj.count);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}



Atomicity (not “automacity”) means an operation happens as one indivisible unit.

It either:

completes fully, or
does not happen at all.

No other thread can see it half-finished.



15. Deadlock

Two threads waiting forever.

Thread1 locks A then waits B
Thread2 locks B then waits A
Example
class A {}
class B {}

A a = new A();
B b = new B();

Thread t1 = new Thread(() -> {
    synchronized(a) {
        synchronized(b) {
            System.out.println("T1 done");
        }
    }
});

Thread t2 = new Thread(() -> {
    synchronized(b) {
        synchronized(a) {
            System.out.println("T2 done");
        }
    }
});

Danger: deadlock.

Prevention

Always lock in same order.





16. wait(), notify(), notifyAll()

Used for thread communication.

Must be inside synchronized block.





This is explicit locking in Java using ReentrantLock.

It is an alternative to synchronized.

What It Is
ReentrantLock lock = new ReentrantLock();

Creates a lock object.

Then:

lock.lock();

Current thread acquires the lock.

Only one thread can hold it at a time.

Other threads calling lock.lock() must wait.

Why Use It

Used to protect shared resources in multithreading.

Example:

bank balance
ticket booking
counter
file writing
shared list

Without locking:

race conditions
corrupted data
wrong results
Why Not Just synchronized?

ReentrantLock gives more control.

Extra Features
1. Try Lock
lock.tryLock()

Try acquiring lock without waiting forever.

2. Fair Locking

Threads get lock in queue order.

3. Interruptible Lock

Can interrupt waiting thread.

4. Manual Unlocking

You choose exactly when to release.

Why try-finally
lock.lock();
try {
   // critical section
} finally {
   lock.unlock();
}

Ensures lock always releases even if exception happens.

Very important.

Full Example (Counter)
import java.util.concurrent.locks.ReentrantLock;

class Counter {

    int count = 0;
    ReentrantLock lock = new ReentrantLock();

    void increment(String name) {

        lock.lock();

        try {
            System.out.println(name + " got lock");

            for (int i = 1; i <= 3; i++) {
                count++;
                System.out.println(name + " count = " + count);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }

            System.out.println(name + " releasing lock\n");

        } finally {
            lock.unlock();
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Counter obj = new Counter();

        Thread t1 = new Thread(() -> obj.increment("Thread-1"));
        Thread t2 = new Thread(() -> obj.increment("Thread-2"));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Count = " + obj.count);
    }
}
Output (Possible)
Thread-1 got lock
Thread-1 count = 1
Thread-1 count = 2
Thread-1 count = 3
Thread-1 releasing lock

Thread-2 got lock
Thread-2 count = 4
Thread-2 count = 5
Thread-2 count = 6

Final Count = 6
What Means Reentrant?

Same thread can acquire same lock multiple times.

Example:

lock.lock();
lock.lock();

Allowed.

But must unlock same number of times.






AtomicInteger is used when multiple threads need to safely update an integer without using synchronized blocks.

It provides atomic operations like increment, decrement, compare-and-set.


Problem Example
int count = 0;

Two threads each increment 1000 times.

Expected:

2000

Actual maybe:

1734
1890
1961

Race condition.
Solution: AtomicInteger
AtomicInteger count = new AtomicInteger(0);

Increment safely:

count.incrementAndGet();

Now threads can update safely without lost increments.




import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger count = new AtomicInteger(0);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Count = " + count.get());
    }
}



