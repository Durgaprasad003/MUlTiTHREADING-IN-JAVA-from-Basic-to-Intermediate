import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ticket_booking_system.main;

public class prac {
    public static void main(String[] args) {
            ExecutorService ex = Executors.newFixedThreadPool(5);
            for (int i = 1; i <= 10; i++) {
    int taskId = i; // IMPORTANT (effectively final) //👉 Lambda requires effectively final variable
    ex.submit(() -> {
        System.out.println("Task " + taskId + " by " + Thread.currentThread().getName());
    });
}
    }
}
