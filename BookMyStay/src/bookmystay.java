import java.util.*;

// Main class
public class bookmystay {

    public static void main(String[] args) {

        Room singleRoom = new Room(1, 250, 1500.0);
        Room doubleRoom = new Room(2, 400, 2500.0);
        Room suiteRoom = new Room(3, 750, 5000.0);

        RoomInventory inventory = new RoomInventory();

        RoomSearchService service = new RoomSearchService();

        service.searchAvailableRooms(inventory, singleRoom, doubleRoom, suiteRoom);
    }
}