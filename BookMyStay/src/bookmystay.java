import java.util.*;

// Booking request class
class BookingRequest {

    private String guestName;
    private String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Booking queue service
class BookingRequestQueue {

    private Queue<BookingRequest> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(BookingRequest request) {
        queue.offer(request);
        System.out.println(request.getGuestName() + " requested " + request.getRoomType() + " room");
    }

    // Process booking request
    public void processRequest() {

        if (queue.isEmpty()) {
            System.out.println("No booking requests in queue");
            return;
        }

        BookingRequest request = queue.poll();

        System.out.println("Processing booking for " + request.getGuestName()
                + " (" + request.getRoomType() + " room)");
    }

    // Display pending requests
    public void showPendingRequests() {

        System.out.println("\nPending Requests:");

        for (BookingRequest r : queue) {
            System.out.println(r.getGuestName() + " - " + r.getRoomType());
        }
    }
}

// Main class
public class bookmystay {

    public static void main(String[] args) {

        BookingRequestQueue queue = new BookingRequestQueue();

        // Add booking requests
        queue.addRequest(new BookingRequest("Alice", "Single"));
        queue.addRequest(new BookingRequest("Bob", "Double"));
        queue.addRequest(new BookingRequest("Charlie", "Suite"));

        System.out.println();

        // Process requests (First Come First Served)
        queue.processRequest();
        queue.processRequest();

        // Show remaining requests
        queue.showPendingRequests();
    }
}