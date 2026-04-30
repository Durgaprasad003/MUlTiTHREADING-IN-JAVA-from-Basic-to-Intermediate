// 🔹 Program 1: Two Objects (Different Locks)

// 👉 Each object has its own intrinsic lock


// class Printer {
//     synchronized void print(String name) {
//         for (int i = 1; i <= 3; i++) {
//             System.out.println(name + " printing " + i);
//             try { Thread.sleep(500); } catch (Exception e) {}
//         }
//     }
// }

// public class Test1 {
//     public static void main(String[] args) {

//         Printer p1 = new Printer(); // Lock 1
//         Printer p2 = new Printer(); // Lock 2

//         Thread t1 = new Thread(() -> p1.print("Thread-1"));
//         Thread t2 = new Thread(() -> p2.print("Thread-2"));

//         t1.start();
//         t2.start();
//     }
// }

// // 🔥 Output (Interleaved / Parallel)
// // Thread-1 printing 1
// // Thread-2 printing 1
// // Thread-1 printing 2
// // Thread-2 printing 2
// // ...

// // 👉 Why?

// // p1 and p2 → different locks
// // No blocking → both run simultaneously







// 🔹 Program 2: Same Object (Same Lock)

// 👉 Both threads share the same lock

// class Printer {
//     synchronized void print(String name) {
//         for (int i = 1; i <= 3; i++) {
//             System.out.println(name + " printing " + i);
//             try { Thread.sleep(500); } catch (Exception e) {}
//         }
//     }
// }

// public class Test2 {
//     public static void main(String[] args) {

//         Printer p = new Printer(); // Single shared lock

//         Thread t1 = new Thread(() -> p.print("Thread-1"));
//         Thread t2 = new Thread(() -> p.print("Thread-2"));

//         t1.start();
//         t2.start();


// 🔥 Output (Sequential)
// Thread-1 printing 1
// Thread-1 printing 2
// Thread-1 printing 3
// Thread-2 printing 1
// Thread-2 printing 2
// Thread-2 printing 3

// 👉 Why?

// Same object → same lock
// One thread blocks the other



// 27. Nested Locks (Danger)
// synchronized(A){
//    synchronized(B){}
// }

// Can lead to deadlock if another thread uses reverse order.

// (Next topic later deeply)