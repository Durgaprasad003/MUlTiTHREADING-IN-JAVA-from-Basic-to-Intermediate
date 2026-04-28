class machine {
    int balance = 1000;

    synchronized void withdraw(String name, int amt) {
        System.out.println(name + " is trying to withdraw ₹" + amt);

        if (balance >= amt) {
            balance -= amt;
            System.out.println(name + " successfully withdrew ₹" + amt);
            System.out.println("Remaining Balance: ₹" + balance);
        } else {
            System.out.println(name + " -> Insufficient Balance");
        }

        System.out.println();
    }

    synchronized void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }
}

public class Atm {
    public static void main(String[] args) throws InterruptedException {

        machine atm = new machine();

        Thread user1 = new Thread(() -> {
            atm.withdraw("Prasad", 400);
        });

        Thread user2 = new Thread(() -> {
            atm.withdraw("Ravi", 500);
        });

        Thread user3 = new Thread(() -> {
            atm.withdraw("John", 300);
        });

        user1.start();
        user2.start();
        user3.start();

        user1.join();
        user2.join();
        user3.join();

        atm.checkBalance();
    }
}