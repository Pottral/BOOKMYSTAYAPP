import java.util.*;

// Booking request class
class reservation {

    String guestName;
    String roomType;

    public void BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory Service
class InventoryService {

    private final Map<String, Integer> availability;

    public InventoryService() {
        availability = new HashMap<>();
        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);
    }

    public boolean isAvailable(String roomType) {
        return availability.get(roomType) > 0;
    }

    public void decreaseRoom(String roomType) {
        availability.put(roomType, availability.get(roomType) - 1);
    }

    public Map<String, Integer> getAvailability() {
        return availability;
    }
}

// Room Allocation Service
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        Queue<BookingRequest> requestQueue = new LinkedList<>();

        // Sample booking requests
        requestQueue.add(new BookingRequest("Alice", "Single"));
        requestQueue.add(new BookingRequest("Bob", "Double"));
        requestQueue.add(new BookingRequest("Charlie", "Suite"));

        InventoryService inventory = new InventoryService();

        // Map to store allocated rooms
        Map<String, Set<String>> allocatedRooms = new HashMap<>();

        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());

        System.out.println("Processing Reservations\n");

        int roomCounter = 1;

        while (!requestQueue.isEmpty()) {

            BookingRequest request = requestQueue.poll();

            String type = request.getRoomType();

            if (inventory.isAvailable(type)) {

                String roomId = type.substring(0,1) + roomCounter++;

                allocatedRooms.get(type).add(roomId);

                inventory.decreaseRoom(type);

                System.out.println("Reservation Confirmed");
                System.out.println("Guest: " + request.getGuestName());
                System.out.println("Room Type: " + type);
                System.out.println("Assigned Room ID: " + roomId);
                System.out.println();
            }
            else {
                System.out.println("No rooms available for " + type);
            }
        }

        System.out.println("Allocated Room Summary:");

        for (String type : allocatedRooms.keySet()) {
            System.out.println(type + " Rooms: " + allocatedRooms.get(type));
        }
    }
}