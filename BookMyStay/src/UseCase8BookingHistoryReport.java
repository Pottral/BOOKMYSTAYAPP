import java.util.*;

// Reservation class
class Reservation2 {

    public String roomId;
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation2(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room Type: " + roomType;
    }
}

// Booking History class
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    // store confirmed reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // retrieve history
    public List<Reservation> getHistory() {
        return history;
    }
}

// Report Service
class BookingReportService {

    public void generateReport(List<Reservation> reservations) {

        System.out.println("Booking History Report\n");

        for (Reservation r : reservations) {
            System.out.println(r);
        }

        System.out.println("\nTotal Bookings: " + reservations.size());
    }
}

// Main class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // confirmed reservations
        history.addReservation(new Reservation("R101", "Alice", "Single"));
        history.addReservation(new Reservation("R102", "Bob", "Double"));
        history.addReservation(new Reservation("R103", "Charlie", "Suite"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history.getHistory());
    }
}