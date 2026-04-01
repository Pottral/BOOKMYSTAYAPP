import java.io.*;
import java.util.*;

class Reservation6 implements Serializable {
    String guest;
    String roomType;

    Reservation6(String guest, String roomType) {
        this.guest = guest;
        this.roomType = roomType;
    }

    public String toString() {
        return guest + " booked " + roomType;
    }
}

public class UseCase12DataPersistenceRecovery {

    static String FILE = "hotelData.txt";

    public static void saveData(Map<String,Integer> inventory, List<Reservation6> bookings) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE));
            out.writeObject(inventory);
            out.writeObject(bookings);
            out.close();

            System.out.println("Data saved to file.");

        } catch(Exception e) {
            System.out.println("Error saving data.");
        }
    }

    public static void loadData() {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE));

            Map<String,Integer> inventory = (Map<String,Integer>) in.readObject();
            List<Reservation6> bookings = (List<Reservation6>) in.readObject();

            in.close();

            System.out.println("\nRecovered Inventory: " + inventory);
            System.out.println("Recovered Bookings:");

            for(Reservation6 r : bookings)
                System.out.println(r);

        } catch(Exception e) {
            System.out.println("No saved data found. Starting new system.");
        }
    }

    public static void main(String[] args) {

        Map<String,Integer> inventory = new HashMap<>();
        inventory.put("Single",2);
        inventory.put("Double",1);

        List<Reservation6> bookings = new ArrayList<>();
        bookings.add(new Reservation6("Alice","Single"));
        bookings.add(new Reservation6("Bob","Double"));

        // save system state
        saveData(inventory, bookings);

        System.out.println("\n--- System Restart ---");

        // load system state
        loadData();
    }
}
