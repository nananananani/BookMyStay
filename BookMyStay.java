import java.util.*;
public class BookMyStay {
    
    public static void main(String[] args) {

        uc1_welcome();
        uc2_roomInitialization();
        uc3_inventoryManagement();
        uc4_searchRooms();
        uc5_bookingQueue();

    }

    // UC1
    public static void uc1_welcome() {

        System.out.println("===================================");
        System.out.println("   Welcome to Book My Stay App   ");
        System.out.println("   Hotel Booking System v1.0     ");
        System.out.println("===================================");

        System.out.println("Application started successfully.");
    }
    // ================= UC2 =================
    public static void uc2_roomInitialization() {

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("\n===== Room Details =====");

        single.displayDetails();
        System.out.println("Available: " + singleAvailable);

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable);

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
    // ================= UC3 =================
public static void uc3_inventoryManagement() {

    RoomInventory inventory = new RoomInventory();

    System.out.println("\n===== Inventory Status =====");

    System.out.println("Single Rooms: " + inventory.getAvailability("Single"));
    System.out.println("Double Rooms: " + inventory.getAvailability("Double"));
    System.out.println("Suite Rooms: " + inventory.getAvailability("Suite"));

    // Update inventory
    inventory.updateAvailability("Single", -1);

    System.out.println("\nAfter Booking 1 Single Room:");

    System.out.println("Single Rooms: " + inventory.getAvailability("Single"));
}
// ================= UC4 =================
public static void uc4_searchRooms() {

    RoomInventory inventory = new RoomInventory();

    System.out.println("\n===== Available Rooms =====");

    // Room objects
    Room single = new SingleRoom();
    Room doubleRoom = new DoubleRoom();
    Room suite = new SuiteRoom();

    // Check and display only available rooms
    if (inventory.getAvailability("Single") > 0) {
        single.displayDetails();
        System.out.println("Available: " + inventory.getAvailability("Single"));
    }

    if (inventory.getAvailability("Double") > 0) {
        doubleRoom.displayDetails();
        System.out.println("Available: " + inventory.getAvailability("Double"));
    }

    if (inventory.getAvailability("Suite") > 0) {
        suite.displayDetails();
        System.out.println("Available: " + inventory.getAvailability("Suite"));
    }
}
// ================= UC5 =================
public static void uc5_bookingQueue() {

    Queue<Reservation> bookingQueue = new LinkedList<>();

    // Add booking requests
    bookingQueue.add(new Reservation("Alice", "Single"));
    bookingQueue.add(new Reservation("Bob", "Double"));
    bookingQueue.add(new Reservation("Charlie", "Suite"));

    System.out.println("\n===== Booking Requests (FIFO Order) =====");

    // Display queue (DO NOT REMOVE)
    for (Reservation r : bookingQueue) {
        r.display();
    }
}
}
// ================= ABSTRACT CLASS =================
abstract class Room {

    String type;
    int beds;
    double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("\nRoom Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Price: $" + price);
    }
}
// ================= SINGLE ROOM =================
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 1000);
    }
}
// ================= DOUBLE ROOM =================
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 1800);
    }
}
// ================= SUITE ROOM =================
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 3000);
    }
}


class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor → initialize inventory
    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {

        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int change) {

        int current = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, current + change);
    }
}
// ================= RESERVATION CLASS =================
class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}
