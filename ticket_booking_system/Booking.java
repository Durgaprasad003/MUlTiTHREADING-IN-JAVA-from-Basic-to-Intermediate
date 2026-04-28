package ticket_booking_system;

public class Booking {
    private int bookingId;
    private String customername;
    private int seatsbooked;
    Booking(int bookingId,String customername,int seatsbooked)
    {
        this.bookingId=bookingId;
        this.customername=customername;
        this.seatsbooked=seatsbooked;
    }
    public int getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customername;
    }

    public int getSeatsBooked() {
        return seatsbooked;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
               ", Name: " + customername +
               ", Seats: " + seatsbooked;
    }
}
