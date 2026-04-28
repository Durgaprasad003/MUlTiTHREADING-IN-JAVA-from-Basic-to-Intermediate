package ticket_booking_system;

import java.util.Scanner;

public class main {
    public static void main(String[] args) throws InterruptedException {
         Scanner sc = new Scanner(System.in);
        TicketSystem system = new TicketSystem(10);

        while (true) {

            System.out.println("\n===== Ticket Booking System =====");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View All Bookings");
            System.out.println("4. View Available Seats");
            System.out.println("5. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("How many seats: ");
                    int seats = sc.nextInt();
                    sc.nextLine();

                    Thread t = new Thread(() -> {
                        system.bookTicket(name, seats);
                    });

                    t.start();

                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    break;

                case 2:
                    System.out.print("Enter Booking ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    system.cancelTicket(id);
                    break;

                case 3:
                    system.showAllBookings();
                    break;

                case 4:
                    system.showAvailableSeats();
                    break;

                case 5:
                    System.out.println("Thank you.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
