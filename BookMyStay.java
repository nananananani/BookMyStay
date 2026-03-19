import java.util.*;
public class BookMyStay {
    
    public static void main(String[] args) {

        uc1_welcome();
        uc2_roomInitialization();
        uc3_inventoryManagement();
        uc4_searchRooms();
        uc5_bookingQueue();
        uc6_allocateRooms();
        uc7_addOnServices();
        uc8_bookingHistory();
        uc9_errorHandling();
        uc10_cancellationRollback();

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
// ================= UC6 =================
public static void uc6_allocateRooms() {

    Queue<Reservation> bookingQueue = new LinkedList<>();

    // Add sample requests
    bookingQueue.add(new Reservation("Alice", "Single"));
    bookingQueue.add(new Reservation("Bob", "Double"));
    bookingQueue.add(new Reservation("Charlie", "Single"));

    RoomInventory inventory = new RoomInventory();

    // Map room type → allocated IDs
    HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

    allocatedRooms.put("Single", new HashSet<>());
    allocatedRooms.put("Double", new HashSet<>());
    allocatedRooms.put("Suite", new HashSet<>());

    System.out.println("\n===== Room Allocation =====");

    int roomCounter = 1;

    while (!bookingQueue.isEmpty()) {

        Reservation request = bookingQueue.poll();

        String type = request.roomType;

        if (inventory.getAvailability(type) > 0) {

            // Generate unique room ID
            String roomId = type.substring(0, 1) + roomCounter++;

            // Ensure uniqueness
            if (!allocatedRooms.get(type).contains(roomId)) {

                allocatedRooms.get(type).add(roomId);

                // Update inventory
                inventory.updateAvailability(type, -1);

                System.out.println("Confirmed: " + request.guestName +
                        " → Room ID: " + roomId);

            }
        } else {

            System.out.println("No rooms available for " + request.guestName);
        }
    }
}
// ================= UC7 =================
public static void uc7_addOnServices() {

    // Map: Reservation ID → List of Services
    HashMap<String, List<Service>> serviceMap = new HashMap<>();

    // Example reservation ID (from UC6 concept)
    String reservationId = "S1";

    // Create services
    List<Service> services = new ArrayList<>();
    services.add(new Service("Breakfast", 200));
    services.add(new Service("Airport Pickup", 500));
    services.add(new Service("Extra Bed", 300));

    // Map services to reservation
    serviceMap.put(reservationId, services);

    System.out.println("\n===== Add-On Services =====");

    int totalCost = 0;

    for (Service s : serviceMap.get(reservationId)) {
        System.out.println("Service: " + s.name + " | Cost: " + s.cost);
        totalCost += s.cost;
    }

    System.out.println("Total Add-On Cost: " + totalCost);
}
// ================= UC8 =================
public static void uc8_bookingHistory() {

    BookingHistory history = new BookingHistory();

    // Simulate confirmed bookings (from UC6 concept)
    Reservation r1 = new Reservation("Alice", "Single");
    Reservation r2 = new Reservation("Bob", "Double");
    Reservation r3 = new Reservation("Charlie", "Suite");

    // Store in history
    history.addBooking(r1);
    history.addBooking(r2);
    history.addBooking(r3);

    // Display history
    history.displayHistory();

    // Generate report
    history.generateReport();
}
// ================= VALIDATION METHOD =================
public static void validateBooking(String roomType, RoomInventory inventory)
        throws InvalidBookingException {

    // Check valid room type
    if (!(roomType.equals("Single") ||
          roomType.equals("Double") ||
          roomType.equals("Suite"))) {

        throw new InvalidBookingException("Invalid room type selected!");
    }

    // Check availability
    if (inventory.getAvailability(roomType) <= 0) {
        throw new InvalidBookingException("No rooms available for " + roomType);
    }
}
// ================= UC9 =================
public static void uc9_errorHandling() {

    RoomInventory inventory = new RoomInventory();

    System.out.println("\n===== Error Handling & Validation =====");

    String[] testInputs = {"Single", "Luxury", "Suite"};

    for (String roomType : testInputs) {

        try {

            System.out.println("\nTrying booking for: " + roomType);

            validateBooking(roomType, inventory);

            System.out.println("Booking valid for " + roomType);

        } catch (InvalidBookingException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
// ================= UC10 =================
public static void uc10_cancellationRollback() {

    RoomInventory inventory = new RoomInventory();

    // Simulate allocated rooms
    HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
    allocatedRooms.put("Single", new HashSet<>());

    allocatedRooms.get("Single").add("S1");
    allocatedRooms.get("Single").add("S2");

    // Stack for rollback
    Stack<String> rollbackStack = new Stack<>();

    System.out.println("\n===== Booking Cancellation =====");

    String cancelRoomId = "S2";  // simulate cancellation

    // Check if room exists
    if (allocatedRooms.get("Single").contains(cancelRoomId)) {

        // Remove from allocated set
        allocatedRooms.get("Single").remove(cancelRoomId);

        // Push to rollback stack
        rollbackStack.push(cancelRoomId);

        // Restore inventory
        inventory.updateAvailability("Single", +1);

        System.out.println("Cancelled booking for Room ID: " + cancelRoomId);

    } else {

        System.out.println("Invalid cancellation request!");
    }

    // Show rollback stack
    System.out.println("\nRollback Stack:");
    for (String id : rollbackStack) {
        System.out.println(id);
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
// ================= SERVICE CLASS =================
class Service {

    String name;
    int cost;

    public Service(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
}
// ================= BOOKING HISTORY =================
class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add confirmed booking
    public void addBooking(Reservation r) {
        history.add(r);
    }

    // Display all bookings
    public void displayHistory() {

        System.out.println("\n===== Booking History =====");

        for (Reservation r : history) {
            r.display();
        }
    }

    // Generate simple report
    public void generateReport() {

        System.out.println("\n===== Booking Report =====");

        System.out.println("Total Bookings: " + history.size());
    }
}
// ================= CUSTOM EXCEPTION =================
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}
