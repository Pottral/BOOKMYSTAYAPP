import java.util.*;

class Reservation {
    String id;
    String roomType;
    String roomId;

    Reservation(String id, String roomType) {
        this.id = id;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

public class UseCase10BookingCancellation {

    static Map<String, Reservation> bookings = new HashMap<>();
    static Map<String, Integer> inventory = new HashMap<>();
    static Stack<String> releasedRooms = new Stack<>();

    public static void cancelBooking(String reservationId) {

        if (!bookings.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation not found.");
            return;
        }

        Reservation r = bookings.get(reservationId);

        // rollback room
        releasedRooms.push(r.roomId);

        // restore inventory
        inventory.put(r.roomType, inventory.get(r.roomType) + 1);

        bookings.remove(reservationId);

        System.out.println("Booking " + reservationId + " cancelled. Room released: " + r.roomId);
    }

    public static void main(String[] args) {

        // initial inventory
        inventory.put("Single", 1);
        inventory.put("Double", 1);

        // confirmed bookings
        bookings.put("R101", new Reservation("R101", "Single"));
        bookings.put("R102", new Reservation("R102", "Double"));

        cancelBooking("R101"); // valid cancellation
        cancelBooking("R200"); // invalid cancellation

        System.out.println("\nInventory After Cancellation:");
        System.out.println(inventory);

        System.out.println("\nReleased Rooms Stack:");
        System.out.println(releasedRooms);
    }
}