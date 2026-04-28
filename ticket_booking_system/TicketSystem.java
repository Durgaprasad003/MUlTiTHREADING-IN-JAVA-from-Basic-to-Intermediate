package ticket_booking_system;

import java.util.HashMap;
import java.util.Map;

public class TicketSystem {
       private int availableSeats;
    private int bookingCounter = 1001;
        private Map<Integer, Booking> bookings = new HashMap<>();

    public TicketSystem(int totalSeats) {
        this.availableSeats = totalSeats;
    }

    public synchronized void bookTicket(String name, int seatsWanted) {

        System.out.println("\nRequest from " + name +
                " for " + seatsWanted + " seat(s)");

        if (seatsWanted <= 0) {
            System.out.println("Invalid seat request");
            return;
        }

        if (availableSeats >= seatsWanted) {

            int id = bookingCounter++;

            Booking booking =
                    new Booking(id, name, seatsWanted);

            bookings.put(id, booking);

            availableSeats -= seatsWanted;

            System.out.println("Booking Success");
            System.out.println(booking);
            System.out.println("Remaining Seats: " + availableSeats);

        } else {
            System.out.println("Booking Failed for " + name);
            System.out.println("Not enough seats available");
        }
    }

    public synchronized void cancelTicket(int bookingId) {

        Booking booking = bookings.remove(bookingId);

        if (booking != null) {
            availableSeats += booking.getSeatsBooked();

            System.out.println("\nCancellation Success");
            System.out.println("Cancelled Booking ID: " + bookingId);
            System.out.println("Seats Restored: " + booking.getSeatsBooked());
            System.out.println("Remaining Seats: " + availableSeats);

        } else {
            System.out.println("\nInvalid Booking ID");
        }
    }

    public synchronized void showAllBookings() {

        System.out.println("\n===== ALL BOOKINGS =====");

        if (bookings.isEmpty()) {
            System.out.println("No bookings found");
            return;
        }

        for (Booking b : bookings.values()) {
            System.out.println(b);
        }
    }

    public synchronized void showAvailableSeats() {
        System.out.println("\nAvailable Seats: " + availableSeats);
    }

}
