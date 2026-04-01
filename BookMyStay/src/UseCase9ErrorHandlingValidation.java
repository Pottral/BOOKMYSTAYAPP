import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory class
class Inventory {

    private Map<String, Integer> rooms = new HashMap<>();

    public Inventory() {
        rooms.put("Single", 2);
        rooms.put("Double", 1);
        rooms.put("Suite", 0);
    }

    public void bookRoom(String roomType) throws InvalidBookingException {

        // Validate room type
        if (!rooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        int available = rooms.get(roomType);

        // Prevent negative inventory
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType);
        }

        // Update inventory
        rooms.put(roomType, available - 1);

        System.out.println("Booking confirmed for " + roomType + " room.");
    }

    public void showInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : rooms.keySet()) {
            System.out.println(type + " : " + rooms.get(type));
        }
    }
}

// Main class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        String[] bookingRequests = {
                "Single",
                "Suite",
                "Double",
                "Penthouse"   // invalid room
        };

        for (String request : bookingRequests) {

            try {
                System.out.println("\nRequesting: " + request);
                inventory.bookRoom(request);
            }

            catch (InvalidBookingException e) {
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }

        inventory.showInventory();
    }
}