import java.util.*;

// Booking Request
class BookingRequest3 {
    String guestName;
    String roomType;

    BookingRequest3(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Booking System
class BookingSystem {

    private Map<String, Integer> inventory = new HashMap<>();

    public BookingSystem() {
        inventory.put("Single", 2);
        inventory.put("Double", 1);
    }

    // synchronized critical section
    public synchronized void allocateRoom(BookingRequest request) {

        int available = inventory.getOrDefault(request.getRoomType(), 0);

        if (available > 0) {
            inventory.put(request.getRoomType(), available - 1);

            System.out.println(Thread.currentThread().getName() +
                    " booked " + request.getRoomType() +
                    " room for " + request.getGuestName());
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " failed booking for " + request.getGuestName() +
                    " (No " + request.getRoomType() + " rooms left)");
        }
    }

    public void showInventory() {
        System.out.println("\nFinal Inventory: " + inventory);
    }
}

// Thread class
class BookingThread extends Thread {

    BookingSystem system;
    BookingRequest request;

    BookingThread(BookingSystem system, BookingRequest request) {
        this.system = system;
        this.request = request;
    }

    public void run() {
        system.allocateRoom(request);
    }
}

// Main class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // booking requests
        BookingRequest r1 = new BookingRequest("Alice", "Single");
        BookingRequest r2 = new BookingRequest("Bob", "Single");
        BookingRequest r3 = new BookingRequest("Charlie", "Single");
        BookingRequest r4 = new BookingRequest("David", "Double");

        // multiple threads
        Thread t1 = new BookingThread(system, r1);
        Thread t2 = new BookingThread(system, r2);
        Thread t3 = new BookingThread(system, r3);
        Thread t4 = new BookingThread(system, r4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        system.showInventory();
    }
}