public class CentralizedRoom {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Book My Stay App ");
        System.out.println(" Version: 3.1 ");
        System.out.println("=====================================");

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Simulate updates
        System.out.println("\nUpdating Inventory...\n");

        inventory.updateAvailability("Single Room", -1); // Booking
        inventory.updateAvailability("Double Room", -2); // Booking
        inventory.updateAvailability("Suite Room", 1);   // Cancellation

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nApplication execution completed.");
    }
}
